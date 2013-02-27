package com.cc.framework.util;

import com.cc.framework.ui.model.TreeGroupDataModel;
import com.cc.framework.ui.model.TreeNodeDataModel;
import com.cc.framework.ui.model.TreeStateModel;
import com.cc.framework.ui.model.imp.VirtualTreeGroupDataModel;

/**
 * Iterates over the tree. Preorder traversal is used, i.e., node is processed
 * before its children. <br>
 * Depth of traversal can be limited by <code>setMaxDepth</code>. <br>
 * Branches can be traversed selectively using <code>skipChildren()</code>.
 */
public class TreeIterator {

	/**
	 * Iterator for one level in a tree structure
	 */
	public static interface LevelIterator {
		/**
		 * @return returns the iterator of the parentlevel or <code>null</code>
		 */
		public LevelIterator getParentIterator();

		/**
		 * Restarts the iterator on the first element
		 */
		public void restart();

		/**
		 * Returns the current node.
		 * 
		 * @return current node
		 */
		public TreeNodeDataModel current();

		/**
		 * Returns key of the of the current node.
		 * 
		 * @return key of the current node
		 */
		public String currentKey();

		/**
		 * Returns index of the of the current node among siblings. Index starts
		 * with 0.
		 * 
		 * @return index of the current node
		 */
		public int currentIndex();

		/**
		 * Checks if there is a parent iterator availabel
		 * 
		 * @return <code>true</code> if there is a parent iterator available
		 */
		public boolean hasParent();

		/**
		 * Returns <code>true</code> if current node has children and iterator
		 * is allowed to traverse them.
		 * 
		 * @return <code>true</code> if current node has children
		 */
		public boolean hasChildren();

		/**
		 * @return returns <code>true</code> when the iterator has reached the
		 *         last element of the iteration. after done() has returned
		 *         <code>true</code> it is not valid to call current()
		 */
		public boolean done();

		/**
		 * Every <code>next()</code> call moves current to the next node.
		 */
		public void next();

		/**
		 * Returns the LevelIterator for the current selected element. The depth
		 * of the returned iterator is getDepth() + 1.
		 * 
		 * @return LevelIterator for the current selected element or
		 *         <code>null</code> when the selected element has no children
		 */
		public LevelIterator currentIterator();

		/**
		 * @return returns the depth of this tree level (relative to the
		 *         iteration root)
		 */
		public int getDepth();

		/**
		 * @return returns the maximum depth the iterator is allowed to descent.
		 */
		public int getMaxDepth();

		/**
		 * @return returns the number of elements the iterator can iterate
		 */
		public int size();

		/**
		 * Sets the maximum depth the iterator is allowed to descent.
		 * 
		 * @param maxDepth
		 *            maximum depth
		 */
		public void setMaxDepth(int maxDepth);

		/**
		 * Checks if the iterator has reached the maximum tree level
		 * 
		 * @return retruns <code>true</code> when the maximum level is reached
		 */
		public boolean isMaxDepth();
	}

	/**
	 * Baseclass for all LevelIterator's
	 */
	private static abstract class AbstractLevelIterator implements LevelIterator {

		private LevelIterator parentIterator = null;

		private int depth = 0;

		private int maxDepth = -1;

		/**
		 * Constructor
		 * 
		 * @param parentIterator
		 *            The iterator for the parent tree level
		 */
		public AbstractLevelIterator(LevelIterator parentIterator) {
			super();

			this.parentIterator = parentIterator;
			
			if (parentIterator != null) {
				this.depth = parentIterator.getDepth() + 1;
				this.maxDepth = parentIterator.getMaxDepth();
			}
		}

		/**
		 * @see com.cc.framework.util.TreeIterator.LevelIterator#getParentIterator()
		 */
		public LevelIterator getParentIterator() {
			return parentIterator;
		}

		/**
		 * @see com.cc.framework.util.TreeIterator.LevelIterator#currentKey()
		 */
		public String currentKey() {
			return current().getUniqueKey();
		}

		/**
		 * @see com.cc.framework.util.TreeIterator.LevelIterator#currentIterator()
		 */
		public LevelIterator currentIterator() {
			TreeNodeDataModel child = current();
			
			if (child instanceof VirtualTreeGroupDataModel) {
				return new VirtualTreeLevelIterator(this, (VirtualTreeGroupDataModel) child);
			} else if (child instanceof TreeGroupDataModel) {
				return new SimpleLevelIterator(this, (TreeGroupDataModel) child);
			} else {
				return null;
			}
		}

		/**
		 * @see com.cc.framework.util.TreeIterator.LevelIterator#getDepth()
		 */
		public int getDepth() {
			return depth;
		}

		/**
		 * @see com.cc.framework.util.TreeIterator.LevelIterator#getMaxDepth()
		 */
		public int getMaxDepth() {
			return maxDepth;
		}

		/**
		 * @see com.cc.framework.util.TreeIterator.LevelIterator#hasChildren()
		 */
		public boolean hasChildren() {
			TreeNodeDataModel child = current();

			if (child instanceof VirtualTreeGroupDataModel) {
				return ((VirtualTreeGroupDataModel) child).capacity() > 0;
			} else if (child instanceof TreeGroupDataModel) {
				return ((TreeGroupDataModel) child).size() > 0;
			} else {
				return false;
			}
		}

		/**
		 * @see com.cc.framework.util.TreeIterator.LevelIterator#hasParent()
		 */
		public boolean hasParent() {
			return (parentIterator != null);
		}

		/**
		 * @see com.cc.framework.util.TreeIterator.LevelIterator#isMaxDepth()
		 */
		public boolean isMaxDepth() {
			if (maxDepth == -1) {
				// The depth is unlimited
				return false;
			} else {
				return depth >= maxDepth;
			}
		}

		/**
		 * @see com.cc.framework.util.TreeIterator.LevelIterator#setMaxDepth(int)
		 */
		public void setMaxDepth(int maxDepth) {
			this.maxDepth = maxDepth;
		}
	}

	/**
	 * Specialised iterator for the root element of the tree
	 */
	private static final class RootIterator extends AbstractLevelIterator {

		private TreeNodeDataModel root = null;
		private boolean done = false;

		/**
		 * Constructor
		 * 
		 * @param root
		 *            The root element of the tree.
		 */
		public RootIterator(TreeNodeDataModel root) {
			super(null);

			this.root = root;
		}

		/**
		 * Constructor
		 * 
		 * @param root
		 *            The root node of the tree
		 * @param maxDepth
		 *            Maximum depth to iterate
		 */
		public RootIterator(TreeNodeDataModel root, int maxDepth) {
			this(root);

			this.root = root;
			setMaxDepth(maxDepth);
		}

		/**
		 * @see com.cc.framework.util.TreeIterator.LevelIterator#current()
		 */
		public TreeNodeDataModel current() {
			return root;
		}

		/**
		 * @see com.cc.framework.util.TreeIterator.LevelIterator#currentIndex()
		 */
		public int currentIndex() {
			return 0;
		}

		/**
		 * @see com.cc.framework.util.TreeIterator.LevelIterator#restart()
		 */
		public void restart() {
			done = false;
		}

		/**
		 * @see com.cc.framework.util.TreeIterator.LevelIterator#done()
		 */
		public boolean done() {
			return done;
		}

		/**
		 * @see com.cc.framework.util.TreeIterator.LevelIterator#next()
		 */
		public void next() {
			// There is only one Node to iterate!
			done = true;
		}

		/**
		 * @see com.cc.framework.util.TreeIterator.LevelIterator#size()
		 */
		public int size() {
			// This iterator has exactly one element
			return 1;
		}
	}

	/**
	 * Iterator for simple Group Nodes
	 */
	private static final class SimpleLevelIterator extends AbstractLevelIterator {

		private TreeGroupDataModel parentNode = null;

		private TreeNodeDataModel currentChild = null;

		private int currentIndex = -1;

		/**
		 * Constructor
		 * 
		 * @param parentIterator
		 *            The iterator for the parent tree level
		 * @param parentNode
		 *            The node which schildren should be iterated
		 */
		public SimpleLevelIterator(LevelIterator parentIterator, TreeGroupDataModel parentNode) {
			super(parentIterator);

			this.parentNode = parentNode;

			restart();
		}

		/**
		 * @see com.cc.framework.util.TreeIterator.LevelIterator#current()
		 */
		public TreeNodeDataModel current() {
			return currentChild;
		}

		/**
		 * @see com.cc.framework.util.TreeIterator.LevelIterator#currentIndex()
		 */
		public int currentIndex() {
			return currentIndex;
		}

		/**
		 * @see com.cc.framework.util.TreeIterator.LevelIterator#restart()
		 */
		public void restart() {
			currentIndex = 0;

			if (parentNode.size() > 0) {
				currentChild = parentNode.getChild(0);
			} else {
				currentChild = null;
			}
		}

		/**
		 * @see com.cc.framework.util.TreeIterator.LevelIterator#done()
		 */
		public boolean done() {
			return currentIndex >= parentNode.size();
		}

		/**
		 * @see com.cc.framework.util.TreeIterator.LevelIterator#next()
		 */
		public void next() {
			currentChild = null;
			++currentIndex;

			if (parentNode.size() > currentIndex) {
				currentChild = parentNode.getChild(currentIndex);
			}
		}

		/**
		 * @see com.cc.framework.util.TreeIterator.LevelIterator#size()
		 */
		public int size() {
			return parentNode.size();
		}
	}

	/**
	 * Iterator for virtual group nodes. This iterator iterates only over the in
	 * memory child nodes(!)
	 */
	private static final class VirtualTreeLevelIterator extends AbstractLevelIterator {

		private VirtualTreeGroupDataModel parentNode = null;

		private TreeNodeDataModel rowsetChild = null;

		private int rowsetIndex = -1;
		
		/**
		 * Constructor
		 * 
		 * @param parentIterator
		 *            The iterator for the parent tree level
		 * @param parentNode
		 *            The node which schildren should be iterated
		 */
		public VirtualTreeLevelIterator(LevelIterator parentIterator, VirtualTreeGroupDataModel parentNode) {
			super(parentIterator);

			this.parentNode = parentNode;

			restart();
		}

		/**
		 * @see com.cc.framework.util.TreeIterator.LevelIterator#current()
		 */
		public TreeNodeDataModel current() {
			return rowsetChild;
		}

		/**
		 * @see com.cc.framework.util.TreeIterator.LevelIterator#currentIndex()
		 */
		public int currentIndex() {
			return parentNode.getRowsetIndex() + rowsetIndex;
		}

		/**
		 * @see com.cc.framework.util.TreeIterator.LevelIterator#restart()
		 */
		public void restart() {
			// Iterate only the currently loaded row set
			rowsetIndex = 0;
			rowsetChild = null;

			if (parentNode.capacity() > rowsetIndex) {
				rowsetChild = parentNode.getElementFromRowset(rowsetIndex);
			}
		}

		/**
		 * @see com.cc.framework.util.TreeIterator.LevelIterator#done()
		 */
		public boolean done() {
			return rowsetIndex >= parentNode.capacity();
		}

		/**
		 * @see com.cc.framework.util.TreeIterator.LevelIterator#next()
		 */
		public void next() {
			rowsetChild = null;
			++rowsetIndex;

			if (parentNode.capacity() > rowsetIndex) {
				rowsetChild = parentNode.getElementFromRowset(rowsetIndex);
			}
		}

		/**
		 * @see com.cc.framework.util.TreeIterator.LevelIterator#size()
		 */
		public int size() {
			return parentNode.capacity();
		}
	}

	private LevelIterator levelIter;

	private boolean skipChildren = false;

	/**
	 * The State Model that holds the expansion states
	 */
	private TreeStateModel state;

	/**
	 * Factory method to create a LevelIterator
	 * 
	 * @param root
	 *            the root element of the tree
	 * @return LevelIterator
	 */
	public static LevelIterator createLevelIterator(TreeNodeDataModel root) {
		return new RootIterator(root);
	}
	
	/**
	 * Creates iterator and sets the root of a tree and tree adapter.
	 * 
	 * @param root
	 *            root of the tree; note that <code>next()</code> moves to the
	 *            first child, not to the root
	 */
	public TreeIterator(TreeNodeDataModel root) {
		this.levelIter = new RootIterator(root);
	}

	/**
	 * Creates iterator and sets the root of a tree and tree adapter.
	 * 
	 * @param root
	 *            root of the tree; note that <code>next()</code> moves to the
	 *            first child, not to the root
	 * @param state
	 *            the tree state that holds the expansion information
	 */
	public TreeIterator(TreeNodeDataModel root, TreeStateModel state) {
		this.levelIter = new RootIterator(root);
		this.state = state;
	}

	/**
	 * Creates iterator and sets the root of a tree, tree adapter and depth of
	 * the traversal.
	 * 
	 * @param root
	 *            root of the tree
	 * @param maxDepth
	 *            depth of the traversal;
	 *            <ul>
	 *            <li>0 = traverse only the root</li>
	 *            <li>1 = traverse root and children</li>
	 *            <li>2 = traverse root, children and grandchildren</li>
	 *            <li>...and so forth</li>
	 *            </ul>
	 */
	public TreeIterator(TreeNodeDataModel root, int maxDepth) {
		levelIter = new RootIterator(root, maxDepth);
	}

	/**
	 * Returns the current node. After iterator is created, root becomes
	 * current. Every <code>next()</code> call moves current to the next node.
	 * 
	 * @return current node
	 */
	public TreeNodeDataModel current() {
		return levelIter.current();
	}

	/**
	 * Sets the maximum depth of the traversal.
	 * 
	 * @param maxDepth
	 *            depth of the traversal;
	 *            <ul>
	 *            <li>0 = traverse only the root;</li>
	 *            <li>1 = traverse root and childrenl;</li>
	 *            <li>2 = traverse root, children and grandchildren</li>
	 *            <li>...and so forth</li>
	 *            </ul>
	 */
	public void setMaxDepth(int maxDepth) {
		levelIter.setMaxDepth(maxDepth);
	}

	/**
	 * Returns the maximum depth of the traversal.
	 * 
	 * @return maximum allowed traversal depth set by <code>setMaxDepth()</code>.
	 */
	public int getMaxDepth() {
		return levelIter.getMaxDepth();
	}

	/**
	 * After this method is called, <code>next()</code> will skip children of
	 * the current node and move to the next sibling instead.
	 */
	public void skipChildren() {
		skipChildren = true;
	}

	/**
	 * Returns <code>true</code> if the maximum allowed level is reached.
	 * 
	 * @return true if this is the level set by <code>setMaxDepth()</code>
	 */
	public boolean isMaxDepth() {
		return levelIter.isMaxDepth();
	}

	/**
	 * Returns the depth of the level being traversed.
	 * 
	 * @return depth of the current node;
	 *         <ul>
	 *         <li>root has depth = 0</li>
	 *         <li>children = 1</li>
	 *         <li>grandchildren = 2</li>
	 *         <li>...and so on</li>
	 *         </ul>
	 */
	public int getDepth() {
		return levelIter.getDepth();
	}

	/**
	 * Returns index of the of the current node among siblings. Index starts
	 * with 0.
	 * 
	 * @return index of the current node
	 */
	public int currentIndex() {
		return levelIter.currentIndex();
	}

	/**
	 * Returns key of the of the current node.
	 * 
	 * @return key of the current node
	 */
	public String currentKey() {
		return levelIter.currentKey();
	}

	/**
	 * Returns <code>true</code> if not all elements of the tree have been
	 * traversed.
	 * 
	 * @return <code>true</code> if end of tree is not reached
	 */
	public boolean hasNext() {
		return (hasChildren() || !levelIter.done() || levelIter.hasParent());
	}

	/**
	 * Returns <code>true</code> if current node has children and iterator is
	 * allowed to traverse them.
	 * 
	 * @return <code>true</code> if current node has children
	 */
	public boolean hasChildren() {
		return (!levelIter.isMaxDepth() && !skipChildren && levelIter.hasChildren());
	}

	/**
	 * Returns <code>true</code> if current node is expanded.
	 * 
	 * @return <code>true</code> if current node is expanded
	 */
	public boolean isExpanded() {
		return (state == null) || state.isExpanded(levelIter.currentKey());
	}

	/**
	 * Returns the next element of a tree. Preorder traversal is perfromed.
	 */
	public void next() {
		// go to the next level only if maxDepth would not be exceeded
		if (hasChildren() && isExpanded()) {
			levelIter = levelIter.currentIterator();
		} else {
			levelIter.next();
			
			while (levelIter.done() && (levelIter.getParentIterator() != null)) {
				levelIter = levelIter.getParentIterator();
				levelIter.next();
			}
		}

		skipChildren = false;
	}
	
	/**
	 * @return returns <code>true</code> when the iterator has reached the
	 *         last element of the iteration. after done() has returned
	 *         <code>true</code> it is not valid to call current()
	 */
	public boolean done() {
		return levelIter.done();
	}
}
