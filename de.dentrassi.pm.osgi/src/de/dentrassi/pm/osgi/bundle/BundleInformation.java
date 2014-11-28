/*******************************************************************************
 * Copyright (c) 2014 Jens Reimann.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Jens Reimann - initial API and implementation
 *******************************************************************************/
package de.dentrassi.pm.osgi.bundle;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.osgi.framework.Version;
import org.osgi.framework.VersionRange;

public class BundleInformation
{
    public static class PackageImport
    {
        private final String name;

        private final VersionRange versionRange;

        private final boolean optional;

        public PackageImport ( final String name, final VersionRange versionRange, final boolean optional )
        {
            this.name = name;
            this.versionRange = versionRange;
            this.optional = optional;
        }

        public String getName ()
        {
            return this.name;
        }

        public VersionRange getVersionRange ()
        {
            return this.versionRange;
        }

        public boolean isOptional ()
        {
            return this.optional;
        }

        @Override
        public int hashCode ()
        {
            final int prime = 31;
            int result = 1;
            result = prime * result + ( this.name == null ? 0 : this.name.hashCode () );
            return result;
        }

        @Override
        public boolean equals ( final Object obj )
        {
            if ( this == obj )
            {
                return true;
            }
            if ( obj == null )
            {
                return false;
            }
            if ( ! ( obj instanceof PackageImport ) )
            {
                return false;
            }
            final PackageImport other = (PackageImport)obj;
            if ( this.name == null )
            {
                if ( other.name != null )
                {
                    return false;
                }
            }
            else if ( !this.name.equals ( other.name ) )
            {
                return false;
            }
            return true;
        }

    }

    public static class PackageExport
    {
        private final String name;

        private final Version version;

        public PackageExport ( final String name, final Version version )
        {
            this.name = name;
            this.version = version;
        }

        public String getName ()
        {
            return this.name;
        }

        public Version getVersion ()
        {
            return this.version;
        }

        @Override
        public int hashCode ()
        {
            final int prime = 31;
            int result = 1;
            result = prime * result + ( this.name == null ? 0 : this.name.hashCode () );
            return result;
        }

        @Override
        public boolean equals ( final Object obj )
        {
            if ( this == obj )
            {
                return true;
            }
            if ( obj == null )
            {
                return false;
            }
            if ( ! ( obj instanceof PackageExport ) )
            {
                return false;
            }
            final PackageExport other = (PackageExport)obj;
            if ( this.name == null )
            {
                if ( other.name != null )
                {
                    return false;
                }
            }
            else if ( !this.name.equals ( other.name ) )
            {
                return false;
            }
            return true;
        }

    }

    public static class BundleRequirement
    {
        private final String id;

        private final VersionRange versionRange;

        private final boolean optional;

        private final boolean reexport;

        public BundleRequirement ( final String id, final VersionRange versionRange, final boolean optional, final boolean reexport )
        {
            this.id = id;
            this.versionRange = versionRange;
            this.optional = optional;
            this.reexport = reexport;
        }

        public String getId ()
        {
            return this.id;
        }

        public VersionRange getVersionRange ()
        {
            return this.versionRange;
        }

        public boolean isOptional ()
        {
            return this.optional;
        }

        public boolean isReexport ()
        {
            return this.reexport;
        }

        @Override
        public int hashCode ()
        {
            final int prime = 31;
            int result = 1;
            result = prime * result + ( this.id == null ? 0 : this.id.hashCode () );
            return result;
        }

        @Override
        public boolean equals ( final Object obj )
        {
            if ( this == obj )
            {
                return true;
            }
            if ( obj == null )
            {
                return false;
            }
            if ( ! ( obj instanceof BundleRequirement ) )
            {
                return false;
            }
            final BundleRequirement other = (BundleRequirement)obj;
            if ( this.id == null )
            {
                if ( other.id != null )
                {
                    return false;
                }
            }
            else if ( !this.id.equals ( other.id ) )
            {
                return false;
            }
            return true;
        }

    }

    private String id;

    private String version;

    private String name;

    private String vendor;

    private boolean singleton;

    private String bundleLocalization;

    private Map<String, Properties> localization = new HashMap<> ();

    private Set<PackageImport> packageImports = new HashSet<> ();

    private Set<PackageExport> packageExports = new HashSet<> ();

    private Set<BundleRequirement> bundleRequirements = new HashSet<> ();

    public void setBundleLocalization ( final String bundleLocalization )
    {
        this.bundleLocalization = bundleLocalization;
    }

    public String getBundleLocalization ()
    {
        return this.bundleLocalization;
    }

    public void setPackageExports ( final Set<PackageExport> packageExports )
    {
        this.packageExports = packageExports;
    }

    public Set<PackageExport> getPackageExports ()
    {
        return this.packageExports;
    }

    public Set<BundleRequirement> getBundleRequirements ()
    {
        return this.bundleRequirements;
    }

    public void setBundleRequirements ( final Set<BundleRequirement> bundleImports )
    {
        this.bundleRequirements = bundleImports;
    }

    public void setPackageImports ( final Set<PackageImport> packageImports )
    {
        this.packageImports = packageImports;
    }

    public Set<PackageImport> getPackageImports ()
    {
        return this.packageImports;
    }

    public void setSingleton ( final boolean singleton )
    {
        this.singleton = singleton;
    }

    public boolean isSingleton ()
    {
        return this.singleton;
    }

    public Map<String, Properties> getLocalization ()
    {
        return this.localization;
    }

    public void setLocalization ( final Map<String, Properties> localization )
    {
        this.localization = localization;
    }

    public void setVendor ( final String vendor )
    {
        this.vendor = vendor;
    }

    public String getVendor ()
    {
        return this.vendor;
    }

    public String getName ()
    {
        return this.name;
    }

    public void setName ( final String name )
    {
        this.name = name;
    }

    public String getVersion ()
    {
        return this.version;
    }

    public void setVersion ( final String version )
    {
        this.version = version;
    }

    public void setId ( final String id )
    {
        this.id = id;
    }

    public String getId ()
    {
        return this.id;
    }
}