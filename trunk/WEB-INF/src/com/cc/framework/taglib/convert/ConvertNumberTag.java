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

import javax.servlet.jsp.JspException;

import com.cc.framework.convert.Converter;
import com.cc.framework.convert.ConverterException;
import com.cc.framework.convert.ConverterHelp;
import com.cc.framework.convert.imp.DateTimeConverter;
import com.cc.framework.convert.imp.NumberConverter;
import com.cc.framework.taglib.InnerTag;
import com.cc.framework.taglib.TagHelp;
import com.cc.framework.util.Formatter;

/**
 * Complex Number Converter as used in the JSF Specification
 * 
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version $Revision$
 */
public class ConvertNumberTag extends ConverterTag implements InnerTag {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -8871917367194761994L;

	private String currencyCode_;

	private String currencySymbol_;

	private boolean groupingUsed_ = false;

	private boolean integerOnly_ = false;

	private boolean maxFractionDigitsSpecified = false;

	private int maxFractionDigits_ = 0;

	private boolean maxIntegerDigitsSpecified = false;

	private int maxIntegerDigits_ = 0;

	private boolean minFractionDigitsSpecified = false;

	private int minFractionDigits_ = 0;

	private boolean minIntegerDigitsSpecified = false;

	private int minIntegerDigits_ = 0;

	private String locale_;

	private String pattern_;

	private String type_;

	/**
	 * Constructor
	 */
	public ConvertNumberTag() {
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
		currencyCode_ = null;
		currencySymbol_ = null;
		groupingUsed_ = false;
		integerOnly_ = false;
		maxFractionDigitsSpecified = false;
		maxFractionDigits_ = 0;
		maxIntegerDigitsSpecified = false;
		maxIntegerDigits_ = 0;
		minFractionDigitsSpecified = false;
		minFractionDigits_ = 0;
		minIntegerDigitsSpecified = false;
		minIntegerDigits_ = 0;
		locale_ = null;
		pattern_ = null;
		type_ = "number";
	}

	/**
	 * @see com.cc.framework.taglib.convert.ConverterTag#doCreateConverter()
	 */
	public Converter doCreateConverter() throws ConverterException {
		super.setConverterId(DateTimeConverter.CONVERTER_ID);

		NumberConverter result = (NumberConverter) ConverterHelp.getInstance(NumberConverter.CONVERTER_ID);
		result.setCurrencyCode(currencyCode_);
		result.setCurrencySymbol(currencySymbol_);
		result.setGroupingUsed(groupingUsed_);
		result.setIntegerOnly(integerOnly_);

		if (maxFractionDigitsSpecified) {
			result.setMaxFractionDigits(maxFractionDigits_);
		}

		if (maxIntegerDigitsSpecified) {
			result.setMaxIntegerDigits(maxIntegerDigits_);
		}

		if (minFractionDigitsSpecified) {
			result.setMinFractionDigits(minFractionDigits_);
		}

		if (minIntegerDigitsSpecified) {
			result.setMinIntegerDigits(minIntegerDigits_);
		}

		result.setLocale(Formatter.parseLocale(locale_));
		result.setPattern(pattern_);
		result.setType(type_);

		return result;
	}

	/**
	 * ISO 4217 currency code, applied only when formatting currencies.
	 * 
	 * @param currencyCode
	 *            Currency Code
	 */
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode_ = currencyCode;
	}

	/**
	 * Currency symbol, applied only when formatting currencies.
	 * 
	 * @param currencySymbol
	 *            Currency Symbol
	 */
	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol_ = currencySymbol;
	}

	/**
	 * Flag specifying whether formatted output will contain grouping
	 * separators. Expressions must evaluate to a boolean. Default value is
	 * true.
	 * 
	 * @param groupingUsed
	 *            "true" whether formatted output should contain grouping
	 *            separators
	 * @throws JspException
	 *             If the argument can not be converted into an Boolean value
	 */
	public void setGroupingUsed(String groupingUsed) throws JspException {
		this.groupingUsed_ = TagHelp.toBoolean(groupingUsed);
	}

	/**
	 * Flag specifying whether only the integer part of the value will be
	 * formatted and parsed. Expressions must evaluate to a boolean. Default
	 * value is false.
	 * 
	 * @param integerOnly
	 *            "true" when only the integer part of the value will be
	 *            formatted and parsed
	 * @throws JspException
	 *             If the argument can not be converted into an Boolean value
	 */
	public void setIntegerOnly(String integerOnly) throws JspException {
		this.integerOnly_ = TagHelp.toBoolean(integerOnly);
	}

	/**
	 * Locale whose predefined styles for numbers are used during formatting and
	 * parsing. If not specified, the Locale returned by
	 * FacesContext.getViewRoot().getLocale() will be used. Expressions must
	 * evaluate to a java.util.Locale.
	 * 
	 * @param locale
	 *            The locale
	 */
	public void setLocale(String locale) {
		this.locale_ = locale;
	}

	/**
	 * Maximum number of digits that will be formatted in the fractional portion
	 * of the output. Expressions must evaluate to an int.
	 * 
	 * @param maxFractionDigits
	 *            Maximum Number of fraction digits
	 * @throws JspException
	 *             If the argument can not be converted into an integer value
	 */
	public void setMaxFractionDigits(String maxFractionDigits)
			throws JspException {
		this.maxFractionDigits_ = TagHelp.toInt(maxFractionDigits);
		this.maxFractionDigitsSpecified = true;
	}

	/**
	 * Maximum number of digits that will be formatted in the integer portion of
	 * the output. Expressions must evaluate to an int.
	 * 
	 * @param maxIntegerDigits
	 *            Maximum Number of integer digits
	 * @throws JspException
	 *             If the argument can not be converted into an integer value
	 */
	public void setMaxIntegerDigits(String maxIntegerDigits)
			throws JspException {
		this.maxIntegerDigits_ = TagHelp.toInt(maxIntegerDigits);
		this.maxIntegerDigitsSpecified = true;
	}

	/**
	 * Minimum number of digits that will be formatted in the fractional portion
	 * of the output. Expressions must evaluate to an int.
	 * 
	 * @param minFractionDigits
	 *            Mimimum Number of fraction digits
	 * @throws JspException
	 *             If the argument can not be converted into an integer value
	 */
	public void setMinFractionDigits(String minFractionDigits)
			throws JspException {
		this.minFractionDigits_ = TagHelp.toInt(minFractionDigits);
		this.minFractionDigitsSpecified = true;
	}

	/**
	 * Minimum number of digits that will be formatted in the integer portion of
	 * the output. Expressions must evaluate to an int.
	 * 
	 * @param minIntegerDigits
	 *            Minimum Number of integer digits
	 * @throws JspException
	 *             If the argument can not be converted into an integer value
	 */
	public void setMinIntegerDigits(String minIntegerDigits)
			throws JspException {
		this.minIntegerDigits_ = TagHelp.toInt(minIntegerDigits);
		this.minIntegerDigitsSpecified = true;
	}

	/**
	 * Custom formatting pattern which determins how the number string should be
	 * formatted and parsed.
	 * 
	 * @param pattern
	 *            The Formatting pattern
	 */
	public void setPattern(String pattern) {
		this.pattern_ = pattern;
	}

	/**
	 * Specifies how the number string will be formatted and parsed. Valid
	 * values are "number", "currency", and "percentage". Default value is
	 * "number".
	 * 
	 * @param type
	 *            "number" (default) , "currency", and "percentage"
	 */
	public void setType(String type) {
		this.type_ = type;
	}
}