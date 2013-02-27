/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/def/DefGaugePainter.java,v 1.15 2005/05/01 08:57:08 P001002 Exp $
 * $Revision: 1.15 $
 * $Date: 2005/05/01 08:57:08 $
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

import org.apache.ecs.AlignType;
import org.apache.ecs.ConcreteElement;
import org.apache.ecs.Entities;
import org.apache.ecs.StringElement;
import org.apache.ecs.html.A;
import org.apache.ecs.html.TD;
import org.apache.ecs.html.TR;
import org.apache.ecs.html.Table;

import com.cc.framework.ui.control.GaugeControl;
import com.cc.framework.ui.painter.PainterContext;
import com.cc.framework.ui.painter.PainterHelp;

/**
 * Painter for the gauge control
 *
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.15 $
 */
public class DefGaugePainter extends DefPainterBase {

	/**
	 * Constructor
	 * @param	painterContext	The PainterContext
	 * @param	ctrl			The Control to render
	 */
	public DefGaugePainter(PainterContext painterContext, GaugeControl ctrl) {
		super(painterContext);
	}

	/**
	 * Retrieves the control for this painter
	 *
	 * @return		Control
	 */
	protected GaugeControl getCtrl() {
		return (GaugeControl) getPainterContext().getControl();
	}

	/**
	 * Creates the gauge
	 *
	 * @return		HTML
	 */
	protected ConcreteElement doCreateGauge() {

		// calculate the total amount
		double total	= getCtrl().getTotalAmount();
		int remaining	= 100;

		String height	= (getCtrl().getHeight() == null) ? "10" : getCtrl().getHeight();
		String width	= (getCtrl().getWidth() == null) ? "100%" : getCtrl().getWidth();

		TR gaugeRow = new TR();

		for (int i = 0; i < getCtrl().size(); i++) {

			int segmentWidth = 0;

			if ((i + 1) == getCtrl().size()) {
				// use all available space for the last segment
				segmentWidth = remaining;
			} else {
				// calculate percentage
				segmentWidth = (int) (getCtrl().getAmount(i) / total * 100.0);

				remaining -= segmentWidth;
			}

			if (segmentWidth > 0) {
				A segment = new A()
					.addElement(createSpacer(height, "100%"));

				if (getCtrl().getHyperlink(i) != null) {
					segment.setHref(getCtrl().getHyperlink(i));
				}

				if (getCtrl().getTarget(i) != null) {
					segment.setTarget(getCtrl().getTarget(i));
				}

				if (getCtrl().getTooltip(i) != null) {
					segment.setTitle(html(getCtrl().getTooltip(i)));
				}

				PainterHelp.setScriptHandler(segment, getCtrl().getClientHandler(i));

				gaugeRow.addElement(new TD()
					.setBgColor(getCtrl().getColor(i).toHtml())
					.setWidth(Integer.toString(segmentWidth) + "%")
					.addElement(segment));
			}
		}

		Table control = new Table()
			.setAlign(AlignType.left)
			.setBorder(0)
			.setCellSpacing(0)
			.setCellPadding(0)
			.addElement(gaugeRow);

		if (getCtrl().getText() != null) {
			control.setWidth("100%");

			// check if the text should be html encoded or not
			String text = (getCtrl().getFilter()) ? html(getCtrl().getText()) : getCtrl().getText();

			// Nest the gauge and the text in one table
			control = new Table()
				.setAlign(AlignType.left)
				.setBorder(0)
				.setHeight("100%")
				.setCellSpacing(0)
				.setCellPadding(0)
				.addElement(new TR()
					.addElement(new TD(control)
						.setVAlign(AlignType.middle)
						.setWidth("74%")
						.setNoWrap(true))
					.addElement(new TD(Entities.NBSP + text)
						.setVAlign(AlignType.middle)
						.setAlign(getCtrl().getAlignment().toString())
						.setNoWrap(true)));
		}

		control.setWidth(width);

		if (getStyleId() != null) {
			control.setID(getStyleId());
		}

		if (getCtrl().getStyle() != null) {
			control.setStyle(getCtrl().getStyle());
		}

		if (getCtrl().getStyleClass() == null) {
			control.setClass(DefHtmlClass.GAUGE);
		} else {
			control.setClass(getCtrl().getStyleClass());
		}

		if (getCtrl().getSummary() != null) {
			control.setSummary(getCtrl().getSummary());
		}

		PainterHelp.setScriptHandler(control, getCtrl().getDesignModel());

		return control;
	}

	/**
	 * @see com.cc.framework.ui.painter.ControlPainter#doCreateElement()
	 */
	protected ConcreteElement doCreateElement() {

		// if no designmodel is specified then terminate
		if (getCtrl().getDesignModel() == null) {
			return null;
		}

		if (getCtrl().size() == 0) {
			String emptyText = getCtrl().getEmptyText();

			// if no emptytext was assigned we use the default
			if (emptyText == null) {
				emptyText = getFrameworkString(DefResourceMap.FW_EMPTY_GAUGE);
			} else {
				emptyText = localize(emptyText);
			}

			// check if the string  should be html encoded
			if (getCtrl().getFilter()) {
				emptyText = html(emptyText);
			}

			return new StringElement(emptyText);
		} else {
			return doCreateGauge();
		}
	}
}
