/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/template/GetTag.java,v 1.11 2005/07/08 14:15:12 P001002 Exp $
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

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Tag handler for the <code>get</code> tag.
 * <p>
 * Returns the contents of a specified JSP-fragment - it thus
 * defiens a placemarker for a JSP-fragment in a template.
 * The tag may only be used within a <template:insert>-tag.
 * It can thus, in particular, be used in a JSP template,
 * since these are located in the Scope of a <template:insert>-tag!
 *
 * @author	   <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version	   $Revision: 1.11 $
 * @since      1.0
 */
public class GetTag extends TagSupport {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 7123705745213138518L;

	/**
	 * Field name
	 */
	private String name;

	/**
	 * Constructor
	 */
	public GetTag() {
		super();
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException {
		PageParameter param = TemplateHelp.getPageParameter(pageContext, name);

		if (param != null) {
			String base		= param.getBase();
			String content	= param.getContent();

			if (param.isDirect()) {
				try {
					pageContext.getOut().print(content);
				} catch (java.io.IOException ex) {
					throw new JspException(ex.getMessage());
				}
			} else {
				try {
					String resource =
						TemplateHelp.expandResourceName(base, content);

					pageContext.getOut().flush();
					pageContext.include(resource);
				} catch (Exception ex) {
					throw new JspException(ex.getMessage());
				}
			}
		}

		return SKIP_BODY;
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#release()
	 */
	public void release() {
		name = null;
	}

	/**
	 * Specifies the logical name of a JSP-fragment.
	 * Note: The name must match with a placeholder
	 * in the JSP template used. The template is
	 * defined in the enclosing <template:insert>-tag.
	 *
	 * @param	name	Specifies the logical name of a JSP-fragment
	 */
	public void setName(String name) {
		this.name = name;
	}
}