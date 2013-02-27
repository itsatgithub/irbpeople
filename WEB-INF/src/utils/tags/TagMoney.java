package utils.tags;

import java.util.ResourceBundle;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

/**
 * Custom tag which is used to show an html input (for a form) to chose a money value.
 * 
 * @author Automatika - JustInMind SL
 */
public class TagMoney extends TagFormattedNumber {
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
		return i;
	}

	protected void render(JspWriter out, ResourceBundle apps) throws Exception {		
		out.println("<img src=\"../images/CATEGORY/action-type/money.gif\" />");
	}

}
