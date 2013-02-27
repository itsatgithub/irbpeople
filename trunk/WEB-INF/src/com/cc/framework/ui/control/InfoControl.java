/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/control/InfoControl.java,v 1.10 2005/07/08 14:18:14 P001002 Exp $
 * $Revision: 1.10 $
 * $Date: 2005/07/08 14:18:14 $
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
import com.cc.framework.ui.model.InfoDataModel;
import com.cc.framework.ui.model.InfoDesignModel;
import com.cc.framework.ui.model.InfoStateModel;
import com.cc.framework.ui.model.StateModel;

/**
 * The info control
 *
 * @author    <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version   $Revision: 1.10 $
 * @since     1.0
 */
public class InfoControl extends Control implements InfoStateModel {
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -6608025346069900263L;

	/**
	 * Datamodel
	 */
	private InfoDataModel dataModel		= null;

	/**
	 * Designmodel
	 */
	private InfoDesignModel designModel	= null;

	/**
	 * Constructor
	 */
	public InfoControl() {
		super();
	}

	/**
	 * @see com.cc.framework.ui.control.Control#getDataModel()
	 */
	public DataModel getDataModel() {
		return dataModel;
	}

	/**
	 * Sets the Datamodel
	 *
	 * @param	dataModel	DataModel
	 */
	public void setDataModel(InfoDataModel dataModel) {
		this.dataModel	= dataModel;
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
	 * Sets the DesignModel
	 *
	 * @param	designModel		InfoDesignModel
	 */
	public void setDesignModel(InfoDesignModel designModel) {
		this.designModel	= designModel;
	}

	/**
	 * Returns the resource.
	 *
	 * @return String
	 */
	public String getResource() {
		return designModel.getResource();
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
	 * @return	<code>true</code> if the frame should be shown
	 */
	public boolean showFrame() {
		return designModel.showFrame();
	}

	/**
	 * Retrieves the raw HTML content of the control
	 * 
	 * @return		raw HTML content
	 */
	public String getContent() {
		return designModel.getContent();
	}

	/**
	 * @see com.cc.framework.ui.model.StateModel#reset()
	 */
	public void reset() {
		// no action
	}

}