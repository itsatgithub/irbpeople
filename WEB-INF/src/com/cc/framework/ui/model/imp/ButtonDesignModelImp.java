/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/imp/ButtonDesignModelImp.java,v 1.15 2005/07/08 14:19:42 P001002 Exp $
 * $Revision: 1.15 $
 * $Date: 2005/07/08 14:19:42 $
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

package com.cc.framework.ui.model.imp;

import com.cc.framework.ui.model.ButtonDesignModel;
import com.cc.framework.ui.model.ButtonType;

/**
 * The design model for the Spin control
 *
 * @author      <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version     $Revision: 1.15 $
 * @since       1.2
 */
public class ButtonDesignModelImp extends ControlDesignModelImp implements ButtonDesignModel {

	/**
	 * Serial Version UID 
	 */
	private static final long serialVersionUID = 791523482980086971L;

	/**
	 * URL to image resource
	 */
	private String src				= null;

	/**
	 * Text
	 */
	private String text				= null;

	/**
	 * Image Base directory
	 */
	private String base				= null;

	/**
	 * The button type
	 */
	private ButtonType buttonType	= ButtonType.BUTTON;

	/**
	 * The button style id.
	 * The id is used to provide
	 * textbuttons with different
	 * background images.
	 */
	private int buttonStyle			= 1;

	/**
	 * Specifies if all String should be converted
	 * into there HTML representation
	 */
	private boolean filter			= true;

	// ----------------------------------
	//           methods
	// ----------------------------------

	/**
	 * Constructor
	 */
	public ButtonDesignModelImp() {
		super();
	}

	/**
	 * @see com.cc.framework.ui.model.ButtonDesignModel#getSrc()
	 */
	public String getSrc() {
		return src;
	}

	/**
	 * @see com.cc.framework.ui.model.ButtonDesignModel#setSrc(java.lang.String)
	 */
	public void setSrc(String src) {
		this.src = src;
	}

	/**
	 * @see com.cc.framework.ui.model.ButtonDesignModel#getText()
	 */
	public String getText() {
		return text;
	}

	/**
	 * @see com.cc.framework.ui.model.ButtonDesignModel#setText(java.lang.String)
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @see com.cc.framework.ui.WebResourceAccess#getBase()
	 */
	public String getBase() {
		return base;
	}

	/**
	 * @see com.cc.framework.ui.WebResourceAccess#setBase(java.lang.String)
	 */
	public void setBase(String base) {
		this.base = base;
	}

	/**
	 * @see com.cc.framework.ui.model.ButtonDesignModel#getButtonStyle()
	 */
	public int getButtonStyle() {
		return buttonStyle;
	}

	/**
	 * @see com.cc.framework.ui.model.ButtonDesignModel#setButtonStyle(int)
	 */
	public void setButtonStyle(int buttonStyle) {
		this.buttonStyle = buttonStyle;
	}

	/**
	 * @see com.cc.framework.ui.model.ButtonDesignModel#getButtonType()
	 */
	public ButtonType getButtonType() {
		return buttonType;
	}

	/**
	 * @see com.cc.framework.ui.model.ButtonDesignModel#setButtonType(com.cc.framework.ui.model.ButtonType)
	 */
	public void setButtonType(ButtonType buttonType) {
		this.buttonType = buttonType;
	}

	/**
	 * @see com.cc.framework.ui.model.ButtonDesignModel#filter()
	 */
	public boolean filter() {
		return filter;
	}

	/**
	 * @see com.cc.framework.ui.model.ButtonDesignModel#setFilter(boolean)
	 */
	public void setFilter(boolean filter) {
		this.filter = filter;
	}
}