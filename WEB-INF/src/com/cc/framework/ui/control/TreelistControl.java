/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/control/TreelistControl.java,v 1.58 2005/11/15 18:07:26 P001002 Exp $
 * $Revision: 1.58 $
 * $Date: 2005/11/15 18:07:26 $
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

import com.cc.framework.adapter.RequestContext;
import com.cc.framework.common.SortOrder;
import com.cc.framework.security.Permission;
import com.cc.framework.ui.ExpansionMode;
import com.cc.framework.ui.SelectMode;
import com.cc.framework.ui.model.ColumnDesignModel;
import com.cc.framework.ui.model.ControlDesignModel;
import com.cc.framework.ui.model.DataModel;
import com.cc.framework.ui.model.ImageModel;
import com.cc.framework.ui.model.InnerFrame;
import com.cc.framework.ui.model.StateModel;
import com.cc.framework.ui.model.TreeGroupDataModel;
import com.cc.framework.ui.model.TreeNodeDataModel;
import com.cc.framework.ui.model.TreelistDesignModel;
import com.cc.framework.ui.model.TreelistStateModel;
import com.cc.framework.ui.model.imp.TreelistStateModelImp;
import com.cc.framework.ui.painter.def.DefResources;
import com.cc.framework.util.TreeHelp;

/**
 * The Tree List Control.
 *
 * @author   <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version  $Revision: 1.58 $
 * @since    1.0
 */
public class TreelistControl extends ListControl implements TreelistStateModel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 4335388539071682449L;

	/**
	 * Datamodel for the control
	 */
	private TreeGroupDataModel dataModel		= null;

	/**
	 * Designmodel for the control
	 */
	private TreelistDesignModel designModel		= null;

	/**
	 * Statemodel
	 */
	private TreelistStateModel stateModel		= null;

	// ------------------------------------
	//           methods
	// ------------------------------------

	/**
	 * Constructor
	 */
	public TreelistControl() {
		super();

		stateModel = doCreateStatemodel();
	}

	/**
	 * Creates the state model for this control instance
	 *
	 * @return		State model instance
	 */
	protected TreelistStateModel doCreateStatemodel() {
		return new TreelistStateModelImp();
	}

	/**
	 * @see com.cc.framework.ui.control.Control#getDataModel()
	 */
	public DataModel getDataModel() {
		return dataModel;
	}

	/**
	 * Sets the Datamodel
	 * @param	dataModel	TreeGroupDataModel
	 */
	public void setDataModel(TreeGroupDataModel dataModel) {

		this.dataModel = dataModel;

		// if a new datamodel is assigned it must
		// be initalized to show the first page
		setCurrentPage(0);

		// Open the root node
		resetExpandInfo(true);
	}

	/**
	 * @see com.cc.framework.ui.control.Control#getDesignModel()
	 */
	public ControlDesignModel getDesignModel() {
		return designModel;
	}

	/**
	 * Sets the DesignModel
	 * @param	designModel	TreelistDesignModel
	 */
	public void setDesignModel(TreelistDesignModel designModel) {
		this.designModel = designModel;
	}

	/**
	 * @see com.cc.framework.ui.control.Control#getStateModel()
	 */
	public StateModel getStateModel() {
		return this;
	}

	/**
	 * Sets the StateModel
	 * @param	stateModel	TreeListStateModel
	 */
	public void setStateModel(TreelistStateModel stateModel) {
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

		// Zuerst werden die sichtbaren Spalten bestimmt
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
			return new TreeLineIterator(this, getFirstItemOfPage(), getPrincipal());
		} else {
			return new TreeLineIterator(this, dataModel, getPrincipal());
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
		return stateModel.getCurrentPage();
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

		// Expand the root node
		resetExpandInfo(false);
	}

	/**
	 * Resets all expanded Nodes
	 *
	 * @param	keepExpanded <code>true</code> to keep the current expansion
	 * 			state of the control
	 */
	protected void resetExpandInfo(boolean keepExpanded) {

		if (!keepExpanded) {
			stateModel.collapseAll();
		}

		// Der Wurzelknoten soll initial immer geöffnet dargestellt
		// werden.
		if (dataModel != null) {
			stateModel.expand(dataModel.getUniqueKey());
		}
	}

	/**
	 * Returns the number of rows on a page
	 *
	 * @return	The number of rows
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
	 * Returns the first node to be displayed
	 *
	 * @return	TreeNodeDataModel
	 */
	protected TreeNodeDataModel getFirstNode() {
		if (designModel.showRoot()) {
			return dataModel;
		} else if ((dataModel != null) && (dataModel.size() > 0)) {
			return dataModel.getChild(0);
		} else {
			return null;
		}
	}

	/**
	 * Liefert die Anzahl der tatsächlich vorhandenen Zeilen zurück.
	 *
	 * @return Anzahl der tatsächlich vorhandenen Zeilen des Datenmodells
	 */
	public int getTotalRowCount() {
		int nodes = calcVisibleNodes(dataModel);

		if (designModel.showRoot()) {
			return nodes;
		} else {
			return --nodes;
		}
	}

	/**
	 * Die Methode berechnet die Anzahl der sichtbaren (=aufgeklappten)
	 * Einträge des Baumes.
	 * @param	root	TreeNodeDataModel
	 * @return	int
	 */
	protected int calcVisibleNodes(TreeNodeDataModel root) {

		// Calculate the number of visible nodes
		return TreeHelp.calcVisibleNodes(root, this, getPrincipal());
	}

	/**
	 * Calculates the absolute row index from a page relative
	 * row index
	 *
	 * @param	relRowIndex page relativ row index
	 * @return	int absolute row index
	 */
	protected int calcAbsoulteRowIndex(int relRowIndex) {
		return (getCurrentPage() * getRowsPerPage()) + relRowIndex;
	}

	/**
	 * Method getNodeFromIndex
	 *
	 * @param	rowIndex absolute row index
	 * @return	TreeNodeDataModel  or <code>null</code>
	 */
	protected TreeNodeDataModel getNodeFromIndex(int rowIndex) {

		TreeLineIterator iter = new TreeLineIterator(this, getFirstNode(), getPrincipal());

		iter.skip(rowIndex);

		if (iter.done()) {
			return null;
		} else {
			return (TreeNodeDataModel) iter.current();
		}
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
	 * Liefert die Anzahl der Zeilen welche auf der aktuellen Seite
	 * dargestellt werden sollen.
	 * @return int
	 */
	protected int getRowsOnCurrentPage() {

		if (-1 == getCurrentPage()) {
			return 0;
		}

		if (-1 == getRowsPerPage()) {
			return getTotalRowCount();
		} else {
			return Math.min(
				getRowsPerPage(),
				getTotalRowCount() - getRowsPerPage() * getCurrentPage());
		}
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
		if (getRowsOnCurrentPage() <= 0) {
			// no entries
			return new String[] {DefResources.FW_PAGE_NOENTRIES};
		} else {
			int first	= getCurrentPage();
			int last	= getTotalPages();

			if (last == -1) {
				// 1 to x
				return new String[] {
					DefResources.FW_PAGE_INFINITE,
					Integer.toString(first + 1)};

			} else if (1 == last) {
				// page 1
				return new String[] {
					DefResources.FW_PAGE_1TE};
			} else {
				// page {current} of {total}

				// Bei dem Outline Kontrollelement macht die Anzahl der total
				// verfügbaren Zeieln keinen großen Sinn, da diese von den
				// geöffneten Knoten abhängig ist.
				// Es werden deshalb nur die Zahl der verfügbaren Seiten
				// ausgegeben. Dies ist wertungsfreier.
				return new String[] {
					DefResources.FW_PAGE_RANGE,
					Integer.toString(first + 1),
					Integer.toString(last)};
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
	 * Liefert das erste Item der aktuell angezeigten Seite zurück
	 * @return	TreeNodeDataModel
	 */
	public TreeNodeDataModel getFirstItemOfPage() {
		return getNodeFromIndex(calcAbsoulteRowIndex(0));
	}

	/**
	 * Checks if the given node is expanded
	 *
	 * @param		node The node to check
	 * @return		returns <code>true<code> if the node is expanded
	 */
	public boolean isExpanded(TreeNodeDataModel node) {
		if (node == null) {
			return false;
		} else {
			return isExpanded(node.getUniqueKey());
		}
	}

	/**
	 * @see com.cc.framework.ui.model.TreeStateModel#isExpanded(java.lang.String)
	 */
	public boolean isExpanded(String nodeKey) {
		// Abhängig vom eingestellten Expansionsmodus wird
		// immer alles aufgeklappt dargestellt.
		if ((designModel == null) || ExpansionMode.FULL.equals(designModel.getExpansionMode())) {
			return true;
		}

		return stateModel.isExpanded(nodeKey);
	}

	/**
	 * Expandes the given node
	 *
	 * @param	node the node to expand
	 */
	public void expand(TreeNodeDataModel node) {
		if (node != null) {
			if ((designModel == null) || ExpansionMode.SINGLE.equals(designModel.getExpansionMode())) {
				// Bei der Einfachauswahl werden alle Knoten bis einschließlich
				// dem selektierten Knoten geöffnet
				resetExpandInfo(false);
			}

			if ((designModel == null) || !ExpansionMode.FULL.equals(designModel.getExpansionMode())) {
				// Expand all parent nodes from bottom up
				while (node != null) {
					stateModel.expand(node.getUniqueKey());
					node = node.getParent();
				}
			}
		}
	}

	/**
	 * @see com.cc.framework.ui.model.TreeStateModel#expand(java.lang.String)
	 */
	public void expand(String key) {
		if ((designModel == null) || ExpansionMode.SINGLE.equals(designModel.getExpansionMode())) {
			// Bei der Einfachauswahl werden alle Knoten bis einschließlich
			// dem selektierten Knoten geöffnet
			resetExpandInfo(false);
		}

		if ((designModel == null) || !ExpansionMode.FULL.equals(designModel.getExpansionMode())) {
			expand(TreeHelp.getNodeByKey(dataModel, key));
		}
	}

	/**
	 * Expands the whole tree.
	 * Note: Only the nodes in memory will be expanded!
	 */
	public void expandAll() {
		expandToLevel(-1);
	}

	/**
	 * Expands the tree up to the given level.
	 * Note: Only the nodes in memory will be expanded!
	 *
	 * @param		level Tree level (0 = root level)
	 */
	public void expandToLevel(int level) {

		if ((designModel == null) || !ExpansionMode.FULL.equals(designModel.getExpansionMode())) {
			collapseAll();

			if (dataModel != null) {
				TreeHelp.expandToLevel(dataModel, level, stateModel);
			}
		}
	}

	/**
	 * @see com.cc.framework.ui.model.TreeStateModel#collapseAll()
	 */
	public void collapseAll() {
		stateModel.collapseAll();
	}

	/**
	 * Collapses the given node
	 *
	 * @param node the node to collapse
	 */
	public void collapse(TreeNodeDataModel node) {
		if (node != null) {
			collapse(node.getUniqueKey());
		}
	}

	/**
	 * @see com.cc.framework.ui.model.TreeStateModel#collapse(java.lang.String)
	 */
	public void collapse(String nodeKey) {
		if (!ExpansionMode.FULL.equals(designModel.getExpansionMode())) {
			stateModel.collapse(nodeKey);
		}
	}

	/**
	 * Marks the given node as selected
	 *
	 * @param node Tree node
	 */
	public void select(TreeNodeDataModel node) {
		if (node != null) {
			select(node.getUniqueKey());
		}
	}

	/**
	 * @see com.cc.framework.ui.model.TreeStateModel#select(java.lang.String)
	 */
	public void select(String nodeKey) {
		stateModel.select(nodeKey);
	}

	/**
	 * @see com.cc.framework.ui.model.TreeStateModel#unselectAll()
	 */
	public void unselectAll() {
		stateModel.unselectAll();
	}

	/**
	 * Checks if the given node is selected
	 *
	 * @param node the node to check
	 * @return returns <code>true</code> if the node is checked
	 */
	public boolean isSelected(TreeNodeDataModel node) {
		if (node == null) {
			return false;
		} else {
			return stateModel.isSelected(node.getUniqueKey());
		}
	}

	/**
	 * @see com.cc.framework.ui.model.TreeStateModel#isSelected(java.lang.String)
	 */
	public boolean isSelected(String nodeKey) {
		return stateModel.isSelected(nodeKey);
	}

	/**
	 * @see com.cc.framework.ui.model.TreeStateModel#getSelected()
	 */
	public String getSelected() {
		return stateModel.getSelected();
	}

	/**
	 * @see com.cc.framework.ui.control.ListControl#getRowFromKey(java.lang.String)
	 */
	public Object getRowFromKey(String rowkey) {

		if (getDataModel() == null) {
			return null;
		} else {
			return TreeHelp.getNodeByKey((TreeNodeDataModel) getDataModel(), rowkey);
		}
	}

	/**
	 * This method is called to set the value of a checkbox
	 * column
	 *
	 * @param		ctx The control action context
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
			TreeHelp.uncheck(dataModel, property, new RowFilter(rowkey));
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
		TreeHelp.uncheck(dataModel, property, new RowFilter(value));

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

	// --------------------------
	//       Action Handler
	// --------------------------

	/**
	 * Default Handler for the <b>ExpandEx</b> Event
	 * @param	ctx	ControlRequestContext
	 * @param	key	Unique Id as generated by the Datamodel to identify the Row
	 * @throws	Exception is thrown when an error occurs
	 */
	public void onExpandEx(ControlRequestContext ctx, String key)
		throws Exception {

		log.debug("onExpandEx(" + key + ")");

		onExpand(ctx, key);
	}

	/**
	 * Default Handler for the <b>Expand</b> Event
	 * @param	ctx	ControlRequestContext
	 * @param	key	Unique Id as generated by the Datamodel to identify the Row
	 * @throws	Exception is thrown when an error occurs
	 */
	public void onExpand(ControlRequestContext ctx, String key)
		throws Exception {

		log.debug("onExpand(" + key + ")");

		expand(key);
	}

	/**
	 * Default Handler for the <b>Collapse</b> Event
	 * @param	ctx	ControlRequestContext
	 * @param	key	Unique Id as generated by the Datamodel to identify the Row
	 * @throws	Exception is thrown when an error occurs
	 */
	public void onCollapse(ControlRequestContext ctx, String key)
		throws Exception {

		log.debug("onCollapse(" + key + ")");

		collapse(key);
	}

	/**
	 * Default Handler for the <b>Check</b> Event
	 * @param	ctx		ControlRequestContext
	 * @param	key		Unique Id as generated by the Datamodel to identify the Row
	 * @param	mode	SelectMode
	 * @param	check	true if checked
	 * @throws	Exception is thrown when an error occurs
	 */
	public void onCheck(
		ControlRequestContext ctx,
		String key,
		SelectMode mode,
		boolean check)
		throws Exception {

		log.debug("onCheck(" + key + "," + mode + "," + check + ")");

		// Wenn ein Datenmodell vorhanden ist
		if ((dataModel != null) && !SelectMode.NONE.equals(mode)) {
			// Knotenobjekt ermitteln
			TreeNodeDataModel node = TreeHelp.getNodeByKey(dataModel, key);

			// Wenn alles erfolgreich verlaufen ist, dann sollte der Zustand des
			// TreeNodeModel entsprechend angepaßt werden.
			if (node != null) {
				// ACHTUNG: Hier werden nur Checkable Knoten berücksichtigt!
				TreeHelp.checkNodes(node, check);
			}
		}
	}

	/**
	 * Default Handler for the <b>CheckAll</b> Event
	 * @param	ctx		ControlRequestContext
	 * @param	mode	SelectMode
	 * @param	check	true if checked
	 * @throws	Exception is thrown when an error occurs
	 */
	public void onCheckAll(
		ControlRequestContext ctx,
		SelectMode mode,
		boolean check)
		throws Exception {

		log.debug("onCheckAll(" + mode + "," + check + ")");

		// Wenn ein Datenmodell vorhanden ist
		if ((dataModel != null) && !SelectMode.NONE.equals(mode)) {
			// ACHTUNG: Hier werden nur Checkable Knoten berücksichtigt!
			TreeHelp.checkNodes(dataModel, check);
		}
	}

	/**
	 * Default Handler for the <b>Page</b> Event
	 * @param	ctx			ControlRequestContext
	 * @param	newPage		Index of the Page
	 * @throws	Exception is thrown when an error occurs
	 */
	public void onPage(ControlRequestContext ctx, int newPage)
		throws Exception {

		log.debug("onPage(" + newPage + ")");

		setCurrentPage(newPage);
	}

	/**
	 * Default Handler for the <b>Sort</b> Event
	 * @param	ctx			ControlRequestContext
	 * @param	column		Column Name
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
	}
}