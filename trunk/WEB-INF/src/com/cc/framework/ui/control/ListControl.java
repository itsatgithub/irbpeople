/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/control/ListControl.java,v 1.47 2005/11/13 14:03:58 P001002 Exp $
 * $Revision: 1.47 $
 * $Date: 2005/11/13 14:03:58 $
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

import com.cc.framework.adapter.RequestContext;
import com.cc.framework.common.AlgorithmFilter;
import com.cc.framework.common.SortOrder;
import com.cc.framework.convert.ConverterHelp;
import com.cc.framework.ui.SelectMode;
import com.cc.framework.ui.model.ColumnDesignModel;
import com.cc.framework.ui.model.ImageModel;
import com.cc.framework.ui.model.InnerFrame;
import com.cc.framework.util.Util;

/**
 * Base class for list controls
 *
 * @author	  <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version	  $Revision: 1.47 $
 * @since     1.0
 */
public abstract class ListControl extends Control {

	/**
	 * Simple Row Filter
	 */
	protected static class RowFilter implements AlgorithmFilter {

		/** The Row to exclude */
		private String exclude = null;

		/**
		 * Constructor
		 *
		 * @param		exclude The row toexclude
		 */
		public RowFilter(String exclude) {
			super();

			this.exclude = exclude;
		}

		/**
		 * @see com.cc.framework.common.AlgorithmFilter#exclude(java.lang.String, java.lang.Object)
		 */
		public boolean exclude(String uniqueId, Object obj) {
			return exclude.equals(uniqueId);
		}
	}

	/**
	 * Constructor for ListControl
	 */
	public ListControl() {
		super();
	}

	/**
	 * Returns the Title
	 *
	 * @return	String
	 */
	public abstract String getTitle();

	/**
	 * Returns the detail text which is displayed in the caption
	 * in the format "3 to 10 from 2000"
	 *
	 * @return	String
	 */
	public abstract String[] getDetailText();

	/**
	 * A text that is output in the body of the
	 * list element if there are no lines available (size() == 0)
	 * @return	String
	 */
	public abstract String getEmptyText();

	/**
	 * Returns the total number of pages.
	 *
	 * @return	The total number of pages. Returns -1 if the
	 * 			number of pages is unknown
	 */
	public abstract int getTotalPages();

	/**
	 * Returns the Number of total Rows in the Datamodel
	 *
	 * @return	number of rows. Returns -1 when the number
	 * 			of rows is unknown
	 */
	public abstract int getTotalRowCount();

	/**
	 * Returns the number of the current page
	 * which is displayed.
	 *
	 * @return	The number of the current page
	 */
	public abstract int getCurrentPage();

	/**
	 * Returns the number of rows on the page. Returns -1 if
	 * all rows schould be displayed.
	 *
	 * @return	The number of rows on the page
	 */
	public abstract int getRowsPerPage();

	/**
	 * Returns the minimal number of rows on the page.
	 * Returns -1 no empty lines schould be displayed.
	 *
	 * @return	The minimal number of rows on the page
	 */
	public abstract int getMinRowCount();

	/**
	 * Returns the number of page buttons.
	 *
	 * @return	The number of page buttons
	 */
	public abstract int getPageButtons();

	/**
	 * Returns true if the frame should be painted
	 * @return	true if the frame should be painted
	 */
	public abstract boolean showFrame();

	/**
	 * Returns true if the header should be painted
	 * @return	true if the header should be painted
	 */
	public abstract boolean showHeader();

	/**
	 * Returns the cell spacing for the list cells
	 *
	 * @return		spacing factor
	 */
	public abstract int getCellSpacing();

	/**
	 * Returns the cell padding for the list cells
	 *
	 * @return		Padding factor
	 */
	public abstract int getCellPadding();

	/**
	 * Returns a bit combination with the position
	 * where to create the page navigation elements of the
	 * list control
	 *
	 * @return		Bit combination
	 * @see			NavigationPosition
	 */
	public int getNavigationPosition() {
		return NavigationPosition.TOP_RIGHT;
	}

	/**
	 * Creates a lne interator
	 *
	 * @param		positioned if set to <code>true</code> the method
	 * 				returns a iterator which is positioned on the first
	 * 				line on the current page.
	 * @return		LineIterator
	 */
	public abstract LineIterator getLineIterator(boolean positioned);

	/**
	 * Retrieves a list of inner frames
	 * 
	 * @param		layoutHint The layout hint that specifies
	 * 				what frames should be selected:
	 * 				<code>AlignmentType.TOP</code> - header frames
	 * 				<code>AlignmentType.BOTTOM</code> - footer frames
	 * @return		Frame list
	 */
	public abstract InnerFrame[] getInnerFrames(Object layoutHint);

	/**
	 * Returns an Array with all columns that are visible
	 * to the user.
	 *
	 * @return	ColumnDesignModel
	 */
	public abstract ColumnDesignModel[] getVisibleColumns();

	/**
	 * Returns an Array with all columns.
	 *
	 * @return	ColumnDesignModel
	 */
	public abstract ColumnDesignModel[] getColumns();

	/**
	 * Returns the sort order for the specified column.
	 *
	 * @param	column		The name of the column
	 * @return	SortOrder
	 */
	public abstract SortOrder getSortOrder(String column);

	/**
	 * Return the selection mode of the list.
	 * @return	SelectMode
	 */
	public abstract SelectMode getSelectMode();

	/**
	 * Retrieves the row bean for the given key
	 *
	 * @param		rowkey The rows unique key
	 * @return		Row Bean or <code>null</code>
	 */
	public abstract Object getRowFromKey(String rowkey);

	/**
	 * Returns the image for the frames title
	 * 
	 * @return		ImageModel
	 */
	public abstract ImageModel getImage();

	/**
	 * @see com.cc.framework.ui.control.Control#setValue(com.cc.framework.adapter.RequestContext,
	 *      com.cc.framework.ui.control.ControlValuePath, java.lang.String[])
	 */
	public void setValue(RequestContext ctx, ControlValuePath path, String[] values)
		throws Exception {

		if (getDataModel() == null) {
			log.warn("could not set values because datamodel was null!");
			return;
		}

		String type		= path.getProperty(ControlValuePath.KEY_TYPE);
		String rowkey	= path.getProperty(ControlValuePath.KEY_ROW);
		String property	= path.getProperty(ControlValuePath.KEY_COLUMN);

		// Retrieve the row bean for the affected row
		Object rowbean	= null;

		if (rowkey != null) {
			rowbean = getRowFromKey(rowkey);
		}

		if (rowbean == null) {
			// In a radio box column the user can select only
			// one row. the radio buttons of a column share all the
			// same name -> so they can work as a radio button
			// group (mutual exclusion). Because of this it is
			// not possible to encode the row key in each buttons
			// name -> rowbean is null.
			// But the rows unique key its set as the value attribute
			// of each radio button. Because of this the request
			// will contain only one parameter for the whole column.
			// The value of this parameter is the unique key of the
			// selected row!
			if (ControlValuePath.TYPE_RADIO.equals(type)) {
				// Call the Property setter for a text element
				rowbean = getRowFromKey(values[0]);

				if (rowbean != null) {
					setRadioValue(ctx, path, rowbean, property, values[0]);
				}
			}
		} else {
			Class propertyClass = Util.getPropertyType(rowbean, property);

			if (ControlValuePath.TYPE_TEXT.equals(type)) {
				// Call the Property setter for a text element
				Util.setProperty(
					rowbean,
					property,
					ConverterHelp.getAsObject(ctx, propertyClass, values));
			} else if (ControlValuePath.TYPE_RADIO.equals(type)) {
				// Call the Property setter for a text element
				Util.setProperty(
					rowbean,
					property,
					ConverterHelp.getAsObject(ctx, propertyClass, values[0]));
			} else if (ControlValuePath.TYPE_CHECKBOX_ORIGINAL.equals(type)) {
				// If there is no coresponding actual checkbox value
				// then the checkbox is unchecked!
				path.setProperty(ControlValuePath.KEY_TYPE, ControlValuePath.TYPE_CHECKBOX);

				if (ctx.request().getParameter(path.toString()) == null) {
					setCheckboxValue(ctx, path, rowbean, property, false, SelectMode.NONE);
				}
			} else if (ControlValuePath.TYPE_CHECKBOX.equals(type)) {
				SelectMode selectmode = SelectMode.parse(path.getProperty(ControlValuePath.KEY_SELECTMODE));

				// Call the Property setter for a checkbox
				setCheckboxValue(ctx, path, rowbean, property, "on".equalsIgnoreCase(values[0]), selectmode);

			} else if (ControlValuePath.TYPE_NESTEDCONTROL.equals(type)) {
				setControlValue(ctx, path, rowbean, property, values);
			} else {
				// Default processing: Call the Property setter for the element
				Util.setProperty(
					rowbean,
					property,
					ConverterHelp.getAsObject(ctx, propertyClass, values));
			}
		}
	}

	/**
	 * This method is called to set the value of a checkbox
	 * column
	 *
	 * @param		ctx The control action context
	 * @param		path Path Name of the input element
	 * @param		rowbean The Rowbean
	 * @param		property The Column name
	 * @param		checked <code>true</code> if the checkbox
	 * 				is checked
	 * @param		selectmode the selection mode of the column
	 * @throws		Exception a derived class can throw an exception
	 * 				if the value could not be set
	 */
	protected void setCheckboxValue(
		RequestContext ctx,
		ControlValuePath path,
		Object rowbean,
		String property,
		boolean checked,
		SelectMode selectmode)
		throws Exception {

		Util.check(rowbean, property, checked);
	}

	/**
	 * This method is called to set the value of a radio button
	 * column
	 *
	 * @param		ctx The control action context
	 * @param		path Path Name of the input element
	 * @param		rowbean The Rowbean
	 * @param		property The Column name
	 * @param		value the radio button value
	 * @throws		Exception a derived class can throw an exception
	 * 				if the value could not be set
	 */
	protected void setRadioValue(
		RequestContext ctx,
		ControlValuePath path,
		Object rowbean,
		String property,
		String value)
		throws Exception {

		Util.check(rowbean, property, true);
	}

	/**
	 * This method sets the value of a nested control
	 *
	 * @param		ctx The Control Action Context
	 * @param		path Path Name of the input element
	 * @param		rowbean The Rowbean
	 * @param		property The Column name
	 * @param		values the value to set
	 * @throws		Exception a derived class can throw an exception
	 * 				if the value could not be set
	 */
	protected void setControlValue(
		RequestContext ctx,
		ControlValuePath path,
		Object rowbean,
		String property,
		String[] values)
		throws Exception {

		try {
			Object obj = Util.getProperty(rowbean, property);

			// Es muss eine Kontrollelement Instanz übergeben worden sein
			if (obj instanceof Control) {
				Control control = (Control) obj;

				// Die Aufgabe an das Kontrollelement delegieren
				control.setValue(ctx, path, values);
			}
		} catch (NoSuchMethodException nsme) {
			log.error("Row Bean has no getter for " + property, nsme);

			throw nsme;
		} catch (java.lang.reflect.InvocationTargetException ite) {
			log.error("Error invoking getter " + property, ite);

			throw ite;
		} catch (IllegalAccessException iae) {
			log.error("Illegal Access invoking getter " + property, iae);

			throw iae;
		} catch (Exception e) {
			log.error("Error while invoking getter " + property, e);

			throw e;
		}
	}

	// --------------------------------
	//          event handler
	//	--------------------------------

	/**
	 * Default Handler for the <b>ExpandEx</b> Event
	 * @param	ctx	ControlRequestContext
	 * @param	key	Unique Id as generated by the Datamodel to identify the Row
	 * @throws	Exception is thrown when an error occurs
	 */
	public void onExpandEx(ControlRequestContext ctx, String key)
		throws Exception {

		log.debug("onExpandEx(" + key + ")");
	}

	/**
	 * Default Handler for the <b>Expand</b> Event
	 * @param	ctx	ControlRequestContext
	 * @param	key	Unique Id as generated by the Datamodel to identify the Row
	 * @throws	Exception is thrown when an error occurs
	 */
	public void onExpand(ControlRequestContext ctx, String key)
		throws Exception {

		log.debug("onExpand(" + key + ")");
	}

	/**
	 * Default Handler for the <b>Collapse</b> Event
	 * @param	ctx	ControlRequestContext
	 * @param	key	Unique Id as generated by the Datamodel to identify the Row
	 * @throws	Exception is thrown when an error occurs
	 */
	public void onCollapse(ControlRequestContext ctx, String key)
		throws Exception {

		log.debug("onCollapse(" + key + ")");
	}

	/**
	 * Defaulthandler for the <b>Create</b> Event
	 * @param	ctx	ControlRequestContext
	 * @throws	Exception is thrown when an error occurs
	 */
	public void onCreate(ControlRequestContext ctx) throws Exception {
		log.debug("onCreate()");
	}

	/**
	 * Defaulthandler for the <b>Refresh</b> Event
	 * @param	ctx	ControlRequestContext
	 * @throws	Exception is thrown when an error occurs
	 */
	public void onRefresh(ControlRequestContext ctx) throws Exception {
		log.debug("onRefresh()");
	}

	/**
	 * Defaulthandler for the <b>Page</b> Event
	 * @param	ctx		ControlRequestContext
	 * @param	newPage	Number of the new Page
	 * @throws	Exception is thrown when an error occurs
	 */
	public void onPage(ControlRequestContext ctx, int newPage)
		throws Exception {

		log.debug("onPage(" + newPage + ")");
	}

	/**
	 * Defaulthandler for the <b>Drilldown</b> Event
	 * @param	ctx		ControlRequestContext
	 * @param	key		Unique Id as generated by the Datamodel to identify the Row
	 * @throws	Exception is thrown when an error occurs
	 */
	public void onDrilldown(ControlRequestContext ctx, String key)
		throws Exception {

		log.debug("onDrilldown(" + key + ")");
	}

	/**
	 * Defaulthandler for the <b>CellClick</b> Event
	 * @param	ctx		ControlRequestContext
	 * @param	column	Name of the Column (Name is the Property)
	 * @param	key		Unique Id as generated by the Datamodel to identify the Row
	 * @throws	Exception is thrown when an error occurs
	 */
	public void onCellClick(
		ControlRequestContext ctx,
		String column,
		String key)
		throws Exception {

		log.debug("onCellClick(" + column + "," + key + ")");
	}

	/**
	 * Defaulthandler for the <b>Add</b> Event
	 * @param	ctx		ControlRequestContext
	 * @param	key		Unique Id as generated by the Datamodel to identify the Row
	 * @throws	Exception is thrown when an error occurs
	 */
	public void onAdd(ControlRequestContext ctx, String key) throws Exception {
		log.debug("onAdd(" + key + ")");
	}

	/**
	 * Defaulthandler for the <b>Edit</b> Event
	 * @param	ctx		ControlRequestContext
	 * @param	key		Unique Id as generated by the Datamodel to identify the Row
	 * @throws	Exception is thrown when an error occurs
	 */
	public void onEdit(ControlRequestContext ctx, String key)
		throws Exception {

		log.debug("onEdit(" + key + ")");
	}

	/**
	 * Defaulthandler for the <b>Delete</b> Event
	 * @param	ctx		ControlRequestContext
	 * @param	key		Unique Id as generated by the Datamodel to identify the Row
	 * @throws	Exception is thrown when an error occurs
	 */
	public void onDelete(ControlRequestContext ctx, String key)
		throws Exception {

		log.debug("onDelete(" + key + ")");
	}

	/**
	 * Defaulthandler for the <b>Select</b> Event
	 * @param	ctx		ControlRequestContext
	 * @param	key		Unique Id as generated by the Datamodel to identify the Row
	 * @throws	Exception is thrown when an error occurs
	 */
	public void onSelect(ControlRequestContext ctx, String key)
		throws Exception {

		log.debug("onSelect(" + key + ")");
	}

	/**
	 * Defaulthandler for the <b>Check</b> Event
	 * @param	ctx		ControlRequestContext
	 * @param	key		Unique Id as generated by the Datamodel to identify the Row
	 * @param	mode	SelectMode
	 * @param	check	true if checked
	 * @throws	Exception Indicates an error while iterating and
	 * 			executing the algorithm
	 */
	public void onCheck(
		ControlRequestContext ctx,
		String key,
		SelectMode mode,
		boolean check)
		throws Exception {

		log.debug("onCheck(" + key + "," + mode + "," + check + ")");
	}

	/**
	 * Defaulthandler for the <b>CheckAll</b> Event
	 * @param	ctx		ControlRequestContext
	 * @param	mode	SelectMode
	 * @param	check	true if checked
	 * @throws	Exception Indicates an error while iterating and
	 * 			executing the algorithm
	 */
	public void onCheckAll(
		ControlRequestContext ctx,
		SelectMode mode,
		boolean check)
		throws Exception {

		log.debug("onCheckAll(" + mode + "," + check + ")");
	}

	/**
	 * Defaulthandler for the <b>CheckColumn</b> Event
	 * @param	ctx		ControlRequestContext
	 * @param	column	Name of the Column (Name is the Property)
	 * @param	check	true if checked
	 * @throws	Exception Indicates an error while iterating and
	 * 			executing the algorithm
	 */
	public void onCheckColumn(
		ControlRequestContext ctx,
		String column,
		boolean check)
		throws Exception {

		log.debug("onCheckColumn(" + column + "," + check + ")");
	}

	/**
	 * Defaulthandler for the <b>Sort</b> Event
	 * @param	ctx			ControlRequestContext
	 * @param	column		Name of the Column
	 * @param	direction	SortOrder
	 * @throws	Exception is thrown when an error occurs
	 */
	public void onSort(
		ControlRequestContext ctx,
		String column,
		SortOrder direction)
		throws Exception {

		log.debug("onSort(" + column + "," + direction + ")");
	}

	/**
	 * Defaulthandler for the <b>ExportList</b> Event
	 * 
	 * @param ctx
	 *            ControlRequestContext
	 * @throws Exception
	 *             is thrown when an error occurs
	 */
	public void onExportList(ControlRequestContext ctx) throws Exception {
		log.debug("onExportList()");
	}

	/**
	 * Defaulthandler for the <b>PrintList</b> Event
	 * 
	 * @param ctx
	 *            ControlRequestContext
	 * @throws Exception
	 *             is thrown when an error occurs
	 */
	public void onPrintList(ControlRequestContext ctx) throws Exception {
		log.debug("onPrintList()");
	}
}