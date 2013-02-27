/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/common/SortOrder.java,v 1.15 2005/07/08 14:13:42 P001002 Exp $
 * $Revision: 1.15 $
 * $Date: 2005/07/08 14:13:42 $
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

package com.cc.framework.common;

import java.io.Serializable;


/**
 * Defines sort order types used by the common controls framework
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.15 $
 * @since      1.0
 */
public final class SortOrder implements SimpleEnumType, Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -8474050280986769597L;

	/** sort: NONE */
	public static final SortOrder NONE			= new SortOrder("none", "none");

	/** sort: ASCENDING */
	public static final SortOrder ASCENDING		= new SortOrder("asc", "Ascending");

	/** sort: DESCENDING */
	public static final SortOrder DESCENDING	= new SortOrder("desc", "Descending");

	/**
	 * identifier / key / type
	 */
	private String type;

	/**
	 * display value / nice name
	 */
	private String name;

	/**
	 * collection with all kinds of sort orders
	 */
	private static final SortOrder[] ALL = {ASCENDING, DESCENDING, NONE};


	// ----------------------------
	//      methods
	// ----------------------------

	/**
	 * Constructor for SortOrder
	 *
	 * @param	type	Key
	 * @param	name	Value
	 */
	private SortOrder(String type, String name) {
		super();

		this.type	= type;
		this.name	= name;
	}

	/**
	 * Returns the Key
	 *
	 * @return	String
	 */
	public String toString() {
		return type;
	}

	/**
	 * Creates for the argument an Object of type SortOrder
	 *
	 * @param	code	String to parse
	 * @return	SortOrder
	 */
	public static SortOrder parse(String code) {

		for (int index = 0; index < ALL.length; index++) {
			if (ALL[index].equals(code)) {
				return ALL[index];
			}
		}

		throw new InvalidEnumType("Invalid SortOrder: " + code);
	}

	/**
	 * Gets the internal type
	 *
	 * @return	String
	 */
	public String getCode() {
		return type;
	}

	/**
	 * Returns the name.
	 *
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Compares this type to the specified object.
	 *
	 * @param	obj The object to compare this <code>SortOrder</code>
	 * 			object against.
	 * @return  <code>true</code> if the internal types are equal;
	 *          <code>false</code> otherwise.
	 */
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		} else if (obj instanceof String) {
			return type.equals(obj);
		} else if (obj instanceof SortOrder) {
			return type.equals(((SortOrder) obj).type);
		}

		return false;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return type.hashCode();
	}
}