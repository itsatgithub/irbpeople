package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-form-bean of payroll_institution
 * 
 * @author Automatika - JustInMind SL
 */
public class Payroll_institution_Form extends ValidatorFormAndAction{

	String version;
	String payroll_institutioncode;


	private String description=null;





	
	public Payroll_institution_Form getPayroll_institution() {
		return this;
	}
	
	public String getPayroll_institutioncode() {
		return payroll_institutioncode;
	}
	
	public void setPayroll_institutioncode(String payroll_institutioncode) {
		this.payroll_institutioncode = payroll_institutioncode;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}

	
	@Override
	public String toString(){
		
		if(getPayroll_institutioncode()==null || getPayroll_institutioncode().equals("")) return "";
	
		
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