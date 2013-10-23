package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-ID-form-bean of type_of_study. ID-formBeans contain all the attributes of type_of_study but it does not contain the associations.
 * 
 * @author Automatika - JustInMind SL
 */
public class Type_of_study_IDForm extends ValidatorFormAndAction{


String type_of_studycode;


	private String description=null;






public Type_of_study_IDForm getType_of_study() {
	return this;
}

public String getType_of_studycode() {
	return type_of_studycode;
}

public void setType_of_studycode(String type_of_studycode) {
	this.type_of_studycode = type_of_studycode;
}

	
	@Override
	public String toString(){
		
		if(getType_of_studycode()==null || getType_of_studycode().equals("")) return "";
	
		
			String result=getDescription()+"";
			return (result!=null)?result:"";
		
	}

public String get_descripcion(){
	return this.toString();
}



	
	public String getDescription(){
		return description;
	}
	
	public void setDescription(String description){
		this.description=description;
	}





}