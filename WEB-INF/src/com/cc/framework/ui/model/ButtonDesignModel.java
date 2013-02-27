/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/ButtonDesignModel.java,v 1.8 2005/02/16 18:03:22 P001001 Exp $
 * $Revision: 1.8 $
 * $Date: 2005/02/16 18:03:22 $
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

package com.cc.framework.ui.model;

import com.cc.framework.ui.WebResourceAccess;

/**
 * The design model for the Button control
 * The design model holds information for rendering and the
 * layout of the control.
 * 
 * @author     <a href="mailto:gschulz@scc-gmbh.com">Gernot Schulz</a>
 * @version    $Revision: 1.8 $
 * @since      1.0
 */
public interface ButtonDesignModel extends ControlDesignModel, WebResourceAccess {

	/**
	 * Returns the type of the button
	 * 
	 * @return The type of the button
	 */
	public ButtonType getButtonType();

	/**
	 * Sets the type of the button
	 *
	 * @param	buttonType	The button type
	 */
	public void setButtonType(ButtonType buttonType);

	/**
	 * Returns the src.
	 * @return String
	 */
	public String getSrc();

	/**
	 * Sets the src.
	 * @param src The src to set
	 */
	public void setSrc(String src);

	/**
	 * Returns the text.
	 * @return String
	 */
	public String getText();

	/**
	 * Sets the text.
	 * @param text The text to set
	 */
	public void setText(String text);
	
	/**
	 * Returns the style for a textbutton
	 * If text buttons with different background
	 * images are needed they can be distinguished
	 * by the buttonStyle id.
	 * @return The style 
	 */
	public int getButtonStyle();
	
	/**
	 * Sets the button style indicating the
	 * background image of the textbutton 
	 * @param	buttonStyle	The button style id
	 */
	public void setButtonStyle(int buttonStyle);

	/**
	 * Returns if the filter is activated (default=true). This means
	 * that all Strings which should be displayed in the HTML page
	 * are html encoded
	 *
	 * @return	<code>true</code> if string will be html encoded;
	 * 			<code>false</code> otherwise
	 */
	public boolean filter();

	/**
	 * Activates the html encoding (filter). Default is true. This
	 * means that all Strings which should be displayed in the HTML page
	 * will be html encoded.
	 *
	 * @param		filter <code>true</code> if strings should be html encoded; false otherwise
	 */
	public void setFilter(boolean filter);
}