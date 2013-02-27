/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/html/HtmlColorPickerPainter.java,v 1.19 2005/08/02 19:15:00 P001002 Exp $
 * $Revision: 1.19 $
 * $Date: 2005/08/02 19:15:00 $
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

import com.cc.framework.ui.control.ColorPickerControl;
import com.cc.framework.ui.model.ColorPickerDesignModel;
import com.cc.framework.ui.painter.PainterContext;
import com.cc.framework.ui.painter.PainterContextAtributes;

/**
 * HTML painter for the color picker control
 * This painter generates the HTML which is necessary for the color picker control.
 * The control consist of a HTML input element followed by an img element,
 * which is embedded into an anchor. If the user clicks on the image an
 * new browser window opens which includes the palette.
 *
 * @author      <a href="mailto:gschulz@scc-gmbh.com">Gernot Schulz</a>
 * @version     $Revision: 1.19 $
 * @since       1.0
 */
public class HtmlColorPickerPainter extends HtmlTextPainter {

	/**
	 * Constructor for HtmlColorPickerPainter
	 *
	 * @param	painterContext	The PainterContext
	 * @param	ctrl			The Control to render
	 */
	public HtmlColorPickerPainter(PainterContext painterContext, ColorPickerControl ctrl) {
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

		ColorPickerControl cctrl = (ColorPickerControl) getCtrl();

		if (getPainterContext().isDisplayOnly()) {
			// For form type="display" we render a display only form element
			return super.doCreateElement();

		} else if (cctrl.isDisabled()) {
			// if the calendar was disabled, we only generate the
			// input field which contains the color value String
			// and a disabled icon

			IMG img = createImage(HtmlResources.IMAGE_COLORTABLE_2);

			Table table = new Table();
			table.setCellPadding(0);
			table.setCellSpacing(0);
			table.setBorder(0);

			TR tr = new TR();
			tr.setVAlign(AlignType.MIDDLE);
			table.addElement(tr);

			// Add the Input Element
			TD td = new TD();
			td.setAlign(AlignType.MIDDLE);
			td.addElement(super.doCreateElement());
			tr.addElement(td);

			td = new TD();
			td.setAlign(AlignType.MIDDLE);
			td.setStyle("padding-left:5px;");
			td.addElement(img);
			tr.addElement(td);

			doAddDecorationsToRow(tr, 1);

			return table;

		} else {
			int palette = ((ColorPickerDesignModel) getCtrl().getDesignModel()).getPalette();

			IMG img = createImage(HtmlResources.IMAGE_COLORTABLE_1);

			if (null != cctrl.getButtonAlt()) {
				img.setAlt(html(localize(cctrl.getButtonAlt())));
			}

			if (null != cctrl.getButtonTooltip()) {
				img.setTitle(html(localize(cctrl.getButtonTooltip())));
			}

			StringBuffer script = new StringBuffer();
			script
				.append("javascript:popupCPicker('")
				.append(getPainterContext().request().getContextPath() + "/" + getPainterContext().getResourceDir() + "colorpicker/picker.html")
				.append("','")
				.append(getStyleId())
				.append("','")
				.append(getPainterContext().getLocale())
				.append("','")
				.append(palette)
				.append("');");

			A a = new A();
			a.setAttributeQuoteChar('"');
			a.setHref(script.toString());
			a.addElement(img);

			Table table = new Table();
			table.setCellPadding(0);
			table.setCellSpacing(0);
			table.setBorder(0);

			TR tr = new TR();

			// add the input element
			TD td = new TD();
			td.setAlign(AlignType.MIDDLE);
			td.addElement(super.doCreateElement());
			tr.addElement(td);

			// add the anchor
			td = new TD();
			td.setAlign(AlignType.MIDDLE);
			td.setStyle("padding-left:5px;");
			td.addElement(a);
			tr.addElement(td);

			doAddDecorationsToRow(tr, 1);

			return table.addElement(tr);
		}
	}
}
