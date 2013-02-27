/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/imp/PanelContentDesignModelImp.java,v 1.14 2005/11/13 12:45:45 P001002 Exp $
 * $Revision: 1.14 $
 * $Date: 2005/11/13 12:45:45 $
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

import java.io.Serializable;
import java.util.ArrayList;

import com.cc.framework.security.Permission;
import com.cc.framework.security.Principal;
import com.cc.framework.security.StaticPermission;
import com.cc.framework.ui.model.ImageModel;
import com.cc.framework.ui.model.PanelContentDesignModel;
import com.cc.framework.ui.model.PanelItemDesignModel;

/**
 * Designmoel for the panel content
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.14 $
 * @since      1.0
 */
public class PanelContentDesignModelImp implements PanelContentDesignModel, Serializable {

	/**
	 * Serial Verion UID
	 */
	private static final long serialVersionUID = 6328034989480771646L;

	/**
	 * The title
	 */
	private String title = null;

	/**
	 * Field more
	 */
	private String more = null;

	/**
	 * The image to be displayed in the Panel
	 */
	private ImageModel image = null;

	/**
	 * The action
	 */
	private String action = null;

	/**
	 * The tooltip
	 */
	private String tooltip = null;

	/**
	 * Specifies if all String should be converted
	 * into there HTML representation
	 */
	private boolean filter = true;

	/**
	 * Locale Setting
	 */
	private String localeName = null;

	/**
	 * Buffer for the items
	 */
	private ArrayList items = new ArrayList();

	/**
	 * Permission
	 */
	private Permission permission = StaticPermission.GRANTED;

	/**
	 * Constructor
	 */
	public PanelContentDesignModelImp() {
		super();
	}

	/**
	 * @see com.cc.framework.ui.model.PanelContentDesignModel#addItem(PanelItemDesignModel)
	 */
	public void addItem(PanelItemDesignModel newItem) {

		synchronized (items) {
			items.add(newItem);
		}
	}

	/**
	 * @see com.cc.framework.ui.model.PanelContentDesignModel#removeItem(PanelItemDesignModel)
	 */
	public void removeItem(PanelItemDesignModel item) {

		synchronized (items) {
			items.remove(item);
		}
	}

	/**
	 * @see com.cc.framework.ui.model.PanelContentDesignModel#getItems()
	 */
	public PanelItemDesignModel[] getItems() {

		synchronized (items) {
			PanelItemDesignModel[] list =
				new PanelItemDesignModel[items.size()];

			return ((PanelItemDesignModel[]) items.toArray(list));
		}
	}

	/**
	 * @see com.cc.framework.ui.model.PanelContentDesignModel#size()
	 */
	public int size() {
		return items.size();
	}

	/**
	 * @see com.cc.framework.ui.model.PanelContentDesignModel#getMore()
	 */
	public String getMore() {
		return more;
	}

	/**
	 * @see com.cc.framework.ui.model.PanelContentDesignModel#getTitle()
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @see com.cc.framework.ui.model.PanelContentDesignModel#setImage(ImageModel image)
	 */
	public void setImage(ImageModel image) {
		this.image = image;
	}

	/**
	 * @see com.cc.framework.ui.model.PanelContentDesignModel#setMore(java.lang.String)
	 */
	public void setMore(String newMore) {
		more = newMore;
	}

	/**
	 * @see com.cc.framework.ui.model.PanelContentDesignModel#setTitle(String title)
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @see com.cc.framework.ui.model.PanelContentDesignModel#getImage()
	 */
	public ImageModel getImage() {
		return image;
	}

	/**
	 * @see com.cc.framework.ui.model.PanelContentDesignModel#setAction(String)
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * @see com.cc.framework.ui.model.PanelContentDesignModel#getAction()
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @see com.cc.framework.ui.model.AccessControlled#getPermission()
	 */
	public Permission getPermission() {
		return permission;
	}

	/**
	 * @see com.cc.framework.ui.model.PanelContentDesignModel#setPermission(com.cc.framework.security.Permission)
	 */
	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	/**
	 * @see com.cc.framework.ui.model.PanelContentDesignModel#setTooltip(java.lang.String)
	 */
	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

	/**
	 * @see com.cc.framework.ui.model.PanelContentDesignModel#getTooltip()
	 */
	public String getTooltip() {
		return tooltip;
	}

	/**
	 * @see com.cc.framework.ui.model.PanelContentDesignModel#filter()
	 */
	public boolean filter() {
		return filter;
	}

	/**
	 * @see com.cc.framework.ui.model.PanelContentDesignModel#setFilter(boolean)
	 */
	public void setFilter(boolean filter) {
		this.filter = filter;
	}

	/**
	 * @see com.cc.framework.ui.model.PanelContentDesignModel#getLocaleName()
	 */
	public String getLocaleName() {
		return localeName;
	}

	/**
	 * @see com.cc.framework.ui.model.PanelContentDesignModel#setLocaleName(java.lang.String)
	 */
	public void setLocaleName(String locale) {
		this.localeName = locale;
	}

	/**
	 * @see com.cc.framework.ui.model.AccessControlled#show(Principal)
	 */
	public boolean show(Principal principal) {
		return permission.isGranted(principal);
	}
}
