/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/MenuItemDesignModel.java,v 1.12 2005/03/05 15:40:37 P001002 Exp $
 * $Revision: 1.12 $
 * $Date: 2005/03/05 15:40:37 $
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
import com.cc.framework.ui.MenuStateType;

/**
 * Designmodel for the MenuItem 
 *
 * @author <a href="mailto:gschulz@scc-gmbh.com">Gernot Schulz</a>
 * @version $Revision: 1.12 $
 */
public interface MenuItemDesignModel extends DesignModel, ClientHandler, AccessControlled {

	/**
	 * Returns a referenz to the parent element
	 * 
	 * @return	MenuItemDesignModel
	 */
	public MenuItemDesignModel getParent();

	/**
	 * Sets the referenz to the parent element
	 * 
	 * @param	parent	MenuItemDesignModel
	 */
	public void setParent(MenuItemDesignModel parent);

	/**
	 * Sets the Menu Id
	 * @param	menuid	menuid id
	 */
	public void setMenuId(String menuid);

	/**
	 * Sets the Action
	 * @param	action	Action
	 */
	public void setAction(String action);

	/**
	 * Sets the Menu State
	 * @param	state	MenuStateType
	 */
	public void setState(MenuStateType state);

	/**
	 * sets the Text
	 * @param	text	Text
	 */
	public void setText(String text);

	/**
	 * Sets the Shortcut
	 * @param shortcut String
	 */
	public void setShortcut(String shortcut);

	/**
	 * Sets the Width
	 * @param	width	Width
	 */
	public void setWidth(String width);

	/**
	 * Sets the Target
	 * @param	target	Target
	 */
	public void setTarget(String target);

	/**
	 * Sets the menu Filter
	 * @param filter	Filter
	 */
	public void setMenuFilter(String filter);

	/**
	 * Assigns an Image from an Imagemap to the MenuItem 
	 * @param	ref	ImageRef
	 */
	public void setImageRef(String ref);

	/**
	 * Returns the Menu Id
	 * @return String
	 */
	public String getMenuId();

	/**
	 * Returns the Action
	 * @return String
	 */
	public String getAction();

	/**
	 * Returns the Menu State
	 * @return 	MenuStateType
	 */
	public MenuStateType getState();

	/**
	 * Returns the Text
	 * @return String
	 */
	public String getText();

	/**
	 * Returns the Shortcut
	 * @return	String
	 */
	public String getShortcut();

	/**
	 * Returns the Width
	 * @return	String
	 */
	public String getWidth();

	/**
	 * Returns the Target
	 * @return	String
	 */
	public String getTarget();

	/**
	 * Returns the menu Filter
	 * @return	String
	 */
	public String getMenuFilter();

	/**
	 * Sets the Locale configuration for this element
	 * 
	 * @param		locale Locale Identifier or <code>true|false</code>
	 */
	public void setLocaleName(String locale);

	/**
	 * Gets the Local Setting for this element
	 * 
	 * @return		Locale Setting
	 */
	public String getLocaleName();

	/**
	 * Returns the Role which specifies an Image
	 * in an ImageMap
	 * @return	String
	 */
	public String getImageRef();

	/**
	 * Adds a SubMenuItem to the MenuItem
	 * @param	item	MenuItemDesignModel
	 * @return	MenuItemDesignModel
	 */
	public MenuItemDesignModel addItem(MenuItemDesignModel item);

	/**
	 * Removes a SubMenuItem from the List
	 * @param	item	MenuItemDesignModel
	 * @return	MenuItemDesignModel
	 */
	public MenuItemDesignModel removeItem(MenuItemDesignModel item);

	/**
	 * Returns a List with all SubMenuItems
	 * @return	MenuItemDesignModel	
	 */
	public MenuItemDesignModel[] getContent();

	/**
	 * Returns the Number of SubMenuItems
	 * @return	int
	 */
	public int size();

	/**
	 * Sets a list with permissions needed to access this object.
	 * The list is seperated by ';'
	 * 
	 * @param		permission	Permission
	 */
	public void setPermission(Permission permission);

	/**
	 * Sets the static tooltip text.
	 *
	 * @param	tooltip Tooltip text
	 */
	public void setTooltip(String tooltip);

	/**
	 * Returns the static tooltip text
	 * @return	String
	 */
	public String getTooltip();
}