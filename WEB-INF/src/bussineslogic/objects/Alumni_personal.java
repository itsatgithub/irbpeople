package bussineslogic.objects;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import utils.Persistent;

/**
 * This class contains business object 'alumni_personal'.
 * 
 * @author Automatika - JustInMind SL
 * 
 */
public class Alumni_personal implements Persistent {

	// Code (primary key) of this alumni_personal
	private String alumni_personalcode;
	// Version of the current instance. This attribute is used by hibernate to
	// control concurrent modifications.
	private int version;
	// Indicates whereas this alumni_personal is deleted
	private boolean deleted = false;

	// definition of the specific attributes
	private boolean external;
	private Alumni_titles titles;
	private String firstname;
	private String surname;
	private String irb_surname;
	private Gender gender;
	private Nationality nationality;
	private Nationality nationality_2;
	private Date birth;
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
	private boolean verified;
	private boolean show_data;
	private Set<Alumni_communications> ialumni_communications = new HashSet<Alumni_communications>();
	private Set<Alumni_external_jobs> ialumni_external_jobs = new HashSet<Alumni_external_jobs>();
	private Set<Alumni_irb_jobs> ialumni_irb_jobs = new HashSet<Alumni_irb_jobs>();
	private Set<Alumni_directory_data> ialumni_directory_data = new HashSet<Alumni_directory_data>();
	
	private String remarks;
	private String skype;
	private String cellphone;
	private boolean deceased;
	private boolean communications_wanted;

	/**
	 * Default Constructor which creates an empty alumni_personal
	 */
	public Alumni_personal() {
	}

	/**
	 * Returns the code (primary key) of this alumni_personal. Convenience
	 * method which calls
	 * {@link bussineslogic.objects.alumni_personal#getPersonalcode()
	 * getPersonalcode()}
	 * 
	 * @return a String with the code
	 */
	public String getCode() {
		return getAlumni_personalcode();
	}

	/**
	 * Set the code (primary key) of this alumni_personal. Convenience method
	 * which calls
	 * {@link bussineslogic.objects.alumni_personals#setPersonalcode(String)
	 * setPersonalcode(String)}
	 * 
	 * @param personalcode
	 *            the String with the code
	 */
	public void setCode(String personalcode) {
		setAlumni_personalcode(personalcode);
	}

	/**
	 * Returns the code (primary key) of this Alumni_personal.
	 * 
	 * @return a String with the code
	 */
	public String getAlumni_personalcode() {
		return alumni_personalcode;
	}

	/**
	 * Set the code (primary key) of this Alumni_personal
	 * 
	 * @param alumni_personalcode
	 *            the String with the code
	 */
	public void setAlumni_personalcode(String alumni_personalcode) {
		this.alumni_personalcode = alumni_personalcode;
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
	 * Tests if this alumni_personal has been deleted
	 * 
	 * @return true if this alumni_personal has been deleted
	 */
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * Sets the delete state of this alumni_personal
	 * 
	 * @param deleted
	 *            true if the object is deleted.
	 */
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public boolean isExternal() {
		return external;
	}

	public void setExternal(boolean external) {
		this.external = external;
	}

	public Alumni_titles getTitles() {
		return titles;
	}

	public void setTitles(Alumni_titles titles) {
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

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Nationality getNationality() {
		return nationality;
	}

	public void setNationality(Nationality nationality) {
		this.nationality = nationality;
	}

	public Nationality getNationality_2() {
		return nationality_2;
	}

	public void setNationality_2(Nationality nationality_2) {
		this.nationality_2 = nationality_2;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
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

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public boolean isShow_data() {
		return show_data;
	}

	public void setShow_data(boolean show_data) {
		this.show_data = show_data;
	}

	/**
	 * @return the ialumni_communications
	 */
	public Set<Alumni_communications> getIalumni_communications() {
		return ialumni_communications;
	}

	public String[] getIalumni_communicationscode_array() {
		if (ialumni_communications == null) {
			return null;
		}
		Iterator<Alumni_communications> it = ialumni_communications.iterator();
		String[] alumni_communications_array = new String[ialumni_communications.size()];
		int i = 0;
		while (it.hasNext()) {
			alumni_communications_array[i++] = it.next().getAlumni_communicationscode();
		}
		return alumni_communications_array;

	}

	public String[] getIalumni_communicationsdescription_array() {
		if (ialumni_communications == null) {
			return null;
		}
		Iterator<Alumni_communications> it = ialumni_communications.iterator();
		String[] alumni_communications_array = new String[ialumni_communications.size()];
		int i = 0;
		while (it.hasNext()) {
			alumni_communications_array[i++] = it.next().getDescription();
		}
		return alumni_communications_array;
		
	}

	/**
	 * @param ialumni_communications
	 *            the ialumni_communications to set.
	 */
	public void setIalumni_communications(Set<Alumni_communications> ialumni_communications) {
		this.ialumni_communications = ialumni_communications;		
	}

	/**
	 * Adds a Alumni communications to the ialumni_communications set.
	 * 
	 * @param ialumni_communications
	 *            Alumni communications to be added
	 */
	public void addIalumni_communications(Alumni_communications ialumni_communications) {
		this.ialumni_communications.add(ialumni_communications);
	}

	/**
	 * Removes a Alumni communications to the ialumni_communications set.
	 * 
	 * @param ialumni_communications
	 *            Alumni communications to be removed
	 */
	public void removeIalumni_communications(Alumni_communications ialumni_communications) {
		this.ialumni_communications.remove(ialumni_communications);
	}

	/**
	 * Removes all Alumni communications to the ialumni_personal set.
	 * 
	 */
	public void clearIalumni_communications() {
		if (ialumni_communications != null) {
			this.ialumni_communications.clear();
		}
	}

	/**
	 * @return the ialumni_external_jobs
	 */
	public Set<Alumni_external_jobs> getIalumni_external_jobs() {
		return ialumni_external_jobs;
	}

	/**
	 * @param ialumni_external_jobs
	 *            the ialumni_external_jobs to set.
	 */
	public void setIalumni_external_jobs(Set<Alumni_external_jobs> ialumni_external_jobs) {
		this.ialumni_external_jobs = ialumni_external_jobs;
	}

	/**
	 * Adds a Alumni communications to the ialumni_external_jobs set.
	 * 
	 * @param ialumni_external_jobs
	 *            Alumni communications to be added
	 */
	public void addIalumni_external_jobs(Alumni_external_jobs ialumni_external_jobs) {
		this.ialumni_external_jobs.add(ialumni_external_jobs);
	}

	/**
	 * Removes a Alumni communications to the ialumni_external_jobs set.
	 * 
	 * @param ialumni_external_jobs
	 *            Alumni communications to be removed
	 */
	public void removeIalumni_external_jobs(Alumni_external_jobs ialumni_external_jobs) {
		this.ialumni_external_jobs.remove(ialumni_external_jobs);
	}

	/**
	 * @return the ialumni_irb_jobs
	 */
	public Set<Alumni_irb_jobs> getIalumni_irb_jobs() {
		return ialumni_irb_jobs;
	}

	/**
	 * @param ialumni_irb_jobs
	 *            the ialumni_irb_jobs to set.
	 */
	public void setIalumni_irb_jobs(Set<Alumni_irb_jobs> ialumni_irb_jobs) {
		this.ialumni_irb_jobs = ialumni_irb_jobs;
	}

	/**
	 * Adds a Alumni communications to the ialumni_irb_jobs set.
	 * 
	 * @param ialumni_irb_jobs
	 *            Alumni communications to be added
	 */
	public void addIalumni_irb_jobs(Alumni_irb_jobs ialumni_irb_jobs) {
		this.ialumni_irb_jobs.add(ialumni_irb_jobs);
	}

	/**
	 * Removes a Alumni communications to the ialumni_irb_jobs set.
	 * 
	 * @param ialumni_irb_jobs
	 *            Alumni communications to be removed
	 */
	public void removeIalumni_irb_jobs(Alumni_irb_jobs ialumni_irb_jobs) {
		this.ialumni_irb_jobs.remove(ialumni_irb_jobs);
	}
	
	/**
	 * @return the ialumni_directory_data
	 */
	public Set<Alumni_directory_data> getIalumni_directory_data() {
		return ialumni_directory_data;
	}

	/**
	 * @param ialumni_directory_data
	 *            the ialumni_directory_data to set.
	 */
	public void setIalumni_directory_data(Set<Alumni_directory_data> ialumni_directory_data) {
		this.ialumni_directory_data = ialumni_directory_data;
	}

	/**
	 * Adds a Alumni communications to the ialumni_directory_data set.
	 * 
	 * @param ialumni_directory_data
	 *            Alumni communications to be added
	 */
	public void addIalumni_directory_data(Alumni_directory_data ialumni_directory_data) {
		this.ialumni_directory_data.add(ialumni_directory_data);
	}

	/**
	 * Removes a Alumni communications to the ialumni_directory_data set.
	 * 
	 * @param ialumni_directory_data
	 *            Alumni communications to be removed
	 */
	public void removeIalumni_directory_data(Alumni_directory_data ialumni_directory_data) {
		this.ialumni_directory_data.remove(ialumni_directory_data);
	}

	@Override
	public String toString() {
		if (getCode() == null || getCode().equals(""))
			return "";

		return getCode();
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

	public boolean isDeceased() {
		return deceased;
	}

	public void setDeceased(boolean deceased) {
		this.deceased = deceased;
	}

	public boolean isCommunications_wanted() {
		return communications_wanted;
	}

	public void setCommunications_wanted(boolean communications_wanted) {
		this.communications_wanted = communications_wanted;
	}

}