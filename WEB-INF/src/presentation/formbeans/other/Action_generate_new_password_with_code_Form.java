package presentation.formbeans.other;




import utils.formbeans.FormBeanContainer;
import utils.formbeans.BOAdderFormBean;

import utils.validator.ValidatorFormAndAction;

import presentation.formbeans.objects.*;

/**
 * This class is a FormBeanContainer for the action action_Generar_nuevo_password.
 * 
 * @author Automatika - JustInMind SL
 */
public class Action_generate_new_password_with_code_Form extends FormBeanContainer {

	
	String usuariocode;
	String passwordGenerationActivationCode;
	
	public String getPasswordGenerationActivationCode() {
		return passwordGenerationActivationCode;
	}
	public void setPasswordGenerationActivationCode(String passwordGenerationActivationCode) {
		this.passwordGenerationActivationCode = passwordGenerationActivationCode;
	}
	public String getUsuariocode() {
		return usuariocode;
	}
	public void setUsuariocode(String usuariocode) {
		this.usuariocode = usuariocode;
	}
	
	
	
	
	
	
	
	
	
}