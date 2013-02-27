/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/ColumnTextDesignModel.java,v 1.7 2005/02/16 18:13:32 P001001 Exp $
 * $Revision: 1.7 $
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

/**
 * Defines the visual Properties for the textColumn
 *
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version $Revision: 1.7 $
 */
public interface ColumnTextDesignModel extends ColumnDesignModel {

	/**
	 * Returns the number of visibel characters
	 * for an input/text field.
	 * 
	 * @return	Number of character positions to allocate
	 */
	public int getSize();

	/**
	 * Sets the number of visibel characters
	 * for an input/text field.
	 * 
	 * @param		size Number of character positions to allocate
	 */
	public void setSize(int size);

	/**
	 * Sets the number of visibel characters
	 * for an input/text field.
	 * 
	 * @param		size Number of character positions to allocate
	 */
	public void setSize(String size);

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
	 * @param		type The type of the Input field
	 */
	public void setInputType(InputFieldType type);
}
