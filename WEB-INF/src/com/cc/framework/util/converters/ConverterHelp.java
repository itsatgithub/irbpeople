/**
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/util/converters/ConverterHelp.java,v 1.2 2005/07/28 19:40:27 P001002 Exp $
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

import java.util.Locale;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

import com.cc.framework.ui.model.AppointmentPriority;

/**
 *
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.2 $
 * @deprecated
 */
public class ConverterHelp {

	/**
	 * Registers Bean Converters for complex framework types
	 */
	public static void registerFrameworkConverters() {
		ConvertUtils.register(new AppointmentPriorityConverter(), AppointmentPriority.class);
		ConvertUtils.register(new StringConverter(), String.class);

		String pattern = "yyyy.MM.dd HH:mm:ss";
		Locale locale = Locale.getDefault();
		DateLocaleConverter converter = new DateLocaleConverter(locale, pattern);
		converter.setLenient(true);
		ConvertUtils.register(converter, java.util.Date.class);
	}
}