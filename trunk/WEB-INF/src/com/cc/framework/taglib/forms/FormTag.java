/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/forms/FormTag.java,v 1.34 2005/09/12 10:33:37 P001002 Exp $
 * $Revision: 1.34 $
 * $Date: 2005/09/12 10:33:37 $
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

import com.cc.framework.Globals;
import com.cc.framework.taglib.FrameContainerTag;
import com.cc.framework.taglib.TagHelp;
import com.cc.framework.taglib.controls.BaseControlTag;
import com.cc.framework.ui.AlignmentType;
import com.cc.framework.ui.FormMethod;
import com.cc.framework.ui.FormType;
import com.cc.framework.ui.control.Control;
import com.cc.framework.ui.control.FormControl;
import com.cc.framework.ui.control.FormElement;
import com.cc.framework.ui.model.ControlDesignModel;
import com.cc.framework.ui.model.FormDesignModel;
import com.cc.framework.ui.model.ImageModel;
import com.cc.framework.ui.model.InnerFrame;
import com.cc.framework.ui.model.imp.FormDesignModelImp;

/**
 * Tag-Handler for the <code>form</code> Tag.
 * <p>
 * The tag generates a form. The form elements are specified in
 * the tag-body.
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.34 $
 * @since      1.0
 */
public class FormTag extends BaseControlTag implements FrameContainerTag, FormElementContainerTag {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -8400743592241657912L;

	/**
	 * Is set to true if the form contains a section
	 * with buttons.
	 */
	private boolean buttonSection = false;

	/**
	 * Constructor
	 */
	public FormTag() {
		super();
	}

	/**
	 * @see com.cc.framework.taglib.controls.BaseControlTag#doCreateDesignModel()
	 */
	protected ControlDesignModel doCreateDesignModel() {
		return new FormDesignModelImp();
	}

	/**
	 * Returns a casted version of the ControlDesignModel
	 *
	 * @return		FormDesignModel
	 */
	protected FormDesignModel getFormDesignModel() {
		return (FormDesignModel) getDesignModel();
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#setPageContext(PageContext pc)
	 */
	public void setPageContext(PageContext pc) {
		super.setPageContext(pc);

		// initialize variables
		buttonSection	= false;
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#release()
	 */
	public void release() {
		super.release();

		buttonSection	= false;
	}

	/**
	 * @see com.cc.framework.taglib.FrameContainerTag#addInnerFrame(com.cc.framework.ui.model.InnerFrame)
	 */
	public void addInnerFrame(InnerFrame frame) {
		if (frame.getLayoutHint() == null) {
			FormDesignModel model = getFormDesignModel();

			Object hint = null;

			if (model.size() > 0) {
				hint = model.getFormElement(model.size() - 1);
			} else {
				hint = AlignmentType.TOP;
			}

			frame.setLayoutHint(hint);
		}

		getFormDesignModel().addInnerFrame(frame);
	}

	/**
	 * @see com.cc.framework.taglib.forms.FormElementContainerTag#addFormElement(com.cc.framework.ui.control.FormElement)
	 */
	public void addFormElement(FormElement element) {
		getFormDesignModel().addFormElement(element);
	}

	/**
	 * Returns true if the form contains a button section
	 * @return Returns a boolean
	 */
	public boolean hasButtonSection() {
		return buttonSection;
	}

	/**
	 * Sets the Flag if the form contains a button section
	 * @param buttonSection	<code>true</code> if the form contains a button section;
	 *  					<code>false</code> otherwise.
	 */
	public void setButtonSection(boolean buttonSection) {
		this.buttonSection = buttonSection;
	}

	/**
	 * @see com.cc.framework.taglib.controls.BaseControlTag#doCreateControl()
	 */
	protected Control doCreateControl() throws JspException {
		FormDesignModel designModel = getFormDesignModel();
		FormControl ctrl = null;

		if ((designModel.getName() != null) || (designModel.getName() != null)) {
			// get the control
			Object value = lookupBean();

			if (value instanceof FormControl) {
				ctrl = (FormControl) value;
			} else {
				ctrl = new FormControl();

				// Store the form bean in the request scope
				// -> Nested form elements will retrieve their data
				//    from this form bean
				pageContext.setAttribute(Globals.STRUTS_BEAN_KEY, value, PageContext.REQUEST_SCOPE);
			}
		} else {
			// create a new control
			ctrl = new FormControl();
		}

		// Assign an action if this doesn't happend by the programmer
		if (designModel.getAction() == null) {
			designModel.setAction(getDefaultAction());
		}

		// Assign the DesignModel if this did not happened
		// in the Application
		if ((ctrl.getDesignModel() == null) || ctrl.getDesignModel().isDynamicDesignModel()) {
			ctrl.setDesignModel(designModel);
		} else {
			FormDesignModel dm = (FormDesignModel) ctrl.getDesignModel();

			dm.setAction(designModel.getAction());
			dm.setProperty(designModel.getProperty());
		}

		return ctrl;
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException {
		// remove form bean
// 		if (designModel.getName() != null) {
// TODO			pageContext.removeAttribute(Globals.STRUTS_BEAN_KEY, PageContext.REQUEST_SCOPE);
//		}

		return super.doEndTag();
	}

	/**
	 * The main title of the form.
	 * The title and detail can also be specified together
	 * in the form "title - detail.
	 *
	 * @param	caption	The caption
	 */
	public void setCaption(String caption) {
		getFormDesignModel().setCaption(caption);
	}

	/**
	 * The detail title of the form
	 * @param	detail	The detail title of the form
	 */
	public void setDetail(String detail) {
		getFormDesignModel().setDetail(detail);
	}

	/**
	 * Specifies the form type. The form type has effects on the depiction
	 * and the behavior of the form. Thus, in the case of a display-form,
	 * e.g, all the form elements are depicted as Read-Only.
	 * <ul>
	 * 	<li>search = Search form. A corresponding icon is displayed in the title line of the form.</li>
	 * 	<li>create = Input form for data record generation.</li>
	 * 	<li>display = Display form. All form elements are depicted s Read-Only fields.</li>
	 * 	<li>edit = Input form.</li>
	 * 	<li>header = Special form of a display form.</li>
	 * 	<li>info = Form for displaying notice texts. A corresponding icon is displayed in the title bar of the form.</li>
	 *
	 * @param	type			Specifies the form type
	 * @throws	JspException	If the argument can not be converted to an Object of Type FormType
	 */
	public void setType(String type) throws JspException {
		try {
			getFormDesignModel().setFormType(FormType.parse(type));
		} catch (com.cc.framework.common.InvalidEnumType iet) {
			throw new JspException("Invalid Attribute value " + iet.getMessage());
		}
	}

	/**
	 * Specifies the method using which the form should be sent.
	 * <ul>
	 * 	<li>get = The form is sent off via HTTP-Get. All the form fields are transferred in the URL.</li>
	 * 	<li>post = The form is sent off with the HTTP-Post. All the form fields are transferred in the HTTP body.</li>
	 * </ul>
	 *
	 * @param	method	Method
	 * @throws	JspException	if the argument can not be converted to an Object of Type FormMethod
	 */
	public void setMethod(String method) throws JspException {
		try {
			getFormDesignModel().setMethod(FormMethod.parse(method));
		} catch (com.cc.framework.common.InvalidEnumType iet) {
			throw new JspException("Invalid Attribute value " + iet.getMessage());
		}
	}

	/**
	 * The unique identification of the form. It is written in
	 * the form as a hidden field with the name formid and is
	 * thus available at the time of a form submission. The
	 * framework can call the corresponding EventHandler via the formid.
	 *
	 * @param	formid	The formid
	 */
	public void setFormid(String formid) {
		getFormDesignModel().setFormId(formid);
	}

	/**
	 * @see com.cc.framework.taglib.forms.FormElementTag#setColspan(java.lang.String)
	 */
	public void setColspan(String colspan) throws JspException {
		getFormDesignModel().setColSpan(TagHelp.toInt(colspan));
	}

	/**
	 * @see com.cc.framework.taglib.forms.FormElementTag#setJoin(java.lang.String)
	 */
	public void setJoin(String join) throws JspException {
		getFormDesignModel().setJoin(TagHelp.toBoolean(join));
	}

	/**
	 * With this attribute, an ImageList is assigned to the form,
	 * The images can be assigned to the individual form elements.
	 *
	 * @param	mapName			The ImageMap to assign
	 * @throws	JspException	If the ImageMap could not be found
	 */
	public void setImagemap(String mapName) throws JspException {
		getFormDesignModel().setImageMap(TagHelp.lookupImageMap(pageContext, mapName));
	}

	/**
	 * @see com.cc.framework.taglib.forms.FormElementContainerTag#setNoframe(java.lang.String)
	 */
	public void setNoframe(String noframe) throws JspException {
		getFormDesignModel().setShowFrame(!TagHelp.toBoolean(noframe));
	}

	/**
	 * @see com.cc.framework.taglib.forms.FormElementTag#setAlign(java.lang.String)
	 */
	public void setAlign(String alignment) throws JspException {
		getFormDesignModel().setAlignment(TagHelp.toAlignment(alignment));
	}

	/**
	 * @see com.cc.framework.taglib.forms.FormElementTag#setValign(java.lang.String)
	 */
	public void setValign(String alignment) throws JspException {
		getFormDesignModel().setVAlignment(TagHelp.toAlignment(alignment));
	}

	/**
	 * @see com.cc.framework.taglib.ImageContainerTag#setImage(com.cc.framework.ui.model.ImageModel)
	 */
	public void setImage(ImageModel image) {
		getFormDesignModel().setImage(image);
	}
}