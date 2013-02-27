/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/util/ImageMapTag.java,v 1.16 2005/07/08 14:15:19 P001002 Exp $
 * $Revision: 1.16 $
 * $Date: 2005/07/08 14:15:19 $
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

package com.cc.framework.taglib.util;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import com.cc.framework.common.InvalidEnumType;
import com.cc.framework.taglib.WebResourceTag;
import com.cc.framework.ui.ImageMap;
import com.cc.framework.ui.RunAt;
import com.cc.framework.ui.model.ImageModel;

/**
 * Tag-Handler for the <code>ImageMap</code> Tag.
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.16 $
 * @since      1.0
 */
public class ImageMapTag extends TagSupport implements WebResourceTag {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -4761465762241172958L;

	/**
	 * Instance of the ImageMap
	 */
	private ImageMap map = null;

	/**
	 * Name to store the ImageMap in the Page-Kontext
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

	/**
	 * Constructor
	 */
	public ImageMapTag() {
		super();
	}

	/**
	 * @see	javax.servlet.jsp.tagext.Tag#setPageContext(PageContext pc)
	 */
	public void setPageContext(PageContext pc) {
		super.setPageContext(pc);

		map		= new ImageMap();
		name	= null;
		base	= null;
		runat	= RunAt.SERVER;
	}

	/**
	 * @see	javax.servlet.jsp.tagext.Tag#release()
	 */
	public void release() {
		map		= null;
		name	= null;
		base	= null;
		runat	= RunAt.SERVER;
	}

	/**
	 * @see	javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException {

		return EVAL_BODY_INCLUDE;
	}

	/**
	 * @see	javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException {

		map.setName(name);
		map.setBase(base);
		map.setRunat(runat);

		pageContext.getRequest().setAttribute(name, map);

		if (RunAt.CLIENT.equals(runat)) {
			doCreateClientScript();
		}

		return EVAL_PAGE;
	}

	/**
	 * Creates the client side javascript object for
	 * the image map which is used for the clientside tree.
	 *
	 * @throws JspException    A generic exception known by the JSP engine
	 */
	protected void doCreateClientScript() throws JspException {

		try {
			JspWriter writer = pageContext.getOut();

			String mapName = name; // map.hashCode();

			StringBuffer out = new StringBuffer();

			out
				.append("var ")
				.append(mapName)
				.append(" = new ImageMap('")
				.append(mapName)
				.append("', ")
				.append(RunAt.CLIENT.equals(runat) ? "RunAt.CLIENT" : "RunAt.SERVER")
				.append(", '")
				.append((null != base) ? base : "")
				.append("');");

			// no append the ImageMap.ImageMapping's
			Collection mappings = map.getMappings();

			Iterator i = mappings.iterator();
			while (i.hasNext()) {
				ImageMap.ImageMapping mapping = (ImageMap.ImageMapping) i.next();

				out
					.append(mapName)
					.append(".addImageMapping(new ImageMapping('")
					.append(mapping.getRule())
					.append("', '")
					.append(mapping.getSource())
					.append("', ")
					.append(mapping.getWidth())
					.append(", ")
					.append(mapping.getHeight())
					.append("));");
			}

			// create the script block
			StringBuffer script = new StringBuffer();

			script
				.append("<script>")
				.append(out)
				.append("</script>");

			// write
			writer.write(script.toString());

		} catch (IOException io) {
			throw new JspException(io.getMessage());
		}
	}


	/**
	 * Adds an image to the ImageMap
	 *
	 * @param	key		Key
	 * @param	image	The image to add
	 */
	public void addImage(String key, ImageModel image) {
		map.addImage(key, image);
	}

	/**
	 * Sets the name
	 *
	 * @param	name	The name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @see com.cc.framework.taglib.WebResourceTag#setBase(java.lang.String)
	 */
	public void setBase(String base) {
		this.base = base;
	}

	/**
	 * @return		Base Directory
	 */
	public String getBase() {
		return base;
	}

	/**
	 * This attribute specifies whether, for the imagemap
	 * should be exposed as a java scripting object
	 *
	 * @param	runat			Specifies whether the imagemap
	 * 			should be exposed as a javascript object
	 * @throws	JspException	If the argument can't be converted
	 * 			to an object of type com.cc.framework.ui.RunAt
	 * @see	com.cc.framework.ui.RunAt
	 */
	public void setRunat(String runat) throws JspException {
		try {
			this.runat = RunAt.parse(runat);
		} catch (InvalidEnumType iet) {
			throw new JspException(iet.getMessage());
		}
	}

	/**
	 * This property specifies whether, for the imagemap
	 * should be exposed as a java scripting object
	 *
	 * @return		<code>RunAt.CLIENT</code> if a java script
	 * 				object should be created
	 */
	public RunAt getRunAt() {
		return runat;
	}
}