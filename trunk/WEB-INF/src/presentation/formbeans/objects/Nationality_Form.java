package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-form-bean of nationality
 * 
 * @author Automatika - JustInMind SL
 */
public class Nationality_Form extends ValidatorFormAndAction{

	String version;
	String nationalitycode;


	private String description=null;





	
	public Nationality_Form getNationality() {
		return this;
	}
	
	public String getNationalitycode() {
		return nationalitycode;
	}
	
	public void setNationalitycode(String nationalitycode) {
		this.nationalitycode = nationalitycode;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
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