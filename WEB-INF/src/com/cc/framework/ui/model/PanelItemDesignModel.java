/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/PanelItemDesignModel.java,v 1.9 2005/02/16 18:13:32 P001001 Exp $
 * $Revision: 1.9 $
 * $Date: 2005/02/16 18:13:32 $
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
 * The design model for a PanelItem
 *
 * @author    <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version   $Revision: 1.9 $
 * @since     1.1
 */
public interface PanelItemDesignModel extends DesignModel, AccessControlled, ClientHandler {

	/**
	 * Returns the title of a panel item
	 * 
	 * @return	String
	 */
	public String getTitle();

	/**
	 * Returns the detail Text
	 * @return	String
	 */
	public String getDetail();

	/**
	 * Sets the detail Text
	 * @param	detail	Detail
	 */
	public void setDetail(String detail);

	/**
	 * Returns the Title
	 * @param	title	Title
	 */
	public void setTitle(String title);

	/**
	 * Sets the Tooltip
	 * @param	tooltip	Tooltip
	 */
	public void setTooltip(String tooltip);

	/**
	 * Returns the Tooltip
	 * @return	String
	 */
	public String getTooltip();

	/**
	 * Returns the Action which should be
	 * processed by the Item
	 * @return	String
	 */
	public String getAction();

	/**
	 * Sets the Action
	 * @param	action	Action
	 */
	public void setAction(String action);

	/**
	 * Sets a list with permissions to access this object.
	 * The permission are seperated by ';'.
	 * 
	 * @param		permission	Permission
	 */
	public void setPermission(Permission permission);
	
	/**
	 * Sets the target where the document referenced
	 * will appear (like _blank, _parent, ...).
	 * @param target	The target
	 */
	public void setTarget(String target);
	
	/**
	 * Returns the target
	 * 
	 * @return	The target
	 */
	public String getTarget();

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