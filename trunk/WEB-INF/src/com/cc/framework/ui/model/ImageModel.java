/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/ImageModel.java,v 1.8 2005/02/16 18:13:32 P001001 Exp $
 * $Revision: 1.8 $
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

import com.cc.framework.ui.WebResourceAccess;

/**
 * Defines the attributes for an image
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.8 $
 * @since      1.0
 */
public interface ImageModel extends WebResourceAccess {

	/**
	 * Returns the height of the image
	 * 
	 * @return	int	the height of the image
	 */
	public int getHeight();

	/**
	 * Returns the width of the image
	 * 
	 * @return	int	the width of the image
	 */
	public int getWidth();

	/**
	 * Returns the URL of the image to be displayed
	 * 
	 * @return	String the URL of the image
	 */
	public String getSource();

	/**
	 * Returns the text to display if the image can't be displayed.
	 * 
	 * @return	String the alternative text.
	 */
	public String getAlternate();

	/**
	 * Returns a tooltip text for this image
	 * 
	 * @return		Tooltip text
	 */
	public String getTooltip();
}