package utils.tags;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.struts.taglib.TagUtils;
import org.apache.struts.taglib.html.SelectTag;

import utils.beanUtils.converters.BooleanConverter;
import utils.userUtils.UserUtils;

/**
 * Custom tag which is used to show an html input (for a form) to chose between yes/no.
 * 
 * @author Automatika - JustInMind SL
 */
public class JimBooleanTagWithSelectable extends SelectTag{

	@Override
	public int doStartTag() throws JspException {
		// TODO Auto-generated method stub
	
		int res=super.doStartTag();
		
		TagUtils.getInstance().write(pageContext, renderOptions());
		
		return res;
	}

	private String renderOptions(){
		String value="";
		 try {
			 value =(String)TagUtils.getInstance().lookup(pageContext, name, property, null);
		} catch (JspException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		Locale l = UserUtils.getCurrentLocale(request);
		ResourceBundle patternsBundle = ResourceBundle.getBundle("Patterns",l);
		String trueEtiq = patternsBundle.getString("trueEtiq");
		String falseEtiq = patternsBundle.getString("falseEtiq");
		
		String result="<option value=\""+trueEtiq+"\""+
			((trueEtiq.equalsIgnoreCase(value))? " SELECTED":"")+
			">"+trueEtiq+"</option>";
		
		result+="<option value=\""+falseEtiq+"\""+
			((falseEtiq.equalsIgnoreCase(value))? " SELECTED":"")+
			">"+falseEtiq+"</option>";
		
		return result;
	}
}
