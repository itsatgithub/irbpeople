/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/FormMethod.java,v 1.12 2005/07/08 14:16:49 P001002 Exp $
 * $Revision: 1.12 $
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

import com.cc.framework.common.InvalidEnumType;
import com.cc.framework.common.SimpleEnumType;

/**
 * Enumeration for methods how a form can be submitted.
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.12 $
 * @since      1.0
 */
public final class FormMethod implements SimpleEnumType, Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 2418123454935422186L;

	/** method: GET */
	public static final FormMethod GET	= new FormMethod("get");

	/** method: POST */
	public static final FormMethod POST	= new FormMethod("post");

	/**
	 * The internal type
	 */
	private String method	= "";

	/**
	 * Collection with all types
	 */
	private static final FormMethod[] ALL = {POST, GET};


	// ----------------------------------
	//           methods
	// ----------------------------------

	/**
	 * Constructor for FormMethod
	 *
	 * @param	method	The method
	 */
	private FormMethod(String method) {
		this.method	= method;
	}

	/**
	 * Compares this type to the specified object.
	 *
	 * @param	obj The object to compare this <code>FormMethod</code>
	 * 			object against.
	 * @return  <code>true</code> if the internal types are equal;
	 *          <code>false</code> otherwise.
	 */
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		} else if (obj instanceof String) {
			return method.equals(obj);
		} else if (obj instanceof FormMethod) {
			return method.equals(((FormMethod) obj).method);
		}

		return false;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return method.hashCode();
	}

	/**
	 * Creates for the argument an object of type FormMethod
	 *
	 * @param	code	String to parse
	 * @return	The new Object
	 */
	public static FormMethod parse(String code) {

		for (int index = 0; index < ALL.length; index++) {
			if (ALL[index].equals(code)) {
				return ALL[index];
			}
		}

		throw new InvalidEnumType("Invalid FormMethod: " + code);
	}

	/**
	 * Returns a string representation for the object
	 *
	 * @return	The mehode
	 */
	public String toString() {
		return method;
	}
}
