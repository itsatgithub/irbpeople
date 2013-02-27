/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/Cachable.java,v 1.9 2005/02/16 18:03:22 P001001 Exp $
 * $Revision: 1.9 $
 * $Date: 2005/02/16 18:03:22 $
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

package com.cc.framework.ui.model;

/**
 * Implemented by controls which uses a caching mechanism
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.9 $
 * @since      1.0
 */
public interface Cachable {

	/**
	 * The class which implement this interface can singal
	 * the some changes at the datamodel will be done.
	 *
	 * Two datamodels are compared as equal, if a call to
	 * this metod in both cases returns <code>true</true>.
	 * If one did not, the datamodels are different.
	 *
	 * If <code>null</code> is returned, then a caching
	 * will not be used.
	 *
	 * @return	String
	 */
	public String getCacheHint();

	/**
	 * This Method checks if a resource is cached in memory
	 * or on the disk (file system)
	 *
	 * @return		<code>true</code> if the object can be cached on the disk (file system);
	 * 				<code>false</code> otherwise.
	 */
	public boolean cacheOnDisk();
}