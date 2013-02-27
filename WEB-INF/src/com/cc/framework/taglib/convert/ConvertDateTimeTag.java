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

package com.cc.framework.taglib.convert;

import com.cc.framework.convert.Converter;
import com.cc.framework.convert.ConverterException;
import com.cc.framework.convert.ConverterHelp;
import com.cc.framework.convert.imp.DateTimeConverter;
import com.cc.framework.taglib.InnerTag;
import com.cc.framework.util.Formatter;

/**
 * Complex DateTime Converter as used in the JSF Specification 
 * 
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version $Revision$
 */
public class ConvertDateTimeTag extends ConverterTag implements InnerTag {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 5795730691800190537L;

	private String dateStyle;

	private String locale;

	private String pattern;

	private String timeStyle;

	private String timeZone;

	private String type;

	/**
	 * Constructor
	 */
	public ConvertDateTimeTag() {
		init();
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#release()
	 */
	public void release() {
		super.release();
		init();
	}

	/**
	 * Initializes all internal members
	 */
	private void init() {
		dateStyle = null;
		locale = null;
		pattern = null;
		timeStyle = null;
		timeZone = null;
		type = null;
	}

	/**
	 * @see com.cc.framework.taglib.convert.ConverterTag#doCreateConverter()
	 */
	public Converter doCreateConverter() throws ConverterException {
		super.setConverterId(DateTimeConverter.CONVERTER_ID);

		DateTimeConverter result = (DateTimeConverter) ConverterHelp.getInstance(DateTimeConverter.CONVERTER_ID);

		result.setDateStyle(dateStyle == null ? "default" : dateStyle);
		result.setLocale(Formatter.parseLocale(locale));
		result.setPattern(pattern);
		result.setTimeStyle((timeStyle == null) ? "default" : timeStyle);
		result.setTimeZone(Formatter.parseTimeZone(timeZone));

		if (type == null) {
			if (timeStyle != null) {
				if (dateStyle != null)
					result.setType("both");
				else
					result.setType("time");
			} else {
				result.setType("date");
			}
		} else {
			result.setType(type);
		}

		return result;
	}

	/**
	 * Predefined formatting style which determines how the date component of a
	 * date string is to be formatted and parsed. Applied only if type is "date"
	 * or "both". Valid values are "default", "short", "medium", "long", and
	 * "full". Default value is "default".
	 * 
	 * @param dateStyle
	 *            the Date Style
	 */
	public void setDateStyle(String dateStyle) {
		this.dateStyle = dateStyle;
	}

	/**
	 * Locale whose predefined styles for dates and times are used during
	 * formatting or parsing. If not specified, the Locale returned by
	 * FacesContext.getViewRoot().getLocale() will be used. Value must be either
	 * a VB expression that evaluates to a java.util.Locale instance, or a
	 * String that is valid to pass as the first argument to the constructor
	 * java.util.Locale(String language, String country). The empty string is
	 * passed as the second argument.
	 * 
	 * @param locale
	 *            the locale id
	 */
	public void setLocale(String locale) {
		this.locale = locale;
	}

	/**
	 * Custom formatting pattern which determines how the date/time string
	 * should be formatted and parsed.
	 * 
	 * @param pattern
	 */
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	/**
	 * Predefined formatting style which determines how the time component of a
	 * date string is to be formatted and parsed. Applied only if type is "time"
	 * or "both". Valid values are "default", "short", "medium", "long", and
	 * "full". Default value is "default".
	 * 
	 * @param timeStyle
	 *            the time style
	 */
	public void setTimeStyle(String timeStyle) {
		this.timeStyle = timeStyle;
	}

	/**
	 * Time zone in which to interpret any time information in the date String.
	 * Value must be either a VB expression that evaluates to a
	 * java.util.TimeVone instance, or a String that is a timezone ID as
	 * described in the javadocs for java.util.TimeZone.getTimeZone().
	 * 
	 * @param timeZone
	 *            the time zone
	 */
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	/**
	 * Specifies what contents the string value will be formatted to include, or
	 * parsed expecting. Valid values are "date", "time", and "both". Default
	 * value is "date".
	 * 
	 * @param type
	 *            the type
	 */
	public void setType(String type) {
		this.type = type;
	}
}