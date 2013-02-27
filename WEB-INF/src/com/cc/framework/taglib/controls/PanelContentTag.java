/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/controls/PanelContentTag.java,v 1.17 2005/07/08 14:17:22 P001002 Exp $
 * $Revision: 1.17 $
 * $Date: 2005/07/08 14:17:22 $
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

package com.cc.framework.taglib.controls;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import com.cc.framework.taglib.ImageContainerTag;
import com.cc.framework.taglib.InnerTag;
import com.cc.framework.taglib.TagHelp;
import com.cc.framework.ui.model.ImageModel;
import com.cc.framework.ui.model.PanelContentDesignModel;
import com.cc.framework.ui.model.PanelItemDesignModel;
import com.cc.framework.ui.model.imp.PanelContentDesignModelImp;

/**
 * Tag-Handler for the <code>panel-content</code> Tag.
 *
 * @author      <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version     $Revision: 1.17 $
 * @since       1.0
 */
public class PanelContentTag extends TagSupport implements ImageContainerTag, InnerTag {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -5147456204865173262L;

	/**
	 * DesignModel
	 */
	private PanelContentDesignModel designModel	= null;

	/**
	 * Constructor
	 */
	public PanelContentTag() {
		super();
	}

	/**
	 * Returns the PanelContentDesignModel
	 *
	 * @return	PanelContentDesignModel
	 */
	protected PanelContentDesignModel getDesignModel() {
		if (designModel == null) {
			designModel = new PanelContentDesignModelImp();
		}
		
		return designModel;
	}

	/**
	 * Releases the PanelContentDesignModel
	 */
	protected void releaseDesignModel() {
		designModel = null;
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#setPageContext(PageContext pc)
	 */
	public void setPageContext(PageContext pc) {
		super.setPageContext(pc);

		// initialize variables
		releaseDesignModel();
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#release()
	 */
	public void release() {
		super.release();

		// initialize variables
		releaseDesignModel();
	}

	/**
	 * Exposes the PanelContentDesignModel instance as a scripting variable
	 * to the Tag body
	 *
	 * @param		content The Design Model
	 */
	protected void exposeScriptingVariable(PanelContentDesignModel content) {
		if (getId() != null) {
			pageContext.setAttribute(getId(), content, PageContext.PAGE_SCOPE);
		}
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException {

		Object parent = findAncestorWithClass(this, PanelTag.class);

		// a content tag must be nested within a PanelTag
		if (parent == null) {
			throw new JspException("PanelContentTag must be nested inside a PanelTag");
		}

		PanelContentDesignModel designModel = getDesignModel();

		// Das neue Modell für den Content-Bereich des Panels wird
		// nun and das umschliessende Tag übergeben.
		PanelTag panel = (PanelTag) parent;
		panel.addContent(designModel);

		// Expose the content as a scripting variable
		exposeScriptingVariable(designModel);

		return EVAL_BODY_INCLUDE;
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException {
		// Release the current model
		releaseDesignModel();

		return EVAL_PAGE;
	}

	/**
	 * Adds a panel item
	 * @param	item	PanelItemDesignModel
	 */
	public void addItem(PanelItemDesignModel item) {
		getDesignModel().addItem(item);
	}

	/**
	 * @see com.cc.framework.taglib.ImageContainerTag#setImage(com.cc.framework.ui.model.ImageModel)
	 */
	public void setImage(ImageModel image) {
		getDesignModel().setImage(image);
	}

	/**
	 * This attribute guides the Painter to a "more..." to draw
	 * an entry at the end of the group. The user's attention
	 * should thus be drawn to additional entries.
	 *
	 * @param	more	More
	 */
	public void setMore(String more) {
		getDesignModel().setMore(more);
	}

	/**
	 * The group title.
	 *
	 * @param	title	The group title.
	 */
	public void setTitle(String title) {
		getDesignModel().setTitle(title);
	}

	/**
	 * Specifies the Struts Action that is to be
	 * called in case of control element events.
	 * This attribute need not be specified if the tag
	 * is included within a Struts <html:form> tag.
	 * In that case, the name of the action is
	 * determined using the Struts Form Bean.
	 *
	 * @param	action	Action to perform
	 */
	public void setAction(String action) {
		getDesignModel().setAction(action);
	}

	/**
	 * With this attribute, access to the element can be restricted.
	 * Authorizations are checked using the com.cc.framework.security.Principal
	 * object in the user session.
	 * Authorizations are always specified in the form of an Access Control List (ACL).
	 * What is involved here is a semicolon-delimited list with individual
	 * authorizations.
	 *
	 * @param		permission	String witch stands for the required
	 * 				authorization
	 * @throws		JspException If the Argument can't be converted	to an object of type permission
	 * @see com.cc.framework.security.SecurityUtil
	 */
	public void setPermission(String permission) throws JspException {
		getDesignModel().setPermission(TagHelp.toPermission(permission));
	}

	/**
	 * Tooltip Text that is displayed in a Popup window
	 * when the user moves the mouse over the title.
	 *
	 * @param	tooltip	Tooltip
	 */
	public void setTooltip(String tooltip) {
		getDesignModel().setTooltip(tooltip);
	}

	/**
	 * Sets HTML filtering on or off
	 *
	 * @param	filter			Boolean String
	 * @throws	JspException	If the argument can't be converted into an
	 * 							object of type boolean
	 */
	public void setFilter(String filter) throws JspException {
		getDesignModel().setFilter(TagHelp.toBoolean(filter));
	}

	/**
	 * Sets the Loacel configuration for this element
	 *
	 * @param	locale Locale Identifier or <code>true|false</code>
	 */
	public void setLocale(String locale) {
		getDesignModel().setLocaleName(locale);
	}
}