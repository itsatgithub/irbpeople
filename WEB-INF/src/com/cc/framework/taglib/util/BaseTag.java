/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/util/BaseTag.java,v 1.17 2005/07/08 14:15:19 P001002 Exp $
 * $Revision: 1.17 $
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.LogFactory;

/**
 * Tag handler for the <code>base</code> tag.
 * The <util:base> tag creates a html <base href="...."> element.
 * The tag automatically sets the context root, so you can write
 * <util:base> instead of <base href="http://localhost:8080/contextRoot/;">
 * if the context root of your application points to "contextRoot".
 * The tag also sets automatically the used protocol (http, https).
 * So you can deploy your application under differnt names, without
 * changing the <base href="...."> element.
 *
 * @author     <a href="mailto:gschulz@scc-gmbh.com">Gernot Schulz</a>
 * @version    $Revision: 1.17 $
 * @since      1.0
 */
public class BaseTag extends TagSupport {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -5407648012749386035L;

	/**
	 * ContextPath
	 */
	private String contextPath = null;

	/**
	 * Portnumber
	 */
	private String port = null;

	/**
	 * Host
	 */
	private String host = null;

	// ------------------------------------------------
	//                Methods
	// ------------------------------------------------

	/**
	 * Constructor
	 */
	public BaseTag() {
		super();
	}

	/**
	 * @see javax.servlet.jsp.tagext.TagSupport#setPageContext(javax.servlet.jsp.PageContext)
	 */
	public void setPageContext(PageContext arg0) {
		super.setPageContext(arg0);

		contextPath = null;
		port = null;
		host = null;
	}

	/**
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	public int doStartTag() throws JspException {
		writeBase(pageContext, host, port, contextPath);

		return SKIP_BODY;
	}

	/**
	 * Writes the html base tag to the output
	 * 
	 * @param		pageContext PageContext
	 * @param		host Host
	 * @param		port Portnumber
	 * @param		contextPath ContextPath
	 */
	public static void writeBase(PageContext pageContext, String host, String port, String contextPath) {
		if (null == host) {
			host = pageContext.getRequest().getServerName();
		}
	
		if (null == port) {
			port = Integer.toString(pageContext.getRequest().getServerPort());
		}
	
		if (null == contextPath) {
			contextPath = ((HttpServletRequest) pageContext.getRequest()).getContextPath();
			contextPath += "/";
		} else {
			contextPath = "/" + contextPath;
		}
	
		String protocol = getProtocol(pageContext);
	
		try	{
			JspWriter out = pageContext.getOut();
	
			out.write("<base href=\"");
			out.write(protocol + "://");
			out.write(host);
			out.write(":" + port);
			out.write(contextPath + "\">");
	
		} catch (Throwable t) {
			LogFactory.getLog(BaseTag.class).error("Error: ", t);
		}
	}

	/**
	 * Returns the Protocol used by the Application/WebServer
	 * 
	 * @param		pageContext PageContext
	 * @return		String protocol
	 */
	public static String getProtocol(PageContext pageContext) {
		String protocol = pageContext.getRequest().getProtocol();

		if (null == protocol) {
			LogFactory.getLog(BaseTag.class).error("Error Base Tag. pageContext.getRequest().getProtocol() returns null!");
		}

		int index = protocol.indexOf("/");

		if (index == -1) {
			return 	protocol;
		} else {
			protocol = protocol.substring(0, index).toLowerCase();

			if (pageContext.getRequest().isSecure() && protocol.startsWith("http")) {
				protocol = "https";
			}

			return protocol;
		}
	}

	/**
	 * Sets the Host
	 * @param	host	Host
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * Sets the Portnumber
	 * @param port	Portnumber
	 */
	public void setPort(String port) {
		this.port = port;
	}

	/**
	 * Sets the ContextPath
	 * @param	contextPath	ContextPath
	 */
	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

}
