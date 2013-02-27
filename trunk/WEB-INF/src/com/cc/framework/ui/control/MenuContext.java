/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/control/MenuContext.java,v 1.10 2005/02/25 12:12:46 P001002 Exp $
 * $Revision: 1.10 $
 * $Date: 2005/02/25 12:12:46 $
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

package com.cc.framework.ui.control;

import java.util.StringTokenizer;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.PageContext;

import com.cc.framework.Globals;

/**
 * MenuContext
 * Every JSP can define one MenuContext
 *
 * @author   <a href="mailto:hschulz@scc-gmbh.com">Gernot Schulz</a>
 * @version  $Revision: 1.10 $
 * @since    1.0
 */
public final class MenuContext {

	/**
	 * The current MenuPath
	 */
	private String path = null;

	/**
	 * Additional filter to filter
	 * MenuItems not needed
	 */
	private String filter = null;

	/**
	 * Constructor
	 * An Instance of this Class only can be created with the
	 * Method getContext()
	 */
	private MenuContext() {
		super();
	}

	/**
	 * Gets the actual menu context from the request.
	 * If the context did not exist, it will be created
	 *
	 * @param	pageContext	PageContext
	 * @return	MenuContext
	 */
	public static MenuContext getContext(PageContext pageContext) {
		return getContext(pageContext.getRequest());
	}

	/**
	 * Gets the actual menu context from the request.
	 * If the context did not exist, it will be created
	 *
	 * @param	request			ServletRequest
	 * @return	MenuContext
	 */
	public static MenuContext getContext(ServletRequest request) {

		MenuContext ctx = (MenuContext) request.getAttribute(Globals.MENUCONTEXT_KEY);

		if (ctx == null) {
			ctx = new MenuContext();

			request.setAttribute(Globals.MENUCONTEXT_KEY, ctx);
		}

		return ctx;
	}

	/**
	 * Helper to check if a menuitem is included in the
	 * actual path
	 *
	 * @param	itemPath	Path
	 * @return	boolean
	 */
	public boolean isSelected(String itemPath) {
		if ((path == null) || (itemPath == null) || !path.startsWith(itemPath)) {
			return false;
		}

		if (path.length() == itemPath.length()) {
			return true;
		}

		// Das Nächste Zeichen muss ein Pfad Separator sein.
		char c = path.charAt(itemPath.length());

		return (c == ' ') || (c == '\\') || (c == '/') || (c == '.') || (c == '_');
	}

	/**
	 * Helper to check if a menuitem matchs a filter expression.
	 *
	 * @param	itemFilter	The filter expression.
	 * @return	boolean
	 */
	public boolean filter(String itemFilter) {
		if (itemFilter == null) {
			return false;
		}

		StringTokenizer st = new StringTokenizer(itemFilter, ";");

		while (st.hasMoreElements()) {
			String itemFilterToken = st.nextToken();

			if (itemFilterToken.equals(filter)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Returns the filter.
	 * @return String
	 */
	public String getFilter() {
		return filter;
	}

	/**
	 * Returns the path.
	 * @return String
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Sets the filter.
	 * @param filter The filter to set
	 */
	public void setFilter(String filter) {
		this.filter = filter;
	}

	/**
	 * Sets the path.
	 * @param path The path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Menucontext: Path=" + path + " Filter=" + filter;
	}
}