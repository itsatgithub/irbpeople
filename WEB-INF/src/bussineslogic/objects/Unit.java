package bussineslogic.objects;




import java.util.HashSet;
import java.util.Set;

import utils.Persistent;

/**
 * This class contains business object 'unit'.
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class Unit implements Persistent{

	
	//Code (primary key) of this unit
	private String unitcode;
	//Version of the current instance. This attribute is used by hibernate to control concurrent modifications.
	private int version;
	//Indicates whereas this unit is deleted
	private boolean deleted=false;
	
	//definition of the specific attributes
	
String description;
String group;
Organization_unit organization_unit;
Personal supervisor;
Set<Research_group> iunit=new HashSet<Research_group>();
Set<Professional> iprofessional_unit=new HashSet<Professional>();
Set<Professional> iprofessional_unit_2=new HashSet<Professional>();
Set<Professional> iprofessional_unit_3=new HashSet<Professional>();
Set<Professional> iprofessional_unit_4=new HashSet<Professional>();

	/**
	 * Default Constructor which creates an empty unit
	 */
	public Unit(){
	}

	
	/**
	 * Returns the code (primary key) of this unit. 
	 * @return a String with the code
	 */
	public String getUnitcode() {
		return unitcode;
	}

	/**
	 * Set the code (primary key) of this unit
	 * @param unitcode the String with the code
	 */
	public void setUnitcode(String unitcode) {
		this.unitcode = unitcode;
	}
	
	/**
	 * Returns the code (primary key) of this unit. Convenience method which calls {@link bussineslogic.objects.Unit#getUnitcode() getUnitcode()}
	 * @return a String with the code
	 */
	public String getCode() {
		return getUnitcode();
	}

	/**
	 * Set the code (primary key) of this unit. Convenience method which calls {@link bussineslogic.objects.Unit#setUnitcode(String) getUnitcode(String)}
	 * @param unitcode the String with the code
	 */
	public void setCode(String unitcode) {
		setUnitcode(unitcode);
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
	 * Tests if this unit has been deleted
	 * @return true if this unit has been deleted
	 */
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * Sets the delete state of this unit
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
 * @return the organization_unit
 */
public Organization_unit getOrganization_unit(){
	return organization_unit;
}

/**
* @param organization_unit the organization_unit to set.
*/
public void setOrganization_unit(Organization_unit organization_unit){
	this.organization_unit=organization_unit;
}

/**
 * @return the iunit
 */
public Set<Research_group> getIunit(){
	return iunit;
}

/**
* @param iunit the iunit to set.
*/
public void setIunit(Set<Research_group> iunit){
	this.iunit=iunit;
}
/**
* Adds a Research_group to the iunit set.
* @param iunit Research_group to be added
*/
public void addIunit(Research_group iunit){
	this.iunit.add(iunit);
}

/**
* Removes a Research_group to the iunit set.
* @param iunit Research_group to be removed
*/
public void removeIunit(Research_group iunit){
	this.iunit.remove(iunit);
}


/**
 * @return the iprofessional_unit
 */
public Set<Professional> getIprofessional_unit(){
	return iprofessional_unit;
}

/**
* @param iprofessional_unit the iprofessional_unit to set.
*/
public void setIprofessional_unit(Set<Professional> iprofessional_unit){
	this.iprofessional_unit=iprofessional_unit;
}
/**
* Adds a Professional to the iprofessional_unit set.
* @param iprofessional_unit Professional to be added
*/
public void addIprofessional_unit(Professional iprofessional_unit){
	this.iprofessional_unit.add(iprofessional_unit);
}

/**
* Removes a Professional to the iprofessional_unit set.
* @param iprofessional_unit Professional to be removed
*/
public void removeIprofessional_unit(Professional iprofessional_unit){
	this.iprofessional_unit.remove(iprofessional_unit);
}


/**
 * @return the iprofessional_unit
 */
public Set<Professional> getIprofessional_unit_2(){
	return iprofessional_unit_2;
}

/**
* @param iprofessional_unit the iprofessional_unit to set.
*/
public void setIprofessional_unit_2(Set<Professional> iprofessional_unit){
	this.iprofessional_unit_2=iprofessional_unit;
}
/**
* Adds a Professional to the iprofessional_unit set.
* @param iprofessional_unit Professional to be added
*/
public void addIprofessional_unit_2(Professional iprofessional_unit){
	this.iprofessional_unit_2.add(iprofessional_unit);
}

/**
* Removes a Professional to the iprofessional_unit set.
* @param iprofessional_unit Professional to be removed
*/
public void removeIprofessional_unit_2(Professional iprofessional_unit){
	this.iprofessional_unit_2.remove(iprofessional_unit);
}


/**
 * @return the iprofessional_unit
 */
public Set<Professional> getIprofessional_unit_3(){
	return iprofessional_unit_3;
}

/**
* @param iprofessional_unit the iprofessional_unit to set.
*/
public void setIprofessional_unit_3(Set<Professional> iprofessional_unit){
	this.iprofessional_unit_3=iprofessional_unit;
}
/**
* Adds a Professional to the iprofessional_unit set.
* @param iprofessional_unit Professional to be added
*/
public void addIprofessional_unit_3(Professional iprofessional_unit){
	this.iprofessional_unit_3.add(iprofessional_unit);
}

/**
* Removes a Professional to the iprofessional_unit set.
* @param iprofessional_unit Professional to be removed
*/
public void removeIprofessional_unit_3(Professional iprofessional_unit){
	this.iprofessional_unit_3.remove(iprofessional_unit);
}



/**
 * @return the iprofessional_unit
 */
public Set<Professional> getIprofessional_unit_4(){
	return iprofessional_unit_4;
}

/**
* @param iprofessional_unit the iprofessional_unit to set.
*/
public void setIprofessional_unit_4(Set<Professional> iprofessional_unit){
	this.iprofessional_unit_4=iprofessional_unit;
}
/**
* Adds a Professional to the iprofessional_unit set.
* @param iprofessional_unit Professional to be added
*/
public void addIprofessional_unit_4(Professional iprofessional_unit){
	this.iprofessional_unit_4.add(iprofessional_unit);
}

/**
* Removes a Professional to the iprofessional_unit set.
* @param iprofessional_unit Professional to be removed
*/
public void removeIprofessional_unit_4(Professional iprofessional_unit){
	this.iprofessional_unit_4.remove(iprofessional_unit);
}


	
	
	@Override
	public String toString(){
		if(getCode()==null || getCode().equals("")) return "";
		
		
		return getCode();
	}


	public Personal getSupervisor() {
		return supervisor;
	}


	public void setSupervisor(Personal supervisor) {
		this.supervisor = supervisor;
	}


    public String getGroup() {
        return group;
    }


    public void setGroup(String group) {
        this.group = group;
    }

}