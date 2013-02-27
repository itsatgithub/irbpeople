/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/PainterHelp.java,v 1.55 2005/09/27 17:08:14 P001002 Exp $
 * $Revision: 1.55 $
 * $Date: 2005/09/27 17:08:14 $
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

package com.cc.framework.ui.painter;

import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import org.apache.commons.logging.LogFactory;
import org.apache.ecs.ConcreteElement;
import org.apache.ecs.ElementContainer;
import org.apache.ecs.Entities;
import org.apache.ecs.html.IMG;

import com.cc.framework.Globals;
import com.cc.framework.adapter.FrameworkAdapterFactory;
import com.cc.framework.adapter.RequestContext;
import com.cc.framework.convert.Converter;
import com.cc.framework.http.HttpUtil;
import com.cc.framework.http.Hyperlink;
import com.cc.framework.http.RequestDecorationType;
import com.cc.framework.http.RequestDecorator;
import com.cc.framework.ui.html.HtmlUtil;
import com.cc.framework.ui.model.ClientEvent;
import com.cc.framework.ui.model.ClientHandler;
import com.cc.framework.ui.model.DesignRule;
import com.cc.framework.ui.model.ImageModel;
import com.cc.framework.ui.model.value.DeferredEnvironment;
import com.cc.framework.util.StringHelp;
import com.cc.framework.util.Util;

/**
 * Utility class for painters
 *
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.55 $
 * @since       1.0
 */
public abstract class PainterHelp {

	/**
	 * Constructor
	 */
	private PainterHelp() {
		super();
	}

	/**
	 * Adds a hidden field for the converter that was used to render the HTML
	 * element
	 * 
	 * @param ctx
	 *            RequestContext
	 * @param element
	 *            The HTML element
	 * @param controlName
	 *            The controls Name - The name used as the request parameter
	 * @param converter
	 *            The converter that was used to render the Control
	 * @return HTML Element
	 */
	public static ConcreteElement appendConverter(RequestContext ctx,
			ConcreteElement element, String controlName, Converter converter) {

		return element;

/* TODO Save converter settings
		if ((controlName != null) && (converter != null)) {
			PropertyMap props = new PropertyMap();
			props.addProperty("name", controlName);
			props.addProperty("cvt", converter.getClass().getName());
			
			if (converter instanceof StatefullConverter) {
				props.addProperty("state", ((StatefullConverter) converter).saveState(ctx));
			}

			return new ElementContainer()
				.addElement(element)
				.addElement(new Input()
					.setType(Input.hidden)
					.setName(Globals.CONVERTER_PARAM)
					.setValue(props.toString()));
		} else {
			return element;
		}
*/
	}
	
	/**
	 * This method searches for all matching rules and
	 * applies the design to the given HTML element
	 *
	 * @param		ctx Jsp Page context
	 * @param		element the HTML element to modify
	 * @param		rules list of rules
	 * @param		bean the bean that holds the values to
	 * 				check
	 * @return		the HTML element
	 */
	public static ConcreteElement applyDesignRules(
		PageContext ctx,
		ConcreteElement element,
		DesignRule[] rules,
		Object bean) {

		if ((rules != null) && (rules.length > 0)) {
			DeferredEnvironment env = new DeferredEnvironment(ctx, bean);

			try {
				for (int i = 0; i < rules.length; i++) {
					if (rules[i].match(env)) {
						rules[i].applyDesign(ctx, element, bean);
					}
				}
			} catch (Exception e) {
				LogFactory.getLog(PainterHelp.class).error(
					"Error while applying design rule!",
					e);
			}
		}

		return element;
	}

	/**
	 * Decorates the url with RequestDecorator settings and appends
	 * the JSESSIONID when URL-rewriting is enabled to track sessions
	 *
	 * <b>Every URL hast to be decorated this way!</b>
	 *
	 * @param		ctx JspPageContext
	 * @param		url the url to encode
	 * @return		encoded url
	 */
	public static String decorateURL(PageContext ctx, String url) {

		if (url == null) {
			// missing url
			return "null";
		}

		StringBuffer buf = new StringBuffer(url);

		// Append URL Decorations
		Hashtable hData =
			RequestDecorator.getValueList(RequestDecorationType.TYPE_URL, ctx);

		boolean first = (url == null) || (url.indexOf('?') == -1);

		if ((null != hData) && !hData.isEmpty()) {
			Enumeration e = hData.keys();

			while (e.hasMoreElements()) {
				String key = (String) e.nextElement();

				if (first) {
					buf.append("?");
					first = false;
				} else {
					buf.append("&");
				}

				buf.append(key).append("=").append(HttpUtil.urlEncode(String.valueOf(hData.get(key))));
			}
		}

		if (ctx.getResponse() instanceof HttpServletResponse) {
			return ((HttpServletResponse) ctx.getResponse()).encodeURL(buf.toString());
		} else {
			return buf.toString();
		}
	}

	/**
	 * Decorates the url with RequestDecorator settings and appends
	 * the JSESSIONID when URL-rewriting is enabled to track sessions
	 *
	 * <b>Every URL hast to be decorated this way!</b>
	 *
	 * @param		ctx JspPageContext
	 * @param		link the hyperlink
	 * @return		encoded url
	 */
	public static String decorateLink(PageContext ctx, Hyperlink link) {

		if (link == null) {
			// missing link
			return "null";
		}

		// Append URL Decorations
		Hashtable hData = RequestDecorator.getValueList(RequestDecorationType.TYPE_URL, ctx);

		if ((null != hData) && !hData.isEmpty()) {
			Enumeration e = hData.keys();

			while (e.hasMoreElements()) {
				String key = (String) e.nextElement();

				link.addQueryParameter(key, hData.get(key));
			}
		}

		if (ctx.getResponse() instanceof HttpServletResponse) {
			return ((HttpServletResponse) ctx.getResponse()).encodeURL(link.toString());
		} else {
			return link.toString();
		}
	}

	/**
	 * Adds the client handler to the button
	 *
	 * @param handler	Collection with ClientHandler
	 * @return	String with the ClientHandlers
	 */
	public static String createScriptHandler(ClientHandler handler) {

		StringBuffer buf = new StringBuffer();

		// get the list with the defined handlers
		ClientEvent[] events = handler.getHandlers();

		// if no handlers are defined return
		if (events.length == 0) {
			return "";
		}

		for (int i = 0; i < events.length; i++) {
			if (!events[i].isExtended()) {
				buf
					.append(" ")
					.append(events[i].getScriptName())
					.append("='")
					.append(handler.getHandler(events[i]))
					.append("'");
			}
		}

		return buf.toString();
	}

	/**
	 * Sets a Script-Handlern for the speciefied control.
	 *
	 * @param	element		The control
	 * @param	handler		The client handler
	 */
	public static void setScriptHandler(ConcreteElement element, ClientHandler handler) {

		if (handler == null) {
			return;
		}

		// get the list with the defined handlers
		ClientEvent[] events = handler.getHandlers();

		for (int i = 0; i < events.length; i++) {
			if (!events[i].isExtended()) {
				if (element.hasAttribute(events[i].getScriptName())) {
					element.removeAttribute(events[i].getScriptName());
				}
				element.addAttribute(events[i].getScriptName(), handler.getHandler(events[i]));
			}
		}
	}

	/**
	 * Sets a Script-Handlern for the speciefied control.
	 *
	 * @param	element		The control
	 * @param	event		The event to set a handler for
	 * @param	handler		The Script text
	 */
	public static void setScriptHandler(ConcreteElement element, ClientEvent event, String handler) {

		if (element.hasAttribute(event.getScriptName())) {
			element.removeAttribute(event.getScriptName());
		}

		if (handler == null) {
			return;
		}

		element.addAttribute(event.getScriptName(), handler);
	}

	/**
	 * Methode encodeURL
	 *
	 * @param	pageContext	PageContext
	 * @param	src	URL to encode
	 * @return	String
	 */
	public static String encodeURL(PageContext pageContext, String src) {
		return ((javax.servlet.http.HttpServletResponse) pageContext.getResponse()).encodeURL(src);
	}

	/**
	 * Processes the Getter-Methode for the speciefied Property on the Bean
	 *
	 * @param   bean		Bean with the specified Property
	 * @param   property	Name of the Property
	 * @return	Object
	 */
	public static Object callPropertyGet(Object bean, String property) {

		if ((bean == null) || (property == null)) {
			return null;
		}

		return Util.getSafeProperty(bean, property);
	}

	/**
	 * Method callPropertyGetStr
	 *
	 * @param	bean	Object
	 * @param	property	Property
	 * @return	String
	 */
	public static String callPropertyGetStr(Object bean, String property) {

		Object value = callPropertyGet(bean, property);

		if (value == null) {
			return null;
		}

		return value.toString();
	}

	/**
	 * Determines the boolean Value of the specified Object
	 *
	 * @param	value	Object
	 * @param	defValue	Default Value
	 * @return	boolean
	 */
	public static boolean toBoolean(Object value, boolean defValue) {
		return (value instanceof Boolean) ? ((Boolean) value).booleanValue() : defValue;
	}

	/**
	 * This method works similar to java.text.MessageFormat with the only difference
	 * that it concatenates ConcreteElement's into an ElementContainer
	 *
	 * @param		pattern Formatting pattern with "{0}" markups
	 * @param		elements The elements that should be placed in the
	 * 				markups
	 * @return		ElementContainer
	 */
	public static ElementContainer formatElements(String pattern, ConcreteElement[] elements) {
		ElementContainer container = new ElementContainer();

		int i1 = 0;
		for (int i = 0; i < elements.length; i++) {

			if (i > 0) {
				container.addElement(Entities.NBSP);
			}

			if (i1 >= 0) {
				int i2 = pattern.indexOf("{" + i + "}");

				if (i2 == -1) {
					container
						.addElement(pattern.substring(i1))
						.addElement(Entities.NBSP);
					i1 = -1;
				} else {
					if (i2 > i1) {
						container
							.addElement(pattern.substring(i1, i2))
							.addElement(Entities.NBSP);
					}

					i1 = i2 + 3;
				}
			}

			container.addElement(elements[i]);
		}

		if (i1 < pattern.length()) {
			container.addElement(Entities.NBSP);
			container.addElement(pattern.substring(i1));
		}

		return container;
	}

	/**
	 * Creates an IMG Tag for the specified Image.
	 * <b>No localization</b> will be done!
	 *
	 * @param	pageContext JSP Page Context
	 * @param	image	ImageModel
	 * @return	IMG-Element
	 */
	public static IMG createImage(PageContext pageContext, ImageModel image) {

		if (image == null) {
			return null;
		} else {
			String src =
				getSource(
					pageContext,
					image.getBase(),
					image.getSource(),
					localeFromName(pageContext, null));

			IMG img = new IMG(src);

			if (image.getAlternate() != null) {
				img.setAlt(HtmlUtil.encodeHtml(localize(pageContext, image.getAlternate())));
			}

			if (image.getTooltip() != null) {
				img.setTitle(HtmlUtil.encodeHtml(localize(pageContext, image.getTooltip())));
			}

			if (image.getHeight() > 0) {
				img.setHeight(image.getHeight());
			}

			if (image.getWidth() > 0) {
				img.setWidth(image.getWidth());
			}

			img.setBorder(0);

			return img;
		}
	}

	/**
	 * Creates an IMG Tag for the specified Image
	 *
	 * @param	pageContext JSP Page Context
	 * @param	image	ImageModel
	 * @return	IMG-Element
	 */
	public static String createImageStr(PageContext pageContext, ImageModel image) {

		if (image == null) {
			return null;
		}

		String src =
			getSource(
				pageContext,
				image.getBase(),
				image.getSource(),
				localeFromName(pageContext, null));

		StringBuffer buf = new StringBuffer()
			.append("<img src='")
			.append(src)
			.append("'");

		if (image.getAlternate() != null) {
			buf
				.append(" alt='")
				.append(HtmlUtil.encodeHtml(localize(pageContext, image.getAlternate())))
				.append("'");
		}

		if (image.getTooltip() != null) {
			buf
				.append(" title='")
				.append(HtmlUtil.encodeHtml(localize(pageContext, image.getTooltip())))
				.append("'");
		}

		if (image.getHeight() > 0) {
			buf
				.append(" height='")
				.append(image.getHeight())
				.append("'");
		} else if (image.getHeight() == -1) {
			buf
				.append(" height='100%'");
		}

		if (image.getWidth() > 0) {
			buf
				.append(" width='")
				.append(image.getWidth())
				.append("'");
		} else if (image.getWidth() == -1) {
			buf
				.append(" width='100%'");
		}

		buf
			.append(" border='0'")
			.append(">");

		return buf.toString();
	}

	/**
	 * Methode getSmartCaption
	 *
	 * @param caption	Caption
	 * @param detail	Detail
	 * @return	String
	 */
	public static String getSmartCaption(String caption, String detail) {
		if (caption == null) {
			return null;
		}

		if (detail != null) {
			return caption;
		}

		int split = caption.indexOf("-");

		if (split == -1) {
			return caption;
		} else {
			return caption.substring(0, split).trim();
		}
	}

	/**
	 * Methode getSmartDetail
	 *
	 * @param caption	Caption
	 * @param detail	Detail
	 * @return	String
	 */
	public static String getSmartDetail(String caption, String detail) {
		if (detail != null) {
			return detail;
		}
		if (caption == null) {
			return null;
		}

		int split = caption.indexOf("-");

		if (split == -1) {
			return null;
		} else {
			return caption.substring(split + 1).trim();
		}
	}

	/**
	 * Localizes a string depending on the localization settings
	 * of the Painter context.
	 *
	 * @param	pageContext JSP Page Context
	 * @param	keyOrString String Literal or Resource Key
	 * @return	localized String
	 */
	public static String localize(PageContext pageContext, String keyOrString) {
		return localize(pageContext, keyOrString, null, localeFromName(pageContext, null), false);
	}

	/**
	 * Localizes a string depending on the localization settings
	 * of the Painter context.
	 *
	 * @param	pageContext JSP Page Context
	 * @param	keyOrString String Literal or Resource Key
	 * @param	arguments MessageFormat arguments or <code>null</code>
	 * @return	localized String
	 */
	public static String localize(PageContext pageContext, String keyOrString, Object[] arguments) {
		return localize(pageContext, keyOrString, arguments, localeFromName(pageContext, null), false);
	}

	/**
	 * Localizes a string depending on the localization settings
	 * of the Painter context.
	 *
	 * @param	pageContext JSP Page Context
	 * @param	keyOrString String Literal or Resource Key
	 * @param	arguments MessageFormat arguments or <code>null</code>
	 * @param	locale Locale or <code>null</code>
	 * @return	localized String
	 */
	public static String localize(PageContext pageContext, String keyOrString, Object[] arguments, Locale locale) {
		return localize(pageContext, keyOrString, arguments, locale, false);
	}

	/**
	 * Localizes a string depending on the localization settings
	 * of the Painter context. A key is searched in the following order:
	 * 1) If no user defined resource bundle is used. The resource
	 * is searched unter the STRUTS_MESSAGES_KEY.<br>
	 * 2) If an user defined resource bundle is used (keq@bunde)
	 * the resource is searched in the spezified resource bundel.<br>
	 * 3) If the key was not found before, the resource is searched in the
	 * framework resource bundel unter the key Globals.MESSAGE.
	 *
	 * @param	pageContext JSP Page Context
	 * @param	keyOrString	The String literal or resource key.
	 * @param	arguments MessageFormat arguments or <code>null</code>
	 * @param	locale Locale or <code>null</code>
	 * @param	returnNull controlls wether to return null or a
	 * 			not present indicator
	 * @return	localized String
	 */
	public static String localize(
		PageContext pageContext,
		String keyOrString,
		Object[] arguments,
		Locale locale,
		boolean returnNull) {

		if ((keyOrString == null) || "".equals(keyOrString)) {
			return keyOrString;
		}

		if (locale == null) {
			// no localization
			if (arguments == null) {
				return keyOrString;
			} else {
				return MessageFormat.format(keyOrString, arguments);
			}
		} else {
			return localizeKey(pageContext, keyOrString, arguments, locale, returnNull);
		}
	}

	/**
	 * Localizes a string depending on the localization settings
	 * of the Painter context. A key is searched in the following order:
	 * 1) If no user defined resource bundle is used. The resource
	 * is searched unter the STRUTS_MESSAGES_KEY.<br>
	 * 2) If an user defined resource bundle is used (keq@bunde)
	 * the resource is searched in the spezified resource bundel.<br>
	 * 3) If the key was not found before, the resource is searched in the
	 * framework resource bundel unter the key Globals.MESSAGE.
	 *
	 * @param	pageContext JSP Page Context
	 * @param	resourceKey	The resource key.
	 * @param	arguments MessageFormat arguments or <code>null</code>
	 * @param	locale Locale or <code>null</code>
	 * @param	returnNull controlls wether to return null or a
	 * 			not present indicator
	 * @return	localized String
	 */
	public static String localizeKey(
		PageContext pageContext,
		String resourceKey,
		Object[] arguments,
		Locale locale,
		boolean returnNull) {

		String str =
			FrameworkAdapterFactory.getAdapter().localizeKey(
				pageContext,
				resourceKey,
				locale,
				returnNull);

		if (str == null) {
			return null;
		} else if (arguments == null) {
			return str;
		} else {
			return MessageFormat.format(str, arguments);
		}
	}

	/**
	 * Returns a framework string resource
	 *
	 * @param	pageContext JSP Page Context
	 * @param	resourceId	Id of the resource/key
	 * @param	arguments MessageFormat arguments or <code>null</code>
	 * @param	locale Locale or <code>null</code>
	 * @param	factories the painter factory stack
	 * @param	returnNull controlls wether to return null or a
	 * 			not present indicator
	 * @return	The value for the resource id
	 */
	public static String getFrameworkString(
		PageContext pageContext,
		String resourceId,
		Object[] arguments,
		Locale locale,
		boolean returnNull,
		PainterFactory[] factories) {

		// try to localize the key based on a resource bundle
		String fwkey = StringHelp.join(resourceId, Globals.MESSAGE, '@');

		String res = localizeKey(pageContext, fwkey, arguments, locale, true);

		// if not found check the resource maps
		if (res == null) {
			// Search for the resource in all the painter factories
			for (int i = 0; (res == null) && (i < factories.length); i++) {
				res = factories[i].getResources().getString(resourceId, arguments, true);
			}
		}

		// resource not found
		if ((res == null) && !returnNull) {
			res = StringHelp.strcat("?", resourceId, "?");
		}

		return res;
	}

	/**
	 * Gets the Locale Object from a Locale Name
	 *
	 * @param		pageContext JSP Page Context
	 * @param		localeName The Name
	 * @return		Locale Object or <code>null</code>
	 */
	public static Locale localeFromName(PageContext pageContext, String localeName) {
		Locale locale =
			FrameworkAdapterFactory.getAdapter().localeFromName(
				pageContext,
				localeName);

		return locale;
	}

	/**
	 * Returns the localized Image resourcename
	 *
	 * @param		pageContext JSP Page Context
	 * @param		base Base Directory or Resourcekey
	 * @param		src Resourcename
	 * @param		locale Locale or <code>null</code>
	 * @return		localized Resourcename
	 */
	public static String getSource(PageContext pageContext, String base, String src, Locale locale) {
		String basedir = localize(pageContext, base, null, locale);

		if (basedir == null) {
			return src;
		} else {
			StringBuffer s = new StringBuffer();

			if (basedir.endsWith("\\") || basedir.endsWith("/")) {
				s.append(basedir).append(src);
			} else {
				s.append(basedir).append("/").append(src);
			}

			return s.toString();
		}
	}
}