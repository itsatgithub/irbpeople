/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/imp/CalendarDesignModelImp.java,v 1.12 2005/08/24 17:51:06 P001002 Exp $
 * $Revision: 1.12 $
 * $Date: 2005/08/24 17:51:06 $
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

import com.cc.framework.ui.model.CalendarDesignModel;
import com.cc.framework.ui.model.CalendarMode;

/**
 * The design model for the Calendar control
 *
 * @author      <a href="mailto:gschulz@scc-gmbh.com">Gernot Schulz</a>
 * @version     $Revision: 1.12 $
 * @since       1.3
 */
public class CalendarDesignModelImp extends TextDesignModelImp implements CalendarDesignModel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 7021192690438115685L;

	/**
	 * The default format mask if none specified
	 */
	public static final String DEFAULT_FORMATMASK = "yyyy-mm-dd";

	/**
	 * Date Format
	 */
	private String format = null;

	/**
	 * The HTML/JSP Template to use
	 */
	private String layout = null;

	/**
	 * Specifies if and where the format
	 * mask should be displayed
	 */
	private String showFormat = null;

	/**
	 * An additional tooltip displayed
	 * if the user moves the mose over
	 * the Calendar icon
	 */
	private String buttonTooltip = null;

	/**
	 * The alt attribute. Specifies
	 * the text if no image for the calendar
	 * is displayed.
	 */
	private String buttonAlt = null;

	/**
	 * If this flag is set to false the
	 * button behind the input field will be hidden
	 */
	private boolean showButton = true;

	/**
	 * The mode controls how the calendar window should
	 * behave.
	 */
	private CalendarMode mode = CalendarMode.POPUP;

	// ----------------------------------
	//        methods
	// ----------------------------------

	/**
	 * Constructor for ColorPickerDesignModelImp
	 */
	public CalendarDesignModelImp() {
		super();
	}

	/**
	 * @see com.cc.framework.ui.model.CalendarDesignModel#getFormat()
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * @see com.cc.framework.ui.model.CalendarDesignModel#getLayout()
	 */
	public String getLayout() {
		return layout;
	}

	/**
	 * @see com.cc.framework.ui.model.CalendarDesignModel#setFormat(java.lang.String)
	 */
	public void setFormat(String format) {
		this.format = format;
	}

	/**
	 * @see com.cc.framework.ui.model.CalendarDesignModel#setLayout(java.lang.String)
	 */
	public void setLayout(String layout) {
		this.layout = layout;
	}

	/**
	 * @see com.cc.framework.ui.model.CalendarDesignModel#getShowFormat()
	 */
	public String getShowFormat() {
		return showFormat;
	}

	/**
	 * @see com.cc.framework.ui.model.CalendarDesignModel#setShowFormat(java.lang.String)
	 */
	public void setShowFormat(String showFormat) {
		this.showFormat = showFormat;
	}

	/**
	 * @see com.cc.framework.ui.model.CalendarDesignModel#setButtonAlt(java.lang.String)
	 */
	public void setButtonAlt(String buttonAlt) {
		this.buttonAlt = buttonAlt;
	}

	/**
	 * @see com.cc.framework.ui.model.CalendarDesignModel#setButtonTooltip(java.lang.String)
	 */
	public void setButtonTooltip(String buttonTooltip) {
		this.buttonTooltip = buttonTooltip;
	}

	/**
	 * @see com.cc.framework.ui.model.CalendarDesignModel#getButtonTooltip()
	 */
	public String getButtonTooltip() {
		return buttonTooltip;
	}

	/**
	 * @see com.cc.framework.ui.model.CalendarDesignModel#getButtonAlt()
	 */
	public String getButtonAlt() {
		return buttonAlt;
	}

	/**
	 * @see com.cc.framework.ui.model.CalendarDesignModel#setShowButton(boolean)
	 */
	public void setShowButton(boolean show) {
		this.showButton = show;
	}

	/**
	 * @see com.cc.framework.ui.model.CalendarDesignModel#getShowButton()
	 */
	public boolean getShowButton() {
		return showButton;
	}

	/**
	 * @see com.cc.framework.ui.model.CalendarDesignModel#getMode()
	 */
	public CalendarMode getMode() {
		return mode;
	}

	/**
	 * @see com.cc.framework.ui.model.CalendarDesignModel#setMode(com.cc.framework.ui.model.CalendarMode)
	 */
	public void setMode(CalendarMode mode) {
		this.mode = mode;
	}
}