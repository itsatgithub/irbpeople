package bussineslogic.objects;




import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import utils.Persistent;

/**
 * This class contains business object 'marital_status'.
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class Marital_status implements Persistent{

	
	//Code (primary key) of this marital_status
	private String marital_statuscode;
	//Version of the current instance. This attribute is used by hibernate to control concurrent modifications.
	private int version;
	//Indicates whereas this marital_status is deleted
	private boolean deleted=false;
	
	//definition of the specific attributes
	
String description;
Set<Personal> imarital_status=new HashSet<Personal>();

	/**
	 * Default Constructor which creates an empty marital_status
	 */
	public Marital_status(){
	}

	
	/**
	 * Returns the code (primary key) of this marital_status. 
	 * @return a String with the code
	 */
	public String getMarital_statuscode() {
		return marital_statuscode;
	}

	/**
	 * Set the code (primary key) of this marital_status
	 * @param marital_statuscode the String with the code
	 */
	public void setMarital_statuscode(String marital_statuscode) {
		this.marital_statuscode = marital_statuscode;
	}
	
	/**
	 * Returns the code (primary key) of this marital_status. Convenience method which calls {@link bussineslogic.objects.Marital_status#getMarital_statuscode() getMarital_statuscode()}
	 * @return a String with the code
	 */
	public String getCode() {
		return getMarital_statuscode();
	}

	/**
	 * Set the code (primary key) of this marital_status. Convenience method which calls {@link bussineslogic.objects.Marital_status#setMarital_statuscode(String) getMarital_statuscode(String)}
	 * @param marital_statuscode the String with the code
	 */
	public void setCode(String marital_statuscode) {
		setMarital_statuscode(marital_statuscode);
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
	 * Tests if this marital_status has been deleted
	 * @return true if this marital_status has been deleted
	 */
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * Sets the delete state of this marital_status
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
 * @return the imarital_status
 */
public Set<Personal> getImarital_status(){
	return imarital_status;
}

/**
* @param imarital_status the imarital_status to set.
*/
public void setImarital_status(Set<Personal> imarital_status){
	this.imarital_status=imarital_status;
}
/**
* Adds a Personal to the imarital_status set.
* @param imarital_status Personal to be added
*/
public void addImarital_status(Personal imarital_status){
	this.imarital_status.add(imarital_status);
}

/**
* Removes a Personal to the imarital_status set.
* @param imarital_status Personal to be removed
*/
public void removeImarital_status(Personal imarital_status){
	this.imarital_status.remove(imarital_status);
}



	
	
	@Override
	public String toString(){
		if(getCode()==null || getCode().equals("")) return "";
		
		
		return getCode();
	}

}