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
package de.dentrassi.pm.storage.web.internal;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import de.dentrassi.pm.aspect.ChannelAspectProcessor;
import de.dentrassi.pm.aspect.recipe.RecipeProcessor;
import de.dentrassi.pm.storage.web.services.ServiceTracker;

public class Activator implements BundleActivator
{

    private static Activator INSTANCE;

    private ServiceTracker tracker;

    private ChannelAspectProcessor aspects;

    private RecipeProcessor recipes;

    /*
     * (non-Javadoc)
     * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
     */
    @Override
    public void start ( final BundleContext bundleContext ) throws Exception
    {
        Activator.INSTANCE = this;
        this.tracker = new ServiceTracker ( bundleContext );
        this.tracker.open ();
        this.aspects = new ChannelAspectProcessor ( bundleContext );
        this.recipes = new RecipeProcessor ( bundleContext );
    }

    /*
     * (non-Javadoc)
     * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
     */
    @Override
    public void stop ( final BundleContext bundleContext ) throws Exception
    {
        this.tracker.close ();
        this.aspects.close ();
        this.recipes.dispose ();

        Activator.INSTANCE = null;
    }

    public static ServiceTracker getTracker ()
    {
        return INSTANCE.tracker;
    }

    public static ChannelAspectProcessor getAspects ()
    {
        return INSTANCE.aspects;
    }

    public static RecipeProcessor getRecipes ()
    {
        return INSTANCE.recipes;
    }
}
