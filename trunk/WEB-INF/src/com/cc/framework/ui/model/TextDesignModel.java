/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/TextDesignModel.java,v 1.11 2005/08/02 19:13:53 P001002 Exp $
 * $Revision: 1.11 $
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
/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/TextDesignModel.java,v 1.11 2005/08/02 19:13:53 P001002 Exp $
 * $Revision: 1.11 $
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

import com.cc.framework.convert.Converter;

/**
 * Designmodel for text fields.<br>
 * The interface only defines getter methods for readonly access.
 * 
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version $Revision: 1.11 $
 * @since 1.0
 */
public interface TextDesignModel extends ControlDesignModel {

	/**
	 * Returns the number of visibel characters for an input/text field.
	 * 
	 * @return Number of character positions to allocate
	 */
	public int getSize();

	/**
	 * Sets the size of the input field.
	 * 
	 * @param size
	 *            The size of the input field.
	 */
	public void setSize(int size);

	/**
	 * Returns the maximal number of input characters which can be inserted into
	 * the text field.
	 * 
	 * @return Maximum number of input characters to accept
	 */
	public int getMaxLength();

	/**
	 * Sets the maximum number of input characters to accept.
	 * 
	 * @param maxLength
	 *            The maximum number of input characters to accept.
	 */
	public void setMaxLength(int maxLength);

	/**
	 * Returns the type of the input field
	 * 
	 * @return The type of the Input field
	 * @see com.cc.framework.ui.model.InputFieldType
	 */
	public InputFieldType getInputType();

	/**
	 * Sets the type of the input field
	 * 
	 * @param inputType
	 *            The input field type
	 * @see com.cc.framework.ui.model.InputFieldType
	 */
	public void setInputType(InputFieldType inputType);

	/**
	 * Sets the optional tooltip text
	 * 
	 * @param tooltip
	 *            Tooltip
	 */
	public void setTooltip(String tooltip);

	/**
	 * Retrieves the optional tooltip text
	 * 
	 * @return tooltip text or <code>null</code>
	 */
	public String getTooltip();

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