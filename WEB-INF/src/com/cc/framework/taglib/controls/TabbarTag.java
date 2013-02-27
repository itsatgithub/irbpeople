/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/controls/TabbarTag.java,v 1.30 2005/11/13 13:39:39 P001002 Exp $
 * $Revision: 1.30 $
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
import javax.servlet.jsp.PageContext;

import com.cc.framework.taglib.TagHelp;
import com.cc.framework.ui.control.Control;
import com.cc.framework.ui.control.TabbarControl;
import com.cc.framework.ui.model.ControlDesignModel;
import com.cc.framework.ui.model.TabDesignModel;
import com.cc.framework.ui.model.TabsetDesignModel;
import com.cc.framework.ui.model.imp.TabsetDesignModelImp;

/**
 * Tag-Handler for the <code>tabbar</code> Tag.
 * <p>
 * Generates a tab strip without the cardex Body.
 * The individual tabs of the control element can
 * be directly specified in the JSP-Page with the
 * help of the <tab>-tag.
 * The Body of the tabs is ignored!
 *
 * @author      <a href="mailto:gschulz@scc-gmbh.com">Gernot Schulz</a>
 * @version     $Revision: 1.30 $
 * @since       1.2
 */
public class TabbarTag extends BaseControlTag implements TabContainer {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 8682155833824932515L;

	/**
	 * Thr current selected tab
	 */
	private String selectedTab = null;

	/**
	 * Constructor
	 */
	public TabbarTag() {
		super();
	}

	/**
	 * @see com.cc.framework.taglib.controls.BaseControlTag#doCreateDesignModel()
	 */
	protected ControlDesignModel doCreateDesignModel() {
		return new TabsetDesignModelImp();
	}

	/**
	 * Returns a casted version of the ControlDesignModel
	 *
	 * @return		TabsetDesignModel
	 */
	protected TabsetDesignModel getTabsetDesignModel() {
		return (TabsetDesignModel) getDesignModel();
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#setPageContext(PageContext pc)
	 */
	public void setPageContext(PageContext pc) {
		super.setPageContext(pc);

		// initialize variables
		selectedTab	= null;
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#release()
	 */
	public void release() {
		super.release();

		selectedTab	= null;
	}

	/**
	 * Returns if the specified TabPage is selected
	 * @param tab	TabDesignModel
	 * @return	boolean
	 */
	public boolean isSelected(TabDesignModel tab) {
		return tab.getTabId().equals(selectedTab);
	}

	/**
	 * Sets the code of the current selected tab
	 *
	 * @param		selected Tab page code
	 */
	public void setSelected(String selected) {
		this.selectedTab = selected;
	}

	/**
	 * @see com.cc.framework.taglib.controls.BaseControlTag#doCreateControl()
	 */
	protected Control doCreateControl() throws JspException {
		Object value = lookupBean();

		TabsetDesignModel designModel = getTabsetDesignModel();
		TabbarControl ctrl = null;

		if (value == null) {
			// if there exists no instance for the control, we create a new one
			ctrl = new TabbarControl();

		} else if (value instanceof TabbarControl) {
			// assign the control
			ctrl = (TabbarControl) value;
		} else {
			// create a new wrapper
			ctrl = new TabbarControl();
			ctrl.setSelectedTab(value.toString());
		}

		// if the designmodel was not specified server side we get the model
		// from the jsp page
		if ((ctrl.getDesignModel() == null) || ctrl.getDesignModel().isDynamicDesignModel()) {
			ctrl.setDesignModel(designModel);
		}

		// assign an action if the programmer did not
		if (designModel.getAction() == null) {
			designModel.setAction(getDefaultAction());
		}

		// remember the selected tab for use in the
		// tag body
		setSelected(ctrl.getSelectedTab());

		return ctrl;
	}

	/**
	 * Sets the number of visible TabPages
	 *
	 * @param tabs	Number of visible TabPages
	 * @throws JspException
	 *             If the Argument can't be converted to int
	 */
	public void setTabs(String tabs) throws JspException {
		getTabsetDesignModel().setMaxVisible(TagHelp.toInt(tabs));
	}

	/**
	 * Sets the maximum
	 * @param maximum The maximum to set
	 */
	public void setMaximum(int maximum) {
		getTabsetDesignModel().setMaxVisible(maximum);
	}

	/**
	 * Sets the background color for the control element.
	 *
	 * @param bgcolor	The background color
	 */
	public void setBgcolor(String bgcolor) {
		getTabsetDesignModel().setBgColor(TagHelp.toColor(bgcolor));
	}

	/**
	 * With this attribute, an ImageList is assigned to the tabset,
	 * with the help of which images can be assigned to the
	 * individual tabs.
	 *
	 * @param	mapName			The ImageMap to assign
	 * @throws	JspException	If the ImageMap could not be found
	 */
	public void setImagemap(String mapName) throws JspException {
		getTabsetDesignModel().setImageMap(TagHelp.lookupImageMap(pageContext, mapName));
	}

	/**
	 * Specifies the maximum number of characters for the labels of the tab.
	 * If a label is longer, it is shortened with "...".
	 *
	 * @param	length	The maximum number of characters for the labels of the tab.
	 * @throws JspException
	 *             If the Argument can't be converted to int
	 */
	public void setLabellength(String length) throws JspException {
		getTabsetDesignModel().setLabelLength(TagHelp.toInt(length));
	}

	/**
	 * @see com.cc.framework.taglib.controls.TabContainer#addTab(com.cc.framework.ui.model.TabDesignModel)
	 */
	public void addTab(TabDesignModel tab) {
		getTabsetDesignModel().addTab(tab);
	}

	/**
	 * The automatic HTML coding of the tabsets can be activated
	 * or disabled with the filter-attribute. The default is <code>true</code>
	 *
	 * @param	filter	<code>true</code> if the tabs should be HTML encoded.
	 * @throws	JspException If the Argument can't be converted to boolean
	 */
	public void setFilter(String filter) throws JspException {
		getTabsetDesignModel().setFilter(TagHelp.toBoolean(filter));
	}
}