/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/imp/FormLabelDesignModelImp.java,v 1.8 2005/07/08 15:15:32 P001002 Exp $
 * $Revision: 1.8 $
 * $Date: 2005/07/08 15:15:32 $
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

import java.io.Serializable;

import com.cc.framework.ui.AlignmentType;
import com.cc.framework.ui.model.FormLabelDesignModel;

/**
 * Implementation of a header design model
 *
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.8 $
 */
public class FormLabelDesignModelImp extends ClientHandlerImp implements FormLabelDesignModel, Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -6113201091866371943L;

	/**
	 * The label text
	 */
	private String text					= null;

	/**
	 * Indicates wether the title is set
	 * within the body or as an attribute
	 */
	private boolean bodyInclude			= false;

	/**
	 * The alignment of the label
	 */
	private AlignmentType alignment		= null;

	/**
	 * Maximum number of visible characters
	 */
	private int maxLength				= -1;

	/**
	 * Identifier to be assigned to this HTML element
	 */
	private String styleId				= null;

	/**
	 * CSS stylesheet class to be applied to this HTML element
	 */
	private String styleClass			= null;

	/**
	 * CSS styles to be applied to this HTML element
	 */
	private String style				= null;

	/**
	 * Specifies if all String should be converted
	 * into there HTML representation
	 */
	private boolean filter				= true;

	/**
	 * Locale Setting
	 */
	private String localeName			= null;

	/**
	 * The ACCESSKEY attribute can be used to specify a shortcut
	 * key for the &lt;LABEL&gt; (activated by pressing 'Alt' and
	 * the ACCESSKEY togther).
	 */
	private String accessKey			= null;

	/**
	 * Static tooltip Attribut
	 */
	private String tooltip				= null;

	/**
	 * Image reference
	 */
	private String imageref				= null;

	/**
	 * The width of the label
	 */
	private String width				= null;

	/**
	 * Nowrap
	 */
	private Boolean nowrap				= null;

	/**
	 * Construktor
	 */
	public FormLabelDesignModelImp() {
		super();
	}

	/**
	 * Construktor
	 *
	 * @param		text The Labels text
	 * @param		imageref Label image
	 */
	public FormLabelDesignModelImp(String text, String imageref) {
		super();

		this.text		= text;
		this.imageref	= imageref;
	}

	/**
	 * @see com.cc.framework.ui.model.FormLabelDesignModel#getText()
	 */
	public String getText() {
		return text;
	}

	/**
	 * @see com.cc.framework.ui.model.FormLabelDesignModel#setText(java.lang.String)
	 */
	public void setText(String text) {
		this.text	= text;
	}

	/**
	 * @see com.cc.framework.ui.model.FormLabelDesignModel#filter()
	 */
	public boolean filter() {
		return filter;
	}

	/**
	 * @see com.cc.framework.ui.model.FormLabelDesignModel#setFilter(boolean)
	 */
	public void setFilter(boolean filter) {
		this.filter = filter;
	}

	/**
	 * @see com.cc.framework.ui.model.FormLabelDesignModel#setStyleClass(java.lang.String)
	 */
	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	/**
	 * @see com.cc.framework.ui.model.FormLabelDesignModel#getStyleClass()
	 */
	public String getStyleClass() {
		return styleClass;
	}

	/**
	 * @see com.cc.framework.ui.model.FormLabelDesignModel#setStyle(String)
	 */
	public void setStyle(String style) {
		this.style = style;
	}

	/**
	 * @see com.cc.framework.ui.model.FormLabelDesignModel#getStyle()
	 */
	public String getStyle() {
		return style;
	}

	/**
	 * @see  com.cc.framework.ui.model.FormLabelDesignModel#setStyleId(String id)
	 */
	public void setStyleId(String id) {
		this.styleId	= id;
	}

	/**
	 * @see  com.cc.framework.ui.model.FormLabelDesignModel#getStyleId()
	 */
	public String getStyleId() {
		return styleId;
	}

	/**
	 * @see com.cc.framework.ui.model.FormLabelDesignModel#getAccessKey()
	 */
	public String getAccessKey() {
		return accessKey;
	}

	/**
	 * @see com.cc.framework.ui.model.FormLabelDesignModel#setAccessKey(java.lang.String)
	 */
	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	/**
	 * @see com.cc.framework.ui.model.FormLabelDesignModel#getTooltip()
	 */
	public String getTooltip() {
		return tooltip;
	}

	/**
	 * @see com.cc.framework.ui.model.FormLabelDesignModel#setTooltip(java.lang.String)
	 */
	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

	/**
	 * @see com.cc.framework.ui.model.FormLabelDesignModel#getAlignment()
	 */
	public AlignmentType getAlignment() {
		return alignment;
	}

	/**
	 * @see com.cc.framework.ui.model.FormLabelDesignModel#getImageRef()
	 */
	public String getImageRef() {
		return imageref;
	}

	/**
	 * @see com.cc.framework.ui.model.FormLabelDesignModel#getMaxLength()
	 */
	public int getMaxLength() {
		return maxLength;
	}

	/**
	 * @see com.cc.framework.ui.model.FormLabelDesignModel#setAlignment(com.cc.framework.ui.AlignmentType)
	 */
	public void setAlignment(AlignmentType alignment) {
		this.alignment = alignment;
	}

	/**
	 * @see com.cc.framework.ui.model.FormLabelDesignModel#setImageRef(java.lang.String)
	 */
	public void setImageRef(String ref) {
		this.imageref = ref;
	}

	/**
	 * @see com.cc.framework.ui.model.FormLabelDesignModel#setMaxLength(int)
	 */
	public void setMaxLength(int max) {
		this.maxLength = max;
	}

	/**
	 * @see com.cc.framework.ui.model.FormLabelDesignModel#isBodyInclude()
	 */
	public boolean isBodyInclude() {
		return bodyInclude;
	}

	/**
	 * @see com.cc.framework.ui.model.FormLabelDesignModel#setBodyInclude(boolean)
	 */
	public void setBodyInclude(boolean include) {
		this.bodyInclude = include;
	}

	/**
	 * @see com.cc.framework.ui.model.FormLabelDesignModel#getWidth()
	 */
	public String getWidth() {
		return width;
	}

	/**
	 * @see com.cc.framework.ui.model.FormLabelDesignModel#setWidth(java.lang.String)
	 */
	public void setWidth(String width) {
		this.width	= width;
	}

	/**
	 * @see com.cc.framework.ui.model.FormLabelDesignModel#getNowrap()
	 */
	public Boolean getNowrap() {
		return nowrap;
	}

	/**
	 * @see com.cc.framework.ui.model.FormLabelDesignModel#setNowrap(java.lang.Boolean)
	 */
	public void setNowrap(Boolean nowrap) {
		this.nowrap = nowrap;
	}

	/**
	 * @see com.cc.framework.ui.model.FormLabelDesignModel#getLocaleName()
	 */
	public String getLocaleName() {
		return localeName;
	}

	/**
	 * @see com.cc.framework.ui.model.FormLabelDesignModel#setLocaleName(java.lang.String)
	 */
	public void setLocaleName(String locale) {
		this.localeName = locale;
	}
}