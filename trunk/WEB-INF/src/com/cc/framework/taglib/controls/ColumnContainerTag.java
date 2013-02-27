/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/controls/ColumnContainerTag.java,v 1.10 2005/02/16 18:03:06 P001001 Exp $
 * $Revision: 1.10 $
 * $Date: 2005/02/16 18:03:06 $
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

package com.cc.framework.taglib.controls;

import com.cc.framework.ui.control.LineIterator;
import com.cc.framework.ui.model.ColumnDesignModel;
import com.cc.framework.ui.painter.PainterContext;

/**
 * Tag-Handler to include Columns
 *
 * @author      <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version     $Revision: 1.10 $
 * @since       1.0
 */
public interface ColumnContainerTag {

	/**
	 * Adds a column to the Collection
	 * @param		column	ColumnDesignModel
	 */
	public void addColumn(ColumnDesignModel column);

	/**
	 * Creates a lne interator which starts with the first
	 * line on the current page.
	 *
	 * @return	LineIterator
	 */
	public LineIterator getLineIterator();

	/**
	 * Returns the number of rows on the page. Returns -1 if
	 * all rows schould be displayed.
	 *
	 * @return	The number of rows on the page
	 */
	public int getRowsPerPage();

	/**
	 * Returns the painter context of the nesting control
	 * painter (-> the context of a list painter)
	 *
	 * @return		Nesting painter context
	 */
	public PainterContext getPainterContext();
}
