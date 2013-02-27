/*
 * $Header: d:/repository/cvs/cc-adapter-struts-1.2/adapter/com/cc/framework/adapter/struts/FWValidatorForm.java,v 1.4 2005/07/08 14:11:04 P001002 Exp $
 * $Revision: 1.4 $
 * $Date: 2005/07/08 14:11:04 $
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

import org.apache.struts.validator.ValidatorForm;

/**
 * Encapsulation of the ValidatorForm for the common controls framework.
 *
 * Note: The Validator Plug-In must be configured, within the struts-config.
 * Then when the validate method is called, the action's name
 * attribute from the struts-config.xml is used to load the
 * validations for the current form. So the form element's name
 * attribute in the validator-rules.xml should match action
 * element's name attribute.
 *
 * @author   <a href="mailto:gschulz@scc-gmbh.com">Gernot Schulz</a>
 * @version  $Revision: 1.4 $
 * @since    1.2
 * @see org.apache.struts.validator.ValidatorForm
 */
public class FWValidatorForm extends ValidatorForm {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -5374977835563138994L;

	/**
	 * Constructor
	 */
	public FWValidatorForm() {
		super();
	}

}
