/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/controls/RadioTag.java,v 1.21 2005/08/10 08:15:19 P001002 Exp $
 * $Revision: 1.21 $
 * $Date: 2005/08/10 08:15:19 $
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
import com.cc.framework.ui.control.RadioControl;
import com.cc.framework.ui.model.ControlDesignModel;
import com.cc.framework.ui.model.RadioDesignModel;
import com.cc.framework.ui.model.imp.RadioDesignModelImp;

/**
 * Tag-Handler for the <code>Radio</code> Tag.
 *
 * @author      <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version     $Revision: 1.21 $
 * @since       1.0
 */
public class RadioTag extends BaseControlTag {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -6572986131300440154L;

	/**
	 * Constructor
	 */
	public RadioTag() {
		super();
	}

	/**
	 * @see com.cc.framework.taglib.controls.BaseControlTag#doCreateDesignModel()
	 */
	protected ControlDesignModel doCreateDesignModel() {
		return new RadioDesignModelImp();
	}

	/**
	 * Returns a casted version of the ControlDesignModel
	 *
	 * @return		RadioDesignModel
	 */
	protected RadioDesignModel getRadioDesignModel() {
		return (RadioDesignModel) getDesignModel();
	}

	/**
	 * @see com.cc.framework.taglib.controls.BaseControlTag#doCreateControl()
	 */
	protected Control doCreateControl() throws JspException {
		// search the model
		Object value = lookupBean();

		RadioDesignModel designModel = getRadioDesignModel();

		// assign a new control
		RadioControl ctrl = new RadioControl(designModel);

		if (value != null) {
			String buttonValue = ((RadioDesignModel) getDesignModel()).getValue();

			ctrl.setChecked(new Boolean(value.toString().equals(buttonValue)));
		}

		return ctrl;
	}

	/**
	 * Specifies the value of the radiobutton. This value is
	 * transferred in the request when the radiobutton has
	 * been selected by the user.
	 *
	 * @param	value	The value of the radiobutton
	 */
	public void setValue(String value) {
		getRadioDesignModel().setValue(value);
	}
}