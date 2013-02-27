package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-form-bean of type_of_institution
 * 
 * @author Automatika - JustInMind SL
 */
public class Type_of_institution_Form extends ValidatorFormAndAction{

	String version;
	String type_of_institutioncode;


	private String description=null;





	
	public Type_of_institution_Form getType_of_institution() {
		return this;
	}
	
	public String getType_of_institutioncode() {
		return type_of_institutioncode;
	}
	
	public void setType_of_institutioncode(String type_of_institutioncode) {
		this.type_of_institutioncode = type_of_institutioncode;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}

	
	@Override
	public String toString(){
		
		if(getType_of_institutioncode()==null || getType_of_institutioncode().equals("")) return "";
	
		
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