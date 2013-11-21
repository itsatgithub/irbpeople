package presentation.formbeans.objects;

import com.cc.framework.util.StringHelp;

import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-form-bean of personal
 * 
 * @author Automatika - JustInMind SL
 */
public class Alumni_personal_Form extends ValidatorFormAndAction {

	String version;
	String alumni_personalcode;

	// definition of the specific attributes
	private boolean external;
	private Alumni_titles_IDForm titles;
	private String firstname;
	private String surname;
	private String irb_surname;
	private Gender_IDForm gender;
	private Nationality_IDForm nationality;
	private Nationality_IDForm nationality_2;
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
	private String[] alumni_communications;

	public Alumni_personal_Form getAlumni_personal() {
		return this;
	}

	@Override
	public String toString() {

		if (getAlumni_personalcode() == null || getAlumni_personalcode().equals(""))
			return "";

		String result = getFirstname() + " " + getSurname();
		return (result != null) ? result : "";

	}

	public String get_descripcion() {
		return this.toString();
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getAlumni_personalcode() {
		return alumni_personalcode;
	}

	public void setAlumni_personalcode(String alumni_personalcode) {
		this.alumni_personalcode = alumni_personalcode;
	}

	public boolean isExternal() {
		return external;
	}

	public void setExternal(boolean external) {
		this.external = external;
	}

	public Alumni_titles_IDForm getTitles() {
		if (titles == null)
			titles = new Alumni_titles_IDForm();
		return titles;
	}

	public void setTitles(Alumni_titles_IDForm titles) {
		this.titles = titles;
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

	public Gender_IDForm getGender() {
		if (gender == null)
			gender = new Gender_IDForm();
		return gender;
	}

	public void setGender(Gender_IDForm gender) {
		this.gender = gender;
	}

	public Nationality_IDForm getNationality() {
		if (nationality == null)
			nationality = new Nationality_IDForm();
		return nationality;
	}

	public void setNationality(Nationality_IDForm nationality) {
		this.nationality = nationality;
	}

	public Nationality_IDForm getNationality_2() {
		if (nationality_2 == null)
			nationality_2 = new Nationality_IDForm();
		return nationality_2;
	}

	public void setNationality_2(Nationality_IDForm nationality_2) {
		this.nationality_2 = nationality_2;
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

	public String[] getAlumni_communications() {
		return alumni_communications;
	}
	
	public String getAlumni_communications_string(){
		return StringHelp.join(alumni_communications,  ',');
	}

	public void setAlumni_communications(String[] alumni_communications) {
		this.alumni_communications = alumni_communications;
	}

}