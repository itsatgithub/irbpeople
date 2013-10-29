package bussineslogic.objects;




import java.util.HashSet;
import java.util.Set;

import utils.Persistent;

/**
 * This class contains business object 'research_group'.
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class Research_group implements Persistent{

	
	//Code (primary key) of this research_group
	private String research_groupcode;
	//Version of the current instance. This attribute is used by hibernate to control concurrent modifications.
	private int version;
	//Indicates whereas this research_group is deleted
	private boolean deleted=false;
	
	//definition of the specific attributes
	
String description;
String group;
Personal supervisor;
Unit unit;
Set<Professional> iresearch_group=new HashSet<Professional>();
Set<Professional> iresearch_group_2=new HashSet<Professional>();
Set<Professional> iresearch_group_3=new HashSet<Professional>();
Set<Professional> iresearch_group_4=new HashSet<Professional>();
Set<Academic_info> iacademic_info=new HashSet<Academic_info>();
Set<Academic_info> iacademic_info_2=new HashSet<Academic_info>();

	/**
	 * Default Constructor which creates an empty research_group
	 */
	public Research_group(){
	}

	
	/**
	 * Returns the code (primary key) of this research_group. 
	 * @return a String with the code
	 */
	public String getResearch_groupcode() {
		return research_groupcode;
	}

	/**
	 * Set the code (primary key) of this research_group
	 * @param research_groupcode the String with the code
	 */
	public void setResearch_groupcode(String research_groupcode) {
		this.research_groupcode = research_groupcode;
	}
	
	/**
	 * Returns the code (primary key) of this research_group. Convenience method which calls {@link bussineslogic.objects.Research_group#getResearch_groupcode() getResearch_groupcode()}
	 * @return a String with the code
	 */
	public String getCode() {
		return getResearch_groupcode();
	}

	/**
	 * Set the code (primary key) of this research_group. Convenience method which calls {@link bussineslogic.objects.Research_group#setResearch_groupcode(String) getResearch_groupcode(String)}
	 * @param research_groupcode the String with the code
	 */
	public void setCode(String research_groupcode) {
		setResearch_groupcode(research_groupcode);
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
	 * Tests if this research_group has been deleted
	 * @return true if this research_group has been deleted
	 */
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * Sets the delete state of this research_group
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
 * @return the supervisor
 */
public Personal getSupervisor(){
	return supervisor;
}

/**
* @param supervisor the supervisor to set.
*/
public void setSupervisor(Personal supervisor){
	this.supervisor=supervisor;
}

/**
 * @return the unit
 */
public Unit getUnit(){
	return unit;
}

/**
* @param unit the unit to set.
*/
public void setUnit(Unit unit){
	this.unit=unit;
}

/**
 * @return the iresearch_group
 */
public Set<Professional> getIresearch_group(){
	return iresearch_group;
}

/**
* @param iresearch_group the iresearch_group to set.
*/
public void setIresearch_group(Set<Professional> iresearch_group){
	this.iresearch_group=iresearch_group;
}
/**
* Adds a Professional to the iresearch_group set.
* @param iresearch_group Professional to be added
*/
public void addIresearch_group(Professional iresearch_group){
	this.iresearch_group.add(iresearch_group);
}

/**
* Removes a Professional to the iresearch_group set.
* @param iresearch_group Professional to be removed
*/
public void removeIresearch_group(Professional iresearch_group){
	this.iresearch_group.remove(iresearch_group);
}


/**
 * @return the iresearch_group
 */
public Set<Professional> getIresearch_group_2(){
	return iresearch_group_2;
}

/**
* @param iresearch_group the iresearch_group to set.
*/
public void setIresearch_group_2(Set<Professional> iresearch_group){
	this.iresearch_group_2=iresearch_group;
}
/**
* Adds a Professional to the iresearch_group set.
* @param iresearch_group Professional to be added
*/
public void addIresearch_group_2(Professional iresearch_group){
	this.iresearch_group_2.add(iresearch_group);
}

/**
* Removes a Professional to the iresearch_group set.
* @param iresearch_group Professional to be removed
*/
public void removeIresearch_group_2(Professional iresearch_group){
	this.iresearch_group_2.remove(iresearch_group);
}
	


/**
 * @return the iresearch_group
 */
public Set<Professional> getIresearch_group_3(){
	return iresearch_group_3;
}

/**
* @param iresearch_group the iresearch_group to set.
*/
public void setIresearch_group_3(Set<Professional> iresearch_group){
	this.iresearch_group_3=iresearch_group;
}
/**
* Adds a Professional to the iresearch_group set.
* @param iresearch_group Professional to be added
*/
public void addIresearch_group_3(Professional iresearch_group){
	this.iresearch_group_3.add(iresearch_group);
}

/**
* Removes a Professional to the iresearch_group set.
* @param iresearch_group Professional to be removed
*/
public void removeIresearch_group_3(Professional iresearch_group){
	this.iresearch_group_3.remove(iresearch_group);
}


/**
 * @return the iresearch_group
 */
public Set<Professional> getIresearch_group_4(){
	return iresearch_group_4;
}

/**
* @param iresearch_group the iresearch_group to set.
*/
public void setIresearch_group_4(Set<Professional> iresearch_group){
	this.iresearch_group_4=iresearch_group;
}
/**
* Adds a Professional to the iresearch_group set.
* @param iresearch_group Professional to be added
*/
public void addIresearch_group_4(Professional iresearch_group){
	this.iresearch_group_4.add(iresearch_group);
}

/**
 * @return the iresearch_group
 */
public Set<Academic_info> getIacademic_info(){
	return iacademic_info;
}

/**
* @param iresearch_group the iresearch_group to set.
*/
public void setIacademic_info(Set<Academic_info> iresearch_group){
	this.iacademic_info=iresearch_group;
}
/**
* Adds a Professional to the iresearch_group set.
* @param iresearch_group Professional to be added
*/
public void addIacademic_info(Academic_info iresearch_group){
	this.iacademic_info.add(iresearch_group);
}

/**
* Removes a Professional to the iresearch_group set.
* @param iresearch_group Professional to be removed
*/
public void removeIacademic_info(Academic_info iresearch_group){
	this.iacademic_info.remove(iresearch_group);
}

/**
 * @return the iresearch_group
 */
public Set<Academic_info> getIacademic_info_2(){
	return iacademic_info_2;
}

/**
* @param iresearch_group the iresearch_group to set.
*/
public void setIacademic_info_2(Set<Academic_info> iresearch_group){
	this.iacademic_info_2=iresearch_group;
}
/**
* Adds a Professional to the iresearch_group set.
* @param iresearch_group Professional to be added
*/
public void addIacademic_info_2(Academic_info iresearch_group){
	this.iacademic_info_2.add(iresearch_group);
}

/**
* Removes a Professional to the iresearch_group set.
* @param iresearch_group Professional to be removed
*/
public void removeIacademic_info_2(Academic_info iresearch_group){
	this.iacademic_info_2.remove(iresearch_group);
}


/**
* Removes a Professional to the iresearch_group set.
* @param iresearch_group Professional to be removed
*/
public void removeIresearch_group_4(Professional iresearch_group){
	this.iresearch_group_4.remove(iresearch_group);
}
	
	@Override
	public String toString(){
		if(getCode()==null || getCode().equals("")) return "";
		
		
		return getCode();
	}


    public String getGroup() {
        return group;
    }


    public void setGroup(String group) {
        this.group = group;
    }

}