/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/controls/TextTag.java,v 1.31 2005/11/13 13:39:39 P001002 Exp $
 * $Revision: 1.31 $
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

import com.cc.framework.convert.Converter;
import com.cc.framework.convert.ConverterHelp;
import com.cc.framework.taglib.ButtonContainerTag;
import com.cc.framework.taglib.ConversionSupportTag;
import com.cc.framework.taglib.TagHelp;
import com.cc.framework.ui.control.Control;
import com.cc.framework.ui.control.TextControl;
import com.cc.framework.ui.model.ControlDesignModel;
import com.cc.framework.ui.model.InputFieldType;
import com.cc.framework.ui.model.TextDesignModel;
import com.cc.framework.ui.model.imp.TextDesignModelImp;

/**
 * Tag-Handler for the Text-Formularelement
 *
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version $Revision: 1.31 $
 * @since 1.0
 */
public class TextTag extends BaseControlTag implements ButtonContainerTag,
		ConversionSupportTag {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 8148382585666287763L;

	/**
	 * Constructor
	 */
	public TextTag() {
		super();
	}

	/**
	 * Retrieves the current Control instance
	 *
	 * @return Control Instance
	 */
	protected TextControl getCtrl() {
		return (TextControl) getControl();
	}

	/**
	 * @see com.cc.framework.taglib.controls.BaseControlTag#doCreateDesignModel()
	 */
	protected ControlDesignModel doCreateDesignModel() {
		return new TextDesignModelImp();
	}

	/**
	 * Returns a casted version of the ControlDesignModel
	 *
	 * @return TextDesignModel
	 */
	protected TextDesignModel getTextDesignModel() {
		return (TextDesignModel) getDesignModel();
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#release()
	 */
	public void release() {
		super.release();
	}

	/**
	 * @see com.cc.framework.taglib.controls.BaseControlTag#doCreateControl()
	 */
	protected Control doCreateControl() throws JspException {
		// assign a new control
		TextControl ctrl = new TextControl(getTextDesignModel());

		Object value = lookupBean();

		if (value != null) {
			ctrl.setValue(value);
		}

		return ctrl;
	}

	/**
	 * Specifies the number of visible characters.
	 *
	 * @param size
	 *            The size
	 * @throws JspException
	 *             If the argument could not be converted to integer
	 */
	public void setSize(String size) throws JspException {
		getTextDesignModel().setSize(TagHelp.toInt(size));
	}

	/**
	 * Specifies the maximum number of characters for the data input.
	 *
	 * @param maxlength
	 *            The maximum number of characters
	 * @throws JspException
	 *             If the argument could not be converted to integer
	 */
	public void setMaxlength(String maxlength) throws JspException {
		getTextDesignModel().setMaxLength(TagHelp.toInt(maxlength));
	}

	/**
	 * Sets the type of the input field
	 *
	 * @param inputType
	 *            The input field type
	 * @see com.cc.framework.ui.model.InputFieldType
	 */
	public void setInputType(InputFieldType inputType) {
		getTextDesignModel().setInputType(inputType);
	}

	/**
	 * The automatic HTML coding of the text element can be activated or
	 * disabled with the filter-attribute
	 *
	 * @param filter
	 *            <code>true</code> if the text should be HTML encoded.
	 * @throws JspException
	 *             If the Argument can't be converted to a boolean
	 */
	public void setFilter(String filter) throws JspException {
		getTextDesignModel().setFilter(TagHelp.toBoolean(filter));
	}

	/**
	 * Sets the Converter that should be used to convert Java Objects into their
	 * localized String representation. If no converter is specified the
	 * framework will use a default Converter that matches the row beans column
	 * property.
	 *
	 * @param converter
	 *            Converters class Name
	 * @throws JspException
	 *             If the Converter is invalid
	 */
	public void setConverter(String converter) throws JspException {
		try {
			assignConverter(ConverterHelp.getInstance(converter));
		} catch (Exception e) {
			throw new JspException(e);
		}
	}

	/**
	 * @see com.cc.framework.taglib.ConversionSupportTag#assignConverter(com.cc.framework.convert.Converter)
	 */
	public void assignConverter(Converter converter) {
		getTextDesignModel().setConverter(converter);
	}

	/**
	 * Adds a Tool Button to the Text Element
	 *
	 * @see com.cc.framework.taglib.ButtonContainerTag#addButton(com.cc.framework.ui.control.Control)
	 */
	public void addButton(Control button) {
		getCtrl().addButton(button);
	}
}