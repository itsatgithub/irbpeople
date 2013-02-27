/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/security/RoleBasedPermission.java,v 1.13 2005/07/08 14:14:03 P001002 Exp $
 * $Revision: 1.13 $
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
 * This class represents a role based permission
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.13 $
 * @since      1.0
 */
public class RoleBasedPermission extends Permission {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -1700272051726573528L;

	/**
	 * INDICATOR
	 */
	public static final String INDICATOR	= "#";

	/**
	 * The role
	 */
	private String role = null;


	/**
	 * Constructor for RoleBasedPermission
	 *
	 * @param	role	The role
	 */
	public RoleBasedPermission(String role) {
		super();

		this.role = role;
	}

	/**
	 * Constructor for RoleBasedPermission
	 *
	 * @param	role		The role
	 * @param	message		A message assigned to the permission
	 */
	public RoleBasedPermission(String role, String message) {
		super(message);

		this.role = role;
	}

	/**
	 * Creates for the argument (role name) an Object of type Permission.
	 *
	 * @param	role	String to parse
	 * @return	Permission
	 * @throws	PermissionException if the argument is not matched
	 * @see com.cc.framework.security.Permission#parse(String)
	 */
	public static Permission parse(String role) throws PermissionException {
		return new RoleBasedPermission(role);
	}

	/**
	 * @see com.cc.framework.security.Permission#isGranted(Principal)
	 */
	public boolean isGranted(Principal principal) {
		if (principal == null) {
			return false;
		}

		return principal.isInRole(role);
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
		return "User is not in role: " + role;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return INDICATOR + role;
	}
}