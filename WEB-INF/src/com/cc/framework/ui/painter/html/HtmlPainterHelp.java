/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/html/HtmlPainterHelp.java,v 1.4 2005/10/11 14:22:58 P001001 Exp $
 * $Revision: 1.4 $
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

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import com.cc.framework.taglib.util.BaseTag;
import com.cc.framework.ui.control.ButtonControl;
import com.cc.framework.ui.html.HtmlUtil;
import com.cc.framework.ui.javascript.JavaScript;
import com.cc.framework.ui.javascript.JavaScriptUtil;
import com.cc.framework.ui.model.ClientEvent;
import com.cc.framework.ui.model.imp.ButtonDesignModelImp;
import com.cc.framework.ui.painter.ControlPainter;
import com.cc.framework.ui.painter.PainterFactory;
import com.cc.framework.ui.painter.PainterHelp;
import com.cc.framework.util.StringHelp;

/**
 * Utility Class to add the javascript object "DTPRes" to a html page
 * and initalize the javascript object with the localized weekday
 * names, month names, button labels andd the window title.
 * The calendar.js file need this information to retrieve and
 * display the localized message strings. Predefined keys can
 * be used to specified the message resources within the resource
 * bundel for the application.
 * <ul>
 * 	<li>fw.calendar.button.ok.label      = Label for the OK button</li>
 * 	<li>fw.calendar.button.cancel.label  = Label for the Cancel button</li>
 * 	<li>fw.calendar.button.today.label   = Label for the Today button</li>
 * 	<li>fw.calendar.title                = Titel for the browser window</li>
 * </ul>
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Gernot Schulz</a>
 * @version    $Revision: 1.4 $
 * @since      1.4
 */
public abstract class HtmlPainterHelp {

	/**
	 * Constructor
	 */
	protected HtmlPainterHelp() {
		super();
	}

	/**
	 * Method encodeHtml
	 *
	 * @param	raw	Raw String
	 * @return	HTML encoded String or <code>null</code>
	 */
	protected static String html(String raw) {
		if (raw == null) {
			return null;
		} else {
			return HtmlUtil.encodeHtml(raw);
		}
	}

	/**
	 * Generate the JavaScript and initializ the DTPRes
	 * JavaScript object.
	 *
	 * @param		pageContext The JSP Page Context
	 */
	public static void createCalendarScript(PageContext pageContext) {
		createCalendarScript(pageContext, null);
	}

	/**
	 * Generate the JavaScript and initializ the DTPRes
	 * JavaScript object.
	 *
	 * @param		pageContext The JSP Page Context
	 * @param		localeName The name of the locale to use
	 */
	public static void createCalendarScript(PageContext pageContext, String localeName) {

		DateFormatSymbols dfs = null;

		Locale locale = PainterHelp.localeFromName(pageContext, localeName);

		// first check the message resources
		PainterFactory[] factories = PainterFactory.getFactoyStack(pageContext);

		String windowtitle		= PainterHelp.getFrameworkString(pageContext, HtmlResourceMap.FW_CALENDAR_WINDOW_TITLE, null, locale, false, factories);
		String labelOkBtn		= PainterHelp.getFrameworkString(pageContext, HtmlResourceMap.FW_CALENDAR_BUTTON_OK_LABEL, null, locale, false, factories);
		String labelCancelBtn	= PainterHelp.getFrameworkString(pageContext, HtmlResourceMap.FW_CALENDAR_BUTTON_CANCEL_LABEL, null, locale, false, factories);
		String labelTodayBtn	= PainterHelp.getFrameworkString(pageContext, HtmlResourceMap.FW_CALENDAR_BUTTON_TODAY_LABEL, null, locale, false, factories);

		String prevYearLabel	= PainterHelp.getFrameworkString(pageContext, HtmlResourceMap.FW_CALENDAR_IMAGE_PREVYEAR_TOOLTIP, null, locale, false, factories);
		String prevYearAlt		= PainterHelp.getFrameworkString(pageContext, HtmlResourceMap.FW_CALENDAR_IMAGE_PREVYEAR_ALT, null, locale, false, factories);
		String nextYearLabel	= PainterHelp.getFrameworkString(pageContext, HtmlResourceMap.FW_CALENDAR_IMAGE_NEXTYEAR_TOOLTIP, null, locale, false, factories);
		String nextYearAlt		= PainterHelp.getFrameworkString(pageContext, HtmlResourceMap.FW_CALENDAR_IMAGE_NEXTYEAR_ALT, null, locale, false, factories);

		String prevMonthLabel	= PainterHelp.getFrameworkString(pageContext, HtmlResourceMap.FW_CALENDAR_IMAGE_PREVMONTH_TOOLTIP, null, locale, false, factories);
		String prevMonthAlt		= PainterHelp.getFrameworkString(pageContext, HtmlResourceMap.FW_CALENDAR_IMAGE_PREVMONTH_ALT, null, locale, false, factories);
		String nextMonthLabel	= PainterHelp.getFrameworkString(pageContext, HtmlResourceMap.FW_CALENDAR_IMAGE_NEXTMONTH_TOOLTIP, null, locale, false, factories);
		String nextMonthAlt		= PainterHelp.getFrameworkString(pageContext, HtmlResourceMap.FW_CALENDAR_IMAGE_NEXTMONTH_ALT, null, locale, false, factories);

		
		// get the date format symbols
		if (locale == null) {
			dfs = new DateFormatSymbols();
		} else {
			dfs = new DateFormatSymbols(locale);
		}

		// now first check if the week and month names where
		// defined in the resource property file. Otherwise
		// try to get the week and month names by the api
		String[] weekdays = null;
		String[] months  = null;
		String[] amp = null;

		String weekdayString	= html(PainterHelp.getFrameworkString(pageContext, HtmlResourceMap.FW_CALENDAR_WEEKDAYS, null, locale, true, factories));
		String monthString		= html(PainterHelp.getFrameworkString(pageContext, HtmlResourceMap.FW_CALENDAR_MONTHS, null, locale, true, factories));
		String ampString		= html(PainterHelp.getFrameworkString(pageContext, HtmlResourceMap.FW_CALENDAR_AMPM, null, locale, true, factories));

		// check Weekday Names
		if (null != weekdayString) {
			weekdays = StringHelp.split(weekdayString, ",");
		} else {
			weekdays = removeEmptyElements(dfs.getWeekdays());
		}

		// Check Month Names
		if (null != monthString) {
			months = StringHelp.split(monthString, ",");
		} else {
			months = removeEmptyElements(dfs.getMonths());
		}

		// Check AmPm Strings
		if (null != ampString) {
			amp = StringHelp.split(ampString, ",");
		} else {
			amp = removeEmptyElements(dfs.getAmPmStrings());
		}

		String localeString;
		
		if (locale == null) {
			localeString = Locale.getDefault().toString().toUpperCase();
		} else {
			localeString = locale.toString().toUpperCase();
		}

		StringBuffer out = new StringBuffer()
			.append("DTPRes.setWeekdays(")
			.append("'")
			.append(localeString)
			.append("',")
			.append(createJSArrayString(weekdays))
			.append(");")

			.append("DTPRes.setMonths(")
			.append("'")
			.append(localeString)
			.append("',")
			.append(createJSArrayString(months))
			.append(");")

			.append("DTPRes.setAmPmStrings(")
			.append("'")
			.append(localeString)
			.append("',")
			.append(createJSArrayString(amp))
			.append(");")

			.append("DTPRes.setButtonLabel(")
			.append("'")
			.append(localeString)
			.append("',")
			.append("'OK',")
			.append("'")
			.append(JavaScriptUtil.encodeJavaScript(labelOkBtn))
			.append("');")

			.append("DTPRes.setButtonLabel(")
			.append("'")
			.append(localeString)
			.append("',")
			.append("'Cancel',")
			.append("'")
			.append(JavaScriptUtil.encodeJavaScript(labelCancelBtn))
			.append("');")

			.append("DTPRes.setButtonLabel(")
			.append("'")
			.append(localeString)
			.append("',")
			.append("'Today',")
			.append("'")
			.append(JavaScriptUtil.encodeJavaScript(labelTodayBtn))
			.append("');")

			// prev Year
			.append("DTPRes.setButtonLabel(")
			.append("'")
			.append(localeString)
			.append("',")
			.append("'PrevYearLabel',")
			.append("'")
			.append(JavaScriptUtil.encodeJavaScript(prevYearLabel))
			.append("');")

			.append("DTPRes.setButtonLabel(")
			.append("'")
			.append(localeString)
			.append("',")
			.append("'PrevYearAlt',")
			.append("'")
			.append(JavaScriptUtil.encodeJavaScript(prevYearAlt))
			.append("');")

			// next Year
			.append("DTPRes.setButtonLabel(")
			.append("'")
			.append(localeString)
			.append("',")
			.append("'NextYearLabel',")
			.append("'")
			.append(JavaScriptUtil.encodeJavaScript(nextYearLabel))
			.append("');")

			.append("DTPRes.setButtonLabel(")
			.append("'")
			.append(localeString)
			.append("',")
			.append("'NextYearAlt',")
			.append("'")
			.append(JavaScriptUtil.encodeJavaScript(nextYearAlt))
			.append("');")

			// prev Month
			.append("DTPRes.setButtonLabel(")
			.append("'")
			.append(localeString)
			.append("',")
			.append("'PrevMonthLabel',")
			.append("'")
			.append(JavaScriptUtil.encodeJavaScript(prevMonthLabel))
			.append("');")

			.append("DTPRes.setButtonLabel(")
			.append("'")
			.append(localeString)
			.append("',")
			.append("'PrevMonthAlt',")
			.append("'")
			.append(JavaScriptUtil.encodeJavaScript(prevMonthAlt))
			.append("');")

			// next Month
			.append("DTPRes.setButtonLabel(")
			.append("'")
			.append(localeString)
			.append("',")
			.append("'NextMonthLabel',")
			.append("'")
			.append(JavaScriptUtil.encodeJavaScript(nextMonthLabel))
			.append("');")

			.append("DTPRes.setButtonLabel(")
			.append("'")
			.append(localeString)
			.append("',")
			.append("'NextMonthAlt',")
			.append("'")
			.append(JavaScriptUtil.encodeJavaScript(nextMonthAlt))
			.append("');")
			
			.append("DTPRes.setWindowTitle(")
			.append("'")
			.append(localeString)
			.append("', '")
			.append(JavaScriptUtil.encodeJavaScript(windowtitle))
			.append("');");

		JavaScript script = new JavaScript(out.toString());

		// Write the Script to the Page
		JspWriter writer = pageContext.getOut();

		try {
			writer.println();
			writer.println("\t<!-- Calendar initialization Script -->");
			script.output(writer);
			writer.println("\t<!-- end -->");
		} catch (java.io.IOException ioe) {
			// No Action
		}
	}

	/**
	 * Writes the Framework includes into the current page
	 * 
	 * @param		pageContext PageContext
	 */
	public static void createFameworkIncludes(PageContext pageContext) {
		PainterFactory.createHeaderIncludes(pageContext);
	}

	/**
	 * Writes the html base tag to the output
	 * 
	 * @param		pageContext PageContext
	 */
	public static void createBase(PageContext pageContext) {
		BaseTag.writeBase(pageContext, null, null, null);
	}

	/**
	 * Writes a Button to the Page
	 * 
	 * @param		pageContext PageContext
	 * @param		name The buttons name
	 * @param		text The buttons label text
	 * @param		tooltip The buttons tooltip
	 * @param		styleId The buttons Style Id
	 * @param		onClick OnClick Handler
	 */
	public static void createButton(
		PageContext pageContext,
		String name,
		String text,
		String tooltip,
		String styleId,
		String onClick) {
	
		ButtonDesignModelImp button = new ButtonDesignModelImp();
		button.setName(name);
		button.setText(text);
		button.setTooltip(tooltip);
		button.setStyleId(styleId);
		button.setHandler(ClientEvent.ONCLICK, onClick);
		
		ButtonControl ctrl = new ButtonControl();
		ctrl.setDesignModel(button);
		
		ControlPainter painter = PainterFactory.createPainter(pageContext, ctrl);

		if (painter != null) {
			// Write the button to the Page
			JspWriter writer = pageContext.getOut();

			try {
				painter.paint(writer);
			} catch (java.io.IOException ioe) {
				// No Action
			}
		}
	}

	/**
	 * Retrieves a calendare resource
	 * 
	 * @param		pageContext PageContext
	 * @param		key The framework resource key
	 * @return		Resource value
	 */
	public static String getLiteral(
		PageContext pageContext,
		String key) {

		Locale locale = PainterHelp.localeFromName(pageContext, null);

		return PainterHelp.getFrameworkString(
			pageContext,
			key,
			null,
			locale,
			false,
			PainterFactory.getFactoyStack(pageContext));
	}

	/**
	 * Retrieves a calendare resource as a JavaScript String
	 * literal ( 'xxxx' )
	 * 
	 * @param		pageContext PageContext
	 * @param		key The framework resource key
	 * @return		Resource value in quotes
	 */
	public static String getStringLiteral(
		PageContext pageContext,
		String key) {

		return StringHelp.strcat(
			"'",
			JavaScriptUtil.encodeJavaScript(getLiteral(pageContext, key)),
			"'");
	}

	/**
	 * Removes the null elements from the array
	 *
	 * @param arr	Array to check
	 * @return	New Array without null elements
	 */
	protected static String[] removeEmptyElements(String[] arr) {

		ArrayList list = new ArrayList();

		for (int i = 0; i < arr.length; i++) {
			if (null != arr[i] && !"".equals(arr[i])) {
				list.add(arr[i]);
			}
		}

		return StringHelp.toStringArray(list.toArray());
	}

	/**
	 * Creates a String representation to initalize a
	 * JavaScript array. Examle ['item1', 'item2', ... , 'itemN']
	 *
	 * @param	arr The array which should be transformed
	 * 			into a String
	 * @return	The String representation for the array
	 */
	protected static String createJSArrayString(String[] arr) {

		StringBuffer out = new StringBuffer("[");

		for (int i = 0; i < arr.length; i++) {
			out
				.append("'")
				.append(JavaScriptUtil.encodeJavaScript(arr[i]))
				.append("'");

			if (i < (arr.length - 1)) {
				out.append(",");
			}
		}

		out.append("]");

		return out.toString();
	}
}
