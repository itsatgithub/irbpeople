package presentation.formbeans.objects;

import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-ID-form-bean of Alumni_personal. ID-formBeans contain all the
 * attributes of Alumni_personal  but it does not contain the associations.
 * 
 * @author Automatika - JustInMind SL
 */
public class Alumni_personal_IDForm extends ValidatorFormAndAction {

	private String alumni_personalcode;
	private String external;
	private String firstname;
	private String surname;
	private String irb_surname;
	private String birth;
	private String email;
	private String url;
	private String facebook;
	private String linkedin;
	private String twitter;
	private String keywords;
	private String biography;
	private String awards;
	private String ORCIDID;
	private String researcherid;
	private String pubmedid;
	private String verified;
	private String show_data;
	
	private String remarks;
	private String skype;
	private String cellphone;
	private String deceased;
	private String communications_wanted;

	public Alumni_personal_IDForm getAlumni_personal() {
		return this;
	}


	@Override
	public String toString() {

		if (getAlumni_personalcode() == null || getAlumni_personalcode().equals(""))
			return "";

		String result = getFirstname() + "";
		return (result != null) ? result : "";

	}


	public String getAlumni_personalcode() {
		return alumni_personalcode;
	}


	public void setAlumni_personalcode(String alumni_personalcode) {
		this.alumni_personalcode = alumni_personalcode;
	}


	public String getExternal() {
		return external;
	}


	public void setExternal(String external) {
		this.external = external;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}


	public String getIrb_surname() {
		return irb_surname;
	}


	public void setIrb_surname(String irb_surname) {
		this.irb_surname = irb_surname;
	}


	public String getBirth() {
		return birth;
	}


	public void setBirth(String birth) {
		this.birth = birth;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getFacebook() {
		return facebook;
	}


	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}


	public String getLinkedin() {
		return linkedin;
	}


	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
	}


	public String getTwitter() {
		return twitter;
	}


	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}


	public String getKeywords() {
		return keywords;
	}


	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}


	public String getBiography() {
		return biography;
	}


	public void setBiography(String biography) {
		this.biography = biography;
	}


	public String getAwards() {
		return awards;
	}


	public void setAwards(String awards) {
		this.awards = awards;
	}


	public String getORCIDID() {
		return ORCIDID;
	}


	public void setORCIDID(String oRCIDID) {
		ORCIDID = oRCIDID;
	}


	public String getResearcherid() {
		return researcherid;
	}


	public void setResearcherid(String researcherid) {
		this.researcherid = researcherid;
	}


	public String getPubmedid() {
		return pubmedid;
	}


	public void setPubmedid(String pubmedid) {
		this.pubmedid = pubmedid;
	}


	public String getVerified() {
		return verified;
	}


	public void setVerified(String verified) {
		this.verified = verified;
	}


	public String getShow_data() {
		return show_data;
	}


	public void setShow_data(String show_data) {
		this.show_data = show_data;
	}
	
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getSkype() {
		return skype;
	}

	public void setSkype(String skype) {
		this.skype = skype;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getDeceased() {
		return deceased;
	}

	public void setDeceased(String deceased) {
		this.deceased = deceased;
	}

	public String getCommunications_wanted() {
		return communications_wanted;
	}

	public void setCommunications_wanted(String communications_wanted) {
		this.communications_wanted = communications_wanted;
	}

}