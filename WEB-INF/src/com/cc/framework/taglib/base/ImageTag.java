/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/base/ImageTag.java,v 1.26 2005/11/13 13:39:53 P001002 Exp $
 * $Revision: 1.26 $
 * $Date: 2005/11/13 13:39:53 $
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

package com.cc.framework.taglib.base;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.ecs.html.IMG;

import com.cc.framework.taglib.ImageContainerTag;
import com.cc.framework.taglib.TagHelp;
import com.cc.framework.ui.ImageMap;
import com.cc.framework.ui.model.ImageModel;
import com.cc.framework.ui.model.imp.ImageModelImp;
import com.cc.framework.ui.painter.PainterFactory;
import com.cc.framework.ui.painter.PainterHelp;

/**
 * Tag handler for the <code>image</code> Tag.
 * The tag generates an HTML-image element.
 * The image tag can also be used in conjunction with
 * other tags of the framework. The surrounding tag is
 * responsible in this case for the depiction of the image.
 * Thus, the image is not directly written into the HTML-page.
 * The tag-handler of the surrounding tag must implement the
 * com.cc.framework.taglib.ImageContainerTag Interface.
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.26 $
 * @since      1.0
 *
 */
public class ImageTag extends TagSupport {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 4646087327406416238L;

	/**
	 * The image model
	 */
	private ImageModelImp model = null;

	/**
	 * A direct set value
	 */
	private Object directValue = null;

	/**
	 * The name of the bean containing our underlying property.
	 */
	private String name = null;

	/**
	 * The name of the property.
	 */
	private String property = null;

	/**
	 * The name of the imagemap.
	 */
	private ImageMap imagemap = null;

	/**
	 * Constructor for ImageTag
	 */
	public ImageTag() {
		super();
	}

	/**
	 * Returns the image model
	 *
	 * @return	ImageModel
	 */
	protected ImageModelImp getImage() {
		if (model == null) {
			model = new ImageModelImp();
		}

		return model;
	}

	/**
	 * Releases the image model
	 */
	protected void releaseImage() {
		model = null;
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#release()
	 */
	public void release() {
		super.release();

		releaseImage();
		name		= null;
		property	= null;
		imagemap	= null;
		directValue = null;
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#setPageContext(PageContext pc)
	 */
	public void setPageContext(PageContext pc) {
		super.setPageContext(pc);

		// initialize variables
		releaseImage();
		name		= null;
		property	= null;
		imagemap	= null;
		directValue = null;
	}

	/**
	 * Retrieves the bean which holds the Display Data or
	 * Control Instance
	 *
	 * @return		Bean (Control instance or DataModel)
	 * @throws		JspException Is thrown when the bean could
	 * 				not be found
	 */
	protected Object lookupBean() throws JspException {
		return TagHelp.lookupBean(pageContext, this, name, property, null);
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException {

		ImageModel image = null;

		Object value = getDirectValue();

		if (value == null) {
			// get the datamodel
			value = lookupBean();
		}

		if ((imagemap != null) && (value != null)) {
			image = imagemap.mapValueToImage(value);
		}

		if (image == null) {
			image = getImage();
		}

		// if the tag is embedded into an image container, we only
		// add the model to the conatainer
		if ((getParent() != null) && (getParent() instanceof ImageContainerTag)) {
			ImageContainerTag parent = (ImageContainerTag) getParent();

			parent.setImage(image);
		} else {
			// otherwise write the image directly to the
			// Jsp Page
			IMG img = PainterHelp.createImage(pageContext, image);

			JspWriter writer = pageContext.getOut();
			img.output(writer);
		}

		// release data model
		releaseImage();

		return EVAL_PAGE;
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException {
		return SKIP_BODY;
	}

	/**
	 * Sets the direct value for this image. This will overwrite the value of
	 * the <code>property</code> attribute
	 *
	 * @param value
	 *            The image value
	 */
	public void setValue(String value) {
		this.directValue = value;
	}

	/**
	 * Returns a direct set value
	 *
	 * @return Value
	 */
	public Object getDirectValue() {
		return directValue;
	}

	/**
	 * Specifies the name of the Java-Bean. The Java-Bean must be stored in the
	 * given scope.
	 *
	 * @param name
	 *            Name of the Bean
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the Property-Attribute
	 *
	 * @param property
	 *            The Property-Attribute
	 */
	public void setProperty(String property) {
		this.property = property;
	}

	/**
	 * Specifies the name of an Imagemap which must be saved in the request. The
	 * values that the property returns are mapped to the entries of this
	 * Imagemap. The mapping is done with the help of the regular expression,
	 * which is assigned to every entry of the Imagemap.
	 *
	 * @param mapName
	 *            The ImageMap to assign
	 * @throws JspException
	 *             If the ImageMap can not be found
	 */
	public void setImagemap(String mapName) throws JspException {
		this.imagemap = TagHelp.lookupImageMap(pageContext, mapName);
	}

	/**
	 * Sets a tooltip text for this image
	 *
	 * @param tooltip
	 *            Tooltip text
	 */
	public void setTooltip(String tooltip) {
		getImage().setTooltip(tooltip);
	}

	/**
	 * Sets the text to display if the image can't be displayed.
	 *
	 * @param alt
	 *            String the alternative text.
	 */
	public void setAlt(String alt) {
		getImage().setAlternate(alt);
	}

	/**
	 * Sets the height of the image
	 *
	 * @param height
	 *            int the height of the image
	 * @throws JspException
	 *             If the argument can not be converted into an integer value
	 */
	public void setHeight(String height) throws JspException {
		getImage().setHeight(TagHelp.toInt(height));
	}

	/**
	 * Sets the image <b>src</b> attribute
	 *
	 * @param src
	 *            <b>src</b> attribute
	 */
	public void setSrc(String src) {
		getImage().setSource(src);
	}

	/**
	 * Uses a image resource from the applications resource maps to setup the
	 * image
	 *
	 * @param resource
	 *            resource key
	 */
	public void setResource(String resource) {
		ImageModel image = PainterFactory.getImageResource(pageContext,
				resource);

		if (image != null) {
			getImage().setFromImage(image);
		}
	}

	/**
	 * Sets the image width
	 *
	 * @param width
	 *            the image width
	 * @throws JspException
	 *             If the argument can not be converted into an integer value
	 */
	public void setWidth(String width) throws JspException {
		getImage().setWidth(TagHelp.toInt(width));
	}
}