/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/def/DefMenuSidebarPainter.java,v 1.24 2005/07/28 19:41:14 P001002 Exp $
 * $Revision: 1.24 $
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
import org.apache.ecs.html.Comment;
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
 * Painter for the vertical Menue (Sidebar)
 *
 * @author      <a href="mailto:hschulz@scc-gmbh.com">Gernot Schulz</a>
 * @version     $Revision: 1.24 $
 * @since       1.0
 */
public class DefMenuSidebarPainter extends DefMenuPainter {

	/**
	 * Constructor for DefMenuSidebarPainter
	 *
	 * @param	painterContext	The PainterContext
	 * @param	ctrl			The Control to render
	 */
	public DefMenuSidebarPainter(PainterContext painterContext, MenuControl ctrl) {
		super(painterContext, ctrl);
	}

	/**
	 * Creates the MenuItems
	 * @param level		Level
	 * @param path		Path
	 * @param items		MenuItemDesignModel
	 * @param imageMap	ImageMap
	 * @param ctx		MenuContext
	 * @return	ConcreteElement
	 */
	protected ConcreteElement doCreateMenuItems(int level, String path, MenuItemDesignModel[] items, ImageMap imageMap, MenuContext ctx) {

		if ((items == null) || (items.length == 0)) {
			return null;
		}

		Principal principal = getPrincipal();

		ElementContainer container = new ElementContainer();

		for (int i = 0; i < items.length; i++) {
			if (!MenuStateType.INVISIBLE.equals(items[i].getState()) && items[i].show(principal)) {
				String itemPath = path + "/" + items[i].getMenuId();

				// check filter
				if (ctx.filter(items[i].getMenuFilter())) {
					continue;
				}

				// check selected menu item
				boolean disabled	= MenuStateType.DISABLED.equals(items[i].getState());
				boolean selected	= !disabled && ctx.isSelected(itemPath);

				String htmlClass = "m" + level;

				if (disabled) {
					htmlClass += "d";
				} else if (selected) {
					htmlClass += "s";
				}

				if (selected && (level == 3)) {
					// draw an image in front of the selected menu item
					container
						.addElement(new TR()
							.addElement(new TD()
								.setWidth(5)
								.addElement(createImage(DefResources.IMAGE_DOT_COLOR, getColor(DefColorPalette.VMENU3_COLOR_TEXT)))
								.setClass(htmlClass))
							.addElement(new TD()
								.setWidth("100%")
								.addElement(createMenuAnchor(items[i], imageMap, selected))
								.setClass(htmlClass)));

				} else {
					// otherwise render the menu item without an image
					container
						.addElement(new TR()
							.addElement(new TD()
								.addElement(createMenuAnchor(items[i], imageMap, selected))
								.setColSpan(2)
								.setClass(htmlClass)));
				}

				// now all the subsidiary menu items are painted
				// for this the menu item must be seletced
				if (selected) {
					ConcreteElement subMenu = doCreateMenuItems(level + 1, itemPath, items[i].getContent(), imageMap, ctx);

					if (subMenu != null) {
						if (level == 1) {
							container.addElement(new TR()
								.addElement(new TD(Entities.NBSP)
									.setWidth(12)
									.setClass(DefHtmlClass.MENU_LEVEL1_SPACER_LEFT))
								.addElement(new TD()
									.addElement(new Table()
										.setCellPadding(4)
										.setCellSpacing(0)
										.setWidth("100%")
										.addElement(subMenu))
									.setClass(DefHtmlClass.MENU_LEVEL1_SPACER_RIGHT)));
						} else {
							container.addElement(subMenu);
						}
					}
				}
			}
		}

		return container;
	}

	/**
	 * @see com.cc.framework.ui.painter.def.DefPainterBase#doCreateElement()
	 */
	protected ConcreteElement doCreateElement() {
		MenuContext ctx = MenuContext.getContext(request());

		Table control = new Table()
			.setCellPadding(0)
			.setCellSpacing(0)
			.setBorder(0);

		control.addElement(new Comment(ctx.toString()));

		ConcreteElement items = doCreateMenuItems(
			1,
			getCtrl().getMenuId(),
			getCtrl().getContent(),
			getCtrl().getImageMap(), ctx);

		if (items != null) {
			control.addElement(items);
		}

		if (getCtrl().getWidth() != null) {
			control.setWidth(getCtrl().getWidth());
		}

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
			control.setClass(DefHtmlClass.MENU_SIDEBAR);
		} else {
			control.setClass(getCtrl().getStyleClass());
		}

		return control;
	}
}