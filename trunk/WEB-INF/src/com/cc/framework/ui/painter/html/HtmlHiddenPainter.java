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

package com.cc.framework.ui.painter.html;

import org.apache.ecs.ConcreteElement;
import org.apache.ecs.ElementContainer;

import com.cc.framework.ui.control.HiddenControl;
import com.cc.framework.ui.painter.PainterContext;

/**
 * Painter for the Hidden Control
 *
 * @author      <a href="mailto:gschulz@scc-gmbh.com">Gernot Schulz</a>
 * @version     $Revision$
 * @since       1.0
 */
public class HtmlHiddenPainter extends HtmlTextPainter {

	/**
	 * Constructor
	 *
	 * @param	painterContext	The PainterContext
	 * @param	ctrl			The Control to render
	 */
	public HtmlHiddenPainter(PainterContext painterContext, HiddenControl ctrl) {
		super(painterContext, ctrl);
	}

	/**
	 * @see com.cc.framework.ui.painter.ControlPainter#doCreateElement()
	 */
	protected ConcreteElement doCreateElement() {

		ConcreteElement control = super.doCreateElement();

		if (((HiddenControl) getCtrl()).isWrite()) {
			return new ElementContainer()
				.addElement(control)
				.addElement(doCreateDisplayElement());
		} else {
			return control;
		}
	}
}