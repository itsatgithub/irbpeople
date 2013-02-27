package presentation.formbeans.oPersonalSys;

import utils.formbeans.FormBeanContainer;
import utils.formbeans.BOAdderFormBean;

import utils.validator.ValidatorFormAndAction;

import presentation.formbeans.objects.*;

/**
 * This class is a FormBeanContainer for the action
 * action_manage_personal_funding_details.
 * 
 * @author Automatika - JustInMind SL
 */
public class Action_manage_personal_funding_details_Form extends FormBeanContainer implements
        Personal_comments_container {

    // FormBean for the action

    /**
     * nested-pojo-form-bean contained in this container
     **/
    private Personal_Form personal_Form = null;

    private BOAdderFormBean<Personal_comment_Form> personal_comments;

    /**
     * @return returns the code of the current personal_Form (a shortcut of
     *         getPersonal_Form().getPersonalcode())
     */
    public String getPersonalcode() {

        return getPersonal_Form().getPersonalcode();
    }

    /**
     * Sets the code of the current personal_Form (a shortcut of
     * getPersonal_Form().setPersonalcode(String))
     * 
     * @param personalcode
     *            the new code
     */

    public void setPersonalcode(String personalcode) {

        getPersonal_Form().setPersonalcode(personalcode);
    }

    /**
     * @return the current personal_Form
     */

    public Personal_Form getPersonal_Form() {

        if (personal_Form == null)

            personal_Form = new Personal_Form();

        return personal_Form;
    }

    /**
     * set the current personal_Form
     * 
     * @param personal_Form
     *            the new personal_Form
     */

    public void setPersonal_Form(Personal_Form personal_Form) {

        this.personal_Form = personal_Form;
    }

    /*
     * (non-Javadoc)
     * 
     * @see utils.formbeans.FormBeanContainer#getMainFormBeanCode()
     */
    @Override
    public String getMainFormBeanCode() {
        return getPersonalcode();
    }

    public BOAdderFormBean<Personal_comment_Form> getPersonal_comments() {
        if (personal_comments == null) {
            personal_comments = new BOAdderFormBean<Personal_comment_Form>(Personal_comment_Form.class);
        }
        return personal_comments;
    }

    public void setPersonal_comments(BOAdderFormBean<Personal_comment_Form> personal_comments) {
        this.personal_comments = personal_comments;
    }

}