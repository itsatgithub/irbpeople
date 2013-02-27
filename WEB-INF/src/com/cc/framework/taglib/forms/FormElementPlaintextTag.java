/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/forms/FormElementPlaintextTag.java,v 1.33 2005/08/02 19:13:05 P001002 Exp $
 * $Revision: 1.33 $
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

import com.cc.framework.taglib.InnerTag;
import com.cc.framework.taglib.TagHelp;
import com.cc.framework.taglib.controls.PlainTextTag;
import com.cc.framework.ui.control.FormControlElement;
import com.cc.framework.ui.model.FormLabelDesignModel;

/**
 * Tag-Handler for the Plaintext-Formelement.
 * <p>
 * The tag generates a simple static text element in the enclosing form.
 * The contents can be specified in three ways:
 * <ul>
 * 	<li>With the property attribute as Java Bean.</li>
 * 	<li>With the value attribute as character string literal.</li>
 * 	<li>In the tag-body.</li>
 * </ul>
 * The tag may only be used within a com.cc.framework.taglib.forms.FormElementContainerTag.
 * Examples of this are <forms:form> and <forms:section>.
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.33 $
 * @since      1.0
 */
public class FormElementPlaintextTag extends PlainTextTag implements FormElementControlTag, InnerTag {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 4899279770594753788L;

	/**
	 * The design model
	 */
	private FormControlElement formElement = null;

	/**
	 * Constructor for FormElementCalendarTag
	 */
	public FormElementPlaintextTag() {
		super();
	}

	/**
	 * @see com.cc.framework.taglib.forms.FormElementControlTag#getFormElement()
	 */
	public FormControlElement getFormElement() {
		if (formElement == null) {
			formElement = doCreateFormElement();
		}

		return formElement;
	}

	/**
	 * @see com.cc.framework.taglib.forms.FormElementControlTag#doCreateFormElement()
	 */
	public FormControlElement doCreateFormElement() {
		return new FormControlElement();
	}

	/**
	 * @see com.cc.framework.taglib.controls.BaseControlTag#releaseDesignModel()
	 */
	protected void releaseDesignModel() {
		super.releaseDesignModel();

		formElement = null;;
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException {
		return EVAL_BODY_BUFFERED;
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException {
		FormElementContainerTag container = TagHelp.getContainer(this);

		// Das Kontrollelement übernehmen
		getFormElement().setControl(createControl());

		// Register the control in the container
		container.addFormElement(getFormElement());

		return super.doEndTag();
	}

	/**
	 * @see com.cc.framework.taglib.forms.FormElementControlTag#setDescription(java.lang.String)
	 */
	public void setDescription(String description) {
		getFormElement().setDescription(description);
	}

	/**
	 * @see com.cc.framework.taglib.forms.FormElementControlTag#setLabel(java.lang.String)
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
	 * @see com.cc.framework.taglib.forms.FormElementControlTag#setRequired(java.lang.String)
	 */
	public void setRequired(String required) throws JspException {
		getFormElement().setRequired(TagHelp.toBoolean(required));
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
	 * @see com.cc.framework.taglib.forms.FormElementControlTag#setImageref(java.lang.String)
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