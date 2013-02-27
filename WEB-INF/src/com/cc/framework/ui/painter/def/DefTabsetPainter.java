/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/def/DefTabsetPainter.java,v 1.71 2005/06/05 11:08:21 P001002 Exp $
 * $Revision: 1.71 $
 * $Date: 2005/06/05 11:08:21 $
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
import org.apache.ecs.StringElement;
import org.apache.ecs.html.Span;
import org.apache.ecs.html.TD;
import org.apache.ecs.html.TR;

import com.cc.framework.ui.Color;
import com.cc.framework.ui.RunAt;
import com.cc.framework.ui.control.TabsetControl;
import com.cc.framework.ui.model.TabDesignModel;
import com.cc.framework.ui.painter.PainterContext;
import com.cc.framework.ui.painter.global.IncludeElement;

/**
 * Painter for the Tabset Control
 *
 * @author      <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version     $Revision: 1.71 $
 * @since       1.0
 */
public class DefTabsetPainter extends DefTabbarPainter {

	/**
	 * Constructor for DefTabsetPainter
	 *
	 * @param	painterContext	The PainterContext
	 * @param	ctrl			The Control to render
	 */
	public DefTabsetPainter(PainterContext painterContext, TabsetControl ctrl) {
		super(painterContext, ctrl);
	}

	/**
	 * Checks if the frame should be painted
	 *
	 * @return		<code>true</code> if the frame should
	 * 				be painted
	 */
	protected boolean showFrame() {
		return true;
	}

	// ======================================
	// object painter
	// ======================================

	/**
	 * @see com.cc.framework.ui.painter.ControlPainter#getElementClass(int)
	 */
	public String getElementClass(int type) {
		if (type == DefClassType.CLASS_CONTROL) {
			return DefHtmlClass.TABSETCONTROL;
		} else {
			return super.getElementClass(type);
		}
	}

	/**
	 * Creates the Content of a single Tab Page
	 * 
	 * @param		tab	The tab page
	 * @return		ConcreteElement
	 */
	protected ConcreteElement doCreateBody(TabDesignModel tab) {
		ConcreteElement tabContent = null;

		if (tab.getContent() != null) {
			// Include another JSP page
			tabContent = new IncludeElement(getPageContext(), tab.getContent());
		} else if (tab.getBody() != null) {
			// Render a HTML body
			tabContent = new StringElement(tab.getBody());
		} else {
			// Tab has no content
			tabContent = new StringElement("no content");
		}

		return tabContent;
	}

	/**
	 * This method creates the content for theselected tab.
	 * If the Tabset is configured as RunAt="CLIENT" than all tabs
	 * will be rendered in hidden DIV elements.
	 * 
	 * @param		tabs The collection with the visible tabs
	 * @return		ConcreteElement
	 */
	protected ConcreteElement doCreateBody(TabDesignModel[] tabs) {
		String spanPrefix = "tab_" + getCtrl().getControlName() + "_";

		TD pageContainer = new TD();
		pageContainer.setBgColor(getBgColor().toHtml());

		Span pageContent = null;

		TabDesignModel selected = null;

		for (int i = 0; i < tabs.length; i++) {

			if (isSelected(tabs[i])) {
				selected = tabs[i];

				// the selected tab is always displayed
				pageContent = new Span();
				pageContent.addElement(doCreateBody(tabs[i]));
				pageContent.setStyle("display : block;");
				pageContent.setID(spanPrefix + tabs[i].getTabId());

				pageContainer.addElement(pageContent);

			} else if (RunAt.CLIENT.equals(getCtrl().getRunAt()) && tabs[i].isEnabled()) {
				// Paint unselected tabs only if RunAt=CLIENT is set
				pageContent = new Span();
				pageContent.addElement(doCreateBody(tabs[i]));
				pageContent.setStyle("display : none");
				pageContent.setID(spanPrefix + tabs[i].getTabId());

				pageContainer.addElement(pageContent);
			}
		}

		// Check if any tab was selected
		if (selected == null) {
			pageContent = new Span();
			pageContent.addElement("no tab selected");
			pageContent.setID(spanPrefix);
			pageContent.setStyle("display : block");

			pageContainer.addElement(pageContent);
		}

		return new TR(pageContainer);
	}

	/**
	 * Retrieves the Background color of the tabset
	 * 
	 * @return		Background color
	 */
	public Color getBgColor() {
		Color bgcolor = getCtrl().getBgColor();

		if (bgcolor == null) {
			return getColor(DefColorPalette.TABSET_COLOR_BG_LEVEL1);
		} else {
			return bgcolor;
		}
	}

	/**
	 * @return		The control type for JavaScript
	 */
	protected String getJSType() {
		return "TABSET";
	}

	/**
	 * @return		Resource identifier for the range text resource
	 */
	protected String getJSRangeResource() {
		return DefResources.FW_TABSET_RANGE;
	}

	/**
	 * Creates the array which holds the images resources
	 * used by our javascript tree
	 *
	 * @return Array containing the images resources
	 */
	protected String[] getJSImageResources() {
		return new String[] {
			DefResources.TABSET_BACKGROUND,
			DefResources.TABSET_SEL_BG,
			DefResources.TABSET_SEL_NONE,
			DefResources.TABSET_SEL_UNSEL,
			DefResources.TABSET_SEL_DIS,
			DefResources.TABSET_UNSEL_BG,
			DefResources.TABSET_UNSEL_NONE,
			DefResources.TABSET_UNSEL_SEL,
			DefResources.TABSET_UNSEL_UNSEL,
			DefResources.TABSET_UNSEL_DIS,
			DefResources.TABSET_DIS_BG,
			DefResources.TABSET_DIS_NONE,
			DefResources.TABSET_DIS_SEL,
			DefResources.TABSET_DIS_UNSEL,
			DefResources.TABSET_DIS_DIS,
			DefResources.TABSET_NONE_SEL,
			DefResources.TABSET_NONE_UNSEL,
			DefResources.TABSET_NONE_DIS,
			DefResources.BUTTON_TABSET_PREVIOUS_1,
			DefResources.BUTTON_TABSET_PREVIOUS_2,
			DefResources.BUTTON_TABSET_NEXT_1,
			DefResources.BUTTON_TABSET_NEXT_2,
			DefResources.BUTTON_TABSET_MORE_PREVIOUS,
			DefResources.BUTTON_TABSET_MORE_NEXT,
			DefResources.BUTTON_TABSET_MORE_EMPTY };
	}
}