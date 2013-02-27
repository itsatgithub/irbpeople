package bussineslogic.objects;




import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import utils.Persistent;

/**
 * This class contains business object 'personalstate'.
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class Personalstate implements Persistent{

	public static final String EDITING_DESCRIPTION="editing";
	public static final String VALIDATING_DESCRIPTION="validating";
	public static final String VALIDATING_CANCELLED_DESCRIPTION="validating cancelled";
	public static final String VALIDATED_DESCRIPTION="validated";
	public static final String CANCELLED_DESCRIPTION="cancelled";
	public static final String ACTIVE_DESCRIPTION="active";
	public static final String INACTIVE_DESCRIPTION="inactive";
	
	public static final String EDITING_CODE="0";
	public static final String VALIDATING_CODE="1";
	public static final String VALIDATING_CANCELLED_CODE="2";
	public static final String VALIDATED_CODE="3";
	public static final String CANCELLED_CODE="4";
	public static final String ACTIVE_CODE="5";
	public static final String INACTIVE_CODE="6";
	

	
	//Code (primary key) of this personalstate
	private String personalstatecode;
	//Version of the current instance. This attribute is used by hibernate to control concurrent modifications.
	private int version;
	//Indicates whereas this personalstate is deleted
	private boolean deleted=false;
	
	//definition of the specific attributes
	
String description;

	/**
	 * Default Constructor which creates an empty personalstate
	 */
	public Personalstate(){
	}

	
	/**
	 * Returns the code (primary key) of this personalstate. 
	 * @return a String with the code
	 */
	public String getPersonalstatecode() {
		return personalstatecode;
	}

	/**
	 * Set the code (primary key) of this personalstate
	 * @param personalstatecode the String with the code
	 */
	public void setPersonalstatecode(String personalstatecode) {
		this.personalstatecode = personalstatecode;
	}
	
	/**
	 * Returns the code (primary key) of this personalstate. Convenience method which calls {@link bussineslogic.objects.Personalstate#getPersonalstatecode() getPersonalstatecode()}
	 * @return a String with the code
	 */
	public String getCode() {
		return getPersonalstatecode();
	}

	/**
	 * Set the code (primary key) of this personalstate. Convenience method which calls {@link bussineslogic.objects.Personalstate#setPersonalstatecode(String) getPersonalstatecode(String)}
	 * @param personalstatecode the String with the code
	 */
	public void setCode(String personalstatecode) {
		setPersonalstatecode(personalstatecode);
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
	 * Tests if this personalstate has been deleted
	 * @return true if this personalstate has been deleted
	 */
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * Sets the delete state of this personalstate
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