package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-form-bean of child
 * 
 * @author Automatika - JustInMind SL
 */
public class Child_Form extends ValidatorFormAndAction{

	String version;
	String childcode;


	private String birth_date=null;

	private String observations=null;




	private Personal_IDForm child_personal=null;


	
	public Child_Form getChild() {
		return this;
	}
	
	public String getChildcode() {
		return childcode;
	}
	
	public void setChildcode(String childcode) {
		this.childcode = childcode;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}

	
	@Override
	public String toString(){
		
		if(getChildcode()==null || getChildcode().equals("")) return "";
	
		
			String result=getBirth_date()+" - "+getObservations()+"";
			return (result!=null)?result:"";
		
	}
	
	public String get_descripcion(){
    	return this.toString();
    }



	
	public String getBirth_date(){
		return birth_date;
	}
	
	public void setBirth_date(String birth_date){
		this.birth_date=birth_date;
	}

	
	public String getObservations(){
		return observations;
	}
	
	public void setObservations(String observations){
		this.observations=observations;
	}




	
	public Personal_IDForm getChild_personal(){
		if(child_personal == null)
			child_personal = new Personal_IDForm();
		return child_personal;
	}
	
	public void setChild_personal(Personal_IDForm child_personal){
		this.child_personal = child_personal;
	}
	


}