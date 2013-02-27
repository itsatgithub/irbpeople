/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/security/SecurityUtil.java,v 1.15 2005/02/25 12:12:43 P001002 Exp $
 * $Revision: 1.15 $
 * $Date: 2005/02/25 12:12:43 $
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

package com.cc.framework.security;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.cc.framework.Globals;

/**
 * Helper class to manage the principal object.
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.15 $
 * @since      1.0
 */
public abstract class SecurityUtil {

	/**
	 * Constructor
	 */
	private SecurityUtil() {
		super();
	}

	/**
	 * Returns the users principal object with the user permissions.
	 *
	 * @param	request		The ServletRequest
	 * @return	Principal
	 */
	public static Principal getPrincipal(ServletRequest request) {
		if (request instanceof HttpServletRequest) {
			return getPrincipal(((HttpServletRequest) request).getSession(false));
		}

		return null;
	}

	/**
	 * Returns the users principal object with the user permissions.
	 *
	 * @param	request		The HttpServletRequest
	 * @return	Principal
	 */
	public static Principal getPrincipal(HttpServletRequest request) {
		return getPrincipal(request.getSession(false));
	}

	/**
	 * Returns the users principal object with the user permissions.
	 *
	 * @param	session		The HttpSession
	 * @return	Principal
	 */
	public static Principal getPrincipal(HttpSession session) {
		// Search the users session for a principal object
		if (session == null) {
			return null;
		}

		Object obj = session.getAttribute(Globals.PRINCIPAL_KEY);

		if (obj instanceof Principal) {
			return (Principal) obj;
		}

		// no principal objekt found!
		return null;
	}

	/**
	 * Registers a principal object for the user in the users session.
	 *
	 * @param	session		The HttpSession
	 * @param	principal	The principal object with the users permissions
	 */
	public static void registerPrincipal(HttpSession session, Principal principal) {

		if (session == null) {
			return;
		}

		if (principal == null) {
			session.removeAttribute(Globals.PRINCIPAL_KEY);
		} else {
			session.setAttribute(Globals.PRINCIPAL_KEY, principal);
		}
	}

	/**
	 * Unregisters the current principal object
	 *
	 * @param	session		The HttpSession
	 */
	public static void unregisterPrincipal(HttpSession session) {

		if (session == null) {
			return;
		}

		session.removeAttribute(Globals.PRINCIPAL_KEY);
	}
}