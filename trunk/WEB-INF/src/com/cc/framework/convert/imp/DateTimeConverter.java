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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

import com.cc.framework.adapter.RequestContext;
import com.cc.framework.convert.ConverterException;
import com.cc.framework.convert.StatefullConverter;

/**
 * Converter for Date and time (as in the Java Server Faces Specification)
 * 
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version $Revision$
 */
public class DateTimeConverter implements StatefullConverter {

	/**
	 * Converter Id
	 */
	public static final String CONVERTER_ID = "javax.faces.DateTime";

	private static final TimeZone DEFAULT_TIME_ZONE = TimeZone.getTimeZone("GMT");

	private String dateStyle;

	private Locale locale;

	private String pattern;

	private String timeStyle;

	private TimeZone timeZone;

	private String type;

	/**
	 * Default constructor
	 */
	public DateTimeConverter() {
		dateStyle = "default";
		locale = null;
		pattern = null;
		timeStyle = "default";
		timeZone = DEFAULT_TIME_ZONE;
		type = "date";
	}

	public String getDateStyle() {
		return dateStyle;
	}

	public void setDateStyle(String dateStyle) {
		this.dateStyle = dateStyle;
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

	public String getTimeStyle() {
		return timeStyle;
	}

	public void setTimeStyle(String timeStyle) {
		this.timeStyle = timeStyle;
	}

	public TimeZone getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(TimeZone timeZone) {
		this.timeZone = timeZone;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @see com.cc.framework.convert.Converter#getAsObject(com.cc.framework.adapter.RequestContext, java.lang.String)
	 */
	public Object getAsObject(RequestContext context, String value)
			throws ConverterException {

		if (value == null) {
			return null;
		}

		value = value.trim();
		if (value.length() < 1) {
			return null;
		}

		DateFormat parser = getDateFormat(context, getLocale(context));

		if (timeZone != null) {
			parser.setTimeZone(timeZone);
		}

		try {
			return parser.parse(value);
		} catch (ParseException pe) {
			throw new ConverterException("Error parsing '" + value + "'", pe);
		} catch (Exception e) {
			throw new ConverterException(e);
		}
	}
	
	/**
	 * @see com.cc.framework.convert.Converter#getAsString(com.cc.framework.adapter.RequestContext, java.lang.Object)
	 */
	public String getAsString(RequestContext context, Object value)
			throws ConverterException {

		if (value == null) {
			return "";
		} else if (value instanceof String) {
			return (String) value;
		} else {
			DateFormat formatter = getDateFormat(context, getLocale(context));

			if (timeZone != null) {
				formatter.setTimeZone(timeZone);
			}

			try {
				return formatter.format(value);
			} catch (Exception e) {
				throw new ConverterException(e);
			}
		}
	}

	/**
	 * Creates a DateFormat Object from the Converter Attributes
	 * 
	 * @param context
	 *            RequestContext
	 * @param locale
	 *            The Locale to use
	 * @return DateFormat
	 * @throws ConverterException
	 *             Is thrown when the Converter Attributes are not valid
	 */
	private DateFormat getDateFormat(RequestContext context, Locale locale)
			throws ConverterException {
		if (pattern == null && type == null) {
			throw new IllegalArgumentException("Either pattern or type must be specified.");
		}

		DateFormat df = null;
		if (pattern != null) {
			df = new SimpleDateFormat(pattern, locale);
		} else if (type.equals("both")) {
			df = DateFormat.getDateTimeInstance(getStyle(dateStyle), getStyle(timeStyle), locale);
		} else if (type.equals("date")) {
			df = DateFormat.getDateInstance(getStyle(dateStyle), locale);
		} else if (type.equals("time")) {
			df = DateFormat.getTimeInstance(getStyle(timeStyle), locale);
		} else {
			throw new IllegalArgumentException("Invalid type: " + type);
		}

		df.setLenient(false);

		return df;
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

	/**
	 * Converts the given Date Style in one of the DateFormat Constants
	 * 
	 * @param name
	 *            The Style Name
	 * @return One of the DateFormat Constants
	 * @throws ConverterException
	 *             Is thrown when the style identifier is unknown
	 */
	private int getStyle(String name) throws ConverterException {
		if (name.equals("default")) {
			return DateFormat.DEFAULT;
		} else if (name.equals("short")) {
			return DateFormat.SHORT;
		} else if (name.equals("medium")) {
			return DateFormat.MEDIUM;
		} else if (name.equals("long")) {
			return DateFormat.LONG;
		} else if (name.equals("full")) {
			return DateFormat.FULL;
		} else {
			throw new ConverterException("Invalid style '" + name + "'");
		}
	}

	/**
	 * @see com.cc.framework.convert.StatefullConverter#saveState(com.cc.framework.adapter.RequestContext)
	 */
	public Object saveState(RequestContext context) {
		Object values[] = new Object[6];
		values[0] = dateStyle;
		values[1] = locale;
		values[2] = pattern;
		values[3] = timeStyle;
		values[4] = timeZone;
		values[5] = type;
		return values;
	}

	/**
	 * @see com.cc.framework.convert.StatefullConverter#restoreState(com.cc.framework.adapter.RequestContext, java.lang.Object)
	 */
	public void restoreState(RequestContext context, Object state) {
		Object values[] = (Object[]) state;
		dateStyle = (String) values[0];
		locale = (Locale) values[1];
		pattern = (String) values[2];
		timeStyle = (String) values[3];
		timeZone = (TimeZone) values[4];
		type = (String) values[5];
	}
}