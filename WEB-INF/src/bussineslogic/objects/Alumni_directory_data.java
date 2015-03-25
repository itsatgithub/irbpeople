package bussineslogic.objects;

import java.util.Date;

import utils.Persistent;

/**
 * This class contains business object 'alumni_titles'.
 * 
 * @author Automatika - JustInMind SL
 * 
 */
public class Alumni_directory_data implements Persistent {

	// Code (primary key) of this alumni_titles
	private String alumni_directory_datacode;
	// Version of the current instance. This attribute is used by hibernate to
	// control concurrent modifications.
	private int version;
	// Indicates whereas this alumni_irb_job is deleted
	private boolean deleted = false;

	// definition of the specific attributes

	private Date start_date;
	private Date end_date;
	private Alumni_personal personal;
	private Unit unit;
	private Unit unit_2;
	
	private Research_group research_group;
	private Research_group research_group_2;
	
	private Alumni_irb_job_positions irb_job_positions;

	/**
	 * Default Constructor which creates an empty alumni_directory_data
	 */
	public Alumni_directory_data() {
	}

	/**
	 * Returns the code (primary key) of this alumni_directory_data.
	 * 
	 * @return a String with the code
	 */
	public String getCode() {
		return getAlumni_directory_datacode();
	}

	/**
	 * Set the code (primary key) of this alumni_irb_job. Convenience method
	 * which calls
	 * {@link bussineslogic.objects.alumni_irb_job#setAlumni_directory_datacode(String)
	 * setAlumni_directory_datacode(String)}
	 * 
	 * @param alumni_directory_datacode
	 *            the String with the code
	 */
	public void setCode(String alumni_directory_datacode) {
		setAlumni_directory_datacode(alumni_directory_datacode);
	}

	/**
	 * Returns the code (primary key) of this Alumni_irb_job.
	 * 
	 * @return a String with the code
	 */
	public String getAlumni_directory_datacode() {
		return alumni_directory_datacode;
	}

	/**
	 * Set the code (primary key) of this Alumni_irb_job
	 * 
	 * @param alumni_directory_datacode
	 *            the String with the code
	 */
	public void setAlumni_directory_datacode(String alumni_directory_datacode) {
		this.alumni_directory_datacode = alumni_directory_datacode;
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
	 * Tests if this alumni_irb_job has been deleted
	 * 
	 * @return true if this alumni_irb_job has been deleted
	 */
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * Sets the delete state of this alumni_irb_job
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

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public Unit getUnit_2() {
		return unit_2;
	}

	public void setUnit_2(Unit unit_2) {
		this.unit_2 = unit_2;
	}
	
	public Research_group getResearch_group() {
		return research_group;
	}

	public void setResearch_group(Research_group research_group) {
		this.research_group = research_group;
	}

	public Research_group getResearch_group_2() {
		return research_group_2;
	}
	
	public void setResearch_group_2(Research_group research_group) {
		this.research_group_2 = research_group;
	}

	public Alumni_irb_job_positions getIrb_job_positions() {
		return irb_job_positions;
	}

	public void setIrb_job_positions(Alumni_irb_job_positions irb_job_positions) {
		this.irb_job_positions = irb_job_positions;
	}

	@Override
	public String toString() {
		if (getCode() == null || getCode().equals(""))
			return "";

		return getCode();
	}

}