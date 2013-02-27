package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-ID-form-bean of marital_status. ID-formBeans contain all the attributes of marital_status but it does not contain the associations.
 * 
 * @author Automatika - JustInMind SL
 */
public class Marital_status_IDForm extends ValidatorFormAndAction{


String marital_statuscode;


	private String description=null;






public Marital_status_IDForm getMarital_status() {
	return this;
}

public String getMarital_statuscode() {
	return marital_statuscode;
}

public void setMarital_statuscode(String marital_statuscode) {
	this.marital_statuscode = marital_statuscode;
}

	
	@Override
	public String toString(){
		
		if(getMarital_statuscode()==null || getMarital_statuscode().equals("")) return "";
	
		
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