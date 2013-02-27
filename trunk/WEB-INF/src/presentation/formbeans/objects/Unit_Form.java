package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-form-bean of unit
 * 
 * @author Automatika - JustInMind SL
 */
public class Unit_Form extends ValidatorFormAndAction{

	String version;
	String unitcode;


	private String description=null;

	private String group=null;


	private Organization_unit_IDForm organization_unit=null;
	
	private Personal_IDForm supervisor=null;


	
	public Unit_Form getUnit() {
		return this;
	}
	
	public String getUnitcode() {
		return unitcode;
	}
	
	public void setUnitcode(String unitcode) {
		this.unitcode = unitcode;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}

	
	@Override
	public String toString(){
		
		if(getUnitcode()==null || getUnitcode().equals("")) return "";
	
		
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




	
	public Organization_unit_IDForm getOrganization_unit(){
		if(organization_unit == null)
			organization_unit = new Organization_unit_IDForm();
		return organization_unit;
	}
	
	public void setOrganization_unit(Organization_unit_IDForm organization_unit){
		this.organization_unit = organization_unit;
	}
	
	public Personal_IDForm getSupervisor(){
		if(supervisor == null)
			supervisor = new Personal_IDForm();
		return supervisor;
	}
	
	public void setSupervisor(Personal_IDForm supervisor){
		this.supervisor = supervisor;
	}

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
	


}