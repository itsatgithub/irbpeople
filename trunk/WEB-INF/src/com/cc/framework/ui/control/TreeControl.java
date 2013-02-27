/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/control/TreeControl.java,v 1.31 2005/10/16 08:39:37 P001002 Exp $
 * $Revision: 1.31 $
 * $Date: 2005/10/16 08:39:37 $
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

import com.cc.framework.adapter.RequestContext;
import com.cc.framework.ui.ExpansionMode;
import com.cc.framework.ui.SelectMode;
import com.cc.framework.ui.model.ControlDesignModel;
import com.cc.framework.ui.model.DataModel;
import com.cc.framework.ui.model.StateModel;
import com.cc.framework.ui.model.TreeDesignModel;
import com.cc.framework.ui.model.TreeGroupDataModel;
import com.cc.framework.ui.model.TreeNodeDataModel;
import com.cc.framework.ui.model.TreeStateModel;
import com.cc.framework.ui.model.imp.TreeStateModelImp;
import com.cc.framework.util.PropertyMap;
import com.cc.framework.util.StringHelp;
import com.cc.framework.util.TreeHelp;

/**
 * TreeControl
 *
 * @author   <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version  $Revision: 1.31 $
 * @since    1.0
 */
public class TreeControl extends Control implements TreeStateModel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -2715231722961569328L;

	/**
	 * Property for the checked nodes
	 * -> this is not realy part of the controls state model.
	 *    but we need a way to synchronize the state of
	 *    checkboxes. The checkbox state is part of the controls
	 *    data model
	 */
	public static final String PROP_CHECKED	= "checked";

	/**
	 * Datamodel
	 */
	private TreeGroupDataModel dataModel	= null;

	/**
	 * Designmodel
	 */
	private TreeDesignModel designModel		= null;

	/**
	 * Designmodel
	 */
	private TreeStateModel stateModel		= null;

	/**
	 * Constructor
	 */
	public TreeControl() {
		super();

		stateModel = doCreateStateModel();
	}

	/**
	 * Creates the state model for this control instance
	 *
	 * @return		State model instance
	 */
	protected TreeStateModel doCreateStateModel() {
		return new TreeStateModelImp();
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
	 * @param	designModel	TreeDesignModel
	 */
	public void setDesignModel(TreeDesignModel designModel) {
		this.designModel = designModel;
	}

	/**
	 * @see com.cc.framework.ui.control.Control#getStateModel()
	 */
	public StateModel getStateModel() {
		return stateModel;
	}

	/**
	 * Sets the StateModel
	 * @param	stateModel	TreeStateModel
	 */
	public void setStateModel(TreeStateModel stateModel) {
		this.stateModel	= stateModel;
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
		return calcVisibleNodes(getFirstNode());
	}

	/**
	 * Die Methode berechnet die Anzahl der sichtbaren (=aufgeklappten)
	 * Einträge des Baumes.
	 * @param	root	TreeNodeDataModel
	 * @return	int
	 */
	protected int calcVisibleNodes(TreeNodeDataModel root) {

		int visible = 0;

		TreeLineIterator iter = new TreeLineIterator(this, root, getPrincipal());

		while (!iter.done()) {
			visible++;
			iter.next();
		}

		return visible;
	}

	// ----------------------------
	//       State Synchronization
	// ----------------------------

	/**
	 * @see com.cc.framework.ui.control.Control#synchronizeState(
	 * 		com.cc.framework.adapter.RequestContext,
	 * 		com.cc.framework.util.PropertyMap)
	 */
	public void synchronizeState(RequestContext ctx, PropertyMap properties)
		throws Exception {
		super.synchronizeState(properties);

		if ((dataModel != null) && properties.hasProperty(PROP_CHECKED)) {
			TreeHelp.checkNodes(
				dataModel,
				StringHelp.toStringSet(properties.getProperties(PROP_CHECKED)));
		}
	}

	// ----------------------------
	//       Action Handler
	// ----------------------------

	/**
	 * Defaulthandler for the onDrilldown Event
	 * @param	ctx		ControlRequestContext
	 * @param	key		Unique Id as generated by the Datamodel to identify the Row
	 * @throws	Exception is thrown when an error occurs
	 */
	public void onDrilldown(ControlRequestContext ctx, String key)
			throws Exception {
		log.debug("onDrilldown(" + key + ")");
	}

	/**
	 * Defaulthandler for the onExpandEx Event
	 * @param	ctx		ControlRequestContext
	 * @param	key		Unique Id as generated by the Datamodel to identify the Row
	 * @throws	Exception is thrown when an error occurs
	 */
	public void onExpandEx(ControlRequestContext ctx, String key)
			throws Exception {

		log.debug("onExpandEx(" + key + ")");

		onExpand(ctx, key);
	}

	/**
	 * Defaulthandler for the onExpand Event
	 * @param	ctx		ControlRequestContext
	 * @param	key		Unique Id as generated by the Datamodel to identify the Row
	 * @throws	Exception is thrown when an error occurs
	 */
	public void onExpand(ControlRequestContext ctx, String key)
			throws Exception {

		log.debug("onExpand(" + key + ")");

		expand(key);
	}

	/**
	 * Defaulthandler for the onCollapse Event
	 * @param	ctx		ControlRequestContext
	 * @param	key		Unique Id as generated by the Datamodel to identify the Row
	 * @throws	Exception is thrown when an error occurs
	 */
	public void onCollapse(ControlRequestContext ctx, String key)
			throws Exception {

		log.debug("onCollapse(" + key + ")");

		collapse(key);
	}

	/**
	 * Defaulthandler for the onCheck Event
	 * @param	ctx		ControlRequestContext
	 * @param	key		Unique Id as generated by the Datamodel to identify the Row
	 * @param	mode	SelectMode
	 * @param	check	true if the Node is checked
	 * @throws	Exception is thrown when an error occurs
	 */
	public void onCheck(ControlRequestContext ctx, String key, SelectMode mode,
			boolean check) throws Exception {

		log.debug("onCheck(" + key + "," + mode + "," + check + ")");

		// Only heck nodes when the datamodel exists
		if ((dataModel != null) && !SelectMode.NONE.equals(mode)) {
			// Retrieve the tree node from the key
			TreeNodeDataModel node = TreeHelp.getNodeByKey(dataModel, key);

			if (node != null) {
				// check the tree node (this works only for Checkable nodes)
				TreeHelp.checkNodes(node, check);
			}
		}
	}

	/**
	 * @see com.cc.framework.ui.model.TreeStateModel#mark(java.lang.String)
	 */
	public void mark(String uniqueKey) {
		stateModel.mark(uniqueKey);
	}

	/**
	 * @see com.cc.framework.ui.model.TreeStateModel#isMarked(java.lang.String)
	 */
	public boolean isMarked(String nodeKey) {
		return stateModel.isMarked(nodeKey);
	}

	/**
	 * Retrieves all marked items
	 * 
	 * @return	Array with the keys of the marked items
	 */
	public String[] getMarked() {
		return stateModel.getMarked();
	}

	/**
	 * Highlights a specified row.
	 * 
	 * @param	nodeKey	the nodes unique key
	 */
	public void unmark(String nodeKey) {
		stateModel.unmark(nodeKey);
	}

	/**
	 * Restes all highlighted rows  
	 */
	public void unmarkAll() {
		stateModel.unmarkAll();
	}
}