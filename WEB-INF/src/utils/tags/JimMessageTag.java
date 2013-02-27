package utils.tags;

import java.util.ResourceBundle;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.struts.taglib.TagUtils;
import org.apache.struts.taglib.bean.MessageTag;

import utils.jsp.JspUtils;

/**
 * Custom tag equals to MessageTag but that quotes the resulting text to html form.
 * 
 * @author Automatika - JustInMind SL
 */
public class JimMessageTag extends MessageTag {
	
	/** Indicates whereas the breaking spaces should be transformed to <br>**/
	String translateBrs="true";	
    /**
     * Process the start tag.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doStartTag() throws JspException {

    	//This method is a copy of MessageTag.doStartTag() except form one line (in the end of the function)
        String key = this.key;
        if (key == null) {
            // Look up the requested property value
            Object value = TagUtils.getInstance().lookup(pageContext, name, property, scope);
            if (value != null && !(value instanceof String)) {
                JspException e =
                    new JspException(messages.getMessage("message.property", key));
               TagUtils.getInstance().saveException(pageContext, e);
                throw e;
            }
            key = (String) value;
        }

        // Construct the optional arguments array we will be using
        Object args[] = new Object[] { arg0, arg1, arg2, arg3, arg4 };

        // Retrieve the message string we are looking for
        String message =
            TagUtils.getInstance().message(
                pageContext,
                this.bundle,
                this.localeKey,
                key,
                args);
                
        if (message == null) {
            JspException e =
                new JspException(
                    messages.getMessage("message.message", "\"" + key + "\""));
            TagUtils.getInstance().saveException(pageContext, e);
            throw e;
        }

        //----This line has been modified to add the quote method----
        TagUtils.getInstance().write(pageContext, JspUtils.quote(message, Boolean.parseBoolean(translateBrs)));

        return (SKIP_BODY);

    }
	public String getTranslateBrs() {
		return translateBrs;
	}
	public void setTranslateBrs(String translateBrs) {
		this.translateBrs = translateBrs;
	}
}
