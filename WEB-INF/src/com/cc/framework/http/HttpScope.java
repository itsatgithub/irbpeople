/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/http/HttpScope.java,v 1.14 2005/07/08 14:13:49 P001002 Exp $
 * $Revision: 1.14 $
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

import com.cc.framework.common.InvalidEnumType;
import com.cc.framework.common.SimpleEnumType;


/**
 * Defines HTTP scope types used in the common controls framework
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.14 $
 * @since      1.0
 */
public final class HttpScope implements SimpleEnumType, Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 474714541873963185L;

	/** HttpScope: Any - no scope specified */
	public static final HttpScope ANY			= new HttpScope("any");

	/** HttpScope: Session */
	public static final HttpScope SESSION		= new HttpScope("session");

	/** HttpScope: Request */
	public static final HttpScope REQUEST		= new HttpScope("request");

	/** HttpScope: Page */
	public static final HttpScope PAGE			= new HttpScope("page");

	/** HttpScope: Dialog */
	public static final HttpScope DIALOG		= new HttpScope("dialog");

	/** HttpScope: Application */
	public static final HttpScope APPLICATION	= new HttpScope("application");

	/**
	 * collection with all kinds of HttpScopes
	 */
	private static final HttpScope[] ALL = {ANY, REQUEST, DIALOG, SESSION, PAGE, APPLICATION};

	/**
	 * The display value
	 */
	private String value = "";


	// ------------------------------------------------
	//                Methods
	// ------------------------------------------------

	/**
	 * Constructor for HttpScope
	 *
	 * @param	value	the display value
	 */
	private HttpScope(String value) {
		super();

		this.value = value;
	}

	/**
	 * Compares this type to the specified object.
	 *
	 * @param	obj The object to compare this <code>HttpScope</code>
	 * 			object against.
	 * @return  <code>true</code> if the internal types are equal;
	 *          <code>false</code> otherwise.
	 */
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		} else if (obj instanceof String) {
			return value.equals(obj);
		} else if (obj instanceof HttpScope) {
			return value.equals(((HttpScope) obj).value);
		}

		return false;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return value.hashCode();
	}

	/**
	 * Creates for the argument an Object of type HttpScope
	 *
	 * @param	code	String to parse
	 * @return	HttpScope
	 */
	public static HttpScope parse(String code) {

		if ((code == null) || "".equals(code)) {
			return ANY;
		}

		for (int index = 0; index < ALL.length; index++) {
			if (ALL[index].equals(code)) {
				return ALL[index];
			}
		}

		throw new InvalidEnumType("Invalid HttpScope: " + code);
	}

	/**
	 * Returns the display value
	 * @return	the display value
	 */
	public String toString() {
		return value;
	}
}

