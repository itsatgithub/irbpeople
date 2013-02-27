/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/util/ListHelp.java,v 1.23 2005/10/17 17:09:21 P001002 Exp $
 * $Revision: 1.23 $
 * $Date: 2005/10/17 17:09:21 $
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

import java.util.Map;

import com.cc.framework.common.Algorithm;
import com.cc.framework.common.AlgorithmFilter;
import com.cc.framework.common.CheckState;
import com.cc.framework.ui.control.LineIterator;
import com.cc.framework.ui.control.ListLineIterator;
import com.cc.framework.ui.control.VirtualKeyListLineIterator;
import com.cc.framework.ui.control.VirtualListLineIterator;
import com.cc.framework.ui.model.ListDataModel;
import com.cc.framework.ui.model.imp.VirtualKeyListDataModel;
import com.cc.framework.ui.model.imp.VirtualListDataModel;

/**
 * Helper for processing ListDataModels
 *
 * @author	   <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version	   $Revision: 1.23 $
 * @since      1.0
 */
public abstract class ListHelp {

	/**
	 * Constructor
	 */
	private ListHelp() {
		super();
	}
	
	/**
	 * Returns the Row (Object) with the specified key from the list.
	 *
	 * @param	list	ListDataModel
	 * @param	key		Key
	 * @return	Object
	 * @throws	Exception Indicates an error while iterating and
	 * 			executing the algorithm
	 */
	public static Object getLineFromKey(ListDataModel list, String key)
		throws Exception {

		if ((list == null) || (key == null)) {
			return null;
		}

		return iterateNodes(list, new Algorithms.KeySearchAlg(key));
	}

	/**
	 * This method processes an algorithm for all elements (nodes) in the list.
	 *
	 * @param	list	ListDataModel
	 * @param	alg 	Algorithm to execute
	 * @return	The object where the iteration terminated or
	 * 			<code>null</code>
	 * @throws	Exception Indicates an error while iterating and
	 * 			executing the algorithm
	 * @see		com.cc.framework.common.Algorithm#execute(String, Object)
	 */
	public static Object iterateNodes(ListDataModel list, Algorithm alg)
		throws Exception {
		return iterateNodes(list, alg, Algorithms.nullFilter);
	}

	/**
	 * This method processes an algorithm for all elements (nodes) in the list.
	 *
	 * @param	list	ListDataModel
	 * @param	alg 	Algorithm to execute
	 * @param	filter	The row filter
	 * @return	The object where the iteration terminated or
	 * 			<code>null</code>
	 * @throws	Exception Indicates an error while iterating and
	 * 			executing the algorithm
	 * @see		com.cc.framework.common.Algorithm#execute(String, Object)
	 */
	public static Object iterateNodes(
		ListDataModel list,
		Algorithm alg,
		AlgorithmFilter filter)
		throws Exception {

		if (list == null) {
			return null;
		}

		LineIterator iterator = null;

		if (list instanceof VirtualListDataModel) {
			// Iterate only over the rows in memory!
			iterator = new VirtualListLineIterator(null, (VirtualListDataModel) list, null);
		} else if (list instanceof VirtualKeyListDataModel) {
			// Iterate only over the rows in memory!
			iterator = new VirtualKeyListLineIterator(null, (VirtualKeyListDataModel) list, null);
		} else {
			iterator = new ListLineIterator(null, list, 0, null);
		}

		return iterateNodes(iterator, alg, filter);
	}

	/**
	 * This method processes an algorithm for all elements (nodes) in the list.
	 *
	 * @param	iter	List Iterator
	 * @param	alg 	Algorithm to execute
	 * @return	The object where the iteration terminated or
	 * 			<code>null</code>
	 * @throws	Exception Indicates an error while iterating and
	 * 			executing the algorithm
	 * @see		com.cc.framework.common.Algorithm#execute(String, Object)
	 */
	public static Object iterateNodes(LineIterator iter, Algorithm alg)
		throws Exception {

		return iterateNodes(iter, alg, null);
	}

	/**
	 * This method processes an algorithm for all elements (nodes) in the list.
	 *
	 * @param	iter	List Iterator
	 * @param	alg 	Algorithm to execute
	 * @param	filter	The row filter
	 * @return	The object where the iteration terminated or
	 * 			<code>null</code>
	 * @throws	Exception Indicates an error while iterating and
	 * 			executing the algorithm
	 * @see		com.cc.framework.common.Algorithm#execute(String, Object)
	 */
	public static Object iterateNodes(
		LineIterator iter,
		Algorithm alg,
		AlgorithmFilter filter)
		throws Exception {

		if (iter == null) {
			return null;
		}

		boolean go = true;

		while (!iter.done()) {
			String key = iter.currentKey();
			Object obj = iter.current();

			if (!filter.exclude(key, obj)) {

				// execute algorithm
				go = alg.execute(key, obj);

				// If the algorithm wants to terminate the
				// processing of the elements quit.
				if (!go) {
					return obj;
				}
			}

			// go to the next row
			iter.next();
		}

		// All elements processed
		return null;
	}

	/**
	 * Returns the index of the row with the specified key from the list.
	 *
	 * @param list	ListDataModel
	 * @param key	Key
	 * @return	int
	 */
	public static int indexOf(ListDataModel list, String key) {

		if ((list == null) || (key == null)) {
			return -1;
		}

		ListLineIterator iter = new ListLineIterator(null, list, 0, null);

		while (!iter.done()) {
			if (key.equals(iter.currentKey())) {
				// row found
				return iter.currentIndex();
			}

			// go to the next row
			iter.next();
		}

		// not found
		return -1;
	}

	/**
	 * Clears all selected elements in a list.
	 * Therefore the elements in the list must implement the
	 * <code>checkabel</code> interface.
	 * Only the checked itmes (1) will be reseted!
	 * Items with an undefined state will (-1) not be affected.
	 *
	 * @param		list ListDataModel
	 * @throws		Exception Indicates an error while iterating and
	 * 				executing the algorithm
	 * @see			com.cc.framework.ui.model.Checkable
	 */
	public static void uncheckAll(ListDataModel list) throws Exception {
		uncheck(list, Algorithms.nullFilter);
	}

	/**
	 * Clears all selected elements in a list.
	 * Therefore the elements in the list must implement the
	 * <code>checkabel</code> interface.
	 * Only the checked itmes (1) will be reseted!
	 * Items with an undefined state will (-1) not be affected.
	 *
	 * @param		list ListDataModel
	 * @param		filter Row Filter
	 * @throws		Exception Indicates an error while iterating and
	 * 				executing the algorithm
	 * @see			com.cc.framework.ui.model.Checkable
	 */
	public static void uncheck(ListDataModel list, AlgorithmFilter filter)
		throws Exception {

		iterateNodes(list, new Algorithms.CheckCheckableAlg(false), filter);
	}

	/**
	 * Unchecks only the checked Items in a List
	 *
	 * @param		list List Datamodel
	 * @param		property The check property
	 * @throws		Exception Indicates an error while iterating and
	 * 				executing the algorithm
	 */
	public static void uncheckAll(ListDataModel list, String property)
		throws Exception {

		uncheck(list, property, Algorithms.nullFilter);
	}

	/**
	 * Unchecks only the checked Items in a List
	 *
	 * @param		list List Datamodel
	 * @param		property The check property
	 * @param		filter the row filter
	 * @throws		Exception Indicates an error while iterating and
	 * 				executing the algorithm
	 */
	public static void uncheck(
		ListDataModel list,
		String property,
		AlgorithmFilter filter)
		throws Exception {

		iterateNodes(list, new Algorithms.CheckPropertyAlg(property, false), filter);
	}

	/**
	 * Checks all elements in a list.
	 * Therefore the elements in the list must implement the
	 * <code>checkabel</code> interface.
	 * Only the unchecked itmes (0) will be checked!
	 * Items with an undefined state will (-1) not be affected.
	 *
	 * @param		list ListDataModel
	 * @throws		Exception Indicates an error while iterating and
	 * 				executing the algorithm
	 * @see			com.cc.framework.ui.model.Checkable
	 */
	public static void checkAll(ListDataModel list) throws Exception {
		check(list, Algorithms.nullFilter);
	}

	/**
	 * Checks all elements in a list.
	 *
	 * @param		list List Datamodel
	 * @param		property The check property
	 * @throws		Exception Indicates an error while iterating and
	 * 				executing the algorithm
	 */
	public static void checkAll(ListDataModel list, String property)
		throws Exception {

		check(list, property, Algorithms.nullFilter);
	}

	/**
	 * Checks all elements in a list.
	 * Therefore the elements in the list must implement the
	 * <code>checkabel</code> interface.
	 * Only the unchecked itmes (0) will be checked!
	 * Items with an undefined state will (-1) not be affected.
	 *
	 * @param		list ListDataModel
	 * @param		filter The row filter
	 * @throws		Exception Indicates an error while iterating and
	 * 				executing the algorithm
	 * @see			com.cc.framework.ui.model.Checkable
	 */
	public static void check(ListDataModel list, AlgorithmFilter filter)
		throws Exception {

		iterateNodes(list, new Algorithms.CheckCheckableAlg(true), filter);
	}

	/**
	 * Checks a property for all elements in a list.
	 *
	 * @param		list List Datamodel
	 * @param		property The check property
	 * @param		filter the row filter
	 * @throws		Exception Indicates an error while iterating and
	 * 				executing the algorithm
	 */
	public static void check(
		ListDataModel list,
		String property,
		AlgorithmFilter filter)
		throws Exception {

		iterateNodes(list, new Algorithms.CheckPropertyAlg(property, true), filter);
	}

	/**
	 * Returns an Array including all the matching row beans
	 * of the list.
	 * Therefore the elements in the list must implement the
	 * <code>checkabel</code> interface.
	 * Only the checked itmes (1) will be returned!
	 *
	 * @param		list The list
	 * @throws		Exception Indicates an error while iterating and
	 * 				executing the algorithm
	 * @return		An Hashtable including the checked elements
	 */
	public static Map getCheckedItems(ListDataModel list)
		throws Exception {

		return getItemsByState(list, CheckState.CHECKED);
	}

	/**
	 * Returns an Array including all the matching row beans in a list.
	 * Only the checked itmes (1) will be returned!
	 *
	 * @param		list The list
	 * @param		property The check property
	 * @throws		Exception Indicates an error while iterating and
	 * 				executing the algorithm
	 * @return		An Hashtable including the checked elements
	 */
	public static Map getCheckedItems(ListDataModel list, String property)
		throws Exception {

		return getItemsByState(list, property, CheckState.CHECKED);
	}

	/**
	 * Returns an Array including all the matching row beans
	 * of the list.
	 * Therefore the elements in the list must implement the
	 * <code>checkabel</code> interface.
	 * Only the unchecked itmes (0) will be returned!
	 *
	 * @param		list The list
	 * @return		An Hashtable including the unchecked elements
	 * @throws		Exception Indicates an error while iterating and
	 * 				executing the algorithm
	 */
	public static Map getUncheckedItems(ListDataModel list)
		throws Exception {

		return getItemsByState(list, CheckState.UNCHECKED);
	}

	/**
	 * Returns an Array including all the matching row beans
	 * of the list.
	 * Only the unchecked itmes (0) will be returned!
	 *
	 * @param		list The list
	 * @param		property The check property
	 * @return		An Hashtable including the unchecked elements
	 * @throws		Exception Indicates an error while iterating and
	 * 				executing the algorithm
	 */
	public static Map getUncheckedItems(ListDataModel list, String property)
		throws Exception {

		return getItemsByState(list, property, CheckState.UNCHECKED);
	}

	/**
	 * Returns a collection for all element in the list
	 * which fits the specified check state.
	 * Therefore the elements in the list must implement the
	 * <code>checkabel</code> interface.
	 *
	 * @param		list	The list which includes the checked elements
	 * @param		state	The checkstate
	 * @return		An Hashtable including the row beans
	 * @throws		Exception Indicates an error while iterating and
	 * 				executing the algorithm
	 */
	public static Map getItemsByState(
		ListDataModel list,
		CheckState state)
		throws Exception {

		Algorithms.CollectCheckableAlg collector = new Algorithms.CollectCheckableAlg(state);

		iterateNodes(list, collector);

		return collector.getMatches();
	}

	/**
	 * Returns a collection for all element in the list
	 * which fits the specified check state.
	 *
	 * @param		list The list which includes the checked elements
	 * @param		property The check property
	 * @param		state The checkstate
	 * @return		An Hashtable including the row beans
	 * @throws		Exception Indicates an error while iterating and
	 * 				executing the algorithm
	 */
	public static Map getItemsByState(
		ListDataModel list,
		String property,
		CheckState state)
		throws Exception {

		Algorithms.CollectPropertyAlg collector = new Algorithms.CollectPropertyAlg(property, state);

		iterateNodes(list, collector);

		return collector.getMatches();
	}
}