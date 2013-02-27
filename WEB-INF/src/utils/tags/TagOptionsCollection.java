package utils.tags;



import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.util.IteratorAdapter;
import org.apache.struts.taglib.TagUtils;
import org.apache.struts.taglib.html.Constants;
import org.apache.struts.taglib.html.OptionsCollectionTag;
import org.apache.struts.taglib.html.SelectTag;
import org.apache.struts.util.MessageResources;

import utils.userUtils.UserUtils;

/**
 * Custom tag which which is an extension of the OptionsCollectionTag, which introduces a new value to the list, in case that the current value does not exist in the list
 * 
 * @author Automatika - JustInMind SL
 */
public class TagOptionsCollection extends OptionsCollectionTag {
	Set<String> valuesPutted=new HashSet(); 
	
	String emptyValueLabel=null;
	
	String excludeLabel = null;

    // ----------------------------------------------------- Instance Variables



    /**
     * Process the start of this tag.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doStartTag() throws JspException {
    	int result=super.doStartTag();
    	
    	TagSelect selectTag = (TagSelect) pageContext.getAttribute(Constants.SELECT_KEY);
    	if(!Boolean.parseBoolean(selectTag.getRequired()) && (selectTag.newOptionvalue==null || selectTag.newOptionvalue.equals(""))){
    		TagUtils.getInstance().write(pageContext, "<option value=\"\" SELECTED>"+getEmptyValueTranslatedLabel()+"</option>");
    	}
    	else {
    		if(!Boolean.parseBoolean(selectTag.getRequired())){
    			TagUtils.getInstance().write(pageContext, "<option value=\"\">"+getEmptyValueTranslatedLabel()+"</option>");
    		}
    		if(! valuesPutted.contains(selectTag.newOptionvalue)){    		
    			TagUtils.getInstance().write(pageContext, selectTag.newOption);
    		}
    	}
    	return result;
    }
   

    private String getEmptyValueTranslatedLabel() {
    	if(getEmptyValueLabel()==null) return "";
    	HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		Locale l = UserUtils.getCurrentLocale(request);
		ResourceBundle bundle = ResourceBundle.getBundle("MessageResources",l);
		return bundle.getString(getEmptyValueLabel());
	}


	protected void addOption(StringBuffer sb, String label, String value, boolean matched) {
	    if(excludeLabel == null || !excludeLabel.equals(label))
	    {
	        super.addOption(sb, label, value, matched);
	        valuesPutted.add(value);
	    }
    }


	public String getEmptyValueLabel() {
		return emptyValueLabel;
	}


	public void setEmptyValueLabel(String emptyValueLabel) {
		this.emptyValueLabel = emptyValueLabel;
	}


    public String getExcludeLabel() {
        return excludeLabel;
    }


    public void setExcludeLabel(String excludeLabel) {
        this.excludeLabel = excludeLabel;
    }

}
