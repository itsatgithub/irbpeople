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
public class Action_Generar_nuevo_password_Form extends FormBeanContainer {

	
	//FormBean for the action
	
	
	UsuarioForm usuario_form;

	public UsuarioForm getUsuario_form() {
		if(usuario_form==null){
			usuario_form=new UsuarioForm();
		}
		return usuario_form;
	}

	public void setUsuario_form(UsuarioForm usuario_form) {
		this.usuario_form = usuario_form;
	}
	
	
	
	
	
	
	
	
	
}