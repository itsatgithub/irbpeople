/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/resource/ObjectResource.java,v 1.10 2005/07/08 14:13:56 P001002 Exp $
 * $Revision: 1.10 $
 * $Date: 2005/07/08 14:13:56 $
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

package com.cc.framework.resource;

import com.cc.framework.ui.model.Cachable;

/**
 * This class represent a web resource
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.10 $
 * @since      1.0
 */
public class ObjectResource extends Resource {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 8592458441301108493L;

	/**
	 * The objekt resource
	 */
	private Object object = null;

	/**
	 * Constructor for ObjectResource
	 *
	 * @param	object		the object
	 * @param	cacheInfo	information about the caching mechanism
	 */
	public ObjectResource(Object object, Cachable cacheInfo) {
		super(cacheInfo);

		this.object = object;
	}

	/**
	 * Returns the object
	 *
	 * @return the object
	 */
	public Object getObject() {
		return object;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		if (object == null) {
			return "null";
		} else {
			return object.getClass().getName();
		}
	}
}
