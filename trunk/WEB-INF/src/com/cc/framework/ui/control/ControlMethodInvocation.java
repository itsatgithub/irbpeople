/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/control/ControlMethodInvocation.java,v 1.17 2005/03/21 18:27:05 P001002 Exp $
 * $Revision: 1.17 $
 * $Date: 2005/03/21 18:27:05 $
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

package com.cc.framework.ui.control;

import com.cc.framework.adapter.RequestContext;
import com.cc.framework.http.HttpUtil;
import com.cc.framework.util.StringHelp;

/**
 * Die Klasse stellt die Informationen über den Aufruf einer Kontrollelement
 * Aktion zur Verfügung.
 * Eine Kontrollelement Aktion wird immer durch die folgenden Attribute
 * bestimmt:
 * <ul>
 *   <li>Der Name des Kontrollelementes</li>
 *   <li>Die Aktion welche durchgeführt werden soll (Bsp. onPage)</li>
 *   <li>Die Parameter für den Actionhandler</li>
 * </ul>
 *
 * @author              <a href="mailto:gschulz@scc-gmbh.com">Gernot Schulz</a>
 * @version             $Revision: 1.17 $
 */
public class ControlMethodInvocation {

	/**
	 * Control Name
	 */
	private String ctrl;

	/**
	 * Action to process
	 */
	private String action;

	/**
	 * Additional action Parameters
	 * e.g. browse.do?id={0}
	 */
	private String[] param = null;

	/**
	 * Constructor for ControlMethodInvocation
	 *
	 * @param	ctrl	Name of the Control
	 * @param	action	Action to process
	 * @param	params	Action parameters
	 */
	public ControlMethodInvocation(String ctrl, String action, String[] params) {
		super();

		this.ctrl		= ctrl;
		this.action		= action;
		this.param		= params;
	}

	/**
	 * Checks if the event of of the control was triggered
	 * by a hyperlinks. This is the default.
	 *
	 * @param	ctx		ActionContext
	 * @return	ControlMethodInvocation
	 */
	public static ControlMethodInvocation getFromHyperlink(RequestContext ctx) {

		ControlMethodInvocation cmi = null;

		// get the action which was send by the control
		String ctrl = ctx.request().getParameter(ControlAction.ACTION_URL_CONTROL);

		if (ctrl != null) {
			cmi = new ControlMethodInvocation(
				ctrl,
				ctx.request().getParameter(ControlAction.ACTION_URL_ACTION),
				ctx.request().getParameterValues(ControlAction.ACTION_URL_PARAMETER));
		}

		return cmi;
	}

	/**
	 * Checks if a action of a form was generated within a form.
	 *
	 * Syntax:
	 *   "ctrla=[controlname]=[Aktionsname]=[Parameter 1]=...=[Parameter n].x"
	 * or
	 *   "ctrla=[controlname]=[Aktionsname]=[Parameter 1]=...=[Parameter n].y"
	 *
	 * @param	ctx		ActionContext
	 * @return	ControlMethodInvocation
	 */
	public static ControlMethodInvocation getFromForm(RequestContext ctx) {
		ControlMethodInvocation cmi = null;

		// Die erste Kontrollelement Aktionen aus dem Request ermitteln
		String controlAction = ctx.request().getParameter(ControlAction.ACTION_INPUT_CONTROL);

		if (controlAction != null) {

			String[] tokens = StringHelp.splitRetainEmpty(controlAction, '=');

			if (tokens.length >= 2) {

				String[] params = new String[0];

				if (tokens.length > 2) {
					params = new String[tokens.length - 2];

					for (int i = 2; i < tokens.length; i++) {
						params[i - 2] = tokens[i];
					}
				}

				cmi = new ControlMethodInvocation(tokens[0], tokens[1], params);
			}
		}

		return cmi;
	}

	/**
	 * Returns the name of the control
	 *
	 * @return	The name of the control
	 */
	public String getControlName() {
		return ctrl;
	}

	/**
	 * Returns the name of the Action to be performed
	 *
	 * @return	String
	 */
	public String getAction() {
		return action;
	}

	/**
	 * Returns an Array with the additional Parameters
	 *
	 * @return	String
	 */
	public String[] getParams() {
		return param;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuffer buf = new StringBuffer()
			.append(ControlAction.ACTION_URL_CONTROL)
			.append("=")
			.append(ctrl)
			.append("&")
			.append(ControlAction.ACTION_URL_ACTION)
			.append("=")
			.append(action);

		if (param != null) {
			for (int i = 0; i < param.length; i++) {
				buf
					.append("&")
					.append(ControlAction.ACTION_URL_PARAMETER)
					.append("=")
					.append(HttpUtil.urlEncode(param[i]));
			}
		}

		return buf.toString();
	}
}