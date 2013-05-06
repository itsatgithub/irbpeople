package presentation.formbeans.objects;

import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-form-bean of professional
 * 
 * @author Automatika - JustInMind SL
 */
public class Professional_Form extends ValidatorFormAndAction {

	String version;
	String professionalcode;

	String current;

	private String start_date = null;

	private String end_date = null;

	private String email = null;

	private String phone = null;

	private String mobile_long = null;

	private String mobile_short = null;

	private String lab_phone_number = null;

	private String fax = null;

	private Personal_IDForm professional_personal = null;

	private Research_group_IDForm research_group = null;

	private Research_group_IDForm research_group_2 = null;

	private Research_group_IDForm research_group_3 = null;

	private Research_group_IDForm research_group_4 = null;

	private Type_of_contract_IDForm type_of_contract = null;

	private Location_IDForm location = null;

	private Position_IDForm position = null;

	private Unit_IDForm professional_unit = null;

	private Unit_IDForm professional_unit_2 = null;

	private Unit_IDForm professional_unit_3 = null;

	private Unit_IDForm professional_unit_4 = null;

	private Payroll_institution_IDForm payroll_institution = null;

	private Payroll_institution_IDForm payroll_institution_2 = null;

	public Professional_Form getProfessional() {
		return this;
	}

	public String getProfessionalcode() {
		return professionalcode;
	}

	public void setProfessionalcode(String professionalcode) {
		this.professionalcode = professionalcode;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String toString() {

		if (getProfessionalcode() == null || getProfessionalcode().equals(""))
			return "";

		String result = getStart_date() + " - " + getEnd_date() + "";
		return (result != null) ? result : "";

	}

	public String get_descripcion() {
		return this.toString();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile_long() {
		return mobile_long;
	}

	public void setMobile_long(String mobile_long) {
		this.mobile_long = mobile_long;
	}

	public String getMobile_short() {
		return mobile_short;
	}

	public void setMobile_short(String mobile_short) {
		this.mobile_short = mobile_short;
	}

	public String getLab_phone_number() {
		return lab_phone_number;
	}

	public void setLab_phone_number(String lab_phone_number) {
		this.lab_phone_number = lab_phone_number;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public Personal_IDForm getProfessional_personal() {
		if (professional_personal == null)
			professional_personal = new Personal_IDForm();
		return professional_personal;
	}

	public void setProfessional_personal(Personal_IDForm professional_personal) {
		this.professional_personal = professional_personal;
	}

	public Research_group_IDForm getResearch_group() {
		if (research_group == null)
			research_group = new Research_group_IDForm();
		return research_group;
	}

	public void setResearch_group(Research_group_IDForm research_group) {
		this.research_group = research_group;
	}

	public Research_group_IDForm getResearch_group_2() {
		if (research_group_2 == null)
			research_group_2 = new Research_group_IDForm();
		return research_group_2;
	}

	public void setResearch_group_2(Research_group_IDForm research_group) {
		this.research_group_2 = research_group;
	}

	public Research_group_IDForm getResearch_group_3() {
		if (research_group_3 == null)
			research_group_3 = new Research_group_IDForm();
		return research_group_3;
	}

	public void setResearch_group_3(Research_group_IDForm research_group) {
		this.research_group_3 = research_group;
	}

	public Research_group_IDForm getResearch_group_4() {
		if (research_group_4 == null)
			research_group_4 = new Research_group_IDForm();
		return research_group_4;
	}

	public void setResearch_group_4(Research_group_IDForm research_group) {
		this.research_group_4 = research_group;
	}

	public Type_of_contract_IDForm getType_of_contract() {
		if (type_of_contract == null)
			type_of_contract = new Type_of_contract_IDForm();
		return type_of_contract;
	}

	public void setType_of_contract(Type_of_contract_IDForm type_of_contract) {
		this.type_of_contract = type_of_contract;
	}

	public Location_IDForm getLocation() {
		if (location == null)
			location = new Location_IDForm();
		return location;
	}

	public void setLocation(Location_IDForm location) {
		this.location = location;
	}

	public Position_IDForm getPosition() {
		if (position == null)
			position = new Position_IDForm();
		return position;
	}

	public void setPosition(Position_IDForm position) {
		this.position = position;
	}

	public Unit_IDForm getProfessional_unit() {
		if (professional_unit == null)
			professional_unit = new Unit_IDForm();
		return professional_unit;
	}

	public void setProfessional_unit(Unit_IDForm professional_unit) {
		this.professional_unit = professional_unit;
	}

	public Unit_IDForm getProfessional_unit_2() {
		if (professional_unit_2 == null)
			professional_unit_2 = new Unit_IDForm();
		return professional_unit_2;
	}

	public void setProfessional_unit_2(Unit_IDForm professional_unit) {
		this.professional_unit_2 = professional_unit;
	}

	public Unit_IDForm getProfessional_unit_3() {
		if (professional_unit_3 == null)
			professional_unit_3 = new Unit_IDForm();
		return professional_unit_3;
	}

	public void setProfessional_unit_3(Unit_IDForm professional_unit) {
		this.professional_unit_3 = professional_unit;
	}

	public Unit_IDForm getProfessional_unit_4() {
		if (professional_unit_4 == null)
			professional_unit_4 = new Unit_IDForm();
		return professional_unit_4;
	}

	public void setProfessional_unit_4(Unit_IDForm professional_unit) {
		this.professional_unit_4 = professional_unit;
	}

	public Payroll_institution_IDForm getPayroll_institution() {
		if (payroll_institution == null)
			payroll_institution = new Payroll_institution_IDForm();
		return payroll_institution;
	}

	public void setPayroll_institution(
			Payroll_institution_IDForm payroll_institution) {
		this.payroll_institution = payroll_institution;
	}

	public Payroll_institution_IDForm getPayroll_institution_2() {
		if (payroll_institution_2 == null)
			payroll_institution_2 = new Payroll_institution_IDForm();
		return payroll_institution_2;
	}

	public void setPayroll_institution_2(
			Payroll_institution_IDForm payroll_institution_2) {
		this.payroll_institution_2 = payroll_institution_2;
	}

	public String getCurrent() {
		return current;
	}

	public void setCurrent(String current) {
		this.current = current;
	}

}