/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/imp/ColorPickerDesignModelImp.java,v 1.9 2005/07/08 14:19:42 P001002 Exp $
 * $Revision: 1.9 $
 * $Date: 2005/07/08 14:19:42 $
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

package com.cc.framework.ui.model.imp;

import com.cc.framework.ui.model.ColorPickerDesignModel;

/**
 * The design model for the ColorPicker control
 *
 * @author      <a href="mailto:gschulz@scc-gmbh.com">Gernot Schulz</a>
 * @version     $Revision: 1.9 $
 * @since       1.0
 */
public class ColorPickerDesignModelImp extends TextDesignModelImp implements ColorPickerDesignModel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -7327402111816669719L;

	/**
	 * The palette to initalize the popup window
	 * if none palette is selected the default palette
	 * (0 = Web safe palette will be used)
	 * @see com.cc.framework.taglib.controls.ColorPickerTag
	 */
	private int palette = 0;

	/**
	 * An additional tooltip displayed
	 * if the user moves the mose over
	 * the ColorPicker icon
	 */
	private String buttonTooltip = null;

	/**
	 * The alt attribute. Specifies
	 * the text if no image for the calendar
	 * is displayed.
	 */
	private String buttonAlt = null;


	/**
	 * Constructor for ColorPickerDesignModelImp
	 */
	public ColorPickerDesignModelImp() {
		super();
	}

	/**
	 * @see com.cc.framework.ui.model.ColorPickerDesignModel#setPalette(int)
	 */
	public void setPalette(int palette) {
		this.palette = palette;
	}

	/**
	 * @see com.cc.framework.ui.model.ColorPickerDesignModel#getPalette()
	 */
	public int getPalette() {
		return palette;
	}

	/**
	 * @see com.cc.framework.ui.model.ColorPickerDesignModel#setButtonAlt(java.lang.String)
	 */
	public void setButtonAlt(String buttonAlt) {
		this.buttonAlt = buttonAlt;
	}

	/**
	 * @see com.cc.framework.ui.model.ColorPickerDesignModel#setButtonTooltip(java.lang.String)
	 */
	public void setButtonTooltip(String buttonTooltip) {
		this.buttonTooltip = buttonTooltip;
	}

	/**
	 * @see com.cc.framework.ui.model.ColorPickerDesignModel#getButtonTooltip()
	 */
	public String getButtonTooltip() {
		return buttonTooltip;
	}

	/**
	 * @see com.cc.framework.ui.model.ColorPickerDesignModel#getButtonAlt()
	 */
	public String getButtonAlt() {
		return buttonAlt;
	}
}
