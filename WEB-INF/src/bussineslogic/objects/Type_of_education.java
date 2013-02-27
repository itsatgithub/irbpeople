package bussineslogic.objects;

import java.util.HashSet;
import java.util.Set;

import utils.Persistent;

/**
 * This class contains business object 'type_of_education'.
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class Type_of_education implements Persistent {

	//Code (primary key) of this type_of_education
	private String type_of_educationcode;
	//Version of the current instance. This attribute is used by hibernate to control concurrent modifications.
	private int version;
	//Indicates whereas this type_of_education is deleted
	private boolean deleted = false;

	//definition of the specific attributes

	String description;
	Set<Education> itype = new HashSet<Education>();

	Integer order;

	/**
	 * Default Constructor which creates an empty type_of_education
	 */
	public Type_of_education() {}

	/**
	 * Returns the code (primary key) of this type_of_education. 
	 * @return a String with the code
	 */
	public String getType_of_educationcode() {
		return type_of_educationcode;
	}

	/**
	 * Set the code (primary key) of this type_of_education
	 * @param type_of_educationcode the String with the code
	 */
	public void setType_of_educationcode(String type_of_educationcode) {
		this.type_of_educationcode = type_of_educationcode;
	}

	/**
	 * Returns the code (primary key) of this type_of_education. Convenience method which calls {@link bussineslogic.objects.Type_of_education#getType_of_educationcode() getType_of_educationcode()}
	 * @return a String with the code
	 */
	public String getCode() {
		return getType_of_educationcode();
	}

	/**
	 * Set the code (primary key) of this type_of_education. Convenience method which calls {@link bussineslogic.objects.Type_of_education#setType_of_educationcode(String) getType_of_educationcode(String)}
	 * @param type_of_educationcode the String with the code
	 */
	public void setCode(String type_of_educationcode) {
		setType_of_educationcode(type_of_educationcode);
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
	 * Tests if this type_of_education has been deleted
	 * @return true if this type_of_education has been deleted
	 */
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * Sets the delete state of this type_of_education
	 * @param deleted true if the object is deleted.
	 */
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	/**
	* @return the description
	*/
	public String getDescription() {
		return description;
	}

	/**
	* @param description the description to set.
	*/
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the itype
	 */
	public Set<Education> getItype() {
		return itype;
	}

	/**
	* @param itype the itype to set.
	*/
	public void setItype(Set<Education> itype) {
		this.itype = itype;
	}

	/**
	* Adds a Education to the itype set.
	* @param itype Education to be added
	*/
	public void addItype(Education itype) {
		this.itype.add(itype);
	}

	/**
	* Removes a Education to the itype set.
	* @param itype Education to be removed
	*/
	public void removeItype(Education itype) {
		this.itype.remove(itype);
	}

	@Override
	public String toString() {
		if (getCode() == null || getCode().equals(""))
			return "";

		return getCode();
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

}