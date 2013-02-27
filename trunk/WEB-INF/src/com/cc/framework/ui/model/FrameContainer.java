/**
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/FrameContainer.java,v 1.6 2005/07/08 15:15:33 P001002 Exp $
 * $Revision: 1.6 $
 * $Date: 2005/07/08 15:15:33 $
 *
 * ====================================================================
 *
 * Copyright (c) 2002 SCC Informationssysteme GmbH. All rights
 * reserved.
 *
 * ====================================================================
 */

package com.cc.framework.ui.model;

import com.cc.framework.security.Principal;


/**
 * Interface for all objects that have a frame 
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.6 $
 */
public interface FrameContainer {

	/**
	 * Adds a inner frame to the List
	 * 
	 * @param		frame the inner frame to add
	 */
	public void addInnerFrame(InnerFrame frame);

	/**
	 * Retrieves a list of only the inner frames
	 * that are matching the given layout hint
	 *
	 * @param		principal The principal object for permisssion
	 * 				tests. This parameter can be <code>null</code> 
	 * @param		layoutHint The layout hint that specifies
	 * 				what frames should be selected:
	 * 				<code>AlignmentType.TOP</code> - header frames
	 * 				<code>AlignmentType.BOTTOM</code> - footer frames
	 * @return		Frame list
	 */
	public InnerFrame[] getInnerFrames(Principal principal, Object layoutHint);

	/**
	 * Returns the image for the frames title
	 * 
	 * @return		ImageModel
	 */
	public ImageModel getImage();

	/**
	 * Sets an image for the frames title
	 * 
	 * @param		img	ImageModel
	 */
	public void setImage(ImageModel img);
}