/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/TreeStateModel.java,v 1.14 2005/09/27 14:06:22 P001002 Exp $
 * $Revision: 1.14 $
 * $Date: 2005/09/27 14:06:22 $
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

/**
 * Interface TreeStateModel
 * 
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version $Revision: 1.14 $
 * @since 1.0
 */
public interface TreeStateModel extends StateModel {

	/**
	 * State property for the expanded nodes
	 */
	public String PROP_EXPANDED = "expanded";

	/**
	 * State property for the selected node
	 */
	public String PROP_SELECTED = "selected";

	/**
	 * Checks if the specified group node is expanded
	 * 
	 * @param nodeKey
	 *            the nodes unique key
	 * @return boolean
	 */
	public boolean isExpanded(String nodeKey);

	/**
	 * The specified group node will be expanded
	 * 
	 * @param nodeKey
	 *            the nodes unique key
	 */
	public void expand(String nodeKey);

	/**
	 * Collapses all tree nodes
	 */
	public void collapseAll();

	/**
	 * The specified group node will be collapsed
	 * 
	 * @param nodeKey
	 *            the nodes unique key
	 */
	public void collapse(String nodeKey);

	/**
	 * Selects the specified node. Within a tree there can only exists one
	 * selected node
	 * 
	 * @param nodeKey
	 *            the nodes unique key
	 */
	public void select(String nodeKey);

	/**
	 * Unselects the current selection of the tree
	 */
	public void unselectAll();

	/**
	 * Checks if the specified node is selected.
	 * 
	 * @param nodeKey
	 *            the nodes unique key
	 * @return boolean
	 */
	public boolean isSelected(String nodeKey);

	/**
	 * Returns the key of the selected node
	 * 
	 * @return Key or <code>null</code>
	 */
	public String getSelected();

	/**
	 * Selects a specified row.
	 * 
	 * @param uniqueKey
	 *            The unique key of the row which should be selected
	 */
	public void mark(String uniqueKey);

	/**
	 * Checks if the specified row is highlighted.
	 * 
	 * @param nodeKey
	 *            the nodes unique key
	 * @return boolean Returns true if the row is selected; false otherwise
	 */
	public boolean isMarked(String nodeKey);

	/**
	 * Retrieves all marked items
	 * 
	 * @return Array with the keys of the marked items
	 */
	public String[] getMarked();

	/**
	 * Highlights a specified row.
	 * 
	 * @param nodeKey
	 *            the nodes unique key
	 */
	public void unmark(String nodeKey);

	/**
	 * Restes all highlighted rows
	 */
	public void unmarkAll();
}