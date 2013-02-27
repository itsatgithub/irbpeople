/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/html/HtmlCalendarPainter.java,v 1.34 2005/10/11 14:22:58 P001001 Exp $
 * $Revision: 1.34 $
 * $Date: 2005/10/11 14:22:58 $
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

import com.cc.framework.ui.control.CalendarControl;
import com.cc.framework.ui.model.CalendarMode;
import com.cc.framework.ui.model.imp.CalendarDesignModelImp;
import com.cc.framework.ui.painter.PainterContext;
import com.cc.framework.ui.painter.PainterContextAtributes;

/**
 * Painter for the calendar control.
 * This painter generates the HTML whch is necessary for the calendar control.
 * The control consist of a HTML input element followed by an img element,
 * which is embedded into an anchor. If the user clicks on the image an
 * new browser window opens which includes the calendar.
 *
 * @author      <a href="mailto:gschulz@scc-gmbh.com">gernot Schulz</a>
 * @version     $Revision: 1.34 $
 * @since       1.0
 */
public class HtmlCalendarPainter extends HtmlTextPainter {

	/**
	 * Constructor for HtmlCalendarPainter
	 * 
	 * @param painterContext
	 *            The PainterContext
	 * @param ctrl
	 *            The Control to render
	 */
	public HtmlCalendarPainter(PainterContext painterContext,
			CalendarControl ctrl) {
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

		CalendarControl cctrl = (CalendarControl) getCtrl();

		if (getPainterContext().isDisplayOnly()) {
			// For form type="display" we render a display only form element
			return super.doCreateElement();

		} else if (cctrl.isDisabled()) {
			// if the calendar was disabled, we generate the disabled
			// input field which contains the Date-Time String

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

			// check if teh button should be displayed
			if (cctrl.getShowButton()) {
				IMG img = createImage(HtmlResources.IMAGE_CALENDAR_2);

				// add the image
				td = new TD();
				td.setAlign(AlignType.MIDDLE);
				td.setStyle("padding-left:5px;");
				td.addElement(img);
				tr.addElement(td);
			}

			// check if the mask also should be displayed
			if (cctrl.getShowFormat() != null) {
				// get the datetime format mask
				String format = getFormat();

				// add the format string
				td = new TD();
				td.setAlign(AlignType.MIDDLE);
				td.addElement("(" + format + ")");
				td.setStyle("padding-left:5px;font-size:8pt;");

				if (cctrl.getShowFormat().equals(AlignType.RIGHT)) {
					tr.addElement(td);
				} else if (cctrl.getShowFormat().equals(AlignType.BOTTOM)) {
					tr = new TR().addElement(td.setColSpan(2).setAlign(AlignType.LEFT));
					table.addElement(tr);
				}
			}

			doAddDecorationsToRow(tr, 1);

			return table;

		} else  {
			// the calendar is not disabled

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

			// if the control should only display a formatted time or date
			// maybe no button is needed. So check if the button must be created
			if (cctrl.getShowButton()) {
				A a = doCreateImageLink();

				// add anchor
				td = new TD();
				td.setAlign(AlignType.MIDDLE);
				td.setStyle("padding-left:5px;");
				td.addElement(a);
				tr.addElement(td);
			}

			// check if the mask also should be displayed
			if (cctrl.getShowFormat() != null) {

				// get the datetime format mask
				String format = getFormat();

				// add the format string
				td = new TD();
				td.setAlign(AlignType.MIDDLE);
				td.addElement("(" + format + ")");
				td.setStyle("padding-left:5px;font-size:8pt;");

				if (cctrl.getShowFormat().equals(AlignType.RIGHT)) {
					tr.addElement(td);
				} else if (cctrl.getShowFormat().equals(AlignType.BOTTOM)) {
					tr = new TR().addElement(td.setColSpan(2).setAlign(AlignType.LEFT));
					table.addElement(tr);
				}
			}

			doAddDecorationsToRow(tr, 1);

			return table;
		}
	}

	/**
	 * Creates the calendar icon which is used to open the popup calendar.
	 * Therefore an anchor ans some javascript is needed.
	 * 
	 * @return Anchor Element which contains an image and the javascript to open
	 *         the calendar
	 */
	protected A doCreateImageLink() {

		CalendarControl cctrl = (CalendarControl) getCtrl();

		// create the image button to open the calendar
		IMG img = createImage(HtmlResources.IMAGE_CALENDAR_1);

		if (null != cctrl.getButtonAlt()) {
			img.setAlt(html(localize(cctrl.getButtonAlt())));
		}

		if (null != cctrl.getButtonTooltip()) {
			img.setTitle(html(localize(cctrl.getButtonTooltip())));
		}

		// assign the layout
		String layout = "";

		if (null != cctrl.getLayout()) {
			layout = cctrl.getLayout();
		} else {
			// use a default
			layout = getPainterContext().getFrameworkString(HtmlResources.HTML_FILE_JSP_CALENDAR); 
		}

		// get the datetime format mask
		String format = getFormat();

		// get the width
		String width = getPainterContext().getFrameworkString(HtmlResourceMap.FW_CALENDAR_WINDOW_WIDTH);

		// get the height
		String height = getPainterContext().getFrameworkString(HtmlResourceMap.FW_CALENDAR_WINDOW_HEIGHT);

		// get the locale
		String localeStr = (getPainterContext().getLocale() == null) ? "null" : getPainterContext().getLocale().toString().toUpperCase();

		A a = new A();
		
		String script = "";
		
		if (cctrl.getMode().equals(CalendarMode.INLINE)) {
			script = doCreateLinkInlineWindow(localeStr, format, width, height, layout);
			
			img.setOnClick(script);
			img.setStyle("cursor:pointer;cursor:hand;");
		} else  {
			script = doCreateLinkPopUpWindow(localeStr, format, width, height, layout);
			a.setHref(script);
		}

		// TODO
		// A a = new A();
		// a.setHref(script);
		a.addElement(img);

		return a;
	}

	/**
	 * Creates the javascript needed to open the popup calendar,
	 * which opens in a seperat new window.
	 * 
	 * @param localeStr The  Locale
	 * @param format The format mask
	 * @param width  The window width
	 * @param height The window height
	 * @param layout The layout template to use
	 * @return HTML String
	 */
	protected String doCreateLinkPopUpWindow(String localeStr, String format,
			String width, String height, String layout) {

		StringBuffer script = new StringBuffer();
		
		script
			.append("javascript:popupCalendar('")
		 	.append(getStyleId())
			.append("','")
			.append(localeStr)
		 	.append("','")
			.append(format)
			.append("',")
			.append(width)
			.append(",")
			.append(height)
			.append(",'")
			.append(getPainterContext().request().getContextPath())
			.append("/")
			.append(layout)
			.append("');");
		
		return script.toString();
	}
	
	/**
	 * Creates the javascript needed to open the inline calendar. 
	 * 
	 * @param localeStr The  Locale
	 * @param format The format mask
	 * @param width  The window width
	 * @param height The window height
	 * @param layout The layout template to use
	 * @return HTML String
	 */
	protected String doCreateLinkInlineWindow(String localeStr, String format, String width, String height, String layout) {

		StringBuffer script = new StringBuffer();
		
		script
			.append("showInlineCalendar(")
			.append("this, '")
		 	.append(getStyleId())
			.append("','")
			.append(localeStr)
			.append("','")
			.append(format)
			.append("',")
			.append(width)
			.append(",")
			.append(height)
			.append(",'")
			.append(getPainterContext().request().getContextPath())
			.append("/")
			.append(layout)
			.append("');");

		return script.toString();
	}
	
	/**
	 * Returns the DateTime pattern /format
	 * 
	 * @return The DateTime pattern /format
	 */
	protected String getFormat() {

		CalendarControl cctrl = (CalendarControl) getCtrl();

		// get the datetime format mask
		String format = "";

		if (null != cctrl.getFormat() && !"".equals(cctrl.getFormat())) {
			format = cctrl.getFormat();
		} else {
			// use the default
			format = CalendarDesignModelImp.DEFAULT_FORMATMASK;
		}

		return format;
	}
}
