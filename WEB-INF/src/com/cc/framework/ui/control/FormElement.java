/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/control/FormElement.java,v 1.12 2005/02/16 18:03:01 P001001 Exp $
 * $Revision: 1.12 $
 * $Date: 2005/02/16 18:03:01 $
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

package com.cc.framework.ui.control;

import com.cc.framework.ui.AlignmentType;
import com.cc.framework.ui.model.AccessControlled;

/**
 * Base Interface for all form elements
 *
 * @author    <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version   $Revision: 1.12 $
 * @since     1.0
 */
public interface FormElement extends AccessControlled {

	/**
	 * Indicates that the form element cell should not
	 * be wrapped
	 *
	 * @return		Boolean
	 */
	public boolean getNoWrap();

	/**
	 * Returns the column span for this element
	 *
	 * @return		column span
	 */
	public int getColSpan();

	/**
	 * Sets the column span of this form element
	 *
	 * @param		colspan column span
	 */
	public void setColSpan(int colspan);

	/**
	 * This method returns <code>true</code> when two adjacent
	 * form elements shold be joined together without a separator
	 *
	 * @return		<code>true</code> when the form elements
	 * 				should be joined
	 */
	public boolean joinElements();

	/**
	 * This method specifies that two adjacent
	 * form elements shold be joined together without a separator
	 *
	 * @param		join <code>true</code> when the form elements
	 * 				should be joined
	 */
	public void setJoin(boolean join);

	/**
	 * Sets the width of the form element (pixel or %)
	 *
	 * @param	width The Width
	 */
	public void setWidth(String width);

	/**
	 * Returns the width of the form element (pixel or %)
	 *
	 * @return	width
	 */
	public String getWidth();

	/**
	 * Sets the height of the form element (pixel or %)
	 *
	 * @param	height The Height
	 */
	public void setHeight(String height);

	/**
	 * Returns the height of the form element (pixel or %)
	 *
	 * @return	height
	 */
	public String getHeight();

	/**
	 * Specifies the horicontal alignment of the form element
	 * within the form
	 * <ul>
	 * 		<li>left</li>
	 * 		<li>right</li>
	 * 		<li>center</li>
	 * </ul>
	 *
	 * @param		alignment	The alignment of the element
	 */
	public void setAlignment(AlignmentType alignment);

	/**
	 * Returns the horizontal alignment
	 *
	 * @return		alignment
	 */
	public AlignmentType getAlignment();

	/**
	 * Specifies the vertical alignment of the form element
	 * within the form
	 * <ul>
	 * 		<li>top</li>
	 * 		<li>bottom</li>
	 * 		<li>center</li>
	 * </ul>
	 *
	 * @param		valignment	The alignment of the element
	 */
	public void setVAlignment(AlignmentType valignment);

	/**
	 * Returns the vertical alignment
	 *
	 * @return		alignment
	 */
	public AlignmentType getVAlignment();

	/**
	 * Sets the Style
	 * @param		style Stil Konstante
	 */
	public void setStyle(String style);

	/**
	 * Returns the Style
	 * @return	String
	 */
	public String getStyle();

	/**
	 * Sets the StyleId
	 * @param		styleId HTML-Id des Elementes
	 */
	public void setStyleId(String styleId);

	/**
	 * returns the StyleId
	 * @return	string
	 */
	public String getStyleId();

	/**
	 * Sets the StyleClass
	 * @param styleClass	StyleClass
	 */
	public void setStyleClass(String styleClass);

	/**
	 * Returns the StyleClass
	 * @return	String
	 */
	public String getStyleClass();

	/**
	 * Returns the Help id that is associated with this
	 * form element. If a control has a help id a little
	 * icon with a link to the help system will be created
	 *
	 * @return		String returns the Help id of the element.
	 * 				The semantic of this id is completely
	 * 				in the responsibility of the applications
	 * 				help system
	 */
	public String getHelp();

	/**
	 * Associates this form element with a help id
	 *
	 * @param		helpId The Help Help id of the element.
	 * 				The semantic of this id is completely
	 * 				in the responsibility of the applications
	 * 				help system
	 */
	public void setHelp(String helpId);
}