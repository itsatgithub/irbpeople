/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/Color.java,v 1.15 2005/07/08 14:16:49 P001002 Exp $
 * $Revision: 1.15 $
 * $Date: 2005/07/08 14:16:49 $
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

package com.cc.framework.ui;

import java.io.Serializable;

/**
 * Enumeration of Color Codes used by the Painters
 *
 * @author	   <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.15 $
 * @since      1.0
 */
public class Color implements Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -842305718519727653L;

	/** color : white */
	public static final Color WHITE			= new Color(0xffffff);

	/** color : black */
	public static final Color BLACK			= new Color(0x000000);

	/** color : red */
	public static final Color RED			= new Color(0xff0000);

	/** color : green */
	public static final Color GREEN			= new Color(0x00ff00);

	/** color : blue */
	public static final Color BLUE			= new Color(0x0000ff);

	/** color : orange */
	public static final Color ORANGE		= new Color(0xffa500);

	/** color : yellow */
	public static final Color YELLOW		= new Color(0xffff00);

	/**
	 * Red
	 */
	private int redPart		= 0;

	/**
	 * Green
	 */
	private int greenPart	= 0;

	/**
	 * Blue
	 */
	private int bluePart	= 0;

	/**
	 * Table with HTML color codes
	 */
	private static final String[][] COLORNAMES	= {
		{"aliceblue",			"#F0F8FF"}, {"antiquewhite",			"#FAEBD7"}, {"aqua",			"#00FFFF"},
		{"aquamarine",			"#7FFFD4"}, {"azure",					"#F0FFFF"}, {"beige",			"#F5F5DC"},
		{"bisque",				"#FFE4C4"}, {"black",					"#000000"}, {"blanchedalmond",	"#FFEBCD"},
		{"blue",				"#0000FF"}, {"blueviolet",				"#8A2BE2"}, {"brown",			"#A52A2A"},
		{"burlywood",			"#DEB887"}, {"cadetblue",				"#5F9EA0"}, {"chartreuse",		"#7FFF00"},
		{"chocolate",			"#D2691E"}, {"coral",					"#FF7F50"}, {"cornflowerblue",	"#6495ED"},
		{"cornsilk",			"#FFF8DC"}, {"crimson",					"#DC143C"}, {"cyan",			"#00FFFF"},
		{"darkblue",			"#00008B"}, {"darkcyan",				"#008B8B"}, {"darkgoldenrod",	"#B8860B"},
		{"darkgray",			"#A9A9A9"}, {"darkgreen",				"#006400"}, {"darkkhaki",		"#BDB76B"},
		{"darkmagenta",			"#8B008B"}, {"darkolivegreen",			"#556B2F"}, {"darkorange",		"#FF8C00"},
		{"darkorchid",			"#9932CC"}, {"darkred",					"#8B0000"}, {"darksalmon",		"#E9967A"},
		{"darkseagreen",		"#8FBC8B"}, {"darkslateblue",			"#483D8B"},
		{"darkslategray",		"#2F4F4F"}, {"darkturquoise",			"#00CED1"}, {"darkviolet",		"#9400D3"},
		{"deeppink",			"#FF1493"}, {"deepskyblue",				"#00BFFF"}, {"dimgray",			"#696969"},
		{"dodgerblue",			"#1E90FF"}, {"firebrick",				"#B22222"}, {"floralwhite",		"#FFFAF0"},
		{"forestgreen",			"#228B22"}, {"fuchsia",					"#FF00FF"}, {"gainsboro",		"#DCDCDC"},
		{"ghostwhite",			"#F8F8FF"}, {"gold",					"#FFD700"}, {"goldenrod",		"#DAA520"},
		{"gray",				"#808080"}, {"green",					"#008000"}, {"greenyellow",		"#ADFF2F"},
		{"honeydew",			"#F0FFF0"}, {"hotpink",					"#FF69B4"}, {"indianred",		"#CD5C5C"},
		{"indigo",				"#4B0082"}, {"ivory",					"#FFFFF0"}, {"khaki",			"#F0E68C"},
		{"lavender",			"#E6E6FA"}, {"lavenderblush",			"#FFF0F5"}, {"lawngreen",		"#7CFC00"},
		{"lemonchiffon",		"#FFFACD"}, {"lightblue",				"#ADD8E6"}, {"lightcoral",		"#F08080"},
		{"lightcyan",			"#E0FFFF"}, {"lightgoldenrodyellow",	"#FAFAD2"},
		{"lightgreen",			"#90EE90"}, {"lightgrey",				"#D3D3D3"}, {"lightpink",		"#FFB6C1"},
		{"lightsalmon",			"#FFA07A"}, {"lightseagreen",			"#20B2AA"}, {"lightskyblue",	"#87CEFA"},
		{"lightslategray",		"#778899"}, {"lightsteelblue",			"#B0C4DE"},
		{"lightyellow",			"#FFFFE0"}, {"lime",					"#00FF00"}, {"limegreen",		"#32CD32"},
		{"linen",				"#FAF0E6"}, {"magenta",					"#FF00FF"}, {"maroon",			"#800000"},
		{"mediumaquamarine",	"#66CDAA"}, {"mediumblue",				"#0000CD"},
		{"mediumorchid",		"#BA55D3"}, {"mediumpurple",			"#9370DB"},
		{"mediumseagreen",		"#3CB371"}, {"mediumslateblue",			"#7B68EE"},
		{"mediumspringgreen",	"#00FA9A"}, {"mediumturquoise",			"#48D1CC"},
		{"mediumvioletred",		"#C71585"}, {"midnightblue",			"#191970"}, {"mintcream",		"#F5FFFA"},
		{"mistyrose",			"#FFE4E1"}, {"moccasin",				"#FFE4B5"}, {"navajowhite",		"#FFDEAD"},
		{"navy",				"#000080"}, {"oldlace",					"#FDF5E6"}, {"olive",			"#808000"},
		{"olivedrab",			"#6B8E23"}, {"orange",					"#FFA500"}, {"orangered",		"#FF4500"},
		{"orchid",				"#DA70D6"}, {"palegoldenrod",			"#EEE8AA"}, {"palegreen",		"#98FB98"},
		{"paleturquoise",		"#AFEEEE"}, {"palevioletred",			"#DB7093"}, {"papayawhip",		"#FFEFD5"},
		{"peachpuff",			"#FFDAB9"}, {"peru",					"#CD853F"}, {"pink",			"#FFC0CB"},
		{"plum",				"#DDA0DD"}, {"powderblue",				"#B0E0E6"}, {"purple",			"#800080"},
		{"red",					"#FF0000"}, {"rosybrown",				"#BC8F8F"}, {"royalblue",		"#4169E1"},
		{"saddlebrown",			"#8B4513"}, {"salmon",					"#FA8072"}, {"sandybrown",		"#F4A460"},
		{"seagreen",			"#2E8B57"}, {"seashell",				"#FFF5EE"}, {"sienna",			"#A0522D"},
		{"silver",				"#C0C0C0"}, {"skyblue",					"#87CEEB"}, {"slateblue",		"#6A5ACD"},
		{"slategray",			"#708090"}, {"snow",					"#FFFAFA"}, {"springgreen",		"#00FF7F"},
		{"steelblue",			"#4682B4"}, {"tan",						"#D2B48C"}, {"teal",			"#008080"},
		{"thistle",				"#D8BFD8"}, {"tomato",					"#FF6347"}, {"turquoise",		"#40E0D0"},
		{"violet",				"#EE82EE"}, {"wheat",					"#F5DEB3"}, {"white",			"#FFFFFF"},
		{"whitesmoke",			"#F5F5F5"}, {"yellow",					"#FFFF00"}, {"yellowgreen",		"#9ACD32"},
	};

	/**
	 * Constructor for Color
	 *
	 * @param	red		The value for red
	 * @param	green	The value for green
	 * @param	blue	The value for blue
	 */
	public Color(int red, int green, int blue) {
		super();

		this.redPart	= red;
		this.greenPart	= green;
		this.bluePart	= blue;
	}

	/**
	 * Constructor for Color
	 *
	 * @param	rgb		The value of the color including the red, green, blue part
	 */
	public Color(int rgb) {
		super();

		this.redPart	= (rgb & 0xFF0000) >> 16;
		this.greenPart	= (rgb & 0x00FF00) >> 8;
		this.bluePart	= (rgb & 0x0000FF);
	}

	/**
	 * Constructor for Color
	 *
	 * @param	colorString		The string value of the color
	 */
	public Color(String colorString) {
		super();

		String col	= colorString.toLowerCase();

		if (!col.startsWith("#")) {
			col	= mapColorName(col);
		}

		if (col.startsWith("#")) {

			// Die einzelnen RGB Farbwerte bestimmen
			redPart		= hexToInt(col.substring(1, 3));
			greenPart	= hexToInt(col.substring(3, 5));
			bluePart	= hexToInt(col.substring(5, 7));
		}
	}

	/**
	 * Converts a hex value to an int value
	 *
	 * @param	digit	Digit to convert
	 * @return	The int value
	 */
	private static int hexToInt(char digit) {

		if ((digit >= '0') && (digit <= '9')) {
			return digit - '0';
		} else if ((digit >= 'a') && (digit <= 'f')) {
			return 10 + (digit - 'a');
		} else if ((digit >= 'A') && (digit <= 'F')) {
			return 10 + (digit - 'A');
		} else {
			return 0;
		}
	}

	/**
	 * Converts a hex value to an int value
	 *
	 * @param	hexDigits	Digit to convert
	 * @return	The int value
	 */
	private static int hexToInt(String hexDigits) {
		return (16 * hexToInt(hexDigits.charAt(0))) + hexToInt(hexDigits.charAt(1));
	}

	/**
	 * Returns the color for a color value
	 *
	 * @param	name	ColorCode
	 * @return	String
	 */
	public static String mapColorName(String name) {

		int left	= 0;
		int right	= COLORNAMES.length - 1;
		int i		= (right - left) / 2;

		while ((i >= left) && (i <= right)) {
			int cmp	= name.compareTo(COLORNAMES[i][0]);

			if (cmp < 0) {
				right	= i - 1;
			} else if (cmp > 0) {
				left	= i + 1;
			} else {
				return COLORNAMES[i][1];
			}

			i	= left + (right - left) / 2;
		}

		return "";
	}

	/**
	 * Creates a color object for the specified argument
	 *
	 * @param	colorString		The color value
	 * @return	A color object
	 */
	public static Color mapString(String colorString) {
		return new Color(colorString);
	}

	/**
	 * Converts an int to a hex value
	 *
	 * @param	dec		The int value
	 * @return	The hex value
	 */
	private String decToHex(int dec) {

		String hex	= Integer.toHexString(dec);

		if (hex.length() == 1) {
			hex	= "0" + hex;
		}

		return hex.toUpperCase();
	}

	/**
	 * Returns the HTML Name for the color
	 *
	 * @return		String
	 */
	public String toHtmlName() {

		String html = toHtml().toUpperCase();

		for (int i = 0; i < COLORNAMES.length; i++) {
			if (COLORNAMES[i][1].equals(html)) {
				return COLORNAMES[i][0];
			}
		}

		return null;
	}

	/**
	 * Returns a String representation for the color in the form #AABBCC
	 *
	 * @return	A String for the color in the form #AABBCC
	 */
	public String toString() {
		return toHtml();
	}

	/**
	 * Generates a Color value in the form #AABBCC
	 *
	 * @return Returns a String with the hex value in the form #AABBCC
	 */
	public String toHtml() {
		return "#" + toHex();
	}

	/**
	 * Returns a String with the hex values for the color
	 *
	 * @return	String
	 */
	public String toHex() {
		return decToHex(redPart) + decToHex(greenPart) + decToHex(bluePart);
	}

	/**
	 * Returns a String with the dezimal values for the color
	 *
	 * @return	String
	 */
	public String toDez() {
		StringBuffer buf = new StringBuffer();
		buf
			.append(redPart)
			.append(",")
			.append(greenPart)
			.append(",")
			.append(bluePart);

		return buf.toString();
	}

	/**
	 * Returns a rgb value for the color
	 *
	 * @return	String
	 */
	public int toRGB() {
		return ((redPart << 16) + (greenPart << 8) + bluePart);
	}

	/**
	 * Generates a Color AWT-Objekt
	 *
	 * @return	The java.awt.Color Object
	 */
	public java.awt.Color toAWT() {
		return new java.awt.Color(redPart, greenPart, bluePart);
	}

	/**
	 * @return		blue part of the color
	 */
	public int blue() {
		return bluePart;
	}

	/**
	 * @return		green part of the color
	 */
	public int green() {
		return greenPart;
	}

	/**
	 * @return		red part of the color
	 */
	public int red() {
		return redPart;
	}
}