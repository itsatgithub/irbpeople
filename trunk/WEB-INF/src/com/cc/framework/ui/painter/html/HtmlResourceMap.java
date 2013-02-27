/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/html/HtmlResourceMap.java,v 1.23 2005/08/14 17:50:14 P001002 Exp $
 * $Revision: 1.23 $
 * $Date: 2005/08/14 17:50:14 $
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

package com.cc.framework.ui.painter.html;

import com.cc.framework.ui.model.ImageModel;
import com.cc.framework.ui.model.imp.ImageModelImp;
import com.cc.framework.ui.painter.ResourceMapImp;

/**
 *
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.23 $
 */
public class HtmlResourceMap extends ResourceMapImp implements HtmlResources {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1613252723486490219L;

	/**
	 * The base directory used for resources by this Painterfactory
	 */
	private static final String RESOURCE_DIR = "../fw/html/";

	/**
	 * Constructor
	 */
	public HtmlResourceMap() {
		super();

		setResourceDir(RESOURCE_DIR);
	}

	/**
	 * @see com.cc.framework.ui.painter.ResourceMapImp#doRegisterImages()
	 */
	protected void doRegisterImages() {
		registerImage(IMAGE_CALENDAR_1,			createImage("imgCalendar1.gif",				16, 15));
		registerImage(IMAGE_CALENDAR_2,			createImage("imgCalendar2.gif",				16, 15));
		registerImage(IMAGE_COLORTABLE_1,		createImage("imgColortable1.gif",			19, 19));
		registerImage(IMAGE_COLORTABLE_2,		createImage("imgColortable2.gif",			19, 19));
		registerImage(IMAGE_TEXTPOPUP_1,		createImage("imgTxtPopup1.gif",				15, 15));
		registerImage(IMAGE_TEXTPOPUP_2,		createImage("imgTxtPopup2.gif",				15, 15));

		registerImage(BUTTON_SPIN_DOWN_1,		createImage("buttons/btnSpinDown1.gif",		15, 17));
		registerImage(BUTTON_SPIN_DOWN_2,		createImage("buttons/btnSpinDown2.gif",		15, 17));
		registerImage(BUTTON_SPIN_UP_1,			createImage("buttons/btnSpinUp1.gif",		15, 17));
		registerImage(BUTTON_SPIN_UP_2,			createImage("buttons/btnSpinUp2.gif",		15, 17));

		// SwapSelect orientation - horizontal
		registerImage(BUTTON_SWAP_ADD_H1,		createImage("sws/btnAdd_H1.gif",			15, 15));
		registerImage(BUTTON_SWAP_ADD_H2,		createImage("sws/btnAdd_H2.gif",			15, 15));
		registerImage(BUTTON_SWAP_ADDALL_H1,	createImage("sws/btnAddAll_H1.gif",			15, 15));
		registerImage(BUTTON_SWAP_ADDALL_H2,	createImage("sws/btnAddAll_H2.gif",			15, 15));
		registerImage(BUTTON_SWAP_REMOVE_H1,	createImage("sws/btnRemove_H1.gif",			15, 15));
		registerImage(BUTTON_SWAP_REMOVE_H2,	createImage("sws/btnRemove_H2.gif",			15, 15));
		registerImage(BUTTON_SWAP_REMOVEALL_H1,	createImage("sws/btnRemoveAll_H1.gif",		15, 15));
		registerImage(BUTTON_SWAP_REMOVEALL_H2,	createImage("sws/btnRemoveAll_H2.gif",		15, 15));

		// SwapSelect orientation - vertical
		registerImage(BUTTON_SWAP_ADD_V1,		createImage("sws/btnAdd_V1.gif",			15, 15));
		registerImage(BUTTON_SWAP_ADD_V2,		createImage("sws/btnAdd_V2.gif",			15, 15));
		registerImage(BUTTON_SWAP_ADDALL_V1,	createImage("sws/btnAddAll_V1.gif",			15, 15));
		registerImage(BUTTON_SWAP_ADDALL_V2,	createImage("sws/btnAddAll_V2.gif",			15, 15));
		registerImage(BUTTON_SWAP_REMOVE_V1,	createImage("sws/btnRemove_V1.gif",			15, 15));
		registerImage(BUTTON_SWAP_REMOVE_V2,	createImage("sws/btnRemove_V2.gif",			15, 15));
		registerImage(BUTTON_SWAP_REMOVEALL_V1,	createImage("sws/btnRemoveAll_V1.gif",		15, 15));
		registerImage(BUTTON_SWAP_REMOVEALL_V2,	createImage("sws/btnRemoveAll_V2.gif",		15, 15));
	}

	/**
	 * @see com.cc.framework.ui.painter.ResourceMapImp#doRegisterColors()
	 */
	protected void doRegisterColors() {
		// No colors defined
	}

	/**
	 * @see com.cc.framework.ui.painter.ResourceMapImp#doRegisterStrings()
	 */
	protected void doRegisterStrings() {
		registerString(FW_TEXTAREA_MAXLENGTH_MESSAGE, "Characters remaining: <b>{0}</b>/{1}");

		// Default String resources for the calendar/colorpicker
		registerString(FW_CALENDAR_BUTTON_OK_LABEL,		"OK");
		registerString(FW_CALENDAR_BUTTON_OK_WIDTH,		"60");
		registerString(FW_CALENDAR_BUTTON_CANCEL_LABEL,	"Cancel");
		registerString(FW_CALENDAR_BUTTON_CANCEL_WIDTH,	"60");
		registerString(FW_CALENDAR_BUTTON_TODAY_LABEL,	"Today");
		registerString(FW_CALENDAR_WINDOW_TITLE,		"Datetime Picker");
		registerString(FW_CALENDAR_WINDOW_WIDTH,		"350");
		registerString(FW_CALENDAR_WINDOW_HEIGHT,		"250");

		registerString(FW_COLORPICKER_WINDOW_TITLE,		"Color Picker");

		// Register Java Script Files
		registerString(HTML_FILE_JSP_CALENDAR,		expandFileName("calendar/layout1/calendar.jsp"));
	}

	/**
	 * Expands a file name
	 *
	 * @param		src Fielename
	 * @return		Expanded filename
	 */
	protected String expandFileName(String src) {
		StringBuffer fullPath = new StringBuffer()
			.append(getResourceDir())
			.append(src);

		return fullPath.toString();
	}

	/**
	 * Creates a image model
	 *
	 * @param		src Image path relative to the painter
	 * @param		width Width
	 * @param		height Height
	 * @return		Image Model
	 */
	protected ImageModel createImage(String src, int width, int height) {
		StringBuffer fullPath = new StringBuffer()
			.append(getResourceDir())
			.append("image/")
			.append(src);

		return new ImageModelImp(fullPath.toString(), width, height);
	}
}