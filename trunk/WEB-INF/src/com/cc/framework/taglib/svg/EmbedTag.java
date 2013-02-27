/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/svg/EmbedTag.java,v 1.18 2005/07/08 14:15:06 P001002 Exp $
 * $Revision: 1.18 $
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
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cc.framework.common.InvalidEnumType;
import com.cc.framework.http.HttpScope;
import com.cc.framework.resource.MimeType;
import com.cc.framework.resource.ResourceManager;

/**
 * This tag is used to embedd a SVG stream in a HTML page.
 * The tag inserts the SVG-Stream specified in the tag-body
 * directly in the resulting HTML page.
 * The SVG-Stream is saved in the ResourceManager for this purpose.
 * On the HTML-page, the following reference to the registered
 * resource is written (see example):
 * <pre>
 * &lt;object data='/cc/session/svgresource.res'
 *    width='100%' height='200'
 *    type='image/svg+xml'&gt;
 *
 *    &lt;embed src='/cc/session/svgresource.res'
 *       width='100%' height='200'
 *       type='image/svg+xml'
 *       pluginspage='http://www.adobe.com/svg/viewer/install/'/&gt;
 * &lt;/object&gt;
 *	</pre>
 * </code>
 *
 * The tag uses the Resource-Servlet of the common controls framework.
 * There must be an SVG-Viewer installed on the client system.
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.18 $
 * @since      1.0
 */
public class EmbedTag extends BodyTagSupport {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -5634356840821608507L;

	/**
	 * Commons Logging instance.
	 */
	private transient Log log	= LogFactory.getLog(this.getClass());

	/**
	 * Scope
	 */
	private HttpScope scope		= HttpScope.SESSION;

	/**
	 * Name
	 */
	private String name			= "svgstream";

	/**
	 * Width of the SVG
	 */
	private String width		= "0";

	/**
	 * Height of the SVG
	 */
	private String height		= "0";


	/**
	 * Constructor
	 */
	public EmbedTag() {
		super();
	}

	/**
	 * @see	javax.servlet.jsp.tagext.Tag#setPageContext(PageContext pc)
	 */
	public void setPageContext(PageContext pc) {
		super.setPageContext(pc);

		// Variablen auf einen Initialisierungswert einstellen
		scope	= HttpScope.SESSION;
		name	= null;
		width	= "0";
		height	= "0";
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#release()
	 */
	public void release() {
		super.release();

		scope		= HttpScope.SESSION;
		name		= null;
		width		= null;
		height		= null;
	}

	/**
	 * @see javax.servlet.jsp.tagext.IterationTag#doAfterBody()
 	 */
	public int doAfterBody() {

		HttpServletRequest request	= (HttpServletRequest) pageContext.getRequest();

		BodyContent body	= getBodyContent();
		String contentBody	= body.getString().trim();

		// Register the SVG-Stream within the resourcen manager
		ResourceManager.registerResource(request, scope, name, MimeType.SVG, contentBody, null);

		// Get the URI for the registered resource
		String svgURI = ResourceManager.getResourceURI(pageContext, scope, name, null);

		try {
			JspWriter out = body.getEnclosingWriter();

			StringBuffer buf = new StringBuffer()
				.append("<object")
				.append(" data='").append(svgURI).append("'")
				.append(" width='").append(width).append("'")
				.append(" height='").append(height).append("'")
				.append(" type='image/svg+xml'>")

				.append("<embed")
				.append(" src='").append(svgURI).append("'")
				.append(" width='").append(width).append("'")
				.append(" height='").append(height).append("'")
				.append(" type='image/svg+xml'")
				.append(" pluginspage='http://www.adobe.com/svg/viewer/install/'/>")

				.append("</object>");

			out.println(buf);

		} catch (IOException ioe) {
			log.error("Error in EmbedTag", ioe);
		}

		return SKIP_BODY;
	}

	/**
	 * Specifies the name of the Java-Bean.
	 * The Java-Bean must be stored in the given scope.
	 *
	 * @param	value	name
	 */
	public void setName(String value) {
		this.name = value;
	}

	/**
	 * The width of the SVG-viewer window.
	 * The height may be specified in absolute or percent terms.
	 *
	 * @param	value	The width of the SVG-viewer window.
	 */
	public void setWidth(String value) {
		this.width = value;
	}

	/**
	 * The height of the SVG-viewer window.
	 * The height may be specified in absolute or percent terms.
	 *
	 * @param	value	The height of the SVG-viewer window.
	 */
	public void setHeight(String value) {
		this.height = value;
	}

	/**
	 * This attribute shows the Scope in which the Java-Bean
	 * with the actual display data can be found.
	 *
	 * @param	scope	Scope
	 * @throws JspException if an invalid Scope is passed as Argument
	 */
	public void setScope(String scope) throws JspException {
		try {
			this.scope = HttpScope.parse(scope);
		} catch (InvalidEnumType iet) {
			throw new JspException(iet.getMessage());
		}
	}
}