/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/controls/BaseControlTag.java,v 1.45 2005/11/13 13:39:39 P001002 Exp $
 * $Revision: 1.45 $
 * $Date: 2005/11/13 13:39:39 $
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

package com.cc.framework.taglib.controls;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cc.framework.adapter.FrameworkAdapterFactory;
import com.cc.framework.common.InvalidEnumType;
import com.cc.framework.http.HttpScope;
import com.cc.framework.taglib.DesignRuleContainerTag;
import com.cc.framework.taglib.ScriptTagSupport;
import com.cc.framework.taglib.TagHelp;
import com.cc.framework.ui.RunAt;
import com.cc.framework.ui.control.Control;
import com.cc.framework.ui.model.ClientHandler;
import com.cc.framework.ui.model.ControlDesignModel;
import com.cc.framework.ui.model.DesignRule;
import com.cc.framework.ui.painter.ControlPainter;
import com.cc.framework.ui.painter.PainterContext;
import com.cc.framework.ui.painter.PainterFactory;

/**
 * Base class for control tag handler
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.45 $
 * @since      1.0
 */
public abstract class BaseControlTag
	extends ScriptTagSupport
	implements DesignRuleContainerTag {

	/**
	 * Commons Logging instance.
	 */
	protected transient Log log = LogFactory.getLog(this.getClass());

	/**
	 * The scope of the bean containing our underlying property.
	 */
	private HttpScope scope = HttpScope.ANY;

	/**
	 * The painter, which is responsible to render the control
	 */
	private ControlPainter painter = null;

	/**
	 * The controls design model
	 */
	private ControlDesignModel designModel = null;

	/**
	 * The Control instance
	 */
	private Control control = null;

	/**
	 * A direct set value
	 */
	private Object directValue = null;

	/**
	 * Constructor
	 */
	public BaseControlTag() {
		super();
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#release()
	 */
	public void release() {
		super.release();

		// initialize variables
		releaseDesignModel();
	}

	/**
	 * @see	javax.servlet.jsp.tagext.Tag#setPageContext(PageContext pc)
	 */
	public void setPageContext(PageContext pc) {
		super.setPageContext(pc);

		// initialize variables
		releaseDesignModel();
	}

	/**
	 * Returns the DesignModel of the Control
	 *
	 * @return	ControlDesignModel
	 */
	protected ControlDesignModel getDesignModel() {
		if (designModel == null) {
			designModel = doCreateDesignModel();
			designModel.setDynamicDesignModel(true);
		}

		return designModel;
	}

	/**
	 * This method is called to create the DesignModel
	 * for the control
	 *
	 * @return		A concrete DesignModel
	 */
	protected abstract ControlDesignModel doCreateDesignModel();

	/**
	 * This method gets called when the design model is not
	 * longer needed
	 */
	protected void releaseDesignModel() {
		control		= null;
		scope		= HttpScope.ANY;
		designModel = null;
		directValue = null;

		// Release the painter
		painter		= null;
	}

	/**
	 * @see com.cc.framework.taglib.ScriptSupport#getClientHandler()
	 */
	public ClientHandler getClientHandler() {
		return getDesignModel();
	}

	/**
	 * Creates the control element
	 *
	 * @return		control element
	 * @throws		JspException If an error during bean
	 * 				lookup occurs
	 */
	protected abstract Control doCreateControl() throws JspException;

	/**
	 * Creates the control element
	 *
	 * @return		control element
	 * @throws		JspException If an error during bean
	 * 				lookup occurs
	 */
	protected final Control createControl() throws JspException {
		// Create and store the control instance
		// so that the control instance can be accessed
		// from the tag body
		control = doCreateControl();
		
		return control;
	}

	/**
	 * Retrieves the current Control instance. This method
	 * can only be called between doStartTag() and doEndTag()
	 * 
	 * @return	the current control instance
	 */
	public Control getControl() {
		return control;
	}

	/**
	 * Retrieves the bean which holds the Display Data or
	 * Control Instance
	 *
	 * @return		Bean (Control instance or DataModel)
	 * @throws		JspException Is thrown when the bean could
	 * 				not be found
	 */
	protected Object lookupBean() throws JspException {
		Object value = getDirectValue();

		if (value == null) {
			value = TagHelp.lookupBean(
				pageContext,
				this,
				getDesignModel().getName(),
				getDesignModel().getProperty(),
				getHttpScope());
		}

		return value;
	}

	/**
	 * Exposes the control instance as a scripting variable
	 * to the Tag body
	 *
	 * @param		ctrl The control instance
	 */
	private void exposeScriptingVariable(Control ctrl) {
		if (getId() != null) {
			pageContext.setAttribute(getId(), ctrl, PageContext.PAGE_SCOPE);
		}
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException {
		Control ctrl = createControl();

		exposeScriptingVariable(ctrl);

		createPainter(ctrl);
		beginPaint();

		return EVAL_BODY_INCLUDE;
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException {
		// render the control
		endPaint();

		// release the design model
		releaseDesignModel();

		return EVAL_PAGE;
	}

	/**
	 * With this attribute, access to the element can be restricted.
	 *
	 * <p>Authorizations are checked using the com.cc.framework.security.Principal
	 * object in the user session. The principal object is registered in the session
	 * with the method com.cc.framework.security.SecurityUtil#registerPrincipal(HttpSession, Principal).
	 * It is made available by the application developer by implementing the principal
	 * interface. In this manner, any authorization system can be very easily connected
	 * within the framework.</p>
	 * <p>Authorizations are always specified in the form of an Access Control List (<b>ACL</b>).
	 * What is involved here is a semicolon-delimited list with individual authorizations.
	 * The framweork supports the following authorization types, which, however, can be
	 * expanded at will by the application developer:
	 * <dl>
	 *		<dt>Literal</dt><dd>true|false -&gt; com.cc.framework.security.StaticPermission</dd>
	 *		<dt>Role</dt><dd>#rolename -&gt; com.cc.framework.security.RoleBasedPermission</dd>
	 *		<dt>Function </dt><dd>$functionname -&gt; com.cc.framework.security.FunctionBasedPermission</dd>
	 * </dl>
	 *
	 * @param	permission		The permission to set for this control
	 * @throws	JspException	If the argument can't be converted intoan object of type Permission
	 */
	public void setPermission(String permission) throws JspException {
		getDesignModel().setPermission(TagHelp.toPermission(permission));
	}

	/**
	 * @see javax.servlet.jsp.tagext.TagSupport#setId(java.lang.String)
	 */
	public void setId(String newId) {
		super.setId(newId);

		getDesignModel().setId(newId);
	}

	/**
	 * Sets the Property-Attribute
	 * @param	property	The Property-Attribute
	 */
	public void setProperty(String property) {
		getDesignModel().setProperty(property);
	}

	/**
	 * Sets the width of the control
	 * @param	newWidth	The Width of the Control in Pixel or %
	 */
	public void setWidth(String newWidth) {
		getDesignModel().setWidth(newWidth);
	}

	/**
	 * sets the height of the control
	 * @param	newHeight	The Height
	 */
	public void setHeight(String newHeight) {
		getDesignModel().setHeight(newHeight);
	}

	/**
	 * Sets the Border
	 * @param	newBorder	Border
	 * @throws JspException
	 *             If the Argument can't be converted to int
	 */
	public void setBorder(String newBorder) throws JspException {
		getDesignModel().setBorder(TagHelp.toInt(newBorder));
	}

	/**
	 * Tabulator Index for this element
	 *
	 * @param tabIndex	Tabindex
	 * @throws JspException
	 *             If the Argument can't be converted to int
	 */
	public void setTabindex(String tabIndex) throws JspException {
		getDesignModel().setTabIndex(TagHelp.toInt(tabIndex));
	}

	/**
	 * Specifies the name of the Java-Bean.
	 * The Java-Bean must be stored in the given scope.
	 *
	 * @param	name	Name of the Bean
	 */
	public void setName(String name) {
		// Save the name in the design model
		getDesignModel().setName(name);
	}

	/**
	 * An HTML-style. See HTML documentation for the attribute style.
	 *
	 * @param	style	An HTML-style
	 */
	public void setStyle(String style) {
		getDesignModel().setStyle(style);
	}

	/**
	 * The HTML-id attribute. See HTML documentation for the Attribute id.
	 *
	 * @param styleId	The HTML-id attribute
	 */
	public void setStyleId(String styleId) {
		getDesignModel().setStyleId(styleId);
	}

	/**
	 * The HTML-class attribute. See HTML documentation for the attribute class.
	 *
	 * @param	styleClass	The HTML-class attribute
	 */
	public void setStyleClass(String styleClass) {
		getDesignModel().setStyleClass(styleClass);
	}

	/**
	 * Associates this control with a help id
	 *
	 * @param		helpId The Help Help id of the element.
	 * 				The semantic of this id is completely
	 * 				in the responsibility of the applications
	 * 				help system
	 */
	public void setHelp(String helpId) {
		getDesignModel().setHelp(helpId);
	}

	/**
	 * Sets the optional tooltip text
	 *
	 * @param	tooltip	Tooltip
	 */
	public void setTooltip(String tooltip) {
		getDesignModel().setTooltip(tooltip);
	}

	/**
	 * Sets the property that can be used for rendering
	 * to non-visual media such as speech or Braille
	 *
	 * @param	summary	specifies a description and/or structure of the object.
	 */
	public void setSummary(String summary) {
		getDesignModel().setSummary(summary);
	}

    /**
	 * Directs the framework to include a transaction token (if any) in all
	 * generated hyperlinks for this column. The Transaction token is used to
	 * track form re-submissions.
	 *
	 * @param		transaction include transaction token
	 * @throws JspException If the Argument can't be converted to boolean
	 */
	public void setTransaction(String transaction) throws JspException {
		getDesignModel().setTransaction(TagHelp.toBoolean(transaction));
	}

	/**
	 * This attribute can be used to disable the control element. It then does
	 * not accept any user inputs and does not generate any control element
	 * events.
	 *
	 * @param		disabled Disabled-Flag
	 * @throws		JspException If the Argument can't be converted to boolean
	 */
	public void setDisabled(String disabled) throws JspException {
		getDesignModel().setDisabled(TagHelp.toBoolean(disabled));
	}

	/**
	 * Sets the scope
	 * @param		scope	Scope
	 * @throws		JspException If the Argument can't be converted
	 * 				to HttpScope
	 */
	public void setScope(String scope) throws JspException {
		try {
			this.scope = HttpScope.parse(scope);
		} catch (InvalidEnumType iet) {
			throw new JspException(iet.getMessage());
		}
	}

	/**
	 * Returns the scope.
	 * @return Scope
	 */
	public HttpScope getHttpScope() {
		return scope;
	}

	/**
	 * Sets the Action
	 * @param	action	Action to be performed
	 */
	public void setAction(String action) {
		getDesignModel().setAction(action);
	}

	/**
	 * Sets the Shaddow Attribute
	 * @param		shaddow Shaddow Attribute
	 * @throws		JspException	If the Argument can't be converted to boolean
	 */
	public void setShaddow(String shaddow) throws JspException {
		getDesignModel().setShaddow(TagHelp.toBoolean(shaddow));
	}

	/**
	 * This attribute specifies whether, for the control element,
	 * Clientside JavaScript should be used, or whether the control element
	 * should work purely with Server Roundtrips.
	 * <ul>
	 *		<li>server = All control element actions result in a Server Roundtrip and are processed on the server.</li>
	 * 		<li>client = Control element actions are carried out directly in the browser of the user.
	 *                   It depends on the Painter Factory used and the specific control element, how far
	 *                   the support for Clientside Scripting goes!</li>
	 * </ul>
	 *
	 * @param	runat			Specifies whether the control element should work with (=server) or without Server Roundtrips (=client).
	 * @throws	JspException	If the argument can't be converted to an object of type com.cc.framework.ui.RunAt
	 * @see	com.cc.framework.ui.RunAt
	 */
	public void setRunat(String runat) throws JspException {
		try {
			getDesignModel().setRunAt(RunAt.parse(runat));
		} catch (InvalidEnumType iet) {
			throw new JspException(iet.getMessage());
		}
	}

	/**
	 * Gets the default Action which should be used to forward
	 * requests from this control. This action is used when the
	 * action-property of the control is not set
	 *
	 * @return	String or <code>null</code>
	 */
	public String getDefaultAction() {
		return FrameworkAdapterFactory.getAdapter().getControlAction(pageContext, this);
	}

	/**
	 * Defines, if the Controls should act as a FormElement
	 * @param		flag True, if the Controls acts as a FormElement
	 * @throws		JspException If the Argument can't be converted
	 * 				to boolean
	 */
	public void setFormElement(String flag) throws JspException {
		getDesignModel().setFormElement(TagHelp.toBoolean(flag));
	}

	// ===========================================
	// Functions for painting
	// ===========================================

	/**
	 * Creates the Painter to render the Control
	 * @param ctrl	Control
	 */
	public void createPainter(Control ctrl) {
		// create the painter
		painter = PainterFactory.createPainter(pageContext, ctrl);

		if (painter == null) {
			log.warn("No painter for Tag " + getClass().getName());
		}
	}

	/**
	 * Starts rendering the Control
	 * @throws		JspException Bei einem Fehler während des Zeichnens
	 */
	public void beginPaint() throws JspException {

		if (painter == null) {
			return;
		}

		JspWriter out = pageContext.getOut();

		try {
			painter.beginPaint(out);
		} catch (Throwable t) {
			log.error("Error paintBegin() Tag " + getClass().getName(), t);

			throw new JspException(t.getMessage());
		}
	}

	/**
	 * Writes the HTML-Code to the Output-Stream
	 * @throws		JspException Bei einem Fehler während des Zeichnens
	 */
	public void endPaint() throws JspException {

		if (painter == null) {
			return;
		}

		JspWriter out = pageContext.getOut();

		try {
			painter.endPaint(out);
		} catch (Throwable t) {
			log.error("Error paintBegin() Tag " + getClass().getName(), t);

			throw new JspException(t.getMessage());
		}
	}

	/**
	 * Returns the painter context of the control
	 * painter
	 *
	 * @return		painter context
	 */
	public PainterContext getPainterContext() {
		if (painter == null) {
			return null;
		} else {
			return painter.getPainterContext();
		}
	}

	/**
	 * Sets the Loacel configuration for this control
	 *
	 * @param	locale Locale Identifier or <code>true|false</code>
	 */
	public void setLocale(String locale) {
		getDesignModel().setLocaleName(locale);
	}

	/**
	 * @see com.cc.framework.taglib.DesignRuleContainerTag#addDesignRule(com.cc.framework.ui.model.DesignRule)
	 */
	public void addDesignRule(DesignRule rule) {
		getDesignModel().addDesignRule(rule);
	}

	/**
	 * Sets the direct value for this control. This will overwrite the value of
	 * the <code>property</code> attribute
	 * 
	 * @param value
	 *            The direct value
	 */
	public void setValue(String value) {
		this.directValue = value;
	}

	/**
	 * Sets the direct value for this control. This will overwrite the value of
	 * the <code>property</code> attribute
	 * 
	 * @param value
	 *            The direct value
	 */
	public void setDirectValue(Object value) {
		this.directValue = value;
	}

	/**
	 * Returns a direct set value
	 * 
	 * @return Value
	 */
	public Object getDirectValue() {
		return directValue;
	}
}