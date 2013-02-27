/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/common/Severity.java,v 1.23 2005/07/08 14:13:42 P001002 Exp $
 * $Revision: 1.23 $
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
 * Defines severity types used by the common controls framework
 *
 * <br>Severity classes:
 * <ul>
 * <li>Information</li>
 * <li>Warning</li>
 * <li>Error</li>
 * <li>Fatal Error</li>
 * </ul>
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.23 $
 * @since      1.0
 */
public final class Severity implements SimpleEnumType, Serializable, Comparable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -3022617742790331697L;

	/** severity: NONE */
	public static final Severity NONE			= new Severity(0, "none", "ok");

	/** severity: QUESTION */
	public static final Severity QUESTION		= new Severity(1, "question", "question");

	/** severity: INFORMATION */
	public static final Severity INFORMATION	= new Severity(2, "information", "information");

	/** severity: WARNING */
	public static final Severity WARNING		= new Severity(3, "warning", "warning");

	/** severity: ERROR */
	public static final Severity ERROR			= new Severity(4, "error", "error");

	/** severity: FATAL */
	public static final Severity FATAL			= new Severity(5, "fatal", "fatal error");

	/**
	 * Category of the Error
	 */
	private int severity = 0;

	/**
	 * Identifier
	 */
	private String code;

	/**
	 * display value / the nice name
	 */
	private String name;

	/**
	 * The buffer with all kinds of severitys
	 */
	private static final Severity[]ALL = {NONE, QUESTION, INFORMATION, WARNING, ERROR, FATAL};


	// ----------------------------
	//      methods
	// ----------------------------

	/**
	 * Constructor
	 *
	 * @param	severity	Severity
	 * @param	code	Identifier
	 * @param	name	Display value
	 */
	private Severity(int severity, String code, String name) {
		super();

		this.severity	= severity;
		this.code		= code;
		this.name		= name;
	}

	/**
	 * Returns the Severity as an Integer Value
	 *
	 * @return		Integer Value
	 */
	public int toInt() {
		return severity;
	}

	/**
	 * Return the more significant Severity of two Objects
	 *
	 * @param	a	Severity Object A
	 * @param	b	Severity Object B
	 * @return	Severity
	 */
	public static Severity max(Severity a, Severity b) {

		if (a.severity > b.severity) {
			return a;
		} else {
			return b;
		}
	}

	/**
	 * Compares this type to the specified object.
	 *
	 * @param	obj The object to compare this <code>Severity</code>
	 * 			object against.
	 * @return  <code>true</code> if the internal types are equal;
	 *          <code>false</code> otherwise.
	 */
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		} else if (obj instanceof String) {
			return code.equals(obj);
		} else if (obj instanceof Severity) {
			return severity == ((Severity) obj).severity;
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
	 * Checks if the Errorclass has serverity ERROR or FATAL
	 *
	 * @return	boolean	True if the Errorclass has serverity ERROR or FATAL, false otherwise
	 */
	public boolean isError() {
		return ERROR.equals(this) || FATAL.equals(this);
	}

	/**
	 * Checks if the Errorclass has serverity WARNING
	 *
	 * @return	boolean	True if the Errorclass has serverity WARNING, false otherwise
	 */
	public boolean isWarning() {
		return WARNING.equals(this);
	}

	/**
	 * Checks if the Errorclass has serverity INFORMATION
	 *
	 * @return	boolean	True if the Errorclass has serverity INFORMATION, false otherwise
	 */
	public boolean isInformation() {
		return INFORMATION.equals(this);
	}

	/**
	 * Checks if the Errorclass has serverity QUESTION
	 *
	 * @return	boolean	True if the Errorclass has serverity QUESTION, false otherwise
	 */
	public boolean isQuestion() {
		return QUESTION.equals(this);
	}

	/**
	 * Returns the nice name for this serverity
	 *
	 * @return	String	The display name (nice name)
	 */
	public String niceName() {
		return name;
	}

	/**
	 * Creates for the argument an Object of type Severity
	 *
	 * @param	strSeverity	String to parse
	 * @return	Severity
	 */
	public static Severity parse(String strSeverity) {

		for (int index = 0; index < ALL.length; index++) {
			if (ALL[index].equals(strSeverity)) {
				return ALL[index];
			}
		}

		throw new InvalidEnumType("Invalid Severity: " + strSeverity);
	}

	/**
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o) {
		if (o instanceof Severity) {
			return severity - ((Severity) o).severity;
		}

		return 0;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return code;
	}
}