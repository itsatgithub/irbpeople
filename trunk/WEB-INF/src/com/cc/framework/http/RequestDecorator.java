/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/http/RequestDecorator.java,v 1.10 2005/07/08 14:13:49 P001002 Exp $
 * $Revision: 1.10 $
 * $Date: 2005/07/08 14:13:49 $
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

import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import com.cc.framework.Globals;

/**
 * The RequestDecorator is used to add additional parameters to urls
 * used or generated within a control.
 *
 * @author    <a href="mailto:gschulz@scc-gmbh.com">Gernot Schulz</a>
 * @version	  $Revision: 1.10 $
 * @since     1.0
 */
public abstract class RequestDecorator {

	/**
	 * Constructor
	 */
	private RequestDecorator() {
		super();
	}

	/**
	 * Sets a Value
	 * @param key		key under which the Data should be stored in the specified Scope (Session, Request)
	 * @param value		Value to store
	 * @param type		RequestDecorationType
	 * @param request	HttpServletRequest
	 * @param scope		HttpScope
	 */
	public static void setValue(String key, String value, RequestDecorationType type, HttpServletRequest request, HttpScope scope) {

		Hashtable hData = null;

		if (HttpScope.REQUEST.equals(scope)) {
			hData = (Hashtable) request.getAttribute(Globals.DECORATOR_KEY);

			// If the collection is not created yet
			// we create a new one
			if (null == hData) {
				hData = new Hashtable();
				request.setAttribute(Globals.DECORATOR_KEY, hData);
			}
		} else if (HttpScope.SESSION.equals(scope)) {
			hData = (Hashtable) request.getSession(false).getAttribute(Globals.DECORATOR_KEY);

			// If the collection is not created yet
			// we crate a new one
			if (null == hData) {
				hData = new Hashtable();
				request.getSession(false).setAttribute(Globals.DECORATOR_KEY, hData);
			}
		}

		// store ore remove the value
		if (value == null) {
			hData.remove(type.decorate(key));
		} else {
			hData.put(type.decorate(key), value);
		}
	}

	/**
	 * Returns a Value
	 * @param key		Key under which the Object is stored
	 * @param type		RequestDecorationType
	 * @param request	HttpServletRequest
	 * @return	String
	 */
	public static String getValue(String key, RequestDecorationType type, HttpServletRequest request) {
		Hashtable hData = null;

		hData = (Hashtable) request.getSession(false).getAttribute(Globals.DECORATOR_ACT_KEY);

		String hdKey = type.decorate(key);

		if (null != hData && hData.containsKey(hdKey)) {
			return (String) hData.get(hdKey);
		}

		hData = (Hashtable) request.getSession(false).getAttribute(Globals.DECORATOR_KEY);

		if (null != hData && hData.containsKey(hdKey)) {
			return (String) hData.get(hdKey);
		}

		// key not found in scope request or session
		throw new IllegalArgumentException("Object for key '" + key + "' not found!");
	}

	/**
	 * Returns the Collection with Hiddendata Objects
	 * @param	type	HiddenDataType
	 * @param	pageContext	PageContext
	 * @return	Hashtable
	 */
	public static Hashtable getValueList(RequestDecorationType type, PageContext pageContext) {

		Hashtable hidden = new Hashtable();
		Hashtable hData = null;

		// Check data in the request scope
		hData = (Hashtable) pageContext.getRequest().getAttribute(Globals.DECORATOR_KEY);

		if (null != hData && !hData.isEmpty()) {
			hidden.putAll(hData);
		}

		// Check data in the session scope
		hData = (Hashtable) pageContext.getSession().getAttribute(Globals.DECORATOR_KEY);

		if (null != hData && !hData.isEmpty()) {
			hidden.putAll(hData);
		}

		return hidden;
	}


	/**
	 * Fills the Collection from the Request.
	 * The fields must follow the naming convention
	 * as defined in class RequestDecorationType.
	 *
	 * @param request	HttpServletRequest
	 */
	public static void parseRequest(HttpServletRequest request) {

		HttpSession session = request.getSession(false);

		if (session == null) {
			return;
		}

		Hashtable hData = (Hashtable) session.getAttribute(Globals.DECORATOR_ACT_KEY);

		if (null == hData) {
			hData = new Hashtable();
			session.setAttribute(Globals.DECORATOR_ACT_KEY, hData);
		}

		// Get all fields of type URL and Form
		Enumeration e = request.getParameterNames();

		while (e.hasMoreElements()) {
			String parameter = (String) e.nextElement();

			if (RequestDecorationType.TYPE_HD_URL.isMatching(parameter)
			  	|| RequestDecorationType.TYPE_HD_FORM.isMatching(parameter)) {
				hData.put(parameter, request.getParameter(parameter));
			}
		}
	}
}