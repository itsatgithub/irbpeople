/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/def/DefCrumbsPainter.java,v 1.23 2005/09/27 14:06:22 P001002 Exp $
 * $Revision: 1.23 $
 * $Date: 2005/09/27 14:06:22 $
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

import java.util.Locale;

import org.apache.ecs.AlignType;
import org.apache.ecs.ConcreteElement;
import org.apache.ecs.ElementContainer;
import org.apache.ecs.html.IMG;
import org.apache.ecs.html.Input;
import org.apache.ecs.html.TD;
import org.apache.ecs.html.TR;
import org.apache.ecs.html.Table;

import com.cc.framework.ui.ImageMap;
import com.cc.framework.ui.control.ControlActionDef;
import com.cc.framework.ui.control.CrumbsControl;
import com.cc.framework.ui.model.ClientEvent;
import com.cc.framework.ui.model.ClientHandler;
import com.cc.framework.ui.model.CrumbModel;
import com.cc.framework.ui.model.CrumbsDesignModel;
import com.cc.framework.ui.model.ImageModel;
import com.cc.framework.ui.model.imp.ClientHandlerImp;
import com.cc.framework.ui.painter.ActionPainter;
import com.cc.framework.ui.painter.PainterContext;
import com.cc.framework.ui.painter.PainterHelp;

/**
 * Painter class for the crumbs control
 *
 * @author      <a href="mailto:gschulz@scc-gmbh.com">Gernot Schulz</a>
 * @version     $Revision: 1.23 $
 * @since       1.2
 */
public class DefCrumbsPainter extends DefPainterBase {

	/** Crumbs State Mask */
	protected static final int FLAG_STATES				= 0x000F;

	/** Crumb is unselected */
	protected static final int FLAG_UNSELECTED			= 0x0001;

	/** Crumb is selected */
	protected static final int FLAG_SELECTED			= 0x0002;

	/** Crumb is disabled */
	protected static final int FLAG_DISABLED			= 0x0003;

	/** This flag is set for the first visible crumb */
	protected static final int FLAG_FIRST				= 0x1000;

	/** This flag is set for the last visible crumb */
	protected static final int FLAG_LAST				= 0x2000;

	/**
	 * Constructor
	 * @param	painterContext	The PainterContext
	 * @param	ctrl			The Control to render
	 */
	public DefCrumbsPainter(PainterContext painterContext, CrumbsControl ctrl) {
		super(painterContext);
	}

	/**
	 * Retrieves the control for this painter
	 *
	 * @return		Control
	 */
	protected CrumbsControl getCtrl() {
		return (CrumbsControl) getPainterContext().getControl();
	}

	/**
	 * Checks if the crumbs should be painted in overlapping mode
	 *
	 * @return		<code>true</code> when overlapping crumbs
	 * 				should be drawn 
	 */
	protected boolean isOverlapping() {
		return true;
	}

	/**
	 * Retrieves the locale to use for a given content element
	 *
	 * @param		crumb Crumb element
	 * @return		Locale
	 */
	protected Locale getLocale(CrumbModel crumb) {
		if (((CrumbsDesignModel) getCtrl().getDesignModel()).size() == 0) {
			// No localization for serverside crumbs
			return null;
		} else if (crumb.getLocaleName() == null) {
			return getPainterContext().getLocale();
		} else {
			String localeName = crumb.getLocaleName();

			return PainterHelp.localeFromName(getPageContext(), localeName);
		}
	}

	/**
	 * @see com.cc.framework.ui.painter.ControlPainter#doCreateElement()
	 */
	protected ConcreteElement doCreateElement() {

		CrumbModel[] crumbs	= getCtrl().getVisibleCrumbs();

		// if the control has no crumbs -> exit
		if (crumbs.length == 0) {
			return null;
		}

		String controlId = getCtrl().getControlName();

		ElementContainer container = new ElementContainer();

		// Create a hidden field for the selected crumb
		// if the control should act as form element.
		// Otherwise the crumb id will be added as a parameter
		// to the request
		if (getCtrl().isFormElement() && (controlId != null)) {
			String selId = getCtrl().getSelectedCrumb();

			if (selId == null) {
				selId = "";
			}

			if (controlId != null) {
				container
					.addElement(new Input(Input.HIDDEN, controlId, selId)
						.setID(controlId));
			}
		}

		Table control = new Table()
			.setCellSpacing(0)
			.setCellPadding(0)
			.setBorder(0)
			.addElement(new TR()
				.addElement(doCreateCrumbs(crumbs)));

		if (getStyleId() != null) {
			control.setID(getStyleId());
		}

		if (getCtrl().getStyle() != null) {
			control.setStyle(getCtrl().getStyle());
		}

		if (getCtrl().getStyleClass() == null) {
			control.setClass(DefHtmlClass.CRUMBSCONTROL);
		} else {
			control.setClass(getCtrl().getStyleClass());
		}

		if (getCtrl().getWidth() != null) {
			control.setWidth(getCtrl().getWidth());
		}

		if (getCtrl().getSummary() != null) {
			control.setSummary(getCtrl().getSummary());
		}

		container.addElement(control);

		return container;
	}

	/**
	 * Checks if the specified crumb is selected
	 * @param	crumb	CrumbDesignModel
	 * @return	boolean
	 */
	protected boolean isSelected(CrumbModel crumb) {
		return getCtrl().isSelected(crumb);
	}

	/**
	 * Calculates the state flags for the given crumb
	 *
	 * @param		crumbs The list of all visible crumbs
	 * @param		index The crumbs index
	 * @return		State flags
	 */
	protected int getCrumbState(CrumbModel[] crumbs, int index) {
		int state = 0;

		if (crumbs[index].isDisabled()) {
			state |= FLAG_DISABLED;
		} else if (isSelected(crumbs[index])) {
			state |= FLAG_SELECTED;
		} else {
			state |= FLAG_UNSELECTED;
		}

		if (index == 0) {
			state |= FLAG_FIRST;
		}

		if (index == (crumbs.length - 1)) {
			state |= FLAG_FIRST;
		}

		return state;
	}

	/**
	 * Retrieves the style clas for the specified crumb state
	 *
	 * @param		state the crumbs state
	 * @return		Style class
	 */
	protected String getStyleClass(int state) {
		switch (state & FLAG_STATES) {
			case FLAG_SELECTED :
				return DefHtmlClass.SELECTED;
			case FLAG_UNSELECTED :
				return DefHtmlClass.UNSELECTED;
			case FLAG_DISABLED :
				return DefHtmlClass.DISABLED;
			default :
				throw new IllegalArgumentException("illegal crumb state!");
		}
	}

	/**
	 * Retrieves the background image for the specified style
	 *
	 * @param		state the crumbs state
	 * @return		Background image
	 */
	protected ImageModel getBgImage(int state) {
		switch (state & FLAG_STATES) {
			case FLAG_SELECTED :
				return getImage(DefResources.CRUMBS_SEL_BG);
			case FLAG_UNSELECTED :
				return getImage(DefResources.CRUMBS_UNSEL_BG);
			case FLAG_DISABLED :
				return getImage(DefResources.CRUMBS_DIS_BG);
			default :
				throw new IllegalArgumentException("illegal crumb state!");
		}
	}

	/**
	 * Retrieves the corner image for two adjacent crumbs
	 *
	 * @param		lstate State of the left crumb
	 * @param		rstate State of the right crumb
	 * @return		Corner image
	 */
	protected IMG createImage(int lstate, int rstate) {

		switch (lstate & FLAG_STATES) {
			case FLAG_SELECTED :
				switch (rstate & FLAG_STATES) {
					case FLAG_SELECTED :
						return createImage(DefResources.CRUMBS_SEL_SEL);
					case FLAG_UNSELECTED :
						return createImage(DefResources.CRUMBS_SEL_UNSEL);
					case FLAG_DISABLED :
						return createImage(DefResources.CRUMBS_SEL_DIS);
					default :
						return createImage(DefResources.CRUMBS_SEL_NONE);
				}

			case FLAG_UNSELECTED :
				switch (rstate & FLAG_STATES) {
					case FLAG_SELECTED :
						return createImage(DefResources.CRUMBS_UNSEL_SEL);
					case FLAG_UNSELECTED :
						return createImage(DefResources.CRUMBS_UNSEL_UNSEL);
					case FLAG_DISABLED :
						return createImage(DefResources.CRUMBS_UNSEL_DIS);
					default :
						return createImage(DefResources.CRUMBS_UNSEL_NONE);
				}

			case FLAG_DISABLED :
				switch (rstate & FLAG_STATES) {
					case FLAG_SELECTED :
						return createImage(DefResources.CRUMBS_DIS_SEL);
					case FLAG_UNSELECTED :
						return createImage(DefResources.CRUMBS_DIS_UNSEL);
					case FLAG_DISABLED :
						return createImage(DefResources.CRUMBS_DIS_DIS);
					default :
						return createImage(DefResources.CRUMBS_DIS_NONE);
				}

			default :
				switch (rstate & FLAG_STATES) {
					case FLAG_SELECTED :
						return createImage(DefResources.CRUMBS_NONE_SEL);
					case FLAG_UNSELECTED :
						return createImage(DefResources.CRUMBS_NONE_UNSEL);
					case FLAG_DISABLED :
						return createImage(DefResources.CRUMBS_NONE_DIS);
					default :
						throw new IllegalArgumentException("illegal crumbs state combination!");
				}
		}
	}

	/**
	 * This method creates the crumb element
	 *
	 * @param		face The crumbs face content
	 * @param		lstate State of the left neighbour
	 * @param		state State of the crumb
	 * @param		rstate State of the right neighbour
	 * @return		Crumbs face element
	 */
	protected ConcreteElement doCreateCrumb(
		ConcreteElement face,
		int lstate,
		int state,
		int rstate) {
		ElementContainer container = new ElementContainer();

		ImageModel bgImage = getBgImage(state);

		if (lstate == 0) {
			// There is no left neighbour
			container
				.addElement(new TD()
					.addElement(createImage(lstate, state))
					.setHeight(bgImage.getHeight()));
		}

		TD td = new TD(face);
		td.setNoWrap(true);
		td.setHeight(bgImage.getHeight());
		td.setStyle("background-image:url(" + bgImage.getSource() + ");background-repeat:repeat");
		td.setAlign(AlignType.MIDDLE);
		td.setClass(getStyleClass(state));

		container
			.addElement(td)
			.addElement(new TD()
				.addElement(createImage(state, rstate))
				.setHeight(bgImage.getHeight()));

		return container;
	}

	/**
	 * Method doCreateCrumbs
	 * @param	crumbs the crumbs list
	 * @return	ConcreteElement
	 */
	protected ConcreteElement doCreateCrumbs(CrumbModel[] crumbs) {
		ElementContainer container = new ElementContainer();

		String controlId = getCtrl().getControlName();

		int lstate	= 0;
		int state	= 0;
		int rstate	= 0;

		for (int i = 0; i < crumbs.length; i++) {

			// Calculate the states of all adjacent crumbs
			lstate				= state;
			state				= getCrumbState(crumbs, i);
			rstate				= 0;
			if ((i + 1) < crumbs.length) {
				rstate = getCrumbState(crumbs, i + 1);
			}

			// Create the crumbs face
			Locale locale		= getLocale(crumbs[i]);
			boolean filter		= (crumbs[i].filter() == null) ? getCtrl().filter() : crumbs[i].filter().booleanValue();
			ActionPainter ap	=
				createActionPainter(
					ControlActionDef.ACTION_CRUMBCLICK,
					getCtrl().getAction(crumbs[i]));

			ap.addParameter(crumbs[i].getCrumbId());
			ap.setImage(getCrumbImage(crumbs[i], state));
			ap.setLabel(html(localize(crumbs[i].getTitle(), locale), filter, getCtrl().getLabelLength()));
			ap.setClientHandler(crumbs[i]);

			if (crumbs[i].getTooltip() != null) {
				ap.setTooltip(html(localize(crumbs[i].getTooltip(), locale), filter));
			}
			ap.setActive(!crumbs[i].isDisabled());

			ClientHandler handler = null;

			if (getCtrl().isFormElement() && (controlId != null)) {
				handler = new ClientHandlerImp(crumbs[i]);

				String saveOnClick = crumbs[i].getHandler(ClientEvent.ONCLICK);

				// Script to update the form member for this tabset
				StringBuffer buf = new StringBuffer()
					.append("document.getElementById('")
					.append(controlId)
					.append("')")
					.append(".value='")
					.append(crumbs[i].getCrumbId())
					.append("';");

				if (saveOnClick != null) {
					buf.append(saveOnClick).append(";");
				}

				// Replace or set the onclick handler
				handler.setHandler(ClientEvent.ONCLICK, buf.toString());
			} else {
				handler = crumbs[i];
			}

			ap.setClientHandler(handler);

			// render the crumb
			if (isOverlapping()) {
				container.addElement(doCreateCrumb(ap.createElement(), lstate, state, rstate));
			} else {
				container.addElement(doCreateCrumb(ap.createElement(), 0, state, 0));
			}
		}

		return container;
	}

	/**
	 * Returns the Image of a crumb
	 * 
	 * @param	 	crumb CrumbDesignModel
	 * @param		state the selection state of the cumb
	 * @return	 	ImageModel
	 */
	protected ImageModel getCrumbImage(CrumbModel crumb, int state) {

		// render item as image
		String imageref = crumb.getImageRef();

		if (imageref == null) {
			return null;
		}

		ImageMap imageMap = getCtrl().getImageMap();

		if (imageMap == null) {
			return null;
		}

		imageref += ((state & FLAG_STATES) == FLAG_SELECTED) ? ".sel" : ".unsel";

		return imageMap.mapValueToImage(imageref);
	}
}
