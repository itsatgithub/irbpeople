package bussineslogic.objects;




import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import utils.Persistent;

/**
 * This class contains business object 'auditmessagetype'.
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class Auditmessagetype implements Persistent{

	
	//Code (primary key) of this auditmessagetype
	private String auditmessagetypecode;
	//Version of the current instance. This attribute is used by hibernate to control concurrent modifications.
	private int version;
	//Indicates whereas this auditmessagetype is deleted
	private boolean deleted=false;
	
	//definition of the specific attributes
	
String description;

	/**
	 * Default Constructor which creates an empty auditmessagetype
	 */
	public Auditmessagetype(){
	}

	
	/**
	 * Returns the code (primary key) of this auditmessagetype. 
	 * @return a String with the code
	 */
	public String getAuditmessagetypecode() {
		return auditmessagetypecode;
	}

	/**
	 * Set the code (primary key) of this auditmessagetype
	 * @param auditmessagetypecode the String with the code
	 */
	public void setAuditmessagetypecode(String auditmessagetypecode) {
		this.auditmessagetypecode = auditmessagetypecode;
	}
	
	/**
	 * Returns the code (primary key) of this auditmessagetype. Convenience method which calls {@link bussineslogic.objects.Auditmessagetype#getAuditmessagetypecode() getAuditmessagetypecode()}
	 * @return a String with the code
	 */
	public String getCode() {
		return getAuditmessagetypecode();
	}

	/**
	 * Set the code (primary key) of this auditmessagetype. Convenience method which calls {@link bussineslogic.objects.Auditmessagetype#setAuditmessagetypecode(String) getAuditmessagetypecode(String)}
	 * @param auditmessagetypecode the String with the code
	 */
	public void setCode(String auditmessagetypecode) {
		setAuditmessagetypecode(auditmessagetypecode);
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
	 * Tests if this auditmessagetype has been deleted
	 * @return true if this auditmessagetype has been deleted
	 */
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * Sets the delete state of this auditmessagetype
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


	
	
	@Override
	public String toString(){
		if(getCode()==null || getCode().equals("")) return "";
		
		
		return getCode();
	}

}