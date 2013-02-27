/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/util/JspTag.java,v 1.12 2005/07/08 14:15:19 P001002 Exp $
 * $Revision: 1.12 $
 * $Date: 2005/07/08 14:15:19 $
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

package com.cc.framework.taglib.util;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import com.cc.framework.Globals;
import com.cc.framework.common.InvalidEnumType;
import com.cc.framework.ui.painter.PainterFactory;

/**
 * Possible values for the JSP tag are:
 * <ul>
 * 	<li>include - Includes all necessary resources for the Framework at the begin of the JSP Page.</li>
 *  <li>endofpage - Releases used resources by the Framework at the end of the JSP Page.</li>
 * </ul>
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.12 $
 * @since      1.0
 */
public class JspTag extends TagSupport {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -4426738400120443115L;

	/**
	 * jsp directive
	 */
	private JspDirective directive = JspDirective.NONE;


	/**
	 * Constructor for JspTag
	 */
	public JspTag() {
		super();
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#release()
	 */
	public void release() {
		super.release();

		directive = JspDirective.NONE;
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#setPageContext(PageContext)
	 */
	public void setPageContext(PageContext arg0) {
		super.setPageContext(arg0);

		directive = JspDirective.NONE;
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException {

		if (JspDirective.INCLUDES.equals(directive)) {
			PainterFactory.createHeaderIncludes(pageContext);
		} else if (JspDirective.ENDOFPAGE.equals(directive)) {
			HttpSession session = pageContext.getSession();

			if (session != null) {
				session.removeAttribute(Globals.ERRORS_SAVE_KEY);
				session.removeAttribute(Globals.MESSAGES_SAVE_KEY);
			}
		}

		// the tag has no body
		return SKIP_BODY;
	}

	/**
	 * Sets the JSP directive
	 *
	 * @param	directive	The JSP directive to set
	 * @throws JspException if the argument can not be converted into an object of type JspDirective
	 */
	public void setDirective(String directive) throws JspException {
		try {
			this.directive = JspDirective.parse(directive);
		} catch (InvalidEnumType iet) {
			throw new JspException(iet.getMessage());
		}
	}
}