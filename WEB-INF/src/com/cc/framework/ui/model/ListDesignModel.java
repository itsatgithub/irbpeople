/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/ListDesignModel.java,v 1.14 2005/11/09 14:43:25 P001002 Exp $
 * $Revision: 1.14 $
 * $Date: 2005/11/09 14:43:25 $
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

import com.cc.framework.security.Permission;
import com.cc.framework.security.Principal;
import com.cc.framework.ui.SelectMode;
import com.cc.framework.ui.control.ControlButton;

/**
 * Designmodel for the ListControl
 *
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version $Revision: 1.14 $
 */
public interface ListDesignModel extends ControlDesignModel, ColumnContainer, FrameContainer {

	/**
	 * Returns the number of rows which should be displayed(rendered) by
	 * the control. If the datamodel has more rows then specified.
	 * The buttons for navigation (prev page, next page..) are
	 * dislayed.
	 *
	 * @return Number of rows to display (render). If set to -1 all rows were rendered.
	 */
	public int getRowCount();

	/**
	 * This attribute is used to specify the maximum number of
	 * lines that are depicted simultaneously. When the total number
	 * of available lines in the data model is greater than the number
	 * of lines displayed, scrollbars are automatically generated for
	 * slowly scrolling through pages.
	 * If this attribute is set to -1, all lines are always displayed.
 	 *
	 * @param		rows Number of lines
	 */
	public void setRowCount(int rows);

	/**
	 * Returns the minimal number of rows which should be displayed(rendered)
	 * by the control. If the datamodel has less rows then specified the
	 * controll will be filled with empty lines.
	 *
	 * @return		Number of minimal rows to display (render).
	 * 				If set to -1 no empty lines will be added.
	 */
	public int getMinRowCount();

	/**
	 * This attribute is used to specify the minimum number of
	 * lines that are depicted simultaneously. When the total number
	 * of available lines in the data model is lower than the number
	 * of lines displayed, empty lines are appended to the list.
	 * If this attribute is set to -1, no empty lines will be
	 * generated.
	 *
	 * @param		rows Number of lines
	 */
	public void setMinRowCount(int rows);

	/**
	 * Returns the Title
	 * @return Titel
	 */
	public String getTitle();

	/**
	 * Sets the titel of the ListControl which is display in the Header
	 * @param	title	The main title of the control 
	 */
	public void setTitle(String title);

	/**
	 * A text that is output in the body of the
	 * list element if there are no lines available
	 *
	 * @return Zext fuer Listen ohne Inhalt
	 */
	public String getEmptyText();

	/**
	 * Sets the text that is output in the body of the
	 * list element if there are no lines available
	 *
	 * @param emptyText	The text that is output in the body of the
	 * list element if there are no lines available
	 */
	public void setEmptyText(String emptyText);

	/**
	 * Returns the height of the header (pixel)
	 *
	 * @return The height of the header
	 */
	public int getHeaderHeight();

	/**
	 * Returns the height of a row (pixel)
	 *
	 * @return The height of the row
	 */
	public int getRowHeight();

	/**
	 * Specifies the selection mode of the list.
	 * This is only used if a Check column is to
	 * be displayed here as well. 
	 * <ul>
	 *   <li>NONE = no selection.</li>
	 *   <li>SINGLE = single selection.</li>
	 *   <li>MULTIPLE = multiple selection.</li>
	 * </ul>
	 * @return	SelectMode
	 */
	public SelectMode getSelectMode();

	/**
	 * Sets the selection mode of the list.
	 * @param	mode	the selection mode
	 */
	public void setSelectMode(SelectMode mode);

	/**
	 * Retrieves the permission for the given button
	 * 
	 * @param button Button constant
	 * @return Permission or <code>null</code>
	 */
	public Permission getButtonPermission(ControlButton button);

	/**
	 * Checks if the button can be displayed.
	 * 
	 * @param  button The Button to query
	 * @param	principal	The principal object
	 * @return	boolean		<code>true</code> if the button should be displayed
	 */
	public boolean showButton(ControlButton button, Principal principal);

	/**
	 * Sets whether a command button should be displayed by specifying an Access Control List.
	 *  
	 * @param  button The Button to query
	 * @param	permission	Permission
	 */
	public void setButtonPermission(ControlButton button, Permission permission);

	/**
	 * Checks if the refresh button can be displayed.
	 * 
	 * @param	principal	The principal object
	 * @return	boolean		<code>true</code> if the button should be displayed
	 * @deprecated use showButton(ControlButton.REFRESH, principal)
	 */
	public boolean showRefreshButton(Principal principal);

	/**
	 * Specifies whether a button should be
	 * displayed for updating the elements.
	 * The list of permissions is set to specifies when the
	 * button should be display.
	 * 
	 * @param	permission	Permission
	 * @deprecated use setButtonPermission(ControlButton.REFRESH, permission)
	 */
	public void setRefreshButtonPermission(Permission permission);

	/**
	 * Checks if the create button can be displayed.
	 * 
	 * @param	principal	The principal object
	 * @return	boolean		<code>true</code> if the button should be displayed
	 * @deprecated use showButton(ControlButton.CREATE, principal)
	 */
	public boolean showCreateButton(Principal principal);

	/**
	 * Sets whether a button for creating a new data record should be displayed.
	 * By specifying an Access Control List, the creation of new records
	 * can be restricted to certain users.
	 *  
	 * @param	permission	Permission
	 * @deprecated use setButtonPermission(ControlButton.CREATE, principal)
	 */
	public void setCreateButtonPermission(Permission permission);

	/**
	 * Returns the number of page buttons to displayed.
	 * 
	 * @return	number of page buttons
	 */
	public int getPageButtons();

	/**
	 * Specifies the number of buttons that should be
	 * displayed for direct page navigation.
	 * 
	 * @param	buttons	Button count
	 */
	public void setPageButtons(int buttons);

	/**
	 * Disables the lists frame
	 *
	 * @param	show <code>true</code> if the lists frame should be shown
	 */
	public void setShowFrame(boolean show);

	/**
	 * @return	<code>true</code> if the frame should be shown
	 */
	public boolean showFrame();
	
	/**
	 * This flag allows to hide the header within the list.
	 * Default is true.
	 * 
	 * @param showHeader	If set to false the header of the control will be suppressed
	 */
	public void setShowHeader(boolean showHeader);
	
	/**
	 * Returns if the header of the control is suppressed
	 * 
	 * @return	<code>false</code> if the header of the control is suppressed;
	 *          <code>true</code> otherwise.
	 */
	public boolean showHeader();

	/**
	 * Sets the cell spacing for the list cells
	 * @param		spacing Spacing factor
	 */
	public void setCellSpacing(int spacing);

	/**
	 * Returns the cell spacing for the list cells
	 * 
	 * @return		spacing factor
	 */
	public int getCellSpacing();

	/**
	 * Sets the cell padding for the list cells
	 * @param		padding Padding factor
	 */
	public void setCellPadding(int padding);

	/**
	 * Returns the cell padding for the list cells
	 * 
	 * @return		Padding factor
	 */
	public int getCellPadding();
}