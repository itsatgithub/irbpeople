package presentation.formbeans.oCalendarSys;




import presentation.formbeans.objects.Irbholidayinfo_Form;
import presentation.formbeans.objects.Personal_Form;
import utils.formbeans.FormBeanContainer;

/**
 * This class is a FormBeanContainer for the action action_eventcalendar.
 * 
 * @author Factory - JustInMind SL
 */
public class Action_manage_personal_calendar_Form extends FormBeanContainer {

	
	//FormBean for the action
	
	private Irbholidayinfo_Form irbholidayinfo_Form = null;

	public Irbholidayinfo_Form getIrbholidayinfo_Form() {
		if(irbholidayinfo_Form == null)
			irbholidayinfo_Form = new Irbholidayinfo_Form();
		return irbholidayinfo_Form;
	}

	public void setIrbholidayinfo_Form(
			Irbholidayinfo_Form irbholidayinfo_Form) {
		
		this.irbholidayinfo_Form = irbholidayinfo_Form;
	}
	
	public String getPersonalcode() {
		return getIrbholidayinfo_Form().getPersonal().getPersonalcode();
	}
	
	public void setPersonalcode(String personalcode) {
		getIrbholidayinfo_Form().getPersonal().setPersonalcode(personalcode);
	}
	
	public String getIrbholidayinfocode() {
		return getIrbholidayinfo_Form().getIrbholidayinfocode();
	}
	
	public void setIrbholidayinfocode(String irbholidayinfocode) {
		getIrbholidayinfo_Form().setIrbholidayinfocode(irbholidayinfocode);
	}
	
	
	
}