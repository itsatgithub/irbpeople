/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/imp/TreeStateModelImp.java,v 1.9 2005/07/08 15:15:32 P001002 Exp $
 * $Revision: 1.9 $
 * $Date: 2005/07/08 15:15:32 $
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
import java.util.HashSet;

import com.cc.framework.ui.model.TreeStateModel;
import com.cc.framework.util.PropertyMap;

/**
 * Imploementation of the TreeStateModel interface
 *
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.9 $
 */
public class TreeStateModelImp implements TreeStateModel, Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 2436935632256909211L;

	/**
	 * List of Keys for the expanded Nodes
	 */
	private HashSet expanded	= new HashSet();

	/**
	 * The selected Node
	 */
	private String selected		= null;

	/**
	 * Collection with the highlight rows
	 */
	private HashSet highlight	= new HashSet();

	/**
	 * @see com.cc.framework.ui.model.TreeStateModel#collapse(java.lang.String)
	 */
	public void collapse(String nodeKey) {
		if (nodeKey != null) {
			expanded.remove(nodeKey);
		}
	}

	/**
	 * @see com.cc.framework.ui.model.TreeStateModel#collapseAll()
	 */
	public void collapseAll() {
		expanded.clear();
	}

	/**
	 * @see com.cc.framework.ui.model.TreeStateModel#expand(java.lang.String)
	 */
	public void expand(String nodeKey) {
		expanded.add(nodeKey);
	}

	/**
	 * @see com.cc.framework.ui.model.TreeStateModel#isExpanded(java.lang.String)
	 */
	public boolean isExpanded(String nodeKey) {
		return expanded.contains(nodeKey);
	}

	/**
	 * @see com.cc.framework.ui.model.TreeStateModel#getSelected()
	 */
	public String getSelected() {
		return selected;
	}

	/**
	 * @see com.cc.framework.ui.model.TreeStateModel#isSelected(java.lang.String)
	 */
	public boolean isSelected(String nodeKey) {
		if (selected == null) {
			return false;
		} else {
			return selected.equals(nodeKey);
		}
	}

	/**
	 * @see com.cc.framework.ui.model.TreeStateModel#select(java.lang.String)
	 */
	public void select(String nodeKey) {
		this.selected = nodeKey;
	}

	/**
	 * @see com.cc.framework.ui.model.TreeStateModel#unselectAll()
	 */
	public void unselectAll() {
		this.selected = null;
	}

	/**
	 * @see com.cc.framework.ui.model.TreeStateModel#isMarked(java.lang.String)
	 */
	public boolean isMarked(String uniqueKey) {
		return highlight.contains(uniqueKey);
	}

	/**
	 * @see com.cc.framework.ui.model.TreeStateModel#getMarked()
	 */
	public String[] getMarked() {
		return (String[]) highlight.toArray(new String[highlight.size()]);
	}

	/**
	 * @see com.cc.framework.ui.model.TreeStateModel#mark(java.lang.String)
	 */
	public void mark(String uniqueKey) {
		if ((uniqueKey != null) && !highlight.contains(uniqueKey)) {
			highlight.add(uniqueKey);
		}
	}

	/**
	 * @see com.cc.framework.ui.model.TreeStateModel#unmark(java.lang.String)
	 */
	public void unmark(String uniqueKey) {
		if ((uniqueKey != null) && highlight.contains(uniqueKey)) {
			highlight.remove(uniqueKey);
		}
	}

	/**
	 * @see com.cc.framework.ui.model.TreeStateModel#unmarkAll()
	 */
	public void unmarkAll() {
		highlight.clear();
	}

	/**
	 * @see com.cc.framework.ui.model.StateModel#reset()
	 */
	public void reset() {
		expanded.clear();
		highlight.clear();
		selected = null;
	}

	/**
	 * @see com.cc.framework.ui.model.StateModel#synchronizeState(com.cc.framework.util.PropertyMap)
	 */
	public void synchronizeState(PropertyMap properties) throws Exception {
		if (properties.hasProperty(PROP_EXPANDED)) {
			// Reset the current expansion state
			collapseAll();

			// retrieve the expanded nodes from the property map
			String[] nodes = properties.getProperties(PROP_EXPANDED);
			for (int i = 0; i < nodes.length; i++) {
				expand(nodes[i]);
			}
		}

		if (properties.hasProperty(PROP_SELECTED)) {
			selected = properties.getProperty(PROP_SELECTED);
		}
	}
}
