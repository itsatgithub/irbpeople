package utils.tags;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.apache.struts.taglib.nested.NestedNameSupport;
import org.apache.struts.taglib.nested.NestedPropertyHelper;
import org.apache.struts.taglib.nested.bean.NestedWriteTag;

public class NestedWriteIRBState extends TagState implements NestedNameSupport {

	

    /**
     * Overriding method of the heart of the matter. Gets the relative property
     * and leaves the rest up to the original tag implementation. Sweet.
     * @return int JSP continuation directive.
     *             This is in the hands of the super class.
     */
    public int doStartTag() throws JspException {
      // get the original properties
      originalName = getName();
      originalProperty = getProperty();

      // request
      HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
      // set the properties
      NestedPropertyHelper.setNestedProperties(request, this);

      // let the super do it's thing
      return super.doStartTag();
    }

    /**
     * Complete the processing of the tag. The nested tags here will restore
     * all the original value for the tag itself and the nesting context.
     * @return int to describe the next step for the JSP processor
     * @throws JspException for the bad things JSP's do
     */
    public int doEndTag() throws JspException {
      // do the super's ending part
      int i = super.doEndTag();

      // reset the properties
      setName(originalName);
      setProperty(originalProperty);

      // continue
      return i;
    }

    /**
     * Release the tag's resources and reset the values.
     */
    public void release() {
      super.release();
      // reset the originals
      originalName = null;
      originalProperty = null;
    }

    /* the usual private member variables */
    private String originalName = null;
    private String originalProperty = null;

    

	
}
