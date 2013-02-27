package bussineslogic.objects;




import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import utils.Persistent;

/**
 * This class contains business object 'payroll_institution'.
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class Payroll_institution implements Persistent{

	
	//Code (primary key) of this payroll_institution
	private String payroll_institutioncode;
	//Version of the current instance. This attribute is used by hibernate to control concurrent modifications.
	private int version;
	//Indicates whereas this payroll_institution is deleted
	private boolean deleted=false;
	
	//definition of the specific attributes
	
String description;
Set<Professional> ipayroll_institution=new HashSet<Professional>();
Set<Professional> ipayroll_institution_2=new HashSet<Professional>();

	/**
	 * Default Constructor which creates an empty payroll_institution
	 */
	public Payroll_institution(){
	}

	
	/**
	 * Returns the code (primary key) of this payroll_institution. 
	 * @return a String with the code
	 */
	public String getPayroll_institutioncode() {
		return payroll_institutioncode;
	}

	/**
	 * Set the code (primary key) of this payroll_institution
	 * @param payroll_institutioncode the String with the code
	 */
	public void setPayroll_institutioncode(String payroll_institutioncode) {
		this.payroll_institutioncode = payroll_institutioncode;
	}
	
	/**
	 * Returns the code (primary key) of this payroll_institution. Convenience method which calls {@link bussineslogic.objects.Payroll_institution#getPayroll_institutioncode() getPayroll_institutioncode()}
	 * @return a String with the code
	 */
	public String getCode() {
		return getPayroll_institutioncode();
	}

	/**
	 * Set the code (primary key) of this payroll_institution. Convenience method which calls {@link bussineslogic.objects.Payroll_institution#setPayroll_institutioncode(String) getPayroll_institutioncode(String)}
	 * @param payroll_institutioncode the String with the code
	 */
	public void setCode(String payroll_institutioncode) {
		setPayroll_institutioncode(payroll_institutioncode);
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
	 * Tests if this payroll_institution has been deleted
	 * @return true if this payroll_institution has been deleted
	 */
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * Sets the delete state of this payroll_institution
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
 * @return the ipayroll_institution
 */
public Set<Professional> getIpayroll_institution(){
	return ipayroll_institution;
}

/**
* @param ipayroll_institution the ipayroll_institution to set.
*/
public void setIpayroll_institution(Set<Professional> ipayroll_institution){
	this.ipayroll_institution=ipayroll_institution;
}
/**
* Adds a Professional to the ipayroll_institution set.
* @param ipayroll_institution Professional to be added
*/
public void addIpayroll_institution(Professional ipayroll_institution){
	this.ipayroll_institution.add(ipayroll_institution);
}

/**
* Removes a Professional to the ipayroll_institution set.
* @param ipayroll_institution Professional to be removed
*/
public void removeIpayroll_institution(Professional ipayroll_institution){
	this.ipayroll_institution.remove(ipayroll_institution);
}

/**
 * @return the ipayroll_institution
 */
public Set<Professional> getIpayroll_institution_2(){
	return ipayroll_institution_2;
}

/**
* @param ipayroll_institution the ipayroll_institution to set.
*/
public void setIpayroll_institution_2(Set<Professional> ipayroll_institution){
	this.ipayroll_institution_2=ipayroll_institution;
}
/**
* Adds a Professional to the ipayroll_institution set.
* @param ipayroll_institution Professional to be added
*/
public void addIpayroll_institution_2(Professional ipayroll_institution){
	this.ipayroll_institution_2.add(ipayroll_institution);
}

/**
* Removes a Professional to the ipayroll_institution set.
* @param ipayroll_institution Professional to be removed
*/
public void removeIpayroll_institution_2(Professional ipayroll_institution){
	this.ipayroll_institution_2.remove(ipayroll_institution);
}



	
	
	@Override
	public String toString(){
		if(getCode()==null || getCode().equals("")) return "";
		
		
		return getCode();
	}

}