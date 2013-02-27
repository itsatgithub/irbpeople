/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/resource/MimeType.java,v 1.17 2005/09/27 14:06:23 P001002 Exp $
 * $Revision: 1.17 $
 * $Date: 2005/09/27 14:06:23 $
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

package com.cc.framework.resource;

import java.io.Serializable;

import com.cc.framework.common.SimpleEnumType;

/**
 * Defines mime types used in the Common Controls Framework
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.17 $
 * @since      1.0
 */
public class MimeType implements SimpleEnumType, Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -4202577630885513132L;

	/** mime type: NONE */
	public static final MimeType NONE			= new MimeType("NONE", "none", "");

	/** mime type: PDF */
	public static final MimeType PDF			= new MimeType("PDF", "Portable Document Format", "application/pdf");

	/** mime type: HTML */
	public static final MimeType HTML			= new MimeType("HTML", "Hypertext Markup Language", "text/html");

	/** mime type: XML */
	public static final MimeType XML			= new MimeType("XML", "Extended Markup Language", "text/xml");

	/** mime type: PCL5 */
	public static final MimeType PCL5			= new MimeType("PCL5", "Printer Control Language 5", "application/x-pcl");

	/** mime type: TEXT */
	public static final MimeType TEXT			= new MimeType("TEXT", "Text", "text/plain");

	/** mime type: GIF */
	public static final MimeType GIF			= new MimeType("GIF", "Graphics Interchange Format", "image/gif");

	/** mime type: JPEG */
	public static final MimeType JPEG			= new MimeType("JPEG", "Joint Photographic Experts Group Format", "image/jpeg");

	/** mime type: SVG */
	public static final MimeType SVG			= new MimeType("SVG", "Scalable vector Graphics", "image/svg-xml");

	/** mime type: XLS */
	public static final MimeType XLS			= new MimeType("XLS", "Excel Workbook", "application/vnd.ms-excel");

	/** mime type: OCTETSTREAM */
	public static final MimeType OCTETSTREAM	= new MimeType("OCTETSTREAM", "Octet Stream", "application/octet-stream");

	/** mime type: JAVASCRIPT */
	public static final MimeType JAVASCRIPT		= new MimeType("JAVASCRIPT", "Java Script", "text/javascript");

	/**
	 * Output Format
	 */
	private String code;

	/**
	 * Display name
	 */
	private String name;

	/**
	 * Content type
	 */
	private String contentType;

	/**
	 * Constructor for MimeType
	 *
	 * @param	code		the code or short name
	 * @param	name		the display name
	 * @param	contentType	the content type
	 */
	public MimeType(String code, String name, String contentType) {
		super();

		this.code			= code;
		this.name			= name;
		this.contentType	= contentType;
	}

	/**
	 * Compares this type to the specified object.
	 *
	 * @param	obj The object to compare this <code>MimeType</code>
	 * 			object against.
	 * @return  <code>true</code> if the internal types are equal;
	 *          <code>false</code> otherwise.
	 */
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		} else if (obj instanceof String) {
			return code.equals(obj);
		} else if (obj instanceof MimeType) {
			return code.equals(((MimeType) obj).code);
		}

		return false;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return code.hashCode();
	}

	/**
	 * Compares the content type of this mime type to the specified object.
	 *
	 * @param	obj		The object to compare this <code>mime type</code> against.
     * @return  <code>true</code> if the <code>content type</code>are equal;
     *          <code>false</code> otherwise.
	 */
	public boolean equalsContentType(Object obj) {

		if (obj instanceof String) {
			return contentType.equals(obj);
		} else if (obj instanceof MimeType) {
			return contentType.equals(((MimeType) obj).getContentType());
		}

		return false;
	}

	/**
	 * @return returns the internal used key
	 */
	public String getKey() {
		return code;
	}

	/**
	 * @return returns the value (the name)
	 */
	public String getValue() {
		return name;
	}

	/**
	 * Returns the  content type
	 *
	 * @return String
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * Creates for the argument an Object of type MimeType
	 *
	 * @param	strFormat	String to parse
	 * @return	MimeType
	 */
	/* TODO:
	public static MimeType parse(String strFormat) {

		String str	= strFormat.trim();

		for (int index = 0; index < ALL.length; index++) {
			if (ALL[index].equals(str)) {
				return ALL[index];
			}
		}

		throw new InvalidEnumType("Invalid MimeType: " + str);
	}
*/
	/**
	 * Returns the Output Format
	 *
	 * @return	string
	 */
	public String toString() {
		return code;
	}
}
