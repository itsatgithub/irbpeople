package bussineslogic.objects;




import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import utils.Persistent;

/**
 * This class contains business object 'type_of_benefit'.
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class Type_of_benefit implements Persistent{

	
	//Code (primary key) of this type_of_benefit
	private String type_of_benefitcode;
	//Version of the current instance. This attribute is used by hibernate to control concurrent modifications.
	private int version;
	//Indicates whereas this type_of_benefit is deleted
	private boolean deleted=false;
	
	//definition of the specific attributes
	
String description;
Set<Benefits> itype_of_benefit=new HashSet<Benefits>();

	/**
	 * Default Constructor which creates an empty type_of_benefit
	 */
	public Type_of_benefit(){
	}

	
	/**
	 * Returns the code (primary key) of this type_of_benefit. 
	 * @return a String with the code
	 */
	public String getType_of_benefitcode() {
		return type_of_benefitcode;
	}

	/**
	 * Set the code (primary key) of this type_of_benefit
	 * @param type_of_benefitcode the String with the code
	 */
	public void setType_of_benefitcode(String type_of_benefitcode) {
		this.type_of_benefitcode = type_of_benefitcode;
	}
	
	/**
	 * Returns the code (primary key) of this type_of_benefit. Convenience method which calls {@link bussineslogic.objects.Type_of_benefit#getType_of_benefitcode() getType_of_benefitcode()}
	 * @return a String with the code
	 */
	public String getCode() {
		return getType_of_benefitcode();
	}

	/**
	 * Set the code (primary key) of this type_of_benefit. Convenience method which calls {@link bussineslogic.objects.Type_of_benefit#setType_of_benefitcode(String) getType_of_benefitcode(String)}
	 * @param type_of_benefitcode the String with the code
	 */
	public void setCode(String type_of_benefitcode) {
		setType_of_benefitcode(type_of_benefitcode);
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
	 * Tests if this type_of_benefit has been deleted
	 * @return true if this type_of_benefit has been deleted
	 */
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * Sets the delete state of this type_of_benefit
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
 * @return the itype_of_benefit
 */
public Set<Benefits> getItype_of_benefit(){
	return itype_of_benefit;
}

/**
* @param itype_of_benefit the itype_of_benefit to set.
*/
public void setItype_of_benefit(Set<Benefits> itype_of_benefit){
	this.itype_of_benefit=itype_of_benefit;
}
/**
* Adds a Benefits to the itype_of_benefit set.
* @param itype_of_benefit Benefits to be added
*/
public void addItype_of_benefit(Benefits itype_of_benefit){
	this.itype_of_benefit.add(itype_of_benefit);
}

/**
* Removes a Benefits to the itype_of_benefit set.
* @param itype_of_benefit Benefits to be removed
*/
public void removeItype_of_benefit(Benefits itype_of_benefit){
	this.itype_of_benefit.remove(itype_of_benefit);
}



	
	
	@Override
	public String toString(){
		if(getCode()==null || getCode().equals("")) return "";
		
		
		return getCode();
	}

}