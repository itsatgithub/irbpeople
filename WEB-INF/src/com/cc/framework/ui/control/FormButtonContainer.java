/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/control/FormButtonContainer.java,v 1.12 2005/02/16 18:03:02 P001001 Exp $
 * $Revision: 1.12 $
 * $Date: 2005/02/16 18:03:02 $
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

/**
 * Container for form buttons
 *
 * @author    <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version   $Revision: 1.12 $
 * @since     1.0
 */
public class FormButtonContainer extends FormGroupElement {

	/**
	 * The default button
	 */
	private String defaultButton = null;

	/**
	 * Constructor
	 */
	public FormButtonContainer() {
		super();

		// join button containers
		setJoin(true);
	}

	/**
	 * Returns the default button.
	 *
	 * @return		The name of the default button
	 * 				or <code>null</code>
	 */
	public String getDefaultButtonId() {
		return defaultButton;
	}

	/**
	 * Returns the default button.
	 *
	 * @return		The name of the default button
	 * 				or <code>null</code>
	 */
	public FormButtonElement getDefaultButton() {
		if (defaultButton == null) {
			return null;
		}

		for (int i = 0; i < size(); i++) {
			FormElement child = getFormElement(i);

			if (child instanceof FormButtonElement) {
				FormButtonElement btn = (FormButtonElement) child;

				if (defaultButton.equals(btn.getName()) || defaultButton.equals(btn.getStyleId())) {
					return btn;
				}
			}
		}

		// Default button not found
		return null;
	}

	/**
	 * Sets the default button.
	 *
	 * @param		defaultButton The name of the default button
	 */
	public void setDefaultButtonId(String defaultButton) {
		this.defaultButton = defaultButton;
	}
}