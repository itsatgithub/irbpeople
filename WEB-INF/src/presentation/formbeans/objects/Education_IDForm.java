package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-ID-form-bean of education. ID-formBeans contain all the attributes of education but it does not contain the associations.
 * 
 * @author Automatika - JustInMind SL
 */
public class Education_IDForm extends ValidatorFormAndAction{


String educationcode;


	private String start_date=null;

	private String end_date=null;

	private String graduation_date=null;

	private String title=null;

	private String speciality=null;

	private String center=null;






public Education_IDForm getEducation() {
	return this;
}

public String getEducationcode() {
	return educationcode;
}

public void setEducationcode(String educationcode) {
	this.educationcode = educationcode;
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





}