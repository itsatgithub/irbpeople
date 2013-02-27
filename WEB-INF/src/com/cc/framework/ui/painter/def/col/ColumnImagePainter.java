/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/def/col/ColumnImagePainter.java,v 1.13 2005/11/15 18:07:03 P001002 Exp $
 * $Revision: 1.13 $
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
import org.apache.ecs.html.IMG;
import org.apache.ecs.html.TD;

import com.cc.framework.ui.ImageMap;
import com.cc.framework.ui.control.ControlActionDef;
import com.cc.framework.ui.control.LineIterator;
import com.cc.framework.ui.model.ColumnDesignModel;
import com.cc.framework.ui.model.ColumnImageDesignModel;
import com.cc.framework.ui.model.ImageModel;
import com.cc.framework.ui.painter.ActionPainter;
import com.cc.framework.ui.painter.PainterContext;
import com.cc.framework.ui.painter.PainterHelp;
import com.cc.framework.ui.painter.def.DefResources;

/**
 * Painter for the <b>columnimage </b> column
 *
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz </a>
 * @version $Revision: 1.13 $
 */
public class ColumnImagePainter extends ColumnPainterBase {

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

		Object cellValue = iter.current(column.getProperty());

		boolean enabled = true;

		if (iter.isValidProperty(column.getEnableProperty())) {
			enabled = PainterHelp.toBoolean(iter.current(column.getEnableProperty()), true);
		}
		
		ImageMap map = column.getImageMap();
		ImageModel image = null;

		if (map != null) {
			image = map.mapValueToImage(cellValue);
		} else {
			// if no imagemap is spefified check if a image can
			// be generated based on the object type
			if (cellValue instanceof Boolean) {
				image = ((Boolean) cellValue).booleanValue() ? ctx.getImage(DefResources.ICON_CHECK) : null;
			}
		}

		if (image != null) {
			String tooltip = getTooltip(ctx, column, iter);

			if (((ColumnImageDesignModel) column).isClickable()) {
				ActionPainter ap = ctx.createActionPainter(ControlActionDef.ACTION_CELLCLICK);
				ap.setTransaction(column.getTransaction());
				ap.addParameter(column.getProperty());
				ap.addParameter(iter.currentKey());
				ap.setImage(image);
				ap.setTarget(getTarget(column, iter));
				ap.setTooltip(tooltip);
				ap.setStyle(column.getStyle());
				ap.setStyleId(column.getStyleId());
				ap.setStyleClass(column.getStyleClass());
				ap.setClientHandler(column);
				ap.setActive(enabled);

				cellElement.addElement(ap.createElement());
			} else {
				IMG img = ctx.createImage(image);

				if (tooltip != null) {
					img.setTitle(ctx.html(tooltip, column.filter()));
				}

				PainterHelp.setScriptHandler(img, column);

				cellElement.addElement(img);
				applyStyle(cellElement, column, true);
			}
		} else {
			cellElement.addElement(Entities.NBSP);
			applyStyle(cellElement, column, true);
		}
	}
}