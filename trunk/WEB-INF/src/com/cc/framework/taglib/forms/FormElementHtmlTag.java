/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/forms/FormElementHtmlTag.java,v 1.13 2005/09/27 14:06:22 P001002 Exp $
 * $Revision: 1.13 $
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
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.cc.framework.taglib.InnerTag;
import com.cc.framework.taglib.TagHelp;
import com.cc.framework.ui.control.FormHtmlElement;
import com.cc.framework.ui.model.FormLabelDesignModel;

/**
 * Tag-Handler for the description formular element.
 * <p>
 * The tag generates a description element. The description text
 * is graphically assigned to the preceding form element.
 * The description text can be specified as an attribute (description)
 * or directly in the tag body. In the tag-body, all the HTML elements
 * can be used for formatting.
 * The tag may only be used within a com.cc.framework.taglib.forms.FormElementContainerTag.
 * Examples of this are <forms:form>, <forms:section> and <forms:buttonsection>.
 * The description element always refers to the immediately preceding form element.
 *
 * @author    <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version   $Revision: 1.13 $
 * @since      1.0
 */
public class FormElementHtmlTag extends BodyTagSupport implements LabeledFormElementTag, InnerTag {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -8395977910668237330L;

	/**
	 * Designmodel
	 */
	private FormHtmlElement formElement	= null;

	/**
	 * Constructor
	 */
	public FormElementHtmlTag() {
		super();
	}

	/**
	 * Returns the concrete form element
	 * 
	 * @return form element
	 */
	public FormHtmlElement getFormElement() {
		if (formElement == null) {
			formElement = doCreateFormElement();
		}

		return formElement;
	}

	/**
	 * Creates the form element
	 *
	 * @return		html form element
	 */
	protected FormHtmlElement doCreateFormElement() {
		return new FormHtmlElement();
	}

	/**
	 * Release the current form element
	 */
	protected void releaseFormElement() {
		formElement = null;;
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#setPageContext(PageContext pc)
	 */
	public void setPageContext(PageContext pc) {
		super.setPageContext(pc);

		// initialize variables
		releaseFormElement();
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#release()
	 */
	public void release() {
		super.release();

		releaseFormElement();
	}

	/**
	 * @see javax.servlet.jsp.tagext.IterationTag#doAfterBody()
	 */
	public int doAfterBody() throws JspException {

		BodyContent body = getBodyContent();
		if (body != null) {
			String contentBody = body.getString().trim();

			if (!"".equals(contentBody)) {
				getFormElement().setHtml(contentBody);
				getFormElement().setFilter(false);
				getFormElement().setBodyInclude(true);
			}
		}

		return SKIP_BODY;
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException {
		FormElementContainerTag container = TagHelp.getContainer(this);

		// Um dem Container eventuelle Layout Aktionen zu ermöglichen
		// wird lediglich das Modell des Elementes im Container
		// registriert
		container.addFormElement(getFormElement());

		// Release the form element
		releaseFormElement();

		return EVAL_PAGE;
	}

	/**
	 * Sets the optional help text
	 * 
	 * @param help
	 *            Help
	 */
	public void setHelp(String help) {
		// no action
	}

	/**
	 * Sets the form element label
	 * 
	 * @param label
	 *            Label
	 */
	public void setLabel(String label) {
		getFormElement().setLabel(label);
	}

	/**
	 * @see com.cc.framework.taglib.forms.LabeledFormElementTag#setLabelDesignModel(com.cc.framework.ui.model.FormLabelDesignModel)
	 */
	public void setLabelDesignModel(FormLabelDesignModel label) {
		getFormElement().setLabel(label);
	}

	/**
	 * Indicates that this form element is a required field
	 * 
	 * @param required
	 *            <code>true</code> if the Field is required
	 * @throws JspException
	 *             if the argument can not be converted to a boolean value
	 */
	public void setRequired(String required) throws JspException {
		// no action
	}

	/**
	 * The automatic HTML coding of the element can be activated
	 * or disabled with the filter-attribute. The default is <code>true</code>
	 *
	 * @param	filter	<code>true</code> if the element should be HTML encoded.
	 * @throws	JspException If the Argument can't be converted to boolean
	 */
	public void setFilter(String filter) throws JspException {
		getFormElement().setFilter(TagHelp.toBoolean(filter));
	}

	/**
	 * @see com.cc.framework.taglib.forms.FormElementTag#setPermission(java.lang.String)
	 */
	public void setPermission(String permission) throws JspException {
		getFormElement().setPermission(TagHelp.toPermission(permission));
	}

	/**
	 * @see com.cc.framework.taglib.forms.FormElementTag#setColspan(java.lang.String)
	 */
	public void setColspan(String colspan) throws JspException {
		getFormElement().setColSpan(TagHelp.toInt(colspan));
	}

	/**
	 * @see com.cc.framework.taglib.forms.FormElementTag#setJoin(java.lang.String)
	 */
	public void setJoin(String join) throws JspException {
		getFormElement().setJoin(TagHelp.toBoolean(join));
	}

	/**
	 * Sets the Reference to an Image in an ImageMap, which should be display in
	 * front of the Label
	 * 
	 * @param imageRef
	 *            Reference to an Image in an ImageMap
	 */
	public void setImageref(String imageRef) {
		getFormElement().setImageRef(imageRef);
	}

	/**
	 * @see com.cc.framework.taglib.forms.FormElementTag#setAlign(java.lang.String)
	 */
	public void setAlign(String alignment) throws JspException {
		getFormElement().setAlignment(TagHelp.toAlignment(alignment));
	}

	/**
	 * @see com.cc.framework.taglib.forms.FormElementTag#setValign(java.lang.String)
	 */
	public void setValign(String alignment) throws JspException {
		getFormElement().setVAlignment(TagHelp.toAlignment(alignment));
	}

	/**
	 * @see com.cc.framework.taglib.forms.FormElementTag#setHeight(java.lang.String)
	 */
	public void setHeight(String height) {
		getFormElement().setHeight(height);
	}

	/**
	 * @see com.cc.framework.taglib.forms.FormElementTag#setWidth(java.lang.String)
	 */
	public void setWidth(String width) {
		getFormElement().setWidth(width);
	}

	/**
	 * @see com.cc.framework.taglib.forms.FormElementTag#setStyle(java.lang.String)
	 */
	public void setStyle(String style) {
		getFormElement().setStyle(style);
	}

	/**
	 * @see com.cc.framework.taglib.forms.FormElementTag#setStyleClass(java.lang.String)
	 */
	public void setStyleClass(String styleClass) {
		getFormElement().setStyleClass(styleClass);
	}

	/**
	 * @see com.cc.framework.taglib.forms.FormElementTag#setStyleId(java.lang.String)
	 */
	public void setStyleId(String styleId) {
		getFormElement().setStyleId(styleId);
	}

	/**
	 * @see com.cc.framework.taglib.forms.LabeledFormElementTag#setAccesskey(java.lang.String)
	 */
	public void setAccesskey(String accessKey) {
		getFormElement().setAccessKey(accessKey);
	}

	/**
	 * @see com.cc.framework.taglib.forms.LabeledFormElementTag#setLabeltooltip(java.lang.String)
	 */
	public void setLabeltooltip(String tooltip) {
		getFormElement().setLabelTooltip(tooltip);
	}
}