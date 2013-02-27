package bussineslogic.objects;




import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import utils.Persistent;

/**
 * This class contains business object 'organization_unit'.
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class Irb_budget implements Persistent{

	
	//Code (primary key) of this Irb_budget
	private String irb_budgetcode;
	//Version of the current instance. This attribute is used by hibernate to control concurrent modifications.
	private int version;
	//Indicates whereas this organization_unit is deleted
	private boolean deleted=false;
	
	//definition of the specific attributes
	
String description;

Set<Funding_detail> ifunding_detail=new HashSet<Funding_detail>();

	/**
	 * Default Constructor which creates an empty irb_budget
	 */
	public Irb_budget(){
	}

	
	/**
	 * Returns the code (primary key) of this irb_budget. 
	 * @return a String with the code
	 */
	public String getIrb_budgetcode() {
		return irb_budgetcode;
	}

	/**
	 * Set the code (primary key) of this irb_budget
	 * @param irb_budgetcode the String with the code
	 */
	public void setIrb_budgetcode(String irb_budgetcode) {
		this.irb_budgetcode = irb_budgetcode;
	}
	
	/**
	 * Returns the code (primary key) of this irb_budget. Convenience method which calls {@link bussineslogic.objects.Irb_budget#getIrb_budgetcode() getIrb_budgetcode()}
	 * @return a String with the code
	 */
	public String getCode() {
		return getIrb_budgetcode();
	}

	/**
	 * Set the code (primary key) of this irb_budget. Convenience method which calls {@link bussineslogic.objects.Irb_budget#setIrb_budgetcode(String) getIrb_budgetcode(String)}
	 * @param irb_budgetcode the String with the code
	 */
	public void setCode(String irb_budgetcode) {
		setIrb_budgetcode(irb_budgetcode);
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
	 * Tests if this irb_budget has been deleted
	 * @return true if this irb_budget has been deleted
	 */
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * Sets the delete state of this irb_budget
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
 * @return the iorganization_unit
 */
public Set<Funding_detail> getIfunding_detail(){
	return ifunding_detail;
}

/**
* @param iorganization_unit the iorganization_unit to set.
*/
public void setIfunding_detail(Set<Funding_detail> ifunding_detail){
	this.ifunding_detail=ifunding_detail;
}
/**
* Adds a Unit to the iorganization_unit set.
* @param iorganization_unit Unit to be added
*/
public void addIfunding_detail(Funding_detail ifunding_detail){
	this.ifunding_detail.add(ifunding_detail);
}

/**
* Removes a Unit to the iorganization_unit set.
* @param iorganization_unit Unit to be removed
*/
public void removeIfunding_detail(Funding_detail ifunding_detail){
	this.ifunding_detail.remove(ifunding_detail);
}


	
	
	@Override
	public String toString(){
		if(getCode()==null || getCode().equals("")) return "";
		
		
		return getCode();
	}

}