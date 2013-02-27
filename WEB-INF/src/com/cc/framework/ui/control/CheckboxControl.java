/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/control/CheckboxControl.java,v 1.17 2005/09/12 10:33:37 P001002 Exp $
 * $Revision: 1.17 $
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

import com.cc.framework.adapter.RequestContext;
import com.cc.framework.ui.model.CheckboxDataModel;
import com.cc.framework.ui.model.CheckboxDesignModel;
import com.cc.framework.ui.model.CheckboxStateModel;
import com.cc.framework.ui.model.ControlDesignModel;
import com.cc.framework.ui.model.DataModel;
import com.cc.framework.ui.model.StateModel;

/**
 * The Checkbox control
 *
 * @author    <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version   $Revision: 1.17 $
 * @since     1.0
 */
public class CheckboxControl extends Control implements CheckboxStateModel, CheckboxDataModel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -7415713691147762929L;

	/**
	 * DesignModel for the control
	 */
	private CheckboxDesignModel designModel	= null;

	/**
	 * The check state
	 */
	private Boolean checked = null;

	/**
	 * Constructor
	 *
	 * @param	designModel		CheckboxDesignModel
	 */
	public CheckboxControl(CheckboxDesignModel designModel) {
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
	 * @see com.cc.framework.ui.model.CheckboxDataModel#isChecked()
	 */
	public Boolean isChecked() {
		return checked;
	}

	/**
	 * @see com.cc.framework.ui.model.CheckboxDataModel#setChecked(java.lang.Boolean)
	 */
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	/**
	 * The values "on" or "off" ar passed in the HTTP-Request
	 * 
	 * @see com.cc.framework.ui.control.Control#setValue(com.cc.framework.adapter.RequestContext, com.cc.framework.ui.control.ControlValuePath, java.lang.String[])
	 */
	public void setValue(RequestContext ctx, ControlValuePath path, String[] values) {
		setChecked(new Boolean("on".equalsIgnoreCase(values[0])));
	}
}
