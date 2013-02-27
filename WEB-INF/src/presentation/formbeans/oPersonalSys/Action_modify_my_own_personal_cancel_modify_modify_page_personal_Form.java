package presentation.formbeans.oPersonalSys;




import utils.formbeans.FormBeanContainer;
import utils.formbeans.BOAdderFormBean;

import utils.validator.ValidatorFormAndAction;

import presentation.formbeans.objects.*;

/**
 * This class is a FormBeanContainer for the action action_modify_my_own_personal_cancel_modify_modify_page_personal.
 * 
 * @author Automatika - JustInMind SL
 */
public class Action_modify_my_own_personal_cancel_modify_modify_page_personal_Form extends FormBeanContainer {

	
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
		
		
		
		
		
		}
}