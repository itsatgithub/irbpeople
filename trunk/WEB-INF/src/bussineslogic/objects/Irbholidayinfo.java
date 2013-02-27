package bussineslogic.objects;

import java.util.Date;

import utils.Persistent;

public class Irbholidayinfo implements Persistent {
	
	//Code (primary key) of this benefits
	private String irbholidayinfocode;
	//Version of the current instance. This attribute is used by hibernate to control concurrent modifications.
//	private int version;
	//Indicates whereas this benefits is deleted
//	private boolean deleted=false;
	
	//definition of the specific attributes
	
	Personal personal;
	int year;
	
	int holidays;
	int aps;
	int previousyearholidays;
	
	int pendingholidays;
	int pendingaps;
	int pendingpreviousyearholidays;
	
	int previousyearholidaysforyear;
	int holidaysforyear;
	int apsforyear;


	public String getIrbholidayinfocode() {
		return irbholidayinfocode;
	}

	public void setIrbholidayinfocode(String irbholidayinfocode) {
		this.irbholidayinfocode = irbholidayinfocode;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getPendingholidays() {
		return pendingholidays;
	}

	public void setPendingholidays(int pendingholidays) {
		this.pendingholidays = pendingholidays;
	}

	public int getPendingaps() {
		return pendingaps;
	}

	public void setPendingaps(int pendingaps) {
		this.pendingaps = pendingaps;
	}

	public Personal getPersonal() {
		return personal;
	}

	public void setPersonal(Personal personal) {
		this.personal = personal;
	}

	public String getCode() {
		return getIrbholidayinfocode();
	}

	public void setCode(String code) {
		setIrbholidayinfocode(code);
	}

	public int getHolidaysforyear() {
		return holidaysforyear;
	}

	public void setHolidaysforyear(int holidaysforyear) {
		this.holidaysforyear = holidaysforyear;
	}

	public int getApsforyear() {
		return apsforyear;
	}

	public void setApsforyear(int apsforyear) {
		this.apsforyear = apsforyear;
	}

    public int getPendingpreviousyearholidays() {
        return pendingpreviousyearholidays;
    }

    public void setPendingpreviousyearholidays(int pendingpreviousyearholidays) {
        this.pendingpreviousyearholidays = pendingpreviousyearholidays;
    }

    public int getPreviousyearholidaysforyear() {
        return previousyearholidaysforyear;
    }

    public void setPreviousyearholidaysforyear(int previousyearholidaysforyear) {
        this.previousyearholidaysforyear = previousyearholidaysforyear;
    }

    public int getHolidays() {
        return holidays;
    }

    public void setHolidays(int holidays) {
        this.holidays = holidays;
    }

    public int getAps() {
        return aps;
    }

    public void setAps(int aps) {
        this.aps = aps;
    }

    public int getPreviousyearholidays() {
        return previousyearholidays;
    }

    public void setPreviousyearholidays(int previousyearholidays) {
        this.previousyearholidays = previousyearholidays;
    }
	
	public int getRemainingholidays() {
	    return this.holidaysforyear - this.holidays - this.pendingholidays;
	}

	public int getRemainingaps() {
	    return this.apsforyear - this.aps - this.pendingaps;
	}
	
	public int getRemainingpreviousyearholidays() {
	    return this.previousyearholidaysforyear - this.previousyearholidays - this.pendingpreviousyearholidays;
	}
	
//	public int getVersion() {
//		return version;
//	}
//
//	public void setVersion(int version) {
//		this.version = version;
//	}
//
//	public boolean isDeleted() {
//		return deleted;
//	}
//
//	public void setDeleted(boolean deleted) {
//		this.deleted = deleted;
//	}
	
	


}
