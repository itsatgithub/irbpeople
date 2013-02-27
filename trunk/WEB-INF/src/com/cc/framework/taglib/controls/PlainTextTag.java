/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/controls/PlainTextTag.java,v 1.8 2005/11/13 13:08:04 P001002 Exp $
 * $Revision: 1.8 $
 * $Date: 2005/11/13 13:08:04 $
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

package com.cc.framework.taglib.controls;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTag;

import com.cc.framework.taglib.TagHelp;
import com.cc.framework.ui.control.Control;
import com.cc.framework.ui.control.PlainTextControl;
import com.cc.framework.ui.model.ControlDesignModel;
import com.cc.framework.ui.model.PlainTextDesignModel;
import com.cc.framework.ui.model.imp.PlainTextDesignModelImp;

/**
 * Tag-Handler for the <code>plaintext</code> Tag.
 *
 * @author      <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version     $Revision: 1.8 $
 * @since       1.0
 */
public class PlainTextTag extends TextTag implements BodyTag {

	/**
	 * Serial Version UID 
	 */
	private static final long serialVersionUID = -290914273275160184L;

	/**
	 * The Body Content of the Tag
	 */
	private BodyContent bodyContent = null;

	/**
	 * @see com.cc.framework.taglib.controls.BaseControlTag#doCreateDesignModel()
	 */
	protected ControlDesignModel doCreateDesignModel() {
		return new PlainTextDesignModelImp();
	}

	/**
	 * Returns a casted version of the ControlDesignModel
	 *
	 * @return		PlainTextDesignModel
	 */
	protected PlainTextDesignModel getPlainTextDesignModel() {
		return (PlainTextDesignModel) getDesignModel();
	}

	/**
	 * @see com.cc.framework.taglib.controls.BaseControlTag#doCreateControl()
	 */
	protected Control doCreateControl() throws JspException {
		// assign a new control
		PlainTextControl ctrl = new PlainTextControl(getPlainTextDesignModel());

		// get the datamodel
		Object value = lookupBean();

		if (value != null) {
			ctrl.setValue(value);
		}

		return ctrl;
	}

	/**
	 * Constructor
	 */
	public PlainTextTag() {
		super();
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#release()
	 */
	public void release() {
		super.release();

		bodyContent = null;
	}

	/**
	 * @see javax.servlet.jsp.tagext.BodyTag#doInitBody()
	 */
	public void doInitBody() throws JspException {
	}

	/**
	 * @see javax.servlet.jsp.tagext.BodyTag#setBodyContent(javax.servlet.jsp.tagext.BodyContent)
	 */
	public void setBodyContent(BodyContent body) {
		bodyContent = body;
	}

	/**
	 * @see javax.servlet.jsp.tagext.IterationTag#doAfterBody()
	 */
	public int doAfterBody() throws JspException {

		if ((bodyContent != null) && (bodyContent.getString() != null)) {
			String bodyText = bodyContent.getString().trim();

			if (bodyText.length() > 0) {
				setValue(bodyText);

				getPlainTextDesignModel().setFilter(false);
			}
		}

		return SKIP_BODY;
	}

	/**
	 * This flag indicates whether a hidden field
	 * should be additionally generated. This will
	 * enable access to the field contents when there
	 * is a Form Submit.
	 *
	 * @param	hidden			If true, an additional Hidden Field is created in the HTML Page
	 * @throws	JspException	if the argument can not be converted to a boolean value
	 */
	public void setHidden(String hidden) throws JspException {
		getPlainTextDesignModel().setHidden(TagHelp.toBoolean(hidden));
	}
}