/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/forms/FormElementGroupTag.java,v 1.13 2005/08/02 19:13:05 P001002 Exp $
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
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.cc.framework.common.InvalidEnumType;
import com.cc.framework.taglib.InnerTag;
import com.cc.framework.taglib.TagHelp;
import com.cc.framework.ui.OrientationType;
import com.cc.framework.ui.control.FormElement;
import com.cc.framework.ui.control.FormGroupElement;

/**
 * Tag-Handler for the <code>group</code> Tag.
 *
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.13 $
 */
public class FormElementGroupTag extends BodyTagSupport implements FormElementContainerTag, InnerTag {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -8580883746693675376L;

	/**
	 * Designmodel
	 */
	private FormGroupElement group = null;

	/**
	 * Constructor
	 */
	public FormElementGroupTag() {
		super();
	}

	/**
	 * Returns the Group Element
	 *
	 * @return		Group
	 */
	protected FormGroupElement getGroup() {
		if (group == null) {
			group = doCreateGroup();
		}

		return group;
	}

	/**
	 * Creates the Group element
	 *
	 * @return		Group Element
	 */
	protected FormGroupElement doCreateGroup() {
		return new FormGroupElement();
	}

	/**
	 * Release the current group
	 */
	protected void releaseGroup() {
		group = null;;
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#setPageContext(PageContext pc)
	 */
	public void setPageContext(PageContext pc) {
		super.setPageContext(pc);

		// initialize variables
		releaseGroup();
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#release()
	 */
	public void release() {
		super.release();

		releaseGroup();
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException {
		FormElementContainerTag container = TagHelp.getContainer(this);

		// Register the control in the container
		container.addFormElement(getGroup());

		return EVAL_BODY_BUFFERED;
	}

	/**
	 * @see javax.servlet.jsp.tagext.IterationTag#doAfterBody()
	 */
	public int doAfterBody() throws JspException {
		return SKIP_BODY;
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException {
		// release the current group
		releaseGroup();

		return super.doEndTag();
	}

	/**
	 * @see com.cc.framework.taglib.forms.FormElementContainerTag#addFormElement(FormElement)
	 */
	public void addFormElement(FormElement element) {
		getGroup().addFormElement(element);
	}

	/**
	 * @see com.cc.framework.taglib.forms.FormElementTag#setPermission(java.lang.String)
	 */
	public void setPermission(String permission) throws JspException {
		getGroup().setPermission(TagHelp.toPermission(permission));
	}

	/**
	 * Specifies the orientation of the elements in the group:
	 * <ul>
	 * 		<li>horicontal = from top to bottom</li>
	 * 		<li>vertical = from left to right</li>
	 * </ul>
	 *
	 * @param		orientation	The orientation of the column
	 * @throws		JspException If the argument can't be converted	to an OrientationType
	 */
	public void setOrientation(String orientation) throws JspException {
		try {
			getGroup().setOrientation(OrientationType.parse(orientation));
		} catch (InvalidEnumType iet) {
			throw new JspException("Invalid attribute value: " + iet.getMessage());
		}
	}

	/**
	 * @see com.cc.framework.taglib.forms.FormElementTag#setColspan(java.lang.String)
	 */
	public void setColspan(String colspan) throws JspException {
		getGroup().setColSpan(TagHelp.toInt(colspan));
	}

	/**
	 * @see com.cc.framework.taglib.forms.FormElementTag#setJoin(java.lang.String)
	 */
	public void setJoin(String join) throws JspException {
		getGroup().setJoin(TagHelp.toBoolean(join));
	}

	/**
	 * @see com.cc.framework.taglib.forms.FormElementTag#setAlign(java.lang.String)
	 */
	public void setAlign(String alignment) throws JspException {
		getGroup().setAlignment(TagHelp.toAlignment(alignment));
	}

	/**
	 * @see com.cc.framework.taglib.forms.FormElementTag#setValign(java.lang.String)
	 */
	public void setValign(String alignment) throws JspException {
		getGroup().setVAlignment(TagHelp.toAlignment(alignment));
	}

	/**
	 * @see com.cc.framework.taglib.forms.FormElementTag#setHeight(java.lang.String)
	 */
	public void setHeight(String height) {
		getGroup().setHeight(height);
	}

	/**
	 * @see com.cc.framework.taglib.forms.FormElementTag#setWidth(java.lang.String)
	 */
	public void setWidth(String width) {
		getGroup().setWidth(width);
	}

	/**
	 * @see com.cc.framework.taglib.forms.FormElementTag#setStyle(java.lang.String)
	 */
	public void setStyle(String style) {
		getGroup().setStyle(style);
	}

	/**
	 * @see com.cc.framework.taglib.forms.FormElementTag#setStyleClass(java.lang.String)
	 */
	public void setStyleClass(String styleClass) {
		getGroup().setStyleClass(styleClass);
	}

	/**
	 * @see com.cc.framework.taglib.forms.FormElementTag#setStyleId(java.lang.String)
	 */
	public void setStyleId(String styleId) {
		getGroup().setStyleId(styleId);
	}

	/**
	 * @see com.cc.framework.taglib.forms.FormElementTag#setHelp(java.lang.String)
	 */
	public void setHelp(String helpId) {
		getGroup().setHelp(helpId);
	}

	/**
	 * @see com.cc.framework.taglib.forms.FormElementContainerTag#setNoframe(java.lang.String)
	 */
	public void setNoframe(String noframe) throws JspException {
		getGroup().setShowFrame(!TagHelp.toBoolean(noframe));
	}
}