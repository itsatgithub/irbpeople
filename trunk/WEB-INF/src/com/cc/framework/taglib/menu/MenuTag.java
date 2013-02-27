/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/menu/MenuTag.java,v 1.20 2005/08/02 19:13:05 P001002 Exp $
 * $Revision: 1.20 $
 * $Date: 2005/08/02 19:13:05 $
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

package com.cc.framework.taglib.menu;

import javax.servlet.jsp.JspException;

import com.cc.framework.common.InvalidEnumType;
import com.cc.framework.taglib.TagHelp;
import com.cc.framework.taglib.controls.BaseControlTag;
import com.cc.framework.ui.MenuType;
import com.cc.framework.ui.control.Control;
import com.cc.framework.ui.control.MenuControl;
import com.cc.framework.ui.model.ControlDesignModel;
import com.cc.framework.ui.model.MenuDesignModel;
import com.cc.framework.ui.model.MenuItemDesignModel;
import com.cc.framework.ui.model.imp.MenuDesignModelImp;

/**
 * Tag handler for the <code>menu</code> tag.
 * <p>
 * A menu definition is triggered with this tag.
 * The actual menu options are specified with <menu:menuitem>-tags in the tag-body.
 *
 * @author     <a href="mailto:gschulz@scc-gmbh.com">Gernot Schulz</a>
 * @version    $Revision: 1.20 $
 * @since      1.0
 */
public class MenuTag extends BaseControlTag implements MenuContainerTag {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -8207610867413553990L;

	/**
	 * Constructor
	 */
	public MenuTag() {
		super();
	}

	/**
	 * @see com.cc.framework.taglib.controls.BaseControlTag#doCreateDesignModel()
	 */
	protected ControlDesignModel doCreateDesignModel() {
		return new MenuDesignModelImp();
	}

	/**
	 * Returns a casted version of the ControlDesignModel
	 *
	 * @return		MenuDesignModel
	 */
	protected MenuDesignModel getMenuDesignModel() {
		return (MenuDesignModel) getDesignModel();
	}

	/**
	 * @see com.cc.framework.taglib.controls.BaseControlTag#doCreateControl()
	 */
	protected Control doCreateControl() throws JspException {
		MenuDesignModel designModel = getMenuDesignModel();
		MenuControl ctrl = null;

		// if a property is specified we get the control form the form bean
		Object value = lookupBean();

		if (value == null) {
			// if there exists no instance for the control, we create a new one
			ctrl = new MenuControl();
		} else if (value instanceof MenuControl) {
			// assign the control
			ctrl = (MenuControl) value;
		} else {
			// create a new wrapper
			throw new JspException("MenuTag: Invalid property class. expected class=MenuControl!");
		}

		// if the designmodel was not specified server side we get the model
		// from the jsp page
		if ((ctrl.getDesignModel() == null)
			|| ctrl.getDesignModel().isDynamicDesignModel()) {
			ctrl.setDesignModel(designModel);
		}

		// assign an action if the programmer did not
		if (designModel.getAction() == null) {
			designModel.setAction(getDefaultAction());
		}

		return ctrl;
	}

	/**
	 * @see com.cc.framework.taglib.menu.MenuContainerTag#addMenuItem(com.cc.framework.ui.model.MenuItemDesignModel)
	 */
	public void addMenuItem(MenuItemDesignModel item) {
		getMenuDesignModel().addItem(item);
	}

	/**
	 * @see javax.servlet.jsp.tagext.TagSupport#setId(java.lang.String)
	 */
	public void setId(String id) {
		super.setId(id);

		// For backward combatibility
		if (getMenuDesignModel().getMenuId() == null) {
			getMenuDesignModel().setMenuId(id);
		}
	}

	/**
	 * Defines the internally used identificator
	 * of the menu option. A menu path is formed
	 * by concatenatingf the menu id's across the
	 * declaration hierarchy.
	 * A menu option can be considered to be selected
	 * when the current menu context path starts with the menu path!
	 * Note: Individual path elements are delimited with "/".
	 *
	 * @param	id	An identificator of the menu option
	 */
	public void setMenuid(String id) {
		getMenuDesignModel().setMenuId(id);
	}

	/**
	 * Specifies the menu type and hence the visual
	 * appearance of the menu.
	 * <ul>
	 * 	<li>main = for a horizontal main menu.</li>
	 * 	<li>tools = for a horizontal Tools menu.</li>
	 * 	<li>sidebar = for a vertical menu.</li>
	 * </ul>
	 *
	 * @param	type			The type of the menu
	 * @throws	JspException 	If the argument can't be converted	to an object of type MenuType
	 */
	public void setType(String type) throws JspException {

		try {
			getMenuDesignModel().setType(MenuType.parse(type));
		} catch (InvalidEnumType iet) {
			throw new JspException("Invalid Attribute value " + iet.getMessage());
		}
	}

	/**
	 * Specifies the name of an Imagemap which must be saved
	 * in the request. The value of the imageref property of
	 * the menu entries are mapped to the entries in this Imagemap.
	 * The mapping is done with the help of the regular expression,
	 * which is assigned to every entry of the Imagemap.
	 * The Painter appends to the imageref-value implicit, the
	 * following suffixes in order to distinguish between different states:
	 * <ul>
	 * 	<li>.sel = selected menu entry.</li>
	 * 	<li>.dis = disabled.</li>
	 * 	<li>.unsel = de-selected menu entry.</li>
	 * </ul>
	 * The selected menu option is always determined through the
	 * current menu context which can be set in the JSP-Page with
	 * <menu:ctx> or in the application with com.cc.framework.ui.control.MenuContext.setPath().
	 * Note: Under the name, there must be an Imagemap saved in the request.
	 *
	 * @param		mapName Imagemap
	 * @throws		JspException When the Image Map could not be found
	 */
	public void setImagemap(String mapName) throws JspException {
		getMenuDesignModel().setImageMap(TagHelp.lookupImageMap(pageContext, mapName));
	}

	/**
	 * An HTML-style. See HTML documentation for the attribute style.
	 *
	 * @param	style	An HTML-style
	 */
	public void setStyle(String style) {
		getMenuDesignModel().setStyle(style);
	}

	/**
	 * The HTML-id attribute. See HTML documentation for the Attribute id.
	 *
	 * @param styleId	The HTML-id attribute
	 */
	public void setStyleId(String styleId) {
		getMenuDesignModel().setStyleId(styleId);
	}

	/**
	 * The HTML-class attribute. See HTML documentation for the attribute class.
	 *
	 * @param	styleClass	The HTML-class attribute
	 */
	public void setStyleClass(String styleClass) {
		getMenuDesignModel().setStyleClass(styleClass);
	}
}