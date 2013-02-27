/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/control/CrumbsControl.java,v 1.9 2005/07/08 14:18:14 P001002 Exp $
 * $Revision: 1.9 $
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

import java.util.Arrays;
import java.util.Vector;

import com.cc.framework.ui.ImageMap;
import com.cc.framework.ui.model.ControlDesignModel;
import com.cc.framework.ui.model.CrumbModel;
import com.cc.framework.ui.model.CrumbsDataModel;
import com.cc.framework.ui.model.CrumbsDesignModel;
import com.cc.framework.ui.model.CrumbsStateModel;
import com.cc.framework.ui.model.DataModel;
import com.cc.framework.ui.model.StateModel;
import com.cc.framework.ui.model.imp.CrumbsStateModelImp;

/**
 * TabbarControl
 *
 * @author   <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version  $Revision: 1.9 $
 * @since    1.3
 */
public class CrumbsControl extends Control implements CrumbsStateModel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -8547819400052162462L;

	/**
	 * Datamodel
	 */
	private CrumbsDataModel dataModel = null;

	/**
	 * Designmodel
	 */
	private CrumbsDesignModel designModel = null;

	/**
	 * Designmodel
	 */
	private CrumbsStateModel stateModel = new CrumbsStateModelImp();

	/**
	 * Constructor
	 */
	public CrumbsControl() {
		super();

		stateModel = doCreateStatemodel();
	}

	/**
	 * Creates the state model for this control instance
	 *
	 * @return		State model instance
	 */
	protected CrumbsStateModel doCreateStatemodel() {
		return new CrumbsStateModelImp();
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
	 * Sets the data model
	 * @param	dataModel	DataModel
	 */
	public void setDataModel(CrumbsDataModel dataModel) {
		this.dataModel	= dataModel;
	}

	/**
	 * @see com.cc.framework.ui.control.Control#getStateModel()
	 */
	public StateModel getStateModel() {
		return stateModel;
	}

	/**
	 * Sets the StateModel
	 * @param	stateModel	CrumbsStateModel
	 */
	public void setStateModel(CrumbsStateModel stateModel) {
		this.stateModel	= stateModel;
	}

	/**
	 * Sets the DesignModel
	 * @param	designModel	TabsetDesignModel
	 */
	public void setDesignModel(CrumbsDesignModel designModel) {
		this.designModel = designModel;
	}

	/**
	 * Returns the maximal length of the Labels on the TabPages
	 * @return int
	 */
	public int getLabelLength() {
		return designModel.getLabelLength();
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
	 * @return	CrumbDesignModel
	 */
	public CrumbModel[] getCrumbs() {
		Vector crumbs = new Vector();

		if (dataModel != null) {
			crumbs.addAll(Arrays.asList(dataModel.getCrumbs()));
		}

		if (designModel != null) {
			crumbs.addAll(Arrays.asList(designModel.getCrumbs()));
		}

		return (CrumbModel[]) crumbs.toArray(new CrumbModel[crumbs.size()]);
	}

	/**
	 * Returns all visible crumbs
	 * 
	 * @return		List of visible crumbs
	 */
	public CrumbModel[] getVisibleCrumbs() {
		CrumbModel[] crumbs = getCrumbs();

		Vector visible = new Vector();
		for (int i = 0; i < crumbs.length; i++) {
			if (crumbs[i].show(getPrincipal())) {
				visible.add(crumbs[i]);
			}
		}
		
		return (CrumbModel[]) visible.toArray(new CrumbModel[visible.size()]);
	}

	/**
	 * @see com.cc.framework.ui.model.CrumbsStateModel#getSelectedCrumb()
	 */
	public String getSelectedCrumb() {
		return stateModel.getSelectedCrumb();
	}

	/**
	 * @see com.cc.framework.ui.model.CrumbsStateModel#setSelectedCrumb(java.lang.String)
	 */
	public void setSelectedCrumb(String selected) {
		stateModel.setSelectedCrumb(selected);
	}

	/**
	 * @see com.cc.framework.ui.model.StateModel#reset()
	 */
	public void reset() {
		stateModel.reset();
	}

	/**
	 * Returns <code>true</code> if the crumb is selected
	 * @param	crumb CrumbDesignModel
	 * @return	boolean
	 */
	public boolean isSelected(CrumbModel crumb) {
		return stateModel.isSelected(crumb);
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
	 * @param crumb	CrumbDesignModel
	 * @return	String
	 */
	public String getAction(CrumbModel crumb) {
		if (crumb.getAction() != null) {
			return crumb.getAction();
		}

		return super.getAction();
	}

	// -------------------------------
	//        Action Handler
	//	-------------------------------

	/**
	 * The Defaulthandler for the onCrumbClick Event
	 * @param	ctx		ControlRequestContext
	 * @param	crumb	Id of the Crumb
	 * @throws	Exception is thrown when an error occurs
	 */
	public void onCrumbClick(ControlRequestContext ctx, String crumb)
		throws Exception {

		log.debug("onCrumbClick(" + crumb + ")");
	}
}