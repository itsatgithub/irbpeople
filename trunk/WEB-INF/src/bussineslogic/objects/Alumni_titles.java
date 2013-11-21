package bussineslogic.objects;

import java.util.HashSet;
import java.util.Set;

import utils.Persistent;

/**
 * This class contains business object 'alumni_titles'.
 * 
 * @author Automatika - JustInMind SL
 * 
 */
public class Alumni_titles implements Persistent {

	// Code (primary key) of this alumni_titles
	private String alumni_titlescode;
	// Version of the current instance. This attribute is used by hibernate to
	// control concurrent modifications.
	private int version;
	// Indicates whereas this alumni_titles is deleted
	private boolean deleted = false;

	// definition of the specific attributes

	private String description;
	private String short_description;
	private int order_number;

	Set<Alumni_personal> ialumni_personal = new HashSet<Alumni_personal>();

	/**
	 * Default Constructor which creates an empty alumni_titles
	 */
	public Alumni_titles() {
	}

	/**
	 * Returns the code (primary key) of this alumni_titles. Convenience method
	 * which calls
	 * {@link bussineslogic.objects.alumni_titles#getAlumni_titlescode()
	 * getAlumni_titlescode()}
	 * 
	 * @return a String with the code
	 */
	public String getCode() {
		return getAlumni_titlescode();
	}

	/**
	 * Set the code (primary key) of this alumni_titles. Convenience method
	 * which calls
	 * {@link bussineslogic.objects.alumni_titles#setAlumni_titlescode(String)
	 * setAlumni_titlescode(String)}
	 * 
	 * @param alumni_titlescode
	 *            the String with the code
	 */
	public void setCode(String alumni_titlescode) {
		setAlumni_titlescode(alumni_titlescode);
	}

	/**
	 * Returns the code (primary key) of this Alumni_titles.
	 * 
	 * @return a String with the code
	 */
	public String getAlumni_titlescode() {
		return alumni_titlescode;
	}

	/**
	 * Set the code (primary key) of this Alumni_titles
	 * 
	 * @param alumni_titlescode
	 *            the String with the code
	 */
	public void setAlumni_titlescode(String alumni_titlescode) {
		this.alumni_titlescode = alumni_titlescode;
	}

	/**
	 * Returns the version of the current instance. This attribute is used by
	 * hibernate to control concurrent modifications.
	 * 
	 * @return int with the version number.
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * Set the version of the current instance. This attribute is used by
	 * hibernate to control concurrent modifications.
	 * 
	 * @param version
	 *            int with the version number.
	 */
	public void setVersion(int version) {
		this.version = version;
	}

	/**
	 * Tests if this alumni_titles has been deleted
	 * 
	 * @return true if this alumni_titles has been deleted
	 */
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * Sets the delete state of this alumni_titles
	 * 
	 * @param deleted
	 *            true if the object is deleted.
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
	 * @param description
	 *            the description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the description
	 */
	public String getShort_description() {
		return short_description;
	}

	/**
	 * @param description
	 *            the description to set.
	 */
	public void setShort_description(String short_description) {
		this.short_description = short_description;
	}

	/**
	 * @return the ialumni_titles
	 */
	public Set<Alumni_personal> getIalumni_personal() {
		return ialumni_personal;
	}

	/**
	 * @param ialumni_titles
	 *            the ialumni_titles to set.
	 */
	public void setIalumni_personal(Set<Alumni_personal> ialumni_personal) {
		this.ialumni_personal = ialumni_personal;
	}

	/**
	 * Adds a Alumni personal to the ialumni_titles set.
	 * 
	 * @param ialumni_titles
	 *            Alumni personal to be added
	 */
	public void addIialumni_personal(Alumni_personal ialumni_personal) {
		this.ialumni_personal.add(ialumni_personal);
	}

	/**
	 * Removes a Alumni personal to the ialumni_titles set.
	 * 
	 * @param ialumni_titles
	 *            Alumni personal to be removed
	 */
	public void removeIalumni_personal(Alumni_personal ialumni_personal) {
		this.ialumni_personal.remove(ialumni_personal);
	}

	public int getOrder_number() {
		return order_number;
	}

	public void setOrder_number(int order_number) {
		this.order_number = order_number;
	}

	@Override
	public String toString() {
		if (getCode() == null || getCode().equals(""))
			return "";

		return getCode();
	}

}