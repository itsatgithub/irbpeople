/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/TabDesignModel.java,v 1.10 2005/02/16 18:13:28 P001001 Exp $
 * $Revision: 1.10 $
 * $Date: 2005/02/16 18:13:28 $
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
import com.cc.framework.ui.Color;

/**
 * Designmodel for a tab 
 *
 * @author    <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version   $Revision: 1.10 $
 * @since     1.0
 */
public interface TabDesignModel extends DesignModel, ClientHandler, AccessControlled {
	
	/**
	 * Returns the unique key for the TabPage
	 * @return	The id of the tabpage
	 */
	public String getTabId();

	/**
	 * Sets the Id for the TabPage
	 * @param	tabid	Unique Id
	 */
	public void setTabId(String tabid);

	/**
	 * Returns the action, which should be prossesed if
	 * the TabPage is selected. This applys only to TabSets
	 * which did not act as form elements
	 * 
	 * @return	String
	 */
	public String getAction();

	/**
	 * Sets the Action, which should be processed if 
	 * a TabPage is selected. This applys only to TabSets
	 * which did not act as Formelements.
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
	 * Returns if the tab can be selected
	 * 
	 * @return  boolean	<code>true</code> if the TabPage can be selected;
	 * 					<code>false</code> otherwise 
	 */
	public boolean isEnabled();

	/**
	 * Sets the enable Flag
	 * 
	 * @param	enable	<code>false</code> if the TabPage can not be selected
	 */
	public void setEnable(boolean enable);

	/**
	 * Sets the Content (Name of the JSP) of the TagPage
	 * @param	content	Name of the JSP Page
	 */
	public void setContent(String content);

	/**
	 * Returns the Content of the TabPage
	 * @return	String
	 */
	public String getContent();

	/**
	 * Sets the HTML-String which should be displayed
	 * as Content of the TabPage. If the Tab-Tag containes a Body
	 * you can not assign a JSP-Page.
	 * @param	body	HTML-Code 
	 */
	public void setBody(String body);

	/**
	 * Returns the HTML-String which should be displayed
	 * as Content of the TabPage 
	 * @return	String
	 */
	public String getBody();

	/**
	 * sets the Background Color
	 * @param	bgcolor	Color
	 */
	public void setBgColor(Color bgcolor);

	/**
	 * Returns the Background Color
	 * @return	Color 
	 */
	public Color getBgColor();

	/**
	 * Assigns the TapPage an Image from an ImageMap
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
	public void setTarget(String target);

	/**
	 * Returns where the document referenced will appear.
	 *
	 * @return	String
	 */
	public String getTarget();

}
