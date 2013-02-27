/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/control/SelectControl.java,v 1.22 2005/07/08 14:18:41 P001002 Exp $
 * $Revision: 1.22 $
 * $Date: 2005/07/08 14:18:41 $
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
import com.cc.framework.ui.model.OptionListDesignModel;
import com.cc.framework.ui.model.SelectDataModel;
import com.cc.framework.ui.model.SelectDesignModel;
import com.cc.framework.ui.model.SelectStateModel;
import com.cc.framework.ui.model.StateModel;

/**
 * The SelectControl
 *
 * @author    <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version   $Revision: 1.22 $
 * @since     1.0
 */
public class SelectControl extends Control implements SelectStateModel, SelectDataModel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -1511853568508451120L;

	/**
	 * The Designmodell
	 */
	private SelectDesignModel designModel	= null;

	/**
	 * Text Value
	 */
	private Object[] values					= null;

	/**
	 * The option list
	 */
	private OptionListDesignModel options	= null;

	/**
	 * Additional HTML Body
	 */
	private String body						= null;

	/**
	 * The Option elements
	 */
	private Object optionElements			= null;

	/**
	 * Constructor
	 *
	 * @param	designModel	SelectDesignModel
	 */
	public SelectControl(SelectDesignModel designModel) {
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
	 * Sets the DesignModel
	 * @param	designModel	SwapSelectDesignModel
	 */
	public void setDesignModel(SelectDesignModel designModel) {
		this.designModel	= designModel;
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
	 * @see com.cc.framework.ui.model.SelectDataModel#getValues()
	 */
	public Object[] getValues() {
		return values;
	}

	/**
	 * @see com.cc.framework.ui.model.SelectDataModel#setValues(java.lang.Object[])
	 */
	public void setValues(Object[] values) {
		this.values = values;
	}

	/**
	 * Returns the option list.
	 *
	 * @return OptionListDesignModel
	 */
	public OptionListDesignModel getOptions() {
		return options;
	}

	/**
	 * Sets a option list.
	 *
	 * @param options The options to set
	 * @param elements The Option elements
	 */
	public void setOptions(OptionListDesignModel options, Object elements) {
		this.options		= options;
		this.optionElements	= elements;
	}

	/**
	 * Returns the Option elements
	 *
	 * @return		Elements
	 */
	public Object getOptionElements() {
		return optionElements;
	}

	/**
	 * Returns the Size (Numer of visible Characters)
	 * @return	int
	 */
	public int getSize() {
		return designModel.getSize();
	}

	/**
	 * Indicates that multiple selections is allowed.
	 *
	 * @return		returns <code>true</code> for a multiple
	 * 				selection control
	 */
	public boolean isMultiple() {
		return designModel.isMultiple();
	}

	/**
	 * Returns the optional HTML Body of the control
	 *
	 * @return		HTML string
	 */
	public String getBody() {
		return body;
	}

	/**
	 * Sets the optional HTML Body of the control.
	 *
	 * @param		string HTML String
	 */
	public void setBody(String string) {
		body = string;
	}
}