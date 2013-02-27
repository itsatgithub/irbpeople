package presentation.formbeans.oPersonalSys;




import utils.formbeans.FormBeanContainer;
import utils.formbeans.BOAdderFormBean;

import utils.validator.ValidatorFormAndAction;

import presentation.formbeans.objects.*;

/**
 * This class is a FormBeanContainer for the action action_modify_my_rgroup_personal_cancel_modify_modify_page_personal.
 * 
 * @author Automatika - JustInMind SL
 */
public class Action_modify_my_rgroup_personal_cancel_modify_modify_page_personal_Form extends FormBeanContainer {

	
	//FormBean for the action
	
	

	
	
	
	
	
	
	
	
	
		protected NonObjectData nonBOData=new NonObjectData();
		
		public NonObjectData getNonObjectData(){
			return nonBOData;
		}
	
		public void setNonObjectData(NonObjectData nonBOData){
			this.nonBOData=nonBOData;
		}
		
		/**
		*  This class groups all the data of the formbean which does not fit in the other places
		**/
		public class NonObjectData extends ValidatorFormAndAction{
		
		
		
			private String research_group=null;
		
			private String position=null;
		
			private String start_date=null;
		
			private String end_date=null;
		
			private String type_of_contract=null;
		
			private String location=null;
		
			private String email=null;
		
			private String professional_form=null;
		
			private String professional_mobile=null;
		
			private String payroll_institution=null;
		
		
		
		
		
		
		
			
			public String getResearch_group(){
				return research_group;
			}
			
			public void setResearch_group(String research_group){
				this.research_group=research_group;
			}
		
			
			public String getPosition(){
				return position;
			}
			
			public void setPosition(String position){
				this.position=position;
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
		
			
			public String getType_of_contract(){
				return type_of_contract;
			}
			
			public void setType_of_contract(String type_of_contract){
				this.type_of_contract=type_of_contract;
			}
		
			
			public String getLocation(){
				return location;
			}
			
			public void setLocation(String location){
				this.location=location;
			}
		
			
			public String getEmail(){
				return email;
			}
			
			public void setEmail(String email){
				this.email=email;
			}
		
			
			public String getProfessional_form(){
				return professional_form;
			}
			
			public void setProfessional_form(String professional_form){
				this.professional_form=professional_form;
			}
		
			
			public String getProfessional_mobile(){
				return professional_mobile;
			}
			
			public void setProfessional_mobile(String professional_mobile){
				this.professional_mobile=professional_mobile;
			}
		
			
			public String getPayroll_institution(){
				return payroll_institution;
			}
			
			public void setPayroll_institution(String payroll_institution){
				this.payroll_institution=payroll_institution;
			}
		
		
		
		
		
		}
}