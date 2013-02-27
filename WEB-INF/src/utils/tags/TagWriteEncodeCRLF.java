package utils.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.struts.taglib.TagUtils;
import org.apache.struts.taglib.bean.WriteTag;

public class TagWriteEncodeCRLF extends WriteTag {

    
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
        
        
        
        JspWriter out = pageContext.getOut();
        
        
        
        // Print this property value to our output writer, suitably filtered
        if (filter) {
            TagUtils.getInstance().write(pageContext, TagUtils.getInstance().filter(output).replace("\r\n", "<br>"));
        } else {
            TagUtils.getInstance().write(pageContext, output.replace("\r\n", "<br>"));
        }

        // Continue processing this page
        return (SKIP_BODY);
        
    }

    

    

    

}
