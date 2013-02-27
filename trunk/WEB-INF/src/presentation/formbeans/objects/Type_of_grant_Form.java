package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-form-bean of type_of_grant
 * 
 * @author Automatika - JustInMind SL
 */
public class Type_of_grant_Form extends ValidatorFormAndAction{

	String version;
	String type_of_grantcode;


	private String description=null;





	
	public Type_of_grant_Form getType_of_grant() {
		return this;
	}
	
	public String getType_of_grantcode() {
		return type_of_grantcode;
	}
	
	public void setType_of_grantcode(String type_of_grantcode) {
		this.type_of_grantcode = type_of_grantcode;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}

	
	@Override
	public String toString(){
		
		if(getType_of_grantcode()==null || getType_of_grantcode().equals("")) return "";
	
		
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