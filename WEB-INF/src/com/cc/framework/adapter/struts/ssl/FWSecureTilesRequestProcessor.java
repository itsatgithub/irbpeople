/**
 * $Header: d:/repository/cvs/cc-adapter-struts-1.2/adapter/com/cc/framework/adapter/struts/ssl/FWSecureTilesRequestProcessor.java,v 1.1 2005/04/04 16:05:11 P001002 Exp $
 * $Revision: 1.1 $
 * $Date: 2005/04/04 16:05:11 $
 *
 * ====================================================================
 *
 * Copyright (c) 2002 SCC Informationssysteme GmbH. All rights
 * reserved.
 *
 * ====================================================================
 */

package com.cc.framework.adapter.struts.ssl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.SecureTilesRequestProcessor;

import com.cc.framework.adapter.struts.RequestProcessorUtil;
import com.cc.framework.http.CacheControl;
import com.cc.framework.security.PermissionException;
import com.cc.framework.security.SecurityUtil;

/**
 * Secured Request Prcessor for use with the Framework
 * Security System
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.1 $
 */
public class FWSecureTilesRequestProcessor extends SecureTilesRequestProcessor {

	/**
	 * Constructor
	 */
	public FWSecureTilesRequestProcessor() {
		super();
	}

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
	 * @exception	IOException if an input/output error occurs
	 * @exception	ServletException if a servlet exception occurs
	 */
	protected ActionForward processActionPerform(
		HttpServletRequest request,
		HttpServletResponse response,
		Action action,
		ActionForm form,
		ActionMapping mapping)
		throws IOException, ServletException {

		try {
			return RequestProcessorUtil.processAction(
				request,
				response,
				action,
				form,
				mapping);
		} catch (Exception e) {
			return processException(request, response, e, form, mapping);
		}
	}

	/**
	 * Set the no-cache headers for all responses, if requested.
	 * <strong>NOTE</strong> - This header will be overridden
	 * automatically if a <code>RequestDispatcher.forward()</code> call is
	 * ultimately invoked.
	 *
	 * @param request The servlet request we are processing
	 * @param response The servlet response we are creating
	 */
	protected void processNoCache(
		HttpServletRequest request,
		HttpServletResponse response) {
		if (moduleConfig.getControllerConfig().getNocache()) {
			CacheControl.setCache(response, true);
		}
	}

	/**
	 * If this action is protected by security roles, make sure that the
	 * current user possesses at least one of them.  Return <code>true</code>
	 * to continue normal processing, or <code>false</code> if an appropriate
	 * response has been created and processing should terminate.
	 *
	 * @param		request The servlet request we are processing
	 * @param		response The servlet response we are creating
	 * @param		mapping The mapping we are using
	 * @return		true
	 * @exception	IOException if an input/output error occurs
	 * @exception	ServletException if a servlet exception occurs
	 */
	protected boolean processRoles(
		HttpServletRequest request,
		HttpServletResponse response,
		ActionMapping mapping)
		throws IOException, ServletException {

		try {
			RequestProcessorUtil.assertRoles(
				mapping.getRoles(),
				SecurityUtil.getPrincipal(request));

			return true;
		} catch (PermissionException pe) {
			throw new ServletException(pe.getMessage());
		}
	}
}
