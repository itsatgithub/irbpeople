/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/http/HttpUtil.java,v 1.27 2005/06/21 07:09:29 P001002 Exp $
 * $Revision: 1.27 $
 * $Date: 2005/06/21 07:09:29 $
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

package com.cc.framework.http;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspEngineInfo;
import javax.servlet.jsp.JspFactory;

import com.cc.framework.util.Formatter;
import com.cc.framework.util.StringHelp;
import com.cc.framework.version.VersionInfo;

/**
 * Helper class to create informations about the session, request object or the
 * system environement.
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.27 $
 * @since      1.0
 */
public abstract class HttpUtil {

	/**
	 * LABEL_LENGTH
	 */
	private static final int LABEL_LENGTH = 45;

	/**
	 * Constructor
	 */
	private HttpUtil() {
		super();
	}

	/**
	 * Marks the request with an named attribute. This mark will survive
	 * multiple request forwards. So it is possible to track if the request
	 * has been handeled before.
	 *
	 * @param		request HttpServletRequest
	 * @param		mark she name of the mark
	 * @param		value an optional value
	 * @return		returns the last value of the mark or <code>null</code>
	 */
	public static Object markRequest(HttpServletRequest request, String mark, Object value) {
		Object curValue = request.getAttribute(mark);

		request.setAttribute(mark, (value == null) ? "mark" : value);

		return curValue;
	}

	/**
	 * Removes the mark from the request
	 *
	 * @param		request HttpServletRequest
	 * @param		mark she name of the mark
	 * @return		returns the last value of the mark or <code>null</code>
	 */
	public static Object unmarkRequest(HttpServletRequest request, String mark) {
		Object curValue = request.getAttribute(mark);

		request.removeAttribute(mark);

		return curValue;
	}

	/**
	 * Checks if a mark is present
	 *
	 * @param		request HttpServletRequest
	 * @param		mark she name of the mark
	 * @return		returns <code>true</code> if the mark is present
	 */
	public static boolean isRequestMarked(HttpServletRequest request, String mark) {
		return request.getAttribute(mark) != null;
	}

	/**
	 * Performs an URL-Encoding for the given String
	 *
	 * @param		decoded String in raw format
	 * @return		encoded String which can be used
	 * 				safely in URL's
	 */
	public static String urlEncode(Object decoded) {
		// Version for JDK 1.3.1
		return URLEncoder.encode(String.valueOf(decoded));

		/*
		// Version for jdk >= 1.4.2_3
		try {
			return URLEncoder.encode(String.valueOf(decoded), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// The system should always have the UTF-8 encoding
			return String.valueOf(decoded);
		}
		*/
	}

	/**
	 * Performs an URL-Decoding for the given String
	 *
	 * @param		encoded URL encoded String
	 * @return		readable decoded String
	 */
	public static String urlDecode(String encoded) {
		// Version for JDK 1.3.1
		return URLDecoder.decode(encoded);

		/*
		// Version for jdk >= 1.4.2_3
		try {
			return URLDecoder.decode(encoded, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// The system should always have the UTF-8 encoding
			return encoded;
		}
		*/
	}

	/**
	 * Computes the applications full qualified context path
	 *
	 * @param		request HTTP request
	 * @return		Context path
	 */
	public static String getAppContextPath(HttpServletRequest request) {
		StringBuffer path = new StringBuffer();

		String protocol = request.getProtocol().toLowerCase();

		int index = protocol.indexOf("/");
		if (index != -1) {
			protocol = protocol.substring(0, index).toLowerCase();

			if (request.isSecure() && protocol.startsWith("http")) {
				protocol = "https";
			}
		}

		path
			.append(protocol)
			.append("://")
			.append(request.getServerName());

		if (request.getServerPort() != 80) {
			path
				.append(":")
				.append(request.getServerPort());
		}

		path.append(request.getContextPath());

		return path.toString();
	}

	/**
	 * Returns an enumeration of paramter names
	 *
	 * @param	request		HttpServletRequest
	 * @return	Hashtable
	 */
	public static Hashtable createParameterTable(HttpServletRequest request) {

		Hashtable ht = new Hashtable();

		Enumeration keys = request.getParameterNames();

		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();

			ht.put(key, request.getParameter(key));
		}

		return ht;
	}

	/**
	 * Returns an enumeration of paramter names
	 *
	 * @param	request		HttpServletRequest
	 * @return	Properties
	 */
	public static Properties createProperties(HttpServletRequest request) {

		Properties properties = new Properties();

		Enumeration keys = request.getParameterNames();

		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();

			properties.setProperty(key, request.getParameter(key));
		}

		return properties;
	}

	/**
	 * Creates informations about the user session
	 *
	 * @param	session	HttpSession
	 * @return	String
	 */
	public static String createSessionInfo(HttpSession session) {

		String separator = System.getProperty("line.separator");

		StringBuffer buf = new StringBuffer(separator);

		// ********************************************
		// Session Informationen
		// ********************************************

		buf
			.append(separator)
			.append("* Session Information ************************************************")
			.append(separator);

		if (session == null) {
			buf
				.append("NO SESSION AVAILABLE")
				.append(separator);
		} else {
			buf
				.append("new................: ").append(session.isNew()).append(separator)
				.append("Id.................: ").append(session.getId()).append(separator)
				.append("CreationTime.......: ").append(Formatter.formatDateAndTime(session.getCreationTime())).append(separator)
				.append("LastAccessedTime...: ").append(Formatter.formatDateAndTime(session.getLastAccessedTime())).append(separator)
				.append("MaxInactiveInterval: ").append(session.getMaxInactiveInterval()).append(separator)
				.append(separator)
				.append("* Session Beans *****")
				.append(separator);

			// Die Attributliste wird zunächst sortiert
			ArrayList list = new ArrayList();

			Enumeration e = session.getAttributeNames();
			while (e.hasMoreElements()) {
				list.add(e.nextElement());
			}

			Collections.sort(list);

			for (int i = 0; i < list.size(); i++) {
				String attribute = (String) list.get(i);

				buf
					.append(StringHelp.fill(attribute, LABEL_LENGTH, '.'))
					.append(": ")
					.append(session.getAttribute(attribute))
					.append(separator);
			}

			buf.append(separator);
		}

		// ********************************************
		// End
		// ********************************************

		buf
			.append("**********************************************************************")
			.append(separator);

		return buf.toString();
	}

	/**
	 * Creates information about the request parameter.
	 *
	 * @param	request HttpServletRequest
	 * @param	includeParameters <code>true</code> to show Parameters
	 * @return	String
	 */
	public static String createRequestInfo(HttpServletRequest request, boolean includeParameters) {

		String separator = System.getProperty("line.separator");

		StringBuffer buf = new StringBuffer(separator);

		// ********************************************
		// Header Informationen
		// ********************************************

		buf
			.append(separator)
			.append("* Header Fields ******************************************************")
			.append(separator);

		// Die Attributliste wird zunächst sortiert
		ArrayList list = new ArrayList();

		Enumeration e = request.getHeaderNames();
		while (e.hasMoreElements()) {
			list.add(e.nextElement());
		}

		Collections.sort(list);

		for (int i = 0; i < list.size(); i++) {
			String headerName = (String) list.get(i);

			buf
				.append(StringHelp.fill(headerName, LABEL_LENGTH, '.'))
				.append(": ");

			Enumeration heades = request.getHeaders(headerName);
			while (heades.hasMoreElements()) {
				buf
					.append(heades.nextElement())
					.append(";");
			}

			buf.append(separator);
		}

		// ********************************************
		// Request Parameter
		// ********************************************
		if (includeParameters) {
			buf
				.append("* Request Parameter **************************************************")
				.append(separator);

			// Die Attributliste wird zunächst sortiert
			list = new ArrayList();

			e = request.getParameterNames();
			while (e.hasMoreElements()) {
				list.add(e.nextElement());
			}

			Collections.sort(list);

			for (int i = 0; i < list.size(); i++) {
				String parameterName = (String) list.get(i);
				String[] parameterValues = request.getParameterValues(parameterName);

				buf
					.append(StringHelp.fill(parameterName, LABEL_LENGTH, '.'))
					.append(": ");

				for (int j = 0; j < parameterValues.length; j++) {
					if (j > 0) {
						buf.append("; ");
					}

					buf.append(parameterValues[j]);
				}

				buf.append(separator);
			}
		}

		// ********************************************
		// Request Attribute
		// ********************************************
		buf
			.append("* Request Attribute **************************************************")
			.append(separator);

		// Die Attributliste wird zunächst sortiert
		list = new ArrayList();

		e = request.getAttributeNames();
		while (e.hasMoreElements()) {
			list.add(e.nextElement());
		}

		Collections.sort(list);

		for (int i = 0; i < list.size(); i++) {
			String attributeName = (String) list.get(i);

			buf
				.append(StringHelp.fill(attributeName, LABEL_LENGTH, '.'))
				.append(": ")
				.append(request.getAttribute(attributeName));

			buf.append(separator);
		}

		// ********************************************
		// Ende der Ausgabe
		// ********************************************

		buf
			.append("**********************************************************************")
			.append(separator);

		return buf.toString();
	}

	/**
	 * Creates information about the cookies
	 *
	 * @param	request HttpServletRequest
	 * @return	String
	 */
	public static String createCookieInfo(HttpServletRequest request) {

		String separator = System.getProperty("line.separator");

		StringBuffer buf = new StringBuffer(separator);

		// ********************************************
		// Cookies
		// ********************************************
		buf
			.append("* Cookies ************************************************************")
			.append(separator);

		// Die Attributliste wird zunächst sortiert
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			buf.append("no cookies available").append(separator);
		} else {
			for (int i = 0; i < cookies.length; i++) {

				buf
					.append("- ").append(cookies[i].getName()).append(separator)
					.append("Comment....: ").append(cookies[i].getComment()).append(separator)
					.append("Domain.....: ").append(cookies[i].getDomain()).append(separator)
					.append("MaxAge.....: ").append(cookies[i].getMaxAge()).append(separator)
					.append("Name.......: ").append(cookies[i].getName()).append(separator)
					.append("Path.......: ").append(cookies[i].getPath()).append(separator)
					.append("Secure.....: ").append(cookies[i].getSecure()).append(separator)
					.append("Value......: ").append(cookies[i].getValue()).append(separator)
					.append("Version....: ").append(cookies[i].getVersion()).append(separator);
			}
		}

		// ********************************************
		// Ende der Ausgabe
		// ********************************************

		buf
			.append("**********************************************************************")
			.append(separator);

		return buf.toString();
	}

	/**
	 * Creates information about the servlet context.
	 *
	 * @param	context	ServletContext
	 * @return	String
	 */
	public static String createContextInfo(ServletContext context) {

		String separator = System.getProperty("line.separator");

		StringBuffer buf = new StringBuffer(separator);

		// ********************************************
		// Application Informationen
		// ********************************************

		buf
			.append(separator)
			.append("* Servlet Context ****************************************************")
			.append(separator)

			.append("Servlet API........: ")
				.append(context.getMajorVersion())
				.append(".")
				.append(context.getMinorVersion())
				.append(separator)
			.append("ServerInfo.........: ").append(context.getServerInfo()).append(separator)

			.append(separator)
			.append("* Attributes ****")
			.append(separator);

		// Die Attributliste wird zunächst sortiert
		ArrayList list = new ArrayList();

		Enumeration e = context.getAttributeNames();
		while (e.hasMoreElements()) {
			list.add(e.nextElement());
		}

		Collections.sort(list);

		for (int i = 0; i < list.size(); i++) {
			String attribute = (String) list.get(i);

			buf
				.append(StringHelp.fill(attribute, LABEL_LENGTH, '.'))
				.append(": ")
				.append(context.getAttribute(attribute))
				.append(separator);
		}

		// ********************************************
		// Ende der Ausgabe
		// ********************************************

		buf
			.append("**********************************************************************")
			.append(separator);

		return buf.toString();
	}

	/**
	 * Creates informations about the system environement and the Java VM.
	 *
	 * @return	String
	 */
	public static String createEnvironmentInfo() {

		String separator = System.getProperty("line.separator");

		// Stringbuffer als Returnvariable
		// Zunächst super ausgeben
		StringBuffer buf = new StringBuffer(separator);

		// ********************************************
		// Sytemkonfiguration
		// ********************************************

		buf
			.append(separator)
			.append("* System Properties **************************************************")
			.append(separator);

		Properties props = System.getProperties();

		// Die Attributliste wird zunächst sortiert
		ArrayList list = new ArrayList();

		Enumeration e = props.propertyNames();
		while (e.hasMoreElements()) {
			list.add(e.nextElement());
		}

		Collections.sort(list);

		for (int i = 0; i < list.size(); i++) {
			String propertyName = (String) list.get(i);

			buf
				.append(StringHelp.fill(propertyName, LABEL_LENGTH, '.'))
				.append(": ")
				.append(System.getProperty(propertyName))
				.append(separator);
		}

		// ********************************************
		// Verwendete JSP Spezifikation
		// ********************************************

		buf
			.append(separator)
			.append("* JSP Engine ****")
			.append(separator)

			.append("JSP-Specification..: ").append(getJSPVersion()).append(separator);

		// ********************************************
		// Speichergrösse der JVM
		// ********************************************

		Runtime runtime = Runtime.getRuntime();

		buf
			.append(separator)
			.append("* Runtime ****")
			.append(separator)

			.append("Total Memory.......: ").append(Formatter.formatBytes(runtime.totalMemory())).append(separator);
			/*
			erst ab jdk 1.4
			.append("Maximum Memory.....: ").append(Formatter.formatBytes(runtime.maxMemory())).append(separator);
			*/

		// ********************************************
		// Ende der Ausgabe
		// ********************************************

		buf
			.append("**********************************************************************")
			.append(separator);

		return buf.toString();
	}

	/**
	 * Creates a Version String
	 *
	 * @param		version Verison Information
	 * @return		String
	 */
	public static String createVersionInfo(VersionInfo version) {

		String separator = System.getProperty("line.separator");

		// Stringbuffer als Returnvariable
		StringBuffer buf = new StringBuffer(separator);

		// ********************************************
		// Versionsinformation
		// ********************************************

		buf
			.append(separator)
			.append("* Version Information ************************************************")
			.append(separator)
			.append("Product Name.........: ").append(version.getProductName()).append(separator)
			.append("Product Version......: ").append(version.getProductVersion()).append(separator)
			.append("Product Build Date...: ").append(version.getProductBuildDate()).append(separator)
			.append("Product Vendor.......: ").append(version.getProductVendor()).append(separator)
			.append("Product Vendor-Site..: ").append(version.getProductVendorSite()).append(separator)
			.append("Product Type.........: ").append(version.getSystemType()).append(separator)
			.append("**********************************************************************")
			.append(separator);

		return buf.toString();
	}

	/**
	 * Returns the version of the JSP specification
	 *
	 * @return	String	the JSP version
	 */
	public static String getJSPVersion() {
		JspFactory factory = JspFactory.getDefaultFactory();

		if (factory != null) {
			JspEngineInfo ei = factory.getEngineInfo();

			if (ei != null) {
				return ei.getSpecificationVersion();
			}
		}

		return "unknown";
	}
}