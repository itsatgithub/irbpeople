package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-form-bean of country
 * 
 * @author Automatika - JustInMind SL
 */
public class Country_Form extends ValidatorFormAndAction{

	String version;
	String countrycode;


	private String description=null;





	
	public Country_Form getCountry() {
		return this;
	}
	
	public String getCountrycode() {
		return countrycode;
	}
	
	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
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