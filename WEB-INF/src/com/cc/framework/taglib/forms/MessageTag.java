/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/forms/MessageTag.java,v 1.24 2005/09/12 10:33:37 P001002 Exp $
 * $Revision: 1.24 $
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

import com.cc.framework.adapter.FrameworkAdapterFactory;
import com.cc.framework.common.InvalidEnumType;
import com.cc.framework.common.Severity;
import com.cc.framework.taglib.FrameContainerTag;
import com.cc.framework.taglib.TagHelp;
import com.cc.framework.taglib.controls.BaseControlTag;
import com.cc.framework.ui.control.Control;
import com.cc.framework.ui.control.MessageControl;
import com.cc.framework.ui.model.ControlDesignModel;
import com.cc.framework.ui.model.ImageModel;
import com.cc.framework.ui.model.InnerFrame;
import com.cc.framework.ui.model.MessageDataModel;
import com.cc.framework.ui.model.MessageDesignModel;
import com.cc.framework.ui.model.imp.MessageDesignModelImp;

/**
 * Tag-Handler for the <code>message</code> Tag.
 * <p>
 * Generates a message text form. The form expects, as the data model,
 * an implementation of the interface com.cc.framework.message.Message.MessageDataModel.
 *
 * Struts: If the Struts Framework is being used, the tag can also be used without
 * the specification of a Data Bean. In this case, the Struts error collection
 * (severity="error") or the message collection (severity="information") are displayed.
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.24 $
 * @since      1.0
 */
public class MessageTag extends BaseControlTag implements FrameContainerTag {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 6868896956076677635L;

	/**
	 * Constructor
	 */
	public MessageTag() {
		super();
	}

	/**
	 * @see com.cc.framework.taglib.controls.BaseControlTag#doCreateDesignModel()
	 */
	protected ControlDesignModel doCreateDesignModel() {
		return new MessageDesignModelImp();
	}

	/**
	 * Returns a casted version of the ControlDesignModel
	 *
	 * @return		MessageDesignModel
	 */
	protected MessageDesignModel getMessageDesignModel() {
		return (MessageDesignModel) getDesignModel();
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#setPageContext(PageContext pc)
	 */
	public void setPageContext(PageContext pc) {
		super.setPageContext(pc);

		// initialize variables
		setName(null);
	}

	/**
	 * @see com.cc.framework.taglib.controls.BaseControlTag#doCreateControl()
	 */
	protected Control doCreateControl() throws JspException {
		MessageDesignModel designModel = getMessageDesignModel();

		// get the control
		Object value = null;

		if ((designModel.getName() != null) && TagHelp.lookup(pageContext, designModel.getName(), getHttpScope()) != null) {
			value =
				TagHelp.lookup(
					pageContext,
					designModel.getName(),
					designModel.getProperty(),
					getHttpScope());
		}

		MessageControl ctrl;
		
		if (value instanceof MessageControl) {
			ctrl = (MessageControl) value;
		} else {
			// Das Kontrollelement für das Formular erzeugen
			ctrl = new MessageControl();
			ctrl.setDesignModel(designModel);

			if (value instanceof MessageDataModel) {
				ctrl.setDataModel((MessageDataModel) value);
			}
		}

		// Dem Modell wird nun eine Aktion zugeordnet, wenn dies nicht bereits vom
		// JSP-Programmierer vorgenommen wurde.
		if (designModel.getAction() == null) {
			designModel.setAction(getDefaultAction());
		}

		// Wenn kein Datenmodell angegeben wurde, dann werden die
		// Framework Meldungen übernommen.
		if ((designModel.getName() == null) && (null == ctrl.getDataModel())) {
			try {
				ctrl.setDataModel(FrameworkAdapterFactory.getAdapter().getMessages(pageContext, designModel.getSeverity()));
			} catch (Throwable t) {
				throw new JspException(t);
			}
		}

		return ctrl;
	}

	/**
	 * The main title of the form.
	 * The title and detail can also be
	 * specified together in the form "title - detail.
	 *
	 * @param	caption		The caption
	 */
	public void setCaption(String caption) {
		getMessageDesignModel().setCaption(caption);
	}

	/**
	 * The detail title of the form.
	 *
	 * @param	detail	The detail title
	 */
	public void setDetail(String detail) {
		getMessageDesignModel().setDetail(detail);
	}

	/**
	 * Specifies the error class of the message form.
	 * The appearance of the form is influenced by the error class.
	 * <ul>
	 * 	<li>none = no errors</li>
	 * 	<li>information = Information</li>
	 * 	<li>warning = Warning</li>
	 * 	<li>error = error</li>
	 * 	<li>fatal = fatal error. In case of a fatal error the application cannot continue running.</li>
	 * </ul>
	 *
	 * @param	severity Severity
	 * @throws	JspException	if the argument can not be converted to an Object of Type Severity
	 */
	public void setSeverity(String severity) throws JspException {
		try {
			getMessageDesignModel().setSeverity(Severity.parse(severity));
		} catch (InvalidEnumType iet) {
			throw new JspException(
				"Invalid Attribute value " + iet.getMessage());
		}
	}

	/**
	 * The unique identification of the form. It is written
	 * in the form as a hidden field with the name formid and
	 * is thus available at the time of a form submission.
	 * The framework can call the corresponding EventHandler
	 * via the formid.
	 *
	 * @param	formid	Id of the Form
	 */
	public void setFormid(String formid) {
		getMessageDesignModel().setFormId(formid);
	}

	/**
	 * The automatic HTML coding of the text element
	 * can be activated or disabled with the filter-attribute
	 *
	 * @param		filter <code>true</code> if the text should be HTML encoded.
	 * @throws		JspException If the Argument can't be converted	to a boolean
	 */
	public void setFilter(String filter) throws JspException {
		getMessageDesignModel().setFilter(TagHelp.toBoolean(filter));
	}

	/**
	 * @see com.cc.framework.taglib.FrameContainerTag#addInnerFrame(com.cc.framework.ui.model.InnerFrame)
	 */
	public void addInnerFrame(InnerFrame frame) {
		getMessageDesignModel().addInnerFrame(frame);
	}

	/**
	 * @see com.cc.framework.taglib.ImageContainerTag#setImage(com.cc.framework.ui.model.ImageModel)
	 */
	public void setImage(ImageModel image) {
		getMessageDesignModel().setImage(image);
	}
}