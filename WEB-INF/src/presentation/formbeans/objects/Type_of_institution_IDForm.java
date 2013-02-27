package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-ID-form-bean of type_of_institution. ID-formBeans contain all the attributes of type_of_institution but it does not contain the associations.
 * 
 * @author Automatika - JustInMind SL
 */
public class Type_of_institution_IDForm extends ValidatorFormAndAction{


String type_of_institutioncode;


	private String description=null;






public Type_of_institution_IDForm getType_of_institution() {
	return this;
}

public String getType_of_institutioncode() {
	return type_of_institutioncode;
}

public void setType_of_institutioncode(String type_of_institutioncode) {
	this.type_of_institutioncode = type_of_institutioncode;
}

	
	@Override
	public String toString(){
		
		if(getType_of_institutioncode()==null || getType_of_institutioncode().equals("")) return "";
	
		
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