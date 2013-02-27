/*
 * $Header: d:/repository/cvs/cc-adapter-struts-1.2/adapter/com/cc/framework/adapter/struts/FormActionCommand.java,v 1.4 2005/10/08 09:25:09 P001002 Exp $
 * $Revision: 1.4 $
 * $Date: 2005/10/08 09:25:09 $
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

import java.util.Enumeration;

import com.cc.framework.Globals;

/**
 * Formular Action
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.4 $
 * @since      1.0
 */
public class FormActionCommand {

	/**
	 * Field HIDDEN_FORMID
	 */
	private static final String HIDDEN_FORMID	= "formid";

	/**
	 * Field BUTTON_PREFIX
	 */
	private static final String BUTTON_PREFIX	= "btn";

	/**
	 * Field METHOD_ONCLICK
	 */
	private static final String METHOD_ONCLICK	= "_onClick";

	/**
	 * Field METHOD_ONSUBMIT
	 */
	private static final String METHOD_ONSUBMIT	= "_onSubmit";

	/**
	 * Field METHOD_CANCEL
	 */
	private static final String METHOD_ONCANCEL	= "_onCancel";

	/**
	 * If a button on a HTML form is clicked, this method returns
	 * the name of the methode to be processed
	 *
	 * @param		ctx    The ActionContext
	 * @return	The name of the method
	 */
	public static String parseFormAction(ActionContext ctx) {

		Enumeration e = ctx.request().getParameterNames();

		String actionName = null;

		while (e.hasMoreElements()) {
			String attName = (String) e.nextElement();

			if (attName.startsWith(BUTTON_PREFIX)) {
				String value = ctx.request().getParameter(attName);

				if (!"".equals(value)) {
					int pos = attName.indexOf(".");

					if (pos != -1) {
						actionName = attName.substring(BUTTON_PREFIX.length(), pos);
					} else {
						actionName = attName.substring(BUTTON_PREFIX.length());
					}

					actionName = actionName.substring(0, 1).toLowerCase() + actionName.substring(1) + METHOD_ONCLICK;

					// exit
					break;
				}
			}
		}
		
		if (actionName == null) {
			// if a hidden field "formid" is found in the request
			// then it was a form submit
			String formId = ctx.request().getParameter(HIDDEN_FORMID);
			
			if (formId != null) {
				boolean canceld = ctx.request().getParameter(Globals.STRUTS_CANCEL_KEY) != null;

				if (canceld) {
					actionName	= formId + METHOD_ONCANCEL;
				} else {
					actionName	= formId + METHOD_ONSUBMIT;
				}
			}
		}

		return actionName;
	}
}