package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-ID-form-bean of area. ID-formBeans contain all the attributes of area but it does not contain the associations.
 * 
 * @author Automatika - JustInMind SL
 */
public class Area_IDForm extends ValidatorFormAndAction{


String areacode;


	private String description=null;






public Area_IDForm getArea() {
	return this;
}

public String getAreacode() {
	return areacode;
}

public void setAreacode(String areacode) {
	this.areacode = areacode;
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