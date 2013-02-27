/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/html/HtmlRadioPainter.java,v 1.23 2005/09/12 09:18:49 P001002 Exp $
 * $Revision: 1.23 $
 * $Date: 2005/09/12 09:18:49 $
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

import com.cc.framework.adapter.FrameworkAdapterFactory;
import com.cc.framework.http.HttpScope;
import com.cc.framework.ui.control.RadioControl;
import com.cc.framework.ui.painter.PainterContext;
import com.cc.framework.ui.painter.PainterHelp;

/**
 * Painter for the Radio Control
 *
 * @author      <a href="mailto:gschulz@scc-gmbh.com">Gernot Schulz</a>
 * @version     $Revision: 1.23 $
 * @since       1.0
 */
public class HtmlRadioPainter extends HtmlPainterBase {

	/**
	 * Constructor for HtmlRadioPainter
	 *
	 * @param	painterContext	The PainterContext
	 * @param	ctrl			The Control to render
	 */
	public HtmlRadioPainter(PainterContext painterContext, RadioControl ctrl) {
		super(painterContext);
	}

	/**
	 * Retrieves the control for this painter
	 *
	 * @return		Control
	 */
	protected RadioControl getCtrl() {
		return (RadioControl) getPainterContext().getControl();
	}

	/**
	 * @see com.cc.framework.ui.painter.ControlPainter#doCreateElement()
	 */
	protected ConcreteElement doCreateElement() {

		Input input = new Input();
		input.setType(Input.radio);
		input.setDisabled(getCtrl().isDisabled());
		input.setChecked(isChecked());

		if (getElementName() != null) {
			input.setName(getElementName());
		}

		if (getCtrl().getValue() != null) {
			input.setValue(getCtrl().getValue());
		}

		if (getCtrl().getStyle() != null) {
			input.setStyle(getCtrl().getStyle());
		}

		if (getStyleId() != null) {
			input.setID(getStyleId());
		}

		if (getCtrl().getStyleClass() != null) {
			input.setClass(getCtrl().getStyleClass());
		}

		if (getCtrl().getTabIndex() != -1) {
			input.setTabindex(getCtrl().getTabIndex());
		}

		if (getCtrl().getTooltip() != null) {
			input.setTitle(html(localize(getCtrl().getTooltip())));
		}

		PainterHelp.setScriptHandler(input, getCtrl());

		if (getPainterContext().isDisplayOnly()) {
			input.setDisabled(true);
		}

		return input;
	}

	/**
	 * Retrieves the value for the radio button
	 *
	 * @return		value
	 */
	protected boolean isChecked() {
		Boolean chk = getCtrl().isChecked();

		if (chk != null) {
			return chk.booleanValue();
		} else {
			try {
				Object value =
						FrameworkAdapterFactory.getAdapter().lookupBean(
							getPageContext(),
							getCtrl().getName(),
							getCtrl().getProperty(),
							HttpScope.ANY);

				if (value != null) {
					String buttonValue = getCtrl().getValue();
//TODO --> Wenn value = Integer --> wird Integer mit String verglichen,
//         was in der Integer Klasse false liefert
					return value.equals(buttonValue);
				}
			} catch (Exception e) {
				// No Action 
			}
		}

		// No Check State available
		return false;
	}
}