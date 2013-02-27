/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/def/col/ColumnLinkPainter.java,v 1.12 2005/11/26 14:34:49 P001002 Exp $
 * $Revision: 1.12 $
 * $Date: 2005/11/26 14:34:49 $
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

import org.apache.ecs.html.Div;
import org.apache.ecs.html.TD;

import com.cc.framework.ui.control.ControlActionDef;
import com.cc.framework.ui.control.LineIterator;
import com.cc.framework.ui.model.ColumnDesignModel;
import com.cc.framework.ui.painter.ActionPainter;
import com.cc.framework.ui.painter.PainterContext;
import com.cc.framework.ui.painter.PainterHelp;

/**
 * Painter for the <b>columnlink </b> column
 *
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz </a>
 * @version $Revision: 1.12 $
 */
public class ColumnLinkPainter extends ColumnPainterBase {

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
		ColumnDesignModel column,
		LineIterator iter) throws Exception {

		String cellValue = ctx.getAsString(
			column.getConverter(),
			iter.current(column.getProperty()));

		boolean enabled = true;

		if (iter.isValidProperty(column.getEnableProperty())) {
			enabled = PainterHelp.toBoolean(iter.current(column.getEnableProperty()), true);
		}

		if (enabled) {
			ActionPainter ap = ctx.createActionPainter(ControlActionDef.ACTION_CELLCLICK);
			ap.setTransaction(column.getTransaction());
			ap.addParameter(column.getProperty());
			ap.addParameter(iter.currentKey());
			ap.setLabel(
				ctx.html(
					cellValue,
					column.filter(),
					column.getMaxLength()));
			ap.setStyle(column.getStyle());
			ap.setStyleId(column.getStyleId());
			ap.setStyleClass(column.getStyleClass());
			ap.setTarget(getTarget(column, iter));
			ap.setTooltip(getTooltip(ctx, column, iter));
			ap.setClientHandler(column);

			cellElement.addElement(ap.createElement());
		} else {
			// otherwise we only output the cell value
			Div element =
				new Div(
					ctx.html(
						cellValue,
						column.filter(),
						column.getMaxLength()));

			applyStyle(element, column, false);

			cellElement.addElement(element);
		}
	}
}