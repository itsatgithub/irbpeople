package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-ID-form-bean of nationality. ID-formBeans contain all the attributes of nationality but it does not contain the associations.
 * 
 * @author Automatika - JustInMind SL
 */
public class Nationality_IDForm extends ValidatorFormAndAction{


String nationalitycode;


	private String description=null;






public Nationality_IDForm getNationality() {
	return this;
}

public String getNationalitycode() {
	return nationalitycode;
}

public void setNationalitycode(String nationalitycode) {
	this.nationalitycode = nationalitycode;
}

	
	@Override
	public String toString(){
		
		if(getNationalitycode()==null || getNationalitycode().equals("")) return "";
	
		
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