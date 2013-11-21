package presentation.formbeans.objects;

import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-form-bean of type_of_study
 * 
 * @author Automatika - JustInMind SL
 */
public class Alumni_external_jobs_Form extends ValidatorFormAndAction {

	String version;
	String alumni_external_jobscode;

	private Alumni_personal_IDForm personal;
	private String current;
	private String start_date;
	private String end_date;
	private Alumni_external_job_positions_IDForm external_job_positions;
	private String comments;
	private Alumni_external_job_sectors_IDForm external_job_sectors;
	private String institution;
	private String address;
	private String postcode;
	private String city;
	private Country_IDForm country;
	private String telephone;
	private String date;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String toString() {

		if (getAlumni_external_jobscode() == null || getAlumni_external_jobscode().equals(""))
			return "";

		String result = getStart_date() + " " + getEnd_date();
		return (result != null) ? result : "";

	}

	public Alumni_external_jobs_Form getAlumni_external_jobs() {
		return this;
	}

	public String get_descripcion() {
		return this.toString();
	}

	public String getAlumni_external_jobscode() {
		return alumni_external_jobscode;
	}

	public void setAlumni_external_jobscode(String alumni_external_jobscode) {
		this.alumni_external_jobscode = alumni_external_jobscode;
	}

	public Alumni_personal_IDForm getPersonal() {
		if (personal == null)
			personal = new Alumni_personal_IDForm();
		return personal;
	}

	public void setPersonal(Alumni_personal_IDForm personal) {
		this.personal = personal;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public Alumni_external_job_positions_IDForm getExternal_job_positions() {
		if (external_job_positions == null) {
			external_job_positions = new Alumni_external_job_positions_IDForm();
		}
		return external_job_positions;
	}

	public void setExternal_job_positions(Alumni_external_job_positions_IDForm external_job_positions) {
		this.external_job_positions = external_job_positions;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Alumni_external_job_sectors_IDForm getExternal_job_sectors() {
		if (external_job_sectors == null) {
			external_job_sectors = new Alumni_external_job_sectors_IDForm();
		}
		return external_job_sectors;
	}

	public void setExternal_job_sectors(Alumni_external_job_sectors_IDForm external_job_sectors) {
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

	public Country_IDForm getCountry() {
		if (country == null)
			country = new Country_IDForm();
		return country;
	}

	public void setCountry(Country_IDForm country) {
		this.country = country;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCurrent() {
		return current;
	}

	public void setCurrent(String current) {
		this.current = current;
	}

}