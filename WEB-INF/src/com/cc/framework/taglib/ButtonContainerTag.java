package com.cc.framework.taglib;

import com.cc.framework.ui.control.Control;

/**
 * Interface for all tags that support nested buttons
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision$
 * @since      1.6
 */
public interface ButtonContainerTag {

	/**
	 * Appends a button to the control
	 * 
	 * @param		button The button to append
	 */
	public void addButton(Control button);
}