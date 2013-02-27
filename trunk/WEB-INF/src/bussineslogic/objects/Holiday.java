package bussineslogic.objects;




import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import utils.Persistent;

/**
 * This class contains business object 'holiday'.
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class Holiday implements Persistent{

	
	//Code (primary key) of this holiday
	private String holidaycode;
	//Version of the current instance. This attribute is used by hibernate to control concurrent modifications.
	private int version;
	//Indicates whereas this holiday is deleted
	private boolean deleted=false;
	
	//definition of the specific attributes
	
Date start_date;
String end_date;
int days;
String description;
Personal holiday_personal;
Type_of_holidays type_of_holiday;

	/**
	 * Default Constructor which creates an empty holiday
	 */
	public Holiday(){
	}

	
	/**
	 * Returns the code (primary key) of this holiday. 
	 * @return a String with the code
	 */
	public String getHolidaycode() {
		return holidaycode;
	}

	/**
	 * Set the code (primary key) of this holiday
	 * @param holidaycode the String with the code
	 */
	public void setHolidaycode(String holidaycode) {
		this.holidaycode = holidaycode;
	}
	
	/**
	 * Returns the code (primary key) of this holiday. Convenience method which calls {@link bussineslogic.objects.Holiday#getHolidaycode() getHolidaycode()}
	 * @return a String with the code
	 */
	public String getCode() {
		return getHolidaycode();
	}

	/**
	 * Set the code (primary key) of this holiday. Convenience method which calls {@link bussineslogic.objects.Holiday#setHolidaycode(String) getHolidaycode(String)}
	 * @param holidaycode the String with the code
	 */
	public void setCode(String holidaycode) {
		setHolidaycode(holidaycode);
	}

	/**
	 * Returns the version of the current instance. This attribute is used by hibernate to control concurrent modifications.
	 * @return int with the version number.
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * Set the version of the current instance. This attribute is used by hibernate to control concurrent modifications.
	 * @param version int with the version number.
	 */
	public void setVersion(int version) {
		this.version = version;
	}
	
	/**
	 * Tests if this holiday has been deleted
	 * @return true if this holiday has been deleted
	 */
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * Sets the delete state of this holiday
	 * @param deleted true if the object is deleted.
	 */
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	/**
 * @return the start_date
 */
public Date getStart_date(){
	return start_date;
}

/**
* @param start_date the start_date to set.
*/
public void setStart_date(Date start_date){
	this.start_date=start_date;
}

/**
 * @return the end_date
 */
public String getEnd_date(){
	return end_date;
}

/**
* @param end_date the end_date to set.
*/
public void setEnd_date(String end_date){
	this.end_date=end_date;
}

/**
 * @return the days
 */
public int getDays(){
	return days;
}

/**
* @param days the days to set.
*/
public void setDays(int days){
	this.days=days;
}

/**
 * @return the description
 */
public String getDescription(){
	return description;
}

/**
* @param description the description to set.
*/
public void setDescription(String description){
	this.description=description;
}

/**
 * @return the holiday_personal
 */
public Personal getHoliday_personal(){
	return holiday_personal;
}

/**
* @param holiday_personal the holiday_personal to set.
*/
public void setHoliday_personal(Personal holiday_personal){
	this.holiday_personal=holiday_personal;
}

/**
 * @return the type_of_holiday
 */
public Type_of_holidays getType_of_holiday(){
	return type_of_holiday;
}

/**
* @param type_of_holiday the type_of_holiday to set.
*/
public void setType_of_holiday(Type_of_holidays type_of_holiday){
	this.type_of_holiday=type_of_holiday;
}


	
	
	@Override
	public String toString(){
		if(getCode()==null || getCode().equals("")) return "";
		
		
		return getCode();
	}

}