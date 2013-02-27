package utils.tags;

import java.io.IOException;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.struts.taglib.TagUtils;
import org.apache.struts.taglib.html.HiddenTag;
import org.apache.struts.taglib.html.SelectTag;

import utils.beanUtils.converters.BooleanConverter;
import utils.userUtils.UserUtils;

/**
 * Custom tag which is used to show an html input (for a form) to chose between yes/no.
 * 
 * @author Automatika - JustInMind SL
 */
public class JimBooleanTag extends HiddenTag{
	

	@Override
	public int doStartTag() throws JspException {
		int res=super.doStartTag();
		TagUtils.getInstance().write(pageContext, renderCkeckBox());
		
		return res;
	}

	private String renderCkeckBox() throws JspException{
		
        // Calculate the value to be rendered separately
        String results = null;
        if (value != null) {
            results = TagUtils.getInstance().filter(value);
        } else {
            Object value = TagUtils.getInstance().lookup(pageContext, name, property,
                                               null);
            if (value == null) {
                results = "";
            } else {
                results = TagUtils.getInstance().filter(value.toString());
            }
        }
        
		
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		Locale l = UserUtils.getCurrentLocale(request);
		ResourceBundle patternsBundle = ResourceBundle.getBundle("Patterns",l);
		String trueEtiq = patternsBundle.getString("trueEtiq");
		String falseEtiq = patternsBundle.getString("falseEtiq");
		
		String checked=(results.equalsIgnoreCase(trueEtiq))?"CHECKED":"";
		String disabled=(getDisabled())?"DISABLED":"";
		String onclick=(getDisabled())?"":"onclick=\"changeValue(this.form['"+prepareName()+"'], this.checked, '"+trueEtiq+"', '"+falseEtiq+"')\"";

		String result="<input type=\"checkbox\" "+checked+" "+ disabled+" "+onclick+"/>";
		
		
		return result;
	}
}
