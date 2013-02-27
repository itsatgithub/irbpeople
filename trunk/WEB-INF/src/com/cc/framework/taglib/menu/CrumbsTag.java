/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/menu/CrumbsTag.java,v 1.12 2005/11/13 13:40:08 P001002 Exp $
 * $Revision: 1.12 $
 * $Date: 2005/11/13 13:40:08 $
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

package com.cc.framework.taglib.menu;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import com.cc.framework.taglib.TagHelp;
import com.cc.framework.taglib.controls.BaseControlTag;
import com.cc.framework.ui.control.Control;
import com.cc.framework.ui.control.CrumbsControl;
import com.cc.framework.ui.model.ControlDesignModel;
import com.cc.framework.ui.model.CrumbModel;
import com.cc.framework.ui.model.CrumbsDesignModel;
import com.cc.framework.ui.model.imp.CrumbsDesignModelImp;

/**
 * Tag-Handler for the <code>crumbs</code> Tag.
 * <p>
 * Generates a CrumbsControl.
 *
 * @author      <a href="mailto:gschulz@scc-gmbh.com">Gernot Schulz</a>
 * @version     $Revision: 1.12 $
 * @since       1.3
 */
public class CrumbsTag extends BaseControlTag implements CrumbContainer {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 7982915911440884145L;

	/**
	 * Thr current selected crumb
	 */
	private String selectedCrumb = null;

	/**
	 * Constructor
	 */
	public CrumbsTag() {
		super();
	}

	/**
	 * @see com.cc.framework.taglib.controls.BaseControlTag#doCreateDesignModel()
	 */
	protected ControlDesignModel doCreateDesignModel() {
		return new CrumbsDesignModelImp();
	}

	/**
	 * Returns a casted version of the ControlDesignModel
	 *
	 * @return		CrumbsDesignModel
	 */
	protected CrumbsDesignModel getCrumbsDesignModel() {
		return (CrumbsDesignModel) getDesignModel();
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#setPageContext(PageContext pc)
	 */
	public void setPageContext(PageContext pc) {
		super.setPageContext(pc);

		// initialize variables
		selectedCrumb	= null;
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#release()
	 */
	public void release() {
		super.release();

		selectedCrumb	= null;
	}

	/**
	 * Returns if the specified crumb is selected
	 * @param		crumb CrumbDesignModel
	 * @return		boolean
	 */
	public boolean isSelected(CrumbModel crumb) {
		return crumb.getCrumbId().equals(selectedCrumb);
	}

	/**
	 * Sets the code of the current selected crumb
	 *
	 * @param		selected Crumb identifier
	 */
	public void setSelected(String selected) {
		this.selectedCrumb = selected;
	}

	/**
	 * @see com.cc.framework.taglib.controls.BaseControlTag#doCreateControl()
	 */
	protected Control doCreateControl() throws JspException {
		CrumbsDesignModel designModel = getCrumbsDesignModel();
		CrumbsControl ctrl = null;

		Object value = lookupBean();

		if (value == null) {
			// if there exists no instance for the control, we create a new one
			ctrl = new CrumbsControl();
		} else if (value instanceof CrumbsControl) {
			// assign the control
			ctrl = (CrumbsControl) value;
		} else {
			// create a new wrapper
			ctrl = new CrumbsControl();
			ctrl.setSelectedCrumb(value.toString());
		}

		// if the designmodel was not specified server side we get the model
		// from the jsp page
		if ((ctrl.getDesignModel() == null)
			|| ctrl.getDesignModel().isDynamicDesignModel()) {
			ctrl.setDesignModel(designModel);
		}

		// assign an action if the programmer did not
		if (designModel.getAction() == null) {
			designModel.setAction(getDefaultAction());
		}

		// remember the selected tab for use in the
		// tag body
		setSelected(ctrl.getSelectedCrumb());

		return ctrl;
	}

	/**
	 * With this attribute, an ImageList is assigned to the crumbs,
	 * with the help of which images can be assigned to the
	 * individual crumbs.
	 *
	 * @param	mapName			The ImageMap to assign
	 * @throws	JspException	If the ImageMap could not be found
	 */
	public void setImagemap(String mapName) throws JspException {
		getCrumbsDesignModel().setImageMap(TagHelp.lookupImageMap(pageContext, mapName));
	}

	/**
	 * Specifies the maximum number of characters for the labels of a crumb.
	 * If a label is longer, it is shortened with "...".
	 *
	 * @param	length	The maximum number of characters for the labels of a crumbs
	 * @throws JspException
	 *             If the Argument can't be converted to int
	 */
	public void setLabellength(String length) throws JspException {
		getCrumbsDesignModel().setLabelLength(TagHelp.toInt(length));
	}

	/**
	 * @see com.cc.framework.taglib.menu.CrumbContainer#addCrumb(com.cc.framework.ui.model.CrumbModel)
	 */
	public void addCrumb(CrumbModel crumb) {
		getCrumbsDesignModel().addCrumb(crumb);
	}

	/**
	 * The automatic HTML coding of the crumbs can be activated
	 * or disabled with the filter-attribute. The default is <code>true</code>
	 *
	 * @param	filter	<code>true</code> if the crumbs should be HTML encoded.
	 * @throws	JspException If the Argument can't be converted to boolean
	 */
	public void setFilter(String filter) throws JspException {
		getCrumbsDesignModel().setFilter(TagHelp.toBoolean(filter));
	}
}