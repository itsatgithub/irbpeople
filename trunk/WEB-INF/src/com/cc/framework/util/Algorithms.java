package com.cc.framework.util;

import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import com.cc.framework.common.Algorithm;
import com.cc.framework.common.AlgorithmFilter;
import com.cc.framework.common.CheckState;
import com.cc.framework.security.Principal;
import com.cc.framework.ui.model.AccessControlled;
import com.cc.framework.ui.model.Checkable;
import com.cc.framework.ui.model.imp.VirtualTreeGroupDataModel;

/**
 * Collection of predefined Algorithms for List processing
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision$
 * @since      1.6
 */
public abstract class Algorithms {

	// =============================
	// Filters
	// =============================
	
	/**
	 * Filter that excludes nothing
	 */
	public static class NullFilter implements AlgorithmFilter {

		/**
		 * @see com.cc.framework.common.AlgorithmFilter#exclude(java.lang.String, java.lang.Object)
		 */
		public boolean exclude(String uniqueId, Object obj) {
			// Do not exclude iteration element
			return false;
		}
	}

	/**
	 * Filter that excludes nodes which the user is not allowed to see
	 */
	public static class AccessFilter implements AlgorithmFilter {

		/**
		 * The Principal Object that performs the access validation
		 */
		private Principal principal;

		/**
		 * Constructor
		 * 
		 * @param principal
		 *            The Principal Object that performs the access validation
		 */
		public AccessFilter(Principal principal) {
			super();

			this.principal = principal;
		}
		
		/**
		 * @see com.cc.framework.common.AlgorithmFilter#exclude(java.lang.String,
		 *      java.lang.Object)
		 */
		public boolean exclude(String uniqueId, Object obj) {
			if ((principal != null) && (obj instanceof AccessControlled)) {
				// The object is access controlled. So let the principal
				// check if the node is visible
				AccessControlled ac = (AccessControlled) obj;

				return !ac.show(principal);
			} else {
				// Do not exclude the object from iteration
				return false;
			}
		}
	}
	
	/**
	 * Null Filter Instance
	 */
	public static NullFilter nullFilter = new NullFilter(); 

	// =============================
	// Algorithms
	// =============================
	
	/**
	 * Algorithm to check row beans that implement the
	 * Checkable interface
	 */
	public static class CheckCheckableAlg implements Algorithm {

		/** Check or uncheck flag */
		private boolean check = true;

		/** A set with keys that should be checked */
		private Set keys = null;
		
		/**
		 * Constructor
		 *
		 * @param		check Check or uncheck flag
		 */
		public CheckCheckableAlg(boolean check) {
			super();

			this.check = check;
		}

		/**
		 * Constructor
		 *
		 * @param		keys A the keys that should be checked
		 */
		public CheckCheckableAlg(Set keys) {
			super();

			this.keys = keys;
		}

		/**
		 * @see com.cc.framework.common.Algorithm#execute(java.lang.String, java.lang.Object)
		 */
		public boolean execute(String uniqueId, Object obj) throws Exception {

			if (obj instanceof Checkable) {
				Checkable checkable = (Checkable) obj;

				int newCheckState;
				
				if (keys != null) {
					newCheckState = keys.contains(uniqueId) ? CheckState.CHECKED.toInt() : CheckState.UNCHECKED.toInt();
				} else {
					newCheckState = check ? CheckState.CHECKED.toInt() : CheckState.UNCHECKED.toInt();
				}
				
				if (checkable.getCheckState() != newCheckState) {
					// Call setCheckState only when there is a realy
					// state change
					checkable.setCheckState(newCheckState);
				}
			}

			// Continue iteration
			return true;
		}
	}

	/**
	 * Algorithm to collect rows of a given check state
	 */
	public static class CollectCheckableAlg implements Algorithm {

		/** State to query for */
		private CheckState query;

		/**Matching items */
		private Map matches = new Hashtable();

		/**
		 * Constructor
		 *
		 * @param		query State to query for
		 */
		public CollectCheckableAlg(CheckState query) {
			super();

			this.query = query;
		}

		/**
		 * Returns the matching items
		 *
		 * @return		Hashtable with matching items
		 */
		public Map getMatches() {
			return matches;
		}

		/**
		 * @see com.cc.framework.common.Algorithm#execute(java.lang.String, java.lang.Object)
		 */
		public boolean execute(String uniqueId, Object obj) throws Exception {

			if (obj instanceof Checkable) {
				Checkable checkable = (Checkable) obj;

				// only get the checked items!
				if (query.equals(checkable.getCheckState())) {
					matches.put(uniqueId, obj);
				}
			}

			// Continue iteration
			return true;
		}
	}

	/**
	 * Algorithm to collect rows of a given check state
	 */
	public static class CollectPropertyAlg implements Algorithm {

		/** State to query for */
		private CheckState query;

		/** The Property to check */
		private String property;

		/**Matching items */
		private Map matches = new Hashtable();

		/**
		 * Constructor
		 *
		 * @param		property Property to check
		 * @param		query State to query for
		 */
		public CollectPropertyAlg(String property, CheckState query) {
			super();

			this.property = property;
			this.query = query;
		}

		/**
		 * Returns the matching items
		 *
		 * @return		Hashtable with matching items
		 */
		public Map getMatches() {
			return matches;
		}

		/**
		 * @see com.cc.framework.common.Algorithm#execute(java.lang.String, java.lang.Object)
		 */
		public boolean execute(String uniqueId, Object obj) throws Exception {

			CheckState checkstate = Util.getCheckState(obj, property);

			// only get the checked items!
			if (checkstate.equals(query)) {
				matches.put(uniqueId, obj);
			}

			// Continue iteration
			return true;
		}
	}

	/**
	 * Algorithm to check a property on each row of a
	 * ListDataModel
	 */
	public static class CheckPropertyAlg implements Algorithm {

		/** Check or uncheck flag */
		private boolean check = true;

		/** The Property to check */
		private String property;

		/**
		 * Constructor
		 *
		 * @param		property Property to check
		 * @param		check Check or uncheck flag
		 */
		public CheckPropertyAlg(String property, boolean check) {
			super();

			this.property	= property;
			this.check		= check;
		}

		/**
		 * @see com.cc.framework.common.Algorithm#execute(java.lang.String, java.lang.Object)
		 */
		public boolean execute(String uniqueId, Object obj) throws Exception {

			if (check) {
				Util.check(obj, property, true);
			} else {
				Util.uncheck(obj, property);
			}

			// Continue iteration
			return true;
		}
	}

	/**
	 * Algorithm to search for a key in a ListDataModel
	 */
	public static class KeySearchAlg implements Algorithm {

		/** The key to search for */
		private String key;

		/**
		 * Constructor
		 *
		 * @param		key The search key
		 */
		public KeySearchAlg(String key) {
			super();

			this.key = key;
		}

		/**
		 * @see com.cc.framework.common.Algorithm#execute(java.lang.String, java.lang.Object)
		 */
		public boolean execute(String uniqueId, Object obj) throws Exception {
			// Continue iteration until a matching key is found
			return !uniqueId.equals(key);
		}
	}

	/**
	 * Algorithm to collect all items of a List or Tree in a Map<String,
	 * Object>
	 */
	public static class CollectItemsAlg implements Algorithm {

		/**
		 * The collection where alle Items should be stored
		 */
		private Map map;

		/**
		 * Constructor
		 * 
		 * @param map
		 *            The collection where alle Items should be stored
		 */
		public CollectItemsAlg(Map map) {
			super();

			this.map = map;
		}

		/**
		 * Returns the collection
		 * 
		 * @return Map with all collected items
		 */
		public Map getMap() {
			return map;
		}

		/**
		 * @see com.cc.framework.common.Algorithm#execute(java.lang.String, java.lang.Object)
		 */
		public boolean execute(String uniqueId, Object obj) throws Exception {
			map.put(uniqueId, obj);

			// Continue iteration
			return true;
		}
	}

	/**
	 * Algorithm to count the number of visible nodes in a
	 * Tree
	 */
	public static class VisibleNodeCounterAlg implements Algorithm {

		/** Counter */
		private int count = 0;

		/**
		 * Constructor
		 */
		public VisibleNodeCounterAlg() {
			super();
		}

		/**
		 * Returns the counter
		 *
		 * @return		Counter
		 */
		public int getCount() {
			return count;
		}

		/**
		 * @see com.cc.framework.common.Algorithm#execute(java.lang.String, java.lang.Object)
		 */
		public boolean execute(String uniqueId, Object obj) throws Exception {
			++count;
			
			if (obj instanceof VirtualTreeGroupDataModel) {
				VirtualTreeGroupDataModel vtgdm = (VirtualTreeGroupDataModel) obj;
				
				if (vtgdm.size() == -1) {
					// We can not determine the current number of nodes
					// because not all nodes are loaded into memory
					count = -1;
					
					// Terminate the iteration
					return false;
				}
			}

			// Continue iteration
			return true;
		}
	}
}