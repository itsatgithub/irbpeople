package presentation.formbeans.user;

import utils.formbeans.FormBeanContainer;
import presentation.formbeans.objects.UsuarioForm;

/**
 * Container formBean which contains, a Nested-pojo-form-bean of a Usuario
 * 
 * @author Automatika - JustInMind SL
 */
public class UsuarioContainerForm extends FormBeanContainer{
	UsuarioForm usuarioForm=null;
	
	ChangePasswordForm changePasswordForm;
	
	public String getUsuariocode(){
		return getUsuarioForm().getUsuariocode();
	}
	
	public void setUsuariocode(String usuariocode){
		getUsuarioForm().setUsuariocode(usuariocode);
	}

	public UsuarioForm getUsuarioForm() {
		if(usuarioForm==null)
			usuarioForm=new UsuarioForm();
		return usuarioForm;
	}

	public void setUsuarioForm(UsuarioForm usuarioForm) {
		this.usuarioForm = usuarioForm;
	}

	public ChangePasswordForm getChangePasswordForm() {
		if(changePasswordForm==null)
			changePasswordForm=new ChangePasswordForm();
		return changePasswordForm;
	}

	public void setChangePasswordForm(ChangePasswordForm changePasswordForm) {
		this.changePasswordForm = changePasswordForm;
	}
}