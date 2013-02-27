package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-ID-form-bean of work_experience. ID-formBeans contain all the attributes of work_experience but it does not contain the associations.
 * 
 * @author Automatika - JustInMind SL
 */
public class Work_experience_IDForm extends ValidatorFormAndAction{


String work_experiencecode;


	private String start_date=null;

	private String end_date=null;

	private String name_of_institution_or_company=null;






public Work_experience_IDForm getWork_experience() {
	return this;
}

public String getWork_experiencecode() {
	return work_experiencecode;
}

public void setWork_experiencecode(String work_experiencecode) {
	this.work_experiencecode = work_experiencecode;
}

	
	@Override
	public String toString(){
		
		if(getWork_experiencecode()==null || getWork_experiencecode().equals("")) return "";
	
		
			String result=getStart_date()+" - "+getEnd_date()+" - "+getName_of_institution_or_company()+"";
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

	
	public String getName_of_institution_or_company(){
		return name_of_institution_or_company;
	}
	
	public void setName_of_institution_or_company(String name_of_institution_or_company){
		this.name_of_institution_or_company=name_of_institution_or_company;
	}





}