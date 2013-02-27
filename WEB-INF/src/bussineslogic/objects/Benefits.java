package bussineslogic.objects;




import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import utils.Persistent;

/**
 * This class contains business object 'benefits'.
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class Benefits implements Persistent{

	
	//Code (primary key) of this benefits
	private String benefitscode;
	//Version of the current instance. This attribute is used by hibernate to control concurrent modifications.
	private int version;
	//Indicates whereas this benefits is deleted
	private boolean deleted=false;
	
	//definition of the specific attributes
	
Date start_date;
Date end_date;
String description;
Personal benefits_personal;
Type_of_benefit type_of_benefit;

	/**
	 * Default Constructor which creates an empty benefits
	 */
	public Benefits(){
	}

	
	/**
	 * Returns the code (primary key) of this benefits. 
	 * @return a String with the code
	 */
	public String getBenefitscode() {
		return benefitscode;
	}

	/**
	 * Set the code (primary key) of this benefits
	 * @param benefitscode the String with the code
	 */
	public void setBenefitscode(String benefitscode) {
		this.benefitscode = benefitscode;
	}
	
	/**
	 * Returns the code (primary key) of this benefits. Convenience method which calls {@link bussineslogic.objects.Benefits#getBenefitscode() getBenefitscode()}
	 * @return a String with the code
	 */
	public String getCode() {
		return getBenefitscode();
	}

	/**
	 * Set the code (primary key) of this benefits. Convenience method which calls {@link bussineslogic.objects.Benefits#setBenefitscode(String) getBenefitscode(String)}
	 * @param benefitscode the String with the code
	 */
	public void setCode(String benefitscode) {
		setBenefitscode(benefitscode);
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
	 * Tests if this benefits has been deleted
	 * @return true if this benefits has been deleted
	 */
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * Sets the delete state of this benefits
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
 * @return the benefits_personal
 */
public Personal getBenefits_personal(){
	return benefits_personal;
}

/**
* @param benefits_personal the benefits_personal to set.
*/
public void setBenefits_personal(Personal benefits_personal){
	this.benefits_personal=benefits_personal;
}

/**
 * @return the type_of_benefit
 */
public Type_of_benefit getType_of_benefit(){
	return type_of_benefit;
}

/**
* @param type_of_benefit the type_of_benefit to set.
*/
public void setType_of_benefit(Type_of_benefit type_of_benefit){
	this.type_of_benefit=type_of_benefit;
}


	
	
	@Override
	public String toString(){
		if(getCode()==null || getCode().equals("")) return "";
		
		
		return getCode();
	}

}