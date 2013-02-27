/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/def/DefFormPainter.java,v 1.79 2005/09/12 10:33:37 P001002 Exp $
 * $Revision: 1.79 $
 * $Date: 2005/09/12 10:33:37 $
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

import java.util.Collection;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.Vector;

import org.apache.ecs.AlignType;
import org.apache.ecs.ConcreteElement;
import org.apache.ecs.ElementContainer;
import org.apache.ecs.Entities;
import org.apache.ecs.StringElement;
import org.apache.ecs.html.IMG;
import org.apache.ecs.html.Input;
import org.apache.ecs.html.Label;
import org.apache.ecs.html.Span;
import org.apache.ecs.html.TD;
import org.apache.ecs.html.TR;
import org.apache.ecs.html.Table;

import com.cc.framework.adapter.FrameworkAdapterFactory;
import com.cc.framework.common.Severity;
import com.cc.framework.ui.AlignmentType;
import com.cc.framework.ui.FormType;
import com.cc.framework.ui.ImageMap;
import com.cc.framework.ui.OrientationType;
import com.cc.framework.ui.control.ButtonControl;
import com.cc.framework.ui.control.CheckboxControl;
import com.cc.framework.ui.control.FormButtonContainer;
import com.cc.framework.ui.control.FormButtonElement;
import com.cc.framework.ui.control.FormControl;
import com.cc.framework.ui.control.FormControlElement;
import com.cc.framework.ui.control.FormElement;
import com.cc.framework.ui.control.FormElementContainer;
import com.cc.framework.ui.control.FormGroupElement;
import com.cc.framework.ui.control.FormHtmlElement;
import com.cc.framework.ui.control.FormMessageElement;
import com.cc.framework.ui.control.LabeledFormElement;
import com.cc.framework.ui.control.RadioControl;
import com.cc.framework.ui.model.FormLabelDesignModel;
import com.cc.framework.ui.model.FrameTitle;
import com.cc.framework.ui.model.ImageModel;
import com.cc.framework.ui.model.InnerFrame;
import com.cc.framework.ui.model.imp.FormLabelDesignModelImp;
import com.cc.framework.ui.painter.ControlPainter;
import com.cc.framework.ui.painter.Frame;
import com.cc.framework.ui.painter.PainterContext;
import com.cc.framework.ui.painter.PainterContextAtributes;
import com.cc.framework.ui.painter.PainterFactory;
import com.cc.framework.ui.painter.PainterHelp;

/**
 * Base class for form painters
 *
 * @author	    <a href="mailto:hschulz@scc-gmbh.com">Gernot Schulz</a>
 * @version	    $Revision: 1.79 $
 * @since       1.0
 */
public abstract class DefFormPainter extends DefPainterBase {

	/** Custom HTML attribute to identify row types */
	protected static final String ROWTYPE				= "rowtype";

	/** Row Type: Description */
	protected static final String ROWTYPE_DESCRIPTION	= "description";

	/** Row Type: Separator */
	protected static final String ROWTYPE_SEPARATOR		= "separator";

	/** Row Type: Container */
	protected static final String ROWTYPE_CONTAINER		= "container";

	/** Row Type: Button Container */
	protected static final String ROWTYPE_BUTTONS		= "buttons";

	/** Row Type: Control */
	protected static final String ROWTYPE_CONTROL		= "control";

	/** Row Type: Inner Frame */
	protected static final String ROWTYPE_INNERFRAME	= "innerframe";

	/** Empty Label */
	protected static final FormLabelDesignModel LABEL_EMPTY =
		new FormLabelDesignModelImp("", null);

	/** Message Label */
	protected static final FormLabelDesignModel LABEL_MESSAGE =
		new FormLabelDesignModelImp(null, DefResources.IMAGE_HAND);

	/**
	 * Constructor for DefFormPainter
	 *
	 * @param	painterContext	The PainterContext
	 * @param	ctrl			The Control to render
	 */
	public DefFormPainter(PainterContext painterContext, FormControl ctrl) {
		super(painterContext);

		// First the id of the form is put into the painter context
		// So enclosed controls can also use the id of the form
		painterContext.setAttribute(PainterContextAtributes.FORMID, ctrl.getFormId());

		if (!ctrl.getFormType().isEditable()) {
			painterContext.setAttribute(PainterContextAtributes.DISPLAY, Boolean.TRUE);
		}
	}

	/**
	 * Returns the from control
	 *
	 * @return		form control
	 */
	public FormControl getForm() {
		return (FormControl) getPainterContext().getControl();
	}

	/**
	 * Returns the type of the form
	 *
	 * @return	FormType
	 */
	public FormType getFormType() {
		return getForm().getFormType();
	}

	/**
	 * Checks if the frame should be painted
	 *
	 * @return		<code>true</code> if the frame should
	 * 				be painted
	 */
	protected boolean showFrame() {
		return getForm().showFrame();
	}

	// ======================================
	// object painter
	// ======================================

	/**
	 * @see com.cc.framework.ui.painter.ControlPainter#getElementClass(int)
	 */
	public String getElementClass(int type) {

		switch (type) {
			case DefClassType.CLASS_CONTROL :
				if (showFrame()) {
					return DefHtmlClass.FORMCONTROL;
				} else {
					return DefHtmlClass.FORMCONTROL_NO_FRAME;
				}

			case DefClassType.CLASS_BODY :
				return "fcb";

			case DefClassType.CLASS_BODY_LEFT:
				return "fls";

			case DefClassType.CLASS_BODY_RIGHT:
				return "frs";

			case DefClassType.CLASS_HEADER :
				return "fch";

			case DefClassType.CLASS_FOOTER :
				return "fcf";

			case DefClassType.CLASS_SECTION:
				return "section";

			case DefClassType.CLASS_INDICATOR:
				return "fe";

			case DefClassType.CLASS_LABEL:
				return "fl";

			case DefClassType.CLASS_DATA:
				return "fd";

			case DefClassType.CLASS_BUTTONS:
				return "fb";

			case DefClassType.CLASS_HIDENBUTTON :
				return "hb";

			default :
				return super.getElementClass(type);
		}
	}

	/**
	 * Returns the required number of TD-Elements for one
	 * form element
	 *
	 * @return		number of cells
	 */
	protected int getCellsPerElement() {
		return getFormType().isEditable() ? 3 : 2;
	}

	/**
	 * Returns the referenced Image
	 *
	 * @param	imageref the image reference
	 * @return 	ImageModel or <code>null</code>
	 */
	protected ImageModel getFormImage(String imageref) {

		if (imageref == null) {
			return null;
		}

		ImageMap imageMap = getForm().getImageMap();

		if (imageMap == null) {
			return null;
		}

		return imageMap.mapValueToImage(imageref);
	}

	/**
	 * This method creates a row container
	 *
	 * @param		rowContent TD Element(s) with the rows content
	 * @param		nestingLevel The nesting Level of the row in the form
	 * 				element tree
	 * @param		rowType an additional attribute for this row. This enables
	 *				a JavaScript client to identify the row
	 * @return		ConcreteElement
	 */
	protected ConcreteElement doCreateRow(
		ConcreteElement rowContent,
		int nestingLevel,
		String rowType) {

		TR row = new TR();

		if ((nestingLevel == 0) && showFrame()) {
			row.addElement(new TD(createImage(DefResources.IMAGE_SPACER))
				.setClass(getElementClass(DefClassType.CLASS_BODY_LEFT)));
		}

		row.addElement(rowContent);

		if ((nestingLevel == 0) && showFrame()) {
			row.addElement(new TD(createImage(DefResources.IMAGE_SPACER))
				.setClass(getElementClass(DefClassType.CLASS_BODY_RIGHT)));
		}

		// Add an additional attribute to this row. This enables
		// a JavaScript client to identify the row
		if (rowType != null) {
			row.addAttribute(ROWTYPE, rowType);
		}

		return row;
	}

	/**
	 * This method creates a row container
	 *
	 * @param		container The form element for this new row
	 * @param		rowContent TD Element(s) with the rows content
	 * @param		nestingLevel The nesting Level of the row in the form
	 * 				element tree
	 * @param		rowType an additional attribute for this row. This enables
	 *				a JavaScript client to identify the row
	 * @return		ConcreteElement
	 */
	protected ConcreteElement doCreateRow(
		FormElementContainer container,
		ConcreteElement rowContent,
		int nestingLevel,
		String rowType) {

		ConcreteElement row = doCreateRow(rowContent, nestingLevel, rowType);

		if (container.getStyle() != null) {
			row.setStyle(container.getStyle());
		}

		if (container.getStyleId() != null) {
			row.setID(container.getStyleId());
		}

		if (container.getStyleClass() != null) {
			row.setClass(container.getStyleClass());
		}

		return row;
	}

	/**
	 * Retrieves the locale to use for a given label
	 *
	 * @param		label the label
	 * @return		Locale
	 */
	protected Locale getLocale(FormLabelDesignModel label) {
		if (label.getLocaleName() == null) {
			return getPainterContext().getLocale();
		} else {
			String localeName = label.getLocaleName();

			return PainterHelp.localeFromName(getPageContext(), localeName);
		}
	}

	/**
	 * Paints the Label of a form element
	 *
	 * @param		labelCell The cell where to put the label
	 * @param		label The Design Model of the label
	 * @param		linkId The id of the control to link this
	 * 				label to
	 */
	protected void paintLabel(
		TD labelCell,
		FormLabelDesignModel label,
		String linkId) {

		Locale locale	= getLocale(label);
		String text		= null;

		if (label.getText() != null) {
			if (label.isBodyInclude()) {
				// if the body is specified within the jsp page we do not localize it!
				text = label.getText();
			} else {
				// if the label attribute is used, we localized it!
				text = localize(label.getText(), locale);

				if ((text != null) && !text.equals("")) {
				 	text += ":";
				}
			}
		}

		ImageModel image = null;

		if (label == LABEL_MESSAGE) {
			image = getImage(label.getImageRef());
		} else {
			image = getFormImage(label.getImageRef());
		}

		Label labelElement = new Label();

		if (image != null) {
			IMG img = createImage(image).setVspace(0).setAlign(AlignType.absmiddle);

			if (text != null) {
				img.setStyle("margin-right:3px;");
			}

			labelElement.addElement(img);
		}

		// Avoid writing a null-Label when not necessary
		if ((text != null) || (image == null)) {

			labelElement.addElement(html(text, label.filter(), label.getMaxLength()));

			if (label.getTooltip() != null) {
				labelElement.setTitle(html(localize(label.getTooltip(), locale), label.filter()));
			}

			if (label.getAccessKey() != null) {
				labelElement.setAccessKey(label.getAccessKey());

				// Link the Label to the COntrol
				if (linkId != null) {
					labelElement.setFor(linkId);
				}
			}
		}

		labelCell.addElement(labelElement);

		if (label.getStyleId() != null) {
			labelCell.setID(label.getStyleId());
		}

		if (label.getStyle() != null) {
			labelCell.setStyle(label.getStyle());
		}

		if (label.getAlignment() != null) {
			labelCell.setAlign(label.getAlignment().toString());
		}

		if (label.getWidth() != null) {
			labelCell.setWidth(label.getWidth());
		}

		if (label.getNowrap() != null) {
			labelCell.setNoWrap(label.getNowrap().booleanValue());
		}

		// add the Script Handlers
		PainterHelp.setScriptHandler(labelCell, label);
	}

	/**
	 * Retrieves the Control Style id of the control element
	 *
	 * @param		formElement The form element
	 * @return		Style Id or <code>null</code>
	 */
	protected String getLinkId(FormElement formElement) {
		if (formElement instanceof FormControlElement) {
			return getPainterContext().getForcedStyleId(
				((FormControlElement) formElement).getControl());
		} else {
			return formElement.getStyleId();
		}
	}

	/**
	 * paints the message indicator
	 *
	 * @param		msgCell The Zell where to put the indicator
	 * @param		formElement The formelement
	 */
	protected void paintMessageIndicatior(
		TD msgCell,
		FormElement formElement) {

		if (hasErrorPending(formElement)) {
			msgCell.addElement(createImage(DefResources.IMAGE_ERROR_INPUT)
				.setID("errInput"));
		} else if (hasMessagePending(formElement)) {
			msgCell.addElement(createImage(DefResources.IMAGE_MESSAGE_INPUT)
				.setID("errInput"));
		} else {
			msgCell.addElement(Entities.NBSP);
		}
	}

	/**
	 * This method creates the TD's for a labeled form element
	 *
	 * @param		formElement the labelede form element
	 * @param		cols number of form element columns to span
	 * @return		ConcreteElement
	 */
	protected ConcreteElement doCreateLabeledElement(FormElement formElement, int cols) {
		int cells = cols * getCellsPerElement();

		ElementContainer c = new ElementContainer();

		FormLabelDesignModel label	= getLabel(formElement);

		boolean showLabel =
			(label != null)
				&& ((label.getImageRef() != null) || (label.getText() != null));

		// The optional label
		if (showLabel) {

			// if a label area is present then show
			// the fields status indicator
			if (getFormType().isEditable()) {
				// Create the fields status indicator
				TD msgCell = new TD();
				msgCell.setClass(getElementClass(DefClassType.CLASS_INDICATOR));

				paintMessageIndicatior(msgCell, formElement);

				c.addElement(msgCell);
				--cells;
			}

			// Create the Label cell
			TD labelCell = new TD();
			labelCell.setClass(getElementClass(DefClassType.CLASS_LABEL));

			if (formElement.getVAlignment() != null) {
				labelCell.setVAlign(formElement.getVAlignment().toString());
			}

			paintLabel(labelCell, label, getLinkId(formElement));

			c.addElement(labelCell);
			--cells;
		}

		TD controlCell = null;

		if (formElement instanceof FormButtonContainer) {
			controlCell = new TD(createButtons((FormButtonContainer) formElement));
		} else {
			ConcreteElement[] decoration = new ConcreteElement[3];

			if (getFormType().isEditable() && isRequired(formElement)) {

				IMG img = createImage(DefResources.IMAGE_REQUIRED);
				img.setVspace(0);
				img.setAlign(AlignType.absmiddle);
				img.setStyle("margin-left:3px;");
				img.setTitle(getFrameworkString(DefResources.FW_FORM_REQUIRED));

				decoration[0] = img;
			}

			decoration[1] = doCreateHelpIcon(formElement.getHelp());

			String description = getDescription(formElement);

			if (description != null) {
				decoration[2] = new ElementContainer()
					.addElement(Entities.NBSP)
					.addElement(html(localize(description)));
			}

			// if this is a radio button the required image
			// is displayed after the description text
			if (attachRight(formElement)) {
				// Swap the sequence
				ConcreteElement swap = decoration[0];
				decoration[0] = decoration[2];
				decoration[2] = decoration[1];
				decoration[1] = swap;
			}

			Collection decorations = new Vector();
			for (int i = 0; i < decoration.length; i++) {
				if (decoration[i] != null) {
					decorations.add(decoration[i]);
				}
			}

			controlCell = new TD(createElementBody(formElement, decorations));
		}

		controlCell.setNoWrap(formElement.getNoWrap());
		controlCell.setClass(getElementClass(DefClassType.CLASS_DATA));

		if (formElement.getAlignment() != null) {
			controlCell.setAlign(formElement.getAlignment().toString());
		}

		if (formElement.getVAlignment() != null) {
			controlCell.setVAlign(formElement.getVAlignment().toString());
		}

		if (formElement.getWidth() != null) {
			controlCell.setWidth(formElement.getWidth());
		}

		if (formElement.getHeight() != null) {
			controlCell.setHeight(formElement.getHeight());
		}

		if (cells > 1) {
			controlCell.setColSpan(cells);
		}

		return c.addElement(controlCell);
	}

	/**
	 * This method will check if the required image
	 * should be attched left or right to the description
	 * This is required for some Controls like the
	 * RadioControl or CheckboxControl.
	 *
	 * @param formElement	The FormElement
	 * @return true if the required image should be displayed on the right; false otherwise
	 */
	protected boolean attachRight(FormElement formElement) {

		if (formElement instanceof FormControlElement) {

			if (((FormControlElement) formElement).getControl() instanceof RadioControl) {
				return true;
			}

			if (((FormControlElement) formElement).getControl() instanceof CheckboxControl) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Retrieves the label of a form element
	 *
	 * @param		formElement Form element
	 * @return		Label or <code>null</code>
	 */
	protected FormLabelDesignModel getLabel(FormElement formElement) {
		if (formElement instanceof LabeledFormElement) {
			return ((LabeledFormElement) formElement).getLabel();
		} else if (formElement instanceof FormMessageElement) {
			return LABEL_MESSAGE;
		} else if (formElement instanceof FormButtonContainer) {
			return LABEL_EMPTY;
		} else {
			return null;
		}
	}

	/**
	 * Retrieves the description of a form element
	 *
	 * @param		formElement Form element
	 * @return		Description String or <code>null</code>
	 */
	protected String getDescription(FormElement formElement) {
		if (formElement instanceof LabeledFormElement) {
			return ((LabeledFormElement) formElement).getDescription();
		} else {
			return null;
		}
	}

	/**
	 * This method creates the Body content of a form element
	 *
	 * @param		formElement the form element
	 * @param		decorations Additional decoration elements that
	 * 				should be rendered next to the elements body
	 * @return		ConcreteElement
	 */
	protected ConcreteElement createElementBody(FormElement formElement, Collection decorations) {
		ConcreteElement body = null;

		if (formElement instanceof FormControlElement) {
			body = doCreateControl((FormControlElement) formElement, decorations);
		} else if (formElement instanceof FormHtmlElement) {
			body = doCreateHtml((FormHtmlElement) formElement);
		} else if (formElement instanceof FormMessageElement) {
			FormMessageElement message = (FormMessageElement) formElement;

			body = new StringElement(html(localize(message.getMessage()), message.filter()));
		}

		return body;
	}

	/**
	 * Creates the buttons of a button container
	 *
	 * @param		container Contrinar definition
	 * @return		Button elements
	 */
	protected ConcreteElement createButtons(FormButtonContainer container) {
		ElementContainer body = new ElementContainer();

		TR buttonRow = new TR();

		buttonRow.addElement(new TD(Entities.NBSP).setWidth("100%"));

		FormElement[] formElements = container.getFormElements();
		for (int i = 0; i < formElements.length; i++) {

			if (formElements[i] instanceof FormButtonElement) {
				buttonRow.addElement(new TD(doCreateButton((FormButtonElement) formElements[i]))
					.setAlign(AlignType.right));
			}
		}

		body.addElement(new Table()
			.setCellPadding(5)
			.setCellSpacing(0)
			.setBorder(0)
			.setWidth("100%")
			.addElement(buttonRow)
			.setClass(getElementClass(DefClassType.CLASS_BUTTONS)));

		return body;
	}

	/**
	 * This method creates a row separator
	 *
	 * @param		cols number of form element columns to span
	 * @param		nestingLevel The nesting Level of the row in the form
	 * 				element tree
	 * @return		ConcreteElement
	 */
	protected ConcreteElement doCreateRowSeparator(
		int cols,
		int nestingLevel) {

		int cells = cols * getCellsPerElement();

		return doCreateRow(
			new TD(createImage(DefResources.IMAGE_SPACER))
				.setBackground(getImageSrc(DefResources.IMAGE_SEPARATOR))
				.setColSpan(cells),
			nestingLevel,
			ROWTYPE_SEPARATOR);
	}

	/**
	 * This method creates inner frames
	 *
	 * @param		innerFrames The frame definitions
	 * @param		cols number of form element columns to span
	 * @param		nestingLevel The nesting Level of the row in the form
	 * 				element tree
	 * @return		ConcreteElement
	 */
	protected ElementContainer doCreateInnerFrames(
		InnerFrame[] innerFrames,
		int cols,
		int nestingLevel) {

		int cells = cols * getCellsPerElement();

		ElementContainer container = new ElementContainer();

		for (int i = 0; i < innerFrames.length; i++) {
			container.addElement(
				doCreateRow(
					new TD(
						getFramePainter().createInnerFrame(
							innerFrames[i])).setColSpan(
						cells),
					nestingLevel,
					ROWTYPE_INNERFRAME));
		}

		return container;
	}

	/**
	 * Creates the header row for a form element group
	 *
	 * @param		group the group
	 * @param		cols the number of form element columns to span
	 * @param		nestingLevel the nesting level of the group within
	 * 				the form element tree
	 * @return		ConcreteElement
	 */
	protected ConcreteElement doCreateGroupHeader(FormGroupElement group, int cols, int nestingLevel) {

		int cells =
			(cols * getCellsPerElement())
				+ (((nestingLevel == 0) && showFrame()) ? 2 : 0);

		TD title = new TD();
		title.setColSpan(cells);
		title.setClass(getElementClass(DefClassType.CLASS_SECTION));

		ImageModel image = getFormImage(group.getImageRef());

		if (image != null) {
			title.addElement(createImage(image)
				.setVspace(0)
				.setAlign(AlignType.absmiddle)
				.setStyle("margin-right:3px;"));
		}

		title.addElement(html(localize(group.getTitle())));

		return title;
	}

	/**
	 * This method creates the control for a form element of
	 * type <code>FormControlElement</code>
	 *
	 * @param		formElement from element
	 * @param		decorations Additional decoration elements that
	 * 				should be rendered next to the elements body
	 * @return		ConcreteElement
	 */
	protected ConcreteElement doCreateControl(FormControlElement formElement, Collection decorations) {

		// Den Painter erzeugen
		ControlPainter painter =
			PainterFactory.createPainter(
				getPageContext(),
				formElement.getControl());

		if (painter == null) {
			return null;
		} else {
			// Store the collection with decoration elements
			// in the painters context
			if (!decorations.isEmpty()) {
				painter.getPainterContext().setAttribute(
					PainterContextAtributes.DECORATIONS, decorations);
			}

			// We need a unique control id for this control
			// when it can be accessed by an access key!
			if (formElement.getLabel().getAccessKey() != null) {
				painter.getPainterContext().setAttribute(
					PainterContextAtributes.FORCE_STYLEID, Boolean.TRUE);
			}

			return painter.createElement();
		}
	}

	/**
	 * This method creates the button for a form element of
	 * type <code>FormButtonElement</code>
	 *
	 * @param		formButton the button element
	 * @return		ConcreteElement
	 */
	protected ConcreteElement doCreateButton(FormButtonElement formButton) {

		ButtonControl button = new ButtonControl();
		button.setDesignModel(formButton);

		// Create a painter for the button control
		ControlPainter painter =
			PainterFactory.createPainter(getPageContext(), button);

		if (painter == null) {
			return null;
		} else {
			return painter.createElement();
		}
	}

	/**
	 * This method creates the description for a form element of
	 * type <code>FormDescriptionElement</code>
	 *
	 * @param		formElement from element
	 * @return		ConcreteElement
	 */
	protected ConcreteElement doCreateHtml(FormHtmlElement formElement) {

		String html;

		if (formElement.isBodyInclude()) {
			// if the body is specified within the jsp page we do not localize it!
			html = formElement.getHtml();
		} else {
			// if the discription attribute is used, we localized it!
			html = localize(formElement.getHtml());
		}

		return new StringElement(html(html, formElement.filter()));
	}

	/**
	 * Creates the buttons in the header of the list
	 *
	 * @return	collection of ConcreteElement Items
	 */
	protected Collection doCreateTitleButtons() {
		Vector buttons = new Vector();

		return buttons;
	}

	/**
	 * This method creates a container with horizontal orientation
	 *
	 * @param		container container element
	 * @param		cols the number of form element columns to span.
	 * @param		nestingLevel the nesting level of the group within
	 * 				the form element tree
	 * @return		ConcreteElement
	 */
	protected ConcreteElement doCreateHorizontalContainer(FormElementContainer container, int cols, int nestingLevel) {

		ElementContainer rowContent = new ElementContainer();

		// Get a list of all visible form elements
		FormElement[] elements = container.getFormElements(getPrincipal());
		for (int i = 0; i < elements.length; i++) {

			if (elements[i].show(getPrincipal())) {
				int span = elements[i].getColSpan();

				if ((i + 1) == elements.length) {
					// Use all available space for the last
					// element in this row
					span = Math.max(cols, span);
				}

				if (elements[i] instanceof FormElementContainer) {
					rowContent.addElement(new TD()
						.setColSpan(span * getCellsPerElement())
						.addElement(new Table()
							.setCellPadding(0)
							.setCellSpacing(0)
							.setWidth("100%")
							.addElement(doCreateContainer(
								(FormElementContainer) elements[i],
								span,
								nestingLevel + 1))));
				} else {
					rowContent.addElement(doCreateLabeledElement(elements[i], span));
				}

				cols -= span;
			}
		}

		return doCreateRow(container, rowContent, nestingLevel, ROWTYPE_CONTAINER);
	}

	/**
	 * This method creates a container with vertical orientation
	 *
	 * @param		container container element
	 * @param		cols the number of form element columns to span.
	 * @param		nestingLevel the nesting level of the group within
	 * 				the form element tree
	 * @return		ConcreteElement
	 */
	protected ConcreteElement doCreateVerticalContainer(FormElementContainer container, int cols, int nestingLevel) {
		ElementContainer rows = new ElementContainer();

		// Get a list of all visible form elements
		FormElement[] elements = container.getFormElements(getPrincipal());
		for (int i = 0; i < elements.length; i++) {

			if (elements[i].show(getPrincipal())) {
				// Add a new row for the form element
				ConcreteElement row = null;

				if (elements[i] instanceof FormElementContainer) {
					row =
						doCreateContainer(
							(FormElementContainer) elements[i],
							cols,
							nestingLevel);
				} else {
					row =
						doCreateRow(
							doCreateLabeledElement(elements[i], cols),
							nestingLevel,
							ROWTYPE_CONTROL);
				}

				if (row != null) {
					String elementId = getFormElementId(elements[i]);

					if (elementId != null) {
						row.setID(elementId);
					}

					rows.addElement(row);

					// Add a separator after the row
					if (((i + 1) < elements.length) && !elements[i + 1].joinElements()) {
						ConcreteElement separator = doCreateRowSeparator(cols, nestingLevel);

						if (separator != null) {
							rows.addElement(separator);
						}
					}
				}
			}

			// Render the inner frames
			InnerFrame[] frames = getForm().getInnerFrames(elements[i]);
			if ((frames != null) && (frames.length > 0)) {
				rows.addElement(
					doCreateInnerFrames(frames, cols, nestingLevel));
			}
		}

		return rows;
	}

	/**
	 * Searches the FormElement tree for a default button
	 * 
	 * @param		container Node of the FormElement Tree
	 * @return		Default button or <code>null</code>
	 */
	protected FormButtonElement getDefaultButton(FormElementContainer container) {
		if (container == null) {
			return null;
		} else {
			FormButtonElement defaultButton = null;
			
			if (container instanceof FormButtonContainer) {
				defaultButton = ((FormButtonContainer) container).getDefaultButton();
			} else {
				FormElement[] elements = container.getFormElements();
				for (int i = 0; (defaultButton == null) && (i < elements.length); i++) {

					if (elements[i] instanceof FormElementContainer) {
						defaultButton = getDefaultButton((FormElementContainer) elements[i]);
					}
				}
			}

			// Button not found
			return defaultButton;
		}
	}

	/**
	 * This method creates a hidden default button for the form
	 *
	 * @return		Button element or <code>null</code>
	 */
	protected ConcreteElement doCreateDefaultButton() {
		return doCreateDefaultButton(getDefaultButton(getForm().getFormElements()));
	}
	
	/**
	 * This method creates a hidden default button for the form
	 *
	 * @param		button the default button
	 * @return		Button element or <code>null</code>
	 */
	protected ConcreteElement doCreateDefaultButton(FormButtonElement button) {
		
		if (button == null) {
			return null;
		}

		Input control = new Input(Input.image)
			.setSrc(getImageSrc(DefResources.IMAGE_SPACER));

		// set the name. The name is send with the
		// request and is needed to invoke the callback methode
		if (button.getName() != null) {
			control.setName(button.getName());
		} else if (button.getId() != null) {
			control.setName(button.getId());
		}

		control.setClass(getElementClass(DefClassType.CLASS_HIDENBUTTON));
		
		// Button should not receive the focus
		control.setTabindex(-1);
		
		// assign script handlers
		if (!getForm().isDisabled()) {
			PainterHelp.setScriptHandler(control, button);
		}

		return control;
	}

	/**
	 * This method creates a generic container
	 *
	 * @param		container container element
	 * @param		cols the number of form element columns to span.
	 * @param		nestingLevel the nesting level of the group within
	 * 				the form element tree
	 * @return		ConcreteElement
	 */
	protected ConcreteElement doCreateContainer(
		FormElementContainer container,
		int cols,
		int nestingLevel) {

		ElementContainer rows = new ElementContainer();

		if (container instanceof FormButtonContainer) {
			// Create a button container
			rows.addElement(
				doCreateRow(
					container,
					doCreateLabeledElement(container, cols),
					nestingLevel,
					ROWTYPE_BUTTONS));
		} else {
			// Create a header for this group if necessary
			if (container instanceof FormGroupElement) {
				FormGroupElement group = (FormGroupElement) container;

				if (group.getTitle() != null) {
					rows.addElement(new TR()
						.addElement(doCreateGroupHeader(group, cols, nestingLevel)));
				}
			}

			if (OrientationType.HORIZONTAL.equals(container.getOrientation())) {
				rows.addElement(doCreateHorizontalContainer(container, cols, nestingLevel));
			} else {
				rows.addElement(doCreateVerticalContainer(container, cols, nestingLevel));
			}
		}

		return rows;
	}

	/**
	 * Creates an additional header.
	 *
	 * @return	ConcreteElement
	 */
	protected ConcreteElement doCreateHeader() {
		return doCreateFrames(AlignmentType.TOP);
	}

	/**
	 * Creates the froms body.
	 *
	 * @return	ConcreteElement
	 */
	protected ConcreteElement doCreateBody() {
		Table body = (Table) new Table()
			.setCellPadding(0)
			.setCellSpacing(0)
			.setWidth("100%")
			.addElement(doCreateContainer(getForm().getFormElements(), getForm().getColSpan(), 0))
			.setClass(getElementClass(DefClassType.CLASS_INNER_BODY));

		return body;
	}

	/**
	 * Creates an additional footer.
	 * The current layout does not render a footer
	 * @return	ConcreteElement
	 */
	protected ConcreteElement doCreateFooter() {
		return doCreateFrames(AlignmentType.BOTTOM);
	}

	/**
	 * Creates a frame container with all frames that match
	 * the given filter
	 * 
	 * @param		alignmentFilter the filter 
	 * @return		Frame container or <code>null</code>
	 */
	protected ConcreteElement doCreateFrames(AlignmentType alignmentFilter) {
		InnerFrame[] frames = getForm().getInnerFrames(alignmentFilter);

		if ((frames == null) || (frames.length == 0)) {
			return null;
		} else {
			ElementContainer ec = new ElementContainer();
			for (int i = 0; i < frames.length; i++) {
				ec.addElement(getFramePainter().createInnerFrame(frames[i]));
			}

			return ec;
		}
	}

	/**
	 * Creates the Title for the Frame
	 *
	 * @return		Frame Title or <code>null</code>
	 */
	protected FrameTitle getFrameTitle() {
		return new FrameTitle(
			getForm().getImage(),
			getSmartCaption(getForm().getCaption(), getForm().getDetail()),
			getSmartDetail(getForm().getCaption(), getForm().getDetail()),
			getForm().getHelp(),
			doCreateTitleButtons());
	}

	/**
	 * @see com.cc.framework.ui.painter.ControlPainter#doBeforeCreate()
	 */
	protected void doBeforeCreate() {
		getPainterContext().pushAttributes();
		getPainterContext().setAttribute(PainterContextAtributes.COMMENTS, Boolean.FALSE);

		super.doBeforeCreate();
	}

	/**
	 * Creates the HTML Code for th following Elements:
	 * <ul>
	 *   <li>Title</li>
	 *   <li>Body</li>
	 *   <li>Footer (optional)</li>
	 * </ul>
	 * @return	ConcreteElement
	 */
	protected ConcreteElement doCreateElement() {

		// if no designmodel is specified then terminate
		if (getForm().getDesignModel() == null) {
			return null;
		}

		// render the form
		Frame control =
			getFramePainter().createFrame(getFrameTitle(), showFrame());

		if (getStyleId() != null) {
			control.setID(getStyleId());
		}

		if (getForm().getStyle() != null) {
			control.setStyle(getForm().getStyle());
		}

		// set general style class
		if (getForm().getStyleClass() == null) {
			control.setClass(getElementClass(DefClassType.CLASS_CONTROL));
		} else {
			control.setClass(getForm().getStyleClass());
		}

		if (getForm().getWidth() != null) {
			control.setWidth(getForm().getWidth());
		}

		if (getForm().getSummary() != null) {
			control.setSummary(getForm().getSummary());
		}

		// add content elements
		control
			.addBodyElement(doCreateHeader())
			.addBodyElement(doCreateBody())
			.addBodyElement(doCreateFooter());

		// embed the control into an html span element
		Span span = new Span();

		if (getForm().getId() != null) {
			span.setID(getForm().getId());
		}

		// Add a hidden input field for the default button
		ConcreteElement defbutton = doCreateDefaultButton();
		if (defbutton != null) {
			span.addElement(defbutton);
		}

		// Add a hidden field with the form id
		// this field is required to map an incomming request to
		// coresponding form action handler
		span
			.addElement(new Input(Input.hidden, "formid", getForm().getFormId()))
			.addElement(control);

		return span;
	}

	/**
	 * @see com.cc.framework.ui.painter.ControlPainter#doAfterCreate()
	 */
	protected void doAfterCreate() {
		super.doAfterCreate();

		getPainterContext().popAttributes();
	}

	// ======================================
	//             helper
	// ======================================

	/**
	 * Returns a id for the element that can be used
	 * in java script
	 *
	 * @param	formElement form element
	 * @return	Scripting Id or <code>null</code>
	 */
	protected String getFormElementId(FormElement formElement) {

		if (formElement instanceof FormControlElement) {
			String property = ((FormControlElement) formElement).getProperty();

			if ((null != property) && !"".equals(property)) {
				StringBuffer id = new StringBuffer("tr_");

				StringTokenizer tokens = new StringTokenizer(property, ".");

				while (tokens.hasMoreTokens()) {
					id.append(tokens.nextToken());
				}

				return id.toString();
			}
		}

		return null;
	}

	/**
	 * Checks if a form element is a required input field
	 *
	 * @param	formElement form element
	 * @return	boolean
	 */
	protected boolean isRequired(FormElement formElement) {

		if (formElement instanceof FormControlElement) {
			// Die Fehlerhinweise sind unter dem Property Namen abgelegt.
			return ((FormControlElement) formElement).isRequired();
		} else {
			return false;
		}
	}

	/**
	 * Checks if a error hint must be painted
	 *
	 * @param	formElement form element
	 * @return	boolean
	 */
	protected boolean hasErrorPending(FormElement formElement) {

		if (formElement instanceof FormControlElement) {
			// Die Fehlerhinweise sind unter dem Property Namen abgelegt.
			return FrameworkAdapterFactory.getAdapter().hasMessages(
				getPageContext(),
				Severity.ERROR,
				((FormControlElement) formElement).getProperty());
		} else {
			return false;
		}
	}

	/**
	 * Checks if a message hint must be painted
	 *
	 * @param	formElement form element
	 * @return	boolean
	 */
	protected boolean hasMessagePending(FormElement formElement) {

		if (formElement instanceof FormControlElement) {
			// Die Fehlerhinweise sind unter dem Property Namen abgelegt.
			return FrameworkAdapterFactory.getAdapter().hasMessages(
				getPageContext(),
				Severity.INFORMATION,
				((FormControlElement) formElement).getProperty());
		} else {
			return false;
		}
	}
}