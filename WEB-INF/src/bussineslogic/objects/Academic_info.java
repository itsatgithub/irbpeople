package bussineslogic.objects;

import java.util.Date;

import utils.Persistent;

/**
 * This class contains business object 'grant_concession'.
 * 
 * @author Automatika - JustInMind SL
 * 
 */
public class Academic_info implements Persistent {

	// Code (primary key) of this grant_concession
	private String academic_infocode;

	// Version of the current instance. This attribute is used by hibernate to
	// control concurrent modifications.
	private int version;

	// Indicates whereas this academic_info is deleted
	private boolean deleted = false;

	// definition of the specific attributes
	private Date start_date;
	private Date end_date;
	private Date lab_rotation_date;
	private Date thesis_defense_date;
	private Type_of_study type_of_study;
	private Personal academic_info_personal;

	private boolean current = false;
	private String studies_name;
	private String thesis_director;
	private String thesis_codirector;
	private String university_enrolled;

	private Research_group lab_rotation_lab;
	private Research_group lab_rotation_lab2;

	private boolean tac0;

	/**
	 * Default Constructor which creates an empty academic_info
	 */
	public Academic_info() {
	}

	public void setCode(String academic_infocode) {
		setAcademic_infocode(academic_infocode);
	}

	public String getCode() {
		return getAcademic_infocode();
	}

	public String getAcademic_infocode() {
		return academic_infocode;
	}

	public void setAcademic_infocode(String academic_infocode) {
		this.academic_infocode = academic_infocode;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public Date getLab_rotation_date() {
		return lab_rotation_date;
	}

	public void setLab_rotation_date(Date lab_rotation_date) {
		this.lab_rotation_date = lab_rotation_date;
	}

	public Date getThesis_defense_date() {
		return thesis_defense_date;
	}

	public void setThesis_defense_date(Date thesis_defense_date) {
		this.thesis_defense_date = thesis_defense_date;
	}

	public Type_of_study getType_of_study() {
		return type_of_study;
	}

	public void setType_of_study(Type_of_study type_of_study) {
		this.type_of_study = type_of_study;
	}

	public Personal getAcademic_info_personal() {
		return academic_info_personal;
	}

	public void setAcademic_info_personal(Personal academic_info_personal) {
		this.academic_info_personal = academic_info_personal;
	}

	public boolean isCurrent() {
		return current;
	}

	public void setCurrent(boolean current) {
		this.current = current;
	}

	public String getStudies_name() {
		return studies_name;
	}

	public void setStudies_name(String studies_name) {
		this.studies_name = studies_name;
	}

	public String getThesis_director() {
		return thesis_director;
	}

	public void setThesis_director(String thesis_director) {
		this.thesis_director = thesis_director;
	}

	public String getThesis_codirector() {
		return thesis_codirector;
	}

	public void setThesis_codirector(String thesis_codirector) {
		this.thesis_codirector = thesis_codirector;
	}

	public String getUniversity_enrolled() {
		return university_enrolled;
	}

	public void setUniversity_enrolled(String university_enrolled) {
		this.university_enrolled = university_enrolled;
	}

	public Research_group getLab_rotation_lab() {
		return lab_rotation_lab;
	}

	public void setLab_rotation_lab(Research_group lab_rotation_lab) {
		this.lab_rotation_lab = lab_rotation_lab;
	}

	public Research_group getLab_rotation_lab2() {
		return lab_rotation_lab2;
	}

	public void setLab_rotation_lab2(Research_group lab_rotation_lab2) {
		this.lab_rotation_lab2 = lab_rotation_lab2;
	}

	public boolean isTac0() {
		return tac0;
	}

	public void setTac0(boolean tac0) {
		this.tac0 = tac0;
	}

	@Override
	public String toString() {
		if (getCode() == null || getCode().equals(""))
			return "";

		return getCode();
	}

}