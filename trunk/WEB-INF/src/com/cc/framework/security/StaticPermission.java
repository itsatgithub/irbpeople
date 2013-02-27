/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/security/StaticPermission.java,v 1.15 2005/07/08 14:14:03 P001002 Exp $
 * $Revision: 1.15 $
 * $Date: 2005/07/08 14:14:03 $
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

package com.cc.framework.security;

import java.util.List;

/**
 * This class represents a static permission (true or false)
 *
 * @author    <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version   $Revision: 1.15 $
 * @since      1.0
 */
public final class StaticPermission extends Permission {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -8823145545183046793L;

	/** static permission: NONE */
	public static final Permission NONE		= new StaticPermission(false);

	/** static permission: GRANTED */
	public static final Permission GRANTED	= new StaticPermission(true);

	/**
	 * the static permission
	 */
	private boolean permission = false;

	/**
	 * Constructor for StaticPermission
	 *
	 * @param	permission	the permission
	 */
	private StaticPermission(boolean permission) {
		this.permission = permission;
	}

	/**
	 * @see Permission#isGranted(Principal)
	 */
	public boolean isGranted(Principal principal) {
		return permission;
	}

	/**
	 * @see com.cc.framework.security.Permission#isGranted(Principal, List)
	 */
	public boolean isGranted(Principal principal, List notGranted) {

		boolean granted = isGranted(principal);

		if (!granted && (notGranted != null)) {
			notGranted.add(this);
		}

		return granted;
	}

	/**
	 * @see com.cc.framework.security.Permission#getDefaultMessage()
	 */
	protected String getDefaultMessage() {
		return permission ? "granted" : "not granted";
	}

	/**
	 * Returns a String representation of the permission
	 *
	 * @return	String	the String representation
	 */
	public String toString() {
		return permission ? "true" : "false";
	}
}