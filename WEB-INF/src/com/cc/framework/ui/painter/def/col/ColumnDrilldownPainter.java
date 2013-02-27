/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/def/col/ColumnDrilldownPainter.java,v 1.15 2005/11/15 18:07:03 P001002 Exp $
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
import org.apache.ecs.html.Div;
import org.apache.ecs.html.TD;
import org.apache.ecs.html.TR;
import org.apache.ecs.html.Table;

import com.cc.framework.ui.control.ControlActionDef;
import com.cc.framework.ui.control.LineIterator;
import com.cc.framework.ui.model.ColumnDesignModel;
import com.cc.framework.ui.model.ImageModel;
import com.cc.framework.ui.painter.ActionPainter;
import com.cc.framework.ui.painter.PainterContext;
import com.cc.framework.ui.painter.PainterHelp;
import com.cc.framework.ui.painter.def.DefPainterUtility;

/**
 * Painter for the <b>columndrilldown </b> column
 *
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz </a>
 * @version $Revision: 1.15 $
 */
public class ColumnDrilldownPainter extends ColumnPainterBase {

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

		String cellValue = ctx.getAsString(column.getConverter(), iter.current(column.getProperty()));

		ImageModel image =
			DefPainterUtility.getItemImage(
				ctx,
				iter,
				column.getImageProperty(),
				column.getImageMap());

		boolean enabled = true;

		if (iter.isValidProperty(column.getEnableProperty())) {
			enabled = PainterHelp.toBoolean(iter.current(column.getEnableProperty()), true);
		}

		ConcreteElement drilldownCell = null;

		if (enabled) {
			// if not disabled create an anchor which includes the cell value
			ActionPainter ap = ctx.createActionPainter(ControlActionDef.ACTION_DRILLDOWN);
			ap.setTransaction(column.getTransaction());
			ap.addParameter(iter.currentKey());
			ap.setLabel(ctx.html(cellValue, column.filter(), column.getMaxLength()));
			ap.setStyle(column.getStyle());
			ap.setStyleId(column.getStyleId());
			ap.setStyleClass(column.getStyleClass());
			ap.setTarget(getTarget(column, iter));
			ap.setTooltip(getTooltip(ctx, column, iter));
			ap.setClientHandler(column);

			drilldownCell = ap.createElement();
		} else {
			// otherwise we only output the cell value
			drilldownCell =
				new Div(
					ctx.html(
						cellValue,
						column.filter(),
						column.getMaxLength()));

			applyStyle(drilldownCell, column, false);
		}

		if (image == null) {
			cellElement.addElement(drilldownCell);
		} else {
			cellElement.addElement(new Table()
				.setBorder(0)
				.setCellPadding(0)
				.setCellSpacing(0)
				.addElement(new TR()
					.addElement(new TD(ctx.createImage(image)
						.setStyle("margin-right:3px;"))
					.addElement(new TD(drilldownCell)))));
		}
	}
}