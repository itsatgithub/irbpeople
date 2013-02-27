/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/imp/MenuItemDesignModelImp.java,v 1.19 2005/07/08 15:15:33 P001002 Exp $
 * $Revision: 1.19 $
 * $Date: 2005/07/08 15:15:33 $
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

import com.cc.framework.security.Permission;
import com.cc.framework.security.Principal;
import com.cc.framework.security.StaticPermission;
import com.cc.framework.ui.MenuStateType;
import com.cc.framework.ui.model.MenuItemDesignModel;

/**
 * Designmodel for a Menuitem
 *
 * @author	   <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version	   $Revision: 1.19 $
 * @since      1.0
 */
public class MenuItemDesignModelImp extends ClientHandlerImp implements MenuItemDesignModel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 555472358521645343L;

	/**
	 * The Menu item id
	 */
	private String menuid				= null;

	/**
	 * Parent Menu item
	 */
	private MenuItemDesignModel parent	= null;

	/**
	 * A buffer for sub menu items
	 */
	private ArrayList items				= new ArrayList();

	/**
	 * The state of the menu item
	 */
	private MenuStateType state			= null;

	/**
	 * The displaytext
	 */
	private String text					= null;

	/**
	 * A tool tip
	 */
	private String tooltip				= null;

	/**
	 * A shortcut
	 */
	private String shortcut				= null;

	/**
	 * The window target in which the resource requested by
	 * this hyperlink will be displayed, for example "blank, self, parent, ..."
	 */
	private String target				= null;

	/**
	 * A filter expression used to
	 * hide or show the menu item in different
	 * contexts
	 */
	private String menuFilter			= null;

	/**
	 * Locale Setting
	 */
	private String localeName			= null;

	/**
	 * Reference for an image in an ImageMap
	 */
	private String imageRef				= null;

	/**
	 * The action to process if the user clicks
	 * the item
	 */
	private String action				= null;

	/**
	 * The unique Id of the menu item
	 */
	private String id					= "";

	/**
	 * The width of the menu item
	 */
	private String width				= null;

	/**
	 * The permission for this menu item
	 */
	private Permission permission		= StaticPermission.GRANTED;

	// --------------------------------------
	//              methods
	// --------------------------------------

	/**
	 * Constructor
	 */
	public MenuItemDesignModelImp() {
		super();
	}

	/**
	 * @see com.cc.framework.ui.model.MenuItemDesignModel#setState(MenuStateType state)
	 */
	public void setState(MenuStateType state) {
		this.state	= state;
	}

	/**
	 * @see com.cc.framework.ui.model.MenuItemDesignModel#setText(String)
	 */
	public void setText(String text) {
		this.text	= text;
	}

	/**
	 * @see com.cc.framework.ui.model.MenuItemDesignModel#setShortcut(String)
	 */
	public void setShortcut(String shortcut) {
		this.shortcut	= shortcut;
	}

	/**
	 * @param		id The unique Id of the menu item
	 */
	public final void setId(String id) {
		this.id	= id;
	}

	/**
	 * @see com.cc.framework.ui.model.MenuItemDesignModel#setWidth(java.lang.String)
	 */
	public final void setWidth(String width) {
		this.width	= width;
	}

	/**
	 * @see com.cc.framework.ui.model.MenuItemDesignModel#setAction(java.lang.String)
	 */
	public final void setAction(String action) {
		this.action	= action;
	}

	/**
	 * @return		returns the id
	 */
	public final String getId() {
		return id;
	}

	/**
	 * @see com.cc.framework.ui.model.MenuItemDesignModel#getAction()
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @see com.cc.framework.ui.model.MenuItemDesignModel#getWidth()
	 */
	public final String getWidth() {
		return width;
	}

	/**
	 * @see com.cc.framework.ui.model.MenuItemDesignModel#getState()
	 */
	public MenuStateType getState() {
		return this.state;
	}

	/**
	 * @see com.cc.framework.ui.model.MenuItemDesignModel#getText()
	 */
	public String getText() {
		return this.text;
	}

	/**
	 * @see com.cc.framework.ui.model.MenuItemDesignModel#getShortcut()
	 */
	public String getShortcut() {
		return shortcut;
	}

	/**
	 * @see com.cc.framework.ui.model.MenuItemDesignModel#getParent()
	 */
	public MenuItemDesignModel getParent() {
		return parent;
	}

	/**
	 * @see com.cc.framework.ui.model.MenuItemDesignModel#setParent(com.cc.framework.ui.model.MenuItemDesignModel)
	 */
	public void setParent(MenuItemDesignModel parent) {
		this.parent = parent;
	}

	/**
	 * @see com.cc.framework.ui.model.MenuItemDesignModel#addItem(MenuItemDesignModel)
	 */
	public MenuItemDesignModel addItem(MenuItemDesignModel item) {

		synchronized (items) {
			items.add(item);
			item.setParent(this);

			return this;
		}
	}

	/**
	 * @see com.cc.framework.ui.model.MenuItemDesignModel#removeItem(MenuItemDesignModel)
	 */
	public MenuItemDesignModel removeItem(MenuItemDesignModel item) {

		synchronized (items) {
			items.remove(item);
			item.setParent(null);

			return this;
		}
	}

	/**
	 * @see com.cc.framework.ui.model.MenuItemDesignModel#getContent()
	 */
	public MenuItemDesignModel[] getContent() {

		synchronized (items) {
			MenuItemDesignModel[] list = new MenuItemDesignModel[items.size()];

			return ((MenuItemDesignModel[]) items.toArray(list));
		}

	}

	/**
	 * @see com.cc.framework.ui.model.MenuItemDesignModel#size()
	 */
	public int size() {
		return items.size();
	}

	/**
	 * @see com.cc.framework.ui.model.MenuItemDesignModel#setTarget(String)
	 */
	public void setTarget(String target) {
		this.target	= target;
	}

	/**
	 * @see com.cc.framework.ui.model.MenuItemDesignModel#getTarget()
	 */
	public String getTarget() {
		return this.target;
	}

	/**
	 * Returns the menu filter.
	 * @return String
	 */
	public String getMenuFilter() {
		return menuFilter;
	}

	/**
	 * Sets the menu filter.
	 * @param menuFilter The filter to set
	 */
	public void setMenuFilter(String menuFilter) {
		this.menuFilter = menuFilter;
	}

	/**
	 * Returns the imageRef.
	 * @return String
	 */
	public String getImageRef() {
		return imageRef;
	}

	/**
	 * Sets the imageRef.
	 * @param imageRef The imageRef to set
	 */
	public void setImageRef(String imageRef) {
		this.imageRef = imageRef;
	}

	/**
	 * @see com.cc.framework.ui.model.AccessControlled#getPermission()
	 */
	public Permission getPermission() {
		return permission;
	}

	/**
	 * @see com.cc.framework.ui.model.MenuItemDesignModel#setPermission(com.cc.framework.security.Permission)
	 */
	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	/**
	 * @see com.cc.framework.ui.model.AccessControlled#show(Principal)
	 */
	public boolean show(Principal principal) {
		return permission.isGranted(principal);
	}

	/**
	 * @see com.cc.framework.ui.model.MenuItemDesignModel#getMenuId()
	 */
	public String getMenuId() {
		return menuid;
	}

	/**
	 * @see com.cc.framework.ui.model.MenuItemDesignModel#setMenuId(java.lang.String)
	 */
	public void setMenuId(String menuid) {
		this.menuid = menuid;
	}

	/**
	 * @see com.cc.framework.ui.model.MenuItemDesignModel#getTooltip()
	 */
	public String getTooltip() {
		return tooltip;
	}

	/**
	 * @see com.cc.framework.ui.model.MenuItemDesignModel#setTooltip(java.lang.String)
	 */
	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

	/**
	 * @see com.cc.framework.ui.model.MenuItemDesignModel#getLocaleName()
	 */
	public String getLocaleName() {
		return localeName;
	}

	/**
	 * @see com.cc.framework.ui.model.MenuItemDesignModel#setLocaleName(java.lang.String)
	 */
	public void setLocaleName(String locale) {
		this.localeName = locale;
	}
}