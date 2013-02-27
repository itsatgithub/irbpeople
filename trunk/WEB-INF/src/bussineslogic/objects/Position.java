package bussineslogic.objects;




import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import utils.Persistent;

/**
 * This class contains business object 'position'.
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class Position implements Persistent{

	
	//Code (primary key) of this position
	private String positioncode;
	//Version of the current instance. This attribute is used by hibernate to control concurrent modifications.
	private int version;
	//Indicates whereas this position is deleted
	private boolean deleted=false;
	
	//definition of the specific attributes
	
String description;
Set<Work_experience> iwork_experience_position=new HashSet<Work_experience>();
Set<Professional> iposition=new HashSet<Professional>();

	/**
	 * Default Constructor which creates an empty position
	 */
	public Position(){
	}

	
	/**
	 * Returns the code (primary key) of this position. 
	 * @return a String with the code
	 */
	public String getPositioncode() {
		return positioncode;
	}

	/**
	 * Set the code (primary key) of this position
	 * @param positioncode the String with the code
	 */
	public void setPositioncode(String positioncode) {
		this.positioncode = positioncode;
	}
	
	/**
	 * Returns the code (primary key) of this position. Convenience method which calls {@link bussineslogic.objects.Position#getPositioncode() getPositioncode()}
	 * @return a String with the code
	 */
	public String getCode() {
		return getPositioncode();
	}

	/**
	 * Set the code (primary key) of this position. Convenience method which calls {@link bussineslogic.objects.Position#setPositioncode(String) getPositioncode(String)}
	 * @param positioncode the String with the code
	 */
	public void setCode(String positioncode) {
		setPositioncode(positioncode);
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
	 * Tests if this position has been deleted
	 * @return true if this position has been deleted
	 */
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * Sets the delete state of this position
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
 * @return the iwork_experience_position
 */
public Set<Work_experience> getIwork_experience_position(){
	return iwork_experience_position;
}

/**
* @param iwork_experience_position the iwork_experience_position to set.
*/
public void setIwork_experience_position(Set<Work_experience> iwork_experience_position){
	this.iwork_experience_position=iwork_experience_position;
}
/**
* Adds a Work_experience to the iwork_experience_position set.
* @param iwork_experience_position Work_experience to be added
*/
public void addIwork_experience_position(Work_experience iwork_experience_position){
	this.iwork_experience_position.add(iwork_experience_position);
}

/**
* Removes a Work_experience to the iwork_experience_position set.
* @param iwork_experience_position Work_experience to be removed
*/
public void removeIwork_experience_position(Work_experience iwork_experience_position){
	this.iwork_experience_position.remove(iwork_experience_position);
}


/**
 * @return the iposition
 */
public Set<Professional> getIposition(){
	return iposition;
}

/**
* @param iposition the iposition to set.
*/
public void setIposition(Set<Professional> iposition){
	this.iposition=iposition;
}
/**
* Adds a Professional to the iposition set.
* @param iposition Professional to be added
*/
public void addIposition(Professional iposition){
	this.iposition.add(iposition);
}

/**
* Removes a Professional to the iposition set.
* @param iposition Professional to be removed
*/
public void removeIposition(Professional iposition){
	this.iposition.remove(iposition);
}



	
	
	@Override
	public String toString(){
		if(getCode()==null || getCode().equals("")) return "";
		
		
		return getCode();
	}

}