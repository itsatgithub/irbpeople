/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/control/ControlAction.java,v 1.35 2005/09/27 14:06:22 P001002 Exp $
 * $Revision: 1.35 $
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

package com.cc.framework.ui.control;

import javax.servlet.jsp.PageContext;

import com.cc.framework.adapter.FrameworkAdapterFactory;
import com.cc.framework.http.Hyperlink;
import com.cc.framework.ui.javascript.JavaScriptUtil;
import com.cc.framework.ui.painter.PainterHelp;

/**
 * This class encapsulate an action which is triggered by a control
 *
 * @author    <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version   $Revision: 1.35 $
 * @since     1.0
 */
public class ControlAction {

	/**
	 * Hidden form field for the control action invocation string
	 */
	public static final String ACTION_INPUT_CONTROL	= "ctrla";

	/**
	 * URL parameter for the control name
	 */
	public static final String ACTION_URL_CONTROL	= "ctrl";

	/**
	 * URL parameter for the action name
	 */
	public static final String ACTION_URL_ACTION	= "action";

	/**
	 * URL parameter for the parameter list
	 */
	public static final String ACTION_URL_PARAMETER	= "param";

	/**
	 * The controls name
	 */
	private String controlName			= null;

	/**
	 * The action to which should be forwarded
	 */
	private String actionName			= null;

	/**
	 * This flag indicates if this Action should be rendered
	 * as a hyperlink or input field
	 */
	private boolean formElement			= false;
	
	/**
	 * Indicates if the transaction token (if any) should be
	 * generated for this hyperlink.
	 */
	private boolean transaction			= false;

	/**
	 * The base parameter definition
	 */
	private ControlActionDef action		= null;

	/**
	 * Array with parameters for the action
	 */
	private Object[] parameter			= null;

	/**
	 * position used to insert the next parameter into the array.
	 */
	private int insertPos				= 0;

	// ------------------------------------------------
	//                methods
	// ------------------------------------------------

	/**
	 * Constructor of ControlAction
	 * The instance is initalized with the action definiton.
	 * So a the types of the parameters can be checked.
	 *
	 * @param	action		ControlActionDef
	 * @param	control		Control
	 */
	public ControlAction(
		ControlActionDef action,
		Control control) {

		this.action			= action;
		this.controlName	= control.getControlName();
		this.actionName		= control.getAction();
		this.formElement	= control.isFormElement();

		// Initialize the transaction behaviour from
		// the control settings
		this.transaction	= control.getTransaction();

		this.parameter		= new Object[action.getFormalParameterCount()];
	}

	/**
	 * Constructor of ControlAction
	 * The instance is initalized with the action definiton.
	 * So a the types of the parameters can be checked.
	 *
	 * @param	action		ControlActionDef
	 * @param	control		Control
	 * @param	actionName	Name
	 */
	public ControlAction(
		ControlActionDef action,
		Control control,
		String actionName) {

		this(action, control);

		this.actionName		= actionName;
	}

    /**
	 * Directs the framework to include a transaction token (if any)
	 * in all generated hyperlinks. The Transaction token is used to
	 * track form re-submissions.
	 * 
	 * @param		transaction include transaction token
	 */
	public void setTransaction(boolean transaction) {
		this.transaction = transaction;
	}

	/**
	 * Checks if the framework should include a transaction token (if any) in
	 * all generated hyperlinks. The Transaction token is used to track form
	 * re-submissions.
	 * 
	 * @return		<code>true</code> if the transaction token should be generated
	 */
	public boolean getTransaction() {
		return transaction;
	}

	/**
	 * Method getActionMappingURL
	 *
	 * @param	pageContext	PageContext
	 * @param	action		Action
	 * @return	String
	 * 
	 * see org.apache.struts.util.RequestUtils#getActionMappingURL(String, PageContext)
	 */
	private static String getActionMappingURL(PageContext pageContext, String action) {
		return FrameworkAdapterFactory.getAdapter().getActionMappingURL(pageContext, action);
	}

	/**
	 * Check if the action is a form action. A form action
	 * always generates a form-submit handler
	 *
	 * @return	boolean	true if it's a form action
	 */
	public boolean isFormAction() {
		return formElement;
	}

	/**
	 * Adds a parameter
	 *
	 * @param	obj	Object
	 */
	public void addParameter(Object obj) {
		if (action.getParameterType(insertPos) == String.class) {
			// Add this Parameter as String
			addParameter(String.valueOf(obj));
		} else {
			action.checkType(insertPos, obj.getClass());
			parameter[insertPos++] = obj;
		}
	}

	/**
	 * Adds a parameter
	 *
	 * @param	value	Value
	 */
	public void addParameter(String value) {
		action.checkType(insertPos, String.class);

		parameter[insertPos++] = value;
	}

	/**
	 * Adds a parameter
	 *
	 * @param	value	Value
	 */
	public void addParameter(Integer value) {
		action.checkType(insertPos, Integer.class);

		parameter[insertPos++] = value;
	}

	/**
	 * Adds a parameter
	 *
	 * @param	value	Value
	 */
	public void addParameter(int value) {
		action.checkType(insertPos, int.class);

		parameter[insertPos++] = new Integer(value);
	}

	/**
	 * Adds a parameter
	 *
	 * @param	value	Value
	 */
	public void addParameter(Long value) {
		action.checkType(insertPos, Long.class);

		parameter[insertPos++] = value;
	}

	/**
	 * Adds a parameter
	 *
	 * @param	value	Value
	 */
	public void addParameter(long value) {
		action.checkType(insertPos, long.class);

		parameter[insertPos++] = new Long(value);
	}

	/**
	 * Adds a parameter
	 *
	 * @param	value	Value
	 */
	public void addParameter(Boolean value) {
		action.checkType(insertPos, Boolean.class);

		parameter[insertPos++] = value;
	}

	/**
	 * Adds a parameter
	 *
	 * @param	value	Value
	 */
	public void addParameter(boolean value) {
		action.checkType(insertPos, boolean.class);

		parameter[insertPos++] = new Boolean(value);
	}

	/**
	 * Adds the action parameters to the given
	 * hyperlink
	 * 
	 * @param		link Hyperlink
	 */
	private void addActionParams(Hyperlink link) {

		// Create a named (or generic) action
		link.addQueryParameter(ACTION_URL_CONTROL, controlName);

		link.addQueryParameter(ACTION_URL_ACTION, action.getName());

		int paramCount = action.getFormalParameterCount();
		for (int i = 0; i < paramCount; i++) {
			link.addQueryParameter(ACTION_URL_PARAMETER, parameter[i]);
		}
	}
	
	/**
	 * Creates a hyperlink
	 * 
	 * @param pageContext
	 *            PageContext
	 * @return Hyperlink
	 */
	public Hyperlink createHyperlink(PageContext pageContext) {
		String prefix = getActionMappingURL(pageContext, actionName);

		if (prefix == null) {
			prefix = "#";
		}

		Hyperlink link = Hyperlink.parse(prefix);

		addActionParams(link);

		if (transaction) {
			FrameworkAdapterFactory.getAdapter().addTransactionToken(pageContext, link);
		}

		return link;
	}

	/**
	 * Creates a hyperlink
	 * 
	 * @param pageContext
	 *            PageContext
	 * @return Hyperlink String
	 */
	public String createHref(PageContext pageContext) {
		Hyperlink link = createHyperlink(pageContext);

		return PainterHelp.decorateLink(pageContext, link);
	}

	/**
	 * Creates a JavaScript Submit handler for the enclosing form
	 *
	 * @param		pageContext	PageContext
	 * @return		JavaScript code
	 */
	public String createSubmitHandler(PageContext pageContext) {
		return createSubmitHandler(pageContext, null);
	}

	/**
	 * Creates a JavaScript Submit handler for the enclosing form
	 *
	 * @param		pageContext	PageContext
	 * @param		userScript String an optional userdefined JavaScript
	 * 				code that should be executed first
	 * @return		JavaScript code
	 */
	public String createSubmitHandler(PageContext pageContext, String userScript) {
		// Aufgabe der JavaScript Funktion crtCtrla():
		// - Erzeugung eines Hiddenfield mit dem Namen "ctrla"
		//   (= ControlAction.ACTION_INPUT_CONTROL)
		// - Submit() des Forumlars

		// crtCtrla(node, param, [formId], [userscript])

		// -> node
		StringBuffer handler = new StringBuffer("CCUtility.crtCtrla(this");

		// -> param
		handler.append(", \"");

		handler.append(controlName).append("=").append(
			action.getName());

		int paramCount = action.getFormalParameterCount();

		for (int i = 0; i < paramCount; i++) {
			handler.append("=").append(parameter[i]);
		}
		handler.append("\"");

		// -> formId
		handler.append(", null");

		// -> userscript
		handler.append(", ");

		if (userScript == null) {
			handler.append("null");
		} else {
			handler
				.append("\"")
				.append(JavaScriptUtil.encodeJavaScript(userScript))
				.append("\"");
		}

		handler.append(");");

		return handler.toString();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		Hyperlink link = new Hyperlink();
		addActionParams(link);
		
		return link.toString();
	}
}