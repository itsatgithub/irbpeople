/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/ColumnButtonDesignModel.java,v 1.7 2005/02/16 18:03:22 P001001 Exp $
 * $Revision: 1.7 $
 * $Date: 2005/02/16 18:03:22 $
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
 * Defines the visual properties for the <code>buttomcolumn</code>
 * 
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.7 $
 * @since      1.0
 */
public interface ColumnButtonDesignModel extends ColumnDesignModel {

	/**
	 * Returns the image used for the button
	 * 
	 * @return	ImageModel
	 */
	public ImageModel getImage();

	/**
	 * Sets the image used for the button
	 * 
	 * @param	image	ImageModel
	 */
	public void setImage(ImageModel image);

	/**
	 * Sets the image used for the button
	 * 
	 * @param	image	ImageModel
	 */
	public void setImage(String image);

	/**
	 * Returns the text.
	 * @return String
	 */
	public String getText();

	/**
	 * Sets the text.
	 * @param text The text to set
	 */
	public void setText(String text);

	/**
	 * Returns the name of the command that
	 * should bet triggered
	 * 
	 * @return		Command name
	 */
	public String getCommand();
	
	/**
	 * Sets the name of the command that
	 * should bet triggered
	 * 
	 * @param		command The Command name
	 */
	public void setCommand(String command);
}