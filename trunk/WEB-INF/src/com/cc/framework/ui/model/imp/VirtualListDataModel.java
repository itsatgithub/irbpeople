/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/imp/VirtualListDataModel.java,v 1.11 2005/10/16 08:40:00 P001002 Exp $
 * $Revision: 1.11 $
 * $Date: 2005/10/16 08:40:00 $
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
import java.util.Collection;
import java.util.Vector;

import com.cc.framework.ui.model.ListDataModel;
import com.cc.framework.ui.model.VirtualDataModel;

/**
 * List Data Model for Displaylists with a very large
 * or unknown number of rows.
 *
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.11 $
 */
public abstract class VirtualListDataModel implements VirtualDataModel, ListDataModel, Serializable {

	/**
	 * This flag directs the list not to flush the
	 * rowset cache when a new rowset is loaded.
	 */
	private boolean keepRows = false;

	/**
	 * This member holds the size of the rowset.
	 * It should be larger than the number of displayed
	 * rows (but that is not necessary)
	 */
	private int rowsetSize = 10;

	/**
	 * Number of overlapping rows between rowsets
	 * to reduce page flittering
	 */
	private int threshold = 0;

	/**
	 * The total number of elements.
	 * <code>-1</code> when the correct number is unknown
	 */
	private int size = -1;

	/**
	 * This is the index of the first row in the
	 * rowset.
	 */
	private int rowsetIndex = 0;

	/**
	 * The elements of the currently loaded rowset
	 */
	private Vector rowset = new Vector();

	/**
	 * Constructor
	 */
	public VirtualListDataModel() {
		super();
	}

	/**
	 * Constructor
	 *
	 * @param		rowsetSize The number of records that should
	 * 				be fetched at a time
	 * @param		keepRows Set to <code>true</code> when all
	 * 				rows should be kept in memory. Set to <code>false</code>
	 * 				when only a number of <code>rowsetSize</code> rows
	 * 				should be kept in memory (default)
	 */
	public VirtualListDataModel(int rowsetSize, boolean keepRows) {
		super();

		this.rowsetSize	= rowsetSize;
		this.keepRows	= keepRows;

		clear();
	}

	/**
	 * Clears the current list
	 */
	public void clear() {
		clearRowset();

		rowsetIndex	= 0;
		size		= -1;
	}

	/**
	 * Removes all elements from the rowset. The current rowset
	 * index is not affected.
	 */
	protected void clearRowset() {
		rowset.clear();
	}

	/**
	 * This method is called when the next rowset
	 * is to be loaded into memory
	 *
	 * @param		index Start Index
	 * @throws		Exception Will be thrown in case of
	 * 				an error in the integration layer
	 */
	private void fetchRowset(int index) throws Exception {

		boolean setup = (size == -1) && rowset.isEmpty();

		if (setup) {
			// Give a derived class a chance to do some
			// initial processing
			doSetup();
		}

		int startIndex	= 0;
		int fetchSize	= 0;

		if (keepRows) {
			// Calculate the index where to start reading
			// and keep the rowset cache
			startIndex	= rowset.size();
			fetchSize	= Math.max(rowsetSize, index - startIndex);
		} else {
			// set the new starting index and flush the
			// rowset cache
			rowsetIndex = Math.max(0, index - threshold);

			// remove all elements from the row set
			clearRowset();

			startIndex	= rowsetIndex;
			fetchSize	= rowsetSize;
		}

		// load the rowset
		Collection collection = doFetchRowset(startIndex, fetchSize);

		rowset.addAll(collection);

		// Adjust the list size
		if (collection.size() < fetchSize) {
			// The size of the list is now known
			setSize(rowsetIndex + rowset.size());
		} else if (setup) {
			setSize(doFetchSize());
		}
	}

	/**
	 * This method is called first to do any initialization
	 * processing
	 *
	 * @throws		Exception Will be thrown in case of
	 * 				an error in the integration layer
	 */
	protected void doSetup() throws Exception {
		// No Action in the base class
	}

	/**
	 * This method is called to fetch the actual rowset size.
	 * It's up to the derived class to provide an implementation
	 * or not
	 *
	 * @return		Returns the size of the list or
	 * 				-1
	 * @throws		Exception Will be thrown in case of
	 * 				an error in the integration layer
	 */
	protected int doFetchSize() throws Exception {
		// No Action in the base class
		return -1;
	}

	/**
	 * This method reads a rowset into memory
	 *
	 * @param		startIndex Index of the first row
	 * @param		rowCount Number of rows to read
	 * @return		Returns a collection of rowbeans
	 * @throws		Exception Will be thrown in case of
	 * 				an error in the integration layer
	 */
	protected abstract Collection doFetchRowset(int startIndex, int rowCount) throws Exception;

	/**
	 * This method maps an absolute index to a relative index
	 * in the rowset. When the relative index is outside the
	 * loaded rowset the missing data will be loaded dynamically
	 * to match the requirde index.
	 *
	 * @param		index the absolute index
	 * @return		returns the relative index or
	 * 				<code>-1</code> when the index could not
	 * 				be matched
	 * @throws		Exception Will be thrown in case of
	 * 				an error in the integration layer
	 */
	private int mapIndexToRowSet(int index) throws Exception {
		if ((index >= rowsetIndex) && (index < rowsetIndex + rowset.size())) {
			// -> cache hit - The row is a member of the cached rowset
			return index - rowsetIndex;
		} else {
			// -> cache miss - The row is not loaded
			fetchRowset(index);

			// try again
			if ((index >= rowsetIndex) && (index < rowsetIndex + rowset.size())) {
				// -> cache hit - The row is loaded in the rowset
				return index - rowsetIndex;
			} else {
				// row not found
				return -1;
			}
		}
	}

	/**
	 * @see com.cc.framework.ui.model.ListDataModel#getElementAt(int)
	 */
	public Object getElementAt(int index) {

		int mappedIndex;

		try {
			// Map the absolute row index to a index
			// relative to the row set. The row set
			// will be loaded on demand
			mappedIndex = mapIndexToRowSet(index);

		} catch (Exception e) {
			throw new IllegalStateException(e.getMessage());
		}

		if (mappedIndex == -1) {
			// The element with the specified index
			// is not in the rowset!
			return null;
		} else {
			// Get the rowbean from the rowset
			return rowset.get(mappedIndex);
		}
	}

	/**
	 * Rturns a Element of the rowset
	 *
	 * @param		index Element index in the rowset
	 * @return		Row bean
	 */
	public Object getElementFromRowset(int index) {
		return rowset.get(index);
	}

	/**
	 * @see com.cc.framework.ui.model.ListDataModel#size()
	 */
	public int size() {
		return size;
	}

	/**
	 * Returns the current capacity of this VirtualListDataModel.
	 *
	 * @return		the current capacity (the length of its internal
	 * 				data array, kept in the field <tt>rowset</tt>
	 * 				of this VirtualListDataModel).
	 */
	public int capacity() {
		return rowset.size();
	}

	/**
	 * Thets the number of total available elements in this
	 * list.
	 *
	 * @param		i Number of elements or <code>-1</code> when
	 * 				the number is unknown
	 */
	public void setSize(int i) {
		this.size = i;
	}

	/**
	 * Returns the Index of the first element in the rowset
	 *
	 * @return		Index of the first element in the rowset
	 */
	public int getRowsetIndex() {
		return rowsetIndex;
	}

	/**
	 * Sets the rowset size to the specified value. It should
	 * match the size of a database rowset for optimized
	 * data throughput.
	 *
	 * @param		i new rowset size
	 */
	public void setRowsetSize(int i) {
		this.rowsetSize = i;
	}

	/**
	 * Sets the threshold value. That is the number of overlapping
	 * rows between two adjacent rowsets. The threshold reduces page
	 * flittering when the user scrolls through the list.
	 *
	 * @param		i new threshold value
	 */
	public void setThreshold(int i) {
		this.threshold = i;
	}
}