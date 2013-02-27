/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/menu/MenuItemTag.java,v 1.24 2005/08/02 19:13:05 P001002 Exp $
 * $Revision: 1.24 $
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
import javax.servlet.jsp.PageContext;

import com.cc.framework.taglib.InnerTag;
import com.cc.framework.taglib.ScriptTagSupport;
import com.cc.framework.taglib.TagHelp;
import com.cc.framework.ui.MenuStateType;
import com.cc.framework.ui.model.ClientHandler;
import com.cc.framework.ui.model.MenuItemDesignModel;
import com.cc.framework.ui.model.imp.MenuItemDesignModelImp;

/**
 * Tag-Handler fo the <code>menuitem</code> Tag.
 * <p>
 * A menu definition is declared with this tag.
 * A menu entry can itself in turn contain additional <menu:menuitem>-tags.
 * The tag can only be used within <menu:menu> and <menu:menuitem>-tags.
 *
 * @author     <a href="mailto:gschulz@scc-gmbh.com">Gernot Schulz</a>
 * @version    $Revision: 1.24 $
 * @since      1.0
 */
public class MenuItemTag extends ScriptTagSupport implements MenuContainerTag, InnerTag {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -1569186886697571743L;

	/**
	 * DesignModel
	 */
	private MenuItemDesignModel designModel	= null;

	/**
	 * Constructor
	 */
	public MenuItemTag() {
		super();
	}

	/**
	 * Returns the MenuItemDesignModel
	 *
	 * @return	MenuItemDesignModel
	 */
	protected MenuItemDesignModel getDesignModel() {
		if (designModel == null) {
			designModel = new MenuItemDesignModelImp();
		}
		
		return designModel;
	}

	/**
	 * Releases the design model model
	 */
	protected void releaseDesignModel() {
		designModel = null;
	}

	/**
	 * @see	javax.servlet.jsp.tagext.Tag#setPageContext(PageContext pc)
	 */
	public void setPageContext(PageContext pc) {
		super.setPageContext(pc);

		// release the design model
		releaseDesignModel();
	}

	/**
	 * @see	javax.servlet.jsp.tagext.Tag#release()
	 */
	public void release() {
		super.release();
		
		releaseDesignModel();
	}

	/**
	 * @see com.cc.framework.taglib.ScriptSupport#getClientHandler()
	 */
	public ClientHandler getClientHandler() {
		return getDesignModel();
	}

	/**
	 * @see	javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException {
		releaseDesignModel();
		
		return EVAL_PAGE;
	}

	/**
	 * @see	javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException {

		Object parent = findAncestorWithClass(this, MenuContainerTag.class);

		// a column tag must be nested within a PanelTag
		if (parent == null) {
			throw new JspException("MenuItemTag must be nested inside a MenuTag oder MenuItemTag");
		}

		// Assign the DesignModel too the Parent which  embedds the Tag
		MenuContainerTag container = (MenuContainerTag) parent;
		container.addMenuItem(getDesignModel());

		// the tag has no body
		return EVAL_BODY_INCLUDE;
	}

	/**
	 * @see javax.servlet.jsp.tagext.TagSupport#setId(java.lang.String)
	 */
	public void setId(String id) {
		super.setId(id);

		// For backward combatibility
		if (getDesignModel().getMenuId() == null) {
			getDesignModel().setMenuId(id);
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
		getDesignModel().setMenuId(id);
	}

	/**
	 * Specifies the Struts Action that is
	 * to be called upon selection of a menu option
	 *
	 * @param	action	The action
	 */
	public void setAction(String action) {
		getDesignModel().setAction(action);
	}

	/**
	 * Specifies the state of the menu option.
	 * <ul>
	 * 	<li>enabled = menu option active.</li>
	 * 	<li>disabled = menu option is inactive and not selectable.</li>
	 * 	<li>checked = menu option is marked.</li>
	 * 	<li>unchecked = menu option is not marked.</li>
	 * 	<li>visible = menu option is visible.</li>
	 * 	<li>invisible = menu option is invisible.</li>
	 * </ul>
	 *
	 * @param	state	The menu state
	 */
	public void setState(String state) {
		try {
			getDesignModel().setState(MenuStateType.parse(state));
		} catch (com.cc.framework.common.InvalidEnumType iet) {
			// No action
		}
	}

	/**
	 * The actual labelling of the menu option. This must also be
	 * specified if an image is to be used for the menu option.
	 *
	 * @param	text	The text of the menu option.
	 */
	public void setText(String text) {
		getDesignModel().setText(text);
	}

	/**
	 * A short descriptive text that is displayed when
	 * the user moves the mouse over the element.
	 *
	 * @param	tooltip	 Tooltip
	*/
	public void setTooltip(String tooltip) {
		getDesignModel().setTooltip(tooltip);
	}

	/**
	 * A keyboard shortcut for the menu entry
	 *
	 * @param	shortcut	Shortcut
	 */
	public void setShortcut(String shortcut) {
		getDesignModel().setShortcut(shortcut);
	}

	/**
	 * Sets the width of the control element.
	 * The height may be specified in absolute
	 * or percent terms.
	 *
	 * @param tag String
	 */
	public void setWidth(String tag) {
		getDesignModel().setWidth(tag);
	}

	/**
	 * The HTML-target attribute of the element can
	 * be specified with this attribute.
	 * Note: See HTML documentation for the attribute target.
	 *
	 * @param	target	Target-Attribute
	 */
	public void setTarget(String target) {
		getDesignModel().setTarget(target);
	}

	/**
	 * Defines the optional filter condition(s) for
	 * this menu option. The menu context filter must
	 * tally with at least one filter so that the menu
	 * option is displayed. Note: Several filters are
	 * delimited with ";".
	 *
	 * @param	filter	An optional filter condition(s)
	 */
	public void setFilter(String filter) {
		getDesignModel().setMenuFilter(filter);
	}

	/**
	 * Sets the Loacel configuration for this element
	 *
	 * @param	locale Locale Identifier or <code>true|false</code>
	 */
	public void setLocale(String locale) {
		getDesignModel().setLocaleName(locale);
	}

	/**
	 * The Painter appends to the imageref-value implicit,
	 * the following suffixes in order to distinguish between
	 * different states. ThePainter appends to the imageref-value
	 * implicit, the following suffixes in order to distinguish
	 * between different states:
	 * <ul>
	 * 	<li>.sel = selected menu entry.</li>
	 * 	<li>.dis = disabled.</li>
	 * 	<li>.unsel = de-selected menu entry.</li>
	 * </ul>
	 *
	 * @param imageRef	The reference to an image declared an imagemap
	 */
	public void setImageref(String imageRef) {
		getDesignModel().setImageRef(imageRef);
	}

	/**
	 * With this attribute, access to the element can be restricted.
	 * Authorizations are always specified in the form of an Access
	 * Control List (ACL). What is involved here is a semicolon-delimited
	 * list with individual authorizations.
	 *
	 * @param	permission		List of permissions
	 * @throws	JspException	If the Argument can't be converted to an Object of type Permission
	 */
	public void setPermission(String permission) throws JspException {
		getDesignModel().setPermission(TagHelp.toPermission(permission));
	}

	/**
	 * @see com.cc.framework.taglib.menu.MenuContainerTag#addMenuItem(MenuItemDesignModel)
	 */
	public void addMenuItem(MenuItemDesignModel item) {
		getDesignModel().addItem(item);
	}
}