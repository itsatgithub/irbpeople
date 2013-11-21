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
public class Alumni_external_job_positions implements Persistent {

	// Code (primary key) of this alumni_titles
	private String alumni_external_job_positionscode;
	// Version of the current instance. This attribute is used by hibernate to
	// control concurrent modifications.
	private int version;
	// Indicates whereas this alumni_external_job_positions is deleted
	private boolean deleted = false;

	// definition of the specific attributes

	private String description;
	private String short_description;
	private int order_number;

	private Alumni_job_position_types job_position_types;
	
	Set<Alumni_external_jobs> ialumni_external_jobs = new HashSet<Alumni_external_jobs>();

	/**
	 * Default Constructor which creates an empty alumni_titles
	 */
	public Alumni_external_job_positions() {
	}

	/**
	 * Returns the code (primary key) of this alumni_external_job_positions. Convenience method
	 * which calls
	 * {@link bussineslogic.objects.alumni_external_job_positions#getAlumni_external_job_positionscode()
	 * getAlumni_external_job_positionscode()}
	 * 
	 * @return a String with the code
	 */
	public String getCode() {
		return getAlumni_external_job_positionscode();
	}

	/**
	 * Set the code (primary key) of this alumni_external_job_positions. Convenience method
	 * which calls
	 * {@link bussineslogic.objects.alumni_external_job_positions#setAlumni_external_job_positionscode(String)
	 * setAlumni_external_job_positionscode(String)}
	 * 
	 * @param alumni_external_job_positionscode
	 *            the String with the code
	 */
	public void setCode(String alumni_external_job_positionscode) {
		setAlumni_external_job_positionscode(alumni_external_job_positionscode);
	}

	/**
	 * Returns the code (primary key) of this Alumni_external_job_positions.
	 * 
	 * @return a String with the code
	 */
	public String getAlumni_external_job_positionscode() {
		return alumni_external_job_positionscode;
	}

	/**
	 * Set the code (primary key) of this Alumni_external_job_positions
	 * 
	 * @param alumni_external_job_positionscode
	 *            the String with the code
	 */
	public void setAlumni_external_job_positionscode(String alumni_external_job_positionscode) {
		this.alumni_external_job_positionscode = alumni_external_job_positionscode;
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
	 * Tests if this alumni_external_job_positions has been deleted
	 * 
	 * @return true if this alumni_external_job_positions has been deleted
	 */
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * Sets the delete state of this alumni_external_job_positions
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
	public Set<Alumni_external_jobs> getIalumni_external_jobs() {
		return ialumni_external_jobs;
	}

	/**
	 * @param ialumni_external_job_positions
	 *            the ialumni_external_job_positions to set.
	 */
	public void setIalumni_external_jobs(Set<Alumni_external_jobs> ialumni_external_jobs) {
		this.ialumni_external_jobs = ialumni_external_jobs;
	}

	/**
	 * Adds a Alumni personal to the ialumni_external_job_positions set.
	 * 
	 * @param ialumni_external_job_positions
	 *            Alumni personal to be added
	 */
	public void addIalumni_external_jobs(Alumni_external_jobs ialumni_personal) {
		this.ialumni_external_jobs.add(ialumni_personal);
	}

	/**
	 * Removes a Alumni personal to the ialumni_external_job_positions set.
	 * 
	 * @param ialumni_external_job_positions
	 *            Alumni personal to be removed
	 */
	public void removeIalumni_external_jobs(Alumni_external_jobs ialumni_personal) {
		this.ialumni_external_jobs.remove(ialumni_personal);
	}

	public int getOrder_number() {
		return order_number;
	}

	public void setOrder_number(int order_number) {
		this.order_number = order_number;
	}

	public Alumni_job_position_types getJob_position_types(){
		return job_position_types;
	}
	public void setJob_position_types(Alumni_job_position_types job_position_types){
		this.job_position_types = job_position_types;
	}
	
	@Override
	public String toString() {
		if (getCode() == null || getCode().equals(""))
			return "";

		return getCode();
	}

}