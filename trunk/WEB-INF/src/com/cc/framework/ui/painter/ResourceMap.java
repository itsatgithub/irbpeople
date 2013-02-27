/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/ResourceMap.java,v 1.11 2005/02/16 18:03:09 P001001 Exp $
 * $Revision: 1.11 $
 * $Date: 2005/02/16 18:03:09 $
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

import com.cc.framework.ui.Color;
import com.cc.framework.ui.model.ImageModel;

/**
 * Painter Resource Map
 *
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.11 $
 * @since		1.1
 */
public interface ResourceMap {

	/**
	 * Returns the base directory used for
	 * resource by the Painterfactory
	 *
	 * @return	The web resource directory
	 */
	public String getResourceDir();

	/**
	 * Retrieves an image for the specified Id
	 *
	 * @param	size		The size
	 * @param	resourceId	The resource id
	 * @return	The Resource or <code>null</code>
	 */
	public Object getResource(int size, String resourceId);

	/**
	 * Sets the directory used for
	 * resource by the Painterfactory
	 * @param resourceDir	The resource directory
	 */
	public void setResourceDir(String resourceDir);

	/**
	 * Retrieves an image for the specified Id
	 *
	 * @param		resourceId Image Id
	 * @return		The Image or <code>null</code>
	 */
	public ImageModel getImage(String resourceId);

	/**
	 * Retrieves an image for the specified Id
	 *
	 * @param		size Image Size
	 * @param		resourceId Image Id
	 * @return		The Image or <code>null</code>
	 */
	public ImageModel getImage(int size, String resourceId);

	/**
	 * Retrieves a color for the specified Id
	 *
	 * @param		resourceId Color Id
	 * @return		The Color or <code>null</code>
	 */
	public Color getColor(String resourceId);

	/**
	 * Retrieves an String for the specified Id
	 *
	 * @param		resourceId String Id
	 * @param		returnNull controlls wether to return null
	 * 				or a not present indicator
	 * @return		The String or <code>null</code>
	 */
	public String getString(String resourceId, boolean returnNull);

	/**
	 * Retrieves an String for the specified Id
	 *
	 * @param		resourceId String Id
	 * @param		params Array mit Parameters for
	 * 				markup substitution
	 * @param		returnNull controlls wether to return null
	 * 				or a not present indicator
	 * @return		The String or <code>null</code>
	 */
	public String getString(String resourceId, Object[] params, boolean returnNull);


	/**
	 * Sets the color palette.
	 * You can register individual colors with registerColor
	 * which will hide the entries of the Color Palette.
	 *
	 * @param		palette Color Palette
	 */
	public void setColorPalette(ColorPalette palette);


	/**
	 * Registers one local image
	 *
	 * @param		resourceId The Image Resource Id
	 * @param		image The Image
	 */
	public void registerImage(String resourceId, ImageModel image);

	/**
	 * Registers one locale image
	 *
	 * @param		size The imagesize
	 * @param		resourceId The Image Resource Id
	 * @param		image The Image
	 */
	public void registerImage(int size, String resourceId, ImageModel image);

	/**
	 * Registers one locale color
	 *
	 * @param		resourceId The Image Resource Id
	 * @param		color the Color
	 */
	public void registerColor(String resourceId, Color color);

	/**
	 * Registers a string resource
	 *
	 * @param		resourceId	The string resource Id
	 * @param		text		The text associated with the resource Id
	 */
	public void registerString(String resourceId, String text);

	/**
	 * Registers a locale resource
	 *
	 * @param		resourceId The Resource Id
	 * @param		resource The Resource
	 */
	public void register(String resourceId, Object resource);

	/**
	 * Creates a resource identifier for an image
	 *
	 * @param		size The imagesize
	 * @param		resourceId The Image Resource Id
	 * @return		Resource Identifier
	 */
	public String getId(int size, String resourceId);

}