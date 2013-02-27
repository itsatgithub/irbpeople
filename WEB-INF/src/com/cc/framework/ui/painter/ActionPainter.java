/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/ActionPainter.java,v 1.45 2005/07/12 16:07:40 P001002 Exp $
 * $Revision: 1.45 $
 * $Date: 2005/07/12 16:07:40 $
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

package com.cc.framework.ui.painter;

import org.apache.ecs.AlignType;
import org.apache.ecs.ConcreteElement;
import org.apache.ecs.ElementContainer;
import org.apache.ecs.html.A;
import org.apache.ecs.html.IMG;
import org.apache.ecs.html.Span;

import com.cc.framework.http.Hyperlink;
import com.cc.framework.ui.AlignmentType;
import com.cc.framework.ui.control.ControlAction;
import com.cc.framework.ui.model.ClientEvent;
import com.cc.framework.ui.model.ClientHandler;
import com.cc.framework.ui.model.ImageModel;
import com.cc.framework.ui.model.imp.ClientHandlerImp;
import com.cc.framework.ui.model.imp.ImageModelImp;
import com.cc.framework.ui.painter.def.DefHtmlClass;

/**
 * The ActionPainter Class is responsible to generate
 * a HTML-Element to initiate an Action
 *
 * @author    <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version   $Revision: 1.45 $
 * @since       1.0
 */
public class ActionPainter {

	/**
	 * ControlAction
	 */
	private ControlAction action			= null;

	/**
	 * Indicates if the User is allowed to
	 * activated the Action
	 */
	private boolean active					= true;

	/**
	 * ImageModel
	 */
	private ImageModel image				= null;

	/**
	 * Additional image style
	 */
	private String imageStyle				= null;

	/**
	 * Label
	 */
	private String label					= null;

	/**
	 * Tooltip
	 */
	private String tooltip					= null;

	/**
	 * Style
	 */
	private String style					= null;

	/**
	 * StyleId
	 */
	private String styleId					= null;

	/**
	 * StyleClass
	 */
	private String styleClass				= null;

	/**
	 * Target
	 */
	private String target					= null;

	/**
	 * Access to the client handler
	 */
	private ClientHandler handler			= null;

	/**
	 * Additional client handlers which can be set
	 * with a call to addClientHandler()
	 */
	private ClientHandler additionalHandler	= null;

	/**
	 * Painter context
	 */
	private PainterContext ctx				= null;

	/**
	 * Specifies the alignment of the image
	 * (in front of / or behinde the label)
	 */
	private AlignmentType imageAlign		= AlignmentType.LEFT;

	/**
	 * Specifies the vertical alignment of the image
	 * (in front of / or behinde the label)
	 */
	private AlignmentType imageVAlign		= AlignmentType.ABSMIDDLE;

	// -------------------------------
	//        methods
	// -------------------------------

	/**
	 * Constructor for ActionPainter
	 *
	 * @param ctx		Painter Context
	 * @param action	ControlAction
	 */
	public ActionPainter(PainterContext ctx, ControlAction action) {
		super();

		this.ctx	= ctx;
		this.action	= action;
	}

	/**
	 * Returns the Formaction-Flag
	 * @return boolean
	 */
	public boolean isFormAction() {
		return action.isFormAction();
	}

	/**
	 * Adds an additional event handler to this painter
	 *
	 * @param event Event
	 * @param handler ScriptCode
	 */
	public void addEventHandler(ClientEvent event, String handler) {
		if (handler != null) {
			if (additionalHandler == null) {
				additionalHandler = new ClientHandlerImp();
			}

			additionalHandler.setHandler(event, handler);
		}
	}

	/**
	 * Method addParameter
	 * @param	obj	Object
	 */
	public void addParameter(Object obj) {
		action.addParameter(obj);
	}

	/**
	 * Method addParameter
	 * @param	value	Value
	 */
	public void addParameter(String value) {
		action.addParameter(value);
	}

	/**
	 * Method addParameter
	 * @param	value	Value
	 */
	public void addParameter(Integer value) {
		action.addParameter(value);
	}

	/**
	 * Method addParameter
	 * @param	value	Value
	 */
	public void addParameter(int value) {
		action.addParameter(value);
	}

	/**
	 * Method addParameter
	 * @param	value	Value
	 */
	public void addParameter(Long value) {
		action.addParameter(value);
	}

	/**
	 * Method addParameter
	 * @param	value	Value
	 */
	public void addParameter(long value) {
		action.addParameter(value);
	}

	/**
	 * Method addParameter
	 * @param	value	Value
	 */
	public void addParameter(Boolean value) {
		action.addParameter(value);
	}

	/**
	 * Method addParameter
	 * @param	value	Value
	 */
	public void addParameter(boolean value) {
		action.addParameter(value);
	}

	/**
	 * Sets the Image, which should be associated with the Action
	 * @param imageResource	ImageResource
	 * @deprecated
	 */
	public void setImage(String imageResource) {
		this.image = new ImageModelImp(imageResource);
	}

	/**
	 * Sets the Image, which should be associated with the Action
	 * @param imageResource	ImageResource
	 * @param width			Image Width
	 * @param height		Image Height
	 * @deprecated
	 */
	public void setImage(String imageResource, int width, int height) {
		this.image = new ImageModelImp(imageResource, width, height);
	}

	/**
	 * Sets the Image
	 * @param image	ImageModel
	 */
	public void setImage(ImageModel image) {
		if (image == null) {
			return;
		}

		this.image = image;
	}

	/**
	 * Sets an additional image Style
	 *
	 * @param	style Image style;
	 */
	public void setImageStyle(String style) {
		this.imageStyle = style;
	}

	/**
	 * sets the Client Event Handlers
	 * @param		handler Client Handler
	 */
	public void setClientHandler(ClientHandler handler) {
		this.handler = handler;
	}

	/**
	 * Sets the ToolTip
	 * @param tooltip	ToolTip
	 */
	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

	/**
	 * Sets the Target
	 * @param target	Target
	 */
	public void setTarget(String target) {
		if ("".equals(target)) {
			this.target = null;
		} else {
			this.target = target;
		}
	}

	/**
	 * Sets the Label.
	 * If necessary the label must be html encoded and
	 * localized first. This is not done by the ActionPainter
	 * @param label	 Label
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * Sets the Style
	 * @param style	Style
	 */
	public void setStyle(String style) {
		this.style = style;
	}

	/**
	 * Sets the StyleId
	 * @param styleId	StyleId
	 */
	public void setStyleId(String styleId) {
		this.styleId = styleId;
	}

	/**
	 * Sets the StyleClass
	 * @param styleClass	StyleClass
	 */
	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}


    /**
	 * Directs the framework to include a transaction token (if any)
	 * in all generated hyperlinks. The Transaction token is used to
	 * track form re-submissions.
	 * 
	 * @param		transaction include transaction token
	 */
	public void setTransaction(boolean transaction) {
		action.setTransaction(transaction);
	}

	/**
	 * return the Action
	 * @return ControlAction
	 */
	public ControlAction getAction() {
		return action;
	}

	/**
	 * Returns the Image
	 * @return	ImageModel
	 */
	public ImageModel getImage() {
		return image;
	}

	/**
	 * Returns the Label
	 * @return	String
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Returns the Style
	 * @return String
	 */
	public String getStyle() {
		return style;
	}

	/**
	 * Returns the StyleClass
	 * @return String
	 */
	public String getStyleClass() {
		return styleClass;
	}

	/**
	 * Returns the StyleId
	 * @return	String
	 */
	public String getStyleId() {
		return styleId;
	}

	/**
	 * Returns the Target
	 * @return	String
	 */
	public String getTarget() {
		return target;
	}

	/**
	 * Returns the Tooltip
	 * @return	String
	 */
	public String getTooltip() {
		return tooltip;
	}

	/**
	 * Checks if the framework should include a transaction token (if any) in
	 * all generated hyperlinks. The Transaction token is used to track form
	 * re-submissions.
	 * 
	 * @return		<code>true</code> if the transaction token should be generated
	 */
	public boolean getTransaction() {
		return action.getTransaction();
	}

	/**
	 * Returns the Active Falf
	 * @return boolean
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * Sets the Active Flag
	 * @param b	Flag
	 */
	public void setActive(boolean b) {
		active = b;
	}

	/**
	 * Returns the alignment of an additional image
	 *
	 * @return		AlignmentType
	 */
	public AlignmentType getImageAlign() {
		return imageAlign;
	}

	/**
	 * Sets the alignment of an additional image
	 *
	 * @param		type Alignment Type
	 */
	public void setImageAlign(AlignmentType type) {
		imageAlign = type;
	}

	/**
	 * Returns the vertical alignment of an additional image
	 *
	 * @return		AlignmentType
	 */
	public AlignmentType getImageVAlign() {
		return imageVAlign;
	}

	/**
	 * Sets the vertical alignment of an additional image
	 *
	 * @param		type Alignment Type
	 */
	public void setImageVAlign(AlignmentType type) {
		imageVAlign = type;
	}


	/**
	 * Creates the actions hyperlink
	 *
	 * @return	String
	 */
	public String createActionHref() {
		Hyperlink link = getAction().createHyperlink(ctx.getPageContext());

		return PainterHelp.decorateLink(ctx.getPageContext(), link);
	}

	/**
	 * Creates an Element which makes it possible to choose the Action
	 * on the user interface.
	 * @return	ConcreteElement
	 */
	public ConcreteElement createElement() {

		ConcreteElement link = createLink(createBody());

		if (getTooltip() != null) {
			link.setTitle(getTooltip());
		}

		if (getStyle() != null) {
			link.setStyle(getStyle());
		}

		if (getStyleId() != null) {
			link.setID(getStyleId());
		}

		if (getStyleClass() != null) {
			link.setClass(getStyleClass());
		}

		return link;
	}

	/**
	 * Creates the Image
	 * @return	IMG
	 */
	protected IMG createImage() {

		if (getImage() == null) {
			return null;
		}

		IMG img = ctx.createImage(getImage());

		// btnXXX Images should be exchanged if the user moves the
		// mouse over the image. Responsible for this is a
		// Javascript handler. So an id is needed which must be set
		if (getImage().getSource().indexOf("btn") != -1) {
			img.setID("btn");
		}

		if (imageVAlign != null) {
			img.setAlign(imageVAlign.toString());
		} else if (getLabel() != null) {
			// if there is a label, the image must be
			// vertical aligned
			img.setAlign(AlignType.middle);
		}

		return img;
	}

	/**
	 * Creates the passive Element which is visible to
	 * the user
	 * @return		ConcreteElement
	 */
	protected ConcreteElement createBody() {

		ConcreteElement body = null;

		if ((getImage() != null) && (getLabel() != null)) {
			// get the sortable image and
			// add a spacer between the image an the
			// column title. 3px equals none breaking space
			IMG img = createImage();
			img.setVspace(0);

			if (imageStyle != null) {
				img.setStyle(imageStyle);
			}

			if (imageAlign.equals(AlignmentType.LEFT)) {
				if (imageStyle == null) {
					// Use a default style
					img.setStyle("margin-right:3px;");
				}
				body = new ElementContainer()
					.addElement(img)
					.addElement(getLabel());
			} else {
				if (imageStyle == null) {
					// Use a default style
					img.setStyle("margin-left:3px;");
				}
				body = new ElementContainer()
					.addElement(getLabel())
					.addElement(img);
			}
		} else if (getImage() != null) {
			IMG img = createImage();
			img.setVspace(0);

			if (imageStyle != null) {
				img.setStyle(imageStyle);
			}

			body = img;
		} else if (getLabel() != null) {
			body = new Span(getLabel());
		}

		return body;
	}

	/**
	 * Creates an active Element which makes it possible
	 * to choose the Action on the user interface.
	 *
	 * @param		linkBody The nested link body
	 * @return		ConcreteElement
	 */
	protected ConcreteElement createLink(ConcreteElement linkBody) {

		if (!isActive()) {
			// Nest the body in a Span element
			if (linkBody instanceof Span) {
				return linkBody;
			} else {
				return new Span().addElement(linkBody);
			}
		} else {
			if (isFormAction()) {
				Span anchor = new Span();
				anchor.addElement(linkBody);

				if ((handler != null) && (handler.getHandler(ClientEvent.ONCLICK) != null)) {
					anchor.setOnClick(getAction().createSubmitHandler(
							ctx.getPageContext(),
							handler.getHandler(ClientEvent.ONCLICK)));
				} else if ((additionalHandler != null) && (additionalHandler.getHandler(ClientEvent.ONCLICK) != null)) {
					anchor.setOnClick(getAction().createSubmitHandler(
							ctx.getPageContext(),
							additionalHandler.getHandler(ClientEvent.ONCLICK)));
				} else {
					anchor.setOnClick(getAction().createSubmitHandler(
							ctx.getPageContext()));
				}

				anchor.setClass(DefHtmlClass.FORMELEMENTACTION);

				return anchor;
			} else {
				A anchor = new A(createActionHref(), linkBody);

				if (getTarget() != null) {
					anchor.setTarget(getTarget());
				}

				// add the Script Handlers
				PainterHelp.setScriptHandler(anchor, handler);
				PainterHelp.setScriptHandler(anchor, additionalHandler);

				return anchor;
			}
		}
	}
}