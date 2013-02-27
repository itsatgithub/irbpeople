package presentation.formbeans.oPersonalSys;

import presentation.formbeans.objects.Personal_comment_Form;
import utils.formbeans.FormBeanContainer;

/**
 * This class is a FormBeanContainer for the action
 * action_modify_page_personal_comment.
 * 
 * @author Automatika - JustInMind SL
 */
public class Action_modify_page_personal_comment_Form extends FormBeanContainer {

    // FormBean for the action

    /**
     * nested-pojo-form-bean contained in this container
     **/
    private Personal_comment_Form personal_comment_Form = null;

    /**
     * @return returns the code of the current personal_comment_Form (a shortcut of
     *         getPersonal_comment_Form().getPersonal_commentcode())
     */
    public String getPersonal_commentcode() {

        return getPersonal_comment_Form().getPersonal_commentcode();
    }

    /**
     * Sets the code of the current personal_comment_Form (a shortcut of
     * getPersonal_comment_Form().setPersonal_commentcode(string))
     * 
     * @param personal_commentcode
     *            the new code
     */

    public void setPersonal_commentcode(String personal_commentcode) {

        getPersonal_comment_Form().setPersonal_commentcode(personal_commentcode);
    }

    /*
     * (non-Javadoc)
     * 
     * @see utils.formbeans.FormBeanContainer#getMainFormBeanCode()
     */
    @Override
    public String getMainFormBeanCode() {
        return getPersonal_commentcode();
    }

    public Personal_comment_Form getPersonal_comment_Form() {
        if(personal_comment_Form == null)
        {
            personal_comment_Form = new Personal_comment_Form();
        }
        return personal_comment_Form;
    }

    public void setPersonal_comment_Form(Personal_comment_Form personal_comment_Form) {
        this.personal_comment_Form = personal_comment_Form;
    }

}