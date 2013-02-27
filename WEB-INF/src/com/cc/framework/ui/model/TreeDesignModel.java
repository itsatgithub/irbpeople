/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/TreeDesignModel.java,v 1.6 2005/02/16 18:13:31 P001001 Exp $
 * $Revision: 1.6 $
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

import com.cc.framework.ui.ImageMap;

/**
 * This interface defines te visual attributes of a tree. 
 *
 * @author    <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version   $Revision: 1.6 $
 * @since     1.0
 */
public interface TreeDesignModel extends ControlDesignModel, TreeStyle {

	/**
	 * Returns the maximum number of lines that are depicted
	 * simultaneously. When the total number of available lines
	 * in the data model is greater than the number of lines
	 * displayed, buttons are automatically generated for
	 * navigation through pages.
	 *
	 * @return	Number of rows to show per page. -1 means that all rows are displayed.
	 */
	public int getRowCount();

	/**
	 * This attribute is used to specify the maximum number of
	 * lines that are depicted simultaneously.
	 *
	 * @param	rows	The number of rows to show
	 */
	public void setRowCount(int rows);

	/**
	 * Specifies the property using which an image
	 * can be assigned to every Row-Bean
	 * 
	 * @param	property	Name of the property
	 */
	public void setImageProperty(String property);

	/**
	 * Returns the image property
	 * @return	Name of the property
	 */
	public String getImageProperty();

	/**
	 * Specifies the property using which a
	 * label can be assigned to every Row-Bean.
	 * 
	 * @param property Name of the property
	 */
	public void setLabelProperty(String property);

	/**
	 * Returns the Property which should be used
	 * to get the labels for the Nodes
	 * 
	 * @return	 Name of the property		
	 */
	public String getLabelProperty();

	/**
	 * Specifies the name of an Imagemap.
	 * 
	 * @param	map	ImageMap
	 */
	public void setImageMap(ImageMap map);

	/**
	 * Returns the associated ImageMap
	 * @return	ImageMap
	 */
	public ImageMap getImageMap();

	/**
	 * Returns if the filter is activated (default=true). This means
	 * that all Strings which should be displayed in the HTML page
	 * are html encoded
	 *
	 * @return	<code>true</code> if string will be html encoded;
	 * 			<code>false</code> otherwise
	 */
	public boolean filter();

	/**
	 * Activates the html encoding (filter). Default is true. This
	 * means that all Strings which should be displayed in the HTML page
	 * will be html encoded.
	 *
	 * @param		filter <code>true</code> if strings should be html encoded; false otherwise
	 */
	public void setFilter(boolean filter);

	/**
	 * Returns the maximal number of input characters
	 * which can be inserted into the text field.
	 * 
	 * @return	Maximum number of input characters to accept
	 */
	public int getMaxLength();

	/**
	 * Sets the maximal number of input characters
	 * which can be inserted into the text field.
	 * 
	 * @param		max Maximum number of input characters to accept
	 */
	public void setMaxLength(int max);
}
