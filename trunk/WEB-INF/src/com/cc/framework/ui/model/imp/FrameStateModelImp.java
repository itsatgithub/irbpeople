/*
 * $Header$
 * $Revision$
 * $Date$
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

package com.cc.framework.ui.model.imp;

import com.cc.framework.ui.model.FrameStateModel;
import com.cc.framework.util.PropertyMap;

/**
 * 
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version $Revision$
 */
public class FrameStateModelImp implements FrameStateModel {

	/**
	 * State property for the open state
	 */
	public String PROP_OPEN = "open";

	/**
	 * State property for the expansion state
	 */
	public String PROP_EXPANSION = "exp";

	private static final int EXP_MINIMIZED = 1;

	private static final int EXP_MAXIMIZED = 2;

	private boolean open = true;

	private int expansionState = 0;

	/**
	 * Constructor
	 */
	public FrameStateModelImp() {
		super();
	}

	/**
	 * @see com.cc.framework.ui.model.FrameStateModel#close()
	 */
	public void close() {
		open = false;
	}

	/**
	 * @see com.cc.framework.ui.model.FrameStateModel#isClosed()
	 */
	public boolean isClosed() {
		return !open;
	}

	/**
	 * @see com.cc.framework.ui.model.FrameStateModel#isMaximized()
	 */
	public boolean isMaximized() {
		return (expansionState == EXP_MAXIMIZED);
	}

	/**
	 * @see com.cc.framework.ui.model.FrameStateModel#isMinimized()
	 */
	public boolean isMinimized() {
		return (expansionState == EXP_MINIMIZED);
	}

	/**
	 * @see com.cc.framework.ui.model.FrameStateModel#isOpen()
	 */
	public boolean isOpen() {
		return open;
	}

	/**
	 * @see com.cc.framework.ui.model.FrameStateModel#maximize()
	 */
	public void maximize() {
		expansionState = EXP_MAXIMIZED;
	}

	/**
	 * @see com.cc.framework.ui.model.FrameStateModel#minimize()
	 */
	public void minimize() {
		expansionState = EXP_MINIMIZED;
	}

	/**
	 * @see com.cc.framework.ui.model.FrameStateModel#open()
	 */
	public void open() {
		open = true;
	}

	/**
	 * @see com.cc.framework.ui.model.FrameStateModel#restore()
	 */
	public void restore() {
		expansionState = 0;
	}

	/**
	 * @see com.cc.framework.ui.model.StateModel#reset()
	 */
	public void reset() {
		open = true;
		expansionState = 0;
	}

	/**
	 * @see com.cc.framework.ui.model.StateModel#synchronizeState(com.cc.framework.util.PropertyMap)
	 */
	public void synchronizeState(PropertyMap properties) throws Exception {
		if (properties.hasProperty(PROP_OPEN)) {
			open = Boolean.getBoolean(properties.getProperty(PROP_OPEN));
		}

		if (properties.hasProperty(PROP_EXPANSION)) {
			expansionState = Integer.parseInt(properties.getProperty(PROP_EXPANSION));
		}
	}
}