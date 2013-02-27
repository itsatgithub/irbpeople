/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/PanelContentDesignModel.java,v 1.9 2005/02/16 18:13:31 P001001 Exp $
 * $Revision: 1.9 $
 * $Date: 2005/02/16 18:13:31 $
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
 * Design model for panels
 *
 * @author   <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version  $Revision: 1.9 $
 * @since    1.0
 */
public interface PanelContentDesignModel extends DesignModel, AccessControlled {

	/**
	 * Adds a panel item
	 * @param	item	PanelItemDesignModel
	 */
	public void addItem(PanelItemDesignModel item);

	/**
	 * Removes a panel item
	 * @param	item	PanelItemDesignModel
	 */
	public void removeItem(PanelItemDesignModel item);

	/**
	 * Returns the image for the content body
	 * @return	ImageModel
	 */
	public ImageModel getImage();

	/**
	 * Returns a collection with the items
	 * @return	PanelItemDesignModel
	 */
	public PanelItemDesignModel[] getItems();

	/**
	 * Return the text wich should be displayed if there
	 * is a "other items" link. This means that not all
	 * items are display in the panel at the same time
	 * 
	 * @return	String
	 */
	public String getMore();

	/**
	 * Return the text wich should be displayed if there
	 * are more items as displayed
	 * 
	 * @param	more	The hint to be display if there are more items	 
	 */
	public void setMore(String more);

	/**
	 * Returns the title
	 * 
	 * @return	String
	 */
	public String getTitle();

	/**
	 * Sets an image for the content body
	 * @param	img	ImageModel
	 */
	public void setImage(ImageModel img);

	/**
	 * Sets the title
	 * @param	title	The title
	 */
	public void setTitle(String title);

	/**
	 * Returns the number of itms
	 * @return	The number of itms
	 */
	public int size();

	/**
	 * Sets the action which should be processed if the
	 * user clicks on the "more link".
	 * 
	 * @param	action	The action to process
	 */
	public void setAction(String action);

	/**
	 * Returns the action
	 * 
	 * @return	The action
	 */
	public String getAction();

	/**
	 * Sets a list with permissions to access this object.
	 * The permission are seperated by ';'.
	 * 
	 * @param		permission	Permission
	 */
	public void setPermission(Permission permission);

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
	 * Returns if the filter is activated (default=true). This means
	 * that all Strings which should be displayed in the HTML page
	 * are html encoded
	 *
	 * @return	<code>true</code> if string will be html encoded;
	 * 			<code>false</code> otherwise. If a <code>null</code> is
	 * 			returned than the filter of the enclosing CrumbControl
	 * 			sholud be used
	 */
	public boolean filter();

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