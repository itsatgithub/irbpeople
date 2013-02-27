/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/ExpansionMode.java,v 1.14 2005/07/08 14:16:48 P001002 Exp $
 * $Revision: 1.14 $
 * $Date: 2005/07/08 14:16:48 $
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
 * Enumeration for expansion types for the nodes of a tree.
 * <p>
 * The nodes of a tree can have the following states:
 * <ul>
 *   <li>FULL - All nodes are expanded.</li>
 *   <li>SINGLEL - Only one node can be expanded.</li>
 *   <li>MULTIPLE - Multiple nodes can be expanded.</li>
 * </ul>
 * </p>
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.14 $
 * @since      1.0
 */
public final class ExpansionMode implements SimpleEnumType, Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 7056796997832090015L;

	/** expansion mode: FULL */
	public static final ExpansionMode FULL		= new ExpansionMode("full");

	/** expansion mode: SINGLE */
	public static final ExpansionMode SINGLE	= new ExpansionMode("single");

	/** expansion mode: MULTIPLE */
	public static final ExpansionMode MULTIPLE	= new ExpansionMode("multiple");

	/**
	 * The internal type
	 */
	private String type;


	/**
	 * Collection with all kinds of expansion types
	 */
	private static final ExpansionMode[] ALL = {FULL, SINGLE, MULTIPLE};


	// ----------------------------------
	//           methods
	// ----------------------------------

	/**
	 * Constructor for ExpansionMode
	 *
	 * @param	type	The expansion type
	 */
	private ExpansionMode(String type) {
		this.type	= type;
	}

	/**
	 * Returns the expansion mode
	 *
	 * @return	String
	 */
	public String toString() {
		return type;
	}

	/**
	 * Compares this type to the specified object.
	 *
	 * @param	obj The object to compare this <code>ExpansionMode</code>
	 * 			object against.
	 * @return  <code>true</code> if the internal types are equal;
	 *          <code>false</code> otherwise.
	 */
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		} else if (obj instanceof String) {
			return type.equals(obj);
		} else if (obj instanceof ExpansionMode) {
			return type.equals(((ExpansionMode) obj).type);
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
	 * Creates for the argument an object of type ExpansionMode
	 *
	 * @param	code	String to parse
	 * @return	The new Object
	 */
	public static ExpansionMode parse(String code) {

		for (int index = 0; index < ALL.length; index++) {
			if (ALL[index].equals(code)) {
				return ALL[index];
			}
		}

		throw new InvalidEnumType("Invalid ExpansionMode: " + code);
	}
}