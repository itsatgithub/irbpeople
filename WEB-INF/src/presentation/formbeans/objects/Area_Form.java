package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-form-bean of area
 * 
 * @author Automatika - JustInMind SL
 */
public class Area_Form extends ValidatorFormAndAction{

	String version;
	String areacode;


	private String description=null;





	
	public Area_Form getArea() {
		return this;
	}
	
	public String getAreacode() {
		return areacode;
	}
	
	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}

	
	@Override
	public String toString(){
		
		if(getAreacode()==null || getAreacode().equals("")) return "";
	
		
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