/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/controls/TabTag.java,v 1.20 2005/07/08 14:17:45 P001002 Exp $
 * $Revision: 1.20 $
 * $Date: 2005/07/08 14:17:45 $
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
import javax.servlet.jsp.tagext.BodyContent;

import com.cc.framework.taglib.InnerTag;
import com.cc.framework.taglib.ScriptBodyTagSupport;
import com.cc.framework.taglib.TagHelp;
import com.cc.framework.ui.RunAt;
import com.cc.framework.ui.model.ClientHandler;
import com.cc.framework.ui.model.TabDesignModel;
import com.cc.framework.ui.model.imp.TabDesignModelImp;

/**
 * Tag-Handler for the <code>tab</code> Tag.
 * <p>
 * Generates a tab for a tabset control element.
 * The contents of the tab can be specified either
 * directly in the tag-body or in the form of a JSP-Include.
 * The <tab>-tag may only be specified within a <tabset>- or <tabbar>-tag.
 *
 * @author      <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version     $Revision: 1.20 $
 * @since       1.0
 */
public class TabTag extends ScriptBodyTagSupport implements InnerTag {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 9174628450649937336L;

	/**
	 * DesignModel of the TabPage
	 */
	private TabDesignModel designModel = null;

	/**
	 * Constructor
	 */
	public TabTag() {
		super();
	}

	/**
	 * Returns the TabDesignModel
	 *
	 * @return	TabDesignModel
	 */
	protected TabDesignModel getTabDesignModel() {
		if (designModel == null) {
			designModel = new TabDesignModelImp();
		}
		
		return designModel;
	}

	/**
	 * Releases the design model model
	 */
	protected void releaseTabDesignModel() {
		designModel = null;
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#setPageContext(PageContext pc)
	 */
	public void setPageContext(PageContext pc) {
		super.setPageContext(pc);

		// release the design model
		releaseTabDesignModel();
	}

	/**
	 * @see	javax.servlet.jsp.tagext.Tag#release()
	 */
	public void release() {
		super.release();
		
		releaseTabDesignModel();
	}

	/**
	 * @see com.cc.framework.taglib.ScriptSupport#getClientHandler()
	 */
	public ClientHandler getClientHandler() {
		return getTabDesignModel();
	}

	/**
	 * Exposes the TabDesignModel instance as a scripting variable
	 * to the Tag body
	 *
	 * @param		tab The Design Model
	 */
	protected void exposeScriptingVariable(TabDesignModel tab) {
		if (getId() != null) {
			pageContext.setAttribute(getId(), tab, PageContext.PAGE_SCOPE);
		}
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException {

		TabContainer container = (TabContainer) findAncestorWithClass(this, TabContainer.class);

		// a tab-Tag must be embedded in a TabContainer
		// this can be a tabset or a tabbar
		if (container == null) {
			throw new JspException("TabTag.doStartTag(): Tag not used inside a TabContainer!");
		}

		TabDesignModel designModel = getTabDesignModel();

		// the model is passed to the enclosing tag
		container.addTab(designModel);

		// The tabset can have an additional body,
		// so check for tabset
		if (container instanceof  TabsetTag) {
			TabsetTag parent = (TabsetTag) container;

			// if a content is specified the body is ignored
			if (designModel.getContent() != null) {
				return SKIP_BODY;
			}

			// The body only must be evaluated if the control works without
			// serverroundtrips
			if (RunAt.CLIENT.equals(parent.getDesignModel().getRunAt()) || parent.isSelected(designModel)) {

				// Expose the tab page as a scripting variable
				exposeScriptingVariable(designModel);

				return EVAL_BODY_BUFFERED;
			}
		}

		return SKIP_BODY;
	}

	/**
	 * @see javax.servlet.jsp.tagext.IterationTag#doAfterBody()
	 */
	public int doAfterBody() throws JspException {

		BodyContent body = getBodyContent();

		if (body != null) {
			String contentBody = body.getString().trim();

			if (!"".equals(contentBody)) {
				getTabDesignModel().setBody(contentBody);
			}
		}

		return SKIP_BODY;
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException {
		// Release the current tab
		releaseTabDesignModel();

		return EVAL_PAGE;
	}

	/**
	 * @see javax.servlet.jsp.tagext.TagSupport#setId(java.lang.String)
	 */
	public void setId(String id) {
		super.setId(id);

		// For backward combatibility
		if (getTabDesignModel().getTabId() == null) {
			getTabDesignModel().setTabId(id);
		}
	}

	/**
	 * The unique identification of the tab. With this id,
	 * the control element determines whether the tab is selected.
	 *
	 * @param	id	The id of the TabPage
	 */
	public void setTabid(String id) {
		getTabDesignModel().setTabId(id);
	}

	/**
	 * The title of the tab.
	 *
	 * @param	title	The title of the tab.
	 */
	public void setTitle(String title) {
		getTabDesignModel().setTitle(title);
	}

	/**
	 * Tooltip Text that is displayed in a Popup window
	 * when the user moves the mouse over the tab.
	 *
	 * @param	tooltip		The tooltip
	 */
	public void setTooltip(String tooltip) {
		getTabDesignModel().setTooltip(tooltip);
	}

	/**
	 * This attribute specifies whether the tab can be selected
	 *
	 * @param	enable			<code>false</code> if the TabPage can not be selected
	 * @throws	JspException	if the Argument can not be converted into a boolean value
	 */
	public void setEnable(String enable) throws JspException {
		getTabDesignModel().setEnable(TagHelp.toBoolean(enable));
	}

	/**
	 * Sets the Page to be included
	 * @param content	Page to be included (e.g. Pag1.jsp)
	 */
	public void setContent(String content) {
		getTabDesignModel().setContent(content);
	}

	/**
	 * Sets the Background-Color of the TabPage
	 * @param bgcolor	Background-Color
	 */
	public void setBgcolor(String bgcolor) {
		getTabDesignModel().setBgColor(TagHelp.toColor(bgcolor));
	}

	/**
	 * Sets the Reference to an Image in an ImageMap,
	 * which should be display in front of the Label
	 * @param imageRef	Reference to an Image in an ImageMap
	 */
	public void setImageref(String imageRef) {
		getTabDesignModel().setImageRef(imageRef);
	}

	/**
	 * Sets the associated action
	 * @param action	Action
	 */
	public void setAction(String action) {
		getTabDesignModel().setAction(action);
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
	 * @throws	JspException	If the argument can't be converted intoan object of type Permission
	 */
	public void setPermission(String permission) throws JspException {
		getTabDesignModel().setPermission(TagHelp.toPermission(permission));
	}

	/**
	 * Sets the target where the document referenced will appear.
	 * (like _blank, _parent, ...). If you use this method each
	 * hyperlinks in each row will use the same target value.
	 * If you want to use different targets you have to
	 * use a property which gets the target from your datamodel.
	 * Therefore you can specify the property with the
	 * setTargetProperty() method.
	 *
	 * @param	target	The target where the document referenced will appear
	 */
	public void setTarget(String target) {
		getTabDesignModel().setTarget(target);
	}
}