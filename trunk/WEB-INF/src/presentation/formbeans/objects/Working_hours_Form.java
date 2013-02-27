package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-form-bean of working_hours
 * 
 * @author Automatika - JustInMind SL
 */
public class Working_hours_Form extends ValidatorFormAndAction{

	String version;
	String working_hourscode;


	private String description=null;





	
	public Working_hours_Form getWorking_hours() {
		return this;
	}
	
	public String getWorking_hourscode() {
		return working_hourscode;
	}
	
	public void setWorking_hourscode(String working_hourscode) {
		this.working_hourscode = working_hourscode;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}

	
	@Override
	public String toString(){
		
		if(getWorking_hourscode()==null || getWorking_hourscode().equals("")) return "";
	
		
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