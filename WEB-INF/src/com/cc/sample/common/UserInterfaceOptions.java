/**
 * $Header: d:/repository/cvs/cc-samples-ext-2/source/com/cc/sample/common/UserInterfaceOptions.java,v 1.3 2005/07/18 16:13:01 P001002 Exp $
 * $Revision: 1.3 $
 * $Date: 2005/07/18 16:13:01 $
 *
 * ==========================================================================
 *
 * Copyright (c) 2003 SCC Informationssysteme GmbH. All rights reserved.
 * www.scc-gmbh.com
 *
 * ==========================================================================
 */

package com.cc.sample.common;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Vector;

import com.cc.framework.common.InvalidEnumType;
import com.cc.framework.ui.model.OptionListDataModel;
import com.cc.framework.ui.painter.ColorPalette;
import com.cc.framework.ui.painter.PainterFactory;
import com.cc.framework.ui.painter.def.DefPainterFactory;
import com.cc.framework.ui.painter.def2.Def2PainterFactory;
import com.cc.framework.util.StringHelp;
import com.cc.sample.presentation.painter.ambiente.AppDef2ColorPalette;
import com.cc.sample.presentation.painter.ambiente.AppDefColorPalette;

/**
 * Enumeration of possible user interfaces
 * 
 * @author     <a href="mailto:gschulz@scc-gmbh.com">Gernot Schulz</a>
 * @version    $Revision: 1.3 $
 */
public class UserInterfaceOptions implements OptionListDataModel, Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 5968873438142521144L;

	/**
	 * PainterFactory Registration
	 */
	private static class UserInterfaceRegistration {

		/**
		 * Userfriendly Display value
		 */
		private String label = "";

		/**
		 * The corresponding PainterFactory for this GUI type
		 */
		private PainterFactory factory = null;

		/**
		 * The Color palette
		 */
		private ColorPalette palette = null;

		/**
		 * Constructor
		 *  
		 * @param		factory The Painter Factory
		 * @param		palette The color palette
		 * @param		label The userfriendly name of the Painter Factory
		 */
		public UserInterfaceRegistration(
			PainterFactory factory,
			ColorPalette palette,
			String label) {
			super();
			
			this.factory	= factory;
			this.palette	= palette;
			this.label		= label;
		}

		/**
		 * Compares this object to the specified object.
		 * 
		 * @param	obj		The object to compare this <code>UserInterface</code> against.
		 * @return  <code>true</code> if the <code>UserInterface</code>are equal;
		 *          <code>false</code> otherwise.
		 */
		public boolean equals(Object obj) {

			if (this == obj) {
				return true;
			} else if (obj instanceof UserInterfaceRegistration) {
				return factory.equals(((UserInterfaceRegistration) obj).factory);
			} else if (obj instanceof String) {
				return factory.getFactoryId().equals(obj);
			}

			return false;
		}

		/**
		 * @return		Returns the PainterFactory
		 */
		public PainterFactory getPainterFactory() {
			return factory;
		}

		/**
		 * @return		Returns the color palette
		 */
		public ColorPalette getColorPalette() {
			return palette;
		}

		/**
		 * @return		Returns the userfriendly name
		 */
		public String getLabel() {
			return StringHelp.strcat(label, " (", getKey(), ")");
		}

		/**
		 * @return		Returns the factories identifier
		 */
		public String getKey() {
			return factory.getFactoryId();
		}
	}

	/**
	 * The registered painter factories
	 */
	private static Vector factories = new Vector();

	/**
	 * Registration of default painter factories
	 */
	static {
		register(
			DefPainterFactory.instance(),
			new AppDefColorPalette(),
			"Default Painter");

		register(
			Def2PainterFactory.instance(),
			new AppDef2ColorPalette(),
			"Default Painter 2");
	}

	// ----------------------------
	//      methods
	// ----------------------------

	/**
	 * Constructor
	 */
	public UserInterfaceOptions() {
		super();
	}

	/**
	 * Registers a new Painter Factory Option
	 *  
	 * @param		factory The Painter Factory
	 * @param		palette The color palette
	 * @param		label The userfriendly name of the Painter Factory
	 */
	public static void register(
		PainterFactory factory,
		ColorPalette palette,
		String label) {
		factories.add(new UserInterfaceRegistration(factory, palette, label));
	}

	/**
	 * Retrieves the painter factory for a given factory id
	 *
	 * @param		id The PainterFactory Id
	 * @return		the PainterFactory instance
	 * @throws		InvalidEnumType if the argument is not matched
	 */
	public static PainterFactory getFactory(String id) {

		Iterator i = factories.iterator();
		while (i.hasNext()) {
			UserInterfaceRegistration ui = (UserInterfaceRegistration) i.next();

			if (ui.equals(id)) {
				return ui.getPainterFactory();
			}
		}

		throw new InvalidEnumType("Invalid UserInterface: " + id);
	}

	/**
	 * Retrieves the color palette for a given factory id
	 *
	 * @param		id The PainterFactory Id
	 * @return		the color palette
	 * @throws		InvalidEnumType if the argument is not matched
	 */
	public static ColorPalette getPalette(String id) {

		Iterator i = factories.iterator();
		while (i.hasNext()) {
			UserInterfaceRegistration ui = (UserInterfaceRegistration) i.next();

			if (ui.equals(id)) {
				return ui.getColorPalette();
			}
		}

		throw new InvalidEnumType("Invalid UserInterface: " + id);
	}

	/**
	 * @see com.cc.framework.ui.model.OptionListDataModel#getKey(int)
	 */
	public Object getKey(int index) {
		return ((UserInterfaceRegistration) factories.get(index)).getKey();
	}

	/**
	 * @see com.cc.framework.ui.model.OptionListDataModel#getTooltip(int)
	 */
	public String getTooltip(int index) {
		return null;
	}

	/**
	 * @see com.cc.framework.ui.model.OptionListDataModel#getValue(int)
	 */
	public Object getValue(int index) {
		return ((UserInterfaceRegistration) factories.get(index)).getLabel();
	}

	/**
	 * @see com.cc.framework.ui.model.OptionListDataModel#size()
	 */
	public int size() {
		return factories.size();
	}
}
