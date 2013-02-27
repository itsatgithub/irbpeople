package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-ID-form-bean of organization_unit. ID-formBeans contain all the attributes of organization_unit but it does not contain the associations.
 * 
 * @author Automatika - JustInMind SL
 */
public class Organization_unit_IDForm extends ValidatorFormAndAction{


String organization_unitcode;


	private String description=null;






public Organization_unit_IDForm getOrganization_unit() {
	return this;
}

public String getOrganization_unitcode() {
	return organization_unitcode;
}

public void setOrganization_unitcode(String organization_unitcode) {
	this.organization_unitcode = organization_unitcode;
}

	
	@Override
	public String toString(){
		
		if(getOrganization_unitcode()==null || getOrganization_unitcode().equals("")) return "";
	
		
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