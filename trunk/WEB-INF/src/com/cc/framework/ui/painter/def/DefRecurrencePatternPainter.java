/**
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/def/DefRecurrencePatternPainter.java,v 1.8 2005/08/24 17:50:46 P001002 Exp $
 * $Revision: 1.8 $
 * $Date: 2005/08/24 17:50:46 $
 *
 * ====================================================================
 *
 * Copyright (c) 2002 SCC Informationssysteme GmbH. All rights
 * reserved.
 *
 * ====================================================================
 */

package com.cc.framework.ui.painter.def;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Locale;
import java.util.Vector;

import org.apache.ecs.AlignType;
import org.apache.ecs.ConcreteElement;
import org.apache.ecs.ElementContainer;
import org.apache.ecs.StringElement;
import org.apache.ecs.html.BR;
import org.apache.ecs.html.Div;
import org.apache.ecs.html.Input;
import org.apache.ecs.html.Option;
import org.apache.ecs.html.Select;
import org.apache.ecs.html.Span;
import org.apache.ecs.html.TD;
import org.apache.ecs.html.TR;
import org.apache.ecs.html.Table;

import com.cc.framework.FrameworkResources;
import com.cc.framework.ui.control.RecurrencePatternControl;
import com.cc.framework.ui.javascript.JavaScript;
import com.cc.framework.ui.model.RecurrenceType;
import com.cc.framework.ui.painter.PainterContext;
import com.cc.framework.ui.painter.PainterContextAtributes;
import com.cc.framework.ui.painter.PainterHelp;
import com.cc.framework.util.CalendarHelp;

/**
 * Painter for the Recurrence Pattern control
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.8 $
 */
public class DefRecurrencePatternPainter extends DefPainterBase {
	
	/**
	 * Constructor
	 * @param	painterContext	The PainterContext
	 * @param	ctrl			The Control to render
	 */
	public DefRecurrencePatternPainter(PainterContext painterContext, RecurrencePatternControl ctrl) {
		super(painterContext);
	}

	/**
	 * Retrieves the control for this painter
	 *
	 * @return		Control
	 */
	protected RecurrencePatternControl getCtrl() {
		return (RecurrencePatternControl) getPainterContext().getControl();
	}

	/**
	 * Checks if the control should be rendered in disabled mode
	 * 
	 * @return		boolean <code>true</code> when the control
	 * 				should be rendered as read only
	 */
	protected boolean isDisabled() {
		return getCtrl().isDisabled() || getPainterContext().isDisplayOnly();
	}

	/**
	 * Utilitiy function to html encode a framework string
	 * 
	 * @param		resourceId Resource Identifier
	 * @return		HTML encoded string
	 */
	protected String htmlfw(String resourceId) {
		return html(getFrameworkString(resourceId));
	}

	/**
	 * This method works similar to java.text.MessageFormat with the only difference
	 * that it concatenates ConcreteElement's into an ElementContainer
	 * 
	 * @param		patternId Formatting pattern resource Id
	 * @param		elements The elements that should be placed in the
	 * 				markups
	 * @return		ElementContainer
	 */
	protected ElementContainer formatElements(String patternId, ConcreteElement[] elements) {
		return PainterHelp.formatElements(htmlfw(patternId), elements);
	}

	/**
	 * @see com.cc.framework.ui.painter.ControlPainter#doCreateElement()
	 */
	protected ConcreteElement doCreateElement() {
		// ensure that a style id will be generated
		getPainterContext().setAttribute(
			PainterContextAtributes.FORCE_STYLEID, Boolean.TRUE);

		Locale locale = getLocale();
		if (locale == null) {
			locale = Locale.getDefault();
		}

		String name = getCtrl().getControlName();

		Table control = new Table()
			.setCellPadding(0)
			.setCellSpacing(1)
			.setWidth("100%")
			.setBorder(0)
			.addElement(new TR()
				.addElement(new TD()
					.setNoWrap(true)
					.setWidth("20%")
					.addElement(doCreateTypeMenu(name, locale)))
				.addElement(new TD()
					.setNoWrap(true)
					.setVAlign(AlignType.top)
					.setWidth("80%")
					.addElement(doCreateSection(RecurrenceType.NONE, doCreateNone(name, locale)))
					.addElement(doCreateSection(RecurrenceType.DAILY, doCreateDay(name, locale)))
					.addElement(doCreateSection(RecurrenceType.WEEKLY, doCreateWeekly(name, locale)))
					.addElement(doCreateSection(RecurrenceType.MONTHLY, doCreateMonthly(name, locale)))
					.addElement(doCreateSection(RecurrenceType.YEARLY, doCreateYearly(name, locale)))));

		// optional script
		ElementContainer container = new ElementContainer(control);

		ConcreteElement script = doCreateScript(name);
		if (script != null) {
			container.addElement(script);
		}

		return container;
	}

	/**
	 * Creates the Java Script Code wihich is needed by
	 * the control
	 *
	 * @param		prefix The controls prefix
	 * @return		Java Script Code
	 */
	protected JavaScript doCreateScript(String prefix) {

		StringBuffer script = new StringBuffer()
			.append("var rc_")
			.append(getStyleId())
			.append(" = new RecurrenceControl('")
			.append(getStyleId())
			.append("', '")
			.append(prefix)
			.append("');");

		return new JavaScript(script.toString());
	}

	/**
	 * Creates the RecurrenceType options on the left side
	 * 
	 * @param		prefix The controls prefix
	 * @param		locale Locale to use
	 * @return		Options
	 */
	protected ElementContainer doCreateTypeMenu(String prefix, Locale locale) {
		RecurrenceType[] types =
			new RecurrenceType[] {
				RecurrenceType.NONE,
				RecurrenceType.DAILY,
				RecurrenceType.WEEKLY,
				RecurrenceType.MONTHLY,
				RecurrenceType.YEARLY };

		String[] text =
			new String[] {
				FrameworkResources.FW_SCHEDULER_RECURRENCETYPE_NONE,
				FrameworkResources.FW_SCHEDULER_RECURRENCETYPE_DAILY,
				FrameworkResources.FW_SCHEDULER_RECURRENCETYPE_WEEKLY,
				FrameworkResources.FW_SCHEDULER_RECURRENCETYPE_MONTHLY,
				FrameworkResources.FW_SCHEDULER_RECURRENCETYPE_YEARLY };

		ElementContainer container = new ElementContainer();
		for (int i = 0; i < types.length; i++) {
			if (i > 0) {
				container.addElement(new BR());
			}

			Input button = new Input(Input.radio, prefix + ".type", types[i].toString());
			button.setDisabled(isDisabled());

			if (types[i].equals(getCtrl().getType())) {
				button.setChecked(true);
			}

			container.addElement(button);
			container.addElement(htmlfw(text[i].toString()));
		}
		
		return container;
	}

	/**
	 * Creates the wrapper element for a section
	 * 
	 * @param		type recurrence type of the section
	 * @param		body sections body
	 * @return		section wrapper
	 */
	protected ConcreteElement doCreateSection(RecurrenceType type, ConcreteElement body) {
		Div section = new Div();
		section.setID(getStyleId() + type);

		if (!type.equals(getCtrl().getType())) {
			section.setStyle("display:none;");
		}

		if (body == null) {
			section.addElement(new Span(type.toString()));
		} else {
			section.addElement(body);
		}			

		return section;
	}

	/**
	 * Creates the Section for the "none" recurrence pattern
	 * 
	 * @param		prefix The controls prefix
	 * @param		locale Locale to use
	 * @return		Sections
	 */
	protected ConcreteElement doCreateNone(String prefix, Locale locale) {
		return new StringElement(htmlfw(FrameworkResources.FW_SCHEDULER_PATTERN_NONE));
	}

	/**
	 * Creates the Section for the "daily" recurrence pattern
	 * 
	 * @param		prefix The controls prefix
	 * @param		locale Locale to use
	 * @return		Sections
	 */
	protected ConcreteElement doCreateDay(String prefix, Locale locale) {
		return new Table()
			.addElement(new TR()
				.addElement(new TD()
					.addElement(formatElements(
						FrameworkResources.FW_SCHEDULER_PATTERN_DAILY_DAY,
						new ConcreteElement[] {
							new Input(Input.radio, prefix + ".dayDayMask", CalendarHelp.DOWM_ALLDAYS)
								.setDisabled(isDisabled())
								.setChecked(CalendarHelp.DOWM_ALLDAYS == getCtrl().getDayDayMask()),
							new Input(Input.text, prefix + ".dayInterval", getCtrl().getDayInterval())
								.setDisabled(isDisabled())
								.setSize(2)
								.setMaxlength(2)}))))
			.addElement(new TR()
				.addElement(new TD()
					.addElement(formatElements(
						FrameworkResources.FW_SCHEDULER_PATTERN_DAILY_WEEKDAY,
						new ConcreteElement[] {
							new Input(Input.radio, prefix + ".dayDayMask", CalendarHelp.DOWM_WEEKDAYS)
								.setDisabled(isDisabled())
								.setChecked(CalendarHelp.DOWM_WEEKDAYS == getCtrl().getDayDayMask())}))))
			.addElement(new TR()
				.addElement(new TD()
					.addElement(formatElements(
						FrameworkResources.FW_SCHEDULER_PATTERN_DAILY_WEEKENDDAY,
						new ConcreteElement[] {
							new Input(Input.radio, prefix + ".dayDayMask", CalendarHelp.DOWM_WEEKENDDAYS)
								.setDisabled(isDisabled())
								.setChecked(CalendarHelp.DOWM_WEEKENDDAYS == getCtrl().getDayDayMask())}))));
	}

	/**
	 * Creates the Section for the "weekly" recurrence pattern
	 * 
	 * @param		prefix The controls prefix
	 * @param		locale Locale to use
	 * @return		Sections
	 */
	protected ConcreteElement doCreateWeekly(String prefix, Locale locale) {
		String[] weekdays = new DateFormatSymbols(locale).getWeekdays();

		return new Table()
			.addElement(new TR()
				.addElement(new TD()
					.setColSpan(4)
					.addElement(formatElements(
						FrameworkResources.FW_SCHEDULER_PATTERN_WEEKLY,
						new ConcreteElement[] {
							new Input(Input.text, prefix + ".weekInterval", getCtrl().getWeekInterval())
								.setDisabled(isDisabled())
								.setSize(3)
								.setMaxlength(2)}))))
			.addElement(new TR()
				.addElement(new TD()
					.addElement(new Input(Input.checkbox, prefix + ".weekDayMask[1]", CalendarHelp.DOWM_SUNDAY)
						.setDisabled(isDisabled())
						.setChecked(getCtrl().getWeekDayMask(Calendar.SUNDAY)))
					.addElement(weekdays[Calendar.SUNDAY]))
				.addElement(new TD()
					.addElement(new Input(Input.checkbox, prefix + ".weekDayMask[2]", CalendarHelp.DOWM_MONDAY)
						.setDisabled(isDisabled())
						.setChecked(getCtrl().getWeekDayMask(Calendar.MONDAY)))
					.addElement(weekdays[Calendar.MONDAY]))
				.addElement(new TD()
					.addElement(new Input(Input.checkbox, prefix + ".weekDayMask[3]", CalendarHelp.DOWM_TUESDAY)
						.setDisabled(isDisabled())
						.setChecked(getCtrl().getWeekDayMask(Calendar.TUESDAY)))
					.addElement(weekdays[Calendar.TUESDAY]))
				.addElement(new TD()
					.addElement(new Input(Input.checkbox, prefix + ".weekDayMask[4]", CalendarHelp.DOWM_WEDNESDAY)
						.setDisabled(isDisabled())
						.setChecked(getCtrl().getWeekDayMask(Calendar.WEDNESDAY)))
					.addElement(weekdays[Calendar.WEDNESDAY])))
			.addElement(new TR()
				.addElement(new TD()
					.addElement(new Input(Input.checkbox, prefix + ".weekDayMask[5]", CalendarHelp.DOWM_THURSDAY)
						.setDisabled(isDisabled())
						.setChecked(getCtrl().getWeekDayMask(Calendar.THURSDAY)))
					.addElement(weekdays[Calendar.THURSDAY]))
				.addElement(new TD()
					.addElement(new Input(Input.checkbox, prefix + ".weekDayMask[6]", CalendarHelp.DOWM_FRIDAY)
						.setDisabled(isDisabled())
						.setChecked(getCtrl().getWeekDayMask(Calendar.FRIDAY)))
					.addElement(weekdays[Calendar.FRIDAY]))
				.addElement(new TD()
					.setColSpan(2)
					.addElement(new Input(Input.checkbox, prefix + ".weekDayMask[7]", CalendarHelp.DOWM_SATURDAY)
						.setDisabled(isDisabled())
						.setChecked(getCtrl().getWeekDayMask(Calendar.SATURDAY)))
					.addElement(weekdays[Calendar.SATURDAY])));
	}

	/**
	 * Creates the Section for the "monthly" recurrence pattern
	 * 
	 * @param		prefix The controls prefix
	 * @param		locale Locale to use
	 * @return		Sections
	 */
	protected ConcreteElement doCreateMonthly(String prefix, Locale locale) {
		return new Table()
			.addElement(new TR()
				.addElement(new TD()
					.addElement(formatElements(
						FrameworkResources.FW_SCHEDULER_PATTERN_MONTHLY,
						new ConcreteElement[] {
							new Input(Input.radio, prefix + ".monthSubtype", 0)
								.setDisabled(isDisabled())
								.setChecked(getCtrl().getMonthSubtype() == 0),
							new Input(Input.text, prefix + ".monthDayOfMonth", getCtrl().getMonthDayOfMonth())
								.setDisabled(isDisabled())
								.setSize(2)
								.setMaxlength(2),
							new Input(Input.text, prefix + ".monthInterval", getCtrl().getMonthInterval())
								.setDisabled(isDisabled())
								.setSize(2)
								.setMaxlength(2)}))))
			.addElement(new TD()
				.addElement(formatElements(
					FrameworkResources.FW_SCHEDULER_PATTERN_MONTHLY_NTH,
					new ConcreteElement[] {
						new Input(Input.radio, prefix + ".monthSubtype", 1)
							.setDisabled(isDisabled())
							.setChecked(getCtrl().getMonthSubtype() == 1),
						new Select(prefix + ".monthNthInstance", doCreateInstanceOptions(locale, getCtrl().getMonthNthInstance()))
							.setDisabled(isDisabled()),
						new Select(prefix + ".monthNthDayMask", doCreateDayMaskOptions(locale, getCtrl().getMonthNthDayMask()))
							.setDisabled(isDisabled()),
						new Input(Input.text, prefix + ".monthNthInterval", getCtrl().getMonthNthInterval())
							.setDisabled(isDisabled())
							.setSize(2)
							.setMaxlength(2)})));
	}

	/**
	 * Creates the Section for the "yearly" recurrence pattern
	 * 
	 * @param		prefix The controls prefix
	 * @param		locale Locale to use
	 * @return		Sections
	 */
	protected ConcreteElement doCreateYearly(String prefix, Locale locale) {
		return new Table()
			.addElement(new TR()
				.addElement(new TD()
					.addElement(formatElements(
						FrameworkResources.FW_SCHEDULER_PATTERN_YEARLY,
						new ConcreteElement[] {
							new Input(Input.radio, prefix + ".yearSubtype", 0)
								.setDisabled(isDisabled())
								.setChecked(getCtrl().getYearSubtype() == 0),
							new Select(prefix + ".yearMonthOfYear", doCreateMonthOptions(locale, getCtrl().getYearMonthOfYear()))
								.setDisabled(isDisabled()),
							new Input(Input.text, prefix + ".yearDayOfMonth", getCtrl().getYearDayOfMonth())
								.setDisabled(isDisabled())
								.setSize(2)
								.setMaxlength(2)}))))
			.addElement(new TD()
				.addElement(formatElements(
					FrameworkResources.FW_SCHEDULER_PATTERN_YEARLY_NTH,
					new ConcreteElement[] {
						new Input(Input.radio, prefix + ".yearSubtype", 1)
							.setDisabled(isDisabled())
							.setChecked(getCtrl().getYearSubtype() == 1),
						new Select(prefix + ".yearNthInstance", doCreateInstanceOptions(locale, getCtrl().getYearNthInstance()))
							.setDisabled(isDisabled()),
						new Select(prefix + ".yearNthDayMask", doCreateDayMaskOptions(locale, getCtrl().getYearNthDayMask()))
							.setDisabled(isDisabled()),
						new Select(prefix + ".yearNthMonthOfYear", doCreateMonthOptions(locale, getCtrl().getYearNthMonthOfYear()))
							.setDisabled(isDisabled())})));
	}

	/**
	 * Creates an option list with the day mask options
	 * 
	 * @param		locale Locale to use
	 * @param		selected The selected mask
	 * @return		Option Elements
	 */
	protected Option[] doCreateDayMaskOptions(Locale locale, int selected) {
		Vector options = new Vector();

		String[] weekdays = new DateFormatSymbols(locale).getWeekdays();

		// Every day
		int mask = CalendarHelp.DOWM_ALLDAYS;
		options.add(new Option(Integer.toString(mask))
			.addElement(htmlfw(FrameworkResources.FW_SCHEDULER_PATTERN_OPTION_DAY))
			.setSelected(selected == mask));

		mask = CalendarHelp.DOWM_WEEKDAYS;
		options.add(new Option(Integer.toString(mask))
			.addElement(htmlfw(FrameworkResources.FW_SCHEDULER_PATTERN_OPTION_WEEKDAY))
			.setSelected(selected == mask));

		mask = CalendarHelp.DOWM_WEEKENDDAYS;
		options.add(new Option(Integer.toString(mask))
			.addElement(htmlfw(FrameworkResources.FW_SCHEDULER_PATTERN_OPTION_WEEKENDDAY))
			.setSelected(selected == mask));

		// Weekdays
		for (int day = Calendar.SUNDAY; day <= Calendar.SATURDAY; day++) {
			mask = 1 << day;

			options.add(new Option(Integer.toString(mask))
				.addElement(weekdays[day])
				.setSelected(selected == mask));
		}
		
		return (Option[]) options.toArray(new Option[options.size()]);
	}

	/**
	 * Creates an option list with the twelve month names
	 * 
	 * @param		locale Locale to use
	 * @param		selected The selected month index
	 * @return		Option Elements
	 */
	protected Option[] doCreateMonthOptions(Locale locale, int selected) {
		Vector options = new Vector();

		String[] months = new DateFormatSymbols(locale).getMonths();

		for (int month = 0; month < 12; month++) {
			options.add(new Option(Integer.toString(month))
				.addElement(months[month])
				.setSelected(selected == month));
		}
		
		return (Option[]) options.toArray(new Option[options.size()]);
	}

	/**
	 * Creates an option list with the instance options
	 * 
	 * @param		locale Locale to use
	 * @param		selected The selected instance
	 * @return		Option Elements
	 */
	protected Option[] doCreateInstanceOptions(Locale locale, int selected) {
		Vector options = new Vector();

		options.add(new Option(Integer.toString(1))
			.addElement(htmlfw(FrameworkResources.FW_SCHEDULER_PATTERN_OPTION_FIRST))
			.setSelected(selected == 1));

		options.add(new Option(Integer.toString(2))
			.addElement(htmlfw(FrameworkResources.FW_SCHEDULER_PATTERN_OPTION_SECOND))
			.setSelected(selected == 2));

		options.add(new Option(Integer.toString(3))
			.addElement(htmlfw(FrameworkResources.FW_SCHEDULER_PATTERN_OPTION_THIRD))
			.setSelected(selected == 3));

		options.add(new Option(Integer.toString(4))
			.addElement(htmlfw(FrameworkResources.FW_SCHEDULER_PATTERN_OPTION_FOURTH))
			.setSelected(selected == 4));

		options.add(new Option(Integer.toString(-1))
			.addElement(htmlfw(FrameworkResources.FW_SCHEDULER_PATTERN_OPTION_LAST))
			.setSelected(selected == -1));

		return (Option[]) options.toArray(new Option[options.size()]);
	}
}