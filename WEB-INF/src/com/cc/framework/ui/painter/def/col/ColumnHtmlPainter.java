/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/def/col/ColumnHtmlPainter.java,v 1.13 2005/11/15 18:07:03 P001002 Exp $
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
import org.apache.ecs.StringElement;
import org.apache.ecs.html.TD;

import com.cc.framework.ui.control.LineIterator;
import com.cc.framework.ui.model.ColumnDesignModel;
import com.cc.framework.ui.model.value.DeferredValue;
import com.cc.framework.ui.painter.PainterContext;

/**
 * Painter for the <b>columnhtml </b> column
 *
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz </a>
 * @version $Revision: 1.13 $
 */
public class ColumnHtmlPainter extends ColumnPainterBase {

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

		String body = getBodyContent(ctx, column, iter);

		if ((body == null) && (column.getProperty() != null) && iter.isValidProperty(column.getProperty())) {
			String cellValue = ctx.getAsString(
				column.getConverter(),
				iter.current(column.getProperty()));

			if (cellValue != null) {
				// There is only a cell value and no HTML template
				// body. So use it as the HTML output
				cellElement.addElement(
					new StringElement(
						DeferredValue.evaluateToString(
							cellValue,
							column.getEnvironment())));
			} else {
				cellElement.addElement(Entities.NBSP);
			}
		} else if (body != null) {
			cellElement.addElement(
				DeferredValue.evaluateToString(body, column.getEnvironment()));
		}

		applyStyle(cellElement, column, true);
	}
}