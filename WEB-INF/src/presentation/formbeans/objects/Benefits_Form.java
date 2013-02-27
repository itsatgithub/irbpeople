package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-form-bean of benefits
 * 
 * @author Automatika - JustInMind SL
 */
public class Benefits_Form extends ValidatorFormAndAction{

	String version;
	String benefitscode;


	private String start_date=null;

	private String end_date=null;

	private String description=null;




	private Personal_IDForm benefits_personal=null;

	private Type_of_benefit_IDForm type_of_benefit=null;


	
	public Benefits_Form getBenefits() {
		return this;
	}
	
	public String getBenefitscode() {
		return benefitscode;
	}
	
	public void setBenefitscode(String benefitscode) {
		this.benefitscode = benefitscode;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}

	
	@Override
	public String toString(){
		
		if(getBenefitscode()==null || getBenefitscode().equals("")) return "";
	
		
			String result=getStart_date()+" - "+getEnd_date()+" - "+getDescription()+"";
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

	
	public String getDescription(){
		return description;
	}
	
	public void setDescription(String description){
		this.description=description;
	}




	
	public Personal_IDForm getBenefits_personal(){
		if(benefits_personal == null)
			benefits_personal = new Personal_IDForm();
		return benefits_personal;
	}
	
	public void setBenefits_personal(Personal_IDForm benefits_personal){
		this.benefits_personal = benefits_personal;
	}
	

	
	public Type_of_benefit_IDForm getType_of_benefit(){
		if(type_of_benefit == null)
			type_of_benefit = new Type_of_benefit_IDForm();
		return type_of_benefit;
	}
	
	public void setType_of_benefit(Type_of_benefit_IDForm type_of_benefit){
		this.type_of_benefit = type_of_benefit;
	}
	


}