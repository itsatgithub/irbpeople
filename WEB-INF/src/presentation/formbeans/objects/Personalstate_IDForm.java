package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-ID-form-bean of personalstate. ID-formBeans contain all the attributes of personalstate but it does not contain the associations.
 * 
 * @author Automatika - JustInMind SL
 */
public class Personalstate_IDForm extends ValidatorFormAndAction{


String personalstatecode;


	private String description=null;






public Personalstate_IDForm getPersonalstate() {
	return this;
}

public String getPersonalstatecode() {
	return personalstatecode;
}

public void setPersonalstatecode(String personalstatecode) {
	this.personalstatecode = personalstatecode;
}

	
	@Override
	public String toString(){
		
		if(getPersonalstatecode()==null || getPersonalstatecode().equals("")) return "";
	
		
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