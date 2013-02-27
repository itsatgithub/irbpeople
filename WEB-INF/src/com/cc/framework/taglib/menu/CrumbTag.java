/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/menu/CrumbTag.java,v 1.12 2005/08/02 19:13:05 P001002 Exp $
 * $Revision: 1.12 $
 * $Date: 2005/08/02 19:13:05 $
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

package com.cc.framework.taglib.menu;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import com.cc.framework.taglib.InnerTag;
import com.cc.framework.taglib.ScriptTagSupport;
import com.cc.framework.taglib.TagHelp;
import com.cc.framework.ui.model.ClientHandler;
import com.cc.framework.ui.model.CrumbModel;
import com.cc.framework.ui.model.imp.CrumbModelImp;

/**
 * Tag-Handler for the <code>crumb</code> Tag.
 * <p>
 * Generates a crumb for a crumb control element.
 * The &gt;crumb&lt;-tag may only be specified within a &gt;crumbs&lt;-tag.
 *
 * @author      <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version     $Revision: 1.12 $
 * @since       1.3
 */
public class CrumbTag extends ScriptTagSupport implements InnerTag {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -8540834794408914859L;
	
	/**
	 * DesignModel of the Crumb
	 */
	private CrumbModel designModel = null;

	/**
	 * Constructor
	 */
	public CrumbTag() {
		super();
	}

	/**
	 * Returns the CrumbModel
	 *
	 * @return	CrumbModel
	 */
	protected CrumbModel getCrumbModel() {
		if (designModel == null) {
			designModel = new CrumbModelImp();
		}
		
		return designModel;
	}

	/**
	 * Releases the design model model
	 */
	protected void releaseCrumbModel() {
		designModel = null;
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#setPageContext(PageContext pc)
	 */
	public void setPageContext(PageContext pc) {
		super.setPageContext(pc);

		// release the design model
		releaseCrumbModel();
	}

	/**
	 * @see	javax.servlet.jsp.tagext.Tag#release()
	 */
	public void release() {
		super.release();
		
		releaseCrumbModel();
	}

	/**
	 * @see com.cc.framework.taglib.ScriptSupport#getClientHandler()
	 */
	public ClientHandler getClientHandler() {
		return getCrumbModel();
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException {

		CrumbContainer container = (CrumbContainer) findAncestorWithClass(this, CrumbContainer.class);

		// a crumb-Tag must be embedded in a CrumbContainer
		// this can be a crumbs-Tag
		if (container == null) {
			throw new JspException("CrumbTag.doStartTag(): Tag not used inside a CrumbContainer!");
		}

		// the model is passed to the enclosing tag
		container.addCrumb(getCrumbModel());

		return SKIP_BODY;
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException {
		releaseCrumbModel();

		return EVAL_PAGE;
	}

	/**
	 * The unique identification of the crumb.
	 *
	 * @param	id	The id of the crumb
	 */
	public void setCrumbid(String id) {
		getCrumbModel().setCrumbId(id);
	}

	/**
	 * The title of the crumb.
	 *
	 * @param	title	The title of the crumb.
	 */
	public void setTitle(String title) {
		getCrumbModel().setTitle(title);
	}

	/**
	 * Tooltip Text that is displayed in a Popup window
	 * when the user moves the mouse over the crumb.
	 *
	 * @param	tooltip		The tooltip
	 */
	public void setTooltip(String tooltip) {
		getCrumbModel().setTooltip(tooltip);
	}

	/**
	 * Sets the Reference to an Image in an ImageMap,
	 * which should be display in front of the Label
	 * @param imageRef	Reference to an Image in an ImageMap
	 */
	public void setImageref(String imageRef) {
		getCrumbModel().setImageRef(imageRef);
	}

	/**
	 * Sets the associated action
	 * @param action	Action
	 */
	public void setAction(String action) {
		getCrumbModel().setAction(action);
	}

	/**
	 * With this attribute, access to the element can be restricted.
	 *
	 * <p>Authorizations are checked using the com.cc.framework.security.Principal
	 * object in the user session. The principal object is registered in the session
	 * with the method com.cc.framework.security.SecurityUtil#registerPrincipal(HttpSession, Principal).
	 * It is made available by the application developer by implementing the principal
	 * interface. In this manner, any authorization system can be very easily connected
	 * within the framework.</p>
	 * <p>Authorizations are always specified in the form of an Access Control List (<b>ACL</b>).
	 * What is involved here is a semicolon-delimited list with individual authorizations.
	 * The framweork supports the following authorization types, which, however, can be
	 * expanded at will by the application developer:
	 * <dl>
	 *		<dt>Literal</dt><dd>true|false -&gt; com.cc.framework.security.StaticPermission</dd>
	 *		<dt>Role</dt><dd>#rolename -&gt; com.cc.framework.security.RoleBasedPermission</dd>
	 *		<dt>Function </dt><dd>$functionname -&gt; com.cc.framework.security.FunctionBasedPermission</dd>
	 * </dl>
	 *
	 * @param	permission		The permission to set for this control
	 * @throws	JspException	If the argument can't be converted into an object of type Permission
	 */
	public void setPermission(String permission) throws JspException {
		getCrumbModel().setPermission(TagHelp.toPermission(permission));
	}

	/**
	 * Marks the crumb as disabled
	 *
	 * @param	disabled		Boolean String
	 * @throws	JspException	If the argument can't be converted into an object of type boolean
	 */
	public void setDisabled(String disabled) throws JspException {
		getCrumbModel().setDisabled(TagHelp.toBoolean(disabled));
	}

	/**
	 * Sets HTML filtering on or off
	 *
	 * @param	filter			Boolean String
	 * @throws	JspException	If the argument can't be converted into an
	 * 							object of type boolean
	 */
	public void setFilter(String filter) throws JspException {
		getCrumbModel().setFilter(TagHelp.toBoolean(filter));
	}

	/**
	 * Sets the Loacel configuration for this element
	 *
	 * @param	locale Locale Identifier or <code>true|false</code>
	 */
	public void setLocale(String locale) {
		getCrumbModel().setLocaleName(locale);
	}
}