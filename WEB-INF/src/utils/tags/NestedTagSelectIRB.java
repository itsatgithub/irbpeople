package utils.tags;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.apache.struts.taglib.TagUtils;
import org.apache.struts.taglib.nested.NestedPropertyHelper;

import utils.userUtils.UserUtils;

public class NestedTagSelectIRB extends NestedTagSelect {

	boolean readonly;

	@Override
	public int doEndTag() throws JspException {
		if (readonly) {
			return SKIP_BODY;
		}
		return super.doEndTag();
	}

	@Override
	public int doStartTag() throws JspException {
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		TagUtils.getInstance().lookup(pageContext, "gender", null);
		readonly = !(UserUtils.isRRHH(request) || UserUtils.isAlumni(request));
		if (readonly) {
			Object bean = TagUtils.getInstance().lookup(
					pageContext,
					this.getName(),
					NestedPropertyHelper.getCurrentProperty(request) + "."
							+ this.getProperty().substring(0, this.getProperty().indexOf('.') > 0 ? this.getProperty().indexOf('.') : this.getProperty().length()), null);
			TagUtils.getInstance().write(pageContext, bean != null ? bean.toString() : "");
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
