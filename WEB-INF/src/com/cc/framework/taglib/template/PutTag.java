/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/template/PutTag.java,v 1.14 2005/08/02 19:13:06 P001002 Exp $
 * $Revision: 1.14 $
 * $Date: 2005/08/02 19:13:06 $
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
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.cc.framework.taglib.InnerTag;
import com.cc.framework.taglib.TagHelp;

/**
 * Tag handler for the <code>put</code> tag.
 * Inserts a JSP-fragment in a template.
 * The fragment can be specified either as an external JSP-file or directly in the tag-body.
 * The tag may only be used within a <template:insert>-tag.
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version	   $Revision: 1.14 $
 * @since      1.0
 */
public class PutTag extends BodyTagSupport implements InnerTag {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1411775649056418716L;

	/**
	 * Field name
	 */
	private String name;

	/**
	 * The Content
	 */
	private String content;

	/**
	 * Output the content literal or as Web Resource
	 */
	private boolean direct = false;

	/**
	 * Constructor
	 */
	public PutTag() {
		super();
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#setPageContext(javax.servlet.jsp.PageContext)
	 */
	public void setPageContext(PageContext arg0) {
		super.setPageContext(arg0);

		name = null;
		content = null;
		direct = false;
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#release()
	 */
	public void release() {
		name = null;
		content = null;
		direct = false;
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException {
		return EVAL_BODY_BUFFERED;
	}

	/**
	 * Save the associated text from the body content (if any).
	 *
	 * @return		int
	 * @exception	JspException if a JSP exception has occurred
	 */
	public int doAfterBody() throws JspException {

		if (bodyContent != null) {
			String value = bodyContent.getString().trim();
			if (value.length() > 0) {
				direct = true;
				content = value;
			}
		}

		return SKIP_BODY;
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException {

		InsertTag parent = (InsertTag) findAncestorWithClass(this, InsertTag.class);

		if (parent == null) {
			throw new JspException("PutTag.doStartTag(): No InsertTag ancestor");
		}

		Stack templateStack = parent.getStack();

		if (templateStack == null) {
			throw new JspException("PutTag: no template stack");
		}

		Hashtable params = (Hashtable) templateStack.peek();

		if (params == null) {
			throw new JspException("PutTag: no hashtable");
		}

		params.put(name, new PageParameter(parent.getBase(), content, direct));

		return EVAL_PAGE;
	}

	/**
	 * Specifies the content of the JSP-fragment.
	 * The HTML-code can also be alternatively
	 * specified diretly in the tag-body.
	 * The direct attribute determines whether the
	 * contents involve a literal or a reference to
	 * an external file.
	 * A filename is enhanced with the base directory
	 * if it starts with $.
	 * Note: If the content-attribute is specified,
	 * then the tag-body must be empty.
	 *
	 * @param	content	Content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * This attribute defines whether the contents
	 * should be interpreted as a literal or as a
	 * pointer to an external file
	 *
	 * @param		direct Direct flag
	 * @throws		JspException If the Argument can't be
	 * 				converted to boolean
	 */
	public void setDirect(String direct) throws JspException {
		this.direct = TagHelp.toBoolean(direct);
	}

	/**
	 * Specifies the logical name of a JSP-fragment.
	 * Note: The name must match with a placeholder
	 * in the JSP template used. The template is
	 * defined in the enclosing <template:insert>-tag.
	 *
	 * @param	name	The logical name of a JSP-fragment.
	 */
	public void setName(String name) {
		this.name = name;
	}
}
