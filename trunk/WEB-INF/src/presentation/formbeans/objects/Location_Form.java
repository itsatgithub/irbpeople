package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-form-bean of location
 * 
 * @author Automatika - JustInMind SL
 */
public class Location_Form extends ValidatorFormAndAction{

	String version;
	String locationcode;
	String newLocationcode;


	private String description=null;





	
	public Location_Form getLocation() {
		return this;
	}
	
	public String getLocationcode() {
		return locationcode;
	}
	
	public void setLocationcode(String locationcode) {
		this.locationcode = locationcode;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}

	
	@Override
	public String toString(){
		
		if(getLocationcode()==null || getLocationcode().equals("")) return "";
	
		
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

	public String getNewLocationcode() {
		if(newLocationcode==null || newLocationcode.equals(""))
			newLocationcode=getLocationcode();
		return newLocationcode;
	}

	public void setNewLocationcode(String newLocationcode) {
		this.newLocationcode = newLocationcode;
	}





}