/*
 * $Header$
 * $Revision$
 * $Date$
 *
 * ====================================================================
 *
 * Copyright (c) 2000 - 2005 SCC Informationssysteme GmbH. All rights
 * reserved.
 * Vendor URL : http://www.scc-gmbh.com
 * Product URL: http://www.common-controls.com
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL SCC INFORMATIONSSYSTEME GMBH OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 */

package com.cc.framework.taglib.controls;

import javax.servlet.jsp.JspException;

import com.cc.framework.taglib.TagHelp;
import com.cc.framework.ui.control.Control;
import com.cc.framework.ui.control.HiddenControl;
import com.cc.framework.ui.model.ControlDesignModel;
import com.cc.framework.ui.model.HiddenDesignModel;
import com.cc.framework.ui.model.imp.HiddenDesignModelImp;

/**
 * Tag-Handler for the <code>hidden</code> Tag.
 * 
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version $Revision$
 * @since 1.6
 */
public class HiddenTag extends TextTag {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 3808198760857630922L;

	/**
	 * @see com.cc.framework.taglib.controls.BaseControlTag#doCreateDesignModel()
	 */
	protected ControlDesignModel doCreateDesignModel() {
		return new HiddenDesignModelImp();
	}

	/**
	 * Returns a casted version of the ControlDesignModel
	 * 
	 * @return PlainTextDesignModel
	 */
	protected HiddenDesignModel getHiddenDesignModel() {
		return (HiddenDesignModel) getDesignModel();
	}

	/**
	 * @see com.cc.framework.taglib.controls.BaseControlTag#doCreateControl()
	 */
	protected Control doCreateControl() throws JspException {
		// assign a new control
		HiddenControl ctrl = new HiddenControl(getHiddenDesignModel());

		// get the datamodel
		Object value = lookupBean();

		if (value != null) {
			ctrl.setValue(value);
		}

		return ctrl;
	}

	/**
	 * Constructor
	 */
	public HiddenTag() {
		super();
	}

	/**
	 * This flag indicates whether the content of the hidden field should be
	 * visible or not.
	 * 
	 * @param write
	 *            If <code>true</code> the content of the field will be
	 *            visible
	 * @throws JspException
	 *             if the argument can not be converted to a boolean value
	 */
	public void setWrite(String write) throws JspException {
		getHiddenDesignModel().setWrite(TagHelp.toBoolean(write));
	}
}