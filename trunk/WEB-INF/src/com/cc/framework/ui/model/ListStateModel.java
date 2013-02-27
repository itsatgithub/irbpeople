/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/ListStateModel.java,v 1.11 2005/07/06 15:56:17 P001002 Exp $
 * $Revision: 1.11 $
 * $Date: 2005/07/06 15:56:17 $
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

package com.cc.framework.ui.model;

import com.cc.framework.common.SortOrder;

/**
 * Statemodel for the ListControl
 *
 * @author    <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version   $Revision: 1.11 $
 * @since     1.0
 */
public interface ListStateModel extends StateModel {

	/**
	 * State property for the current page
	 */
	public String PROP_PAGE			= "page";

	/**
	 * Returns the zero based index of the page which is currently display.
	 *
	 * @return Zero based index of the page which is currently displayed
	 */
	public int getCurrentPage();

	/**
	 * Sets the index for the page which is currently display
	 * to another value.
	 *
	 * @param	page	The new page index
	 */
	public void setCurrentPage(int page);

	/**
	 * Returns the name of the column which is currently used for
	 * sorting.
	 * 
	 * @return	The name of the column which is currently used for sorting
	 */
	public String getSortColumn();
	
	/**
	 * Returns the sort order
	 * 
	 * @return	 The sort order
	 */
	public SortOrder getSortOrder();
	
	
	/**
 	 * Selects a specified row.
 	 * 
 	 * @param	uniqueKey	The unique key of the row which should be selected
 	 */
	public void mark(String uniqueKey);
	
	/**
	 * Checks if the specified row is selected.
	 * 
	 * @param	uniqueKey	The unique key
	 * @return	boolean		Returns true if the row is selected; false otherwise
	 */
	public boolean isMarked(String uniqueKey);

	/**
	 * Retrieves all marked items
	 * 
	 * @return	Array with the keys of the marked items
	 */
	public String[] getMarked();

	/**
	 * Unselects a specified row.
	 * 
	 * @param uniqueKey The unique key of the row which should be unselected
	 */
	public void unmark(String uniqueKey);

	/**
	 * Unselects all selected rows  
	 */
	public void unmarkAll();
	
	/**
	 * Sets the sort order for the specified column
	 * 
	 * @param	column		Name of the column
	 * @param	direction	The sort order
	 */
	public void setSortInfo(String column, SortOrder direction);

	/**
	 * Resets any sorting Information
	 *
	 */
	public void resetSortInfo();
}

