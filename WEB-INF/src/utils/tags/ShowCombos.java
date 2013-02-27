package utils.tags;

import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * This class is a CustomTag which prints out a table with 2 select boxes
 * where using some buttons and javascript functions defined in formUtils.js
 * you can move from one combo to another.
 * 
 * @author JustInMind - Guillem Corominas
 */
public class ShowCombos extends TagSupport{

	private String prefix = "";
    
     /**
   	 * Default constructor
     *
     */
	public ShowCombos()
	{
		//actualObj = null;
	}

	/**
	 * This is the manager method for the ShowCombos Tag, and it generates
	 * a table structure with the 2 comboboxes.
	 * IMPORTANT:
	 * You can fill the comboboxes by introducing in the request up to 4 Vectors
	 * containing String parameters:
	 *  -leftFieldNames - Vector of value fields
	 *  -leftLabels - Vector of label fields
	 *  -rightFieldNames - Vector of value fields
	 *  -rightLabels - Vector of label fields
	 */
	
	public int doStartTag() throws JspException
    {
		JspWriter out = pageContext.getOut();

    	try
        {
    		Iterator itNames;	
    		Iterator itLabels;
			Vector leftNames = (Vector) pageContext.getRequest().getAttribute(prefix+"leftFieldNames");
			Vector leftLabels = (Vector) pageContext.getRequest().getAttribute(prefix+"leftLabels");
			Vector rightNames = (Vector) pageContext.getRequest().getAttribute(prefix+"rightFieldNames");
			Vector rightLabels = (Vector) pageContext.getRequest().getAttribute(prefix+"rightLabels");
			
			//Creating the table bgcolor=\"#DDDDDD\"
			out.println("<table align=\"center\" border=\"0\" cellpadding=15 cellspacing=0 >");
			out.println("<tr><td width=\"39%\" align=\"center\">");
			
			//Creating the first Select Box
			out.println("<select name=\""+prefix+"allfields\" size=\"15\" multiple style=\"width: 200px\">");
			if (leftNames != null) {
				itNames = leftNames.iterator();
				itLabels = leftLabels.iterator();
				while (itNames.hasNext()) {
					out.println("<option value=\""+ (String) itNames.next()+"\">"+ (String) itLabels.next()+"</option>");
				}
			}
			out.println("</select></td>");
			
			//Creating the move buttons
			out.println("<td width=\"20%\" align=\"center\">");
			out.println("<input type=\"button\" value=\"  >>  \" onClick=\"copyToList("+prefix+"allfields, "+prefix+"selectedfields)\">");	
			out.println("<br><br><br>");		
			out.println("<input type=\"button\" value=\"  <<  \" onClick=\"copyToList("+prefix+"selectedfields, "+prefix+"allfields)\"></td>");			
			
			//Creating the second Select Box
			out.println("<td width=\"39%\" align=\"center\">");
			out.println("<select name=\""+prefix+"selectedfields\" size=\"15\" multiple style=\"width: 200px\">");
			if (rightNames != null) {
				itNames = rightNames.iterator();
				itLabels = rightLabels.iterator();
				while (itNames.hasNext()) {
					out.println("<option value=\""+ (String) itNames.next()+"\">"+ (String) itLabels.next()+"</option>");
				}
			}
			out.println("</select></td>");	
					
			//Creating the order buttons
			out.println("<td width=\"2%\" align=\"left\" >");		
			out.println("<input type=\"button\" value=\"  up  \" onClick=\"moveUp("+prefix+"selectedfields)\">");	
			out.println("<br><br><br>");		
			out.println("<input type=\"button\" value=\"down\" onClick=\"moveDown("+prefix+"selectedfields)\">");
			
			//Closing the table
			out.println("</td><tr></table>");		

        }
        catch(IOException e)
        {
            throw new JspException(e.getMessage());
        }
          
        // This tag has no body
        return SKIP_BODY;
    }

	/**
	 * Method used internally to get the prefix introduced in the customTag header
	 * @return the prefix introduced
	 */
	public String getPrefix() {
		return prefix;
	}

	/**
	 * Sets a prefix: Required if using 2 or more times this tag in the 
	 * same jsp to make a name diference in the inputs
	 * @param prefix - the prefix for the inputnames
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	
}
