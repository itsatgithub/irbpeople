/**
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/def/frame/DefFormFramePainter.java,v 1.8 2005/09/12 10:33:37 P001002 Exp $
 * $Revision: 1.8 $
 * $Date: 2005/09/12 10:33:37 $
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
import org.apache.ecs.Entities;
import org.apache.ecs.html.TBody;
import org.apache.ecs.html.TD;
import org.apache.ecs.html.THead;
import org.apache.ecs.html.TR;
import org.apache.ecs.html.Table;

import com.cc.framework.ui.model.FrameTitle;
import com.cc.framework.ui.painter.Frame;
import com.cc.framework.ui.painter.def.DefClassType;
import com.cc.framework.ui.painter.def.DefResources;

/**
 * Concrete Frame painter
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.8 $
 */
public class DefFormFramePainter extends DefFramePainterBase {

	/**
	 * Creates a new FramePainter
	 */
	public DefFormFramePainter() {
		super();
	}

	/**
	 * @see com.cc.framework.ui.painter.FramePainter#createFrame(boolean)
	 */
	public Frame createFrame(boolean showFrame) {
		ElementContainer sections	= new ElementContainer();
		Table sectionBody			= createBody();

		Frame frame = (Frame) new Frame()
			.addElement(sections
				.addElement(new TBody()
					.addElement(new TR()
						.addElement(new TD()
							.addElement(sectionBody)))));

		frame.setSectionContainer(sections);
		frame.setBodyContainer(sectionBody);

		return frame;
	}

	/**
	 * @see com.cc.framework.ui.painter.FramePainter#createFrame(com.cc.framework.ui.model.FrameTitle, boolean)
	 */
	public Frame createFrame(FrameTitle title, boolean showFrame) {
		ElementContainer sections	= new ElementContainer();
		Table sectionBody			= createBody();

		Frame frame = new Frame();

		if (showFrame || showButtons(title)) {
			frame
				.addElement(new THead()
					.addElement(new TR()
						.addElement(new TD()
							.addElement(createTitle(title, showFrame)))));
		}

		frame
			.addElement(sections
				.addElement(new TBody()
					.addElement(new TR()
						.addElement(new TD()
							.addElement(sectionBody)))));

		frame.setSectionContainer(sections);
		frame.setBodyContainer(sectionBody);

		return frame;
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

	/**
	 * @see com.cc.framework.ui.painter.FramePainter#createInnerFrame(boolean)
	 */
	public Frame createInnerFrame(boolean showFrame) {
		return new Frame();
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
				.setCellPadding(0)
				.setWidth("100%")
				.setClass(getElementClass(DefClassType.CLASS_BODY));
	}

	/**
	 * @see com.cc.framework.ui.painter.FramePainter#createTitle(com.cc.framework.ui.model.FrameTitle, boolean)
	 */
	public ConcreteElement createTitle(FrameTitle title, boolean showFrame) {

		if (showFrame && (title != null)) {
			TR titleRow = new TR();
			
			if (title.getImage() != null) {
				// Add an optional frame image
				titleRow.addElement(new TD()
					.addElement(createImage(title.getImage())
						.setVspace(0)
						.setAlign(AlignType.absmiddle)
						.setStyle("margin-right:3px;")));
			}

			titleRow
				.addElement(new TD(html(title.getCaption(), title.filter()))
					.setNoWrap(true)
					.setClass(getElementClass(DefClassType.CLASS_CAPTION)));

			if (title.hasDetail()) {
				titleRow
					.addElement(new TD()
						.addElement(createImage(DefResources.IMAGE_BULLET)))
					.addElement(new TD(html(title.getDetail(), title.filter()))
						.setNoWrap(true)
						.setClass(getElementClass(DefClassType.CLASS_DETAIL)));
			}

			titleRow
				.addElement(new TD(Entities.NBSP)
					.setWidth("100%"));

			ElementContainer bottonContainer = createButtonList(title);
			if (bottonContainer != null) {
				titleRow.addElement(bottonContainer);
			}

			return (Table) new Table()
				.setCellPadding(0)
				.setCellSpacing(0)
				.setBorder(0)
				.addElement(
					new TR()
						.addElement(new TD()
							.setVAlign(AlignType.top)
							.addElement(createImage(DefResources.CORNER_FORM_LEFT)))
						.addElement(new TD()
							.setWidth("100%")
							.addElement(new Table()
								.setCellPadding(0)
								.setCellSpacing(3)
								.setWidth("100%")
								.addElement(titleRow)))
						.addElement(new TD()
							.setVAlign(AlignType.top)
							.addElement(createImage(DefResources.CORNER_FORM_RIGHT))))
				.setClass(getElementClass(DefClassType.CLASS_HEADER));
		} else {
			// Frameless title
			return (Table) new Table()
				.setCellPadding(0)
				.setCellSpacing(3)
				.addElement(new TR()
					.setVAlign(AlignType.top)
					.addElement(new TD(Entities.NBSP)
						.setWidth("100%"))
					.addElement(createButtonList(title)))
				.setClass(getElementClass(DefClassType.CLASS_HEADER));
		}
	}
}
