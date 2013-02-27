/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/controls/ListTag.java,v 1.41 2005/11/13 14:03:58 P001002 Exp $
 * $Revision: 1.41 $
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
import com.cc.framework.ui.SelectMode;
import com.cc.framework.ui.control.Control;
import com.cc.framework.ui.control.ControlButton;
import com.cc.framework.ui.control.LineIterator;
import com.cc.framework.ui.control.ListControl;
import com.cc.framework.ui.control.SimpleListControl;
import com.cc.framework.ui.model.ColumnDesignModel;
import com.cc.framework.ui.model.ControlDesignModel;
import com.cc.framework.ui.model.ImageModel;
import com.cc.framework.ui.model.InnerFrame;
import com.cc.framework.ui.model.ListDesignModel;
import com.cc.framework.ui.model.imp.ListDesignModelImp;

/**
 * Tag-Handler for the <code>list</code> Tag.
 * <p>
 * Generates a multi-column list element based on
 * a ListDataModel. The layout of the list can be
 * defined in the JSP-Page with the help of the <columnXXXX>-tags.
 * The output format of the list element (HTML, XML, JavaApplet, etc.)
 * is determined by the Painter Factory used.
 * The <list>-tag can only be used in conjunction with a Bean
 * that implements the ListDataModel interface.
 *
 * @author      <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version     $Revision: 1.41 $
 * @since       1.0
 */
public class ListTag extends BaseControlTag implements FrameContainerTag, ColumnContainerTag {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -3294286431681988688L;

	/**
	 * Constructor
	 */
	public ListTag() {
		super();
	}

	/**
	 * Retrieves the current Control instance
	 * 
	 * @return	Control Instance
	 */
	protected ListControl getCtrl() {
		return (ListControl) getControl();
	}

	/**
	 * @see com.cc.framework.taglib.controls.BaseControlTag#doCreateDesignModel()
	 */
	public ControlDesignModel doCreateDesignModel() {
		return new ListDesignModelImp();
	}

	/**
	 * Returns a casted version of the ControlDesignModel
	 *
	 * @return		ListDesignModel
	 */
	protected ListDesignModel getListDesignModel() {
		return (ListDesignModel) getDesignModel();
	}

	/**
	 * @see com.cc.framework.taglib.controls.BaseControlTag#doCreateControl()
	 */
	protected Control doCreateControl() throws JspException {
		// get the control
		Object value = lookupBean();

		if (!(value instanceof SimpleListControl)) {
			throw new JspException("ListTag: Invalid property class. expected class=SimpleListControl!");
		}

		ListDesignModel designModel = getListDesignModel();

		// set the control
		SimpleListControl ctrl = (SimpleListControl) value;

		// Assign an action if this doesn't happend by the programmer
		if (designModel.getAction() == null) {
			designModel.setAction(getDefaultAction());
		}

		// Assign the DesignModel if this did not happened
		// in the Application
		if ((ctrl.getDesignModel() == null) || ctrl.getDesignModel().isDynamicDesignModel()) {
			ctrl.setDesignModel(designModel);
		} else {
			ListDesignModel dm = (ListDesignModel) ctrl.getDesignModel();

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
			if (getListDesignModel().size() == 0) {
				// Add a header frame
				frame.setLayoutHint(AlignmentType.TOP);
			} else {
				frame.setLayoutHint(AlignmentType.BOTTOM);
			}
		}

		getListDesignModel().addInnerFrame(frame);
	}

	/**
	 * @see com.cc.framework.taglib.controls.ColumnContainerTag#addColumn(com.cc.framework.ui.model.ColumnDesignModel)
	 */
	public void addColumn(ColumnDesignModel column) {
		getListDesignModel().addColumn(column);
	}

	/**
	 * The main title of the list element
	 * @param	title	The Title
	 */
	public void setTitle(String title) {
		getListDesignModel().setTitle(title);
	}

	/**
	 * A text that is output in the body of the
	 * list element if there are no lines available.
	 *
	 * @param	emptyText	Text
	 */
	public void setEmptyText(String emptyText) {
		getListDesignModel().setEmptyText(emptyText);
	}

	/**
	 * Sets the number of maximal diplayed rows per page
	 *
	 * @param	rowCount	Number of rows
	 * @throws JspException
	 *             If the Argument can't be converted to int
	 */
	public void setRows(String rowCount) throws JspException {
		getListDesignModel().setRowCount(TagHelp.toInt(rowCount));
	}

	/**
	 * Sets the number of maximal diplayed rows per page
	 *
	 * @param	rowCount	Number of rows
	 */
	public void setRows(int rowCount) {
		getListDesignModel().setRowCount(rowCount);
	}

	/**
	 * Sets the number of minimal diplayed rows per page
	 *
	 * @param	rowCount	Number of rows
	 * @throws JspException
	 *             If the Argument can't be converted to int
	 */
	public void setMinRows(String rowCount) throws JspException {
		getListDesignModel().setMinRowCount(TagHelp.toInt(rowCount));
	}

	/**
	 * Sets the number of minimal diplayed rows per page
	 *
	 * @param	rowCount	Number of rows
	 */
	public void setMinRows(int rowCount) {
		getListDesignModel().setMinRowCount(rowCount);
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
			getListDesignModel().setSelectMode(SelectMode.parse(mode));
		} catch (InvalidEnumType iet) {
			throw new JspException("Invalid Attribute value " + iet.getMessage());
		}
	}

	/**
	 * Sets the Flag for the RefreshButton
	 * @param	value	Flag, true if the RefreshButton should be painted
	 * @throws	JspException	if the Argument can not be converted to a boolean Value
	 */
	public void setRefreshButton(String value) throws JspException {
		getListDesignModel()
			.setButtonPermission(ControlButton.REFRESH, TagHelp.toPermission(value));
	}

	/**
	 * Sets the Flag for the CreateButton
	 * @param		value	Flag, true if the CreateButton should be painted
	 * @throws	JspException	if the Argument can not be converted to a boolean Value
	 */
	public void setCreateButton(String value) throws JspException {
		getListDesignModel()
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
		getListDesignModel()
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
		getListDesignModel()
			.setButtonPermission(ControlButton.EXPORTLIST, TagHelp.toPermission(value));
	}

	/**
	 * Sets the number of page buttons
	 * 
	 * @param value number of buttons
	 * @throws JspException
	 *             If the Argument can't be converted to int
	 */
	public void setPageButtons(String value) throws JspException {
		getListDesignModel().setPageButtons(TagHelp.toInt(value));
	}

	/**
	 * Sets the number of page buttons
	 * @param value number of buttons
	 */
	public void setPageButtons(int value) {
		getListDesignModel().setPageButtons(value);
	}

	/**
	 * Sets the cell spacing for the list cells
	 * @param		spacing Spacing factor
	 * @throws JspException
	 *             If the Argument can't be converted to int
	 */
	public void setCellSpacing(String spacing) throws JspException {
		getListDesignModel().setCellSpacing(TagHelp.toInt(spacing));
	}

	/**
	 * Sets the cell padding for the list cells
	 * @param		padding Padding factor
	 * @throws JspException
	 *             If the Argument can't be converted to int
	 */
	public void setCellPadding(String padding) throws JspException {
		getListDesignModel().setCellPadding(TagHelp.toInt(padding));
	}

	/**
	 * If this flag is set to false the header of the control will be suppressed.
	 *
	 * @param	value	<code>false</code> if the header should not be painted; <code>true</code> otherwise
	 * @throws	JspException	if the Argument can not be converted to a boolean Value
	 */
	public void setShowHeader(String value) throws JspException {
		getListDesignModel().setShowHeader(TagHelp.toBoolean(value));
	}

	/**
	 * Disables the list border
	 *
	 * @param	noframe	<code>true</code> if the list frame should be
	 *			hidden
	 * @throws	JspException If the Argument can't be converted to boolean
	 */
	public void setNoframe(String noframe) throws JspException {
		getListDesignModel().setShowFrame(!TagHelp.toBoolean(noframe));
	}

	/**
	 * Disables the list header
	 *
	 * @param	noheader <code>true</code> if the list header should be
	 *			hidden
	 * @throws	JspException If the Argument can't be converted to boolean
	 */
	public void setNoheader(String noheader) throws JspException {
		getListDesignModel().setShowHeader(!TagHelp.toBoolean(noheader));
	}

	/**
	 * @see com.cc.framework.taglib.ImageContainerTag#setImage(com.cc.framework.ui.model.ImageModel)
	 */
	public void setImage(ImageModel image) {
		getListDesignModel().setImage(image);
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