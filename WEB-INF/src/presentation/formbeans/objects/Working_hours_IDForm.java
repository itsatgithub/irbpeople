package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-ID-form-bean of working_hours. ID-formBeans contain all the attributes of working_hours but it does not contain the associations.
 * 
 * @author Automatika - JustInMind SL
 */
public class Working_hours_IDForm extends ValidatorFormAndAction{


String working_hourscode;


	private String description=null;






public Working_hours_IDForm getWorking_hours() {
	return this;
}

public String getWorking_hourscode() {
	return working_hourscode;
}

public void setWorking_hourscode(String working_hourscode) {
	this.working_hourscode = working_hourscode;
}

	
	@Override
	public String toString(){
		
		if(getWorking_hourscode()==null || getWorking_hourscode().equals("")) return "";
	
		
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