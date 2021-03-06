/*******************************************************************************
 * Copyright (c) 2015 IBH SYSTEMS GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBH SYSTEMS GmbH - initial API and implementation
 *******************************************************************************/
package de.dentrassi.rpm;

public class RpmLead
{
    private final byte major;

    private final byte minor;

    private final String name;

    private final int signatureVersion;

    public RpmLead ( final byte major, final byte minor, final String name, final int signatureVersion )
    {
        this.major = major;
        this.minor = minor;
        this.name = name;
        this.signatureVersion = signatureVersion;
    }

    public byte getMajor ()
    {
        return this.major;
    }

    public byte getMinor ()
    {
        return this.minor;
    }

    public String getName ()
    {
        return this.name;
    }

    public int getSignatureVersion ()
    {
        return this.signatureVersion;
    }
}
