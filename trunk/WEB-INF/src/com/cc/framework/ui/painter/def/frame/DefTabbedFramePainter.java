/**
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/def/frame/DefTabbedFramePainter.java,v 1.4 2005/07/04 14:23:02 P001002 Exp $
 * $Revision: 1.4 $
 * $Date: 2005/07/04 14:23:02 $
 *
 * ====================================================================
 *
 * Copyright (c) 2002 SCC Informationssysteme GmbH. All rights
 * reserved.
 *
 * ====================================================================
 */

package com.cc.framework.ui.painter.def.frame;

import org.apache.ecs.AlignType;
import org.apache.ecs.ConcreteElement;
import org.apache.ecs.ElementContainer;
import org.apache.ecs.html.Div;
import org.apache.ecs.html.IMG;
import org.apache.ecs.html.Span;
import org.apache.ecs.html.TBody;
import org.apache.ecs.html.TD;
import org.apache.ecs.html.TR;
import org.apache.ecs.html.Table;

import com.cc.framework.ui.Color;
import com.cc.framework.ui.control.ControlActionDef;
import com.cc.framework.ui.control.TabbarControl;
import com.cc.framework.ui.model.ClientEvent;
import com.cc.framework.ui.model.ClientHandler;
import com.cc.framework.ui.model.FrameTitle;
import com.cc.framework.ui.model.ImageModel;
import com.cc.framework.ui.model.TabDesignModel;
import com.cc.framework.ui.model.imp.ClientHandlerImp;
import com.cc.framework.ui.painter.ActionPainter;
import com.cc.framework.ui.painter.Frame;
import com.cc.framework.ui.painter.def.DefClassType;
import com.cc.framework.ui.painter.def.DefHtmlClass;
import com.cc.framework.ui.painter.def.DefResources;
import com.cc.framework.ui.painter.def.DefTabbarPainter;

/**
 * Painter for a tabbed frame
 *
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.4 $
 */
public class DefTabbedFramePainter extends DefFramePainterBase {

	/** Tab State Mask */
	protected static final int FLAG_STATES				= 0x000F;

	/** Tab is unselected */
	protected static final int FLAG_UNSELECTED			= 0x0001;

	/** Tab is selected */
	protected static final int FLAG_SELECTED			= 0x0002;

	/** Tab is disabled */
	protected static final int FLAG_DISABLED			= 0x0003;

	/** This flag is set for the first visible tab */
	protected static final int FLAG_FIRST				= 0x1000;

	/** This flag is set for the last visible tab */
	protected static final int FLAG_LAST				= 0x2000;

	/**
	 * This flag indicates if tabs should be drawn
	 * with overlapping
	 */
	private boolean overlapping = true;

	/**
	 * Creates a new FramePainter
	 * 
	 * @param		overlapping This flag indicates if tabs
	 * 				should be drawn with overlapping
	 */
	public DefTabbedFramePainter(boolean overlapping) {
		super();

		this.overlapping = overlapping;
	}

	/**
	 * Retrieves the control for this painter
	 *
	 * @return		Control
	 */
	protected TabbarControl getCtrl() {
		return (TabbarControl) getPainterContext().getControl();
	}

	/**
	 * Checks if the specified tab is selected
	 *
	 * @param		tab	the tab to check
	 * @return		<code>true</code> if the tab is selected
	 */
	protected boolean isSelected(TabDesignModel tab) {
		return tab.getTabId().equals(getCtrl().getSelectedTab());
	}

	/**
	 * Checks if the tabs should be painted in overlapping mode
	 *
	 * @return		<code>true</code> when overlapping tabs
	 * 				should be drawn 
	 */
	public boolean isOverlapping() {
		return overlapping;
	}

	/**
	 * Retrieves the Background color of the tabset
	 *
	 * @return		Background color
	 */
	protected Color getBgColor() {
		return ((DefTabbarPainter) getControlPainter()).getBgColor();
	}

	/**
	 * Returns the optional image for the given tab
	 *
	 * @param	 	tab the tab
	 * @return 		ImageModel
	 */
	protected ImageModel getTabImage(TabDesignModel tab) {
		return ((DefTabbarPainter) getControlPainter()).getTabImage(tab);
	}

	/**
	 * Calculates the state flags for the given tab
	 *
	 * @param		tabs The list of all visible Tabs
	 * @param		index The tabs index
	 * @return		State flags
	 */
	protected int getTabState(TabDesignModel[] tabs, int index) {
		int state = 0;

		if (!tabs[index].isEnabled()) {
			state |= FLAG_DISABLED;
		} else if (isSelected(tabs[index])) {
			state |= FLAG_SELECTED;
		} else {
			state |= FLAG_UNSELECTED;
		}

		if (index == 0) {
			state |= FLAG_FIRST;
		}

		if (index == (tabs.length - 1)) {
			state |= FLAG_FIRST;
		}

		return state;
	}

	/**
	 * Retrieves the style clas for the specified tab state
	 *
	 * @param		state the tabs state
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
				throw new IllegalArgumentException("illegal tab state!");
		}
	}

	/**
	 * Retrieves the background image for the specified style
	 *
	 * @param		state the tabs state
	 * @param		bgcolor the tabsets background color
	 * @return		Background image
	 */
	protected ImageModel getBgImage(int state, Color bgcolor) {
		switch (state & FLAG_STATES) {
			case FLAG_SELECTED :
				return getImage(DefResources.TABSET_SEL_BG, bgcolor);
			case FLAG_UNSELECTED :
				return getImage(DefResources.TABSET_UNSEL_BG, bgcolor);
			case FLAG_DISABLED :
				return getImage(DefResources.TABSET_DIS_BG, bgcolor);
			default :
				throw new IllegalArgumentException("illegal tab state!");
		}
	}

	/**
	 * Retrieves the corner image for two adjacent tabs
	 *
	 * @param		lstate State of the left tab
	 * @param		rstate State of the right tab
	 * @param		bgcolor the tabsets background color
	 * @return		Corner image
	 */
	protected IMG createImage(int lstate, int rstate, Color bgcolor) {

		switch (lstate & FLAG_STATES) {
			case FLAG_SELECTED :
				switch (rstate & FLAG_STATES) {
					case FLAG_SELECTED :
						throw new IllegalArgumentException("illegal tab state combination!");
					case FLAG_UNSELECTED :
						return createImage(DefResources.TABSET_SEL_UNSEL, bgcolor);
					case FLAG_DISABLED :
						return createImage(DefResources.TABSET_SEL_DIS, bgcolor);
					default :
						return createImage(DefResources.TABSET_SEL_NONE, bgcolor);
				}

			case FLAG_UNSELECTED :
				switch (rstate & FLAG_STATES) {
					case FLAG_SELECTED :
						return createImage(DefResources.TABSET_UNSEL_SEL, bgcolor);
					case FLAG_UNSELECTED :
						return createImage(DefResources.TABSET_UNSEL_UNSEL, bgcolor);
					case FLAG_DISABLED :
						return createImage(DefResources.TABSET_UNSEL_DIS, bgcolor);
					default :
						return createImage(DefResources.TABSET_UNSEL_NONE, bgcolor);
				}

			case FLAG_DISABLED :
				switch (rstate & FLAG_STATES) {
					case FLAG_SELECTED :
						return createImage(DefResources.TABSET_DIS_SEL, bgcolor);
					case FLAG_UNSELECTED :
						return createImage(DefResources.TABSET_DIS_UNSEL, bgcolor);
					case FLAG_DISABLED :
						return createImage(DefResources.TABSET_DIS_DIS, bgcolor);
					default :
						return createImage(DefResources.TABSET_DIS_NONE, bgcolor);
				}

			default :
				switch (rstate & FLAG_STATES) {
					case FLAG_SELECTED :
						return createImage(DefResources.TABSET_NONE_SEL, bgcolor);
					case FLAG_UNSELECTED :
						return createImage(DefResources.TABSET_NONE_UNSEL, bgcolor);
					case FLAG_DISABLED :
						return createImage(DefResources.TABSET_NONE_DIS, bgcolor);
					default :
						throw new IllegalArgumentException("illegal tab state combination!");
				}
		}
	}

	/**
	 * This method creates the tab face
	 *
	 * @param		face The tabs face content
	 * @param		lstate State of the left neighbour
	 * @param		state State of the tab
	 * @param		rstate State of the right neighbour
	 * @return		Tabs face element
	 */
	protected ConcreteElement doCreateTab(
		ConcreteElement face,
		int lstate,
		int state,
		int rstate) {
		ElementContainer container = new ElementContainer();

		Color bgcolor		= getBgColor();
		ImageModel bgImage	= getBgImage(state, bgcolor);

		if (lstate == 0) {
			// There is no left neighbour
			container
				.addElement(new TD()
					.addElement(createImage(lstate, state, bgcolor))
					.setHeight(bgImage.getHeight()));
		}

		TD td = new TD(face);
		td.setNoWrap(true);
		td.setHeight(bgImage.getHeight());
		td.setStyle("background-image:url(" + bgImage.getSource() + ");background-repeat:repeat");
		td.setClass(getStyleClass(state));

		container
			.addElement(td)
			.addElement(new TD()
				.addElement(createImage(state, rstate, bgcolor))
				.setHeight(bgImage.getHeight()));

		return container;
	}

	/**
	 * Creates the scroll left Button
	 * 
	 * @param		scrollTo The tab to scroll to
	 * @return		ConcreteElement
	 */
	protected ConcreteElement doCreatePrevButton(TabDesignModel scrollTo) {
		if (scrollTo == null) {
			return createImage(DefResources.BUTTON_TABSET_PREVIOUS_2);
		} else {
			// create button
			ActionPainter ap = createActionPainter(ControlActionDef.ACTION_TABSCROLL);

			ap.addParameter(scrollTo.getTabId());
			ap.setImage(getImage(DefResources.BUTTON_TABSET_PREVIOUS_1));

			return ap.createElement();
		}
	}

	/**
	 * Creates the scroll left Button
	 * 
	 * @param		scrollTo The tab to scroll to
	 * @return		ConcreteElement
	 */
	protected ConcreteElement doCreateNextButton(TabDesignModel scrollTo) {
		if (scrollTo == null) {
			return createImage(DefResources.BUTTON_TABSET_NEXT_2);
		} else {
			// create button
			ActionPainter ap = createActionPainter(ControlActionDef.ACTION_TABSCROLL);

			ap.addParameter(scrollTo.getTabId());
			ap.setImage(getImage(DefResources.BUTTON_TABSET_NEXT_1));

			return ap.createElement();
		}
	}

	/**
	 * Method createTabs
	 *
	 * @return		ConcreteElement
	 */
	protected ConcreteElement doCreateTabs() {
		TabDesignModel[] tabs = getCtrl().getVisibleTabs();

		int startIndex	= 0;
		int stopIndex	= tabs.length - 1;

		// skip tabs that are scrolled out of the view area
		String first = getCtrl().getFirstVisibleTab();
		if (first != null) {
			while ((startIndex < tabs.length) && !first.equals(tabs[startIndex].getTabId())) {
				++startIndex;
			}
		}

		if (getCtrl().getMaxVisible() != -1) {
			stopIndex = Math.min(stopIndex, startIndex + getCtrl().getMaxVisible() - 1);
		}

		// Add scrolling buttons when necessary
		if ((startIndex > 0) || (stopIndex < tabs.length - 1)) {
			ElementContainer container = new ElementContainer();

			TabDesignModel prevTab = (startIndex > 0) ? tabs[startIndex - 1] : null;
			TabDesignModel nextTab = (stopIndex < tabs.length - 1) ? tabs[startIndex + 1] : null;

			container.addElement(new TD(doCreatePrevButton(prevTab)));
			container.addElement(new TD(doCreateNextButton(nextTab))
				.setStyle("padding-left: 1px;"));

			if (startIndex > 0) {
				container.addElement(new TD(createImage(DefResources.BUTTON_TABSET_MORE_PREVIOUS))
					.setClass("tabScrollBtnL"));
			} else {
				container.addElement(new TD(createImage(DefResources.BUTTON_TABSET_MORE_EMPTY)));
			}

			container.addElement(doCreateTabs(tabs, startIndex, stopIndex));

			if (stopIndex < tabs.length - 1) {
				container.addElement(new TD(createImage(DefResources.BUTTON_TABSET_MORE_NEXT))
					.setClass("tabScrollBtnR"));
			}

			container.addElement(new TD()
				.setNoWrap(true)
				.addElement(
					new Span(
						getFrameworkString(DefResources.FW_TABSET_RANGE,
						new Object[] {
							new Integer(startIndex + 1),
							new Integer(stopIndex + 1),
							new Integer(tabs.length)}))
					.setClass("tabDetail")));

			return container;
		} else {
			return doCreateTabs(tabs, startIndex, stopIndex);
		}
	}

	/**
	 * Method createTabs
	 *
	 * @param		tabs Collection with all Tabs
	 * @param		startIndex Index of the first visible Tab
	 * @param		stopIndex Index of the last visible Tab
	 * @return		ConcreteElement
	 */
	protected ConcreteElement doCreateTabs(TabDesignModel[] tabs, int startIndex, int stopIndex) {
		ElementContainer container = new ElementContainer();

		int lstate		= 0;
		int state		= 0;

		for (int i = startIndex; i <= stopIndex; i++) { 
			ActionPainter ap =
				createActionPainter(
					ControlActionDef.ACTION_TABCLICK,
					getCtrl().getAction(tabs[i]));

			ap.addParameter(tabs[i].getTabId());
			ap.setActive(tabs[i].isEnabled());
			ap.setImage(getTabImage(tabs[i]));
			ap.setLabel(html(localize(tabs[i].getTitle()), getCtrl().filter(), getCtrl().getLabelLength()));

			if (tabs[i].getTooltip() != null) {
				ap.setTooltip(html(localize(tabs[i].getTooltip()), getCtrl().filter()));
			}

			ClientHandler handler = null;

			if (getCtrl().isFormElement()) {
				handler = new ClientHandlerImp(tabs[i]);

				String saveOnClick = tabs[i].getHandler(ClientEvent.ONCLICK);

				// Script to update the form member for this tabset
				StringBuffer buf = new StringBuffer()
					.append("document.getElementById('")
					.append(getCtrl().getControlName())
					.append("')")
					.append(".value='")
					.append(tabs[i].getTabId())
					.append("';");

				if (saveOnClick != null) {
					buf.append(saveOnClick).append(";");
				}

				// Replace or set the onclick handler
				handler.setHandler(ClientEvent.ONCLICK, buf.toString());
			} else {
				handler = tabs[i];
			}

			ap.setClientHandler(handler);

			// Calculate the Tabs state
			lstate	= state;
			state	= getTabState(tabs, i);

			// render the tabs
			if (isOverlapping()) {
				int rstate	= 0;

				if ((i + 1) < tabs.length) {
					rstate = getTabState(tabs, i + 1);
				}

				container.addElement(doCreateTab(ap.createElement(), lstate, state, rstate));
			} else {
				container.addElement(doCreateTab(ap.createElement(), 0, state, 0));
			}
		}

		return container;
	}

	/**
	 * Creates the body element of the frame
	 *
	 * @return		Body Table element
	 */
	protected Table createBody() {
		return (Table)
			new Table()
				.setCellSpacing(0)
				.setCellPadding(10)
				.setWidth("100%")
				.setBorder(0)
				.setClass(getElementClass(DefClassType.CLASS_BODY));
	}

	/**
	 * @see com.cc.framework.ui.painter.FramePainter#createFrame(boolean)
	 */
	public Frame createFrame(boolean showFrame) {
		ImageModel bg = getImage(DefResources.TABSET_BACKGROUND);

		ElementContainer sections = new ElementContainer();

		Frame frame = (Frame) new Frame()
			.setCellSpacing(0)
			.setCellPadding(0)
			.setBorder(0)
			.addElement(new TR()
				.addElement(new TD()
					.addElement(new Div()
						.addElement(new Table()
							.setCellSpacing(0)
							.setBorder(0)
							.setCellPadding(0)
							.addElement(new TR()
								.addElement(doCreateTabs()))
							.setClass(getElementClass(DefClassType.CLASS_CONTROL)))
						.setID("tabset_" + 	getCtrl().getControlName()))
					.setHeight(bg.getHeight())
					.setStyle("background-image:url(" + bg.getSource() + ");background-repeat:repeat")))
			.addElement(sections);

		if (showFrame) {
			Table body = createBody();

			sections
				.addElement(new TBody()
					.addElement(new TR()
						.addElement(new TD()
							.addElement(body))
						.setVAlign(AlignType.TOP)));

			frame.setBodyContainer(body);
		}

		frame.setSectionContainer(sections);

		return frame;
	}

	/**
	 * @see com.cc.framework.ui.painter.FramePainter#createFrame(com.cc.framework.ui.model.FrameTitle, boolean)
	 */
	public Frame createFrame(FrameTitle title, boolean showFrame) {
		// A tabset has no title
		return createFrame(showFrame);
	}

	/**
	 * @see com.cc.framework.ui.painter.FramePainter#createInnerFrame(boolean)
	 */
	public Frame createInnerFrame(boolean showFrame) {
		return new Frame();
	}

	/**
	 * @see com.cc.framework.ui.painter.FramePainter#createTitle(com.cc.framework.ui.model.FrameTitle, boolean)
	 */
	public ConcreteElement createTitle(FrameTitle title, boolean showFrame) {
		return null;
	}

	/**
	 * @see com.cc.framework.ui.painter.FramePainter#addSection(com.cc.framework.ui.painter.Frame, com.cc.framework.ui.model.FrameTitle, boolean)
	 */
	public void addSection(Frame frame, FrameTitle title, boolean showFrame) {
		Table sectionBody = createBody();

		frame.addSection(new TBody()
			.addElement(new TR()
				.addElement(new TD()
					.addElement(createTitle(title, showFrame))))
			.addElement(new TR()
				.addElement(new TD()
					.addElement(sectionBody))));

		frame.setBodyContainer(sectionBody);
	}
}