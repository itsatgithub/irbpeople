/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/InnerFrame.java,v 1.3 2005/11/13 12:45:51 P001002 Exp $
 * $Revision: 1.3 $
 * $Date: 2005/11/13 12:45:51 $
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

import java.io.Serializable;

import com.cc.framework.security.Permission;
import com.cc.framework.security.Principal;
import com.cc.framework.security.StaticPermission;

/**
 * Group of form elements
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.3 $
 */
public class InnerFrame implements AccessControlled, Serializable {

	/**
	 * Serial Verion UID
	 */
	private static final long serialVersionUID = -5669213532419350375L;

	/**
	 * The height of the form element
	 */
	private String height = null;

	/**
	 * The width of the form element
	 */
	private String width = null;

	/**
	 * Style ID
	 */
	private String styleId = null;

	/**
	 * Style Class -> HTML class Attribut
	 */
	private String styleClass = null;

	/**
	 * Html-Style
	 */
	private String style = null;

	/**
	 * Enables or disables the frames border
	 */
	private boolean border = false;

	/**
	 * permissions for the element
	 */
	private Permission permission = StaticPermission.GRANTED;

	/**
	 * The Frames content
	 */
	private String content = null;

	/**
	 * Text that is used for rendering to non-visual media
	 * such as speech or Braille
	 */
	private String summary = null;

	/**
	 * Optional layout hint for this frame
	 */
	private Object layoutHint = null;

	/**
	 * @see com.cc.framework.ui.model.AccessControlled#getPermission()
	 */
	public Permission getPermission() {
		return permission;
	}

	/**
	 * Sets the elements permission
	 * 
	 * @param		permission required permission
	 */
	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	/**
	 * @see com.cc.framework.ui.model.AccessControlled#show(Principal)
	 */
	public boolean show(Principal principal) {
		return permission.isGranted(principal);
	}

	/**
	 * Returns the height of the frame element (pixel or %)
	 *
	 * @return	height
	 */
	public String getHeight() {
		return height;
	}

	/**
	 * Returns the width of the frame element (pixel or %)
	 *
	 * @return	width
	 */
	public String getWidth() {
		return width;
	}

	/**
	 * Sets the height of the frame element (pixel or %)
	 *
	 * @param	height The Height
	 */
	public void setHeight(String height) {
		this.height = height;
	}

	/**
	 * Sets the width of the frame element (pixel or %)
	 *
	 * @param	width The Width
	 */
	public void setWidth(String width) {
		this.width = width;
	}

	/**
	 * Returns the Style
	 * @return	String
	 */
	public String getStyle() {
		return style;
	}

	/**
	 * returns the StyleId
	 * @return	string
	 */
	public String getStyleId() {
		return styleId;
	}

	/**
	 * Returns the StyleClass
	 * @return	String
	 */
	public String getStyleClass() {
		return styleClass;
	}

	/**
	 * Sets the Style
	 * @param		style Stil Konstante
	 */
	public void setStyle(String style) {
		this.style = style;
	}

	/**
	 * Sets the StyleId
	 * @param		styleId HTML-Id des Elementes
	 */
	public void setStyleId(String styleId) {
		this.styleId = styleId;
	}

	/**
	 * Sets the StyleClass
	 * @param styleClass	StyleClass
	 */
	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	/**
	 * Disables the frames border
	 *
	 * @param	border <code>true</code> if the border should be shown
	 */
	public void setBorder(boolean border) {
		this.border = border;
	}

	/**
	 * @return	<code>true</code> if the border should be shown
	 */
	public boolean showBorder() {
		return border;
	}

	/**
	 * @return		The Content of the inner frame (raw HTML code)
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param		content The raw HTML contetn of the inner frame
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * Sets the property that can be used for rendering
	 * to non-visual media such as speech or Braille
	 * 
	 * @param	summary	specifies a description and/or structure of the object.
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}

	/**
	 * Retrieves the property that can be used for rendering
	 * to non-visual media such as speech or Braille
	 * 
	 * @return	receives a description and/or structure of the object
	 * 			or <code>null</code>
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * Retrieves the layout hint that is associated with this frame
	 * 
	 * @return		layout hint or <code>null</code>
	 */
	public Object getLayoutHint() {
		return layoutHint;
	}	

	/**
	 * Assigns a layout hint to this inner frame
	 * 
	 * @param		hint layout hint or <code>null</code>
	 */
	public void setLayoutHint(Object hint) {
		this.layoutHint = hint;
	}	
}