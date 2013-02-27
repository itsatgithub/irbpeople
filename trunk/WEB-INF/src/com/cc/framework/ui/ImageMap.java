/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/ImageMap.java,v 1.16 2005/07/08 14:16:49 P001002 Exp $
 * $Revision: 1.16 $
 * $Date: 2005/07/08 14:16:49 $
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

package com.cc.framework.ui;

import java.io.Serializable;
import java.util.Collection;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.regexp.RE;
import org.apache.regexp.RESyntaxException;

import com.cc.framework.ui.model.ImageModel;

/**
 * Provides an image map, which associates regulare expressions
 * with images.
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.16 $
 * @since      1.0
 */
public class ImageMap implements Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -7328339276964969007L;

	/**
	 * A ImageMapping maps a regular expression to an image
	 */
	public class ImageMapping implements ImageModel, Serializable {

		/**
		 * Serial Version UID
		 */
		private static final long serialVersionUID = -8652921802804526224L;

		/**
		 * The Mapping rule (regular expression)
		 */
		private String rule;
		
		/**
		 * The Image Rule 
		 */
		private ImageModel image;

		/**
		 * Constructor
		 * 
		 * @param		rule The mapping rule (regular expression)
		 * @param		image The image model
		 */
		public ImageMapping(String rule, ImageModel image) {
			super();

			this.rule	= rule;
			this.image	= image;
		}

		/**
		 * @return		Mapping rule (regular expression)
		 */
		public String getRule() {
			return rule;
		}

		/**
		 * @see com.cc.framework.ui.model.ImageModel#getHeight()
		 */
		public int getHeight() {
			return image.getHeight();
		}

		/**
		 * @see com.cc.framework.ui.model.ImageModel#getWidth()
		 */
		public int getWidth() {
			return image.getWidth();
		}

		/**
		 * @see com.cc.framework.ui.model.ImageModel#getSource()
		 */
		public String getSource() {
			return image.getSource();
		}

		/**
		 * @see com.cc.framework.ui.model.ImageModel#getAlternate()
		 */
		public String getAlternate() {
			return image.getAlternate();
		}

		/**
		 * @see com.cc.framework.ui.model.ImageModel#getTooltip()
		 */
		public String getTooltip() {
			return image.getTooltip();
		}

		/**
		 * @see com.cc.framework.ui.WebResourceAccess#getBase()
		 */
		public String getBase() {
			return image.getBase();
		}

		/**
		 * @see com.cc.framework.ui.WebResourceAccess#setBase(java.lang.String)
		 */
		public void setBase(String base) {
			image.setBase(base);
		}
	} 

	/**
	 * Commons Logging instance.
	 */
	private static Log log = LogFactory.getLog(ImageMap.class);

	/**
	 * Collection with all images in the image map in the format
	 * Tupel: (regular expression, image)
	 */
	private Vector mappings = new Vector();

	/**
	 * Name of the ImageMap
	 */
	private String name;

	/**
	 * The base directory for all the images
	 */
	private String base = null;

	/**
	 * Specifies if the image map should be exposed as a java script
	 * object to the client browser (RunAt.CLIENT)
	 */
	private RunAt runat = RunAt.SERVER;

	// ----------------------------------------------
	//          methods
	//	----------------------------------------------

	/**
	 * Constructor for ImageMap
	 */
	public ImageMap() {
		super();
	}

	/**
	 * Adds an image to the image map
	 *
	 * @param	rule	A regular expression
	 * @param	image	The image
	 * @see	com.cc.framework.ui.model.ImageModel
	 */
	public void addImage(String rule, ImageModel image) {
		mappings.add(new ImageMapping(rule, image));
	}

	/**
	 * Returns an image for an expression
	 *
	 * @param	value	Object
	 * @return	The image
	 */
	public ImageModel mapValueToImage(Object value) {

		if (value == null) {
			return null;
		}

		String valueStr = (value == null) ? null : value.toString();

		try {
			for (int i = 0; i < mappings.size(); i++) {
				ImageMapping mapping = (ImageMapping) mappings.get(i);

				RE re = new RE(mapping.getRule());

				if (re.match(valueStr)) {
					return mapping;
				}
			}
		} catch (RESyntaxException rese) {
			log.error("Error mapping image: value=" + valueStr, rese);
		}

		// No Expression matched
		return null;
	}

	/**
	 * Returns a collection of ImageMapping objects
	 * 
	 * @return		ImageModel collection
	 */
	public Collection getMappings() {
		return mappings;
	}

	/**
	 * Returns the base directory for all the images
	 * @return The base directory for all the images
	 */
	public String getBase() {
		return base;
	}

	/**
	 * Returns the name for this ImageMap
	 *
	 * @return  The name for this ImageMap
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the RunAt attribute
	 *
	 * @return The RunAt attribute
	 */
	public RunAt getRunat() {
		return runat;
	}

	/**
	 * Sets the base directory for all the images
	 * @param base	The base directory
	 */
	public void setBase(String base) {
		this.base = base;
	}

	/**
	 * Sets the name for this ImageMap
	 *
	 * @param name	The name for the ImageMap
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the RunAt attribute
	 *
	 * @param runat	The RunAt attribute
	 */
	public void setRunat(RunAt runat) {
		this.runat = runat;
	}

}