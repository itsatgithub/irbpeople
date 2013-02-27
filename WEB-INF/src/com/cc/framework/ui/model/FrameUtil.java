/**
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/FrameUtil.java,v 1.3 2005/07/08 15:15:33 P001002 Exp $
 * $Revision: 1.3 $
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

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import com.cc.framework.security.Principal;

/**
 * Utility Class for Frames
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.3 $
 */
public abstract class FrameUtil {

	/**
	 * Protected constructor
	 */
	protected FrameUtil() {
	}

	/**
	 * Retrieves a list of only the inner frames
	 * that are matching the given layout hint
	 * 
	 * @param		frameList The Frame List
	 * @param		principal The principal object for permisssion
	 * 				tests. This parameter can be <code>null</code> 
	 * @param		layoutHint The layout hint that specifies
	 * 				what frames should be selected:
	 * 				<code>AlignmentType.TOP</code> - header frames
	 * 				<code>AlignmentType.BOTTOM</code> - footer frames
	 * @return		Frame list
	 */
	public static InnerFrame[] filter(Collection frameList, Principal principal, Object layoutHint) {
		if ((frameList == null) || (frameList.size() == 0)) {
			return null;
		} else {
			Vector selected = new Vector();
	
			Iterator i = frameList.iterator();
			while (i.hasNext()) {
				InnerFrame frame = (InnerFrame) i.next();
	
				if (((layoutHint == null) && (frame.getLayoutHint() == null))
					|| ((layoutHint != null) && layoutHint.equals(frame.getLayoutHint()))) {

					// Check user permissions to view this inner frame
					if (frame.show(principal)) {
						selected.add(frame);
					}
				}
			}
	
			if (selected == null) {
				return null;
			} else {
				return (InnerFrame[]) selected.toArray(new InnerFrame[selected.size()]);
			}
		}
	}
}