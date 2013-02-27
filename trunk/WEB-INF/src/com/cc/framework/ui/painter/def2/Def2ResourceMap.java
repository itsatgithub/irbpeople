/**
 * $Header: d:/repository/cvs/cc-framework/painter/com/cc/framework/ui/painter/def2/Def2ResourceMap.java,v 1.29 2005/07/12 06:36:47 P001002 Exp $
 * $Revision: 1.29 $
 * $Date: 2005/07/12 06:36:47 $
 *
 * ====================================================================
 *
 * Copyright (c) 2002 SCC Informationssysteme GmbH. All rights
 * reserved.
 *
 * ====================================================================
 */

package com.cc.framework.ui.painter.def2;

import com.cc.framework.ui.painter.def.DefResourceMap;

/**
 * Resourcemap for the Def2Painter.
 * Defines Resources like images used by the Def2Painter.
 *
 * @author    <a href="mailto:gschulz@scc-gmbh.com">Gernot Schulz</a>
 * @version	  $Revision: 1.29 $
 * @since     1.2
 */
public class Def2ResourceMap extends DefResourceMap {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -6003819435979994559L;

	/**
	 * Base directory used for resource by this Painterfactory
	 */
	private static final String RESOURCE_DIR = "../fw/def2/";

	/**
	 * Constructor
	 */
	public Def2ResourceMap() {
		super();
		
		setResourceDir(RESOURCE_DIR);
	}

	/**
	 * @see com.cc.framework.ui.painter.ResourceMapImp#doRegisterImages()
	 */
	protected void doRegisterImages() {
		super.doRegisterImages();

		// define the resources to use by this painter
		registerImage(IMAGE_DOT_COLOR,				createImage("dots/dot_{0}.gif",				5, 5));
		registerImage(IMAGE_MAGNIFIER,				createImage("magnifier.gif",				20, 23));
		registerImage(IMAGE_HEADER_TOP,				createImage("headertop.gif",				9, 17));
		registerImage(IMAGE_HEADER_BOTTOM,			createImage("headerbottom.gif",				9, 17));

		// icons
		registerImage(ICON_ADD,						createImage("icons/add.gif",				16, 15));
		registerImage(ICON_EDIT,					createImage("icons/edit.gif",				16, 15));
		registerImage(ICON_DELETE,					createImage("icons/delete.gif",				16, 15));
		registerImage(ICON_SELECT,					createImage("icons/select.gif",				21, 16));
		
		// corner
		registerImage(CORNER_TABLE_LEFT,			createImage("corners/tl.gif",				10, 17));
		registerImage(CORNER_TABLE_RIGHT,			createImage("corners/tr.gif",				10, 17));
		registerImage(CORNER_FORM_LEFT,				createImage("corners/fl.gif",				17, 20));
		registerImage(CORNER_FORM_RIGHT,			createImage("corners/fr.gif",				80, 20));
		registerImage(CORNER_FORMSEARCH_LEFT,		createImage("corners/tl.gif",				15, 20));
		registerImage(CORNER_FORMSEARCH_RIGHT,		createImage("corners/fr.gif",				80, 20));

		// buttons listcontrol
		registerImage(BUTTON_NEXT_1,				createImage("buttons/btnNext1.gif",			15, 15));
		registerImage(BUTTON_NEXT_2,				createImage("buttons/btnNext2.gif",			15, 15));
		registerImage(BUTTON_FIRST_1,				createImage("buttons/btnFirst1.gif",		15, 15));
		registerImage(BUTTON_FIRST_2,				createImage("buttons/btnFirst2.gif",		15, 15));
		registerImage(BUTTON_LAST_1,				createImage("buttons/btnLast1.gif",			15, 15));
		registerImage(BUTTON_LAST_2,				createImage("buttons/btnLast2.gif",			15, 15));
		registerImage(BUTTON_PREVIOUS_1,			createImage("buttons/btnPrev1.gif",			15, 15));
		registerImage(BUTTON_PREVIOUS_2,			createImage("buttons/btnPrev2.gif",			15, 15));
		registerImage(BUTTON_CREATE_1,				createImage("buttons/btnCreate1.gif",		15, 15));
		registerImage(BUTTON_REFRESH_1,				createImage("buttons/btnRefresh1.gif",		15, 15));

		registerImage(BUTTON_SORTABLE,				createImage("buttons/btnSortable1.gif",		11, 13));
		registerImage(BUTTON_SORTASC,				createImage("buttons/btnSortUp1.gif",		11, 13));
		registerImage(BUTTON_SORTDESC,				createImage("buttons/btnSortDown1.gif",		11, 13));
	
		registerImage(BUTTON_CHECKALL_1,			createImage("buttons/btnChkAll1.gif",		11, 13));
		registerImage(BUTTON_UNCHECKALL_1,			createImage("buttons/btnUnchkAll1.gif",		11, 13));

		// textbutton
		registerImage(TEXTBUTTON_BG_LEFT1,			createImage("buttons/btnTxtBgL1.gif",		25, 17));
		registerImage(TEXTBUTTON_BG_MIDDLE1,		createImage("buttons/btnTxtBgM1.gif",		15, 17));
		registerImage(TEXTBUTTON_BG_RIGHT1,			createImage("buttons/btnTxtBgR1.gif",		8, 17));

		// Tabset
		registerImage(TABSET_BACKGROUND,			createImage("tab/tab.gif",					1, 19));
		registerImage(TABSET_NONE_SEL,				createImage("tab/tabLSel_{0}.gif",			10, 19));
		registerImage(TABSET_SEL_NONE,				createImage("tab/tabRSel_{0}.gif",			11, 19));
		registerImage(TABSET_SEL_BG,				createImage("tab/tabBgSel_{0}.gif",			1, 19));
		registerImage(TABSET_NONE_UNSEL,			createImage("tab/tabL.gif",					8, 19));
		registerImage(TABSET_UNSEL_NONE,			createImage("tab/tabR.gif",					10, 19));
		registerImage(TABSET_UNSEL_BG,				createImage("tab/tabBg.gif",				1, 19));
		registerImage(TABSET_NONE_DIS,				createImage("tab/tabDisL.gif",				8, 19));
		registerImage(TABSET_DIS_NONE,				createImage("tab/tabDisR.gif",				10, 19));
		registerImage(TABSET_DIS_BG,				createImage("tab/tabDisBg.gif",				1, 19));

		// Tabbar
		registerImage(TABBAR_BACKGROUND,			createImage("tab/tab.gif",						1, 19));
		registerImage(TABBAR_NONE_SEL,				createImage("tab/tabLSel_{0}.gif",				10, 19));
		registerImage(TABBAR_SEL_NONE,				createImage("tab/tabRSel_{0}.gif",				11, 19));
		registerImage(TABBAR_SEL_BG,				createImage("tab/tabBgSel_{0}.gif",				1, 19));
		registerImage(TABBAR_NONE_UNSEL,			createImage("tab/tabL.gif",						8, 19));
		registerImage(TABBAR_UNSEL_NONE,			createImage("tab/tabR.gif",						10, 19));
		registerImage(TABBAR_UNSEL_BG,				createImage("tab/tabBg.gif",					1, 19));
		registerImage(TABBAR_NONE_DIS,				createImage("tab/tabDisL.gif",					8, 19));
		registerImage(TABBAR_DIS_NONE,				createImage("tab/tabDisR.gif",					10, 19));
		registerImage(TABBAR_DIS_BG,				createImage("tab/tabDisBg.gif",					1, 19));

		registerImage(CHECKBOX_UNCHECKED,			createImage("check/15/cb0.gif",				15, 15));
		registerImage(CHECKBOX_CHECKED,				createImage("check/15/cb1.gif",				15, 15));

		registerImage(RADIO_UNCHECKED,				createImage("check/15/radio0.gif",			15, 15));
		registerImage(RADIO_CHECKED,				createImage("check/15/radio1.gif",			15, 15));

		registerImage(CRUMBS_UNSEL_BG,				createImage("crumbs/u_bg.gif",				1, 18));
		registerImage(CRUMBS_UNSEL_NONE,			createImage("crumbs/u_.gif",				19, 18));
		registerImage(CRUMBS_UNSEL_UNSEL,			createImage("crumbs/uu.gif",				27, 18));
		registerImage(CRUMBS_UNSEL_SEL,				createImage("crumbs/us.gif",				27, 18));
		registerImage(CRUMBS_SEL_BG,				createImage("crumbs/s_bg.gif",				1, 18));
		registerImage(CRUMBS_SEL_NONE,				createImage("crumbs/s_.gif",				19, 18));
		registerImage(CRUMBS_SEL_UNSEL,				createImage("crumbs/su.gif",				27, 18));
		registerImage(CRUMBS_SEL_SEL,				createImage("crumbs/ss.gif",				27, 18));
		registerImage(CRUMBS_NONE_SEL,				createImage("crumbs/_s.gif",				9, 18));
		registerImage(CRUMBS_NONE_UNSEL,			createImage("crumbs/_u.gif",				9, 18));

		// 15 Pixel images
		registerImage(15, TREE_FOLDEROPEN,			createImage("tree/15/folderOpen.gif",		15, 15));
		registerImage(15, TREE_FOLDERCLOSED,		createImage("tree/15/folderClosed.gif",		15, 15));
		registerImage(15, TREE_ITEM,				createImage("tree/15/item.gif",				15, 15));
		registerImage(15, TREE_STRUCTURE,			createImage("tree/15/0.gif",				15, 15));
		registerImage(15, TREE_STRUCTURE_2,			createImage("tree/15/2.gif",				15, 15));
		registerImage(15, TREE_STRUCTURE_10,		createImage("tree/15/10.gif",				15, 15));
		registerImage(15, TREE_STRUCTURE_12,		createImage("tree/15/12.gif",				15, 15));
		registerImage(15, TREE_STRUCTURE_14,		createImage("tree/15/14.gif",				15, 15));
		registerImage(15, TREE_STRUCTURE_16,		createImage("tree/15/16.gif",				15, 15));
		registerImage(15, TREE_STRUCTURE_18,		createImage("tree/15/18.gif",				15, 15));
		registerImage(15, TREE_STRUCTURE_26,		createImage("tree/15/26.gif",				15, 15));
		registerImage(15, TREE_STRUCTURE_30,		createImage("tree/15/30.gif",				15, 15));
		registerImage(15, TREE_STRUCTURE_32,		createImage("tree/15/32.gif",				15, 15));
		registerImage(15, TREE_STRUCTURE_34,		createImage("tree/15/34.gif",				15, 15));
		registerImage(15, TREE_STRUCTURE_42,		createImage("tree/15/42.gif",				15, 15));
		registerImage(15, TREE_STRUCTURE_46,		createImage("tree/15/46.gif",				15, 15));

		registerImage(15, CHECKBOX_NONE,			createImage("spacer.gif",					15, 15));
		registerImage(15, CHECKBOX_INVALID,			createImage("check/15/cb.gif",				15, 15));
		registerImage(15, CHECKBOX_UNCHECKED,		createImage("check/15/cb0.gif",				15, 15));
		registerImage(15, CHECKBOX_CHECKED,			createImage("check/15/cb1.gif",				15, 15));
		registerImage(15, CHECKBOX_INDETERMINATE,	createImage("check/15/cb2.gif",				15, 15));
		
	}

	/**
	 * @see com.cc.framework.ui.painter.ResourceMapImp#doRegisterColors()
	 */
	protected void doRegisterColors() {
		// We are using the Def2ColorPalette Object
		// so we don't have to register any colors explicitly
		// The Def2ColorPalette Object was created by the
		// ResourceFactory Tool
		setColorPalette(new Def2ColorPalette());
	}
}