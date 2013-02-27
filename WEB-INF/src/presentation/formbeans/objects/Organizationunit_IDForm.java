package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-ID-form-bean of organizationunit. ID-formBeans contain all the attributes of organizationunit but it does not contain the associations.
 * 
 * @author Automatika - JustInMind SL
 */
public class Organizationunit_IDForm extends ValidatorFormAndAction{


String organizationunitcode;


	private String description=null;






public Organizationunit_IDForm getOrganizationunit() {
	return this;
}

public String getOrganizationunitcode() {
	return organizationunitcode;
}

public void setOrganizationunitcode(String organizationunitcode) {
	this.organizationunitcode = organizationunitcode;
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