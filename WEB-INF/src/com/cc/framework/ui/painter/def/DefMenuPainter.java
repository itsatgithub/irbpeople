/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/def/DefMenuPainter.java,v 1.24 2005/02/16 18:03:20 P001001 Exp $
 * $Revision: 1.24 $
 * $Date: 2005/02/16 18:03:20 $
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

import java.util.Locale;

import org.apache.ecs.ConcreteElement;
import org.apache.ecs.MultiPartElement;
import org.apache.ecs.html.A;
import org.apache.ecs.html.Span;

import com.cc.framework.ui.ImageMap;
import com.cc.framework.ui.MenuStateType;
import com.cc.framework.ui.control.MenuControl;
import com.cc.framework.ui.model.ImageModel;
import com.cc.framework.ui.model.MenuDesignModel;
import com.cc.framework.ui.model.MenuItemDesignModel;
import com.cc.framework.ui.painter.PainterContext;
import com.cc.framework.ui.painter.PainterHelp;

/**
 * Base Class for menu controls
 *
 * @author	    <a href="mailto:hschulz@scc-gmbh.com">Gernot Schulz</a>
 * @version	    $Revision: 1.24 $
 * @since       1.0
 */
public abstract class DefMenuPainter extends DefPainterBase {

	/**
	 * Constructor
	 *
	 * @param	painterContext	The PainterContext
	 * @param	ctrl			The Control to render
	 */
	public DefMenuPainter(PainterContext painterContext, MenuControl ctrl) {
		super(painterContext);
	}

	/**
	 * Retrieves the control for this painter
	 *
	 * @return		Control
	 */
	protected MenuControl getCtrl() {
		return (MenuControl) getPainterContext().getControl();
	}

	/**
	 * Returns the DesignModel
	 * @return	MenuDesignModel
	 */
	protected MenuDesignModel getDesignModel() {
		return (MenuDesignModel) getCtrl().getDesignModel();
	}

	/**
	 * Retrieves the locale to use for a given menu item
	 *
	 * @param		item Panel item
	 * @return		Locale
	 */
	protected Locale getLocale(MenuItemDesignModel item) {
		if (item == null) {
			return getPainterContext().getLocale();
		} else if (item.getLocaleName() == null) {
			return getLocale(item.getParent());
		} else {
			String localeName = item.getLocaleName();

			return PainterHelp.localeFromName(getPageContext(), localeName);
		}
	}

	/**
	 * Creates the Menu Anchor
	 * @param item		MenuItemDesignModel
	 * @param imageMap	ImageMap
	 * @param selected	Selected Flag
	 * @return	ConcreteElement
	 */
	protected ConcreteElement createMenuAnchor(MenuItemDesignModel item, ImageMap imageMap, boolean selected) {

		boolean disabled	= MenuStateType.DISABLED.equals(item.getState());
		Locale locale		= getLocale(item);
		ImageModel image	= null;

		if ((item.getImageRef() != null) && (imageMap != null)) {
			// the item should be painted as an image not as text
			String suffix = null;

			if (disabled) {
				suffix = ".disabled";
			} else if (selected) {
				suffix = ".sel";
			} else {
				suffix = ".unsel";
			}

			image = imageMap.mapValueToImage(item.getImageRef() + suffix);
		}

		MultiPartElement menuAnchor;

		if (disabled) {
			menuAnchor = new Span();
		} else {
			A a = new A(decorateURL(item.getAction()));

			if (null != item.getTarget()) {
				a.setTarget(item.getTarget());
			}

			menuAnchor = a;
		}

		if (item.getTooltip() != null) {
			menuAnchor.setTitle(html(localize(item.getTooltip(), locale)));
		}

		if (image != null) {
			menuAnchor.addElementToRegistry(createImage(image).setID("btn"));
		} else {
			// the item will be painted as normal text
			menuAnchor.addElementToRegistry(html(localize(item.getText(), locale)));
		}

		if (!disabled) {
			PainterHelp.setScriptHandler(menuAnchor, item);
		}

		return menuAnchor;
	}
}