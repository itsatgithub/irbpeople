package presentation.formbeans.user;

import utils.validator.ValidatorFormAndAction;

/**
 * This class is a formBean used to change the password.
 * 
 * @author Automatika - JustInMind SL
 */
public class ChangePasswordForm extends ValidatorFormAndAction {    
    private String usuariocode;
    private String current;
    private String password;
    private String password2;    
    
    /** 
     * @return returns itself
     */
    public ChangePasswordForm getChangePasswordForm() {
		return this;
	}

    /** 
     * @return the new password
     */
	public String getPassword() {
		return password;
	}

	 /** 
	 * sets the new password
     * @param password string with the password
     */
	public void setPassword(String password) {
		this.password = password;
	}

    /** 
     * @return the copy of the new password
     */
	public String getPassword2() {
		return password2;
	}

	 /** 
	 * sets the copy of the new password
     * @param password2 string with the password
     */
	public void setPassword2(String password2) {
		this.password2 = password2;
	}

    /** 
     * @return the code of the user
     */
	public String getUsuariocode(){
		return usuariocode;
	}

	/** 
	 * sets the code of the user
     * @param usuariocode string with the code
     */
	public void setUsuariocode(String usuariocode) {
		this.usuariocode = usuariocode;
	}

	/** 
     * @return the current password
     */
	public String getCurrent() {
		return current;
	}

	 /** 
	 * sets the current password
     * @param current string with the password
     */
	public void setCurrent(String current) {
		this.current = current;
	}
    


}