/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/resource/Resource.java,v 1.10 2005/07/08 15:23:27 P001002 Exp $
 * $Revision: 1.10 $
 * $Date: 2005/07/08 15:23:27 $
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

import java.io.Serializable;

import com.cc.framework.ui.model.Cachable;

/**
 * Base class for resources which can be managed by the ResourceManager.
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.10 $
 * @since      1.0
 */
public abstract class Resource implements Serializable {

	/**
	 * The CachHint can be used to get Resources out of the Cache
	 * instead of generating them with each request.
	 * The CachHint is not used if inizalized with <code>null</code>.
	 */
	private String cacheHint = null;

	/**
	 * The Flag is true if the Resource ist stored
	 * in the Filesystem
	 */
	private boolean cacheOnDisk = false;

	/**
	 * Constructor for Resource
	 *
	 * @param cacheInfo	Cachable
	 */
	public Resource(Cachable cacheInfo) {

		if (cacheInfo != null) {
			this.cacheHint		= cacheInfo.getCacheHint();
			this.cacheOnDisk	= cacheInfo.cacheOnDisk();
		}
	}

	/**
	 * Checks if the Resource is up to date or must be
	 * new created.
	 * The decision is been made by the  ChachHint.
	 *
	 * @param cacheInfo	Cachable
	 * @return	boolean
	 */
	public final boolean isUpToDate(Cachable cacheInfo) {
		if ((cacheHint == null) || (cacheInfo == null)) {
			return false;
		}

		return cacheHint.equals(cacheInfo.getCacheHint());
	}

	/**
	 * @return Returns the cacheOnDisk.
	 */
	public boolean isCacheOnDisk() {
		return cacheOnDisk;
	}
}