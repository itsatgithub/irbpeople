/**
 * $Header: d:/repository/cvs/cc-framework/painter/com/cc/framework/ui/painter/def2/Def2PainterFactory.java,v 1.6 2005/07/12 06:36:47 P001002 Exp $
 * $Revision: 1.6 $
 * $Date: 2005/07/12 06:36:47 $
 *
 * ====================================================================
 *
 * Copyright (c) 2000 - 2003 SCC Informationssysteme GmbH. All rights
 * reserved.
 * Vendor Site : http://www.scc-gmbh.com
 * Product Site: http://www.common-controls.com
 *
 * ====================================================================
 */

package com.cc.framework.ui.painter.def2;

import com.cc.framework.common.Singleton;
import com.cc.framework.ui.painter.PainterFactory;
import com.cc.framework.ui.painter.ResourceMap;
import com.cc.framework.ui.painter.def.DefPainterFactory;

/**
 * Factory class for creating the Def2Painter.<br>
 * The Def2Painter renders the gui similarly to the DefPainter but
 * substitute the stylesheet and some images.  
 * 
 * @author    <a href="mailto:gschulz@scc-gmbh.com">Gernot Schulz</a>
 * @version   $Revision: 1.6 $
 * @since     1.2
 */
public final class Def2PainterFactory extends DefPainterFactory implements Singleton {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -6350470474085277688L;

	/**
	 * The single instance of this class
	 */
	private static Def2PainterFactory instance = new Def2PainterFactory();

	/**
	 * Constructor
	 */
	protected Def2PainterFactory() {
		super();
	}

	/**
	 * @see com.cc.framework.ui.painter.PainterFactory#createResourceMap()
	 */
	protected ResourceMap createResourceMap() {
		return new Def2ResourceMap();
	}

	/**
	 * Returns the unique Id for this Painterfactory
	 *
	 * @return	The unique Id for this Painterfactory which is "def"
	 */
	public String getFactoryId() {
		return "def2";
	}

	/**
	 * Returns the single instance of the class (singleton)
	 *
	 * @return	The single instance  of the DefPainterFactory
	 */
	public static PainterFactory instance() {
		return instance;
	}
}