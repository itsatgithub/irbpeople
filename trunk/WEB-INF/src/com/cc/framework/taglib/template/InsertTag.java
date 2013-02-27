/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/template/InsertTag.java,v 1.11 2005/07/08 14:15:12 P001002 Exp $
 * $Revision: 1.11 $
 * $Date: 2005/07/08 14:15:12 $
 *
 * ====================================================================
 *
 * Copyright (c) 2000 - 2005 SCC Informationssysteme GmbH. All rights
 * reserved.
 * Vendor URL : http://www.scc-gmbh.com
 * Product URL: http://www.common-controls.com
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL SCC INFORMATIONSSYSTEME GMBH OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 */

package com.cc.framework.taglib.template;

import java.util.Hashtable;
import java.util.Stack;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import com.cc.framework.Globals;

/**
 * Tag handler for the <code>insert</code> tag.
 * <p>
 * The tag inserts a template blank in the JSP-Page.
 * The template is a JSP-Page that contains placemarkers for JSP fragments.
 * The JSP-fragments are addressed by means of a logical name.
 *
 * The assignment to physical file names is achieved using <template:put>-tags,
 * which are specified within the <template:insert>-tags. For every placeholder
 * in the template, a corresponding <template:put>-tag can be specified.
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.11 $
 * @since      1.0
 */
public class InsertTag extends TagSupport {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -2575011992812043356L;

	/**
	 * The template
	 */
	private String template;

	/**
	 * Base directory for resources
	 */
	private String base = "";

	/**
	 * The stack
	 */
	private Stack stack;

	/**
	 * Constructor
	 */
	public InsertTag() {
		super();
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException {

		try {
			String resource = TemplateHelp.expandResourceName(base, template);

			pageContext.include(resource);
		} catch (Exception ex) {
			throw new JspException(ex.getMessage());
		}

		stack.pop();

		return EVAL_PAGE;
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException {

		stack = getStack();

		stack.push(new Hashtable());

		return EVAL_BODY_INCLUDE;
	}

	/**
	 * Returns the template stack
	 *
	 * @return	Stack
	 */
	public Stack getStack() {
		Stack stack =
			(Stack) pageContext.getAttribute(
				Globals.TEMPLATE_KEY,
				PageContext.REQUEST_SCOPE);

		if (stack == null) {
			stack = new Stack();

			pageContext.setAttribute(
				Globals.TEMPLATE_KEY,
				stack,
				PageContext.REQUEST_SCOPE);
		}

		return stack;
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#release()
	 */
	public void release() {
		template = null;
		stack = null;
	}

	/**
	 * The base directory for the JSP-pages is determined
	 * with the base attribute. This also has an action on
	 * the included <template:put>-tags.
	 * The base directory can be individually set for every
	 * Painter, as the following example shows:
	 * myPainter:/jspCustom;/jspDef
	 * The example uses the base directory /jspCustom for
	 * those cases where the Painter myPainter is used.
	 * In all other cases, the base directory /jspDef is set.
	 * CAUTION: The pace directory is taken into consideration
	 * only in the case of JSP-files that start with '$'.
	 *
	 * @param	base	The base directory for the JSP-pages
	 */
	public void setBase(String base) {
		this.base = TemplateHelp.resolve(pageContext, base);
	}

	/**
	 * Specifies the physical filename of the JSP-templates file.
	 * The filename is enhanced with the base directory if it starts with $.
	 *
	 * @param	template The template to set
	 */
	public void setTemplate(String template) {
		this.template = template;
	}

	/**
	 * Returns the base directory for the JSP-pages
	 *
	 * @return String
	 */
	public String getBase() {
		return base;
	}
}