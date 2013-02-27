package bussineslogic.objects;




import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import utils.Persistent;

/**
 * This class contains business object 'area'.
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class Area implements Persistent{

	
	//Code (primary key) of this area
	private String areacode;
	//Version of the current instance. This attribute is used by hibernate to control concurrent modifications.
	private int version;
	//Indicates whereas this area is deleted
	private boolean deleted=false;
	
	//definition of the specific attributes
	
String description;
Set<Work_experience> iarea=new HashSet<Work_experience>();

	/**
	 * Default Constructor which creates an empty area
	 */
	public Area(){
	}

	
	/**
	 * Returns the code (primary key) of this area. 
	 * @return a String with the code
	 */
	public String getAreacode() {
		return areacode;
	}

	/**
	 * Set the code (primary key) of this area
	 * @param areacode the String with the code
	 */
	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}
	
	/**
	 * Returns the code (primary key) of this area. Convenience method which calls {@link bussineslogic.objects.Area#getAreacode() getAreacode()}
	 * @return a String with the code
	 */
	public String getCode() {
		return getAreacode();
	}

	/**
	 * Set the code (primary key) of this area. Convenience method which calls {@link bussineslogic.objects.Area#setAreacode(String) getAreacode(String)}
	 * @param areacode the String with the code
	 */
	public void setCode(String areacode) {
		setAreacode(areacode);
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
	 * Tests if this area has been deleted
	 * @return true if this area has been deleted
	 */
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * Sets the delete state of this area
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
 * @return the iarea
 */
public Set<Work_experience> getIarea(){
	return iarea;
}

/**
* @param iarea the iarea to set.
*/
public void setIarea(Set<Work_experience> iarea){
	this.iarea=iarea;
}
/**
* Adds a Work_experience to the iarea set.
* @param iarea Work_experience to be added
*/
public void addIarea(Work_experience iarea){
	this.iarea.add(iarea);
}

/**
* Removes a Work_experience to the iarea set.
* @param iarea Work_experience to be removed
*/
public void removeIarea(Work_experience iarea){
	this.iarea.remove(iarea);
}



	
	
	@Override
	public String toString(){
		if(getCode()==null || getCode().equals("")) return "";
		
		
		return getCode();
	}

}