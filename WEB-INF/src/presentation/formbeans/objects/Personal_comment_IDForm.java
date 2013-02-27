package presentation.formbeans.objects;

import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-form-bean of child
 * 
 * @author Automatika - JustInMind SL
 */
public class Personal_comment_IDForm extends ValidatorFormAndAction {

    private static final long serialVersionUID = 3345426296407025407L;

    private String personal_commentcode;

    private String version;

    private String input_date;

    private String text;

    private String author;

    private String tabindex;

    public Personal_comment_IDForm getPersonal_comment() {
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