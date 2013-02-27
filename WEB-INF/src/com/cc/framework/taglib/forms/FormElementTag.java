/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/forms/FormElementTag.java,v 1.13 2005/08/02 19:13:05 P001002 Exp $
 * $Revision: 1.13 $
 * $Date: 2005/08/02 19:13:05 $
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

/**
 * An interface to identify form element tags.
 * 
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version $Revision: 1.13 $
 * @since 1.0
 */
public interface FormElementTag {

	/**
	 * Sets the permission for the element
	 * 
	 * @param permission
	 *            Permisson
	 * @throws JspException
	 *             If the Argument can't be converted to Permission
	 */
	public void setPermission(String permission) throws JspException;

	/**
	 * Sets the column span of this form element
	 * 
	 * @param colspan
	 *            column span
	 * @throws JspException
	 *             Is thrown when a number conversion error occurs
	 */
	public void setColspan(String colspan) throws JspException;

	/**
	 * This method specifies that two adjacent form elements shold be joined
	 * together without a separator
	 * 
	 * @param join
	 *            <code>true</code> when the form elements should be joined
	 * @throws JspException
	 *             if the Argument can not be converted to a boolean Value
	 */
	public void setJoin(String join) throws JspException;

	/**
	 * Sets the width of the form element (pixel or %)
	 * 
	 * @param width
	 *            The Width
	 * @throws JspException
	 *             Is thrown when a number conversion error occurs
	 */
	public void setWidth(String width) throws JspException;

	/**
	 * Sets the height of the form element (pixel or %)
	 * 
	 * @param height
	 *            The Height
	 * @throws JspException
	 *             Is thrown when a number conversion error occurs
	 */
	public void setHeight(String height) throws JspException;

	/**
	 * Specifies the horicontal alignment of the form element within the form
	 * <ul>
	 * <li>left</li>
	 * <li>right</li>
	 * <li>center</li>
	 * </ul>
	 * 
	 * @param alignment
	 *            The alignment of the element
	 * @throws JspException
	 *             If the argument can't be converted to an AlignmentType
	 */
	public void setAlign(String alignment) throws JspException;

	/**
	 * Specifies the vertical alignment of the form element within the form
	 * <ul>
	 * <li>top</li>
	 * <li>bottom</li>
	 * <li>center</li>
	 * </ul>
	 * 
	 * @param alignment
	 *            The alignment of the element
	 * @throws JspException
	 *             If the argument can't be converted to an AlignmentType
	 */
	public void setValign(String alignment) throws JspException;

	/**
	 * An HTML-style. See HTML documentation for the attribute style.
	 * 
	 * @param style
	 *            An HTML-style
	 */
	public void setStyle(String style);

	/**
	 * The HTML-id attribute. See HTML documentation for the Attribute id.
	 * 
	 * @param styleId
	 *            The HTML-id attribute
	 */
	public void setStyleId(String styleId);

	/**
	 * The HTML-class attribute. See HTML documentation for the attribute class.
	 * 
	 * @param styleClass
	 *            The HTML-class attribute
	 */
	public void setStyleClass(String styleClass);

	/**
	 * Associates this control with a help id
	 * 
	 * @param helpId
	 *            The Help Help id of the element. The semantic of this id is
	 *            completely in the responsibility of the applications help
	 *            system
	 */
	public void setHelp(String helpId);
}
