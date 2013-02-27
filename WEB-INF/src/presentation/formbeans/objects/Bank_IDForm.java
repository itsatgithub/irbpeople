package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-ID-form-bean of bank. ID-formBeans contain all the attributes of bank but it does not contain the associations.
 * 
 * @author Automatika - JustInMind SL
 */
public class Bank_IDForm extends ValidatorFormAndAction{


String bankcode;


	private String description=null;






public Bank_IDForm getBank() {
	return this;
}

public String getBankcode() {
	return bankcode;
}

public void setBankcode(String bankcode) {
	this.bankcode = bankcode;
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