/**
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/def/frame/DefSearchFramePainter.java,v 1.2 2005/09/27 14:06:23 P001002 Exp $
 * $Revision: 1.2 $
 * $Date: 2005/09/27 14:06:23 $
 *
 * ====================================================================
 *
 * Copyright (c) 2002 SCC Informationssysteme GmbH. All rights
 * reserved.
 *
 * ====================================================================
 */

package com.cc.framework.ui.painter.def.frame;

import org.apache.ecs.ConcreteElement;
import org.apache.ecs.Entities;
import org.apache.ecs.html.TD;
import org.apache.ecs.html.TR;
import org.apache.ecs.html.Table;

import com.cc.framework.ui.model.FrameTitle;
import com.cc.framework.ui.painter.def.DefHtmlClass;
import com.cc.framework.ui.painter.def.DefResources;

/**
 * Concrete Frame painter
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.2 $
 */
public class DefSearchFramePainter extends DefFormFramePainter {

	/**
	 * Creates a new FramePainter
	 */
	public DefSearchFramePainter() {
		super();
	}

	/**
	 * @see com.cc.framework.ui.painter.FramePainter#createTitle(com.cc.framework.ui.model.FrameTitle, boolean)
	 */
	public ConcreteElement createTitle(FrameTitle title, boolean showFrame) {

		if (showFrame) {
			return new Table()
				.setCellPadding(0)
				.setCellSpacing(0)
				.setWidth("100%")
				.addElement(new TR()
					.addElement(new TD()
						.setHeight(3))
					.addElement(new TD(createImage(DefResources.IMAGE_MAGNIFIER))
						.setRowSpan(2))
					.addElement(new TD()
						.setHeight(3))
					.addElement(new TD()
						.setHeight(3)))
				.addElement(new TR()
					.addElement(new TD()
						.addElement(createImage(DefResources.CORNER_FORMSEARCH_LEFT)))
					.addElement(new TD()
						.addElement(Entities.NBSP)
						.addElement(html(title.getCaption(), title.filter()))
						.setClass(DefHtmlClass.FORMCONTROL_SPACER_LEFT))
					.addElement(new TD()
						.addElement(createImage(DefResources.CORNER_FORMSEARCH_RIGHT))));
		} else {
			// Frameless title
			return super.createTitle(title, showFrame);
		}
	}
}
