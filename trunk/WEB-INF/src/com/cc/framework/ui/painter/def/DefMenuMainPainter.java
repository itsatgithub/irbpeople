/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/def/DefMenuMainPainter.java,v 1.23 2005/07/28 19:41:14 P001002 Exp $
 * $Revision: 1.23 $
 * $Date: 2005/07/28 19:41:14 $
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

package com.cc.framework.ui.painter.def;

import org.apache.ecs.ConcreteElement;
import org.apache.ecs.ElementContainer;
import org.apache.ecs.Entities;
import org.apache.ecs.html.TD;
import org.apache.ecs.html.TR;
import org.apache.ecs.html.Table;

import com.cc.framework.security.Principal;
import com.cc.framework.ui.ImageMap;
import com.cc.framework.ui.MenuStateType;
import com.cc.framework.ui.control.MenuContext;
import com.cc.framework.ui.control.MenuControl;
import com.cc.framework.ui.model.MenuItemDesignModel;
import com.cc.framework.ui.painter.PainterContext;

/**
 * Painter for the Main Menu
 *
 * @author      <a href="mailto:hschulz@scc-gmbh.com">Gernot Schulz</a>
 * @version     $Revision: 1.23 $
 * @since       1.0
 */
public class DefMenuMainPainter extends DefMenuPainter {

	/**
	 * Constructor
	 *
	 * @param	painterContext	The PainterContext
	 * @param	ctrl			The control to render
	 */
	public DefMenuMainPainter(PainterContext painterContext, MenuControl ctrl) {
		super(painterContext, ctrl);
	}

	/**
	 * Creates the cells including the menu items of the menu
	 *
	 * @param	items		The menu items
	 * @param	imageMap	An optional image map
	 * @param	ctx			The MenuContext
	 * @return	ConcreteElement
	 */
	protected ConcreteElement doCreateMenuItems(MenuItemDesignModel[] items, ImageMap imageMap, MenuContext ctx) {

		if ((items == null) || (items.length == 0)) {
			return null;
		}

		ElementContainer container = new ElementContainer();

			container
				.addElement(new TD(Entities.NBSP)
					.setWidth(20)
					.setNoWrap(true));

		Principal principal = getPrincipal();

		for (int i = 0; i < items.length; i++) {
			if (!MenuStateType.INVISIBLE.equals(items[i].getState()) && items[i].show(principal)) {
				String itemPath = items[i].getMenuId();

				// check the filter attribute
				if (ctx.filter(items[i].getMenuFilter())) {
					continue;
				}

				// check for selected item
				boolean disabled	= MenuStateType.DISABLED.equals(items[i].getState());
				boolean selected	= !disabled && ctx.isSelected(itemPath);

				String htmlClass = "mm1";

				if (disabled) {
					htmlClass += "d";
				} else if (selected) {
					htmlClass += "s";
				}

				container
					.addElement(new TD()
						.addElement(createMenuAnchor(items[i], imageMap, selected))
						.setClass(htmlClass));
			}
		}

		return container;
	}

	/**
	 * Creates a html table element, which holds the specified menu items
	 *
	 * @see com.cc.framework.ui.painter.def.DefPainterBase#doCreateElement()
	 */
	protected ConcreteElement doCreateElement() {

		MenuContext ctx = MenuContext.getContext(request());

		Table control = new Table()
			.setCellPadding(0)
			.setCellSpacing(0)
			.setBorder(0);

		// create the menu items
		ConcreteElement items = doCreateMenuItems(getCtrl().getContent(), getCtrl().getImageMap(), ctx);

		// add the menu items to the html table row
		if (items != null) {
			control.addElement(new TR(items));
		}

		// add the width attribute
		if (getCtrl().getWidth() != null) {
			control.setWidth(getCtrl().getWidth());
		}

		// add the id attribute
		if (getStyleId() != null) {
			control.setID(getStyleId());
		} else {
			control.setID(getCtrl().getMenuId());
		}

		if (getCtrl().getStyle() != null) {
			control.setStyle(getCtrl().getStyle());
		}

		// add the class attribute
		if (getCtrl().getStyleClass() == null) {
			control.setClass(DefHtmlClass.MENU_MAIN);
		} else {
			control.setClass(getCtrl().getStyleClass());
		}

		return control;
	}
}