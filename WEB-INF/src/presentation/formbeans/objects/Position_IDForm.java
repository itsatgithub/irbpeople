package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-ID-form-bean of position. ID-formBeans contain all the attributes of position but it does not contain the associations.
 * 
 * @author Automatika - JustInMind SL
 */
public class Position_IDForm extends ValidatorFormAndAction{


String positioncode;


	private String description=null;






public Position_IDForm getPosition() {
	return this;
}

public String getPositioncode() {
	return positioncode;
}

public void setPositioncode(String positioncode) {
	this.positioncode = positioncode;
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