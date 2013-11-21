package presentation.formbeans.objects;

import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-form-bean of type_of_study
 * 
 * @author Automatika - JustInMind SL
 */
public class Alumni_external_jobs_IDForm extends ValidatorFormAndAction {

	private String alumni_external_jobscode;
	private String start_date;
	private String end_date;
	private String comments;
	private String institution;
	private String address;
	private String postcode;
	private String city;
	private String telephone;	
	
	@Override
	public String toString() {

		if (getAlumni_external_jobscode() == null || getAlumni_external_jobscode().equals(""))
			return "";

		String result = getStart_date() + " " + getEnd_date();
		return (result != null) ? result : "";

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

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
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

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

}