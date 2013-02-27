package bussineslogic.objects;

import java.util.Date;

import utils.Persistent;

public class Irbholiday implements Persistent {
	
	public static int TYPE_VACANCES = 0;
	public static int TYPE_ASUMPTES_PROPIS = 1;
	public static int TYPE_FESTIU = 2;
	public static int TYPE_LIMIT_VANCANCES_ANY_ANTERIOR = 3;
	public static int TYPE_CURRENT_YEAR_FOR_HOLIDAYS = 4;
	
	public static int STATUS_DEMANAT = 0;
	public static int STATUS_APROVAT = 1;
	public static int STATUS_DISFRUTAT = 2;
	
	
	//Code (primary key) of this benefits
	private String irbholidaycode;
	//Version of the current instance. This attribute is used by hibernate to control concurrent modifications.
//	private int version;
	//Indicates whereas this benefits is deleted
//	private boolean deleted=false;
	
	//definition of the specific attributes
	
	Date initialdate;
	Date enddate;
	String description;
	int type;
	int status;
	int holidays;
	int previousyearholidays;
	int aps;
	
	Personal personal;
	
	String validationcode;

	public String getValidationcode() {
		return validationcode;
	}

	public void setValidationcode(String validationcode) {
		this.validationcode = validationcode;
	}

	public String getIrbholidaycode() {
		return irbholidaycode;
	}

	public void setIrbholidaycode(String irbholidaycode) {
		this.irbholidaycode = irbholidaycode;
	}

	public Date getInitialdate() {
		return initialdate;
	}

	public void setInitialdate(Date initialdate) {
		this.initialdate = initialdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Personal getPersonal() {
		return personal;
	}

	public void setPersonal(Personal personal) {
		this.personal = personal;
	}

	public String getCode() {
		return getIrbholidaycode();
	}

	public void setCode(String code) {
		setIrbholidaycode(code);
	}

	public int getHolidays() {
		return holidays;
	}

	public void setHolidays(int holidays) {
		this.holidays = holidays;
	}

	public int getPreviousyearholidays() {
		return previousyearholidays;
	}

	public void setPreviousyearholidays(int previousyearholidays) {
		this.previousyearholidays = previousyearholidays;
	}

	public int getAps() {
		return aps;
	}

	public void setAps(int aps) {
		this.aps = aps;
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
