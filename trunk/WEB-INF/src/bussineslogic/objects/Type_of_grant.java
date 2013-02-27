package bussineslogic.objects;




import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import utils.Persistent;

/**
 * This class contains business object 'type_of_grant'.
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class Type_of_grant implements Persistent{

	
	//Code (primary key) of this type_of_grant
	private String type_of_grantcode;
	//Version of the current instance. This attribute is used by hibernate to control concurrent modifications.
	private int version;
	//Indicates whereas this type_of_grant is deleted
	private boolean deleted=false;
	
	//definition of the specific attributes
	
String description;
Set<Grant> itype_of_grant=new HashSet<Grant>();

	/**
	 * Default Constructor which creates an empty type_of_grant
	 */
	public Type_of_grant(){
	}

	
	/**
	 * Returns the code (primary key) of this type_of_grant. 
	 * @return a String with the code
	 */
	public String getType_of_grantcode() {
		return type_of_grantcode;
	}

	/**
	 * Set the code (primary key) of this type_of_grant
	 * @param type_of_grantcode the String with the code
	 */
	public void setType_of_grantcode(String type_of_grantcode) {
		this.type_of_grantcode = type_of_grantcode;
	}
	
	/**
	 * Returns the code (primary key) of this type_of_grant. Convenience method which calls {@link bussineslogic.objects.Type_of_grant#getType_of_grantcode() getType_of_grantcode()}
	 * @return a String with the code
	 */
	public String getCode() {
		return getType_of_grantcode();
	}

	/**
	 * Set the code (primary key) of this type_of_grant. Convenience method which calls {@link bussineslogic.objects.Type_of_grant#setType_of_grantcode(String) getType_of_grantcode(String)}
	 * @param type_of_grantcode the String with the code
	 */
	public void setCode(String type_of_grantcode) {
		setType_of_grantcode(type_of_grantcode);
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
	 * Tests if this type_of_grant has been deleted
	 * @return true if this type_of_grant has been deleted
	 */
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * Sets the delete state of this type_of_grant
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
 * @return the itype_of_grant
 */
public Set<Grant> getItype_of_grant(){
	return itype_of_grant;
}

/**
* @param itype_of_grant the itype_of_grant to set.
*/
public void setItype_of_grant(Set<Grant> itype_of_grant){
	this.itype_of_grant=itype_of_grant;
}
/**
* Adds a Grant to the itype_of_grant set.
* @param itype_of_grant Grant to be added
*/
public void addItype_of_grant(Grant itype_of_grant){
	this.itype_of_grant.add(itype_of_grant);
}

/**
* Removes a Grant to the itype_of_grant set.
* @param itype_of_grant Grant to be removed
*/
public void removeItype_of_grant(Grant itype_of_grant){
	this.itype_of_grant.remove(itype_of_grant);
}



	
	
	@Override
	public String toString(){
		if(getCode()==null || getCode().equals("")) return "";
		
		
		return getCode();
	}

}