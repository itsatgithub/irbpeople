/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/def/DefHtmlClass.java,v 1.3 2005/06/05 11:08:20 P001002 Exp $
 * $Revision: 1.3 $
 * $Date: 2005/06/05 11:08:20 $
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

/**
 * This interface defines HTML class Id's used by the painters of
 * the framework.
 * The class Id's ar abreviated to reduce the size of the HTML
 * page
 *
 * @author      <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version     $Revision: 1.3 $
 * @since       1.0
 */
public interface DefHtmlClass {

	/** ctrl */
	String CONTROL						= "ctrl";

	/** header */
	String HEADER						= "header";

	/** body */
	String BODY							= "body";

	/** ibody */
	String INNER_BODY					= "ibody";

	/** footer */
	String FOOTER						= "footer";

	/** help */
	String HELP							= "help";

	/** feact */
	String FORMELEMENTACTION			= "feact";

	// ==============================
	// Common styles
	// ==============================

	/** c */
	String CAPTION						= "c";

	/** d */
	String DETAIL						= "d";

	/** disabled */
	String DISABLED						= "disabled";

	/** sel */
	String SELECTED						= "sel";

	/** unsel */
	String UNSELECTED					= "unsel";

	/** inner frame */
	String INNER_FRAME					= "if";

	/** inner frame without border */
	String INNER_FRAME_NO_BORDER		= "ifnb";

	/** Popup window */
	String POPUP						= "popup";

	// ==============================
	// Controls
	// ==============================

	/** Panel Control */
	String PANEL						= "pc";


	/** Gauge Control */
	String GAUGE						= "gauge";


	/** Text Button: tbtn */
	String TEXTBUTTON					= "tbtn";


	/** Info Control */
	String INFOCONTROL					= "info";

	/** Info Control */
	String INFOCONTROL_NO_FRAME			= "info_nf";


	/** Frame Control: frc */
	String FRAMECONTROL					= "frc";

	/** Frameless Frame Control: frc_nf */
	String FRAMECONTROL_NO_FRAME		= "frc_nf";

	/** Scheduler Control */
	String SCHEDULERCONTROL				= "shc";

	/** Scheduler Control */
	String SCHEDULERCONTROL_NO_FRAME	= "shc_nf";


	/** Form Control: fc */
	String FORMCONTROL					= "fc";

	/** Frameless Form Control: fc_nf */
	String FORMCONTROL_NO_FRAME			= "fc_nf";

	/** Form Control left spacer */
	String FORMCONTROL_SPACER_LEFT		= "fsl";


	/** SwapSelect Control: sws */
	String SWAPCONTROL					= "sws";

	/** SwapSelect Control: label_left */
	String SWAPCONTROL_LABEL_L			= "label_left";

	/** SwapSelect Control: label_right */
	String SWAPCONTROL_LABEL_R			= "label_right";

	/** SwapSelect Control: select_left */
	String SWAPCONTROL_SELECT_L			= "left";

	/** SwapSelect Control: select_right */
	String SWAPCONTROL_SELECT_R			= "right";

	/** SwapSelect Control: swsbtnh */
	String SWAPCONTROL_BUTTON_H			= "swsbtnh";

	/** SwapSelect Control: swsbtnv */
	String SWAPCONTROL_BUTTON_V			= "swsbtnv";


	/** List Control: lc */
	String LISTCONTROL					= "lc";

	/** List Control (no frame) : lc_nf */
	String LISTCONTROL_NOFRAME			= "lc_nf";

	/** List Control: lch */
	String LISTCONTROL_HEADER			= "lch";

	/** List Control: lcb */
	String LISTCONTROL_BODY				= "lcb";

	/** List Control: lcf */
	String LISTCONTROL_FOOTER			= "lcf";

	/** List Control: header */
	String LISTCONTROL_BODYHEADER		= "header";

	/** List Control: even */
	String LISTCONTROL_EVENLINE			= "even";

	/** List Control: even selected */
	String LISTCONTROL_EVENLINE_SEL		= "evens";

	/** List Control: odd */
	String LISTCONTROL_ODDLINE			= "odd";

	/** List Control: odd selected */
	String LISTCONTROL_ODDLINE_SEL		= "odds";

	/** List Control: pg */
	String LISTCONTROL_PAGE				= "pg";

	/** List Control: pgs */
	String LISTCONTROL_PAGE_SEL			= "pgs";

	/** List Control: pgsep */
	String LISTCONTROL_PAGESEPERATOR	= "pgsep";

	/** List Control: cell */
	String LISTCONTROL_CELL				= "cl";


	/** TreeList Control: tlc */
	String TREELISTCONTROL				= "tlc";

	/** TreeList Control (no frame): tlc_nf */
	String TREELISTCONTROL_NOFRAME		= "tlc_nf";

	/** TreeList Control: tlch */
	String TREELISTCONTROL_HEADER		= "tlch";

	/** TreeList Control: tlcb */
	String TREELISTCONTROL_BODY			= "tlcb";

	/** TreeList Control: tlcf */
	String TREELISTCONTROL_FOOTER		= "tlcf";


	/** Menu Control: msb */
	String MENU_SIDEBAR					= "msb";

	/** Menu Control: mm */
	String MENU_MAIN					= "mm";

	/** Menu Control: mt */
	String MENU_TOOLS					= "mt";

	/** Menu Control left spacer */
	String MENU_LEVEL1_SPACER_LEFT		= "m1s_l";

	/** Menu Control right spacer */
	String MENU_LEVEL1_SPACER_RIGHT		= "m1s_r";


	/** Tree Control: tc */
	String TREECONTROL_TREE				= "tc";

	/** Tree Control: tleven */
	String TREECONTROL_EVENLINE			= "tleven";

	/** Tree Control: tleven selected */
	String TREECONTROL_EVENLINE_SEL		= "tlevens";

	/** Tree Control: tlodd */
	String TREECONTROL_ODDLINE			= "tlodd";

	/** Tree Control: tlodd selected */
	String TREECONTROL_ODDLINE_SEL		= "tlodds";

	/** Tree: tol */
	String TREE_OUTLINE					= "tol";

	/** Tree: ti */
	String TREEITEM						= "ti";

	/** Tree: tis */
	String TREEITEM_SEL					= "tis";


	/** Headline Control: hdr */
	String HEADLINE						= "hdr";

	/** Tabset Control: tsc */
	String TABSETCONTROL				= "tsc";

	/** Tabbar Control: tsc */
	String TABBARCONTROL				= TABSETCONTROL;

	/** Crumbs Control: crc */
	String CRUMBSCONTROL				= "crc";

	// ==============================
	// Forms
	// ==============================

	/** Error Form Control */
	String ERROR_FORM					= "efc";

	/** Info Form Control */
	String INFO_FORM					= "ifc";

	/** Warning Form Control */
	String WARNING_FORM					= "wfc";

	/** Severity Form Control */
	String SEVERITY_FORM				= "sevfc";


	/** Message Form: headerl */
	String MESSAGE_HEADER_LABEL			= "header";

	/** Message Form: body */
	String MESSAGE_BODY					= "body";

	/** Message Form: data */
	String MESSAGE_BODY_DATA			= "data";
}