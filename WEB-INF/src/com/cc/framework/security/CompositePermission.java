/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/security/CompositePermission.java,v 1.17 2005/07/08 14:14:02 P001002 Exp $
 * $Revision: 1.17 $
 * $Date: 2005/07/08 14:14:02 $
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
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * Container for permissions (access control list)
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.17 $
 * @since      1.0
 */
public class CompositePermission extends Permission {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 3213515329218142301L;

	/**
	 * The buffer into which the permissions are stored.
	 */
	private List permissions = new Vector();

	/**
	 * User needs all permission
	 */
	private boolean requiresAll = false;

	/**
	 * Constructor for CompositePermission
	 */
	public CompositePermission() {
		super();
	}

	/**
	 * Constructor for CompositePermission
	 *
	 * @param		requiresAll User neds all permission
	 */
	public CompositePermission(boolean requiresAll) {
		super();

		this.requiresAll = requiresAll;
	}

	/**
	 * Constructor for CompositePermission
	 *
	 * @param	permissions		Vector with permissions
	 */
	public CompositePermission(List permissions) {
		super();

		this.permissions.addAll(permissions);
	}

	/**
	 * Constructor for CompositePermission
	 *
	 * @param	permissions		Vector with permissions
	 * @param	message			A message assigned to the permission
	 */
	public CompositePermission(List permissions, String message) {
		super(message);

		this.permissions.addAll(permissions);
	}

	/**
	 * Creates for the argument an Object of type Permission.
	 * <p>
	 * Legal Values for an access protection:
	 * <ul>
	 *   <li>static permission: "true" | "false"</li>
	 *   <li>role based permission: "@"role name</li>
	 *   <li>function based permission: "$"function name</li>
	 * </ul>
	 * Different permissions can be specified in a list separated by ";"
	 *
	 * @param	permissionList		String to parse
	 * @return	Permission
	 * @throws	PermissionException	if the argument is not matched
	 */
	public static Permission parse(String permissionList) throws PermissionException {

		if (permissionList == null) {
			return StaticPermission.GRANTED;
		}

		StringTokenizer st = new StringTokenizer(permissionList, ";");

		// Collect all permission in the list
		List list = new Vector();

		while (st.hasMoreTokens()) {
			String permissionToken = st.nextToken();

			Permission permission = null;

			if ("true".equalsIgnoreCase(permissionToken)) {
				permission = StaticPermission.GRANTED;
			} else if ("false".equalsIgnoreCase(permissionToken)) {
				permission = StaticPermission.NONE;
			} else if (permissionToken.startsWith(FunctionBasedPermission.INDICATOR)) {
				permission = FunctionBasedPermission.parse(permissionToken.substring(1));
			} else if (permissionToken.startsWith(RoleBasedPermission.INDICATOR)) {
				permission = RoleBasedPermission.parse(permissionToken.substring(1));
			} else {
				// unknown permission
				throw new PermissionException("Invalid Permission Format: " + permissionToken);
			}

			// store the new permission object in the list
			list.add(permission);
		}

		// if the list only contains one item. A composit must
		// not be created
		if (list.size() == 1) {
			return (Permission) list.get(0);
		} else {
			return new CompositePermission(list);
		}
	}

	/**
	 * Appends the specified permission to this list.
	 *
	 * @param	permission	The permission to be appended to this list.
	 * @return	Returns the Composite for nested calls
	 */
	public CompositePermission add(Permission permission) {
		permissions.add(permission);

		return this;
	}

	/**
	 * @see com.cc.framework.security.Permission#isGranted(Principal)
	 */
	public boolean isGranted(Principal principal) {
		return isGranted(principal, null);
	}

	/**
	 * @see com.cc.framework.security.Permission#isGranted(Principal, List)
	 */
	public boolean isGranted(Principal principal, List notGranted) {
		boolean grantedAll = true;

		// Check if a permission can be granted
		for (int i = 0; i < permissions.size(); i++) {
			Permission permission = (Permission) permissions.get(i);

			boolean granted = permission.isGranted(principal, notGranted);

			if (granted && !requiresAll) {
				return true;
			}

			grantedAll &= granted;
		}

		return grantedAll;
	}

	/**
	 * @see com.cc.framework.security.Permission#getDefaultMessage()
	 */
	protected String getDefaultMessage() {
		return "permission not granted";
	}

	/**
	 * Returns a String representation of the list.
	 * Shows all permissions
	 *
	 * @return String
	 */
	public String toString() {
		StringBuffer buf = new StringBuffer();

		for (int i = 0; i < permissions.size(); i++) {

			if (i > 0) {
				buf.append(";");
			}

			buf.append(permissions.get(i));
		}

		return buf.toString();
	}
}