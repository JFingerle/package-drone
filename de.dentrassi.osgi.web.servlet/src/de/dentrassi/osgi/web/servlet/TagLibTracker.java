/*******************************************************************************
 * Copyright (c) 2014, 2015 IBH SYSTEMS GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBH SYSTEMS GmbH - initial API and implementation
 *******************************************************************************/
package de.dentrassi.osgi.web.servlet;

import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.util.tracker.BundleTracker;
import org.osgi.util.tracker.BundleTrackerCustomizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dentrassi.osgi.utils.AttributedValue;
import de.dentrassi.osgi.utils.Headers;

public class TagLibTracker implements ResourceProvider
{
    private final static Logger logger = LoggerFactory.getLogger ( TagLibTracker.class );

    private final Set<String> systemTlds = new HashSet<> ();

    private final BundleTracker<TagLibInfo> bundleTracker;

    private final BundleTrackerCustomizer<TagLibInfo> customizer = new BundleTrackerCustomizer<TagLibInfo> () {

        @Override
        public TagLibInfo addingBundle ( final Bundle bundle, final BundleEvent event )
        {
            return createTagLibInfo ( bundle );
        }

        @Override
        public void modifiedBundle ( final Bundle bundle, final BundleEvent event, final TagLibInfo object )
        {
        }

        @Override
        public void removedBundle ( final Bundle bundle, final BundleEvent event, final TagLibInfo object )
        {
        }
    };

    private static class TagLibInfo
    {
        private final Map<String, URL> tlds;

        public TagLibInfo ( final Map<String, URL> tlds )
        {
            this.tlds = tlds;
        }

        @Override
        public String toString ()
        {
            return String.format ( "[TagLib - tlds: %s]", this.tlds );
        }

        public Set<String> getEntries ()
        {
            return this.tlds.keySet ();
        }

        public Map<String, URL> getTlds ()
        {
            return this.tlds;
        }
    }

    public TagLibTracker ( final BundleContext context )
    {
        this.systemTlds.add ( "org.apache.taglibs.standard-impl" );

        this.bundleTracker = new BundleTracker<> ( context, Bundle.RESOLVED | Bundle.ACTIVE, this.customizer );
        this.bundleTracker.open ();
    }

    @Override
    public void dispose ()
    {
        this.bundleTracker.close ();
    }

    protected void fillFromExportHeader ( final Bundle bundle, final String headerName, final Map<String, URL> tlds )
    {
        final String tldHeader = bundle.getHeaders ().get ( headerName );
        if ( tldHeader == null )
        {
            return;
        }

        final List<AttributedValue> list = Headers.parseList ( tldHeader );
        for ( final AttributedValue av : list )
        {
            String tld = av.getValue ();
            if ( !tld.startsWith ( "/" ) )
            {
                tld = "/" + tld;
            }
            final URL entry = bundle.getEntry ( tld );
            if ( entry == null )
            {
                logger.warn ( "Failed to resolve - {}", tld );
            }
            else
            {
                final String key = makeKey ( tld );
                logger.info ( "Found tag lib  {} in bundle {} (as '{}')", tld, bundle, key );
                tlds.put ( key, entry );
            }
        }
    }

    private void fillFromSystemBundle ( final Bundle bundle, final Map<String, URL> tlds )
    {
        if ( !this.systemTlds.contains ( bundle.getSymbolicName () ) )
        {
            return;
        }

        final Enumeration<String> paths = bundle.getEntryPaths ( "/META-INF/" );
        while ( paths.hasMoreElements () )
        {
            final String name = paths.nextElement ();
            if ( name.endsWith ( ".tld" ) )
            {
                final String key = makeKey ( name );
                final URL entry = bundle.getEntry ( name );
                if ( entry != null )
                {
                    logger.debug ( "Add system mapping {} -> {} / {}", key, name, entry );
                    tlds.put ( key, entry );
                }
            }
        }
    }

    protected TagLibInfo createTagLibInfo ( final Bundle bundle )
    {
        logger.trace ( "Checking for tag lib directories: {}", bundle );

        final Map<String, URL> tlds = new HashMap<> ();

        fillFromExportHeader ( bundle, "Web-Export-Taglib", tlds );
        fillFromSystemBundle ( bundle, tlds );

        if ( tlds.isEmpty () )
        {
            return null;
        }
        else
        {
            return new TagLibInfo ( tlds );
        }
    }

    private static String makeKey ( final String tld )
    {
        final String[] toks = tld.split ( "\\/" );
        return toks[toks.length - 1];
    }

    public void open ()
    {
        this.bundleTracker.open ();
    }

    public void close ()
    {
        this.bundleTracker.close ();
    }

    @Override
    public Set<String> getPaths ( final String path )
    {
        if ( !path.equals ( "/WEB-INF/" ) )
        {
            return null;
        }

        final Set<String> result = new HashSet<> ();

        for ( final TagLibInfo tli : this.bundleTracker.getTracked ().values () )
        {
            for ( final String name : tli.getEntries () )
            {
                result.add ( "/WEB-INF/" + name );
            }
        }

        return result;
    }

    @Override
    public URL getResource ( final String name )
    {
        logger.trace ( "Getting resource: {}", name );

        if ( !name.startsWith ( "/WEB-INF" ) )
        {
            return null;
        }

        final String tldName = name.substring ( "/WEB-INF/".length () );

        for ( final TagLibInfo tli : this.bundleTracker.getTracked ().values () )
        {
            final URL result = tli.getTlds ().get ( tldName );
            if ( result != null )
            {
                return result;
            }
        }

        return null;
    }
}
