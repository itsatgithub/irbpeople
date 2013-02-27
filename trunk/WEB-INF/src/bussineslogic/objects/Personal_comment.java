package bussineslogic.objects;

import java.util.Date;

import utils.Persistent;

/**
 * This class contains business object 'professional'.
 * 
 * @author Automatika - JustInMind SL
 * 
 */
public class Personal_comment implements Persistent {

    // Code (primary key) of this professional
    private String personal_commentcode;

    // Version of the current instance. This attribute is used by hibernate to
    // control concurrent modifications.
    private int version;

    // Indicates whereas this professional is deleted
    private boolean deleted = false;

    // definition of the specific attributes

    private Date input_date;

    private String text;

    private String author;

    private int tabindex;

    private Personal personal;

    /**
     * Default Constructor which creates an empty professional
     */
    public Personal_comment() {
    }

    /**
     * Returns the version of the current instance. This attribute is used by
     * hibernate to control concurrent modifications.
     * 
     * @return int with the version number.
     */
    public int getVersion() {
        return version;
    }

    /**
     * Set the version of the current instance. This attribute is used by
     * hibernate to control concurrent modifications.
     * 
     * @param version
     *            int with the version number.
     */
    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * Tests if this professional has been deleted
     * 
     * @return true if this professional has been deleted
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * Sets the delete state of this professional
     * 
     * @param deleted
     *            true if the object is deleted.
     */
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * @return the input_date
     */
    public Date getInput_date() {
        return input_date;
    }

    /**
     * @param input_date
     *            the input_date to set.
     */
    public void setInput_date(Date input_date) {
        this.input_date = input_date;
    }

    /**
     * @return the professional_personal
     */
    public Personal getPersonal() {
        return personal;
    }

    /**
     * @param professional_personal
     *            the professional_personal to set.
     */
    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    @Override
    public String toString() {
        if (getCode() == null || getCode().equals(""))
            return "";

        return getCode();
    }

    public String getPersonal_commentcode() {
        return personal_commentcode;
    }

    public void setPersonal_commentcode(String personal_commentcode) {
        this.personal_commentcode = personal_commentcode;
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

    public int getTabindex() {
        return tabindex;
    }

    public void setTabindex(int tabindex) {
        this.tabindex = tabindex;
    }

    public String getCode() {
        return personal_commentcode;
    }

    public void setCode(String code) {
        this.personal_commentcode = code;
    }

}