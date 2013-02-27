package utils.tags;

import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.struts.taglib.html.TextTag;

import utils.userUtils.UserUtils;

/**
 * Custom tag which is used to show an html input (for a form) to chose dates.
 * 
 * @author Automatika - JustInMind SL
 */
public class TagDate extends TextTag {
	/**
	 * Complete the processing of the tag. The nested tags here will restore all
	 * the original value for the tag itself and the nesting context.
	 * 
	 * @return int to describe the next step for the JSP processor
	 * @throws JspException
	 *             for the bad things JSP's do
	 */
	public int doEndTag() throws JspException {
		int i = super.doEndTag();
		/**
		 * Get date pattern from properties file.
		 */
		HttpServletRequest request = (HttpServletRequest) pageContext
				.getRequest();
		Locale l = UserUtils.getCurrentLocale(request);
		ResourceBundle apps = ResourceBundle.getBundle("Patterns", l);

		JspWriter out = pageContext.getOut();
		try {
			render(out, apps);
		} catch (Exception e) {
			throw new JspException(e.getMessage());
		}

		return i;
	}

	private void render(JspWriter out, ResourceBundle apps) throws Exception {
		String datePattern = apps.getString("date.pattern");

		String mondayShort = apps.getString("date.Monday.short");
		String tuesdayShort = apps.getString("date.Tuesday.short");
		String wednesdayShort = apps.getString("date.Wednesday.short");
		String thursdayShort = apps.getString("date.Thursday.short");
		String fridayShort = apps.getString("date.Friday.short");
		String saturdayShort = apps.getString("date.Saturday.short");
		String sundayShort = apps.getString("date.Sunday.short");

		String today = apps.getString("date.Today");
		String startDay = apps.getString("date.StartDay");

		String january = apps.getString("date.January");
		String february = apps.getString("date.February");
		String march = apps.getString("date.March");
		String april = apps.getString("date.April");
		String may = apps.getString("date.May");
		String june = apps.getString("date.June");
		String july = apps.getString("date.July");
		String august = apps.getString("date.August");
		String september = apps.getString("date.September");
		String october = apps.getString("date.October");
		String november = apps.getString("date.November");
		String december = apps.getString("date.December");
		String calendarName = apps.getString("date.Calendar");

		/**
		 * If date as parameter, transform it to human date type.
		 */
		String varName = "cal"
				+ Integer.toString((new Random()).nextInt(10000));

		/**
		 * Render the javascript that enables user interaction and set the
		 * language.
		 */
		out.println("<SCRIPT LANGUAGE=\"JavaScript\" >");
		out.println("var " + varName + " = new CalendarPopup();");
		out.println(varName + ".setYearSelectStartOffset(120);");
		
		out.println(varName + ".showNavigationDropdowns();");
		out.println(varName + ".setMonthNames('" + january + "','" + february
				+ "','" + march + "','" + april + "','" + may + "','" + june
				+ "','" + july + "','" + august + "','" + september + "','"
				+ october + "','" + november + "','" + december + "');");
		out.println(varName + ".setDayHeaders('" + sundayShort + "','"
				+ mondayShort + "','" + tuesdayShort + "','" + wednesdayShort
				+ "','" + thursdayShort + "','" + fridayShort + "','"
				+ saturdayShort + "');");
		out.println(varName + ".setWeekStartDay(" + startDay + ");");
		out.println(varName + ".setTodayText(\"" + today + "\");");
		out.println("</SCRIPT>");

		out
				.println("<input type=\"image\" onClick=\""
						+ varName
						+ ".select(this.form['"
						+ getProperty()
						+ "'],'"
						+ varName
						+ "','"
						+ datePattern
						+ "'); return false;\" TITLE=\""
						+ calendarName
						+ "\" NAME=\""
						+ varName
						+ "\" ID=\""
						+ varName
						+ "\" src=\"../images/CATEGORY/action-type/calendar.gif\" style=\"position:relative;top:0;left:0;border:0;\">");

	}

}
