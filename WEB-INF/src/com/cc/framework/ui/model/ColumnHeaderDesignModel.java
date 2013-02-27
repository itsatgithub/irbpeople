/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/ColumnHeaderDesignModel.java,v 1.4 2005/02/16 18:13:31 P001001 Exp $
 * $Revision: 1.4 $
 * $Date: 2005/02/16 18:13:31 $
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

import com.cc.framework.ui.AlignmentType;
import com.cc.framework.ui.ImageMap;

/**
 * Design Model for a Coumn Header
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.4 $
 */
public interface ColumnHeaderDesignModel extends DesignModel, ClientHandler {

	/**
	 * Returns the title of the column
	 *
	 * @return	string
	 */
	public String getTitle();

	/**
	 * Sets the titel of the column
	 *
	 * @param	title	The titel of the column
	 */
	public void setTitle(String title);

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

	/**
	 * Sets the static tooltip text.
	 *
	 * @param	tooltip Tooltip text
	 */
	public void setTooltip(String tooltip);

	/**
	 * Returns the static tooltip text
	 * @return	String
	 */
	public String getTooltip();

	/**
	 * Identifier to be assigned to this column.
	 *
	 * @param	id	The identifier to be assigned to this column.
	 */
	public void setStyleId(String id);

	/**
	 * Returns the Identifier assigned to this column.
	 *
	 * @return	String
	 */
	public String getStyleId();

	/**
	 * Sets the CSS stylesheet class
	 *
	 * @param	styleClass	The CSS stylesheet class
	 */
	public void setStyleClass(String styleClass);

	/**
	 * Returns the CSS stylesheet class
	 *
	 * @return	String	the CSS stylesheet class
	 */
	public String getStyleClass();

	/**
	 * Sets the CSS styles to be applied to this column.
	 *
	 * @param	style	The CSS styles for this column.
	 */
	public void setStyle(String style);

	/**
	 * Returns the CSS styles for this column.
	 *
	 * @return	String	the CSS styles for this column.
	 */
	public String getStyle();

	/**
	 * Returns the alignment for the column
	 *
	 * @return the alignment for the column
	 */
	public AlignmentType getAlignment();

	/**
	 * Sets the alignment for the column
	 *
	 * @param alignment The alignment
	 */
	public void setAlignment(AlignmentType alignment);

	/**
	 * Assigns an ImageMap to the Columnheader
	 * @param	map	ImageMap
	 */
	public void setImageMap(ImageMap map);

	/**
	 * Returns the ImageMap
	 * @return	ImageMap	
	 */
	public ImageMap getImageMap();

	/**
	 * Assigns an Image from an ImageMap
	 * @param	ref	Rule of the ImageMap to match
	 */
	public void setImageRef(String ref);

	/**
	 * Returns the assignment Rule
	 * @return	String
	 */
	public String getImageRef();

	/**
	 * Returns the maximal number of input characters
	 * which can be inserted into the text field.
	 * 
	 * @return	Maximum number of input characters to accept
	 */
	public int getMaxLength();

	/**
	 * Sets the maximal number of input characters
	 * which can be inserted into the text field.
	 * 
	 * @param		max Maximum number of input characters to accept
	 */
	public void setMaxLength(int max);

	/**
	 * Returns if the header text was set within the body (true) or
	 * as an attribute (false)
	 * 
	 * @return		true if the body was specified; false otherwise
	 */
	public boolean isBodyInclude();

	/**
	 * Sets if the header text is set within the body (true)
	 * 
	 * @param		include Flag which indicates if the body was set
	 * 				instead of the attribute
	 */
	public void setBodyInclude(boolean include);

	/**
	 * @return		<code>true</code> if the column should
	 * 				show a column header
	 */
	public boolean showHeader();
}