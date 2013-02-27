/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/imp/TreelistStateModelImp.java,v 1.8 2005/07/08 15:15:31 P001002 Exp $
 * $Revision: 1.8 $
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

import com.cc.framework.common.SortOrder;
import com.cc.framework.ui.model.TreelistStateModel;
import com.cc.framework.util.PropertyMap;

/**
 * Implementation of the TreelistStateModel interface
 *
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.8 $
 */
public class TreelistStateModelImp extends TreeStateModelImp implements TreelistStateModel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -4975764992049944558L;

	/**
	 * Current Page, which is displayed
	 */
	private int currentPage		= 0;

	/**
	 * Column to sort
	 */
	private String sortColumn	= null;

	/**
	 * The sort order
	 */
	private SortOrder sortOrder	= SortOrder.NONE;

	/**
	 * @see com.cc.framework.ui.model.ListStateModel#getCurrentPage()
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * @see com.cc.framework.ui.model.ListStateModel#getSortColumn()
	 */
	public String getSortColumn() {
		return sortColumn;
	}

	/**
	 * @see com.cc.framework.ui.model.ListStateModel#getSortOrder()
	 */
	public SortOrder getSortOrder() {
		return sortOrder;
	}

	/**
	 * @see com.cc.framework.ui.model.ListStateModel#setCurrentPage(int)
	 */
	public void setCurrentPage(int page) {
		this.currentPage = page;
	}

	/**
	 * @see com.cc.framework.ui.model.ListStateModel#setSortInfo(java.lang.String, com.cc.framework.common.SortOrder)
	 */
	public void setSortInfo(String column, SortOrder direction) {
		// go to the first page
		this.currentPage	= 0;

		this.sortColumn		= column;
		this.sortOrder		= direction;
	}

	/**
	 * @see com.cc.framework.ui.model.ListStateModel#resetSortInfo()
	 */
	public void resetSortInfo() {
		this.currentPage	= 0;
		this.sortColumn		= null;
		this.sortOrder		= SortOrder.NONE;
	}

	/**
	 * @see com.cc.framework.ui.model.StateModel#reset()
	 */
	public void reset() {
		super.reset();

		currentPage	= 0;
		sortColumn	= null;
		sortOrder	= SortOrder.NONE;
	}

	/**
	 * @see com.cc.framework.ui.model.StateModel#synchronizeState(com.cc.framework.util.PropertyMap)
	 */
	public void synchronizeState(PropertyMap properties) throws Exception {
		if (properties.hasProperty(PROP_PAGE)) {
			currentPage = Integer.parseInt(properties.getProperty(PROP_PAGE));
		}

		if (properties.hasProperty(PROP_EXPANDED)) {
			// Reset the current expansion state
			collapseAll();

			// retrieve the expanded nodes from the property map
			String[] nodes = properties.getProperties(PROP_EXPANDED);
			for (int i = 0; i < nodes.length; i++) {
				expand(nodes[i]);
			}
		}
	}
}
