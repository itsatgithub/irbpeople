package presentation.formbeans.objects;

import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-ID-form-bean of personal. ID-formBeans contain all the attributes of personal but it does not contain the associations.
 * 
 * @author Automatika - JustInMind SL
 */
public class Personal_IDForm extends ValidatorFormAndAction {

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
	
	private String bic = null;
	
	private String swift = null;

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

	private String second_affiliation = null;

	private Personalstate_IDForm state = null;

	public Personal_IDForm getPersonal() {
		return this;
	}

	public String getPersonalcode() {
		return personalcode;
	}

	public void setPersonalcode(String personalcode) {
		this.personalcode = personalcode;
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
	
	public String getBic() {
		return bic;
	}
	
	public void setBic(String bic) {
		this.bic = bic;
	}

	public String getSwift() {
		return swift;
	}
	
	public void setSwift(String swift) {
		this.swift = swift;
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

	public Personalstate_IDForm getState() {
		if (state == null)
			state = new Personalstate_IDForm();
		return state;
	}

	public void setState(Personalstate_IDForm state) {
		this.state = state;
	}

	public void setSecond_affiliation(String second_affiliation) {
		this.second_affiliation = second_affiliation;
	}

	public String getSecond_affiliation() {
		return second_affiliation;
	}

}