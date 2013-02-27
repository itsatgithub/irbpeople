package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-ID-form-bean of type_of_compensation. ID-formBeans contain all the attributes of type_of_compensation but it does not contain the associations.
 * 
 * @author Automatika - JustInMind SL
 */
public class Type_of_compensation_IDForm extends ValidatorFormAndAction{


String type_of_compensationcode;


	private String description=null;






public Type_of_compensation_IDForm getType_of_compensation() {
	return this;
}

public String getType_of_compensationcode() {
	return type_of_compensationcode;
}

public void setType_of_compensationcode(String type_of_compensationcode) {
	this.type_of_compensationcode = type_of_compensationcode;
}

	
	@Override
	public String toString(){
		
		if(getType_of_compensationcode()==null || getType_of_compensationcode().equals("")) return "";
	
		
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