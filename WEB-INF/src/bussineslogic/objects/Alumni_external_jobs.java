package bussineslogic.objects;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import utils.Persistent;

/**
 * This class contains business object 'alumni_titles'.
 * 
 * @author Automatika - JustInMind SL
 * 
 */
public class Alumni_external_jobs implements Persistent {

	// Code (primary key) of this alumni_titles
	private String alumni_external_jobscode;
	// Version of the current instance. This attribute is used by hibernate to
	// control concurrent modifications.
	private int version;
	// Indicates whereas this alumni_external_job is deleted
	private boolean deleted = false;

	// definition of the specific attributes

	private Alumni_personal personal;
	private Date start_date;
	private Date end_date;
	private Alumni_external_job_positions external_job_positions;
	private String comments;
	private Alumni_external_job_sectors external_job_sectors;
	private String institution;
	private String address;
	private String postcode;
	private String city;
	private Country country;
	private String telephone;
	private boolean current = false;	
	
	/**
	 * Default Constructor which creates an empty alumni_external_jobs
	 */
	public Alumni_external_jobs() {
	}

	/**
	 * Returns the code (primary key) of this alumni_external_jobs.
	 * 
	 * @return a String with the code
	 */
	public String getCode() {
		return getAlumni_external_jobscode();
	}

	/**
	 * Set the code (primary key) of this alumni_external_job. Convenience
	 * method which calls
	 * {@link bussineslogic.objects.alumni_external_job#setAlumni_external_jobscode(String)
	 * setAlumni_external_jobscode(String)}
	 * 
	 * @param alumni_external_jobscode
	 *            the String with the code
	 */
	public void setCode(String alumni_external_jobscode) {
		setAlumni_external_jobscode(alumni_external_jobscode);
	}

	/**
	 * Returns the code (primary key) of this Alumni_external_job.
	 * 
	 * @return a String with the code
	 */
	public String getAlumni_external_jobscode() {
		return alumni_external_jobscode;
	}

	/**
	 * Set the code (primary key) of this Alumni_external_job
	 * 
	 * @param alumni_external_jobscode
	 *            the String with the code
	 */
	public void setAlumni_external_jobscode(String alumni_external_jobscode) {
		this.alumni_external_jobscode = alumni_external_jobscode;
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
	 * Tests if this alumni_external_job has been deleted
	 * 
	 * @return true if this alumni_external_job has been deleted
	 */
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * Sets the delete state of this alumni_external_job
	 * 
	 * @param deleted
	 *            true if the object is deleted.
	 */
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public Alumni_personal getPersonal() {
		return personal;
	}

	public void setPersonal(Alumni_personal personal) {
		this.personal = personal;
	}

	public Alumni_external_job_positions getExternal_job_positions() {
		return external_job_positions;
	}

	public void setExternal_job_positions(Alumni_external_job_positions external_job_positions) {
		this.external_job_positions = external_job_positions;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Alumni_external_job_sectors getExternal_job_sectors() {
		return external_job_sectors;
	}

	public void setExternal_job_sectors(Alumni_external_job_sectors external_job_sectors) {
		this.external_job_sectors = external_job_sectors;
	}

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public boolean isCurrent() {
		return current;
	}

	public void setCurrent(boolean current) {
		this.current = current;
	}

	@Override
	public String toString() {
		if (getCode() == null || getCode().equals(""))
			return "";

		return getCode();
	}

}