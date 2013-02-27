package utils.tags;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.struts.taglib.html.TextTag;

import utils.userUtils.UserUtils;

/**
 * Custom tag which is used to show an html input (for a form) to chose a formated number.
 * 
 * @author Automatika - JustInMind SL
 */
public abstract class TagFormattedNumber extends TextTag {
	/**
	 * Complete the processing of the tag. The nested tags here will restore all
	 * the original value for the tag itself and the nesting context.
	 * 
	 * @return int to describe the next step for the JSP processor
	 * @throws JspException
	 *             for the bad things JSP's do
	 */
	public int doEndTag() throws JspException {
		int i = super.doEndTag();
		/**
		 * Get date pattern from properties file.
		 */
		HttpServletRequest request = (HttpServletRequest) pageContext
				.getRequest();
		Locale l = UserUtils.getCurrentLocale(request);
		ResourceBundle apps = ResourceBundle.getBundle("Patterns", l);

		JspWriter out = pageContext.getOut();
		try {
			render(out, apps);
		} catch (Exception e) {
			throw new JspException(e.getMessage());
		}

		return i;
	}

	protected abstract void render(JspWriter out, ResourceBundle apps) throws Exception;

}
