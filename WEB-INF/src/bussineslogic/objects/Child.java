package bussineslogic.objects;




import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import utils.Persistent;

/**
 * This class contains business object 'child'.
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class Child implements Persistent{

	
	//Code (primary key) of this child
	private String childcode;
	//Version of the current instance. This attribute is used by hibernate to control concurrent modifications.
	private int version;
	//Indicates whereas this child is deleted
	private boolean deleted=false;
	
	//definition of the specific attributes
	
Date birth_date;
String observations;
Personal child_personal;

	/**
	 * Default Constructor which creates an empty child
	 */
	public Child(){
	}

	
	/**
	 * Returns the code (primary key) of this child. 
	 * @return a String with the code
	 */
	public String getChildcode() {
		return childcode;
	}

	/**
	 * Set the code (primary key) of this child
	 * @param childcode the String with the code
	 */
	public void setChildcode(String childcode) {
		this.childcode = childcode;
	}
	
	/**
	 * Returns the code (primary key) of this child. Convenience method which calls {@link bussineslogic.objects.Child#getChildcode() getChildcode()}
	 * @return a String with the code
	 */
	public String getCode() {
		return getChildcode();
	}

	/**
	 * Set the code (primary key) of this child. Convenience method which calls {@link bussineslogic.objects.Child#setChildcode(String) getChildcode(String)}
	 * @param childcode the String with the code
	 */
	public void setCode(String childcode) {
		setChildcode(childcode);
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
	 * Tests if this child has been deleted
	 * @return true if this child has been deleted
	 */
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * Sets the delete state of this child
	 * @param deleted true if the object is deleted.
	 */
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	/**
 * @return the birth_date
 */
public Date getBirth_date(){
	return birth_date;
}

/**
* @param birth_date the birth_date to set.
*/
public void setBirth_date(Date birth_date){
	this.birth_date=birth_date;
}

/**
 * @return the observations
 */
public String getObservations(){
	return observations;
}

/**
* @param observations the observations to set.
*/
public void setObservations(String observations){
	this.observations=observations;
}

/**
 * @return the child_personal
 */
public Personal getChild_personal(){
	return child_personal;
}

/**
* @param child_personal the child_personal to set.
*/
public void setChild_personal(Personal child_personal){
	this.child_personal=child_personal;
}


	
	
	@Override
	public String toString(){
		if(getCode()==null || getCode().equals("")) return "";
		
		
		return getCode();
	}

}