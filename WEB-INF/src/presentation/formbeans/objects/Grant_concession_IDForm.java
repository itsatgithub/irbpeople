package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-ID-form-bean of grant_concession. ID-formBeans contain all the attributes of grant_concession but it does not contain the associations.
 * 
 * @author Automatika - JustInMind SL
 */
public class Grant_concession_IDForm extends ValidatorFormAndAction{


String grant_concessioncode;


	private String start_date=null;

	private String end_date=null;

	private String call_code=null;






public Grant_concession_IDForm getGrant_concession() {
	return this;
}

public String getGrant_concessioncode() {
	return grant_concessioncode;
}

public void setGrant_concessioncode(String grant_concessioncode) {
	this.grant_concessioncode = grant_concessioncode;
}

	
	@Override
	public String toString(){
		
		if(getGrant_concessioncode()==null || getGrant_concessioncode().equals("")) return "";
	
		
			String result=getStart_date()+" - "+getEnd_date()+" - "+getCall_code()+"";
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

	
	public String getCall_code(){
		return call_code;
	}
	
	public void setCall_code(String call_code){
		this.call_code=call_code;
	}





}