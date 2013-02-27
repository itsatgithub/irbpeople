/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/imp/VirtualKeyListDataModel.java,v 1.4 2005/10/16 08:40:00 P001002 Exp $
 * $Revision: 1.4 $
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
 * List Data Model for Key lists with a very large
 * or unknown number of keys.
 *
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.4 $
 */
public abstract class VirtualKeyListDataModel implements VirtualDataModel, ListDataModel, Serializable {

	/**
	 * This flag directs the list not to flush the
	 * keyset cache when a new keyset is loaded.
	 */
	private boolean keepKeys = false;

	/**
	 * This member holds the size of the keyset.
	 * It should be larger than the number of displayed
	 * rows (but that is not necessary)
	 */
	private int keysetSize = 10;

	/**
	 * Number of overlapping keys between keysets
	 * to reduce page flittering
	 */
	private int threshold = 0;

	/**
	 * The total number of elements.
	 * <code>-1</code> when the correct number is unknown
	 */
	private int size = -1;

	/**
	 * This is the index of the first key in the
	 * keyset.
	 */
	private int keysetIndex = 0;

	/**
	 * The elements of the currently loaded keyset
	 */
	private Vector keyset = new Vector();

	/**
	 * The List implements a very simple cache mechanism to
	 * prevent unnecessary loading
	 */
	private int cache = -1;

	/**
	 * Cached row bean
	 */
	private Object cacheBean = null;

	/**
	 * Constructor
	 */
	public VirtualKeyListDataModel() {
		super();
	}

	/**
	 * Constructor
	 *
	 * @param		keysetSize The number of keys that should
	 * 				be fetched at a time
	 * @param		keepKeys Set to <code>true</code> when all
	 * 				keys should be kept in memory. Set to <code>false</code>
	 * 				when only a number of <code>keysetSize</code> keys
	 * 				should be kept in memory (default)
	 */
	public VirtualKeyListDataModel(int keysetSize, boolean keepKeys) {
		super();

		this.keysetSize	= keysetSize;
		this.keepKeys	= keepKeys;

		clear();
	}

	/**
	 * Clears the current list
	 */
	public void clear() {
		clearKeyset();

		keysetIndex	= 0;
		size		= -1;
	}

	/**
	 * Removes all elements from the keyset. The current keyset
	 * index is not affected.
	 */
	protected void clearKeyset() {
		keyset.clear();
	}

	/**
	 * This method is called when the next keyset
	 * is to be loaded into memory
	 *
	 * @param		index Start Index
	 * @throws		Exception Will be thrown in case of
	 * 				an error in the integration layer
	 */
	private void fetchKeyset(int index) throws Exception {

		boolean setup = (size == -1) && keyset.isEmpty();

		if (setup) {
			// Give a derived class a chance to do some
			// initial processing
			doSetup();
		}

		int startIndex	= 0;
		int fetchSize	= 0;

		if (keepKeys) {
			// Calculate the index where to start reading
			// and keep the keyset cache
			startIndex	= keyset.size();
			fetchSize	= Math.max(keysetSize, index - startIndex);
		} else {
			// set the new starting index and flush the
			// keyset cache
			keysetIndex = Math.max(0, index - threshold);

			// remove all elements from the keyset
			clearKeyset();

			startIndex	= keysetIndex;
			fetchSize	= keysetSize;
		}

		// load the keyset
		Collection collection = doFetchKeyset(startIndex, fetchSize);

		keyset.addAll(collection);

		// Adjust the list size
		if (collection.size() < fetchSize) {
			// The size of the list is now known
			setSize(keysetIndex + keyset.size());
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
	 * This method is called to fetch the actual keyset size.
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
	 * This method reads a keyset into memory
	 *
	 * @param		startIndex Index of the first key
	 * @param		keyCount Number of keys to read
	 * @return		Returns a collection of keys
	 * @throws		Exception Will be thrown in case of
	 * 				an error in the integration layer
	 */
	protected abstract Collection doFetchKeyset(int startIndex, int keyCount) throws Exception;

	/**
	 * Overwrite this method to load one single row bean
	 *
	 * @param		key The key
	 * @return		row bean
	 * @throws		Exception An error ocurred while loading the
	 * 				row bean
	 */
	protected abstract Object doFetchRowBean(Object key) throws Exception;

	/**
	 * This method maps an absolute index to a relative index
	 * in the keyset. When the relative index is outside the
	 * loaded keyset the missing data will be loaded dynamically
	 * to match the requirde index.
	 *
	 * @param		index the absolute index
	 * @return		returns the relative index or
	 * 				<code>-1</code> when the index could not
	 * 				be matched
	 * @throws		Exception Will be thrown in case of
	 * 				an error in the integration layer
	 */
	private int mapIndexToKeyset(int index) throws Exception {
		if ((index >= keysetIndex) && (index < keysetIndex + keyset.size())) {
			// -> cache hit - The row is a member of the cached rowset
			return index - keysetIndex;
		} else {
			// -> cache miss - The row is not loaded
			fetchKeyset(index);

			// try again
			if ((index >= keysetIndex) && (index < keysetIndex + keyset.size())) {
				// -> cache hit - The row is loaded in the rowset
				return index - keysetIndex;
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
			mappedIndex = mapIndexToKeyset(index);

		} catch (Exception e) {
			throw new IllegalStateException(e.getMessage());
		}

		if (mappedIndex == -1) {
			// The element with the specified index
			// is not in the rowset!
			return null;
		} else {
			// Get the rowbean from the rowset
			return getRowBean(mappedIndex);
		}
	}

	/**
	 * @see com.cc.framework.ui.model.ListDataModel#getUniqueKey(int)
	 */
	public String getUniqueKey(int index) {
		int mappedIndex;

		try {
			// Map the absolute row index to a index
			// relative to the row set. The row set
			// will be loaded on demand
			mappedIndex = mapIndexToKeyset(index);

		} catch (Exception e) {
			throw new IllegalStateException(e.getMessage());
		}

		if (mappedIndex == -1) {
			// The element with the specified index
			// is not in the rowset!
			return null;
		} else {
			// Get the rowbean from the rowset
			return String.valueOf(keyset.get(mappedIndex));
		}
	}

	/**
	 * Returns the row bean for the given index
	 *
	 * @param		index Element index
	 * @return		row bean
	 */
	private Object getRowBean(int index) {

		if (cache != index) {
			try {
				// Clear the cache
				cache		= -1;
				cacheBean	= null;

				// load the bean into the cache
				cacheBean	= doFetchRowBean(keyset.get(index));
				cache		= index;
			} catch (Exception e) {
				throw new IllegalStateException(e.getMessage());
			}
		}

		// Return the bean from cache
		return cacheBean;
	}

	/**
	 * Returns a Element of the keyset
	 *
	 * @param		index Element index in the keyset
	 * @return		Key
	 */
	public Object getElementFromKeyset(int index) {
		return keyset.get(index);
	}

	/**
	 * @see com.cc.framework.ui.model.ListDataModel#size()
	 */
	public int size() {
		return size;
	}

	/**
	 * Returns the current capacity of this VirtualKeyListDataModel.
	 *
	 * @return		the current capacity (the length of its internal
	 * 				data array, kept in the field <tt>keyset</tt>
	 * 				of this VirtualKeyListDataModel).
	 */
	public int capacity() {
		return keyset.size();
	}

	/**
	 * Thets the number of total available keys in this
	 * list.
	 *
	 * @param		i Number of keys or <code>-1</code> when
	 * 				the number is unknown
	 */
	public void setSize(int i) {
		this.size = i;
	}

	/**
	 * Returns the Index of the first element in the keyset
	 *
	 * @return		Index of the first element in the keyset
	 */
	public int getKeysetIndex() {
		return keysetIndex;
	}

	/**
	 * Sets the keyset size to the specified value. It should
	 * match the size of a database keyset for optimized
	 * data throughput.
	 *
	 * @param		i new keyset size
	 */
	public void setKeysetSize(int i) {
		this.keysetSize = i;
	}

	/**
	 * Sets the threshold value. That is the number of overlapping
	 * rows between two adjacent keysets. The threshold reduces page
	 * flittering when the user scrolls through the list.
	 *
	 * @param		i new threshold value
	 */
	public void setThreshold(int i) {
		this.threshold = i;
	}
}