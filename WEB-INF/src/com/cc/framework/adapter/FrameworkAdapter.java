/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/adapter/FrameworkAdapter.java,v 1.9 2005/09/27 14:06:23 P001002 Exp $
 * $Revision: 1.9 $
 * $Date: 2005/09/27 14:06:23 $
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

import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import com.cc.framework.common.Severity;
import com.cc.framework.http.HttpScope;
import com.cc.framework.http.Hyperlink;
import com.cc.framework.ui.control.ControlAction;
import com.cc.framework.ui.model.MessageDataModel;
import com.cc.framework.ui.painter.ActionPainter;
import com.cc.framework.ui.painter.PainterContext;

/**
 * This adapter interface contains all methods a host
 * framework (like struts) must provide for the common-controls
 * framework
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.9 $
 */
public interface FrameworkAdapter {

	/**
	 * Gets the Locale Object from a Locale Name
	 *
	 * @param		pageContext JSP Page Context
	 * @param		localeName The Name
	 * @return		Locale Object or <code>null</code>
	 */
	public Locale localeFromName(PageContext pageContext, String localeName);

	/**
	 * Localizes a string depending on the localization settings
	 * of the Painter context. A key is searched in the following order:
	 * <ol>
	 *   <li>If no user defined resource bundle is used. The resource
	 *       is searched unter the STRUTS_MESSAGES_KEY.</li>
	 *   <li>If an user defined resource bundle is used (keq@bunde)
	 *       the resource is searched in the spezified resource bundel.</li>
	 *   <li>If the key was not found before, the resource is searched in the
	 *       framework resource bundel unter the key Globals.MESSAGE.</li>
	 * </ol>
	 *
	 * @param	pageContext JSP Page Context
	 * @param	resourceKey	The resource key.
	 * @param	locale Locale or <code>null</code>
	 * @param	returnNull controlls wether to return null or a
	 * 			not present indicator
	 * @return	localized String
	 */
	public String localizeKey(
		PageContext pageContext,
		String resourceKey,
		Locale locale,
		boolean returnNull);

	/**
	 * Checks for framework messages
	 * 
	 * @param	pageContext JSP Page Context
	 * @param	severity Severity
	 * @param	property Property name or <code>null</code>
	 * @return	boolean
	 */
	public boolean hasMessages(
		PageContext pageContext,
		Severity severity,
		String property);

	/**
	 * Returns the Framework Messages
	 *  
	 * @param	pageContext JSP Page Context
	 * @param	severity Severity
	 * @return	MessageDataModel
	 * @throws	Exception If an error occurs while loading the
	 * 			Message List
	 */
	public MessageDataModel getMessages(
		PageContext pageContext,
		Severity severity)
		throws Exception;

	/**
	 * This Method maps an Action identifier to an URL
	 *
	 * @param	pageContext	PageContext
	 * @param	action		Action
	 * @return	String
	 * 
	 * see org.apache.struts.util.RequestUtils#getActionMappingURL(String, PageContext)
	 */
	public String getActionMappingURL(PageContext pageContext, String action);

	/**
	 * Applies the Frontcontrollers servlet mapping to the given url
	 * 
	 * @param		url the uer to apply the servlet mapping to (eg. "/main")
	 * @return		mapped url (eg. "/main.do")
	 */
	public String applyServletMapping(String url);

	/**
	 * Adds the a transaction token (if any) to the given Parameter List.
	 * The Transaction token is used to track form re-submissions.
	 * 
	 * @param		pageContext Search the context's scopes for the
	 * 				resources.
	 * @param		link Hyperlink
	 */
	public void addTransactionToken(PageContext pageContext, Hyperlink link);

	/**
	 * Gets the default Action which should be used to forward
	 * requests from this control. This action is used when the
	 * action-property of the control is not set
	 * 
	 * @param	pageContext JSP Page Context
	 * @param	taghandler The Controls JSP-Tag
	 * @return	String or <code>null</code>
	 * 
	 */
	public String getControlAction(
		PageContext pageContext,
		TagSupport taghandler);

	/**
	 * Lookup a Javabean for use in a jsp tag handler
	 * 
	 * @param		pageContext jsp page context
	 * @param		name Name of the bean
	 * @param		property Name of a beans property
	 * @param		scope the scope where the bean should be located
	 * @return		Java Bean or <code>null</code>
	 * @throws		Exception is thrown in cas of an error
	 */
	public Object lookupBean(
		PageContext pageContext,
		String name,
		String property,
		HttpScope scope)
		throws Exception;

	/**
	 * Creates an ActionPainter. The Painter is used by Controls to
	 * render Actions into the user interface
	 *
	 * @param	ctx The Painter Context
	 * @param	action	ControlAction
	 * @return	ActionPainter
	 */
	public ActionPainter createActionPainter(PainterContext ctx, ControlAction action);
}
