/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/controls/ColumnCheckboxTag.java,v 1.5 2005/07/08 14:17:11 P001002 Exp $
 * $Revision: 1.5 $
 * $Date: 2005/07/08 14:17:11 $
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

import javax.servlet.jsp.JspException;

import com.cc.framework.common.InvalidEnumType;
import com.cc.framework.taglib.TagHelp;
import com.cc.framework.ui.SelectMode;
import com.cc.framework.ui.model.ColumnCheckboxDesignModel;
import com.cc.framework.ui.model.ColumnDesignModel;
import com.cc.framework.ui.model.imp.ColumnCheckboxDesignModelImp;

/**
 * Tag-Handler for the <code>columncheckbox</code> Tag.
 * <p>
 * Generates an checkbox column
 *
 * @author      <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version     $Revision: 1.5 $
 * @since       1.3
 */
public class ColumnCheckboxTag extends ColumnTextTag {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -809820826848021849L;

	/**
	 * Constructor
	 */
	public ColumnCheckboxTag() {
		super();
	}

	/**
	 * @see com.cc.framework.taglib.controls.ColumnBaseTag#doCreateDesignModel()
	 */
	public ColumnDesignModel doCreateDesignModel() {
		return new ColumnCheckboxDesignModelImp();
	}

	/**
	 * Specifies the selection mode of the list. This is only
	 * used if a Check column is to be displayed here as well.
	 * <ul>
	 * 		<li>none = no selection/not selectable.</li>
	 * 		<li>single = single selection.</li>
	 *		<li>multiple = multiple selection.</li>
	 * </ul>
	 *
	 * @param	mode	The selection mode of the list.
	 * @throws	JspException If the Argument can't be converted	to an object of type SelectMode
	 */
	public void setSelect(String mode) throws JspException {

		try {
			((ColumnCheckboxDesignModel) getDesignModel()).setSelectMode(SelectMode.parse(mode));
		} catch (InvalidEnumType iet) {
			throw new JspException("Invalid Attribute value " + iet.getMessage());
		}
	}

	/**
	 * Enables the check all property of the column
	 *
	 * @param	enable <code>true</code> enable the multiple check
	 *          option of the column
	 * @throws	JspException If the Argument can't be converted to boolean
	 */
	public void setCheckAll(String enable) throws JspException {
		((ColumnCheckboxDesignModel) getDesignModel()).setCheckAll(TagHelp.toBoolean(enable));
	}
}