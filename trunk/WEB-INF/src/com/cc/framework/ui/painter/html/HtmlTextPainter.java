/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/html/HtmlTextPainter.java,v 1.28 2005/09/22 06:21:39 P001002 Exp $
 * $Revision: 1.28 $
 * $Date: 2005/09/22 06:21:39 $
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
import org.apache.ecs.Entities;
import org.apache.ecs.StringElement;
import org.apache.ecs.html.Input;
import org.apache.ecs.html.Span;

import com.cc.framework.adapter.FrameworkAdapterFactory;
import com.cc.framework.http.HttpScope;
import com.cc.framework.ui.control.TextControl;
import com.cc.framework.ui.model.InputFieldType;
import com.cc.framework.ui.painter.PainterContext;
import com.cc.framework.ui.painter.PainterHelp;

/**
 * Painter for the Text Control
 *
 * @author      <a href="mailto:gschulz@scc-gmbh.com">Gernot Schulz</a>
 * @version     $Revision: 1.28 $
 * @since       1.0
 */
public class HtmlTextPainter extends HtmlPainterBase {

	/**
	 * Indicator for a password String
	 */
	private static final String PASSWORD_INDICATOR = "***";

	/**
	 * Constructor
	 *
	 * @param	painterContext	The PainterContext
	 * @param	ctrl			The Control to render
	 */
	public HtmlTextPainter(PainterContext painterContext, TextControl ctrl) {
		super(painterContext);
	}

	/**
	 * Retrieves the control for this painter
	 *
	 * @return		Control
	 */
	protected TextControl getCtrl() {
		return (TextControl) getPainterContext().getControl();
	}

	/**
	 * Creates the element for display only.
	 *
	 * @return		HTML Element
	 */
	protected ConcreteElement doCreateDisplayElement() {
		Span control = new Span();

		// Render a display only form element
		if (InputFieldType.PASSWORD.equals(getCtrl().getInputType())) {
			// Render a password element as three stars
			control.addElement(new StringElement(PASSWORD_INDICATOR));
		} else {
			String value = getValue();

			if (value == null) {
				control.addElement(Entities.NBSP);
			} else {
				control.addElement(new StringElement(html(value, getCtrl().filter())));
			}
		}

		if (getCtrl().getStyle() != null) {
			control.setStyle(getCtrl().getStyle());
		}

		if (getStyleId() != null) {
			control.setID(getStyleId());
		}

		if (getCtrl().getStyleClass() != null) {
			control.setClass(getCtrl().getStyleClass());
		}

		if (getCtrl().getTooltip() != null) {
			control.setTitle(html(localize(getCtrl().getTooltip())));
		}

		return control;
	}

	/**
	 * @see com.cc.framework.ui.painter.ControlPainter#doCreateElement()
	 */
	protected ConcreteElement doCreateElement() {
		if (getPainterContext().isDisplayOnly()) {
			return doCreateDisplayElement();
		} else {
			// forms are always rendered in multiple parts
			Input input = new Input();
			input.setDisabled(getCtrl().isDisabled());
			input.setType(getCtrl().getInputType().toString());

			String controlName = getElementName();
			
			if (controlName != null) {
				input.setName(controlName);
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

			String value = getValue();

			if (value != null) {
				input.setValue(value);
			}

			if (getCtrl().getMaxLength() != -1) {
				input.setMaxlength(getCtrl().getMaxLength());
			}

			if (getCtrl().getSize() != -1) {
				input.setSize(getCtrl().getSize());
			}

			if (getCtrl().getTabIndex() != -1) {
				input.setTabindex(getCtrl().getTabIndex());
			}

			if (getCtrl().getTooltip() != null) {
				input.setTitle(html(localize(getCtrl().getTooltip())));
			}

			PainterHelp.setScriptHandler(input, getCtrl());

			return PainterHelp.appendConverter(getPainterContext(), input,
					controlName, getCtrl().getConverter());
		}
	}

	/**
	 * Retrieves the value for the text control
	 * in form of a String
	 *
	 * @return		value as String
	 */
	protected String getValue() {
		Object value = getCtrl().getValue();

		try {
			if (value == null) {
				value =
					FrameworkAdapterFactory.getAdapter().lookupBean(
						getPageContext(),
						getCtrl().getName(),
						getCtrl().getProperty(),
						HttpScope.ANY);
			}

			// Convert the Controls value to String
			return getAsString(getCtrl().getConverter(), value);
		} catch (Exception e) {
			log.error(e);

			return null;
		}
	}
}