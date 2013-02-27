package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-ID-form-bean of gender. ID-formBeans contain all the attributes of gender but it does not contain the associations.
 * 
 * @author Automatika - JustInMind SL
 */
public class Gender_IDForm extends ValidatorFormAndAction{


String gendercode;


	private String description=null;






public Gender_IDForm getGender() {
	return this;
}

public String getGendercode() {
	return gendercode;
}

public void setGendercode(String gendercode) {
	this.gendercode = gendercode;
}

	
	@Override
	public String toString(){
		
		if(getGendercode()==null || getGendercode().equals("")) return "";
	
		
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