/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/MenuType.java,v 1.13 2005/07/08 14:16:49 P001002 Exp $
 * $Revision: 1.13 $
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
 * Enumeration of menu types used by the framework
 *
 * @author	   <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version	   $Revision: 1.13 $
 * @since      1.0
 */
public final class MenuType implements SimpleEnumType, Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -2984213105300734324L;

	/** type: NONE */
	public static final MenuType NONE		= new MenuType("none");

	/** type: MAIN */
	public static final MenuType MAIN		= new MenuType("main");

	/** type: TOOLS */
	public static final MenuType TOOLS		= new MenuType("tools");

	/** type: SIDEBAR */
	public static final MenuType SIDEBAR	= new MenuType("sidebar");

	/**
	 * The internal type
	 */
	private String type;

	/**
	 * Collection with all menue types
	 */
	private static final MenuType[] ALL = {MAIN, TOOLS, SIDEBAR};


	// ----------------------------
	//          methods
	//	----------------------------

	/**
	 * Constructor for MenuType
	 *
	 * @param	type	The menu type
	 */
	private MenuType(String type) {
		this.type	= type;
	}

	/**
	 * Compares this type to the specified object.
	 *
	 * @param	obj The object to compare this <code>MenuType</code>
	 * 			object against.
	 * @return  <code>true</code> if the internal types are equal;
	 *          <code>false</code> otherwise.
	 */
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		} else if (obj instanceof String) {
			return type.equals(obj);
		} else if (obj instanceof MenuType) {
			return type.equals(((MenuType) obj).type);
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
	 * Creates for the argument an object of type MenuType
	 *
	 * @param	code	String to parse
	 * @return	The new Object
	 */
	public static MenuType parse(String code) {

		for (int index = 0; index < ALL.length; index++) {
			if (ALL[index].equals(code)) {
				return ALL[index];
			}
		}

		throw new InvalidEnumType("Invalid MenuType: " + code);
	}

	/**
	 * Returns a string representation for the object
	 *
	 * @return	The menu type
	 */
	public String toString() {
		return type;
	}

}