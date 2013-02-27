/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/forms/FormElementControlTag.java,v 1.6 2005/09/27 14:06:22 P001002 Exp $
 * $Revision: 1.6 $
 * $Date: 2005/09/27 14:06:22 $
 *
 * ====================================================================
 *
 * Copyright (c) 2000 - 2005 SCC Informationssysteme GmbH. All rights
 * reserved.
 * Vendor URL : http://www.scc-gmbh.com
 * Product URL: http://www.common-controls.com
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL SCC INFORMATIONSSYSTEME GMBH OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 */

package com.cc.framework.taglib.forms;

import javax.servlet.jsp.JspException;

import com.cc.framework.ui.control.FormControlElement;

/**
 * 
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version $Revision: 1.6 $
 */
public interface FormElementControlTag extends LabeledFormElementTag {

	/**
	 * Creates the concrete form element
	 * 
	 * @return form element
	 */
	public FormControlElement doCreateFormElement();

	/**
	 * Returns the concrete form element
	 * 
	 * @return form element
	 */
	public FormControlElement getFormElement();

	/**
	 * Sets the optional description text
	 * 
	 * @param description
	 *            Discription
	 */
	public void setDescription(String description);

	/**
	 * Sets the optional help text
	 * 
	 * @param help
	 *            Help
	 */
	public void setHelp(String help);

	/**
	 * Sets the form element label
	 * 
	 * @param label
	 *            Label
	 */
	public void setLabel(String label);

	/**
	 * Indicates that this form element is a required field
	 * 
	 * @param required
	 *            <code>true</code> if the Field is required
	 * @throws JspException
	 *             if the argument can not be converted to a boolean value
	 */
	public void setRequired(String required) throws JspException;

	/**
	 * Sets the Reference to an Image in an ImageMap, which should be display in
	 * front of the Label
	 * 
	 * @param imageRef
	 *            Reference to an Image in an ImageMap
	 */
	public void setImageref(String imageRef);
}
