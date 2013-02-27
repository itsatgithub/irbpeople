/*
 * $Header: d:/repository/cvs/cc-adapter-struts-1.2/adapter/com/cc/framework/adapter/struts/FWRequestProcessor.java,v 1.5 2005/09/27 12:57:13 P001002 Exp $
 * $Revision: 1.5 $
 * $Date: 2005/09/27 12:57:13 $
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

package com.cc.framework.adapter.struts;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.RequestProcessor;
import org.apache.struts.config.ForwardConfig;
import org.apache.struts.util.RequestUtils;

import com.cc.framework.http.CacheControl;
import com.cc.framework.security.PermissionException;
import com.cc.framework.security.SecurityUtil;

/**
 * Request Prcessor for use with the Framework Security System
 * 
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version $Revision: 1.5 $
 * @version 1.2
 */
public class FWRequestProcessor extends RequestProcessor {

	/**
	 * Constructor
	 */
	public FWRequestProcessor() {
		super();
	}

	/**
	 * Ask the specified <code>Action</code> instance to handle this request.
	 * Return the <code>ActionForward</code> instance (if any) returned by the
	 * called <code>Action</code> for further processing.
	 * 
	 * @param request
	 *            The servlet request we are processing
	 * @param response
	 *            The servlet response we are creating
	 * @param action
	 *            The Action instance to be used
	 * @param form
	 *            The ActionForm instance to pass to this Action
	 * @param mapping
	 *            The ActionMapping instance to pass to this Action
	 * @return ActionForward or <code>null</code>
	 * @exception IOException
	 *                if an input/output error occurs
	 * @exception ServletException
	 *                if a servlet exception occurs
	 */
	protected ActionForward processActionPerform(HttpServletRequest request,
			HttpServletResponse response, Action action, ActionForm form,
			ActionMapping mapping) throws IOException, ServletException {

		try {
			return RequestProcessorUtil.processAction(request, response,
					action, form, mapping);
		} catch (Exception e) {
			return processException(request, response, e, form, mapping);
		}
	}

	/**
	 * Set the no-cache headers for all responses, if requested. <strong>NOTE</strong> -
	 * This header will be overridden automatically if a
	 * <code>RequestDispatcher.forward()</code> call is ultimately invoked.
	 * 
	 * @param request
	 *            The servlet request we are processing
	 * @param response
	 *            The servlet response we are creating
	 */
	protected void processNoCache(HttpServletRequest request,
			HttpServletResponse response) {
		if (moduleConfig.getControllerConfig().getNocache()) {
			CacheControl.setCache(response, true);
		}
	}

	/**
	 * If this action is protected by security roles, make sure that the current
	 * user possesses at least one of them. Return <code>true</code> to
	 * continue normal processing, or <code>false</code> if an appropriate
	 * response has been created and processing should terminate.
	 * 
	 * @param request
	 *            The servlet request we are processing
	 * @param response
	 *            The servlet response we are creating
	 * @param mapping
	 *            The mapping we are using
	 * @return true
	 * @exception IOException
	 *                if an input/output error occurs
	 * @exception ServletException
	 *                if a servlet exception occurs
	 */
	protected boolean processRoles(HttpServletRequest request,
			HttpServletResponse response, ActionMapping mapping)
			throws IOException, ServletException {

		try {
			RequestProcessorUtil.assertRoles(mapping.getRoles(), SecurityUtil
					.getPrincipal(request));

			return true;
		} catch (PermissionException pe) {
			throw new ServletException(pe.getMessage());
		}
	}

	/**
	 * @see org.apache.struts.action.RequestProcessor#processForwardConfig(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse,
	 *      org.apache.struts.config.ForwardConfig)
	 */
	protected void processForwardConfig(HttpServletRequest request,
			HttpServletResponse response, ForwardConfig forward)
			throws IOException, ServletException {

		if (forward == null) {
			return;
		}

		String uri = RequestUtils.forwardURL(request, forward);

		if (forward.getRedirect()) {
			response.sendRedirect(RequestProcessorUtil.getForwardURL(response
					.encodeRedirectURL(request.getContextPath() + uri)));
		} else {
			doForward(uri, request, response);
		}
	}

	/**
	 * <p>
	 * Populate the properties of the specified <code>ActionForm</code>
	 * instance from the request parameters included with this request. In
	 * addition, request attribute <code>Globals.CANCEL_KEY</code> will be set
	 * if the request was submitted with a button created by
	 * <code>CancelTag</code>.
	 * </p>
	 * 
	 * @param request
	 *            The servlet request we are processing
	 * @param response
	 *            The servlet response we are creating
	 * @param form
	 *            The ActionForm instance we are populating
	 * @param mapping
	 *            The ActionMapping we are using
	 * 
	 * @exception ServletException
	 *                if thrown by RequestUtils.populate()
	 */
/*
	protected void processPopulate(HttpServletRequest request,
			HttpServletResponse response, ActionForm form, ActionMapping mapping)
			throws ServletException {

		if (form == null) {
			return;
		}

		// Populate the bean properties of this ActionForm instance
		if (log.isDebugEnabled()) {
			log.debug(" Populating bean properties from this request");
		}

		form.setServlet(this.servlet);
		form.reset(mapping, request);

		if (mapping.getMultipartClass() != null) {
			request.setAttribute(org.apache.struts.Globals.MULTIPART_KEY,
					mapping.getMultipartClass());
		}

		ActionContext ctx = new ConcreteActionContext(mapping, form, request,
				response);

		FWRequestUtils.populate(ctx, mapping.getPrefix(), mapping.getSuffix());

		// Set the cancellation request attribute if appropriate
		if ((request.getParameter(Constants.CANCEL_PROPERTY) != null)
				|| (request.getParameter(Constants.CANCEL_PROPERTY_X) != null)) {

			request.setAttribute(org.apache.struts.Globals.CANCEL_KEY, Boolean.TRUE);
		}
	}
*/
}