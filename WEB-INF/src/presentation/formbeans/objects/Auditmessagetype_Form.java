package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-form-bean of auditmessagetype
 * 
 * @author Automatika - JustInMind SL
 */
public class Auditmessagetype_Form extends ValidatorFormAndAction{

	String version;
	String auditmessagetypecode;


	private String description=null;





	
	public Auditmessagetype_Form getAuditmessagetype() {
		return this;
	}
	
	public String getAuditmessagetypecode() {
		return auditmessagetypecode;
	}
	
	public void setAuditmessagetypecode(String auditmessagetypecode) {
		this.auditmessagetypecode = auditmessagetypecode;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}

	
	@Override
	public String toString(){
		
		if(getAuditmessagetypecode()==null || getAuditmessagetypecode().equals("")) return "";
	
		
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