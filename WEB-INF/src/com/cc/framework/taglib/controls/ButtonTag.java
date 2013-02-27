/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/controls/ButtonTag.java,v 1.21 2005/09/27 14:06:23 P001002 Exp $
 * $Revision: 1.21 $
 * $Date: 2005/09/27 14:06:23 $
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

import com.cc.framework.taglib.ButtonContainerTag;
import com.cc.framework.taglib.TagHelp;
import com.cc.framework.taglib.WebResourceTag;
import com.cc.framework.ui.control.ButtonControl;
import com.cc.framework.ui.control.Control;
import com.cc.framework.ui.model.ButtonDesignModel;
import com.cc.framework.ui.model.ControlDesignModel;
import com.cc.framework.ui.model.imp.ButtonDesignModelImp;

/**
 * Tag-Handler for the Button Control
 * 
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version $Revision: 1.21 $
 * @since 1.2
 */
public class ButtonTag extends BaseControlTag implements WebResourceTag {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 2859974649116128738L;

	// ---------------------------
	// methods
	// ---------------------------

	/**
	 * Constructor
	 */
	public ButtonTag() {
		super();
	}

	/**
	 * @see com.cc.framework.taglib.controls.BaseControlTag#doCreateDesignModel()
	 */
	protected ControlDesignModel doCreateDesignModel() {
		return new ButtonDesignModelImp();
	}

	/**
	 * Returns a casted version of the ControlDesignModel
	 * 
	 * @return ButtonDesignModel
	 */
	protected ButtonDesignModel getButtonDesignModel() {
		return (ButtonDesignModel) getDesignModel();
	}

	/**
	 * @see com.cc.framework.taglib.controls.BaseControlTag#doCreateControl()
	 */
	protected Control doCreateControl() throws JspException {
		ButtonControl ctrl = new ButtonControl();
		ctrl.setDesignModel(getButtonDesignModel());

		return ctrl;
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException {
		if ((getParent() != null)
				&& (getParent() instanceof ButtonContainerTag)) {
			ButtonContainerTag parent = (ButtonContainerTag) getParent();

			parent.addButton(createControl());
		} else {
			super.doStartTag();
		}

		// this tag has no body
		return SKIP_BODY;
	}

	/**
	 * Sets the src.
	 * 
	 * @param src
	 *            The src to set
	 */
	public void setSrc(String src) {
		getButtonDesignModel().setSrc(src);
	}

	/**
	 * Sets the text.
	 * 
	 * @param text
	 *            The text to set
	 */
	public void setText(String text) {
		getButtonDesignModel().setText(text);
	}

	/**
	 * Sets the buttons Tooltip text
	 * 
	 * @param title
	 *            Tooltip text
	 */
	public void setTitle(String title) {
		getButtonDesignModel().setTooltip(title);
	}

	/**
	 * sets the button style
	 * 
	 * @param buttonStyle
	 *            Button Style
	 * @throws JspException
	 *             If the Argument can't be converted to int
	 */
	public void setButtonStyle(String buttonStyle) throws JspException {
		getButtonDesignModel().setButtonStyle(TagHelp.toInt(buttonStyle));
	}

	/**
	 * @see com.cc.framework.taglib.WebResourceTag#setBase(java.lang.String)
	 */
	public void setBase(String base) {
		getButtonDesignModel().setBase(base);
	}

	/**
	 * The automatic HTML coding of the text element can be activated or
	 * disabled with the filter-attribute
	 * 
	 * @param filter
	 *            <code>true</code> if the text should be HTML encoded.
	 * @throws JspException
	 *             If the Argument can't be converted to a boolean
	 */
	public void setFilter(String filter) throws JspException {
		getButtonDesignModel().setFilter(TagHelp.toBoolean(filter));
	}
}