/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/def/DefButtonPainter.java,v 1.45 2005/11/15 18:02:00 P001001 Exp $
 * $Revision: 1.45 $
 * $Date: 2005/11/15 18:02:00 $
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

import org.apache.ecs.AlignType;
import org.apache.ecs.ConcreteElement;
import org.apache.ecs.ElementContainer;
import org.apache.ecs.html.Div;
import org.apache.ecs.html.IMG;
import org.apache.ecs.html.Input;
import org.apache.ecs.html.Span;
import org.apache.ecs.html.TD;
import org.apache.ecs.html.TR;
import org.apache.ecs.html.Table;

import com.cc.framework.Globals;
import com.cc.framework.ui.Color;
import com.cc.framework.ui.control.ButtonControl;
import com.cc.framework.ui.model.ButtonType;
import com.cc.framework.ui.model.ClientEvent;
import com.cc.framework.ui.painter.PainterContext;
import com.cc.framework.ui.painter.PainterHelp;
import com.cc.framework.util.StringHelp;

/**
 * HTML painter for the button control
 *
 * @author      <a href="mailto:gschulz@scc-gmbh.com">Gernot Schulz</a>
 * @version     $Revision: 1.45 $
 * @since       1.0
 */
public class DefButtonPainter extends DefPainterBase {

	/**
	 * Constructor
	 *
	 * @param	painterContext	The PainterContext
	 * @param	ctrl			The Control to render
	 */
	public DefButtonPainter(PainterContext painterContext, ButtonControl ctrl) {
		super(painterContext);
	}

	/**
	 * Retrieves the control for this painter
	 *
	 * @return		Control
	 */
	protected ButtonControl getCtrl() {
		return (ButtonControl) getPainterContext().getControl();
	}

	/**
	 * Creates a text button
	 *
	 * @return		HTML Elements for a the text button
	 */
	protected ConcreteElement doCreateTextButton() {

		// Draw a text button
		Div textbutton = new Div();

		// Id-Attribute
		String btnId = "";

		if (getStyleId() != null) {
			btnId = getStyleId();
		} else if (getCtrl().getName() != null) {
			btnId = getCtrl().getName();
		}
		
		textbutton.setID(btnId);

		// Tooltip-Attribute
		if (getCtrl().getTooltip() != null) {
			textbutton.setTitle(html(localize(getCtrl().getTooltip()), getCtrl().filter()));
		}

		// width-Attribute
		if (getCtrl().getWidth() != null) {
			textbutton.setStyle("width:" + getCtrl().getWidth());
		}

		if (!getCtrl().isDisabled()) {
			// add all javascript handlers
			PainterHelp.setScriptHandler(textbutton, getCtrl());

			// -------- set the onclick handler ----------
			String fieldid = getCtrl().getName() + "Hidden"; //getCtrl().hashCode();

			// Create a hidden field
			if (getCtrl().getName() != null) {
				
				Input input = new Input(Input.HIDDEN, getCtrl().getControlName());
				input.setID(fieldid);
				
				if (ButtonType.CANCEL.equals(getCtrl().getButtonType())) {
					input.setName(Globals.STRUTS_CANCEL_KEY);
					input.setValue("Cancel");
				}
				
				textbutton.addElement(input);
			}

			String onClick = null;

			if (getCtrl().getHandler(ClientEvent.ONCLICK) == null) {

				if (ButtonType.BUTTON.equals(getCtrl().getButtonType())) {
					// create a default onclick handler which submits the form
					onClick = StringHelp.strcat(
						"document.getElementById('",
						fieldid,
						"').value='clicked'; CCUtility.submitEnclosingForm(this);");
				} else if (ButtonType.CANCEL.equals(getCtrl().getButtonType())) {
					onClick = StringHelp.strcat(
						"bCancel=true;",
						"document.getElementById('",
						fieldid,
						"').value='clicked'; CCUtility.submitEnclosingForm(this);");
				} else if (ButtonType.RESET.equals(getCtrl().getButtonType())) {
					onClick = "return CCUtility.resetEnclosingForm(this);";
				}
			} else {
				// otherwise use the assigned onclickhandler
				onClick = StringHelp.strcat("document.getElementById('", fieldid, "').value='clicked'; ", getCtrl().getHandler(ClientEvent.ONCLICK));
			}

			PainterHelp.setScriptHandler(textbutton, ClientEvent.ONCLICK, onClick);
		}

		// -------- get the background images for this text button ----------
		String bgImageLeft   = "";
		String bgImageMiddle = "";
		String bgImageRight  = "";

		if (getCtrl().getButtonStyle() != 1) {
			// otherwise use userdefined background images
			bgImageLeft   = "app.tbtn.bg.left" + getCtrl().getButtonStyle();
			bgImageMiddle = "app.tbtn.bg.middle" + getCtrl().getButtonStyle();
			bgImageRight  = "app.tbtn.bg.right" + getCtrl().getButtonStyle();
		} else {
			// Use the default background images of the painter
			bgImageLeft   = DefResources.TEXTBUTTON_BG_LEFT1;
			bgImageMiddle = DefResources.TEXTBUTTON_BG_MIDDLE1;
			bgImageRight  = DefResources.TEXTBUTTON_BG_RIGHT1;
		}

		// create a container for text and image
		ElementContainer btnface = new ElementContainer();

		// 1) check if an icon in front of the button label must be displayed
		if (getCtrl().getSrc() != null) {
			String srcpath = getSource(getCtrl().getBase(), getCtrl().getSrc());

			IMG img = new IMG(srcpath);
			img.setStyle("margin-right:3px;");
			img.setHspace(0);
			img.setVspace(0);
			img.setBorder(0);
			img.setAlign(AlignType.absmiddle);

			btnface.addElement(img);
		}

		// 2) button text
		Span span = new Span(html(localize(getCtrl().getText()), getCtrl().filter()));
		span.setID(btnId + "Label");

		Color color = null;

		// If the control is disabled gray out the label
		// so get the color which should be used for the text 
		if (getCtrl().isDisabled()) {
			color = getColor(DefColorPalette.BUTTON_COLOR_TEXT_INACTIVE);
		} else {
			color = getColor(DefColorPalette.BUTTON_COLOR_TEXT_ACTIVE);
		}

		// if no styleClass or style was specified
		// set the default css class.
		if (getCtrl().getStyleClass() != null) {
			span.setClass(getCtrl().getStyleClass());
		} else if (getCtrl().getStyle() != null) {
			span.setStyle(getCtrl().getStyle());
		} else if (!getCtrl().isDisabled()) {
			span.setStyle("color:" + color.toHtml());  // TODO not longer needed?
			span.setClass(DefHtmlClass.TEXTBUTTON);
		} else if (color != null && getCtrl().isDisabled()) {
			span.setStyle("color:" + color.toHtml());
		}

		btnface.addElement(span);

		// ---------------------------------
		//      Paint the Text Button
		// ---------------------------------
		Table table = new Table()
			.setCellSpacing(0)
			.setCellPadding(0)
			.setBorder(0);

		table.setClass(DefHtmlClass.TEXTBUTTON);

		if (getCtrl().getWidth() != null) {
			table.setWidth(getCtrl().getWidth());
		}

		IMG imgLeft      = getCtrl().isDisabled() ? disableImg(createImage(bgImageLeft)) : createImage(bgImageLeft);
		imgLeft.setID("btnBgL");

		IMG imgRight     = getCtrl().isDisabled() ? disableImg(createImage(bgImageRight)) : createImage(bgImageRight);
		imgRight.setID("btnBgR");

		String imgMiddle = getCtrl().isDisabled() ? disableImg(getImageSrc(bgImageMiddle)) : getImageSrc(bgImageMiddle);

		TD left  = new TD(imgLeft);
		TD right = new TD(imgRight);

		TD middle = new TD(btnface)
			.setBackground(imgMiddle)
			.setNoWrap(true)
			.setAlign(AlignType.MIDDLE);
		middle.setStyle("background-position: right;");
		middle.setID("btnMiddle");

		// if the width attribute was specified
		// the middle cell must set to 100%
		if (getCtrl().getWidth() != null) {
			middle.setWidth("100%");
		}

		table.addElement(new TR()
			.addElement(left)
			.addElement(middle)
			.addElement(right)
			.setVAlign(AlignType.middle));

/*
		if (getCtrl().getStyleClass() != null) {
			table.setClass(getCtrl().getStyleClass());
		} else {
			table.setClass(DefHtmlClass.TEXTBUTTON);
		}
*/
		// ---------------------------------

		textbutton.addElement(table);

		return textbutton;
	}

	/**
	 * Creates a image button
	 *
	 * @return		HTML Elements for a the image button
	 */
	protected ConcreteElement doCreateImageButton() {

		String srcpath = getSource(getCtrl().getBase(), getCtrl().getSrc());

		// Draw an image button
		Input input = new Input();
		input.setType(Input.image);

		if (getStyleId() != null) {
			input.setID(getStyleId());
		} else if (getCtrl().getName() != null) {
			input.setID(getCtrl().getName());
		}

		if (getElementName() != null) {
			input.setName(getElementName());
		}

		if (getCtrl().getStyle() != null) {
			input.setStyle(getCtrl().getStyle());
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

		if (getCtrl().isDisabled()) {
			input.setDisabled(true);
			input.setSrc(disableImg(srcpath));
		} else {
			input.setSrc(srcpath);

			// assign script handlers only if the button is not disabled
			PainterHelp.setScriptHandler(input, getCtrl());

			if (getCtrl().getHandler(ClientEvent.ONCLICK) == null) {
				if (ButtonType.CANCEL.equals(getCtrl().getButtonType())) {
					input.setOnClick("bCancel=true;");
					input.setName(Globals.STRUTS_CANCEL_KEY);
					input.setValue("Cancel");
				} else if (ButtonType.RESET.equals(getCtrl().getButtonType())) {
					input.setOnClick("return CCUtility.resetEnclosingForm(this);");
				}
			}
		}

		return input;
	}

	/**
	 * @see com.cc.framework.ui.painter.ControlPainter#doCreateElement()
	 */
	protected ConcreteElement doCreateElement() {
		if (getCtrl().getText() == null) {
			return doCreateImageButton();
		} else {
			return doCreateTextButton();
		}
	}

	/**
	 * If an image button is disabled the correct image must be used.
	 * To get the correct image the image is exchanged. Therefore
	 * the state xxxx1.gif is replaced by xxxx3.gif which stands for
	 * the disabled image.
	 *
	 * @param imgSrc The image source
	 * @return	The new image source for the disabled button
	 */
	protected String disableImg(String imgSrc) {

		int pos = imgSrc.lastIndexOf(".");

		if (pos == -1) {
			// no image to replace
			return imgSrc;
		}

		String state = imgSrc.substring(pos - 1, pos);

		try {
			Integer.parseInt(state);

			imgSrc = imgSrc.substring(0, pos - 1) + "2" + imgSrc.substring(pos, imgSrc.length());

		} catch (NumberFormatException nfe) {
			// no action
		}

		return imgSrc;
	}

	/**
	 * If an image button is disabled the correct image must be used.
	 * To get the correct image the image is exchanged. Therefore
	 * the state xxxx1.gif is replaced by xxxx3.gif which stands for
	 * the disabled image.
	 *
	 * @param image The image
	 * @return	The image with changed src Attribute
	 */
	protected IMG disableImg(IMG image) {
		String src = image.getAttribute("src");

		image.setSrc(disableImg(src));

		return image;
	}
}
