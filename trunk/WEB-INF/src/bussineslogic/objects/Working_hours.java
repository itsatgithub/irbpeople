package bussineslogic.objects;




import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import utils.Persistent;

/**
 * This class contains business object 'working_hours'.
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class Working_hours implements Persistent{

	
	//Code (primary key) of this working_hours
	private String working_hourscode;
	//Version of the current instance. This attribute is used by hibernate to control concurrent modifications.
	private int version;
	//Indicates whereas this working_hours is deleted
	private boolean deleted=false;
	
	//definition of the specific attributes
	
String description;
Set<Personal> iworking_hours=new HashSet<Personal>();

	/**
	 * Default Constructor which creates an empty working_hours
	 */
	public Working_hours(){
	}

	
	/**
	 * Returns the code (primary key) of this working_hours. 
	 * @return a String with the code
	 */
	public String getWorking_hourscode() {
		return working_hourscode;
	}

	/**
	 * Set the code (primary key) of this working_hours
	 * @param working_hourscode the String with the code
	 */
	public void setWorking_hourscode(String working_hourscode) {
		this.working_hourscode = working_hourscode;
	}
	
	/**
	 * Returns the code (primary key) of this working_hours. Convenience method which calls {@link bussineslogic.objects.Working_hours#getWorking_hourscode() getWorking_hourscode()}
	 * @return a String with the code
	 */
	public String getCode() {
		return getWorking_hourscode();
	}

	/**
	 * Set the code (primary key) of this working_hours. Convenience method which calls {@link bussineslogic.objects.Working_hours#setWorking_hourscode(String) getWorking_hourscode(String)}
	 * @param working_hourscode the String with the code
	 */
	public void setCode(String working_hourscode) {
		setWorking_hourscode(working_hourscode);
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
	 * Tests if this working_hours has been deleted
	 * @return true if this working_hours has been deleted
	 */
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * Sets the delete state of this working_hours
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
 * @return the iworking_hours
 */
public Set<Personal> getIworking_hours(){
	return iworking_hours;
}

/**
* @param iworking_hours the iworking_hours to set.
*/
public void setIworking_hours(Set<Personal> iworking_hours){
	this.iworking_hours=iworking_hours;
}
/**
* Adds a Personal to the iworking_hours set.
* @param iworking_hours Personal to be added
*/
public void addIworking_hours(Personal iworking_hours){
	this.iworking_hours.add(iworking_hours);
}

/**
* Removes a Personal to the iworking_hours set.
* @param iworking_hours Personal to be removed
*/
public void removeIworking_hours(Personal iworking_hours){
	this.iworking_hours.remove(iworking_hours);
}



	
	
	@Override
	public String toString(){
		if(getCode()==null || getCode().equals("")) return "";
		
		
		return getCode();
	}

}