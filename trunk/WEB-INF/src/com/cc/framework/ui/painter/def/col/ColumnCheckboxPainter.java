/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/def/col/ColumnCheckboxPainter.java,v 1.18 2005/11/15 18:07:03 P001002 Exp $
 * $Revision: 1.18 $
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
import org.apache.ecs.ElementContainer;
import org.apache.ecs.Entities;
import org.apache.ecs.html.Input;
import org.apache.ecs.html.TD;

import com.cc.framework.common.CheckState;
import com.cc.framework.ui.SelectMode;
import com.cc.framework.ui.control.ControlValuePath;
import com.cc.framework.ui.control.LineIterator;
import com.cc.framework.ui.model.ColumnCheckboxDesignModel;
import com.cc.framework.ui.model.ColumnDesignModel;
import com.cc.framework.ui.painter.PainterContext;
import com.cc.framework.ui.painter.PainterHelp;
import com.cc.framework.ui.painter.def.DefResources;
import com.cc.framework.util.Util;

/**
 * Painter for the <b>columncheckbox </b> column
 *
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz </a>
 * @version $Revision: 1.18 $
 */
public class ColumnCheckboxPainter extends ColumnPainterBase {

	/**
	 * @see com.cc.framework.ui.painter.def.col.ColumnPainterBase#createHeaderButtons(
	 *      com.cc.framework.ui.painter.PainterContext,
	 *      com.cc.framework.ui.model.ColumnDesignModel)
	 */
	protected Collection createHeaderButtons(
		PainterContext ctx,
		ColumnDesignModel rawcolumn) {

		ColumnCheckboxDesignModel column = (ColumnCheckboxDesignModel) rawcolumn;

		if (column.checkAll()) {
			Vector buttons = new Vector();

			buttons.add(createCheckColumnButton(ctx, column));
			buttons.add(createUncheckColumnButton(ctx, column));

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

		ColumnCheckboxDesignModel column = (ColumnCheckboxDesignModel) rawcolumn;

		CheckState chkstate = CheckState.NONE;

		try {
			chkstate = Util.getCheckState(iter.current(), column.getProperty());
		} catch (Exception e) {
			// no action
		}

		if (CheckState.UNSELECTABLE.equals(chkstate)) {
			// If the checkbox is unselectable we render no checkbox
			cellElement.addElement(Entities.NBSP);
		} else if (!isEditable(ctx, column, iter)) {

			if ((null == chkstate) || CheckState.NONE.equals(chkstate)) {
				// If the checkbox is unselectable we render no checkbox
				cellElement.addElement(Entities.NBSP);
			} else if (CheckState.UNDEFINED.equals(chkstate)) {
				// If the checkbox is of undefind state --> disable the checkbox
				cellElement.addElement(ctx.createImage(DefResources.CHECKBOX_INVALID));
			} else if (CheckState.CHECKED.equals(chkstate)) {
				// Checkbox was selected
				cellElement.addElement(ctx.createImage(DefResources.CHECKBOX_CHECKED));
			} else if (CheckState.UNCHECKED.equals(chkstate)) {
				// Checkbox was unselected
				cellElement.addElement(ctx.createImage(DefResources.CHECKBOX_UNCHECKED));
			}

			applyStyle(cellElement, column, true);
		} else {
			ConcreteElement checkboxCell	= null;
			Input input						= new Input();
			input.setDisabled(ctx.getControl().isDisabled());
			input.setType(Input.checkbox);

			ControlValuePath path			= new ControlValuePath(ctx.getControl());
			path.setProperty(ControlValuePath.KEY_TYPE, ControlValuePath.TYPE_CHECKBOX);
			path.setProperty(ControlValuePath.KEY_ROW, iter.currentKey());
			path.setProperty(ControlValuePath.KEY_COLUMN, column.getProperty());
			path.setProperty(ControlValuePath.KEY_SELECTMODE, column.getSelectMode());

			input.setName(path.toString());

			if (column.getStyle() != null) {
				input.setStyle(column.getStyle());
			}

			if (column.getStyleId() != null) {
				input.setID(column.getStyleId());
			} else {
				// Use a default transparent Style
				input.setStyle("background-color:transparent;");
			}

			if (column.getStyleClass() != null) {
				input.setClass(column.getStyleClass());
			}

			PainterHelp.setScriptHandler(input, column);

			if (CheckState.CHECKED.equals(chkstate)) {
				input.setChecked(true);

				// We won't be able to track unchecked checkboxes because
				// they are not included in the HTTP-Request. So we need
				// a indicator that the checkbox was checked initially
				path.setProperty(ControlValuePath.KEY_TYPE, ControlValuePath.TYPE_CHECKBOX_ORIGINAL);

				checkboxCell =
					new ElementContainer().addElement(input).addElement(
						new Input(Input.hidden).setName(path.toString()));
			} else {
				checkboxCell = input;
			}

			// If the select mode is set to single we must add
			// an javascript handler which handles the select state
			// within the column. This is necessary because if the
			// control acts als formelement the checkbox column
			// works without server roundtrips
			if (ctx.getControl().isFormElement() && SelectMode.SINGLE.equals(column.getSelectMode())) {
				//String columnId		= Integer.toString(column.hashCode());
				//String checkboxId	= columnId + "_" + iter.currentKey();

				StringBuffer script =
					new StringBuffer()
						.append("lc_")
						.append(ctx.getStyleId())
						.append(".handleCheckState(this, '")
						.append(column.getProperty())
						.append("', SelectMode.SINGLE);");

				input.setOnClick(script.toString());
			}

			// Special treatment for a checkstate -1 / 2
			// NOTE: This option is only available if the
			// cellvalue is based on an integer or CheckState object
			if (CheckState.UNSELECTABLE.equals(chkstate)) {
				// If the checkbox is unselectable we render no checkbox
				cellElement.addElement(Entities.NBSP);
			} else if (CheckState.UNDEFINED.equals(chkstate)) {
				// If the checkbox is of undefind state --> disable the checkbox
				cellElement.addElement(input.setDisabled(true));
			} else {
				// Checkbox was selected or unselected --> no changes
				cellElement.addElement(checkboxCell);
			}
		}
	}
}