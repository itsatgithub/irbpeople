package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-form-bean of holiday
 * 
 * @author Automatika - JustInMind SL
 */
public class Holiday_Form extends ValidatorFormAndAction{

	String version;
	String holidaycode;


	private String start_date=null;

	private String end_date=null;

	private String days=null;

	private String description=null;




	private Personal_IDForm holiday_personal=null;

	private Type_of_holidays_IDForm type_of_holiday=null;


	
	public Holiday_Form getHoliday() {
		return this;
	}
	
	public String getHolidaycode() {
		return holidaycode;
	}
	
	public void setHolidaycode(String holidaycode) {
		this.holidaycode = holidaycode;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
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




	
	public Personal_IDForm getHoliday_personal(){
		if(holiday_personal == null)
			holiday_personal = new Personal_IDForm();
		return holiday_personal;
	}
	
	public void setHoliday_personal(Personal_IDForm holiday_personal){
		this.holiday_personal = holiday_personal;
	}
	

	
	public Type_of_holidays_IDForm getType_of_holiday(){
		if(type_of_holiday == null)
			type_of_holiday = new Type_of_holidays_IDForm();
		return type_of_holiday;
	}
	
	public void setType_of_holiday(Type_of_holidays_IDForm type_of_holiday){
		this.type_of_holiday = type_of_holiday;
	}
	


}