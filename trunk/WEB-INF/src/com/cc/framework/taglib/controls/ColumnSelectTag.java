/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/controls/ColumnSelectTag.java,v 1.15 2005/08/02 19:13:06 P001002 Exp $
 * $Revision: 1.15 $
 * $Date: 2005/08/02 19:13:06 $
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

import com.cc.framework.Globals;
import com.cc.framework.taglib.TagHelp;
import com.cc.framework.ui.model.ColumnDesignModel;
import com.cc.framework.ui.model.ColumnSelectDesignModel;
import com.cc.framework.ui.model.OptionListDesignModel;
import com.cc.framework.ui.model.imp.ColumnSelectDesignModelImp;

/**
 * Tag-Handler for the <code>columnselect</code> Tag.
 * <p>
 * Generates a select column.
 * The <columnselect>-tag may only be used within a Taghandler
 * which implements the com.cc.framework.taglib.controls.ColumnContainerTag
 * Interface. These are e.g. the <list>- or the <treelist>-tag.
 *
 * @author      <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version     $Revision: 1.15 $
 * @since       1.3
 */
public class ColumnSelectTag extends ColumnBaseBodyTag {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1987023856986689872L;

	/**
	 * Constructor
	 */
	public ColumnSelectTag() {
		super();
	}

	/**
	 * @see com.cc.framework.taglib.controls.ColumnBaseTag#doCreateDesignModel()
	 */
	public ColumnDesignModel doCreateDesignModel() {
		return new ColumnSelectDesignModelImp();
	}

	/**
	 * Sets the Text for the optional empty element
	 *
	 * @param	empty the empty text
	 */
	public void setEmpty(String empty) {
		((ColumnSelectDesignModel) getDesignModel()).setEmpty(empty);
	}

	/**
	 * Sets the property that holds the option elements
	 * for the select box (Implementation of the OptionListDatamodel interface)
	 *
	 * @param	property The name of the property
	 */
	public void setOptionsProperty(String property) {
		((ColumnSelectDesignModel) getDesignModel()).setOptionsProperty(property);
	}

	/**
	 * Specifies the name of the property which returns the
	 * key of the option. Note: Valid Java designator/label for
	 * a property which has to be implemented from the elements
	 * of the collection
	 *
	 * @param keyProperty The keyProperty to set
	 */
	public void setKeyProperty(String keyProperty) {
		((ColumnSelectDesignModel) getDesignModel()).setKeyProperty(keyProperty);
	}

	/**
	 * Specifies the name of the property which
	 * supplies the display text of the option.
	 *
	 * @param labelProperty The labelProperty to set
	 */
	public void setLabelProperty(String labelProperty) {
		((ColumnSelectDesignModel) getDesignModel()).setLabelProperty(labelProperty);
	}

	/**
	 * Specifies the number of visible characters.
	 *
	 * @param	size	The size
	 */
	public void setSize(String size) {
		((ColumnSelectDesignModel) getDesignModel()).setSize(size);
	}

	/**
	 * Indicates that multiple selections is allowed.
	 *
	 * @param		multiple <code<true</code> for multiple
	 * 				selection
	 * @throws		JspException If the Argument can't be converted
	 * 				to boolean
	 */
	public void setMultiple(String multiple) throws JspException {
		((ColumnSelectDesignModel) getDesignModel()).setMultiple(TagHelp.toBoolean(multiple));
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException {

		// Store this tag itself as a page attribute
		pageContext.setAttribute(Globals.STRUTS_SELECT_KEY, this);

		return super.doStartTag();
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException {

		// Remove the page scope attributes we created
		pageContext.removeAttribute(Globals.STRUTS_SELECT_KEY);

		// Continue processing this page
		return super.doEndTag();
	}

	/**
	 * Sets the OptionList which is used by the Select-Control
	 * @param	optionList OptionListDesignModel
	 * @param	optionElements The selected options
	 */
	public void setOptionList(
		OptionListDesignModel optionList,
		Object optionElements) {

		ColumnSelectDesignModel csdm = (ColumnSelectDesignModel) getDesignModel();

		if ((csdm != null) && (csdm.getSharedOptions() == null)) {
			csdm.setSharedOptions(optionElements);

			csdm.setEmpty(optionList.getEmpty());
			csdm.setKeyProperty(optionList.getKeyProperty());
			csdm.setLabelProperty(optionList.getLabelProperty());
			csdm.setStyle(optionList.getStyle());
			csdm.setStyleClass(optionList.getStyleClass());
			csdm.setMaxLength(optionList.getMaxLength());
			csdm.setFilter(optionList.filter());
			csdm.setLocalize(optionList.localize());
		}
	}
}