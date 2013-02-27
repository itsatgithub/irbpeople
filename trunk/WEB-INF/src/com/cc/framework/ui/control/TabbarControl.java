/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/control/TabbarControl.java,v 1.12 2005/07/08 14:18:42 P001002 Exp $
 * $Revision: 1.12 $
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

import java.util.Vector;

import com.cc.framework.ui.Color;
import com.cc.framework.ui.ImageMap;
import com.cc.framework.ui.model.ControlDesignModel;
import com.cc.framework.ui.model.DataModel;
import com.cc.framework.ui.model.StateModel;
import com.cc.framework.ui.model.TabDesignModel;
import com.cc.framework.ui.model.TabsetDataModel;
import com.cc.framework.ui.model.TabsetDesignModel;
import com.cc.framework.ui.model.TabsetStateModel;
import com.cc.framework.ui.model.imp.TabsetStateModelImp;

/**
 * TabbarControl
 *
 * @author   <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version  $Revision: 1.12 $
 * @since    1.2
 */
public class TabbarControl extends Control implements TabsetStateModel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 2435397495685794475L;

	/**
	 * Datamodel
	 */
	private TabsetDataModel dataModel		= null;

	/**
	 * Designmodel
	 */
	private TabsetDesignModel designModel	= null;

	/**
	 * Statemodel
	 */
	private TabsetStateModel stateModel		= null;

	/**
	 * Constructor
	 */
	public TabbarControl() {
		super();

		stateModel = doCreateStatemodel();
	}

	/**
	 * Creates the state model for this control instance
	 *
	 * @return		State model instance
	 */
	protected TabsetStateModel doCreateStatemodel() {
		return new TabsetStateModelImp();
	}

	/**
	 * @see com.cc.framework.ui.control.Control#getDesignModel()
	 */
	public ControlDesignModel getDesignModel() {
		return designModel;
	}

	/**
	 * @see com.cc.framework.ui.control.Control#getDataModel()
	 */
	public DataModel getDataModel() {
		return dataModel;
	}

	/**
	 * @see com.cc.framework.ui.control.Control#getStateModel()
	 */
	public StateModel getStateModel() {
		return stateModel;
	}

	/**
	 * Sets the StateModel
	 * @param	stateModel	TabsetStateModel
	 */
	public void setStateModel(TabsetStateModel stateModel) {
		this.stateModel	= stateModel;
	}

	/**
	 * @see com.cc.framework.ui.model.TabsetStateModel#getSelectedTab()
	 */
	public String getSelectedTab() {
		return stateModel.getSelectedTab();
	}

	/**
	 * @see com.cc.framework.ui.model.TabsetStateModel#setSelectedTab(java.lang.String)
	 */
	public void setSelectedTab(String selected) {
		stateModel.setSelectedTab(selected);
	}

	/**
	 * @see com.cc.framework.ui.model.StateModel#reset()
	 */
	public void reset() {
		stateModel.reset();
	}

	/**
	 * Sets the DesignModel
	 * @param	designModel	TabsetDesignModel
	 */
	public void setDesignModel(TabsetDesignModel designModel) {
		this.designModel = designModel;
	}

	/**
	 * Returns the Number of visible TabPages
	 * @return	int
	 */
	public int getMaxVisible() {
		return designModel.getMaxVisible();
	}

	/**
	 * Returns the maximal length of the Labels on the TabPages
	 * @return int
	 */
	public int getLabelLength() {
		return designModel.getLabelLength();
	}

	/**
	 * Returns if the TabPage is selected
	 * @param	tab	TabDesignModel
	 * @return	boolean
	 */
	public boolean isSelected(TabDesignModel tab) {
		return stateModel.isSelected(tab);
	}

	/**
	 * @see com.cc.framework.ui.model.TabsetStateModel#getFirstVisibleTab()
	 */
	public String getFirstVisibleTab() {
		return stateModel.getFirstVisibleTab();
	}

	/**
	 * @see com.cc.framework.ui.model.TabsetStateModel#setFirstVisibleTab(java.lang.String)
	 */
	public void setFirstVisibleTab(String first) {
		stateModel.setFirstVisibleTab(first);
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

	/**
	 * Returns all Tabpages
	 * @return	TabDesignModel
	 */
	public TabDesignModel[] getTabs() {
		return designModel.getTabs();
	}

	/**
	 * Returns all visible tab pages
	 * 
	 * @return		List of visible tabs
	 */
	public TabDesignModel[] getVisibleTabs() {
		TabDesignModel[] tabs = getTabs();

		Vector visible = new Vector();
		for (int i = 0; i < tabs.length; i++) {
			if (tabs[i].show(getPrincipal())) {
				visible.add(tabs[i]);
			}
		}
		
		return (TabDesignModel[]) visible.toArray(new TabDesignModel[visible.size()]);
	}

	/**
	 * Returns the Background Color
	 * @return	Color
	 */
	public Color getBgColor() {
		return designModel.getBgColor();
	}

	/**
	 * Returns the ImageMap
	 * @return	ImageMap
	 */
	public ImageMap getImageMap() {
		return designModel.getImageMap();
	}

	/**
	 * Returns the acossiated Action for a TabPage
	 * @param tab	TabDesignModel
	 * @return	String
	 */
	public String getAction(TabDesignModel tab) {
		if (tab.getAction() != null) {
			return tab.getAction();
		}

		return super.getAction();
	}

	// -------------------------------
	//        Action Handler
	//	-------------------------------

	/**
	 * The Defaulthandler for the onTabClick Event
	 * @param	ctx	ControlRequestContext
	 * @param	tabId	Id of the Tab
	 * @throws	Exception is thrown when an error occurs
	 */
	public void onTabClick(ControlRequestContext ctx, String tabId)
		throws Exception {

		log.debug("onTabClick(" + tabId + ")");

		setSelectedTab(tabId);
	}

	/**
	 * The Defaulthandler for the onTabScroll Event
	 * @param	ctx	ControlRequestContext
	 * @param	tabId	Id of the Tab to scroll to
	 * @throws	Exception is thrown when an error occurs
	 */
	public void onTabScroll(ControlRequestContext ctx, String tabId)
		throws Exception {

		log.debug("onTabScroll(" + tabId + ")");

		setFirstVisibleTab(tabId);
	}
}
