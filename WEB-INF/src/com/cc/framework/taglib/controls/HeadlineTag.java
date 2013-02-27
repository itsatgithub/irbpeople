/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/controls/HeadlineTag.java,v 1.19 2005/08/02 19:13:05 P001002 Exp $
 * $Revision: 1.19 $
 * $Date: 2005/08/02 19:13:05 $
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
import com.cc.framework.ui.control.Control;
import com.cc.framework.ui.control.HeadlineControl;
import com.cc.framework.ui.model.ControlDesignModel;
import com.cc.framework.ui.model.HeadlineDesignModel;
import com.cc.framework.ui.model.imp.HeadlineDesignModelImp;

/**
 * Tag-Handler for the <code>headline</code> Tag.
 * <p>
 * Generates a headline element.
 *
 * @author      <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version     $Revision: 1.19 $
 * @since       1.0
 */
public class HeadlineTag extends BaseControlTag {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -8286508830235296900L;

	/**
	 * Constructor
	 */
	public HeadlineTag() {
		super();
	}

	/**
	 * Creates the Design Model
	 *
	 * @return		Design Model
	 */
	public ControlDesignModel doCreateDesignModel() {
		return new HeadlineDesignModelImp();
	}

	/**
	 * Returns a casted version of the ControlDesignModel
	 *
	 * @return		HeadlineDesignModel
	 */
	protected HeadlineDesignModel getHeadlineDesignModel() {
		return (HeadlineDesignModel) getDesignModel();
	}

	/**
	 * @see com.cc.framework.taglib.controls.BaseControlTag#doCreateControl()
	 */
	protected Control doCreateControl() throws JspException {
		HeadlineControl ctrl = null;
		
		HeadlineDesignModel designModel = getHeadlineDesignModel();

		if (designModel.getCaption() != null && !designModel.getCaption().equals("")) {
			// create the headline control
			ctrl = new HeadlineControl();
			ctrl.setDesignModel(designModel);

		} else {
			Object value = lookupBean();

			if (value instanceof HeadlineControl) {
				// set the control
				ctrl = (HeadlineControl) value;
			} else if (value instanceof HeadlineDesignModel) {
				ctrl = new HeadlineControl();
				ctrl.setDesignModel((HeadlineDesignModel) value);
			} else if (value instanceof String) {
				ctrl = new HeadlineControl();
				ctrl.setDesignModel(designModel);
				designModel.setCaption((String) value);
			}
		}

		if (designModel.getAction() == null) {
			designModel.setAction(getDefaultAction());
		}

		return ctrl;
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException {
		super.doStartTag();

		return SKIP_BODY;
	}

	/**
	 * Sets the Caption
	 * @param	caption	Caption
	 */
	public void setCaption(String caption) {
		getHeadlineDesignModel().setCaption(caption);
	}

	/**
	 * Sets the Detail Attribute
	 * @param	detail	Detail
	 */
	public void setDetail(String detail) {
		getHeadlineDesignModel().setDetail(detail);
	}

	/**
	 * The automatic HTML coding of the column contents
	 * can be activated or disabled with the filter-attribute
	 *
	 * @param	filter	<code>true</code> if the column content should be HTML encoded.
	 * @throws		JspException If the Argument can't be converted	to a boolean
	 */
	public void setFilter(String filter) throws JspException {
		getHeadlineDesignModel().setFilter(TagHelp.toBoolean(filter));
	}
}
