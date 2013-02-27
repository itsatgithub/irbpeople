/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/security/FunctionBasedPermission.java,v 1.14 2005/07/08 14:14:03 P001002 Exp $
 * $Revision: 1.14 $
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
 * This class represents a permission for a function in the application
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.14 $
 * @since      1.0
 */
public class FunctionBasedPermission extends Permission {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 8647590147100303745L;

	/**
	 * INDICATOR
	 */
	public static final String INDICATOR	= "$";

	/**
	 * Function
	 */
	private String function = null;


	/**
	 * Constructor for FunctionBasedPermission
	 *
	 * @param	function	the name of the function
	 */
	public FunctionBasedPermission(String function) {
		super();

		this.function = function;
	}

	/**
	 * Constructor for FunctionBasedPermission
	 *
	 * @param	function	the name of the function
	 * @param	message		a message assigned to the permission
	 */
	public FunctionBasedPermission(String function, String message) {
		super(message);

		this.function = function;
	}

	/**
	 * Returns the function name
	 *
	 * @return String
	 */
	public String getFunction() {
		return function;
	}

	/**
	 * Creates for the argument an Object of type Permission.
	 * <p>
	 * Legal Values:
	 *  <ul>
	 *   <li>ClassBasedRight....: class.method --> client.insert</li>
	 *   <li>InstanceBasedRight.: class:instance.method --> client:demo.insert</li>
	 * </ul>
	 *
	 * @param	permission	String to parse
	 * @return	Permission
	 * @throws	PermissionException	if the argument is not matched
	 */
	public static Permission parse(String permission) throws PermissionException {
		return new FunctionBasedPermission(permission);
	}

	/**
	 * @see com.cc.framework.security.Permission#isGranted(Principal)
	 */
	public boolean isGranted(Principal principal) {
		if (principal == null) {
			return false;
		}

		// authorisation check
		return principal.hasRight(function);
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
		return "User has no right to execute function: " + function;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return INDICATOR + function;
	}
}