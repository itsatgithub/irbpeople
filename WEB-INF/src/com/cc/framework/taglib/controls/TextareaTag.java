/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/controls/TextareaTag.java,v 1.28 2005/11/13 13:39:39 P001002 Exp $
 * $Revision: 1.28 $
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
import com.cc.framework.taglib.ConversionSupportTag;
import com.cc.framework.taglib.TagHelp;
import com.cc.framework.ui.control.Control;
import com.cc.framework.ui.control.TextareaControl;
import com.cc.framework.ui.model.ControlDesignModel;
import com.cc.framework.ui.model.TextareaDesignModel;
import com.cc.framework.ui.model.imp.TextareaDesignModelImp;

/**
 * Tag-Handler for the Textare-Formularelement
 *
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version $Revision: 1.28 $
 * @since 1.0
 */
public class TextareaTag extends BaseControlTag implements ConversionSupportTag {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -4261802427186648757L;

	/**
	 * Constructor
	 */
	public TextareaTag() {
		super();
	}

	/**
	 * @see com.cc.framework.taglib.controls.BaseControlTag#doCreateDesignModel()
	 */
	protected ControlDesignModel doCreateDesignModel() {
		return new TextareaDesignModelImp();
	}

	/**
	 * Returns a casted version of the ControlDesignModel
	 *
	 * @return TextareaDesignModel
	 */
	protected TextareaDesignModel getTextareaDesignModel() {
		return (TextareaDesignModel) getDesignModel();
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
		TextareaControl ctrl = new TextareaControl(getTextareaDesignModel());

		Object value = lookupBean();

		if (value != null) {
			ctrl.setValue(value);
		}

		return ctrl;
	}

	/**
	 * Specifies the number of columns for the textarea
	 *
	 * @param cols
	 *            The number of columns
	 * @throws JspException
	 *             if the Argument can not be converted into a integer
	 */
	public void setCols(String cols) throws JspException {
		getTextareaDesignModel().setColumns(TagHelp.toInt(cols));
	}

	/**
	 * Specifies the number of rows for the textarea
	 *
	 * @param rows
	 *            The number of lines
	 * @throws JspException
	 *             if the Argument can not be converted into a integer
	 */
	public void setRows(String rows) throws JspException {
		getTextareaDesignModel().setRows(TagHelp.toInt(rows));
	}

	/**
	 * Specifies the readonly attribute
	 *
	 * @param readonly
	 *            <code>true</code> if the textarea should be rendered as
	 *            readonly
	 * @throws JspException
	 *             if the Argument can not be converted into a boolean value
	 */
	public void setReadonly(String readonly) throws JspException {
		getTextareaDesignModel().setReadonly(TagHelp.toBoolean(readonly));
	}

	/**
	 *
	 * @param maxlength
	 *            The maximal length for the input value
	 * @throws JspException
	 *             if the Argument can not be converted into a integer
	 */
	public void setMaxlength(String maxlength) throws JspException {
		getTextareaDesignModel().setMaxLength(TagHelp.toInt(maxlength));
	}

	/**
	 * The WRAP attribute can be used to specify how to handle word-wrapping
	 * display in text input areas in forms.
	 *
	 * @param wrap
	 *            Word Wrapping
	 */
	public void setWrap(String wrap) {
		getTextareaDesignModel().setWrap(wrap);
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
		getTextareaDesignModel().setConverter(converter);
	}
}