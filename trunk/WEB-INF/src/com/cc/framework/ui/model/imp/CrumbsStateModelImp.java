/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/imp/CrumbsStateModelImp.java,v 1.6 2005/07/08 15:15:31 P001002 Exp $
 * $Revision: 1.6 $
 * $Date: 2005/07/08 15:15:31 $
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

import com.cc.framework.ui.model.CrumbModel;
import com.cc.framework.ui.model.CrumbsStateModel;
import com.cc.framework.util.PropertyMap;

/**
 * Implementation of the CrumbsStateModel interface
 *
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.6 $
 */
public class CrumbsStateModelImp implements CrumbsStateModel, Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 5375988008002299545L;

	/**
	 * The selected Crumb
	 */
	private String selected = null;

	/**
	 * @see com.cc.framework.ui.model.CrumbsStateModel#getSelectedCrumb()
	 */
	public String getSelectedCrumb() {
		return selected;
	}

	/**
	 * @see com.cc.framework.ui.model.CrumbsStateModel#setSelectedCrumb(java.lang.String)
	 */
	public void setSelectedCrumb(String selected) {
		this.selected = selected;
	}

	/**
	 * @see com.cc.framework.ui.model.CrumbsStateModel#isSelected(com.cc.framework.ui.model.CrumbModel)
	 */
	public boolean isSelected(CrumbModel crumb) {
		if (crumb == null) {
			return false;
		}

		if (selected == null) {
			return "".equals(crumb.getCrumbId());
		} else {
			return selected.equals(crumb.getCrumbId());
		}
	}

	/**
	 * @see com.cc.framework.ui.model.StateModel#reset()
	 */
	public void reset() {
		// reset the selection
		selected = null;
	}

	/**
	 * @see com.cc.framework.ui.model.StateModel#synchronizeState(com.cc.framework.util.PropertyMap)
	 */
	public void synchronizeState(PropertyMap properties) throws Exception {
		if (properties.hasProperty(PROP_SELECTED)) {
			selected = properties.getProperty(PROP_SELECTED);
		}
	}
}
