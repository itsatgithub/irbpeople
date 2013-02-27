/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/def/DefListPainter.java,v 1.128 2005/11/13 14:12:45 P001002 Exp $
 * $Revision: 1.128 $
 * $Date: 2005/11/13 14:12:45 $
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

import java.util.Collection;
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

import org.apache.ecs.AlignType;
import org.apache.ecs.ConcreteElement;
import org.apache.ecs.ElementContainer;
import org.apache.ecs.Entities;
import org.apache.ecs.html.Span;
import org.apache.ecs.html.TD;
import org.apache.ecs.html.TR;
import org.apache.ecs.html.Table;

import com.cc.framework.ui.AlignmentType;
import com.cc.framework.ui.control.ControlActionDef;
import com.cc.framework.ui.control.ControlButton;
import com.cc.framework.ui.control.LineIterator;
import com.cc.framework.ui.control.ListControl;
import com.cc.framework.ui.control.NavigationPosition;
import com.cc.framework.ui.control.TreelistControl;
import com.cc.framework.ui.javascript.JavaScript;
import com.cc.framework.ui.model.ColumnContainer;
import com.cc.framework.ui.model.ColumnDesignModel;
import com.cc.framework.ui.model.FrameTitle;
import com.cc.framework.ui.model.InnerFrame;
import com.cc.framework.ui.model.value.DeferredEnvironment;
import com.cc.framework.ui.painter.ActionPainter;
import com.cc.framework.ui.painter.Frame;
import com.cc.framework.ui.painter.PainterContext;
import com.cc.framework.ui.painter.PainterContextAtributes;
import com.cc.framework.ui.painter.PainterHelp;
import com.cc.framework.ui.painter.def.col.ColumnPainterFactory;

/**
 * Defaultpainter for Lists
 *
 * @author      <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version     $Revision: 1.128 $
 * @since       1.0
 */
public class DefListPainter extends DefPainterBase {

	// --------------------------------
	//   Members
	// --------------------------------

	/**
	 * The painters for each column of the control
	 *
	 * @see		ColumnPainter
	 */
	private transient Map colpaintermap = new Hashtable();

	// --------------------------------
	//      methods
	// --------------------------------

	/**
	 * Constructor
	 *
	 * @param	painterContext	The PainterContext
	 * @param	ctrl			The Control to render
	 */
	public DefListPainter(PainterContext painterContext, ListControl ctrl) {
		super(painterContext);
	}

	/**
	 * Retrieves the control for this painter
	 *
	 * @return		Control
	 */
	protected ListControl getCtrl() {
		return (ListControl) getPainterContext().getControl();
	}

	/**
	 * Checks if the frame should be painted
	 *
	 * @return		<code>true</code> if the frame should
	 * 				be painted
	 */
	protected boolean showFrame() {
		return getCtrl().showFrame();
	}

	/**
	 * Returns the number of page buttons to show
	 * @return	int number of buttons
	 */
	protected int getPageButtons() {
		return getCtrl().getPageButtons();
	}

	/**
	 * Returns true for the FirstPage
	 * @return	boolean
	 */
	protected boolean isFirstPage() {
		return (getCtrl().getCurrentPage() == 0);
	}

	/**
	 * Returns true for the LastPage
	 * @return	boolean
	 */
	protected boolean isLastPage() {
		int pages = getCtrl().getTotalPages();

		return (pages != -1) && (getCtrl().getCurrentPage() + 1 >= pages);
	}

	/**
	 * Returns the cell spacing for the list cells
	 *
	 * @return		spacing factor
	 */
	protected int getCellSpacing() {
		if (getCtrl().getCellSpacing() == -1) {
			// Use the painter defualt cell spacing
			return 1;
		} else {
			return getCtrl().getCellSpacing();
		}
	}

	/**
	 * Returns the cell padding for the list cells
	 *
	 * @return		Padding factor
	 */
	protected int getCellPadding() {
		if (getCtrl().getCellPadding() == -1) {
			// Use the painter defualt cell padding
			return 0;
		} else {
			return getCtrl().getCellPadding();
		}
	}

	/**
	 * Calculates the visible depth (number of column child levels) of
	 * the given column.
	 *
	 * @param		column The root column of a column hierarchy
	 * @return		retuns the depth.
	 * 				0 = the column is invisible
	 * 				1 = Column has no child columns
	 * 				greater 1 = Column has child levels
	 */
	protected int calcVisibleDepth(ColumnDesignModel column) {
		int depth = 0;

		if (getColumnPainter(column) != null) {

			if (column instanceof ColumnContainer) {
				depth = calcVisibleDepth(((ColumnContainer) column).getColumns());
			}

			if ((depth > 0) || column.getHeader().showHeader()) {
				++depth;
			}
		}

		return depth;
	}

	/**
	 * Calculates the visible depth (number of column child levels) of
	 * the given columnset.
	 *
	 * @param		columns The columnset (column forrest) to
	 * 				calculate
	 * @return		retuns the depth.
	 * 				0 = no column is visible
	 * 				1 = all Columns have no child columns
	 * 				greater 1 = Columns have child levels
	 */
	protected int calcVisibleDepth(ColumnDesignModel[] columns) {
		int depth = 0;

		for (int i = 0; i < columns.length; i++) {
			depth = Math.max(depth, calcVisibleDepth(columns[i]));
		}

		return depth;
	}

	/**
	 * Calculates the number of visible columns.
	 *
	 * @param		column the root column of a column hierarchy
	 * @return		number of visible columns in the
	 * 				column hierarchy
	 */
	protected int calcVisibleColumns(ColumnDesignModel column) {
		int cols = 0;

		if (getColumnPainter(column) != null) {
			if (column instanceof ColumnContainer) {
				cols += calcVisibleColumns(((ColumnContainer) column).getColumns());
			} else {
				cols += 1;
			}
		}

		return cols;
	}

	/**
	 * Calculates the number of visible columns in the given
	 * column set.
	 *
	 * @param		columns column set
	 * @return		number of visible columns in the
	 * 				column hierarchy
	 */
	protected int calcVisibleColumns(ColumnDesignModel[] columns) {
		int cols = 0;

		for (int i = 0; i < columns.length; i++) {
			cols += calcVisibleColumns(columns[i]);
		}

		return cols;
	}

	/**
	 * Returns the HTML-Class for a Row
	 * @param	relRowIndex	relative Row Index
	 * @param	selected <code>true</code> when the
	 * 			row is selected
	 * @return	The style class for this row
	 */
	protected String getRowClass(int relRowIndex, boolean selected) {
		if (selected) {
			return ((relRowIndex % 2) == 0) ? DefHtmlClass.LISTCONTROL_EVENLINE_SEL : DefHtmlClass.LISTCONTROL_ODDLINE_SEL;
		} else {
			return ((relRowIndex % 2) == 0) ? DefHtmlClass.LISTCONTROL_EVENLINE : DefHtmlClass.LISTCONTROL_ODDLINE;
		}
	}

	/**
	 * Creates the New-Button
	 * @return	ConcreteElement
	 */
	protected ConcreteElement doCreateNewButton() {
		// get the tooltip
		String tooltip = getFrameworkString(DefResources.FW_TOOLTIP_CREATE_ITEM);

		// create button
		ActionPainter ap = createActionPainter(ControlActionDef.ACTION_CREATE);

		ap.setImage(getImage(DefResources.BUTTON_CREATE_1));
		ap.setTooltip(tooltip);

		return ap.createElement();
	}

	/**
	 * Creates the Export-Button
	 * 
	 * @param empty
	 *            Indicates an empty list
	 * @return ConcreteElement
	 */
	protected ConcreteElement doCreateExportButton(boolean empty) {
		if (empty) {
			return createImage(DefResources.BUTTON_EXPORT_2);
		} else {
			// get the tooltip
			String tooltip = getFrameworkString(DefResources.FW_TOOLTIP_EXPORT_LIST);
	
			// create button
			ActionPainter ap = createActionPainter(ControlActionDef.ACTION_EXPORTLIST);
	
			ap.setImage(getImage(DefResources.BUTTON_EXPORT_1));
			ap.setTooltip(tooltip);
	
			return ap.createElement();
		}
	}

	/**
	 * Creates the Print-Button
	 * 
	 * @param empty
	 *            Indicates an empty list
	 * @return ConcreteElement
	 */
	protected ConcreteElement doCreatePrintButton(boolean empty) {
		if (empty) {
			return createImage(DefResources.BUTTON_PRINT_2);
		} else {
			// get the tooltip
			String tooltip = getFrameworkString(DefResources.FW_TOOLTIP_PRINT_LIST);
	
			// create button
			ActionPainter ap = createActionPainter(ControlActionDef.ACTION_PRINTLIST);
	
			ap.setImage(getImage(DefResources.BUTTON_PRINT_1));
			ap.setTooltip(tooltip);
	
			return ap.createElement();
		}
	}

	/**
	 * Creates the Refresh-Button
	 * @return	ConcreteElement
	 */
	protected ConcreteElement doCreateRefreshButton() {
		// get the tooltip
		String tooltip = getFrameworkString(DefResources.FW_TOOLTIP_REFRESH_LIST);

		// create button
		ActionPainter ap = createActionPainter(ControlActionDef.ACTION_REFRESH);

		ap.setImage(getImage(DefResources.BUTTON_REFRESH_1));
		ap.setTooltip(tooltip);

		return ap.createElement();
	}

	/**
	 * Creates the First-Button
	 * @param enabled	true if the Button is enabled
	 * @return	ConcreteElement
	 */
	protected ConcreteElement doCreateFirstButton(boolean enabled) {
		if (enabled) {
			// get the tooltip
			String tooltip = getFrameworkString(DefResources.FW_TOOLTIP_FIRSTPAGE);

			// create button
			ActionPainter ap = createActionPainter(ControlActionDef.ACTION_PAGE);

			ap.addParameter(0);
			ap.setImage(getImage(DefResources.BUTTON_FIRST_1));
			ap.setTooltip(tooltip);

			return ap.createElement();
		} else {
			return createImage(DefResources.BUTTON_FIRST_2);
		}
	}

	/**
	 * Creates the Last-Button
	 * @param	enabled		true if the Button is enabled
	 * @return	ConcreteElement
	 */
	protected ConcreteElement doCreateLastButton(boolean enabled) {
		if (enabled) {
			// get the tooltip
			String tooltip = getFrameworkString(DefResources.FW_TOOLTIP_LASTPAGE);

			// create button
			ActionPainter ap = createActionPainter(ControlActionDef.ACTION_PAGE);

			ap.addParameter(-1);
			ap.setImage(getImage(DefResources.BUTTON_LAST_1));
			ap.setTooltip(tooltip);

			return ap.createElement();
		} else {
			return createImage(DefResources.BUTTON_LAST_2);
		}
	}

	/**
	 * Creates the Prev-Button
	 * @param enabled	true if the Button is enabled
	 * @param page		page number
	 * @return	ConcreteElement
	 */
	protected ConcreteElement doCreatePrevButton(boolean enabled, int page) {
		if (enabled) {
			// get the tooltip
			String tooltip = getFrameworkString(DefResources.FW_TOOLTIP_PREVPAGE);

			// create button
			ActionPainter ap = createActionPainter(ControlActionDef.ACTION_PAGE);

			ap.addParameter(page - 1);
			ap.setImage(getImage(DefResources.BUTTON_PREVIOUS_1));
			ap.setTooltip(tooltip);

			return ap.createElement();
		} else {
			return createImage(DefResources.BUTTON_PREVIOUS_2);
		}
	}

	/**
	 * Creates the Next-Button
	 * @param enabled	true if the Button is enabled
	 * @param page		page number
	 * @return	ConcreteElement
	 */
	protected ConcreteElement doCreateNextButton(boolean enabled, int page) {
		if (enabled) {
			// get the tooltip
			String tooltip = getFrameworkString(DefResources.FW_TOOLTIP_NEXTPAGE);

			// create button
			ActionPainter ap = createActionPainter(ControlActionDef.ACTION_PAGE);

			ap.addParameter(page + 1);
			ap.setImage(getImage(DefResources.BUTTON_NEXT_1));
			ap.setTooltip(tooltip);

			return ap.createElement();
		} else {
			return createImage(DefResources.BUTTON_NEXT_2);
		}
	}

	/**
	 * Creates the Next-Button
	 * @param enabled	true if the Button is enabled
	 * @param page		page number
	 * @return	ConcreteElement
	 */
	protected ConcreteElement doCreatePageButton(boolean enabled, int page) {
		if (enabled) {
			// get the tooltip
			String tooltip =
				getFrameworkString(
					DefResources.FW_TOOLTIP_PAGE,
					new Object[]{new Integer(page + 1)});

			// create button
			ActionPainter ap = createActionPainter(ControlActionDef.ACTION_PAGE);

			ap.addParameter(page);
			ap.setLabel(Integer.toString(page + 1));
			ap.setTooltip(tooltip);

			return (ConcreteElement)
				ap.createElement().setClass(DefHtmlClass.LISTCONTROL_PAGE);
		} else {
			return (ConcreteElement) new Span()
				.addElement(Integer.toString(page + 1))
				.setClass(DefHtmlClass.LISTCONTROL_PAGE_SEL);
		}
	}

	/**
	 * Creates the navigation buttons of the list
	 *
	 * @return	collection of ConcreteElement Items
	 */
	protected Collection doCreateNavigationButtons() {
		Vector buttons = new Vector();

		if ((getCtrl().getTotalPages() > 1) || (getCtrl().getTotalPages() == -1)) {

			boolean infinite = getCtrl().getTotalPages() == -1;

			// Navigation buttons to navigate through the pages
			// ================================================

			// the next and previous buttons are only painted if
			// it makes sense, means the pages exists
			int currentPage = getCtrl().getCurrentPage();

			if (getCtrl().showButton(ControlButton.FIRSTPAGE)) {
				buttons.add(doCreateFirstButton(!isFirstPage()));
			}

			if (getCtrl().showButton(ControlButton.PREVPAGE)) {
				buttons.add(doCreatePrevButton(!isFirstPage(), currentPage));
			}

			if (getCtrl().showButton(ControlButton.PAGE)) {
				int buttoncount = getPageButtons();

				// Try to keep the selected page button in the middle
				// of the list. So the user can navigate in both
				// directions
				int first	= Math.max(0, currentPage - (buttoncount / 2));
				int last	= 0;

				if (getCtrl().getTotalPages() < 0) {
					last = currentPage;
				} else {
					last = Math.min(first + buttoncount - 1, getCtrl().getTotalPages() - 1);
				}

				// Show as many page buttons as possible 
				if (last - first < buttoncount) {
					first = Math.max(0, last - buttoncount + 1);
				}

				for (int i = first; i <= last; i++) {
					if (i > first) {
						// add a separator image
						buttons.add(doCreatePageButtonSeperator());
					}

					buttons.add(doCreatePageButton(i != currentPage, i));
				}
			}

			if (getCtrl().showButton(ControlButton.NEXTPAGE)) {
				buttons.add(doCreateNextButton(!isLastPage(), currentPage));
			}

			// Hide the last page button until the number of records
			// is known
			if (getCtrl().showButton(ControlButton.LASTPAGE)) {
				buttons.add(doCreateLastButton(!isLastPage() && !infinite));
			}
		}

		return buttons;
	}

	/**
	 * Creates the seperator between page buttons
	 * @return ConcreteElement
	 */
	protected ConcreteElement doCreatePageButtonSeperator() {
		// return createImage(DefResources.BUTTON_SEPARATOR);

		return (ConcreteElement) new Span("|").setClass(DefHtmlClass.LISTCONTROL_PAGESEPERATOR);
	}

	/**
	 * Creates the buttons in the header of the list
	 *
	 * @return	collection of ConcreteElement Items
	 */
	protected Collection doCreateTitleButtons() {
		Vector buttons = new Vector();

		if (getCtrl().showButton(ControlButton.CREATE)) {
			buttons.add(doCreateNewButton());
		}

		if (getCtrl().showButton(ControlButton.REFRESH)) {
			buttons.add(doCreateRefreshButton());
		}

		if ((getCtrl().getNavigationPosition() & NavigationPosition.TOP_RIGHT) != 0) {
			buttons.addAll(doCreateNavigationButtons());
		}

		// Additional Buttons
		boolean empty = getCtrl().getTotalPages() == 0;

		if (getCtrl().showButton(ControlButton.EXPORTLIST)) {
			buttons.add(doCreateExportButton(empty));
		}

		if (getCtrl().showButton(ControlButton.PRINTLIST)) {
			buttons.add(doCreatePrintButton(empty));
		}

		return buttons;
	}

	/**
	 * Creates the cells for the given header column
	 *
	 * @param		headerrows HTML row elements that make
	 * 				up the header
	 * @param		level the nesting level of the column
	 * @param		column the column
	 * @param		showLevel a value of <code>false</code> indicates
	 * 				that no header elements should be generated
	 * 				for the current header level
	 */
	protected void doCreateHeaderCells(
		TR[] headerrows,
		int level,
		ColumnDesignModel column,
		boolean showLevel) {

		// Get the painter for this column
		ColumnPainter columnpainter = getColumnPainter(column);

		if ((columnpainter != null) && (level < headerrows.length)) {

			if (showLevel) {
				TD headerColumn = new TD();

				if (column.getWidth() != null) {
					headerColumn.setWidth(column.getWidth());
				}

				if (!AlignmentType.LEFT.equals(column.getAlignment())) {
					headerColumn.setAlign(column.getAlignment().toString());
				}

				int colspan = calcVisibleColumns(column);
				if (colspan > 1) {
					headerColumn.setColSpan(colspan);
				}

				int rowspan = (calcVisibleDepth(column) > 1) ? 1 : (headerrows.length - level);
				if (rowspan > 1) {
					headerColumn.setRowSpan(rowspan);
				}

				// Let the column painter render the header
				try {
					columnpainter.paintHeader(
						headerColumn,
						getPainterContext(),
						column);
				} catch (Exception e) {
					// Write the messag to the log and continue
					log.error(e);
				}

				headerrows[level].addElement(headerColumn);
			}

			if (column instanceof ColumnContainer) {
				ColumnDesignModel[] childcols = ((ColumnContainer) column).getColumns();

				int childLevel			= level + (showLevel ? 1 : 0);
				boolean showChildLevel	= false;

				for (int i = 0; i < childcols.length; i++) {
					showChildLevel |= calcVisibleDepth(childcols[i]) > 0;
				}

				for (int i = 0; i < childcols.length; i++) {
					doCreateHeaderCells(headerrows, childLevel, childcols[i], showChildLevel);
				}
			}
		}
	}

	/**
	 * Creates the Row with the headers for each Column
	 *
	 * @param		columns The column painters
	 * @return		ConcreteElement
	 */
	protected ConcreteElement doCreateHeader(ColumnDesignModel[] columns) {

		int rowcount = calcVisibleDepth(columns);

		ElementContainer header = new ElementContainer();
		TR[] headerrows = new TR[rowcount];

		for (int i = 0; i < rowcount; i++) {
			headerrows[i] = new TR();

			if (i == 0) {
				headerrows[i].setClass(DefHtmlClass.LISTCONTROL_BODYHEADER);
			} else {
				headerrows[i].setClass(DefHtmlClass.LISTCONTROL_BODYHEADER + i);
			}

			header.addElement(headerrows[i]);
		}

		// now we add the column header
		for (int i = 0; i < columns.length; i++) {
			doCreateHeaderCells(headerrows, 0, columns[i], true);
		}

		return header;
	}

	/**
	 * Creates the Data for each cell of a row
	 *
	 * @param		row The HTML-Row element
	 * @param		columns The column painters
	 * @param		iter LineIterator with access to the row bean
	 * 				or <code>null</code> to create an empty row
	 * @param		env The Environment that should be used to evaluate
	 * 				expressions
	 */
	protected void doCreateCells(
		TR row,
		ColumnDesignModel[] columns,
		LineIterator iter,
		DeferredEnvironment env) {

		// now we render one line of the list control
		for (int i = 0; i < columns.length; i++) {

			if (columns[i] instanceof ColumnContainer) {
				doCreateCells(row, ((ColumnContainer) columns[i]).getColumns(), iter, env);
			} else {
				columns[i].setEnvironment(env);

				// Get the painter for this column
				ColumnPainter columnpainter = getColumnPainter(columns[i]);

				if (columnpainter != null) {
					TD cell = new TD();
					cell.setClass(DefHtmlClass.LISTCONTROL_CELL);

					if (iter == null) {
						// Create an empty row
						cell.addElement(Entities.NBSP);
					} else {
						if (!AlignmentType.LEFT.equals(columns[i].getAlignment())) {
							cell.setAlign(columns[i].getAlignment().toString());
						}

						// Apply all design rules to this cell element
						PainterHelp.applyDesignRules(
							getPageContext(),
							cell,
							columns[i].getDesignRules(),
							iter.current());

						// Let the column painter render the cell's content
						try {
							columnpainter.paintCell(
								cell,
								getPainterContext(),
								columns[i],
								iter);
						} catch (Exception e) {
							// Write the messag to the log and continue
							log.error(e);
						}
					}

					row.addElement(cell);
				}

				// Reset the columns environment because it is only
				// valid during painting
				columns[i].setEnvironment(null);
			}
		}
	}

	/**
	 * Creates the cells for an "empty list" row
	 *
	 * @param		row The HTML-Row element
	 * @param		columns The column painters
	 */
	protected void doCreateEmptyListCells(TR row, ColumnDesignModel[] columns) {
		String emptyText = getCtrl().getEmptyText();

		// if no emptytext was assigned we use the default
		if (emptyText == null) {
			emptyText = getFrameworkString(DefResourceMap.FW_EMPTY_TEXT);
		} else {
			emptyText = localize(emptyText);
		}

		int visiblecols = calcVisibleColumns(columns);

		// the list contains no rows
		row.addElement(
			new TD(html(emptyText))
				.setAlign(AlignType.center)
				.setColSpan(visiblecols)
				.setClass(DefHtmlClass.LISTCONTROL_EVENLINE));
	}

	/**
	 * Creates the Rows of the List
	 *
	 * @param		columns The column painters
	 * @param		iter LineIterator
	 * @return		ConcreteElement
	 */
	protected ConcreteElement doCreateRows(ColumnDesignModel[] columns, LineIterator iter) {

		ElementContainer rows = null;

		int minRows		= getCtrl().getMinRowCount();
		int rowCount	= getCtrl().getRowsPerPage();

		rows = new ElementContainer();

		DeferredEnvironment env = new DeferredEnvironment(getPageContext());

		int row = 0;
		while ((rowCount == -1) || (row < rowCount) || (row < minRows)) {

			if (iter.done() && (row > 0) && (row >= minRows)) {
				// no not create any further lines
				break;
			}

			boolean highlighted = false;

			// the row itself
			TR tr = new TR();
			tr.setOnMouseOver("high(this);");
			tr.setOnMouseOut("low(this);");

			if (iter.done()) {
				if (row == 0) {
					// create an "empty list" line
					doCreateEmptyListCells(tr, columns);
				} else {
					// create an empty line
					doCreateCells(tr, columns, null, null);
				}
			} else {
				env.setBean(iter.current());

				// is row highlight?
				highlighted = iter.isMarked();

				// Apply the design rules for this row
				PainterHelp.applyDesignRules(
					getPageContext(),
					tr,
					getCtrl().getDesignRules(),
					iter.current());

				doCreateCells(tr, columns, iter, env);

				// go to the next row
				iter.next();
			}

			tr.setClass(getRowClass(row, highlighted));

			rows.addElement(tr);

			++row;
		}

		return rows;
	}

	/**
	 * This method creates the body of the list control
	 *
	 * @param		columns The column painters
	 * @return		ConcreteElement
	 */
	protected ConcreteElement doCreateBody(ColumnDesignModel[] columns) {

		Table listBody = (Table) new Table()
			.setCellPadding(getCellPadding())
			.setCellSpacing(getCellSpacing())
			.setBorder(0)
			.setWidth("100%")
			.setClass(getElementClass(DefClassType.CLASS_INNER_BODY));

		// ==================
		// = Table header   =
		// ==================
		ConcreteElement header = doCreateHeader(columns);

		if (header != null && getCtrl().showHeader()) {
			listBody.addElement(header);
		}

		// ==================
		// = rows with data =
		// ==================
		ConcreteElement rows = doCreateRows(columns, getCtrl().getLineIterator(true));

		if (rows != null) {
			listBody.addElement(rows);
		}

		return listBody;
	}

	/**
	 * Creates an additional header.
	 *
	 * @return	ConcreteElement
	 */
	protected ConcreteElement doCreateHeader() {
		return doCreateFrames(AlignmentType.TOP);
	}

	/**
	 * Creates an additional footer.
	 * The current layout does not render a footer
	 * @return	ConcreteElement
	 */
	protected ConcreteElement doCreateFooter() {
		return doCreateFrames(AlignmentType.BOTTOM);
	}

	/**
	 * Creates a frame container with all frames that match
	 * the given filter
	 * 
	 * @param		alignmentFilter the filter 
	 * @return		Frame container or <code>null</code>
	 */
	protected ConcreteElement doCreateFrames(AlignmentType alignmentFilter) {
		InnerFrame[] frames = getCtrl().getInnerFrames(alignmentFilter);

		if ((frames == null) || (frames.length == 0)) {
			return null;
		} else {
			ElementContainer ec = new ElementContainer();
			for (int i = 0; i < frames.length; i++) {
				ec.addElement(getFramePainter().createInnerFrame(frames[i]));
			}

			return ec;
		}
	}

	/**
	 * Registers a column painter for the given column class
	 *
	 * @param		columnClass the columns class
	 * @param		painter Column painter instance
	 */
	public static void registerColumnPainter(Class columnClass, ColumnPainter painter) {
		ColumnPainterFactory.registerColumnPainter(columnClass, painter);
	}

	/**
	 * This method creates a concrete column painter for a given column object.
	 *
	 * @param column
	 *            the concrete column object
	 * @return a painter instance for the concrete column
	 */
	protected ColumnPainter doCreateColumnPainter(ColumnDesignModel column) {
		return ColumnPainterFactory.createPainter(column);
	}

	/**
	 * Creates a Table with a painter for each column
	 *
	 * @param		columns The list of columns
	 */
	protected void createColumnPainters(ColumnDesignModel[] columns) {

		for (int i = 0; i < columns.length; i++) {
			// Create only painters for visible columns
			if (columns[i].show(getPrincipal())) {
				assignColumnPainter(columns[i], doCreateColumnPainter(columns[i]));

				if (columns[i] instanceof ColumnContainer) {
					createColumnPainters(((ColumnContainer) columns[i]).getColumns());
				}
			}
		}
	}

	/**
	 * Retrieves the column painter for the given column.
	 * Only visible columns have a painter
	 *
	 * @param		column Column
	 * @return		painter or <code>null</code>
	 */
	protected ColumnPainter getColumnPainter(ColumnDesignModel column) {
		return (ColumnPainter) colpaintermap.get(column);
	}

	/**
	 * Assigns a column painter to a specific column
	 *
	 * @param		column Column
	 * @param		painter The CollumnPainter for this column
	 */
	protected void assignColumnPainter(ColumnDesignModel column, ColumnPainter painter) {
		colpaintermap.put(column, painter);
	}

	/**
	 * Creates the Title for the Frame
	 *
	 * @return		Frame Title or <code>null</code>
	 */
	protected FrameTitle getFrameTitle() {
		return new FrameTitle(
			getCtrl().getImage(),
			localize(getCtrl().getTitle()),
			getDetailText(),
			getCtrl().getHelp(),
			doCreateTitleButtons());
	}

	/**
	 * @see com.cc.framework.ui.painter.ControlPainter#doBeforeCreate()
	 */
	protected void doBeforeCreate() {
		getPainterContext().pushAttributes();
		getPainterContext().setAttribute(PainterContextAtributes.COMMENTS, Boolean.FALSE);

		super.doBeforeCreate();
	}

	/**
	 * Creates the HTML Code for th following Elements:
	 * <ul>
	 *   <li>Title</li>
	 *   <li>Body</li>
	 *   <li>Footer (optional)</li>
	 * </ul>
	 * @return	ConcreteElement
	 */
	protected ConcreteElement doCreateElement() {

		// if no designmodel is specified then terminate
		if (getCtrl().getDesignModel() == null) {
			return null;
		}

		// ensure that a style id will be generated
		getPainterContext().setAttribute(
			PainterContextAtributes.FORCE_STYLEID, Boolean.TRUE);

		// get all columns
		ColumnDesignModel[] columns	= getCtrl().getVisibleColumns();
		createColumnPainters(columns);

		// body
		// we create the body first because the list model may not
		// know its size() right now and may adjust it as needed
		ConcreteElement body = doCreateBody(columns);

		// render the control
		Frame control =
			getFramePainter().createFrame(getFrameTitle(), showFrame());


		if (getCtrl().getStyle() != null) {
			control.setStyle(getCtrl().getStyle());
		}

		if (getStyleId() != null) {
			control.setID(getStyleId());
		}

		// set general style class
		if (getCtrl().getStyleClass() == null) {
			control.setClass(getElementClass(DefClassType.CLASS_CONTROL));
		} else {
			control.setClass(getCtrl().getStyleClass());
		}

		if (getCtrl().getWidth() != null) {
			control.setWidth(getCtrl().getWidth());
		}

		if (getCtrl().getSummary() != null) {
			control.setSummary(getCtrl().getSummary());
		}

		// Add body Elements
		control
			.addBodyElement(doCreateHeader())
			.addBodyElement(body)
			.addBodyElement(doCreateFooter());

		// embed the control into an html span element
		Span span = new Span();
		span.addElement(control);
		span.setID("lc_" + getStyleId());

		// optional script
		ConcreteElement script = doCreateScript();
		if (script != null) {
			span.addElement(script);
		}

		return span;
	}

	/**
	 * @see com.cc.framework.ui.painter.ControlPainter#doAfterCreate()
	 */
	protected void doAfterCreate() {
		super.doAfterCreate();

		getPainterContext().popAttributes();
	}

	/**
	 * Returns the detail text for the header
	 * The first element in the array is the resource key
	 *
	 * @return	The detail text
	 */
	protected String getDetailText() {
		String[] tokens = getCtrl().getDetailText();

		if ((tokens == null) || (tokens.length == 0)) {
			return null;
		} else {
			String[] args = null;

			if (tokens.length > 1) {
				args = new String[tokens.length - 1];

				for (int i = 1; i < tokens.length; i++) {
					args[i - 1] = tokens[i];
				}
			}

			return getFrameworkString(tokens[0], args);
		}
	}

	/**
	 * @see com.cc.framework.ui.painter.ControlPainter#getElementClass(int)
	 */
	public String getElementClass(int type) {

		if (getCtrl() instanceof TreelistControl) {
			switch (type) {
				case DefClassType.CLASS_CONTROL :
					return showFrame() ? DefHtmlClass.TREELISTCONTROL : DefHtmlClass.TREELISTCONTROL_NOFRAME;
				case DefClassType.CLASS_HEADER :
					return DefHtmlClass.TREELISTCONTROL_HEADER;
				case DefClassType.CLASS_BODY :
					return DefHtmlClass.TREELISTCONTROL_BODY;
				case DefClassType.CLASS_FOOTER :
					return DefHtmlClass.TREELISTCONTROL_FOOTER;
				default :
					return super.getElementClass(type);
			}
		} else {
			switch (type) {
				case DefClassType.CLASS_CONTROL :
					return showFrame() ? DefHtmlClass.LISTCONTROL : DefHtmlClass.LISTCONTROL_NOFRAME;
				case DefClassType.CLASS_HEADER :
					return DefHtmlClass.LISTCONTROL_HEADER;
				case DefClassType.CLASS_BODY :
					return DefHtmlClass.LISTCONTROL_BODY;
				case DefClassType.CLASS_FOOTER :
					return DefHtmlClass.LISTCONTROL_FOOTER;
				default :
					return super.getElementClass(type);
			}
		}
	}

	/**
	 * Creates the Java Script Code wihich is needed by
	 * the control
	 *
	 * @return		Java Script Code
	 */
	protected JavaScript doCreateScript() {
		StringBuffer script = new StringBuffer()
			.append("var lc_")
			.append(getStyleId())
			.append(" = new ListControl('")
			.append(getStyleId())
			.append("');");

		return new JavaScript(script.toString());
	}
}