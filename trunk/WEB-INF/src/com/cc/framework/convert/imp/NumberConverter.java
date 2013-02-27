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

package com.cc.framework.convert.imp;

import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import com.cc.framework.adapter.RequestContext;
import com.cc.framework.convert.ConverterException;
import com.cc.framework.convert.StatefullConverter;

/**
 * Converter for Numbers (as in the Java Server Faces Specification)
 * 
 * Converter implementation for java.lang.Number values.
 * 
 * The getAsObject() method parses a String into an java.lang.Double or
 * java.lang.Long, according to the following algorithm:
 * 
 * <ul>
 *   <li>If the specified String is null, return a null. Otherwise, trim leading
 *       and trailing whitespace before proceeding.</li>
 *   <li>If the specified String - after trimming - has a zero length, return
 *       null.</li>
 *   <li>If the locale property is not null, use that Locale for managing
 *       parsing. Otherwise, use the Locale from the RequestContext.</li>
 *   <li>If a pattern has been specified, its syntax must conform the rules
 *       specified by java.text.DecimalFormat. Such a pattern will be used to parse,
 *       and the type property will be ignored.</li>
 *   <li>If a pattern has not been specified, parsing will be based on the type
 *       property, which expects a currency, a number, or a percent. The parse pattern
 *       for currencies, numbers, and percentages is determined by calling the
 *       getCurrencyInstance(), getNumberInstance(), or getPercentInstance() method of
 *       the java.text.NumberFormat class, passing in the selected Locale.</li>
 *   <li>If the integerOnly property has been set to true, only the integer
 *       portion of the String will be parsed. See the JavaDocs for the
 *       setParseIntegerOnly() method of the java.text.NumberFormat class for more
 *       information. </li>
 * </ul>
 * 
 * The getAsString() method expects a value of type java.lang.Number (or a
 * subclass), and creates a formatted String according to the following
 * algorithm:
 * 
 * <ul>
 *   <li>If the specified value is null, return a zero-length String.</li>
 *   <li>If the specified value is a String, return it unmodified. </li>
 *   <li>If the locale property is not null, use that Locale for managing
 *       formatting. Otherwise, use the Locale from the FacesContext.</li>
 *   <li>If a pattern has been specified, its syntax must conform the rules
 *       specified by java.text.DecimalFormat. Such a pattern will be used to format,
 *       and the type property (along with related formatting options described in the
 *       next paragraph) will be ignored.</li>
 *   <li>If a pattern has not been specified, formatting will be based on the
 *       type property, which formats the value as a currency, a number, or a percent.
 *       The format pattern for currencies, numbers, and percentages is determined by
 *       calling the percentages is determined by calling the getCurrencyInstance(),
 *       getNumberInstance(), or getPercentInstance() method of the
 *       java.text.NumberFormat class, passing in the selected Locale. In addition,
 *       the following properties will be applied to the format pattern, if specified:</li>
 *       <ul>
 *         <li>If the groupingUsed property is true, the setGroupingUsed(true) method
 *             on the corresponding NumberFormat instance will be called.</li>
 *         <li>The minimum and maximum number of digits in the integer and fractional
 *             portions of the result will be configured based on any values set for the
 *             maxFractionDigits, maxIntegerDigits, minFractionDigits, and minIntegerDigits
 *             properties.</li>
 *         <li>If the type is set to currency, it is also possible to configure the
 *             currency symbol to be used, using either the currencyCode or currencySymbol
 *             properties. If both are set, the value for currencyCode takes precedence on a
 *             JDK 1.4 (or later) JVM; otherwise, the value for currencySymbol takes
 *             precedence.</li>
 *       </ul>
 * </ul>
 * 
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version $Revision$
 */
public class NumberConverter implements StatefullConverter {

	/**
	 * Converter Id
	 */
	public static final String CONVERTER_ID = "javax.faces.Number";

	private String currencyCode;

	private String currencySymbol;

	private boolean groupingUsed;

	private boolean integerOnly;

	private int maxFractionDigits;

	private boolean maxFractionDigitsSpecified;

	private int maxIntegerDigits;

	private boolean maxIntegerDigitsSpecified;

	private int minFractionDigits;

	private boolean minFractionDigitsSpecified;

	private int minIntegerDigits;

	private boolean minIntegerDigitsSpecified;

	private Locale locale;

	private String pattern;

	private String type;

	private static Class currencyClass;

	private static final Class GET_INSTANCE_PARAM_TYPES[];

	static {
		try {
			currencyClass = Class.forName("java.util.Currency");
		} catch (Exception cnfe) {
		}
		GET_INSTANCE_PARAM_TYPES = (new Class[] { java.lang.String.class });
	}

	/**
	 * Constructor
	 */
	public NumberConverter() {
		currencyCode = null;
		currencySymbol = null;
		groupingUsed = true;
		integerOnly = false;
		maxFractionDigits = 0;
		maxFractionDigitsSpecified = false;
		maxIntegerDigits = 0;
		maxIntegerDigitsSpecified = false;
		minFractionDigits = 0;
		minFractionDigitsSpecified = false;
		minIntegerDigits = 0;
		minIntegerDigitsSpecified = false;
		locale = null;
		pattern = null;
		type = "number";
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getCurrencySymbol() {
		return currencySymbol;
	}

	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}

	public boolean isGroupingUsed() {
		return groupingUsed;
	}

	public void setGroupingUsed(boolean groupingUsed) {
		this.groupingUsed = groupingUsed;
	}

	public boolean isIntegerOnly() {
		return integerOnly;
	}

	public void setIntegerOnly(boolean integerOnly) {
		this.integerOnly = integerOnly;
	}

	public int getMaxFractionDigits() {
		return maxFractionDigits;
	}

	public void setMaxFractionDigits(int maxFractionDigits) {
		this.maxFractionDigits = maxFractionDigits;
		maxFractionDigitsSpecified = true;
	}

	public int getMaxIntegerDigits() {
		return maxIntegerDigits;
	}

	public void setMaxIntegerDigits(int maxIntegerDigits) {
		this.maxIntegerDigits = maxIntegerDigits;
		maxIntegerDigitsSpecified = true;
	}

	public int getMinFractionDigits() {
		return minFractionDigits;
	}

	public void setMinFractionDigits(int minFractionDigits) {
		this.minFractionDigits = minFractionDigits;
		minFractionDigitsSpecified = true;
	}

	public int getMinIntegerDigits() {
		return minIntegerDigits;
	}

	public void setMinIntegerDigits(int minIntegerDigits) {
		this.minIntegerDigits = minIntegerDigits;
		minIntegerDigitsSpecified = true;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Object getAsObject(RequestContext context, String value)
			throws ConverterException {
		if (context == null)
			throw new NullPointerException();
		if (value == null)
			return null;
		value = value.trim();
		if (value.length() < 1)
			return null;
		NumberFormat parser;
		Locale locale = getLocale(context);
		parser = getNumberFormat(locale);
		parser.setParseIntegerOnly(isIntegerOnly());
		try {
			return parser.parse(value);
		} catch (ParseException pe) {
			throw new ConverterException("Error parsing '" + value + "'", pe);
		}
	}

	public String getAsString(RequestContext context, Object value)
			throws ConverterException {
		if (context == null)
			throw new NullPointerException();
		if (value == null)
			return "";
		if (value instanceof String)
			return (String) value;
		NumberFormat formatter;
		Locale locale = getLocale(context);
		formatter = getNumberFormat(locale);
		try {
			if (pattern != null && !pattern.equals("")
					|| "currency".equals(type))
				configureCurrency(formatter);
			configureFormatter(formatter);

			return formatter.format(value);
		} catch (Exception e) {
			throw new ConverterException(e);
		}
	}

	private void configureCurrency(NumberFormat formatter) throws Exception {
		String code = null;
		String symbol = null;
		if (currencyCode == null && currencySymbol == null)
			return;
		if (currencyCode != null && currencySymbol != null) {
			if (currencyClass != null)
				code = currencyCode;
			else
				symbol = currencySymbol;
		} else if (currencyCode == null)
			symbol = currencySymbol;
		else if (currencyClass != null)
			code = currencyCode;
		else
			symbol = currencyCode;
		if (code != null) {
			Object methodArgs[] = new Object[1];
			Method m = currencyClass.getMethod("getInstance",
					GET_INSTANCE_PARAM_TYPES);
			methodArgs[0] = code;
			Object currency = m.invoke(null, methodArgs);
			Class paramTypes[] = new Class[1];
			paramTypes[0] = currencyClass;
			Class numberFormatClass = Class.forName("java.text.NumberFormat");
			m = numberFormatClass.getMethod("setCurrency", paramTypes);
			methodArgs[0] = currency;
			m.invoke(formatter, methodArgs);
		} else {
			DecimalFormat df = (DecimalFormat) formatter;
			DecimalFormatSymbols dfs = df.getDecimalFormatSymbols();
			dfs.setCurrencySymbol(symbol);
			df.setDecimalFormatSymbols(dfs);
		}
	}

	private void configureFormatter(NumberFormat formatter) {
		formatter.setGroupingUsed(groupingUsed);
		if (maxIntegerDigitsSpecified)
			formatter.setMaximumIntegerDigits(maxIntegerDigits);
		if (minIntegerDigitsSpecified)
			formatter.setMinimumIntegerDigits(minIntegerDigits);
		if (maxFractionDigitsSpecified)
			formatter.setMaximumFractionDigits(maxFractionDigits);
		if (minFractionDigitsSpecified)
			formatter.setMinimumFractionDigits(minFractionDigits);
	}

	/**
	 * Retrieves the Locale for this Converter
	 * 
	 * @param		context The RequestContext
	 * @return		Locale
	 */
	private Locale getLocale(RequestContext context) {
		Locale locale = this.locale;

		if (locale == null) {
			locale = context.getLocale();
		}

		if (locale == null) {
			locale = Locale.getDefault();
		}

		return locale;
	}

	private NumberFormat getNumberFormat(Locale locale)
			throws ConverterException {
		if (pattern == null && type == null)
			throw new IllegalArgumentException(
					"Either pattern or type must be specified.");
		if (pattern != null) {
			DecimalFormatSymbols symbols = new DecimalFormatSymbols(locale);
			return new DecimalFormat(pattern, symbols);
		}
		if (type.equals("currency"))
			return NumberFormat.getCurrencyInstance(locale);
		if (type.equals("number"))
			return NumberFormat.getNumberInstance(locale);
		if (type.equals("percent"))
			return NumberFormat.getPercentInstance(locale);
		else
			throw new ConverterException(new IllegalArgumentException(type));
	}

	/**
	 * @see com.cc.framework.convert.StatefullConverter#saveState(com.cc.framework.adapter.RequestContext)
	 */
	public Object saveState(RequestContext context) {
		Object values[] = new Object[15];
		values[0] = currencyCode;
		values[1] = currencySymbol;
		values[2] = isGroupingUsed() ? Boolean.TRUE : Boolean.FALSE;
		values[3] = isIntegerOnly() ? Boolean.TRUE : Boolean.FALSE;
		values[4] = new Integer(maxFractionDigits);
		values[5] = maxFractionDigitsSpecified ? Boolean.TRUE : Boolean.FALSE;
		values[6] = new Integer(maxIntegerDigits);
		values[7] = maxIntegerDigitsSpecified ? Boolean.TRUE : Boolean.FALSE;
		values[8] = new Integer(minFractionDigits);
		values[9] = minFractionDigitsSpecified ? Boolean.TRUE : Boolean.FALSE;
		values[10] = new Integer(minIntegerDigits);
		values[11] = minIntegerDigitsSpecified ? Boolean.TRUE : Boolean.FALSE;
		values[12] = locale;
		values[13] = pattern;
		values[14] = type;

		return values;
	}

	/**
	 * @see com.cc.framework.convert.StatefullConverter#restoreState(com.cc.framework.adapter.RequestContext, java.lang.Object)
	 */
	public void restoreState(RequestContext context, Object state) {
		Object values[] = (Object[]) state;
		currencyCode = (String) values[0];
		currencySymbol = (String) values[1];
		groupingUsed = ((Boolean) values[2]).booleanValue();
		integerOnly = ((Boolean) values[3]).booleanValue();
		maxFractionDigits = ((Integer) values[4]).intValue();
		maxFractionDigitsSpecified = ((Boolean) values[5]).booleanValue();
		maxIntegerDigits = ((Integer) values[6]).intValue();
		maxIntegerDigitsSpecified = ((Boolean) values[7]).booleanValue();
		minFractionDigits = ((Integer) values[8]).intValue();
		minFractionDigitsSpecified = ((Boolean) values[9]).booleanValue();
		minIntegerDigits = ((Integer) values[10]).intValue();
		minIntegerDigitsSpecified = ((Boolean) values[11]).booleanValue();
		locale = (Locale) values[12];
		pattern = (String) values[13];
		type = (String) values[14];
	}
}