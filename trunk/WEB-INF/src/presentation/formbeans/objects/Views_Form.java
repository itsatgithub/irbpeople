package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-form-bean of country
 * 
 * @author Automatika - JustInMind SL
 */
public class Views_Form extends ValidatorFormAndAction{

	String version;
	String viewscode;


	private String name=null;

	
	public String getViewscode() {
		return viewscode;
	}


	public void setViewscode(String viewscode) {
		this.viewscode = viewscode;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Views_Form getViews() {
		return this;
	}
	
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}

	
	@Override
	public String toString(){
		
		if(getViewscode()==null || getViewscode().equals("")) return "";
	
		
			String result=getName()+"";
			return (result!=null)?result:"";
		
	}
	
	public String get_descripcion(){
    	return this.toString();
    }


}