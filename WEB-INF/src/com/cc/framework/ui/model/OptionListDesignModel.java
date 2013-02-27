/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/OptionListDesignModel.java,v 1.8 2005/08/02 19:13:53 P001002 Exp $
 * $Revision: 1.8 $
 * $Date: 2005/08/02 19:13:53 $
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


/**
 * Designmodel for the option list
 * 
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version $Revision: 1.8 $
 * @since 1.0
 */
public interface OptionListDesignModel extends DesignModel {

	/**
	 * Returns the label of an additional empty-entry
	 * 
	 * @return Label for the additional empty-entry
	 */
	public String getEmpty();

	/**
	 * Sets the label of an additional empty-entry
	 * 
	 * @param empty
	 *            Label for the additional empty-entry
	 */
	public void setEmpty(String empty);

	/**
	 * Returns the name of the property which returns the key of the option.
	 * 
	 * @return Name of the property
	 */
	public String getKeyProperty();

	/**
	 * Sets the name of the property which returns the key of the option.
	 * 
	 * @param property
	 *            Name of the property
	 */
	public void setKeyProperty(String property);

	/**
	 * Returns the name of the property which supplies the display text of the
	 * option.
	 * 
	 * @return Name of the property
	 */
	public String getLabelProperty();

	/**
	 * Sets the name of the property which supplies the display text of the
	 * option.
	 * 
	 * @param property
	 *            Name of the property
	 */
	public void setLabelProperty(String property);

	/**
	 * Returns the name of the property using which the Java-Bean is to be
	 * accessed.
	 * 
	 * @return Name of the property
	 */
	public String getProperty();

	/**
	 * Sets the name of the property which holds the data for the column
	 * 
	 * @param property
	 *            The name of the property.
	 */
	public void setProperty(String property);

	/**
	 * Returns the HTML-style that can be directly specified with this
	 * attribute.
	 * 
	 * @return The HTML-style
	 */
	public String getStyle();

	/**
	 * Sets the CSS styles to be applied to this column.
	 * 
	 * @param style
	 *            The CSS styles for this column.
	 */
	public void setStyle(String style);

	/**
	 * Returns the HTML-class attribute that can be specified with this
	 * attribute
	 * 
	 * @return The HTML-class
	 */
	public String getStyleClass();

	/**
	 * Sets the CSS stylesheet class
	 * 
	 * @param styleClass
	 *            The CSS stylesheet class
	 */
	public void setStyleClass(String styleClass);

	/**
	 * Returns the maximum number of characters that are displayed for each
	 * option element.
	 * 
	 * @return Maximum number of characters to display
	 */
	public int getMaxLength();

	/**
	 * Sets the maximum number of characters that are displayed for each option
	 * element.
	 * 
	 * @param max
	 *            Maximum number of characters to display
	 */
	public void setMaxLength(int max);

	/**
	 * Returns if the filter is activated (default=true). This means that all
	 * Strings which should be displayed in the HTML page are html encoded
	 * 
	 * @return <code>true</code> if string will be html encoded;
	 *         <code>false</code> otherwise
	 */
	public boolean filter();

	/**
	 * Activates the html encoding (filter). Default is true. This means that
	 * all Strings which should be displayed in the HTML page will be html
	 * encoded.
	 * 
	 * @param filter
	 *            <code>true</code> if strings should be html encoded; false
	 *            otherwise
	 */
	public void setFilter(boolean filter);

	/**
	 * If this flag is set to <code>true</code> the framework interprets all
	 * labels as resource identifiers and localize them. If the flag is set to
	 * <code>false</code> the lables are treated as string literals without
	 * localization.
	 * 
	 * @param localize
	 *            Localization setting for the option list
	 */
	public void setLocalize(boolean localize);

	/**
	 * If this flag is set to <code>true</code> the framework interprets all
	 * labels as resource identifiers and localize them. If the flag is set to
	 * <code>false</code> the lables are treated as string literals without
	 * localization.
	 * 
	 * @return <code>true</code> if all labels should be localized
	 */
	public boolean localize();
}