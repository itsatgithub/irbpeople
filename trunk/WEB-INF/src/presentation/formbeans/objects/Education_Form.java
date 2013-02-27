package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-form-bean of education
 * 
 * @author Automatika - JustInMind SL
 */
public class Education_Form extends ValidatorFormAndAction{

	String version;
	String educationcode;


	private String start_date=null;

	private String end_date=null;

	private String graduation_date=null;

	private String title=null;

	private String speciality=null;

	private String center=null;




	private Personal_IDForm education_personal=null;

	private Type_of_education_IDForm type=null;

	private Country_IDForm education_country=null;


	
	public Education_Form getEducation() {
		return this;
	}
	
	public String getEducationcode() {
		return educationcode;
	}
	
	public void setEducationcode(String educationcode) {
		this.educationcode = educationcode;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}

	
	@Override
	public String toString(){
		
		if(getEducationcode()==null || getEducationcode().equals("")) return "";
	
		
			String result=getStart_date()+" - "+getEnd_date()+" - "+getGraduation_date()+"";
			return (result!=null)?result:"";
		
	}
	
	public String get_descripcion(){
    	return this.toString();
    }



	
	public String getStart_date(){
		return start_date;
	}
	
	public void setStart_date(String start_date){
		this.start_date=start_date;
	}

	
	public String getEnd_date(){
		return end_date;
	}
	
	public void setEnd_date(String end_date){
		this.end_date=end_date;
	}

	
	public String getGraduation_date(){
		return graduation_date;
	}
	
	public void setGraduation_date(String graduation_date){
		this.graduation_date=graduation_date;
	}

	
	public String getTitle(){
		return title;
	}
	
	public void setTitle(String title){
		this.title=title;
	}

	
	public String getSpeciality(){
		return speciality;
	}
	
	public void setSpeciality(String speciality){
		this.speciality=speciality;
	}

	
	public String getCenter(){
		return center;
	}
	
	public void setCenter(String center){
		this.center=center;
	}




	
	public Personal_IDForm getEducation_personal(){
		if(education_personal == null)
			education_personal = new Personal_IDForm();
		return education_personal;
	}
	
	public void setEducation_personal(Personal_IDForm education_personal){
		this.education_personal = education_personal;
	}
	

	
	public Type_of_education_IDForm getType(){
		if(type == null)
			type = new Type_of_education_IDForm();
		return type;
	}
	
	public void setType(Type_of_education_IDForm type){
		this.type = type;
	}
	

	
	public Country_IDForm getEducation_country(){
		if(education_country == null)
			education_country = new Country_IDForm();
		return education_country;
	}
	
	public void setEducation_country(Country_IDForm education_country){
		this.education_country = education_country;
	}
	


}