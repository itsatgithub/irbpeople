/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/FrameDesignModel.java,v 1.1 2005/05/01 08:56:28 P001002 Exp $
 * $Revision: 1.1 $
 * $Date: 2005/05/01 08:56:28 $
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
 * Interface for the FrameDesignModel
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.1 $
 */
public interface FrameDesignModel extends ControlDesignModel, FrameContainer {

	/**
	 * Returns the Caption of the Frame
	 * @return	String
	 */
	public String getCaption();

	/**
	 * Sets the Caption
	 * @param	caption	Caption
	 */
	public void setCaption(String caption);

	/**
	 * Returns the Detail, which is
	 * displayed right of the Caption
	 * @return	String
	 */
	public String getDetail();

	/**
	 * Sets the Detail
	 * @param	detail	Detail String
	 */
	public void setDetail(String detail);

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
}