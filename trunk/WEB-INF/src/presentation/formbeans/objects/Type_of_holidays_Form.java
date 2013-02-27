package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-form-bean of type_of_holidays
 * 
 * @author Automatika - JustInMind SL
 */
public class Type_of_holidays_Form extends ValidatorFormAndAction{

	String version;
	String type_of_holidayscode;


	private String description=null;





	
	public Type_of_holidays_Form getType_of_holidays() {
		return this;
	}
	
	public String getType_of_holidayscode() {
		return type_of_holidayscode;
	}
	
	public void setType_of_holidayscode(String type_of_holidayscode) {
		this.type_of_holidayscode = type_of_holidayscode;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}

	
	@Override
	public String toString(){
		
		if(getType_of_holidayscode()==null || getType_of_holidayscode().equals("")) return "";
	
		
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