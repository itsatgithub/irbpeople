/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/ControlDesignModel.java,v 1.16 2005/09/27 14:06:22 P001002 Exp $
 * $Revision: 1.16 $
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

package com.cc.framework.ui.model;

import com.cc.framework.security.Permission;
import com.cc.framework.ui.RunAt;

/**
 * Class ControlDesignModel
 * 
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version $Revision: 1.16 $
 */
public interface ControlDesignModel extends DesignModel, ClientHandler,
		AccessControlled {

	/**
	 * Checks if the designmodel was declared within the jsp or (design time) it
	 * was predetermined by the programmer (run time).
	 * 
	 * @return true - The designmodel was set within a jsp page false - The
	 *         designmodel was set by the programmer (server side)
	 */
	public boolean isDynamicDesignModel();

	/**
	 * Sets if the designmodel was declared within the jsp or (design time) it
	 * was predetermined by the programmer (run time). The framework will use
	 * this information to detect if a designmodel can be created new if a
	 * server roundtrip occurred and if the server side designmodel should not
	 * be changed.
	 * 
	 * @param dynamic
	 *            <code>true</code> if the designmodel was declared within the
	 *            jsp page.
	 */
	public void setDynamicDesignModel(boolean dynamic);

	/**
	 * Specified if the control should work with or without serverroundtrips
	 * 
	 * @param location
	 *            Ausführungsort.
	 */
	public void setRunAt(RunAt location);

	/**
	 * Returns if the control works with or without serverroundtrips.
	 * 
	 * @return RunAt
	 */
	public RunAt getRunAt();

	/**
	 * Defines if the control should act like a form element.
	 * 
	 * @param flag
	 *            <code>true</code> if the control should act like a form
	 *            element.
	 */
	public void setFormElement(boolean flag);

	/**
	 * Checks if the control should act like a form element. In this case all
	 * action on the control must result in a submition of the enclosing form.
	 * This means that the data which is required to detected the actual action
	 * on the control (drilldown, edit, ...) must be sumit as form data and can
	 * not attached to the url.
	 * 
	 * @return <code>true</code> if the control acts like a form element.
	 */
	public boolean isFormElement();

	/**
	 * Returns the name of the element (bean)
	 * 
	 * @return String
	 */
	public String getName();

	/**
	 * Sets the name of the element (bean)
	 * 
	 * @param name
	 *            Name
	 */
	public void setName(String name);

	/**
	 * Returns the Propety Attribute
	 * 
	 * @return String
	 */
	public String getProperty();

	/**
	 * Sets the Property
	 * 
	 * @param property
	 *            Propertyname
	 */
	public void setProperty(String property);

	/**
	 * Sets the action, which should be performed, if an event of a control
	 * occur.
	 * 
	 * @param action
	 *            Action
	 */
	public void setAction(String action);

	/**
	 * Returns the action which is assigned to the control
	 * 
	 * @return String
	 */
	public String getAction();

	/**
	 * Returns the height of the control
	 * 
	 * @return String
	 */
	public String getHeight();

	/**
	 * Returns the id of the control
	 * 
	 * @return String
	 */
	public String getId();

	/**
	 * Returns the width of the control
	 * 
	 * @return String
	 */
	public String getWidth();

	/**
	 * Sets the height of the control
	 * 
	 * @param height
	 *            Height
	 */
	public void setHeight(String height);

	/**
	 * Sets the unique Id for the Control
	 * 
	 * @param id
	 *            Id
	 */
	public void setId(String id);

	/**
	 * Sets the Width of the Control
	 * 
	 * @param width
	 *            Width
	 */
	public void setWidth(String width);

	/**
	 * Returns the border width
	 * 
	 * @return int
	 */
	public int getBorder();

	/**
	 * Sets the border width
	 * 
	 * @param border
	 *            Border
	 */
	public void setBorder(int border);

	/**
	 * Returns if the control uses a shadow
	 * 
	 * @return boolean
	 */
	public boolean hasShaddow();

	/**
	 * Sets the shadow for a control
	 * 
	 * @param shaddow
	 *            <code>true</code> if the control should render a shadow
	 */
	public void setShaddow(boolean shaddow);

	/**
	 * Sets the disabled Flag
	 * 
	 * @param disabled
	 *            <code>true</code> if the control should be rendered as
	 *            inactiv
	 */
	public void setDisabled(boolean disabled);

	/**
	 * Returns the disabled Flag
	 * 
	 * @return boolean
	 */
	public boolean isDisabled();

	/**
	 * Sets the Style
	 * 
	 * @param style
	 *            Stil Konstante
	 */
	public void setStyle(String style);

	/**
	 * Returns the Style
	 * 
	 * @return String
	 */
	public String getStyle();

	/**
	 * Sets the StyleId
	 * 
	 * @param styleId
	 *            HTML-Id des Elementes
	 */
	public void setStyleId(String styleId);

	/**
	 * returns the StyleId
	 * 
	 * @return string
	 */
	public String getStyleId();

	/**
	 * Sets the StyleClass
	 * 
	 * @param styleClass
	 *            StyleClass
	 */
	public void setStyleClass(String styleClass);

	/**
	 * Returns the StyleClass
	 * 
	 * @return String
	 */
	public String getStyleClass();

	/**
	 * Returns the Help id that is associated with this control element. If a
	 * control has a help id a little icon with a link to the help system will
	 * be created
	 * 
	 * @return String returns the Help id of the element. The semantic of this
	 *         id is completely in the responsibility of the applications help
	 *         system
	 */
	public String getHelp();

	/**
	 * Associates this control with a help id
	 * 
	 * @param helpId
	 *            The Help Help id of the element. The semantic of this id is
	 *            completely in the responsibility of the applications help
	 *            system
	 */
	public void setHelp(String helpId);

	/**
	 * Sets the optional tooltip text
	 * 
	 * @param tooltip
	 *            Tooltip
	 */
	public void setTooltip(String tooltip);

	/**
	 * Retrieves the optional tooltip text
	 * 
	 * @return tooltip text or <code>null</code>
	 */
	public String getTooltip();

	/**
	 * Returns the Tabindex
	 * 
	 * @return int
	 */
	public int getTabIndex();

	/**
	 * Sets the Tabindex
	 * 
	 * @param tabIndex
	 *            Index
	 */
	public void setTabIndex(int tabIndex);

	/**
	 * Sets a list with permission for the object. Permissions are seperated by
	 * ';'
	 * 
	 * @param permission
	 *            Permission
	 */
	public void setPermission(Permission permission);

	/**
	 * Sets the Locale configuration for this control
	 * 
	 * @param locale
	 *            Locale Identifier or <code>true|false</code>
	 */
	public void setLocaleName(String locale);

	/**
	 * Gets the Local Setting for this Control
	 * 
	 * @return Locale Setting
	 */
	public String getLocaleName();

	/**
	 * Adds a new design rule to the control
	 * 
	 * @param rule
	 *            Design rule
	 */
	public void addDesignRule(DesignRule rule);

	/**
	 * Returns the design rules for this control
	 * 
	 * @return Array of design rules
	 */
	public DesignRule[] getDesignRules();

	/**
	 * Sets the property that can be used for rendering to non-visual media such
	 * as speech or Braille
	 * 
	 * @param summary
	 *            specifies a description and/or structure of the object.
	 */
	public void setSummary(String summary);

	/**
	 * Retrieves the property that can be used for rendering to non-visual media
	 * such as speech or Braille
	 * 
	 * @return receives a description and/or structure of the object or
	 *         <code>null</code>
	 */
	public String getSummary();

	/**
	 * Directs the framework to include a transaction token (if any) in all
	 * generated hyperlinks. The Transaction token is used to track form
	 * re-submissions.
	 * 
	 * @param transaction
	 *            include transaction token
	 */
	public void setTransaction(boolean transaction);

	/**
	 * Checks if the framework should include a transaction token (if any) in
	 * all generated hyperlinks. The Transaction token is used to track form
	 * re-submissions.
	 * 
	 * @return <code>true</code> if the transaction token should be generated
	 */
	public boolean getTransaction();
}