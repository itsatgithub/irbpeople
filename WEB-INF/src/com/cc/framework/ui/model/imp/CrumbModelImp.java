/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/imp/CrumbModelImp.java,v 1.10 2005/07/08 15:15:32 P001002 Exp $
 * $Revision: 1.10 $
 * $Date: 2005/07/08 15:15:32 $
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

import com.cc.framework.security.Permission;
import com.cc.framework.security.Principal;
import com.cc.framework.security.StaticPermission;
import com.cc.framework.ui.model.CrumbModel;

/**
 * Designmodel for the crumbs of a CrumbsControl
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.10 $
 * @since      1.3
 */
public class CrumbModelImp extends ClientHandlerImp implements CrumbModel, Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -6317050182751594361L;

	/**
	 * The unique id of the crumb
	 */
	private String crumbid;

	/**
	 * The action which should be processed
	 * if the crumb is activated.
	 */
	private String action;

	/**
	 * The title of the crumb
	 */
	private String title;

	/**
	 * A tooltip which is displayed if the
	 * user moves the mouse over the label/title.
	 */
	private String tooltip;

	/**
	 * Reference to an image in the ImageMap
	 * of the CrumbControl which should be
	 * displayed on the crumb in front of the label.
	 */
	private String imageRef = null;

	/**
	 * This flag indicates if the crumb is disabled
	 */
	private boolean disabled = false;

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
	 * The Permission for the crumb
	 */
	private Permission permission = StaticPermission.GRANTED;

	// ----------------------------
	//       methods
	// ----------------------------

	/**
	 * Constructor for CrumbDesignModelImp
	 */
	public CrumbModelImp() {
		super();
	}

	/**
	 * @see com.cc.framework.ui.model.CrumbModel#getTitle()
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @see com.cc.framework.ui.model.CrumbModel#setTitle(java.lang.String)
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @see com.cc.framework.ui.model.CrumbModel#setTooltip(java.lang.String)
	 */
	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

	/**
	 * @see com.cc.framework.ui.model.CrumbModel#getTooltip()
	 */
	public String getTooltip() {
		return tooltip;
	}

	/**
	 * @see com.cc.framework.ui.model.CrumbModel#getCrumbId()
	 */
	public String getCrumbId() {
		return crumbid;
	}

	/**
	 * @see com.cc.framework.ui.model.CrumbModel#setCrumbId(java.lang.String)
	 */
	public void setCrumbId(String crumbid) {
		this.crumbid = crumbid;
	}

	/**
	 * @see com.cc.framework.ui.model.CrumbModel#getImageRef()
	 */
	public String getImageRef() {
		return imageRef;
	}

	/**
	 * @see com.cc.framework.ui.model.CrumbModel#setImageRef(java.lang.String)
	 */
	public void setImageRef(String imageRef) {
		this.imageRef = imageRef;
	}

	/**
	 * @see com.cc.framework.ui.model.CrumbModel#isDisabled()
	 */
	public boolean isDisabled() {
		return disabled;
	}

	/**
	 * @see com.cc.framework.ui.model.CrumbModel#setDisabled(boolean)
	 */
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	/**
	 * @see com.cc.framework.ui.model.CrumbModel#getAction()
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @see com.cc.framework.ui.model.CrumbModel#setAction(java.lang.String)
	 */
	public void setAction(String string) {
		action = string;
	}

	/**
	 * @see com.cc.framework.ui.model.CrumbModel#filter()
	 */
	public Boolean filter() {
		return filter;
	}

	/**
	 * @see com.cc.framework.ui.model.CrumbModel#setFilter(boolean)
	 */
	public void setFilter(boolean filter) {
		this.filter = filter ? Boolean.TRUE : Boolean.FALSE;
	}

	/**
	 * @see com.cc.framework.ui.model.CrumbModel#getLocaleName()
	 */
	public String getLocaleName() {
		return localeName;
	}

	/**
	 * @see com.cc.framework.ui.model.CrumbModel#setLocaleName(java.lang.String)
	 */
	public void setLocaleName(String locale) {
		this.localeName = locale;
	}

	/**
	 * @see com.cc.framework.ui.model.AccessControlled#getPermission()
	 */
	public Permission getPermission() {
		return permission;
	}

	/**
	 * @see com.cc.framework.ui.model.CrumbModel#setPermission(com.cc.framework.security.Permission)
	 */
	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	/**
	 * @see com.cc.framework.ui.model.AccessControlled#show(com.cc.framework.security.Principal)
	 */
	public boolean show(Principal principal) {
		return permission.isGranted(principal);
	}
}