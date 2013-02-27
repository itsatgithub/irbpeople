package presentation.formbeans.personalphoto;




import utils.formbeans.FormBeanContainer;
import utils.formbeans.BOAdderFormBean;

import utils.validator.ValidatorFormAndAction;

import presentation.formbeans.objects.*;

/**
 * This class is a FormBeanContainer for the action action_new_new_page_professional.
 * 
 * @author Automatika - JustInMind SL
 */
public class PersonalPhotoContainer_Form extends FormBeanContainer {

	
	private PersonalPhoto_Form personalPhoto_Form;
	private Personal_Form personal_Form = null;
	
	public PersonalPhoto_Form getPersonalPhoto_Form() {
		if(personalPhoto_Form==null) personalPhoto_Form=new PersonalPhoto_Form();
		return personalPhoto_Form;
	}

	public void setPersonalPhoto_Form(PersonalPhoto_Form personalPhoto_Form) {
		this.personalPhoto_Form = personalPhoto_Form;
	}
			
	public String getPersonalPhotocode(){
		return getPersonalPhoto_Form().getPersonalPhotocode();
	}
	
	public void setPersonalPhotocode(String personalPhotoCode){
		getPersonalPhoto_Form().setPersonalPhotocode(personalPhotoCode);
	}
	
	/**
	 * @return the current personal_Form
	 */
	
	public Personal_Form getPersonal_Form() {
		
		if(personal_Form==null)
			
			personal_Form = new Personal_Form();
		
		return personal_Form;
	}

	/**
	 * set the current personal_Form
	 * @param personal_Form the new personal_Form
	 */
	
	public void setPersonal_Form(Personal_Form personal_Form){
		
		this.personal_Form=personal_Form;
	}
	
	/**
	 * @return returns the code of the current personal_Form (a shortcut of getPersonal_Form().getPersonalcode())
	 */
	public String getPersonalcode() {
		
		return getPersonal_Form().getPersonalcode();
	}

	/**
	 * Sets the code of the current personal_Form (a shortcut of getPersonal_Form().setPersonalcode(String))
	 * @param personalcode the new code
	 */
	
	public void setPersonalcode(String personalcode){
		
		getPersonal_Form().setPersonalcode(personalcode);
	}
	
	
	
	
	
	
}