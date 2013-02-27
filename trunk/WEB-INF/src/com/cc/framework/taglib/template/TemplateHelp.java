/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/template/TemplateHelp.java,v 1.13 2005/05/27 14:25:23 P001002 Exp $
 * $Revision: 1.13 $
 * $Date: 2005/05/27 14:25:23 $
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
import java.util.StringTokenizer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import com.cc.framework.Globals;
import com.cc.framework.ui.painter.PainterFactory;

/**
 * Helper Class for the Template Library
 *
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.13 $
 */
public abstract class TemplateHelp {

	/**
	 * Constructor
	 */
	private TemplateHelp() {
		super();
	}

	/**
	 * Die Methode liefert die Templatedefinition mit dem angegebenen
	 * Namen zurück. Das Tamplate wurde zuvor auf einer JSP-Seite mit
	 * <template.put> deklariert.
	 * @param	pageContext	PageContext
	 * @param	name	Template Name
	 * @return	PageParameter
	 * @throws	JspException Is thrown when when the template
	 * 			stack is not propperly initialized
	 */
	public static PageParameter getPageParameter(
		PageContext pageContext,
		String name)
		throws JspException {

		Stack stack =
			(Stack) pageContext.getAttribute(
				Globals.TEMPLATE_KEY,
				PageContext.REQUEST_SCOPE);

		if (stack == null) {
			throw new JspException("No template stack");
		}

		Hashtable params = (Hashtable) stack.peek();

		if (params == null) {
			throw new JspException("No hashtable");
		}

		return (PageParameter) params.get(name);
	}

	/**
	 * Checks if the specified template is deklared/present
	 * @param	pageContext	PageContext
	 * @param	name	Template Name
	 * @return	boolean
	 * @throws	JspException Is thrown when when the template
	 * 			stack is not propperly initialized
	 */
	public static boolean isContentPresent(
		PageContext pageContext,
		String name)
		throws JspException {

		return (getPageParameter(pageContext, name) != null);
	}

	/**
	 * returns the Content of the specified Template
	 * @param	pageContext	PageContext
	 * @param	name	Template Name
	 * @return	String
	 * @throws	JspException Is thrown when when the template
	 * 			stack is not propperly initialized
	 */
	public static String getTemplate(PageContext pageContext, String name)
		throws JspException {

		PageParameter param = getPageParameter(pageContext, name);

		return (param == null) ? null : param.getContent();
	}

	/**
	 * Die Methode löst den Inhalt eines Templates für die Verwendung auf.
	 * Dabei werden die augenblicklich registrierten Painter Factories
	 * berücksichtigt.
	 *
	 * Ein Template kann in der folgenden Weise deklariert werden:
	 * {[factoryId:JSP-Resource;]}JSP-Resource
	 *
	 * Mit Hilfe dieser Methode können Templates für eine bestimmte
	 * Painter Factory spezialisiert werden.
	 * @param	pageContext	PageContext
	 * @param	templateDefList	TemplateDefList
	 * @return	String
	 */
	public static String resolve(
		PageContext pageContext,
		String templateDefList) {
		StringTokenizer st = new StringTokenizer(templateDefList, ";");

		while (st.hasMoreElements()) {
			String templateDef = st.nextToken();

			int split = templateDef.indexOf(":");

			if (split == -1) {
				// There is no factory prefix specified
				return templateDef;
			} else {
				// resolve the prefix of the factory
				String factory = templateDef.substring(0, split);

				// if the factory is actvie the the template is used
				if (PainterFactory.isFactorySelected(pageContext, factory)) {
					// Die Factory für dieses Template ist gerade in Verwendung.
					// Das Template wird daher zurückgegeben
					return templateDef.substring(split + 1);
				}
			}
		}

		return null;
	}

	/**
	 * Methode expandResourceName
	 * @param baseDir	base directory
	 * @param resourceName	Resource Name
	 * @return	String
	 */
	public static String expandResourceName(
		String baseDir,
		String resourceName) {
		String expanded = null;

		if (resourceName.startsWith("$")) {
			// the name of the path is specified relative to the base directory
			expanded = baseDir + resourceName.substring(1);
		} else {
			// the name of the path is specified absolute to the base directory
			expanded = resourceName;
		}

		return expanded;
	}
}