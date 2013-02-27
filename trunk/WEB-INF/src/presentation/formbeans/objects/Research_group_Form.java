package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-form-bean of research_group
 * 
 * @author Automatika - JustInMind SL
 */
public class Research_group_Form extends ValidatorFormAndAction{

	String version;
	String research_groupcode;


	private String description=null;

	private String group=null;


	private Personal_IDForm supervisor=null;

	private Unit_IDForm unit=null;


	
	public Research_group_Form getResearch_group() {
		return this;
	}
	
	public String getResearch_groupcode() {
		return research_groupcode;
	}
	
	public void setResearch_groupcode(String research_groupcode) {
		this.research_groupcode = research_groupcode;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}

	
	@Override
	public String toString(){
		
		if(getResearch_groupcode()==null || getResearch_groupcode().equals("")) return "";
	
		
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




	
	public Personal_IDForm getSupervisor(){
		if(supervisor == null)
			supervisor = new Personal_IDForm();
		return supervisor;
	}
	
	public void setSupervisor(Personal_IDForm supervisor){
		this.supervisor = supervisor;
	}
	

	
	public Unit_IDForm getUnit(){
		if(unit == null)
			unit = new Unit_IDForm();
		return unit;
	}
	
	public void setUnit(Unit_IDForm unit){
		this.unit = unit;
	}

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
	


}