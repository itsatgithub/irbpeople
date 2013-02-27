/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/adapter/RequestContext.java,v 1.4 2005/07/31 11:54:13 P001002 Exp $
 * $Revision: 1.4 $
 * $Date: 2005/07/31 11:54:13 $
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

package com.cc.framework.adapter;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cc.framework.security.Principal;

/**
 * If an action is called, all relevant informations (like the
 * session, request, response object) are passed by a context object
 * which implements this interface.
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.4 $
 * @since      1.0
 */
public interface RequestContext {

	/**
	 * Gets the nested Action Context
	 *
	 * @return		The nested Action Context
	 */
	public RequestContext getNestedContext();

	/**
	 * Returns the HTTP Request
	 *
	 * @return the HttpServletRequest
	 */
	public HttpServletRequest request();

	/**
	 * Returns the HTTP Response
	 *
	 * @return the HttpServletResponse
	 */
	public HttpServletResponse response();

	/**
	 * Gets the session object
	 *
	 * @return	HttpSession
	 */
	public HttpSession session();

	/**
	 * Returns the Principal Object in the current User session
	 *
	 * @return	The object which implements the principal interface
	 */
	public Principal getPrincipal();

	/**
	 * Returns the current Locale for this Request
	 * 
	 * @return		Locale
	 */
	public Locale getLocale();
}
