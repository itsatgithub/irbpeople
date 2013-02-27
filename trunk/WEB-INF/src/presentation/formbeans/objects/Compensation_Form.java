package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-form-bean of compensation
 * 
 * @author Automatika - JustInMind SL
 */
public class Compensation_Form extends ValidatorFormAndAction{
	
	public static final String FINAL_AMOUNT="0";
	public static final String PERCENT_AMOUNT="1";
	public static final String INCREASE_AMOUNT="2";
	
	String percentAugment;
	String amountAugment;
	String amountType=FINAL_AMOUNT;
	
	

	String version;
	String compensationcode;
	String current;


	private String start_date=null;

	private String end_date=null;

	private String description=null;

	private String amount=null;




	private Personal_IDForm compensation_personal=null;

	private Type_of_compensation_IDForm type_of_compensation=null;


	
	public Compensation_Form getCompensation() {
		return this;
	}
	
	public String getCompensationcode() {
		return compensationcode;
	}
	
	public void setCompensationcode(String compensationcode) {
		this.compensationcode = compensationcode;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
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




	
	public Personal_IDForm getCompensation_personal(){
		if(compensation_personal == null)
			compensation_personal = new Personal_IDForm();
		return compensation_personal;
	}
	
	public void setCompensation_personal(Personal_IDForm compensation_personal){
		this.compensation_personal = compensation_personal;
	}
	

	
	public Type_of_compensation_IDForm getType_of_compensation(){
		if(type_of_compensation == null)
			type_of_compensation = new Type_of_compensation_IDForm();
		return type_of_compensation;
	}
	
	public void setType_of_compensation(Type_of_compensation_IDForm type_of_compensation){
		this.type_of_compensation = type_of_compensation;
	}

	public String getAmountAugment() {
		return amountAugment;
	}

	public void setAmountAugment(String amountAugment) {
		this.amountAugment = amountAugment;
	}

	public String getPercentAugment() {
		return percentAugment;
	}

	public void setPercentAugment(String percentAugment) {
		this.percentAugment = percentAugment;
	}

	public String getAmountType() {
		return amountType;
	}

	public void setAmountType(String amountType) {
		this.amountType = amountType;
	}

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }
	


}
