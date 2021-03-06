/*******************************************************************************
 * Copyright (c) 2014 IBH SYSTEMS GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBH SYSTEMS GmbH - initial API and implementation
 *******************************************************************************/
package de.dentrassi.osgi.web.form.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class FormTagSupport extends TagSupport
{
    private static final long serialVersionUID = 1L;

    private String cssClass;

    protected void writeDefaultAttributes ( final WriterHelper writer ) throws JspException
    {
        writer.writeOptionalAttribute ( "class", this.cssClass );
    }

    public void setCssClass ( final String cssClass )
    {
        this.cssClass = cssClass;
    }

}
