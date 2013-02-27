/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/util/Formatter.java,v 1.16 2005/07/08 14:15:51 P001002 Exp $
 * $Revision: 1.16 $
 * $Date: 2005/07/08 14:15:51 $
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

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.TimeZone;

import org.apache.ecs.Entities;

/**
 * This class provides functions for formatting Strings
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.16 $
 * @since      1.0
 */
public abstract class Formatter {

	/**
	 * Constructor
	 */
	private Formatter() {
		super();
	}
	
	/**
	 * Parses a String into a Locale Object.
	 *
	 * @param		locale String to parse
	 * @return		locale Object or <code>null</code>
	 * 				when the String is empty
	 */
	public static Locale parseLocale(String locale) {
		if ((null == locale) || "".equals(locale)) {
			return null;
		}

		String language	= "";
		String country	= "";
		String variant	= "";

		StringTokenizer st = new StringTokenizer(locale, "_");

		if (st.hasMoreTokens()) {
			language = st.nextToken();
		}

		if (st.hasMoreTokens()) {
			country = st.nextToken();
		}

		if (st.hasMoreTokens()) {
			variant = st.nextToken();
		}

		return new java.util.Locale(language, country, variant);
	}

	/**
	 * Parses a String into a TimeZone Object.
	 *
	 * @param		zone String to parse
	 * @return		TimeZone Object or <code>null</code>
	 * 				when the String is empty
	 */
	public static TimeZone parseTimeZone(String zone) {
		if ((null == zone) || "".equals(zone)) {
			return null;
		}

		return TimeZone.getTimeZone(zone);
	}

	/**
	 * Formats Bytes in the format ..bytes, ..kb oder ..mb
	 *
	 * @param	bytes	Number to format
	 * @return	The formatted byte size
	 */
	public static String formatBytes(long bytes) {
		if (bytes < 1024) {
			return Long.toString(bytes) + " bytes";
		} else if (bytes < 1024 * 1024) {
			return Long.toString(bytes / 1024) + " kb";
		} else {
			return Long.toString(bytes / (1024 * 1024)) + " mb";
		}
	}

	/**
	 * This method returns a String representing a formatted date.
	 *
	 * @param	 date	The date value to be formatted into a time string.
	 * @return the formatted date string
	 */
	public static String formatDate(Date date) {

		DateFormat df = new SimpleDateFormat("dd.MM.yyyy");

		return df.format(date);
	}

	/**
	 * This method returns a String representing a formatted date.
	 *
	 * @param	date	The date value to be formatted into a date string.
	 * @param	zone	The timezone value to be used in the formatted date string
	 * @return the formatted date string
	 */
	public static String formatDate(Date date, String zone) {

		DateFormat df = new SimpleDateFormat("dd.MM.yyyy");

		df.setTimeZone(TimeZone.getTimeZone(zone));

		return df.format(date);
	}

	/**
	 * This method returns a String representing a formatted date.
	 *
	 * @param	date	The date value to be formatted into a time string.
	 * @return the formatted date string
	 */
	public static String formatDateAndTime(Date date) {

		DateFormat df = new SimpleDateFormat("yyyy.MM.dd 'at' hh:mm:ss");

		return df.format(date);
	}

	/**
	 * This method returns a String representing a formatted date.
	 *
	 * @param	date 	The date value to be formatted into a time string.
	 * @return the formatted date string
	 */
	public static String formatDateAndTime(long date) {

		DateFormat df = new SimpleDateFormat("yyyy.MM.dd 'at' hh:mm:ss");

		return df.format(new Date(date));
	}

	/**
	 * This method returns a String representing a formatted time.
	 *
	 * @param	time	The time value to be formatted into a time string.
	 * @return	the formatted time string
	 */
	public static String formatTime(Time time) {
		return time.toString();
	}

	/**
	 * This method returns a String representing a formatted time span.
	 *
	 * @param 	span	The time span
	 * @return	the formatted time span string
	 */
	public static String formatTimeSpan(long span) {

		DateFormat df = new SimpleDateFormat("H:mm:ss");

		return df.format(new Timestamp(span));
	}

	/**
	 * This method inserts a html break tag at the specified position
	 * into an String.
	 *
	 * @param	str		String to process
	 * @param	length	Postion where the break should be inserted
	 * @return	String
	 */
	public static String splitHtmlString(String str, int length) {

		// if the position is greater than the string length return
		if (str.length() <= length) {
			return str;
		}

		// ... else search a posible location to break the string
		int splitPos = length - 1;

		while (splitPos > 0) {
			char c = str.charAt(splitPos);

			if ((' ' == c) || ('_' == c) || (',' == c) || (';' == c)) {
				break;
			}

			splitPos--;
		}

		if (0 == splitPos) {
			splitPos = length - 1;
		}

		StringBuffer out = new StringBuffer()
			.append(str.substring(0, splitPos))
			.append("<br>")
			.append(Entities.NBSP)
			.append(splitHtmlString(str.substring(splitPos + 1), length));

		return out.toString();
	}
}
