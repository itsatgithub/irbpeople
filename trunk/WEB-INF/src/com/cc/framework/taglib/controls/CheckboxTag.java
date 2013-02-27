/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/controls/CheckboxTag.java,v 1.22 2005/11/13 13:39:39 P001002 Exp $
 * $Revision: 1.22 $
 * $Date: 2005/11/13 13:39:39 $
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
import com.cc.framework.ui.control.CheckboxControl;
import com.cc.framework.ui.control.Control;
import com.cc.framework.ui.model.CheckboxDesignModel;
import com.cc.framework.ui.model.ControlDesignModel;
import com.cc.framework.ui.model.imp.CheckboxDesignModelImp;

/**
 * Tag handler for the checkbox tag
 *
 * @author      <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version     $Revision: 1.22 $
 * @since       1.0
 */
public class CheckboxTag extends BaseControlTag {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 4212899196222631831L;

	/**
	 * Constructor
	 */
	public CheckboxTag() {
		super();
	}

	/**
	 * @see com.cc.framework.taglib.controls.BaseControlTag#doCreateDesignModel()
	 */
	public ControlDesignModel doCreateDesignModel() {
		return new CheckboxDesignModelImp();
	}

	/**
	 * Returns a casted version of the ControlDesignModel
	 *
	 * @return		CheckboxDesignModel
	 */
	protected CheckboxDesignModel getCheckboxDesignModel() {
		return (CheckboxDesignModel) getDesignModel();
	}

	/**
	 * @see com.cc.framework.taglib.controls.BaseControlTag#doCreateControl()
	 */
	protected Control doCreateControl() throws JspException {
		// Get the DesignModel
		CheckboxControl ctrl = new CheckboxControl(getCheckboxDesignModel());

		// get the datamodel
		Object value = lookupBean();

		if (value != null) {
			ctrl.setChecked(new Boolean(TagHelp.toBoolean(value)));
		}

		return ctrl;
	}
}