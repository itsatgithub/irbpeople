/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/imp/PanelItemDesignModelImp.java,v 1.16 2005/07/08 15:15:31 P001002 Exp $
 * $Revision: 1.16 $
 * $Date: 2005/07/08 15:15:31 $
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

import com.cc.framework.security.Permission;
import com.cc.framework.security.Principal;
import com.cc.framework.security.StaticPermission;
import com.cc.framework.ui.model.PanelItemDesignModel;

/**
 * Designmodel for a PanelItem
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.16 $
 * @since      1.0
 */
public class PanelItemDesignModelImp extends ClientHandlerImp implements PanelItemDesignModel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -8067210016541338129L;

	/**
	 * The title of the PanelItem
	 */
	private String title = null;

	/**
	 * The detail
	 */
	private String detail = null;

	/**
	 * The action
	 */
	private String action = null;

	/**
	 * The tooltip
	 */
	private String tooltip = null;

	/**
	 * The target
	 */
	private String target = null;

	/**
	 * Specifies if all String should be converted
	 * into there HTML representation
	 */
	private Boolean filter = null;

	/**
	 * Locale Setting
	 */
	private String localeName = null;

	/**
	 * The permission for the PanelItem
	 */
	private Permission permission = StaticPermission.GRANTED;

	// ------------------------------------
	//           methods
	// ------------------------------------

	/**
	 * Constructor
	 */
	public PanelItemDesignModelImp() {
		super();
	}

	/**
	 * @see com.cc.framework.ui.model.PanelItemDesignModel#getDetail()
	 */
	public String getDetail() {
		return detail;
	}

	/**
	 * @see com.cc.framework.ui.model.PanelItemDesignModel#getTitle()
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @see com.cc.framework.ui.model.PanelItemDesignModel#setDetail(String detail)
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}

	/**
	 * @see com.cc.framework.ui.model.PanelItemDesignModel#setTitle(String title)
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @see com.cc.framework.ui.model.PanelItemDesignModel#getAction()
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @see com.cc.framework.ui.model.PanelItemDesignModel#setAction(String)
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * @see com.cc.framework.ui.model.PanelItemDesignModel#setTooltip(String)
	 */
	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

	/**
	 * @see com.cc.framework.ui.model.PanelItemDesignModel#getTooltip()
	 */
	public String getTooltip() {
		return tooltip;
	}

	/**
	 * @see com.cc.framework.ui.model.AccessControlled#getPermission()
	 */
	public Permission getPermission() {
		return permission;
	}

	/**
	 * @see com.cc.framework.ui.model.PanelItemDesignModel#setPermission(com.cc.framework.security.Permission)
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
	 * @see com.cc.framework.ui.model.PanelItemDesignModel#getTarget()
	 */
	public String getTarget() {
		return target;
	}

	/**
	 * @see com.cc.framework.ui.model.PanelItemDesignModel#setTarget(java.lang.String)
	 */
	public void setTarget(String target) {
		this.target = target;
	}

	/**
	 * @see com.cc.framework.ui.model.PanelItemDesignModel#filter()
	 */
	public Boolean filter() {
		return filter;
	}

	/**
	 * @see com.cc.framework.ui.model.PanelItemDesignModel#setFilter(boolean)
	 */
	public void setFilter(boolean filter) {
		this.filter = filter ? Boolean.TRUE : Boolean.FALSE;
	}

	/**
	 * @see com.cc.framework.ui.model.PanelItemDesignModel#getLocaleName()
	 */
	public String getLocaleName() {
		return localeName;
	}

	/**
	 * @see com.cc.framework.ui.model.PanelItemDesignModel#setLocaleName(java.lang.String)
	 */
	public void setLocaleName(String locale) {
		this.localeName = locale;
	}
}