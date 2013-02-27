/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/svg/SvgTag.java,v 1.20 2005/07/08 14:15:06 P001002 Exp $
 * $Revision: 1.20 $
 * $Date: 2005/07/08 14:15:06 $
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

package com.cc.framework.taglib.svg;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cc.framework.common.InvalidEnumType;
import com.cc.framework.http.HttpScope;
import com.cc.framework.resource.MimeType;
import com.cc.framework.resource.ResourceManager;
import com.cc.framework.taglib.TagHelp;

/**
 * This Tag is used to embedd a SVG-Stream in a HTML-Page
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.20 $
 * @since      1.0
 */
public class SvgTag extends TagSupport {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 5600473624672784012L;

	/**
	 * Commons Logging instance.
	 */
	private transient Log log = LogFactory.getLog(this.getClass());

	/**
	 * The Scope of the bean containing our underlying property.
	 */
	private HttpScope scope = HttpScope.SESSION;

	/**
	 * The name of the bean containing our underlying property.
	 */
	private String name = null;

	/**
	 * Property
	 */
	private String property = null;

	/**
	 * Width
	 */
	private String width = null;

	/**
	 * Height
	 */
	private String height = null;

	/**
	 * Constructor
	 */
	public SvgTag() {
		super();
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#setPageContext(PageContext pc)
	 */
	public void setPageContext(PageContext pc) {
		super.setPageContext(pc);

		// Variablen auf einen Initialisierungswert einstellen
		scope		= HttpScope.SESSION;
		name		= null;
		width		= "0";
		height		= "0";
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#release()
	 */
	public void release() {
		super.release();

		scope		= HttpScope.SESSION;
		name		= null;
		property	= null;
		width		= null;
		height		= null;
	}

	/**
	 * Retrieves the bean which holds the Display Data or
	 * Control Instance
	 *
	 * @return		Bean (Control instance or DataModel)
	 * @throws		JspException Is thrown when the bean could
	 * 				not be found
	 */
	protected Object lookupBean() throws JspException {
		return TagHelp.lookupBean(pageContext, this, name, property, null);
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException {
		// Den SVG-Stream ermitteln
		Object svgStream = lookupBean();

		if (svgStream != null) {
			HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();

			String svgName = getResourceName();

			// Register the SVG-Stream within the Resourcen Manager
			ResourceManager.registerResource(
				request,
				scope,
				svgName,
				MimeType.SVG,
				svgStream.toString(),
				null);

			// Get the URI for the registered Resource
			String svgURI = ResourceManager.getResourceURI(pageContext, scope, svgName, null);

			try {
				JspWriter out = pageContext.getOut();

				StringBuffer buf =
					new StringBuffer()
						.append("<object")
						.append(" data='")
						.append(svgURI)
						.append("'")
						.append(" width='")
						.append(width)
						.append("'")
						.append(" height='")
						.append(height)
						.append("'")
						.append(" type='image/svg+xml'>")
						.append("<embed")
						.append(" src='")
						.append(svgURI)
						.append("'")
						.append(" width='")
						.append(width)
						.append("'")
						.append(" height='")
						.append(height)
						.append("'")
						.append(" type='image/svg+xml'")
						.append(" pluginspage='http://www.adobe.com/svg/viewer/install/'/>")
						.append("</object>");

				out.println(buf);

			} catch (IOException ioe) {
				log.error("Error in EmbedTag", ioe);
			}
		}

		return EVAL_PAGE;
	}

	/**
	 * Returns the rescource name
	 *
	 * @return	String
	 */
	protected String getResourceName() {

		if (property != null) {
			return property;
		}

		return name;
	}

	/**
	 * Sets the scope
	 *
	 * @param	scope	Scope
	 * @throws	JspException If the Argument can't be converted to HttpScope
	 */
	public void setScope(String scope) throws JspException {
		try {
			this.scope = HttpScope.parse(scope);
		} catch (InvalidEnumType iet) {
			throw new JspException(iet.getMessage());
		}
	}

	/**
	 * Sets the name
	 *
	 * @param value	Name
	 */
	public void setName(String value) {
		this.name = value;
	}

	/**
	 * Sets the property
	 *
	 * @param property	Property
	 */
	public void setProperty(String property) {
		this.property = property;
	}

	/**
	 * Sets the width
	 *
	 * @param value	Width
	 */
	public void setWidth(String value) {
		this.width = value;
	}

	/**
	 * Sets the height
	 *
	 * @param value	Height
	 */
	public void setHeight(String value) {
		this.height = value;
	}
}