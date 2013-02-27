package presentation.formbeans.oCalendarSys;

import presentation.formbeans.objects.Irbholiday_Form;
import utils.formbeans.FormBeanContainer;

public class Action_delete_delete_event_Form extends FormBeanContainer {

	private Irbholiday_Form irbholiday_Form = null;

	public Irbholiday_Form getIrbholiday_Form() {
		if (irbholiday_Form == null) {
			irbholiday_Form = new Irbholiday_Form();
		}
		return irbholiday_Form;
	}

	public void setIrbholiday_Form(Irbholiday_Form Irbholiday_Form) {
		this.irbholiday_Form = Irbholiday_Form;
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
