package presentation.formbeans.oCategorySys;




import utils.formbeans.FormBeanContainer;
import utils.formbeans.BOAdderFormBean;

import utils.validator.ValidatorFormAndAction;

import presentation.formbeans.objects.*;

/**
 * This class is a FormBeanContainer for the action action_modify_page_category.
 * 
 * @author Automatika - JustInMind SL
 */
public class Action_modify_page_category_Form extends FormBeanContainer {

	
	//FormBean for the action
			
			/**
			* nested-pojo-form-bean contained in this container
			**/
			private Category_Form category_Form = null;
	
	
	

	
	
	
		
		/**
		 * @return returns the code of the current category_Form (a shortcut of getCategory_Form().getCategorycode())
		 */
		public String getCategorycode() {
			
			return getCategory_Form().getCategorycode();
		}

		/**
		 * Sets the code of the current category_Form (a shortcut of getCategory_Form().setCategorycode(String))
		 * @param categorycode the new code
		 */
		
		public void setCategorycode(String categorycode){
			
			getCategory_Form().setCategorycode(categorycode);
		}

		/**
		 * @return the current category_Form
		 */
		
		public Category_Form getCategory_Form() {
			
			if(category_Form==null)
				
				category_Form = new Category_Form();
			
			return category_Form;
		}

		/**
		 * set the current category_Form
		 * @param category_Form the new category_Form
		 */
		
		public void setCategory_Form(Category_Form category_Form){
			
			this.category_Form=category_Form;
		}
		
		
			/* (non-Javadoc)
			 * @see utils.formbeans.FormBeanContainer#getMainFormBeanCode()
			 */
			@Override
			public String getMainFormBeanCode() {
				return getCategorycode();
			}
			
			
			
	
	
	
	
	
	
}