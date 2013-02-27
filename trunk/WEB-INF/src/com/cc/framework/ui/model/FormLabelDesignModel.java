/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/FormLabelDesignModel.java,v 1.5 2005/02/16 18:13:29 P001001 Exp $
 * $Revision: 1.5 $
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

import com.cc.framework.ui.AlignmentType;

/**
 * Design Model for a Coumn Header
 *
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.5 $
 */
public interface FormLabelDesignModel extends DesignModel, ClientHandler {

	/**
	 * Returns the labels text
	 *
	 * @return	string
	 */
	public String getText();

	/**
	 * Sets the labels text
	 *
	 * @param	text The label text
	 */
	public void setText(String text);

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
	 * The ACCESSKEY attribute can be used to specify a shortcut
	 * key for the &lt;LABEL&gt; (activated by pressing 'Alt' and
	 * the ACCESSKEY togther).
	 *
	 * @param		accessKey The Access Key
	 */
	public void setAccessKey(String accessKey);

	/**
	 * The ACCESSKEY attribute can be used to specify a shortcut
	 * key for the &lt;LABEL&gt; (activated by pressing 'Alt' and
	 * the ACCESSKEY togther).
	 *
	 * @return		The Access Key
	 */
	public String getAccessKey();

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
	 * Identifier to be assigned to this label.
	 *
	 * @param	id	The identifier to be assigned to this label.
	 */
	public void setStyleId(String id);

	/**
	 * Returns the Identifier assigned to this label.
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
	 * Sets the CSS styles to be applied to this label.
	 *
	 * @param	style	The CSS styles for this label.
	 */
	public void setStyle(String style);

	/**
	 * Returns the CSS styles for this label.
	 *
	 * @return	String	the CSS styles for this label.
	 */
	public String getStyle();

	/**
	 * Returns the alignment for the label
	 *
	 * @return the alignment for the label
	 */
	public AlignmentType getAlignment();

	/**
	 * Sets the alignment for the label
	 *
	 * @param alignment The alignment
	 */
	public void setAlignment(AlignmentType alignment);

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
	 * Returns the width of the label
	 *
	 * @return	String	The width.
	 */
	public String getWidth();

	/**
	 * Sets the width of the label
	 *
	 * @param	width	The width of the label
	 */
	public void setWidth(String width);

	/**
	 * Returns the value of the nowrap attribute.
	 *
	 * @return		Boolean or <code>null</code> if
	 * 				not set
	 */
	public Boolean getNowrap();

	/**
	 * Sets the value of the nowrap attribute
	 *
	 * @param	nowrap	Boolean or <code>null</code>
	 */
	public void setNowrap(Boolean nowrap);

	/**
	 * Sets the Locale configuration for this element
	 *
	 * @param		locale Locale Identifier or <code>true|false</code>
	 */
	public void setLocaleName(String locale);

	/**
	 * Gets the Local Setting for this element
	 *
	 * @return		Locale Setting
	 */
	public String getLocaleName();
}