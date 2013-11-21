package presentation.formbeans.objects;

import java.util.HashSet;
import java.util.Set;

import utils.validator.ValidatorFormAndAction;
import bussineslogic.objects.Alumni_external_jobs;

/**
 * Nested-pojo-form-bean of type_of_study
 * 
 * @author Automatika - JustInMind SL
 */
public class Alumni_external_job_positions_Form extends ValidatorFormAndAction {

	String version;
	String alumni_external_job_positionscode;

	private String description = null;
	private String short_description = null;
	private String order_number;

	private Alumni_job_position_types_IDForm job_position_types = null;
	Set<Alumni_external_jobs> ialumni_external_jobs = new HashSet<Alumni_external_jobs>();

	public void setAlumni_external_job_positionscode(String alumni_external_job_positionscode) {
		this.alumni_external_job_positionscode = alumni_external_job_positionscode;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String toString() {

		if (getAlumni_external_job_positionscode() == null || getAlumni_external_job_positionscode().equals(""))
			return "";

		String result = getDescription() + "";
		return (result != null) ? result : "";

	}

	public String get_descripcion() {
		return this.toString();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getShort_description() {
		return short_description;
	}

	public void setShort_description(String short_description) {
		this.short_description = short_description;
	}

	public String getOrder_number() {
		return order_number;
	}

	public void setOrder_number(String order_number) {
		this.order_number = order_number;
	}

	public Alumni_job_position_types_IDForm getJob_position_types() {
		if (job_position_types == null) {
			job_position_types = new Alumni_job_position_types_IDForm();
		}
		return job_position_types;
	}

	public void setJob_position_types(Alumni_job_position_types_IDForm job_position_types) {
		this.job_position_types = job_position_types;
	}

	public Alumni_external_job_positions_Form getAlumni_external_job_positions() {
		return this;
	}

	public String getAlumni_external_job_positionscode() {
		return alumni_external_job_positionscode;
	}

	/**
	 * @return the ialumni_external_job_sectors
	 */
	public Set<Alumni_external_jobs> getIalumni_external_jobs() {
		return ialumni_external_jobs;
	}

	/**
	 * @param ialumni_external_job_sectors
	 *            the ialumni_external_job_sectors to set.
	 */
	public void setIalumni_external_jobs(Set<Alumni_external_jobs> ialumni_external_jobs) {
		this.ialumni_external_jobs = ialumni_external_jobs;
	}

	/**
	 * Adds a Alumni personal to the ialumni_external_job_sectors set.
	 * 
	 * @param ialumni_external_job_sectors
	 *            Alumni personal to be added
	 */
	public void addIalumni_external_jobs(Alumni_external_jobs ialumni_personal) {
		this.ialumni_external_jobs.add(ialumni_personal);
	}

	/**
	 * Removes a Alumni personal to the ialumni_external_job_sectors set.
	 * 
	 * @param ialumni_external_job_sectors
	 *            Alumni personal to be removed
	 */
	public void removeIalumni_external_jobs(Alumni_external_jobs ialumni_personal) {
		this.ialumni_external_jobs.remove(ialumni_personal);
	}
}