package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-ID-form-bean of unit. ID-formBeans contain all the attributes of unit but it does not contain the associations.
 * 
 * @author Automatika - JustInMind SL
 */
public class Unit_IDForm extends ValidatorFormAndAction{


String unitcode;


	private String description=null;






public Unit_IDForm getUnit() {
	return this;
}

public String getUnitcode() {
	return unitcode;
}

public void setUnitcode(String unitcode) {
	this.unitcode = unitcode;
}

	
	@Override
	public String toString(){
		
		if(getUnitcode()==null || getUnitcode().equals("")) return "";
	
		
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