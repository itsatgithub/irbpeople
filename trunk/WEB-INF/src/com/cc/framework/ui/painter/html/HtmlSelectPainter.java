/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/html/HtmlSelectPainter.java,v 1.34 2005/08/02 19:15:01 P001002 Exp $
 * $Revision: 1.34 $
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
import org.apache.ecs.html.Div;
import org.apache.ecs.html.Select;

import com.cc.framework.adapter.FrameworkAdapterFactory;
import com.cc.framework.http.HttpScope;
import com.cc.framework.ui.control.SelectControl;
import com.cc.framework.ui.painter.PainterContext;
import com.cc.framework.ui.painter.PainterHelp;
import com.cc.framework.util.StringHelp;

/**
 * Painter for the Select Control
 *
 * @author     <a href="mailto:gschulz@scc-gmbh.com">Gernot Schulz</a>
 * @version    $Revision: 1.34 $
 * @since       1.0
 */
public class HtmlSelectPainter extends HtmlPainterBase {

	/**
	 * Constructor for HtmlSelectPainter
	 *
	 * @param	painterContext	The PainterContext
	 * @param	ctrl			The Control to render
	 */
	public HtmlSelectPainter(PainterContext painterContext, SelectControl ctrl) {
		super(painterContext);
	}

	/**
	 * Retrieves the control for this painter
	 *
	 * @return		Control
	 */
	protected SelectControl getCtrl() {
		return (SelectControl) getPainterContext().getControl();
	}

	/**
	 * Creates an option iterator
	 *
	 * @return		Iterator
	 */
	protected OptionsIterator createIterator() {
		return OptionsHelp.createIterator(
			getCtrl().getOptions(),
			getCtrl().getOptionElements());
	}

	/**
	 * Creates the element for display only. It shows the
	 * selected values
	 *
	 * @return		HTML Element
	 */
	protected ConcreteElement doCreateDisplayElement() {
		// Show the control in display only mode
		Div control = new Div();

		if (getCtrl().getOptions() == null) {
			control.addElement(
				OptionsHelp.createDisplayElement(getValues()));
		} else {
			control.addElement(
				OptionsHelp.createDisplayElement(
					getPainterContext(),
					createIterator(),
					new LiteralOptionsComparator(getValues())));
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
			Select control = new Select();
			control.setDisabled(getCtrl().isDisabled());

			if (getElementName() != null) {
				control.setName(getElementName());
			}

			if (getCtrl().getSize() != -1) {
				control.setSize(getCtrl().getSize());
			}

			if (getCtrl().isMultiple()) {
				control.setMultiple(getCtrl().isMultiple());
			}

			// Set the controls width and style
			if  (getCtrl().getWidth() != null) {
				if (getCtrl().getStyle() == null) {
					control.setStyle(StringHelp.strcat("width: ", getCtrl().getWidth(), ";"));
				} else {
					control.setStyle(StringHelp.strcat("width: ", getCtrl().getWidth(), ";", getCtrl().getStyle()));
				}
			} else if (getCtrl().getStyle() != null) {
				control.setStyle(getCtrl().getStyle());
			}

			if (getStyleId() != null) {
				control.setID(getStyleId());
			}

			if (getCtrl().getStyleClass() != null) {
				control.setClass(getCtrl().getStyleClass());
			}

			if (getCtrl().getTabIndex() != -1) {
				control.setTabindex(getCtrl().getTabIndex());
			}

			if (getCtrl().getTooltip() != null) {
				control.setTitle(html(localize(getCtrl().getTooltip())));
			}

			PainterHelp.setScriptHandler(control, getCtrl());

			// render the option list
			if (getCtrl().getOptions() != null) {
				OptionsPainter op =
					new OptionsPainter(
						getCtrl().getOptions(),
						getCtrl().getOptionElements(),
						new LiteralOptionsComparator(getValues()));

				control.addElement(op.paint(getPainterContext()));
			}

			// insert an optional raw body
			if (getCtrl().getBody() != null) {
				control.addElement(getCtrl().getBody());
			}

			return control;
		}
	}

	/**
	 * Retrieves the value for the text control
	 *
	 * @return		value
	 */
	protected Object[] getValues() {
		Object[] values = getCtrl().getValues();

		if (values == null) {
			try {
				Object value =
					FrameworkAdapterFactory.getAdapter().lookupBean(
						getPageContext(),
						getCtrl().getName(),
						getCtrl().getProperty(),
						HttpScope.ANY);

				values = OptionsHelp.toArray(value);
			} catch (Exception e) {
				// No action
			}
		}

		return values;
	}
}