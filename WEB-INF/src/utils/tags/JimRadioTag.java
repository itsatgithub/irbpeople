package utils.tags;

import javax.servlet.jsp.JspException;

import org.apache.struts.taglib.TagUtils;
import org.apache.struts.taglib.html.RadioTag;

/**
 * Custom tag which is used to show an html input (for a form) of type radio. Differently to the struts tag, this one has the property checked.
 * 
 * @author Automatika - JustInMind SL
 */
public class JimRadioTag extends RadioTag{
	
	String checked;

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

    /**
     * Copied function from RadioTag.
     * This function has been overwrtten in ordet to overwritte the private function currentValue.
     */
    public int doStartTag() throws JspException {

        String radioTag = renderRadioElement(serverValue(), currentValue());

        TagUtils.getInstance().write(pageContext, radioTag);

        this.text = null;
        return (EVAL_BODY_TAG);
    }
    
    /**
     * copied function from RadioTag
     */
    private String serverValue() throws JspException {
        // Not using indexed radio buttons
        if (this.idName == null) {
            return this.value;
        }
        String serverValue = this.lookupProperty(this.idName, this.value);
        return (serverValue == null) ? "" : serverValue;
    }

    /**
     * If the property checked is not set, it looks the porperty as in the original 
     * tag (Acquire the current value of the bean specified by the <code>name</code> 
     * attribute and the property specified by the <code>property</code> attribute.
     * This radio button with this value will be checked.)
     * @throws JspException
     */
    private String currentValue() throws JspException {
    	if(checked!=null){
    		if(Boolean.parseBoolean(getChecked())) return serverValue();
    		else return "";
    	}
    	
        String current = this.lookupProperty(this.name, this.property);
        return (current == null) ? "" : current;
    }

}
