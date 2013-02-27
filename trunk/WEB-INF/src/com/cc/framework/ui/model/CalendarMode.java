/*
 * $Header$
 * $Revision$
 * $Date$
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

package com.cc.framework.ui.model;

import java.io.Serializable;

import com.cc.framework.common.InvalidEnumType;
import com.cc.framework.common.SimpleEnumType;

/**
 * Enumeration for Calendar Modes
 * 
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version $Revision$
 */
public final class CalendarMode implements SimpleEnumType, Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -2304193556527075483L;

	/** ButtonType: popup calendar */
	public static final CalendarMode POPUP = new CalendarMode("popup");

	/** ButtonType: inline calendar */
	public static final CalendarMode INLINE = new CalendarMode("inline");

	/** ButtonType: split into seperate fields */
	public static final CalendarMode FIELDS = new CalendarMode("fields");

	/**
	 * Internal Mode Identifier
	 */
	private String mode;

	/**
	 * Collection with all Elemenets
	 */
	private static final CalendarMode[] ALL = { POPUP, INLINE, FIELDS };

	/**
	 * Constructor
	 * 
	 * @param mode
	 *            Calendar Mode
	 */
	private CalendarMode(String mode) {
		this.mode = mode;
	}

	/**
	 * Returns the mode
	 * 
	 * @return String
	 */
	public String toString() {
		return mode;
	}

	/**
	 * Compares this type to the specified object.
	 * 
	 * @param obj
	 *            The object to compare this <code>CalendarMode</code> object
	 *            against.
	 * @return <code>true</code> if the internal types are equal;
	 *         <code>false</code> otherwise.
	 */
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		} else if (obj instanceof String) {
			return mode.equals(obj);
		} else if (obj instanceof CalendarMode) {
			return mode.equals(((CalendarMode) obj).mode);
		}

		return false;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return mode.hashCode();
	}

	/**
	 * Creates for the argument an Object of Type CalendarMode
	 * 
	 * @param code
	 *            String to parse
	 * @return CalendarMode
	 */
	public static CalendarMode parse(String code) {
		for (int index = 0; index < ALL.length; index++) {
			if (ALL[index].equals(code)) {
				return ALL[index];
			}
		}

		throw new InvalidEnumType("Invalid CalendarMode: " + code);
	}
}