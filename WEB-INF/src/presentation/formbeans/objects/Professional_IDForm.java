package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-ID-form-bean of professional. ID-formBeans contain all the attributes of professional but it does not contain the associations.
 * 
 * @author Automatika - JustInMind SL
 */
public class Professional_IDForm extends ValidatorFormAndAction{


String professionalcode;


	private String start_date=null;

	private String end_date=null;

	private String email=null;

	private String phone=null;

	private String mobile_long=null;






public Professional_IDForm getProfessional() {
	return this;
}

public String getProfessionalcode() {
	return professionalcode;
}

public void setProfessionalcode(String professionalcode) {
	this.professionalcode = professionalcode;
}

	
	@Override
	public String toString(){
		
		if(getProfessionalcode()==null || getProfessionalcode().equals("")) return "";
	
		
			String result=getStart_date()+" - "+getEnd_date()+"";
			return (result!=null)?result:"";
		
	}

public String get_descripcion(){
	return this.toString();
}



	
	public String getStart_date(){
		return start_date;
	}
	
	public void setStart_date(String start_date){
		this.start_date=start_date;
	}

	
	public String getEnd_date(){
		return end_date;
	}
	
	public void setEnd_date(String end_date){
		this.end_date=end_date;
	}

	
	public String getEmail(){
		return email;
	}
	
	public void setEmail(String email){
		this.email=email;
	}

	
	public String getPhone(){
		return phone;
	}
	
	public void setPhone(String phone){
		this.phone=phone;
	}

	
	public String getMobile_long(){
		return mobile_long;
	}
	
	public void setMobile_long(String mobile_long){
		this.mobile_long=mobile_long;
	}





}