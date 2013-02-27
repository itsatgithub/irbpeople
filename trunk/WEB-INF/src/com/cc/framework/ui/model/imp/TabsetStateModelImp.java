/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/imp/TabsetStateModelImp.java,v 1.7 2005/07/08 15:15:32 P001002 Exp $
 * $Revision: 1.7 $
 * $Date: 2005/07/08 15:15:32 $
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

package com.cc.framework.ui.model.imp;

import java.io.Serializable;

import com.cc.framework.ui.model.TabDesignModel;
import com.cc.framework.ui.model.TabsetStateModel;
import com.cc.framework.util.PropertyMap;

/**
 * Implementation of the TabsetStateModel interface
 *
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.7 $
 */
public class TabsetStateModelImp implements TabsetStateModel, Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -5636528312673648426L;

	/**
	 * The selected Tabpage
	 */
	private String selected = null;

	/**
	 * The first visible Tabpage
	 */
	private String first = null;

	/**
	 * @see com.cc.framework.ui.model.TabsetStateModel#getFirstVisibleTab()
	 */
	public String getFirstVisibleTab() {
		return first;
	}

	/**
	 * @see com.cc.framework.ui.model.TabsetStateModel#setFirstVisibleTab(java.lang.String)
	 */
	public void setFirstVisibleTab(String first) {
		this.first = first;
	}

	/**
	 * @see com.cc.framework.ui.model.TabsetStateModel#getSelectedTab()
	 */
	public String getSelectedTab() {
		return selected;
	}

	/**
	 * @see com.cc.framework.ui.model.TabsetStateModel#setSelectedTab(java.lang.String)
	 */
	public void setSelectedTab(String selected) {
		this.selected = selected;
	}

	/**
	 * @see com.cc.framework.ui.model.TabsetStateModel#isSelected(com.cc.framework.ui.model.TabDesignModel)
	 */
	public boolean isSelected(TabDesignModel tab) {
		if (tab == null) {
			return false;
		}

		if (selected == null) {
			return "".equals(tab.getTabId());
		} else {
			return selected.equals(tab.getTabId());
		}
	}

	/**
	 * @see com.cc.framework.ui.model.StateModel#reset()
	 */
	public void reset() {
		// reset the selection
		selected	= null;
		first		= null;
	}

	/**
	 * @see com.cc.framework.ui.model.StateModel#synchronizeState(com.cc.framework.util.PropertyMap)
	 */
	public void synchronizeState(PropertyMap properties) throws Exception {
		if (properties.hasProperty(PROP_SELECTED)) {
			selected = properties.getProperty(PROP_SELECTED);
		}

		if (properties.hasProperty(PROP_FIRST)) {
			first = properties.getProperty(PROP_FIRST);
		}
	}
}