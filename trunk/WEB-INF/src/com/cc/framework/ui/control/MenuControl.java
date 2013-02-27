/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/control/MenuControl.java,v 1.10 2005/07/08 14:18:14 P001002 Exp $
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

import com.cc.framework.ui.ImageMap;
import com.cc.framework.ui.MenuType;
import com.cc.framework.ui.model.ControlDesignModel;
import com.cc.framework.ui.model.DataModel;
import com.cc.framework.ui.model.MenuDataModel;
import com.cc.framework.ui.model.MenuDesignModel;
import com.cc.framework.ui.model.MenuItemDesignModel;
import com.cc.framework.ui.model.MenuStateModel;
import com.cc.framework.ui.model.StateModel;

/**
 * The MenuControl
 *
 * @author   <a href="mailto:hschulz@scc-gmbh.com">Gernot Schulz</a>
 * @version  $Revision: 1.10 $
 * @since    1.0
 */
public class MenuControl extends Control implements MenuStateModel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -1694482060729184843L;

	/**
	 * Datamodel
	 */
	private MenuDataModel dataModel	= null;

	/**
	 * Designmodel
	 */
	private MenuDesignModel designModel	= null;


	/**
	 * Constructor
	 */
	public MenuControl() {
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
	 * @param	dataModel	MenuDataModel
	 */
	public void setDataModel(MenuDataModel dataModel) {
		this.dataModel	= dataModel;
	}

	/**
	 * @see com.cc.framework.ui.control.Control#getDesignModel()
	 */
	public ControlDesignModel getDesignModel() {
		return designModel;
	}

	/**
	 * Sets the DesignModel
	 * @param	designModel	MenuDesignModel
	 */
	public void setDesignModel(MenuDesignModel designModel) {
		this.designModel	= designModel;
	}

	/**
	 * @see com.cc.framework.ui.control.Control#getStateModel()
	 */
	public StateModel getStateModel() {
		return this;
	}

	/**
	 * Returns the Type
	 * @return	MenuType
	 */
	public MenuType getType() {
		return designModel.getType();
	}

	/**
	 * Returns the Content
	 * @return	MenuItemDesignModel
	 */
	public MenuItemDesignModel[] getContent() {
		return designModel.getContent();
	}

	/**
	 * Returns the Id
	 * @return	String
	 */
	public String getMenuId() {
		return designModel.getMenuId();
	}

	/**
	 * Returns the ImageMap
	 * @return	ImageMap
	 */
	public ImageMap getImageMap() {
		return designModel.getImageMap();
	}

	/**
	 * @see com.cc.framework.ui.model.StateModel#reset()
	 */
	public void reset() {
		// No Action
	}
}
