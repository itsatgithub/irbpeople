/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/util/TreeHelp.java,v 1.23 2005/10/31 18:50:49 P001002 Exp $
 * $Revision: 1.23 $
 * $Date: 2005/10/31 18:50:49 $
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

package com.cc.framework.util;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cc.framework.common.Algorithm;
import com.cc.framework.common.AlgorithmFilter;
import com.cc.framework.common.CheckState;
import com.cc.framework.security.Principal;
import com.cc.framework.ui.model.Checkable;
import com.cc.framework.ui.model.TreeGroupDataModel;
import com.cc.framework.ui.model.TreeNodeDataModel;
import com.cc.framework.ui.model.TreeStateModel;

/**
 * Helper class for managing tree structures.
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.23 $
 * @since      1.0
 */
public abstract class TreeHelp {
	
	/** Logging instance */
	private static Log log = LogFactory.getLog(TreeHelp.class);
	
	/**
	 * Returns the check state of a given tree node. The method can handle
	 * Checkable and Non Checkable Nodes
	 * 
	 * <ul>
	 * <li>-2 = Don't show any checkboxes</li>
	 * <li>-1 = The object/node is not selectable</li>
	 * <li> 0 = The object/node is not checked</li>
	 * <li> 1 = The object/node is checked</li>
	 * <li> 2 = The check sate is undefined</li>
	 * </ul>
	 * 
	 * @param root
	 *            TreeNodeDataModel
	 * @return int
	 */
	public static int getCheckState(TreeNodeDataModel root) {
		return calcCheckState(TreeIterator.createLevelIterator(root));
	}
		
	/**
	 * Returns the check state of a given tree node. The method can handle
	 * Checkable and Non Checkable Nodes
	 * 
	 * <ul>
	 * <li>-2 = Don't show any checkboxes</li>
	 * <li>-1 = The object/node is not selectable</li>
	 * <li> 0 = The object/node is not checked</li>
	 * <li> 1 = The object/node is checked</li>
	 * <li> 2 = The check sate is undefined</li>
	 * </ul>
	 * 
	 * @param iter
	 *            Iterator
	 * @return int
	 */
	private static int calcCheckState(TreeIterator.LevelIterator iter) {
		int checkState = -1;

		// Wenn der Knoten die Markierungseigenschaft besitz, dann
		// kann der Markierungszustand sofort bestimmt werden
		if (iter.current() instanceof Checkable) {
			Checkable cnode = (Checkable) iter.current();

			checkState = cnode.getCheckState();
		} else if (iter.hasChildren()) {
			// Create a iterator for the children of the
			// current node
			TreeIterator.LevelIterator childIter = iter.currentIterator();

			int children = childIter.size();

			// Wenn ein Gruppenknoten selbst über keine
			// Markierungseigenschaft verfügt, dann wird
			// der Markierungszustand für die gesamte Gruppe
			// berechnet.
			int checked			= 0;
			int intermediate	= 0;
			int invalid			= 0;

			while (!childIter.done()) {
				switch (calcCheckState(childIter)) {

					case -1 :
						++invalid;
						break;

					case 1 :
						++checked;
						break;

					case 2 :
						++intermediate;
						break;

					default :
						;
				}
				
				childIter.next();
			}

			if (children == -1) {
				// Die Gruppe ist noch nicht in den Speicher geladen.
				// Der Selektionszustand ist daher unbekannt
				checkState = CheckState.UNSELECTABLE.toInt();
			} else if (invalid == children) {
				// Die Gruppe verfügt über keine selektierbaren Kinder.
				// Sie ist damit selbst nicht selektierbar.
				checkState = CheckState.UNSELECTABLE.toInt();
			} else if ((checked == 0) && (intermediate == 0)) {
				// Keines der Kinder ist markiert
				checkState = CheckState.UNCHECKED.toInt();
			} else {
				// Einige Kinder sind markiert, aber nicht alle
				checkState = (checked == children)
					? CheckState.CHECKED.toInt()
					: CheckState.UNDEFINED.toInt();
			}
		}

		return checkState;
	}

	/**
	 * Constructor
	 */
	private TreeHelp() {
		super();
	}

	/**
	 * Expand nodes top down
	 * 
	 * @param root
	 *            Root of the current sub tree
	 * @param level
	 *            a value greater or equal to zero indicates that this node
	 *            should be expanded
	 * @param state
	 *            The TreeStateModel that holds the expansion information
	 */
	public static void expandToLevel(TreeGroupDataModel root, int level, TreeStateModel state) {

		TreeIterator iter = new TreeIterator(root, level);
		
		while (!iter.done()) {
			state.expand(iter.currentKey());

			// go to the next row
			iter.next();
		}
	}

	/**
	 * Expand nodes top down
	 * 
	 * @param node
	 *            Root of the current sub tree
	 * @param state
	 *            The TreeStateModel that holds the expansion information
	 * @param principal
	 *            The principal Object that has the information which node is
	 *            visible for the current user
	 * @return returns the number of visible nodes or -1 if the number is
	 *         unknown
	 */
	public static int calcVisibleNodes(TreeNodeDataModel node, TreeStateModel state, Principal principal) {

		Algorithms.VisibleNodeCounterAlg alg = new Algorithms.VisibleNodeCounterAlg();

		try {
			iterateNodes(node, state, alg, new Algorithms.AccessFilter(principal));
		} catch (RuntimeException re) {
			throw re;
		} catch (Exception e) {
			log.error("Exception while iterating tree nodes", e);
		}

		return alg.getCount();
	}

	/**
	 * Sets the checked state for the specified Node
	 * 
	 * @param node
	 *            TreeNode
	 * @param check
	 *            true or false
	 */
	public static void checkNode(TreeNodeDataModel node, boolean check) {

		if ((node != null) && (node instanceof Checkable)) {
			Checkable checkable = (Checkable) node;

			int newCheckState =
				check
					? CheckState.CHECKED.toInt()
					: CheckState.UNCHECKED.toInt();

			if (checkable.getCheckState() != newCheckState) {
				checkable.setCheckState(newCheckState);
			}
		}
	}

	/**
	 * Connects all nodes with a parent child relationship a collection. The
	 * elements of the collection must implements the
	 * {@link com.cc.framework.ui.model.TreeNodeDataModel TreeNodeDataModel}
	 * interface.
	 * 
	 * @param nodes
	 *            Collection of TreeNodeDataModel's
	 * @throws TreeStructureException
	 *             if an error in the structur of the tree is detected
	 */
	public static void connectOutline(Collection nodes) throws TreeStructureException {

		// Hastable für die schnelle Suche nach Items aufbauen.
		// Der Baum wird jedoch in der Reihenfolge der Collection
		// aufgebaut. Damit bleibt eine vorgegebene Sortierreihenfolge
		// erhalten!
		Map map = new Hashtable();

		Iterator iter = nodes.iterator();
		while (iter.hasNext()) {
			TreeNodeDataModel item = (TreeNodeDataModel) iter.next();

			map.put(item.getUniqueKey(), item);
		}

		// Die gelesenen Baumeinträge werden nun der Reihe nach
		// abgewandert und in eine Baumstruktur eingehängt.
		iter = nodes.iterator();
		while (iter.hasNext()) {
			TreeNodeDataModel item = (TreeNodeDataModel) iter.next();

			String parentKey = item.getParentKey();

			// Nun wird das neue Item an der richtigen Stelle in
			// die Baumstruktur eingehängt
			if (parentKey != null) {
				// Parent Item suchen und das Element als Kind
				// einfügen.
				// Definition: Der Parent muss ein Gruppenknoten sein
				TreeNodeDataModel parent = (TreeNodeDataModel) map.get(parentKey);

				if (parent == null) {
					throw new TreeStructureException("Parent not found: " + parentKey);
				} else if (!(parent instanceof TreeGroupDataModel)) {
					throw new TreeStructureException("Parent must implement TreeGroupDataModel: " + parentKey);
				}

				((TreeGroupDataModel) parent).addChild(item);
				item.setParent((TreeGroupDataModel) parent);
			}
		}
	}

	/**
	 * Creates a Tree from a Collection. The elements in the Collection must
	 * implement the
	 * {@link com.cc.framework.ui.model.TreeNodeDataModel TreeNodeDataModel}
	 * interface. The List must contain one root element
	 * 
	 * @param nodes
	 *            Collection of TreeNodeDataModel's
	 * @return TreeGroupDataModel
	 * @throws TreeStructureException
	 *             if an error in the structur of the tree is detected
	 */
	public static TreeGroupDataModel createOutline(Collection nodes) throws TreeStructureException {
		// The root element of the tree
		TreeGroupDataModel root = null;

		// Hastable für die schnelle Suche nach Items aufbauen.
		// Der Baum wird jedoch in der Reihenfolge der Collection
		// aufgebaut. Damit bleibt eine vorgegebene Sortierreihenfolge
		// erhalten!
		Map map = new Hashtable();

		Iterator iter = nodes.iterator();
		while (iter.hasNext()) {
			TreeNodeDataModel item = (TreeNodeDataModel) iter.next();

			map.put(item.getUniqueKey(), item);
		}

		// Die gelesenen Baumeinträge werden nun der Reihe nach
		// abgewandert und in eine Baumstruktur eingehängt.
		iter = nodes.iterator();
		while (iter.hasNext()) {
			TreeNodeDataModel item = (TreeNodeDataModel) iter.next();

			String parentKey = item.getParentKey();

			// Nun wird das neue Item an der richtigen Stelle in
			// die Baumstruktur eingehängt
			if (parentKey == null) {
				// Es handelt sich um ein Item der obersten Baumebene
				// Definition: Das Item muss ein Gruppenknoten sein!

				if (root != null) {
					throw new TreeStructureException("Duplicate Root Element: " + item.getUniqueKey());
				}

				root = (TreeGroupDataModel) item;

			} else {
				// Parent Item suchen und das Element als Kind
				// einfügen.
				// Definition: Der Parent muss ein Gruppenknoten sein
				TreeNodeDataModel parent = (TreeNodeDataModel) map.get(parentKey);

				if (parent == null) {
					throw new TreeStructureException("Parent not found: " + parentKey);
				} else if (!(parent instanceof TreeGroupDataModel)) {
					throw new TreeStructureException("Parent must implement TreeGroupDataModel: " + parentKey);
				}

				((TreeGroupDataModel) parent).addChild(item);
				item.setParent((TreeGroupDataModel) parent);
			}
		}

		return root;
	}

	/**
	 * Creates a Tree from an ArrayList. The elements in the ArrayList must
	 * implement the
	 * {@link com.cc.framework.ui.model.TreeNodeDataModel TreeNodeDataModel}
	 * interface. The Nodes in the ArrayList will be appended to the specified
	 * Node (root Argument).
	 * 
	 * @param root
	 *            TreeGroupDataModel
	 * @param nodes
	 *            Collection of TreeNodeDataModel's
	 * @return TreeGroupDataModel
	 * @throws TreeStructureException
	 *             if an error in the structur of the tree is detected
	 */
	public static TreeGroupDataModel createOutline(TreeGroupDataModel root, Collection nodes) throws TreeStructureException {
		// Hastable für die schnelle Suche nach Items aufbauen.
		// Der Baum wird jedoch in der Reihenfolge der ArrayList
		// aufgebaut. Damit bleibt eine vorgegebene Sortierreihenfolge
		// erhalten!
		Map map = new Hashtable();

		Iterator iter = nodes.iterator();
		while (iter.hasNext()) {
			TreeNodeDataModel item = (TreeNodeDataModel) iter.next();

			map.put(item.getUniqueKey(), item);
		}

		// Die gelesenen Baumeinträge werden nun der Reihe nach
		// abgewandert und in eine Baumstruktur eingehängt.
		iter = nodes.iterator();
		while (iter.hasNext()) {
			TreeNodeDataModel item = (TreeNodeDataModel) iter.next();

			String parentKey = item.getParentKey();

			// Nun wird das neue Item an der richtigen Stelle in
			// die Baumstruktur eingehängt
			if (parentKey == null) {
				// Es handelt sich um ein Item der obersten Baumebene
				// Definition: Das Item muss ein Gruppenknoten sein!
				root.addChild(item);
				item.setParent(root);

			} else {
				// Parent Item suchen und das Element als Kind
				// einfügen.
				// Definition: Der Parent muss ein Gruppenknoten sein
				TreeNodeDataModel parent = (TreeNodeDataModel) map.get(parentKey);

				if (parent == null) {
					throw new TreeStructureException("Parent not found: " + parentKey);
				} else if (!(parent instanceof TreeGroupDataModel)) {
					throw new TreeStructureException("Parent must implement TreeGroupDataModel: " + parentKey);
				}

				((TreeGroupDataModel) parent).addChild(item);
				item.setParent((TreeGroupDataModel) parent);
			}
		}

		return root;
	}

	/**
	 * Creates a Tree from an Hashtable. The elements in the ArrayList must
	 * implement the
	 * {@link com.cc.framework.ui.model.TreeNodeDataModel TreeNodeDataModel}
	 * interface. The Nodes in the ArrayList will be appended to the specified
	 * Node (root Argument).
	 * 
	 * @param root
	 *            the root element
	 * @param elements
	 *            Hashtable
	 * @return TreeGroupDataModel
	 * @throws TreeStructureException
	 *             if an error in the structur of the tree is detected
	 */
	public static TreeGroupDataModel createOutline(TreeGroupDataModel root, Map elements) throws TreeStructureException {

		// Die gelesenen Baumeinträge werden nun der Reihe nach
		// abgewandert und in eine Baumstruktur eingehängt.
		Iterator i = elements.values().iterator();
		while (i.hasNext()) {
			TreeNodeDataModel item = (TreeNodeDataModel) i.next();

			String parentKey = item.getParentKey();

			// Nun wird das neue Item an der richtigen Stelle in
			// die Baumstruktur eingehängt
			if (parentKey == null) {
				// Es handelt sich um ein Item der obersten Baumebene
				// Definition: Das Item muss ein Gruppenknoten sein!
				root.addChild(item);
				item.setParent(root);

			} else {
				// Parent Item suchen und das Element als Kind
				// einfügen.
				// Definition: Der Parent muss ein Gruppenknoten sein
				TreeNodeDataModel parent = (TreeNodeDataModel) elements.get(parentKey);

				if (parent == null) {
					throw new TreeStructureException("Parent not found: " + parentKey);
				} else if (!(parent instanceof TreeGroupDataModel)) {
					throw new TreeStructureException("Parent must implement TreeGroupDataModel: " + parentKey);
				}

				((TreeGroupDataModel) parent).addChild(item);
				item.setParent((TreeGroupDataModel) parent);
			}
		}

		return root;
	}

	/**
	 * Inserts all nodes of the specified tree in a Hashtable
	 * 
	 * @param root
	 *            TreeNodeDataModel
	 * @return Hashtable
	 */
	public static Map createHashtable(TreeNodeDataModel root) {

		Map map = new Hashtable();

		Algorithms.CollectItemsAlg collector = new Algorithms.CollectItemsAlg(map);

		try {
			iterateNodes(root, collector);
		} catch (RuntimeException re) {
			throw re;
		} catch (Exception e) {
			log.error("Exception while iterating tree nodes", e);
		}

		return collector.getMap();
	}

	/**
	 * Returns a node for a given key
	 * 
	 * @param root
	 *            TreeNodeDataModel
	 * @param key
	 *            The key
	 * @return TreeNodeDataModel
	 */
	public static TreeNodeDataModel getNodeByKey(TreeNodeDataModel root, String key) {

		if ((root == null) || (key == null)) {
			return null;
		}

		try {
			return iterateNodes(root, new Algorithms.KeySearchAlg(key));
		} catch (RuntimeException re) {
			throw re;
		} catch (Exception e) {
			log.error("Exception while iterating tree nodes", e);

			return null;
		}
	}

	/**
	 * Sets the checked state for all items on the specified (Sub-) Tree.
	 * 
	 * @param root
	 *            TreeNode
	 * @param check
	 *            true or false
	 */
	public static void checkNodes(TreeNodeDataModel root, boolean check) {
		try {
			iterateNodes(root, new Algorithms.CheckCheckableAlg(check));
		} catch (RuntimeException re) {
			throw re;
		} catch (Exception e) {
			log.error("Exception while iterating tree nodes", e);
		}
	}

	/**
	 * Applies the checked nodes from the key set
	 * 
	 * @param root
	 *            The root node
	 * @param checked
	 *            Set with all the checked keys
	 * @throws Exception
	 *             Indicates an error while iterating and executing the
	 *             algorithm
	 */
	public static void checkNodes(TreeNodeDataModel root, Set checked)
		throws Exception {

		iterateNodes(root, new Algorithms.CheckCheckableAlg(checked));
	}

	/**
	 * Unchecks all tree nodes
	 * 
	 * @param root
	 *            TreeNode
	 * @param property
	 *            The check property
	 * @param filter
	 *            Node filter
	 * @throws Exception
	 *             Indicates an error while iterating and executing the
	 *             algorithm
	 */
	public static void uncheck(
		TreeNodeDataModel root,
		String property,
		AlgorithmFilter filter)
		throws Exception {

		iterateNodes(root, new Algorithms.CheckPropertyAlg(property, false), filter);
	}

	/**
	 * This method processes an algorithm for all elements (nodes) in the tree.
	 * 
	 * @param root
	 *            TreeNodeDataModel
	 * @param alg
	 *            Algorithm to execute
	 * @return The object where the iteration terminated or <code>null</code>
	 * @throws Exception
	 *             Indicates an error while iterating and executing the
	 *             algorithm
	 * @see com.cc.framework.common.Algorithm#execute(String, Object)
	 */
	public static TreeNodeDataModel iterateNodes(TreeNodeDataModel root, Algorithm alg)
		throws Exception {
		return iterateNodes(root, alg, Algorithms.nullFilter);
	}

	/**
	 * This method processes an algorithm for all elements (nodes) in the tree.
	 * 
	 * @param root
	 *            TreeNodeDataModel
	 * @param alg
	 *            Algorithm to execute
	 * @param filter
	 *            The row filter
	 * @return The object where the iteration terminated or <code>null</code>
	 * @throws Exception
	 *             Indicates an error while iterating and executing the
	 *             algorithm
	 * @see com.cc.framework.common.Algorithm#execute(String, Object)
	 */
	public static TreeNodeDataModel iterateNodes(
		TreeNodeDataModel root,
		Algorithm alg,
		AlgorithmFilter filter)
		throws Exception {

		if (root == null) {
			return null;
		}

		TreeIterator iterator = new TreeIterator(root);

		return iterateNodes(iterator, alg, filter);
	}

	/**
	 * This method processes an algorithm for all elements (nodes) in the tree.
	 * 
	 * @param root
	 *            TreeNodeDataModel
	 * @param state
	 *            TreeStateModel
	 * @param alg
	 *            Algorithm to execute
	 * @param filter
	 *            The row filter
	 * @return The object where the iteration terminated or <code>null</code>
	 * @throws Exception
	 *             Indicates an error while iterating and executing the
	 *             algorithm
	 * @see com.cc.framework.common.Algorithm#execute(String, Object)
	 */
	public static TreeNodeDataModel iterateNodes(
		TreeNodeDataModel root,
		TreeStateModel state,
		Algorithm alg,
		AlgorithmFilter filter)
		throws Exception {

		if (root == null) {
			return null;
		}

		TreeIterator iterator = new TreeIterator(root, state);

		return iterateNodes(iterator, alg, filter);
	}

	/**
	 * This method processes an algorithm for all elements (nodes) in the tree.
	 * 
	 * @param iter
	 *            TreeIterator
	 * @param alg
	 *            Algorithm to execute
	 * @return The object where the iteration terminated or <code>null</code>
	 * @throws Exception
	 *             Indicates an error while iterating and executing the
	 *             algorithm
	 * @see com.cc.framework.common.Algorithm#execute(String, Object)
	 */
	public static TreeNodeDataModel iterateNodes(TreeIterator iter, Algorithm alg)
		throws Exception {

		return iterateNodes(iter, alg, Algorithms.nullFilter);
	}

	/**
	 * This method processes an algorithm for all elements (nodes) in the tree.
	 * 
	 * @param iter
	 *            TreeIterator
	 * @param alg
	 *            Algorithm to execute
	 * @param filter
	 *            The row filter
	 * @return The object where the iteration terminated or <code>null</code>
	 * @throws Exception
	 *             Indicates an error while iterating and executing the
	 *             algorithm
	 * @see com.cc.framework.common.Algorithm#execute(String, Object)
	 */
	public static TreeNodeDataModel iterateNodes(
		TreeIterator iter,
		Algorithm alg,
		AlgorithmFilter filter)
		throws Exception {

		if ((iter == null) || (alg == null)) {
			return null;
		}
		
		boolean go = true;

		while (!iter.done()) {
			String uniqueId = iter.currentKey();

			if (uniqueId == null) {
				throw new IllegalArgumentException("Tree node with invalid unique Id: " + iter.current());
			}
			
			TreeNodeDataModel node = iter.current();

			if (!filter.exclude(uniqueId, node)) {

				// execute algorithm
				go = alg.execute(uniqueId, node);

				// If the algorithm wants to terminate the
				// processing of the elements quit.
				if (!go) {
					return node;
				}
			}

			// go to the next row
			iter.next();
		}

		// All elements processed
		return null;
	}
}