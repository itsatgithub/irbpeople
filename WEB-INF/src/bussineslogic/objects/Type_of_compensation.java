package bussineslogic.objects;




import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import utils.Persistent;

/**
 * This class contains business object 'type_of_compensation'.
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class Type_of_compensation implements Persistent{

	
	//Code (primary key) of this type_of_compensation
	private String type_of_compensationcode;
	//Version of the current instance. This attribute is used by hibernate to control concurrent modifications.
	private int version;
	//Indicates whereas this type_of_compensation is deleted
	private boolean deleted=false;
	
	//definition of the specific attributes
	
String description;
Set<Compensation> itype_of_compensation=new HashSet<Compensation>();

	/**
	 * Default Constructor which creates an empty type_of_compensation
	 */
	public Type_of_compensation(){
	}

	
	/**
	 * Returns the code (primary key) of this type_of_compensation. 
	 * @return a String with the code
	 */
	public String getType_of_compensationcode() {
		return type_of_compensationcode;
	}

	/**
	 * Set the code (primary key) of this type_of_compensation
	 * @param type_of_compensationcode the String with the code
	 */
	public void setType_of_compensationcode(String type_of_compensationcode) {
		this.type_of_compensationcode = type_of_compensationcode;
	}
	
	/**
	 * Returns the code (primary key) of this type_of_compensation. Convenience method which calls {@link bussineslogic.objects.Type_of_compensation#getType_of_compensationcode() getType_of_compensationcode()}
	 * @return a String with the code
	 */
	public String getCode() {
		return getType_of_compensationcode();
	}

	/**
	 * Set the code (primary key) of this type_of_compensation. Convenience method which calls {@link bussineslogic.objects.Type_of_compensation#setType_of_compensationcode(String) getType_of_compensationcode(String)}
	 * @param type_of_compensationcode the String with the code
	 */
	public void setCode(String type_of_compensationcode) {
		setType_of_compensationcode(type_of_compensationcode);
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
	 * Tests if this type_of_compensation has been deleted
	 * @return true if this type_of_compensation has been deleted
	 */
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * Sets the delete state of this type_of_compensation
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
 * @return the itype_of_compensation
 */
public Set<Compensation> getItype_of_compensation(){
	return itype_of_compensation;
}

/**
* @param itype_of_compensation the itype_of_compensation to set.
*/
public void setItype_of_compensation(Set<Compensation> itype_of_compensation){
	this.itype_of_compensation=itype_of_compensation;
}
/**
* Adds a Compensation to the itype_of_compensation set.
* @param itype_of_compensation Compensation to be added
*/
public void addItype_of_compensation(Compensation itype_of_compensation){
	this.itype_of_compensation.add(itype_of_compensation);
}

/**
* Removes a Compensation to the itype_of_compensation set.
* @param itype_of_compensation Compensation to be removed
*/
public void removeItype_of_compensation(Compensation itype_of_compensation){
	this.itype_of_compensation.remove(itype_of_compensation);
}



	
	
	@Override
	public String toString(){
		if(getCode()==null || getCode().equals("")) return "";
		
		
		return getCode();
	}

}