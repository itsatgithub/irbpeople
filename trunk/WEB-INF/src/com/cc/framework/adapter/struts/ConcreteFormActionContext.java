/*
 * $Header: d:/repository/cvs/cc-adapter-struts-1.2/adapter/com/cc/framework/adapter/struts/ConcreteFormActionContext.java,v 1.3 2005/02/16 20:00:05 P001001 Exp $
 * $Revision: 1.3 $
 * $Date: 2005/02/16 20:00:05 $
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

import com.cc.framework.util.StringHelp;

/**
 * ActionContext for Actions in Forms
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.3 $
 * @since      1.0
 */
public class ConcreteFormActionContext extends ActionContextWrapper implements  FormActionContext {

	/**
	 * The action
	 */
	private String action = null;

	/**
	 * Constructor
	 *
	 * @param	ctx		ActionContext
	 * @param	action	Action
	 */
	public ConcreteFormActionContext(ActionContext ctx, String action) {
		super(ctx);

		this.action	= action;
	}

	/**
	 * Returns the name of the Method which will
	 * be called to handle the FormAction
	 *
	 * @return		Name of the action method
	 */
	public String getActionMethod() {
		return null;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return StringHelp.strcat("ctx_frm(", action, ")");
	}
}