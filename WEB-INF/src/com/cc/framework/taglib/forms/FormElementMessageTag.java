/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/forms/FormElementMessageTag.java,v 1.24 2005/09/27 14:06:22 P001002 Exp $
 * $Revision: 1.24 $
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

import com.cc.framework.common.InvalidEnumType;
import com.cc.framework.common.Severity;
import com.cc.framework.taglib.InnerTag;
import com.cc.framework.taglib.TagHelp;
import com.cc.framework.ui.control.FormMessageElement;

/**
 * Tag-Handler for the Message Formelement.
 * <p>
 * Generates a message text form. The form expects, as the data model,
 * an implementation of the interface com.cc.framework.message.Message.MessageDataModel.
 * Struts: If the Struts Framework is being used, the tag can also be used without the
 * specification of a Data Bean. In this case, the Struts error collection (severity="error")
 * or the message collection (severity="information") are displayed.
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.24 $
 * @since      1.0
 */
public class FormElementMessageTag extends BodyTagSupport implements FormElementTag, InnerTag {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -5914140152189473403L;

	/**
	 * Designmodel
	 */
	private FormMessageElement formElement	= null;

	/**
	 * Constructor
	 */
	public FormElementMessageTag() {
		super();
	}

	/**
	 * Returns the concrete form element
	 * 
	 * @return form element
	 */
	public FormMessageElement getFormElement() {
		if (formElement == null) {
			formElement = doCreateFormElement();
		}

		return formElement;
	}

	/**
	 * Creates the form element
	 *
	 * @return		form element
	 */
	public FormMessageElement doCreateFormElement() {
		return new FormMessageElement();
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
				getFormElement().setMessage(contentBody);
				getFormElement().setFilter(false);
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
	 * Sets the Message
	 * @param message	Message
	 */
	public void setMessage(String message) {
		getFormElement().setMessage(message);
	}

	/**
	 * Sets the severity of the message.
	 * @param severity The severity to set
	 * @throws	JspException	if the argument can not be converted to an Object of Type Severity
	 */
	public void setSeverity(String severity) throws JspException {
		try {
			getFormElement().setSeverity(Severity.parse(severity));
		} catch (InvalidEnumType iet) {
			throw new JspException(iet.getMessage());
		}
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
	 * @see com.cc.framework.taglib.forms.FormElementTag#setHelp(java.lang.String)
	 */
	public final void setHelp(String helpId) {
		getFormElement().setHelp(helpId);
	}
}