/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/control/ColorPickerControl.java,v 1.13 2005/07/08 14:18:14 P001002 Exp $
 * $Revision: 1.13 $
 * $Date: 2005/07/08 14:18:14 $
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

package com.cc.framework.ui.control;

import com.cc.framework.ui.model.ColorPickerDesignModel;
import com.cc.framework.ui.model.TextDesignModel;

/**
 * The color picker makes it easy to fill out color value field(s)
 * by selecting the color from a popup palette.
 *
 * @author      <a href="mailto:gschulz@scc-gmbh.com">Gernot Schulz</a>
 * @version     $Revision: 1.13 $
 * @since       1.0
 */
public class ColorPickerControl extends TextControl {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -4838139871236402441L;

	/**
	 * Constructor for ColorPickerControl
	 *
	 * @param	designModel	TextDesignModel
	 */
	public ColorPickerControl(TextDesignModel designModel) {
		super(designModel);
	}

	/**
	 * @return	returns the alt attribute for the icon
	 */
	public String getButtonAlt() {
		return ((ColorPickerDesignModel) getDesignModel()).getButtonAlt();
	}

	/**
	 * @return	returns the tooltip for the icon
	 */
	public String getButtonTooltip() {
		return ((ColorPickerDesignModel) getDesignModel()).getButtonTooltip();
	}
}
