package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-ID-form-bean of auditmessage. ID-formBeans contain all the attributes of auditmessage but it does not contain the associations.
 * 
 * @author Automatika - JustInMind SL
 */
public class Auditmessage_IDForm extends ValidatorFormAndAction{


String auditmessagecode;


	private String timestamp=null;

	private String message=null;




	private Auditmessagetype_IDForm type=null;



public Auditmessage_IDForm getAuditmessage() {
	return this;
}

public String getAuditmessagecode() {
	return auditmessagecode;
}

public void setAuditmessagecode(String auditmessagecode) {
	this.auditmessagecode = auditmessagecode;
}

	
	@Override
	public String toString(){
		
		if(getAuditmessagecode()==null || getAuditmessagecode().equals("")) return "";
	
		
			String result=getTimestamp()+" - "+getMessage()+"";
			return (result!=null)?result:"";
		
	}

public String get_descripcion(){
	return this.toString();
}



	
	public String getTimestamp(){
		return timestamp;
	}
	
	public void setTimestamp(String timestamp){
		this.timestamp=timestamp;
	}

	
	public String getMessage(){
		return message;
	}
	
	public void setMessage(String message){
		this.message=message;
	}




	
	public Auditmessagetype_IDForm getType(){
		if(type == null)
			type = new Auditmessagetype_IDForm();
		return type;
	}
	
	public void setType(Auditmessagetype_IDForm type){
		this.type = type;
	}


}