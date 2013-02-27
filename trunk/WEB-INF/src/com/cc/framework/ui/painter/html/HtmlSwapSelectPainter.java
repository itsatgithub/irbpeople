/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/html/HtmlSwapSelectPainter.java,v 1.27 2005/08/02 19:15:01 P001002 Exp $
 * $Revision: 1.27 $
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

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import org.apache.ecs.AlignType;
import org.apache.ecs.ConcreteElement;
import org.apache.ecs.ElementContainer;
import org.apache.ecs.Entities;
import org.apache.ecs.html.Input;
import org.apache.ecs.html.Option;
import org.apache.ecs.html.Select;
import org.apache.ecs.html.Span;
import org.apache.ecs.html.TD;
import org.apache.ecs.html.TR;
import org.apache.ecs.html.Table;

import com.cc.framework.adapter.FrameworkAdapterFactory;
import com.cc.framework.http.HttpScope;
import com.cc.framework.ui.OrientationType;
import com.cc.framework.ui.RunAt;
import com.cc.framework.ui.control.ControlActionDef;
import com.cc.framework.ui.control.ControlButton;
import com.cc.framework.ui.control.SwapSelectControl;
import com.cc.framework.ui.javascript.JavaScript;
import com.cc.framework.ui.javascript.JavaScriptUtil;
import com.cc.framework.ui.model.ClientEvent;
import com.cc.framework.ui.painter.ActionPainter;
import com.cc.framework.ui.painter.PainterContext;
import com.cc.framework.ui.painter.PainterContextAtributes;
import com.cc.framework.ui.painter.def.DefHtmlClass;

/**
 * Painter for the Swap Select Control
 *
 * @author     <a href="mailto:gschulz@scc-gmbh.com">Gernot Schulz</a>
 * @version    $Revision: 1.27 $
 * @since       1.0
 */
public class HtmlSwapSelectPainter extends HtmlPainterBase {

	/**
	 * Constructor
	 *
	 * @param	painterContext	The PainterContext
	 * @param	ctrl			The Control to render
	 */
	public HtmlSwapSelectPainter(PainterContext painterContext, SwapSelectControl ctrl) {
		super(painterContext);
	}

	/**
	 * Retrieves the control for this painter
	 *
	 * @return		Control
	 */
	protected SwapSelectControl getCtrl() {
		return (SwapSelectControl) getPainterContext().getControl();
	}

	/**
	 * Checks if the controls orientation is horizontal
	 *
	 * @return		<code>true</code> if the controls orientation is
	 * 				horizontal
	 */
	protected boolean isHorizontal() {
		return OrientationType.HORIZONTAL.equals(getCtrl().getOrientation());
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
	 * This method creates all the hidden fields that are necessary to
	 * synchronize the selected values back to the server
	 *
	 * @param		cmp Options Comparator
	 * @return		Collection of hidden fields
	 */
	protected ElementContainer doCreateHiddenFields(OptionsComparator cmp) {
		ElementContainer fields = new ElementContainer();

		// This field will indicate if ther was any hidden
		// field generated
		boolean noField = true;

		// create a hidden field for every selected option key.
		// The hidden fields get all the same name! This will
		// generate the same HTTP parameters as a <select> would do
		for (OptionsIterator iter = createIterator(); !iter.isDone(); iter.next()) {
			if (cmp.match(iter.getKey())) {

				fields.addElement(new Input()
					.setType(Input.hidden)
					.setName(getElementName())
					.setValue(String.valueOf(iter.getKey())));

				noField = false;
			}
		}

		// Create at least one empty hidden field
		if (noField) {
			fields.addElement(new Input()
				.setType(Input.hidden)
				.setName(getElementName())
				.setValue(String.valueOf("")));
		}

		return fields;
	}

	/**
	 * Creates on single select box
	 *
	 * @param		isRight indicate wether the box with the
	 * 				selected or unselected elements should be created
	 * @param		cmp Options Comparator
	 * @return		returns the HTML select element
	 */
	protected ConcreteElement doCreateSelectBox(
		boolean isRight,
		OptionsComparator cmp) {

		String styleId = "";

		// create the id attribute
		if (isRight) {
			styleId = "swcr_" + getStyleId();
		} else {
			styleId = "swcl_" + getStyleId();
		}

		// create the select html element
		Select select = new Select();
		select.setDisabled(getCtrl().isDisabled());
		select.setMultiple(true);
		select.setID(styleId);

		if (getCtrl().getStyle() != null) {
			select.setStyle("width:100%;" + getCtrl().getStyle());
		} else {
			select.setStyle("width:100%;");
		}


		if (getCtrl().getSize() != -1) {
			select.setSize(getCtrl().getSize());
		}

		if (!isRight && (getCtrl().getTabIndex() != -1)) {
			select.setTabindex(getCtrl().getTabIndex());
		}

		select.setClass(
		isRight
				? DefHtmlClass.SWAPCONTROL_SELECT_R
				: DefHtmlClass.SWAPCONTROL_SELECT_L);

		// create options
		int index = 0;
		for (OptionsIterator iter = createIterator(); !iter.isDone(); iter.next()) {
			++index;

			if (isRight == cmp.match(iter.getKey())) {
				// No selection state will be set for this
				// option -> Comparator == null
				Option option = OptionsPainter.createOption(
					getPainterContext(), iter, null);

				// This additional attribute is used
				// for client side sorting
				option.addAttribute("swc_index", index);

				select.addElement(option);
			}
		}

		return select;
	}

	/**
	 * Selects the image resource to use for the spezified button
	 *
	 * @param		type Buttton Type
	 * @param		enabled Indicates if the button is enabled or
	 * 				disables
	 * @return		Image resource name
	 */
	protected String imgRes(ControlButton type, boolean enabled) {
		if (isHorizontal()) {
			if (type == ControlButton.ADDALL) {
				return enabled ? HtmlResources.BUTTON_SWAP_ADDALL_H1 : HtmlResources.BUTTON_SWAP_ADDALL_H2;
			} else if (type == ControlButton.ADD) {
				return enabled ? HtmlResources.BUTTON_SWAP_ADD_H1 : HtmlResources.BUTTON_SWAP_ADD_H2;
			} else if (type == ControlButton.REMOVEALL) {
				return enabled ? HtmlResources.BUTTON_SWAP_REMOVEALL_H1 : HtmlResources.BUTTON_SWAP_REMOVEALL_H2;
			} else {
				return enabled ? HtmlResources.BUTTON_SWAP_REMOVE_H1 : HtmlResources.BUTTON_SWAP_REMOVE_H2;
			}
		} else {
			if (type == ControlButton.ADDALL) {
				return enabled ? HtmlResources.BUTTON_SWAP_ADDALL_V1 : HtmlResources.BUTTON_SWAP_ADDALL_V2;
			} else if (type == ControlButton.ADD) {
				return enabled ? HtmlResources.BUTTON_SWAP_ADD_V1 : HtmlResources.BUTTON_SWAP_ADD_V2;
			} else if (type == ControlButton.REMOVEALL) {
				return enabled ? HtmlResources.BUTTON_SWAP_REMOVEALL_V1 : HtmlResources.BUTTON_SWAP_REMOVEALL_V2;
			} else {
				return enabled ? HtmlResources.BUTTON_SWAP_REMOVE_V1 : HtmlResources.BUTTON_SWAP_REMOVE_V2;
			}
		}
	}

	/**
	 * Creates the add all Button
	 *
	 * @param	enabled true if the Button is enabled
	 * @return	ConcreteElement
	 */
	protected ConcreteElement doCreateAddAllButton(boolean enabled) {
		if (enabled) {
			// get the tooltip
			String tooltip = getFrameworkString(HtmlResources.FW_SWAPSELECT_ADD_ALL);

			// create button
			ActionPainter ap = createActionPainter(ControlActionDef.ACTION_PAGE);

			ap.setImage(getImage(imgRes(ControlButton.ADDALL, true)));
			ap.setTooltip(tooltip);
			ap.setStyleId("btnswc_" + getStyleId() + "_AddAll");

			return ap.createElement();
		} else {
			return createImage(imgRes(ControlButton.ADDALL, false));
		}
	}

	/**
	 * Creates the add Button
	 *
	 * @param	enabled true if the Button is enabled
	 * @return	ConcreteElement
	 */
	protected ConcreteElement doCreateAddButton(boolean enabled) {
		if (enabled) {
			// get the tooltip
			String tooltip = getFrameworkString(HtmlResources.FW_SWAPSELECT_ADD);

			// create button
			ActionPainter ap = createActionPainter(ControlActionDef.ACTION_PAGE);

			ap.setImage(getImage(imgRes(ControlButton.ADD, true)));
			ap.setTooltip(tooltip);
			ap.setStyleId("btnswc_" + getStyleId() + "_Add");

			return ap.createElement();
		} else {
			return createImage(imgRes(ControlButton.ADD, false));
		}
	}

	/**
	 * Creates the remove Button
	 *
	 * @param	enabled true if the Button is enabled
	 * @return	ConcreteElement
	 */
	protected ConcreteElement doCreateRemoveButton(boolean enabled) {
		if (enabled) {
			// get the tooltip
			String tooltip = getFrameworkString(HtmlResources.FW_SWAPSELECT_REMOVE);

			// create button
			ActionPainter ap = createActionPainter(ControlActionDef.ACTION_PAGE);

			ap.setImage(getImage(imgRes(ControlButton.REMOVE, true)));
			ap.setTooltip(tooltip);
			ap.setStyleId("btnswc_" + getStyleId() + "_Remove");

			return ap.createElement();
		} else {
			return createImage(imgRes(ControlButton.REMOVE, false));
		}
	}

	/**
	 * Creates the remove all Button
	 *
	 * @param	enabled true if the Button is enabled
	 * @return	ConcreteElement
	 */
	protected ConcreteElement doCreateRemoveAllButton(boolean enabled) {
		if (enabled) {
			// get the tooltip
			String tooltip = getFrameworkString(HtmlResources.FW_SWAPSELECT_REMOVE_ALL);

			// create button
			ActionPainter ap = createActionPainter(ControlActionDef.ACTION_PAGE);

			ap.setImage(getImage(imgRes(ControlButton.REMOVEALL, true)));
			ap.setTooltip(tooltip);
			ap.setStyleId("btnswc_" + getStyleId() + "_RemoveAll");

			return ap.createElement();
		} else {
			return createImage(imgRes(ControlButton.REMOVEALL, false));
		}
	}

	/**
	 * Creates the buttons to move the option elements from
	 * one window to the other
	 *
	 * @param		cmp Options Comparator
	 * @return		collection of ConcreteElement Items
	 */
	protected Collection doCreateButtons(OptionsComparator cmp) {
		Vector buttons = new Vector();

		boolean enabled			= !getCtrl().isDisabled();
		boolean enableRemove	= enabled && true;	// TODO check selection state
		boolean enableAdd		= enabled && true;

		if (RunAt.SERVER.equals(getCtrl().getRunAt())) {

			// Count selected and unselected elements
			int sel		= 0;
			int unsel	= 0;

			for (OptionsIterator iter = createIterator(); !iter.isDone(); iter.next()) {
				if (cmp.match(iter.getKey())) {
					++sel;
				} else {
					++unsel;
				}
			}

			enableRemove = (sel > 0);
			enableAdd    = (unsel > 0);
		}

		// add all button
		if (getCtrl().showButton(ControlButton.ADDALL)) {
			buttons.add(doCreateAddAllButton(enableAdd));
		}

		// add button
		if (getCtrl().showButton(ControlButton.ADD)) {
			buttons.add(doCreateAddButton(enableAdd));
		}

		// remove button
		if (getCtrl().showButton(ControlButton.REMOVE)) {
			buttons.add(doCreateRemoveButton(enableRemove));
		}

		// remove all button
		if (getCtrl().showButton(ControlButton.REMOVEALL)) {
			buttons.add(doCreateRemoveAllButton(enableRemove));
		}

		return buttons;
	}

	/**
	 * Paints the control in horizontal orientation
	 *
	 * @param		control The resulting table element
	 * @param		cmp Options Comparator
	 */
	protected void doPaintHorizontal(Table control, OptionsComparator cmp) {

		TR labelRow = null;

		// Label
		if (getCtrl().showLabels()) {
			labelRow = new TR()
				.addElement(new TD(html(localize(getCtrl().getLabelLeft()), getCtrl().filter()))
					.setClass(DefHtmlClass.SWAPCONTROL_LABEL_L))
				.addElement(new TD(Entities.NBSP))
				.addElement(new TD(html(localize(getCtrl().getLabelRight()), getCtrl().filter()))
					.setClass(DefHtmlClass.SWAPCONTROL_LABEL_R));

			control.addElement(labelRow);
		}

		// Button Table
		Table bt = new Table()
			.setBorder(0)
			.setCellPadding(0)
			.setCellSpacing(0);

		Iterator buttonIter = doCreateButtons(cmp).iterator();
		while (buttonIter.hasNext()) {
			ConcreteElement button = (ConcreteElement) buttonIter.next();

			bt.addElement(new TR().addElement(new TD(button).setAlign(AlignType.center)));
		}

		// Body
		TR bodyRow = new TR()
			.setVAlign(AlignType.top)
			.addElement(new TD(doCreateSelectBox(false, cmp)).setWidth("50%"))
			.addElement(new TD()
				.setAlign(AlignType.center)
				.setVAlign(AlignType.middle)
				.setNoWrap(true)
				.addElement(bt)
				.setClass(DefHtmlClass.SWAPCONTROL_BUTTON_H))
			.addElement(new TD(doCreateSelectBox(true, cmp)).setWidth("50%"));

		control.addElement(bodyRow);

		// Add decorations
		if (labelRow != null) {
			doAddDecorationsToRow(labelRow, 2);
		} else {
			// There is no label row. So we have to add the
			// decorations to the body row
			doAddDecorationsToRow(bodyRow, 1);
		}
	}

	/**
	 * Paints the control in vertical orientation
	 *
	 * @param		control The resulting table element
	 * @param		cmp Options Comparator
	 */
	protected void doPaintVertical(Table control, OptionsComparator cmp) {

		TR labelTopRow = null;

		// Top Label
		if (getCtrl().getLabelLeft() != null) {
			labelTopRow = new TR()
				.setVAlign(AlignType.top)
				.addElement(new TD(html(localize(getCtrl().getLabelLeft()), getCtrl().filter()))
					.setClass(DefHtmlClass.SWAPCONTROL_LABEL_L));

			control.addElement(labelTopRow);
		}

		TR selectTopRow = new TR()
			.setVAlign(AlignType.top)
			.addElement(new TD(doCreateSelectBox(false, cmp)).setWidth("100%"));
		control.addElement(selectTopRow);

		// Button row
		TR buttonRow = new TR();

		Iterator buttonIter = doCreateButtons(cmp).iterator();
		while (buttonIter.hasNext()) {
			ConcreteElement button = (ConcreteElement) buttonIter.next();

			buttonRow.addElement(new TD(button));
		}

		control.addElement(new TR()
			.addElement(new TD()
				.setAlign(AlignType.center)
				.setVAlign(AlignType.middle)
				.addElement(new Table()
					.setBorder(0)
					.setCellPadding(0)
					.setCellSpacing(0)
					.addElement(buttonRow))
				.setClass(DefHtmlClass.SWAPCONTROL_BUTTON_V)));

		// Bottom Label
		TR labelBottomRow = null;

		if (getCtrl().getLabelRight() != null) {
			labelBottomRow = new TR()
				.setVAlign(AlignType.top)
				.addElement(new TD(html(localize(getCtrl().getLabelRight()), getCtrl().filter()))
					.setClass(DefHtmlClass.SWAPCONTROL_LABEL_R));

			control.addElement(labelBottomRow);
		}

		control.addElement(new TR()
			.setVAlign(AlignType.top)
			.addElement(new TD(doCreateSelectBox(true, cmp)).setWidth("100%")));

		// Add decorations
		if (labelTopRow != null) {
			// Rows: topLabel + topSelectBox + Buttons + bottomLabel? + bottomSelectBox
			int rows = 4 + ((labelBottomRow == null) ? 0 : 1);

			doAddDecorationsToRow(labelTopRow, rows);
		} else {
			// Rows: topSelectBox + Buttons + bottomLabel? + bottomSelectBox
			int rows = 3 + ((labelBottomRow == null) ? 0 : 1);

			doAddDecorationsToRow(selectTopRow, rows);
		}

	}

	/**
	 * Creates the element for display only. It shows the
	 * selected values
	 *
	 * @return		HTML Element
	 */
	protected ConcreteElement doCreateDisplayElement() {
		// Render a display only form element
		Span control = new Span();

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

		control.setID(getStyleId());

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
		// Force the painter context to create a unique style id
		// for this control. We need this for JavaScript
		getPainterContext().setAttribute(
			PainterContextAtributes.FORCE_STYLEID,
			Boolean.TRUE);

		// The options iterator to use
		if (createIterator() == null) {
			// Can't paint element without option list
			return null;
		}

		if (getPainterContext().isDisplayOnly()) {
			return doCreateDisplayElement();
		} else {
			// render the control
			Table control = new Table();
			control.setCellPadding(0);
			control.setCellSpacing(0);
			control.setBorder(0);

//			if (getCtrl().getStyle() != null) {
//				control.setStyle(getCtrl().getStyle());
//			}

			control.setID(getStyleId());

			// set general style class
			if (getCtrl().getStyleClass() == null) {
				control.setClass(DefHtmlClass.SWAPCONTROL);
			} else {
				control.setClass(getCtrl().getStyleClass());
			}

			if (getCtrl().getWidth() != null) {
				control.setWidth(getCtrl().getWidth());
			}

			if (getCtrl().getTooltip() != null) {
				control.setTitle(html(localize(getCtrl().getTooltip())));
			}

			if (getCtrl().getSummary() != null) {
				control.setSummary(getCtrl().getSummary());
			}

			// The options comparator to use
			OptionsComparator comparator = new LiteralOptionsComparator(getValues());

			if (isHorizontal()) {
				doPaintHorizontal(control, comparator);
			} else {
				doPaintVertical(control, comparator);
			}

			// embed the control into an html span element
			Span span = new Span();
			span.addElement(control);
			span.setID("swc_span_" + getStyleId());

			// optional script
			if (getCtrl().getRunAt().equals(RunAt.CLIENT)) {
				ConcreteElement script = doCreateScript(comparator);
				if (script != null) {
					span.addElement(script);
				}
			}

			// Create additional hidden fields
			span.addElement(doCreateHiddenFields(comparator));

			return span;
		}
	}

	/**
	 * Creates the Java Script Code wihich is needed by
	 * the control
	 *
	 * @param		cmp Options Comparator
	 * @return		Java Script Code
	 */
	protected JavaScript doCreateScript(OptionsComparator cmp) {

		String controlId = getStyleId();

		StringBuffer script = new StringBuffer()

			.append("var swc_")
			.append(controlId)
			.append(" = new SwapSelect('")
			.append(controlId)
			.append("', ")
			.append("RunAt.")
			.append(RunAt.CLIENT.equals(getCtrl().getRunAt()) ? "CLIENT" : "SERVER")
			.append(", '")
			.append(getElementName())
			.append("', ")
			.append(getCtrl().isDisabled())
			.append(");")

			.append("swc_")
			.append(controlId)
			.append(".setOrientation(Orientation.")
			.append(OrientationType.HORIZONTAL.equals(getCtrl().getOrientation()) ? "HORIZONTAL" : "VERTICAL")
			.append(");")

			.append("swc_")
			.append(controlId)
			.append(".setKeepSortOrder(")
			.append(true)
			.append(");")

			.append("swc_")
			.append(controlId)
			.append(".setKeepSelection(")
			.append(true)
			.append(");");


			// append the client handler
			if (null != getCtrl().getDesignModel().getHandler(ClientEvent.ONCHANGE)) {

				script
					.append("swc_")
					.append(controlId)
					.append(".addClientHandler(")
					.append("ClientHandler.ONCHANGE")
					.append(", '")
					.append(JavaScriptUtil.encodeJavaScript(getCtrl().getDesignModel().getHandler(ClientEvent.ONCHANGE)))
					.append("');");
			}

		return new JavaScript(script.toString());
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