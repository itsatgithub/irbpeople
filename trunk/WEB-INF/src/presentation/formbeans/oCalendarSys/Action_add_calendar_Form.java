package presentation.formbeans.oCalendarSys;

import presentation.formbeans.objects.Irbholiday_Form;
import presentation.formbeans.objects.Irbholidayinfo_Form;
import presentation.formbeans.objects.Personal_IDForm;
import utils.formbeans.FormBeanContainer;

public class Action_add_calendar_Form extends FormBeanContainer {

	private Irbholiday_Form irbholiday_Form = null;
	private Irbholidayinfo_Form irbholidayinfo_Form = null;
	private Personal_IDForm supervisor = null;

	public Irbholiday_Form getIrbholiday_Form() {
		if (irbholiday_Form == null) {
			irbholiday_Form = new Irbholiday_Form();
		}
		return irbholiday_Form;
	}
	
	public Irbholidayinfo_Form getIrbholidayinfo_Form() {
		if (irbholidayinfo_Form == null) {
			irbholidayinfo_Form = new Irbholidayinfo_Form();
		}
		return irbholidayinfo_Form;
	}

	public Personal_IDForm getSupervisor() {
		if (supervisor == null) {
			supervisor = new Personal_IDForm();
		}
		return supervisor;
	}

	public void setSupervisor(Personal_IDForm supervisor) {
		this.supervisor = supervisor;
	}

	public void setIrbholiday_Form(Irbholiday_Form Irbholiday_Form) {
		this.irbholiday_Form = Irbholiday_Form;
	}
	
	public void setIrbholidayinfo_Form(Irbholidayinfo_Form Irbholidayinfo_Form) {
		this.irbholidayinfo_Form = Irbholidayinfo_Form;
	}

	public String getIrbholidaycode() {
		return getIrbholiday_Form().getIrbholidaycode();
	}
	
	public void setIrbholidaycode(String code) {
		getIrbholiday_Form().setIrbholidaycode(code);
	}
	
	public String getInitialdate() {
		return getIrbholiday_Form().getInitialdate();
	}
	
	public void setInitialdate(String initialdate) {
		getIrbholiday_Form().setInitialdate(initialdate);
	}
	
	public String getEnddate() {
		return getIrbholiday_Form().getEnddate();
	}
	
	public void setEnddate(String enddate) {
		getIrbholiday_Form().setEnddate(enddate);
	}
	
	public String getPersonalcode() {
		return getIrbholiday_Form().getPersonal().getPersonalcode();
	}
	
	public void setPersonalcode(String code) {
		getIrbholiday_Form().getPersonal().setPersonalcode(code);
	}
	
}
