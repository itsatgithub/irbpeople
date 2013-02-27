package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-form-bean of personalstate
 * 
 * @author Automatika - JustInMind SL
 */
public class Personalstate_Form extends ValidatorFormAndAction{

	String version;
	String personalstatecode;


	private String description=null;





	
	public Personalstate_Form getPersonalstate() {
		return this;
	}
	
	public String getPersonalstatecode() {
		return personalstatecode;
	}
	
	public void setPersonalstatecode(String personalstatecode) {
		this.personalstatecode = personalstatecode;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}

	
	@Override
	public String toString(){
		
		if(getPersonalstatecode()==null || getPersonalstatecode().equals("")) return "";
	
		
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