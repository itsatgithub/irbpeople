/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/def/DefInfoPainter.java,v 1.22 2005/06/02 10:43:51 P001002 Exp $
 * $Revision: 1.22 $
 * $Date: 2005/06/02 10:43:51 $
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

package com.cc.framework.ui.painter.def;

import org.apache.ecs.ConcreteElement;
import org.apache.ecs.StringElement;

import com.cc.framework.ui.control.InfoControl;
import com.cc.framework.ui.painter.Frame;
import com.cc.framework.ui.painter.PainterContext;
import com.cc.framework.ui.painter.global.IncludeElement;

/**
 * Painter for the InfoControl
 *
 * @author      <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version     $Revision: 1.22 $
 * @since       1.0
 */
public class DefInfoPainter extends DefPainterBase {

	/**
	 * Constructor for DefInfoPainter
	 *
	 * @param	painterContext	The PainterContext
	 * @param	ctrl			The Control to render
	 */
	public DefInfoPainter(PainterContext painterContext, InfoControl ctrl) {
		super(painterContext);
	}

	/**
	 * Retrieves the control for this painter
	 *
	 * @return		Control
	 */
	protected InfoControl getCtrl() {
		return (InfoControl) getPainterContext().getControl();
	}

	/**
	 * Checks if the frame should be painted
	 *
	 * @return		<code>true</code> if the frame should
	 * 				be painted
	 */
	protected boolean showFrame() {
		return getCtrl().showFrame();
	}

	// ======================================
	// object painter
	// ======================================

	/**
	 * @see com.cc.framework.ui.painter.ControlPainter#getElementClass(int)
	 */
	public String getElementClass(int type) {

		switch (type) {
			case DefClassType.CLASS_CONTROL :
				if (showFrame()) {
					return DefHtmlClass.INFOCONTROL;
				} else {
					return DefHtmlClass.INFOCONTROL_NO_FRAME;
				}

			case DefClassType.CLASS_BODY :
				return "icb";

			case DefClassType.CLASS_HEADER :
				return "ich";

			case DefClassType.CLASS_FOOTER :
				return "icf";

			default :
				return super.getElementClass(type);
		}
	}

	/**
	 * Creates the froms body.
	 *
	 * @return	ConcreteElement
	 */
	protected ConcreteElement doCreateBody() {

		if (getCtrl().getResource() != null) {
			// Include the web resource 
			String uri = getPainterContext().getSource(getCtrl().getBase(), getCtrl().getResource());

			return new IncludeElement(getPageContext(), uri);
		} else if (getCtrl().getContent() != null) {
			// Include raw HTML content
			return new StringElement(getCtrl().getContent());
		} else {
			return null;
		}
	}

	/**
	 * @see com.cc.framework.ui.painter.ControlPainter#doCreateElement()
	 */
	protected ConcreteElement doCreateElement() {
		// if no designmodel is specified then terminate
		if (getCtrl().getDesignModel() == null) {
			return null;
		}

		// render the form
		Frame control = getFramePainter().createFrame(showFrame());
	
		if (getStyleId() != null) {
			control.setID(getStyleId());
		}
	
		if (getCtrl().getStyle() != null) {
			control.setStyle(getCtrl().getStyle());
		}
	
		// set general style class
		if (getCtrl().getStyleClass() == null) {
			control.setClass(getElementClass(DefClassType.CLASS_CONTROL));
		} else {
			control.setClass(getCtrl().getStyleClass());
		}
	
		if (getCtrl().getWidth() != null) {
			control.setWidth(getCtrl().getWidth());
		}
	
		if (getCtrl().getSummary() != null) {
			control.setSummary(getCtrl().getSummary());
		}
		
		// add content elements
		control.addBodyElement(doCreateBody());

		return control;
	}
}
