/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/def/col/ColumnRadioPainter.java,v 1.12 2005/11/15 18:07:03 P001002 Exp $
 * $Revision: 1.12 $
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

import org.apache.ecs.html.Input;
import org.apache.ecs.html.TD;

import com.cc.framework.common.CheckState;
import com.cc.framework.ui.control.ControlValuePath;
import com.cc.framework.ui.control.LineIterator;
import com.cc.framework.ui.model.ColumnDesignModel;
import com.cc.framework.ui.model.ColumnRadioDesignModel;
import com.cc.framework.ui.painter.PainterContext;
import com.cc.framework.ui.painter.def.DefResources;
import com.cc.framework.util.Util;

/**
 * Painter for the <b>columnradio </b> column
 *
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz </a>
 * @version $Revision: 1.12 $
 */
public class ColumnRadioPainter extends ColumnPainterBase {

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

		boolean checked = false;

		ColumnRadioDesignModel column = (ColumnRadioDesignModel) rawcolumn;

		if (column.isRowSpan()) {
			CheckState chkstate = CheckState.NONE;

			try {
				chkstate = Util.getCheckState(iter.current(), column.getProperty());
			} catch (Exception e) {
				// no action
			}

			checked = CheckState.CHECKED.equals(chkstate);
		} else {
			Object cellValue = iter.current(column.getProperty());

			if (cellValue != null) {
				checked = cellValue.equals(column.getValue());
			}
		}


		if (!isEditable(ctx, column, iter)) {
			cellElement.addElement(ctx.createImage(checked ? DefResources.RADIO_CHECKED : DefResources.RADIO_UNCHECKED));
			applyStyle(cellElement, column, false);
		} else {
			Input input = new Input();
			input.setDisabled(ctx.getControl().isDisabled());
			input.setType(column.getInputType().toString());
			input.setChecked(checked);

			ControlValuePath path = new ControlValuePath(ctx.getControl());
			path.setProperty(ControlValuePath.KEY_TYPE, ControlValuePath.TYPE_RADIO);
			path.setProperty(ControlValuePath.KEY_COLUMN, column.getProperty());

			if (column.isRowSpan()) {
				input.setValue(iter.currentKey());
			} else {
				input.setValue(column.getValue());

				path.setProperty(ControlValuePath.KEY_ROW, iter.currentKey());
			}
			input.setName(path.toString());

			input.setChecked(checked);

			if (column.getMaxLength() != -1) {
				input.setMaxlength(column.getMaxLength());
			}

			if (column.getSize() != -1) {
				input.setSize(column.getSize());
			}

			applyStyle(input, column, true);
			
			if (column.getStyle() == null) {
				// Use a default transparent Style
				input.setStyle("background-color:transparent;");
			}

			cellElement.addElement(input);
		}
	}
}