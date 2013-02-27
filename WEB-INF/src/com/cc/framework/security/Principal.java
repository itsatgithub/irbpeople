/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/security/Principal.java,v 1.11 2005/02/16 18:03:17 P001001 Exp $
 * $Revision: 1.11 $
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

/**
 * This interface is implemented by an object, whose access should be protected.
 * After that, the object must be registered using the SecurityUtil.registerPrincipal() methode.
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.11 $
 * @since      1.0
 */
public interface Principal {

	/**
	 * Checks if the object has the specified permission.
	 *
	 * @param	role	The symbolic name for the role to check.
	 * @return	boolean
	 */
	public boolean isInRole(String role);

	/**
	 * Checks if the object has the specified right.
	 *
	 * @param	right	 The symbolic name for the right to check.
	 * @return	boolean
	 */
	public boolean hasRight(String right);
}