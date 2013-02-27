/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/def/ColumnPainter.java,v 1.5 2005/07/28 19:41:14 P001002 Exp $
 * $Revision: 1.5 $
 * $Date: 2005/07/28 19:41:14 $
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

package com.cc.framework.ui.painter.def;

import org.apache.ecs.html.TD;

import com.cc.framework.ui.control.LineIterator;
import com.cc.framework.ui.model.ColumnDesignModel;
import com.cc.framework.ui.painter.PainterContext;

/**
 * Interface for all column painters
 * 
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz </a>
 * @version $Revision: 1.5 $
 */
public interface ColumnPainter {

	/**
	 * Creates a Cell
	 * 
	 * @param cellElement
	 *            The cell element
	 * @param ctx
	 *            the painter context
	 * @param column
	 *            the columns design model
	 * @param iter
	 *            the row iterator positioned on the current row bean
	 * @throws Exception
	 *             When an error occurs
	 */
	public void paintCell(
		TD cellElement,
		PainterContext ctx,
		ColumnDesignModel column,
		LineIterator iter) throws Exception;

	/**
	 * Creates the column header
	 * 
	 * @param headerElement
	 *            The header element
	 * @param ctx
	 *            the painter context
	 * @param column
	 *            the columns design model
	 * @throws Exception
	 *             When an error occurs
	 */
	public void paintHeader(
		TD headerElement,
		PainterContext ctx,
		ColumnDesignModel column) throws Exception;
}