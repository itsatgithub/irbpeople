/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/def/DefFormInfoPainter.java,v 1.23 2005/05/01 08:57:08 P001002 Exp $
 * $Revision: 1.23 $
 * $Date: 2005/05/01 08:57:08 $
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

import org.apache.ecs.ConcreteElement;
import org.apache.ecs.html.TR;

import com.cc.framework.ui.control.FormControl;
import com.cc.framework.ui.painter.PainterContext;

/**
 * Painter for the Info Formular
 *
 * @author	    <a href="mailto:hschulz@scc-gmbh.com">Gernot Schulz</a>
 * @version	    $Revision: 1.23 $
 * @since       1.0
 */
public class DefFormInfoPainter extends DefFormPainter {

	/**
	 * Constructor for DefFormInfoPainter
	 *
	 * @param	painterContext	The PainterContext
	 * @param	ctrl			The Control to render
	 */
	public DefFormInfoPainter(PainterContext painterContext, FormControl ctrl) {
		super(painterContext, ctrl);
	}

	/**
	 * The method returns the HTML style class required for
	 * a specific element.
	 *
	 * @param	type The required style class
	 * @return	The style class
	 */
	public String getElementClass(int type) {

		switch (type) {
			case DefClassType.CLASS_BODY :
				return "hb";

			case DefClassType.CLASS_LABEL:
				return "hl";

			case DefClassType.CLASS_DATA:
				return "hd";

			default :
				return super.getElementClass(type);
		}
	}

	/**
	 * This method creates a row container
	 *
	 * @param		rowContent TD Element(s) with the rows content
	 * @param		rowId an optional row id
	 * @param		nestingLevel The nesting Level of the row in the form
	 * 				element tree
	 * @return		ConcreteElement
	 */
	protected ConcreteElement doCreateRow(ConcreteElement rowContent, String rowId, int nestingLevel) {

		TR row = new TR();

		if (rowId != null) {
			row.setID(rowId);
		}

		row.addElement(rowContent);

		return row;
	}

	/**
	 * @see com.cc.framework.ui.painter.def.DefFormPainter#doCreateRowSeparator(int, int)
	 */
	protected ConcreteElement doCreateRowSeparator(int cols, int nestingLevel) {
		// no separator in header forms
		return null;
	}
}