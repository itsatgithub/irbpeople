/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/security/Permission.java,v 1.13 2005/02/16 18:03:17 P001001 Exp $
 * $Revision: 1.13 $
 * $Date: 2005/02/16 18:03:17 $
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

import java.io.Serializable;
import java.util.List;

/**
 * Base class for all permissions
 * <p>
 * An implementation of a permission can be accessed with the
 * factory method parse().
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.13 $
 * @since      1.0
 */
public abstract class Permission implements Serializable {

	/**
	 * Additional message text which can be
	 * assigned to the permission
	 */
	private String message = null;

	/**
	 * Constructor for Permission
	 */
	public Permission() {
		super();
	}

	/**
	 * Constructor for Permission
	 *
	 * @param	message		A message assigned to the permission
	 */
	public Permission(String message) {
		super();

		this.message = message;
	}

	/**
	 * Creates for the argument an object of type Permission.
	 * <p>
	 * Legal values for an access protection:
	 * <ul>
	 *   <li>static permission: "true"|"false"</li>
	 *   <li>role based permission: "@"role name</li>
	 *   <li>function based permission: "$"function name</li>
	 * </ul>
	 * Different permissions can be specified in a list separated by ";"
	 *
	 * @param	permission		String to parse
	 * @return	Permission
	 * @throws	PermissionException	if the argument is not matched
	 */
	public static Permission parse(String permission) throws PermissionException {
		return CompositePermission.parse(permission);
	}

	/**
	 * Checks if the permission is granted.
	 *
	 * @param	principal	Principal
	 * @return	boolean
	 */
	public abstract boolean isGranted(Principal principal);

	/**
	 * Checks if the permission is granted.
	 * Not granted permission will be added to the specified list.
	 *
	 * @param	principal	Principal
	 * @param	notGranted	List with Permission
	 * @return	boolean
	 */
	public abstract boolean isGranted(Principal principal, List notGranted);

	/**
	 * If no message is assigned to the permisson this method
	 * returns a default message.
	 *
	 * @return	String
	 */
	protected abstract String getDefaultMessage();

	/**
	 * Returns the message assigned to the permission
	 *
	 * @return String
	 */
	public String getMessage() {
		if (message == null) {
			return getDefaultMessage();
		} else {
			return message;
		}
	}

	/**
	 * Sets the message
	 *
	 * @param string	Message
	 */
	public void setMessage(String string) {
		message = string;
	}
}