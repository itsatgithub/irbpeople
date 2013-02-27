package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-ID-form-bean of auditmessagetype. ID-formBeans contain all the attributes of auditmessagetype but it does not contain the associations.
 * 
 * @author Automatika - JustInMind SL
 */
public class Auditmessagetype_IDForm extends ValidatorFormAndAction{


String auditmessagetypecode;


	private String description=null;






public Auditmessagetype_IDForm getAuditmessagetype() {
	return this;
}

public String getAuditmessagetypecode() {
	return auditmessagetypecode;
}

public void setAuditmessagetypecode(String auditmessagetypecode) {
	this.auditmessagetypecode = auditmessagetypecode;
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