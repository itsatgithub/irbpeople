package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-form-bean of bank
 * 
 * @author Automatika - JustInMind SL
 */
public class Bank_Form extends ValidatorFormAndAction{

	String version;
	String bankcode;


	private String description=null;





	
	public Bank_Form getBank() {
		return this;
	}
	
	public String getBankcode() {
		return bankcode;
	}
	
	public void setBankcode(String bankcode) {
		this.bankcode = bankcode;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}

	
	@Override
	public String toString(){
		
		if(getBankcode()==null || getBankcode().equals("")) return "";
	
		
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