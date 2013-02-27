package bussineslogic.objects;




import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import utils.Persistent;

/**
 * This class contains business object 'payment'.
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class Payment implements Persistent{

	
	//Code (primary key) of this payment
	private String paymentcode;
	//Version of the current instance. This attribute is used by hibernate to control concurrent modifications.
	private int version;
	//Indicates whereas this payment is deleted
	private boolean deleted=false;
	
	//definition of the specific attributes
	
String description;
Set<Personal> ipayments=new HashSet<Personal>();

	/**
	 * Default Constructor which creates an empty payment
	 */
	public Payment(){
	}

	
	/**
	 * Returns the code (primary key) of this payment. 
	 * @return a String with the code
	 */
	public String getPaymentcode() {
		return paymentcode;
	}

	/**
	 * Set the code (primary key) of this payment
	 * @param paymentcode the String with the code
	 */
	public void setPaymentcode(String paymentcode) {
		this.paymentcode = paymentcode;
	}
	
	/**
	 * Returns the code (primary key) of this payment. Convenience method which calls {@link bussineslogic.objects.Payment#getPaymentcode() getPaymentcode()}
	 * @return a String with the code
	 */
	public String getCode() {
		return getPaymentcode();
	}

	/**
	 * Set the code (primary key) of this payment. Convenience method which calls {@link bussineslogic.objects.Payment#setPaymentcode(String) getPaymentcode(String)}
	 * @param paymentcode the String with the code
	 */
	public void setCode(String paymentcode) {
		setPaymentcode(paymentcode);
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
	 * Tests if this payment has been deleted
	 * @return true if this payment has been deleted
	 */
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * Sets the delete state of this payment
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
 * @return the ipayments
 */
public Set<Personal> getIpayments(){
	return ipayments;
}

/**
* @param ipayments the ipayments to set.
*/
public void setIpayments(Set<Personal> ipayments){
	this.ipayments=ipayments;
}
/**
* Adds a Personal to the ipayments set.
* @param ipayments Personal to be added
*/
public void addIpayments(Personal ipayments){
	this.ipayments.add(ipayments);
}

/**
* Removes a Personal to the ipayments set.
* @param ipayments Personal to be removed
*/
public void removeIpayments(Personal ipayments){
	this.ipayments.remove(ipayments);
}



	
	
	@Override
	public String toString(){
		if(getCode()==null || getCode().equals("")) return "";
		
		
		return getCode();
	}

}