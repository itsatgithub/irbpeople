/*
 * $Header$
 * $Revision$
 * $Date$
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

package com.cc.framework.convert;

import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cc.framework.common.Singleton;
import com.cc.framework.convert.imp.AppointmentPriorityConverter;
import com.cc.framework.convert.imp.BooleanConverter;
import com.cc.framework.convert.imp.ByteSizeConverter;
import com.cc.framework.convert.imp.DateConverter;
import com.cc.framework.convert.imp.DateTimeConverter;
import com.cc.framework.convert.imp.DoubleConverter;
import com.cc.framework.convert.imp.FloatConverter;
import com.cc.framework.convert.imp.IntegerConverter;
import com.cc.framework.convert.imp.LongConverter;
import com.cc.framework.convert.imp.NumberConverter;
import com.cc.framework.convert.imp.StringConverter;

/**
 * The ConverterRegistry is the central place where all converters are
 * registered. 
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision$
 */
public class ConverterRegistry implements Singleton {

	/**
	 * Logging instance
	 */
	private Log log = LogFactory.getLog(getClass());

	/**
	 * This map maps java data types to converters Map<Class, Converter>
	 */
	private Map converters = new Hashtable();

	/**
	 * This is a registry for custom Converter classes
	 */
	private Map customConverters = new Hashtable();

	/**
	 * This is a cache for reusable custom Converter instances
	 */
	private Map converterCache = new Hashtable();

	/**
	 * The default converter that should be used when no
	 * matching converter is available
	 */
	private Converter defaultConverter = new StringConverter();

	/**
	 * Singleton instance
	 */
	private static ConverterRegistry instance = new ConverterRegistry();

	/**
	 * Constructor
	 */
	private ConverterRegistry() {
		super();

		// Register the predefined Converters
		init();
	}

	/**
	 * Register the predefined Converters
	 */
	public void init() {
		converters.clear();
		customConverters.clear();
		converterCache.clear();

		// Base Types
		registerTypeConverter(String.class, new StringConverter());

		registerTypeConverter(Boolean.class, new BooleanConverter());
		registerTypeConverter(Boolean.TYPE, new BooleanConverter());

		registerTypeConverter(Integer.class, new IntegerConverter());
		registerTypeConverter(Integer.TYPE, new IntegerConverter());

		registerTypeConverter(Long.class, new LongConverter());
		registerTypeConverter(Long.TYPE, new LongConverter());

		registerTypeConverter(Double.class, new DoubleConverter());
		registerTypeConverter(Double.TYPE, new DoubleConverter());

		registerTypeConverter(Float.class, new FloatConverter());
		registerTypeConverter(Float.TYPE, new FloatConverter());

		registerTypeConverter(Date.class, new DateConverter());

//        register(BigDecimal.class, new BigDecimalConverter());
//        register(BigInteger.class, new BigIntegerConverter());
//        register(Byte.TYPE, new ByteConverter());
//        register(Byte.class, new ByteConverter());
//        register(Character.TYPE, new CharacterConverter());
//        register(Character.class, new CharacterConverter());
//        register(Short.TYPE, new ShortConverter());
//        register(Short.class, new ShortConverter());
//        register(Date.class, new SqlDateConverter());
//        register(Time.class, new SqlTimeConverter());
//        register(Timestamp.class, new SqlTimestampConverter());

//        register(Class.class, new ClassConverter());
//        register(File.class, new FileConverter());
//        register(URL.class, new URLConverter());

		// Complex Datatypes
		registerTypeConverter(AppointmentPriorityConverter.class, new AppointmentPriorityConverter());

		// Custom Converters
		registerCustomConverter(NumberConverter.CONVERTER_ID, NumberConverter.class);
		registerCustomConverter(DateTimeConverter.CONVERTER_ID, DateTimeConverter.class);
		registerCustomConverter(ByteSizeConverter.CONVERTER_ID, ByteSizeConverter.class);
	}

	/**
	 * Returns the singelton instance of this class
	 * 
	 * @return The HtmlPainterFactory
	 */
	public static ConverterRegistry instance() {
		return instance;
	}

	/**
	 * Register a custom {@link Converter} for the specified destination
	 * <code>Class</code>, replacing any previously registered Converter.
	 * 
	 * @param dataType
	 *            Destination class for conversions performed by this Converter
	 * @param converter
	 *            Converter to be registered
	 */
	public synchronized void registerTypeConverter(Class dataType, Converter converter) {
		converters.put(dataType, converter);
	}

	/**
	 * Register a custom {@link Converter} for the specified destination
	 * <code>Class</code>, replacing any previously registered Converter.
	 * 
	 * @param converterId
	 *            The converters identifier or class name
	 * @param converterClass
	 *            Converter class to be registered
	 */
	public synchronized void registerCustomConverter(String converterId, Class converterClass) {
		customConverters.put(converterId, converterClass);
	}

	/**
	 * Remove any registered {@link Converter} for the specified destination
	 * <code>Class</code>.
	 * 
	 * @param dataType
	 *            Class for which to remove a registered Converter
	 */
	public synchronized void deregister(Class dataType) {
		converters.remove(dataType);
	}

	/**
	 * Look up and return any registered {@link Converter} for the specified
	 * destination class; if there is no registered Converter, return a default
	 * String Converter.
	 * 
	 * @param dataType
	 *            Class for which to return a registered Converter
	 * @return a matching Converter or the default StringConverter
	 */
	public Converter lookup(Class dataType) {
		if (dataType == null) {
			throw new NullPointerException();
		}

		if (dataType.isArray()) {
			dataType = dataType.getComponentType();
		}

		if (converters.containsKey(dataType)) {
			return (Converter) converters.get(dataType);
		} else {
			return defaultConverter;
		}
	}

	/**
	 * Look up and custom converter.
	 * 
	 * @param converterId
	 *            The converters identifier or Class name
	 * @return a matching Converter or the default StringConverter
	 * @throws ConverterException
	 *             is thrown when a conversion error occurs
	 */
	public Converter getCustomConverter(String converterId)
			throws ConverterException {

		if (converterCache.containsKey(converterId)) {
			return (Converter) converterCache.get(converterId);
		}
		
		Class converterClass = null;
		
		if (customConverters.containsKey(converterId)) {
			converterClass = (Class) customConverters.get(converterId);
		}

		try {
			// A Java Class Name is given
			if (converterClass == null) {
				converterClass = Class.forName(converterId);
			}

			Converter converter = (Converter) converterClass.newInstance();

			if (!(converter instanceof StatefullConverter)) {
				// We can register a stateless converter for later
				// reuse. A statefull converter requires a new instance
				// every time
				synchronized (converterCache) {
					converterCache.put(converterId, converter);

					// Addd an additional registration for the converters
					// class name when necessary
					if (!converterId.equals(converter.getClass().getName())) {
						converterCache.put(converter.getClass().getName(), converter);
					}
				}
			}

			return converter;
		} catch (Exception e) {
			log.error(e);

			return defaultConverter;
		}
	}
}
