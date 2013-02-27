package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-form-bean of gender
 * 
 * @author Automatika - JustInMind SL
 */
public class Gender_Form extends ValidatorFormAndAction{

	String version;
	String gendercode;


	private String description=null;





	
	public Gender_Form getGender() {
		return this;
	}
	
	public String getGendercode() {
		return gendercode;
	}
	
	public void setGendercode(String gendercode) {
		this.gendercode = gendercode;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
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