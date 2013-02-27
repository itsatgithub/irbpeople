/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/html/HtmlTextPopupPainter.java,v 1.15 2005/09/12 09:18:49 P001002 Exp $
 * $Revision: 1.15 $
 * $Date: 2005/09/12 09:18:49 $
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

package com.cc.framework.ui.painter.html;

import org.apache.ecs.AlignType;
import org.apache.ecs.ConcreteElement;
import org.apache.ecs.html.A;
import org.apache.ecs.html.IMG;
import org.apache.ecs.html.TD;
import org.apache.ecs.html.TR;
import org.apache.ecs.html.Table;

import com.cc.framework.ui.control.TextPopupControl;
import com.cc.framework.ui.painter.PainterContext;
import com.cc.framework.ui.painter.PainterContextAtributes;
import com.cc.framework.util.StringHelp;

/**
 * Painter for the TextArea Control
 *
 * @author      <a href="mailto:gschulz@scc-gmbh.com">Gernot Schulz</a>
 * @version     $Revision: 1.15 $
 * @since       1.0
 */
public class HtmlTextPopupPainter extends HtmlTextareaPainter {

	/**
	 * Constructor for HtmlTextareaPainter
	 *
	 * @param	painterContext	The PainterContext
	 * @param	ctrl			The Control to render
	 */
	public HtmlTextPopupPainter(PainterContext painterContext, TextPopupControl ctrl) {
		super(painterContext, ctrl);
	}

	/**
	 * @see com.cc.framework.ui.painter.ControlPainter#doCreateElement()
	 */
	protected ConcreteElement doCreateElement() {
		// Force the painter context to create a unique style id
		// for this control. We need this for JavaScript
		getPainterContext().setAttribute(
			PainterContextAtributes.FORCE_STYLEID,
			Boolean.TRUE);

		return super.doCreateElement();
	}

	/**
	 * @see com.cc.framework.ui.painter.html.HtmlTextareaPainter#doCreateTextArea()
	 */
	protected ConcreteElement doCreateTextArea() {

		TextPopupControl cctrl = (TextPopupControl) getCtrl();

		if (getPainterContext().isDisplayOnly()) {
			// For form type="display" we render a display only form element
			return super.doCreateTextArea();

		} else if (cctrl.isDisabled()) {
			// if the textarea was disabled, we only generate the

			IMG img = createImage(HtmlResources.IMAGE_TEXTPOPUP_2);

			Table table = new Table();
			table.setCellPadding(0);
			table.setCellSpacing(0);
			table.setBorder(0);

			TR tr = new TR();
			tr.setVAlign(AlignType.middle);
			table.addElement(tr);

			// Add the Input Element
			TD td = new TD();
			td.setAlign(AlignType.middle);
			td.addElement(super.doCreateTextArea());
			tr.addElement(td);

			// Add image
			td = new TD();
			td.setAlign(AlignType.middle);
			td.setVAlign(AlignType.top);
			td.setStyle("padding-left:5px;");
			td.addElement(img);
			tr.addElement(td);

			doAddDecorationsToRow(tr, 1);

			return table;
		} else {
			IMG img = createImage(HtmlResources.IMAGE_TEXTPOPUP_1);

			img.setAlt(html(getFrameworkString(HtmlResources.FW_TEXTPOPUP_BUTTON_ICON_ALT)));
			img.setTitle(html(getFrameworkString(HtmlResources.FW_TEXTPOPUP_BUTTON_ICON_TOOLTIP)));
			img.setID("btnTxtPopUp1");
			img.setVspace(3);

			// use a default
			String layout = getPainterContext().getResourceDir() + "textpopup/textpopup.jsp";

			// get the locale
			String localeStr = (getPainterContext().getLocale() == null) ? "null" : getPainterContext().getLocale().toString().toUpperCase();

			StringBuffer script = new StringBuffer();
			script
				.append("javascript:popupTextPopup('")
				.append(getStyleId())
				.append("','")
				.append(localeStr)
				.append("',")
				.append(cctrl.getMaxLength())
				.append(",")
				.append(cctrl.isReadonly())
				.append(",")
				.append(cctrl.getPopupWidth())
				.append(",")
				.append(cctrl.getPopupHeight())
				.append(",")
				.append(cctrl.getPopupRows())
				.append(",'")
				.append(getPainterContext().request().getContextPath())
				.append("/")
				.append(layout)
				.append("');");

			A a = new A();
			a.setHref(script.toString());
			a.addElement(img);

			Table table = new Table();
			table.setCellPadding(0);
			table.setCellSpacing(0);
			table.setBorder(0);

			TR tr = new TR();
			tr.setVAlign(AlignType.middle);
			table.addElement(tr);
		
			ConcreteElement textarea = super.doCreateTextArea();

			// Add the Input Element
			TD td = new TD();
			td.setAlign(AlignType.left);
			td.addElement(textarea);
			
			if  (getCtrl().getWidth() != null) {

				td.setWidth(getCtrl().getWidth());
				
				if (getCtrl().getStyle() == null) {
					textarea.setStyle("width: 100%;");
				} else {
					textarea.setStyle(StringHelp.strcat("width: 100%;", getCtrl().getStyle()));
				}
				
			} else if (getCtrl().getStyle() != null) {
				textarea.setStyle(getCtrl().getStyle());
			}
			
			tr.addElement(td);

			// Add Anchor
			td = new TD();
			td.setVAlign(AlignType.top);
			td.setStyle("padding-left:5px;");
			//td.setWidth("100%");
			td.addElement(a);
			tr.addElement(td);

			doAddDecorationsToRow(tr, 1);

			return table;
		}
	}
}