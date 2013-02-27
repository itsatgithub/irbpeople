/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/html/HtmlSpinPainter.java,v 1.23 2005/08/02 19:15:01 P001002 Exp $
 * $Revision: 1.23 $
 * $Date: 2005/08/02 19:15:01 $
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

package com.cc.framework.ui.painter.html;

import org.apache.ecs.ConcreteElement;
import org.apache.ecs.html.Input;
import org.apache.ecs.html.TD;
import org.apache.ecs.html.TR;
import org.apache.ecs.html.Table;

import com.cc.framework.ui.control.SpinControl;
import com.cc.framework.ui.model.ClientEvent;
import com.cc.framework.ui.painter.PainterContext;
import com.cc.framework.ui.painter.PainterContextAtributes;

/**
 * Painter for the spin control.
 *
 * @author      <a href="mailto:gschulz@scc-gmbh.com">Gernot Schulz</a>
 * @version     $Revision: 1.23 $
 * @since       1.0
 */
public class HtmlSpinPainter extends HtmlTextPainter {

	/**
	 * Constructor for HtmlCalendarPainter
	 *
	 * @param	painterContext	The PainterContext
	 * @param	ctrl			The Control to render
	 */
	public HtmlSpinPainter(PainterContext painterContext, SpinControl ctrl) {
		super(painterContext, ctrl);
	}

	/**
	 * @see com.cc.framework.ui.painter.ControlPainter#doCreateElement()
	 */
	protected ConcreteElement doCreateElement() {
		// Force the painter context to create a unique style id
		// for this control. We need this for JavaScript
		getPainterContext().setAttribute(
			PainterContextAtributes.FORCE_STYLEID,
			Boolean.TRUE);

		if (getPainterContext().isDisplayOnly()) {
			// For form type="display" we render a display only form element
			return super.doCreateElement();
		} else {
			TR row = new TR()
				.addElement(new TD(createSpinDownButton()))
				.addElement(new TD(super.doCreateElement()))
				.addElement(new TD(createSpinUpButton()));

			doAddDecorationsToRow(row, 1);

			return new Table()
				.setCellPadding(0)
				.setCellSpacing(1)
				.setBorder(0)
				.addElement(row);
		}
	}

	/**
	 * Creates the spin up button
	 *
	 * @return	Returns a button element
	 */
	protected ConcreteElement createSpinUpButton() {

		if (getCtrl().isDisabled()) {
			return createImage(HtmlResources.BUTTON_SPIN_UP_2);
		} else {
			String ctrlId    = getStyleId();
			String onClick   = getCtrl().getDesignModel().getHandler(ClientEvent.EXT_ONSPINUP);

			Input btn = createInput(HtmlResources.BUTTON_SPIN_UP_1);

			if (onClick == null) {
				// if no spinUp-Handler is specified, use a default handler
				onClick = "return spinUp(document.getElementById('" + ctrlId + "'));";
			}

			if (onClick != null) {
				btn.setOnClick(onClick);
			}

			return btn;
		}
	}

	/**
	 * Creates the spin down button
	 *
	 * @return	Returns a button element
	 */
	protected ConcreteElement createSpinDownButton() {

		if (getCtrl().isDisabled()) {
			return createImage(HtmlResources.BUTTON_SPIN_DOWN_2);
		} else {
			String ctrlId    = getStyleId();
			String onCklick  = getCtrl().getDesignModel().getHandler(ClientEvent.EXT_ONSPINDOWN);

			Input btn = createInput(HtmlResources.BUTTON_SPIN_DOWN_1);

			if (onCklick == null) {
				// if no spinDown-Handler is specified, use a default handler
				onCklick = "return spinDown(document.getElementById('" + ctrlId + "'));";
			}

			if (onCklick != null) {
				btn.setOnClick(onCklick);
			}

			return btn;
		}
	}
}