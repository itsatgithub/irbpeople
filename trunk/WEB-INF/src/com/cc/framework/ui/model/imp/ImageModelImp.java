/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/imp/ImageModelImp.java,v 1.15 2005/07/08 15:15:33 P001002 Exp $
 * $Revision: 1.15 $
 * $Date: 2005/07/08 15:15:33 $
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

import com.cc.framework.ui.model.ImageModel;

/**
 * Designmodel for images.
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.15 $
 * @since      1.0
 */
public class ImageModelImp implements ImageModel, Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -8321680906193059969L;

	/**
	 * The source URL of the image
	 */
	private String source = null;

	/**
	 * The base directory for all the images
	 */
	private String base = null;

	/**
	 * Tooltip Text
	 */
	private String tooltip = null;

	/**
	 * The alternate text for this image
	 */
	private String alternate = null;

	/**
	 * The width of the image
	 */
	private int width = 0;

	/**
	 * The height of the image
	 */
	private int height = 0;

	/**
	 * Constructor for ImageModelImp
	 */
	public ImageModelImp() {
		super();
	}

	/**
	 * Constructor for ImageModelImp
	 *
	 * @param	source	 The source URL of the image
	 */
	public ImageModelImp(String source) {

		super();

		this.source = source;
	}

	/**
	 * Constructor for ImageModelImp
	 *
	 * @param	source	The source URL of the image
	 * @param	width	The width of the image
	 * @param	height	The height of the image
	 */
	public ImageModelImp(String source, int width, int height) {
		super();

		this.source = source;
		this.width = width;
		this.height = height;
	}

	/**
	 * Copy Constructor for ImageModelImp
	 *
	 * @param	other The Model to copy from
	 */
	public ImageModelImp(ImageModel other) {
		super();

		setFromImage(other);
	}

	/**
	 * Copies all attributes from an other image
	 *
	 * @param	other The Model to copy from
	 */
	public void setFromImage(ImageModel other) {
		this.source		= other.getSource();
		this.base		= other.getBase();
		this.tooltip	= other.getTooltip();
		this.alternate	= other.getAlternate();
		this.width		= other.getWidth();
		this.height		= other.getHeight();
	}

	/**
	 * @see com.cc.framework.ui.model.ImageModel#getTooltip()
	 */
	public String getTooltip() {
		return tooltip;
	}

	/**
	 * @see com.cc.framework.ui.model.ImageModel#getAlternate()
	 */
	public String getAlternate() {
		return alternate;
	}

	/**
	 * @see com.cc.framework.ui.model.ImageModel#getHeight()
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @see com.cc.framework.ui.model.ImageModel#getSource()
	 */
	public String getSource() {
		return source;
	}

	/**
	 * @see com.cc.framework.ui.model.ImageModel#getWidth()
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Sets the tooltip for this image
	 *
	 * @param		tooltip Tooltip text
	 */
	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

	/**
	 * Sets the text to display if the image can't
	 * be displayed or the image content can't be found.
	 *
	 * @param	alt		The text to display if the image can't be displayed.
	 */
	public void setAlternate(String alt) {
		this.alternate = alt;
	}

	/**
	 * Sets the height of the image
	 *
	 * @param	height	the height of the image
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Sets the URL of the image to be displayed
	 *
	 * @param	src		the URL of the image
	 */
	public void setSource(String src) {
		this.source = src;
	}

	/**
	 * Sets the width of the image
	 *
	 * @param	width	the width of the image
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @see com.cc.framework.ui.WebResourceAccess#getBase()
	 */
	public String getBase() {
		return base;
	}

	/**
	 * Sets the resoure base directory
	 *
	 * @param		base Base Directory
	 */
	public void setBase(String base) {
		this.base = base;
	}
}