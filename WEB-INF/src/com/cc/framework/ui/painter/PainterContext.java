/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/PainterContext.java,v 1.47 2005/08/09 14:13:17 P001002 Exp $
 * $Revision: 1.47 $
 * $Date: 2005/08/09 14:13:17 $
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
import java.util.Hashtable;
import java.util.Locale;
import java.util.Stack;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import org.apache.ecs.html.IMG;
import org.apache.ecs.html.Input;

import com.cc.framework.Globals;
import com.cc.framework.adapter.RequestContext;
import com.cc.framework.convert.Converter;
import com.cc.framework.convert.ConverterException;
import com.cc.framework.convert.ConverterHelp;
import com.cc.framework.security.Principal;
import com.cc.framework.security.SecurityUtil;
import com.cc.framework.ui.Color;
import com.cc.framework.ui.control.Control;
import com.cc.framework.ui.control.ControlActionDef;
import com.cc.framework.ui.control.ControlValuePath;
import com.cc.framework.ui.html.HtmlUtil;
import com.cc.framework.ui.model.ImageModel;
import com.cc.framework.ui.model.imp.ImageModelImp;
import com.cc.framework.util.StringHelp;

/**
 * This class encapsulates all the necessary objects for painting the control.
 * 
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version $Revision: 1.47 $
 * @since 1.2
 */
public class PainterContext implements RequestContext {

	/**
	 * PageContext of the JSP-Page
	 */
	private PageContext pageContext = null;

	/**
	 * Instance of the Control
	 */
	private Control control = null;

	/**
	 * The Locale for this Painter
	 */
	private Locale locale = null;

	/**
	 * The nesting Painter Context when the actual control is nested within
	 * another control eg Formelements
	 */
	private PainterContext parentContext = null;

	/**
	 * The Principal Objekt.
	 */
	private Principal principal = null;

	/**
	 * The Painter Factory
	 */
	private PainterFactory factory = null;

	/**
	 * The Painter Factory Stack
	 */
	private PainterFactory[] factories = null;

	/**
	 * Attributes
	 */
	private Hashtable attributes = null;

	/**
	 * Stack for saving the whole Attribute collection
	 */
	private Stack attributesSave = null;

	// -------------------------------
	// methods
	// -------------------------------

	/**
	 * Constructor
	 * 
	 * @param pageContext
	 *            the Jsp-PageContext
	 * @param control
	 *            The selected Control
	 */
	public PainterContext(PageContext pageContext, Control control) {
		super();

		this.factory = null;
		this.factories = null;
		this.control = control;
		this.pageContext = pageContext;
		this.principal = SecurityUtil.getPrincipal((HttpServletRequest) pageContext.getRequest());
	}

	/**
	 * Retrieves the painter context stack for the current request. The context
	 * stack is used to nest control painters.
	 * 
	 * @return painter context stack
	 */
	protected Stack getContextStack() {
		Stack contextStack = (Stack) pageContext.getRequest().getAttribute(Globals.PAINTERCONEXT_STACK_KEY);

		if (contextStack == null) {
			contextStack = new Stack();
			pageContext.getRequest().setAttribute(Globals.PAINTERCONEXT_STACK_KEY, contextStack);
		}

		return contextStack;
	}

	/**
	 * The painter context has to be opened before it can be used. It
	 * initializes some internal variables
	 */
	public void open() {
		Stack contextStack = getContextStack();

		if (!contextStack.isEmpty()) {
			parentContext = (PainterContext) contextStack.peek();
		}

		contextStack.push(this);

		initLocale();
	}

	/**
	 * After the painter context has been used it has to be closed. This will
	 * remove some internal variables vrom the request
	 */
	public void close() {
		getContextStack().pop();

		locale = null;
	}

	/**
	 * Retrieves the name of the HTML element. This could be a path name when
	 * the control is nested within another control
	 * 
	 * @return The name to use for the <b>name</b>-attribute of a HTML element
	 */
	public String getElementName() {
		/*
		 * Future implementation creates a hirachical name for nested list
		 * controls
		 * 
		 * String prefix = null; String name = null; String index = null;
		 * 
		 * if (control.getProperty() != null) {
		 * 
		 * if (parentContext != null) { prefix = parentContext.getElementName(); }
		 * 
		 * name = control.getProperty(); } else { name = control.getName(); }
		 * 
		 * if (attributes.containsKey(PainterContextAtributes.ATTRIBUTE_ROW)) {
		 * index =
		 * attributes.getProperty(PainterContextAtributes.ATTRIBUTE_ROW); }
		 * 
		 * StringBuffer path = new StringBuffer();
		 * 
		 * if (prefix != null) { path.append(prefix); }
		 * 
		 * if (name != null) { if (prefix != null) { path.append("."); }
		 * 
		 * path.append(name); }
		 * 
		 * if (index != null) { path .append("[") .append(index) .append("]"); }
		 * 
		 * if (path.length() == 0) { return null; } else { return
		 * path.toString(); }
		 */

		String name = null;

		if ((control.getProperty() != null) && (parentContext != null) &&
				parentContext.hasAttribute(PainterContextAtributes.ROWKEY, true)) {

			// The control is nested inside a row of a ListControl.
			// So we have to access the control through the Datamodel
			// of the ListControl and the current row bean
			ControlValuePath path = new ControlValuePath(parentContext.getElementName());
			path.setProperty(ControlValuePath.KEY_TYPE, ControlValuePath.TYPE_TEXT);
			path.setProperty(ControlValuePath.KEY_ROW, getAttribute(PainterContextAtributes.ROWKEY, true));
			path.setProperty(ControlValuePath.KEY_COLUMN, control.getProperty());

			name = path.toString();
		} else {
			if (control.getProperty() != null) {
				name = control.getProperty();
			} else {
				name = control.getName();
			}
		}

		return name;
	}

	/**
	 * Sets the locale configuration for this Painter Context. Search Path:
	 * <ol>
	 * <li>the control</li>
	 * <li>the parent control</li>
	 * <li>the page scope</li>
	 * <li>the request scope</li>
	 * <li>the session scope</li>
	 * <li>the session application scope</li>
	 * </ol>
	 */
	protected void initLocale() {
		String localeName = control.getLocaleName();

		if ((localeName != null) || (parentContext == null)) {
			locale = PainterHelp.localeFromName(pageContext, localeName);
		} else {
			locale = parentContext.getLocale();
		}
	}

	/**
	 * Sets the painter factory
	 * 
	 * @param factory
	 *            Painter factory
	 * @param factories
	 *            The factory stack
	 */
	public void setFactory(PainterFactory factory, PainterFactory[] factories) {
		this.factory = factory;
		this.factories = factories;
	}

	/**
	 * Gets the Page Context
	 * 
	 * @return PageContext
	 */
	public PageContext getPageContext() {
		return pageContext;
	}

	/**
	 * @see com.cc.framework.adapter.RequestContext#getNestedContext()
	 */
	public RequestContext getNestedContext() {
		// Not aplicable
		return null;
	}

	/**
	 * @see com.cc.framework.adapter.RequestContext#request()
	 */
	public HttpServletRequest request() {
		return (HttpServletRequest) pageContext.getRequest();
	}

	/**
	 * @see com.cc.framework.adapter.RequestContext#response()
	 */
	public HttpServletResponse response() {
		return (HttpServletResponse) pageContext.getResponse();
	}

	/**
	 * @see com.cc.framework.adapter.RequestContext#session()
	 */
	public HttpSession session() {
		return pageContext.getSession();
	}

	/**
	 * @see com.cc.framework.adapter.RequestContext#getPrincipal()
	 */
	public Principal getPrincipal() {
		return principal;
	}

	/**
	 * Returns the HttpSession
	 * 
	 * @return The HttpSession or <code>null</code>
	 * @deprecated use session()
	 */
	public HttpSession getSession() {
		return pageContext.getSession();
	}

	/**
	 * @return HttpServletRequest
	 * @deprecated use request()
	 */
	public HttpServletRequest getRequest() {
		return (HttpServletRequest) pageContext.getRequest();
	}

	/**
	 * @return HttpServletResponse
	 * @deprecated use response()
	 */
	public HttpServletResponse getResponse() {
		return (HttpServletResponse) pageContext.getResponse();
	}

	/**
	 * Uses a Bean Converter to convert the given object to a String
	 * representation.
	 * 
	 * @param converter
	 *            The converter to use or <code>null</code> to use a default
	 *            Converter
	 * @param value
	 *            The Bean to convert
	 * @return String or <code>null</code>
	 * @throws ConverterException
	 *             Is thrown when a conversion exception occurs
	 */
	public String getAsString(Converter converter, Object value)
			throws ConverterException {
		return ConverterHelp.getAsString(this, converter, value);
	}

	/**
	 * Decorates the url with RequestDecorator settings and appends the
	 * JSESSIONID when URL-rewriting is enabled to track sessions
	 * 
	 * <b>Every URL hast to be decorated this way!</b>
	 * 
	 * @param url
	 *            the url to encode
	 * @return encoded url
	 */
	public String decorateURL(String url) {
		return PainterHelp.decorateURL(getPageContext(), url);
	}

	/**
	 * Returns the base directory used for resource by the Painterfactory
	 * 
	 * @return The web resource directory
	 */
	public String getResourceDir() {
		return factory.getResources().getResourceDir();
	}

	/**
	 * Returns a string resource
	 * 
	 * @param stringOrKey
	 *            Id of the resource/key
	 * @return The value for the resource id
	 */
	public String getStringResource(String stringOrKey) {
		// first check the application properties
		String res = PainterHelp.localize(pageContext, stringOrKey, null,
				getLocale(), true);

		// if not found check the resource map
		if (res == null) {
			// Search for the resource in all the painter factories
			for (int i = 0; (res == null) && (i < factories.length); i++) {
				res = factories[i].getResources().getString(stringOrKey, false);
			}
		}

		return res;
	}

	/**
	 * Returns a framework string resource
	 * 
	 * @param resourceId
	 *            Id of the resource/key
	 * @return The value for the resource id
	 */
	public String getFrameworkString(String resourceId) {
		return getFrameworkString(resourceId, null);
	}

	/**
	 * Returns a framework string resource
	 * 
	 * @param resourceId
	 *            Id of the resource/key
	 * @param arguments
	 *            MessageFormat arguments
	 * @return The value for the resource id
	 */
	public String getFrameworkString(String resourceId, Object[] arguments) {

		// first let the control handle string resources
		String res = getControl().getFrameworkString(resourceId, arguments, getLocale());

		if (res == null) {
			res = PainterHelp.getFrameworkString(pageContext, resourceId,
					arguments, getLocale(), false, factories);
		}

		return res;
	}

	/**
	 * Retrieves an image for the specified Id
	 * 
	 * @param resourceId
	 *            Image Code
	 * @return The Image or <code>null</code>
	 */
	public ImageModel getImage(String resourceId) {
		ImageModel img = null;

		// Search for the resource in all the painter factories
		for (int i = 0; (img == null) && (i < factories.length); i++) {
			img = factories[i].getResources().getImage(resourceId);
		}

		return img;
	}

    public ImageModel getOutterImage(String resourceId) {
        ImageModel img = null;

        // Search for the resource in all the painter factories
        for (int i = 0; (img == null) && (i < factories.length); i++) {
            img = factories[i].getResources().getImage(resourceId);
        }

        return img;
    }    
    
	/**
	 * Retrieves an image for the specified Id
	 * 
	 * @param size
	 *            Imagesize
	 * @param resourceId
	 *            Image Code
	 * @return The Image or <code>null</code>
	 */
	public ImageModel getImage(int size, String resourceId) {
		ImageModel img = null;

		// Search for the resource in all the painter factories
		for (int i = 0; (img == null) && (i < factories.length); i++) {
			img = factories[i].getResources().getImage(size, resourceId);
		}

		return img;
	}

	/**
	 * Retrieves an image for the specified Id
	 * 
	 * @param resourceId
	 *            Image Code
	 * @param param
	 *            Markup parameter
	 * @return The Image or <code>null</code>
	 */
	public ImageModel getImage(String resourceId, Object param) {
		ImageModel template = getImage(resourceId);

		if (template == null) {
			return null;
		} else {
			if (template.getSource().indexOf("{0}") == -1) {
				return template;
			} else {
				ImageModelImp image = new ImageModelImp(template);

				if (param instanceof Color) {
					image.setSource(MessageFormat.format(template.getSource(),
							new Object[] { ((Color) param).toHex() }));
				} else {
					image.setSource(MessageFormat.format(template.getSource(),
							new Object[] { param }));
				}

				return image;
			}
		}
	}

	/**
	 * Retrieves an image for the specified Id
	 * 
	 * @param resourceId
	 *            Image Code
	 * @return The Image Source
	 */
	public String getImageSrc(String resourceId) {
		ImageModel image = getImage(resourceId);

		if (image == null) {
			return "";
		} else {
			return getSource(image);
		}
	}

	/**
	 * Retrieves an image for the specified Id
	 * 
	 * @param resourceId
	 *            Image Code
	 * @param param
	 *            Markup Parameter
	 * @return The Image Source
	 */
	public String getImageSrc(String resourceId, Object param) {
		ImageModel image = getImage(resourceId, param);

		if (image == null) {
			return "";
		} else {
			return getSource(image);
		}
	}

	/**
	 * Retrieves an image for the specified Id
	 * 
	 * @param size
	 *            Image size
	 * @param resourceId
	 *            Image Code
	 * @return The Image Source
	 */
	public String getImageSrc(int size, String resourceId) {
		ImageModel image = getImage(size, resourceId);

		if (image == null) {
			return "";
		} else {
			return getSource(image);
		}
	}

	/**
	 * Retrieves an color for the specified Id
	 * 
	 * @param resourceId
	 *            Color Code
	 * @return The Color or <code>null</code>
	 */
	public Color getColor(String resourceId) {
		Color col = null;

		// Search for the resource in all the painter factories
		for (int i = 0; (col == null) && (i < factories.length); i++) {
			col = factories[i].getResources().getColor(resourceId);
		}

		return col;
	}

	/**
	 * Retrieves an color for the specified Id
	 * 
	 * @param resourceId
	 *            Color Code
	 * @return The Color or <code>null</code>
	 */
	public java.awt.Color getAwtColor(String resourceId) {
		Color color = getColor(resourceId);

		if (color == null) {
			return null;
		} else {
			return color.toAWT();
		}
	}

	/**
	 * @return Gets the Control
	 */
	public Control getControl() {
		return control;
	}

	/**
	 * Returns the Style Id of the control. Uner some circumstances this method
	 * will generate an unique Style id if no style id is explicit set.
	 * 
	 * @return style id for this control
	 */
	public String getStyleId() {
		String id = control.getStyleId();

		if ((id == null)
				&& hasAttribute(PainterContextAtributes.FORCE_STYLEID, true)) {
			return getForcedStyleId(control);
		} else {
			return id;
		}
	}

	/**
	 * Returns a unique Style id for the given control
	 * 
	 * @param control
	 *            The control
	 * @return unique style id for the current request
	 */
	public String getForcedStyleId(Control control) {
		return "ctrl" + control.hashCode();
	}

	/**
	 * This method checks if html comments should be written to the output
	 * 
	 * @return <code>false</code> when comments should be hidden
	 */
	public boolean showComments() {
		// Default value is true
		return !Boolean.FALSE
				.equals(getAttribute(PainterContextAtributes.COMMENTS, true));
	}

	/**
	 * This method checks if a control that is rendered within this Panter
	 * context has to be "display only"
	 * 
	 * @return returns <code>true</code> if a control should be rendered as
	 *         display only
	 */
	public boolean isDisplayOnly() {
		// Default value is false
		return Boolean.TRUE
				.equals(getAttribute(PainterContextAtributes.DISPLAY, true));
	}

	/**
	 * Creates an ActionPainter. The Painter is used by Controls to render
	 * Actions into the user interface
	 * 
	 * @param action
	 *            ControlAction
	 * @return ActionPainter
	 */
	public ActionPainter createActionPainter(ControlActionDef action) {
		return factory
				.createActionPainter(this, action.createInstance(control));
	}

	/**
	 * Creates an ActionPainter. The Painter is used by Controls to render
	 * Actions into the user interface
	 * 
	 * @param action
	 *            ControlAction
	 * @param actionName
	 *            Additional Action Name
	 * @return ActionPainter
	 */
	public ActionPainter createActionPainter(ControlActionDef action,
			String actionName) {

		return factory.createActionPainter(this, action.createInstance(control,
				actionName));
	}

	/**
	 * Retrieves an image for the specified Id
	 * 
	 * @param image
	 *            Image
	 * @return The Image or <code>null</code>
	 */
	public Input createInput(ImageModel image) {

		if (image == null) {
			return null;
		}

		Input img = new Input();
		img.setType(Input.image);
		img.setSrc(getSource(image));

		if (image.getAlternate() != null) {
			img.setAlt(html(localize(image.getAlternate())));
		}

		if (image.getTooltip() != null) {
			img.setTitle(html(localize(image.getTooltip())));
		}

		img.setBorder(0);

		return img;
	}

	/**
	 * Retrieves an image for the specified Id
	 * 
	 * @param resourceId
	 *            Image Code
	 * @return The Image or <code>null</code>
	 */
	public Input createInput(String resourceId) {
		return createInput(getImage(resourceId));
	}

	/**
	 * Retrieves an image for the specified Id
	 * 
	 * @param image
	 *            Image
	 * @return The Image or <code>null</code>
	 */
	public IMG createImage(ImageModel image) {

		if (image == null) {
			return null;
		}

		IMG img = new IMG(getSource(image));

		if (image.getAlternate() != null) {
			img.setAlt(html(localize(image.getAlternate())));
		}

		if (image.getTooltip() != null) {
			img.setTitle(html(localize(image.getTooltip())));
		}

		if (image.getHeight() > 0) {
			img.setHeight(image.getHeight());
		} else if (image.getHeight() == -1) {
			img.setHeight("100%");
		}

		if (image.getWidth() > 0) {
			img.setWidth(image.getWidth());
		} else if (image.getWidth() == -1) {
			img.setWidth("100%");
		}

		img.setBorder(0);

		return img;
	}

	/**
	 * Retrieves an image for the specified Id
	 * 
	 * @param resourceId
	 *            Image Code
	 * @return The Image or <code>null</code>
	 */
	public IMG createImage(String resourceId) {
		return createImage(getImage(resourceId));
	}

	/**
	 * Retrieves an image for the specified Id
	 * 
	 * @param resourceId
	 *            Image Code
	 * @param param
	 *            Markup parameter
	 * @return The Image or <code>null</code>
	 */
	public IMG createImage(String resourceId, Object param) {
		return createImage(getImage(resourceId, param));
	}

	/**
	 * Retrieves an image for the specified Id
	 * 
	 * @param size
	 *            Image size
	 * @param resourceId
	 *            Image Code
	 * @return The Image or <code>null</code>
	 */
	public IMG createImage(int size, String resourceId) {
		return createImage(getImage(size, resourceId));
	}

	/**
	 * Gets the Locale Setting for this Control
	 * 
	 * @return Locale Setting
	 */
	public Locale getLocale() {
		if ((locale == null) && (parentContext != null)
				&& (control.getLocaleName() == null)) {
			// Return the locale from the nesting painter context
			return parentContext.getLocale();
		} else {
			return locale;
		}
	}

	/**
	 * Localizes a string depending on the localization settings of the Painter
	 * context
	 * 
	 * @param keyOrString
	 *            String Literal or Resource Key
	 * @return localized String
	 */
	public String localize(String keyOrString) {
		return PainterHelp
				.localize(pageContext, keyOrString, null, getLocale());
	}

	/**
	 * Localizes a string depending on the localization settings of the Painter
	 * context
	 * 
	 * @param keyOrString
	 *            String Literal or Resource Key
	 * @param locale
	 *            the locale to use
	 * @return localized String
	 */
	public String localize(String keyOrString, Locale locale) {
		return PainterHelp.localize(pageContext, keyOrString, null, locale);
	}

	/**
	 * Localizes a string depending on the localization settings of the Painter
	 * context
	 * 
	 * @param keyOrString
	 *            String Literal or Resource Key
	 * @param arguments
	 *            MessageFormat arguments
	 * @return localized String
	 */
	public String localize(String keyOrString, Object[] arguments) {
		return PainterHelp.localize(pageContext, keyOrString, arguments,
				getLocale());
	}

	/**
	 * Localizes a string depending on the localization settings of the Painter
	 * context. A key is searched in the following order: 1) If no user defined
	 * resource bundle is used. The resource is searched unter the
	 * STRUTS_MESSAGES_KEY.<br>
	 * 2) If an user defined resource bundle is used (keq@bunde) the resource is
	 * searched in the spezified resource bundel.<br>
	 * 3) If the key was not found before, the resource is searched in the
	 * framework resource bundel unter the key Globals.MESSAGE.
	 * 
	 * @param resourceId
	 *            The resource key.
	 * @param arguments
	 *            MessageFormat arguments or <code>null</code>
	 * @param returnNull
	 *            controlls wether to return null or a not present indicator
	 * @return localized String
	 */
	public String localizeKey(String resourceId, Object[] arguments,
			boolean returnNull) {

		// try to localize the key based on a resource bundle
		return PainterHelp.localizeKey(pageContext, resourceId, arguments,
				getLocale(), returnNull);
	}

	/**
	 * Converts a String to an equivalent HTML-String
	 * 
	 * @param raw
	 *            Object
	 * @return String
	 */
	public String html(Object raw) {
		return HtmlUtil.encodeHtml(raw);
	}

	/**
	 * Converts a String to an equivalent HTML-String
	 * 
	 * @param raw
	 *            Object
	 * @param filter
	 *            Filter
	 * @return String
	 */
	public String html(Object raw, boolean filter) {
		return HtmlUtil.encodeHtml(raw, filter);
	}

	/**
	 * Converts a String to an equivalent HTML-String
	 * 
	 * @param raw
	 *            Object
	 * @param filter
	 *            Filter
	 * @param maxlength
	 *            The maximum number of visible characters
	 * @return String
	 */
	public String html(Object raw, boolean filter, int maxlength) {
		if (filter) {
			return HtmlUtil.encodeHtml(truncate(raw, maxlength));
		} else {
			// Never truncate a plain HTML-String
			return HtmlUtil.encodeHtml(raw, filter);
		}
	}

	/**
	 * Truncates a given string to a maximum number of characters
	 * 
	 * @param obj
	 *            The string to tuncate
	 * @param maxlength
	 *            The maximum number of characters
	 * @return truncated string
	 */
	public String truncate(Object obj, int maxlength) {
		if (obj == null) {
			return null;
		} else {
			return StringHelp.truncate(obj.toString(), maxlength);
		}
	}

	/**
	 * Returns the localized Image resourcename
	 * 
	 * @param base
	 *            Base Directory or resource Identifier
	 * @param src
	 *            Resourcename
	 * @return localized Resourcename
	 */
	public String getSource(String base, String src) {
		return PainterHelp.getSource(pageContext, base, src, getLocale());
	}

	/**
	 * Gets the localized resource name
	 * 
	 * @param image
	 *            Image Model
	 * @return localized Resourcename
	 */
	public String getSource(ImageModel image) {
		return getSource(image.getBase(), image.getSource());
	}

	// ==========================
	// PainterContext attributes
	// ==========================

	/**
	 * Sets an Attribute for this Painter
	 * 
	 * @param key
	 *            Atribute Key
	 * @param value
	 *            Attribute Value
	 */
	public void setAttribute(String key, Object value) {
		if (value == null) {
			if (attributes != null) {
				attributes.remove(key);
			}
		} else {
			if (attributes == null) {
				attributes = new Hashtable();
			}

			attributes.put(key, value);
		}
	}

	/**
	 * Removes an Attribute for this Painter
	 * 
	 * @param key
	 *            Atribute Key
	 * @return Value or <code>null</code>
	 */
	public Object removeAttribute(String key) {
		if (attributes != null) {
			return attributes.remove(key);
		} else {
			return null;
		}
	}

	/**
	 * Gets an Atribute from the Painter Context hierarchie
	 * 
	 * @param key
	 *            Attribute Key
	 * @param searchParent
	 *            if set to <code>true</code> the attribute will be searcht up
	 *            the PainterContext hierarchy
	 * @return Value or <code>null</code>
	 */
	public Object getAttribute(String key, boolean searchParent) {
		Object value = null;

		if (attributes != null) {
			// This painter Context has attributes
			value = attributes.get(key);
		}

		if ((value == null) && searchParent && (parentContext != null)) {
			return parentContext.getAttribute(key, searchParent);
		} else {
			return value;
		}
	}

	/**
	 * Gets an Atribute from the Painter Context hierarchie
	 * 
	 * @param key
	 *            Attribute Key
	 * @param searchParent
	 *            if set to <code>true</code> the attribute will be searcht up
	 *            the PainterContext hierarchy
	 * @return Value or <code>null</code>
	 */
	public boolean hasAttribute(String key, boolean searchParent) {

		boolean exists = false;

		if (attributes != null) {
			// This painter Context has attributes
			exists = attributes.containsKey(key);
		}

		if (!exists && searchParent && (parentContext != null)) {
			// The attribute is not undefined so far
			// search the painter context hierarchy
			exists = parentContext.hasAttribute(key, true);
		}

		return exists;
	}

	/**
	 * Pushes the current attributes on a stack for later restore
	 */
	public void pushAttributes() {
		if (attributes != null) {
			if (attributesSave == null) {
				attributesSave = new Stack();
			}

			// Save a copy of the current attributes on the
			// Stack
			attributesSave.push(attributes.clone());
		}
	}

	/**
	 * Restores the attributes from the Stack all current attributes are lost
	 */
	public void popAttributes() {
		attributes = null;

		if ((attributesSave != null) && !attributesSave.empty()) {
			attributes = (Hashtable) attributesSave.pop();
		}
	}
}