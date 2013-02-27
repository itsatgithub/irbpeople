/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/imp/ColumnTextareaDesignModelImp.java,v 1.6 2005/07/08 14:19:42 P001002 Exp $
 * $Revision: 1.6 $
 * $Date: 2005/07/08 14:19:42 $
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

package com.cc.framework.ui.model.imp;

import com.cc.framework.ui.model.ColumnTextareaDesignModel;
import com.cc.framework.ui.model.value.DeferredValue;

/**
 *
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.6 $
 */
public class ColumnTextareaDesignModelImp extends ColumnDesignModelImp implements ColumnTextareaDesignModel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 4033550283208747408L;

	/**
	 * Number for character rows
	 */
	private DeferredValue rows = DeferredValue.NEG;

	/**
	 * Number ofcharacter columns
	 */
	private DeferredValue cols = DeferredValue.NEG;

	/**
	 * The WRAP attribute can be used to specify how to
	 * handle word-wrapping display in text input areas in forms.
	 */
	private DeferredValue wrap = null;

	/**
	 * @see com.cc.framework.ui.model.ColumnTextareaDesignModel#getCols()
	 */
	public int getCols() {
		return cols.toInt(getEnvironment());
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnTextareaDesignModel#setCols(int)
	 */
	public void setCols(int cols) {
		this.cols = new DeferredValue(cols);
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnTextareaDesignModel#setCols(java.lang.String)
	 */
	public void setCols(String cols) {
		this.cols = DeferredValue.parseInt(cols);
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnTextareaDesignModel#getRows()
	 */
	public int getRows() {
		return rows.toInt(getEnvironment());
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnTextareaDesignModel#setRows(int)
	 */
	public void setRows(int rows) {
		this.rows = new DeferredValue(rows);
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnTextareaDesignModel#setRows(java.lang.String)
	 */
	public void setRows(String rows) {
		this.rows = DeferredValue.parseInt(rows);
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnTextareaDesignModel#getWrap()
	 */
	public String getWrap() {
		return DeferredValue.toString(wrap, getEnvironment());
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnTextareaDesignModel#setWrap(java.lang.String)
	 */
	public void setWrap(String wrap) {
		this.wrap = new DeferredValue(wrap);
	}
}
