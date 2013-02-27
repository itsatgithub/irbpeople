package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-form-bean of payment
 * 
 * @author Automatika - JustInMind SL
 */
public class Payment_Form extends ValidatorFormAndAction{

	String version;
	String paymentcode;


	private String description=null;





	
	public Payment_Form getPayment() {
		return this;
	}
	
	public String getPaymentcode() {
		return paymentcode;
	}
	
	public void setPaymentcode(String paymentcode) {
		this.paymentcode = paymentcode;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
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