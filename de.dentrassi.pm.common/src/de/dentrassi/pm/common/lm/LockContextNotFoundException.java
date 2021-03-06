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
package de.dentrassi.pm.common.lm;

public class LockContextNotFoundException extends IllegalStateException
{
    private static final long serialVersionUID = 1L;

    public LockContextNotFoundException ()
    {
        super ( "No lock context in local thread storage" );
    }
}
