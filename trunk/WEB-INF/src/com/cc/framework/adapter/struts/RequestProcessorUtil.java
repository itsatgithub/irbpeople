/**
 * $Header: d:/repository/cvs/cc-adapter-struts-1.2/adapter/com/cc/framework/adapter/struts/RequestProcessorUtil.java,v 1.1 2005/01/27 09:22:59 P001002 Exp $
 * $Revision: 1.1 $
 * $Date: 2005/01/27 09:22:59 $
 *
 * ====================================================================
 *
 * Copyright (c) 2002 SCC Informationssysteme GmbH. All rights
 * reserved.
 *
 * ====================================================================
 */

package com.cc.framework.adapter.struts;

import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.cc.framework.security.Permission;
import com.cc.framework.security.PermissionException;
import com.cc.framework.security.Principal;
import com.cc.framework.util.StringHelp;

/**
 * Utilities for RequestProcessor derived classes
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.1 $
 */
public abstract class RequestProcessorUtil {

	/**
	 * Commons Logging instance.
	 */
	private static Log log = LogFactory.getLog(RequestProcessorUtil.class);

	/**
	 * Ask the specified <code>Action</code> instance to handle this
	 * request.  Return the <code>ActionForward</code> instance (if any)
	 * returned by the called <code>Action</code> for further processing.
	 *
	 * @param		request The servlet request we are processing
	 * @param		response The servlet response we are creating
	 * @param		action The Action instance to be used
	 * @param		form The ActionForm instance to pass to this Action
	 * @param		mapping The ActionMapping instance to pass to this Action
	 * @return		ActionForward or <code>null</code>
	 * @exception	Exception if an error occurs
	 */
	public static ActionForward processAction(
		HttpServletRequest request,
		HttpServletResponse response,
		Action action,
		ActionForm form,
		ActionMapping mapping)
		throws Exception {

		if (action instanceof FrameworkAction) {
			ActionContext ctx = ((FrameworkAction) action).createActionContext(mapping, form, request, response);

			if (ctx == null) {
				((FrameworkAction) action).execute(ctx);
				return ctx.getForward();
			}
		}

		// Default Struts Action processsing
		return action.execute(mapping, form, request, response);
	}

	/**
	 * This method checks if the current principal has the required
	 * permission. the method will only return if the if the principal
	 * has the required permissions.
	 *
	 * @param		roles required roles for this action
	 * @param		principal the current principal
	 * @throws		PermissionException is thrown if the users permissions
	 * 				are unsufficient
	 */
	public static void assertRoles(String roles, Principal principal)
		throws PermissionException {

		// get the access control liste (ACL) for this action
		if (roles == null) {
			// The action is not access controled
			return;
		}

		// Throw an exception if no principal object is
		// registered in teh users session
		if (principal == null) {
			throw new PermissionException("No principal object registered!");
		}

		Permission permission = Permission.parse(roles);

		Vector notGranted = new Vector();

		if (permission.isGranted(principal, notGranted)) {
			if (log.isDebugEnabled()) {
				log.debug(
					" User '"
						+ principal
						+ "' has permission '"
						+ permission
						+ "', granting access");
			}

			return;
		}

		// the user has not the neccessary permisssion!
		if (log.isDebugEnabled()) {
			log.debug(
				" User '"
					+ principal
					+ "' does not have any required permission, denying access");
		}

		// output all not granded permissions.
		throw new PermissionException(
			"Missing required permission: "
				+ StringHelp.join(notGranted, ';'));
	}

	/**
	 * Workaround for problems with WAS4.0
	 *
	 * @param		url Die URL
	 * @return		Forward
	 */
	public static String getForwardURL(String url) {
		StringTokenizer st = new StringTokenizer(url, "/");

		String lastToken	= "";
		String token		= "";
		String fwdURL		= "";
		boolean first		= true;

		while (st.hasMoreElements()) {
			token = st.nextToken();

			if (!token.equals(lastToken)) {
				lastToken = token;
			} else {
				continue;
			}

			fwdURL += token;

			if (st.hasMoreElements()) {
				if (first && token.equals("http:")) {
					fwdURL += "//";
					first = false;
				} else if (first && token.equals("https:")) {
					fwdURL += "//";
					first = false;
				} else if (first) {
					fwdURL = "/" + fwdURL + "/";
					first = false;
				} else {
					fwdURL += "/";
				}
			}
		}

		return fwdURL;
	}
}
