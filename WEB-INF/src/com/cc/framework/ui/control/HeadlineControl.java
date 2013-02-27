/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/control/HeadlineControl.java,v 1.8 2005/07/08 14:18:13 P001002 Exp $
 * $Revision: 1.8 $
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

import com.cc.framework.ui.model.ControlDesignModel;
import com.cc.framework.ui.model.DataModel;
import com.cc.framework.ui.model.HeadlineDataModel;
import com.cc.framework.ui.model.HeadlineDesignModel;
import com.cc.framework.ui.model.HeadlineStateModel;
import com.cc.framework.ui.model.StateModel;

/**
 * The headline control
 *
 * @author    <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version   $Revision: 1.8 $
 * @since     1.0
 */
public class HeadlineControl extends Control implements HeadlineStateModel, HeadlineDataModel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -1798550626184066171L;

	/**
	 * The Designmodel
	 */
	private HeadlineDesignModel designModel = null;

	/**
	 * @see com.cc.framework.ui.control.Control#getDataModel()
	 */
	public DataModel getDataModel() {
		return this;
	}

	/**
	 * @see com.cc.framework.ui.control.Control#getDesignModel()
	 */
	public ControlDesignModel getDesignModel() {
		return designModel;
	}

	/**
	 * Sets the DesignModel
	 * @param	designModel	HeadlineDesignModel
	 */
	public void setDesignModel(HeadlineDesignModel designModel) {
		this.designModel = designModel;
	}

	/**
	 * @see com.cc.framework.ui.control.Control#getStateModel()
	 */
	public StateModel getStateModel() {
		return this;
	}

	/**
	 * Returns the caption
	 * @return	String
	 */
	public String getCaption() {
		return designModel.getCaption();
	}

	/**
	 * Returns the detail text
	 * @return	String
	 */
	public String getDetail() {
		return designModel.getDetail();
	}

	/**
	 * Returns the Filter Flag
	 * @return	boolean
	 */
	public boolean getFilter() {
		return designModel.getFilter();
	}

	/**
	 * @see com.cc.framework.ui.model.StateModel#reset()
	 */
	public void reset() {
		// no action
	}
}
