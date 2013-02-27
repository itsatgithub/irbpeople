/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/def/DefResources.java,v 1.53 2005/11/13 14:03:57 P001002 Exp $
 * $Revision: 1.53 $
 * $Date: 2005/11/13 14:03:57 $
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

import com.cc.framework.FrameworkResources;
import com.cc.framework.ui.painter.Resources;

/**
 * This interface defines string and image codes
 * for resources.
 *
 * @author   <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version  $Revision: 1.53 $
 * @since    1.2
 */
public interface DefResources extends Resources, FrameworkResources {

	// ============================
	// File resources
	// ============================

	/** default.css */
	String FW_FILE_CSS_DEFAULT			= "fw.file.css.default";

	/** resourcemap.js */
	String FW_FILE_JS_RESOURCEMAP       = "fw.file.js.resourcemap";

	/** functions.js */
	String FW_FILE_JS_FUNCTIONS			= "fw.file.js.functions";

	/** controls */
	String FW_FILE_JS_CONTROLS			= "fw.file.js.controls";

	/** tabset */
	String FW_FILE_JS_TABSET			= "fw.file.js.tabset";

	/** list */
	String FW_FILE_JS_LIST				= "fw.file.js.list";

	/** tree */
	String FW_FILE_JS_TREE				= "fw.file.js.tree";


	// ============================
	// Image resources
	// ============================

	/** Severity: Fatal */
	String IMAGE_SEV_FATAL				= "def.img.sev.fatal";

	/** Severity: Error */
	String IMAGE_SEV_ERROR				= "def.img.sev.err";

	/** Severity: Warning */
	String IMAGE_SEV_WARN				= "def.img.sev.warn";

	/** Severity: Information */
	String IMAGE_SEV_INFO				= "def.img.sev.inf";

	/** Severity: Question */
	String IMAGE_SEV_QUESTION			= "def.img.sev.qest";

	/** Severity: None */
	String IMAGE_SEV_NONE				= "def.img.sev.none";

	// Button suffix:
	// 1 = active
	// 2 = inactive
	// 3 = hover
	// 4 = pressed

	/** Button: SORTABLE */
	String BUTTON_SORTABLE				= "def.btn.sortable";

	/** Button: SORTASC */
	String BUTTON_SORTASC				= "def.btn.sortasc";

	/** Button: SORTDESC */
	String BUTTON_SORTDESC				= "def.btn.sortdesc";

	/** Button: CHECKALL_1 */
	String BUTTON_CHECKALL_1			= "def.btn.ckall1";

	/** Button: UNCHECKALL_1 */
	String BUTTON_UNCHECKALL_1			= "def.btn.unchkall1";

	/** Button: NEXT_1 */
	String BUTTON_NEXT_1				= "def.btn.next1";

	/** Button: NEXT_2 */
	String BUTTON_NEXT_2				= "def.btn.next2";

	/** Button: FIRST_1 */
	String BUTTON_FIRST_1				= "def.btn.first1";

	/** Button: FIRST_2 */
	String BUTTON_FIRST_2				= "def.btn.first2";

	/** Button: LAST_1 */
	String BUTTON_LAST_1				= "def.btn.last1";

	/** Button: LAST_2 */
	String BUTTON_LAST_2				= "def.btn.last2";

	/** Button: PREVIOUS_1 */
	String BUTTON_PREVIOUS_1			= "def.btn.prev1";

	/** Button: PREVIOUS_2 */
	String BUTTON_PREVIOUS_2			= "def.btn.prev2";

	/** Button: CREATE_1 */
	String BUTTON_CREATE_1				= "def.btn.create1";

	/** Button: CREATE_2 */
	String BUTTON_CREATE_2				= "def.btn.create2";

	/** Button: REFRESH_1 */
	String BUTTON_REFRESH_1				= "def.btn.refresh1";

	/** Button: REFRESH_2 */
	String BUTTON_REFRESH_2				= "def.btn.refresh2";

	/** Button: HELP_1 */
	String BUTTON_HELP_1				= "def.btn.help1";

	/** Button: HELP_2 */
	String BUTTON_HELP_2				= "def.btn.help2";

	/** Button: MAXIMIZE_1 */
	String BUTTON_MAXIMIZE_1			= "def.btn.max1";

	/** Button: MINIMIZE_1 */
	String BUTTON_MINIMIZE_1			= "def.btn.min1";

	/** Button: RESTORE_1 */
	String BUTTON_RESTORE_1				= "def.btn.restore1";

	/** Button: CLOSE_1 */
	String BUTTON_CLOSE_1				= "def.btn.close1";

	/** Groupable List: def.btn.expand */
	String BUTTON_EXPAND				= "def.btn.expand";

	/** Groupable List: def.btn.collapse */
	String BUTTON_COLLAPSE				= "def.btn.collapse";

	/** Button: def.btn.export1 */
	String BUTTON_EXPORT_1				= "def.btn.export1";

	/** Button: def.btn.export2 */
	String BUTTON_EXPORT_2				= "def.btn.export2";

	/** Button: def.btn.print1 */
	String BUTTON_PRINT_1				= "def.btn.print1";

	/** Button: def.btn.print_2 */
	String BUTTON_PRINT_2				= "def.btn.print2";

	/** Text button: left background */
	String TEXTBUTTON_BG_LEFT1			= "def.tbtn.bg.left1";

	/** Text button: middle background */
	String TEXTBUTTON_BG_MIDDLE1		= "def.tbtn.bg.middle1";

	/** Text button: right background */
	String TEXTBUTTON_BG_RIGHT1			= "def.tbtn.bg.right1";

	/** Scheduler: Create */
	String BUTTON_SCHEDULER_CREATE1		= "def.btn.shd.crt1";
	
	/** Scheduler: View Day */
	String BUTTON_SCHEDULER_DAY1		= "def.btn.shd.day1";

	/** Scheduler: View Working Week */
	String BUTTON_SCHEDULER_WORKWEEK1	= "def.btn.shd.wweek1";

	/** Scheduler: View Week */
	String BUTTON_SCHEDULER_WEEK1		= "def.btn.shd.week1";

	/** Scheduler: View Month */
	String BUTTON_SCHEDULER_MONTH1		= "def.btn.shd.month1";

	/** Scheduler: View Year */
	String BUTTON_SCHEDULER_YEAR1		= "def.btn.shd.year1";

	/** Scheduler: Roll down */
	String BUTTON_SCHEDULER_PREV_1		= "def.btn.shd.prev1";

	/** Scheduler: Roll up */
	String BUTTON_SCHEDULER_NEXT_1		= "def.btn.shd.next1";

	// Scroll buttons for the client side TabSet

	/** Button: BUTTON_TABSET_PREVIOUS_1 */
	String BUTTON_TABSET_PREVIOUS_1     = "def.btn.tabset.prev1";

	/** Button: BUTTON_TABSET_PREVIOUS_2 */
	String BUTTON_TABSET_PREVIOUS_2     = "def.btn.tabset.prev2";

	/** Button: BUTTON_TABSET_NEXT_1 */
	String BUTTON_TABSET_NEXT_1         = "def.btn.tabset.next1";

	/** Button: BUTTON_TABSET_NEXT_2 */
	String BUTTON_TABSET_NEXT_2         = "def.btn.tabset.next2";

	/** Button: BUTTON_TABSET_MORE_PREVIOUS */
	String BUTTON_TABSET_MORE_PREVIOUS	= "def.btn.tabset.more.prev";

	/** Button: BUTTON_TABSET_MORE_NEXT */
	String BUTTON_TABSET_MORE_NEXT		= "def.btn.tabset.more.next";

	/** Button: BUTTON_TABSET_MORE_EMPTY */
	String BUTTON_TABSET_MORE_EMPTY		= "def.btn.tabset.more.empty";

	// Scroll buttons for the client side Tabbar

	/** Button: BUTTON_TABBAR_PREVIOUS_1 */
	String BUTTON_TABBAR_PREVIOUS_1     = "def.btn.tabbar.prev1";

	/** Button: BUTTON_TABBAR_PREVIOUS_2 */
	String BUTTON_TABBAR_PREVIOUS_2     = "def.btn.tabbar.prev2";

	/** Button: BUTTON_TABBAR_NEXT_1 */
	String BUTTON_TABBAR_NEXT_1         = "def.btn.tabbar.next1";

	/** Button: BUTTON_TABBAR_NEXT_2 */
	String BUTTON_TABBAR_NEXT_2         = "def.btn.tabbar.next2";

	/** Button: BUTTON_TABBAR_MORE_PREVIOUS */
	String BUTTON_TABBAR_MORE_PREVIOUS	= "def.btn.tabbar.more.prev";

	/** Button: BUTTON_TABBAR_MORE_NEXT */
	String BUTTON_TABBAR_MORE_NEXT		= "def.btn.tabbar.more.next";

	/** Button: BUTTON_TABBAR_MORE_EMPTY */
	String BUTTON_TABBAR_MORE_EMPTY		= "def.btn.tabbar.more.empty";


	// ----------------- images

	/** Image: HAND */
	String IMAGE_HAND					= "def.img.hand";

	/** Image: DOT_COLOR */
	String IMAGE_DOT_COLOR				= "def.img.dotc";

	/** Image: MAGNIFIER */
	String IMAGE_MAGNIFIER				= "def.img.mag";

	/** Image: LINK_EXTERNAL */
	String IMAGE_LINK_EXTERNAL			= "def.img.link.external";

	/** Image: ERROR */
	String IMAGE_ERROR					= "def.img.err";

	/** Image: WARNING */
	String IMAGE_WARNING				= "def.img.warn";

	/** Image: INFORMATION */
	String IMAGE_INFORMATION			= "def.img.inf";

	/** Image: ERROR_INPUT */
	String IMAGE_ERROR_INPUT			= "def.img.errin";

	/** Image: MESSAGE_INPUT */
	String IMAGE_MESSAGE_INPUT			= "def.img.msgin";

	/** Image: HEADER_TOP */
	String IMAGE_HEADER_TOP				= "def.img.hdrtop";

	/** Image: HEADER_BOTTOM */
	String IMAGE_HEADER_BOTTOM			= "def.img.hdrbot";

	/** Image: Required */
	String IMAGE_REQUIRED				= "def.img.required";

	/** Image: help */
	String IMAGE_HELP					= "def.img.help";

	// ----------------- corners

	/** Corner: Table right */
	String CORNER_TABLE_RIGHT			= "def.cor.table.right";

	/** Corner: Table left */
	String CORNER_TABLE_LEFT			= "def.cor.table.left";

	/** Corner: Form right */
	String CORNER_FORM_RIGHT			= "def.cor.form.right";

	/** Corner: Form left */
	String CORNER_FORM_LEFT				= "def.cor.form.left";

	/** Corner: Searchform right */
	String CORNER_FORMSEARCH_RIGHT		= "def.cor.form.search.right";

	/** Corner: Searchform left */
	String CORNER_FORMSEARCH_LEFT		= "def.cor.form.search.left";

	/** Corner: Panel top left */
	String CORNER_PANEL_TL				= "def.cor.panel.top.left";

	/** Corner: Panel top right */
	String CORNER_PANEL_TR				= "def.cor.panel.top.right";

	/** Corner: Panel bottom left */
	String CORNER_PANEL_BL				= "def.cor.panel.bottom.left";

	/** Corner: Panel bottom right */
	String CORNER_PANEL_BR				= "def.cor.panel.bottom.right";

	/** Corner: Form right (image with color encoding) */
	String CORNER_FORM_RIGHT_COLOR		= "def.cor.form.rightc";

	/** Corner: Form left (image with color encoding) */
	String CORNER_FORM_LEFT_COLOR		= "def.cor.form.leftc";

	// ----------------- checkboxes

	/** Checkbox: NONE */
	String CHECKBOX_NONE				= "def.cb.none";

	/** Checkbox: INVALID */
	String CHECKBOX_INVALID				= "def.cb.inv";

	/** Checkbox: UNCHECKED */
	String CHECKBOX_UNCHECKED			= "def.cb.unchk";

	/** Checkbox: CHECKED */
	String CHECKBOX_CHECKED				= "def.cb.chk";

	/** Checkbox: INDETERMINATE */
	String CHECKBOX_INDETERMINATE		= "def.cb.na";

	// ----------------- radiobuttons

	/** Radiobutton: UNCHECKED */
	String RADIO_UNCHECKED				= "def.rb.unchk";

	/** Radiobutton: CHECKED */
	String RADIO_CHECKED				= "def.rb.chk";

	// ----------------- crumbs

	/** Crumbs: selected background */
	String CRUMBS_SEL_BG				= "def.cr.s";

	/** Crumbs: unselected background */
	String CRUMBS_UNSEL_BG				= "def.cr.u";

	/** Crumbs: disabled background */
	String CRUMBS_DIS_BG				= "def.cr.d";

	/** Crumbs: [selected, - ] */
	String CRUMBS_SEL_NONE				= "def.cr.s_";

	/** Crumbs: [unselected, - ] */
	String CRUMBS_UNSEL_NONE			= "def.cr.u_";

	/** Crumbs: [disabled, - ] */
	String CRUMBS_DIS_NONE				= "def.cr.d_";

	/** Crumbs: [ - , selected] */
	String CRUMBS_NONE_SEL				= "def.cr._s";

	/** Crumbs: [ - , unselected] */
	String CRUMBS_NONE_UNSEL			= "def.cr._u";

	/** Crumbs: [ - , disabled] */
	String CRUMBS_NONE_DIS				= "def.cr._d";

	/** Crumbs: [selected, selected] */
	String CRUMBS_SEL_SEL				= "def.cr.ss";

	/** Crumbs: [selected, unselected] */
	String CRUMBS_SEL_UNSEL				= "def.cr.su";

	/** Crumbs: [selected, disabled] */
	String CRUMBS_SEL_DIS				= "def.cr.sd";

	/** Crumbs: [unselected, selected] */
	String CRUMBS_UNSEL_SEL				= "def.cr.us";

	/** Crumbs: [unselected, unselected] */
	String CRUMBS_UNSEL_UNSEL			= "def.cr.uu";

	/** Crumbs: [unselected, disabled] */
	String CRUMBS_UNSEL_DIS				= "def.cr.ud";

	/** Crumbs: [disabled, selected] */
	String CRUMBS_DIS_SEL				= "def.cr.ds";

	/** Crumbs: [disabled, unselected] */
	String CRUMBS_DIS_UNSEL				= "def.cr.du";

	/** Crumbs: [disabled, disabled] */
	String CRUMBS_DIS_DIS				= "def.cr.dd";

	// ----------------- tabset

	/** Tabset: Background Image */
	String TABSET_BACKGROUND			= "def.tab.bg";

	/** Tabset: selected background */
	String TABSET_SEL_BG				= "def.tab.s";

	/** Tabset: unselected background */
	String TABSET_UNSEL_BG				= "def.tab.u";

	/** Tabset: disabled background */
	String TABSET_DIS_BG				= "def.tab.d";

	/** Tabset: [selected, - ] */
	String TABSET_SEL_NONE				= "def.tab.s_";

	/** Tabset: [unselected, - ] */
	String TABSET_UNSEL_NONE			= "def.tab.u_";

	/** Tabset: [disabled, - ] */
	String TABSET_DIS_NONE				= "def.tab.d_";

	/** Tabset: [ - , selected] */
	String TABSET_NONE_SEL				= "def.tab._s";

	/** Tabset: [ - , unselected] */
	String TABSET_NONE_UNSEL			= "def.tab._u";

	/** Tabset: [ - , disabled] */
	String TABSET_NONE_DIS				= "def.tab._d";

	/** Tabset: [selected, unselected] */
	String TABSET_SEL_UNSEL				= "def.tab.su";

	/** Tabset: [selected, disabled] */
	String TABSET_SEL_DIS				= "def.tab.sd";

	/** Tabset: [unselected, selected] */
	String TABSET_UNSEL_SEL				= "def.tab.us";

	/** Tabset: [unselected, unselected] */
	String TABSET_UNSEL_UNSEL			= "def.tab.uu";

	/** Tabset: [unselected, disabled] */
	String TABSET_UNSEL_DIS				= "def.tab.ud";

	/** Tabset: [disabled, selected] */
	String TABSET_DIS_SEL				= "def.tab.ds";

	/** Tabset: [disabled, unselected] */
	String TABSET_DIS_UNSEL				= "def.tab.du";

	/** Tabset: [disabled, disabled] */
	String TABSET_DIS_DIS				= "def.tab.dd";

	/** 
	 * Tabset: Selected tab left (image with color encoding)
	 * @deprecated		use TABSET_NONE_SEL
	 */
	String TABSET_TABSEL_L_COLOR		= TABSET_NONE_SEL;

	/**
	 * Tabset: Selected tab right (image with color encoding)
	 * @deprecated		use TABSET_SEL_NONE
	 */
	String TABSET_TABSEL_R_COLOR		= TABSET_SEL_NONE;

	/**
	 * Tabset: Selected tab backgroupd (image with color encoding)
	 * @deprecated		use TABSET_SEL_BG
	 */
	String TABSET_TABSEL_BG_COLOR		= TABSET_SEL_BG;

	/**
	 * Tabset: Unselected tab left
	 * @deprecated		use TABSET_NONE_UNSEL
	 */
	String TABSET_TABUNSEL_L			= TABSET_NONE_UNSEL;

	/**
	 * Tabset: Unselected tab right
	 * @deprecated		use TABSET_UNSEL_NONE
	 */
	String TABSET_TABUNSEL_R			= TABSET_UNSEL_NONE;

	/**
	 * Tabset: Unselected tab background
	 * @deprecated		use TABSET_UNSEL_BG
	 */
	String TABSET_TABUNSEL_BG			= TABSET_UNSEL_BG;

	/**
	 * Tabset: Disabeled tab left
	 * @deprecated		use TABSET_NONE_DIS
	 */
	String TABSET_TABDISABLED_L			= TABSET_NONE_DIS;

	/**
	 * Tabset: Disabeled tab right
	 * @deprecated		use TABSET_DIS_NONE
	 */
	String TABSET_TABDISABLED_R			= TABSET_DIS_NONE;

	/**
	 * Tabset: Disabeled tab background
	 * @deprecated		use TABSET_DIS_BG
	 */
	String TABSET_TABDISABLED_BG		= TABSET_DIS_BG;

	// ----------------- tabbar

	/** Tabbar: Background Image */
	String TABBAR_BACKGROUND			= "def.tabbar.bg";

	/** Tabbar: selected background */
	String TABBAR_SEL_BG				= "def.tabbar.s";

	/** Tabbar: unselected background */
	String TABBAR_UNSEL_BG				= "def.tabbar.u";

	/** Tabbar: disabled background */
	String TABBAR_DIS_BG				= "def.tabbar.d";

	/** Tabbar: [selected, - ] */
	String TABBAR_SEL_NONE				= "def.tabbar.s_";

	/** Tabbar: [unselected, - ] */
	String TABBAR_UNSEL_NONE			= "def.tabbar.u_";

	/** Tabbar: [disabled, - ] */
	String TABBAR_DIS_NONE				= "def.tabbar.d_";

	/** Tabbar: [ - , selected] */
	String TABBAR_NONE_SEL				= "def.tabbar._s";

	/** Tabbar: [ - , unselected] */
	String TABBAR_NONE_UNSEL			= "def.tabbar._u";

	/** Tabbar: [ - , disabled] */
	String TABBAR_NONE_DIS				= "def.tabbar._d";

	/** Tabbar: [selected, unselected] */
	String TABBAR_SEL_UNSEL				= "def.tabbar.su";

	/** Tabbar: [selected, disabled] */
	String TABBAR_SEL_DIS				= "def.tabbar.sd";

	/** Tabbar: [unselected, selected] */
	String TABBAR_UNSEL_SEL				= "def.tabbar.us";

	/** Tabbar: [unselected, unselected] */
	String TABBAR_UNSEL_UNSEL			= "def.tabbar.uu";

	/** Tabbar: [unselected, disabled] */
	String TABBAR_UNSEL_DIS				= "def.tabbar.ud";

	/** Tabbar: [disabled, selected] */
	String TABBAR_DIS_SEL				= "def.tabbar.ds";

	/** Tabbar: [disabled, unselected] */
	String TABBAR_DIS_UNSEL				= "def.tabbar.du";

	/** Tabbar: [disabled, disabled] */
	String TABBAR_DIS_DIS				= "def.tabbar.dd";

	/** 
	 * Tabbar: Selected tab left (image with color encoding)
	 * @deprecated		use TABBAR_NONE_SEL
	 */
	String TABBAR_TABSEL_L_COLOR		= TABBAR_NONE_SEL;

	/**
	 * Tabbar: Selected tab right (image with color encoding)
	 * @deprecated		use TABBAR_SEL_NONE
	 */
	String TABBAR_TABSEL_R_COLOR		= TABBAR_SEL_NONE;

	/**
	 * Tabbar: Selected tab backgroupd (image with color encoding)
	 * @deprecated		use TABBAR_SEL_BG
	 */
	String TABBAR_TABSEL_BG_COLOR		= TABBAR_SEL_BG;

	/**
	 * Tabbar: Unselected tab left
	 * @deprecated		use TABBAR_NONE_UNSEL
	 */
	String TABBAR_TABUNSEL_L			= TABBAR_NONE_UNSEL;

	/**
	 * Tabbar: Unselected tab right
	 * @deprecated		use TABBAR_UNSEL_NONE
	 */
	String TABBAR_TABUNSEL_R			= TABBAR_UNSEL_NONE;

	/**
	 * Tabbar: Unselected tab background
	 * @deprecated		use TABBAR_UNSEL_BG
	 */
	String TABBAR_TABUNSEL_BG			= TABBAR_UNSEL_BG;

	/**
	 * Tabbar: Disabeled tab left
	 * @deprecated		use TABBAR_NONE_DIS
	 */
	String TABBAR_TABDISABLED_L			= TABBAR_NONE_DIS;

	/**
	 * Tabbar: Disabeled tab right
	 * @deprecated		use TABBAR_DIS_NONE
	 */
	String TABBAR_TABDISABLED_R			= TABBAR_DIS_NONE;

	/**
	 * Tabbar: Disabeled tab background
	 * @deprecated		use TABBAR_DIS_BG
	 */
	String TABBAR_TABDISABLED_BG		= TABBAR_DIS_BG;

	// ----------------- Scheduler

	/** Scheduler: Priority none */
	String SCHEDULER_PRIO_NONE			= "def.sched.prio.none";

	/** Scheduler: Priority low */
	String SCHEDULER_PRIO_LOW			= "def.sched.prio.low";

	/** Scheduler: Priority normal */
	String SCHEDULER_PRIO_NORMAL		= "def.sched.prio.normal";

	/** Scheduler: Priority high */
	String SCHEDULER_PRIO_HIGH			= "def.sched.prio.high";

	/** Scheduler: Recurring */
	String SCHEDULER_RECURRING			= "def.sched.rec";

	/** Scheduler: Attachment */
	String SCHEDULER_ATTACHMENT			= "def.sched.attach";

	/** Scheduler: More button down */
	String SCHEDULER_MORE_DOWN			= "def.sched.more.down";

	/** Scheduler: More button up */
	String SCHEDULER_MORE_UP			= "def.sched.more.up";

	/** Scheduler: Clock */
	String SCHEDULER_CLOCK				= "def.sched.clock";

	/** Scheduler: Roll down */
	String SCHEDULER_PREV				= "def.sched.prev";

	/** Scheduler: Roll up */
	String SCHEDULER_NEXT				= "def.sched.next";

	/** Scheduler: Add Appointment */
	String SCHEDULER_ADD_APPOINTMENT	= "def.sched.addapp";

	// ----------------- tree outline

	// Bit encoding for tree outline images:
	//
	// 0x20 = Expand button (+)
	// 0x10 = collapse button (-)
	// 0x08 = Connector to the north (N)
	// 0x04 = Connector to the south (S)
	// 0x02 = Connector to the east (E)
	// 0x01 = Connector to the west (W)

	/** Tree outline: open folder */
	String TREE_FOLDEROPEN				= "def.tree.open";

	/** Tree outline: closed folder */
	String TREE_FOLDERCLOSED			= "def.tree.closed";

	/** Tree outline: item */
	String TREE_ITEM					= "def.tree.item";

	/** Tree outline: otline spacer */
	String TREE_STRUCTURE				= "def.tree.ol";

	/** Tree outline: otline E */
	String TREE_STRUCTURE_2				= "def.tree.ol2";

	/** Tree outline: otline NE */
	String TREE_STRUCTURE_10			= "def.tree.ol10";

	/** Tree outline: otline NS */
	String TREE_STRUCTURE_12			= "def.tree.ol12";

	/** Tree outline: otline NSE */
	String TREE_STRUCTURE_14			= "def.tree.ol14";

	/** Tree outline: otline - */
	String TREE_STRUCTURE_16			= "def.tree.ol16";

	/** Tree outline: otline -E */
	String TREE_STRUCTURE_18			= "def.tree.ol18";

	/** Tree outline: otline -NSE */
	String TREE_STRUCTURE_26			= "def.tree.ol26";

	/** Tree outline: otline -NE */
	String TREE_STRUCTURE_30			= "def.tree.ol30";

	/** Tree outline: otline + */
	String TREE_STRUCTURE_32			= "def.tree.ol32";

	/** Tree outline: otline +E */
	String TREE_STRUCTURE_34			= "def.tree.ol34";

	/** Tree outline: otline +NSE */
	String TREE_STRUCTURE_42			= "def.tree.ol42";

	/** Tree outline: otline +NE */
	String TREE_STRUCTURE_46			= "def.tree.ol46";
}