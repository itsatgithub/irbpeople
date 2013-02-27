/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/ColumnTextareaDesignModel.java,v 1.4 2005/02/16 18:13:30 P001001 Exp $
 * $Revision: 1.4 $
 * $Date: 2005/02/16 18:13:30 $
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
 * Defines the visual Properties for the textareaColumn
 *
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version $Revision: 1.4 $
 */
public interface ColumnTextareaDesignModel extends ColumnDesignModel {

	/**
	 * Returns the number of visibel columns
	 * for an input/text field.
	 * 
	 * @return	Number of character columns
	 */
	public int getCols();

	/**
	 * Sets the number of visibel columns
	 * for an input/text field.
	 * 
	 * @param		cols Number of character columns
	 */
	public void setCols(int cols);

	/**
	 * Sets the number of visibel columns
	 * for an input/text field.
	 * 
	 * @param		cols Number of character columns
	 */
	public void setCols(String cols);

	/**
	 * Returns the number of visibel rows
	 * for an input/text field.
	 * 
	 * @return	Number of character rows
	 */
	public int getRows();

	/**
	 * Sets the number of visibel rows
	 * for an input/text field.
	 * 
	 * @param		rows Number of character rows
	 */
	public void setRows(int rows);

	/**
	 * Sets the number of visibel rows
	 * for an input/text field.
	 * 
	 * @param		rows Number of character rows
	 */
	public void setRows(String rows);

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
}