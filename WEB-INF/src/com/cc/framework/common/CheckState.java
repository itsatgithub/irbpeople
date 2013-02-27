/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/common/CheckState.java,v 1.10 2005/07/08 14:13:42 P001002 Exp $
 * $Revision: 1.10 $
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
 * Defines check states used by the common controls framework
 *
 * @author	   <a href="mailto:gschulz@scc-gmbh.com">Gernot Schulz</a>
 * @version	   $Revision: 1.10 $
 * @since      1.2
 */
public final class CheckState implements SimpleEnumType2, Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -8886045820640850325L;

	/** CheckState: CHECKED */
	public static final CheckState CHECKED			= new CheckState(1, "checked");

	/** CheckState: UNCHECKED */
	public static final CheckState UNCHECKED		= new CheckState(0, "unchecked");

	/** CheckState: UNDEFINED */
	public static final CheckState UNDEFINED		= new CheckState(2, "undefined");

	/** CheckState: UNSELECTABLE */
	public static final CheckState UNSELECTABLE		= new CheckState(-1, "not selectable");

	/** CheckState: NONE */
	public static final CheckState NONE				= new CheckState(-2, "none");

	/**
	 * Unique Identifier / key / type
	 */
	private int key;

	/**
	 * Display value / nice name
	 */
	private String value;

	/**
	 * Collection with all kinds of check states
	 */
	private static final CheckState[] ALL = {CHECKED, UNCHECKED, UNDEFINED, UNSELECTABLE, NONE};

	// ----------------------------
	//      methods
	// ----------------------------

	/**
	 * Constructor
	 *
	 * @param 	key		The key
	 * @param	value	The value / nice name
	 */
	private CheckState(int key, String value) {
		super();

		this.key = key;
		this.value = value;
	}

	/**
	 * @see com.cc.framework.common.SimpleEnumType2#elements()
	 */
	public SimpleEnumType2[] elements() {
		return ALL;
	}

	/**
	 * @see com.cc.framework.common.SimpleEnumType2#getKey()
	 */
	public String getKey() {
		return Integer.toString(key);
	}

	/**
	 * @see com.cc.framework.common.SimpleEnumType2#getValue()
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Compares this check state to the specified object.
	 *
	 * @param	obj		The object to compare this <code>CheckState</code> against.
	 * @return  <code>true</code> if the <code>check states</code>are equal;
	 *          <code>false</code> otherwise.
	 */
	public boolean equals(Object obj) {

		if (obj instanceof CheckState) {
			return key == ((CheckState) obj).key;
		} else if (obj instanceof String) {
			int other = Integer.parseInt((String) obj);

			return key == other;
		} else  if (obj instanceof Integer) {
			int other = ((Integer) obj).intValue();

			return key == other;
		}

		return super.equals(obj);
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return key;
	}

	/**
	 * Compares this check state to the specified int.
	 *
	 * @param	other The int to compare this <code>CheckState</code> against.
	 * @return  <code>true</code> if the <code>check states</code>are equal;
	 *          <code>false</code> otherwise.
	 */
	public boolean equals(int other) {
		return key == other;
	}

	/**
	 * Creates for the argument an Object of type CheckState
	 *
	 * @param	i Integer to parse
	 * @return	SortOrder
	 */
	public static CheckState parseInt(int i) {

		for (int index = 0; index < ALL.length; index++) {
			if (ALL[index].key == i) {
				return ALL[index];
			}
		}

		throw new InvalidEnumType("Invalid CheckState: " + i);
	}

	/**
	 * Returns the key as an int
	 *
	 * @return The internal key
	 */
	public int toInt() {
		return key;
	}
}