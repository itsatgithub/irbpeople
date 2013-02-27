package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-ID-form-bean of location. ID-formBeans contain all the attributes of location but it does not contain the associations.
 * 
 * @author Automatika - JustInMind SL
 */
public class Location_IDForm extends ValidatorFormAndAction{


String locationcode;


	private String description=null;






public Location_IDForm getLocation() {
	return this;
}

public String getLocationcode() {
	return locationcode;
}

public void setLocationcode(String locationcode) {
	this.locationcode = locationcode;
}

	
	@Override
	public String toString(){
		
		if(getLocationcode()==null || getLocationcode().equals("")) return "";
	
		
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