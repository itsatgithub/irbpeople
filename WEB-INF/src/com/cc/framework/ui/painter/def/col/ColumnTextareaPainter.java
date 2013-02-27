/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/def/col/ColumnTextareaPainter.java,v 1.15 2005/11/15 18:07:03 P001002 Exp $
 * $Revision: 1.15 $
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
import org.apache.ecs.html.TD;
import org.apache.ecs.html.TextArea;

import com.cc.framework.ui.control.ControlValuePath;
import com.cc.framework.ui.control.LineIterator;
import com.cc.framework.ui.model.ColumnDesignModel;
import com.cc.framework.ui.model.ColumnTextareaDesignModel;
import com.cc.framework.ui.painter.PainterContext;
import com.cc.framework.ui.painter.PainterHelp;

/**
 * Painter for the <b>columntextarea </b> column
 *
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz </a>
 * @version $Revision: 1.15 $
 */
public class ColumnTextareaPainter extends ColumnPainterBase {

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
		LineIterator iter) throws Exception {

		ColumnTextareaDesignModel column = (ColumnTextareaDesignModel) rawcolumn;

		String cellValue = ctx.getAsString(
			column.getConverter(),
			iter.current(column.getProperty()));

		ConcreteElement textCell = null;

		TextArea input = new TextArea();
		input.setDisabled(ctx.getControl().isDisabled());

		ControlValuePath path = new ControlValuePath(ctx.getControl());
		path.setProperty(ControlValuePath.KEY_TYPE, ControlValuePath.TYPE_TEXT);
		path.setProperty(ControlValuePath.KEY_ROW, iter.currentKey());
		path.setProperty(ControlValuePath.KEY_COLUMN, column.getProperty());

		input.setName(path.toString());

		applyStyle(input, column, true);

		if (cellValue != null) {
			input.addElement(cellValue);
		}

		if (column.getRows() != -1) {
			input.setRows(column.getRows());
		}

		if (column.getCols() != -1) {
			input.setCols(column.getCols());
		}

		if (column.getWrap() != null) {
			input.setWrap(column.getWrap());
		}

		if (!isEditable(ctx, column, iter)) {
			input.setReadOnly(true);
		}

		PainterHelp.setScriptHandler(input, column);

		textCell = input;

		cellElement.addElement(textCell);
	}
}