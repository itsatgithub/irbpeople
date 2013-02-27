/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/SelectMode.java,v 1.16 2005/07/08 14:16:49 P001002 Exp $
 * $Revision: 1.16 $
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
 * Enumeration for select nodes of checkboxes in a list or tree control.
 * <p>
 * The selection mode <code>single</code> means that only one checkbox can be selected.
 * So other check ckeckboxes will be unchecked. If you choose the
 * select mode <code>multiple</code> different chekcboxes can be checked at the same time.
 * </p>
 *
 * @author    <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version   $Revision: 1.16 $
 * @since      1.0
 */
public final class SelectMode implements SimpleEnumType, Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 228861242923780983L;

	/** mode: NONE */
	public static final SelectMode NONE	= new SelectMode("none");

	/** mode: SINGLE */
	public static final SelectMode SINGLE	= new SelectMode("single");

	/** mode: MULTIPLE */
	public static final SelectMode MULTIPLE	= new SelectMode("multiple");

	/**
	 * The internal type
	 */
	private String type;

	/**
	 * Collection with all objects
	 */
	private static final SelectMode[] ALL = {NONE, SINGLE, MULTIPLE};

	// ----------------------------------
	//           methods
	// ----------------------------------

	/**
	 * Constructor for SelectMode
	 *
	 * @param	type	The select mode
	 */
	private SelectMode(String type) {
		this.type	= type;
	}

	/**
	 * Returns a string representation for the object
	 *
	 * @return	The select mode
	 */
	public String toString() {
		return type;
	}

	/**
	 * Compares this type to the specified object.
	 *
	 * @param	obj The object to compare this <code>SelectMode</code>
	 * 			object against.
	 * @return  <code>true</code> if the internal types are equal;
	 *          <code>false</code> otherwise.
	 */
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		} else if (obj instanceof String) {
			return type.equals(obj);
		} else if (obj instanceof SelectMode) {
			return type.equals(((SelectMode) obj).type);
		}

		return false;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return type.hashCode();
	}

	/**
	 * Creates for the argument an object of type SelectMode
	 *
	 * @param	code	String to parse
	 * @return	The new Object
	 */
	public static SelectMode parse(String code) {

		for (int index = 0; index < ALL.length; index++) {
			if (ALL[index].equals(code)) {
				return ALL[index];
			}
		}

		throw new InvalidEnumType("Invalid SelectMode: " + code);
	}
}