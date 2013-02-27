package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-form-bean of auditmessage
 * 
 * @author Automatika - JustInMind SL
 */
public class Auditmessage_Form extends ValidatorFormAndAction{

	String version;
	String auditmessagecode;


	private String timestamp=null;

	private String message=null;




	private UsuarioIDForm user=null;

	private Auditmessagetype_IDForm type=null;


	
	public Auditmessage_Form getAuditmessage() {
		return this;
	}
	
	public String getAuditmessagecode() {
		return auditmessagecode;
	}
	
	public void setAuditmessagecode(String auditmessagecode) {
		this.auditmessagecode = auditmessagecode;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
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




	
	public UsuarioIDForm getUser(){
		if(user == null)
			user = new UsuarioIDForm();
		return user;
	}
	
	public void setUser(UsuarioIDForm user){
		this.user = user;
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