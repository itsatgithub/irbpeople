package bussineslogic.objects;




import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import utils.Persistent;

/**
 * This class contains business object 'compensation'.
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class Compensation implements Persistent{

	
	//Code (primary key) of this compensation
	private String compensationcode;
	//Version of the current instance. This attribute is used by hibernate to control concurrent modifications.
	private int version;
	//Indicates whereas this compensation is deleted
	private boolean deleted=false;
	
	private boolean current=false;
	
	//definition of the specific attributes
	
Date start_date;
Date end_date;
String description;
BigDecimal amount;
Personal compensation_personal;
Type_of_compensation type_of_compensation;

	/**
	 * Default Constructor which creates an empty compensation
	 */
	public Compensation(){
	}

	
	/**
	 * Returns the code (primary key) of this compensation. 
	 * @return a String with the code
	 */
	public String getCompensationcode() {
		return compensationcode;
	}

	/**
	 * Set the code (primary key) of this compensation
	 * @param compensationcode the String with the code
	 */
	public void setCompensationcode(String compensationcode) {
		this.compensationcode = compensationcode;
	}
	
	/**
	 * Returns the code (primary key) of this compensation. Convenience method which calls {@link bussineslogic.objects.Compensation#getCompensationcode() getCompensationcode()}
	 * @return a String with the code
	 */
	public String getCode() {
		return getCompensationcode();
	}

	/**
	 * Set the code (primary key) of this compensation. Convenience method which calls {@link bussineslogic.objects.Compensation#setCompensationcode(String) getCompensationcode(String)}
	 * @param compensationcode the String with the code
	 */
	public void setCode(String compensationcode) {
		setCompensationcode(compensationcode);
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
	 * Tests if this compensation has been deleted
	 * @return true if this compensation has been deleted
	 */
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * Sets the delete state of this compensation
	 * @param deleted true if the object is deleted.
	 */
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	/**
 * @return the start_date
 */
public Date getStart_date(){
	return start_date;
}

/**
* @param start_date the start_date to set.
*/
public void setStart_date(Date start_date){
	this.start_date=start_date;
}

/**
 * @return the end_date
 */
public Date getEnd_date(){
	return end_date;
}

/**
* @param end_date the end_date to set.
*/
public void setEnd_date(Date end_date){
	this.end_date=end_date;
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
 * @return the amount
 */
public BigDecimal getAmount(){
	return amount;
}

/**
* @param amount the amount to set.
*/
public void setAmount(BigDecimal amount){
	this.amount=amount;
}

/**
 * @return the compensation_personal
 */
public Personal getCompensation_personal(){
	return compensation_personal;
}

/**
* @param compensation_personal the compensation_personal to set.
*/
public void setCompensation_personal(Personal compensation_personal){
	this.compensation_personal=compensation_personal;
}

/**
 * @return the type_of_compensation
 */
public Type_of_compensation getType_of_compensation(){
	return type_of_compensation;
}

/**
* @param type_of_compensation the type_of_compensation to set.
*/
public void setType_of_compensation(Type_of_compensation type_of_compensation){
	this.type_of_compensation=type_of_compensation;
}


	
	
	@Override
	public String toString(){
		if(getCode()==null || getCode().equals("")) return "";
		
		
		return getCode();
	}


    public boolean isCurrent() {
        return current;
    }


    public void setCurrent(boolean current) {
        this.current = current;
    }

}