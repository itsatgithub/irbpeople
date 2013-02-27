/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/control/ControlButton.java,v 1.16 2005/11/26 13:58:13 P001002 Exp $
 * $Revision: 1.16 $
 * $Date: 2005/11/26 13:58:13 $
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

package com.cc.framework.ui.control;

import java.io.Serializable;

/**
 * Constants for buttons used by controls
 * 
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version $Revision: 1.16 $
 * @since 1.0
 */
public class ControlButton implements Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -7605850546703560721L;

	/**
	 * PAGE Button
	 */
	public static final ControlButton PAGE = new ControlButton("page");

	/**
	 * CREATE Button
	 */
	public static final ControlButton CREATE = new ControlButton("create");

	/**
	 * REFRESH Button
	 */
	public static final ControlButton REFRESH = new ControlButton("refresh");

	/**
	 * FIRSTPAGE Button
	 */
	public static final ControlButton FIRSTPAGE = new ControlButton("first page");

	/**
	 * PREVPAGE Button
	 */
	public static final ControlButton PREVPAGE = new ControlButton("previous page");

	/**
	 * NEXTPAGE Button
	 */
	public static final ControlButton NEXTPAGE = new ControlButton("next page");

	/**
	 * LASTPAGE Button
	 */
	public static final ControlButton LASTPAGE = new ControlButton("last page");

	/**
	 * ADD Button
	 */
	public static final ControlButton ADD = new ControlButton("add");

	/**
	 * ADDALL Button
	 */
	public static final ControlButton ADDALL = new ControlButton("add all");

	/**
	 * REMOVE Button
	 */
	public static final ControlButton REMOVE = new ControlButton("remove");

	/**
	 * REMOVEALL Button
	 */
	public static final ControlButton REMOVEALL = new ControlButton(
			"remove all");

	/**
	 * HELP Button
	 */
	public static final ControlButton HELP = new ControlButton("help");

	/**
	 * Minimize Button
	 */
	public static final ControlButton MINIMIZE = new ControlButton("minimize");

	/**
	 * Maximize Button
	 */
	public static final ControlButton MAXIMIZE = new ControlButton("maximize");

	/**
	 * Restore Button
	 */
	public static final ControlButton RESTORE = new ControlButton("restore");

	/**
	 * Close Button
	 */
	public static final ControlButton CLOSE = new ControlButton("close");

	/**
	 * Close Button
	 */
	public static final ControlButton VIEW = new ControlButton("view");

	/**
	 * Roll Button
	 */
	public static final ControlButton ROLL = new ControlButton("roll");

	/**
	 * AddAppointment Button
	 */
	public static final ControlButton ADDAPPOINTMENT = new ControlButton("add Appointment");

	/**
	 * EXPORT Button
	 */
	public static final ControlButton EXPORTLIST	= new ControlButton("export List");

	/**
	 * PRINT Button
	 */
	public static final ControlButton PRINTLIST	= new ControlButton("print List");

	/**
	 * Button Name
	 */
	private String name;

	/**
	 * Constructor of ControlButton
	 * 
	 * @param name
	 *            Button name
	 */
	protected ControlButton(String name) {
		super();

		this.name = name;
	}

	/**
	 * Compares this type to the specified object.
	 * 
	 * @param obj
	 *            The object to compare this <code>ControlButton</code> object
	 *            against.
	 * @return <code>true</code> if the internal types are equal;
	 *         <code>false</code> otherwise.
	 */
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		} else if (obj instanceof String) {
			return name.equals(obj);
		} else if (obj instanceof ControlButton) {
			return name.equals(((ControlButton) obj).name);
		}

		return false;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return name;
	}
}
