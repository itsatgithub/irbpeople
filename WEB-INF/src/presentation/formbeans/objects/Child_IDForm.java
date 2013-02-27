package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-ID-form-bean of child. ID-formBeans contain all the attributes of child but it does not contain the associations.
 * 
 * @author Automatika - JustInMind SL
 */
public class Child_IDForm extends ValidatorFormAndAction{


String childcode;


	private String birth_date=null;

	private String observations=null;






public Child_IDForm getChild() {
	return this;
}

public String getChildcode() {
	return childcode;
}

public void setChildcode(String childcode) {
	this.childcode = childcode;
}

	
	@Override
	public String toString(){
		
		if(getChildcode()==null || getChildcode().equals("")) return "";
	
		
			String result=getBirth_date()+" - "+getObservations()+"";
			return (result!=null)?result:"";
		
	}

public String get_descripcion(){
	return this.toString();
}



	
	public String getBirth_date(){
		return birth_date;
	}
	
	public void setBirth_date(String birth_date){
		this.birth_date=birth_date;
	}

	
	public String getObservations(){
		return observations;
	}
	
	public void setObservations(String observations){
		this.observations=observations;
	}





}