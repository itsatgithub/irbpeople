/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/ColumnSelectDesignModel.java,v 1.9 2005/08/02 19:13:53 P001002 Exp $
 * $Revision: 1.9 $
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
 * Defines the visual Properties for the select Column
 *
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version $Revision: 1.9 $
 */
public interface ColumnSelectDesignModel extends ColumnDesignModel, OptionListDesignModel {

	/**
	 * Specifies the name of the property which
	 * returns the OptionListDataModel
	 *
	 * @param		property The name of the property
	 */
	public void setOptionsProperty(String property);

	/**
	 * Returns the name of the property which
	 * returns the individual OptionListDataModel for
	 * each row of the list
	 *
	 * @return		The name of the property
	 */
	public String getOptionsProperty();

	/**
	 * Sets a option list that is shared among all
	 * rows of the list
	 *
	 * @param		elements The Option elements
	 */
	public void setSharedOptions(Object elements);

	/**
	 * Returns the OptionListDataModel that is
	 * shared among all rows of the list
	 *
	 * @return		Option elements
	 */
	public Object getSharedOptions();

	/**
	 * Returns the number of visibel characters
	 * for an input/text field.
	 * 
	 * @return	Number of character positions to allocate
	 */
	public int getSize();

	/**
	 * Sets the size of the input field.
	 *
	 * @param	size	The size of the input field.
	 */
	public void setSize(int size);

	/**
	 * Sets the size of the input field.
	 *
	 * @param	size	The size of the input field.
	 */
	public void setSize(String size);

	/**
	 * Indicates that multiple selections is allowed.
	 * 
	 * @return		returns <code>true</code> for a multiple
	 * 				selection control
	 */
	public boolean isMultiple();

	/**
	 * Indicates that multiple selections is allowed.
	 * 
	 * @param		multiple <code<true</code> for multiple
	 * 				selection		
	 */
	public void setMultiple(boolean multiple);
}