/**
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/util/converters/AppointmentPriorityConverter.java,v 1.2 2005/07/28 19:40:27 P001002 Exp $
 * $Revision: 1.2 $
 * $Date: 2005/07/28 19:40:27 $
 *
 * ====================================================================
 *
 * Copyright (c) 2002 SCC Informationssysteme GmbH. All rights
 * reserved.
 *
 * ====================================================================
 */

package com.cc.framework.util.converters;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;

import com.cc.framework.common.InvalidEnumType;
import com.cc.framework.ui.model.AppointmentPriority;

/**
 * Bean Converter for AppointmentPriority enumeration type
 *
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.2 $
 * @deprecated
 */
public class AppointmentPriorityConverter implements Converter {

	/**
	 * Convert the specified input object into an output object of the
	 * specified type.
	 *
	 * @param		type Data type to which this value should be converted
	 * @param		value The input value to be converted
	 * @return		AppointmentPriority instance
	 * @exception	ConversionException if conversion cannot be performed
	 * 				successfully
	 */
	public Object convert(Class type, Object value) throws ConversionException {
		if (!AppointmentPriority.class.equals(type)) {
			throw new ConversionException("Illegal conversion target: " + type.getName());
		}

		if (value == null) {
			return null;
		} else if (value instanceof AppointmentPriority) {
			return value;
		} else {
			try {
				return AppointmentPriority.parse(value.toString());
			} catch (InvalidEnumType iet) {
				throw new ConversionException(iet);
			}
		}
	}
}