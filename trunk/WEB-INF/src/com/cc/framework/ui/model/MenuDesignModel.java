/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/MenuDesignModel.java,v 1.7 2005/02/16 18:13:32 P001001 Exp $
 * $Revision: 1.7 $
 * $Date: 2005/02/16 18:13:32 $
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


import com.cc.framework.ui.ImageMap;
import com.cc.framework.ui.MenuType;

/**
 * DesignModel for the MenuControl
 *
 * @author    <a href="mailto:hschulz@scc-gmbh.com">Gernot Schulz</a>
 * @version   $Revision: 1.7 $
 * @since     1.0
 */
public interface MenuDesignModel extends ControlDesignModel {

	/**
	 * Sets the MenuType
	 * @param type	MenuType
	 */
	public void setType(MenuType type);

	/**
	 * Sets the Menu Id
	 * @param	menuid	Id
	 */
	public void setMenuId(String menuid);

	/**
	 * Sets the Action
	 * @param	action	Action
	 */
	public void setAction(String action);

	/**
	 * Sets the Width of the Menu
	 * @param	width	Width
	 */
	public void setWidth(String width);

	/**
	 * Sets the Height of the Menu
	 * @param	height	Height
	 */
	public void setHeight(String height);

	/**
	 * Assigns an ImageMap to the Menu
	 * @param	map		ImageMap
	 */
	public void setImageMap(ImageMap map);
	
	/**
	 * Returns the MenuType 
	 * @return MenuType
	 */
	public MenuType getType();

	/**
	 * Returns the Id
	 * @return	String
	 */
	public String getMenuId();

	/**
	 * Returns the Action
	 * @return	String
	 */
	public String getAction();

	/**
	 * Returns the Width
	 * @return	String
	 */
	public String getWidth();

	/**
	 * Returns the Height
	 * @return	String
	 */
	public String getHeight();

	/**
	 * Returns the ImageMap used by the Menu
	 * @return	ImageMap
	 */
	public ImageMap getImageMap();

	/**
	 * Adds an Item to the Menu
	 * @param	item	MenuItemDesignModel
	 * @return	MenuDesignModel
	 */
	public MenuDesignModel addItem(MenuItemDesignModel item);

	/**
	 * Removes a MenuItem
	 * @param	item	MenuItemDesignModel
	 * @return	MenuDesignModel
	 */
	public MenuDesignModel removeItem(MenuItemDesignModel item);

	/**
	 * Returns an Array with the MenuItems
	 * @return	MenuItemDesignModel
	 */
	public MenuItemDesignModel[] getContent();

	/**
	 * Returns the Number of MenuItems
	 * @return int	
	 */
	public int size();

}

