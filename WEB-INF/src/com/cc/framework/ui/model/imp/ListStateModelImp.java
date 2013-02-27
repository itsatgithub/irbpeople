/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/imp/ListStateModelImp.java,v 1.8 2005/07/08 15:15:32 P001002 Exp $
 * $Revision: 1.8 $
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
import java.util.HashSet;

import com.cc.framework.common.SortOrder;
import com.cc.framework.ui.model.ListStateModel;
import com.cc.framework.util.PropertyMap;

/**
 * Implementation of the ListState model
 *
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.8 $
 */
public class ListStateModelImp implements ListStateModel, Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 7300878752577742210L;

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
	 * Collection with the highlight rows
	 */
	private HashSet highlight	= new HashSet();

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
	 * @see com.cc.framework.ui.model.ListStateModel#isMarked(java.lang.String)
	 */
	public boolean isMarked(String uniqueKey) {
		return highlight.contains(uniqueKey);
	}

	/**
	 * @see com.cc.framework.ui.model.ListStateModel#getMarked()
	 */
	public String[] getMarked() {
		return (String[]) highlight.toArray(new String[highlight.size()]);
	}

	/**
	 * @see com.cc.framework.ui.model.ListStateModel#mark(java.lang.String)
	 */
	public void mark(String uniqueKey) {
		if ((uniqueKey != null) && !highlight.contains(uniqueKey)) {
			highlight.add(uniqueKey);
		}
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
	 * @see com.cc.framework.ui.model.ListStateModel#unmark(java.lang.String)
	 */
	public void unmark(String uniqueKey) {
		if ((uniqueKey != null) && highlight.contains(uniqueKey)) {
			highlight.remove(uniqueKey);
		}
	}

	/**
	 * @see com.cc.framework.ui.model.ListStateModel#unmarkAll()
	 */
	public void unmarkAll() {
		highlight.clear();
	}

	/**
	 * @see com.cc.framework.ui.model.StateModel#reset()
	 */
	public void reset() {
		currentPage	= 0;
		sortColumn	= null;
		sortOrder	= SortOrder.NONE;

		highlight.clear();
	}

	/**
	 * @see com.cc.framework.ui.model.StateModel#synchronizeState(com.cc.framework.util.PropertyMap)
	 */
	public void synchronizeState(PropertyMap properties) throws Exception {
		if (properties.hasProperty(PROP_PAGE)) {
			currentPage = Integer.parseInt(properties.getProperty(PROP_PAGE));
		}
	}
}
