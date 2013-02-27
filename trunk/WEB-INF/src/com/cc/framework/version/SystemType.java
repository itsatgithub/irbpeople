/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/version/SystemType.java,v 1.14 2005/07/08 14:15:45 P001002 Exp $
 * $Revision: 1.14 $
 * $Date: 2005/07/08 14:15:45 $
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

package com.cc.framework.version;

import java.io.Serializable;

import com.cc.framework.common.InvalidEnumType;
import com.cc.framework.common.SimpleEnumType;

/**
 * Defines system types. A system type tells the user in which
 * mode (test, debug, production, develop, ...) the application runs.
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.14 $
 * @since      1.0
 */
public final class SystemType implements SimpleEnumType, Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -2379206702691943677L;

	/** system type: Field SYSTYPE_NONE */
	public static final SystemType SYSTYPE_NONE =
		new SystemType("none", "none");

	/** system type: SYSTYPE_PRODUCTION */
	public static final SystemType SYSTYPE_PRODUCTION =
		new SystemType("production", "Production System");

	/** system type: SYSTYPE_TEST */
	public static final SystemType SYSTYPE_TEST =
		new SystemType("test", "Test System");

	/** system type: SYSTYPE_DEBUG */
	public static final SystemType SYSTYPE_DEBUG =
		new SystemType("debug", "Debug System");

	/** system type: Field SYSTYPE_DEVELOP */
	public static final SystemType SYSTYPE_DEVELOP =
		new SystemType("develop", "Development System");

	/** system type: Field SYSTYPE_DEMO */
	public static final SystemType SYSTYPE_DEMO =
		new SystemType("demo", "Demo System");

	/**
	 * Code or internal key
	 */
	private String code;

	/**
	 * Display value
	 */
	private String name;

	/**
	 * Collection of all kindes of types
	 */
	private static final SystemType[] ALL =
		{
			SYSTYPE_NONE,
			SYSTYPE_PRODUCTION,
			SYSTYPE_TEST,
			SYSTYPE_DEBUG,
			SYSTYPE_DEVELOP,
			SYSTYPE_DEMO };

	/**
	 * Constructor for SystemType
	 *
	 * @param	code	The code or key
	 * @param	name	The display value
	 */
	private SystemType(String code, String name) {
		this.code = code;
		this.name = name;
	}

	/**
	 * Returns the display value
	 *
	 * @return	String
	 */
	public String getNiceName() {
		return name;
	}

	/**
	 * Returns true for a test system
	 *
	 * @return	boolean
	 */
	public boolean isTestSystem() {
		return SYSTYPE_TEST.equals(this);
	}

	/**
	 * Returns true for a production system
	 *
	 * @return	boolean
	 */
	public boolean isProductionSystem() {
		return SYSTYPE_PRODUCTION.equals(this);
	}

	/**
	 * Creates for the argument an Object of type SystemType
	 *
	 * @param	code	String to parse
	 * @return	SystemType
	 */
	public static SystemType parse(String code) {

		for (int index = 0; index < ALL.length; index++) {
			if (ALL[index].equals(code)) {
				return ALL[index];
			}
		}

		throw new InvalidEnumType("Invalid SystemType: " + code);
	}

	/**
	 * Returns the code
	 *
	 * @return	String
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Compares this type to the specified object.
	 *
	 * @param	obj The object to compare this <code>SystemType</code>
	 * 			object against.
	 * @return  <code>true</code> if the internal types are equal;
	 *          <code>false</code> otherwise.
	 */
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		} else if (obj instanceof String) {
			return code.equals(obj);
		} else if (obj instanceof SystemType) {
			return code.equals(((SystemType) obj).code);
		}

		return false;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return code.hashCode();
	}

	/**
	 * Returns the name as a string representation for the object
	 *
	 * @return	String
	 */
	public String toString() {
		return name;
	}
}
