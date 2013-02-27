package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-form-bean of position
 * 
 * @author Automatika - JustInMind SL
 */
public class Position_Form extends ValidatorFormAndAction{

	String version;
	String positioncode;


	private String description=null;





	
	public Position_Form getPosition() {
		return this;
	}
	
	public String getPositioncode() {
		return positioncode;
	}
	
	public void setPositioncode(String positioncode) {
		this.positioncode = positioncode;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}

	
	@Override
	public String toString(){
		
		if(getPositioncode()==null || getPositioncode().equals("")) return "";
	
		
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