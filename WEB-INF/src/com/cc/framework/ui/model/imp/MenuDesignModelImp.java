/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/imp/MenuDesignModelImp.java,v 1.13 2005/09/27 14:06:22 P001002 Exp $
 * $Revision: 1.13 $
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

import java.util.ArrayList;

import com.cc.framework.ui.ImageMap;
import com.cc.framework.ui.MenuType;
import com.cc.framework.ui.model.MenuDesignModel;
import com.cc.framework.ui.model.MenuItemDesignModel;

/**
 * Designmodel for the MenuControl
 *
 * @author	   <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version	   $Revision: 1.13 $
 * @since      1.0
 */
public class MenuDesignModelImp extends ControlDesignModelImp implements MenuDesignModel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -7274625875566460246L;

	/**
	 * The Menu Id
	 */
	private String menuid = null;

	/**
	 * Buffer with the sub menu items
	 */
	private ArrayList items	= new ArrayList();

	/**
	 * The type of the menu
	 */
	private MenuType type = MenuType.NONE;

	/**
	 * An ImageMap with images if the
	 * menu items are made of images.
	 */
	private ImageMap imagemap = null;

	// -------------------------------
	//         Methods
	// -------------------------------

	/**
	 * Constructor fro MenuDesignModelImp
	 */
	public MenuDesignModelImp() {
		super();
	}

	/**
	 * @see com.cc.framework.ui.model.MenuDesignModel#addItem(com.cc.framework.ui.model.MenuItemDesignModel)
	 */
	public MenuDesignModel addItem(MenuItemDesignModel item) {

		synchronized (items) {
			items.add(item);

			return this;
		}
	}

	/**
	 * @see com.cc.framework.ui.model.MenuDesignModel#removeItem(com.cc.framework.ui.model.MenuItemDesignModel)
	 */
	public MenuDesignModel removeItem(MenuItemDesignModel item) {

		synchronized (items) {
			items.remove(item);

			return this;
		}
	}

	/**
	 * @see com.cc.framework.ui.model.MenuDesignModel#getContent()
	 */
	public MenuItemDesignModel[] getContent() {

		synchronized (items) {
			MenuItemDesignModel[] list = new MenuItemDesignModel[items.size()];

			return ((MenuItemDesignModel[]) items.toArray(list));
		}
	}

	/**
	 * @see com.cc.framework.ui.model.MenuDesignModel#size()
	 */
	public int size() {
		return items.size();
	}

	/**
	 * @see com.cc.framework.ui.model.MenuDesignModel#getType()
	 */
	public MenuType getType() {
		return type;
	}

	/**
	 * @see com.cc.framework.ui.model.MenuDesignModel#setType(com.cc.framework.ui.MenuType)
	 */
	public void setType(MenuType type) {
		this.type = type;
	}

	/**
	 * @see com.cc.framework.ui.model.MenuDesignModel#getImageMap()
	 */
	public ImageMap getImageMap() {
		return imagemap;
	}

	/**
	 * @see com.cc.framework.ui.model.MenuDesignModel#setImageMap(com.cc.framework.ui.ImageMap)
	 */
	public void setImageMap(ImageMap imagemap) {
		this.imagemap = imagemap;
	}

	/**
	 * @see com.cc.framework.ui.model.MenuDesignModel#getMenuId()
	 */
	public String getMenuId() {
		return menuid;
	}

	/**
	 * @see com.cc.framework.ui.model.MenuDesignModel#setMenuId(java.lang.String)
	 */
	public void setMenuId(String menuid) {
		this.menuid = menuid;
	}
}