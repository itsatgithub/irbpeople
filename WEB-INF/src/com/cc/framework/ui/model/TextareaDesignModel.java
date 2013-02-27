/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/TextareaDesignModel.java,v 1.9 2005/07/28 19:41:39 P001002 Exp $
 * $Revision: 1.9 $
 * $Date: 2005/07/28 19:41:39 $
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

import javax.servlet.jsp.JspException;

import com.cc.framework.convert.Converter;

/**
 * Designmodel for the Textarea field
 * 
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version $Revision: 1.9 $
 */
public interface TextareaDesignModel extends ControlDesignModel {

	/**
	 * Returns the number of Columns
	 * @return	int
	 */
	public int getColumns();

	/**
	 * Sets the number of Columns
	 * @param	columns	Number of Columns 
	 */
	public void setColumns(int columns);

	/**
	 * Returns the number of Rows
	 * @return	int
	 */
	public int getRows();

	/**
	 * Sets the number of Rows
	 * @param	rows	Number of Rows
	 */		
	public void setRows(int rows);
	
	/**
	 * Specifies the readonly attribute
	 * 
	 * @param	readonly		<code>true</code> if the textarea should be rendered as readonly
	 * @throws	JspException	if the Argument can not be converted into a boolean value
	 */	
	public void setReadonly(boolean readonly) throws JspException;
	
	/**
	 * Returns true if the readonly attribute was set
	 * @return True if the readonly attribute was set; default is false.
	 */
	public boolean isReadonly(); 
	
	/**
	 * Sets the maximal length of the value for the textarea
	 * 
	 * @param maxlength	The maximal length of the value for the textarea
	 */
	public void setMaxLength(int maxlength); 

	/**
	 * Returns the maximal length of the value for the textarea
	 * 
	 * @return The maximal length of the value for the textarea
	 */
	public int getMaxLength();

	/**
	 * The WRAP attribute can be used to specify how to
	 * handle word-wrapping display in text input areas in forms.
	 *
	 * @param	wrap Word Wrapping
	 */
	public void setWrap(String wrap);

	/**
	 * The WRAP attribute can be used to specify how to
	 * handle word-wrapping display in text input areas in forms.
	 *
	 * @return	Word Wrapping
	 */
	public String getWrap();


	/**
	 * Sets the optional tooltip text
	 * 
	 * @param	tooltip	Tooltip
	 */
	public void setTooltip(String tooltip);

	/**
	 * Retrieves the optional tooltip text
	 * 
	 * @return	tooltip text or <code>null</code>
	 */
	public String getTooltip();

	/**
	 * Returns the the Converter that should be used to convert Java Objects
	 * into their localized String representation. If no converter is specified
	 * the framework will use a default Converter that matches the beans data
	 * type
	 * 
	 * @return Converter or <code>null</code for a default
	 * 				converter
	 */
	public Converter getConverter();

	/**
	 * Sets the Converter that should be used to convert Java Objects into their
	 * localized String representation. If no converter is specified the
	 * framework will use a default Converter that matches the beans data type.
	 * 
	 * @param converter
	 *            Converters instance
	 */
	public void setConverter(Converter converter);
}