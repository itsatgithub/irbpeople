/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/html/HtmlUtil.java,v 1.24 2005/07/19 14:50:00 P001002 Exp $
 * $Revision: 1.24 $
 * $Date: 2005/07/19 14:50:00 $
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

package com.cc.framework.ui.html;

import org.apache.ecs.Entities;

/**
 * Helper Class for HTML-Resources
 *
 * @author      <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version     $Revision: 1.24 $
 * @since       1.0
 */
public abstract class HtmlUtil {

	/**
	 * Mapping Table for HTML-String
	 */
	private static final String[] HTMLCODE	= {
		null,			// 000
		null,			// 001
		null,			// 002
		null,			// 003
		null,			// 004
		null,			// 005
		null,			// 006
		null,			// 007
		null,			// 008
		null,			// 009 Horizontal tab
		null,			// 010 Line feed
		null,			// 011
		null,			// 012
		null,			// 013 Carriage Return
		null,			// 014
		null,			// 015
		null,			// 016
		null,			// 017
		null,			// 018
		null,			// 019
		null,			// 020
		null,			// 021
		null,			// 022
		null,			// 023
		null,			// 024
		null,			// 025
		null,			// 026
		null,			// 027
		null,			// 028
		null,			// 029
		null,			// 030
		null,			// 031
		null,			// 032 Space
		null,			// 033 ! Exclamation mark
		"&quot;",		// 034 " Quotation mark
		null,			// 035 # Number sign
		null,			// 036 $ Dollar sign
		null,			// 037 % Percent sign
		"&amp;",		// 038 & Ampersand
		null,			// 039 ' Apostrophe
		null,			// 040 ( Left parenthesis
		null,			// 041 ) Right parenthesis
		null,			// 042 * Asterisk
		null,			// 043 + Plus sign
		null,			// 044 , Comma
		null,			// 045 - Hyphen
		null,			// 046 . Period (fullstop)
		null,			// 047 / Solidus (slash)
		null,			// 048 0 Digit 0
		null,			// 049 1 Digit 1
		null,			// 050 2 Digit 2
		null,			// 051 3 Digit 3
		null,			// 052 4 Digit 4
		null,			// 053 5 Digit 5
		null,			// 054 6 Digit 6
		null,			// 055 7 Digit 7
		null,			// 056 8 Digit 8
		null,			// 057 9 Digit 9
		null,			// 058 : Colon
		null,			// 059 ; Semicolon
		"&lt;",			// 060 < Less than
		null,			// 061 = Equals sign
		"&gt;",			// 062 > Greater than
		null,			// 063 ? Question mark
		null,			// 064 @ Commercial at
		null,			// 065 A Capital A
		null,			// 066 B Capital B
		null,			// 067 C Capital C
		null,			// 068 D Capital D
		null,			// 069 E Capital E
		null,			// 070 F Capital F
		null,			// 071 G Capital G
		null,			// 072 H Capital H
		null,			// 073 I Capital I
		null,			// 074 J Capital J
		null,			// 075 K Capital K
		null,			// 076 L Capital L
		null,			// 077 M Capital M
		null,			// 078 N Capital N
		null,			// 079 O Capital O
		null,			// 080 P Capital P
		null,			// 081 Q Capital Q
		null,			// 082 R Capital R
		null,			// 083 S Capital S
		null,			// 084 T Capital T
		null,			// 085 U Capital U
		null,			// 086 V Capital V
		null,			// 087 W Capital W
		null,			// 088 X Capital X
		null,			// 089 Y Capital Y
		null,			// 090 Z Capital Z
		null,			// 091 [ Left square bracket
		null,			// 092 \ Reverse solidus (backslash)
		null,			// 093 ] Right square bracket
		null,			// 094 ^ Caret
		null,			// 095 _ Horizontal bar (underscore)
		null,			// 096 ` Acute accent
		null,			// 097 a Small a
		null,			// 098 b Small b
		null,			// 099 c Small c
		null,			// 100 d Small d
		null,			// 101 e Small e
		null,			// 102 f Small f
		null,			// 103 g Small g
		null,			// 104 h Small h
		null,			// 105 i Small i
		null,			// 106 j Small j
		null,			// 107 k Small k
		null,			// 108 l Small l
		null,			// 109 m Small m
		null,			// 110 n Small n
		null,			// 111 o Small o
		null,			// 112 p Small p
		null,			// 113 q Small q
		null,			// 114 r Small r
		null,			// 115 s Small s
		null,			// 116 t Small t
		null,			// 117 u Small u
		null,			// 118 v Small v
		null,			// 119 w Small w
		null,			// 120 x Small x
		null,			// 121 y Small y
		null,			// 122 z Small z
		null,			// 123 { Left curly brace
		null,			// 124 | Vertical bar
		null,			// 125 } Right curly brace
		null,			// 126 ~ Tilde
		null,			// 127   Unused
		null,			// 128   Unused
		null,			// 129   Unused
		null,			// 130   Unused
		null,			// 131   Unused
		null,			// 132   Unused
		null,			// 133   Unused
		null,			// 134   Unused
		null,			// 135   Unused
		null,			// 136   Unused
		null,			// 137   Unused
		null,			// 138   Unused
		null,			// 139   Unused
		null,			// 140   Unused
		null,			// 141   Unused
		null,			// 142   Unused
		null,			// 143   Unused
		null,			// 144   Unused
		null,			// 145   Unused
		null,			// 146   Unused
		null,			// 147   Unused
		null,			// 148   Unused
		null,			// 149   Unused
		null,			// 150   Unused
		null,			// 151   Unused
		null,			// 152   Unused
		null,			// 153   Unused
		null,			// 154   Unused
		null,			// 155   Unused
		null,			// 156   Unused
		null,			// 157   Unused
		null,			// 158   Unused
		null,			// 159   Unused
		"&nbsp;",		// 160   Nonbreaking space
		"&iexcl;",		// 161 � Inverted exclamation
		"&cent;",		// 162 � Cent sign
		"&pound;",		// 163 � Pound sterling
		"&curren;",		// 164 � General currency sign
		"&yen;",		// 165 � Yen sign
		"&brvbar;",		// 166 � or &brkbar; Broken vertical bar
		"&sect;",		// 167 � Section sign
		"&uml;",		// 168 � or &die; Di�resis / Umlaut
		"&copy;",		// 169 � Copyright
		"&ordf;",		// 170 � Feminine ordinal
		"&laquo;",		// 171 � Left angle quote, guillemot left
		"&not;",		// 172 � Not sign
		"&shy;",		// 173 � Soft hyphen
		"&reg;",		// 174 � Registered trademark
		"&macr;",		// 175 � or &hibar; Macron accent
		"&deg;",		// 176 � Degree sign
		"&plusmn;",		// 177 � Plus or minus
		"&sup2;",		// 178 � Superscript two
		"&sup3;",		// 179 � Superscript three
		"&acute;",		// 180 � Acute accent
		"&micro;",		// 181 � Micro sign
		"&para;",		// 182 � Paragraph sign
		"&middot;",		// 183 � Middle dot
		"&cedil;",		// 184 � Cedilla
		"&sup1;",		// 185 � Superscript one
		"&ordm;",		// 186 � Masculine ordinal
		"&raquo;",		// 187 � Right angle quote, guillemot right
		"&frac14;",		// 188 � Fraction one-fourth
		"&frac12;",		// 189 � Fraction one-half
		"&frac34;",		// 190 � Fraction three-fourths
		"&iquest;",		// 191 � Inverted question mark
		"&Agrave;",		// 192 � Capital A, grave accent
		"&Aacute;",		// 193 � Capital A, acute accent
		"&Acirc;",		// 194 � Capital A, circumflex
		"&Atilde;",		// 195 � Capital A, tilde
		"&Auml;",		// 196 � Capital A, di�resis / umlaut
		"&Aring;",		// 197 � Capital A, ring
		"&AElig;",		// 198 � Capital AE ligature
		"&Ccedil;",		// 199 � Capital C, cedilla
		"&Egrave;",		// 200 � Capital E, grave accent
		"&Eacute;",		// 201 � Capital E, acute accent
		"&Ecirc;",		// 202 � Capital E, circumflex
		"&Euml;",		// 203 � Capital E, di�resis / umlaut
		"&Igrave;",		// 204 � Capital I, grave accent
		"&Iacute;",		// 205 � Capital I, acute accent
		"&Icirc;",		// 206 � Capital I, circumflex
		"&Iuml;",		// 207 � Capital I, di�resis / umlaut
		"&ETH;",		// 208 � Capital Eth, Icelandic
		"&Ntilde;",		// 209 � Capital N, tilde
		"&Ograve;",		// 210 � Capital O, grave accent
		"&Oacute;",		// 211 � Capital O, acute accent
		"&Ocirc;",		// 212 � Capital O, circumflex
		"&Otilde;",		// 213 � Capital O, tilde
		"&Ouml;",		// 214 � Capital O, di�resis / umlaut
		"&times;",		// 215 � Multiply sign
		"&Oslash;",		// 216 � Capital O, slash
		"&Ugrave;",		// 217 � Capital U, grave accent
		"&Uacute;",		// 218 � Capital U, acute accent
		"&Ucirc;",		// 219 � Capital U, circumflex
		"&Uuml;",		// 220 � Capital U, di�resis / umlaut
		"&Yacute;",		// 221 � Capital Y, acute accent
		"&THORN;",		// 222 � Capital Thorn, Icelandic
		"&szlig;",		// 223 � Small sharp s, German sz
		"&agrave;",		// 224 � Small a, grave accent
		"&aacute;",		// 225 � Small a, acute accent
		"&acirc;",		// 226 � Small a, circumflex
		"&atilde;",		// 227 � Small a, tilde
		"&auml;",		// 228 � Small a, di�resis / umlaut
		"&aring;",		// 229 � Small a, ring
		"&aelig;",		// 230 � Small ae ligature
		"&ccedil;",		// 231 � Small c, cedilla
		"&egrave;",		// 232 � Small e, grave accent
		"&eacute;",		// 233 � Small e, acute accent
		"&ecirc;",		// 234 � Small e, circumflex
		"&euml;",		// 235 � Small e, di�resis / umlaut
		"&igrave;",		// 236 � Small i, grave accent
		"&iacute;",		// 237 � Small i, acute accent
		"&icirc;",		// 238 � Small i, circumflex
		"&iuml;",		// 239 � Small i, di�resis / umlaut
		"&eth;",		// 240 � Small eth, Icelandic
		"&ntilde;",		// 241 � Small n, tilde
		"&ograve;",		// 242 � Small o, grave accent
		"&oacute;",		// 243 � Small o, acute accent
		"&ocirc;",		// 244 � Small o, circumflex
		"&otilde;",		// 245 � Small o, tilde
		"&ouml;",		// 246 � Small o, di�resis / umlaut
		"&divide;",		// 247 � Division sign
		"&oslash;",		// 248 � Small o, slash
		"&ugrave;",		// 249 � Small u, grave accent
		"&uacute;",		// 250 � Small u, acute accent
		"&ucirc;",		// 251 � Small u, circumflex
		"&uuml;",		// 252 � Small u, di�resis / umlaut
		"&yacute;",		// 253 � Small y, acute accent
		"&thorn;",		// 254 � Small thorn, Icelandic
		"&yuml;",		// 255 � Small y, di�resis / umlaut
	};


	/**
	 * Constructor
	 */
	private HtmlUtil() {
		super();
	}

	/**
	 * Method encodeHtml
	 * @param	obj	Object
	 * @return	String
	 */
	public static final String encodeHtml(Object obj) {

		if (obj == null) {
			return Entities.NBSP;
		} else if (obj instanceof String) {
			return encodeHtml((String) obj);
		} else {
			return encodeHtml(obj.toString());
		}
	}

	/**
	 * Converts a String to an equivalent HTML-String
	 * @param	raw		Object
	 * @param	filter	Filter
	 * @return	String
	 */
	public static final String encodeHtml(Object raw, boolean filter) {
		if (filter) {
			return encodeHtml(raw);
		} else {
			// If no Filter is specified return it as raw
			if (raw == null) {
				return "";
			} else {
				return raw.toString();
			}
		}
	}

	/**
	 * Method encodeHtml
	 * @param	str	String to encode
	 * @return	String
	 */
	private static final String encodeHtml(String str) {

		if ((str == null) || "".equals(str.trim())) {
			return Entities.NBSP;
		}

		StringBuffer buf = new StringBuffer();

		int len = str.length();
		int i = 0;

		while (i < len) {
			char c	= str.charAt(i++);

			if (c == '\r') {
				// Skip the additional line feed character
				if (((i + 1) < len) && (str.charAt(i + 1) == '\n')) {
					++i;
				}

				buf.append("<br>");
			} else if (c < HTMLCODE.length) {
				if (null == HTMLCODE[c]) {
					buf.append(c);
				} else {
					buf.append(HTMLCODE[c]);
				}
			} else {
				// No Item found in the Mapping Table
				buf.append(c);
			}
		}

		return buf.toString();
	}

	/**
	 * This method replaces every occurence of <code>&<code> into <code>&amp;</code>
	 * 
	 * @param		str	String to encode
	 * @return		encoded String
	 */
	public static final String encodeTextarea(String str) {

		if ((str == null) || "".equals(str.trim())) {
			return "";
		}

		StringBuffer buf = new StringBuffer();

		int len = str.length();
		for (int i = 0; i < len; i++) {
			char c = str.charAt(i);

			if (c == '&') {
				buf.append("&amp;");
			} else if (c == '<') {
				buf.append("&lt;");
			} else if (c == '>') {
				buf.append("&gt;");
			} else {
				buf.append(c);
			}
		}

		return buf.toString();
	}

	/**
	 * Method encodeHtml
	 * @param	c	Character to encode
	 * @return	Encoded HTML String
	 */
	public static final String encodeHtml(char c) {
		if (c < HTMLCODE.length) {
			if (null == HTMLCODE[c]) {
				return String.valueOf(c);
			} else {
				return HTMLCODE[c];
			}
		}

		return String.valueOf(c);
	}

	/**
	 * Method encodeUrl
	 * @param	str	URL
	 * @return	String
	 */
	public static final String encodeUrl(String str) {
		return encodeHtml(str);
	}

}