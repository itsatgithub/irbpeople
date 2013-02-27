/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/controls/ColorPickerTag.java,v 1.17 2005/09/27 14:06:23 P001002 Exp $
 * $Revision: 1.17 $
 * $Date: 2005/09/27 14:06:23 $
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
import com.cc.framework.ui.control.ColorPickerControl;
import com.cc.framework.ui.control.Control;
import com.cc.framework.ui.model.ColorPickerDesignModel;
import com.cc.framework.ui.model.ControlDesignModel;
import com.cc.framework.ui.model.imp.ColorPickerDesignModelImp;

/**
 * Tag-Handler for the color picker form element. <br>
 * The color picker makes it easy to fill out color value field(s) by selecting
 * the color from a popup palette.<br>
 * The color can be selected from three available palettes. <br>
 * To specify the palette to use, choose one of the following values:
 * <ul>
 * <li>0 = Web safe palette</li>
 * <li>1 = Windows system palette</li>
 * <li>2 = grayscale palette</li>
 * </ul>
 * 
 * @author <a href="mailto:gschulz@scc-gmbh.com">Gernot Schulz</a>
 * @version $Revision: 1.17 $
 * @since 1.0
 */
public class ColorPickerTag extends TextTag {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 2742205383643364693L;

	/**
	 * Constructor for ColorPickerTag
	 */
	public ColorPickerTag() {
		super();
	}

	/**
	 * @see com.cc.framework.taglib.controls.BaseControlTag#doCreateDesignModel()
	 */
	protected ControlDesignModel doCreateDesignModel() {
		return new ColorPickerDesignModelImp();
	}

	/**
	 * Returns a casted version of the ControlDesignModel
	 * 
	 * @return CheckboxDesignModel
	 */
	protected ColorPickerDesignModel getColorPickerDesignModel() {
		return (ColorPickerDesignModel) getDesignModel();
	}

	/**
	 * @see com.cc.framework.taglib.controls.BaseControlTag#doCreateControl()
	 */
	protected Control doCreateControl() throws JspException {
		// get the datamodel
		Object value = lookupBean();

		// assign a new control
		ColorPickerControl ctrl = new ColorPickerControl(
				getColorPickerDesignModel());

		if (value != null) {
			ctrl.setValue(value);
		}

		return ctrl;
	}

	/**
	 * Specify the palette to us. If none is selected the Web safe palette will
	 * be used by default.
	 * 
	 * @param palette
	 *            The preffered palette.
	 * @throws JspException
	 *             If the Argument can't be converted to int
	 */
	public void setPalette(String palette) throws JspException {
		try {
			int selpalette = TagHelp.toInt(palette);

			getColorPickerDesignModel().setPalette(selpalette);
		} catch (NumberFormatException nfe) {
			// no action
		}
	}

	/**
	 * Sets the tooltip for the colorpicker image
	 * 
	 * @param tooltip
	 *            The tooltip
	 */
	public void setButtonTooltip(String tooltip) {
		getColorPickerDesignModel().setButtonTooltip(tooltip);
	}

	/**
	 * Sets the alt attribute for the colorpicker image
	 * 
	 * @param alt
	 *            The alt sttribute if no image is displayed
	 */
	public void setButtonAlt(String alt) {
		getColorPickerDesignModel().setButtonAlt(alt);
	}
}