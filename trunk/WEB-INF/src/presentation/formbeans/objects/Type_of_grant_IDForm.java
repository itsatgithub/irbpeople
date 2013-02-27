package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-ID-form-bean of type_of_grant. ID-formBeans contain all the attributes of type_of_grant but it does not contain the associations.
 * 
 * @author Automatika - JustInMind SL
 */
public class Type_of_grant_IDForm extends ValidatorFormAndAction{


String type_of_grantcode;


	private String description=null;






public Type_of_grant_IDForm getType_of_grant() {
	return this;
}

public String getType_of_grantcode() {
	return type_of_grantcode;
}

public void setType_of_grantcode(String type_of_grantcode) {
	this.type_of_grantcode = type_of_grantcode;
}

	
	@Override
	public String toString(){
		
		if(getType_of_grantcode()==null || getType_of_grantcode().equals("")) return "";
	
		
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