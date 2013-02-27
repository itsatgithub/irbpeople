/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/FrameStateModel.java,v 1.3 2005/09/27 14:06:22 P001002 Exp $
 * $Revision: 1.3 $
 * $Date: 2005/09/27 14:06:22 $
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
 * Statemodel for the Frame Element
 * 
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version $Revision: 1.3 $
 */
public interface FrameStateModel extends StateModel {

	/**
	 * Changes the state of the frame to closed
	 */
	public void close();

	/**
	 * Changes the state of the frame to opened
	 */
	public void open();

	/**
	 * @return returns <code>true</code> when the Frame is closed
	 */
	public boolean isClosed();

	/**
	 * @return returns <code>true</code> when the Frame is opened
	 */
	public boolean isOpen();

	/**
	 * Minimizes the frame
	 */
	public void minimize();

	/**
	 * Maximizes the frame
	 */
	public void maximize();

	/**
	 * Restores the frame
	 */
	public void restore();

	/**
	 * @return returns <code>true</code> when the Frame is minimized
	 */
	public boolean isMinimized();

	/**
	 * @return returns <code>true</code> when the Frame is maximized
	 */
	public boolean isMaximized();
}