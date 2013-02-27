package presentation.formbeans.objects;

import java.util.Calendar;
import java.util.Locale;

import uicomponents.scheduler.ICalendar;
import uicomponents.scheduler.SchedulerFacadeForCommonControlsScheduler;
import utils.dateformat.MultipleDateFormat;
import utils.validator.ValidatorFormAndAction;

public class Irbholiday_IDForm extends ValidatorFormAndAction implements ICalendar {

	String irbholidaycode;
//	String version;
	
	private String initialdate=null;
	private String enddate=null;
	private String description=null;
	private String type=null;
	private String status=null;

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

	
	
//	public String getVersion() {
//		return version;
//	}
//	public void setVersion(String version) {
//		this.version = version;
//	}


}
