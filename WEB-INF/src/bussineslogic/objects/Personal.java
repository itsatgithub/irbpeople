package bussineslogic.objects;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import utils.Persistent;

import com.justinmind.usermanagement.entity.Language;

/**
 * This class contains business object 'personal'.
 * 
 * @author Automatika - JustInMind SL
 * 
 */
public class Personal implements Persistent {

	// Code (primary key) of this personal
	private String personalcode;

	// Version of the current instance. This attribute is used by hibernate to
	// control concurrent modifications.
	private int version;

	// Indicates whereas this personal is deleted
	private boolean deleted = false;

	// definition of the specific attributes

	String name;

	String surname1;

	String surname2;

	String dni;

	Date birth_date;

	String birth_city;

	String street;

	String city;

	String zip_code;

	String phone;

	String phone2;

	String ice_phone;

	String ss_number;

	String bank_account;
	
	String bic;
	
	String swift;

	String research_project;

	String sponsoring_agency;

	String holding_institution;

	String principal_investigator;

	String end_of_contract_reason;

	String end_of_contract_address;

	String end_of_contract_city;

	String end_of_contract_zip_code;

	String end_of_contract_phone;

	String end_of_contract_email;

	String personal_email;

	Personalstate state;

	String username;

	Country birth_country;

	Nationality nationality;

	Nationality nationality_2;

	Country country;

	Payment payments;

	Country end_of_contract_country;

	Gender gender;

	Marital_status marital_status;

	Bank bank;

	Working_hours working_hours;

	Category category;

	PersonalPhoto photo;

	Language language;

	String second_affiliation;

	Set<Grant_concession> igrant_concession_personal = new HashSet<Grant_concession>();
	
	Set<Academic_info> iacademic_info_personal = new HashSet<Academic_info>();

	Set<Benefits> ibenefits_personal = new HashSet<Benefits>();

	Set<Education> ieducation_personal = new HashSet<Education>();

	Set<Funding_detail> ifunding_detail_personal = new HashSet<Funding_detail>();

	Set<Holiday> iholiday_personal = new HashSet<Holiday>();

	Set<Work_experience> iwork_experience_personal = new HashSet<Work_experience>();

	Set<Child> ichild_personal = new HashSet<Child>();

	Set<Professional> iprofessional_personal = new HashSet<Professional>();

	Set<Personal_comment> ipersonal_comments = new HashSet<Personal_comment>();

	Set<Compensation> icompensation_personal = new HashSet<Compensation>();

	Set<Research_group> isupervisor = new HashSet<Research_group>();

	Set<Unit> isupervisor_unit = new HashSet<Unit>();

	Set<Irbholiday> iirbholiday = new HashSet<Irbholiday>();

	Set<Irbholidayinfo> iirbholidayinfo = new HashSet<Irbholidayinfo>();

	// atributs per a validacio del supervisor

	String validationCode;

	/**
	 * Default Constructor which creates an empty personal
	 */
	public Personal() {}

	/**
	 * Returns the code (primary key) of this personal.
	 * 
	 * @return a String with the code
	 */
	public String getPersonalcode() {
		return personalcode;
	}

	/**
	 * Set the code (primary key) of this personal
	 * 
	 * @param personalcode
	 *            the String with the code
	 */
	public void setPersonalcode(String personalcode) {
		this.personalcode = personalcode;
	}

	/**
	 * Returns the code (primary key) of this personal. Convenience method which
	 * calls {@link bussineslogic.objects.Personal#getPersonalcode()
	 * getPersonalcode()}
	 * 
	 * @return a String with the code
	 */
	public String getCode() {
		return getPersonalcode();
	}

	/**
	 * Set the code (primary key) of this personal. Convenience method which
	 * calls {@link bussineslogic.objects.Personal#setPersonalcode(String)
	 * getPersonalcode(String)}
	 * 
	 * @param personalcode
	 *            the String with the code
	 */
	public void setCode(String personalcode) {
		setPersonalcode(personalcode);
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
	 * Tests if this personal has been deleted
	 * 
	 * @return true if this personal has been deleted
	 */
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * Sets the delete state of this personal
	 * 
	 * @param deleted
	 *            true if the object is deleted.
	 */
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the surname1
	 */
	public String getSurname1() {
		return surname1;
	}

	/**
	 * @param surname1
	 *            the surname1 to set.
	 */
	public void setSurname1(String surname1) {
		this.surname1 = surname1;
	}

	/**
	 * @return the surname2
	 */
	public String getSurname2() {
		return surname2;
	}

	/**
	 * @param surname2
	 *            the surname2 to set.
	 */
	public void setSurname2(String surname2) {
		this.surname2 = surname2;
	}

	/**
	 * @return the dni
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * @param dni
	 *            the dni to set.
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * @return the birth_date
	 */
	public Date getBirth_date() {
		return birth_date;
	}

	/**
	 * @param birth_date
	 *            the birth_date to set.
	 */
	public void setBirth_date(Date birth_date) {
		this.birth_date = birth_date;
	}

	/**
	 * @return the birth_city
	 */
	public String getBirth_city() {
		return birth_city;
	}

	/**
	 * @param birth_city
	 *            the birth_city to set.
	 */
	public void setBirth_city(String birth_city) {
		this.birth_city = birth_city;
	}

	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param street
	 *            the street to set.
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set.
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the zip_code
	 */
	public String getZip_code() {
		return zip_code;
	}

	/**
	 * @param zip_code
	 *            the zip_code to set.
	 */
	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            the phone to set.
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the phone2
	 */
	public String getPhone2() {
		return phone2;
	}

	/**
	 * @param phone2
	 *            the phone2 to set.
	 */
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	/**
	 * @return the ice_phone
	 */
	public String getIce_phone() {
		return ice_phone;
	}

	/**
	 * @param ice_phone
	 *            the ice_phone to set.
	 */
	public void setIce_phone(String ice_phone) {
		this.ice_phone = ice_phone;
	}

	/**
	 * @return the ss_number
	 */
	public String getSs_number() {
		return ss_number;
	}

	/**
	 * @param ss_number
	 *            the ss_number to set.
	 */
	public void setSs_number(String ss_number) {
		this.ss_number = ss_number;
	}

	/**
	 * @return the bank_account
	 */
	public String getBank_account() {
		return bank_account;
	}

	/**
	 * @param bank_account
	 *            the bank_account to set.
	 */
	public void setBank_account(String bank_account) {
		this.bank_account = bank_account;
	}

	/**
	 * @return the bic
	 */
	public String getBic(){
		return bic;
	}

	/**
	* @param bic the bic to set.
	*/
	public void setBic(String bic){
		this.bic=bic;
	}
	
	/**
	 * @return the swift
	 */
	public String getSwift(){
		return swift;
	}
	
	/**
	 * @param swift the swift to set.
	 */
	public void setSwift(String swift){
		this.swift=swift;
	}

	
	/**
	 * @return the research_project
	 */
	public String getResearch_project() {
		return research_project;
	}

	/**
	 * @param research_project
	 *            the research_project to set.
	 */
	public void setResearch_project(String research_project) {
		this.research_project = research_project;
	}

	/**
	 * @return the sponsoring_agency
	 */
	public String getSponsoring_agency() {
		return sponsoring_agency;
	}

	/**
	 * @param sponsoring_agency
	 *            the sponsoring_agency to set.
	 */
	public void setSponsoring_agency(String sponsoring_agency) {
		this.sponsoring_agency = sponsoring_agency;
	}

	/**
	 * @return the holding_institution
	 */
	public String getHolding_institution() {
		return holding_institution;
	}

	/**
	 * @param holding_institution
	 *            the holding_institution to set.
	 */
	public void setHolding_institution(String holding_institution) {
		this.holding_institution = holding_institution;
	}

	/**
	 * @return the principal_investigator
	 */
	public String getPrincipal_investigator() {
		return principal_investigator;
	}

	/**
	 * @param principal_investigator
	 *            the principal_investigator to set.
	 */
	public void setPrincipal_investigator(String principal_investigator) {
		this.principal_investigator = principal_investigator;
	}

	/**
	 * @return the end_of_contract_reason
	 */
	public String getEnd_of_contract_reason() {
		return end_of_contract_reason;
	}

	/**
	 * @param end_of_contract_reason
	 *            the end_of_contract_reason to set.
	 */
	public void setEnd_of_contract_reason(String end_of_contract_reason) {
		this.end_of_contract_reason = end_of_contract_reason;
	}

	/**
	 * @return the end_of_contract_address
	 */
	public String getEnd_of_contract_address() {
		return end_of_contract_address;
	}

	/**
	 * @param end_of_contract_address
	 *            the end_of_contract_address to set.
	 */
	public void setEnd_of_contract_address(String end_of_contract_address) {
		this.end_of_contract_address = end_of_contract_address;
	}

	/**
	 * @return the end_of_contract_city
	 */
	public String getEnd_of_contract_city() {
		return end_of_contract_city;
	}

	/**
	 * @param end_of_contract_city
	 *            the end_of_contract_city to set.
	 */
	public void setEnd_of_contract_city(String end_of_contract_city) {
		this.end_of_contract_city = end_of_contract_city;
	}

	/**
	 * @return the end_of_contract_zip_code
	 */
	public String getEnd_of_contract_zip_code() {
		return end_of_contract_zip_code;
	}

	/**
	 * @param end_of_contract_zip_code
	 *            the end_of_contract_zip_code to set.
	 */
	public void setEnd_of_contract_zip_code(String end_of_contract_zip_code) {
		this.end_of_contract_zip_code = end_of_contract_zip_code;
	}

	/**
	 * @return the end_of_contract_phone
	 */
	public String getEnd_of_contract_phone() {
		return end_of_contract_phone;
	}

	/**
	 * @param end_of_contract_phone
	 *            the end_of_contract_phone to set.
	 */
	public void setEnd_of_contract_phone(String end_of_contract_phone) {
		this.end_of_contract_phone = end_of_contract_phone;
	}

	/**
	 * @return the end_of_contract_email
	 */
	public String getEnd_of_contract_email() {
		return end_of_contract_email;
	}

	/**
	 * @param end_of_contract_email
	 *            the end_of_contract_email to set.
	 */
	public void setEnd_of_contract_email(String end_of_contract_email) {
		this.end_of_contract_email = end_of_contract_email;
	}

	/**
	 * @return the state
	 */
	public Personalstate getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set.
	 */
	public void setState(Personalstate state) {
		this.state = state;
	}

	/**
	 * @return the usercode
	 */
	public String getUsercode() {
		return getCode();
	}

	/**
	 * @return the birth_country
	 */
	public Country getBirth_country() {
		return birth_country;
	}

	/**
	 * @param birth_country
	 *            the birth_country to set.
	 */
	public void setBirth_country(Country birth_country) {
		this.birth_country = birth_country;
	}

	/**
	 * @return the nationality
	 */
	public Nationality getNationality() {
		return nationality;
	}

	/**
	 * @param nationality
	 *            the nationality to set.
	 */
	public void setNationality(Nationality nationality) {
		this.nationality = nationality;
	}

	/**
	 * @return the nationality_2
	 */
	public Nationality getNationality_2() {
		return nationality_2;
	}

	/**
	 * @param nationality_2
	 *            the nationality_2 to set.
	 */
	public void setNationality_2(Nationality nationality_2) {
		this.nationality_2 = nationality_2;
	}

	/**
	 * @return the country
	 */
	public Country getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set.
	 */
	public void setCountry(Country country) {
		this.country = country;
	}

	/**
	 * @return the payments
	 */
	public Payment getPayments() {
		return payments;
	}

	/**
	 * @param payments
	 *            the payments to set.
	 */
	public void setPayments(Payment payments) {
		this.payments = payments;
	}

	/**
	 * @return the end_of_contract_country
	 */
	public Country getEnd_of_contract_country() {
		return end_of_contract_country;
	}

	/**
	 * @param end_of_contract_country
	 *            the end_of_contract_country to set.
	 */
	public void setEnd_of_contract_country(Country end_of_contract_country) {
		this.end_of_contract_country = end_of_contract_country;
	}

	/**
	 * @return the gender
	 */
	public Gender getGender() {
		return gender;
	}

	/**
	 * @param gender
	 *            the gender to set.
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	/**
	 * @return the marital_status
	 */
	public Marital_status getMarital_status() {
		return marital_status;
	}

	/**
	 * @param marital_status
	 *            the marital_status to set.
	 */
	public void setMarital_status(Marital_status marital_status) {
		this.marital_status = marital_status;
	}

	/**
	 * @return the bank
	 */
	public Bank getBank() {
		return bank;
	}

	/**
	 * @param bank
	 *            the bank to set.
	 */
	public void setBank(Bank bank) {
		this.bank = bank;
	}

	/**
	 * @return the working_hours
	 */
	public Working_hours getWorking_hours() {
		return working_hours;
	}

	/**
	 * @param working_hours
	 *            the working_hours to set.
	 */
	public void setWorking_hours(Working_hours working_hours) {
		this.working_hours = working_hours;
	}

	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set.
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * @return the igrant_concession_personal
	 */
	public Set<Grant_concession> getIgrant_concession_personal() {
		return igrant_concession_personal;
	}

	/**
	 * @param igrant_concession_personal
	 *            the igrant_concession_personal to set.
	 */
	public void setIgrant_concession_personal(Set<Grant_concession> igrant_concession_personal) {
		this.igrant_concession_personal = igrant_concession_personal;
	}

	/**
	 * Adds a Grant_concession to the igrant_concession_personal set.
	 * 
	 * @param igrant_concession_personal
	 *            Grant_concession to be added
	 */
	public void addIgrant_concession_personal(Grant_concession igrant_concession_personal) {
		this.igrant_concession_personal.add(igrant_concession_personal);
	}

	/**
	 * Removes a Grant_concession to the igrant_concession_personal set.
	 * 
	 * @param igrant_concession_personal
	 *            Grant_concession to be removed
	 */
	public void removeIgrant_concession_personal(Grant_concession igrant_concession_personal) {
		this.igrant_concession_personal.remove(igrant_concession_personal);
	}
	
	/**
	 * @return the igrant_concession_personal
	 */
	public Set<Academic_info> getIacademic_info_personal() {
		return iacademic_info_personal;
	}
	
	/**
	 * @param igrant_concession_personal
	 *            the igrant_concession_personal to set.
	 */
	public void setIacademic_info_personal(Set<Academic_info> iacademic_info_personal) {
		this.iacademic_info_personal = iacademic_info_personal;
	}
	
	/**
	 * Adds a Grant_concession to the igrant_concession_personal set.
	 * 
	 * @param igrant_concession_personal
	 *            Grant_concession to be added
	 */
	public void addIacademic_info_personal(Academic_info iacademic_info_personal) {
		this.iacademic_info_personal.add(iacademic_info_personal);
	}
	
	/**
	 * Removes a Grant_concession to the igrant_concession_personal set.
	 * 
	 * @param igrant_concession_personal
	 *            Grant_concession to be removed
	 */
	public void removeIacademic_info_personal(Academic_info iacademic_info_personal) {
		this.iacademic_info_personal.remove(iacademic_info_personal);
	}

	/**
	 * @return the ibenefits_personal
	 */
	public Set<Benefits> getIbenefits_personal() {
		return ibenefits_personal;
	}

	/**
	 * @param ibenefits_personal
	 *            the ibenefits_personal to set.
	 */
	public void setIbenefits_personal(Set<Benefits> ibenefits_personal) {
		this.ibenefits_personal = ibenefits_personal;
	}

	/**
	 * Adds a Benefits to the ibenefits_personal set.
	 * 
	 * @param ibenefits_personal
	 *            Benefits to be added
	 */
	public void addIbenefits_personal(Benefits ibenefits_personal) {
		this.ibenefits_personal.add(ibenefits_personal);
	}

	/**
	 * Removes a Benefits to the ibenefits_personal set.
	 * 
	 * @param ibenefits_personal
	 *            Benefits to be removed
	 */
	public void removeIbenefits_personal(Benefits ibenefits_personal) {
		this.ibenefits_personal.remove(ibenefits_personal);
	}

	/**
	 * @return the ieducation_personal
	 */
	public Set<Education> getIeducation_personal() {
		return ieducation_personal;
	}

	/**
	 * @param ieducation_personal
	 *            the ieducation_personal to set.
	 */
	public void setIeducation_personal(Set<Education> ieducation_personal) {
		this.ieducation_personal = ieducation_personal;
	}

	/**
	 * Adds a Education to the ieducation_personal set.
	 * 
	 * @param ieducation_personal
	 *            Education to be added
	 */
	public void addIeducation_personal(Education ieducation_personal) {
		this.ieducation_personal.add(ieducation_personal);
	}

	/**
	 * Removes a Education to the ieducation_personal set.
	 * 
	 * @param ieducation_personal
	 *            Education to be removed
	 */
	public void removeIeducation_personal(Education ieducation_personal) {
		this.ieducation_personal.remove(ieducation_personal);
	}

	/**
	 * @return the ifunding_detail_personal
	 */
	public Set<Funding_detail> getIfunding_detail_personal() {
		return ifunding_detail_personal;
	}

	/**
	 * @param ifunding_detail_personal
	 *            the ifunding_detail_personal to set.
	 */
	public void setIfunding_detail_personal(Set<Funding_detail> ifunding_detail_personal) {
		this.ifunding_detail_personal = ifunding_detail_personal;
	}

	/**
	 * Adds a Funding_detail to the ifunding_detail_personal set.
	 * 
	 * @param ifunding_detail_personal
	 *            Funding_detail to be added
	 */
	public void addIfunding_detail_personal(Funding_detail ifunding_detail_personal) {
		this.ifunding_detail_personal.add(ifunding_detail_personal);
	}

	/**
	 * Removes a Funding_detail to the ifunding_detail_personal set.
	 * 
	 * @param ifunding_detail_personal
	 *            Funding_detail to be removed
	 */
	public void removeIfunding_detail_personal(Funding_detail ifunding_detail_personal) {
		this.ifunding_detail_personal.remove(ifunding_detail_personal);
	}

	/**
	 * @return the iholiday_personal
	 */
	public Set<Holiday> getIholiday_personal() {
		return iholiday_personal;
	}

	/**
	 * @param iholiday_personal
	 *            the iholiday_personal to set.
	 */
	public void setIholiday_personal(Set<Holiday> iholiday_personal) {
		this.iholiday_personal = iholiday_personal;
	}

	/**
	 * Adds a Holiday to the iholiday_personal set.
	 * 
	 * @param iholiday_personal
	 *            Holiday to be added
	 */
	public void addIholiday_personal(Holiday iholiday_personal) {
		this.iholiday_personal.add(iholiday_personal);
	}

	/**
	 * Removes a Holiday to the iholiday_personal set.
	 * 
	 * @param iholiday_personal
	 *            Holiday to be removed
	 */
	public void removeIholiday_personal(Holiday iholiday_personal) {
		this.iholiday_personal.remove(iholiday_personal);
	}

	/**
	 * @return the iwork_experience_personal
	 */
	public Set<Work_experience> getIwork_experience_personal() {
		return iwork_experience_personal;
	}

	/**
	 * @param iwork_experience_personal
	 *            the iwork_experience_personal to set.
	 */
	public void setIwork_experience_personal(Set<Work_experience> iwork_experience_personal) {
		this.iwork_experience_personal = iwork_experience_personal;
	}

	/**
	 * Adds a Work_experience to the iwork_experience_personal set.
	 * 
	 * @param iwork_experience_personal
	 *            Work_experience to be added
	 */
	public void addIwork_experience_personal(Work_experience iwork_experience_personal) {
		this.iwork_experience_personal.add(iwork_experience_personal);
	}

	/**
	 * Removes a Work_experience to the iwork_experience_personal set.
	 * 
	 * @param iwork_experience_personal
	 *            Work_experience to be removed
	 */
	public void removeIwork_experience_personal(Work_experience iwork_experience_personal) {
		this.iwork_experience_personal.remove(iwork_experience_personal);
	}

	/**
	 * @return the ichild_personal
	 */
	public Set<Child> getIchild_personal() {
		return ichild_personal;
	}

	/**
	 * @param ichild_personal
	 *            the ichild_personal to set.
	 */
	public void setIchild_personal(Set<Child> ichild_personal) {
		this.ichild_personal = ichild_personal;
	}

	/**
	 * Adds a Child to the ichild_personal set.
	 * 
	 * @param ichild_personal
	 *            Child to be added
	 */
	public void addIchild_personal(Child ichild_personal) {
		this.ichild_personal.add(ichild_personal);
	}

	/**
	 * Removes a Child to the ichild_personal set.
	 * 
	 * @param ichild_personal
	 *            Child to be removed
	 */
	public void removeIchild_personal(Child ichild_personal) {
		this.ichild_personal.remove(ichild_personal);
	}

	/**
	 * @return the iprofessional_personal
	 */
	public Set<Professional> getIprofessional_personal() {
		return iprofessional_personal;
	}

	/**
	 * @param iprofessional_personal
	 *            the iprofessional_personal to set.
	 */
	public void setIprofessional_personal(Set<Professional> iprofessional_personal) {
		this.iprofessional_personal = iprofessional_personal;
	}

	/**
	 * Adds a Professional to the iprofessional_personal set.
	 * 
	 * @param iprofessional_personal
	 *            Professional to be added
	 */
	public void addIprofessional_personal(Professional iprofessional_personal) {
		this.iprofessional_personal.add(iprofessional_personal);
	}

	/**
	 * Removes a Professional to the iprofessional_personal set.
	 * 
	 * @param iprofessional_personal
	 *            Professional to be removed
	 */
	public void removeIprofessional_personal(Professional iprofessional_personal) {
		this.iprofessional_personal.remove(iprofessional_personal);
	}

	/**
	 * @return the ipersonal_comments
	 */
	public Set<Personal_comment> getIpersonal_comments() {
		return ipersonal_comments;
	}

	/**
	 * @param ipersonal_comments
	 *            the ipersonal_comments to set.
	 */
	public void setIpersonal_comments(Set<Personal_comment> ipersonal_comments) {
		this.ipersonal_comments = ipersonal_comments;
	}

	/**
	 * Adds a Personal to the ipersonal_comments set.
	 * 
	 * @param personal
	 *            Personal to be added
	 */
	public void addIpersonal_comments(Personal_comment personal_comment) {
		this.ipersonal_comments.add(personal_comment);
	}

	/**
	 * Removes a Personal to the ipersonal_comments set.
	 * 
	 * @param personal
	 *            Personal to be removed
	 */
	public void removeIpersonal_comments(Personal_comment personal_comment) {
		this.ipersonal_comments.remove(personal_comment);
	}

	/**
	 * @return the icompensation_personal
	 */
	public Set<Compensation> getIcompensation_personal() {
		return icompensation_personal;
	}

	/**
	 * @param icompensation_personal
	 *            the icompensation_personal to set.
	 */
	public void setIcompensation_personal(Set<Compensation> icompensation_personal) {
		this.icompensation_personal = icompensation_personal;
	}

	/**
	 * Adds a Compensation to the icompensation_personal set.
	 * 
	 * @param icompensation_personal
	 *            Compensation to be added
	 */
	public void addIcompensation_personal(Compensation icompensation_personal) {
		this.icompensation_personal.add(icompensation_personal);
	}

	/**
	 * Removes a Compensation to the icompensation_personal set.
	 * 
	 * @param icompensation_personal
	 *            Compensation to be removed
	 */
	public void removeIcompensation_personal(Compensation icompensation_personal) {
		this.icompensation_personal.remove(icompensation_personal);
	}

	/**
	 * @return the isupervisor
	 */
	public Set<Research_group> getIsupervisor() {
		return isupervisor;
	}

	/**
	 * @param isupervisor
	 *            the isupervisor to set.
	 */
	public void setIsupervisor(Set<Research_group> isupervisor) {
		this.isupervisor = isupervisor;
	}

	/**
	 * Adds a Research_group to the isupervisor set.
	 * 
	 * @param isupervisor
	 *            Research_group to be added
	 */
	public void addIsupervisor(Research_group isupervisor) {
		this.isupervisor.add(isupervisor);
	}

	/**
	 * Removes a Research_group to the isupervisor set.
	 * 
	 * @param isupervisor
	 *            Research_group to be removed
	 */
	public void removeIsupervisor(Research_group isupervisor) {
		this.isupervisor.remove(isupervisor);
	}

	/**
	 * @return the isupervisor_unit
	 */
	public Set<Unit> getIsupervisor_unit() {
		return isupervisor_unit;
	}

	/**
	 * @param isupervisor_unit
	 *            the isupervisor_unit to set.
	 */
	public void setIsupervisor_unit(Set<Unit> isupervisor_unit) {
		this.isupervisor_unit = isupervisor_unit;
	}

	/**
	 * Adds a Unit to the isupervisor_unit set.
	 * 
	 * @param isupervisor_unit
	 *            Unit to be added
	 */
	public void addIsupervisor_unit(Unit isupervisor_unit) {
		this.isupervisor_unit.add(isupervisor_unit);
	}

	/**
	 * Removes a Unit to the isupervisor_unit set.
	 * 
	 * @param isupervisor_unit
	 *            Unit to be removed
	 */
	public void removeIsupervisor_unit(Unit isupervisor_unit) {
		this.isupervisor_unit.remove(isupervisor_unit);
	}

	@Override
	public String toString() {
		if (getCode() == null || getCode().equals(""))
			return "";

		return "[" + getPersonalcode() + "] " + (getName() != null ? getName() : "") + " " + (getSurname1() != null ? getSurname1() : "");
	}

	public PersonalPhoto getPhoto() {
		return photo;
	}

	public void setPhoto(PersonalPhoto photo) {
		this.photo = photo;
	}

	public String getValidationCode() {
		return validationCode;
	}

	public void setValidationCode(String validationCode) {
		this.validationCode = validationCode;
	}

	public Set<Irbholiday> getIirbholiday() {
		return iirbholiday;
	}

	public void setIirbholiday(Set<Irbholiday> iirbholiday) {
		this.iirbholiday = iirbholiday;
	}

	/**
	 * Adds a Unit to the isupervisor_unit set.
	 * 
	 * @param isupervisor_unit
	 *            Unit to be added
	 */
	public void addIirbholiday(Irbholiday iirbholiday) {
		this.iirbholiday.add(iirbholiday);
	}

	/**
	 * Removes a Unit to the isupervisor_unit set.
	 * 
	 * @param isupervisor_unit
	 *            Unit to be removed
	 */
	public void removeIirbholiday(Irbholiday iirbholiday) {
		this.iirbholiday.remove(iirbholiday);
	}

	public Set<Irbholidayinfo> getIirbholidayinfo() {
		return iirbholidayinfo;
	}

	public void setIirbholidayinfo(Set<Irbholidayinfo> iirbholidayinfo) {
		this.iirbholidayinfo = iirbholidayinfo;
	}

	/**
	 * Adds a Unit to the isupervisor_unit set.
	 * 
	 * @param isupervisor_unit
	 *            Unit to be added
	 */
	public void addIirbholidayinfo(Irbholidayinfo iirbholidayinfo) {
		this.iirbholidayinfo.add(iirbholidayinfo);
	}

	/**
	 * Removes a Unit to the isupervisor_unit set.
	 * 
	 * @param isupervisor_unit
	 *            Unit to be removed
	 */
	public void removeIirbholidayinfo(Irbholidayinfo iirbholidayinfo) {
		this.iirbholidayinfo.remove(iirbholidayinfo);
	}

	public String getPersonal_email() {
		return personal_email;
	}

	public void setPersonal_email(String personal_email) {
		this.personal_email = personal_email;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSecond_affiliation() {
		return second_affiliation;
	}

	public void setSecond_affiliation(String second_affiliation) {
		this.second_affiliation = second_affiliation;
	}

}