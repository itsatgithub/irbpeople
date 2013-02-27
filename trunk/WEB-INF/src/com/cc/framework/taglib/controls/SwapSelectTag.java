/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/controls/SwapSelectTag.java,v 1.16 2005/11/13 13:39:39 P001002 Exp $
 * $Revision: 1.16 $
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
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTag;

import com.cc.framework.Globals;
import com.cc.framework.common.InvalidEnumType;
import com.cc.framework.taglib.TagHelp;
import com.cc.framework.ui.OrientationType;
import com.cc.framework.ui.control.Control;
import com.cc.framework.ui.control.SwapSelectControl;
import com.cc.framework.ui.model.ControlDesignModel;
import com.cc.framework.ui.model.OptionListDesignModel;
import com.cc.framework.ui.model.SwapSelectDesignModel;
import com.cc.framework.ui.model.imp.SwapSelectDesignModelImp;
import com.cc.framework.ui.painter.html.LiteralOptionsComparator;
import com.cc.framework.ui.painter.html.OptionsComparator;
import com.cc.framework.ui.painter.html.OptionsHelp;
import com.cc.framework.util.StringHelp;

/**
 *
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.16 $
 */
public class SwapSelectTag extends BaseControlTag implements BodyTag {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -7045937851342816431L;

	/**
	 * The Body Content of the Tag
	 */
	private BodyContent bodyContent				= null;

	/**
	 * Constructor
	 */
	public SwapSelectTag() {
		super();
	}

	/**
	 * Retrieves the current Control instance
	 * 
	 * @return	Control Instance
	 */
	protected SwapSelectControl getCtrl() {
		return (SwapSelectControl) getControl();
	}

	/**
	 * @see com.cc.framework.taglib.controls.BaseControlTag#doCreateDesignModel()
	 */
	protected ControlDesignModel doCreateDesignModel() {
		return new SwapSelectDesignModelImp();
	}

	/**
	 * Returns a casted version of the ControlDesignModel
	 *
	 * @return		SwapSelectDesignModel
	 */
	protected SwapSelectDesignModel getSwapSelectDesignModel() {
		return (SwapSelectDesignModel) getDesignModel();
	}

	/**
	 * @see javax.servlet.jsp.tagext.BodyTag#setBodyContent(javax.servlet.jsp.tagext.BodyContent)
	 */
	public void setBodyContent(BodyContent b) {
		bodyContent = b;
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#release()
	 */
	public void release() {
		super.release();

		bodyContent	= null;
	}

	/**
	 * @see com.cc.framework.taglib.controls.BaseControlTag#doCreateControl()
	 */
	protected Control doCreateControl() throws JspException {
		SwapSelectDesignModel designModel = getSwapSelectDesignModel();
		Object value = lookupBean();

		SwapSelectControl ctrl;
		if (value instanceof SwapSelectControl) {
			ctrl = (SwapSelectControl) value;

			// Assign the DesignModel if this did not happened
			// in the Application
			if ((ctrl.getDesignModel() == null) || ctrl.getDesignModel().isDynamicDesignModel()) {
				ctrl.setDesignModel(designModel);
			} else {
				SwapSelectDesignModel dm = (SwapSelectDesignModel) ctrl.getDesignModel();

				dm.setAction(designModel.getAction());
				dm.setProperty(designModel.getProperty());
			}
		} else {
			// Das Kontrollelement übernehmen
			ctrl = new SwapSelectControl(designModel);

			if (value != null) {
				ctrl.setValues(OptionsHelp.toArray(value));
			}
		}

		// Assign an action if this doesn't happend by the programmer
		if (designModel.getAction() == null) {
			designModel.setAction(getDefaultAction());
		}

		// Store this tag itself as a page attribute
		pageContext.setAttribute(Globals.STRUTS_SELECT_KEY, this);

		return ctrl;
	}

	/**
	 * @see javax.servlet.jsp.tagext.BodyTag#doInitBody()
	 */
	public void doInitBody() throws JspException {
	}

	/**
	 * @see javax.servlet.jsp.tagext.IterationTag#doAfterBody()
	 */
	public int doAfterBody() throws JspException {

		// Render the output from this iteration to the output stream
		if ((bodyContent != null) && (bodyContent.getString() != null)) {
			String bodyText = bodyContent.getString().trim();

			if (bodyText.length() > 0) {
				// We don't paint the body at this time, so
				// store it for later use
				getCtrl().setBody(bodyText);
			}

			bodyContent.clearBody();
		}

		// Remove the page scope attributes we created
		pageContext.removeAttribute(Globals.STRUTS_SELECT_KEY);

		return SKIP_BODY;
	}

	/**
	 * Returns an option comparator for this control
	 *
	 * @return		OptionsComparator
	 */
	public OptionsComparator getOptionsComparator() {
		if (getCtrl() == null) {
			return null;
		} else {
			return new LiteralOptionsComparator(getCtrl().getValues());
		}
	}

	/**
	 * Specifies the number of visible characters.
	 *
	 * @param	size	The size
	 * @throws JspException
	 *             If the Argument can't be converted to int
	 */
	public void setSize(String size) throws JspException {
		getSwapSelectDesignModel().setSize(TagHelp.toInt(size));
	}

	/**
	 * Sets the label for the left selection window
	 *
	 * @param	label	the label
	 */
	public void setLabelLeft(String label) {
		getSwapSelectDesignModel().setLabelLeft(label);
	}

	/**
	 * Sets the label for the right selection window
	 *
	 * @param	label	the label
	 */
	public void setLabelRight(String label) {
		getSwapSelectDesignModel().setLabelRight(label);
	}

	/**
	 * The automatic HTML coding of the element can be activated
	 * or disabled with the filter-attribute. The default is <code>true</code>
	 *
	 * @param	filter	<code>true</code> if the element should be HTML encoded.
	 * @throws	JspException If the Argument can't be converted to boolean
	 */
	public void setFilter(String filter) throws JspException {
		getSwapSelectDesignModel().setFilter(TagHelp.toBoolean(filter));
	}

	/**
	 * Specifies the orientation of the elements in the group:
	 * <ul>
	 * 		<li>horicontal = from top to bottom</li>
	 * 		<li>vertical = from left to right</li>
	 * </ul>
	 *
	 * @param		orientation	The orientation of the column
	 * @throws		JspException If the argument can't be converted	to an OrientationType
	 */
	public void setOrientation(String orientation) throws JspException {
		try {
			getSwapSelectDesignModel().setOrientation(OrientationType.parse(orientation));
		} catch (InvalidEnumType iet) {
			throw new JspException("Invalid attribute value: " + iet.getMessage());
		}
	}

	/**
	 * Sets the OptionList which is used by the Select-Control
	 * @param	optionList OptionListDesignModel
	 * @param	optionElements The selected options
	 */
	public void setOptionList(
		OptionListDesignModel optionList,
		Object optionElements) {
		if (getCtrl() != null) {
			getCtrl().setOptions(optionList, optionElements);
		}
	}

	/**
	 * Sets the direct value for this control. This will overwrite
	 * the value of the <code>property</code> attribute
	 *
	 * @param		value The controls value
	 */
	public void setValue(String value) {
		super.setDirectValue(StringHelp.split(value, ";"));
	}
}