/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/imp/KeyListDataModel.java,v 1.7 2005/10/16 08:40:00 P001002 Exp $
 * $Revision: 1.7 $
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
 * ListDataModel which holds only a Key Set in Memory.
 * The Display Beans are loaded as needed
 *
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.7 $
 */
public abstract class KeyListDataModel implements VirtualDataModel, ListDataModel, Serializable {

	/**
	 * The Set of Keys
	 */
	private Vector keySet = null;

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
	 * Overwrite this method to load the keyset
	 *
	 * @return		Collection of keys
	 * @throws		Exception An error ocurred while loading the
	 * 				keyset
	 */
	protected abstract Collection doFetchKeys() throws Exception;

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
	 * Clears the current keyset
	 */
	public void clear() {
		keySet.clear();
	}

	/**
	 * Use this method to set up the keys collection explicit
	 * 
	 * @throws		Exception An error ocurred while loading the
	 * 				keyset
	 */
	public void setupKeys() throws Exception {
		doSetup();

		Collection keys = doFetchKeys();

		keySet = new Vector();
		if (keys != null) {
			keySet.addAll(keys);
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
				cacheBean	= doFetchRowBean(keySet.get(index));
				cache		= index;
			} catch (Exception e) {
				throw new IllegalStateException(e.getMessage());
			}
		}

		// Return the bean from cache
		return cacheBean;
	}

	/**
	 * @see com.cc.framework.ui.model.ListDataModel#getElementAt(int)
	 */
	public Object getElementAt(int index) {
		return getRowBean(index);
	}

	/**
	 * @see com.cc.framework.ui.model.ListDataModel#getUniqueKey(int)
	 */
	public String getUniqueKey(int index) {
		return String.valueOf(keySet.get(index));
	}

	/**
	 * @see com.cc.framework.ui.model.ListDataModel#size()
	 */
	public int size() {
		if (keySet == null) {
			try {
				setupKeys();
			} catch (Exception e) {
				throw new IllegalStateException(e.getMessage());
			}
		}

		return keySet.size();
	}
}
