/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/def/DefResourceMap.java,v 1.59 2005/11/13 14:03:56 P001002 Exp $
 * $Revision: 1.59 $
 * $Date: 2005/11/13 14:03:56 $
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

package com.cc.framework.ui.painter.def;

import com.cc.framework.ui.model.ImageModel;
import com.cc.framework.ui.model.imp.ImageModelImp;
import com.cc.framework.ui.painter.ResourceMapImp;

/**
 * The DefResourceMap registers all images that are needed by
 * the DefaulPainter.
 *
 * @author	  <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version	  $Revision: 1.59 $
 * @since     1.2
 */
public class DefResourceMap extends ResourceMapImp implements DefResources {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 8489784865655931461L;

	/**
	 * The base directory used for resources by this Painterfactory
	 */
	private static final String RESOURCE_DIR = "../fw/def/";

	/**
	 * Constructor
	 */
	public DefResourceMap() {
		super();

		setResourceDir(RESOURCE_DIR);
	}


	/**
	 * @see com.cc.framework.ui.painter.ResourceMapImp#doRegisterImages()
	 */
	protected void doRegisterImages() {

		// define the resources to use by this painter
		registerImage(IMAGE_SPACER,					createImage("spacer.gif",						1, 1));
		registerImage(IMAGE_SEPARATOR,				createImage("separator.gif",					8, 1));
		registerImage(IMAGE_BULLET,					createImage("bullet.gif",						6, 15));
		registerImage(IMAGE_MAGNIFIER,				createImage("magnifier.gif",					20, 20));
		registerImage(IMAGE_ERROR,					createImage("error.gif",						12, 25));
		registerImage(IMAGE_WARNING,				createImage("warning.gif",						12, 25));
		registerImage(IMAGE_INFORMATION,			createImage("info.gif",							14, 23));
		registerImage(IMAGE_ERROR_INPUT,			createImage("errInput.gif",						16, 16));
		registerImage(IMAGE_MESSAGE_INPUT,			createImage("msgInput.gif",						16, 16));
		registerImage(IMAGE_HEADER_TOP,				createImage("headertop.gif",					9, 17));
		registerImage(IMAGE_HEADER_BOTTOM,			createImage("headerbottom.gif",					9, 17));
		registerImage(IMAGE_DOT_COLOR,				createImage("dots/dot_{0}.gif",					5, 5));
		registerImage(IMAGE_HAND,					createImage("imgHandRight.gif",					43, 22));
		registerImage(IMAGE_LINK_EXTERNAL,			createImage("lnkExternal.gif",					12, 9));
		registerImage(IMAGE_REQUIRED,				createImage("required.gif",						9, 13));
		registerImage(IMAGE_HELP,					createImage("help.gif",							15, 15));

		registerImage(IMAGE_SEV_FATAL,				createImage("severity/imgFatal13x13.gif",		13, 13));
		registerImage(IMAGE_SEV_ERROR,				createImage("severity/imgError13x13.gif",		13, 13));
		registerImage(IMAGE_SEV_WARN,				createImage("severity/imgWarning13x13.gif",		13, 13));
		registerImage(IMAGE_SEV_INFO,				createImage("severity/imgInfo13x13.gif",		13, 13));
		registerImage(IMAGE_SEV_QUESTION,			createImage("severity/imgQuestion13x13.gif",	13, 13));
		registerImage(IMAGE_SEV_NONE,				createImage("severity/imgNone13x13.gif",		13, 13));

		// buttons scheduler
		registerImage(BUTTON_SCHEDULER_CREATE1,		createImage("scheduler/btnCreate1.gif",			16, 15));
		registerImage(BUTTON_SCHEDULER_DAY1,		createImage("scheduler/btnDay1.gif",			16, 15));
		registerImage(BUTTON_SCHEDULER_WORKWEEK1,	createImage("scheduler/btnWork1.gif",			16, 15));
		registerImage(BUTTON_SCHEDULER_WEEK1,		createImage("scheduler/btnWeek1.gif",			16, 15));
		registerImage(BUTTON_SCHEDULER_MONTH1,		createImage("scheduler/btnMonth1.gif",			16, 15));
		registerImage(BUTTON_SCHEDULER_YEAR1,		createImage("scheduler/btnYear1.gif",			16, 15));
		registerImage(BUTTON_SCHEDULER_PREV_1,		createImage("buttons/btnLeft1.gif",				15, 15));
		registerImage(BUTTON_SCHEDULER_NEXT_1,		createImage("buttons/btnRight1.gif",			15, 15));

		registerImage(SCHEDULER_PRIO_NONE,			createImage("scheduler/none.gif",				10, 16));
		registerImage(SCHEDULER_PRIO_LOW,			createImage("scheduler/low.gif",				11, 16));
		registerImage(SCHEDULER_PRIO_NORMAL,		createImage("scheduler/normal.gif",				7, 16));
		registerImage(SCHEDULER_PRIO_HIGH,			createImage("scheduler/high.gif",				7, 16));
		registerImage(SCHEDULER_RECURRING,			createImage("scheduler/recurring.gif",			16, 16));
		registerImage(SCHEDULER_ATTACHMENT,			createImage("scheduler/attachment.gif",			10, 16));
		registerImage(SCHEDULER_MORE_UP,			createImage("scheduler/more_u.gif",				20, 8));
		registerImage(SCHEDULER_MORE_DOWN,			createImage("scheduler/more_d.gif",				20, 8));
		registerImage(SCHEDULER_CLOCK,				createImage("scheduler/h{0}.gif",				15, 15));
		registerImage(SCHEDULER_PREV,				createImage("buttons/btnLeft1.gif",				15, 15));
		registerImage(SCHEDULER_NEXT,				createImage("buttons/btnRight1.gif",			15, 15));
		registerImage(SCHEDULER_ADD_APPOINTMENT,	createImage("icons/add.gif",					16, 16));

		// buttons listcontrol
		registerImage(BUTTON_NEXT_1,				createImage("buttons/btnNext1.gif",				15, 15));
		registerImage(BUTTON_NEXT_2,				createImage("buttons/btnNext2.gif",				15, 15));
		registerImage(BUTTON_FIRST_1,				createImage("buttons/btnFirst1.gif",			15, 15));
		registerImage(BUTTON_FIRST_2,				createImage("buttons/btnFirst2.gif",			15, 15));
		registerImage(BUTTON_LAST_1,				createImage("buttons/btnLast1.gif",				15, 15));
		registerImage(BUTTON_LAST_2,				createImage("buttons/btnLast2.gif",				15, 15));
		registerImage(BUTTON_PREVIOUS_1,			createImage("buttons/btnPrev1.gif",				15, 15));
		registerImage(BUTTON_PREVIOUS_2,			createImage("buttons/btnPrev2.gif",				15, 15));
		registerImage(BUTTON_CREATE_1,				createImage("buttons/btnCreate1.gif",			15, 15));
		registerImage(BUTTON_REFRESH_1,				createImage("buttons/btnRefresh1.gif",			15, 15));
		registerImage(BUTTON_HELP_1,				createImage("buttons/btnHelp1.gif",				15, 15));
		registerImage(BUTTON_MINIMIZE_1,			createImage("buttons/btnMin1.gif",				15, 15));
		registerImage(BUTTON_MAXIMIZE_1,			createImage("buttons/btnMax1.gif",				15, 15));
		registerImage(BUTTON_RESTORE_1,				createImage("buttons/btnRestore1.gif",			15, 15));
		registerImage(BUTTON_CLOSE_1,				createImage("buttons/btnClose1.gif",			15, 15));
		registerImage(BUTTON_EXPORT_1,				createImage("buttons/btnExport1.gif",			15, 15));
		registerImage(BUTTON_PRINT_1,				createImage("buttons/btnPrint1.gif",			15, 15));

		registerImage(BUTTON_SORTABLE,				createImage("buttons/btnSortable1.gif",			11, 13));
		registerImage(BUTTON_SORTASC,				createImage("buttons/btnSortUp1.gif",			11, 13));
		registerImage(BUTTON_SORTDESC,				createImage("buttons/btnSortDown1.gif",			11, 13));

		registerImage(BUTTON_CHECKALL_1,			createImage("buttons/btnChkAll1.gif",			11, 13));
		registerImage(BUTTON_UNCHECKALL_1,			createImage("buttons/btnUnchkAll1.gif",			11, 13));

		registerImage(BUTTON_COLLAPSE,				createImage("buttons/btnCollapse1.gif",			14, 14));
		registerImage(BUTTON_EXPAND,				createImage("buttons/btnExpand1.gif",			14, 14));

		// textbutton
		registerImage(TEXTBUTTON_BG_LEFT1,			createImage("buttons/btnTxtBgL1.gif",			16, 17));
		registerImage(TEXTBUTTON_BG_MIDDLE1,		createImage("buttons/btnTxtBgM1.gif",			15, 17));
		registerImage(TEXTBUTTON_BG_RIGHT1,			createImage("buttons/btnTxtBgR1.gif",			8, 17));

		registerImage(CORNER_TABLE_RIGHT,			createImage("corners/r.gif",					10, 17));
		registerImage(CORNER_TABLE_LEFT,			createImage("corners/l.gif",					10, 17));
		registerImage(CORNER_FORM_RIGHT,			createImage("corners/r.gif",					10, 17));
		registerImage(CORNER_FORM_LEFT,				createImage("corners/l.gif",					10, 17));
		registerImage(CORNER_FORMSEARCH_RIGHT,		createImage("corners/r.gif",					10, 17));
		registerImage(CORNER_FORMSEARCH_LEFT,		createImage("corners/l.gif",					10, 17));

		// corner
		registerImage(CORNER_PANEL_TL,				createImage("corners/l.gif",					10, 17));
		registerImage(CORNER_PANEL_TR,				createImage("corners/r.gif",					10, 17));
		registerImage(CORNER_PANEL_BL,				createImage("corners/bl.gif",					10, 8));
		registerImage(CORNER_PANEL_BR,				createImage("corners/br.gif",					10, 8));

		registerImage(CORNER_FORM_RIGHT_COLOR,		createImage("corners/r_{0}.gif",				10, 17));
		registerImage(CORNER_FORM_LEFT_COLOR,		createImage("corners/l_{0}.gif",				10, 17));

		// icons
		registerImage(ICON_ADD,						createImage("icons/add.gif",					16, 16));
		registerImage(ICON_EDIT,					createImage("icons/edit.gif",					16, 16));
		registerImage(ICON_INSERT,					createImage("icons/insert.gif",					16, 15));
		registerImage(ICON_DELETE,					createImage("icons/delete.gif",					16, 16));
		registerImage(ICON_SELECT,					createImage("icons/select.gif",					15, 15));
		registerImage(ICON_CHECK,					createImage("icons/check.gif",					16, 16));
		registerImage(ICON_CHECKED,					createImage("icons/checked.gif",				16, 16));

		// Tabset
		registerImage(TABSET_BACKGROUND,			createImage("tab/tab.gif",						-1, 19));
//		registerImage(TABSET_SEL_SEL,				createImage("tab/ss{0}.gif",					));
//		registerImage(TABSET_SEL_UNSEL,				createImage("tab/su{0}.gif",					));
//		registerImage(TABSET_SEL_DIS,				createImage("tab/sd{0}.gif",					));
		registerImage(TABSET_SEL_NONE,				createImage("tab/s_{0}.gif",					11, 19));
		registerImage(TABSET_SEL_BG,				createImage("tab/s_bg{0}.gif",					-1, 19));
//		registerImage(TABSET_UNSEL_SEL,				createImage("tab/us{0}.gif",					));
//		registerImage(TABSET_UNSEL_UNSEL,			createImage("tab/uu.gif",						));
//		registerImage(TABSET_UNSEL_DIS,				createImage("tab/ud.gif",						));
		registerImage(TABSET_UNSEL_NONE,			createImage("tab/u_.gif",						10, 19));
		registerImage(TABSET_UNSEL_BG,				createImage("tab/u_bg.gif",						-1, 19));
//		registerImage(TABSET_DIS_SEL,				createImage("tab/ds{0}.gif",					));
//		registerImage(TABSET_DIS_UNSEL,				createImage("tab/du.gif",						));
//		registerImage(TABSET_DIS_DIS,				createImage("tab/dd.gif",						));
		registerImage(TABSET_DIS_NONE,				createImage("tab/d_.gif",						10, 19));
		registerImage(TABSET_DIS_BG,				createImage("tab/d_bg.gif",						-1, 19));
		registerImage(TABSET_NONE_SEL,				createImage("tab/_s{0}.gif",					10, 19));
		registerImage(TABSET_NONE_UNSEL,			createImage("tab/_u.gif",						8, 19));
		registerImage(TABSET_NONE_DIS,				createImage("tab/_d.gif",						8, 19));

		// ScrollButtons for the Tabset
		registerImage(BUTTON_TABSET_PREVIOUS_1,		createImage("tab/btnArrow_left1.gif",			15, 15));
		registerImage(BUTTON_TABSET_PREVIOUS_2,		createImage("tab/btnArrow_left2.gif",			15, 15));
		registerImage(BUTTON_TABSET_NEXT_1,			createImage("tab/btnArrow_right1.gif",			15, 15));
		registerImage(BUTTON_TABSET_NEXT_2,			createImage("tab/btnArrow_right2.gif",			15, 15));

		registerImage(BUTTON_TABSET_MORE_PREVIOUS,	createImage("tab/tabMoreL.gif",					 9, 11));
		registerImage(BUTTON_TABSET_MORE_NEXT,		createImage("tab/tabMoreR.gif",					 9, 10));
		registerImage(BUTTON_TABSET_MORE_EMPTY,		createImage("tab/tabMoreL_empty.gif",			 9, 11));

		// Tabbar
		registerImage(TABBAR_BACKGROUND,			createImage("tab/tab.gif",						-1, 19));
//		registerImage(TABBAR_SEL_SEL,				createImage("tab/ss{0}.gif",					));
//		registerImage(TABBAR_SEL_UNSEL,				createImage("tab/su{0}.gif",					));
//		registerImage(TABBAR_SEL_DIS,				createImage("tab/sd{0}.gif",					));
		registerImage(TABBAR_SEL_NONE,				createImage("tab/s_{0}.gif",					11, 19));
		registerImage(TABBAR_SEL_BG,				createImage("tab/s_bg{0}.gif",					-1, 19));
//		registerImage(TABBAR_UNSEL_SEL,				createImage("tab/us{0}.gif",					));
//		registerImage(TABBAR_UNSEL_UNSEL,			createImage("tab/uu.gif",						));
//		registerImage(TABBAR_UNSEL_DIS,				createImage("tab/ud.gif",						));
		registerImage(TABBAR_UNSEL_NONE,			createImage("tab/u_.gif",						10, 19));
		registerImage(TABBAR_UNSEL_BG,				createImage("tab/u_bg.gif",						-1, 19));
//		registerImage(TABBAR_DIS_SEL,				createImage("tab/ds{0}.gif",					));
//		registerImage(TABBAR_DIS_UNSEL,				createImage("tab/du.gif",						));
//		registerImage(TABBAR_DIS_DIS,				createImage("tab/dd.gif",						));
		registerImage(TABBAR_DIS_NONE,				createImage("tab/d_.gif",						10, 19));
		registerImage(TABBAR_DIS_BG,				createImage("tab/d_bg.gif",						-1, 19));
		registerImage(TABBAR_NONE_SEL,				createImage("tab/_s{0}.gif",					10, 19));
		registerImage(TABBAR_NONE_UNSEL,			createImage("tab/_u.gif",						8, 19));
		registerImage(TABBAR_NONE_DIS,				createImage("tab/_d.gif",						8, 19));

		// ScrollButtons for the Tabbar
		registerImage(BUTTON_TABBAR_PREVIOUS_1,		createImage("tab/btnArrow_left1.gif",			15, 15));
		registerImage(BUTTON_TABBAR_PREVIOUS_2,		createImage("tab/btnArrow_left2.gif",			15, 15));
		registerImage(BUTTON_TABBAR_NEXT_1,			createImage("tab/btnArrow_right1.gif",			15, 15));
		registerImage(BUTTON_TABBAR_NEXT_2,			createImage("tab/btnArrow_right2.gif",			15, 15));

		registerImage(BUTTON_TABBAR_MORE_PREVIOUS,	createImage("tab/tabMoreL.gif",					 9, 11));
		registerImage(BUTTON_TABBAR_MORE_NEXT,		createImage("tab/tabMoreR.gif",					 9, 10));
		registerImage(BUTTON_TABBAR_MORE_EMPTY,		createImage("tab/tabMoreL_empty.gif",			 9, 11));

		registerImage(CHECKBOX_UNCHECKED,			createImage("check/15/cb0.gif",					15, 15));
		registerImage(CHECKBOX_CHECKED,				createImage("check/15/cb1.gif",					15, 15));

		registerImage(RADIO_UNCHECKED,				createImage("check/15/radio0.gif",				15, 15));
		registerImage(RADIO_CHECKED,				createImage("check/15/radio1.gif",				15, 15));

		// Crumbs Control
		registerImage(CRUMBS_SEL_SEL,				createImage("crumbs/ss.gif",					27, 18));
		registerImage(CRUMBS_SEL_UNSEL,				createImage("crumbs/su.gif",					27, 18));
		registerImage(CRUMBS_SEL_DIS,				createImage("crumbs/sd.gif",					27, 18));
		registerImage(CRUMBS_SEL_NONE,				createImage("crumbs/s_.gif",					19, 18));
		registerImage(CRUMBS_SEL_BG,				createImage("crumbs/s_bg.gif",					-1, 18));
		registerImage(CRUMBS_UNSEL_SEL,				createImage("crumbs/us.gif",					27, 18));
		registerImage(CRUMBS_UNSEL_UNSEL,			createImage("crumbs/uu.gif",					27, 18));
		registerImage(CRUMBS_UNSEL_DIS,				createImage("crumbs/ud.gif",					27, 18));
		registerImage(CRUMBS_UNSEL_NONE,			createImage("crumbs/u_.gif",					19, 18));
		registerImage(CRUMBS_UNSEL_BG,				createImage("crumbs/u_bg.gif",					-1, 18));
		registerImage(CRUMBS_DIS_SEL,				createImage("crumbs/ds.gif",					27, 18));
		registerImage(CRUMBS_DIS_UNSEL,				createImage("crumbs/du.gif",					27, 18));
		registerImage(CRUMBS_DIS_DIS,				createImage("crumbs/dd.gif",					27, 18));
		registerImage(CRUMBS_DIS_NONE,				createImage("crumbs/d_.gif",					19, 18));
		registerImage(CRUMBS_DIS_BG,				createImage("crumbs/d_bg.gif",					-1, 18));
		registerImage(CRUMBS_NONE_SEL,				createImage("crumbs/_s.gif",					9, 18));
		registerImage(CRUMBS_NONE_UNSEL,			createImage("crumbs/_u.gif",					9, 18));
		registerImage(CRUMBS_NONE_DIS,				createImage("crumbs/_d.gif",					9, 18));

		// 15 Pixel images
		registerImage(15, TREE_FOLDEROPEN,			createImage("tree/15/folderOpen.gif",			15, 15));
		registerImage(15, TREE_FOLDERCLOSED,		createImage("tree/15/folderClosed.gif",			15, 15));
		registerImage(15, TREE_ITEM,				createImage("tree/15/item.gif",					15, 15));
		registerImage(15, TREE_STRUCTURE,			createImage("tree/15/0.gif",					15, 15));
		registerImage(15, TREE_STRUCTURE_2,			createImage("tree/15/2.gif",					15, 15));
		registerImage(15, TREE_STRUCTURE_10,		createImage("tree/15/10.gif",					15, 15));
		registerImage(15, TREE_STRUCTURE_12,		createImage("tree/15/12.gif",					15, 15));
		registerImage(15, TREE_STRUCTURE_14,		createImage("tree/15/14.gif",					15, 15));
		registerImage(15, TREE_STRUCTURE_16,		createImage("tree/15/16.gif",					15, 15));
		registerImage(15, TREE_STRUCTURE_18,		createImage("tree/15/18.gif",					15, 15));
		registerImage(15, TREE_STRUCTURE_26,		createImage("tree/15/26.gif",					15, 15));
		registerImage(15, TREE_STRUCTURE_30,		createImage("tree/15/30.gif",					15, 15));
		registerImage(15, TREE_STRUCTURE_32,		createImage("tree/15/32.gif",					15, 15));
		registerImage(15, TREE_STRUCTURE_34,		createImage("tree/15/34.gif",					15, 15));
		registerImage(15, TREE_STRUCTURE_42,		createImage("tree/15/42.gif",					15, 15));
		registerImage(15, TREE_STRUCTURE_46,		createImage("tree/15/46.gif",					15, 15));

		registerImage(15, CHECKBOX_NONE,			createImage("spacer.gif",						15, 15));
		registerImage(15, CHECKBOX_INVALID,			createImage("check/15/cb.gif",					15, 15));
		registerImage(15, CHECKBOX_UNCHECKED,		createImage("check/15/cb0.gif",					15, 15));
		registerImage(15, CHECKBOX_CHECKED,			createImage("check/15/cb1.gif",					15, 15));
		registerImage(15, CHECKBOX_INDETERMINATE,	createImage("check/15/cb2.gif",					15, 15));

		// 20 Pixel images
		registerImage(20, TREE_FOLDEROPEN,			createImage("tree/20/folderOpen.gif",			16, 16));
		registerImage(20, TREE_FOLDERCLOSED,		createImage("tree/20/folderClosed.gif",			16, 16));
		registerImage(20, TREE_ITEM,				createImage("tree/20/item.gif",					16, 16));
		registerImage(20, TREE_STRUCTURE,			createImage("tree/20/0.gif",					20, 20));
		registerImage(20, TREE_STRUCTURE_2,			createImage("tree/20/2.gif",					20, 20));
		registerImage(20, TREE_STRUCTURE_10,		createImage("tree/20/10.gif",					20, 20));
		registerImage(20, TREE_STRUCTURE_12,		createImage("tree/20/12.gif",					20, 20));
		registerImage(20, TREE_STRUCTURE_14,		createImage("tree/20/14.gif",					20, 20));
		registerImage(20, TREE_STRUCTURE_16,		createImage("tree/20/16.gif",					20, 20));
		registerImage(20, TREE_STRUCTURE_18,		createImage("tree/20/18.gif",					20, 20));
		registerImage(20, TREE_STRUCTURE_26,		createImage("tree/20/26.gif",					20, 20));
		registerImage(20, TREE_STRUCTURE_30,		createImage("tree/20/30.gif",					20, 20));
		registerImage(20, TREE_STRUCTURE_32,		createImage("tree/20/32.gif",					20, 20));
		registerImage(20, TREE_STRUCTURE_34,		createImage("tree/20/34.gif",					20, 20));
		registerImage(20, TREE_STRUCTURE_42,		createImage("tree/20/42.gif",					20, 20));
		registerImage(20, TREE_STRUCTURE_46,		createImage("tree/20/46.gif",					20, 20));

		registerImage(20, CHECKBOX_NONE,			createImage("spacer.gif",						20, 20));
		registerImage(20, CHECKBOX_INVALID,			createImage("check/20/cb.gif",					20, 20));
		registerImage(20, CHECKBOX_UNCHECKED,		createImage("check/20/cb0.gif",					20, 20));
		registerImage(20, CHECKBOX_CHECKED,			createImage("check/20/cb1.gif",					20, 20));
		registerImage(20, CHECKBOX_INDETERMINATE,	createImage("check/20/cb2.gif",					20, 20));
	}

	/**
	 * @see com.cc.framework.ui.painter.ResourceMapImp#doRegisterStrings()
	 */
	protected void doRegisterStrings() {
		registerString(FW_EMPTY_GAUGE,				"no element");

		registerString(FW_ITEMS_NOENTRIES,			"no entries");
		registerString(FW_ITEMS_1TE,				"1 item");
		registerString(FW_ITEMS,					"{0} items");
		registerString(FW_ITEMS_RANGE,				"{0} to {1} of {2}");
		registerString(FW_ITEMS_INFINITE,			"{0} to {1} of many");
		registerString(FW_EMPTY_TEXT,				"No items in list!");

		registerString(FW_PAGE_NOENTRIES,			"no entries");
		registerString(FW_PAGE_1TE,					"page 1");
		registerString(FW_PAGE_RANGE,				"page {0} of {1} ");

		registerString(FW_TABSET_RANGE,				"{0} ... {1} from {2}");
		registerString(FW_TABBAR_RANGE,				"{0} ... {1} from {2}");

		registerString(FW_TOOLTIP_PAGE,				"goto page {0}");
		registerString(FW_TOOLTIP_FIRSTPAGE,		"goto first page");
		registerString(FW_TOOLTIP_PREVPAGE,			"goto previous page");
		registerString(FW_TOOLTIP_NEXTPAGE,			"goto next page");
		registerString(FW_TOOLTIP_LASTPAGE,			"goto last page");
		registerString(FW_TOOLTIP_REFRESH_LIST,		"refresh list");
		registerString(FW_TOOLTIP_CREATE_ITEM,		"create new item");

		registerString(FW_TOOLTIP_CHECKALL,			"check all items");
		registerString(FW_TOOLTIP_UNCHECKALL,		"uncheck all items");

		registerString(FW_FORM_REQUIRED,			"required input element");

		// Register Cascading Stylesheets
		registerString(FW_FILE_CSS_DEFAULT,			expandFileName("style/default.css"));

		// Register Java Script Files
		registerString(FW_FILE_JS_FUNCTIONS,		expandFileName("jscript/functions.js"));
		registerString(FW_FILE_JS_RESOURCEMAP,		expandFileName("jscript/resourcemap.js"));
		registerString(FW_FILE_JS_CONTROLS,			expandFileName("jscript/controls.js"));
		registerString(FW_FILE_JS_TABSET,			expandFileName("jscript/tabset.js"));
		registerString(FW_FILE_JS_LIST,				expandFileName("jscript/list.js"));
		registerString(FW_FILE_JS_TREE,				expandFileName("jscript/tree.js"));

	}

	/**
	 * @see com.cc.framework.ui.painter.ResourceMapImp#doRegisterColors()
	 */
	protected void doRegisterColors() {
		// We are using a ColorPalette Object
		// so we don't have to register any colors explicitly
		setColorPalette(new DefColorPalette());
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
}