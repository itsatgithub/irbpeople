package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-ID-form-bean of country. ID-formBeans contain all the attributes of country but it does not contain the associations.
 * 
 * @author Automatika - JustInMind SL
 */
public class Country_IDForm extends ValidatorFormAndAction{


String countrycode;


	private String description=null;






public Country_IDForm getCountry() {
	return this;
}

public String getCountrycode() {
	return countrycode;
}

public void setCountrycode(String countrycode) {
	this.countrycode = countrycode;
}

	
	@Override
	public String toString(){
		
		if(getCountrycode()==null || getCountrycode().equals("")) return "";
	
		
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