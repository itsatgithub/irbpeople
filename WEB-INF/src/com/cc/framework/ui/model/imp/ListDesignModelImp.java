/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/imp/ListDesignModelImp.java,v 1.27 2005/11/13 14:03:57 P001002 Exp $
 * $Revision: 1.27 $
 * $Date: 2005/11/13 14:03:57 $
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

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import com.cc.framework.security.Permission;
import com.cc.framework.security.Principal;
import com.cc.framework.security.StaticPermission;
import com.cc.framework.ui.SelectMode;
import com.cc.framework.ui.control.ControlButton;
import com.cc.framework.ui.model.ColumnDesignModel;
import com.cc.framework.ui.model.FrameUtil;
import com.cc.framework.ui.model.ImageModel;
import com.cc.framework.ui.model.InnerFrame;
import com.cc.framework.ui.model.ListDesignModel;

/**
 * Designmodel for the ListControl
 *
 * @author	   <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version	   $Revision: 1.27 $
 * @since      1.0
 */
public class ListDesignModelImp extends ControlDesignModelImp implements ListDesignModel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -2666683087195075955L;

	/**
	 * Number of displayed lines. The default value is -1
	 * which displays all lines.
	 */
	private int rowCount				= -1;

	/**
	 * Number of minimal displayed lines
	 */
	private int minRowCount				= -1;

	/**
	 * The height of the header. Default is 24px
	 */
	private int headerHeight			= 24;

	/**
	 * Height of a row. Default is 20px
	 */
	private int rowHeight				= 20;

	/**
	 * Cell spacing factor (-1 = Painter default)
	 */
	private int cellSpacing				= -1;

	/**
	 * Cell padding factor (-1 = Painter default)
	 */
	private int cellPadding				= -1;

	/**
	 * The caption of the listcontrol
	 */
	private String title				= null;

	/**
	 * Text to display if a empty list is shown.
	 * The default text is "no items in list".
	 */
	private String emptyText			= null;

	/**
	 * The selection mode for optional checkboxes.
	 */
	private SelectMode selectMode		= SelectMode.NONE;

	/**
	 * The users permission which is necessary to see
	 * any buttons (Map&lt;ControlButton, Permission&gt;)
	 */
	private Map buttonPermissions		= new Hashtable();

	/**
	 * The number of paging buttons to show
	 */
	private int pageButtons				= 0;

	/**
	 * The buffer with the columns of the list control
	 */
	private ArrayList columns			= new ArrayList();

	/**
	 * Enables or disables the lists frame
	 */
	private boolean showFrame			= true;

	/**
	 * The optional title image
	 */
	private ImageModel image			= null;

	/**
	 * This flag indicates if the header should be painted
	 */
	private boolean showHeader			= true;

	/**
	 * Inner Frames
	 */
	private ArrayList frames			= new ArrayList();

	// -----------------------------------
	//        methods
	// -----------------------------------

	/**
	 * Constructor for ListDesignModelImp
	 */
	public ListDesignModelImp() {
		super();

		buttonPermissions.put(ControlButton.REFRESH, StaticPermission.NONE);
		buttonPermissions.put(ControlButton.CREATE, StaticPermission.NONE);
		buttonPermissions.put(ControlButton.PRINTLIST, StaticPermission.NONE);
		buttonPermissions.put(ControlButton.EXPORTLIST, StaticPermission.NONE);
	}

	/**
	 * @see com.cc.framework.ui.model.ListDesignModel#getRowCount()
	 */
	public int getRowCount() {
		return rowCount;
	}

	/**
	 * @see com.cc.framework.ui.model.ListDesignModel#setMinRowCount(int)
	 */
	public void setMinRowCount(int rows) {
		this.minRowCount = rows;
	}

	/**
	 * @see com.cc.framework.ui.model.ListDesignModel#getMinRowCount()
	 */
	public int getMinRowCount() {
		return minRowCount;
	}

	/**
	 * @see com.cc.framework.ui.model.ListDesignModel#setRowCount(int)
	 */
	public void setRowCount(int rows) {
		this.rowCount	= rows;
	}

	/**
	 * @see com.cc.framework.ui.model.ListDesignModel#getTitle()
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @see com.cc.framework.ui.model.ListDesignModel#setTitle(String)
	 */
	public void setTitle(String title) {
		this.title	= title;
	}

	/**
	 * @see com.cc.framework.ui.model.ListDesignModel#getEmptyText()
	 */
	public String getEmptyText() {
		return emptyText;
	}

	/**
	 * @see com.cc.framework.ui.model.ListDesignModel#setEmptyText(String)
	 */
	public void setEmptyText(String emptyText) {
		this.emptyText	= emptyText;
	}

	/**
	 * @see com.cc.framework.ui.model.ListDesignModel#addColumn(ColumnDesignModel)
	 */
	public void addColumn(ColumnDesignModel newColumn) {

		synchronized (columns) {
			columns.add(newColumn);
		}
	}

	/**
	 * @see com.cc.framework.ui.model.ListDesignModel#removeColumn(ColumnDesignModel)
	 */
	public void removeColumn(ColumnDesignModel column) {

		synchronized (columns) {
			columns.remove(column);
		}
	}

	/**
	 * @see com.cc.framework.ui.model.ListDesignModel#getColumns()
	 */
	public ColumnDesignModel[] getColumns() {

		synchronized (columns) {
			ColumnDesignModel[] list = new ColumnDesignModel[columns.size()];

			return ((ColumnDesignModel[]) columns.toArray(list));
		}
	}

	/**
	 * @see com.cc.framework.ui.model.ListDesignModel#size()
	 */
	public int size() {
		return columns.size();
	}

	/**
	 * @see com.cc.framework.ui.model.ListDesignModel#getHeaderHeight()
	 */
	public int getHeaderHeight() {
		return headerHeight;
	}

	/**
	 * @see com.cc.framework.ui.model.ListDesignModel#getRowHeight()
	 */
	public int getRowHeight() {
		return rowHeight;
	}

	/**
	 * @see com.cc.framework.ui.model.ListDesignModel#getSelectMode()
	 */
	public SelectMode getSelectMode() {
		return selectMode;
	}

	/**
	 * @see com.cc.framework.ui.model.ListDesignModel#setSelectMode(SelectMode)
	 */
	public void setSelectMode(SelectMode mode) {
		this.selectMode	= mode;
	}

	/**
	 * Retrieves the permission for the given button
	 * 
	 * @param button Button constant
	 * @return Permission or <code>null</code>
	 */
	public Permission getButtonPermission(ControlButton button) {
		return (Permission) buttonPermissions.get(button);
	}
	
	/**
	 * Checks if the button can be displayed.
	 * 
	 * @param  button The Button to query
	 * @param	principal	The principal object
	 * @return	boolean		<code>true</code> if the button should be displayed
	 */
	public boolean showButton(ControlButton button, Principal principal) {
		Permission permission = getButtonPermission(button);
	
		if (permission == null) {
			return false;
		} else {
			return permission.isGranted(principal);
		}
	}

	/**
	 * Sets whether a command button should be displayed by specifying an Access Control List.
	 *  
	 * @param  button The Button to query
	 * @param	permission	Permission
	 */
	public void setButtonPermission(ControlButton button, Permission permission) {
		buttonPermissions.put(button, permission);
	}

	/**
	 * @see com.cc.framework.ui.model.ListDesignModel#showRefreshButton(com.cc.framework.security.Principal)
	 */
	public boolean showRefreshButton(Principal principal) {
		return showButton(ControlButton.REFRESH, principal);
	}

	/**
	 * @see com.cc.framework.ui.model.ListDesignModel#setRefreshButtonPermission(Permission)
	 */
	public void setRefreshButtonPermission(Permission permission) {
		setButtonPermission(ControlButton.REFRESH, permission);
	}

	/**
	 * @see com.cc.framework.ui.model.ListDesignModel#showCreateButton(Principal)
	 */
	public boolean showCreateButton(Principal principal) {
		return showButton(ControlButton.CREATE, principal);
	}

	/**
	 * @see com.cc.framework.ui.model.ListDesignModel#setCreateButtonPermission(Permission)
	 */
	public void setCreateButtonPermission(Permission permission) {
		setButtonPermission(ControlButton.CREATE, permission);
	}

	/**
	 * @see com.cc.framework.ui.model.ListDesignModel#getPageButtons()
	 */
	public int getPageButtons() {
		return pageButtons;
	}

	/**
	 * @see com.cc.framework.ui.model.ListDesignModel#setPageButtons(int)
	 */
	public void setPageButtons(int buttons) {
		this.pageButtons = buttons;
	}

	/**
	 * @see com.cc.framework.ui.model.ListDesignModel#setShowFrame(boolean)
	 */
	public void setShowFrame(boolean show) {
		this.showFrame = show;
	}

	/**
	 * @see com.cc.framework.ui.model.ListDesignModel#showFrame()
	 */
	public boolean showFrame() {
		return showFrame;
	}

	/**
	 * @see com.cc.framework.ui.model.ListDesignModel#showHeader()
	 */
	public boolean showHeader() {
		return showHeader;
	}

	/**
	 * @see com.cc.framework.ui.model.ListDesignModel#setShowHeader(boolean)
	 */
	public void setShowHeader(boolean showHeader) {
		this.showHeader = showHeader;
	}

	/**
	 * @see com.cc.framework.ui.model.ListDesignModel#getCellPadding()
	 */
	public int getCellPadding() {
		return cellPadding;
	}

	/**
	 * @see com.cc.framework.ui.model.ListDesignModel#getCellSpacing()
	 */
	public int getCellSpacing() {
		return cellSpacing;
	}

	/**
	 * @see com.cc.framework.ui.model.ListDesignModel#setCellPadding(int)
	 */
	public void setCellPadding(int padding) {
		this.cellPadding = padding;
	}

	/**
	 * @see com.cc.framework.ui.model.ListDesignModel#setCellSpacing(int)
	 */
	public void setCellSpacing(int spacing) {
		this.cellSpacing = spacing;
	}

	/**
	 * @see com.cc.framework.ui.model.FrameContainer#addInnerFrame(com.cc.framework.ui.model.InnerFrame)
	 */
	public void addInnerFrame(InnerFrame frame) {
		frames.add(frame);
	}

	/**
	 * @see com.cc.framework.ui.model.FrameContainer#getInnerFrames(com.cc.framework.security.Principal, java.lang.Object)
	 */
	public InnerFrame[] getInnerFrames(Principal principal, Object layoutHint) {
		return FrameUtil.filter(frames, principal, layoutHint);
	}

	/**
	 * @see com.cc.framework.ui.model.FrameContainer#getImage()
	 */
	public ImageModel getImage() {
		return image;
	}

	/**
	 * @see com.cc.framework.ui.model.FrameContainer#setImage(com.cc.framework.ui.model.ImageModel)
	 */
	public void setImage(ImageModel img) {
		this.image = img;
	}
}
