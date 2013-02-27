/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/control/SimpleListControl.java,v 1.52 2005/11/09 14:42:14 P001002 Exp $
 * $Revision: 1.52 $
 * $Date: 2005/11/09 14:42:14 $
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.cc.framework.adapter.RequestContext;
import com.cc.framework.common.SortOrder;
import com.cc.framework.security.Permission;
import com.cc.framework.ui.SelectMode;
import com.cc.framework.ui.model.ColumnDesignModel;
import com.cc.framework.ui.model.ControlDesignModel;
import com.cc.framework.ui.model.DataModel;
import com.cc.framework.ui.model.ImageModel;
import com.cc.framework.ui.model.InnerFrame;
import com.cc.framework.ui.model.ListDataModel;
import com.cc.framework.ui.model.ListDesignModel;
import com.cc.framework.ui.model.ListStateModel;
import com.cc.framework.ui.model.Sortable;
import com.cc.framework.ui.model.StateModel;
import com.cc.framework.ui.model.imp.ListStateModelImp;
import com.cc.framework.ui.painter.def.DefResources;
import com.cc.framework.util.Checker;
import com.cc.framework.util.ListHelp;
import com.cc.framework.util.PropertyComparator;

/**
 *The SimpleListControl
 *
 * @author    <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version   $Revision: 1.52 $
 * @since     1.0
 */
public class SimpleListControl extends ListControl implements ListStateModel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 34719847189696337L;

	/**
	 * The Datamodel
	 */
	private ListDataModel dataModel		= null;

	/**
	 * The Designmodel
	 */
	private ListDesignModel designModel	= null;

	/**
	 * The Statemodel
	 */
	private ListStateModel stateModel	= null;

	// ------------------------------------
	//           methods
	// ------------------------------------

	/**
	 * Constructor
	 */
	public SimpleListControl() {
		super();

		stateModel = doCreateStatemodel();
	}

	/**
	 * Creates the state model for this control instance
	 *
	 * @return		State model instance
	 */
	protected ListStateModel doCreateStatemodel() {
		return new ListStateModelImp();
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
	public void setDataModel(ListDataModel dataModel) {

		this.dataModel	= dataModel;

		// if a new datamodel is assigned it must
		// be initalized to show the first page
		setCurrentPage(0);
	}

	/**
	 * @see com.cc.framework.ui.control.Control#getDesignModel()
	 */
	public ControlDesignModel getDesignModel() {
		return designModel;
	}

	/**
	 * Sets the DesignModel
	 * @param	designModel	ListDesignModel
	 */
	public void setDesignModel(ListDesignModel designModel) {
		this.designModel	= designModel;
	}

	/**
	 * @see com.cc.framework.ui.control.Control#getStateModel()
	 */
	public StateModel getStateModel() {
		return this;
	}

	/**
	 * Sets the StateModel
	 * @param	stateModel	ListStateModel
	 */
	public void setStateModel(ListStateModel stateModel) {
		this.stateModel	= stateModel;
	}

	/**
	 * @see com.cc.framework.ui.control.ListControl#getPageButtons()
	 */
	public int getPageButtons() {
		return designModel.getPageButtons();
	}

	/**
	 * @see com.cc.framework.ui.control.ListControl#showFrame()
	 */
	public boolean showFrame() {
		return designModel.showFrame();
	}

	/**
	 * @see com.cc.framework.ui.control.ListControl#showHeader()
	 */
	public boolean showHeader() {
		return designModel.showHeader();
	}

	/**
	 * @see com.cc.framework.ui.control.ListControl#getCellPadding()
	 */
	public int getCellPadding() {
		return designModel.getCellPadding();
	}

	/**
	 * @see com.cc.framework.ui.control.ListControl#getCellSpacing()
	 */
	public int getCellSpacing() {
		return designModel.getCellSpacing();
	}

	/**
	 * @see com.cc.framework.ui.control.Control#showButton(com.cc.framework.ui.control.ControlButton)
	 */
	public boolean showButton(ControlButton button) {

		Permission permission = designModel.getButtonPermission(button);
		if (permission != null) {
			return permission.isGranted(getPrincipal());
		} else if (ControlButton.PAGE.equals(button)) {
			return getPageButtons() > 0;
		} else {
			return super.showButton(button);
		}
	}

	/**
	 * @see com.cc.framework.ui.control.ListControl#getVisibleColumns()
	 */
	public ColumnDesignModel[] getVisibleColumns() {

		// first get the visible columns
		ArrayList visible = new ArrayList();
		ColumnDesignModel[] all = getColumns();

		for (int i = 0; i < all.length; i++) {
			if (all[i].show(getPrincipal())) {
				visible.add(all[i]);
			}
		}

		ColumnDesignModel[] result = new ColumnDesignModel[visible.size()];
		visible.toArray(result);

		return result;
	}

	/**
	 * @see com.cc.framework.ui.control.ListControl#getColumns()
	 */
	public ColumnDesignModel[] getColumns() {
		return designModel.getColumns();
	}

	/**
	 * @see com.cc.framework.ui.control.ListControl#getLineIterator(boolean)
	 */
	public LineIterator getLineIterator(boolean positioned) {
		if (positioned) {
			return new ListLineIterator(this, dataModel, calcAbsoulteRowIndex(0), getPrincipal());
		} else {
			return new ListLineIterator(this, dataModel, 0, getPrincipal());
		}
	}

	/**
	 * Retrieves a list of inner frames
	 * that are matching the given layout hint
	 * 
	 * @param		layoutHint The layout hint that specifies
	 * 				what frames should be selected:
	 * 				<code>AlignmentType.TOP</code> - header frames
	 * 				<code>AlignmentType.BOTTOM</code> - footer frames
	 * @return		Frame list
	 */
	public InnerFrame[] getInnerFrames(Object layoutHint) {
		return designModel.getInnerFrames(getPrincipal(), layoutHint);
	}

	/**
	 * @see com.cc.framework.ui.control.ListControl#getCurrentPage()
	 */
	public int getCurrentPage() {
		int cur = stateModel.getCurrentPage();
		
		int totalPages = getTotalPages();
		if ((totalPages >= 0) && (cur > 0) && (cur >= totalPages)) {
			// The current page is out of bounds
			// Adjust the page to a existing page 
			cur = Math.max(0, totalPages - 1);
			setCurrentPage(cur); 
		}

		// The current page is now within the bounds
		// of valid pages
		return cur;
	}

	/**
	 * @see com.cc.framework.ui.model.ListStateModel#setCurrentPage(int)
	 */
	public void setCurrentPage(int page) {
		if (page == -1) {
			// go to the last page
			stateModel.setCurrentPage(getTotalPages() - 1);
		} else {
			stateModel.setCurrentPage(page);
		}
	}

	/**
	 * @see com.cc.framework.ui.model.ListStateModel#getSortColumn()
	 */
	public String getSortColumn() {
		return stateModel.getSortColumn();
	}

	/**
	 * @see com.cc.framework.ui.model.ListStateModel#getSortOrder()
	 */
	public SortOrder getSortOrder() {
		return stateModel.getSortOrder();
	}

	/**
	 * @see com.cc.framework.ui.control.ListControl#getSortOrder(java.lang.String)
	 */
	public SortOrder getSortOrder(String column) {
		if ((getSortColumn() == null) || !getSortColumn().equals(column)) {
			// the column is not the sort column
			return SortOrder.NONE;
		}

		return stateModel.getSortOrder();
	}

	/**
	 * Sets the sort order for the specified column
	 * @param	column		Name of the column
	 * @param	direction	The sort order
	 */
	public void setSortInfo(String column, SortOrder direction) {
		stateModel.setSortInfo(column, direction);
	}

	/**
	 * @see com.cc.framework.ui.model.ListStateModel#resetSortInfo()
	 */
	public void resetSortInfo() {
		stateModel.resetSortInfo();
	}

	/**
	 * @see com.cc.framework.ui.model.StateModel#reset()
	 */
	public void reset() {
		stateModel.reset();
	}

	/**
	 * @see com.cc.framework.ui.control.ListControl#getRowsPerPage()
	 */
	public int getRowsPerPage() {
		return designModel.getRowCount();
	}

	/**
	 * @see com.cc.framework.ui.control.ListControl#getMinRowCount()
	 */
	public int getMinRowCount() {
		return designModel.getMinRowCount();
	}

	/**
	 * Returns the Number of total Rows in the Datamodel
	 *
	 * @return	number of rows. Returns -1 when the number
	 * 			of rows is unknown
	 */
	public int getTotalRowCount() {
		if (dataModel == null) {
			return 0;
		}

		return dataModel.size();
	}

	/**
	 * @see com.cc.framework.ui.control.ListControl#getTotalPages()
	 */
	public int getTotalPages() {

		if (getRowsPerPage() == -1) {
			return 1;
		} else if (getTotalRowCount() == -1) {
			return -1;
		} else {
			return ((getRowsPerPage() - 1) + getTotalRowCount()) / getRowsPerPage();
		}
	}

	/**
	 * Calculates the Number of rows which should be diplayed on the Page
	 * @return int
	 */
	public int getRowsOnCurrentPage() {

		if (getCurrentPage() == -1) {
			return 0;
		}

		if (getRowsPerPage() == -1) {
			return getTotalRowCount();
		} else if (getTotalRowCount() == -1) {
			// the number of rows is unknown
			return getRowsPerPage();
		} else {
			return Math.min(getRowsPerPage(),
							getTotalRowCount() - getRowsPerPage() * getCurrentPage());
		}
	}

	/**
	 * Berechent anhand eines seitenrelativen Zeilenindex einen
	 * absoluten Index.
	 *
	 * @param relRowIndex nullbasierter seitenrelativer Zeilenindex
	 * @return Liefert einen nullbasierten absoluten Zeilenindex zurück
	 */
	public int calcAbsoulteRowIndex(int relRowIndex) {
		return (getCurrentPage() * getRowsPerPage()) + relRowIndex;
	}

	/**
	 * Berechent anhand eines absoluten Zeilenindex einen
	 * seitenrelativen Index.
	 *
	 * @param absRowIndex nullbasierter sabsoluter Zeilenindex
	 * @return Liefert einen nullbasierten seitenrelativen Zeilenindex zurück
	 */
	public int calcRelativeRowIndex(int absRowIndex) {
		return (absRowIndex - (getCurrentPage() * getRowsPerPage()));
	}

	/**
	 * @see com.cc.framework.ui.control.ListControl#getTitle()
	 */
	public String getTitle() {
		return designModel.getTitle();
	}

	/**
	 * @see com.cc.framework.ui.control.ListControl#getDetailText()
	 */
	public String[] getDetailText() {

		if (getRowsOnCurrentPage() == 0) {
			// no entries
			return new String[] {DefResources.FW_ITEMS_NOENTRIES};
		} else if (getTotalPages() == 1) {
			if (1 == getTotalRowCount()) {
				// 1 item
				return new String[] {DefResources.FW_ITEMS_1TE};
			} else {
				// xx items
				return new String[] {DefResources.FW_ITEMS, Integer.toString(getTotalRowCount())};
			}
		} else {
			int first	= calcAbsoulteRowIndex(0) + 1;
			int last	= first + getRowsOnCurrentPage();

			if (getTotalPages() == -1) {
				// 1 to x
				return new String[] {DefResources.FW_ITEMS_INFINITE,
										Integer.toString(first),
										Integer.toString(last - 1),
									 };
			} else {
				// 1 to x from n
				return new String[] {DefResources.FW_ITEMS_RANGE,
										Integer.toString(first),
										Integer.toString(last - 1),
										Integer.toString(getTotalRowCount())
									 };
			}
		}
	}

	/**
	 * @see com.cc.framework.ui.control.ListControl#getImage()
	 */
	public ImageModel getImage() {
		return designModel.getImage();
	}

	/**
	 * @see com.cc.framework.ui.control.ListControl#getEmptyText()
	 */
	public String getEmptyText() {
		return designModel.getEmptyText();
	}

	/**
	 * @see com.cc.framework.ui.control.ListControl#getSelectMode()
	 */
	public SelectMode getSelectMode() {
		return designModel.getSelectMode();
	}

	/**
	 * Returns true if the First Page is displayed
	 * @return boolean
	 */
	public boolean isFirstPage() {
		return (getCurrentPage() <= 0);
	}

	/**
	 * Returns true if the Last Page is displayed
	 * @return boolean
	 */
	public boolean isLastPage() {
		return ((getCurrentPage() + 1) == getTotalPages());
	}

	/**
	 * @see com.cc.framework.ui.control.ListControl#getRowFromKey(java.lang.String)
	 */
	public Object getRowFromKey(String rowkey) {

		if (getDataModel() == null) {
			return null;
		} else {
			try {
				return ListHelp.getLineFromKey((ListDataModel) getDataModel(), rowkey);
			} catch (Exception e) {
				log.error(e);
				return null;
			}
		}
	}

	/**
	 * This method is called to set the value of a checkbox
	 * column
	 *
	 * @param		ctx The Control action context
	 * @param		path Path Name of the input element
	 * @param		rowbean The Rowbean
	 * @param		property The Column name
	 * @param		checked <code>true</code> if the checkbox
	 * 				is checked
	 * @param		selectmode the selection mode of the column
	 * @throws		Exception a derived class can throw an exception
	 * 				if the value could not be set
	 */
	protected void setCheckboxValue(
		RequestContext ctx,
		ControlValuePath path,
		Object rowbean,
		String property,
		boolean checked,
		SelectMode selectmode)
		throws Exception {

		if (checked && SelectMode.SINGLE.equals(selectmode)) {
			String rowkey = path.getProperty(ControlValuePath.KEY_ROW);

			// Uncheck all checked rows
			ListHelp.uncheck(dataModel, property, new RowFilter(rowkey));
		}

		super.setCheckboxValue(ctx, path, rowbean, property, checked, selectmode);
	}

	/**
	 * This method is called to set the value of a radio button
	 * column
	 *
	 * @param		ctx The control action context
	 * @param		path Path Name of the input element
	 * @param		rowbean The Rowbean
	 * @param		property The Column name
	 * @param		value the radio button value
	 * @throws		Exception a derived class can throw an exception
	 * 				if the value could not be set
	 */
	protected void setRadioValue(
		RequestContext ctx,
		ControlValuePath path,
		Object rowbean,
		String property,
		String value)
		throws Exception {

		// Uncheck all checked rows
		ListHelp.uncheck(dataModel, property, new RowFilter(value));

		super.setRadioValue(ctx, path, rowbean, property, value);
	}

	/**
	 * @see com.cc.framework.ui.model.ListStateModel#isMarked(java.lang.String)
	 */
	public boolean isMarked(String uniqueKey) {
		return stateModel.isMarked(uniqueKey);
	}

	/**
	 * @see com.cc.framework.ui.model.ListStateModel#getMarked()
	 */
	public String[] getMarked() {
		return stateModel.getMarked();
	}

	/**
	 * @see com.cc.framework.ui.model.ListStateModel#mark(java.lang.String)
	 */
	public void mark(String uniqueKey) {
		stateModel.mark(uniqueKey);
	}

	/**
	 * @see com.cc.framework.ui.model.ListStateModel#unmark(java.lang.String)
	 */
	public void unmark(String uniqueKey) {
		stateModel.unmark(uniqueKey);
	}

	/**
	 * @see com.cc.framework.ui.model.ListStateModel#unmarkAll()
	 */
	public void unmarkAll() {
		stateModel.unmarkAll();
	}

	// --------------------------------
	//          action handler
	//	--------------------------------

	/**
	 * Defaulthandler for the <b>Page</b> Event
	 * @param	ctx		ControlRequestContext
	 * @param	newPage	Number of the new Page
	 * @throws	Exception is thrown when an error occurs
	 */
	public void onPage(ControlRequestContext ctx, int newPage)
		throws Exception {

		log.debug("onPage(" + newPage + ")");

		setCurrentPage(newPage);
	}

	/**
	 * Defaulthandler for the <b>Check</b> Event
	 * @param	ctx		ControlRequestContext
	 * @param	key		Unique Id as generated by the Datamodel to identify the Row
	 * @param	mode	SelectMode
	 * @param	check	true if checked
	 * @throws	Exception Indicates an error while iterating and
	 * 			executing the algorithm
	 */
	public void onCheck(
		ControlRequestContext ctx,
		String key,
		SelectMode mode,
		boolean check)
		throws Exception {

		log.debug("onCheck(" + key + "," + mode + "," + check + ")");

		if (SelectMode.NONE.equals(mode)) {
			// No selection mode!
			return;
		}

		// Iterate over all rows
		ListHelp.iterateNodes(dataModel, new Checker(mode, key, check));
	}

	/**
	 * Defaulthandler for the <b>CheckAll</b> Event
	 * @param	ctx		ControlRequestContext
	 * @param	mode	SelectMode
	 * @param	check	true if checked
	 * @throws	Exception Indicates an error while iterating and
	 * 			executing the algorithm
	 */
	public void onCheckAll(
		ControlRequestContext ctx,
		SelectMode mode,
		boolean check)
		throws Exception {

		log.debug("onCheckAll(" + mode + "," + check + ")");

		if (SelectMode.NONE.equals(mode)) {
			// No selection mode!
			return;
		}

		// Iterate over all rows
		ListHelp.iterateNodes(dataModel, new Checker(mode, check));
	}

	/**
	 * Defaulthandler for the <b>CheckColumn</b> Event
	 * @param	ctx		ControlRequestContext
	 * @param	column	the column
	 * @param	check	true if checked
	 * @throws	Exception Indicates an error while iterating and
	 * 			executing the algorithm
	 */
	public void onCheckColumn(
		ControlRequestContext ctx,
		String column,
		boolean check)
		throws Exception {

		log.debug("onCheckColumn(" + column + "," + check + ")");

		// Iterate over all rows
		if (check) {
			ListHelp.checkAll(dataModel, column);
		} else {
			ListHelp.uncheckAll(dataModel, column);
		}
	}

	/**
	 * Defaulthandler for the <b>Sort</b> Event
	 * @param	ctx			ControlRequestContext
	 * @param	column		Name of the Column
	 * @param	direction	SortOrder
	 * @throws	Exception is thrown when an error occurs
	 */
	public void onSort(
		ControlRequestContext ctx,
		String column,
		SortOrder direction)
		throws Exception {

		log.debug("onSort(" + column + "," + direction + ")");

		setSortInfo(column, direction);

		// Sort the List
		if (dataModel instanceof Sortable) {
			((Sortable) dataModel).sort(column, direction);
		} else if (dataModel instanceof List) {
			Collections.sort((List) dataModel, new PropertyComparator(column, direction));
		}
	}
}
