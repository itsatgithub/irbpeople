/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/control/RadioControl.java,v 1.10 2005/09/12 10:33:37 P001002 Exp $
 * $Revision: 1.10 $
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

package com.cc.framework.ui.control;

import com.cc.framework.ui.model.ControlDesignModel;
import com.cc.framework.ui.model.DataModel;
import com.cc.framework.ui.model.RadioDataModel;
import com.cc.framework.ui.model.RadioDesignModel;
import com.cc.framework.ui.model.RadioStateModel;
import com.cc.framework.ui.model.StateModel;

/**
 * Control for radio buttons
 *
 * @author   <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version  $Revision: 1.10 $
 * @since    1.0
 */
public class RadioControl extends Control implements RadioStateModel, RadioDataModel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -5973962111126854953L;

	/**
	 * The designmodel
	 */
	private RadioDesignModel designModel = null;

	/**
	 * Checked flag
	 */
	private Boolean checked = null;

	/**
	 * Constructor
	 *
	 * @param	designModel	The	design model
	 */
	public RadioControl(RadioDesignModel designModel) {
		super();

		this.designModel = designModel;
	}

	/**
	 * @see com.cc.framework.ui.control.Control#getDataModel()
	 */
	public DataModel getDataModel() {
		return null;
	}

	/**
	 * @see com.cc.framework.ui.control.Control#getDesignModel()
	 */
	public ControlDesignModel getDesignModel() {
		return designModel;
	}

	/**
	 * @see com.cc.framework.ui.control.Control#getStateModel()
	 */
	public StateModel getStateModel() {
		return this;
	}

	/**
	 * @see com.cc.framework.ui.model.StateModel#reset()
	 */
	public void reset() {
	}

	/**
	 * @see com.cc.framework.ui.model.RadioDataModel#isChecked()
	 */
	public Boolean isChecked() {
		return checked;
	}

	/**
	 * @see com.cc.framework.ui.model.RadioDataModel#setChecked(java.lang.Boolean)
	 */
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	/**
	 * Returns the value
	 * @return	The value
	 */
	public String getValue() {
		return designModel.getValue();
	}
}