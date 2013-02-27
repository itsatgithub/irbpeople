/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/util/StringHelp.java,v 1.29 2005/09/22 06:19:52 P001002 Exp $
 * $Revision: 1.29 $
 * $Date: 2005/09/22 06:19:52 $
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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * Helper class for string operations.
 *
 * @author	   <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version	   $Revision: 1.29 $
 * @since      1.0
 */
public abstract class StringHelp {

	/**
	 * Code for carriage return
	 */
	public static final String CRLF = "\r\n";

	/**
	 * Constructor
	 */
	private StringHelp() {
		super();
	}

	/**
	 * Checks if a String contains only Whitespace characters
	 * 
	 * @param str
	 *            String to test
	 * @return returns <code<true</code> if the string contains only
	 *         whitespace Characters
	 */
	public static boolean isWhitespaceOnlyStr(String str) {
		if (str != null) {
			for (int i = 0; i < str.length(); i++) {
				char c = str.charAt(i);
				
				boolean whiteSpace = (c == ' ') || (c == '\r') || (c == '\t') || (c == '\n');

				if (!whiteSpace) {
					// found some none whitespace character
					return false;
				}
			}
		}

		// String contains only Whitespace Characters
		return true;
	}

	/**
	 * Transforms an object into a String Array
	 *
	 * @param		value Object
	 * @return		Array of Strings
	 */
	public static String[] toStringArray(Object value) {

		if (value == null) {
			return null;
		} else if (value instanceof Collection) {
			ArrayList values = new ArrayList();
			Iterator items = ((Collection) value).iterator();
			while (items.hasNext()) {
				Object item = items.next();
				if (item == null) {
					values.add((String) null);
				} else {
					values.add(item.toString());
				}
			}
			return (String[]) values.toArray(new String[values.size()]);
		} else if (value.getClass().isArray()) {
			int n = Array.getLength(value);
			String[] results = new String[n];
			for (int i = 0; i < n; i++) {
				Object item = Array.get(value, i);
				if (item == null) {
					results[i] = null;
				} else {
					results[i] = item.toString();
				}
			}
			return results;
		} else {
			String[] results = new String[1];
			results[0] = value.toString();
			return results;
		}
	}

	/**
	 * Transforms an object into a String Set
	 *
	 * @param		values String Array
	 * @return		Set of Strings
	 */
	public static Set toStringSet(Object[] values) {

		HashSet set = new HashSet();

		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				if (values[i] != null) {
					set.add(values[i].toString());
				}
			}
		}

		return set;
	}

	/**
	 * Truncates a given string to a maximum number of
	 * characters
	 *
	 * @param		str The string to tuncate
	 * @param		maxlength The maximum number of characters
	 * @return		truncated string
	 */
	public static String truncate(String str, int maxlength) {
		if ((maxlength == -1) || (str == null) || (str.length() <= maxlength)) {
			return str;
		} else {
			return str.substring(0, maxlength) + "...";
		}
	}

	/**
	 * Fills a String with the specified pattern till the specified length.
	 *
	 * @param	obj			String
	 * @param	fillLength	length
	 * @param	fillChar	pattern
	 * @return	String
	 */
	public static String fill(Object obj, int fillLength, char fillChar) {

		StringBuffer buf = new StringBuffer();

		if (obj == null) {

			for (int i = 0; i < fillLength; i++) {
				buf.append(fillChar);
			}
		} else {
			String str = String.valueOf(obj);

			buf.append(str);

			for (int i = str.length(); i < fillLength; i++) {
				buf.append(fillChar);
			}
		}

		return buf.toString();
	}

	/**
	 * Removes whitespaces at the end of a string.
	 * This function takes care of NULL-Values.
	 * This function should be used if CHAR(x) columns are
	 * read from a database because this values are filled up to
	 * the specified length with whitespaces.
	 *
	 * @param	str		String
	 * @return	String
	 */
	public static String safetrim(String str) {
		if (str == null) {
			return null;
		}

		return str.trim();
	}

	/**
	 * Trims a String
	 *
	 * @param	str	String to trim
	 * @return	String
	 */
	public static String trimRight(String str) {

		int n = str.length();

		while ((n > 0) && (' ' == str.charAt(n - 1))) {
			n--;
		}

		if (0 == n) {
			return "";
		} else {
			return str.substring(0, n);
		}
	}

	/**
	 * Methode safeequals
	 *
	 * @param	o1	Object1
	 * @param	o2	Object2
	 * @return boolean
	 */
	public static boolean safeequals(Object o1, Object o2) {

		if ((o1 == null) && (o2 == null)) {
			return true;
		}

		if ((o1 != null) && (o2 != null)) {
			return o1.equals(o2);
		}

		return false;
	}

	/**
	 * Splits the provided text into a list, based on a given separator.
	 * The separator is not included in the returned String array.
	 * The maximum number of splits to perfom can be controlled.
	 * A null separator will cause parsing to be on whitespace.
	 *
	 * @param	text		The string to parse
	 * @param	delimiter	The delimiter character. If <code>null</code>, splits on whitespace.
	 * @return an array of parsed Strings
	 */
	public static String[] split(String text, String delimiter) {

		if (text == null) {
			return new String[0];
		}

		StringTokenizer tok = null;

		if (delimiter == null) {
			// Null separator means we're using StringTokenizer's default
			// delimiter, which comprises all whitespace characters.
			tok = new StringTokenizer(text);
		} else {
			tok = new StringTokenizer(text, delimiter);
		}

		int listSize = tok.countTokens();

		String[] list = new String[listSize];

		int i = 0;
		while (tok.hasMoreTokens()) {
			list[i++] = tok.nextToken();
		}

		return list;
	}

	/**
	 * Splits the provided text into a list, based on a given separator.
	 * The separator is not included in the returned String array.
	 * The maximum number of splits to perfom can be controlled.
	 * A null separator will cause parsing to be on whitespace.
	 *
	 * @param	text		The string to parse
	 * @param	delimiter	The delimiter character. If <code>null</code>, splits on whitespace.
	 * @return an array of parsed Strings
	 */
	public static String[] splitRetainEmpty(String text, char delimiter) {

		if (text == null) {
			return new String[0];
		}

		Vector list = new Vector();

		char[] c = text.toCharArray();
		int start = 0;
		int i = 0;

		while (i < c.length) {
			if (c[i] == delimiter) {
				list.add(new String(c, start, (i - start)));

				start = ++i;
			} else {
				++i;
			}
		}

		// add the last token
		list.add(new String(c, start, (i - start)));

		String[] result = new String[list.size()];
		return (String[]) list.toArray(result);
	}

	/**
	 * Joins an array and inserts the specified delimiter between the
	 * array elements.
	 *
	 * @param	tokens		The tokens to join
	 * @param	delimiter	The delimiter to insert between the tokens
	 * @return	String
	 */
	public static String join(Object[] tokens, char delimiter) {

		StringBuffer buf = new StringBuffer();

		for (int i = 0; i < tokens.length; i++) {
			if (i > 0) {
				buf.append(delimiter);
			}

			if (tokens[i] != null) {
				buf.append(tokens[i]);
			}
		}

		return buf.toString();
	}

	/**
	 * Joins on token.
	 *
	 * @param	token		The token to join
	 * @param	repeat		The repeat count
	 * @param	delimiter	The delimiter to insert between the tokens
	 * @return	String
	 */
	public static String join(String token, int repeat, char delimiter) {

		StringBuffer buf = new StringBuffer();

		for (int i = 0; i < repeat; i++) {
			if (i > 0) {
				buf.append(delimiter);
			}

			if (token != null) {
				buf.append(token);
			}
		}

		return buf.toString();
	}

	/**
	 * Joins an array and inserts the specified delimiter between the
	 * array elements.
	 *
	 * @param	tokens		The tokens to join
	 * @param	delimiter	The delimiter to insert between the tokens
	 * @return	String
	 */
	public static String join(Collection tokens, String delimiter) {

		if (tokens == null) {
			return null;
		}

		StringBuffer buf = new StringBuffer();

		boolean first = true;
		Iterator iter = tokens.iterator();
		while (iter.hasNext()) {

			if (!first) {
				buf.append(delimiter);
			}

			buf.append(iter.next());
			first = false;
		}

		return buf.toString();
	}

	/**
	 * Joins an array and inserts the specified delimiter between the
	 * array elements.
	 *
	 * @param	tokens		The tokens to join
	 * @param	delimiter	The delimiter to insert between the tokens
	 * @return	String
	 */
	public static String join(Collection tokens, char delimiter) {

		if (tokens == null) {
			return null;
		}

		StringBuffer buf = new StringBuffer();

		boolean first = true;
		Iterator iter = tokens.iterator();
		while (iter.hasNext()) {

			if (!first) {
				buf.append(delimiter);
			}

			buf.append(iter.next());
			first = false;
		}

		return buf.toString();
	}

	/**
	 * Joins two strings and inserst the specified delimiter between the string.
	 * @param	t1			String1
	 * @param 	t2			String2
	 * @param	delimiter	The delimiter to used
	 * @return	String
	 */
	public static String join(String t1, String t2, char delimiter) {
		return join(new String[]{t1, t2}, delimiter);
	}

	/**
	 * Joins three strings and inserst the specified delimiter between the string.
	 * @param	t1			String1
	 * @param 	t2			String2
	 * @param 	t3			String3
	 * @param	delimiter	The delimiter to used
	 * @return	String
	 */
	public static String join(String t1, String t2, String t3, char delimiter) {
		return join(new String[]{t1, t2, t3}, delimiter);
	}

	/**
	 * Joins four strings and inserst the specified delimiter between the string.
	 * @param	t1			String1
	 * @param 	t2			String2
	 * @param 	t3			String3
	 * @param 	t4			String4
	 * @param	delimiter	The delimiter to used
	 * @return	String
	 */
	public static String join(String t1, String t2, String t3, String t4,  char delimiter) {
		return join(new String[]{t1, t2, t3, t4}, delimiter);
	}

	/**
	 * Concatenates an array.
	 *
	 * @param	tokens		The tokens to join
	 * @return	String
	 */
	public static String strcat(String[] tokens) {

		StringBuffer buf = new StringBuffer();

		for (int i = 0; i < tokens.length; i++) {
			if (tokens[i] != null) {
				buf.append(tokens[i]);
			}
		}

		return buf.toString();
	}

	/**
	 * Concatenates an array of Objects.
	 *
	 * @param	tokens		The tokens to join
	 * @return	String
	 */
	public static String strcat(List tokens) {

		StringBuffer buf = new StringBuffer();

		for (int i = 0; i < tokens.size(); i++) {
			buf.append(tokens.get(i));
		}

		return buf.toString();
	}

	/**
	 * Concatenates two strings
	 * @param	t1			String1
	 * @param 	t2			String2
	 * @return	String
	 */
	public static String strcat(String t1, String t2) {
		return strcat(new String[]{t1, t2});
	}

	/**
	 * Concatenates three strings
	 * @param	t1			String1
	 * @param 	t2			String2
	 * @param 	t3			String3
	 * @return	String
	 */
	public static String strcat(String t1, String t2, String t3) {
		return strcat(new String[]{t1, t2, t3});
	}

	/**
	 * Concatenates four strings
	 * @param	t1			String1
	 * @param 	t2			String2
	 * @param 	t3			String3
	 * @param 	t4			String4
	 * @return	String
	 */
	public static String strcat(String t1, String t2, String t3, String t4) {
		return strcat(new String[]{t1, t2, t3, t4});
	}

	/**
	 * Concatenates four strings
	 * @param	t1			String1
	 * @param 	t2			String2
	 * @param 	t3			String3
	 * @param 	t4			String4
	 * @param 	t5			String5
	 * @return	String
	 */
	public static String strcat(String t1, String t2, String t3, String t4, String t5) {
		return strcat(new String[]{t1, t2, t3, t4, t5});
	}

	/**
	 * This method expands the markups (Format %macro%) in a string
	 * by the specified macros.
	 *
	 * @param	str		String to expand
	 * @param	macros	Map with macros
	 * @return	Bei erfolgreicher Ausführung wird die expandierte
	 * 				Zeichenkette zurückgegeben
	 */
	public static String expand(String str, Map macros) {

		if (null == str) {
			return null;
		}

		StringBuffer out = new StringBuffer();

		int i = 0;

		while (i < str.length()) {

			if ((str.charAt(i) == '%') && (i < str.length())) {

				if (str.charAt(i + 1) == '%') {
					// Aufeinanderfolgende %% zu % ersetzen
					out.append('%');
					i += 2;
				} else {
					// Bis zum nächsten % lesen
					int start	= ++i;

					while ((i < str.length()) && (str.charAt(i) != '%')) {
						++i;
					}

					if (i >= str.length()) {
						throw new IllegalArgumentException("Invalid Macro String: " + str);
					}

					String macro	= str.substring(start, i);
					String expanded	= (String) macros.get(macro);

					if (expanded == null) {
						throw new IllegalArgumentException("Undefined Macro: " + macro);
					}

					out.append(expanded);

					++i;
				}
			} else {
				out.append(str.charAt(i));
				++i;
			}
		}

		return out.toString();
	}
}