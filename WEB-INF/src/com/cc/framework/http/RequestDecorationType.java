/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/http/RequestDecorationType.java,v 1.11 2005/07/08 14:13:49 P001002 Exp $
 * $Revision: 1.11 $
 * $Date: 2005/07/08 14:13:49 $
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

package com.cc.framework.http;

import java.io.Serializable;

import com.cc.framework.common.SimpleEnumType;

/**
 * Enumeration of RequestDecorationTypes
 *
 * @author	  <a href="mailto:gschulz@scc-gmbh.com">Gernot Schulz</a>
 * @version	  $Revision: 1.11 $
 * @since     1.0
 */
public class RequestDecorationType implements SimpleEnumType, Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -6533559269910057685L;

	/**
	 * TYPE_HD_FORM
	 */
	public static final RequestDecorationType TYPE_HD_FORM	= new RequestDecorationType("Form", "hdf");

	/**
	 * TYPE_HD_URL
	 */
	public static final RequestDecorationType TYPE_HD_URL	= new RequestDecorationType("URL", "hdu");

	/**
	 * TYPE_URL
	 */
	public static final RequestDecorationType TYPE_URL 	= new RequestDecorationType("URL", null);

	/**
	 * Internal Type
	 */
	private String value	= null;

	/**
	 * Prefix
	 */
	private String prefix	= null;


	// ------------------------------------------------
	//                Methods
	// ------------------------------------------------

	/**
	 * Constructor for RequestDecorationType
	 *
	 * @param	value	Value
	 * @param	prefix	Prefix
	 */
	public RequestDecorationType(String value, String prefix) {
		super();

		this.value		= value;
		this.prefix	= prefix;
	}

	/**
	 * Checks if the Argument matches the Prefix of the
	 * EnumerationTyp
	 * @param	requestParam	Request Parameter
	 * @return	boolean
	 */
	public boolean isMatching(String requestParam) {
		if (prefix == null) {
			return false;
		}

		return requestParam.indexOf(prefix) != -1;
	}

	/**
	 * Concates a String with the Prefix
	 * @param 	str	String
	 * @return	String
	 */
	public String decorate(String str) {
		if (prefix == null) {
			return str;
		}
		return prefix + str;
	}

	/**
	 * Retuns the Name
	 * @return String
	 */
	public String getName() {
		return value;
	}

	/**
	 * Compares this type to the specified object.
	 *
	 * @param	obj The object to compare this <code>RequestDecorationType</code>
	 * 			object against.
	 * @return  <code>true</code> if the internal types are equal;
	 *          <code>false</code> otherwise.
	 */
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		} else if (obj instanceof String) {
			return value.equals(obj);
		} else if (obj instanceof RequestDecorationType) {
			return value.equals(((RequestDecorationType) obj).value);
		}

		return false;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return value.hashCode();
	}
}