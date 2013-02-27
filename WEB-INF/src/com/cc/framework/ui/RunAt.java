/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/RunAt.java,v 1.13 2005/07/08 14:16:49 P001002 Exp $
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
 * The runat attribute tells a Control if it should work with
 * or without server roundtrips. So possible values are:
 * <ul>
 * 		<li>CLIENT = The control works without server roundtrips.</li>
 * 		<li>SERVER = The control works with server roundtrips.</li>
 * </ul>
 *
 * Important: At this time only the tabset control supports this feature.
 * In the next release the tree control will also support this feature.
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.13 $
 * @since      1.0
 */
public final class RunAt implements SimpleEnumType, Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 6487339090078836347L;

	/** RunAt: NONE */
	public static final RunAt DEFAULT = new RunAt("default");

	/** RunAt: SINGLE */
	public static final RunAt CLIENT = new RunAt("client");

	/** RunAt: MULTIPLE */
	public static final RunAt SERVER = new RunAt("server");

	/**
	 * The internal type
	 */
	private String type;

	/**
	 * Collection with all elements
	 */
	private static final RunAt[] ALL = {DEFAULT, CLIENT, SERVER};


	// ----------------------------
	//          methods
	//	----------------------------

	/**
	 * Constructor for RunAt
	 *
	 * @param	type	The type
	 */
	private RunAt(String type) {
		this.type = type;
	}

	/**
	 * Compares this type to the specified object.
	 *
	 * @param	obj The object to compare this <code>RunAt</code>
	 * 			object against.
     * @return  <code>true</code> if the internal types are equal;
     *          <code>false</code> otherwise.
	 */
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		} else if (obj instanceof String) {
			return type.equals(obj);
		} else if (obj instanceof RunAt) {
			return type.equals(((RunAt) obj).type);
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
	 * Creates for the argument an object of type RunAt
	 *
	 * @param	code	String to parse
	 * @return	The new Object
	 */
	public static RunAt parse(String code) {

		for (int index = 0; index < ALL.length; index++) {
			if (ALL[index].equals(code)) {
				return ALL[index];
			}
		}

		throw new InvalidEnumType("Invalid RunAt: " + code);
	}

	/**
	 * Returns a string representation for the object
	 *
	 * @return	The internal type
	 */
	public String toString() {
		return type;
	}

}