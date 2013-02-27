package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-form-bean of marital_status
 * 
 * @author Automatika - JustInMind SL
 */
public class Marital_status_Form extends ValidatorFormAndAction{

	String version;
	String marital_statuscode;


	private String description=null;





	
	public Marital_status_Form getMarital_status() {
		return this;
	}
	
	public String getMarital_statuscode() {
		return marital_statuscode;
	}
	
	public void setMarital_statuscode(String marital_statuscode) {
		this.marital_statuscode = marital_statuscode;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}

	
	@Override
	public String toString(){
		
		if(getMarital_statuscode()==null || getMarital_statuscode().equals("")) return "";
	
		
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