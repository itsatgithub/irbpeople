/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/ColorPickerDesignModel.java,v 1.5 2005/02/16 18:03:22 P001001 Exp $
 * $Revision: 1.5 $
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

/**
 * The design model for the color picker control
 * The design model holds information for rendering and the
 * layout of the control.
 * 
 * @author     <a href="mailto:gschulz@scc-gmbh.com">Gernot Schulz</a>
 * @version    $Revision: 1.5 $
 * @since      1.0
 */
public interface ColorPickerDesignModel extends TextDesignModel {

	/**
	 * Sets the palette to initalize the popup window
	 * @param	palette	 The palette
	 */
	public void setPalette(int palette);
	
	/**
	 * Returns the palette
	 * @return	The palette
	 */
	public int getPalette();
	
	/**
	 * Sets the tooltip for the colorpicker image
	 * @param	tooltip	The tooltip
	 */
	public void setButtonTooltip(String tooltip);

	/**
	 * Returns the tooltip for the colorpicker image
	 * @return	The tooltip
	 */
	public String getButtonTooltip();
	
	/**
	 * Sets the alt attribute for the colorpicker image
	 * @param alt	The alt sttribute if no image is displayed
	 */
	public void setButtonAlt(String alt);

	/**
	 * Returns the alt attribute for the colorpicker image
	 * @return  The alt sttribute if no image is displayed
	 */
	public String getButtonAlt();

}
