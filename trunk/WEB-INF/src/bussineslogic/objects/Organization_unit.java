package bussineslogic.objects;




import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import utils.Persistent;

/**
 * This class contains business object 'organization_unit'.
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class Organization_unit implements Persistent{

	
	//Code (primary key) of this organization_unit
	private String organization_unitcode;
	//Version of the current instance. This attribute is used by hibernate to control concurrent modifications.
	private int version;
	//Indicates whereas this organization_unit is deleted
	private boolean deleted=false;
	
	//definition of the specific attributes
	
String description;
Set<Unit> iorganization_unit=new HashSet<Unit>();

	/**
	 * Default Constructor which creates an empty organization_unit
	 */
	public Organization_unit(){
	}

	
	/**
	 * Returns the code (primary key) of this organization_unit. 
	 * @return a String with the code
	 */
	public String getOrganization_unitcode() {
		return organization_unitcode;
	}

	/**
	 * Set the code (primary key) of this organization_unit
	 * @param organization_unitcode the String with the code
	 */
	public void setOrganization_unitcode(String organization_unitcode) {
		this.organization_unitcode = organization_unitcode;
	}
	
	/**
	 * Returns the code (primary key) of this organization_unit. Convenience method which calls {@link bussineslogic.objects.Organization_unit#getOrganization_unitcode() getOrganization_unitcode()}
	 * @return a String with the code
	 */
	public String getCode() {
		return getOrganization_unitcode();
	}

	/**
	 * Set the code (primary key) of this organization_unit. Convenience method which calls {@link bussineslogic.objects.Organization_unit#setOrganization_unitcode(String) getOrganization_unitcode(String)}
	 * @param organization_unitcode the String with the code
	 */
	public void setCode(String organization_unitcode) {
		setOrganization_unitcode(organization_unitcode);
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
	 * Tests if this organization_unit has been deleted
	 * @return true if this organization_unit has been deleted
	 */
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * Sets the delete state of this organization_unit
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
 * @return the iorganization_unit
 */
public Set<Unit> getIorganization_unit(){
	return iorganization_unit;
}

/**
* @param iorganization_unit the iorganization_unit to set.
*/
public void setIorganization_unit(Set<Unit> iorganization_unit){
	this.iorganization_unit=iorganization_unit;
}
/**
* Adds a Unit to the iorganization_unit set.
* @param iorganization_unit Unit to be added
*/
public void addIorganization_unit(Unit iorganization_unit){
	this.iorganization_unit.add(iorganization_unit);
}

/**
* Removes a Unit to the iorganization_unit set.
* @param iorganization_unit Unit to be removed
*/
public void removeIorganization_unit(Unit iorganization_unit){
	this.iorganization_unit.remove(iorganization_unit);
}



	
	
	@Override
	public String toString(){
		if(getCode()==null || getCode().equals("")) return "";
		
		
		return getCode();
	}

}