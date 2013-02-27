package bussineslogic.objects;




import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import presentation.formbeans.objects.Irb_budget_Form;

import utils.Persistent;

/**
 * This class contains business object 'funding_detail'.
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class Funding_detail implements Persistent{

	
	//Code (primary key) of this funding_detail
	private String funding_detailcode;
	//Version of the current instance. This attribute is used by hibernate to control concurrent modifications.
	private int version;
	//Indicates whereas this funding_detail is deleted
	private boolean deleted=false;
	
	//definition of the specific attributes
	
String institution;
Irb_budget irb_budget_code;
int percent;
Personal funding_detail_personal;


	/**
	 * Default Constructor which creates an empty funding_detail
	 */
	public Funding_detail(){
	}

	
	/**
	 * Returns the code (primary key) of this funding_detail. 
	 * @return a String with the code
	 */
	public String getFunding_detailcode() {
		return funding_detailcode;
	}

	/**
	 * Set the code (primary key) of this funding_detail
	 * @param funding_detailcode the String with the code
	 */
	public void setFunding_detailcode(String funding_detailcode) {
		this.funding_detailcode = funding_detailcode;
	}
	
	/**
	 * Returns the code (primary key) of this funding_detail. Convenience method which calls {@link bussineslogic.objects.Funding_detail#getFunding_detailcode() getFunding_detailcode()}
	 * @return a String with the code
	 */
	public String getCode() {
		return getFunding_detailcode();
	}

	/**
	 * Set the code (primary key) of this funding_detail. Convenience method which calls {@link bussineslogic.objects.Funding_detail#setFunding_detailcode(String) getFunding_detailcode(String)}
	 * @param funding_detailcode the String with the code
	 */
	public void setCode(String funding_detailcode) {
		setFunding_detailcode(funding_detailcode);
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
	 * Tests if this funding_detail has been deleted
	 * @return true if this funding_detail has been deleted
	 */
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * Sets the delete state of this funding_detail
	 * @param deleted true if the object is deleted.
	 */
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	/**
 * @return the institution
 */
public String getInstitution(){
	return institution;
}

/**
* @param institution the institution to set.
*/
public void setInstitution(String institution){
	this.institution=institution;
}

/**
 * @return the irb_budget_code
 */
public Irb_budget getIrb_budget_code(){
	return irb_budget_code;
}

/**
* @param irb_budget_code the irb_budget_code to set.
*/
public void setIrb_budget_code(Irb_budget irb_budget_code){
	this.irb_budget_code=irb_budget_code;
}

/**
 * @return the percent
 */
public int getPercent(){
	return percent;
}

/**
* @param percent the percent to set.
*/
public void setPercent(int percent){
	this.percent=percent;
}

/**
 * @return the funding_detail_personal
 */
public Personal getFunding_detail_personal(){
	return funding_detail_personal;
}

/**
* @param funding_detail_personal the funding_detail_personal to set.
*/
public void setFunding_detail_personal(Personal funding_detail_personal){
	this.funding_detail_personal=funding_detail_personal;
}


	
	
	@Override
	public String toString(){
		if(getCode()==null || getCode().equals("")) return "";
		
		
		return getCode();
	}
	


}