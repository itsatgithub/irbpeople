/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/http/AttributeAccess.java,v 1.8 2005/02/16 18:03:10 P001001 Exp $
 * $Revision: 1.8 $
 * $Date: 2005/02/16 18:03:10 $
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

package com.cc.framework.http;

import java.util.Enumeration;

/**
 * Interface for classes which provide access to named
 * attributes.
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.8 $
 * @since      1.0
 */
public interface AttributeAccess {

	/**
	 * Returns the value of the named attribute as an object, or
	 * <code>null</code> if no attribute of the given name exists.
	 *
	 * @param		name	A String specifying the name of the attribute.
	 * @return		an Object containing the value of the attribute, or
	 * 				<code>null</code> if the attribute does not exist
	 */
	Object getAttribute(String name);

	/**
	 * Returns an Enumeration containing the names of the attributes
	 * available to this request. This method returns an empty Enumeration
	 * if the request has no attributes available to it.
	 *
	 * @return		an Enumeration of strings containing the names of the
	 * 				request's attributes
	 */
	Enumeration getAttributeNames();

	/**
	 * Stores an attribute in this request. Attributes are reset between
	 * requests.
	 *
	 * @param	name	A String specifying the name of the attribute
	 * @param	obj		The Object to be stored
	 */
	void setAttribute(String name, Object obj);

	/**
	 * Removes an attribute from this request. This method is not generally
	 * needed as attributes only persist as long as the request is being handled.
	 * Attribute names should follow the same conventions as package names.
	 *
	 * @param		name a String specifying the name of the attribute to remove
	 */
	void removeAttribute(String name);

	/**
	 * Clears all known names/values.
	 */
	void clearAttributes();
}