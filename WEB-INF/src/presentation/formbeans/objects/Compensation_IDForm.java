package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-ID-form-bean of compensation. ID-formBeans contain all the attributes of compensation but it does not contain the associations.
 * 
 * @author Automatika - JustInMind SL
 */
public class Compensation_IDForm extends ValidatorFormAndAction{


String compensationcode;


	private String start_date=null;

	private String end_date=null;

	private String description=null;

	private String amount=null;






public Compensation_IDForm getCompensation() {
	return this;
}

public String getCompensationcode() {
	return compensationcode;
}

public void setCompensationcode(String compensationcode) {
	this.compensationcode = compensationcode;
}

	
	@Override
	public String toString(){
		
		if(getCompensationcode()==null || getCompensationcode().equals("")) return "";
	
		
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

	
	public String getAmount(){
		return amount;
	}
	
	public void setAmount(String amount){
		this.amount=amount;
	}





}