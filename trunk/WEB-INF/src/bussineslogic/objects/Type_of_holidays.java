package bussineslogic.objects;




import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import utils.Persistent;

/**
 * This class contains business object 'type_of_holidays'.
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class Type_of_holidays implements Persistent{

	
	//Code (primary key) of this type_of_holidays
	private String type_of_holidayscode;
	//Version of the current instance. This attribute is used by hibernate to control concurrent modifications.
	private int version;
	//Indicates whereas this type_of_holidays is deleted
	private boolean deleted=false;
	
	//definition of the specific attributes
	
String description;
Set<Holiday> itype_of_holiday=new HashSet<Holiday>();

	/**
	 * Default Constructor which creates an empty type_of_holidays
	 */
	public Type_of_holidays(){
	}

	
	/**
	 * Returns the code (primary key) of this type_of_holidays. 
	 * @return a String with the code
	 */
	public String getType_of_holidayscode() {
		return type_of_holidayscode;
	}

	/**
	 * Set the code (primary key) of this type_of_holidays
	 * @param type_of_holidayscode the String with the code
	 */
	public void setType_of_holidayscode(String type_of_holidayscode) {
		this.type_of_holidayscode = type_of_holidayscode;
	}
	
	/**
	 * Returns the code (primary key) of this type_of_holidays. Convenience method which calls {@link bussineslogic.objects.Type_of_holidays#getType_of_holidayscode() getType_of_holidayscode()}
	 * @return a String with the code
	 */
	public String getCode() {
		return getType_of_holidayscode();
	}

	/**
	 * Set the code (primary key) of this type_of_holidays. Convenience method which calls {@link bussineslogic.objects.Type_of_holidays#setType_of_holidayscode(String) getType_of_holidayscode(String)}
	 * @param type_of_holidayscode the String with the code
	 */
	public void setCode(String type_of_holidayscode) {
		setType_of_holidayscode(type_of_holidayscode);
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
	 * Tests if this type_of_holidays has been deleted
	 * @return true if this type_of_holidays has been deleted
	 */
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * Sets the delete state of this type_of_holidays
	 * @param deleted true if the object is deleted.
	 */
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
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
 * @return the itype_of_holiday
 */
public Set<Holiday> getItype_of_holiday(){
	return itype_of_holiday;
}

/**
* @param itype_of_holiday the itype_of_holiday to set.
*/
public void setItype_of_holiday(Set<Holiday> itype_of_holiday){
	this.itype_of_holiday=itype_of_holiday;
}
/**
* Adds a Holiday to the itype_of_holiday set.
* @param itype_of_holiday Holiday to be added
*/
public void addItype_of_holiday(Holiday itype_of_holiday){
	this.itype_of_holiday.add(itype_of_holiday);
}

/**
* Removes a Holiday to the itype_of_holiday set.
* @param itype_of_holiday Holiday to be removed
*/
public void removeItype_of_holiday(Holiday itype_of_holiday){
	this.itype_of_holiday.remove(itype_of_holiday);
}



	
	
	@Override
	public String toString(){
		if(getCode()==null || getCode().equals("")) return "";
		
		
		return getCode();
	}

}