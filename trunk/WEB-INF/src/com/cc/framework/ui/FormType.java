/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/FormType.java,v 1.11 2005/02/25 12:12:45 P001002 Exp $
 * $Revision: 1.11 $
 * $Date: 2005/02/25 12:12:45 $
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

import com.cc.framework.common.InvalidEnumType;

/**
 * Enumaration of forms used by the common controls framework
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.11 $
 * @since      1.0
 */
public final class FormType {

	/** form: NONE */
	public static final FormType NONE		= new FormType("none", false);

	/** form: SEARCH */
	public static final FormType SEARCH		= new FormType("search", true);

	/** form: CREATE */
	public static final FormType CREATE		= new FormType("create", true);

	/** form: DISPLAY */
	public static final FormType DISPLAY	= new FormType("display", false);

	/** form: EDIT */
	public static final FormType EDIT		= new FormType("edit", true);

	/** form: HEADER */
	public static final FormType HEADER		= new FormType("header", false);

	/** form: INFO */
	public static final FormType INFO		= new FormType("info", false);

	/**
	 * The form type
	 */
	private String type	= "";

	/**
	 * This flag indicates if editing is allowed
	 */
	private boolean editable = false;

	/**
	 * Collection of all forms
	 */
	private static final FormType[] ALL = {SEARCH, CREATE, DISPLAY, EDIT, HEADER, INFO};


	// ----------------------------------
	//           methods
	// ----------------------------------

	/**
	 * Constructor for FormType
	 *
	 * @param	type The form type
	 * @param	editable Flag that indicates if editing is allowed
	 */
	private FormType(String type, boolean editable) {
		super();

		this.type = type;
		this.editable = editable;
	}

	/**
	 * Compares this form type to the specified object.
	 *
	 * @param	obj		The object to compare this <code>FormType</code> against.
     * @return  <code>true</code> if the <code>form types</code>are equal;
     *          <code>false</code> otherwise.
	 */
	public boolean equals(Object obj) {

		if (obj instanceof String) {
			String other = (String) obj;

			return type.equals(other);
		}

		return super.equals(obj);
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return type.hashCode();
	}

	/**
	 * Creates for the argument an object of type FormType
	 *
	 * @param	code	String to parse
	 * @return	The new Object
	 */
	public static FormType parse(String code) {

		for (int index = 0; index < ALL.length; index++) {
			if (ALL[index].equals(code)) {
				return ALL[index];
			}
		}

		throw new InvalidEnumType("Invalid FormType: " + code);
	}

	/**
 	 * Returns a string representation for the object
 	 *
	 * @return	The form type
	 */
	public String toString() {
		return type;
	}

	/**
	 * @return		returns <code>true</code> if this form type allows
	 * 				editing
	 */
	public boolean isEditable() {
		return editable;
	}

}
