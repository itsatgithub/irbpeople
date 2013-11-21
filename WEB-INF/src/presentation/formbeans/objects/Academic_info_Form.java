package presentation.formbeans.objects;

import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-form-bean of grant_concession
 * 
 * @author Automatika - JustInMind SL
 */
public class Academic_info_Form extends ValidatorFormAndAction {

	String version;
	String academic_infocode;

	// definition of the specific attributes
	private String start_date;
	private String end_date;
	private String lab_rotation_date;
	private String thesis_defense_date;
 
	private String current;
	private String studies_name;
	private String thesis_director;
	private String thesis_codirector;
	private String university_enrolled;
	private String tac0;

	private Research_group_IDForm lab_rotation_lab;
	private Research_group_IDForm lab_rotation_lab2;


	private Personal_IDForm academic_info_personal = null;

	private Type_of_study_IDForm type_of_study = null;

	public Academic_info_Form getAcademic_info() {
		return this;
	}

	@Override
	public String toString() {

		if (getAcademic_infocode() == null || getAcademic_infocode().equals(""))
			return "";

		String result = getStart_date() + " - " + getEnd_date() + "";
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

	public String getAcademic_infocode() {
		return academic_infocode;
	}

	public void setAcademic_infocode(String academic_infocode) {
		this.academic_infocode = academic_infocode;
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

	public String getLab_rotation_date() {
		return lab_rotation_date;
	}

	public void setLab_rotation_date(String lab_rotation_date) {
		this.lab_rotation_date = lab_rotation_date;
	}

	public String getThesis_defense_date() {
		return thesis_defense_date;
	}

	public void setThesis_defense_date(String thesis_defense_date) {
		this.thesis_defense_date = thesis_defense_date;
	}

	public String getCurrent() {
		return current;
	}

	public void setCurrent(String current) {
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

	public Research_group_IDForm getLab_rotation_lab() {
		if (lab_rotation_lab == null)
			lab_rotation_lab = new Research_group_IDForm();
		return lab_rotation_lab;
	}

	public void setLab_rotation_lab(Research_group_IDForm lab_rotation_lab) {
		this.lab_rotation_lab = lab_rotation_lab;
	}

	public Research_group_IDForm getLab_rotation_lab2() {
		if (lab_rotation_lab2 == null)
			lab_rotation_lab2 = new Research_group_IDForm();
		return lab_rotation_lab2;
	}

	public void setLab_rotation_lab2(Research_group_IDForm lab_rotation_lab2) {
		this.lab_rotation_lab2 = lab_rotation_lab2;
	}

	public String getTac0() {
		return tac0;
	}

	public void setTac0(String tac0) {
		this.tac0 = tac0;
	}

	public Personal_IDForm getAcademic_info_personal() {
		if (academic_info_personal == null)
			academic_info_personal = new Personal_IDForm();
		return academic_info_personal;
	}

	public void setAcademic_info_personal(Personal_IDForm academic_info_personal) {
		this.academic_info_personal = academic_info_personal;
	}

	public Type_of_study_IDForm getType_of_study() {
		if (type_of_study == null)
			type_of_study = new Type_of_study_IDForm();
		return type_of_study;
	}

	public void setType_of_study(Type_of_study_IDForm type_of_study) {
		this.type_of_study = type_of_study;
	}

}