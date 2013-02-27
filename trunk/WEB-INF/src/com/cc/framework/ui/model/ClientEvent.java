/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/ClientEvent.java,v 1.11 2005/07/08 14:19:30 P001002 Exp $
 * $Revision: 1.11 $
 * $Date: 2005/07/08 14:19:30 $
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

import java.io.Serializable;

import com.cc.framework.common.SimpleEnumType;

/**
 * Enumeration of client events
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.11 $
 * @since      1.0
 */
public final class ClientEvent implements SimpleEnumType, Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 5132637979960031915L;

	/**
	 * Fires when the user aborts the download of an image.
	 */
	public static final ClientEvent ONABORT				= new ClientEvent("onAbort");

	/**
	 * Fires when the object is set as the active element.
	 */
	public static final ClientEvent ONACTIVATE			= new ClientEvent("onActivate");

	/**
	 * Fires on the object immediately after its associated
	 * document prints or previews for printing.
	 */
	public static final ClientEvent ONAFTERPRINT		= new ClientEvent("onAfterPrint");

	/**
	 * Fires on a databound object after successfully updating the
	 * associated data in the data source object.
	 */
	public static final ClientEvent ONAFTERUPDATE		= new ClientEvent("onAfterUpdate");

	/**
	 * Fires immediately before the object is set as the active element.
	 */
	public static final ClientEvent ONBEFOREACTIVATE	= new ClientEvent("onBeforeActivate");

	/**
	 * Fires on the source object before the selection is copied
	 * to the system clipboard.
	 */
	public static final ClientEvent ONBEFORECOPY		= new ClientEvent("onBeforeCopy");

	/**
	 * Fires on the source object before the selection is deleted
	 * from the document.
	 */
	public static final ClientEvent ONBEFORECUT			= new ClientEvent("onBeforeCut");

	/**
	 * Fires immediately before the activeElement is changed from the
	 * current object to another object in the parent document.
	 */
	public static final ClientEvent ONBEFOREDEACTIVATE	= new ClientEvent("onBeforeDeactivate");

	/**
	 * Fires before an object contained in an editable element enters
	 * a UI-activated state or when an editable container object is control
	 * selected.
	 */
	public static final ClientEvent ONBEFOREEDITFOCUS	= new ClientEvent("onBeforeEditFocus");

	/**
	 * Fires on the target object before the selection is pasted from the
	 * system clipboard to the document.
	 */
	public static final ClientEvent ONBEFOREPASTE		= new ClientEvent("onBeforePaste");

	/**
	 * Fires on the object before its associated document prints or previews
	 * for printing.
	 */
	public static final ClientEvent ONBEFOREPRINT		= new ClientEvent("onBeforePrint");

	/**
	 * Fires prior to a page being unloaded.
	 */
	public static final ClientEvent ONBEFOREUNLOAD		= new ClientEvent("onBeforeUnload");

	/**
	 * Fires on a databound object before updating the associated data in
	 * the data source object.
	 */
	public static final ClientEvent ONBEFOREUPDATE		= new ClientEvent("onBeforeUpdate");

	/**
	 * Fires when the object loses the input focus.
	 */
	public static final ClientEvent ONBLUR				= new ClientEvent("onBlur");

	/**
	 * Fires when the behavior property of the marquee object is set to
	 * "alternate" and the contents of the marquee reach one side of the window.
	 */
	public static final ClientEvent ONBOUNCE			= new ClientEvent("onBounce");

	/**
	 * Fires when data changes in the data provider.
	 */
	public static final ClientEvent ONCELLCHANGE		= new ClientEvent("onCellChange");

	/**
	 * Fires when the contents of the object or selection have changed.
	 */
	public static final ClientEvent ONCHANGE			= new ClientEvent("onChange");

	/**
	 * Fires when the user clicks the left mouse button on the object.
	 */
	public static final ClientEvent ONCLICK				= new ClientEvent("onClick");

	/**
	 * Fires when the user clicks the right mouse button in the client
	 * area, opening the context menu.
	 */
	public static final ClientEvent ONCONTEXTMENU		= new ClientEvent("onContextMenu");

	/**
	 * Fires when the user is about to make a control selection of the
	 * object.
	 */
	public static final ClientEvent ONCONTROLSELECT		= new ClientEvent("onControlSelect");

	/**
	 * Fires on the source element when the user copies the object or
	 * selection, adding it to the system clipboard.
	 */
	public static final ClientEvent ONCOPY				= new ClientEvent("onCopy");

	/**
	 * Fires on the source element when the object or selection is removed
	 * from the document and added to the system clipboard.
	 */
	public static final ClientEvent ONCUT				= new ClientEvent("onCut");

	/**
	 * Fires periodically as data arrives from data source objects that
	 * asynchronously transmit their data.
	 */
	public static final ClientEvent ONDATAAVAILABLE		= new ClientEvent("onDataAvailable");

	/**
	 * Fires when the data set exposed by a data source object changes.
	 */
	public static final ClientEvent ONDATASETCHANGED	= new ClientEvent("onDatasetChanged");

	/**
	 * Fires to indicate that all data is available from the data source object.
	 */
	public static final ClientEvent ONDATASETCOMPLETE	= new ClientEvent("onDatasetComplete");

	/**
	 * Fires when the user double-clicks the object.
	 */
	public static final ClientEvent ONDBLCLICK			= new ClientEvent("onDblClick");

	/**
	 * Fires when the activeElement is changed from the current object to
	 * another object in the parent document.
	 */
	public static final ClientEvent ONDEACTIVATE		= new ClientEvent("onDeactivate");

	/**
	 * Fires on the source object continuously during a drag operation.
	 */
	public static final ClientEvent ONDRAG				= new ClientEvent("onDrag");

	/**
	 * Fires on the source object when the user releases the mouse at the
	 * close of a drag operation.
	 */
	public static final ClientEvent ONDRAGEND			= new ClientEvent("onDragEnd");

	/**
	 * Fires on the target element when the user drags the object to a valid
	 * drop target.
	 */
	public static final ClientEvent ONDRAGENTER			= new ClientEvent("onDragEnter");

	/**
	 * Fires on the target object when the user moves the mouse out of a
	 * valid drop target during a drag operation.
	 */
	public static final ClientEvent ONDRAGLEAVE			= new ClientEvent("onDragLeave");

	/**
	 * Fires on the target element continuously while the user drags the
	 * object over a valid drop target.
	 */
	public static final ClientEvent ONDRAGOVER			= new ClientEvent("onDragOver");

	/**
	 * Fires on the source object when the user starts to drag a text
	 * selection or selected object.
	 */
	public static final ClientEvent ONDRAGSTART			= new ClientEvent("onDragStart");

	/**
	 * Fires on the target object when the mouse button is released during
	 * a drag-and-drop operation.
	 */
	public static final ClientEvent ONDROP				= new ClientEvent("onDrop");

	/**
	 * Fires when an error occurs during object loading.
	 */
	public static final ClientEvent ONERROR				= new ClientEvent("onError");

	/**
	 * Fires on a databound object when an error occurs while updating the
	 * associated data in the data source object.
	 */
	public static final ClientEvent ONERRORUPDATE		= new ClientEvent("onErrorUpdate");

	/**
	 * Fires when a visual filter changes state or completes a transition.
	 */
	public static final ClientEvent ONFILTERCHANGE		= new ClientEvent("onFilterChange");

	/**
	 * Fires when marquee looping is complete.
	 */
	public static final ClientEvent ONFINISH			= new ClientEvent("onFinish");

	/**
	 * Fires when the object receives focus.
	 */
	public static final ClientEvent ONFOCUS				= new ClientEvent("onFocus");

	/**
	 * Fires for an element just prior to setting focus on that element.
	 */
	public static final ClientEvent ONFOCUSIN			= new ClientEvent("onFocusIn");

	/**
	 * Fires for the current element with focus immediately after moving
	 * focus to another element.
	 */
	public static final ClientEvent ONFOCUSOUT			= new ClientEvent("onFocusOut");

	/**
	 * Fires when the user presses the F1 key while the browser is the active
	 * window.
	 */
	public static final ClientEvent ONHELP				= new ClientEvent("onHelp");

	/**
	 * Fires when the user presses a key.
	 */
	public static final ClientEvent ONKEYDOWN			= new ClientEvent("onKeyDown");

	/**
	 * Fires when the user presses an alphanumeric key.
	 */
	public static final ClientEvent ONKEYPRESS			= new ClientEvent("onKeyPress");

	/**
	 * Fires when the user releases a key.
	 */
	public static final ClientEvent ONKEYUP				= new ClientEvent("onKeyUp");

	/**
	 * Fires when the print or print preview layout process finishes filling the
	 * current LayoutRect object with content from the source document.
	 */
	public static final ClientEvent ONLAYOUTCOMPLETE	= new ClientEvent("onLayoutComplete");

	/**
	 * Fires immediately after the browser loads the object.
	 */
	public static final ClientEvent ONLOAD				= new ClientEvent("onLoad");

	/**
	 * Fires when the object loses the mouse capture.
	 */
	public static final ClientEvent ONLOSECAPTURE		= new ClientEvent("onLoseCapture");

	/**
	 * Fires when the user clicks the object with either mouse button.
	 */
	public static final ClientEvent ONMOUSEDOWN			= new ClientEvent("onMouseDown");

	/**
	 * Fires when the user moves the mouse pointer into the object.
	 */
	public static final ClientEvent ONMOUSEENTER		= new ClientEvent("onMouseEnter");

	/**
	 * Fires when the user moves the mouse pointer outside the boundaries
	 * of the object.
	 */
	public static final ClientEvent ONMOUSELEAVE		= new ClientEvent("onMouseLeave");

	/**
	 * Fires when the user moves the mouse over the object.
	 */
	public static final ClientEvent ONMOUSEMOVE			= new ClientEvent("onMouseMove");

	/**
	 * Fires when the user moves the mouse pointer outside the boundaries
	 * of the object.
	 */
	public static final ClientEvent ONMOUSEOUT			= new ClientEvent("onMouseOut");

	/**
	 * Fires when the user moves the mouse pointer into the object.
	 */
	public static final ClientEvent ONMOUSEOVER			= new ClientEvent("onMouseOver");

	/**
	 * Fires when the user releases a mouse button while the mouse is
	 * over the object.
	 */
	public static final ClientEvent ONMOUSEUP			= new ClientEvent("onMouseUp");

	/**
	 * Fires when the wheel button is rotated.
	 */
	public static final ClientEvent ONMOUSEWHEEL		= new ClientEvent("onMouseWheel");

	/**
	 * Fires when the object moves.
	 */
	public static final ClientEvent ONMOVE				= new ClientEvent("onMove");

	/**
	 * Fires when the object stops moving.
	 */
	public static final ClientEvent ONMOVEEND			= new ClientEvent("onMoveEnd");

	/**
	 * Fires when the object starts to move.
	 */
	public static final ClientEvent ONMOVESTART			= new ClientEvent("onMoveStart");

	/**
	 * Fires on the target object when the user pastes data, transferring
	 * the data from the system clipboard to the document.
	 */
	public static final ClientEvent ONPASTE				= new ClientEvent("onPaste");

	/**
	 * Fires when a property changes on the object.
	 */
	public static final ClientEvent ONPROPERTYCHANGE	= new ClientEvent("onPropertyChange");

	/**
	 * Fires when the state of the object has changed.
	 */
	public static final ClientEvent ONREADYSTATECHANGE	= new ClientEvent("onReadyStateChange");

	/**
	 * Fires when the user resets a form.
	 */
	public static final ClientEvent ONRESET				= new ClientEvent("onReset");

	/**
	 * Fires when the size of the object is about to change.
	 */
	public static final ClientEvent ONRESIZE			= new ClientEvent("onResize");

	/**
	 * Fires when the user finishes changing the dimensions of the object
	 * in a control selection.
	 */
	public static final ClientEvent ONRESIZEEND			= new ClientEvent("onResizeEnd");

	/**
	 * Fires when the user begins to change the dimensions of the object
	 * in a control selection.
	 */
	public static final ClientEvent ONRESIZESTART		= new ClientEvent("onResizeStart");

	/**
	 * Fires to indicate that the current row has changed in the data source
	 * and new data values are available on the object.
	 */
	public static final ClientEvent ONROWENTER			= new ClientEvent("onRowEnter");

	/**
	 * Fires just before the data source control changes the current row in
	 * the object.
	 */
	public static final ClientEvent ONROWEXIT			= new ClientEvent("onRowExit");

	/**
	 * Fires when rows are about to be deleted from the recordset.
	 */
	public static final ClientEvent ONROWSDELETE		= new ClientEvent("onRowsDelete");

	/**
	 * Fires just after new rows are inserted in the current recordset.
	 */
	public static final ClientEvent ONROWSINSERTED		= new ClientEvent("onRowsInserted");

	/**
	 * Fires when the user repositions the scroll box in the scroll bar on
	 * the object.
	 */
	public static final ClientEvent ONSCROLL			= new ClientEvent("onScroll");

	/**
	 * Fires when the current selection changes.
	 */
	public static final ClientEvent ONSELECT			= new ClientEvent("onSelect");

	/**
	 * Fires when the selection state of a document changes.
	 */
	public static final ClientEvent ONSELECTIONCHANGE	= new ClientEvent("onSelectionChange");

	/**
	 * Fires when the object is being selected.
	 */
	public static final ClientEvent ONSELECTSTART		= new ClientEvent("onSelectStart");

	/**
	 * Fires at the beginning of every loop of the marquee object.
	 */
	public static final ClientEvent ONSTART				= new ClientEvent("onStart");

	/**
	 * Fires when the user clicks the Stop button or leaves the Web page.
	 */
	public static final ClientEvent ONSTOP				= new ClientEvent("onStop");

	/**
	 * Fires when a FORM is about to be submitted.
	 */
	public static final ClientEvent ONSUBMIT			= new ClientEvent("onSubmit");

	/**
	 * Fires immediately before the object is unloaded.
	 */
	public static final ClientEvent ONUNLOAD			= new ClientEvent("onUnload");

	/**
	 * Fires when a tree node is expanded
	 */
	public static final ClientEvent EXT_ONEXPAND		= new ClientEvent("onExpand", true);

	/**
	 * Fires when a tree node with unknown child count is expanded
	 */
	public static final ClientEvent EXT_ONEXPANDEX		= new ClientEvent("onExpandEx", true);

	/**
	 * Fires when a tree node is collapsed
	 */
	public static final ClientEvent EXT_ONCOLLAPSE		= new ClientEvent("onCollapse", true);

	/**
	 * Fires when a spin-up button is clicked
	 */
	public static final ClientEvent EXT_ONSPINUP		= new ClientEvent("onSpinUp", true);

	/**
	 * Fires when a spin-down button is clicked
	 */
	public static final ClientEvent EXT_ONSPINDOWN		= new ClientEvent("onSpinDown", true);

	/**
	 * Fires when a checkbox is checked
	 */
	public static final ClientEvent EXT_ONCHECK			= new ClientEvent("onCheck", true);

	/**
	 * Fires when a checkbox is unchecked
	 */
	public static final ClientEvent EXT_ONUNCHECK		= new ClientEvent("onUnCheck", true);

	/**
	 * Fires when all rows are checked
	 */
	public static final ClientEvent EXT_ONCHECKALL		= new ClientEvent("onCheckAll", true);

	/**
	 * Fires when all rows are unchecked
	 */
	public static final ClientEvent EXT_ONUNCHECKALL	= new ClientEvent("onUnCheckAll", true);

	/**
	 * The name of the event
	 */
	private String eventName;

	/**
	 * This flag indicates an extended eventhandler
	 * which is not part of the HTML-Specification
	 */
	private boolean extended = false;

	/**
	 * Constructor for ClientEvent
	 *
	 * @param	eventName	The name of the event
	 */
	private ClientEvent(String eventName) {
		super();

		this.eventName	= eventName;
		this.extended	= false;
	}

	/**
	 * Constructor for ClientEvent
	 *
	 * @param	eventName	The name of the event
	 * @param	extended	Indicates an extended eventhandler
	 */
	private ClientEvent(String eventName, boolean extended) {
		super();

		this.eventName	= eventName;
		this.extended	= extended;
	}

	/**
	 * Indicates if this is an extended eventhandler
	 * which is not part of the HTML-Specification
	 * 
	 * @return		retruns<code>true</code> if this is
	 * 				an extended handler
	 */
	public boolean isExtended() {
		return extended;
	}

	/**
	 * Returns the name for the JavaScript event handler 
	 * 
	 * @return	String	Name of the event handler
	 */
	public String getScriptName() {
		return eventName;
	}

	/**
	 * Compares this type to the specified object.
	 *
	 * @param	obj The object to compare this <code>ClientEvent</code>
	 * 			object against.
	 * @return  <code>true</code> if the internal types are equal;
	 *          <code>false</code> otherwise.
	 */
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		} else if (obj instanceof String) {
			return eventName.equals(obj);
		} else if (obj instanceof ClientEvent) {
			return eventName.equals(((ClientEvent) obj).eventName);
		}

		return false;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return eventName.hashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Event=" + eventName;
	}
}