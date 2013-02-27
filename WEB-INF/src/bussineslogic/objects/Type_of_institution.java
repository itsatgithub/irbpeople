package bussineslogic.objects;




import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import utils.Persistent;

/**
 * This class contains business object 'type_of_institution'.
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class Type_of_institution implements Persistent{

	
	//Code (primary key) of this type_of_institution
	private String type_of_institutioncode;
	//Version of the current instance. This attribute is used by hibernate to control concurrent modifications.
	private int version;
	//Indicates whereas this type_of_institution is deleted
	private boolean deleted=false;
	
	//definition of the specific attributes
	
String description;
Set<Work_experience> itype_of_institution=new HashSet<Work_experience>();

	/**
	 * Default Constructor which creates an empty type_of_institution
	 */
	public Type_of_institution(){
	}

	
	/**
	 * Returns the code (primary key) of this type_of_institution. 
	 * @return a String with the code
	 */
	public String getType_of_institutioncode() {
		return type_of_institutioncode;
	}

	/**
	 * Set the code (primary key) of this type_of_institution
	 * @param type_of_institutioncode the String with the code
	 */
	public void setType_of_institutioncode(String type_of_institutioncode) {
		this.type_of_institutioncode = type_of_institutioncode;
	}
	
	/**
	 * Returns the code (primary key) of this type_of_institution. Convenience method which calls {@link bussineslogic.objects.Type_of_institution#getType_of_institutioncode() getType_of_institutioncode()}
	 * @return a String with the code
	 */
	public String getCode() {
		return getType_of_institutioncode();
	}

	/**
	 * Set the code (primary key) of this type_of_institution. Convenience method which calls {@link bussineslogic.objects.Type_of_institution#setType_of_institutioncode(String) getType_of_institutioncode(String)}
	 * @param type_of_institutioncode the String with the code
	 */
	public void setCode(String type_of_institutioncode) {
		setType_of_institutioncode(type_of_institutioncode);
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
	 * Tests if this type_of_institution has been deleted
	 * @return true if this type_of_institution has been deleted
	 */
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * Sets the delete state of this type_of_institution
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
 * @return the itype_of_institution
 */
public Set<Work_experience> getItype_of_institution(){
	return itype_of_institution;
}

/**
* @param itype_of_institution the itype_of_institution to set.
*/
public void setItype_of_institution(Set<Work_experience> itype_of_institution){
	this.itype_of_institution=itype_of_institution;
}
/**
* Adds a Work_experience to the itype_of_institution set.
* @param itype_of_institution Work_experience to be added
*/
public void addItype_of_institution(Work_experience itype_of_institution){
	this.itype_of_institution.add(itype_of_institution);
}

/**
* Removes a Work_experience to the itype_of_institution set.
* @param itype_of_institution Work_experience to be removed
*/
public void removeItype_of_institution(Work_experience itype_of_institution){
	this.itype_of_institution.remove(itype_of_institution);
}



	
	
	@Override
	public String toString(){
		if(getCode()==null || getCode().equals("")) return "";
		
		
		return getCode();
	}

}