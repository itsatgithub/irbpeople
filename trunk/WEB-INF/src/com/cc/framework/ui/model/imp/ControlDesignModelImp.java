/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/imp/ControlDesignModelImp.java,v 1.24 2005/07/12 16:07:39 P001002 Exp $
 * $Revision: 1.24 $
 * $Date: 2005/07/12 16:07:39 $
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
import com.cc.framework.ui.RunAt;
import com.cc.framework.ui.model.ControlDesignModel;
import com.cc.framework.ui.model.DesignRule;

/**
 * Base Class for all control designmodels
 *
 * @author      <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version     $Revision: 1.24 $
 * @since       1.0
 */
public abstract class ControlDesignModelImp extends ClientHandlerImp implements ControlDesignModel, Serializable {

	/**
	 * Indicates if the Designmodel was created dynamicly in the JSP-Page.
	 * Dynamic created Designmodels can be overriden by a server Roundtrip
	 */
	private boolean dynamic				= false;

	/**
	 * Indicates where to execute the Eventhandler.
	 */
	private RunAt runat					= RunAt.DEFAULT;

	/**
	 * The name of the Bean unsed for the Control
	 */
	private String name					= null;

	/**
	 * Field property
	 */
	private String property				= null;

	/**
	 * The Action assigned with the Control
	 */
	private String action				= null;

	/**
	 * Unique Id
	 */
	private String id					= null;

	/**
	 * Height of the Control
	 */
	private String height				= null;

	/**
	 * Width of the Control
	 */
	private String width				= null;

	/**
	 * Border which should be drawn
	 * around the Control
	 */
	private int border					= 0;

	/**
	 * Indicates if the Control is deactivated
	 */
	private boolean disabled			= false;

	/**
	 * Indicates if the Control should render a shaddow
	 */
	private boolean shaddow				= false;

	/**
	 * The Permission for the Control
	 */
	private Permission permission		= StaticPermission.GRANTED;

	/**
	 * Style ID
	 */
	private String styleId				= null;

	/**
	 * Style Class -> HTML class Attribut
	 */
	private String styleClass			= null;

	/**
	 * Html-Style
	 */
	private String style				= null;

	/**
	 * The Help Identifier for this control
	 */
	private String help					= null;

	/**
	 * Tooltip
	 */
	private String tooltip				= null;

	/**
	 * specifies a description and/or structure of the object
	 * That can be used for rendering to non-visual media such as
	 * speech or Braille.
	 */
	private String summary				= null;

	/**
	 * Tabulator Index
	 */
	private int tabIndex				= -1;

	/**
	 * Indicates if the control always should
	 * submit the form
	 */
	private boolean formElement			= false;

	/**
	 * Locale Setting
	 */
	private String localeName			= null;

	/**
	 * Design rules
	 */
	private ArrayList designRules		= null;

    /**
     * Directs the framework to include a transaction token (if any)
     * in all generated hyperlinks for this column. The Transaction
     * token is used to track form re-submissions.
     */
	private boolean transaction			= false;

	// ------------------------------------------------
    //                Methods
    // ------------------------------------------------

	/**
	 * Constructor
	 */
	public ControlDesignModelImp() {
		super();
	}

	/**
	 * @see com.cc.framework.ui.model.ControlDesignModel#isDynamicDesignModel()
	 */
	public boolean isDynamicDesignModel() {
		return dynamic;
	}

	/**
	 * @see com.cc.framework.ui.model.ControlDesignModel#setDynamicDesignModel(boolean)
	 */
	public void setDynamicDesignModel(boolean dynamic) {
		this.dynamic = dynamic;
	}

	/**
	 * @see com.cc.framework.ui.model.ControlDesignModel#getHeight()
	 */
	public String getHeight() {
		return height;
	}

	/**
	 * @see com.cc.framework.ui.model.ControlDesignModel#getId()
	 */
	public String getId() {
		return id;
	}

	/**
	 * @see com.cc.framework.ui.model.ControlDesignModel#getWidth()
	 */
	public String getWidth() {
		return width;
	}

	/**
	 * @see com.cc.framework.ui.model.ControlDesignModel#setHeight(String height)
	 */
	public void setHeight(String height) {
		this.height	= height;
	}

	/**
	 * @see com.cc.framework.ui.model.ControlDesignModel#setId(String id)
	 */
	public void setId(String id) {
		this.id	= id;
	}

	/**
	 * @see com.cc.framework.ui.model.ControlDesignModel#setWidth(String width)
	 */
	public void setWidth(String width) {
		this.width	= width;
	}

	/**
	 * @see com.cc.framework.ui.model.ControlDesignModel#setAction(String)
	 */
	public void setAction(String action) {
		this.action	= action;
	}

	/**
	 * @see com.cc.framework.ui.model.ControlDesignModel#getAction()
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @see com.cc.framework.ui.model.ControlDesignModel#getName()
	 */
	public String getName() {
		return name;
	}

	/**
	 * @see com.cc.framework.ui.model.ControlDesignModel#setName(java.lang.String)
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @see com.cc.framework.ui.model.ControlDesignModel#getProperty()
	 */
	public String getProperty() {
		return property;
	}

	/**
	 * @see com.cc.framework.ui.model.ControlDesignModel#setProperty(String)
	 */
	public void setProperty(String property) {
		this.property	= property;
	}

	/**
	 * @see com.cc.framework.ui.model.ControlDesignModel#getBorder()
	 */
	public int getBorder() {
		return border;
	}

	/**
	 * @see com.cc.framework.ui.model.ControlDesignModel#setBorder(int)
	 */
	public void setBorder(int border) {
		this.border	= border;
	}

	/**
	 * @see com.cc.framework.ui.model.ControlDesignModel#hasShaddow()
	 */
	public boolean hasShaddow() {
		return shaddow;
	}

	/**
	 * @see com.cc.framework.ui.model.ControlDesignModel#setShaddow(boolean)
	 */
	public void setShaddow(boolean shaddow) {
		this.shaddow	= shaddow;
	}

	/**
	 * @see com.cc.framework.ui.model.AccessControlled#getPermission()
	 */
	public Permission getPermission() {
		return permission;
	}
	/**
	 * @see com.cc.framework.ui.model.ControlDesignModel#setPermission(com.cc.framework.security.Permission)
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
	 * @see com.cc.framework.ui.model.ControlDesignModel#setRunAt(RunAt)
	 */
	public void setRunAt(RunAt location) {
		runat = location;
	}

	/**
	 * @see com.cc.framework.ui.model.ControlDesignModel#getRunAt()
	 */
	public RunAt getRunAt() {
		return runat;
	}

	/**
	 * @see com.cc.framework.ui.model.ControlDesignModel#setFormElement(boolean)
	 */
	public void setFormElement(boolean flag) {
		this.formElement = flag;
	}

	/**
	 * @see com.cc.framework.ui.model.ControlDesignModel#isFormElement()
	 */
	public boolean isFormElement() {
		return formElement;
	}

	/**
	 * @see com.cc.framework.ui.model.ControlDesignModel#getStyle()
	 */
	public String getStyle() {
		return style;
	}

	/**
	 * @see com.cc.framework.ui.model.ControlDesignModel#getStyleId()
	 */
	public String getStyleId() {
		return styleId;
	}

	/**
	 * @see com.cc.framework.ui.model.ControlDesignModel#setStyle(String style)
	 */
	public void setStyle(String style) {
		this.style = style;
	}

	/**
	 * @see com.cc.framework.ui.model.ControlDesignModel#setStyleId(String styleId)
	 */
	public void setStyleId(String styleId) {
		this.styleId = styleId;
	}

	/**
	 * @see com.cc.framework.ui.model.ControlDesignModel#getHelp()
	 */
	public String getHelp() {
		return help;
	}

	/**
	 * @see com.cc.framework.ui.model.ControlDesignModel#setHelp(java.lang.String)
	 */
	public void setHelp(String helpId) {
		this.help = helpId;
	}

	/**
	 * @see com.cc.framework.ui.model.ControlDesignModel#isDisabled()
	 */
	public boolean isDisabled() {
		return disabled;
	}

	/**
	 * @see com.cc.framework.ui.model.ControlDesignModel#setDisabled(boolean disabled)
	 */
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	/**
	 * @see com.cc.framework.ui.model.ControlDesignModel#getStyleClass()
	 */
	public String getStyleClass() {
		return styleClass;
	}

	/**
	 * @see com.cc.framework.ui.model.ControlDesignModel#setStyleClass(String styleClass)
	 */
	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	/**
	 * @see com.cc.framework.ui.model.ControlDesignModel#getTabIndex()
	 */
	public int getTabIndex() {
		return tabIndex;
	}

	/**
	 * @see com.cc.framework.ui.model.ControlDesignModel#setTabIndex(int)
	 */
	public void setTabIndex(int tabIndex) {
		this.tabIndex = tabIndex;
	}

	/**
	 * Sets the optional tooltip text
	 *
	 * @param	tooltip	Tooltip
	 */
	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

	/**
	 * Retrieves the optional tooltip text
	 *
	 * @return	tooltip text or <code>null</code>
	 */
	public String getTooltip() {
		return tooltip;
	}

	/**
	 * @see com.cc.framework.ui.model.ControlDesignModel#getSummary()
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * @see com.cc.framework.ui.model.ControlDesignModel#setSummary(java.lang.String)
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}

	/**
	 * @see com.cc.framework.ui.model.ControlDesignModel#getTransaction()
	 */
	public boolean getTransaction() {
		return transaction;
	}

	/**
	 * @see com.cc.framework.ui.model.ControlDesignModel#setTransaction(boolean)
	 */
	public void setTransaction(boolean transaction) {
		this.transaction = transaction;
	}

	/**
	 * @see com.cc.framework.ui.model.ControlDesignModel#setLocaleName(java.lang.String)
	 */
	public void setLocaleName(String locale) {
		this.localeName = locale;
	}

	/**
	 * @see com.cc.framework.ui.model.ControlDesignModel#getLocaleName()
	 */
	public String getLocaleName() {
		return localeName;
	}

	/**
	 * @see com.cc.framework.ui.model.ControlDesignModel#addDesignRule(com.cc.framework.ui.model.DesignRule)
	 */
	public void addDesignRule(DesignRule rule) {
		if (designRules == null) {
			designRules = new ArrayList();
		}

		designRules.add(rule);
	}

	/**
	 * @see com.cc.framework.ui.model.ControlDesignModel#getDesignRules()
	 */
	public DesignRule[] getDesignRules() {
		if (designRules == null) {
			return new DesignRule[0];
		} else {
			return (DesignRule[]) designRules.toArray(
				new DesignRule[designRules.size()]);
		}
	}
}