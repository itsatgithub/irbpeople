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
public class Alumni_job_position_types implements Persistent {

	// Code (primary key) of this alumni_titles
	private String alumni_job_position_typescode;
	// Version of the current instance. This attribute is used by hibernate to
	// control concurrent modifications.
	private int version;
	// Indicates whereas this alumni_job_position_types is deleted
	private boolean deleted = false;

	// definition of the specific attributes

	private String description;
	private String short_description;
	private int order_number;

	Set<Alumni_external_job_positions> ialumni_external_job_positions = new HashSet<Alumni_external_job_positions>();
	Set<Alumni_irb_job_positions> ialumni_irb_job_positions = new HashSet<Alumni_irb_job_positions>();

	/**
	 * Default Constructor which creates an empty alumni_titles
	 */
	public Alumni_job_position_types() {
	}

	/**
	 * Returns the code (primary key) of this alumni_job_position_types. Convenience method
	 * which calls
	 * {@link bussineslogic.objects.alumni_job_position_types#getAlumni_job_position_typescode()
	 * getAlumni_job_position_typescode()}
	 * 
	 * @return a String with the code
	 */
	public String getCode() {
		return getAlumni_job_position_typescode();
	}

	/**
	 * Set the code (primary key) of this alumni_job_position_types. Convenience method
	 * which calls
	 * {@link bussineslogic.objects.alumni_job_position_types#setAlumni_job_position_typescode(String)
	 * setAlumni_job_position_typescode(String)}
	 * 
	 * @param alumni_job_position_typescode
	 *            the String with the code
	 */
	public void setCode(String alumni_job_position_typescode) {
		setAlumni_job_position_typescode(alumni_job_position_typescode);
	}

	/**
	 * Returns the code (primary key) of this Alumni_job_position_types.
	 * 
	 * @return a String with the code
	 */
	public String getAlumni_job_position_typescode() {
		return alumni_job_position_typescode;
	}

	/**
	 * Set the code (primary key) of this Alumni_job_position_types
	 * 
	 * @param alumni_job_position_typescode
	 *            the String with the code
	 */
	public void setAlumni_job_position_typescode(String alumni_job_position_typescode) {
		this.alumni_job_position_typescode = alumni_job_position_typescode;
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
	 * Tests if this alumni_job_position_types has been deleted
	 * 
	 * @return true if this alumni_job_position_types has been deleted
	 */
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * Sets the delete state of this alumni_job_position_types
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
	 * @return the ialumni_external_job_positions
	 */
	public Set<Alumni_external_job_positions> getIalumni_external_job_positions() {
		return ialumni_external_job_positions;
	}

	/**
	 * @param ialumni_external_job_positions
	 *            the ialumni_external_job_positions to set.
	 */
	public void setIalumni_external_job_positions(Set<Alumni_external_job_positions> ialumni_external_job_positions) {
		this.ialumni_external_job_positions = ialumni_external_job_positions;
	}

	/**
	 * Adds a Alumni_external_job_positions to the ialumni_external_job_positions set.
	 * 
	 * @param ialumni_job_position_types
	 *            Alumni personal to be added
	 */
	public void addIialumni_external_job_positions(Alumni_external_job_positions ialumni_personal) {
		this.ialumni_external_job_positions.add(ialumni_personal);
	}

	/**
	 * Removes a Alumni_external_job_positions to the ialumni_external_job_positions set.
	 * 
	 * @param ialumni_job_position_types
	 *            Alumni personal to be removed
	 */
	public void removeIalumni_external_job_positions(Alumni_external_job_positions ialumni_personal) {
		this.ialumni_external_job_positions.remove(ialumni_personal);
	}

	/**
	 * @return the ialumni_irb_job_positions
	 */
	public Set<Alumni_irb_job_positions> getIalumni_irb_job_positions() {
		return ialumni_irb_job_positions;
	}

	/**
	 * @param ialumni_job_position_types
	 *            the ialumni_job_position_types to set.
	 */
	public void setIalumni_irb_job_positions(Set<Alumni_irb_job_positions> ialumni_irb_job_positions) {
		this.ialumni_irb_job_positions = ialumni_irb_job_positions;
	}

	/**
	 * Adds a Alumni personal to the ialumni_job_position_types set.
	 * 
	 * @param ialumni_job_position_types
	 *            Alumni personal to be added
	 */
	public void addIalumni_irb_job_positions(Alumni_irb_job_positions ialumni_personal) {
		this.ialumni_irb_job_positions.add(ialumni_personal);
	}

	/**
	 * Removes a Alumni personal to the ialumni_job_position_types set.
	 * 
	 * @param ialumni_job_position_types
	 *            Alumni personal to be removed
	 */
	public void removeIalumni_irb_job_positions(Alumni_irb_job_positions ialumni_personal) {
		this.ialumni_irb_job_positions.remove(ialumni_personal);
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