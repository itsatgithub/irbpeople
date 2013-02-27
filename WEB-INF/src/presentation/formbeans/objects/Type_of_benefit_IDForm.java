package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-ID-form-bean of type_of_benefit. ID-formBeans contain all the attributes of type_of_benefit but it does not contain the associations.
 * 
 * @author Automatika - JustInMind SL
 */
public class Type_of_benefit_IDForm extends ValidatorFormAndAction{


String type_of_benefitcode;


	private String description=null;






public Type_of_benefit_IDForm getType_of_benefit() {
	return this;
}

public String getType_of_benefitcode() {
	return type_of_benefitcode;
}

public void setType_of_benefitcode(String type_of_benefitcode) {
	this.type_of_benefitcode = type_of_benefitcode;
}

	
	@Override
	public String toString(){
		
		if(getType_of_benefitcode()==null || getType_of_benefitcode().equals("")) return "";
	
		
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