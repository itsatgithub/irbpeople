/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/html/OptionsIterator.java,v 1.8 2005/08/02 19:15:00 P001002 Exp $
 * $Revision: 1.8 $
 * $Date: 2005/08/02 19:15:00 $
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

package com.cc.framework.ui.painter.html;


/**
 * Iterator Adapter for various Option types
 *
 * This interface has no reset() method because some of the
 * underlying classes (like Iterator and Enumeration) do not
 * support such an operation.
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.8 $
 */
public interface OptionsIterator {

	/**
	 * Check if the iteration has no more elements
	 * 
	 * @return		returns <code>true</code> if there
	 * 				is no element left
	 */	
	public boolean isDone();

	/**
	 * Locates the iterator on the next element of
	 * the option list
	 */
	public void next();

	/**
	 * Gets the key of the current iteration element
	 * 
	 * @return		Key
	 */	
	public Object getKey();

	/**
	 * Returns the label of the current element
	 * 
	 * @return		Label
	 */
	public Object getLabel();

	/**
	 * Gets an optional indentiation level of the
	 * current element. This is used when iterating over
	 * a tree structure
	 *  
	 * @return		indentation level
	 */
	public int getIndent();

	/**
	 * Returns the maximum number of characters
	 * that are displayed for each option element.
	 * 
	 * @return	Maximum number of characters to display
	 */
	public int getMaxLength();

	/**
	 * Returns the HTML-style that can be directly specified
	 * with this attribute.
	 * 
	 * @return The HTML-style
	 */
	public String getStyle();

	/**
	 * Returns the HTML-class attribute that can be specified
	 * with this attribute
	 * 
	 * @return The HTML-class
	 */
	public String getStyleClass();

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
	 * If this flag is set to <code>true</code> the framework interprets all
	 * labels as resource identifiers and localize them. If the flag is set to
	 * <code>false</code> the lables are treated as string literals without
	 * localization.
	 * 
	 * @return <code>true</code> if all labels should be localized
	 */
	public boolean localize();
}