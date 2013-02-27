/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/menu/ContextEqualTag.java,v 1.10 2005/07/08 14:14:52 P001002 Exp $
 * $Revision: 1.10 $
 * $Date: 2005/07/08 14:14:52 $
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

package com.cc.framework.taglib.menu;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.regexp.RE;

import com.cc.framework.ui.control.MenuContext;

/**
 * Checks a filter condition
 *
 * @author     <a href="mailto:gschulz@scc-gmbh.com">Gernot Schulz</a>
 * @version    $Revision: 1.10 $
 * @since      1.0
 */
public class ContextEqualTag extends TagSupport {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 7022014868147224738L;

	/**
	 * Commons Logging instance.
	 */
	private transient Log log	= LogFactory.getLog(this.getClass());

	/**
	 * Filter to evaluate
	 * (Regular Expression)
	 */
	private String filterExpr;

	/**
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException {
		int rc = SKIP_BODY;

		MenuContext ctx = MenuContext.getContext(pageContext);

		try {
			RE re = new RE(filterExpr);

			if (re.match(ctx.getFilter())) {
				rc = EVAL_BODY_INCLUDE;
			}

		} catch (org.apache.regexp.RESyntaxException rese) {
			log.error("Error comparing menu filter: " + filterExpr, rese);
		}

		return rc;
	}

	/**
	 * Sets the filter
	 *
	 * @param	filter	The filter
	 */
	public void setFilter(String filter) {
		this.filterExpr = filter;
	}

}
