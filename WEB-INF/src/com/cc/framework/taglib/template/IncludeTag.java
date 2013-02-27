/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/template/IncludeTag.java,v 1.7 2005/07/08 14:15:12 P001002 Exp $
 * $Revision: 1.7 $
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

import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import com.cc.framework.taglib.TagHelp;
import com.cc.framework.ui.painter.PainterHelp;

/**
 * Mit Hilfe dieses Tags wird eine, durch das <code>base</code>
 * <b>lokalisierbare</b>, Resource in den HTTPResponse eingebunden
 *
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.7 $
 */
public class IncludeTag extends TagSupport {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 971534620174944408L;

	/**
	 * Base Directory or resource key
	 */
	private String base;

	/**
	 * Name of the JSP page to include
	 */
	private String page;

	/**
	 * Locale Name
	 */
	private String localeName;

	/**
	 * Buffer flushing or not
	 */
	private boolean flush;

	/**
	 * @see	javax.servlet.jsp.tagext.Tag#setPageContext(PageContext pc)
	 */
	public void setPageContext(PageContext pc) {
		super.setPageContext(pc);

		release();
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#release()
	 */
	public void release() {
		super.release();

		base = null;
		page = null;
		flush = false;
		localeName = null;
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException {
		try {
			if (flush) {
				pageContext.getOut().flush();
			}

			Locale locale	= PainterHelp.localeFromName(pageContext, localeName);
			String uri		= PainterHelp.getSource(pageContext, base, page, locale);

			if (!uri.startsWith("/")) {
				uri = "/" + uri;
			}

			RequestDispatcher rd = pageContext.getServletContext().getRequestDispatcher(uri);

			if (rd != null) {
				rd.include(pageContext.getRequest(), pageContext.getResponse());
			}

			if (flush) {
				pageContext.getOut().flush();
			}
		} catch (ServletException se) {
			throw new JspException("IncludeTag: " + se.getMessage());
		} catch (java.io.IOException ioe) {
			throw new JspException("IncludeTag: " + ioe.getMessage());
		}

		// this tag has no body
		return SKIP_BODY;
	}

	/**
	 * Sets the base directory
	 *
	 * @param		string pathname or resource key
	 */
	public void setBase(String string) {
		this.base = string;
	}

	/**
	 * Sets the page
	 *
	 * @param		page page name
	 */
	public void setPage(String page) {
		this.page = page;
	}

	/**
	 * @param		loc Locale
	 */
	public void setLocale(String loc) {
		this.localeName = loc;
	}

	/**
	 * Directs the element to flush the page
	 *
	 * @param		flush True, if the page should be flushed
	 * @throws		JspException If the Argument can't be converted
	 * 				to boolean
	 */
	public void setFlush(String flush) throws JspException {
		this.flush = TagHelp.toBoolean(flush);
	}
}
