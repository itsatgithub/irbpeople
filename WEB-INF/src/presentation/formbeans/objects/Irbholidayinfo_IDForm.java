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

public class Irbholidayinfo_IDForm extends ValidatorFormAndAction {

	String irbholidayinfocode;
//	String version;
	
	private String year=null;
	private String holidays=null;
	private String pendingholidays=null;
	private String aps=null;
	private String pendingaps=null;
	private String previousyearholidays=null;
	private String holidaysforyear=null;
	private String apsforyear=null;

	public String getIrbholidayinfocode() {
		return irbholidayinfocode;
	}

	public void setIrbholidayinfocode(String irbholidayinfocode) {
		this.irbholidayinfocode = irbholidayinfocode;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getHolidays() {
		return holidays;
	}

	public void setHolidays(String holidays) {
		this.holidays = holidays;
	}

	public String getPendingholidays() {
		return pendingholidays;
	}

	public void setPendingholidays(String pendingholidays) {
		this.pendingholidays = pendingholidays;
	}

	public String getAps() {
		return aps;
	}

	public void setAps(String aps) {
		this.aps = aps;
	}

	public String getPendingaps() {
		return pendingaps;
	}

	public void setPendingaps(String pendingaps) {
		this.pendingaps = pendingaps;
	}

	public String getPreviousyearholidays() {
		return previousyearholidays;
	}

	public void setPreviousyearholidays(String previousyearholidays) {
		this.previousyearholidays = previousyearholidays;
	}

	public String getHolidaysforyear() {
		return holidaysforyear;
	}

	public void setHolidaysforyear(String holidaysforyear) {
		this.holidaysforyear = holidaysforyear;
	}

	public String getApsforyear() {
		return apsforyear;
	}

	public void setApsforyear(String apsforyear) {
		this.apsforyear = apsforyear;
	}


//	public String getVersion() {
//		return version;
//	}
//	public void setVersion(String version) {
//		this.version = version;
//	}


}
