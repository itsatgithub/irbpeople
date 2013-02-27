/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/CrumbModel.java,v 1.5 2005/02/16 18:13:27 P001001 Exp $
 * $Revision: 1.5 $
 * $Date: 2005/02/16 18:13:27 $
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
 
package com.cc.framework.ui.model;

import com.cc.framework.security.Permission;

/**
 * Designmodel for a Crumb 
 *
 * @author    <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version   $Revision: 1.5 $
 * @since     1.3
 */
public interface CrumbModel extends DesignModel, ClientHandler, AccessControlled {
	
	/**
	 * Returns the unique key for the Crumb
	 * @return	The id of the crumb
	 */
	public String getCrumbId();

	/**
	 * Sets the Id for the Crumb
	 * @param	crumbid	Unique Id
	 */
	public void setCrumbId(String crumbid);

	/**
	 * Returns the action, which should be prossesed if
	 * the Crumb is selected.
	 * 
	 * @return	String
	 */
	public String getAction();

	/**
	 * Sets the Action, which should be processed if 
	 * the Crumb is selected.
	 * 
	 * @param	action	Action
	 */
	public void setAction(String action);

	/**
	 * Returns the Title
	 * @return	The Title
	 */
	public String getTitle();

	/**
	 * Sets the Title
	 * @param	title	The Title
	 */
	public void setTitle(String title);

	/**
	 * Sets the tooltip text
	 * @param	tooltip		The Tooltip
	 */
	public void setTooltip(String tooltip);

	/**
	 * Returns the tooltip
	 * @return	The tooltip
	 */
	public String getTooltip();

	/**
	 * Assigns the Crumb an Image from an ImageMap
	 * @param	ref	Rule of the ImageMap to match
	 */
	public void setImageRef(String ref);

	/**
	 * Returns the assignment Rule
	 * @return	String
	 */
	public String getImageRef();

	/**
	 * Sets a list with permission for the object.
	 * Permissions are seperated by ';'
	 * 
	 * @param		permission	Permission
	 */
	public void setPermission(Permission permission);

	/**
	 * Marks the crumb as disabled
	 * 
	 * @param	disabled <code>true</code> to disable the crumb
	 */
	public void setDisabled(boolean disabled);

	/**
	 * Checks if the crumb is disabled
	 * 
	 * @return	returns <code>true</code> if the crumb is disabled
	 */
	public boolean isDisabled();

	/**
	 * Returns if the filter is activated (default=true). This means
	 * that all Strings which should be displayed in the HTML page
	 * are html encoded
	 *
	 * @return	<code>true</code> if string will be html encoded;
	 * 			<code>false</code> otherwise. If a <code>null</code> is
	 * 			returned than the filter of the enclosing CrumbControl
	 * 			sholud be used
	 */
	public Boolean filter();

	/**
	 * Activates the html encoding (filter). Default is true. This
	 * means that all Strings which should be displayed in the HTML page
	 * will be html encoded.
	 *
	 * @param		filter <code>true</code> if strings should be html
	 * 				encoded; <code>false</code> otherwise
	 */
	public void setFilter(boolean filter);

	/**
	 * Sets the Locale configuration for this element
	 * 
	 * @param		locale Locale Identifier or <code>true|false</code>
	 */
	public void setLocaleName(String locale);

	/**
	 * Gets the Local Setting for this element
	 * 
	 * @return		Locale Setting
	 */
	public String getLocaleName();
}