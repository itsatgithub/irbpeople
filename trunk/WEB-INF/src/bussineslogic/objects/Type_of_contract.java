package bussineslogic.objects;




import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import utils.Persistent;

/**
 * This class contains business object 'type_of_contract'.
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class Type_of_contract implements Persistent{

	
	//Code (primary key) of this type_of_contract
	private String type_of_contractcode;
	//Version of the current instance. This attribute is used by hibernate to control concurrent modifications.
	private int version;
	
	private boolean is_irbs;
	//Indicates whereas this type_of_contract is deleted
	private boolean deleted=false;
	
	//definition of the specific attributes
	
String description;
Set<Professional> itype_of_contract=new HashSet<Professional>();

	/**
	 * Default Constructor which creates an empty type_of_contract
	 */
	public Type_of_contract(){
	}

	
	/**
	 * Returns the code (primary key) of this type_of_contract. 
	 * @return a String with the code
	 */
	public String getType_of_contractcode() {
		return type_of_contractcode;
	}

	/**
	 * Set the code (primary key) of this type_of_contract
	 * @param type_of_contractcode the String with the code
	 */
	public void setType_of_contractcode(String type_of_contractcode) {
		this.type_of_contractcode = type_of_contractcode;
	}
	
	/**
	 * Returns the code (primary key) of this type_of_contract. Convenience method which calls {@link bussineslogic.objects.Type_of_contract#getType_of_contractcode() getType_of_contractcode()}
	 * @return a String with the code
	 */
	public String getCode() {
		return getType_of_contractcode();
	}

	/**
	 * Set the code (primary key) of this type_of_contract. Convenience method which calls {@link bussineslogic.objects.Type_of_contract#setType_of_contractcode(String) getType_of_contractcode(String)}
	 * @param type_of_contractcode the String with the code
	 */
	public void setCode(String type_of_contractcode) {
		setType_of_contractcode(type_of_contractcode);
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
	 * Tests if this type_of_contract has been deleted
	 * @return true if this type_of_contract has been deleted
	 */
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * Sets the delete state of this type_of_contract
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
 * @return the itype_of_contract
 */
public Set<Professional> getItype_of_contract(){
	return itype_of_contract;
}

/**
* @param itype_of_contract the itype_of_contract to set.
*/
public void setItype_of_contract(Set<Professional> itype_of_contract){
	this.itype_of_contract=itype_of_contract;
}
/**
* Adds a Professional to the itype_of_contract set.
* @param itype_of_contract Professional to be added
*/
public void addItype_of_contract(Professional itype_of_contract){
	this.itype_of_contract.add(itype_of_contract);
}

/**
* Removes a Professional to the itype_of_contract set.
* @param itype_of_contract Professional to be removed
*/
public void removeItype_of_contract(Professional itype_of_contract){
	this.itype_of_contract.remove(itype_of_contract);
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