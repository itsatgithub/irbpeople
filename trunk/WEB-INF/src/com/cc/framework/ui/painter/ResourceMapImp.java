/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/ResourceMapImp.java,v 1.18 2005/07/12 06:36:48 P001002 Exp $
 * $Revision: 1.18 $
 * $Date: 2005/07/12 06:36:48 $
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

package com.cc.framework.ui.painter;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Hashtable;
import java.util.Set;

import com.cc.framework.ui.Color;
import com.cc.framework.ui.model.ImageModel;

/**
 * ResourceMap
 *
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.18 $
 * @since		1.1
 */
public abstract class ResourceMapImp implements ResourceMap, Serializable {

	/**
	 * Resource directory
	 */
	private String resourceDir = null;

	/**
	 * The Resource repository
	 */
	private Hashtable resources = new Hashtable();

	/**
	 * Color Palette
	 */
	private ColorPalette colorpalette = null;

	/**
	 * Flag for lazy initialization
	 */
	private boolean initialized = false;


	// --------------------------------
	//            methods
	// --------------------------------

	/**
	 * Constructor
	 */
	public ResourceMapImp() {
		super();
	}

	/**
	 * For initalization
	 */
	protected void init() {

		initialized = true;

		doRegisterImages();
		doRegisterColors();
		doRegisterStrings();
	}

	/**
	 * Registers all local images
	 */
	protected abstract void doRegisterImages();

	/**
	 * Registers all local colors
	 */
	protected abstract void doRegisterColors();

	/**
	 * Registers all local strings
	 */
	protected abstract void doRegisterStrings();

	/**
	 * @see com.cc.framework.ui.painter.ResourceMap#registerImage(java.lang.String, com.cc.framework.ui.model.ImageModel)
	 */
	public void registerImage(String resourceId, ImageModel image) {
		register(getId(0, resourceId), image);
	}

	/**
	 * @see com.cc.framework.ui.painter.ResourceMap#getId(int, java.lang.String)
	 */
	public String getId(int size, String resourceId) {
		if (size == 0) {
			return resourceId;
		} else {
			return resourceId + "@" + size;
		}
	}

	/**
	 * @see com.cc.framework.ui.painter.ResourceMap#registerImage(int, java.lang.String, com.cc.framework.ui.model.ImageModel)
	 */
	public void registerImage(int size, String resourceId, ImageModel image) {
		register(getId(size, resourceId), image);
	}

	/**
	 * @see com.cc.framework.ui.painter.ResourceMap#registerColor(java.lang.String, com.cc.framework.ui.Color)
	 */
	public void registerColor(String resourceId, Color color) {
		register(getId(0, resourceId), color);
	}

	/**
	 * @see com.cc.framework.ui.painter.ResourceMap#registerString(java.lang.String, java.lang.String)
	 */
	public void registerString(String resourceId, String text) {
		register(getId(0, resourceId), text);
	}

	/**
	 * @see com.cc.framework.ui.painter.ResourceMap#setColorPalette(com.cc.framework.ui.painter.ColorPalette)
	 */
	public void setColorPalette(ColorPalette palette) {
		this.colorpalette = palette;
	}

	/**
	 * @see com.cc.framework.ui.painter.ResourceMap#register(java.lang.String, java.lang.Object)
	 */
	public void register(String resourceId, Object resource) {
		if (resources.contains(resourceId)) {
			resources.remove(resourceId);
		}

		resources.put(resourceId, resource);
	}

	/**
	 * @see com.cc.framework.ui.painter.ResourceMap#getImage(java.lang.String)
	 */
	public ImageModel getImage(String resourceId) {
		Object resource = getResource(0, resourceId);

		if (resource instanceof ImageModel) {
			return (ImageModel) resource;
		} else {
			return null;
		}
	}

	/**
	 * @see com.cc.framework.ui.painter.ResourceMap#getImage(int, java.lang.String)
	 */
	public ImageModel getImage(int size, String resourceId) {
		Object resource = getResource(size, resourceId);

		if (resource instanceof ImageModel) {
			return (ImageModel) resource;
		} else {
			return getImage(resourceId);
		}
	}

	/**
	 * @see com.cc.framework.ui.painter.ResourceMap#getColor(java.lang.String)
	 */
	public Color getColor(String resourceId) {
		// Allow a derived class to overwrite individual colors.
		// Individual colors are stored in the resources collection
		// and hide the entries of a color palette
		Object resource = getResource(0, resourceId);

		if (resource instanceof Color) {
			// Do not use the color from the Palette object,
			// use the overwritten color instead
			return (Color) resource;
		} else if (colorpalette != null) {
			// The color was not overwritten so use the color
			// from our Palette object.
			return colorpalette.getColor(resourceId);
		} else {
			// The color is undefined
			return null;
		}
	}

	/**
	 * @see com.cc.framework.ui.painter.ResourceMap#getResource(int, java.lang.String)
	 */
	public Object getResource(int size, String resourceId) {

		if (!initialized) {
			init();
		}

		return resources.get(getId(size, resourceId));
	}

	/**
	 * @see com.cc.framework.ui.painter.ResourceMap#getString(java.lang.String, boolean)
	 */
	public String getString(String resourceId, boolean returnNull) {
		Object res = getResource(0, resourceId);

		if (res == null) {
			if (returnNull) {
				return null;
			} else {
				return resourceId;
			}
		} else {
			return res.toString();
		}
	}

	/**
	 * @see com.cc.framework.ui.painter.ResourceMap#getString(java.lang.String, java.lang.Object[], boolean)
	 */
	public String getString(String resourceId, Object[] params, boolean returnNull) {

		if (resourceId == null) {
			return null;
		}

		Object template = getResource(0, resourceId);

		if (template == null) {
			if (returnNull) {
				return null;
			} else {
				return resourceId;
			}
		} else if (params == null) {
			return template.toString();
		} else {
			return MessageFormat.format(template.toString(), params);

		}
	}

	/**
	 * @see com.cc.framework.ui.painter.ResourceMap#getResourceDir()
	 */
	public String getResourceDir() {
		return resourceDir;
	}

	/**
	 * @see com.cc.framework.ui.painter.ResourceMap#setResourceDir(java.lang.String)
	 */
	public void setResourceDir(String resourceDir) {
		this.resourceDir = resourceDir;
	}

	/**
	 * Returns a set with all the Resource Keys of this Resource Map
	 *
	 * @return		Key Set
	 */
	public Set getResourceKeys() {
		return resources.keySet();
	}

	/**
	 * Returns the color palette
	 *
	 * @return		Color palette or <code>null</code>
	 */
	public ColorPalette getColorPalette() {
		return colorpalette;
	}
}