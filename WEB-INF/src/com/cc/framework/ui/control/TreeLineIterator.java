/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/control/TreeLineIterator.java,v 1.23 2005/07/08 14:19:11 P001002 Exp $
 * $Revision: 1.23 $
 * $Date: 2005/07/08 14:19:11 $
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

import com.cc.framework.security.Principal;
import com.cc.framework.ui.model.AccessControlled;
import com.cc.framework.ui.model.TreeGroupDataModel;
import com.cc.framework.ui.model.TreeNodeDataModel;
import com.cc.framework.ui.model.TreeStateModel;
import com.cc.framework.util.TreeHelp;
import com.cc.framework.util.Util;

/**
 * A TreeLineIterator
 *
 * @author   <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version  $Revision: 1.23 $
 * @since    1.0
 */
public class TreeLineIterator implements LineIterator {

	/**
	 * Field current
	 */
	private TreeNodeDataModel current	= null;

	/**
	 * The state model of the tree that holds the expansion state
	 * of the tree items.
	 */
	private TreeStateModel state		= null;

	/**
	 * The principal objekt used to check permissions
	 */
	private Principal principal			= null;

	/**
	 * Constructor for TreeLineIterator
	 *
	 * @param	current		TreeNodeDataModel
	 * @param	principal	Principal
	 */
	public TreeLineIterator(TreeNodeDataModel current, Principal principal) {
		super();

		this.principal	= principal;
		this.current	= current;
		this.state		= null;
	}

	/**
	 * Constructor for TreeLineIterator
	 *
	 * @param	state		TreeStateModel
	 * @param	current		TreeNodeDataModel
	 * @param	principal	Principal
	 */
	public TreeLineIterator(TreeStateModel state, TreeNodeDataModel current, Principal principal) {
		super();

		this.principal	= principal;
		this.current	= current;
		this.state		= state;
	}

	/**
	 * Creates a new tree iterator that is located
	 * on the last visible item
	 *
	 * @param	state		TreeStateModel
	 * @param	parent		TreeNodeDataModel
	 * @param	principal	Principal
	 * @return	TreeLineIterator
	 */
	public static TreeLineIterator locateOnLastVisibleChild(TreeStateModel state, TreeNodeDataModel parent, Principal principal) {

		TreeLineIterator iter = new TreeLineIterator(state, null, principal);

		TreeNodeDataModel node = iter.getLastVisibleTreeItem(parent);

		iter.locate((node == null) ? parent : node);

		return iter;
	}

	/**
	 * Locates the tree iterator on the given node
	 *
	 * @param current	TreeNodeDataModel
	 */
	protected void locate(TreeNodeDataModel current) {
		this.current = current;
	}

	/**
	 * Checks if there tree iterator has reached the end of
	 * the collection
	 *
	 * @see com.cc.framework.ui.control.LineIterator#done()
	 */
	public boolean done() {
		return (current == null);
	}

	/**
	 * Returns the current tree node
	 *
	 * @see com.cc.framework.ui.control.LineIterator#current()
	 */
	public Object current() {
		return current;
	}

	/**
	 * @see com.cc.framework.ui.control.LineIterator#current(String property)
	 */
	public Object current(String property) {
		if (property == null) {
			return null;
		}

		Object bean = current();

		if (bean == null) {
			return null;
		}

		return Util.getSafeProperty(bean, property);
	}

	/**
	 * @see com.cc.framework.ui.control.LineIterator#isValidProperty(java.lang.String)
	 */
	public boolean isValidProperty(String property) {
		if (property == null) {
			return false;
		}

		Object bean = current();

		if (bean == null) {
			return false;
		}

		return Util.isValidProperty(bean, property);
	}

	/**
	 * Returns the unique key of the current node
	 *
	 * @see com.cc.framework.ui.control.LineIterator#currentKey()
	 */
	public String currentKey() {
		return current.getUniqueKey();
	}

	/**
	 * Checks if the current node is expanded
	 *
	 * @return boolean
	 */
	public boolean isCurrentExpanded() {
		return isExpanded(current);
	}

	/**
	 * Checks if the given node is expanded
	 *
	 * @param		node the node to check
	 * @return		boolean
	 */
	public boolean isExpanded(TreeNodeDataModel node) {
		if (state == null) {
			return true;
		} else if (node == null) {
			return false;
		} else {
			return state.isExpanded(node.getUniqueKey());
		}
	}

	/**
	 * Checks if the current node is selected
	 *
	 * @return	boolean
	 */
	public boolean isCurrentSelected() {
		if (state == null) {
			return false;
		} else if (current == null) {
			return false;
		} else {
			return state.isSelected(current.getUniqueKey());
		}
	}

	/**
	 * Returns the check state of the current tree node
	 *
	 * @see com.cc.framework.ui.control.LineIterator#currentCheckState()
	 */
	public int currentCheckState() {
		return TreeHelp.getCheckState(current);
	}

	/**
	 * Moves the iterator to the next visible tree
	 * node
	 *
	 * @see com.cc.framework.ui.control.LineIterator#next()
	 */
	public void next() {
		current = getNextVisibleItem(current);
	}

	/**
	 * Moves the iterator to the previous visible tree node
	 *
	 * @see com.cc.framework.ui.control.LineIterator#prev()
	 */
	public void prev() {
		current = getPrevVisibleItem(current);
	}

	/**
	 * Skips a certain number of visible tree nodes.
	 * The method kan skip in both directions
	 *
	 * @param	index Numer of visible rows to skip
	 */
	public void skip(int index) {
		if (index < 0) {
			for (int i = 0; (current != null) && (i > index); i--) {
				current = getPrevVisibleItem(current);
			}
		} else if (index > 0) {
			for (int i = 0; (current != null) && (i < index); i++) {
				current = getNextVisibleItem(current);
			}
		}
	}

	/**
	 * Returns the number of visible children of the current tree
	 * node
	 *
	 * @return	number of visible children
	 */
	public int getVisibleChildren() {
		if (current == null) {
			return 0;
		}

		return countVisibleNodes(current) - 1;
	}

	/**
	 * Checks if the given node is the last visible node in
	 * a child list
	 *
	 * @param	item TreeNodeDataModel
	 * @return	boolean
	 */
	public boolean isLastVisibleChild(TreeNodeDataModel item) {
		if (item == null) {
			return false;
		}

		if (item.getParent() == null) {
			return false;
		}

		return getNextVisibleSibling(item, false) == null;
	}

	/**
	 * Checks if the given item is visible
	 *
	 * @param	item	TreeNodeDataModel
	 * @return	boolean
	 */
	protected boolean isVisible(TreeNodeDataModel item) {

		if ((principal != null) && (item instanceof AccessControlled)) {
			// The node is access controlled. So let the principal
			// check if the node is visible
			AccessControlled ac = (AccessControlled) item;

			return ac.show(principal);
		}

		return true;
	}

	/**
	 * Counts the visible nodes in the given subtree
	 *
	 * @param		item the root node of the subtree
	 * @return		Numner of visible nodes
	 */
	protected int countVisibleNodes(TreeNodeDataModel item) {
		if ((item == null) || !isVisible(item)) {
			return 0;
		}

		// The node itself is visible
		int visible = 1;

		if (item instanceof TreeGroupDataModel) {
			TreeGroupDataModel group = (TreeGroupDataModel) item;

			if (isExpanded(group)) {
				for (int i = 1; i < group.size(); i++) {

					// count all visible child subtrees
					visible += countVisibleNodes(group.getChild(i));
				}
			}
		}

		return visible;
	}

	/**
	 * retrieves the last visible node in the given subtree
	 *
	 * @param	item the root of the subtree
	 * @return	TreeNodeDataModel or <code>null</code>
	 */
	protected TreeNodeDataModel getLastVisibleTreeItem(TreeNodeDataModel item) {
		// stop when the node is not visible
		if (!isVisible(item)) {
			return null;
		}

		TreeNodeDataModel lastVisible = item;

		if (item instanceof TreeGroupDataModel) {
			TreeGroupDataModel groupItem = (TreeGroupDataModel) item;

			if ((groupItem.size() > 0) && isExpanded(groupItem)) {

				// Search the last visible child of the group
				int i = groupItem.size() - 1;

				while ((lastVisible == null) && (i >= 0)) {
					lastVisible	= getLastVisibleTreeItem(groupItem.getChild(i--));
				}
			}
		}

		return lastVisible;
	}

	/**
	 * returns the previous visible item oft the given node
	 *
	 * @param	item TreeNodeDataModel
	 * @return	TreeNodeDataModel or <code>null</code>
	 */
	protected TreeNodeDataModel getPrevVisibleItem(TreeNodeDataModel item) {
		TreeNodeDataModel prev = null;

		if (item.getParent() != null) {
			TreeGroupDataModel parent = item.getParent();

			int i = getChildIndex(item);

			// move to the (first) privious node
			--i;

			// search for the last visible child in the
			// previous nodes subtree
			while ((prev == null) && (i >= 0)) {
				prev = getLastVisibleTreeItem(parent.getChild(i--));
			}

			// If no visible sibling is available then the parent node
			// must be the privious visible item
			if (prev == null) {
				prev = parent;
			}
		}

		return prev;
	}

	/**
	 * returns the next visible item of the given node
	 *
	 * @param	item	TreeNodeDataModel
	 * @return	TreeNodeDataModel or <code>null</code>
	 */
	protected TreeNodeDataModel getNextVisibleItem(TreeNodeDataModel item) {

		if (item instanceof TreeGroupDataModel) {
			TreeGroupDataModel groupItem = (TreeGroupDataModel) item;

			if ((groupItem.size() > 0) && isExpanded(groupItem)) {
				// search for the first visible child
				for (int i = 0; i <  groupItem.size(); i++) {
					TreeNodeDataModel child = groupItem.getChild(i);

					if (isVisible(child)) {
						return child;
					}
				}
			}
		}

		// When no visible node was found in the subtree, then extend the
		// search to the siblings nodes. The search can be extended to the
		// root of the tree
		return getNextVisibleSibling(item, true);
	}

	/**
	 * Searches for a sibling node of the given child node
	 *
	 * @param		child TreeNodeDataModel
	 * @param		expandSearch permission to expand the search towards
	 * 				the root of the tree
	 * @return		TreeNodeDataModel or <code>null</code>
	 */
	protected TreeNodeDataModel getNextVisibleSibling(TreeNodeDataModel child, boolean expandSearch) {
		if (child.getParent() == null) {
			// the child node has no parent, so it is impossible
			// to locate any sibling nodes
			return null;
		}

		TreeGroupDataModel parent = child.getParent();

		// Retrieve the index of the child node
		int i = getChildIndex(child);

		if (i != -1) {
			// Search the next visible sibling
			for (int j = i + 1; j < parent.size(); j++) {
				TreeNodeDataModel nextChild = parent.getChild(j);

				if (isVisible(nextChild)) {
					return nextChild;
				}
			}

			// There is no visible sibling on this tree level
			// Expand the search on the parent node
			if (expandSearch) {
				return getNextVisibleSibling(parent, expandSearch);
			}
		}

		// No visible sibling found
		return null;
	}

	/**
	 * @see com.cc.framework.ui.control.LineIterator#isMarked()
	 */
	public boolean isMarked() {
		return state.isMarked(current.getUniqueKey());
	}

	/**
	 * @see com.cc.framework.ui.control.LineIterator#reset()
	 */
	public void reset() {
		if (current != null) {
			// go to the root node
			while (current.getParent() != null) {
				current = current.getParent();
			}
		}
	}

	/**
	 * Returns the index of a child node in its parent node
	 *
	 * @param		child the node whos index is to search
	 * @return		index of the child node or <code>-1</code>
	 * 				if the position could not be calcualted
	 */
	protected int getChildIndex(TreeNodeDataModel child) {
		if (child.getParent() == null) {
			return -1;
		}

		TreeGroupDataModel parent = child.getParent();

		for (int i = 0; i < parent.size(); i++) {
			if (parent.getChild(i) == child) {
				return i;
			}
		}

		// child not found
		return -1;
	}

	/**
	 * @return Returns the principal.
	 */
	public Principal getPrincipal() {
		return principal;
	}
}