package utils.tags;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.apache.struts.taglib.TagUtils;
import org.apache.struts.taglib.nested.NestedPropertyHelper;
import org.apache.struts.taglib.nested.html.NestedTextareaTag;

import utils.userUtils.UserUtils;

public class NestedTextAreaIRB extends NestedTextareaTag {

	@Override
	public int doStartTag() throws JspException {
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
		if(UserUtils.isRRHH(request)) {
			this.setReadonly(false);
		}
		return super.doStartTag();
	}

	@Override
	public void release() {
		// TODO Auto-generated method stub
		super.release();
	}

}
