/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/ControlPainter.java,v 1.51 2005/09/27 14:06:22 P001002 Exp $
 * $Revision: 1.51 $
 * $Date: 2005/09/27 14:06:22 $
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

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ecs.AlignType;
import org.apache.ecs.ConcreteElement;
import org.apache.ecs.ElementContainer;
import org.apache.ecs.html.IMG;
import org.apache.ecs.html.Input;
import org.apache.ecs.html.TD;
import org.apache.ecs.html.TR;

import com.cc.framework.convert.Converter;
import com.cc.framework.convert.ConverterException;
import com.cc.framework.security.Principal;
import com.cc.framework.ui.Color;
import com.cc.framework.ui.control.Control;
import com.cc.framework.ui.control.ControlActionDef;
import com.cc.framework.ui.model.ImageModel;

/**
 * This Class is responsible for the HTML-generation (rendering) of a Control
 * 
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version $Revision: 1.51 $
 * @since 1.0
 */
public abstract class ControlPainter {

	/**
	 * Commons Logging instance.
	 */
	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * PageContext of the JSP-Seite
	 */
	private PainterContext painterContext = null;

	/**
	 * The painter that is used to create the frame element
	 */
	private FramePainter framePainter = null;

	// -------------------------------
	// methods
	// -------------------------------

	/**
	 * Constructor for ControlPainter
	 */
	public ControlPainter() {
		super();
	}

	/**
	 * Constructor for ControlPainter
	 * 
	 * @param painterContext
	 *            The PainterContext
	 */
	public ControlPainter(PainterContext painterContext) {
		super();

		init(painterContext);
	}

	/**
	 * Sets the painter context
	 * 
	 * @param painterContext
	 *            The PainterContext
	 */
	public void init(PainterContext painterContext) {
		this.painterContext = painterContext;
	}

	/**
	 * Returns the FramePainter for this Control. The method will call
	 * doCreateFramePainter to create a new FramePainter at the first access.
	 * 
	 * @return FaramePainter
	 */
	protected FramePainter getFramePainter() {
		if (framePainter == null) {
			framePainter = PainterFactory.createFramePainter(this);
		}

		return framePainter;
	}

	/**
	 * Returns the JSP page context
	 * 
	 * @return The jsp page context
	 */
	public PageContext getPageContext() {
		return painterContext.getPageContext();
	}

	/**
	 * Returns the Principal Object
	 * 
	 * @return Principal
	 */
	public Principal getPrincipal() {
		return painterContext.getPrincipal();
	}

	/**
	 * Returns the portion of the request URI that indicates the context of the
	 * request
	 * 
	 * @return String
	 */
	public String getContextPath() {
		return painterContext.request().getContextPath();
	}

	/**
	 * This method checks if html comments should be written to the output
	 * 
	 * @return <code>false</code> when comments should be hidden
	 */
	public boolean showComments() {
		return painterContext.showComments();
	}

	/**
	 * Returns the HTTP Request
	 *
	 * @return the HttpServletRequest
	 */
	public HttpServletRequest request() {
		return painterContext.request();
	}

	/**
	 * Returns the HTTP Response
	 *
	 * @return the HttpServletResponse
	 */
	public HttpServletResponse response() {
		return painterContext.response();
	}

	/**
	 * Gets the session object
	 *
	 * @return	HttpSession
	 */
	public HttpSession session() {
		return painterContext.session();
	}

	/**
	 * Returns the HttpServletRequest
	 * 
	 * @return The HttpServletRequest
	 * @deprecated use session()
	 */
	public HttpServletRequest getRequest() {
		return painterContext.request();
	}

	/**
	 * Returns the HttpServletResponse
	 * 
	 * @return The HttpServletResponse
	 * @deprecated use response()
	 */
	public HttpServletResponse getResponse() {
		return painterContext.response();
	}

	/**
	 * Returns the HttpSession
	 * 
	 * @return The HttpSession or <code>null</code>
	 * @deprecated use session()
	 */
	public HttpSession getSession() {
		return painterContext.session();
	}

	/**
	 * Retrieves the current Locale
	 * 
	 * @return Locale
	 */
	public Locale getLocale() {
		return painterContext.getLocale();
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

		return painterContext.getAsString(converter, value);
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
		return painterContext.decorateURL(url);
	}

	/**
	 * Returns the Style Id of the control. Uner some circumstances this method
	 * will generate an unique Style id if no style id is explicit set.
	 * 
	 * @return style id for this control
	 */
	public String getStyleId() {
		return painterContext.getStyleId();
	}

	/**
	 * The method returns the html style class required for a list or treelist
	 * control.
	 * 
	 * @param type
	 *            The required style class
	 * @return The style class
	 */
	public String getElementClass(int type) {
		throw new IllegalArgumentException("Unhandled Element Class: " + type);
	}

	/**
	 * Creates the HTML-Element. This Methode must be implemented by the
	 * concrete Sub-Class
	 * 
	 * @return ConcreteElement
	 */
	protected abstract ConcreteElement doCreateElement();

	/**
	 * Removes the decoration collection from the Painter Context
	 * 
	 * <p>this method can only be called <b>once</b>. subsequent calls
	 * will return a <code>null</code> pointer!
	 * 
	 * @return	The current decoration collection
	 */
	protected Collection getDecorationsOnce() {

		if (getPainterContext().hasAttribute(PainterContextAtributes.DECORATIONS_DONE, false)) {
			return null;
		}

		Collection decorations1 = null;
		
		if (getPainterContext().getControl().getButtons() != null) {
			decorations1 = new Vector(); 
				
			Iterator iter = getPainterContext().getControl().getButtons().iterator();
			while (iter.hasNext()) {
				Control childControl = (Control) iter.next();

				ControlPainter painter =
					PainterFactory.createPainter(
						getPageContext(),
						childControl);

				if (painter != null) {
					ConcreteElement childElement = painter.createElement();
						
					if (childElement != null) {
						if (childElement.getAttribute("style") == null) {
							childElement.setStyle("align: absmiddle; padding-left: 3px;");
						}

						decorations1.add(childElement);
					}
				}
			}
		}

		// Add the decorations from the PainterContext attributes
		Collection decorations2 = (Collection) getPainterContext()
			.getAttribute(PainterContextAtributes.DECORATIONS, false);

		// The collection has been processed
		getPainterContext().setAttribute(PainterContextAtributes.DECORATIONS_DONE, Boolean.TRUE);

		if (decorations1 == null) {
			return decorations2;
		} else if (decorations2 == null) {
			return decorations1;
		} else {
			decorations1.addAll(decorations2);

			return decorations1;
		}
	}

	/**
	 * Attaches all decoration elements that are stored in the painter context
	 * to the control HTML table row. The Decorations collection will be removed
	 * from the painter context.
	 * 
	 * @param row
	 *            The row wehere the decorations should be attached
	 * @param rowSpan
	 *            the rows that a decoration element should span
	 */
	protected void doAddDecorationsToRow(TR row, int rowSpan) {
		Collection decorations = getDecorationsOnce();

		if ((decorations != null) && !decorations.isEmpty()) {
			Iterator i = decorations.iterator();
			while (i.hasNext()) {
				row.addElement(new TD((ConcreteElement) i.next()).setVAlign(
						AlignType.top).setRowSpan(rowSpan));
			}
		}
	}

	/**
	 * Attaches all decoration elements that are stored in the painter context
	 * to the left side of the control. The Decorations collection will be
	 * removed from the painter context.
	 * 
	 * @param control
	 *            The Controls HTML representation
	 * @return returns the decorated Control
	 */
	protected ConcreteElement doAttachDecorations(ConcreteElement control) {
		Collection decorations = getDecorationsOnce();

		if ((decorations == null) || decorations.isEmpty()) {
			return control;
		} else {

			ElementContainer container = new ElementContainer();

			if (control != null) {
				container.addElement(control);
			}

			Iterator i = decorations.iterator();
			while (i.hasNext()) {
				container.addElement((ConcreteElement) i.next());
			}

			return container;
		}
	}

	/**
	 * Encodes an URL Some characters present the possibility of being
	 * misunderstood within URLs for various reasons. For this reason these
	 * characters are always be encoded. For example 'Less Than' symbol ("<"),
	 * Greater Than' symbol (">"), Left Curly Brace ("{"), Right Curly Brace
	 * ("}"), Vertical Bar/Pipe ("|"), Backslash ("\"), Caret ("^"), Tilde
	 * ("~"), Left Square Bracket ("["), Right Square Bracket ("]"), Grave
	 * Accent ("`"), Percent character ("%").
	 * 
	 * @param src
	 *            The URL which should be encoded
	 * @return String The encoded state.
	 */
	public String encodeURL(String src) {
		return painterContext.response().encodeURL(src);
	}

	/**
	 * Methode getSmartCaption
	 * 
	 * @param caption
	 *            Caption
	 * @param detail
	 *            Detail
	 * @return String
	 */
	public String getSmartCaption(String caption, String detail) {
		return PainterHelp.getSmartCaption(localize(caption), localize(detail));
	}

	/**
	 * Methode getSmartDetail
	 * 
	 * @param caption
	 *            Caption
	 * @param detail
	 *            Detail
	 * @return String
	 */
	public String getSmartDetail(String caption, String detail) {
		return PainterHelp.getSmartDetail(localize(caption), localize(detail));
	}

	/**
	 * Retrieves the name of the HTML element. This could be a path name when
	 * the control is nested within another control
	 * 
	 * @return The name to use for the <b>name</b>-attribute of a HTML element
	 */
	public String getElementName() {
		return getPainterContext().getElementName();
	}

	/**
	 * Converts a String to an equivalent HTML-String
	 * 
	 * @param raw
	 *            Object
	 * @return String
	 */
	public String html(Object raw) {
		return painterContext.html(raw);
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
		return painterContext.html(raw, filter);
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
		return painterContext.html(raw, filter, maxlength);
	}

	/**
	 * Gets the Painter Context
	 * 
	 * @return PainterContext
	 */
	public PainterContext getPainterContext() {
		return painterContext;
	}

	/**
	 * Localizes a string depending on the localization settings of the Painter
	 * context
	 * 
	 * @param raw
	 *            String
	 * @return localized String
	 */
	public String localize(String raw) {
		return painterContext.localize(raw);
	}

	/**
	 * Localizes a string depending on the localization settings of the Painter
	 * context
	 * 
	 * @param raw
	 *            String
	 * @param locale
	 *            the Locale
	 * @return localized String
	 */
	public String localize(String raw, Locale locale) {
		return painterContext.localize(raw, locale);
	}

	/**
	 * Localizes a string depending on the localization settings of the Painter
	 * context
	 * 
	 * @param raw
	 *            String
	 * @param arguments
	 *            MessageFormat arguments
	 * @return localized String
	 */
	public String localize(String raw, Object[] arguments) {
		return painterContext.localize(raw, arguments);
	}

	/**
	 * Gets the localied Image resourcename
	 * 
	 * @param base
	 *            Base Directory or Resourcekey
	 * @param src
	 *            Resourcename
	 * @return localized Resourcename
	 */
	public String getSource(String base, String src) {
		return painterContext.getSource(base, src);
	}

	/**
	 * Gets the localied resourcename
	 * 
	 * @param image
	 *            Image Model
	 * @return localized Resourcename
	 */
	public String getSource(ImageModel image) {
		return painterContext.getSource(image);
	}

	/**
	 * Retrieves an color for the specified Id
	 * 
	 * @param resourceId
	 *            Color Code
	 * @return The Color or <code>null</code>
	 */
	public Color getColor(String resourceId) {
		return painterContext.getColor(resourceId);
	}

	/**
	 * Retrieves an color for the specified Id
	 * 
	 * @param resourceId
	 *            Color Code
	 * @return The Color or <code>null</code>
	 */
	public java.awt.Color getAwtColor(String resourceId) {
		return painterContext.getAwtColor(resourceId);
	}

	/**
	 * Retrieves an image for the specified Id
	 * 
	 * @param resourceId
	 *            Image Code
	 * @return The Image or <code>null</code>
	 */
	public ImageModel getImage(String resourceId) {
		return painterContext.getImage(resourceId);
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
	public ImageModel getImage(String resourceId, Object param) {
		return painterContext.getImage(resourceId, param);
	}

	/**
	 * Retrieves an image for the specified Id
	 * 
	 * @param size
	 *            The image size
	 * @param resourceId
	 *            Image Code
	 * @return The Image Source
	 */
	public ImageModel getImage(int size, String resourceId) {
		return painterContext.getImage(size, resourceId);
	}

	/**
	 * Retrieves an String for the specified Id
	 * 
	 * @param resourceId
	 *            The resource id
	 * @return The String Source
	 */
	public String getStringResource(String resourceId) {
		return painterContext.getStringResource(resourceId);
	}

	/**
	 * Retrieves an framework String for the specified Id
	 * 
	 * @param resourceId
	 *            The resource id
	 * @return The String Source
	 */
	public String getFrameworkString(String resourceId) {
		return painterContext.getFrameworkString(resourceId);
	}

	/**
	 * Retrieves an framework String for the specified Id
	 * 
	 * @param resourceId
	 *            The resource id
	 * @param arguments
	 *            MessageFormat arguments
	 * @return The String Source
	 */
	public String getFrameworkString(String resourceId, Object[] arguments) {
		return painterContext.getFrameworkString(resourceId, arguments);
	}

	/**
	 * Retrieves an image for the specified Id
	 * 
	 * @param resourceId
	 *            Image Code
	 * @return The Image Source
	 */
	public String getImageSrc(String resourceId) {
		return painterContext.getImageSrc(resourceId);
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
		return painterContext.getImageSrc(resourceId, param);
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
		return painterContext.getImageSrc(size, resourceId);
	}

	/**
	 * Retrieves an image for the specified Id
	 * 
	 * @param image
	 *            Image
	 * @return The Image or <code>null</code>
	 */
	public Input createInput(ImageModel image) {
		return painterContext.createInput(image);
	}

	/**
	 * Retrieves an image for the specified Id
	 * 
	 * @param resourceId
	 *            Image Code
	 * @return The Image or <code>null</code>
	 */
	public Input createInput(String resourceId) {
		return painterContext.createInput(resourceId);
	}

	/**
	 * Creates a spacer image with the given dimension
	 * 
	 * @param height
	 *            spacer height
	 * @param width
	 *            spacer width
	 * @return Spacer Image
	 */
	public ConcreteElement createSpacer(int height, int width) {
		return new IMG().setSrc(getImageSrc(Resources.IMAGE_SPACER)).setHeight(
				height).setWidth(width).setBorder(0);
	}

	/**
	 * Creates a spacer image with the given dimension
	 * 
	 * @param height
	 *            spacer height
	 * @param width
	 *            spacer width
	 * @return Spacer Image
	 */
	public ConcreteElement createSpacer(int height, String width) {
		return new IMG().setSrc(getImageSrc(Resources.IMAGE_SPACER)).setHeight(
				height).setWidth(width).setBorder(0);
	}

	/**
	 * Creates a spacer image with the given dimension
	 * 
	 * @param height
	 *            spacer height
	 * @param width
	 *            spacer width
	 * @return Spacer Image
	 */
	public ConcreteElement createSpacer(String height, String width) {
		return new IMG().setSrc(getImageSrc(Resources.IMAGE_SPACER)).setHeight(
				height).setWidth(width).setBorder(0);
	}

	/**
	 * Creates an HTML image Tag for the specified image model
	 * 
	 * @param image
	 *            Image
	 * @return The Image or <code>null</code>
	 */
	public IMG createImage(ImageModel image) {
		return painterContext.createImage(image);
	}

	/**
	 * Retrieves an image for the specified Id
	 * 
	 * @param resourceId
	 *            Image Code
	 * @return The Image or <code>null</code>
	 */
	public IMG createImage(String resourceId) {
		return painterContext.createImage(resourceId);
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
		return painterContext.createImage(resourceId, param);
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
		return painterContext.createActionPainter(action);
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
		return painterContext.createActionPainter(action, actionName);
	}

	// ====================================
	// Element Creation
	// ====================================

	/**
	 * This template method gets called before
	 * element creation
	 */
	protected void doBeforeCreate() {
	}

	/**
	 * This template method gets called after
	 * element creation
	 */
	protected void doAfterCreate() {
	}

	/**
	 * Creates the HTML-Element. This Methode must be implemented by the
	 * concrete Sub-Class
	 * 
	 * @return ConcreteElement or <code>null</code>
	 */
	public final ConcreteElement createElement() {
		if (!painterContext.getControl().show(getPrincipal())) {
			// The control is hidden
			return null;
		} else {
			getPainterContext().open();
			doBeforeCreate();
			ConcreteElement element = doAttachDecorations(doCreateElement());
			doAfterCreate();
			getPainterContext().close();		
	
			return element;
		}
	}

	/**
	 * Methode paint
	 * 
	 * @param out
	 *            JspWriter
	 * @throws IOException
	 *             If an input or output exception occurred
	 */
	public final void paint(Writer out) throws IOException {
		beginPaint(out);
		endPaint(out);
	}

	/**
	 * Methode beginPaint
	 * 
	 * @param out
	 *            Writer
	 * @throws IOException
	 *             If an input or output exception occurred
	 */
	public final void beginPaint(Writer out) throws IOException {
		// The painter context must be opened before it can
		// be used
		getPainterContext().open();

		PrintWriter pw = new PrintWriter(out);

		// in debug mode a html comment is written, which indicates
		// that the following html code was generated by the active
		// painter
		if (showComments()) {
			pw.println();
			pw.println(MessageFormat.format(
				"<!-- BEGIN: {0} [{1}] -->",
				new Object[] { this.getClass().getName(), painterContext.getControl().getControlName() }));
		}

		// check the principal object if the control should be painted
		if (painterContext.getControl().show(getPrincipal())) {
			doBeforeCreate();
		} else {
			if (showComments()) {
				pw.println("<!-- hidden -->");
			}
		}

		pw.flush();
	}

	/**
	 * This Method writes the Control as an HTML-String to the Output from the
	 * JSP-Page.
	 * 
	 * @param out
	 *            The Writer
	 * @throws IOException
	 *             Signals that an I/O exception of some sort has occurred
	 */
	public final void endPaint(Writer out) throws IOException {

		PrintWriter pw = new PrintWriter(out);

		if (painterContext.getControl().show(getPrincipal())) {
			ConcreteElement element = doAttachDecorations(doCreateElement());

			if (element == null) {
				log.warn("Painter returned no element: " + this.getClass().getName());
			} else {
				element.output(out);
			}

			doAfterCreate();
		}

		// in debug mode a html comment is written, which indicates that
		// the generated html code of the active painter ends now.
		if (showComments()) {
			pw.println(MessageFormat.format(
				"<!-- END: {0} -->",
				new Object[] { this.getClass().getName()}));
		}

		pw.flush();

		// close the painter context
		getPainterContext().close();
	}
}