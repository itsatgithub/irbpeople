package bussineslogic.objects;




import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import utils.Persistent;

/**
 * This class contains business object 'category'.
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class Category implements Persistent{

	
	//Code (primary key) of this category
	private String categorycode;
	//Version of the current instance. This attribute is used by hibernate to control concurrent modifications.
	private int version;
	//Indicates whereas this category is deleted
	private boolean deleted=false;
	
	//definition of the specific attributes
	
String description;
Set<Personal> icategory=new HashSet<Personal>();

	/**
	 * Default Constructor which creates an empty category
	 */
	public Category(){
	}

	
	/**
	 * Returns the code (primary key) of this category. 
	 * @return a String with the code
	 */
	public String getCategorycode() {
		return categorycode;
	}

	/**
	 * Set the code (primary key) of this category
	 * @param categorycode the String with the code
	 */
	public void setCategorycode(String categorycode) {
		this.categorycode = categorycode;
	}
	
	/**
	 * Returns the code (primary key) of this category. Convenience method which calls {@link bussineslogic.objects.Category#getCategorycode() getCategorycode()}
	 * @return a String with the code
	 */
	public String getCode() {
		return getCategorycode();
	}

	/**
	 * Set the code (primary key) of this category. Convenience method which calls {@link bussineslogic.objects.Category#setCategorycode(String) getCategorycode(String)}
	 * @param categorycode the String with the code
	 */
	public void setCode(String categorycode) {
		setCategorycode(categorycode);
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
	 * Tests if this category has been deleted
	 * @return true if this category has been deleted
	 */
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * Sets the delete state of this category
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
 * @return the icategory
 */
public Set<Personal> getIcategory(){
	return icategory;
}

/**
* @param icategory the icategory to set.
*/
public void setIcategory(Set<Personal> icategory){
	this.icategory=icategory;
}
/**
* Adds a Personal to the icategory set.
* @param icategory Personal to be added
*/
public void addIcategory(Personal icategory){
	this.icategory.add(icategory);
}

/**
* Removes a Personal to the icategory set.
* @param icategory Personal to be removed
*/
public void removeIcategory(Personal icategory){
	this.icategory.remove(icategory);
}



	
	
	@Override
	public String toString(){
		if(getCode()==null || getCode().equals("")) return "";
		
		
		return getCode();
	}

}