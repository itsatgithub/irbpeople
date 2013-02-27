/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/controls/GaugeTag.java,v 1.9 2005/08/02 19:13:05 P001002 Exp $
 * $Revision: 1.9 $
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
import com.cc.framework.ui.control.GaugeControl;
import com.cc.framework.ui.model.ControlDesignModel;
import com.cc.framework.ui.model.GaugeDataModel;
import com.cc.framework.ui.model.GaugeDesignModel;
import com.cc.framework.ui.model.imp.GaugeDesignModelImp;

/**
 * Tag-Handler for the Gauge Control
 *
 * @author      <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version     $Revision: 1.9 $
 * @since       1.4
 */
public class GaugeTag extends BaseControlTag {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 3498663218170110866L;

	/**
	 * Constructor
	 */
	public GaugeTag() {
		super();
	}

	/**
	 * @see com.cc.framework.taglib.controls.BaseControlTag#doCreateDesignModel()
	 */
	public ControlDesignModel doCreateDesignModel() {
		return new GaugeDesignModelImp();
	}

	/**
	 * Returns a casted version of the ControlDesignModel
	 *
	 * @return		GaugeDesignModel
	 */
	protected GaugeDesignModel getGaugeDesignModel() {
		return (GaugeDesignModel) getDesignModel();
	}

	/**
	 * @see com.cc.framework.taglib.controls.BaseControlTag#doCreateControl()
	 */
	protected Control doCreateControl() throws JspException {
		// set the control
		GaugeControl ctrl = null;

		// get the control
		Object value = lookupBean();

		if (value instanceof GaugeDataModel) {
			ctrl = new GaugeControl();
			ctrl.setDataModel((GaugeDataModel) value);
		} else if (value instanceof GaugeControl) {
			ctrl = (GaugeControl) value;
		} else {
			throw new JspException("GaugeTag: Invalid property class. expected class=GaugeControl!");
		}

		// Assign an action if this doesn't happend by the programmer
		if (getGaugeDesignModel().getAction() == null) {
			getGaugeDesignModel().setAction(getDefaultAction());
		}

		// Assign the DesignModel if this did not happened
		// in the Application
		if ((ctrl.getDesignModel() == null) || ctrl.getDesignModel().isDynamicDesignModel()) {
			ctrl.setDesignModel(getGaugeDesignModel());
		} else {
			GaugeDesignModel dm = (GaugeDesignModel) ctrl.getDesignModel();

			dm.setAction(getGaugeDesignModel().getAction());
			dm.setProperty(getGaugeDesignModel().getProperty());
		}

		return ctrl;
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException {
		super.doStartTag();

		// this tag has no body
		return SKIP_BODY;
	}

	/**
	 * A text that is output in the body of the
	 * gauge element if there are no items available.
	 *
	 * @param	emptyText	Text
	 */
	public void setEmptyText(String emptyText) {
		getGaugeDesignModel().setEmptyText(emptyText);
	}

	/**
	 * The automatic HTML coding of the column contents
	 * can be activated or disabled with the filter-attribute
	 *
	 * @param	filter	<code>true</code> if the column content should be HTML encoded.
	 * @throws		JspException If the Argument can't be converted	to a boolean
	 */
	public void setFilter(String filter) throws JspException {
		getGaugeDesignModel().setFilter(TagHelp.toBoolean(filter));
	}

	/**
	 * Specifies the alignment of the text:
	 * <ul>
	 * 		<li>left = left alignment.</li>
	 * 		<li>right =	right alignment.</li>
	 * 		<li>center = the column contents are centered.</li>
	 * </ul>
	 *
	 * @param		alignment	The alignment of the column
	 * @throws		JspException If the argument can't be converted	to an AlignmentType
	 */
	public void setAlign(String alignment) throws JspException {
		getGaugeDesignModel().setAlignment(TagHelp.toAlignment(alignment));
	}
}