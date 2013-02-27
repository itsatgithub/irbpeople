/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/MenuStateType.java,v 1.13 2005/07/08 14:16:48 P001002 Exp $
 * $Revision: 1.13 $
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
 * Enumeration for the states of menu items
 *
 * @author     <a href="mailto:gschulz@scc-gmbh.com">Gernot Schulz</a>
 * @version    $Revision: 1.13 $
 * @since      1.0
 */
public final class MenuStateType implements SimpleEnumType, Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -8365194093686431059L;

	/** state: ENABLED */
	public static  final MenuStateType ENABLED	= new MenuStateType("enabled");

	/**  state: DISABLED */
	public static final MenuStateType DISABLED	= new MenuStateType("disabled");

	/**  state: CHECKED */
	public static final MenuStateType CHECKED	= new MenuStateType("checked");

	/**  state: UNCHECKED */
	public static final MenuStateType UNCHECKED	= new MenuStateType("unchecked");

	/**  state: VISIBLE */
	public static final MenuStateType VISIBLE	= new MenuStateType("visible");

	/**  state: INVISIBLE */
	public static final MenuStateType INVISIBLE	= new MenuStateType("invisible");

	/**
	 * The internal typ
	 */
	private String type;

	/**
	 * Collection of all elements (menu states)
	 */
	private static final MenuStateType[] ALL = {ENABLED, DISABLED, CHECKED, UNCHECKED, VISIBLE, INVISIBLE};

	// ----------------------------
	//          methods
	//	----------------------------

	/**
	 * Constructor for MenuStateType
	 *
	 * @param	type	The menu state
	 */
	public MenuStateType(String type) {

		super();

		this.type	= type;
	}

	/**
	 * Compares this type to the specified object.
	 *
	 * @param	obj The object to compare this <code>MenuStateType</code>
	 * 			object against.
	 * @return  <code>true</code> if the internal types are equal;
	 *          <code>false</code> otherwise.
	 */
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		} else if (obj instanceof String) {
			return type.equals(obj);
		} else if (obj instanceof MenuStateType) {
			return type.equals(((MenuStateType) obj).type);
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
	 * Creates for the argument an object of type MenuStateType
	 *
	 * @param	code	String to parse
	 * @return	The new Object
	 */
	public static MenuStateType parse(String code) {

		for (int index = 0; index < ALL.length; index++) {
			if (ALL[index].equals(code)) {
				return ALL[index];
			}
		}

		throw new InvalidEnumType("Invalid MenuStateType: " + code);
	}

	/**
	 * Returns a string representation for the object
	 *
	 * @return	The menu state
	 */
	public String toString() {
		return type;
	}

}

