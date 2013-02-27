package presentation.formbeans.objects;

import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-form-bean of personal
 * 
 * @author Automatika - JustInMind SL
 */
public class Personal_Form extends ValidatorFormAndAction {

	String version;
	String personalcode;

	private String name = null;

	private String surname1 = null;

	private String surname2 = null;

	private String dni = null;

	private String birth_date = null;

	private String birth_city = null;

	private String street = null;

	private String city = null;

	private String zip_code = null;

	private String phone = null;

	private String phone2 = null;

	private String ice_phone = null;

	private String ss_number = null;

	private String bank_account = null;

	private String research_project = null;

	private String sponsoring_agency = null;

	private String holding_institution = null;

	private String principal_investigator = null;

	private String end_of_contract_reason = null;

	private String end_of_contract_address = null;

	private String end_of_contract_city = null;

	private String end_of_contract_zip_code = null;

	private String end_of_contract_phone = null;

	private String end_of_contract_email = null;

	private String personal_email = null;

	private LanguageForm language = null;

	private String username = null;

	private String second_affiliation = null;

	private Country_IDForm birth_country = null;

	private Nationality_IDForm nationality = null;

	private Nationality_IDForm nationality_2 = null;

	private Country_IDForm country = null;

	private Payment_IDForm payments = null;

	private Country_IDForm end_of_contract_country = null;

	private Gender_IDForm gender = null;

	private Marital_status_IDForm marital_status = null;

	private Bank_IDForm bank = null;

	private Working_hours_IDForm working_hours = null;

	private Category_IDForm category = null;

	private Personalstate_IDForm state = null;

	private String validationCode = null;

	private String mostAdvancedEducation = null;

	public String getValidationCode() {
		return validationCode;
	}

	public void setValidationCode(String validationCode) {
		this.validationCode = validationCode;
	}

	public Personal_Form getPersonal() {
		return this;
	}

	public String getPersonalcode() {
		return personalcode;
	}

	public void setPersonalcode(String personalcode) {
		this.personalcode = personalcode;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String toString() {

		if (getPersonalcode() == null || getPersonalcode().equals(""))
			return "";

		String result = getName() + " " + getSurname1() + " " + getSurname2();
		return (result != null) ? result : "";

	}

	public String get_descripcion() {
		return this.toString();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname1() {
		return surname1;
	}

	public void setSurname1(String surname1) {
		this.surname1 = surname1 != null ? surname1.toUpperCase() : surname1;
	}

	public String getSurname2() {
		return surname2;
	}

	public void setSurname2(String surname2) {
		this.surname2 = surname2 != null ? surname2.toUpperCase() : surname2;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getBirth_date() {
		return birth_date;
	}

	public void setBirth_date(String birth_date) {
		this.birth_date = birth_date;
	}

	public String getBirth_city() {
		return birth_city;
	}

	public void setBirth_city(String birth_city) {
		this.birth_city = birth_city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip_code() {
		return zip_code;
	}

	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getIce_phone() {
		return ice_phone;
	}

	public void setIce_phone(String ice_phone) {
		this.ice_phone = ice_phone;
	}

	public String getSs_number() {
		return ss_number;
	}

	public void setSs_number(String ss_number) {
		this.ss_number = ss_number;
	}

	public String getBank_account() {
		return bank_account;
	}

	public void setBank_account(String bank_account) {
		this.bank_account = bank_account;
	}

	public String getResearch_project() {
		return research_project;
	}

	public void setResearch_project(String research_project) {
		this.research_project = research_project;
	}

	public String getSponsoring_agency() {
		return sponsoring_agency;
	}

	public void setSponsoring_agency(String sponsoring_agency) {
		this.sponsoring_agency = sponsoring_agency;
	}

	public String getHolding_institution() {
		return holding_institution;
	}

	public void setHolding_institution(String holding_institution) {
		this.holding_institution = holding_institution;
	}

	public String getPrincipal_investigator() {
		return principal_investigator;
	}

	public void setPrincipal_investigator(String principal_investigator) {
		this.principal_investigator = principal_investigator;
	}

	public String getEnd_of_contract_reason() {
		return end_of_contract_reason;
	}

	public void setEnd_of_contract_reason(String end_of_contract_reason) {
		this.end_of_contract_reason = end_of_contract_reason;
	}

	public String getEnd_of_contract_address() {
		return end_of_contract_address;
	}

	public void setEnd_of_contract_address(String end_of_contract_address) {
		this.end_of_contract_address = end_of_contract_address;
	}

	public String getEnd_of_contract_city() {
		return end_of_contract_city;
	}

	public void setEnd_of_contract_city(String end_of_contract_city) {
		this.end_of_contract_city = end_of_contract_city;
	}

	public String getEnd_of_contract_zip_code() {
		return end_of_contract_zip_code;
	}

	public void setEnd_of_contract_zip_code(String end_of_contract_zip_code) {
		this.end_of_contract_zip_code = end_of_contract_zip_code;
	}

	public String getEnd_of_contract_phone() {
		return end_of_contract_phone;
	}

	public void setEnd_of_contract_phone(String end_of_contract_phone) {
		this.end_of_contract_phone = end_of_contract_phone;
	}

	public String getEnd_of_contract_email() {
		return end_of_contract_email;
	}

	public void setEnd_of_contract_email(String end_of_contract_email) {
		this.end_of_contract_email = end_of_contract_email;
	}

	public String getUsercode() {
		return getPersonalcode();
	}

	public Country_IDForm getBirth_country() {
		if (birth_country == null)
			birth_country = new Country_IDForm();
		return birth_country;
	}

	public void setBirth_country(Country_IDForm birth_country) {
		this.birth_country = birth_country;
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

	public Country_IDForm getCountry() {
		if (country == null)
			country = new Country_IDForm();
		return country;
	}

	public void setCountry(Country_IDForm country) {
		this.country = country;
	}

	public Payment_IDForm getPayments() {
		if (payments == null)
			payments = new Payment_IDForm();
		return payments;
	}

	public void setPayments(Payment_IDForm payments) {
		this.payments = payments;
	}

	public Country_IDForm getEnd_of_contract_country() {
		if (end_of_contract_country == null)
			end_of_contract_country = new Country_IDForm();
		return end_of_contract_country;
	}

	public void setEnd_of_contract_country(Country_IDForm end_of_contract_country) {
		this.end_of_contract_country = end_of_contract_country;
	}

	public Gender_IDForm getGender() {
		if (gender == null)
			gender = new Gender_IDForm();
		return gender;
	}

	public void setGender(Gender_IDForm gender) {
		this.gender = gender;
	}

	public Marital_status_IDForm getMarital_status() {
		if (marital_status == null)
			marital_status = new Marital_status_IDForm();
		return marital_status;
	}

	public void setMarital_status(Marital_status_IDForm marital_status) {
		this.marital_status = marital_status;
	}

	public Bank_IDForm getBank() {
		if (bank == null)
			bank = new Bank_IDForm();
		return bank;
	}

	public void setBank(Bank_IDForm bank) {
		this.bank = bank;
	}

	public Working_hours_IDForm getWorking_hours() {
		if (working_hours == null)
			working_hours = new Working_hours_IDForm();
		return working_hours;
	}

	public void setWorking_hours(Working_hours_IDForm working_hours) {
		this.working_hours = working_hours;
	}

	public Category_IDForm getCategory() {
		if (category == null)
			category = new Category_IDForm();
		return category;
	}

	public void setCategory(Category_IDForm category) {
		this.category = category;
	}

	public Personalstate_IDForm getState() {
		if (state == null)
			state = new Personalstate_IDForm();
		return state;
	}

	public void setState(Personalstate_IDForm state) {
		this.state = state;
	}

	public String getPersonal_email() {
		return personal_email;
	}

	public void setPersonal_email(String personal_email) {
		this.personal_email = personal_email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public LanguageForm getLanguage() {
		if (language == null)
			language = new LanguageForm();
		return language;
	}

	public void setLanguage(LanguageForm language) {
		this.language = language;
	}

	public void setSecond_affiliation(String second_affiliation) {
		this.second_affiliation = second_affiliation;
	}

	public String getSecond_affiliation() {
		return second_affiliation;
	}

	public String getMostAdvancedEducation() {
		return mostAdvancedEducation;
	}

	public void setMostAdvancedEducation(String mostAdvancedEducation) {
		this.mostAdvancedEducation = mostAdvancedEducation;
	}

}