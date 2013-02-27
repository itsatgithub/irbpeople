package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-form-bean of organizationunit
 * 
 * @author Automatika - JustInMind SL
 */
public class Organizationunit_Form extends ValidatorFormAndAction{

	String version;
	String organizationunitcode;


	private String description=null;





	
	public Organizationunit_Form getOrganizationunit() {
		return this;
	}
	
	public String getOrganizationunitcode() {
		return organizationunitcode;
	}
	
	public void setOrganizationunitcode(String organizationunitcode) {
		this.organizationunitcode = organizationunitcode;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}

	
	@Override
	public String toString(){
		
		if(getOrganizationunitcode()==null || getOrganizationunitcode().equals("")) return "";
	
		
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