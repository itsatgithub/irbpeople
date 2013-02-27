/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/InfoDesignModel.java,v 1.6 2005/05/01 08:56:29 P001002 Exp $
 * $Revision: 1.6 $
 * $Date: 2005/05/01 08:56:29 $
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
 * Designmodel for Info Control
 * 
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version $Revision: 1.6 $
 */
public interface InfoDesignModel extends ControlDesignModel, WebResourceAccess {

	/**
	 * Sets a Resource
	 * @param resource	Resource
	 */
	public void setResource(String resource);
	
	/**
	 * Returns the Resource
	 * @return	String
	 */
	public String getResource();

	/**
	 * Disables the frames border
	 *
	 * @param	show <code>true</code> if the frame should be shown
	 */
	public void setShowFrame(boolean show);

	/**
	 * @return	<code>true</code> if the frame should be shown
	 */
	public boolean showFrame();

	/**
	 * @return		The Content of the inner frame (raw HTML code)
	 */
	public String getContent();

	/**
	 * @param		content The raw HTML contetn of the inner frame
	 */
	public void setContent(String content);
}