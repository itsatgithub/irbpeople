/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/menu/ContextTag.java,v 1.12 2005/07/08 14:14:52 P001002 Exp $
 * $Revision: 1.12 $
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
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import com.cc.framework.ui.control.MenuContext;

/**
 * This Tag is used to specify a menu context directly in the JSP-Page.
 * The application has exactly one menu context per user session.
 * This specifies which menu option is currently active.
 * This is done by defining the current menu context path.
 * The menu context can, with this tag, be directly specified in a JSP-Page,
 * or it is set, in the application, before the call to the JSP-Page with
 * the method com.cc.framework.ui.control.MenuContext.setPath().
 * The menu context acts on the depiction of all menu context elements.
 *
 * @author     <a href="mailto:gschulz@scc-gmbh.com">Gernot Schulz</a>
 * @version    $Revision: 1.12 $
 * @since      1.0
 */
public class ContextTag extends TagSupport {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 5683347088456958202L;

	/**
	 * Constructor
	 */
	public ContextTag() {
		super();
	}

	/**
	 * @see	javax.servlet.jsp.tagext.Tag#setPageContext(PageContext pc)
	 */
	public void setPageContext(PageContext pc) {
		super.setPageContext(pc);
	}

	/**
	 * @see	javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException {
		// the tag has no body
		return SKIP_BODY;
	}

	/**
	 * @see	javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	/**
	 * Defines the current menu context path.
	 * The context path defines which menu entry
	 * is selected. There is a hierarchical menu
	 * path assigned to every menu element (menu or menu option).
	 * It is determined across the menu hierarchy
	 * by concatenation of the menu IDs.
	 * A menu element should be considered to be selected
	 * when the context path starts with its menu path.
	 *
	 * @param	path	The current menu context path.
	 */
	public void setPath(String path) {
		MenuContext.getContext(pageContext).setPath(path);
	}

	/**
	 * Sets the current menu context filter
	 *
	 * @param	filter	Filter
	 */
	public void setFilter(String filter) {
		MenuContext.getContext(pageContext).setFilter(filter);
	}
}