/**
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/controls/RecurrencePatternTag.java,v 1.4 2005/08/02 19:13:06 P001002 Exp $
 * $Revision: 1.4 $
 * $Date: 2005/08/02 19:13:06 $
 *
 * ====================================================================
 *
 * Copyright (c) 2002 SCC Informationssysteme GmbH. All rights
 * reserved.
 *
 * ====================================================================
 */

package com.cc.framework.taglib.controls;

import javax.servlet.jsp.JspException;

import com.cc.framework.taglib.TagHelp;
import com.cc.framework.ui.control.Control;
import com.cc.framework.ui.control.RecurrencePatternControl;
import com.cc.framework.ui.model.ControlDesignModel;
import com.cc.framework.ui.model.RecurrencePatternDesignModel;
import com.cc.framework.ui.model.imp.RecurrencePatternDesignModelImp;

/**
 * Tag-Handler for the RecurrencePattern Control
 *
 * @author      <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version     $Revision: 1.4 $
 * @since       1.0
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.4 $
 */
public class RecurrencePatternTag extends BaseControlTag {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 2371179209703688435L;

	/**
	 * Constructor
	 */
	public RecurrencePatternTag() {
		super();
	}

	/**
	 * @see com.cc.framework.taglib.controls.BaseControlTag#doCreateDesignModel()
	 */
	protected ControlDesignModel doCreateDesignModel() {
		return new RecurrencePatternDesignModelImp();
	}

	/**
	 * Returns a casted version of the ControlDesignModel
	 *
	 * @return		RecurrencePatternDesignModel
	 */
	protected RecurrencePatternDesignModel getRecurrencePatternDesignModel() {
		return (RecurrencePatternDesignModel) getDesignModel();
	}

	/**
	 * @see com.cc.framework.taglib.controls.BaseControlTag#doCreateControl()
	 */
	protected Control doCreateControl() throws JspException {
		RecurrencePatternDesignModel designModel = getRecurrencePatternDesignModel();

		// get the model
		Object value = lookupBean();

		if (!(value instanceof RecurrencePatternControl)) {
			throw new JspException("RecurrencePatternTag: invalid property class. expected class=RecurrencePatternControl!");
		}

		// assign the control
		RecurrencePatternControl ctrl = (RecurrencePatternControl) value;

		ctrl.setDesignModel(designModel);

		// assign an action if this was not done by the programmer
		if (designModel.getAction() == null) {
			designModel.setAction(getDefaultAction());
		}

		return ctrl;
	}

	/**
	 * The automatic HTML coding of the text element
	 * can be activated or disabled with the filter-attribute
	 *
	 * @param		filter <code>true</code> if the text should be HTML encoded.
	 * @throws		JspException If the Argument can't be converted	to a boolean
	 */
	public void setFilter(String filter) throws JspException {
		getRecurrencePatternDesignModel().setFilter(TagHelp.toBoolean(filter));
	}
}