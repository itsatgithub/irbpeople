package bussineslogic.objects;




import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import utils.Persistent;

/**
 * This class contains business object 'auditmessage'.
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class Auditmessage implements Persistent{

	
	//Code (primary key) of this auditmessage
	private String auditmessagecode;
	//Version of the current instance. This attribute is used by hibernate to control concurrent modifications.
	private int version;
	//Indicates whereas this auditmessage is deleted
	private boolean deleted=false;
	
	//definition of the specific attributes
	
Date timestamp;
String message;
Auditmessagetype type;
String user;

	/**
	 * Default Constructor which creates an empty auditmessage
	 */
	public Auditmessage(){
	}

	
	/**
	 * Returns the code (primary key) of this auditmessage. 
	 * @return a String with the code
	 */
	public String getAuditmessagecode() {
		return auditmessagecode;
	}

	/**
	 * Set the code (primary key) of this auditmessage
	 * @param auditmessagecode the String with the code
	 */
	public void setAuditmessagecode(String auditmessagecode) {
		this.auditmessagecode = auditmessagecode;
	}
	
	/**
	 * Returns the code (primary key) of this auditmessage. Convenience method which calls {@link bussineslogic.objects.Auditmessage#getAuditmessagecode() getAuditmessagecode()}
	 * @return a String with the code
	 */
	public String getCode() {
		return getAuditmessagecode();
	}

	/**
	 * Set the code (primary key) of this auditmessage. Convenience method which calls {@link bussineslogic.objects.Auditmessage#setAuditmessagecode(String) getAuditmessagecode(String)}
	 * @param auditmessagecode the String with the code
	 */
	public void setCode(String auditmessagecode) {
		setAuditmessagecode(auditmessagecode);
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
	 * Tests if this auditmessage has been deleted
	 * @return true if this auditmessage has been deleted
	 */
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * Sets the delete state of this auditmessage
	 * @param deleted true if the object is deleted.
	 */
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	/**
 * @return the timestamp
 */
public Date getTimestamp(){
	return timestamp;
}

/**
* @param timestamp the timestamp to set.
*/
public void setTimestamp(Date timestamp){
	this.timestamp=timestamp;
}

/**
 * @return the message
 */
public String getMessage(){
	return message;
}

/**
* @param message the message to set.
*/
public void setMessage(String message){
	this.message=message;
}

/**
 * @return the type
 */
public Auditmessagetype getType(){
	return type;
}

/**
* @param type the type to set.
*/
public void setType(Auditmessagetype type){
	this.type=type;
}

/**
 * @return the user
 */
public String getUser(){
	return user;
}

/**
* @param user the user to set.
*/
public void setUser(String user){
	this.user=user;
}


	
	
	@Override
	public String toString(){
		if(getCode()==null || getCode().equals("")) return "";
		
		
		return getCode();
	}

}