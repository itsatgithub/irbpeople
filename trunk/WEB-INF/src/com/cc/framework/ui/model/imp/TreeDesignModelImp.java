/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/imp/TreeDesignModelImp.java,v 1.15 2005/09/27 14:06:22 P001002 Exp $
 * $Revision: 1.15 $
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
import com.cc.framework.ui.ImageMap;
import com.cc.framework.ui.SelectMode;
import com.cc.framework.ui.model.TreeDesignModel;

/**
 * Designmodel for the TreeControl
 *
 * @author	   <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version	   $Revision: 1.15 $
 * @since      1.0
 */
public class TreeDesignModelImp extends ControlDesignModelImp implements TreeDesignModel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 5371001063262910336L;

	/**
	 * Field expansion
	 */
	private ExpansionMode expansion		= ExpansionMode.FULL;

	/**
	 * Anzahl der auf dem Bildschirm dargestellten Zeilen
	 */
	private int rowCount				= -1;

	/**
	 * Das Flag gibt an ob das Kontrollelement mit Knöpfen zum
	 * auf- und zuklappen der Zweige erstellt werden soll
	 */
	private boolean showButtons			= true;

	/**
	 * Field showLines
	 */
	private boolean showLines			= true;

	/**
	 * Field showLinesAtRoot
	 */
	private boolean showLinesAtRoot		= true;

	/**
	 * Field showCheckBoxes
	 */
	private boolean showCheckBoxes		= false;

	/**
	 * Field showRoot
	 */
	private boolean showRoot			= false;

	/**
	 * Field groupSelect
	 */
	private boolean groupSelect			= true;

	/**
	 * Field imageProperty
	 */
	private String imageProperty		= null;

	/**
	 * Field labelProperty
	 */
	private String labelProperty		= null;

	/**
	 * Field tooltipProperty
	 */
	private String tooltipProperty		= null;

	/**
	 * Das Target Attribut
	 */
	private String target				= null;

	/**
	 * Das Property über welches das Target Attribut ermittelt
	 * werden kann
	 */
	private String targetProperty		= null;

	/**
	 * Field enableProperty
	 */
	private String enableProperty		= null;

	/**
	 * Field imageMap
	 */
	private ImageMap imageMap			= null;

	/**
	 * Selektionsmodus der Liste
	 */
	private SelectMode selectMode		= SelectMode.MULTIPLE;

	/**
	 * Specifies if all String should be converted
	 * into there HTML representation
	 */
	private boolean filter				= true;

	/**
	 * Maximum number of visible characters
	 */
	private int maxLength				= -1;

	// -------------------------------
	//        methods
	// -------------------------------

	/**
	 * Constructor
	 */
	public TreeDesignModelImp() {
		super();
	}

	/**
	 * @see com.cc.framework.ui.model.TreeDesignModel#getRowCount()
	 */
	public int getRowCount() {
		return rowCount;
	}

	/**
	 * @see com.cc.framework.ui.model.TreeDesignModel#setRowCount(int)
	 */
	public void setRowCount(int rows) {
		this.rowCount	= rows;
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

		// Wenn immer alle Äste voll aufgeklapt dargestellt
		// werden sollen, dann sind die Buttons unnötig
		if (ExpansionMode.FULL.equals(expansion)) {
			return false;
		}

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
	 * @see com.cc.framework.ui.model.TreeStyle#showCheckBoxes(boolean)
	 */
	public void showCheckBoxes(boolean show) {
		this.showCheckBoxes	= show;
	}

	/**
	 * @see com.cc.framework.ui.model.TreeDesignModel#setImageProperty(String)
	 */
	public void setImageProperty(String property) {
		this.imageProperty	= property;
	}

	/**
	 * @see com.cc.framework.ui.model.TreeDesignModel#getImageProperty()
	 */
	public String getImageProperty() {
		return imageProperty;
	}

	/**
	 * @see com.cc.framework.ui.model.TreeDesignModel#setLabelProperty(String)
	 */
	public void setLabelProperty(String property) {
		this.labelProperty	= property;
	}

	/**
	 * @see com.cc.framework.ui.model.TreeDesignModel#getLabelProperty()
	 */
	public String getLabelProperty() {
		return labelProperty;
	}

	/**
	 * @see com.cc.framework.ui.model.TreeDesignModel#setImageMap(ImageMap)
	 */
	public void setImageMap(ImageMap map) {
		this.imageMap	= map;
	}

	/**
	 * @see com.cc.framework.ui.model.TreeDesignModel#getImageMap()
	 */
	public ImageMap getImageMap() {
		return imageMap;
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
		this.target = target;
	}

	/**
	 * @see com.cc.framework.ui.model.TreeStyle#getTarget()
	 */
	public String getTarget() {
		return target;
	}

	/**
	 * @see com.cc.framework.ui.model.TreeStyle#setTargetProperty(java.lang.String)
	 */
	public void setTargetProperty(String targetProperty) {
		this.targetProperty = targetProperty;
	}

	/**
	 * @see com.cc.framework.ui.model.TreeStyle#getTargetProperty()
	 */
	public String getTargetProperty() {
		return targetProperty;
	}

	/**
	 * @see com.cc.framework.ui.model.TreeStyle#getSelectMode()
	 */
	public SelectMode getSelectMode() {
		return selectMode;
	}

	/**
	 * @see com.cc.framework.ui.model.TreeStyle#setSelectMode(SelectMode)
	 */
	public void setSelectMode(SelectMode mode) {
		this.selectMode = mode;
	}

	/**
	 * @see com.cc.framework.ui.model.TreeStyle#getTooltipProperty()
	 */
	public String getTooltipProperty() {
		return tooltipProperty;
	}

	/**
	 * @see com.cc.framework.ui.model.TreeStyle#setTooltipProperty(java.lang.String)
	 */
	public void setTooltipProperty(String tooltipProperty) {
		this.tooltipProperty = tooltipProperty;
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

	/**
	 * @see com.cc.framework.ui.model.TreeDesignModel#getMaxLength()
	 */
	public int getMaxLength() {
		return maxLength;
	}

	/**
	 * @see com.cc.framework.ui.model.TreeDesignModel#setMaxLength(int)
	 */
	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

	/**
	 * @see com.cc.framework.ui.model.TreeDesignModel#filter()
	 */
	public boolean filter() {
		return filter;
	}

	/**
	 * @see com.cc.framework.ui.model.TreeDesignModel#setFilter(boolean)
	 */
	public void setFilter(boolean filter) {
		this.filter = filter;
	}
}