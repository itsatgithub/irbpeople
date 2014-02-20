package presentation.formbeans.oUser_accessSys;

import presentation.formbeans.objects.User_access_Form;
import utils.formbeans.FormBeanContainer;

public class Action_unlock_modify_modify_page_user_access_Form extends FormBeanContainer {

	// FormBean for the action

	private User_access_Form user_access_Form = null;

	public String getUsercode() {
		return getUser_access_Form().getUsercode();
	}

	public void setUsercode(String code) {
		getUser_access_Form().setUsercode(code);
	}

	public User_access_Form getUser_access_Form() {

		if (user_access_Form == null)

			user_access_Form = new User_access_Form();

		return user_access_Form;
	}

	public String getUser_accesscode() {
		return getUser_access_Form().getUsercode();
	}

	public void setUser_accesscode(String code) {
		getUser_access_Form().setUsercode(code);
	}

	public void setUser_access_Form(User_access_Form user_access_Form) {

		this.user_access_Form = user_access_Form;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see utils.formbeans.FormBeanContainer#getMainFormBeanCode()
	 */
	@Override
	public String getMainFormBeanCode() {
		return getUsercode();
	}

}