package bussineslogic.objects;




import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import utils.Persistent;

/**
 * This class contains business object 'bank'.
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class Bank implements Persistent{

	
	//Code (primary key) of this bank
	private String bankcode;
	//Version of the current instance. This attribute is used by hibernate to control concurrent modifications.
	private int version;
	//Indicates whereas this bank is deleted
	private boolean deleted=false;
	
	//definition of the specific attributes
	
String description;
Set<Personal> ibank=new HashSet<Personal>();

	/**
	 * Default Constructor which creates an empty bank
	 */
	public Bank(){
	}

	
	/**
	 * Returns the code (primary key) of this bank. 
	 * @return a String with the code
	 */
	public String getBankcode() {
		return bankcode;
	}

	/**
	 * Set the code (primary key) of this bank
	 * @param bankcode the String with the code
	 */
	public void setBankcode(String bankcode) {
		this.bankcode = bankcode;
	}
	
	/**
	 * Returns the code (primary key) of this bank. Convenience method which calls {@link bussineslogic.objects.Bank#getBankcode() getBankcode()}
	 * @return a String with the code
	 */
	public String getCode() {
		return getBankcode();
	}

	/**
	 * Set the code (primary key) of this bank. Convenience method which calls {@link bussineslogic.objects.Bank#setBankcode(String) getBankcode(String)}
	 * @param bankcode the String with the code
	 */
	public void setCode(String bankcode) {
		setBankcode(bankcode);
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
	 * Tests if this bank has been deleted
	 * @return true if this bank has been deleted
	 */
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * Sets the delete state of this bank
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
 * @return the ibank
 */
public Set<Personal> getIbank(){
	return ibank;
}

/**
* @param ibank the ibank to set.
*/
public void setIbank(Set<Personal> ibank){
	this.ibank=ibank;
}
/**
* Adds a Personal to the ibank set.
* @param ibank Personal to be added
*/
public void addIbank(Personal ibank){
	this.ibank.add(ibank);
}

/**
* Removes a Personal to the ibank set.
* @param ibank Personal to be removed
*/
public void removeIbank(Personal ibank){
	this.ibank.remove(ibank);
}



	
	
	@Override
	public String toString(){
		if(getCode()==null || getCode().equals("")) return "";
		
		
		return getCode();
	}

}