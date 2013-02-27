/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/html/HtmlTextareaPainter.java,v 1.35 2005/11/13 12:51:32 P001001 Exp $
 * $Revision: 1.35 $
 * $Date: 2005/11/13 12:51:32 $
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

import java.util.Collection;

import org.apache.ecs.AlignType;
import org.apache.ecs.ConcreteElement;
import org.apache.ecs.ElementContainer;
import org.apache.ecs.html.TD;
import org.apache.ecs.html.TR;
import org.apache.ecs.html.Table;
import org.apache.ecs.html.TextArea;

import com.cc.framework.adapter.FrameworkAdapterFactory;
import com.cc.framework.http.HttpScope;
import com.cc.framework.ui.control.TextareaControl;
import com.cc.framework.ui.html.HtmlUtil;
import com.cc.framework.ui.javascript.JavaScript;
import com.cc.framework.ui.painter.PainterContext;
import com.cc.framework.ui.painter.PainterContextAtributes;
import com.cc.framework.ui.painter.PainterHelp;
import com.cc.framework.util.StringHelp;

/**
 * Painter for the TextArea Control
 *
 * @author      <a href="mailto:gschulz@scc-gmbh.com">Gernot Schulz</a>
 * @version     $Revision: 1.35 $
 * @since       1.0
 */
public class HtmlTextareaPainter extends HtmlPainterBase {

	/**
	 * Constructor for HtmlTextareaPainter
	 *
	 * @param	painterContext	The PainterContext
	 * @param	ctrl			The Control to render
	 */
	public HtmlTextareaPainter(PainterContext painterContext, TextareaControl ctrl) {
		super(painterContext);
	}

	/**
	 * Retrieves the control for this painter
	 *
	 * @return		Control
	 */
	protected TextareaControl getCtrl() {
		return (TextareaControl) getPainterContext().getControl();
	}

	/**
	 * Creates the textarea element
	 *
	 * @return		HTML Element
	 */
	protected ConcreteElement doCreateTextArea() {
		// Force the painter context to create a unique style id
		// for this control. We need this for JavaScript
		getPainterContext().setAttribute(
			PainterContextAtributes.FORCE_STYLEID,
			Boolean.TRUE);

		TextArea area = new TextArea();
		area.setDisabled(getCtrl().isDisabled());

		if (getElementName() != null) {
			area.setName(getElementName());
		}

		if (getCtrl().getRows() != -1) {
			area.setRows(getCtrl().getRows());
		}

		if (getCtrl().getColumns() != -1) {
			area.setCols(getCtrl().getColumns());
		}

		if (getCtrl().getWrap() != null) {
			area.setWrap(getCtrl().getWrap());
		}

		if (getValue() != null) {
			area.addElement(HtmlUtil.encodeTextarea(getValue()));
		}

		if (getCtrl().getStyle() != null) {
			area.setStyle(getCtrl().getStyle());
		}

		if (getStyleId() != null) {
			area.setID(getStyleId());
		}

		if (getCtrl().getStyleClass() != null) {
			area.setClass(getCtrl().getStyleClass());
		}

		if (getCtrl().isReadonly()) {
			area.setReadOnly(true);
		}

		if (getCtrl().getTabIndex() != -1) {
			area.setTabindex(getCtrl().getTabIndex());
		}

		if (getCtrl().getTooltip() != null) {
			area.setTitle(html(localize(getCtrl().getTooltip())));
		}

		if (getPainterContext().isDisplayOnly()) {
			// if the form type is set to display we only generate a disabled textarea
			area.setReadOnly(true);
			return area;

		} else if (getCtrl().isDisabled()) {
			// it the control was disabled we only generate a disabled textarea
			area.setReadOnly(true);
			return area;
		} else {
			// add the javascript handler
			PainterHelp.setScriptHandler(area, getCtrl());
		}

		return area;
	}

	/**
	 * Encapsulates the given HTML Element in an length controlling
	 * container
	 *
	 * @param		nested The nested element
	 * @return		HTML Element
	 */
	protected ConcreteElement doCreateLengthControledElement(ConcreteElement nested) {
		ElementContainer container = new ElementContainer();

		String messageline = getFrameworkString(HtmlResources.FW_TEXTAREA_MAXLENGTH_MESSAGE);

		StringBuffer script = new StringBuffer()
			.append("var ta_")
			.append(getStyleId())
			.append(" = new Textarea('")
			.append(getStyleId())
			.append("',")
			.append(getCtrl().getMaxLength())
			.append(",'")
			.append(messageline)
			.append("',false,")
			.append(getCtrl().isDisabled())
			.append(",")
			.append(getCtrl().isReadonly())
			.append(");");

		container.addElement(nested);
		container.addElement(new JavaScript(script.toString()));

		// return texarea + script
		return container;
	}

	/**
	 * @see com.cc.framework.ui.painter.ControlPainter#doCreateElement()
	 */
	protected ConcreteElement doCreateElement() {

		ConcreteElement textarea = doCreateTextArea();

		if (getCtrl().isDisabled() || getPainterContext().isDisplayOnly()) {
			return textarea;
		} else {
			// check if the maxlength attribute was set
			// in this case an additional script must be
			// generated, which checks the specified limit
			// for the textarea
			if (getCtrl().getMaxLength() != 0) {
				return doCreateLengthControledElement(textarea);
			} else {
				return textarea;
			}
		}
	}

	/**
	 * @see com.cc.framework.ui.painter.ControlPainter#doAttachDecorations(org.apache.ecs.ConcreteElement)
	 */
	protected ConcreteElement doAttachDecorations(ConcreteElement control) {
		if (getCtrl().getMaxLength() == 0) {
			return super.doAttachDecorations(control);
		} else {
			Collection decorations = getDecorationsOnce();

			if ((decorations == null) || decorations.isEmpty()) {
				return control;
			} else {
				Table table = new Table();
				table.setCellPadding(0);
				table.setCellSpacing(0);
				table.setBorder(0);

				TR tr = new TR();
				tr.setVAlign(AlignType.middle);
				table.addElement(tr);

				// Add the Input Element
				TD td = new TD();
				td.setAlign(AlignType.left);
				td.addElement(control);

				if (getCtrl().getWidth() != null) {

					td.setWidth(getCtrl().getWidth());

					if (getCtrl().getStyle() == null) {
						control.setStyle("width: 100%;");
					} else {
						control.setStyle(StringHelp.strcat("width: 100%;", getCtrl().getStyle()));
					}

				} else if (getCtrl().getStyle() != null) {
					control.setStyle(getCtrl().getStyle());
				}

				tr.addElement(td);

				doAddDecorationsToRow(tr, 1);

				return table;
			}
		}
	}

	/**
	 * Retrieves the value for the textarea control
	 *
	 * @return		value
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