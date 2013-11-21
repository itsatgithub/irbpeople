package bussineslogic.objects;

import java.util.HashSet;
import java.util.Set;

import utils.Persistent;

/**
 * This class contains business object 'gender'.
 * 
 * @author Automatika - JustInMind SL
 * 
 */
public class Gender implements Persistent {

	// Code (primary key) of this gender
	private String gendercode;
	// Version of the current instance. This attribute is used by hibernate to
	// control concurrent modifications.
	private int version;
	// Indicates whereas this gender is deleted
	private boolean deleted = false;

	// definition of the specific attributes

	String description;
	Set<Personal> igender = new HashSet<Personal>();
	Set<Alumni_personal> ialumni_personal = new HashSet<Alumni_personal>();

	/**
	 * Default Constructor which creates an empty gender
	 */
	public Gender() {
	}

	/**
	 * Returns the code (primary key) of this gender.
	 * 
	 * @return a String with the code
	 */
	public String getGendercode() {
		return gendercode;
	}

	/**
	 * Set the code (primary key) of this gender
	 * 
	 * @param gendercode
	 *            the String with the code
	 */
	public void setGendercode(String gendercode) {
		this.gendercode = gendercode;
	}

	/**
	 * Returns the code (primary key) of this gender. Convenience method which
	 * calls {@link bussineslogic.objects.Gender#getGendercode()
	 * getGendercode()}
	 * 
	 * @return a String with the code
	 */
	public String getCode() {
		return getGendercode();
	}

	/**
	 * Set the code (primary key) of this gender. Convenience method which calls
	 * {@link bussineslogic.objects.Gender#setGendercode(String)
	 * getGendercode(String)}
	 * 
	 * @param gendercode
	 *            the String with the code
	 */
	public void setCode(String gendercode) {
		setGendercode(gendercode);
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
	 * Tests if this gender has been deleted
	 * 
	 * @return true if this gender has been deleted
	 */
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * Sets the delete state of this gender
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
	 * @return the igender
	 */
	public Set<Personal> getIgender() {
		return igender;
	}

	/**
	 * @param igender
	 *            the igender to set.
	 */
	public void setIgender(Set<Personal> igender) {
		this.igender = igender;
	}

	/**
	 * Adds a Personal to the igender set.
	 * 
	 * @param igender
	 *            Personal to be added
	 */
	public void addIgender(Personal igender) {
		this.igender.add(igender);
	}

	/**
	 * Removes a Personal to the igender set.
	 * 
	 * @param igender
	 *            Personal to be removed
	 */
	public void removeIgender(Personal igender) {
		this.igender.remove(igender);
	}

	/**
	 * @return the alumni_personal
	 */
	public Set<Alumni_personal> getIalumni_personal() {
		return ialumni_personal;
	}

	/**
	 * @param alumni_personal
	 *            the alumni_personal to set.
	 */
	public void setIalumni_personal(Set<Alumni_personal> alumni_personal) {
		this.ialumni_personal = alumni_personal;
	}

	/**
	 * Adds a Personal to the alumni_personal set.
	 * 
	 * @param alumni_personal
	 *            Personal to be added
	 */
	public void addIalumni_personal(Alumni_personal alumni_personal) {
		this.ialumni_personal.add(alumni_personal);
	}

	/**
	 * Removes a Personal to the alumni_personal set.
	 * 
	 * @param alumni_personal
	 *            Personal to be removed
	 */
	public void removeIalumni_personal(Alumni_personal alumni_personal) {
		this.ialumni_personal.remove(alumni_personal);
	}

	@Override
	public String toString() {
		if (getCode() == null || getCode().equals(""))
			return "";

		return getCode();
	}

}