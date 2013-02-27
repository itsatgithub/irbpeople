package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-ID-form-bean of type_of_education. ID-formBeans contain all the attributes of type_of_education but it does not contain the associations.
 * 
 * @author Automatika - JustInMind SL
 */
public class Type_of_education_IDForm extends ValidatorFormAndAction{


String type_of_educationcode;


	private String description=null;






public Type_of_education_IDForm getType_of_education() {
	return this;
}

public String getType_of_educationcode() {
	return type_of_educationcode;
}

public void setType_of_educationcode(String type_of_educationcode) {
	this.type_of_educationcode = type_of_educationcode;
}

	
	@Override
	public String toString(){
		
		if(getType_of_educationcode()==null || getType_of_educationcode().equals("")) return "";
	
		
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