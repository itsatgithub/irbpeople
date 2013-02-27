package presentation.formbeans.objects;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import uicomponents.scheduler.ICalendar;
import uicomponents.scheduler.SchedulerFacadeForCommonControlsScheduler;
import utils.dateformat.MultipleDateFormat;
import utils.validator.ValidatorFormAndAction;

public class Irbholiday_Form extends ValidatorFormAndAction implements ICalendar {

	String irbholidaycode;
//	String version;
	
	private String initialdate=null;
	private String enddate=null;
	private String description=null;
	private String type=null;
	private String status=null;
	private String validationcode=null;
	
	private String total=null;
	
	private Personal_Form personal=null;

	public String getIrbholidaycode() {
		return irbholidaycode;
	}

	public void setIrbholidaycode(String irbholidaycode) {
		this.irbholidaycode = irbholidaycode;
	}
	
	public String getInitialdate() {
		return initialdate;
	}

	public void setInitialdate(String initialdate) {
		this.initialdate = initialdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Personal_Form getPersonal() {
		if(personal == null) {
			personal = new Personal_Form();
		}
		return personal;
	}

	public void setPersonal(Personal_Form personal) {
		this.personal = personal;
	}
	
	public String getPersonalcode() {
		return getPersonal().getPersonalcode();
	}
	
	public void setPersonalcode(String personalcode) {
		getPersonal().setPersonalcode(personalcode);
	}

	public Calendar getCalendarEnddate(Object date, Locale l) {
		return SchedulerFacadeForCommonControlsScheduler.facadeTimeAdapter( getEnddate(),(MultipleDateFormat) date, l);
	}

	public String getCalendarId() {
		return getIrbholidaycode();
	}

	public Calendar getCalendarIntialdate(Object date, Locale l) {
		return SchedulerFacadeForCommonControlsScheduler.facadeTimeAdapter( getInitialdate(),(MultipleDateFormat) date, l);
	}

	public String getCalendarSubject() {
		return getDescription();
	}
	
	public static List<TypeIrbholiday_Form> getTypes(Locale l) {
		
		ResourceBundle apps = ResourceBundle.getBundle("MessageResources", l);
		
		List<TypeIrbholiday_Form> list = new ArrayList<TypeIrbholiday_Form>();
		TypeIrbholiday_Form form = new TypeIrbholiday_Form();
		form.setTypeirbholidaycode(ICalendar.TYPE_VACANCES);
		form.setDescription(apps.getString(ICalendar.DESCRIPTION_VACANCES));
		list.add(form);
		form = new TypeIrbholiday_Form();
		form.setTypeirbholidaycode(ICalendar.TYPE_ASUMPTES_PROPIS);
		form.setDescription(apps.getString(ICalendar.DESCRIPTION_ASUMPTES_PROPIS));
		list.add(form);
		
		return list;
	}
	
	public static String getType(String code, Locale l) {
		
		ResourceBundle apps = ResourceBundle.getBundle("MessageResources", l);
		
		String result;
		if(code.equals(ICalendar.TYPE_FESTIU)) {
			result = ICalendar.DESCRIPTION_FESTIU;
		} else if(code.equals(ICalendar.TYPE_ASUMPTES_PROPIS)) {
			result = ICalendar.DESCRIPTION_ASUMPTES_PROPIS;
		} else if(code.equals(ICalendar.TYPE_VACANCES)) {
			result = ICalendar.DESCRIPTION_VACANCES;
		} else if(code.equals(ICalendar.TYPE_LIMIT_VANCANCES_ANY_ANTERIOR )) {
			result = ICalendar.DESCRIPTION_LIMIT_VACANCES_ANY_ANTERIOR;
		} else {
			return "";
		}
		
		try {
			return apps.getString(result);
		} catch(Exception e) {
			return "";
		}
		
	}
	
	public static String getStatus(String code, Locale l) {
			
		ResourceBundle apps = ResourceBundle.getBundle("MessageResources", l);
		
		String result;
		if(code.equals(ICalendar.STATUS_APROVAT)) {
			result = ICalendar.DESCRIPTION_APROVAT;
		} else if(code.equals(ICalendar.STATUS_DEMANAT)) {
			result = ICalendar.DESCRIPTION_DEMANAT;
		} else if(code.equals(ICalendar.STATUS_DISFRUTAT)) {
			result = ICalendar.DESCRIPTION_DISFRUTAT;
		} else {
			return "";
		}
		
		try {
			return apps.getString(result);
		} catch(Exception e) {
			return "";
		}
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getValidationcode() {
		return validationcode;
	}

	public void setValidationcode(String validationcode) {
		this.validationcode = validationcode;
	}
	
	
//	public String getVersion() {
//		return version;
//	}
//	public void setVersion(String version) {
//		this.version = version;
//	}


}
