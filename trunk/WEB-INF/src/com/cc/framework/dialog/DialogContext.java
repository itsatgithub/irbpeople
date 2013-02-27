/*
 * $Header: d:/repository/cvs/cc-adapter-struts-1.2/adapter/com/cc/framework/dialog/DialogContext.java,v 1.5 2005/08/17 20:17:29 P001002 Exp $
 * $Revision: 1.5 $
 * $Date: 2005/08/17 20:17:29 $
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

package com.cc.framework.dialog;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.LogFactory;

import com.cc.framework.Globals;
import com.cc.framework.adapter.RequestContext;

/**
 * Base Class for all dialog context classes.
 * 
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version $Revision: 1.5 $
 * @since 1.0
 */
public abstract class DialogContext {

	/**
	 * The action to return to
	 */
	private String lastAction = null;

	/**
	 * The registered attributes. HashMap chosen over Hashtable for its ability
	 * to store a null value.
	 */
	private HashMap attributes = null;

	// --------------------------
	// methods
	// --------------------------

	/**
	 * Returns a collection with the active Dialog Contexts
	 * 
	 * @param session
	 *            HttpSession
	 * @return Hashtable
	 */
	private static Hashtable getContextTable(HttpSession session) {

		// check if a session exists
		if (session == null) {
			return null;
		}

		Hashtable contextTable = (Hashtable) session
				.getAttribute(Globals.DIALOG_KEY);

		if (contextTable == null) {
			contextTable = new Hashtable();

			session.setAttribute(Globals.DIALOG_KEY, contextTable);
		}

		return contextTable;
	}

	/**
	 * Identifys or creates a new dialog context from the users session.
	 * 
	 * @param session
	 *            HttpSession
	 * @param name
	 *            Name
	 * @param ctxClass
	 *            Context Class
	 * @return DialogContext
	 */
	protected static DialogContext get(HttpSession session, String name,
			Class ctxClass) {

		DialogContext dialogContext = null;
		Hashtable contextTable = getContextTable(session);

		if (contextTable == null) {
			return null;
		}

		// Check for an existing instance
		dialogContext = (DialogContext) contextTable.get(name);

		if (dialogContext == null) {
			// the context object was no present so we will creat a new one
			try {
				dialogContext = (DialogContext) ctxClass.newInstance();

				contextTable.put(name, dialogContext);
			} catch (Exception e) {
				LogFactory.getLog(DialogContext.class.getName()).error(e);
			}
		}

		return dialogContext;
	}

	/**
	 * Identifys the specified dialog context from the users session. If no
	 * context is specified a new context will be created.
	 * 
	 * @param session
	 *            HttpSession
	 * @param create
	 *            Zeigt an, ob der Dialogkontext neu erzeugt werden soll
	 * @param name
	 *            Name
	 * @param ctxClass
	 *            Context Class
	 * @return DialogContext
	 */
	protected static DialogContext get(HttpSession session, boolean create,
			String name, Class ctxClass) {

		DialogContext dialogContext = null;
		Hashtable contextTable = getContextTable(session);

		if (contextTable == null) {
			return null;
		}

		// Check for an existing instance
		if (!create) {
			dialogContext = (DialogContext) contextTable.get(name);
		} else {
			// the context object was no present so we will creat a new one
			try {
				dialogContext = (DialogContext) ctxClass.newInstance();

				contextTable.put(name, dialogContext);
			} catch (Exception e) {
				LogFactory.getLog(DialogContext.class.getName()).error(e);
			}
		}

		return dialogContext;
	}

	/**
	 * Returns a Dialog Context
	 * 
	 * @param ctx
	 *            ActionContext
	 * @param create
	 *            Zeigt an, ob der Dialogkontext neu erzeugt werden soll
	 * @param name
	 *            Name
	 * @param ctxClass
	 *            Context Class
	 * @return DialogContext
	 */
	protected static DialogContext get(RequestContext ctx, boolean create,
			String name, Class ctxClass) {
		return get(ctx.session(), create, name, ctxClass);
	}

	/**
	 * Returns a Dialog Context
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param create
	 *            Zeigt an, ob der Dialogkontext neu erzeugt werden soll
	 * @param name
	 *            Name
	 * @param ctxClass
	 *            Context Class
	 * @return DialogContext
	 */
	protected static DialogContext get(HttpServletRequest request,
			boolean create, String name, Class ctxClass) {
		return get(request.getSession(false), create, name, ctxClass);
	}

	/**
	 * Removes a Context from the memory
	 * 
	 * @param ctx
	 *            ActionContext
	 * @param name
	 *            Name
	 */
	public static void remove(RequestContext ctx, String name) {

		Hashtable contextTable = getContextTable(ctx.session());

		if (contextTable != null) {
			contextTable.remove(name);
		}
	}

	/**
	 * @return The names of all known attributes for this workflow. May be null
	 *         if there are no known attributes.
	 */
	public synchronized Set getAttributeNames() {
		Set retVal = null;

		if (null != attributes) {
			retVal = attributes.keySet();
		}

		return retVal;
	}

	/**
	 * @return The number of all known attributes for this workflow.
	 */
	public synchronized int getAttributeCount() {
		int retVal = 0;

		if (null != attributes) {
			retVal = attributes.size();
		}

		return retVal;
	}

	/**
	 * Retrieve the value associated with the specified name.
	 * 
	 * @param attributeName
	 *            The name associated with the value sought.
	 * @return The value associated with the specified name. May be null if the
	 *         attribute with the specified name is unknown.
	 */
	public synchronized Object getAttribute(String attributeName) {
		Object retVal = null;

		if (null != attributes) {
			retVal = attributes.get(attributeName);
		}

		return retVal;
	}

	/**
	 * Remove the value associated with the specified name and return it.
	 * 
	 * @param attributeName
	 *            The name associated with the value sought.
	 * @return The value associated with the specified name that was removed.
	 *         May be null if the attribute with the specified name is unknown.
	 */
	public synchronized Object removeAttribute(String attributeName) {
		Object retVal = null;

		if (null != attributes) {
			retVal = attributes.remove(attributeName);
		}

		return retVal;
	}

	/**
	 * Associate a value with the specified name which will remain available
	 * until this workflow terminates or it is removed using the
	 * removeAttribute() method.
	 * 
	 * @param attributeName
	 *            The name associated with the value to be stored.
	 * @param attributeValue
	 *            The value to be associated with the name and stored. If an
	 *            attribute is already stored under the specified name for this
	 *            workflow, it is replaced by this one.
	 * @throws IllegalArgumentException
	 *             If the attribute name is null.
	 */
	public synchronized void setAttribute(String attributeName,
			Object attributeValue) throws IllegalArgumentException {
		// even though the hash map will accept a null key, it is hard to see
		// this as anything other than a mistake
		if (null == attributeName) {
			throw new IllegalArgumentException("Attribute name cannot be null");
		}

		if (null == attributes) {
			attributes = new HashMap();
		}

		attributes.put(attributeName, attributeValue);
	}

	/**
	 * Clears all known names/values.
	 */
	public synchronized void clearAttributes() {
		if (null != attributes) {
			attributes.clear();
			attributes = null;
		}
	}

	/**
	 * Gets the lastAction
	 * 
	 * @return Returns a String
	 */
	public String getLastAction() {
		return lastAction;
	}

	/**
	 * Sets the lastAction
	 * 
	 * @param lastAction
	 *            The lastAction to set
	 */
	public void setLastAction(String lastAction) {
		this.lastAction = lastAction;
	}
}