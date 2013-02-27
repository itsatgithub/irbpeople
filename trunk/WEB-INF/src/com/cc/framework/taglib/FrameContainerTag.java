/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/FrameContainerTag.java,v 1.2 2005/08/02 19:13:06 P001002 Exp $
 * $Revision: 1.2 $
 * $Date: 2005/08/02 19:13:06 $
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

package com.cc.framework.taglib;

import com.cc.framework.ui.model.InnerFrame;

/**
 * All tags witch can have nested inner frames must implement this
 * interface
 *
 * @author    <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version   $Revision: 1.2 $
 * @since     1.0
 */
public interface FrameContainerTag extends ImageContainerTag {

	/**
	 * Adds a new inner Frame to the container
	 *
	 * @param		frame The inner Frame to add
	 */
	public void addInnerFrame(InnerFrame frame);
}