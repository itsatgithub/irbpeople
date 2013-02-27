/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/control/PanelControl.java,v 1.9 2005/07/08 14:18:42 P001002 Exp $
 * $Revision: 1.9 $
 * $Date: 2005/07/08 14:18:42 $
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
import com.cc.framework.ui.model.PanelContentDesignModel;
import com.cc.framework.ui.model.PanelDataModel;
import com.cc.framework.ui.model.PanelDesignModel;
import com.cc.framework.ui.model.PanelStateModel;
import com.cc.framework.ui.model.StateModel;

/**
 * Class PanelControl.
 *
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version $Revision: 1.9 $
 */
public class PanelControl extends Control implements PanelStateModel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 6441240482584341231L;

	/**
	 * The Datamodel
	 */
	private PanelDataModel dataModel	= null;

	/**
	 * The Designmodel
	 */
	private PanelDesignModel designModel	= null;

	/**
	 * @see com.cc.framework.ui.control.Control#getDataModel()
	 */
	public DataModel getDataModel() {
		return dataModel;
	}

	/**
	 * Sets the Datamodel
	 * @param	dataModel	PanelDataModel
	 */
	public void setDataModel(PanelDataModel dataModel) {
		this.dataModel	= dataModel;
	}

	/**
	 * @see com.cc.framework.ui.control.Control#getDesignModel()
	 */
	public ControlDesignModel getDesignModel() {
		return designModel;
	}

	/**
	 * sets the Designmodel
	 * @param	designModel		PanelDesignModel
	 */
	public void setDesignModel(PanelDesignModel designModel) {
		this.designModel	= designModel;
	}

	/**
	 * @see com.cc.framework.ui.control.Control#getStateModel()
	 */
	public StateModel getStateModel() {
		return this;
	}

	/**
	 * Returns a collection with content elements
	 *
	 * @return	PanelContentDesignModel
	 */
	public PanelContentDesignModel[] getContent() {
		return designModel.getContent();
	}

	/**
	 * @see com.cc.framework.ui.model.StateModel#reset()
	 */
	public void reset() {
		// no action
	}
}
