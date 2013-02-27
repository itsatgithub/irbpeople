/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/controls/ColumnCheckTag.java,v 1.10 2005/07/08 14:17:11 P001002 Exp $
 * $Revision: 1.10 $
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

import com.cc.framework.taglib.TagHelp;
import com.cc.framework.ui.model.ColumnCheckDesignModel;
import com.cc.framework.ui.model.ColumnDesignModel;
import com.cc.framework.ui.model.imp.ColumnCheckDesignModelImp;

/**
 * Tag-Handler for the <code>columncheck</code> Tag.
 * <p>
 * Generates a checkbox column.
 * The check state of a line can be determined in two ways:
 * Explicitly with the property-attribute. It returns the values true=Checked
 * or false=Unchecked. The Row-Bean implements the Checkable Interface.
 * The onCheck-Eventhandler of the assigned action is fired.
 * The <columncheck>-tag may only be used within a Taghandler which implements
 * the com.cc.framework.taglib.controls.ColumnContainerTag Interface.
 * These are e.g. the <list>- ot the <treelist>-tag
 *
 * @author      <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version     $Revision: 1.10 $
 * @since       1.0
 */
public class ColumnCheckTag extends ColumnBaseTag {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 8842787873623426952L;

	/**
	 * Constructor
	 */
	public ColumnCheckTag() {
		super();
	}

	/**
	 * @see com.cc.framework.taglib.controls.ColumnBaseTag#doCreateDesignModel()
	 */
	public ColumnDesignModel doCreateDesignModel() {
		return new ColumnCheckDesignModelImp();
	}

	/**
	 * Enables the check all property of the column
	 *
	 * @param	enable <code>true</code> enable the multiple check
	 *          option of the column
	 * @throws	JspException If the Argument can't be converted to boolean
	 */
	public void setCheckAll(String enable) throws JspException {
		((ColumnCheckDesignModel) getDesignModel()).setCheckAll(TagHelp.toBoolean(enable));
	}
}
