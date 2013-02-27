/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/controls/CalendarTag.java,v 1.23 2005/09/27 14:06:23 P001002 Exp $
 * $Revision: 1.23 $
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

import com.cc.framework.common.InvalidEnumType;
import com.cc.framework.taglib.TagHelp;
import com.cc.framework.ui.control.CalendarControl;
import com.cc.framework.ui.control.Control;
import com.cc.framework.ui.model.CalendarDesignModel;
import com.cc.framework.ui.model.CalendarMode;
import com.cc.framework.ui.model.ControlDesignModel;
import com.cc.framework.ui.model.imp.CalendarDesignModelImp;

/**
 * Tag-Handler for the calendar form element.
 * <p>
 * The calendar makes it easy to fill out date value field(s) by selecting the
 * date from a popup calendar.
 * 
 * @author <a href="mailto:gschulz@scc-gmbh.com">Gernot Schulz</a>
 * @version $Revision: 1.23 $
 * @since 1.0
 */
public class CalendarTag extends TextTag {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -1576946500398557781L;

	/**
	 * Constructor for CalendarTag
	 */
	public CalendarTag() {
		super();
	}

	/**
	 * @see com.cc.framework.taglib.controls.BaseControlTag#doCreateDesignModel()
	 */
	protected ControlDesignModel doCreateDesignModel() {
		return new CalendarDesignModelImp();
	}

	/**
	 * Returns a casted version of the ControlDesignModel
	 * 
	 * @return CalendarDesignModel
	 */
	protected CalendarDesignModel getCalendarDesignModel() {
		return (CalendarDesignModel) getDesignModel();
	}

	/**
	 * @see com.cc.framework.taglib.controls.BaseControlTag#doCreateControl()
	 */
	protected Control doCreateControl() throws JspException {
		// get the datamodel
		Object value = lookupBean();

		// assign a new control
		CalendarControl ctrl = new CalendarControl(getCalendarDesignModel());

		if (value != null) {
			ctrl.setValue(value);
		}

		return ctrl;
	}

	/**
	 * Sets the HTML-Layout which configures the Control
	 * 
	 * @param layout
	 *            URI of the HTML Layout
	 */
	public void setLayout(String layout) {
		getCalendarDesignModel().setLayout(layout);
	}

	/**
	 * Sets the date and format mask like "yyyy.mm.dd HH.mm.ss"
	 * 
	 * @param format
	 *            The date and time format
	 */
	public void setFormat(String format) {
		getCalendarDesignModel().setFormat(format);
	}

	/**
	 * Specifies if and where the mask should be displayed
	 * 
	 * <ul>
	 * <li>none (default) = displays no format mask</li>
	 * <li>right = displays the format mask behind the icon</li>
	 * <li>bottom = displays the format mask under the input field</li>
	 * </ul>
	 * 
	 * @param showFormat
	 *            Format
	 */
	public void setShowformat(String showFormat) {
		getCalendarDesignModel().setShowFormat(showFormat);
	}

	/**
	 * Sets the tooltip for the calendar image
	 * 
	 * @param tooltip
	 *            The tooltip
	 */
	public void setButtonTooltip(String tooltip) {
		getCalendarDesignModel().setButtonTooltip(tooltip);
	}

	/**
	 * Sets the alt attribute for the calendar image
	 * 
	 * @param alt
	 *            The alt sttribute if no image is displayed
	 */
	public void setButtonAlt(String alt) {
		getCalendarDesignModel().setButtonAlt(alt);
	}

	/**
	 * Shows the button behind the input field to open the calendar
	 * 
	 * @param show
	 *            The State: <code>true</code> if the button should be
	 *            displayed; <code>false</code> otherwise
	 * @throws JspException
	 *             If the Argument can't be converted to boolean
	 */
	public void setShowButton(String show) throws JspException {
		getCalendarDesignModel().setShowButton(TagHelp.toBoolean(show));
	}

	/**
	 * Sets the mode in which the user can invoke the calendar.
	 * 
	 * @param mode
	 *            The mode
	 * @throws JspException
	 *             If the Argument can't be converted to a valid CalendarMode
	 */
	public void setMode(String mode) throws JspException {
		try {
			getCalendarDesignModel().setMode(CalendarMode.parse(mode));
		} catch (InvalidEnumType iet) {
			throw new JspException(iet);
		}
	}
}