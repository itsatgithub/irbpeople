/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/def/col/ColumnSelectPainter.java,v 1.14 2005/11/15 18:07:03 P001002 Exp $
 * $Revision: 1.14 $
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

import org.apache.ecs.ConcreteElement;
import org.apache.ecs.html.Select;
import org.apache.ecs.html.TD;

import com.cc.framework.ui.control.ControlValuePath;
import com.cc.framework.ui.control.LineIterator;
import com.cc.framework.ui.model.ColumnDesignModel;
import com.cc.framework.ui.model.ColumnSelectDesignModel;
import com.cc.framework.ui.painter.PainterContext;
import com.cc.framework.ui.painter.PainterHelp;
import com.cc.framework.ui.painter.html.LiteralOptionsComparator;
import com.cc.framework.ui.painter.html.OptionsHelp;
import com.cc.framework.ui.painter.html.OptionsPainter;

/**
 * Painter for the <b>columnselect </b> column
 *
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz </a>
 * @version $Revision: 1.14 $
 */
public class ColumnSelectPainter extends ColumnPainterBase {

	/**
	 * @see com.cc.framework.ui.painter.def.ColumnPainter#paintCell(
	 *        org.apache.ecs.html.TD,
	 *        com.cc.framework.ui.painter.PainterContext,
	 *        com.cc.framework.ui.model.ColumnDesignModel,
	 *        com.cc.framework.ui.control.LineIterator)
	 */
	public void paintCell(
		TD cellElement,
		PainterContext ctx,
		ColumnDesignModel rawcolumn,
		LineIterator iter) {

		ConcreteElement selectCell = null;

		ColumnSelectDesignModel column = (ColumnSelectDesignModel) rawcolumn;

		Object value	= iter.current(column.getProperty());
		Object options	= getOptions(column, iter);

		if (!isEditable(ctx, column, iter)) {

			if (options == null) {
				selectCell = OptionsHelp.createDisplayElement(value);
			} else {
				selectCell =
					OptionsHelp.createDisplayElement(
						ctx,
						OptionsHelp.createIterator(column, options),
						new LiteralOptionsComparator(value));
			}
		} else {
			Select select = new Select();
			select.setDisabled(ctx.getControl().isDisabled());

			ControlValuePath path = new ControlValuePath(ctx.getControl());
			path.setProperty(ControlValuePath.KEY_TYPE, ControlValuePath.TYPE_TEXT);
			path.setProperty(ControlValuePath.KEY_ROW, iter.currentKey());
			path.setProperty(ControlValuePath.KEY_COLUMN, column.getProperty());

			select.setName(path.toString());

			if (column.getSize() != -1) {
				select.setSize(column.getSize());
			}

			if (column.isMultiple()) {
				select.setMultiple(column.isMultiple());
			}

			if (options != null) {
				OptionsPainter op =
					new OptionsPainter(
						column,
						options,
						new LiteralOptionsComparator(value));

				select.addElement(op.paint(ctx));
			} else {
				// Try to find a pre registered Jsp Body for
				// this row bean
				String body = getBodyContent(ctx, column, iter);

				if (body != null) {
					select.addElement(body);
				}
			}

			PainterHelp.setScriptHandler(select, column);

			selectCell = select;
		}

		applyStyle(selectCell, column, true);

		cellElement.addElement(selectCell);
	}

	/**
	 * Retrieves the available option elements for the current row
	 * 
	 * @param		column The columns design model
	 * @param		iter The row iterator that is positioned on the
	 * 				current roe
	 * @return		Option element collection or <code>null</code>
	 */
	protected Object getOptions(ColumnSelectDesignModel column, LineIterator iter) {
		if (column.getOptionsProperty() != null) {
			// each row has its individual option list
			return iter.current(column.getOptionsProperty());
		} else {
			// Return any shared option list 
			return column.getSharedOptions();
		}	
	}
}