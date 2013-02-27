/*
 * $Header: d:/repository/cvs/cc-adapter-struts-1.2/adapter/com/cc/framework/adapter/struts/ConcreteControlActionContext.java,v 1.7 2005/07/07 18:40:55 P001002 Exp $
 * $Revision: 1.7 $
 * $Date: 2005/07/07 18:40:55 $
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

package com.cc.framework.adapter.struts;

import com.cc.framework.ui.control.Control;
import com.cc.framework.ui.control.ControlActionContext;
import com.cc.framework.ui.control.ControlActionDef;
import com.cc.framework.util.StringHelp;

/**
 * encapsulates all parameters wich are required if a control action is performed.
 *
 * @author    <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version   $Revision: 1.7 $
 * @since     1.0
 */
public class ConcreteControlActionContext extends ActionContextWrapper implements ControlActionContext {

	/**
	 * The control
	 */
	private Control control			= null;

	/**
	 * The name of the Control
	 */
	private String controlName		= null;

	/**
	 * The control action
	 */
	private ControlActionDef action	= null;

	/**
 	 * Constructor for ConcreteControlActionContext
 	 *
	 * @param	ctx		ActionContext
	 * @param 	control	Control
	 * @param	controlName The name of the Control
	 * @param	action	ControlActionDef
	 */
	public ConcreteControlActionContext(ActionContext ctx, Control control, String controlName, ControlActionDef action) {
		super(ctx);

		this.control		= control;
		this.controlName	= controlName;
		this.action			= action;
	}

	/**
	 * @see com.cc.framework.ui.control.ControlRequestContext#control()
	 */
	public Control control() {
		return control;
	}

	/**
	 * @see com.cc.framework.ui.control.ControlRequestContext#action()
	 */
	public ControlActionDef action() {
		return action;
	}

	/**
	 * @see com.cc.framework.ui.control.ControlRequestContext#getActionMethod()
	 */
	public String getActionMethod() {
		StringBuffer buf = new StringBuffer();

		if ((controlName == null) || "".equals(controlName)) {
			// create a generic event handler
			buf.append("on");
		} else {
			String tokens[] = StringHelp.split(controlName, ".");

			buf.append(tokens[tokens.length - 1]).append("_on");
		}

		if (action != null) {
			buf.append(action.getName());
		}

		return buf.toString();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return StringHelp.strcat("ctx_ctrl(", controlName, ")");
	}
}