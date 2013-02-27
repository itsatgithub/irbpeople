/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/control/ButtonControl.java,v 1.13 2005/07/08 14:18:13 P001002 Exp $
 * $Revision: 1.13 $
 * $Date: 2005/07/08 14:18:13 $
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

import com.cc.framework.ui.model.ButtonDesignModel;
import com.cc.framework.ui.model.ButtonStateModel;
import com.cc.framework.ui.model.ButtonType;
import com.cc.framework.ui.model.ControlDesignModel;
import com.cc.framework.ui.model.DataModel;
import com.cc.framework.ui.model.StateModel;

/**
 * Button control
 *
 * @author      <a href="mailto:gschulz@scc-gmbh.com">Gernot Schulz</a>
 * @version     $Revision: 1.13 $
 * @since       1.0
 */
public class ButtonControl extends Control implements ButtonStateModel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -7359505871319551138L;

	/**
	 * the design model for the button
	 */
	private ButtonDesignModel designModel = null;

	// ----------------------------------
	//       methods
	// ----------------------------------

	/**
	 * Constructor
	 */
	public ButtonControl() {
		super();
	}

	/**
	 * @see com.cc.framework.ui.control.Control#getDataModel()
	 */
	public DataModel getDataModel() {
		return null;
	}

	/**
	 * @see com.cc.framework.ui.model.StateModel#reset()
	 */
	public void reset() {
		// no action
	}

	/**
	 * @see com.cc.framework.ui.control.Control#getDesignModel()
	 */
	public ControlDesignModel getDesignModel() {
		return designModel;
	}

	/**
	 * Sets the design model
	 *
	 * @param	designModel	The design model
	 */
	public void setDesignModel(ButtonDesignModel designModel) {
		this.designModel = designModel;
	}

	/**
	 * @see com.cc.framework.ui.control.Control#getStateModel()
	 */
	public StateModel getStateModel() {
		return this;
	}

	/**
	 * Returns the text
	 *
	 * @return	The text
	 */
	public String getText() {
		return designModel.getText();
	}

	/**
	 * Returns the type of the button
	 *
	 * @return The type of the button
	 */
	public ButtonType getButtonType() {
		return designModel.getButtonType();
	}

	/**
	 * Returns the src.
	 *
	 * @return String
	 */
	public String getSrc() {
		return designModel.getSrc();
	}

	/**
	 * Gets the base directory for the resource
	 *
	 * @return		base directory or <code>null</code>
	 */
	public String getBase() {
		return designModel.getBase();
	}

	/**
	 * Returns the Id for the button style
	 * used to get the background images for
	 * a text button
	 * @return	The button style id
	 */
	public int getButtonStyle() {
		return designModel.getButtonStyle();
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