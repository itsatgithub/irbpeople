/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/common/DirtyMarker.java,v 1.10 2005/02/16 18:03:04 P001001 Exp $
 * $Revision: 1.10 $
 * $Date: 2005/02/16 18:03:04 $
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

package com.cc.framework.common;

/**
 * Interface for objects to manage a dirty state
 *
 * @author	   <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version	   $Revision: 1.10 $
 * @since      1.0
 */
public interface DirtyMarker {

	/** indicates that the business object is new */
	public static final int FLAG_NEW		= 0x0001;

	/** indicates that the business object was deleted */
	public static final int FLAG_DELETED	= 0x0002;

	/** indicates that the business object is dirty and has unsaved changes */
	public static final int FLAG_DIRTY		= 0x0004;

	/**
	 * Returns if an object is dirty and has unsaved changes.
	 *
     * @return  <code>true</code> if the object is dirty;
     *          <code>false</code> otherwise.
	 */
	public boolean isDirty();

	/**
	 * Sets the dirty flag
	 */
	public void setDirty();

	/**
	 * Clears the dirty flag
	 */
	public void resetDirty();

	/**
	 * Returns if the object is new
	 *
     * @return  <code>true</code> if the object is new;
     *          <code>false</code> otherwise.
	 */
	public boolean isNew();

	/**
	 * Sets the new flag
	 */
	public void setNew();

	/**
	 * Clears the new flag
	 */
	public void resetNew();

	/**
	 * Returns if the object is deleted
	 *
     * @return  <code>true</code> if the object is deleted;
     *          <code>false</code> otherwise.
	 */
	public boolean isDeleted();

	/**
	 * Sets the deleted flag
	 */
	public void setDeleted();

	/**
	 * Clears the deleted flag
	 */
	public void resetDeleted();
}