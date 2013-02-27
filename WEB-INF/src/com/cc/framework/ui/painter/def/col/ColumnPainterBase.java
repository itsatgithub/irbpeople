/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/def/col/ColumnPainterBase.java,v 1.23 2005/11/15 18:07:03 P001002 Exp $
 * $Revision: 1.23 $
 * $Date: 2005/11/15 18:07:03 $
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


package com.cc.framework.ui.painter.def.col;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;

import org.apache.ecs.AlignType;
import org.apache.ecs.ConcreteElement;
import org.apache.ecs.html.IMG;
import org.apache.ecs.html.TD;

import com.cc.framework.Globals;
import com.cc.framework.common.SortOrder;
import com.cc.framework.ui.AlignmentType;
import com.cc.framework.ui.ImageMap;
import com.cc.framework.ui.SelectMode;
import com.cc.framework.ui.control.ControlActionDef;
import com.cc.framework.ui.control.LineIterator;
import com.cc.framework.ui.control.ListControl;
import com.cc.framework.ui.model.ClientEvent;
import com.cc.framework.ui.model.ColumnDesignModel;
import com.cc.framework.ui.model.ColumnHeaderDesignModel;
import com.cc.framework.ui.model.ImageModel;
import com.cc.framework.ui.painter.ActionPainter;
import com.cc.framework.ui.painter.PainterContext;
import com.cc.framework.ui.painter.PainterHelp;
import com.cc.framework.ui.painter.def.ColumnPainter;
import com.cc.framework.ui.painter.def.DefResources;

/**
 * Base Class for column painters
 *
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz </a>
 * @version $Revision: 1.23 $
 */
public class ColumnPainterBase implements ColumnPainter {

	/**
	 * Constructor
	 */
	public ColumnPainterBase() {
		super();
	}

	/**
	 * @see com.cc.framework.ui.painter.def.ColumnPainter#paintCell(org.apache.ecs.html.TD,
	 *      com.cc.framework.ui.painter.PainterContext,
	 *      com.cc.framework.ui.model.ColumnDesignModel,
	 *      com.cc.framework.ui.control.LineIterator)
	 */
	public void paintCell(
		TD cellElement,
		PainterContext ctx,
		ColumnDesignModel column,
		LineIterator iter) throws Exception {

		String cellValue = ctx.getAsString(
			column.getConverter(),
			iter.current(column.getProperty()));

		cellElement.addElement(cellValue);
	}

	/**
	 * Try to find a pre registered Jsp Body for this row bean
	 * 
	 * @param ctx
	 *            the painter context
	 * @param column
	 *            the columns design model
	 * @param iter
	 *            the iterator with the current row bean
	 * @return Body content or <code>null</code>
	 */
	protected static String getBodyContent(
		PainterContext ctx,
		ColumnDesignModel column,
		LineIterator iter) {

		String body = null;

		// Try to find a pre registered Jsp Body for
		// this row bean
		Hashtable ht =
			(Hashtable) ctx.getPageContext().getAttribute(
				Globals.JSPBODYBUFFER_KEY);

		if (ht != null) {
			String hashkey =
				Integer.toHexString(column.hashCode())
					+ "@"
					+ iter.currentKey();

			body = (String) ht.get(hashkey);
		}

		return body;
	}

	/**
	 * Applies the column styles to the HTML element
	 * 
	 * @param element
	 *            HTML element
	 * @param column
	 *            Column
	 * @return the HTML element
	 */
	protected ConcreteElement applyStyle(ConcreteElement element, ColumnDesignModel column) {
		return applyStyle(element, column, false);
	}

	/**
	 * Applies the column styles to the HTML element
	 * 
	 * @param element
	 *            HTML element
	 * @param column
	 *            Column
	 * @param addHandlers
	 *            This flag indicates that all script handlers should be added
	 *            to the element
	 * @return the HTML element
	 */
	protected ConcreteElement applyStyle(
			ConcreteElement element,
			ColumnDesignModel column,
			boolean addHandlers) {

		if ((element == null) || (column == null)) {
			return element;
		}

		if (column.getStyle() != null) {
			element.setStyle(column.getStyle());
		}

		if (column.getStyleClass() != null) {
			element.setClass(column.getStyleClass());
		}

		if (column.getStyleId() != null) {
			element.setID(column.getStyleId());
		}

		if (addHandlers) {
			PainterHelp.setScriptHandler(element, column);
		}
		
		return element;
	}

	/**
	 * Creates the buttons for the column header
	 * 
	 * @param ctx
	 *            the painter contex
	 * @param column
	 *            the columns design model
	 * @return collection of ConcreteElement Items
	 */
	protected Collection createHeaderButtons(PainterContext ctx, ColumnDesignModel column) {

		// no buttons in the base class
		return null;
	}

	/**
	 * Returns the Image of a column header
	 * 
	 * @param header
	 *            the header design model
	 * @return ImageModel
	 */
	protected ImageModel getHeaderImage(ColumnHeaderDesignModel header) {

		// render item as image
		String imageref = header.getImageRef();

		if (imageref == null) {
			return null;
		}

		ImageMap imageMap = header.getImageMap();

		if (imageMap == null) {
			return null;
		}

		return imageMap.mapValueToImage(imageref);
	}

	/**
	 * @see com.cc.framework.ui.painter.def.ColumnPainter#paintHeader(org.apache.ecs.html.TD,
	 *      com.cc.framework.ui.painter.PainterContext,
	 *      com.cc.framework.ui.model.ColumnDesignModel)
	 */
	public void paintHeader(
		TD headerElement,
		PainterContext ctx,
		ColumnDesignModel column) {

		ListControl ctrl = (ListControl) ctx.getControl();
		ColumnHeaderDesignModel header = column.getHeader();

		Collection buttons = createHeaderButtons(ctx, column);

		int maxLength =
			header.getMaxLength() == -1
				? column.getMaxLength()
				: header.getMaxLength();

		if (buttons != null) {
			Iterator iter = buttons.iterator();
			while (iter.hasNext()) {
				headerElement.addElement((ConcreteElement) iter.next());
			}
		}

		String title;
		if (header.isBodyInclude()) {
			// if the body is specified within the jsp page we do not localize it!
			title = header.getTitle();
		} else {
			// if the discription attribute is used, we localized it!
			title = ctx.localize(header.getTitle());
		}

		ImageModel image = getHeaderImage(header);

		if (image != null) {
			IMG img =
				ctx.createImage(image)
					.setVspace(0)
					.setAlign(AlignType.absmiddle);

			if (title != null) {
				img.setStyle("margin-right:3px;");
			}

			headerElement.addElement(img);
		}

		if (column.isSortable()) {
			// if the column can be sorted generate a hyperlink
			SortOrder curSortOrder = ctrl.getSortOrder(column.getProperty());
			SortOrder newSortOrder = SortOrder.ASCENDING;
			ImageModel sortImage = null;

			if (SortOrder.ASCENDING.equals(curSortOrder)) {
				newSortOrder = SortOrder.DESCENDING;
				sortImage = ctx.getImage(DefResources.BUTTON_SORTASC);
			} else if (SortOrder.DESCENDING.equals(curSortOrder)) {
				sortImage = ctx.getImage(DefResources.BUTTON_SORTDESC);
			} else {
				sortImage = ctx.getImage(DefResources.BUTTON_SORTABLE);
			}

			ActionPainter ap = ctx.createActionPainter(ControlActionDef.ACTION_SORT);
			ap.setTransaction(column.getTransaction());
			ap.addParameter(column.getProperty());
			ap.addParameter(newSortOrder);
			ap.setClientHandler(header);

			if (title != null) {
				ap.setLabel(ctx.html(title, header.filter(), maxLength));
			}

			if (header.getTooltip() != null) {
				ap.setTooltip(ctx.html(ctx.localize(header.getTooltip()), header.filter()));
			}

			ap.setStyleId(column.getStyleId());

			// Add the sort icon on the action
			ap.setImage(sortImage);
			ap.setImageAlign(AlignmentType.RIGHT);

			headerElement.addElement(ap.createElement());
		} else {
			// Avoid writing a null-Title when not necessary
			if ((title != null) || (image == null)) {
				headerElement.addElement(
					ctx.html(title, header.filter(), maxLength));
			}

			if (header.getTooltip() != null) {
				headerElement.setTitle(ctx.html(ctx.localize(header.getTooltip()), header.filter()));
			}

			if (header.getStyleId() != null) {
				headerElement.setID(header.getStyleId());
			}

			if (header.getStyle() != null) {
				headerElement.setStyle(header.getStyle());
			}

			if (header.getAlignment() != null) {
				headerElement.setAlign(header.getAlignment().toString());
			}

			// add the Script Handlers
			PainterHelp.setScriptHandler(headerElement, header);
		}
	}

	/**
	 * Creates the check all button
	 * 
	 * @param ctx
	 *            the painter context
	 * @param column
	 *            the columns design model
	 * @param mode
	 *            the select mode of the list
	 * @return ConcreteElement
	 */
	protected ConcreteElement createCheckAllButton(
		PainterContext ctx,
		ColumnDesignModel column,
		SelectMode mode) {

		// get the tooltip
		String tooltip =
			ctx.getFrameworkString(DefResources.FW_TOOLTIP_CHECKALL);

		// create button
		ActionPainter ap = ctx.createActionPainter(ControlActionDef.ACTION_CHECKALL);
		ap.setTransaction(column.getTransaction());
		ap.addParameter(mode);
		ap.addParameter(true);
		ap.setImage(ctx.getImage(DefResources.BUTTON_CHECKALL_1));
		ap.setImageStyle("margin-right:2px;");
		ap.setTooltip(tooltip);
		ap.addEventHandler(
			ClientEvent.ONCLICK,
			column.getHandler(ClientEvent.EXT_ONCHECKALL));

		return ap.createElement();
	}

	/**
	 * Creates the uncheck all button
	 * 
	 * @param ctx
	 *            the painter context
	 * @param column
	 *            the columns design model
	 * @param mode
	 *            the select mode of the list
	 * @return ConcreteElement
	 */
	protected ConcreteElement createUncheckAllButton(
		PainterContext ctx,
		ColumnDesignModel column,
		SelectMode mode) {

		// get the tooltip
		String tooltip =
			ctx.getFrameworkString(DefResources.FW_TOOLTIP_UNCHECKALL);

		// create button
		ActionPainter ap = ctx.createActionPainter(ControlActionDef.ACTION_CHECKALL);
		ap.setTransaction(column.getTransaction());
		ap.addParameter(mode);
		ap.addParameter(false);
		ap.setImage(ctx.getImage(DefResources.BUTTON_UNCHECKALL_1));
		ap.setImageStyle("margin-right:4px;");
		ap.setTooltip(tooltip);
		ap.addEventHandler(
			ClientEvent.ONCLICK,
			column.getHandler(ClientEvent.EXT_ONUNCHECKALL));

		return ap.createElement();
	}

	/**
	 * Creates the check all button
	 * 
	 * @param ctx
	 *            the painter context
	 * @param column
	 *            the columns design model
	 * @return ConcreteElement
	 */
	protected ConcreteElement createCheckColumnButton(
		PainterContext ctx,
		ColumnDesignModel column) {

		// get the tooltip
		String tooltip =
			ctx.getFrameworkString(DefResources.FW_TOOLTIP_CHECKALL);

		// create button
		ActionPainter ap = ctx.createActionPainter(ControlActionDef.ACTION_CHECKCOLUMN);
		ap.setTransaction(column.getTransaction());
		ap.addParameter(column.getProperty());
		ap.addParameter(true);
		ap.setImage(ctx.getImage(DefResources.BUTTON_CHECKALL_1));
		ap.setImageStyle("margin-right:2px;");
		ap.setTooltip(tooltip);
		ap.addEventHandler(
			ClientEvent.ONCLICK,
			column.getHandler(ClientEvent.EXT_ONCHECKALL));

		return ap.createElement();
	}

	/**
	 * Creates the uncheck all button
	 * 
	 * @param ctx
	 *            the painter context
	 * @param column
	 *            the columns design model
	 * @return ConcreteElement
	 */
	protected ConcreteElement createUncheckColumnButton(
		PainterContext ctx,
		ColumnDesignModel column) {

		// get the tooltip
		String tooltip =
			ctx.getFrameworkString(DefResources.FW_TOOLTIP_UNCHECKALL);

		// create button
		ActionPainter ap = ctx.createActionPainter(ControlActionDef.ACTION_CHECKCOLUMN);
		ap.setTransaction(column.getTransaction());
		ap.addParameter(column.getProperty());
		ap.addParameter(false);
		ap.setImage(ctx.getImage(DefResources.BUTTON_UNCHECKALL_1));
		ap.setImageStyle("margin-right:4px;");
		ap.setTooltip(tooltip);
		ap.addEventHandler(
			ClientEvent.ONCLICK,
			column.getHandler(ClientEvent.EXT_ONUNCHECKALL));

		return ap.createElement();
	}

	/**
	 * Returns the Target Attribute which should be included in a HyperLink
	 * 
	 * @param column
	 *            ColumnDesignModel
	 * @param iter
	 *            LineIterator
	 * @return String
	 */
	protected String getTarget(ColumnDesignModel column, LineIterator iter) {
		String target = null;

		if (column.getTargetProperty() != null) {
			target = String.valueOf(iter.current(column.getTargetProperty()));
		} else {
			target = column.getTarget();
		}

		return target;
	}

	/**
	 * Returns the localised and html encoded Tooltip Attribute which should be
	 * used for the row
	 * 
	 * @param ctx
	 *            the painter context
	 * @param column
	 *            ColumnDesignModel
	 * @param iter
	 *            LineIterator
	 * @return String
	 */
	protected String getTooltip(
		PainterContext ctx,
		ColumnDesignModel column,
		LineIterator iter) {
		String tooltip = null;

		if (column.getTooltipProperty() != null) {
			// the property tooltip is not localized!
			tooltip = String.valueOf(iter.current(column.getTooltipProperty()));
		} else {
			// localize static tooltip
			tooltip = ctx.localize(column.getTooltip());
		}

		if (tooltip == null) {
			return null;
		} else {
			return ctx.html(tooltip);
		}
	}

	/**
	 * Checks if the column or cell is editable or not
	 * 
	 * @param ctx
	 *            the painter context
	 * @param column
	 *            The column
	 * @param iter
	 *            the iterator with the current row bean
	 * @return returns <code>true</code> if the cell is editable
	 */
	protected boolean isEditable(PainterContext ctx, ColumnDesignModel column, LineIterator iter) {
		if (ctx.isDisplayOnly()) {
			// Ignore any editable propertiy settings when this
			// control is in display only mode
			return false;
		} else if (column.isEditable()) {
			return true;
		} else if (column.getEditableProperty() != null) {
			return PainterHelp.toBoolean(iter.current(column.getEditableProperty()), false);
		}

		// Tze column is not editable
		return false;
	}
}