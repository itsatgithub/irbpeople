/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/imp/ControlContainerImp.java,v 1.11 2005/02/16 18:24:43 P001001 Exp $
 * $Revision: 1.11 $
 * $Date: 2005/02/16 18:24:43 $
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

import java.util.ArrayList;

import com.cc.framework.ui.control.Control;
import com.cc.framework.ui.model.ControlContainer;

/**
 * Designmodel for ControlContainer
 *
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.11 $
 * @since       1.0
 */
public abstract class ControlContainerImp extends ControlDesignModelImp implements ControlContainer {

	/**
	 * A buffer for the controls in the container
	 */
	private ArrayList controls	= new ArrayList();

	/**
	 * Constructor
	 */
	public ControlContainerImp() {
		super();
	}

	/**
	 * @see com.cc.framework.ui.model.ControlContainer#addControl(com.cc.framework.ui.control.Control)
	 */
	public void addControl(Control control) {
		synchronized (controls) {
			controls.add(control);
		}
	}

	/**
	 * @see com.cc.framework.ui.model.ControlContainer#removeControl(com.cc.framework.ui.control.Control)
	 */
	public void removeControl(Control control) {
		synchronized (controls) {
			controls.remove(control);
		}
	}

	/**
	 * @see com.cc.framework.ui.model.ControlContainer#getControls()
	 */
	public Control[] getControls() {

		synchronized (controls) {
			Control[] list = new Control[controls.size()];
			return ((Control[]) controls.toArray(list));
		}
	}

	/**
	 * @see com.cc.framework.ui.model.ControlContainer#size()
	 */
	public int size() {
		return controls.size();
	}
}
