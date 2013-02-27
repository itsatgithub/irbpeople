package utils.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.struts.taglib.TagUtils;
import org.apache.struts.taglib.bean.WriteTag;

public class TagState extends WriteTag {

    
    @Override
    public int doStartTag() throws JspException {
        
     // Look up the requested bean (if necessary)
        if (ignore) {
            if (TagUtils.getInstance().lookup(pageContext, name, scope) == null) {
                return (SKIP_BODY); // Nothing to output
            }
        }

        // Look up the requested property value
        Object value = TagUtils.getInstance().lookup(pageContext, name, property, scope);

        if (value == null) {
            return (SKIP_BODY); // Nothing to output
        }

        // Convert value to the String with some formatting
        String output = formatValue(value);

        String style = "";
        
        if(output!=null)
        {
            if(output.equalsIgnoreCase("active"))
            {
                style = "color:lightgreen; font-weight: bold";
            }
            else if(output.equalsIgnoreCase("inactive"))
            {
                style = "color:red; font-weight: bold";
            }
            else
            {
                style = "color:black";
            }
            
            output= output.substring(0, 1).toUpperCase() + output.substring(1);
            
        }
        
        JspWriter out = pageContext.getOut();
        
        try {
            out.write("<span style=\"" + style + "\">");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        // Print this property value to our output writer, suitably filtered
        if (filter) {
            TagUtils.getInstance().write(pageContext, TagUtils.getInstance().filter(output));
        } else {
            TagUtils.getInstance().write(pageContext, output);
        }

        // Continue processing this page
        return (SKIP_BODY);
        
    }

    @Override
    public int doEndTag() throws JspException {
        JspWriter out = pageContext.getOut();
        
        try {
            out.write("</span>");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return SKIP_BODY;
    }

    

    

}
