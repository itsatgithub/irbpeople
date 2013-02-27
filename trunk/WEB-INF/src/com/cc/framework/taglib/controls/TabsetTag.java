/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/controls/TabsetTag.java,v 1.26 2005/11/13 13:39:39 P001002 Exp $
 * $Revision: 1.26 $
 * $Date: 2005/11/13 13:39:39 $
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

import com.cc.framework.ui.control.Control;
import com.cc.framework.ui.control.TabsetControl;
import com.cc.framework.ui.model.TabsetDesignModel;

/**
 * Tag-Handler for the <code>tabset</code> Tag.
 * <p>
 * Generates a tabset control element (cardex cards).
 * The individual tabs of the control element can be
 * directly specified in the JSP-Page with the help of the <tab>-tags.
 *
 * @author      <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version     $Revision: 1.26 $
 * @since       1.0
 */
public class TabsetTag extends TabbarTag implements TabContainer {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 3191078007045656913L;

	/**
	 * Constructor
	 */
	public TabsetTag() {
		super();
	}

	/**
	 * @see com.cc.framework.taglib.controls.BaseControlTag#doCreateControl()
	 */
	protected Control doCreateControl() throws JspException {
		// Zunächst wird der direkte Wert verwendet
		Object value = lookupBean();

		TabsetDesignModel designModel = getTabsetDesignModel();
		TabsetControl ctrl = null;

		if (value == null) {
			// if there exists no instance for the control, we create a new one
			ctrl = new TabsetControl();

		} else if (value instanceof TabsetControl) {
			// assign the control
			ctrl = (TabsetControl) value;
		} else {
			// create a new wrapper
			ctrl = new TabsetControl();
			ctrl.setSelectedTab(value.toString());
		}

		// if the designmodel was not specified server side we get the model
		// from the jsp page
		if ((ctrl.getDesignModel() == null) || ctrl.getDesignModel().isDynamicDesignModel()) {
			ctrl.setDesignModel(designModel);
		}

		// assign an action if the programmer did not
		if (designModel.getAction() == null) {
			designModel.setAction(getDefaultAction());
		}

		// remember the selected tab for use in the
		// tag body
		setSelected(ctrl.getSelectedTab());

		return ctrl;
	}
}