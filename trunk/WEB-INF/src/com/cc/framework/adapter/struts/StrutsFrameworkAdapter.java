/**
 * $Header: d:/repository/cvs/cc-adapter-struts-1.2/adapter/com/cc/framework/adapter/struts/StrutsFrameworkAdapter.java,v 1.14 2005/09/27 12:56:57 P001002 Exp $
 * $Revision: 1.14 $
 * $Date: 2005/09/27 12:56:57 $
 *
 * ====================================================================
 *
 * Copyright (c) 2002 SCC Informationssysteme GmbH. All rights
 * reserved.
 *
 * ====================================================================
 */

package com.cc.framework.adapter.struts;

import java.text.MessageFormat;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.taglib.TagUtils;
import org.apache.struts.taglib.html.Constants;
import org.apache.struts.taglib.html.FormTag;
import org.apache.struts.util.MessageResources;

import com.cc.framework.Globals;
import com.cc.framework.adapter.FrameworkAdapter;
import com.cc.framework.common.Severity;
import com.cc.framework.http.HttpScope;
import com.cc.framework.http.Hyperlink;
import com.cc.framework.taglib.TagHelp;
import com.cc.framework.ui.control.ControlAction;
import com.cc.framework.ui.model.MessageDataModel;
import com.cc.framework.ui.model.imp.StrutsMessageDataModelImp;
import com.cc.framework.ui.painter.ActionPainter;
import com.cc.framework.ui.painter.PainterContext;
import com.cc.framework.util.Formatter;
import com.cc.framework.util.StringHelp;

/**
 * Framework Adapter for the Jakarta Struts Framework
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.14 $
 */
public class StrutsFrameworkAdapter implements FrameworkAdapter {

	/**
	 * Log
	 */
	private static transient Log log = LogFactory.getLog(StrutsFrameworkAdapter.class);

	/**
	 * The servlet mapping of the frontcontroller servlet
	 */
	private String servletMapping = "*.do";

	/**
	 * Sets the frontcontrollers servlet mapping. Call this
	 * method if your servlet mapping differs from "*.do"
	 * 
	 * @param		mapping Servlet mapping string
	 */
	public void setServletMapping(String mapping) {
		this.servletMapping = mapping;
	}	

	/**
	 * @see com.cc.framework.adapter.FrameworkAdapter#applyServletMapping(java.lang.String)
	 */
	public String applyServletMapping(String url) {

		StringBuffer buf = new StringBuffer();

		String params = null;

		if (url == null) {
			url = "";
		} else {
			int pos = url.indexOf('?');
			if (pos != -1) {
				params	= url.substring(pos);
				url		= url.substring(0, pos);
			} 
		}

		// Replace the url patterns '*' markup with the given url
		int pos = servletMapping.indexOf('*');
		if (pos == -1) {
			buf.append(url).append(servletMapping);
		} else if (pos == 0) {
			buf.append(url).append(servletMapping.substring(1));
		} else {
			buf.append(servletMapping.substring(0, pos)).append(url).append(servletMapping.substring(pos + 1));
		}

		// add the optional request parameters
		if (params != null) {
			buf.append(params);
		}

		return buf.toString();
	}

	/**
	 * Adds the a transaction token (if any) to the given Parameter List.
	 * The Transaction token is used to track form re-submissions.
	 * 
	 * @param		pageContext Search the context's scopes for the
	 * 				resources.
	 * @param		link Hyperlink
	 */
	public void addTransactionToken(PageContext pageContext, Hyperlink link) {

		HttpSession session = pageContext.getSession();

		if (session != null) {
			String token = token = (String) session
					.getAttribute(org.apache.struts.Globals.TRANSACTION_TOKEN_KEY);

			if (token != null) {
				link.addQueryParameter(Constants.TOKEN_KEY, token);
			}
		}
	}
	
	/**
	 * Returns the appropriate MessageResources object for the current struts
	 * module and the given bundle.
	 * 
	 * @param pageContext
	 *            Search the context's scopes for the resources.
	 * @param bundle
	 *            The bundle name to look for. If this is <code>null</code>,
	 *            the default bundle name is used.
	 * @return MessageResources The bundle's resources stored in some scope.
	 */
	protected MessageResources retrieveMessageResources(
		PageContext pageContext,
		String bundle) {

		MessageResources resources = null;

		if (bundle == null) {
			bundle = org.apache.struts.Globals.MESSAGES_KEY;
		}

		if (resources == null) {
			resources =
				(MessageResources) pageContext.getAttribute(
					bundle,
					PageContext.REQUEST_SCOPE);
		}

		if (resources == null) {
			ModuleConfig moduleConfig =
				TagUtils.getInstance().getModuleConfig(pageContext);
			resources =
				(MessageResources) pageContext.getAttribute(
					bundle + moduleConfig.getPrefix(),
					PageContext.APPLICATION_SCOPE);
		}

		if (resources == null) {
			resources =
				(MessageResources) pageContext.getAttribute(
					bundle,
					PageContext.APPLICATION_SCOPE);
		}

		return resources;
	}

	/**
	 * @see com.cc.framework.adapter.FrameworkAdapter#localizeKey(javax.servlet.jsp.PageContext, java.lang.String, java.util.Locale, boolean)
	 */
	public String localizeKey(
		PageContext pageContext,
		String resourceKey,
		Locale locale,
		boolean returnNull) {

		if (resourceKey == null) {
			return null;
		}

		// The resource cey kan be of the following format:
		// (1) #stringliteral
		// (2) key@bundle#arg0#arg1#arg2#...

		// Check if the key should be interpreted as a
		// string literal (format 1)
		if (resourceKey.startsWith("#")) {
			return resourceKey.substring(1);
		}
			
		// The key is in format 2 -> lokalize it from a
		// property resource bundle

		// Localize the given resource key
		String translated		= null;

		// search the resource bundle
		String key				= null;
		String bundle			= null;
		String arg				= null;

		int bundlepos			= resourceKey.indexOf("@");
		int argsPos				= resourceKey.indexOf("#");

		if (bundlepos == -1) {
			if (argsPos == -1) {
				key		= resourceKey.toString();
			} else {
				key		= resourceKey.substring(0, argsPos);
				arg		= resourceKey.substring(argsPos + 1, resourceKey.length());
			}
		} else {
			if (argsPos == -1) {
				key		= resourceKey.substring(0,  bundlepos);
				bundle	= resourceKey.substring(bundlepos + 1, resourceKey.length());
			} else {
				key		= resourceKey.substring(0,  bundlepos);
				bundle	= resourceKey.substring(bundlepos + 1, argsPos);
				arg		= resourceKey.substring(argsPos + 1, resourceKey.length());
			}
		}

		if (Globals.MESSAGE.equals(bundle)) {
			// Search the default application resource bundle first. So the
			// developer gets the chance to overwrite framework resources
			// in his application resource bundle
			MessageResources res = retrieveMessageResources(pageContext, null);

			if ((res != null) && (res.isPresent(locale, key))) {
				translated = res.getMessage(locale, key);
			}
		}

		if (translated == null) {
			// get the user defined messages resource bundle
			MessageResources res = retrieveMessageResources(pageContext, bundle);

			if (res != null) {
				translated = res.getMessage(locale, key);
			}
		}

		if ((translated != null) && (arg != null)) {
			Object[] args = StringHelp.split(arg, "#");
			translated = MessageFormat.format(translated, args);
		}

		// resource not found
		if ((translated == null) && !returnNull) {
			// Write the missing resource key to the log.
			// Just set the log configuration for
			// com.cc.framework.adapter.struts.StrutsFrameworkAdapter
			// to a seperate file any you will get an empty property file
			// with all semissing properties!
			log.debug(key + "=");
			
			translated = StringHelp.strcat("?", resourceKey, "?");
		}

		return translated;
	}

	/**
	 * Return the form action converted into an action mapping path.  The
	 * value of the <code>action</code> property is manipulated as follows in
	 * computing the name of the requested mapping:
	 * <ul>
	 * <li>Any filename extension is removed (on the theory that extension
	 *     mapping is being used to select the controller servlet).</li>
	 * <li>If the resulting value does not start with a slash, then a
	 *     slash is prepended.</li>
	 * </ul>
	 * @param	action	Action
	 * @return	String
	 */
	private static String getActionMappingName(String action) {

		String value	= action;
		int    question	= action.indexOf("?");

		if (question >= 0) {
			value	= value.substring(0, question);
		}

		int slash	= value.lastIndexOf("/");
		int period	= value.lastIndexOf(".");

		if ((period >= 0) && (period > slash)) {
			value	= value.substring(0, period);
		}

		if (value.startsWith("/")) {
			return (value);
		} else {
			return ("/" + value);
		}

	}

	/**
	 * @see com.cc.framework.adapter.FrameworkAdapter#getActionMappingURL(javax.servlet.jsp.PageContext, java.lang.String)
	 */
	public String getActionMappingURL(PageContext pageContext, String action) {

		if (action == null) {
			return "#";
		}

		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		StringBuffer value = new StringBuffer(request.getContextPath());
		ModuleConfig config =
			(ModuleConfig) pageContext.getRequest().getAttribute(org.apache.struts.Globals.MODULE_KEY);
		if (config != null) {
			value.append(config.getPrefix());
		}

		// Use our servlet mapping, if one is specified
		String servletMapping =
			(String) pageContext.getAttribute(org.apache.struts.Globals.SERVLET_KEY, PageContext.APPLICATION_SCOPE);
		if (servletMapping != null) {
			String queryString = null;
			int question = action.indexOf("?");

			if (question >= 0) {
				queryString = action.substring(question);
			}

			String actionMapping = getActionMappingName(action);

			if (servletMapping.startsWith("*.")) {
				value.append(actionMapping);
				value.append(servletMapping.substring(1));
			} else if (servletMapping.endsWith("/*")) {
				value.append(servletMapping.substring(0, servletMapping.length() - 2));
				value.append(actionMapping);
			} else if (servletMapping.equals("/")) {
				value.append(actionMapping);
			}

			if (queryString != null) {
				value.append(queryString);
			}
		} else {
			// Otherwise, assume extension mapping is in use and extension is
			// already included in the action property


			if (!action.startsWith("/")) {
				value.append("/");
			}
			value.append(action);
		}

		// Return the completed value
		return (value.toString());
	}

	/**
	 * @see com.cc.framework.adapter.FrameworkAdapter#localeFromName(javax.servlet.jsp.PageContext, java.lang.String)
	 */
	public Locale localeFromName(PageContext pageContext, String localeName) {
		Locale locale = null;

		if (localeName == null) {
			Object v = TagHelp.lookup(pageContext, Globals.LOCALENAME_KEY, HttpScope.ANY);

			if (v != null) {
				localeName = String.valueOf(v);
			}
		}

		if (localeName == null) {
			locale = null;
		} else if ("true".equalsIgnoreCase(localeName)) {
			// Use the Struts locale to localize Strings
			try {
				locale = (Locale) pageContext.getSession().getAttribute(org.apache.struts.Globals.LOCALE_KEY);
			} catch (IllegalStateException e) {
				// Invalidated session
			}

			if (locale == null) {
				locale	= Locale.getDefault();
			}
		} else if ("false".equalsIgnoreCase(localeName)) {
			locale = null;
		} else {
			// Lokalisierung anhand der angegebenen Ländereinstellung
			locale = Formatter.parseLocale(localeName);
		}

		return locale;
	}

	/**
	 * Gets the default Action which should be used to forward
	 * requests from this control. This action is used when the
	 * action-property of the control is not set
	 * 
	 * @param		pageContext The JSP Page context
	 * @param		taghandler The JSP Tag
	 * @return		String or <code>null</code>
	 */
	public String getControlAction(PageContext pageContext, TagSupport taghandler) {

		// search the Struts-Form Tag
		FormTag strutsForm	= (FormTag) TagSupport.findAncestorWithClass(taghandler, FormTag.class);

		if (strutsForm != null) {
			return strutsForm.getAction();
		}

		// No form found!
		return null;
	}

	/**
	 * Does a lookup for the specified Java bean
	 * 
	 * @param pageContext
	 *            JSP Page Context
	 * @param taghandler
	 *            The Tag Handler that calls this method
	 * @param name
	 *            The Bean's name
	 * @param property
	 *            The property
	 * @param scope
	 *            The scope where the bean is stored
	 * @return Bean
	 * @throws Exception
	 *             Is thrown in case of an error
	 */
	public Object lookupBean(
		PageContext pageContext,
		TagSupport taghandler,
		String name,
		String property,
		HttpScope scope) throws Exception {

		return lookupBean(pageContext, name, property, scope);
	}

	/**
	 * @see com.cc.framework.adapter.FrameworkAdapter#lookupBean(javax.servlet.jsp.PageContext, java.lang.String, java.lang.String, com.cc.framework.http.HttpScope)
	 */
	public Object lookupBean(
		PageContext pageContext,
		String name,
		String property,
		HttpScope scope) throws Exception {

		if ((name == null) && (property == null)) {
			return null;
		}

		if (name == null) {
			name = org.apache.struts.taglib.html.Constants.BEAN_KEY;
		}

		return TagHelp.lookup(pageContext, name, property, scope);
	}

	/**
	 * @see com.cc.framework.adapter.FrameworkAdapter#getMessages(javax.servlet.jsp.PageContext, com.cc.framework.common.Severity)
	 */
	public MessageDataModel getMessages(
		PageContext pageContext,
		Severity severity)
		throws Exception {

		StrutsMessageDataModelImp dm = new StrutsMessageDataModelImp();
		dm.load(severity, pageContext);

		return dm;
	}

	/**
	 * @see com.cc.framework.adapter.FrameworkAdapter#hasMessages(javax.servlet.jsp.PageContext, com.cc.framework.common.Severity, java.lang.String)
	 */
	public boolean hasMessages(
		PageContext pageContext,
		Severity severity,
		String property) {

		if (pageContext.getSession() == null) {
			// No valid user session
			return false;
		}

		String collectionKey =
			Severity.ERROR.equals(severity)
				? Globals.ERRORS_SAVE_KEY
				: Globals.MESSAGES_SAVE_KEY;

		ActionMessages collection =
			(ActionMessages) pageContext.getSession().getAttribute(collectionKey);

		if (collection == null) {
			return false;
		}

		return collection.size(property) > 0;
	}

	/**
	 * @see com.cc.framework.adapter.FrameworkAdapter#createActionPainter(com.cc.framework.ui.painter.PainterContext, com.cc.framework.ui.control.ControlAction)
	 */
	public ActionPainter createActionPainter(
		PainterContext ctx,
		ControlAction action) {

		return new ActionPainter(ctx, action);
	}
}
