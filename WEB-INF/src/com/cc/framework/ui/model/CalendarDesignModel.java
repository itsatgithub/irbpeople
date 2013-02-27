/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/CalendarDesignModel.java,v 1.10 2005/08/24 17:51:06 P001002 Exp $
 * $Revision: 1.10 $
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

package com.cc.framework.ui.model;

/**
 * The design model for the calendar control The design model holds information
 * for rendering and the layout of the control.
 * 
 * @author <a href="mailto:gschulz@scc-gmbh.com">Gernot Schulz</a>
 * @version $Revision: 1.10 $
 * @since 1.3
 */
public interface CalendarDesignModel extends TextDesignModel {

	/**
	 * @return The HTML-Layout used to render the Control
	 */
	public String getLayout();

	/**
	 * Sets the HTML-Layout which configures the Control
	 * 
	 * @param layout
	 *            URI of the HTML Layout
	 */
	public void setLayout(String layout);

	/**
	 * Returns the date and time format mask
	 * 
	 * @return returns the Date Format
	 */
	public String getFormat();

	/**
	 * Sets the date and format mask like "yyyy.mm.dd HH.mm.ss"
	 * 
	 * @param format
	 *            The date and time format
	 */
	public void setFormat(String format);

	/**
	 * Specifies if and where the mask should be displayed
	 * 
	 * <ul>
	 * <li>none (default) = displays no format mask</li>
	 * <li>right = displays the format mask behind the icon</li>
	 * <li>bottom = displays the format mask under the input field</li>
	 * </ul>
	 * 
	 * @param align
	 *            Alignment
	 */
	public void setShowFormat(String align);

	/**
	 * Returns the alignemnt for the mask if displayed
	 * 
	 * @return The alignment type
	 */
	public String getShowFormat();

	/**
	 * Sets the tooltip for the calendar image
	 * 
	 * @param tooltip
	 *            The tooltip
	 */
	public void setButtonTooltip(String tooltip);

	/**
	 * Returns the tooltip for the calendar image
	 * 
	 * @return The tooltip
	 */
	public String getButtonTooltip();

	/**
	 * Sets the alt attribute for the calendar image
	 * 
	 * @param alt
	 *            The alt sttribute if no image is displayed
	 */
	public void setButtonAlt(String alt);

	/**
	 * Returns the alt attribute for the calendar image
	 * 
	 * @return The alt sttribute if no image is displayed
	 */
	public String getButtonAlt();

	/**
	 * Shows the button behind the input field to open the calendar
	 * 
	 * @param show
	 *            The State: <code>true</code> if the button should be
	 *            displayed; <code>false</code> otherwise
	 */
	public void setShowButton(boolean show);

	/**
	 * Returns if Button for the PopUp should be displayed
	 * 
	 * @return <code>true</code> if the button should be displayed;
	 *         <code>false</code> otherwise
	 */
	public boolean getShowButton();

	/**
	 * Sets the mode in which the user can invoke the calendar.
	 * 
	 * @param mode
	 *            The mode
	 */
	public void setMode(CalendarMode mode);

	/**
	 * Returns the mode in which the user can invoke the calendar.
	 * 
	 * @return The mode
	 */
	public CalendarMode getMode();
}