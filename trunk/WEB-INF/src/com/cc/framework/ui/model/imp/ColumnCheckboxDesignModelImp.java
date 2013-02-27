/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/imp/ColumnCheckboxDesignModelImp.java,v 1.7 2005/07/08 14:19:42 P001002 Exp $
 * $Revision: 1.7 $
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

import com.cc.framework.ui.AlignmentType;
import com.cc.framework.ui.SelectMode;
import com.cc.framework.ui.model.ColumnCheckboxDesignModel;
import com.cc.framework.ui.model.InputFieldType;

/**
 * Designmodel for the ColumnCheckboxColumn
 *
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.7 $
 * @since       1.3
 */
public class ColumnCheckboxDesignModelImp
	extends ColumnTextDesignModelImp
	implements ColumnCheckboxDesignModel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 7278503192259051291L;

	/**
	 * The selection mode for optional checkboxes.
	 */
	private SelectMode selectMode = SelectMode.MULTIPLE;

	/**
	 * Indicates if the multiple check option of the column is
	 * enabled
	 */
	private boolean checkAll = false;

	/**
	 * Constructor for ColumnEditDesignModelImp
	 */
	public ColumnCheckboxDesignModelImp() {
		super();

		setWidth(40);
		setInputType(InputFieldType.CHECKBOX);
		setAlignment(AlignmentType.CENTER);
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnCheckboxDesignModel#getSelectMode()
	 */
	public SelectMode getSelectMode() {
		return selectMode;
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnCheckboxDesignModel#setSelectMode(com.cc.framework.ui.SelectMode)
	 */
	public void setSelectMode(SelectMode mode) {
		this.selectMode = mode;
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnCheckboxDesignModel#checkAll()
	 */
	public boolean checkAll() {
		return checkAll;
	}

	/**
	 * @see com.cc.framework.ui.model.ColumnCheckboxDesignModel#setCheckAll(boolean)
	 */
	public void setCheckAll(boolean enable) {
		this.checkAll = enable;
	}
}