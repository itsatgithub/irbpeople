/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/security/GrantedTag.java,v 1.7 2005/07/08 14:14:59 P001002 Exp $
 * $Revision: 1.7 $
 * $Date: 2005/07/08 14:14:59 $
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

package com.cc.framework.taglib.security;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.cc.framework.security.Permission;
import com.cc.framework.security.PermissionException;
import com.cc.framework.security.Principal;
import com.cc.framework.security.SecurityUtil;

/**
 * Tag-Handler for the <code>granted</code> Tag.
 * Processes the content of the tag-body if the registerd
 * Principal Objekt has the demanded permission.
 *
 * @author   <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version	 $Revision: 1.7 $
 * @since    1.0
 */
public class GrantedTag extends TagSupport {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -4647981391679243545L;

	/**
	 * The required Permission to execute the Tag Body
	 */
	private String acl;

	/**
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException {
		boolean includeBody = false;

		try {
			Permission permission	= Permission.parse(acl);
			Principal principal		= SecurityUtil.getPrincipal(pageContext.getRequest());

			if (principal == null) {
				// No Principal -> Security System is not active
				includeBody = true;
			} else {
				includeBody = doIncludeBody(principal, permission);
			}
		} catch (PermissionException pe) {
			pe.printStackTrace();
		}

		return includeBody ? EVAL_BODY_INCLUDE : SKIP_BODY;
	}

	/**
	 * This method checks for inclusion or exlusion of the Tag-Body
	 *
	 * @param		principal The Principal Object
	 * @param		permission The Permission
	 * @return		return <code>true</code> to include the Tag-Body
	 */
	protected boolean doIncludeBody(Principal principal, Permission permission) {
		return permission.isGranted(principal);
	}

	/**
	 * Sets the required permission for the Tag-Body
	 *
	 * @param		string Permission
	 */
	public void setPermission(String string) {
		this.acl = string;
	}
}