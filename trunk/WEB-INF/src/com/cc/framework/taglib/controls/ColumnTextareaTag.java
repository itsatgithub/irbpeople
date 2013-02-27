/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/controls/ColumnTextareaTag.java,v 1.5 2005/07/08 14:17:10 P001002 Exp $
 * $Revision: 1.5 $
 * $Date: 2005/07/08 14:17:10 $
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

import com.cc.framework.ui.model.ColumnDesignModel;
import com.cc.framework.ui.model.ColumnTextareaDesignModel;
import com.cc.framework.ui.model.imp.ColumnTextareaDesignModelImp;

/**
 * Tag-Handler for the <code>columntextarea</code> Tag.
 * <p>
 * Generates a textarea column.
 * If the filter-attribute is set to false then, with
 * the help of the text column, any desired HTML-code can
 * be output in a line.
 * The <columntextarea>-tag may only be used within a Taghandler
 * which implements the com.cc.framework.taglib.controls.ColumnContainerTag
 * Interface. These are e.g. the <list>- or the <treelist>-tag.
 *
 * @author      <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version     $Revision: 1.5 $
 * @since       1.3
 */
public class ColumnTextareaTag extends ColumnBaseTag {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -1661731489825078140L;

	/**
	 * Constructor
	 */
	public ColumnTextareaTag() {
		super();
	}

	/**
	 * @see com.cc.framework.taglib.controls.ColumnBaseTag#doCreateDesignModel()
	 */
	public ColumnDesignModel doCreateDesignModel() {
		return new ColumnTextareaDesignModelImp();
	}

	/**
	 * Specifies the number of visible columns
	 *
	 * @param	cols	The column count
	 */
	public void setCols(String cols) {
		((ColumnTextareaDesignModel) getDesignModel()).setCols(cols);
	}

	/**
	 * Specifies the maximum number of visible rows
	 *
	 * @param	rows	The maximum number of visible rows
	 */
	public void setRows(String rows) {
		((ColumnTextareaDesignModel) getDesignModel()).setRows(rows);
	}

	/**
	 * The WRAP attribute can be used to specify how to
	 * handle word-wrapping display in text input areas in forms.
	 *
	 * @param	wrap Word Wrapping
	 */
	public void setWrap(String wrap) {
		((ColumnTextareaDesignModel) getDesignModel()).setWrap(wrap);
	}
}