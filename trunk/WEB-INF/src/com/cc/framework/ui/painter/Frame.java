/**
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/Frame.java,v 1.4 2005/07/08 14:16:31 P001002 Exp $
 * $Revision: 1.4 $
 * $Date: 2005/07/08 14:16:31 $
 *
 * ====================================================================
 *
 * Copyright (c) 2002 SCC Informationssysteme GmbH. All rights
 * reserved.
 *
 * ====================================================================
 */

package com.cc.framework.ui.painter;

import java.util.Enumeration;

import org.apache.ecs.Element;
import org.apache.ecs.ElementContainer;
import org.apache.ecs.html.TD;
import org.apache.ecs.html.TR;
import org.apache.ecs.html.Table;

/**
 * A Frame is a HTML table with a designated Body
 * Cell
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.4 $
 */
public class Frame extends Table {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -8182169950830123419L;

	/**
	 * A frame can have multiple sections. A frame has at
	 * least one section and a section can have its own title.
	 */
	private ElementContainer sections = null;

	/**
	 * The body element of the Frame. The body element
	 * belongs to the current section.
	 */
	private Table body = this;

	/**
	 * Constructor
	 */
	public Frame() {
		super();

		setCellSpacing(0);
		setCellPadding(0);
		setBorder(0);
	}

	/**
	 * Sets the frames section container
	 * 
	 * @param		container Section container
	 * @return		Frame
	 */
	public Frame setSectionContainer(ElementContainer container) {
		this.sections = container;
		
		return this;
	}

	/**
	 * Sets the body table.
	 * 
	 * @param		body sets the frames body element
	 * @return		Frame
	 */
	public Frame setBodyContainer(Table body) {
		this.body = body;

		return this;
	}

	/**
	 * Adds a new section to the section container of the
	 * frame.
	 * 
	 * @param		element Section element
	 * @return		Frame
	 */
	public Frame addSection(Element element) {
		if (element == null) {
			return this;
		}
		
		if (sections != null) {
			// Frame has a section container
			sections.addElement(element);
		} else {
			// This frame has no section container assigned
			// so add the element to the body container
			addBodyElement(element);
		}

		return this;
	}

	/**
	 * Adds an element to the frames body region.
	 * A new row is created for the element
	 * 
	 * @param		element The element to add
	 * @return		Frame
	 */
	public Frame addBodyElement(Element element) {
		if ((element == null) || (body == null)) {
			// No element or no body container
			return this;
		}

		if (element.getClass().equals(ElementContainer.class)) {
			Enumeration e = ((ElementContainer) element).elements();
			while (e.hasMoreElements()) {
				addBodyElement((Element) e.nextElement());
			}
		} else if (element instanceof TR) {
			body.addElement(element);
		} else {
			body.addElement(new TR().addElement(new TD(element)));
		}

		return this;
	}
}