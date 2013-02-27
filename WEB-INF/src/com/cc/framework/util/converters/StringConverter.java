/**
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/util/converters/StringConverter.java,v 1.5 2005/09/27 14:06:22 P001002 Exp $
 * $Revision: 1.5 $
 * $Date: 2005/09/27 14:06:22 $
 *
 * ====================================================================
 *
 * Copyright (c) 2002 SCC Informationssysteme GmbH. All rights
 * reserved.
 *
 * ====================================================================
 */

package com.cc.framework.util.converters;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;

/**
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.5 $
 * @deprecated
 */
public class StringConverter implements Converter {

	/**
	 * Convert the specified input object into an output object of the
	 * specified type.
	 *
	 * @param		type Data type to which this value should be converted
	 * @param		value The input value to be converted
	 * @return		converted object
	 * @exception	ConversionException if conversion cannot be performed
	 * 				successfully
	 */
	public Object convert(Class type, Object value) throws ConversionException {

		if (value == null) {
			return null;
		} else if (value instanceof Date) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

			return formatter.format(value);
		} else {
			return (value.toString());
		}
	}
}
