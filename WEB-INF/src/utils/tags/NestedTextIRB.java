package utils.tags;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.apache.struts.taglib.TagUtils;
import org.apache.struts.taglib.nested.NestedNameSupport;
import org.apache.struts.taglib.nested.NestedPropertyHelper;
import org.apache.struts.taglib.nested.html.NestedTextTag;

import utils.userUtils.UserUtils;

public class NestedTextIRB extends NestedTextTag implements NestedNameSupport {

	boolean readonly;
	@Override
	public int doEndTag() throws JspException {
		if(readonly){
			return SKIP_BODY;
		}
		return super.doEndTag();
	}

	@Override
	public int doStartTag() throws JspException {
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
		readonly = !(UserUtils.isRRHH(request) || UserUtils.isAlumni(request));
		if (readonly) {
			Object bean = TagUtils.getInstance().lookup(pageContext, this.getName(), 
					NestedPropertyHelper.getAdjustedProperty(request, this.getProperty()), null);
			TagUtils.getInstance().write(pageContext, bean!=null ? bean.toString(): "");
			return SKIP_BODY;
		}
		return super.doStartTag();
	}

	@Override
	public void release() {
		// TODO Auto-generated method stub
		super.release();
	}

	
}
