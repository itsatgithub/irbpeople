/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/controls/SpinTag.java,v 1.14 2005/08/02 19:13:06 P001002 Exp $
 * $Revision: 1.14 $
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
import com.cc.framework.ui.control.SpinControl;
import com.cc.framework.ui.model.ControlDesignModel;
import com.cc.framework.ui.model.SpinDesignModel;
import com.cc.framework.ui.model.imp.SpinDesignModelImp;

/**
 * Tag-Handler for the spin control.
 *
 * @author      <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version     $Revision: 1.14 $
 * @since       1.2
 */
public class SpinTag extends TextTag {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -8553113400999667703L;

	/**
	 *  Constructor for CalendarTag
	 */
	public SpinTag() {
		super();
	}

	/**
	 * @see com.cc.framework.taglib.controls.BaseControlTag#doCreateDesignModel()
	 */
	protected ControlDesignModel doCreateDesignModel() {
		return new SpinDesignModelImp();
	}

	/**
	 * Returns a casted version of the ControlDesignModel
	 *
	 * @return		SpinDesignModel
	 */
	protected SpinDesignModel getSpinDesignModel() {
		return (SpinDesignModel) getDesignModel();
	}

	/**
	 * @see com.cc.framework.taglib.controls.BaseControlTag#doCreateControl()
	 */
	protected Control doCreateControl() throws JspException {
		// get the datamodel
		Object value = lookupBean();

		// assign a new control
		SpinControl ctrl = new SpinControl(getSpinDesignModel());

		if (value != null) {
			ctrl.setValue(value);
		}

		return ctrl;
	}
}