/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/controls/PanelTag.java,v 1.18 2005/08/02 19:13:06 P001002 Exp $
 * $Revision: 1.18 $
 * $Date: 2005/08/02 19:13:06 $
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
import com.cc.framework.ui.control.PanelControl;
import com.cc.framework.ui.model.ControlDesignModel;
import com.cc.framework.ui.model.PanelContentDesignModel;
import com.cc.framework.ui.model.PanelDesignModel;
import com.cc.framework.ui.model.imp.PanelDesignModelImp;

/**
 * Tag-Handler for the <code>panel</code> Tag.
 *
 * @author      <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version     $Revision: 1.18 $
 * @since       1.0
 */
public class PanelTag extends BaseControlTag {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -401722844634086352L;

	/**
	 * Constructor
	 */
	public PanelTag() {
		super();
	}

	/**
	 * @see com.cc.framework.taglib.controls.BaseControlTag#doCreateDesignModel()
	 */
	protected ControlDesignModel doCreateDesignModel() {
		return new PanelDesignModelImp();
	}

	/**
	 * Returns a casted version of the ControlDesignModel
	 *
	 * @return		PanelDesignModel
	 */
	protected PanelDesignModel getPanelDesignModel() {
		return (PanelDesignModel) getDesignModel();
	}

	/**
	 * @see com.cc.framework.taglib.controls.BaseControlTag#doCreateControl()
	 */
	protected Control doCreateControl() throws JspException {
		PanelControl ctrl = null;
		PanelDesignModel designModel = getPanelDesignModel();

		// if a property is specified get the control from the form bean
		if (designModel.getProperty() != null) {

			// get the model
			Object value = lookupBean();

			if (!(value instanceof PanelControl)) {
				throw new JspException("PanelTag: invalid property class. expected class=PanelControl!");
			}

			// assign the control
			ctrl = (PanelControl) value;
		} else {
			// if there did not exists an instance of an control create a new one
			ctrl = new PanelControl();
		}

		// If the designmodel was not declared serverside we take the
		// designmodel defined in the jsp page.
		if (ctrl.getDesignModel() == null) {
			ctrl.setDesignModel(designModel);
		}

		return ctrl;
	}

	/**
	 * Adds a content to the panel
	 *
	 * @param	content	PanelContentDesignModel
	 */
	public void addContent(PanelContentDesignModel content) {
		getPanelDesignModel().addContent(content);
	}
}