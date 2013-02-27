/**
 * $Header: d:/repository/cvs/cc-samples-ext-2/source/com/cc/sample/presentation/painter/AppResourceMap.java,v 1.2 2005/07/18 16:13:06 P001002 Exp $
 * $Revision: 1.2 $
 * $Date: 2005/07/18 16:13:06 $
 *
 * ====================================================================
 *
 * Copyright (c) 2002 SCC Informationssysteme GmbH. All rights
 * reserved.
 *
 * ====================================================================
 */

package com.cc.sample.presentation.painter;

import com.cc.framework.ui.Color;
import com.cc.framework.ui.model.imp.ImageModelImp;
import com.cc.framework.ui.painter.ResourceMapImp;

/**
 * This ResourceMap manages some resources for our application
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.2 $
 */
public class AppResourceMap extends ResourceMapImp {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -1722120168290379906L;

	/**
	 * Constructor
	 */
	public AppResourceMap() {
		super();
		
		init();
	}

	/**
	 * @see com.cc.framework.ui.painter.ResourceMapImp#doRegisterColors()
	 */
	protected void doRegisterColors() {
		// We will use a color palette. So no action
		// is required here
	}

	/**
	 * @see com.cc.framework.ui.painter.ResourceMapImp#doRegisterImages()
	 */
	protected void doRegisterImages() {
		// Register some images for the button example 841
		registerImage(AppResources.IMAGE_TBTN_BG_LEFT2,		new ImageModelImp("app/images/buttons/btnBkg2_left.gif",		13, 25));
		registerImage(AppResources.IMAGE_TBTN_BG_MIDDLE2,	new ImageModelImp("app/images/buttons/btnBkg2_middle.gif",		160, 25));
		registerImage(AppResources.IMAGE_TBTN_BG_RIGHT2,	new ImageModelImp("app/images/buttons/btnBkg2_right.gif",		13, 25));
		registerImage(AppResources.IMAGE_TBTN_BG_LEFT3,		new ImageModelImp("app/images/buttons/btnBkg3_left.gif",		7, 24));
		registerImage(AppResources.IMAGE_TBTN_BG_MIDDLE3,	new ImageModelImp("app/images/buttons/btnBkg3_middle.gif",		160, 24));
		registerImage(AppResources.IMAGE_TBTN_BG_RIGHT3,	new ImageModelImp("app/images/buttons/btnBkg3_right.gif",		7, 24));
		registerImage(AppResources.IMAGE_TBTN_BG_LEFT4,		new ImageModelImp("app/images/buttons/btnBkg4_left.gif",		14, 27));
		registerImage(AppResources.IMAGE_TBTN_BG_MIDDLE4,	new ImageModelImp("app/images/buttons/btnBkg4_middle.gif",		120, 27));
		registerImage(AppResources.IMAGE_TBTN_BG_RIGHT4,	new ImageModelImp("app/images/buttons/btnBkg4_right.gif",		14, 27));
	}

	/**
	 * @see com.cc.framework.ui.painter.ResourceMapImp#doRegisterStrings()
	 */
	protected void doRegisterStrings() {
		// Register Cascading Stylesheets
		registerString(AppResources.STRING_FILE_CSS_DEFAULT, "app/css/default.css");
	}

	/**
	 * @see com.cc.framework.ui.painter.ResourceMap#getString(java.lang.String, boolean)
	 */
	public String getString(String resourceId, boolean returnNull) {
		String res = super.getString(resourceId, returnNull);
		
		if (res == null) {
			Color color = getColor(resourceId);

			if (color != null) {
				res = color.toHtml();
			}
		}

		return res;
	}

}