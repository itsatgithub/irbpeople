/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/model/StateModel.java,v 1.8 2005/07/08 15:15:33 P001002 Exp $
 * $Revision: 1.8 $
 * $Date: 2005/07/08 15:15:33 $
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

import com.cc.framework.util.PropertyMap;

/**
 * Interface for all Statemodels
 * <br>
 * A state model holds the state information of a control.
 * A control can save its own state across server roundtrips,
 * so that the scrolling mechanism of a list, for example,
 * or the explosion and folding of a tree structure do not
 * have to be implemented independently. Rather, the state is
 * managed by the control element itself.
 *
 * @author   <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version  $Revision: 1.8 $
 * @since    1.0
 */
public interface StateModel {

	/**
	 * State property for the control name
	 */
	public String PROP_CONTROL = "ctrl";

	/**
	 * This method applies the given state properties to the
	 * controls state model
	 *
	 * @param		properties State properties
	 * @throws		Exception is thrown when an error occurs
	 * 				during execution
	 */
	public void synchronizeState(PropertyMap properties) throws Exception;

	/**
	 * Resets the actual state of a control to the initial state
     */
	public void reset();
}

