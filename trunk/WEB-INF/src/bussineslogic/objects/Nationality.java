package bussineslogic.objects;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import utils.Persistent;

/**
 * This class contains business object 'nationality'.
 * 
 * @author Automatika - JustInMind SL
 * 
 */
public class Nationality implements Persistent {

	// Code (primary key) of this nationality
	private String nationalitycode;
	// Version of the current instance. This attribute is used by hibernate to
	// control concurrent modifications.
	private int version;
	// Indicates whereas this nationality is deleted
	private boolean deleted = false;

	// definition of the specific attributes

	String description;
	Set<Personal> inationality = new HashSet<Personal>();
	Set<Personal> inationality_2 = new HashSet<Personal>();

	Set<Alumni_personal> ialumni_personal = new HashSet<Alumni_personal>();
	Set<Alumni_personal> ialumni_personal_2 = new HashSet<Alumni_personal>();

	/**
	 * Default Constructor which creates an empty nationality
	 */
	public Nationality() {
	}

	/**
	 * Returns the code (primary key) of this nationality.
	 * 
	 * @return a String with the code
	 */
	public String getNationalitycode() {
		return nationalitycode;
	}

	/**
	 * Set the code (primary key) of this nationality
	 * 
	 * @param nationalitycode
	 *            the String with the code
	 */
	public void setNationalitycode(String nationalitycode) {
		this.nationalitycode = nationalitycode;
	}

	/**
	 * Returns the code (primary key) of this nationality. Convenience method
	 * which calls
	 * {@link bussineslogic.objects.Nationality#getNationalitycode()
	 * getNationalitycode()}
	 * 
	 * @return a String with the code
	 */
	public String getCode() {
		return getNationalitycode();
	}

	/**
	 * Set the code (primary key) of this nationality. Convenience method which
	 * calls
	 * {@link bussineslogic.objects.Nationality#setNationalitycode(String)
	 * getNationalitycode(String)}
	 * 
	 * @param nationalitycode
	 *            the String with the code
	 */
	public void setCode(String nationalitycode) {
		setNationalitycode(nationalitycode);
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
	 * Tests if this nationality has been deleted
	 * 
	 * @return true if this nationality has been deleted
	 */
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * Sets the delete state of this nationality
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
	 * @return the inationality
	 */
	public Set<Personal> getInationality() {
		return inationality;
	}

	/**
	 * @param inationality
	 *            the inationality to set.
	 */
	public void setInationality(Set<Personal> inationality) {
		this.inationality = inationality;
	}

	/**
	 * Adds a Personal to the inationality set.
	 * 
	 * @param inationality
	 *            Personal to be added
	 */
	public void addInationality(Personal inationality) {
		this.inationality.add(inationality);
	}

	/**
	 * Removes a Personal to the inationality set.
	 * 
	 * @param inationality
	 *            Personal to be removed
	 */
	public void removeInationality(Personal inationality) {
		this.inationality.remove(inationality);
	}

	/**
	 * @return the inationality_2
	 */
	public Set<Personal> getInationality_2() {
		return inationality_2;
	}

	/**
	 * @param inationality_2
	 *            the inationality_2 to set.
	 */
	public void setInationality_2(Set<Personal> inationality_2) {
		this.inationality_2 = inationality_2;
	}

	/**
	 * Adds a Personal to the inationality_2 set.
	 * 
	 * @param inationality_2
	 *            Personal to be added
	 */
	public void addInationality_2(Personal inationality_2) {
		this.inationality_2.add(inationality_2);
	}

	/**
	 * Removes a Personal to the inationality_2 set.
	 * 
	 * @param inationality_2
	 *            Personal to be removed
	 */
	public void removeInationality_2(Personal inationality_2) {
		this.inationality_2.remove(inationality_2);
	}

	/**
	 * @return the iAlumni_personal
	 */
	public Set<Alumni_personal> getIalumni_personal() {
		return ialumni_personal;
	}
	
	/**
	 * @param iAlumni_personal
	 *            the iAlumni_personal to set.
	 */
	public void setIalumni_personal(Set<Alumni_personal> ialumni_personal) {
		this.ialumni_personal = ialumni_personal;
	}

	/**
	 * Adds a Personal to the iAlumni_personal set.
	 * 
	 * @param ialumni
	 *            Personal to be added
	 */
	public void addIalumni_personal(Alumni_personal ialumni_personal) {
		this.ialumni_personal.add(ialumni_personal);
	}

	/**
	 * Removes a Personal to the iAlumni_personal set.
	 * 
	 * @param ialumni
	 *            Personal to be removed
	 */
	public void removeIalumni_personal(Alumni_personal ialumni_personal) {
		this.ialumni_personal.remove(ialumni_personal);
	}

	/**
	 * @return the iAlumni_personal_2
	 */
	public Set<Alumni_personal> getIalumni_personal_2() {
		return ialumni_personal_2;
	}

	/**
	 * @param iAlumni_personal_2
	 *            the iAlumni_personal_2 to set.
	 */
	public void setIalumni_personal_2(Set<Alumni_personal> ialumni_personal_2) {
		this.ialumni_personal_2 = ialumni_personal_2;
	}

	/**
	 * Adds a Personal to the iAlumni_personal_2 set.
	 * 
	 * @param iAlumni_personal_2
	 *            Personal to be added
	 */
	public void addIalumni_personal_2(Alumni_personal ialumni_personal_2) {
		this.ialumni_personal_2.add(ialumni_personal_2);
	}

	/**
	 * Removes a Personal to the iAlumni_personal_2 set.
	 * 
	 * @param iAlumni_personal_2
	 *            Personal to be removed
	 */
	public void removeIalumni_personal_2(Alumni_personal ialumni_personal_2) {
		this.ialumni_personal_2.remove(ialumni_personal_2);
	}

	@Override
	public String toString() {
		if (getCode() == null || getCode().equals(""))
			return "";

		return getCode();
	}

}