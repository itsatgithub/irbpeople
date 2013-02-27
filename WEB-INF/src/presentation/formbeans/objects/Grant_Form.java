package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-form-bean of grant
 * 
 * @author Automatika - JustInMind SL
 */
public class Grant_Form extends ValidatorFormAndAction{

	String version;
	String grantcode;


	private String description=null;
	
	private String is_irbs=null;



	private Type_of_grant_IDForm type_of_grant=null;


	
	public Grant_Form getGrant() {
		return this;
	}
	
	public String getGrantcode() {
		return grantcode;
	}
	
	public void setGrantcode(String grantcode) {
		this.grantcode = grantcode;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
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




	
	public Type_of_grant_IDForm getType_of_grant(){
		if(type_of_grant == null)
			type_of_grant = new Type_of_grant_IDForm();
		return type_of_grant;
	}
	
	public void setType_of_grant(Type_of_grant_IDForm type_of_grant){
		this.type_of_grant = type_of_grant;
	}

	public String getIs_irbs() {
		return is_irbs;
	}

	public void setIs_irbs(String is_irbs) {
		this.is_irbs = is_irbs;
	}
	


}