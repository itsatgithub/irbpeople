/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/TagHelp.java,v 1.32 2005/08/23 12:22:26 P001002 Exp $
 * $Revision: 1.32 $
 * $Date: 2005/08/23 12:22:26 $
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

package com.cc.framework.taglib;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.text.MessageFormat;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import com.cc.framework.Globals;
import com.cc.framework.adapter.FrameworkAdapterFactory;
import com.cc.framework.common.InvalidEnumType;
import com.cc.framework.http.AttributeAccess;
import com.cc.framework.http.HttpScope;
import com.cc.framework.security.Permission;
import com.cc.framework.security.PermissionException;
import com.cc.framework.taglib.controls.ColumnHtmlTag;
import com.cc.framework.taglib.forms.FormElementContainerTag;
import com.cc.framework.ui.AlignmentType;
import com.cc.framework.ui.Color;
import com.cc.framework.ui.ImageMap;
import com.cc.framework.util.Util;

/**
 * Helper for the tag library
 * 
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version $Revision: 1.32 $
 * @since 1.0
 */
public abstract class TagHelp {

	/**
	 * Constructor
	 */
	private TagHelp() {
		super();
	}

	/**
	 * Searches the Tag hierarchy for a FormElementContainerTag parent
	 * 
	 * @param tag
	 *            the tag to start searching
	 * @return Container
	 * @throws JspException
	 *             is thrown when no parent is present
	 */
	public static FormElementContainerTag getContainer(Tag tag)
			throws JspException {
		Tag container = TagSupport.findAncestorWithClass(tag,
				FormElementContainerTag.class);

		if (container == null) {
			String className = tag.getClass().getName();
			className = className.substring(className.lastIndexOf('.') + 1);

			throw new JspException(className
					+ " must be nested within a FormElementContainerTag tag!");
		}

		return (FormElementContainerTag) container;
	}

	/**
	 * Searches the Tag hierarchy for a FrameContainerTag parent
	 * 
	 * @param tag
	 *            the tag to start searching
	 * @param parrentTagClass
	 *            The Type of the parent tag
	 * @return The Parent tag
	 * @throws JspException
	 *             is thrown when no parent is present
	 */
	public static Tag getParentTag(Tag tag, Class parrentTagClass)
			throws JspException {
		Tag parent = TagSupport.findAncestorWithClass(tag, parrentTagClass);

		if (parent == null) {
			String className;
			className = tag.getClass().getName();
			className = className.substring(className.lastIndexOf('.') + 1);

			StringBuffer err = new StringBuffer().append(className).append(
					" must be nested within a ").append(
					parrentTagClass.getName()).append(" tag!");

			throw new JspException(err.toString());
		}

		return parent;
	}

	/**
	 * Lookup a Javabean for use in a jsp tag handler.
	 * 
	 * @param pageContext
	 *            jsp page context
	 * @param taghandler
	 *            the current tag handler
	 * @param name
	 *            Name of the bean
	 * @param property
	 *            Name of a beans property
	 * @param scope
	 *            the scope where the bean should be located
	 * @return Java Bean or <code>null</code>
	 * @throws JspException
	 *             is thrown in cas of an error
	 */
	public static Object lookupBean(PageContext pageContext,
			TagSupport taghandler, String name, String property, HttpScope scope)
			throws JspException {

		// Search for the bean
		try {
			if (name == null) {
				ColumnHtmlTag column = (ColumnHtmlTag) TagSupport
						.findAncestorWithClass(taghandler, ColumnHtmlTag.class);

				if (column != null) {
					// the control is nested in a html column.
					// Use the current row bean
					Object bean = column.getCurrent();

					if (property == null) {
						return bean;
					} else {
						return Util.getProperty(bean, property);
					}
				}
			}

			// Search for the bean
			if ((name == null) && (property == null)) {
				return null;
			} else {
				return FrameworkAdapterFactory.getAdapter().lookupBean(
						pageContext, name, property, scope);
			}
		} catch (Throwable t) {
			throw new JspException(t);
		}
	}

	/**
	 * This method checks if a class is derived from the specified baseclass
	 * 
	 * @param theClass
	 *            the class to check
	 * @param baseClass
	 *            the baseclass
	 * @return returns <code>true</code> if the class is derived from the
	 *         basclass
	 */
	public static boolean checkForBaseClass(Class theClass, Class baseClass) {
		do {
			if ((baseClass.getModifiers() & Modifier.INTERFACE) != 0) {
				// Check for an interface
				Class[] interfaces = theClass.getInterfaces();
				for (int i = 0; i < interfaces.length; i++) {
					if (interfaces[i] == baseClass) {
						return true;
					}
				}
			} else if (theClass.equals(baseClass)) {
				return true;
			}

			theClass = theClass.getSuperclass();
		} while (theClass != null);

		// Base class not found
		return false;
	}

	/**
	 * Converts an object to a boolean if possible
	 * 
	 * @param obj
	 *            The object to convert
	 * @return The boolean value
	 * @throws JspException
	 *             If the object can't be converted
	 */
	public static boolean toBoolean(Object obj) throws JspException {
		Boolean value = Util.toBoolean(obj);

		if (value == null) {
			throw new JspException("Invalid value for boolean attribute: "
					+ obj);
		}

		return value.booleanValue();
	}

	/**
	 * Converts an object to a int if possible
	 * 
	 * @param str
	 *            The string to convert
	 * @return The boolean value
	 * @throws JspException
	 *             If the object can't be converted
	 */
	public static int toInt(String str) throws JspException {
		try {
			return Integer.parseInt(str);
		} catch (NumberFormatException nfe) {
			throw new JspException("Invalid value for int attribute: " + str);
		}
	}

	/**
	 * Parses a string an returns a boolean value.
	 * 
	 * @param str
	 *            The string to parse
	 * @return The boolean value
	 * @throws JspException
	 *             If the argument can't be parsed
	 */
	public static boolean toBoolean(String str) throws JspException {
		Boolean value = Util.toBoolean(str);

		if (value == null) {
			throw new JspException("Invalid value for boolean attribute: '"
					+ str + "'");
		}

		return value.booleanValue();
	}

	/**
	 * Creates a permission object for the specified argument.
	 * 
	 * @param permission
	 *            The string to parse
	 * @return The permission object
	 * @throws JspException
	 *             If the argument can not be parsed
	 */
	public static Permission toPermission(String permission)
			throws JspException {
		try {
			return Permission.parse(permission);
		} catch (PermissionException ipe) {
			throw new JspException(ipe.getMessage());
		}
	}

	/**
	 * Creates a AlignmentType object for the specified argument.
	 * 
	 * @param alignment
	 *            The string to parse
	 * @return The alignment object
	 * @throws JspException
	 *             If the argument can not be parsed
	 */
	public static AlignmentType toAlignment(String alignment)
			throws JspException {
		try {
			return AlignmentType.parse(alignment);
		} catch (InvalidEnumType iet) {
			throw new JspException(iet.getMessage());
		}
	}

	/**
	 * Creates a color object for the specified argument.
	 * 
	 * @param color
	 *            The string with the color value to parse
	 * @return The color object
	 */
	public static Color toColor(String color) {
		return Color.mapString(color);
	}

	/**
	 * Locate and return the specified bean, from the dialog scope. If no such
	 * bean is found, return <code>null</code> instead.
	 * 
	 * @param pageContext
	 *            Page context to be searched
	 * @param name
	 *            Name of the bean to be retrieved
	 * @return JavaBean in the specified page context
	 */
	private static Object lookupDialog(PageContext pageContext, String name) {
		Object bean = null;

		AttributeAccess attr = (AttributeAccess) pageContext.getAttribute(
				Globals.DIALOG_KEY, PageContext.REQUEST_SCOPE);

		if (attr != null) {
			bean = attr.getAttribute(name);
		}

		return bean;
	}

	/**
	 * Returns an image map
	 * 
	 * @param pageContext
	 *            The page context
	 * @param mapName
	 *            The name of the image map
	 * @return The image map
	 * @throws JspException
	 *             If the image map can not be found
	 */
	public static ImageMap lookupImageMap(PageContext pageContext,
			String mapName) throws JspException {

		Object value = TagHelp.lookup(pageContext, mapName, null, null);

		if (!(value instanceof ImageMap)) {
			throw new JspException("ImageMap not found " + mapName);
		}

		return (ImageMap) value;
	}

	/**
	 * Locate and return the specified bean, from an optionally specified scope,
	 * in the specified page context. If no such bean is found, return
	 * <code>null</code> instead.
	 * 
	 * @param pageContext
	 *            Page context to be searched
	 * @param name
	 *            Name of the bean to be retrieved
	 * @param scope
	 *            Scope to be searched (page, request, session, application) or
	 *            <code>null</code> to use <code>findAttribute()</code>
	 *            instead
	 * @return JavaBean in the specified page context
	 */
	public static Object lookup(PageContext pageContext, String name,
			HttpScope scope) {
		Object bean = null;

		if (HttpScope.ANY.equals(scope)) {
			bean = pageContext.findAttribute(name);

			// Zusätzlich im dialog Scope nachsehen
			if (bean == null) {
				bean = lookupDialog(pageContext, name);
			}
		} else if (HttpScope.PAGE.equals(scope)) {
			bean = pageContext.getAttribute(name, PageContext.PAGE_SCOPE);
		} else if (HttpScope.DIALOG.equals(scope)) {
			bean = lookupDialog(pageContext, name);
		} else if (HttpScope.REQUEST.equals(scope)) {
			bean = pageContext.getAttribute(name, PageContext.REQUEST_SCOPE);
		} else if (HttpScope.SESSION.equals(scope)) {
			bean = pageContext.getAttribute(name, PageContext.SESSION_SCOPE);
		} else if (HttpScope.APPLICATION.equals(scope)) {
			bean = pageContext.getAttribute(name, PageContext.APPLICATION_SCOPE);
		}

		return bean;
	}

	/**
	 * Locate and return the specified property of the specified bean, from an
	 * optionally specified scope, in the specified page context. If an
	 * exception is thrown, it will have already been saved via a call to
	 * <code>saveException()</code>.
	 * 
	 * @param pageContext
	 *            Page context to be searched
	 * @param name
	 *            Name of the bean to be retrieved
	 * @param property
	 *            Name of the property to be retrieved, or <code>null</code>
	 *            to retrieve the bean itself
	 * @param scope
	 *            Scope to be searched (page, request, session, application) or
	 *            <code>null</code> to use <code>findAttribute()</code>
	 *            instead
	 * @return property of specified JavaBean
	 * 
	 * @exception JspException
	 *                if an invalid scope name is requested
	 * @exception JspException
	 *                if the specified bean is not found
	 * @exception JspException
	 *                if accessing this property causes an
	 *                IllegalAccessException, IllegalArgumentException,
	 *                InvocationTargetException, or NoSuchMethodException
	 */
	public static Object lookup(PageContext pageContext, String name,
			String property, HttpScope scope) throws JspException {

		if (scope == null) {
			scope = HttpScope.ANY;
		}

		// Look up the requested bean, and return if requested
		Object bean = lookup(pageContext, name, scope);

		if (bean == null) {
			JspException e = null;

			if (HttpScope.ANY.equals(scope)) {
				e = new JspException("Cannot find bean [" + name
						+ "] in any scope");
			} else {
				e = new JspException("Cannot find bean [" + name
						+ "] in scope [" + scope.toString() + "]");
			}

			throw e;
		}

		if (property == null) {
			return bean;
		}

		// Locate and return the specified property
		try {
			return (Util.getProperty(bean, property));
		} catch (IllegalAccessException e) {
			throw new JspException(MessageFormat.format(
				"Invalid access looking up property '{0}' of bean [{1}]",
				new Object[] { property, name }));
		} catch (InvocationTargetException e) {
			throw new JspException(MessageFormat.format(
				"Exception thrown by getter for property '{0}' of bean [{1}]",
				new Object[] { property, name }));
		} catch (NoSuchMethodException e) {
			throw new JspException(MessageFormat.format(
				"No getter method for property '{0}' of bean [{1}]",
				new Object[] { property, name }));
		} catch (Exception e) {
			throw new JspException(MessageFormat.format(
				"Error while calling getter method for property '{0}' of bean [{1}]",
				new Object[] { property, name }));
		}
	}
}