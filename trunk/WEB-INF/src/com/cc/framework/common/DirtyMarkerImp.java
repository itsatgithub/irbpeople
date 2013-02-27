/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/common/DirtyMarkerImp.java,v 1.2 2005/02/16 18:03:04 P001001 Exp $
 * $Revision: 1.2 $
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
 * Simple Implementation of the DirtyMarker interface
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.2 $
 */
public class DirtyMarkerImp implements DirtyMarker {

	/**
	 * State of the object
	 */
	private int flags = FLAG_NEW;

	/**
	 * Constructor
	 */
	public DirtyMarkerImp() {
		super();
	}

	// ==================================
	// Status Verwaltung
	// ==================================

	/**
	 * @see com.cc.framework.common.DirtyMarker#isDeleted()
	 */
	public final boolean isDeleted() {
		return ((flags & FLAG_DELETED) > 0);
	}

	/**
	 * @see com.cc.framework.common.DirtyMarker#isDirty()
	 */
	public final boolean isDirty() {
		return ((flags & FLAG_DIRTY) > 0);
	}

	/**
	 * @see com.cc.framework.common.DirtyMarker#isNew()
	 */
	public final boolean isNew() {
		return ((flags & FLAG_NEW) > 0);
	}

	/**
	 * @see com.cc.framework.common.DirtyMarker#setDeleted()
	 */
	public final void setDeleted() {
		flags |= FLAG_DELETED;
	}

	/**
	 * @see com.cc.framework.common.DirtyMarker#setDirty()
	 */
	public final void setDirty() {
		flags |= FLAG_DIRTY;
	}

	/**
	 * @see com.cc.framework.common.DirtyMarker#setNew()
	 */
	public final void setNew() {
		flags |= FLAG_NEW;
	}

	/**
	 * @see com.cc.framework.common.DirtyMarker#resetDirty()
	 */
	public final void resetDirty() {
		flags &= ~FLAG_DIRTY;
	}

	/**
	 * @see com.cc.framework.common.DirtyMarker#resetNew()
	 */
	public final void resetNew() {
		flags &= ~FLAG_NEW;
	}

	/**
	 * @see com.cc.framework.common.DirtyMarker#resetDeleted()
	 */
	public final void resetDeleted() {
		flags &= ~FLAG_DELETED;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuffer buf = new StringBuffer(super.toString());

		if (isNew()) {
			buf.append(" NEW ");
		}

		if (isDeleted()) {
			buf.append(" DELETED ");
		}

		if (isDirty()) {
			buf.append(" DIRTY ");
		}

		return buf.toString();
	}
}
