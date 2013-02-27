package bussineslogic.objects;




import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import utils.Persistent;

/**
 * This class contains business object 'grant'.
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class Grant implements Persistent{

	
	//Code (primary key) of this grant
	private String grantcode;
	//Version of the current instance. This attribute is used by hibernate to control concurrent modifications.
	private int version;
	
	boolean is_irbs;
	//Indicates whereas this grant is deleted
	private boolean deleted=false;
	
	//definition of the specific attributes
	
String description;
Type_of_grant type_of_grant;
Set<Grant_concession> igrant=new HashSet<Grant_concession>();

	/**
	 * Default Constructor which creates an empty grant
	 */
	public Grant(){
	}

	
	/**
	 * Returns the code (primary key) of this grant. 
	 * @return a String with the code
	 */
	public String getGrantcode() {
		return grantcode;
	}

	/**
	 * Set the code (primary key) of this grant
	 * @param grantcode the String with the code
	 */
	public void setGrantcode(String grantcode) {
		this.grantcode = grantcode;
	}
	
	/**
	 * Returns the code (primary key) of this grant. Convenience method which calls {@link bussineslogic.objects.Grant#getGrantcode() getGrantcode()}
	 * @return a String with the code
	 */
	public String getCode() {
		return getGrantcode();
	}

	/**
	 * Set the code (primary key) of this grant. Convenience method which calls {@link bussineslogic.objects.Grant#setGrantcode(String) getGrantcode(String)}
	 * @param grantcode the String with the code
	 */
	public void setCode(String grantcode) {
		setGrantcode(grantcode);
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
	 * Tests if this grant has been deleted
	 * @return true if this grant has been deleted
	 */
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * Sets the delete state of this grant
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
 * @return the type_of_grant
 */
public Type_of_grant getType_of_grant(){
	return type_of_grant;
}

/**
* @param type_of_grant the type_of_grant to set.
*/
public void setType_of_grant(Type_of_grant type_of_grant){
	this.type_of_grant=type_of_grant;
}

/**
 * @return the igrant
 */
public Set<Grant_concession> getIgrant(){
	return igrant;
}

/**
* @param igrant the igrant to set.
*/
public void setIgrant(Set<Grant_concession> igrant){
	this.igrant=igrant;
}
/**
* Adds a Grant_concession to the igrant set.
* @param igrant Grant_concession to be added
*/
public void addIgrant(Grant_concession igrant){
	this.igrant.add(igrant);
}

/**
* Removes a Grant_concession to the igrant set.
* @param igrant Grant_concession to be removed
*/
public void removeIgrant(Grant_concession igrant){
	this.igrant.remove(igrant);
}



	
	
	@Override
	public String toString(){
		if(getCode()==null || getCode().equals("")) return "";
		
		
		return getCode();
	}


	public boolean isIs_irbs() {
		return is_irbs;
	}


	public void setIs_irbs(boolean is_irbs) {
		this.is_irbs = is_irbs;
	}

}