/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/imp/TabDesignModelImp.java,v 1.17 2005/09/27 14:06:22 P001002 Exp $
 * $Revision: 1.17 $
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

import java.io.Serializable;

import com.cc.framework.security.Permission;
import com.cc.framework.security.Principal;
import com.cc.framework.security.StaticPermission;
import com.cc.framework.ui.Color;
import com.cc.framework.ui.model.TabDesignModel;

/**
 * Designmodel for the Tabs of a TabControl
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.17 $
 * @since      1.0
 */
public class TabDesignModelImp extends ClientHandlerImp implements TabDesignModel, Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -1610418457816895048L;

	/**
	 * The unique id of the Tab page
	 */
	private String tabid;

	/**
	 * The action which should be processed
	 * if the Tab is activated.
	 */
	private String action;

	/**
	 * The title of the Tab page
	 */
	private String title;

	/**
	 * A tooltip which is displayed if the
	 * user moves the mouse over the label/title.
	 */
	private String tooltip;

	/**
	 * True if the Tab can be selected; false otherwise
	 */
	private boolean enable = true;

	/**
	 * Name of a JSP page with the content of
	 * the Tab page to display.
	 */
	private String content = null;

	/**
	 * The HTML body which should be displayed
	 * on this Tab page. If you do not want to
	 * include HTML directly you can specify the
	 * name of a JSP page. In this case you do
	 * no set the body but the content.
	 */
	private String body;

	/**
	 * The background color of the Tab
	 */
	private Color bgcolor = null;

	/**
	 * Reference to an image in the ImageMap
	 * of the TabSet Control which should be
	 * displayed on the Tab in front of the label.
	 */
	private String imageRef = null;

	/**
	 * The Permission for the Control
	 */
	private Permission permission	= StaticPermission.GRANTED;

	/**
	 * Target Attribut
	 */
	private String target				= null;

	// ----------------------------
	//       methods
	// ----------------------------

	/**
	 * Constructor for TabDesignModelImp
	 */
	public TabDesignModelImp() {
		super();
	}

	/**
	 * @see com.cc.framework.ui.model.TabDesignModel#getTitle()
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @see com.cc.framework.ui.model.TabDesignModel#setTitle(String title)
	 */
	public void setTitle(String title) {
		this.title	= title;
	}

	/**
	 * @see com.cc.framework.ui.model.TabDesignModel#setTooltip(String)
	 */
	public void setTooltip(String tooltip) {
		this.tooltip	= tooltip;
	}

	/**
	 * @see com.cc.framework.ui.model.TabDesignModel#getTooltip()
	 */
	public String getTooltip() {
		return tooltip;
	}

	/**
	 * @see com.cc.framework.ui.model.TabDesignModel#getTabId()
	 */
	public String getTabId() {
		return tabid;
	}

	/**
	 * @see com.cc.framework.ui.model.TabDesignModel#setTabId(String)
	 */
	public void setTabId(String tabid) {
		this.tabid	= tabid;
	}

	/**
	 * @see com.cc.framework.ui.model.TabDesignModel#isEnabled()
	 */
	public boolean isEnabled() {
		return enable;
	}

	/**
	 * @see com.cc.framework.ui.model.TabDesignModel#setEnable(boolean)
	 */
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	/**
	 * @see com.cc.framework.ui.model.TabDesignModel#setBody(String)
	 */
	public void setBody(String body) {
		this.body = body;
	}
	/**
	 * @see com.cc.framework.ui.model.TabDesignModel#getBody()
	 */
	public String getBody() {
		return body;
	}

	/**
	 * @see com.cc.framework.ui.model.TabDesignModel#getContent()
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @see com.cc.framework.ui.model.TabDesignModel#setContent(java.lang.String)
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @see com.cc.framework.ui.model.TabDesignModel#getBgColor()
	 */
	public Color getBgColor() {
		return bgcolor;
	}

	/**
	 * @see com.cc.framework.ui.model.TabDesignModel#setBgColor(com.cc.framework.ui.Color)
	 */
	public void setBgColor(Color bgcolor) {
		this.bgcolor = bgcolor;
	}

	/**
	 * @see com.cc.framework.ui.model.TabDesignModel#getImageRef()
	 */
	public String getImageRef() {
		return imageRef;
	}

	/**
	 * @see com.cc.framework.ui.model.TabDesignModel#setImageRef(java.lang.String)
	 */
	public void setImageRef(String imageRef) {
		this.imageRef = imageRef;
	}

	/**
	 * @see com.cc.framework.ui.model.TabDesignModel#getAction()
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @see com.cc.framework.ui.model.TabDesignModel#setAction(java.lang.String)
	 */
	public void setAction(String string) {
		action = string;
	}

	/**
	 * @see com.cc.framework.ui.model.AccessControlled#getPermission()
	 */
	public Permission getPermission() {
		return permission;
	}

	/**
	 * @see com.cc.framework.ui.model.TabDesignModel#setPermission(com.cc.framework.security.Permission)
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
	 * @see com.cc.framework.ui.model.TabDesignModel#getTarget()
	 */
	public String getTarget() {
		return target;
	}

	/**
	 * @see com.cc.framework.ui.model.TabDesignModel#setTarget(java.lang.String)
	 */
	public void setTarget(String target) {
		this.target = target;
	}
}