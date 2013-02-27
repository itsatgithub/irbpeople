/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/def/col/ColumnCheckPainter.java,v 1.11 2005/11/15 18:07:03 P001002 Exp $
 * $Revision: 1.11 $
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
import java.util.Vector;

import org.apache.ecs.ConcreteElement;
import org.apache.ecs.html.TD;

import com.cc.framework.ui.SelectMode;
import com.cc.framework.ui.control.LineIterator;
import com.cc.framework.ui.control.ListControl;
import com.cc.framework.ui.model.ColumnCheckDesignModel;
import com.cc.framework.ui.model.ColumnDesignModel;
import com.cc.framework.ui.painter.PainterContext;
import com.cc.framework.ui.painter.def.DefPainterUtility;

/**
 * Painter for the <b>columncheck </b> column
 * 
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz </a>
 * @version $Revision: 1.11 $
 */
public class ColumnCheckPainter extends ColumnPainterBase {

	/**
	 * @see com.cc.framework.ui.painter.def.col.ColumnPainterBase#createHeaderButtons(
	 *      com.cc.framework.ui.painter.PainterContext,
	 *      com.cc.framework.ui.model.ColumnDesignModel)
	 */
	protected Collection createHeaderButtons(
		PainterContext ctx,
		ColumnDesignModel rawcolumn) {

		ColumnCheckDesignModel column = (ColumnCheckDesignModel) rawcolumn;

		if (column.checkAll()) {
			ListControl ctrl = (ListControl) ctx.getControl();

			SelectMode mode = ctrl.getSelectMode();

			Vector buttons = new Vector();

			if (SelectMode.MULTIPLE.equals(mode)) {
				buttons.add(createCheckAllButton(ctx, column, mode));
			}

			buttons.add(createUncheckAllButton(ctx, column, mode));

			return buttons;
		} else {
			return null;
		}
	}

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

		ColumnCheckDesignModel column = (ColumnCheckDesignModel) rawcolumn;

		Object cellValue = iter.current(column.getProperty());

		int check = -1;

		if (cellValue instanceof Boolean) {
			check = ((Boolean) cellValue).booleanValue() ? 1 : 0;
		} else {
			// check the state ob the display object
			check = iter.currentCheckState();
		}

		ConcreteElement cb =
			DefPainterUtility.createCheckBox(
				ctx,
				iter.currentKey(),
				check,
				((ListControl) ctx.getControl()).getSelectMode(),
				column,
				20,
				false,
				column.getTransaction());

		applyStyle(cb, column, true);

		cellElement.addElement(cb);
	}
}