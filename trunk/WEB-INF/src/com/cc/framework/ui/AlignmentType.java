/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/AlignmentType.java,v 1.18 2005/07/08 14:16:49 P001002 Exp $
 * $Revision: 1.18 $
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
 * Defines alignment types used in the Common Controls Framework
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.18 $
 * @since      1.0
 */
public final class AlignmentType implements SimpleEnumType, Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -8112982595186035928L;

	/** alignment type: left */
	public static final AlignmentType LEFT		= new AlignmentType("left");

	/** alignment type: right */
	public static final AlignmentType RIGHT		= new AlignmentType("right");

	/** alignment type: center */
	public static final AlignmentType CENTER	= new AlignmentType("center");

	/** alignment type: top */
	public static final AlignmentType TOP		= new AlignmentType("top");

	/** alignment type: texttop */
	public static final AlignmentType TEXTTOP	= new AlignmentType("texttop");

	/** alignment type: bottom */
	public static final AlignmentType BOTTOM	= new AlignmentType("bottom");

	/** alignment type: baseline */
	public static final AlignmentType BASELINE	= new AlignmentType("baseline");

	/** alignment type: absmiddle */
	public static final AlignmentType ABSMIDDLE	= new AlignmentType("absmiddle");

	/** alignment type: middle */
	public static final AlignmentType MIDDLE	= new AlignmentType("middle");

	/**
	 * The internal type
	 */
	private String type;

	/**
	 * Collection with all kinds of alignment types
	 */
	private static final AlignmentType[] ALL =
		{
			LEFT,
			RIGHT,
			CENTER,
			TOP,
			BOTTOM,
			ABSMIDDLE,
			MIDDLE,
			TEXTTOP,
			BASELINE };

	// ----------------------------------
	//           methods
	// ----------------------------------

	/**
	 * Constructor for AlignmentType
	 *
	 * @param	type	The alignment type to initialize the Object
	 */
	private AlignmentType(String type) {
		this.type	= type;
	}

	/**
	 * Returns the alignment type
	 *
	 * @return	The alignment type
	 */
	public String toString() {
		return type;
	}

	/**
	 * Compares this type to the specified object.
	 *
	 * @param	obj The object to compare this <code>AlignmentType</code>
	 * 			object against.
	 * @return  <code>true</code> if the internal types are equal;
	 *          <code>false</code> otherwise.
	 */
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		} else if (obj instanceof String) {
			return type.equals(obj);
		} else if (obj instanceof AlignmentType) {
			return type.equals(((AlignmentType) obj).type);
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
	 * Creates for the argument an object of type AlignmentType
	 *
	 * @param	code	String to parse
	 * @return	The new Object
	 */
	public static AlignmentType parse(String code) {

		for (int index = 0; index < ALL.length; index++) {
			if (ALL[index].equals(code)) {
				return ALL[index];
			}
		}

		throw new InvalidEnumType("Invalid AlignmentType: " + code);
	}
}