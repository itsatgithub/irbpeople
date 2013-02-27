/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/util/Util.java,v 1.24 2005/07/28 19:40:27 P001002 Exp $
 * $Revision: 1.24 $
 * $Date: 2005/07/28 19:40:27 $
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

package com.cc.framework.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cc.framework.common.CheckState;

/**
 * Utility class to compare/sort objects
 *
 * @author	   <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version	   $Revision: 1.24 $
 * @since      1.0
 */
public abstract class Util {

	/**
	 * Constructor
	 */
	private Util() {
		super();
	}

	/**
	 * Compares to objects for order
	 *
	 * @param	a	the Object1 to be compared.
	 * @param	b	the Object2 to be compared.
	 * @return	Object
	 */
	public static Object max(Comparable a, Comparable b) {
		return (a.compareTo(b) > 0) ? a : b;
	}

	/**
	 * Compares two objects for order
	 *
	 * @param	a	the Object1 to be compared.
	 * @param	b	the Object2 to be compared.
	 * @return	Object
	 */
	public static Object min(Comparable a, Comparable b) {
		return (a.compareTo(b) <= 0) ? a : b;
	}

	/**
	 * Compares three objects for order
	 *
	 * @param	a	the Object1 to be compared.
	 * @param	b	the Object2 to be compared.
	 * @param	c	the Object3 to be compared.
	 *
	 * @return	Object
	 */
	public static Object min(Comparable a, Comparable b, Comparable c) {
		return min((Comparable) min(a, b), c);
	}

	/**
	 * Converts an object to a boolean if possible
	 *
	 * @param	obj		The object to convert
	 * @return	The boolean value
	 */
	public static Boolean toBoolean(Object obj) {
		if (obj instanceof Boolean) {
			return (Boolean) obj;
		} else if (obj instanceof String) {
			return toBoolean((String) obj);
		} else if (obj instanceof Number) {
			return (((Number) obj).intValue() == 1) ? Boolean.TRUE : Boolean.FALSE;
		}

		// Could not find boolean value
		return null;
	}

	/**
	 * Parses a string an returns a boolean value.
	 *
	 * @param	str		The	string to parse
	 * @return	The boolean value
	 */
	public static Boolean toBoolean(String str) {
		if ("true".equalsIgnoreCase(str)
			|| "on".equalsIgnoreCase(str)
			|| "1".equals(str)) {
			return Boolean.TRUE;
		}

		if ("".equals(str)
			|| "false".equalsIgnoreCase(str)
			|| "off".equalsIgnoreCase(str)
			|| "0".equals(str)) {
			return Boolean.FALSE;
		}

		// Could not find boolean value
		return null;
	}

	/**
	 * Unchecks a checked property
	 *
	 * @param		bean the bean
	 * @param		property The check property
	 * @throws		IllegalAccessException Property Setter is not accessible
	 * @throws		NoSuchMethodException Property Setter is not available
	 * @throws		InvocationTargetException Propperty Setter could not be called
	 */
	public static void uncheck(Object bean, String property)
		throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {

		Object value		= getProperty(bean, property);
		Class propertyType	= getPropertyType(bean, property);

		if (Integer.class.equals(propertyType) || int.class.equals(propertyType)) {
			// Ignore all states <= 0 (!)
			if (((Integer) value).intValue() > 0) {
				// uncheck a checked integer value
				setProperty(bean, property, new Integer(0));
			}
		} else if (Boolean.class.equals(propertyType) || boolean.class.equals(propertyType)) {
			if (((Boolean) value).booleanValue()) {
				// uncheck a checked boolean value
				setProperty(bean, property, Boolean.FALSE);
			}
		} else if (CheckState.class.equals(propertyType)) {
			// Ignore all states <= 0 (!)
			if (((CheckState) value).toInt() > 0) {
				// uncheck a checkstate value
				setProperty(bean, property, CheckState.UNCHECKED);
			}
		} else if (String.class.equals(propertyType)) {
			if ("on".equalsIgnoreCase((String) value)) {
				// uncheck a checked String value
				setProperty(bean, property, "off");
			}
		}
	}

	/**
	 * checks a property
	 *
	 * @param		bean the bean
	 * @param		property The check property
	 * @param		checked check state
	 * @throws		IllegalAccessException Property Setter is not accessible
	 * @throws		NoSuchMethodException Property Setter is not available
	 * @throws		InvocationTargetException Propperty Setter could not be called
	 */
	public static void check(Object bean, String property, boolean checked)
		throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {

		Object value		= getProperty(bean, property);
		Class propertyType	= getPropertyType(bean, property);

		if (Integer.class.equals(propertyType) || int.class.equals(propertyType)) {
			// Ignore all states < 0 (!)
			if (((Integer) value).intValue() >= 0) {
				// check a integer value
				setProperty(bean, property, new Integer(checked ? 1 : 0));
			}
		} else if (Boolean.class.equals(propertyType) || boolean.class.equals(propertyType)) {
			if (((Boolean) value).booleanValue() != checked) {
				// uncheck a checked boolean value
				setProperty(bean, property, new Boolean(checked));
			}
		} else if (CheckState.class.equals(propertyType)) {
			// Ignore all states < 0 (!)
			if (((CheckState) value).toInt() >= 0) {
				// uncheck a checkstate value
				setProperty(bean, property, checked ? CheckState.CHECKED : CheckState.UNCHECKED);
			}
		} else if (String.class.equals(propertyType)) {
			setProperty(bean, property, checked ? "on" : "off");
		}
	}

	/**
	 * retrieves the check state for a given property
	 *
	 * @param		bean the bean
	 * @param		property The check property
	 * @return		check state
	 * @throws		IllegalAccessException Property Setter is not accessible
	 * @throws		NoSuchMethodException Property Setter is not available
	 * @throws		InvocationTargetException Propperty Setter could not be called
	 */
	public static CheckState getCheckState(Object bean, String property)
		throws
			IllegalAccessException,
			NoSuchMethodException,
			InvocationTargetException {

		Object value		= getProperty(bean, property);
		Class propertyType	= getPropertyType(bean, property);

		if (Integer.class.equals(propertyType) || int.class.equals(propertyType)) {
			return CheckState.parseInt(((Integer) value).intValue());
		} else if (Boolean.class.equals(propertyType) || boolean.class.equals(propertyType)) {
			return ((Boolean) value).booleanValue() ? CheckState.CHECKED : CheckState.UNCHECKED;
		} else if (CheckState.class.equals(propertyType)) {
			return (CheckState) value;
		} else if (String.class.equals(propertyType)) {
			return "on".equalsIgnoreCase((String) value) ? CheckState.CHECKED : CheckState.UNCHECKED;
		} else {
			return CheckState.UNDEFINED;
		}
	}

	/**
	 * Return the Java Class representing the property type of the specified
	 * property, or <code>null</code> if there is no such property for the
	 * specified bean. This method follows the same name resolution rules
	 * used by <code>getPropertyDescriptor()</code>, so if the last element
	 * of a name reference is indexed, the type of the property itself will
	 * be returned.  If the last (or only) element has no property with the
	 * specified name, <code>null</code> is returned.
	 *
	 * @param bean Bean for which a property descriptor is requested
	 * @param name Possibly indexed and/or nested name of the property for
	 *  which a property descriptor is requested
	 *
	 * @exception IllegalAccessException if the caller does not have
	 *  access to the property accessor method
	 * @exception InvocationTargetException if the property accessor method
	 *  throws an exception
	 * @exception NoSuchMethodException if an accessor method for this
	 *  propety cannot be found
	 *
	 * @return	property type
	 */
	public static Class getPropertyType(Object bean, String name)
		throws
			IllegalAccessException,
			InvocationTargetException,
			NoSuchMethodException {
		return PropertyUtils.getPropertyType(bean, name);
	}

	/**
	 * Return the value of the specified property of the specified bean,
	 * no matter which property reference format is used, as a String.
	 *
	 * @param bean Bean whose property is to be extracted
	 * @param name Possibly indexed and/or nested name of the property
	 *  to be extracted
	 *
	 * @exception IllegalAccessException if the caller does not have
	 *  access to the property accessor method
	 * @exception InvocationTargetException if the property accessor method
	 *  throws an exception
	 * @exception NoSuchMethodException if an accessor method for this
	 *  property cannot be found
	 *
	 * @return		property value
	 */
	public static Object getProperty(Object bean, String name)
		throws
			IllegalAccessException,
			InvocationTargetException,
			NoSuchMethodException {
		return PropertyUtils.getProperty(bean, name);
	}

	/**
	 * Checks if the current iteration element has the
	 * given property
	 *
	 * @param		bean Bean whose property is to be extracted
	 * @param		name Possibly indexed and/or nested name of the property
	 * 				to be extracted
	 * @return		<code>true</code> if the current bean
	 * 				posesses a matching property
	 */
	public static boolean isValidProperty(Object bean, String name) {
		PropertyDescriptor pd = null;

		try {
			pd = PropertyUtils.getPropertyDescriptor(bean, name);
		} catch (Exception e) {
			// ignore exception
		}

		return (pd != null);
	}

	/**
	 * Return the value of the specified property of the specified bean,
	 * no matter which property reference format is used, as a String.
	 *
	 * @param		bean Bean whose property is to be extracted
	 * @param		name Possibly indexed and/or nested name of the property
	 * 				to be extracted
	 * @return		property value or <code>null</code>
	 */
	public static Object getSafeProperty(Object bean, String name) {
		try {
			return PropertyUtils.getProperty(bean, name);
		} catch (Exception e) {
			Log log = LogFactory.getLog(Util.class);

			if (log.isDebugEnabled()) {
				log.debug(
					"Problem while calling property ["
						+ name
						+ "] on bean ["
						+ bean
						+ "]"
						+ e.getMessage());
			}

			return null;
		}
	}

	/**
	 * <p>Set the specified property value, performing type conversions as
	 * required to conform to the type of the destination property.</p>
	 *
	 * <p>If the property is read only then the method returns
	 * without throwing an exception.</p>
	 *
	 * <p>If <code>null</code> is passed into a property expecting a primitive value,
	 * then this will be converted as if it were a <code>null</code> string.</p>
	 *
	 * <p><strong>WARNING</strong> - The logic of this method is customized
	 * to meet the needs of <code>populate()</code>, and is probably not what
	 * you want for general property copying with type conversion.  For that
	 * purpose, check out the <code>copyProperty()</code> method instead.</p>
	 *
	 * <p><strong>WARNING</strong> - PLEASE do not modify the behavior of this
	 * method without consulting with the Struts developer community.  There
	 * are some subtleties to its functionality that are not documented in the
	 * Javadoc description above, yet are vital to the way that Struts utilizes
	 * this method.</p>
	 *
	 * @param bean Bean on which setting is to be performed
	 * @param name Property name (can be nested/indexed/mapped/combo)
	 * @param value Value to be set
	 *
	 * @exception IllegalAccessException if the caller does not have
	 *  access to the property accessor method
	 * @exception InvocationTargetException if the property accessor method
	 *  throws an exception
	 */
	public static void setProperty(Object bean, String name, Object value)
		throws IllegalAccessException, InvocationTargetException {

		BeanUtils.setProperty(bean, name, value);
	}
}