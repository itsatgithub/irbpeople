package presentation.formbeans.oCalendarSys;

import presentation.formbeans.objects.Unit_IDForm;
import utils.formbeans.FormBeanContainer;

public class Action_export_holidays_Form extends FormBeanContainer {

	private String start_date = null;

	private String end_date = null;

	private Unit_IDForm unit = null;

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public Unit_IDForm getUnit() {
		if (unit == null)
			unit = new Unit_IDForm();
		return unit;
	}

	public void setUnit(Unit_IDForm unit) {
		this.unit = unit;
	}

}
