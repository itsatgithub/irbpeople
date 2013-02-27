package utils.tags;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.jsp.JspException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.taglib.TagUtils;
import org.apache.struts.taglib.html.MultiboxTag;
import org.apache.struts.taglib.html.RadioTag;

/**
 * Custom tag which is used to show an html input (for a form) of type multibox. Differently to the struts tag, this one has the property checked.
 * 
 * @author Automatika - JustInMind SL
 */
public class JimMultiboxTag extends MultiboxTag{
	
	String checked;

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

   
    
    protected void prepareChecked(StringBuffer results, String value) throws JspException {

    	if(checked!=null){
    		super.prepareChecked(results, value);
    	}
    	else {
    		if(Boolean.parseBoolean(checked)){
    			results.append(" checked=\"checked\"");
    		}
    	}
    }

}
