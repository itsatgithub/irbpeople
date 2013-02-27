/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/control/CalendarControl.java,v 1.17 2005/08/24 17:51:18 P001002 Exp $
 * $Revision: 1.17 $
 * $Date: 2005/08/24 17:51:18 $
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

import com.cc.framework.ui.model.CalendarDesignModel;
import com.cc.framework.ui.model.CalendarMode;

/**
 * The calendar makes it easy to fill out date value field(s)
 * by selecting the date from a popup calendar.
 *
 * @author      <a href="mailto:gschulz@scc-gmbh.com">Gernot Schulz</a>
 * @version     $Revision: 1.17 $
 * @since       1.0
 */
public class CalendarControl extends TextControl {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -2891575196112402364L;

	/**
	 * Contructor for CalendarControl
	 *
	 * @param designModel	TextDesignModel
	 */
	public CalendarControl(CalendarDesignModel designModel) {
		super(designModel);
	}

	/**
	 * @return		returns the HTML-Tamplate to render the Control
	 */
	public String getLayout() {
		return ((CalendarDesignModel) getDesignModel()).getLayout();
	}

	/**
	 * @return		returns the Date Format
	 */
	public String getFormat() {
		return ((CalendarDesignModel) getDesignModel()).getFormat();
	}

	/**
	 * @return		returns where to display the format mask
	 */
	public String getShowFormat() {
		return ((CalendarDesignModel) getDesignModel()).getShowFormat();
	}

	/**
	 * @return	returns the alt attribute for the calendar icon
	 */
	public String getButtonAlt() {
		return ((CalendarDesignModel) getDesignModel()).getButtonAlt();
	}

	/**
	 * @return	returns the tooltip for the calendar icon
	 */
	public String getButtonTooltip() {
		return ((CalendarDesignModel) getDesignModel()).getButtonTooltip();
	}

	/**
	 * @return  the state if the button should be displayed
	 */
	public boolean getShowButton() {
		return ((CalendarDesignModel) getDesignModel()).getShowButton();
	}

	/**
	 * Returns the mode in which the user can invoke the calendar.
	 * 
	 * @return The mode
	 */
	public CalendarMode getMode() {
		return ((CalendarDesignModel) getDesignModel()).getMode();
	}
}
