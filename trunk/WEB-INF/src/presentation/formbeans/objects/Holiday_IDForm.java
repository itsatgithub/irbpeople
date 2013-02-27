package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-ID-form-bean of holiday. ID-formBeans contain all the attributes of holiday but it does not contain the associations.
 * 
 * @author Automatika - JustInMind SL
 */
public class Holiday_IDForm extends ValidatorFormAndAction{


String holidaycode;


	private String start_date=null;

	private String end_date=null;

	private String days=null;

	private String description=null;






public Holiday_IDForm getHoliday() {
	return this;
}

public String getHolidaycode() {
	return holidaycode;
}

public void setHolidaycode(String holidaycode) {
	this.holidaycode = holidaycode;
}

	
	@Override
	public String toString(){
		
		if(getHolidaycode()==null || getHolidaycode().equals("")) return "";
	
		
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

	
	public String getDays(){
		return days;
	}
	
	public void setDays(String days){
		this.days=days;
	}

	
	public String getDescription(){
		return description;
	}
	
	public void setDescription(String description){
		this.description=description;
	}





}