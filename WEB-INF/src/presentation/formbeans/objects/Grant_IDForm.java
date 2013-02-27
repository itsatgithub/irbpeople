package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-ID-form-bean of grant. ID-formBeans contain all the attributes of grant but it does not contain the associations.
 * 
 * @author Automatika - JustInMind SL
 */
public class Grant_IDForm extends ValidatorFormAndAction{


String grantcode;


	private String description=null;

	private String is_irbs=null;





public Grant_IDForm getGrant() {
	return this;
}

public String getGrantcode() {
	return grantcode;
}

public void setGrantcode(String grantcode) {
	this.grantcode = grantcode;
}

	
	@Override
	public String toString(){
		
		if(getGrantcode()==null || getGrantcode().equals("")) return "";
	
		
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

	public String getIs_irbs() {
		return is_irbs;
	}

	public void setIs_irbs(String is_irbs) {
		this.is_irbs = is_irbs;
	}





}