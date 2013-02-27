/*
 * $Header: d:/repository/cvs/cc-adapter-struts-1.2/adapter/com/cc/framework/adapter/struts/ActionContextWrapper.java,v 1.7 2005/07/31 11:58:52 P001002 Exp $
 * $Revision: 1.7 $
 * $Date: 2005/07/31 11:58:52 $
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

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import com.cc.framework.adapter.RequestContext;
import com.cc.framework.security.Principal;
import com.cc.framework.util.StringHelp;

/**
 * Wrapper Class for the ActionContext
 *
 * @author     <a href="mailto:gschulz@scc-gmbh.com">Gernot Schulz</a>
 * @version    $Revision: 1.7 $
 * @since      1.0
 */
public abstract class ActionContextWrapper implements ActionContext {

	/**
	 * The ActionContext
	 */
	private ActionContext ctx = null;

	/**
	 * Constructor
	 * @param	ctx	ActionContext
	 */
	public ActionContextWrapper(ActionContext ctx) {
		super();

		this.ctx = ctx;
	}

	/**
	 * @see com.cc.framework.adapter.RequestContext#getNestedContext()
	 */
	public RequestContext getNestedContext() {
		return ctx;
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#consumeStrutsMessages()
	 */
	public void consumeStrutsMessages() {
		ctx.consumeStrutsMessages();
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#addError(String, Throwable)
	 */
	public void addError(String key, Throwable t) {
		ctx.addError(key, t);
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#addErrors(ActionMessages)
	 */
	public void addErrors(ActionMessages errorList) {
		ctx.addErrors(errorList);
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#addMessages(org.apache.struts.action.ActionMessages)
	 */
	public void addMessages(ActionMessages messageList) {
		ctx.addMessages(messageList);
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#addGlobalError(String, Object, Object, Object, Object)
	 */
	public void addGlobalError(String key, Object p1, Object p2, Object p3, Object p4) {
		ctx.addGlobalError(key, p1, p2, p3, p4);
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#addGlobalError(String, Object, Object, Object)
	 */
	public void addGlobalError(String key, Object p1, Object p2, Object p3) {
		ctx.addGlobalError(key, p1, p2, p3);
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#addGlobalError(String, Object, Object)
	 */
	public void addGlobalError(String key, Object p1, Object p2) {
		ctx.addGlobalError(key, p1, p2);
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#addGlobalError(String, Object)
	 */
	public void addGlobalError(String key, Object p1) {
		ctx.addGlobalError(key, p1);
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#addGlobalError(String)
	 */
	public void addGlobalError(String key) {
		ctx.addGlobalError(key);
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#addGlobalMessage(String, Object, Object, Object, Object)
	 */
	public void addGlobalMessage(String key, Object p1, Object p2, Object p3, Object p4) {
		ctx.addGlobalMessage(key, p1, p2, p3, p4);
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#addGlobalMessage(String, Object, Object, Object)
	 */
	public void addGlobalMessage(String key, Object p1, Object p2, Object p3) {
		ctx.addGlobalMessage(key, p1, p2, p3);
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#addGlobalMessage(String, Object, Object)
	 */
	public void addGlobalMessage(String key, Object p1, Object p2) {
		ctx.addGlobalMessage(key, p1, p2);
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#addGlobalMessage(String, Object)
	 */
	public void addGlobalMessage(String key, Object p1) {
		ctx.addGlobalMessage(key, p1);
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#addGlobalMessage(String)
	 */
	public void addGlobalMessage(String key) {
		ctx.addGlobalMessage(key);
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#addPropertyError(String, String, Object, Object, Object, Object)
	 */
	public void addPropertyError(String property, String key, Object p1, Object p2, Object p3, Object p4) {
		ctx.addPropertyError(property, key, p1, p2, p3, p4);
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#addPropertyError(String, String, Object, Object, Object)
	 */
	public void addPropertyError(String property, String key, Object p1, Object p2, Object p3) {
		ctx.addPropertyError(property, key, p1, p2, p3);
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#addPropertyError(String, String, Object, Object)
	 */
	public void addPropertyError(String property, String key, Object p1, Object p2) {
		ctx.addPropertyError(property, key, p1, p2);
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#addPropertyError(String, String, Object)
	 */
	public void addPropertyError(String property, String key, Object p1) {
		ctx.addPropertyError(property, key, p1);
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#addPropertyError(String, String)
	 */
	public void addPropertyError(String property, String key) {
		ctx.addPropertyError(property, key);
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#addPropertyMessage(String, String, Object, Object, Object, Object)
	 */
	public void addPropertyMessage(String property, String key, Object p1, Object p2, Object p3, Object p4) {
		ctx.addPropertyMessage(property, key, p1, p2, p3, p4);
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#addPropertyMessage(String, String, Object, Object, Object)
	 */
	public void addPropertyMessage(String property, String key, Object p1, Object p2, Object p3) {
		ctx.addPropertyMessage(property, key, p1, p2, p3);
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#addPropertyMessage(String, String, Object, Object)
	 */
	public void addPropertyMessage(String property, String key, Object p1, Object p2) {
		ctx.addPropertyMessage(property, key, p1, p2);
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#addPropertyMessage(String, String, Object)
	 */
	public void addPropertyMessage(String property, String key, Object p1) {
		ctx.addPropertyMessage(property, key, p1);
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#addPropertyMessage(String, String)
	 */
	public void addPropertyMessage(String property, String key) {
		ctx.addPropertyMessage(property, key);
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#form()
	 */
	public ActionForm form() {
		return ctx.form();
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#validateForm()
	 */
	public void validateForm() {
		ctx.validateForm();
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#forward(ActionForward)
	 */
	public void forward(ActionForward forward) {
		ctx.forward(forward);
	}


	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#forwardByName(java.lang.String, java.lang.Object[])
	 */
	public void forwardByName(String forwardName, Object[] params) {
		ctx.forwardByName(forwardName, params);
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#forwardByName(String, Object, Object, Object)
	 */
	public void forwardByName(String forwardName, Object p1, Object p2, Object p3) {
		ctx.forwardByName(forwardName, p1, p2, p3);
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#forwardByName(String, Object, Object)
	 */
	public void forwardByName(String forwardName, Object p1, Object p2) {
		ctx.forwardByName(forwardName, p1, p2);
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#forwardByName(String, Object)
	 */
	public void forwardByName(String forwardName, Object p1) {
		ctx.forwardByName(forwardName, p1);
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#forwardByName(String)
	 */
	public void forwardByName(String forwardName) {
		ctx.forwardByName(forwardName);
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#forwardToAction(String)
	 */
	public void forwardToAction(String actionName) {
		ctx.forwardToAction(actionName);
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#forwardToInput()
	 */
	public void forwardToInput() {
		ctx.forwardToInput();
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#forwardToResponse()
	 */
	public void forwardToResponse() {
		ctx.forwardToResponse();
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#getErrors()
	 */
	public ActionMessages getErrors() {
		return ctx.getErrors();
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#getForward()
	 */
	public ActionForward getForward() {
		return ctx.getForward();
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#getMessages()
	 */
	public ActionMessages getMessages() {
		return ctx.getMessages();
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#hasErrors()
	 */
	public boolean hasErrors() {
		return ctx.hasErrors();
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#hasMessages()
	 */
	public boolean hasMessages() {
		return ctx.hasMessages();
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#invalidDialogContext(String)
	 */
	public void invalidDialogContext(String messageKey) {
		ctx.invalidDialogContext(messageKey);
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#isHandeled()
	 */
	public boolean isHandeled() {
		return ctx.isHandeled();
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#mapping()
	 */
	public ActionMapping mapping() {
		return ctx.mapping();
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#request()
	 */
	public HttpServletRequest request() {
		return ctx.request();
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#response()
	 */
	public HttpServletResponse response() {
		return ctx.response();
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#session()
	 */
	public HttpSession session() {
		return ctx.session();
	}

	/**
	 * @see com.cc.framework.adapter.struts.ActionContext#getPrincipal()
	 */
	public Principal getPrincipal() {
		return ctx.getPrincipal();
	}

	/**
	 * @see com.cc.framework.adapter.RequestContext#getLocale()
	 */
	public Locale getLocale() {
		return ctx.getLocale();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return StringHelp.strcat("ctx(", ctx.toString(), ")");
	}
}