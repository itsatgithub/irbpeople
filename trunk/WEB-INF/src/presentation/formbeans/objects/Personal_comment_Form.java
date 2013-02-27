package presentation.formbeans.objects;

import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-form-bean of child
 * 
 * @author Automatika - JustInMind SL
 */
public class Personal_comment_Form extends ValidatorFormAndAction {

    private static final long serialVersionUID = -8421142561244108175L;

    private String personal_commentcode;

    private String version;

    private String input_date;

    private String text;

    private String author;

    private String tabindex;

    private Personal_IDForm personal = null;

    public Personal_comment_Form getPersonal_comment() {
        return this;
    }

    public String getPersonal_commentcode() {
        return personal_commentcode;
    }

    public void setPersonal_commentcode(String personal_commentcode) {
        this.personal_commentcode = personal_commentcode;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {

        if (getPersonal_commentcode() == null || getPersonal_commentcode().equals(""))
            return "";

        String result = this.getAuthor() + " - " + this.getInput_date() + " - " + getText() + "";
        return (result != null) ? result : "";

    }

    public String get_descripcion() {
        return this.toString();
    }

    public Personal_IDForm getPersonal() {
        if (personal == null)
            personal = new Personal_IDForm();
        return personal;
    }

    public void setPersonal(Personal_IDForm personal) {
        this.personal = personal;
    }

    public String getInput_date() {
        return input_date;
    }

    public void setInput_date(String input_date) {
        this.input_date = input_date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTabindex() {
        return tabindex;
    }

    public void setTabindex(String tabindex) {
        this.tabindex = tabindex;
    }

}