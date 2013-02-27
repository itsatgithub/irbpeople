/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/controls/TextPopupTag.java,v 1.10 2005/08/02 19:13:05 P001002 Exp $
 * $Revision: 1.10 $
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

package com.cc.framework.taglib.controls;

import javax.servlet.jsp.JspException;

import com.cc.framework.taglib.TagHelp;
import com.cc.framework.ui.control.Control;
import com.cc.framework.ui.control.TextPopupControl;
import com.cc.framework.ui.model.ControlDesignModel;
import com.cc.framework.ui.model.TextPopupDesignModel;
import com.cc.framework.ui.model.imp.TextPopupDesignModelImp;

/**
 * Tag-Handler for the Textarea Popup element
 *
 * @author      <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version     $Revision: 1.10 $
 * @since       1.0
 */
public class TextPopupTag extends TextareaTag {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 8863412101482846856L;

	/**
	 * Constructor
	 */
	public TextPopupTag() {
		super();
	}

	/**
	 * @see com.cc.framework.taglib.controls.BaseControlTag#doCreateDesignModel()
	 */
	protected ControlDesignModel doCreateDesignModel() {
		return new TextPopupDesignModelImp();
	}

	/**
	 * Returns a casted version of the ControlDesignModel
	 *
	 * @return		TextPopupDesignModel
	 */
	protected TextPopupDesignModel getTextPopupDesignModel() {
		return (TextPopupDesignModel) getDesignModel();
	}

	/**
	 * @see com.cc.framework.taglib.controls.BaseControlTag#doCreateControl()
	 */
	protected Control doCreateControl() throws JspException {
		// get the model
		Object value = lookupBean();

		// assign the control
		TextPopupControl ctrl = new TextPopupControl(getTextPopupDesignModel());

		if (value != null) {
			ctrl.setValue(value);
		}

		return ctrl;
	}

	/**
	 * Specifies the height of the popup window
	 *
	 * @param height window height
	 */
	public void setPopupHeight(String height) {
		getTextPopupDesignModel().setPopupHeight(height);
	}

	/**
	 * Specifies the width of the popup window
	 *
	 * @param width window width
	 */
	public void setPopupWidth(String width) {
		getTextPopupDesignModel().setPopupWidth(width);
	}

	/**
	 * Specifies the number of rows for the textarea popup
	 * window
	 *
	 * @param	rows	The number of lines
	 * @throws JspException if the Argument can not be converted into a integer
	 */
	public void setPopupRows(String rows) throws JspException {
		getTextPopupDesignModel().setPopupRows(TagHelp.toInt(rows));
	}
}