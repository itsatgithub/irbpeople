/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/util/JspDirective.java,v 1.13 2005/07/08 14:15:19 P001002 Exp $
 * $Revision: 1.13 $
 * $Date: 2005/07/08 14:15:19 $
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

package com.cc.framework.taglib.util;

import java.io.Serializable;

import com.cc.framework.common.InvalidEnumType;
import com.cc.framework.common.SimpleEnumType;

/**
 * Defines JSP directive types used in the common controls framework
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.13 $
 * @since      1.0
 */
public final class JspDirective implements SimpleEnumType, Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 9106106718184057771L;

	/** JSP directive: NONE */
	public static final JspDirective NONE		= new JspDirective("none");

	/** JSP directive: INCLUDES */
	public static final JspDirective INCLUDES	= new JspDirective("includes");

	/** JSP directive: ENDOFPAGE */
	public static final JspDirective ENDOFPAGE	= new JspDirective("endofpage");

	/**
	 * The internal type
	 */
	private String type;

	/**
	 * Collection of all JspDirective objects
	 */
	private static final JspDirective[] ALL = {INCLUDES, ENDOFPAGE};


	/**
	 * Constructor for JspDirective
	 *
	 * @param	type	The type
	 */
	private JspDirective(String type) {
		this.type	= type;
	}

	/**
	 * Returns the type
	 *
	 * @return	String
	 */
	public String toString() {
		return type;
	}

	/**
	 * Compares this type to the specified object.
	 *
	 * @param	obj The object to compare this <code>JspDirective</code>
	 * 			object against.
	 * @return  <code>true</code> if the internal types are equal;
	 *          <code>false</code> otherwise.
	 */
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		} else if (obj instanceof String) {
			return type.equals(obj);
		} else if (obj instanceof JspDirective) {
			return type.equals(((JspDirective) obj).type);
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
	 * Creates for the argument an Object of type JspDirective
	 *
	 * @param	code	String to parse
	 * @return	SortOrder
	 */
	public static JspDirective parse(String code) {

		for (int index = 0; index < ALL.length; index++) {
			if (ALL[index].equals(code)) {
				return ALL[index];
			}
		}

		throw new InvalidEnumType("Invalid JspDirective: " + code);
	}
}
