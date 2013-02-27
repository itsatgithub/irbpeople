/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/imp/TreelistDesignModelImp.java,v 1.16 2005/09/27 14:06:22 P001002 Exp $
 * $Revision: 1.16 $
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

package com.cc.framework.ui.model.imp;

import com.cc.framework.ui.ExpansionMode;
import com.cc.framework.ui.SelectMode;
import com.cc.framework.ui.model.ColumnDesignModel;
import com.cc.framework.ui.model.ColumnTreeDesignModel;
import com.cc.framework.ui.model.TreelistDesignModel;

/**
 * Designmodel for the TreeListControl
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version	   $Revision: 1.16 $
 * @since      1.0
 */
public class TreelistDesignModelImp extends ListDesignModelImp implements TreelistDesignModel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 211527862941418394L;

	/**
	 * Field expansion
	 */
	private ExpansionMode expansion			= ExpansionMode.FULL;

	/**
	 * Das Flag gibt an ob das Kontrollelement mit Knöpfen zum
	 * auf- und zuklappen der Zweige erstellt werden soll
	 */
	private boolean showButtons				= true;

	/**
	 * Field showLines
	 */
	private boolean showLines				= true;

	/**
	 * Field showLinesAtRoot
	 */
	private boolean showLinesAtRoot			= true;

	/**
	 * Field showCheckBoxes
	 */
	private boolean showCheckBoxes			= false;

	/**
	 * Field showRoot
	 */
	private boolean showRoot				= false;

	/**
	 * Field groupSelect
	 */
	private boolean groupSelect				= true;

	/**
	 * Field enableProperty
	 */
	private String enableProperty			= null;

	// -------------------------------
	//        methods
	// -------------------------------

	/**
	 * Constructor for TreelistDesignModelImp
	 */
	public TreelistDesignModelImp() {
		super();

		// set the selection mode to multiple
		setSelectMode(SelectMode.MULTIPLE);
	}

	/**
	 * Returns the ColumnTreeDesignModel
	 *
	 * @return	ColumnTreeDesignModel
	 */
	private ColumnTreeDesignModel getTreeColumn() {
		ColumnDesignModel[] columns = getColumns();

		for (int i = 0; i < columns.length; i++) {
			if (columns[i] instanceof ColumnTreeDesignModel) {
				return (ColumnTreeDesignModel) columns[i];
			}
		}

		// no tree column found!
		return null;
	}

	/**
	 * @see com.cc.framework.ui.model.TreeStyle#getExpansionMode()
	 */
	public ExpansionMode getExpansionMode() {
		return expansion;
	}

	/**
	 * @see com.cc.framework.ui.model.TreeStyle#setExpansionMode(ExpansionMode)
	 */
	public void setExpansionMode(ExpansionMode mode) {
		this.expansion	= mode;
	}

	/**
	 * @see com.cc.framework.ui.model.TreeStyle#showButtons()
	 */
	public boolean showButtons() {
		return showButtons;
	}

	/**
	 * @see com.cc.framework.ui.model.TreeStyle#showButtons(boolean)
	 */
	public void showButtons(boolean show) {
		this.showButtons	= show;
	}

	/**
	 * @see com.cc.framework.ui.model.TreeStyle#showLines()
	 */
	public boolean showLines() {
		return showLines;
	}

	/**
	 * @see com.cc.framework.ui.model.TreeStyle#showLines(boolean)
	 */
	public void showLines(boolean show) {
		this.showLines	= show;
	}

	/**
	 * @see com.cc.framework.ui.model.TreeStyle#showLinesAtRoot()
	 */
	public boolean showLinesAtRoot() {
		return showLinesAtRoot;
	}

	/**
	 * @see com.cc.framework.ui.model.TreeStyle#showLinesAtRoot(boolean)
	 */
	public void showLinesAtRoot(boolean show) {
		this.showLinesAtRoot	= show;
	}

	/**
	 * @see com.cc.framework.ui.model.TreeStyle#showRoot()
	 */
	public boolean showRoot() {
		return showRoot;
	}

	/**
	 * @see com.cc.framework.ui.model.TreeStyle#showRoot(boolean)
	 */
	public void showRoot(boolean show) {
		this.showRoot	= show;
	}

	/**
	 * @see com.cc.framework.ui.model.TreeStyle#showCheckBoxes()
	 */
	public boolean showCheckBoxes() {
		return showCheckBoxes;
	}

	/**
	 * @see com.cc.framework.ui.model.TreeStyle#showCheckBoxes(boolean show)
	 */
	public void showCheckBoxes(boolean show) {
		this.showCheckBoxes	= show;
	}

	/**
	 * @see com.cc.framework.ui.model.TreeStyle#setGroupSelect(boolean)
	 */
	public void setGroupSelect(boolean select) {
		this.groupSelect	= select;
	}

	/**
	 * @see com.cc.framework.ui.model.TreeStyle#getGroupSelect()
	 */
	public boolean getGroupSelect() {
		return groupSelect;
	}

	/**
	 * @see com.cc.framework.ui.model.TreeStyle#setTarget(java.lang.String)
	 */
	public void setTarget(String target) {
		// this method can not be called, because the treelist-tag
		// did not support this property
	}

	/**
	 * @see com.cc.framework.ui.model.TreeStyle#getTarget()
	 */
	public String getTarget() {
		ColumnTreeDesignModel treeColumn = getTreeColumn();

		return (treeColumn == null) ? null : treeColumn.getTarget();
	}

	/**
	 * @see com.cc.framework.ui.model.TreeStyle#setTargetProperty(java.lang.String)
	 */
	public void setTargetProperty(String targetProperty) {
		// this method can not be called, because the treelist-tag
		// did not support this property
	}

	/**
	 * @see com.cc.framework.ui.model.TreeStyle#getTargetProperty()
	 */
	public String getTargetProperty() {
		ColumnTreeDesignModel treeColumn = getTreeColumn();

		return (treeColumn == null) ? null : treeColumn.getTargetProperty();
	}

	/**
	 * @see com.cc.framework.ui.model.TreeStyle#getTooltipProperty()
	 */
	public String getTooltipProperty() {
		ColumnTreeDesignModel treeColumn = getTreeColumn();

		return (treeColumn == null) ? null : treeColumn.getTooltipProperty();
	}

	/**
	 * @see com.cc.framework.ui.model.TreeStyle#setTooltipProperty(java.lang.String)
	 */
	public void setTooltipProperty(String tooltipProperty) {
		// this method can not be called, because the treelist-tag
		// did not support this property
	}

	/**
	 * @see com.cc.framework.ui.model.TreeStyle#getEnableProperty()
	 */
	public String getEnableProperty() {
		return enableProperty;
	}

	/**
	 * @see com.cc.framework.ui.model.TreeStyle#setEnableProperty(java.lang.String)
	 */
	public void setEnableProperty(String property) {
		this.enableProperty	= property;
	}
}