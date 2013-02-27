package presentation.formbeans.oCalendarSys;

import presentation.formbeans.objects.Irbholiday_Form;
import presentation.formbeans.objects.Irbholidayinfo_Form;
import presentation.formbeans.objects.Personal_Form;
import utils.formbeans.FormBeanContainer;

public class Action_new_new_year_Form extends FormBeanContainer {

	private Irbholidayinfo_Form irbholidayinfo_Form = null;
	
	public Irbholidayinfo_Form getIrbholidayinfo_Form() {
		if (irbholidayinfo_Form == null) {
			irbholidayinfo_Form = new Irbholidayinfo_Form();
		}
		return irbholidayinfo_Form;
	}

	public void setIrbholidayinfo_Form(Irbholidayinfo_Form Irbholidayinfo_Form) {
		this.irbholidayinfo_Form = Irbholidayinfo_Form;
	}
	
	public String getIrbholidayinfocode() {
		return getIrbholidayinfo_Form().getIrbholidayinfocode();
	}
	
	public void setIrbholidayinfocode(String irbholidayinfocode) {
		getIrbholidayinfo_Form().setIrbholidayinfocode(irbholidayinfocode);
	}
	
}
