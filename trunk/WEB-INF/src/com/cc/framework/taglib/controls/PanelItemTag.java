/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/controls/PanelItemTag.java,v 1.18 2005/07/08 14:17:22 P001002 Exp $
 * $Revision: 1.18 $
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

import com.cc.framework.taglib.InnerTag;
import com.cc.framework.taglib.ScriptTagSupport;
import com.cc.framework.taglib.TagHelp;
import com.cc.framework.ui.model.ClientHandler;
import com.cc.framework.ui.model.PanelItemDesignModel;
import com.cc.framework.ui.model.imp.PanelItemDesignModelImp;

/**
 * Tag-Handler for the <code>panel-item</code> Tag.
 *
 * @author      <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version     $Revision: 1.18 $
 * @since       1.0
 */
public class PanelItemTag extends ScriptTagSupport implements InnerTag {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -8305396042511961704L;

	/**
	 * DesignModel for the PanelItem
	 */
	private PanelItemDesignModel designModel	= null;

	/**
	 * Constructor
	 *
	 */
	public PanelItemTag() {
		super();
	}

	/**
	 * Returns the PanelItemDesignModel
	 *
	 * @return	PanelItemDesignModel
	 */
	protected PanelItemDesignModel getDesignModel() {
		if (designModel == null) {
			designModel = new PanelItemDesignModelImp();
		}
		
		return designModel;
	}

	/**
	 * Releases the PanelItemDesignModel
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
	 * @see com.cc.framework.taglib.ScriptSupport#getClientHandler()
	 */
	public ClientHandler getClientHandler() {
		return getDesignModel();
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException {

		Object parent = findAncestorWithClass(this, PanelContentTag.class);

		// a column tag must be nested within a PanelTag
		if (parent == null) {
			throw new JspException("PanelItemTag must be nested inside a PanelContentTag");
		}

		// the new model is now added to the enclosing tag
		PanelContentTag content = (PanelContentTag) parent;
		content.addItem(getDesignModel());

		// no body
		return SKIP_BODY;
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException {
		// Release the panel item
		releaseDesignModel();

		return EVAL_PAGE;
	}

	/**
	 * An additional descriptive text that is output
	 * below the title.
	 *
	 * @param	detail	Detail
	 */
	public void setDetail(String detail) {
		getDesignModel().setDetail(detail);
	}

	/**
	 * The title of the item. It is mostly used as a
	 * hyperlink for calling the specified action.
	 *
	 * @param	title	Title
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

	 * @param	action	The action to perform
	 */
	public void setAction(String action) {
		getDesignModel().setAction(action);
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
	 * Sets the target attribute
	 * @param target	the target attribute
	 */
	public void setTarget(String target) {
		getDesignModel().setTarget(target);
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