package presentation.formbeans.objects;





import presentation.formbeans.objects.*;
import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-form-bean of funding_detail
 * 
 * @author Automatika - JustInMind SL
 */
public class Funding_detail_Form extends ValidatorFormAndAction{

	String version;
	String funding_detailcode;


	private String institution=null;

	private Irb_budget_Form irb_budget_code=null;

	private String percent=null;




	private Personal_IDForm funding_detail_personal=null;


	
	public Funding_detail_Form getFunding_detail() {
		return this;
	}
	
	public String getFunding_detailcode() {
		return funding_detailcode;
	}
	
	public void setFunding_detailcode(String funding_detailcode) {
		this.funding_detailcode = funding_detailcode;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}

	
	@Override
	public String toString(){
		
		if(getFunding_detailcode()==null || getFunding_detailcode().equals("")) return "";
	
		
			String result=getInstitution()+" - "+getIrb_budget_code()+" - "+getPercent()+"";
			return (result!=null)?result:"";
		
	}
	
	public String get_descripcion(){
    	return this.toString();
    }



	
	public String getInstitution(){
		return institution;
	}
	
	public void setInstitution(String institution){
		this.institution=institution;
	}

	
	public Irb_budget_Form getIrb_budget_code(){
		if(irb_budget_code==null){
			irb_budget_code=new Irb_budget_Form();
		}
		return irb_budget_code;
	}
	
	public void setIrb_budget_code(Irb_budget_Form irb_budget_code){
		this.irb_budget_code=irb_budget_code;
	}

	
	public String getPercent(){
		return percent;
	}
	
	public void setPercent(String percent){
		this.percent=percent;
	}




	
	public Personal_IDForm getFunding_detail_personal(){
		if(funding_detail_personal == null)
			funding_detail_personal = new Personal_IDForm();
		return funding_detail_personal;
	}
	
	public void setFunding_detail_personal(Personal_IDForm funding_detail_personal){
		this.funding_detail_personal = funding_detail_personal;
	}
	


}