package bussineslogic.objects;




import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import utils.Persistent;

/**
 * This class contains business object 'location'.
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class Location implements Persistent{

	
	//Code (primary key) of this location
	private String locationcode;
	private String buildingcode;
	//Version of the current instance. This attribute is used by hibernate to control concurrent modifications.
	private int version;
	//Indicates whereas this location is deleted
	private boolean deleted=false;
	
	//definition of the specific attributes
	
String description;
Set<Professional> ilocation=new HashSet<Professional>();

	/**
	 * Default Constructor which creates an empty location
	 */
	public Location(){
	}

	
	/**
	 * Returns the code (primary key) of this location. 
	 * @return a String with the code
	 */
	public String getLocationcode() {
		return locationcode;
	}

	/**
	 * Set the code (primary key) of this location
	 * @param locationcode the String with the code
	 */
	public void setLocationcode(String locationcode) {
		this.locationcode = locationcode;
	}
	
	/**
	 * Returns the code (primary key) of this location. Convenience method which calls {@link bussineslogic.objects.Location#getLocationcode() getLocationcode()}
	 * @return a String with the code
	 */
	public String getCode() {
		return getLocationcode();
	}

	/**
	 * Set the code (primary key) of this location. Convenience method which calls {@link bussineslogic.objects.Location#setLocationcode(String) getLocationcode(String)}
	 * @param locationcode the String with the code
	 */
	public void setCode(String locationcode) {
		setLocationcode(locationcode);
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
	 * Tests if this location has been deleted
	 * @return true if this location has been deleted
	 */
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * Sets the delete state of this location
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
 * @return the ilocation
 */
public Set<Professional> getIlocation(){
	return ilocation;
}

/**
* @param ilocation the ilocation to set.
*/
public void setIlocation(Set<Professional> ilocation){
	this.ilocation=ilocation;
}
/**
* Adds a Professional to the ilocation set.
* @param ilocation Professional to be added
*/
public void addIlocation(Professional ilocation){
	this.ilocation.add(ilocation);
}

/**
* Removes a Professional to the ilocation set.
* @param ilocation Professional to be removed
*/
public void removeIlocation(Professional ilocation){
	this.ilocation.remove(ilocation);
}



	
	
	@Override
	public String toString(){
		if(getCode()==null || getCode().equals("")) return "";
		
		
		return getCode();
	}


    public String getBuildingcode() {
        // TODO Auto-generated method stub
        return this.buildingcode;
    }
    
    public void setBuildingcode(String buildingcode)
    {
        this.buildingcode = buildingcode;
    }

}