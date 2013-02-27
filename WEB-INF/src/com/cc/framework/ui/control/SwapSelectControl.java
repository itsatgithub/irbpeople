/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/control/SwapSelectControl.java,v 1.10 2005/07/08 14:18:41 P001002 Exp $
 * $Revision: 1.10 $
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

import com.cc.framework.ui.OrientationType;
import com.cc.framework.ui.model.ControlDesignModel;
import com.cc.framework.ui.model.DataModel;
import com.cc.framework.ui.model.OptionListDesignModel;
import com.cc.framework.ui.model.StateModel;
import com.cc.framework.ui.model.SwapSelectDataModel;
import com.cc.framework.ui.model.SwapSelectDesignModel;
import com.cc.framework.ui.model.SwapSelectStateModel;

/**
 * Control element for the select element with two windows
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.10 $
 */
public class SwapSelectControl extends Control implements SwapSelectStateModel, SwapSelectDataModel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -3583924541067959306L;

	/**
	 * The Designmodell
	 */
	private SwapSelectDesignModel designModel	= null;

	/**
	 * Text Value
	 */
	private Object[] values						= null;

	/**
	 * The option list
	 */
	private OptionListDesignModel options		= null;

	/**
	 * Additional HTML Body
	 */
	private String body							= null;

	/**
	 * The Option elements
	 */
	private Object optionElements				= null;

	/**
	 * Constructor
	 */
	public SwapSelectControl() {
		super();
	}

	/**
	 * Constructor
	 *
	 * @param	designModel	SelectDesignModel
	 */
	public SwapSelectControl(SwapSelectDesignModel designModel) {
		super();

		this.designModel = designModel;
	}

	/**
	 * Sets the DesignModel
	 * @param	designModel	SwapSelectDesignModel
	 */
	public void setDesignModel(SwapSelectDesignModel designModel) {
		this.designModel	= designModel;
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
	 * @see com.cc.framework.ui.model.SwapSelectDataModel#getValues()
	 */
	public Object[] getValues() {
		return values;
	}

	/**
	 * @see com.cc.framework.ui.model.SwapSelectDataModel#setValues(java.lang.Object[])
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

	/**
	 * Retrieves the label for the left selection window
	 * 
	 * @return	the label
	 */
	public String getLabelLeft() {
		return designModel.getLabelLeft();
	}

	/**
	 * Retrieves the label for the right selection window
	 * 
	 * @return	the label
	 */
	public String getLabelRight() {
		return designModel.getLabelRight();
	}

	/**
	 * Checks if the labels should be visible
	 * 
	 * @return		<code>true</code> if the labels should be
	 * 				visible
	 */
	public boolean showLabels() {
		return (getLabelLeft() != null) || (getLabelRight() != null);
	}
	
	/**
	 * Retrieves the orientation of the selection windows
	 * 
	 * @return	the orientation 
	 */
	public OrientationType getOrientation() {
		return designModel.getOrientation();
	}

	/**
	 * Returns if the filter is activated (default=true). This means
	 * that all Strings which should be displayed in the HTML page
	 * are html encoded
	 *
	 * @return	<code>true</code> if string will be html encoded;
	 * 			<code>false</code> otherwise
	 */
	public boolean filter() {
		return designModel.filter();
	}
}
