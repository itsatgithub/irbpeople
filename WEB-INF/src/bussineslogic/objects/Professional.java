package bussineslogic.objects;

import java.util.Date;

import utils.Persistent;

/**
 * This class contains business object 'professional'.
 * 
 * @author Automatika - JustInMind SL
 * 
 */
public class Professional implements Persistent {

	// Code (primary key) of this professional
	private String professionalcode;
	// Version of the current instance. This attribute is used by hibernate to
	// control concurrent modifications.
	private int version;
	// Indicates whereas this professional is deleted
	private boolean deleted = false;

	private boolean current = false;
	// definition of the specific attributes

	Date start_date;
	Date end_date;
	String email;
	String phone;
	String mobile_long;
	String mobile_short;
	String lab_phone_number;
	String fax;
	Personal professional_personal;
	Research_group research_group;
	Research_group research_group_2;
	Research_group research_group_3;
	Research_group research_group_4;
	Type_of_contract type_of_contract;
	Location location;
	Position position;
	Unit professional_unit;
	Unit professional_unit_2;
	Unit professional_unit_3;
	Unit professional_unit_4;
	Payroll_institution payroll_institution;
	Payroll_institution payroll_institution_2;
	String building;

	/**
	 * Default Constructor which creates an empty professional
	 */
	public Professional() {
	}

	/**
	 * Returns the code (primary key) of this professional.
	 * 
	 * @return a String with the code
	 */
	public String getProfessionalcode() {
		return professionalcode;
	}

	/**
	 * Set the code (primary key) of this professional
	 * 
	 * @param professionalcode
	 *            the String with the code
	 */
	public void setProfessionalcode(String professionalcode) {
		this.professionalcode = professionalcode;
	}

	/**
	 * Returns the code (primary key) of this professional. Convenience method
	 * which calls
	 * {@link bussineslogic.objects.Professional#getProfessionalcode()
	 * getProfessionalcode()}
	 * 
	 * @return a String with the code
	 */
	public String getCode() {
		return getProfessionalcode();
	}

	/**
	 * Set the code (primary key) of this professional. Convenience method which
	 * calls
	 * {@link bussineslogic.objects.Professional#setProfessionalcode(String)
	 * getProfessionalcode(String)}
	 * 
	 * @param professionalcode
	 *            the String with the code
	 */
	public void setCode(String professionalcode) {
		setProfessionalcode(professionalcode);
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
	 * Tests if this professional has been deleted
	 * 
	 * @return true if this professional has been deleted
	 */
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * Sets the delete state of this professional
	 * 
	 * @param deleted
	 *            true if the object is deleted.
	 */
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	/**
	 * @return the start_date
	 */
	public Date getStart_date() {
		return start_date;
	}

	/**
	 * @param start_date
	 *            the start_date to set.
	 */
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	/**
	 * @return the end_date
	 */
	public Date getEnd_date() {
		return end_date;
	}

	/**
	 * @param end_date
	 *            the end_date to set.
	 */
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set.
	 */
	public void setEmail(String email) {
		this.email = email;
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
	 * @return the mobile
	 */
	public String getMobile_long() {
		return mobile_long;
	}

	/**
	 * @param mobile
	 *            the mobile to set.
	 */
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

	/**
	 * @return the professional_personal
	 */
	public Personal getProfessional_personal() {
		return professional_personal;
	}

	/**
	 * @param professional_personal
	 *            the professional_personal to set.
	 */
	public void setProfessional_personal(Personal professional_personal) {
		this.professional_personal = professional_personal;
	}

	/**
	 * @return the research_group
	 */
	public Research_group getResearch_group() {
		return research_group;
	}

	/**
	 * @param research_group
	 *            the research_group to set.
	 */
	public void setResearch_group(Research_group research_group) {
		this.research_group = research_group;
	}

	/**
	 * @return the type_of_contract
	 */
	public Type_of_contract getType_of_contract() {
		return type_of_contract;
	}

	/**
	 * @param type_of_contract
	 *            the type_of_contract to set.
	 */
	public void setType_of_contract(Type_of_contract type_of_contract) {
		this.type_of_contract = type_of_contract;
	}

	/**
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set.
	 */
	public void setLocation(Location location) {
		this.location = location;
	}

	/**
	 * @return the position
	 */
	public Position getPosition() {
		return position;
	}

	/**
	 * @param position
	 *            the position to set.
	 */
	public void setPosition(Position position) {
		this.position = position;
	}

	/**
	 * @return the professional_unit
	 */
	public Unit getProfessional_unit() {
		return professional_unit;
	}

	/**
	 * @param professional_unit
	 *            the professional_unit to set.
	 */
	public void setProfessional_unit(Unit professional_unit) {
		this.professional_unit = professional_unit;
	}

	/**
	 * @return the payroll_institution
	 */
	public Payroll_institution getPayroll_institution() {
		return payroll_institution;
	}

	/**
	 * @param payroll_institution
	 *            the payroll_institution to set.
	 */
	public void setPayroll_institution(Payroll_institution payroll_institution) {
		this.payroll_institution = payroll_institution;
	}

	@Override
	public String toString() {
		if (getCode() == null || getCode().equals(""))
			return "";

		return getCode();
	}

	public Payroll_institution getPayroll_institution_2() {
		return payroll_institution_2;
	}

	public void setPayroll_institution_2(
			Payroll_institution payroll_institution_2) {
		this.payroll_institution_2 = payroll_institution_2;
	}

	public boolean isCurrent() {
		return current;
	}

	public void setCurrent(boolean current) {
		this.current = current;
	}

	public void setBuilding(String buildingcode) {
		this.building = buildingcode;

	}

	public String getBuilding() {
		return this.building;
	}

	public Research_group getResearch_group_2() {
		return research_group_2;
	}

	public void setResearch_group_2(Research_group research_group_2) {
		this.research_group_2 = research_group_2;
	}

	public Research_group getResearch_group_3() {
		return research_group_3;
	}

	public void setResearch_group_3(Research_group research_group_3) {
		this.research_group_3 = research_group_3;
	}

	public Research_group getResearch_group_4() {
		return research_group_4;
	}

	public void setResearch_group_4(Research_group research_group_4) {
		this.research_group_4 = research_group_4;
	}

	public Unit getProfessional_unit_2() {
		return professional_unit_2;
	}

	public void setProfessional_unit_2(Unit professional_unit_2) {
		this.professional_unit_2 = professional_unit_2;
	}

	public Unit getProfessional_unit_3() {
		return professional_unit_3;
	}

	public void setProfessional_unit_3(Unit professional_unit_3) {
		this.professional_unit_3 = professional_unit_3;
	}

	public Unit getProfessional_unit_4() {
		return professional_unit_4;
	}

	public void setProfessional_unit_4(Unit professional_unit_4) {
		this.professional_unit_4 = professional_unit_4;
	}



}