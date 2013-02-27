package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-ID-form-bean of benefits. ID-formBeans contain all the attributes of benefits but it does not contain the associations.
 * 
 * @author Automatika - JustInMind SL
 */
public class Benefits_IDForm extends ValidatorFormAndAction{


String benefitscode;


	private String start_date=null;

	private String end_date=null;

	private String description=null;






public Benefits_IDForm getBenefits() {
	return this;
}

public String getBenefitscode() {
	return benefitscode;
}

public void setBenefitscode(String benefitscode) {
	this.benefitscode = benefitscode;
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





}