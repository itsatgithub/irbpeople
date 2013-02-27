/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/SwapSelectDesignModel.java,v 1.3 2005/02/16 18:13:29 P001001 Exp $
 * $Revision: 1.3 $
 * $Date: 2005/02/16 18:13:29 $
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

import com.cc.framework.ui.OrientationType;

/**
 * Designmodel for the Swap Select Control
 * 
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version $Revision: 1.3 $
 */
public interface SwapSelectDesignModel extends ControlDesignModel {

	/**
	 * Sets the label for the left selection window
	 * 
	 * @param	label	the label
	 */
	public void setLabelLeft(String label);

	/**
	 * Retrieves the label for the left selection window
	 * 
	 * @return	the label
	 */
	public String getLabelLeft();

	/**
	 * Sets the label for the right selection window
	 * 
	 * @param	label	the label
	 */
	public void setLabelRight(String label);

	/**
	 * Retrieves the label for the right selection window
	 * 
	 * @return	the label
	 */
	public String getLabelRight();

	/**
	 * Specifies the orientation of the selection windows:
	 * <ul>
	 * 		<li>horicontal = from top to bottom</li>
	 * 		<li>vertical = from left to right</li>
	 * </ul>
	 *
	 * @param		orientation	The orientation of the control
	 */
	public void setOrientation(OrientationType orientation);

	/**
	 * Retrieves the orientation of the selection windows
	 * 
	 * @return	the orientation 
	 */
	public OrientationType getOrientation();

	/**
	 * Returns the number of visibel characters
	 * for an input/text field.
	 * 
	 * @return	Number of character positions to allocate
	 */
	public int getSize();

	/**
	 * Sets the size of the input field.
	 *
	 * @param	size	The size of the input field.
	 */
	public void setSize(int size);

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
	 * @param		filter <code>true</code> if strings should be html
	 * 				encoded; false otherwise
	 */
	public void setFilter(boolean filter);
}