package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-ID-form-bean of payment. ID-formBeans contain all the attributes of payment but it does not contain the associations.
 * 
 * @author Automatika - JustInMind SL
 */
public class Payment_IDForm extends ValidatorFormAndAction{


String paymentcode;


	private String description=null;






public Payment_IDForm getPayment() {
	return this;
}

public String getPaymentcode() {
	return paymentcode;
}

public void setPaymentcode(String paymentcode) {
	this.paymentcode = paymentcode;
}

	
	@Override
	public String toString(){
		
		if(getPaymentcode()==null || getPaymentcode().equals("")) return "";
	
		
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