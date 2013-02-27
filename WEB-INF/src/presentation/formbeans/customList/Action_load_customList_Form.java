package presentation.formbeans.customList;

import presentation.formbeans.objects.CustomList_Form;
import utils.formbeans.FormBeanContainer;

public class Action_load_customList_Form extends FormBeanContainer {

    private CustomList_Form customList_Form = null;
    private String listdate;

    /**
     * @return returns the code of the current customList_Form (a shortcut of
     *         getCustomList_Form().getCustomListcode())
     */
    public String getCustomListcode() {

	return getCustomList_Form().getCustomListcode();
    }

    /**
     * Sets the code of the current customList_Form (a shortcut of
     * getCustomList_Form().setCustomListcode(String))
     * 
     * @param customListcode
     *            the new code
     */

    public void setCustomListcode(String customListcode) {

	getCustomList_Form().setCustomListcode(customListcode);
    }

    /**
     * @return the current customList_Form
     */

    public CustomList_Form getCustomList_Form() {

	if (customList_Form == null)

	    customList_Form = new CustomList_Form();

	return customList_Form;
    }

    /**
     * set the current customList_Form
     * 
     * @param customList_Form
     *            the new customList_Form
     */

    public void setCustomList_Form(CustomList_Form customList_Form) {

	this.customList_Form = customList_Form;
    }

    /*
     * (non-Javadoc)
     * 
     * @see utils.formbeans.FormBeanContainer#getMainFormBeanCode()
     */
    @Override
    public String getMainFormBeanCode() {
	return getCustomListcode();
    }

    public void setListdate(String listdate) {
	this.listdate = listdate;
    }

    public String getListdate() {
	return listdate;
    }
}
