/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/html/HtmlCheckboxPainter.java,v 1.19 2005/05/01 08:56:40 P001002 Exp $
 * $Revision: 1.19 $
 * $Date: 2005/05/01 08:56:40 $
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
import com.cc.framework.ui.control.CheckboxControl;
import com.cc.framework.ui.painter.PainterContext;
import com.cc.framework.ui.painter.PainterHelp;
import com.cc.framework.util.Util;

/**
 * Painter for the Checkbox Control
 *
 * @author      <a href="mailto:gschulz@scc-gmbh.com">Gernot Schulz</a>
 * @version     $Revision: 1.19 $
 * @since       1.0
 */
public class HtmlCheckboxPainter extends HtmlPainterBase {

	/**
	 * Constructor for HtmlCheckboxPainter
	 *
	 * @param	painterContext	The PainterContext
	 * @param	ctrl			The Control to render
	 */
	public HtmlCheckboxPainter(PainterContext painterContext, CheckboxControl ctrl) {
		super(painterContext);
	}

	/**
	 * Retrieves the control for this painter
	 *
	 * @return		Control
	 */
	protected CheckboxControl getCtrl() {
		return (CheckboxControl) getPainterContext().getControl();
	}

	/**
	 * @see com.cc.framework.ui.painter.ControlPainter#doCreateElement()
	 */
	protected ConcreteElement doCreateElement() {
		// Formulare werden immer in mehreren Teilen gezeichnet
		Input input = new Input();
		input.setType(Input.checkbox);
		input.setValue("on");
		input.setDisabled(getCtrl().isDisabled());
		input.setChecked(isChecked());

		if (getElementName() != null) {
			input.setName(getElementName());
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
	 * Retrieves the value for the checkbox control
	 *
	 * @return		value
	 */
	protected boolean isChecked() {
		Boolean value = getCtrl().isChecked();

		if (value == null) {
			try {
				value =
					Util.toBoolean(
						FrameworkAdapterFactory.getAdapter().lookupBean(
							getPageContext(),
							getCtrl().getName(),
							getCtrl().getProperty(),
							HttpScope.ANY));
			} catch (Exception e) {
				// No Action 
			}
		}

		return (value == null) ? false : value.booleanValue();
	}
}