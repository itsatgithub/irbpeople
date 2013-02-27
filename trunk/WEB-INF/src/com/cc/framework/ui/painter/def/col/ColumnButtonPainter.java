/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/def/col/ColumnButtonPainter.java,v 1.18 2005/11/15 18:07:03 P001002 Exp $
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

import org.apache.ecs.Entities;
import org.apache.ecs.html.TD;

import com.cc.framework.ui.ImageMap;
import com.cc.framework.ui.control.ControlActionDef;
import com.cc.framework.ui.control.LineIterator;
import com.cc.framework.ui.model.ColumnButtonDesignModel;
import com.cc.framework.ui.model.ColumnDesignModel;
import com.cc.framework.ui.model.ImageModel;
import com.cc.framework.ui.painter.ActionPainter;
import com.cc.framework.ui.painter.PainterContext;
import com.cc.framework.ui.painter.PainterHelp;

/**
 * Painter for the <b>columnbutton </b> column
 *
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz </a>
 * @version $Revision: 1.18 $
 */
public class ColumnButtonPainter extends ColumnPainterBase {

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
		LineIterator iter) {

		ColumnButtonDesignModel btncolumn = (ColumnButtonDesignModel) column;

		boolean enabled		= true;

		if (iter.isValidProperty(btncolumn.getProperty())) {
			Object cellValue	= iter.current(btncolumn.getProperty());
			enabled				= PainterHelp.toBoolean(cellValue, true);
		}

		ImageMap map		= btncolumn.getImageMap();
		ImageModel image	= null;

		if (map != null) {
			image = map.mapValueToImage(iter.current(btncolumn.getImageProperty()));
		} else if (btncolumn.getImage() != null) {
			image = btncolumn.getImage();
		}

		if (enabled && ((image != null) || (btncolumn.getText() != null))) {

			// Check for custom Command
			ControlActionDef actionDef = null;

			if (btncolumn.getCommand() != null) {
				// convert the first char into uppercase
				String cmd =
					Character.toUpperCase(btncolumn.getCommand().charAt(0))
						+ btncolumn.getCommand().substring(1);


				if (!ControlActionDef.isRegistered(cmd)) {
					// Register a new Action Handler for this command
					ControlActionDef.registerActionDefinition(
						new ControlActionDef(cmd, new Class[]{String.class}));
				}

				actionDef = ControlActionDef.query(cmd);
			}

			if (actionDef == null) {
				// If no user command is available then use the generic
				// CellClick command
				actionDef = ControlActionDef.ACTION_CELLCLICK;
			}

			ActionPainter ap = ctx.createActionPainter(actionDef);
			ap.setTransaction(column.getTransaction());

			if (ControlActionDef.ACTION_CELLCLICK.equals(actionDef)) {
				// We need the additional property parameter
				// for the generic event handler
				ap.addParameter(btncolumn.getProperty());
			}
			ap.addParameter(iter.currentKey());
			ap.setImage(image);

			if (btncolumn.getText() != null) {
				ap.setLabel(ctx.html(ctx.localize(btncolumn.getText())));
			}
			ap.setTarget(getTarget(btncolumn, iter));
			ap.setTooltip(getTooltip(ctx, btncolumn, iter));
			ap.setStyle(btncolumn.getStyle());
			ap.setStyleId(btncolumn.getStyleId());
			ap.setStyleClass(btncolumn.getStyleClass());
			ap.setClientHandler(btncolumn);

			cellElement.addElement(ap.createElement());
		} else {
			cellElement.addElement(Entities.NBSP);

			applyStyle(cellElement, column, true);
		}
	}
}