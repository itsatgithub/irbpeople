/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/security/NotGrantedTag.java,v 1.6 2005/07/08 14:14:59 P001002 Exp $
 * $Revision: 1.6 $
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

import com.cc.framework.security.Permission;
import com.cc.framework.security.Principal;

/**
 * Tag-Handler for the <code>notGranted</code> Tag.
 * Processes the content of the tag-body if the registerd
 * Principal Objekt has not(!) the demanded permission.
 *
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.6 $
 */
public class NotGrantedTag extends GrantedTag {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 4083329666169243542L;

	/**
	 * @see com.cc.framework.taglib.security.GrantedTag#doIncludeBody(com.cc.framework.security.Principal, com.cc.framework.security.Permission)
	 */
	protected boolean doIncludeBody(Principal principal, Permission permission) {
		return !super.doIncludeBody(principal, permission);
	}
}