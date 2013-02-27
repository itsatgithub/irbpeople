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

public class Irbholidayinfo_Form extends ValidatorFormAndAction {

	String irbholidayinfocode;
//	String version;
	
	private String year=null;
	
	private String holidays=null;
	private String aps=null;
	private String previousyearholidays=null;
	
	private String pendingholidays=null;
	
	private String pendingaps=null;
	private String pendingpreviousyearholidays=null;
	private String holidaysforyear=null;
	private String apsforyear=null;
	private String previousyearholidaysforyear=null;
	
	private Personal_IDForm personal=null;
	
	private String remainingholidays=null;
	private String remainingaps=null;
	private String remainingpreviousyearholidays=null;

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

	public String getPendingholidays() {
		return pendingholidays;
	}

	public void setPendingholidays(String pendingholidays) {
		this.pendingholidays = pendingholidays;
	}

	public String getPendingaps() {
		return pendingaps;
	}

	public void setPendingaps(String pendingaps) {
		this.pendingaps = pendingaps;
	}

	public Personal_IDForm getPersonal() {
		if(personal == null) {
			personal = new Personal_IDForm();
		}
		return personal;
	}

	public void setPersonal(Personal_IDForm personal) {
		this.personal = personal;
	}
	
	public String getPersonalcode() {
		return getPersonal().getPersonalcode();
	}
	
	public void setPersonalcode(String personalcode) {
		getPersonal().setPersonalcode(personalcode);
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

    public String getPendingpreviousyearholidays() {
        return pendingpreviousyearholidays;
    }

    public void setPendingpreviousyearholidays(String pendingpreviousyearholidays) {
        this.pendingpreviousyearholidays = pendingpreviousyearholidays;
    }

    public String getPreviousyearholidaysforyear() {
        return previousyearholidaysforyear;
    }

    public void setPreviousyearholidaysforyear(String previousyearholidaysforyear) {
        this.previousyearholidaysforyear = previousyearholidaysforyear;
    }

    public String getHolidays() {
        return holidays;
    }

    public void setHolidays(String holidays) {
        this.holidays = holidays;
    }

    public String getAps() {
        return aps;
    }

    public void setAps(String aps) {
        this.aps = aps;
    }

    public String getPreviousyearholidays() {
        return previousyearholidays;
    }

    public void setPreviousyearholidays(String previousyearholidays) {
        this.previousyearholidays = previousyearholidays;
    }

    public String getRemainingholidays() {
        return remainingholidays;
    }

    public void setRemainingholidays(String remainingholidays) {
        this.remainingholidays = remainingholidays;
    }

    public String getRemainingaps() {
        return remainingaps;
    }

    public void setRemainingaps(String remainingaps) {
        this.remainingaps = remainingaps;
    }

    public String getRemainingpreviousyearholidays() {
        return remainingpreviousyearholidays;
    }

    public void setRemainingpreviousyearholidays(String remainingpreviousyearholidays) {
        this.remainingpreviousyearholidays = remainingpreviousyearholidays;
    }


//	public String getVersion() {
//		return version;
//	}
//	public void setVersion(String version) {
//		this.version = version;
//	}


}
