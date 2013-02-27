/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/def/DefTabbarPainter.java,v 1.41 2005/08/23 12:22:26 P001002 Exp $
 * $Revision: 1.41 $
 * $Date: 2005/08/23 12:22:26 $
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
import org.apache.ecs.html.Input;

import com.cc.framework.ui.Color;
import com.cc.framework.ui.ImageMap;
import com.cc.framework.ui.RunAt;
import com.cc.framework.ui.control.TabbarControl;
import com.cc.framework.ui.javascript.JavaScript;
import com.cc.framework.ui.javascript.JavaScriptUtil;
import com.cc.framework.ui.model.ClientEvent;
import com.cc.framework.ui.model.ImageModel;
import com.cc.framework.ui.model.TabDesignModel;
import com.cc.framework.ui.painter.Frame;
import com.cc.framework.ui.painter.PainterContext;
import com.cc.framework.ui.painter.def.frame.DefTabbedFramePainter;

/**
 * Painter class for the tabbar control
 *
 * @author      <a href="mailto:gschulz@scc-gmbh.com">Gernot Schulz</a>
 * @version     $Revision: 1.41 $
 * @since       1.2
 */
public class DefTabbarPainter extends DefPainterBase {

	/**
	 * Constructor for DefTabbarPainter
	 *
	 * @param	painterContext	The PainterContext
	 * @param	ctrl			The Control to render
	 */
	public DefTabbarPainter(PainterContext painterContext, TabbarControl ctrl) {
		super(painterContext);
	}

	/**
	 * Retrieves the control for this painter
	 *
	 * @return		Control
	 */
	protected TabbarControl getCtrl() {
		return (TabbarControl) getPainterContext().getControl();
	}

	/**
	 * Checks if the specified tab is selected
	 *
	 * @param		tab	the tab to check
	 * @return		<code>true</code> if the tab is selected
	 */
	protected boolean isSelected(TabDesignModel tab) {
		return tab.getTabId().equals(getCtrl().getSelectedTab());
	}

	/**
	 * Checks if the tabs should be painted in overlapping mode
	 *
	 * @return		<code>true</code> when overlapping tabs
	 * 				should be drawn
	 */
	protected boolean isOverlapping() {
		if (getFramePainter() instanceof DefTabbedFramePainter) {
			return ((DefTabbedFramePainter) getFramePainter()).isOverlapping();
		} else {
			// Draw overlapping tabs
			return true;
		}
	}

	/**
	 * Checks if the frame should be painted
	 *
	 * @return		<code>true</code> if the frame should
	 * 				be painted
	 */
	protected boolean showFrame() {
		return false;
	}

	/**
	 * Retrieves the Background color of the control
	 *
	 * @return		Background color
	 */
	public Color getBgColor() {
		Color bgcolor = getCtrl().getBgColor();

		if (bgcolor == null) {
			return getColor(DefColorPalette.TABBAR_COLOR_BG_LEVEL1);
		} else {
			return bgcolor;
		}
	}

	/**
	 * Returns the optional image for the given tab
	 *
	 * @param	 	tab the tab
	 * @return 		ImageModel
	 */
	public ImageModel getTabImage(TabDesignModel tab) {

		// render item as image
		String imageref = tab.getImageRef();

		if (imageref == null) {
			return null;
		}

		ImageMap imageMap = getCtrl().getImageMap();

		if (imageMap == null) {
			return null;
		}

		if (tab.isEnabled()) {
			imageref += isSelected(tab) ? ".sel" : ".unsel";
		}

		return imageMap.mapValueToImage(imageref);
	}

	// ======================================
	// object painter
	// ======================================

	/**
	 * @see com.cc.framework.ui.painter.ControlPainter#getElementClass(int)
	 */
	public String getElementClass(int type) {
		if (type == DefClassType.CLASS_CONTROL) {
			return DefHtmlClass.TABBARCONTROL;
		} else {
			return super.getElementClass(type);
		}
	}

	/**
	 * This method creates the content for the selected tab.
	 * If the control is configured as RunAt="CLIENT" than all tabs
	 * will be rendered in hidden DIV elements.
	 *
	 * @param		tabs The collection with the visible tabs
	 * @return		ConcreteElement
	 */
	protected ConcreteElement doCreateBody(TabDesignModel[] tabs) {
		return null;
	}

	/**
	 * Creates an JavaScript for the client Side Handler of the control.
	 * The Script is needed if:<br>
	 * 1) The Attrbite runAt is set to true
	 * 2) The control has more tab pages then should be visible
	 *
	 * @param		tabs TabDesignModel
	 * @return		JavaScript
	 */
	protected JavaScript createClientScript(TabDesignModel[] tabs) {

		if (!RunAt.CLIENT.equals(getCtrl().getRunAt())
			&& ((tabs.length <= getCtrl().getMaxVisible()) || (getCtrl().getMaxVisible() == -1))) {
			return null;
		}
		
		String nl = System.getProperty("line.separator");
		
		String controlId	= getCtrl().getControlName();
		Color bgcolor		= getBgColor();
		StringBuffer buf	= new StringBuffer();

		// First create the controls js object and set the runAt attribute.
		// Example:
		// var ctrl_<<id>>=new TabSet('<<id>>',false);
		// ctrl_<<id>>.setRunAt(RunAt.CLIENT);
		buf
			.append("var ctrl_")
			.append(controlId)
			.append("=new TabSet('")
			.append(controlId)
			.append("',")
			.append(getCtrl().isFormElement())
			.append(");")
			.append("ctrl_")
			.append(controlId)
			.append(".setRunAt(RunAt.")
			.append(RunAt.CLIENT.equals(getCtrl().getRunAt()) ? "CLIENT" : "SERVER")
			.append(");")
			.append(nl);
		
		buf
			.append("ctrl_")
			.append(controlId)
			.append(".setFirstVisibleTab('")
			.append((getCtrl().getFirstVisibleTab() == null) ? "" : JavaScriptUtil.encodeJavaScript(getCtrl().getFirstVisibleTab()))
			.append("');")
			.append(nl);;
		
		// Now add the tabs. Example:
		// ctrl_<<id>>.addTab('tab1','Tab No. 1',true,'Tooltip Tab1',false);
		for (int i = 0; i < tabs.length; i++) {

			if (tabs[i].show(getPrincipal())) {
				ImageModel image = getTabImage(tabs[i]);

				buf
					.append("ctrl_")
					.append(controlId)
					.append(".addTab('")
					.append(tabs[i].getTabId())
					.append("','")
					.append(JavaScriptUtil.encodeJavaScript(localize(tabs[i].getTitle())))
					.append("',")
					.append(isSelected(tabs[i]))
					.append(",'")
					.append(JavaScriptUtil.encodeJavaScript(localize(tabs[i].getTooltip())))
					.append("',")
					.append(!tabs[i].isEnabled());

				if (image != null) {
					buf
						.append(",'")
						.append(getSource(image))
						.append("'")
						.append(",'")
						.append(image.getWidth())
						.append("'")
						.append(",'")
						.append(image.getHeight())
						.append("'");
				}

				buf.append(")");
				
				ClientEvent[] clientEvents = tabs[i].getHandlers();
				
				if (clientEvents.length > 0) {
					
					for (int j = 0; j < clientEvents.length; j++) {
						buf
							.append(".addHandler(")
							.append("ClientEvent." + clientEvents[j].getScriptName().toUpperCase())
							.append(", '")
							.append(JavaScriptUtil.encodeJavaScript(tabs[i].getHandler(clientEvents[j])))
							.append("')");
					}
				}
				
				buf
					.append(";")
					.append(nl);
			}
		}

		// Now we create an array with the images used for the control

		// Now setup the TabSetPainterData object, which holds information about the resources
		// var tsp_<<id>>=new TabSetPainterData(ctrl_<<id>>,'/cc-custompainter','fw/def/image/tab/', 'FFFFFF',3,20);
		// Create the images
		String[] resources = getJSImageResources();

		// create the JavaScript array which holds all the image resources
		// We will the pass this array to our JavaScript painter
		buf
			.append("var arr_imageRes_")
			.append(controlId)
			.append(" = new Array();")
			.append(nl);

		for (int i = 0; i < resources.length; i++) {
			String image =
				JavaScriptUtil.doCreateJSImage(
					resources[i],
					getPainterContext().getImage(resources[i], bgcolor));

			buf
				.append("arr_imageRes_")
				.append(controlId)
				.append("['")
				.append(resources[i])
				.append("'] = new ")
				.append(image)
				.append(nl);
		}

		// create the JavaScript array which holds all the string/message resources
		buf
			.append("var arr_stringRes_")
			.append(controlId)
			.append(" = new Array();");

		buf
			.append("arr_stringRes_")
			.append(controlId)
			.append("['")
			.append(getJSRangeResource())
			.append("'] = '")
			.append(JavaScriptUtil.encodeJavaScript(getFrameworkString(getJSRangeResource())))
			.append("';")
			.append(nl);

		buf
			.append("var tsp_")
			.append(controlId)
			.append("=new TabSetPainterData(ctrl_")
			.append(controlId)
			.append(", '")
			.append(getJSType())
			.append("', '")
			.append(getPainterContext().request().getContextPath())
			.append("', ")
			.append("arr_imageRes_").append(controlId)
			.append(", ")
			.append("arr_stringRes_").append(controlId)
			.append(", '")
			.append(bgcolor.toHex())
			.append("', ")
			.append(getCtrl().getMaxVisible())
			.append(", ")
			.append(getCtrl().getLabelLength())
			.append(", '")
			.append(getCtrl().getStyleClass())
			.append("', ")
			.append(isOverlapping())
			.append(");")
			.append(nl);

		// Add the javascript object which starts redering the control
		// TabSetPainter.render(tsp_<<id>>);
		buf
			.append("TabSetPainter.render(tsp_")
			.append(controlId)
			.append(");");

		JavaScript script = new JavaScript();
		script.addElement(buf.toString());

		return script;
	}

	/**
	 * @return		The control type for JavaScript
	 */
	protected String getJSType() {
		return "TABBAR";
	}

	/**
	 * @return		Resource identifier for the range text resource
	 */
	protected String getJSRangeResource() {
		return DefResources.FW_TABBAR_RANGE;
	}

	/**
	 * Creates the array which holds the images resources
	 * used by our javascript tree
	 *
	 * @return Array containing the images resources
	 */
	protected String[] getJSImageResources() {
		return new String[] {
			DefResources.TABBAR_BACKGROUND,
			DefResources.TABBAR_SEL_BG,
			DefResources.TABBAR_SEL_NONE,
			DefResources.TABBAR_SEL_UNSEL,
			DefResources.TABBAR_SEL_DIS,
			DefResources.TABBAR_UNSEL_BG,
			DefResources.TABBAR_UNSEL_NONE,
			DefResources.TABBAR_UNSEL_SEL,
			DefResources.TABBAR_UNSEL_UNSEL,
			DefResources.TABBAR_UNSEL_DIS,
			DefResources.TABBAR_DIS_BG,
			DefResources.TABBAR_DIS_NONE,
			DefResources.TABBAR_DIS_SEL,
			DefResources.TABBAR_DIS_UNSEL,
			DefResources.TABBAR_DIS_DIS,
			DefResources.TABBAR_NONE_SEL,
			DefResources.TABBAR_NONE_UNSEL,
			DefResources.TABBAR_NONE_DIS,
			DefResources.BUTTON_TABBAR_PREVIOUS_1,
			DefResources.BUTTON_TABBAR_PREVIOUS_2,
			DefResources.BUTTON_TABBAR_NEXT_1,
			DefResources.BUTTON_TABBAR_NEXT_2,
			DefResources.BUTTON_TABBAR_MORE_PREVIOUS,
			DefResources.BUTTON_TABBAR_MORE_NEXT,
			DefResources.BUTTON_TABBAR_MORE_EMPTY };
	}

	/**
	 * @see com.cc.framework.ui.painter.ControlPainter#doCreateElement()
	 */
	protected ConcreteElement doCreateElement() {

		// if no designmodel is specified then terminate
		if (getCtrl().getDesignModel() == null) {
			return null;
		}

		// get all visible tabs
		TabDesignModel[] tabs	= getCtrl().getVisibleTabs();

		// if the control has no tabs --> return
		if (tabs.length == 0) {
			return null;
		}

		ElementContainer container = new ElementContainer();

		// A hidden field is used if the control should act as an formelement
		// Otherwise the selected tab is passed in the url to invoke the
		// correct callback method
		if (getCtrl().isFormElement()) {
			String selId = getCtrl().getSelectedTab();

			if (selId == null) {
				selId = "";
			}

			String controlId = getCtrl().getControlName();
			if (controlId != null) {
				container
					.addElement(new Input(Input.HIDDEN, controlId, selId)
						.setID(controlId));
			}
		}

		// render the control
		Frame control = getFramePainter().createFrame(null, showFrame());

		if (getCtrl().getStyle() != null) {
			control.setStyle(getCtrl().getStyle());
		}

		if (getStyleId() != null) {
			control.setID(getStyleId());
		}

		// set general style class
		if (getCtrl().getStyleClass() == null) {
			control.setClass(getElementClass(DefClassType.CLASS_CONTROL));
		} else {
			control.setClass(getCtrl().getStyleClass());
		}

		if (getCtrl().getWidth() != null) {
			control.setWidth(getCtrl().getWidth());
		}

		if (getCtrl().getSummary() != null) {
			control.setSummary(getCtrl().getSummary());
		}

		// Add body Elements
		control.addBodyElement(doCreateBody(tabs));

		container.addElement(control);

		JavaScript script = createClientScript(tabs);

		if (script != null) {
			container.addElement(script);
		}

		return container;
	}
}
