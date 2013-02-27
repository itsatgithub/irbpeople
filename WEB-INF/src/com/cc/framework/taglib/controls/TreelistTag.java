/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/controls/TreelistTag.java,v 1.38 2005/11/13 14:03:58 P001002 Exp $
 * $Revision: 1.38 $
 * $Date: 2005/11/13 14:03:58 $
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

package com.cc.framework.taglib.controls;

import javax.servlet.jsp.JspException;

import com.cc.framework.common.InvalidEnumType;
import com.cc.framework.taglib.FrameContainerTag;
import com.cc.framework.taglib.TagHelp;
import com.cc.framework.ui.AlignmentType;
import com.cc.framework.ui.ExpansionMode;
import com.cc.framework.ui.SelectMode;
import com.cc.framework.ui.control.Control;
import com.cc.framework.ui.control.ControlButton;
import com.cc.framework.ui.control.LineIterator;
import com.cc.framework.ui.control.TreelistControl;
import com.cc.framework.ui.model.ColumnDesignModel;
import com.cc.framework.ui.model.ControlDesignModel;
import com.cc.framework.ui.model.ImageModel;
import com.cc.framework.ui.model.InnerFrame;
import com.cc.framework.ui.model.TreelistDesignModel;
import com.cc.framework.ui.model.imp.TreelistDesignModelImp;

/**
 * Tag-Handler for the <code>treelist</code> Tag.
 * <p>
 * Generates a list control element whose underlying data
 * structure is a tree. The data model thus implements
 * the TreeGroupDataModel.
 * The layout of the list can be defined in the JSP-Page.
 * The <treelist>-tag can only be used in conjunction with
 * a bean that implements the TreeGroupDataModel Interface
 * or is derived from TreeListControl.
 *
 * @author      <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version     $Revision: 1.38 $
 * @since       1.0
 */
public class TreelistTag extends BaseControlTag implements FrameContainerTag, ColumnContainerTag {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -7149258075437868949L;

	/**
	 * Constructor
	 */
	public TreelistTag() {
		super();
	}

	/**
	 * Retrieves the current Control instance
	 * 
	 * @return	Control Instance
	 */
	protected TreelistControl getCtrl() {
		return (TreelistControl) getControl();
	}
	
	/**
	 * @see com.cc.framework.taglib.controls.BaseControlTag#doCreateDesignModel()
	 */
	public ControlDesignModel doCreateDesignModel() {
		return new TreelistDesignModelImp();
	}

	/**
	 * Returns a casted version of the ControlDesignModel
	 *
	 * @return		TreelistDesignModel
	 */
	protected TreelistDesignModel getTreeListDesignModel() {
		return (TreelistDesignModel) getDesignModel();
	}

	/**
	 * Returns a casted version of the ControlDesignModel
	 *
	 * @return		TreelistDesignModel
	 */
	protected TreelistDesignModel getTreelistDesignModel() {
		return (TreelistDesignModel) getDesignModel();
	}

	/**
	 * @see com.cc.framework.taglib.controls.BaseControlTag#doCreateControl()
	 */
	protected Control doCreateControl() throws JspException {
		// get the model
		Object value = lookupBean();

		if (!(value instanceof TreelistControl)) {
			throw new JspException("TreelistTag: invalid property class. expected class=TreelistControl!");
		}

		TreelistDesignModel designModel = getTreelistDesignModel();

		// assign the control
		TreelistControl ctrl = (TreelistControl) value;

		// assign an action if the programmer did not
		if (designModel.getAction() == null) {
			designModel.setAction(getDefaultAction());
		}

		// Assign the DesignModel if this did not happened
		// within the Application
		if ((ctrl.getDesignModel() == null) || ctrl.getDesignModel().isDynamicDesignModel()) {
			ctrl.setDesignModel(designModel);
		} else {
			TreelistDesignModel dm = (TreelistDesignModel) ctrl.getDesignModel();

			dm.setAction(designModel.getAction());
			dm.setProperty(designModel.getProperty());
		}

		return ctrl;
	}

	/**
	 * @see com.cc.framework.taglib.FrameContainerTag#addInnerFrame(com.cc.framework.ui.model.InnerFrame)
	 */
	public void addInnerFrame(InnerFrame frame) {
		if (frame.getLayoutHint() == null) {
			// Use declaration sequence to derive
			// layout information
			if (getTreeListDesignModel().size() == 0) {
				// Add a header frame
				frame.setLayoutHint(AlignmentType.TOP);
			} else {
				frame.setLayoutHint(AlignmentType.BOTTOM);
			}
		}

		getTreeListDesignModel().addInnerFrame(frame);
	}

	/**
	 * @see com.cc.framework.taglib.controls.ColumnContainerTag#addColumn(com.cc.framework.ui.model.ColumnDesignModel)
	 */
	public void addColumn(ColumnDesignModel column) {
		getTreelistDesignModel().addColumn(column);
	}

	/**
	 * The main title of the list element.
	 * The character string is HTML-encoded before outputting
	 *
	 * @param	title	The main title
	 */
	public void setTitle(String title) {
		getTreelistDesignModel().setTitle(title);
	}

	/**
	 * A text that is output in the body of the list element
	 * if there are no lines available
	 *
	 * @param	emptyText	Text to output if there are no lines
	 */
	public void setEmptyText(String emptyText) {
		getTreelistDesignModel().setEmptyText(emptyText);
	}

	/**
	 * Specifies the selection mode of the list. This is only
	 * used if a Check column is to be displayed here as well.
	 * <ul>
	 * 		<li>none = no selection/not selectable.</li>
	 * 		<li>single = single selection.</li>
	 *		<li>multiple = multiple selection.</li>
	 * </ul>
	 *
	 * @param	mode	The selection mode of the list.
	 * @throws	JspException If the Argument can't be converted	to an object of type SelectMode
	 */
	public void setSelect(String mode) throws JspException {
		try {
			getTreelistDesignModel().setSelectMode(SelectMode.parse(mode));
		} catch (InvalidEnumType iet) {
			throw new JspException("Invalid Attribute value " + iet.getMessage());
		}
	}

	/**
	 * Specifies whether a button should be displayed for updating the elements.
	 * By specifying an Access Control List, the creation of the button
	 * can be restricted to certain users.
	 *
	 * @param	value			An Access Control List
	 * @throws	JspException	If the Argument can not be converted to a boolean value
	 */
	public void setRefreshButton(String value) throws JspException {
		getTreelistDesignModel()
			.setButtonPermission(ControlButton.REFRESH, TagHelp.toPermission(value));
	}

	/**
	 * Indicates whether a button for creating a new data record should
	 * be displayed. By specifying an Access Control List, the creation
	 * of new records can be restricted to certain users.
	 *
	 * @param	value			An Access Control List
	 * @throws	JspException	if the Argument can not be converted to a boolean value
	 */
	public void setCreateButton(String value) throws JspException {
		getTreelistDesignModel()
			.setButtonPermission(ControlButton.CREATE, TagHelp.toPermission(value));
	}

	/**
	 * Sets the Flag for the PrintList Button
	 * 
	 * @param value
	 *            Flag, <code>true</code> if the Button should be shown
	 * @throws JspException
	 *             if the Argument can not be converted to a boolean Value
	 */
	public void setPrintListButton(String value) throws JspException {
		getTreelistDesignModel()
			.setButtonPermission(ControlButton.PRINTLIST, TagHelp.toPermission(value));
	}

	/**
	 * Sets the Flag for the ExportList Button
	 * 
	 * @param value
	 *            Flag, <code>true</code> if the Button should be shown
	 * @throws JspException
	 *             if the Argument can not be converted to a boolean Value
	 */
	public void setExportListButton(String value) throws JspException {
		getTreelistDesignModel()
			.setButtonPermission(ControlButton.EXPORTLIST, TagHelp.toPermission(value));
	}

	/**
	 * Sets the number of maximal diplayed rows per page
	 *
	 * @param	rowCount	The number of rows
	 * @throws JspException
	 *             If the Argument can't be converted to int
	 */
	public void setRows(String rowCount) throws JspException {
		getTreelistDesignModel().setRowCount(TagHelp.toInt(rowCount));
	}

	/**
	 * Sets the number of minimal diplayed rows per page
	 *
	 * @param	rowCount	Number of rows
	 * @throws JspException
	 *             If the Argument can't be converted to int
	 */
	public void setMinRows(String rowCount) throws JspException {
		getTreelistDesignModel().setMinRowCount(TagHelp.toInt(rowCount));
	}

	/**
	 * Sets the number of minimal diplayed rows per page
	 *
	 * @param	rowCount	Number of rows
	 */
	public void setMinRows(int rowCount) {
		getTreelistDesignModel().setMinRowCount(rowCount);
	}

	/**
	 * This attribute specifies how many nodes of the tree
	 * structure may be displayed exploded at the same time.
	 * <ul>
	 * 		<li>full =	All nodes are shown exploded at all times. The user cannot close any node.</li>
	 * 		<li>single = Only one branch can be exploded.</li>
	 * 		<li>multiple = As many branches as desired can be exploded.</li>
	 * </ul>
	 *
 	 * @param	expand			Specifies how many nodes of the tree structure may be displayed exploded
	 * @throws	JspException	If the argument can not be converted to an object of type ExpansionMode
	 */
	public void setExpandMode(String expand) throws JspException {

		try {
			getTreelistDesignModel().setExpansionMode(ExpansionMode.parse(expand));
		} catch (com.cc.framework.common.InvalidEnumType iet) {
			throw new JspException("Invalid attribute value " + iet.getMessage());
		}
	}

	/**
	 * Indicates whether a checkbox should be displayed before
	 * the tree entries. The tree entries must implement the
	 * Checkable Interface, so that the control element can draw
	 * the checkboxes. Group nodes must not implement this interface,
	 * since the Check-State of groups can be automatically calculated
	 * with the help of the state of the pages.
	 *
	 * @param	value			<code>true</code> if checkboxes should be displayed in the tree
	 * @throws	JspException	if the argument can not be converted to a boolean value
	 */
	public void setCheckboxes(String value) throws JspException {
		getTreelistDesignModel().showCheckBoxes(TagHelp.toBoolean(value));
	}

	/**
	 * Indicates whether, in case of group nodes, buttons for
	 * exploding or closing the branch should be displayed.
	 * This option has no effect if the expandMode of the tree
	 * structure is set to full. In that case, buttons are never shown.
	 *
	 * @param		value			<code>true</code> if buttons for exploding or closing the branch should be displayed.
	 * @throws		JspException 	If the Argument can't be converted	to boolean
	 */
	public void setButtons(String value) throws JspException {
		getTreelistDesignModel().showButtons(TagHelp.toBoolean(value));
	}

	/**
	 * Specifies whether connecting lines should be drawn
	 * between the construction elements.
	 *
	 * @param	value			<code>true</code> if connecting lines should be drawn
	 * @throws	JspException	If the Argument can't be converted	to boolean
	 */
	public void setLines(String value) throws JspException {
		getTreelistDesignModel().showLines(TagHelp.toBoolean(value));
	}

	/**
	 * Specifies whether lines should be drawn to the construction
	 * elements of the uppermost (displayed) level.
	 *
	 * @param	value			<code>false</code> if the lines on the top node should not be displayed.
	 * @throws	JspException	if the argument can not be converted to a boolean value
	 */
	public void setLinesAtRoot(String value) throws JspException {
		getTreelistDesignModel().showLinesAtRoot(TagHelp.toBoolean(value));
	}

	/**
	 * Specifies whether the root node of the tree structure (Level 0)
	 * should be displayed or whether the display should first start
	 * at the next tree level(Level 1).
	 *
	 * @param	value			<code>false</code> if the root element should not be displayed
	 * @throws	JspException	if the argument can not be converted to a boolean value
	 */
	public void setRoot(String value) throws JspException {
		getTreelistDesignModel().showRoot(TagHelp.toBoolean(value));
	}

	/**
	 * Specifies whether, for the group nodes of the tree
	 * structure, a Click Eventhandler should be called.
	 *
	 * @param	value			<code>true</code> if a Click Eventhandler should be called.
	 * @throws	JspException	If the Argument can't be converted	to boolean
	 */
	public void setGroupselect(String value) throws JspException {
		getTreelistDesignModel().setGroupSelect(TagHelp.toBoolean(value));
	}

	/**
	 * Sets the number of page buttons
	 * @param value number of buttons
	 * @throws JspException
	 *             If the Argument can't be converted to int
	 */
	public void setPageButtons(String value) throws JspException {
		getTreelistDesignModel().setPageButtons(TagHelp.toInt(value));
	}

	/**
	 * Sets the cell spacing for the list cells
	 * @param		spacing Spacing factor
	 * @throws JspException
	 *             If the Argument can't be converted to int
	 */
	public void setCellSpacing(String spacing) throws JspException {
		getTreelistDesignModel().setCellSpacing(TagHelp.toInt(spacing));
	}

	/**
	 * Sets the cell padding for the list cells
	 * @param		padding Padding factor
	 * @throws JspException
	 *             If the Argument can't be converted to int
	 */
	public void setCellPadding(String padding) throws JspException {
		getTreelistDesignModel().setCellPadding(TagHelp.toInt(padding));
	}

	/**
	 * If this flag is set to false the header of the control will be suppressed.
	 *
	 * @param	value	<code>false</code> if the header should not be painted; <code>true</code> otherwise
	 * @throws	JspException	if the Argument can not be converted to a boolean Value
	 */
	public void setShowHeader(String value) throws JspException {
		getTreelistDesignModel().setShowHeader(TagHelp.toBoolean(value));
	}

	/**
	 * Indicates the name of a property, with the help of which the
	 *  column function can be disabled in the relevant line.
	 * For this, the Row-Bean must provide a corresponding
	 * Property Getter, which returns a Boolean data type.
	 *
	 * @param	value	enable
	 */
	public void setEnableProperty(String value) {
		getTreelistDesignModel().setEnableProperty(value);
	}

	/**
	 * Disables the list border
	 *
	 * @param	noframe	<code>true</code> if the list frame should be
	 *			hidden
	 * @throws	JspException If the Argument can't be converted to boolean
	 */
	public void setNoframe(String noframe) throws JspException {
		getTreelistDesignModel().setShowFrame(!TagHelp.toBoolean(noframe));
	}

	/**
	 * Disables the list header
	 *
	 * @param	noheader <code>true</code> if the list header should be
	 *			hidden
	 * @throws	JspException If the Argument can't be converted to boolean
	 */
	public void setNoheader(String noheader) throws JspException {
		getTreelistDesignModel().setShowHeader(!TagHelp.toBoolean(noheader));
	}

	/**
	 * @see com.cc.framework.taglib.ImageContainerTag#setImage(com.cc.framework.ui.model.ImageModel)
	 */
	public void setImage(ImageModel image) {
		getTreeListDesignModel().setImage(image);
	}

	/**
	 * @see com.cc.framework.taglib.controls.ColumnContainerTag#getLineIterator()
	 */
	public LineIterator getLineIterator() {
		// Get the LineIterator for the current page
		return getCtrl().getLineIterator(true);
	}

	/**
	 * @see com.cc.framework.taglib.controls.ColumnContainerTag#getRowsPerPage()
	 */
	public int getRowsPerPage() {
		return getCtrl().getRowsPerPage();
	}
}