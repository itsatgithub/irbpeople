/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/def/DefHeadlinePainter.java,v 1.20 2005/09/27 14:06:22 P001002 Exp $
 * $Revision: 1.20 $
 * $Date: 2005/09/27 14:06:22 $
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

import org.apache.ecs.AlignType;
import org.apache.ecs.ConcreteElement;
import org.apache.ecs.Entities;
import org.apache.ecs.html.IMG;
import org.apache.ecs.html.TD;
import org.apache.ecs.html.TR;
import org.apache.ecs.html.Table;

import com.cc.framework.ui.control.HeadlineControl;
import com.cc.framework.ui.painter.PainterContext;

/**
 * Painter for the Headline Control
 *
 * @author	    <a href="mailto:hschulz@scc-gmbh.com">Gernot Schulz</a>
 * @version	    $Revision: 1.20 $
 * @since       1.0
 */
public class DefHeadlinePainter extends DefPainterBase {

	/**
	 * Constructor
	 *
	 * @param	painterContext	The PainterContext
	 * @param	ctrl			The Control to render
	 */
	public DefHeadlinePainter(PainterContext painterContext, HeadlineControl ctrl) {
		super(painterContext);
	}

	/**
	 * Retrieves the control for this painter
	 *
	 * @return		Control
	 */
	protected HeadlineControl getCtrl() {
		return (HeadlineControl) getPainterContext().getControl();
	}

	/**
	 * @see com.cc.framework.ui.painter.ControlPainter#doCreateElement()
	 */
	protected ConcreteElement doCreateElement() {

		String caption	= getSmartCaption(getCtrl().getCaption(), getCtrl().getDetail());
		String detail	= getSmartDetail(getCtrl().getCaption(), getCtrl().getDetail());

		String width	= getCtrl().getDesignModel().getWidth();

		// of no width is assigned set width to 100%
		width = (null == width) ? "100%" : width;

		Table control = new Table()
			.setCellSpacing(0)
			.setCellPadding(0)
			.setBorder(0)
			.setWidth(width)
			.addElement(new TR()
				.addElement(new TD()
					.addElement(new IMG(getImageSrc(DefResources.IMAGE_HEADER_TOP)))
					.setVAlign(AlignType.bottom))
				.addElement(new TD(Entities.NBSP + html(caption, getCtrl().getFilter()))
					.setColSpan(2)
					.setNoWrap(true)
					.setWidth("100%")
					.setClass(DefHtmlClass.CAPTION)))
			.addElement(new TR()
				.addElement(new TD()
					.addElement(new IMG(getImageSrc(DefResources.IMAGE_HEADER_BOTTOM)))
					.setVAlign(AlignType.top))
				.addElement(new TD(Entities.NBSP)
					.setWidth("100%"))
				.addElement(new TD(html(detail, getCtrl().getFilter()))
					.setNoWrap(true)
					.setClass(DefHtmlClass.DETAIL)));

		if (getStyleId() != null) {
			control.setID(getStyleId());
		}

		if (getCtrl().getStyle() != null) {
			control.setStyle(getCtrl().getStyle());
		}

		if (getCtrl().getStyleClass() == null) {
			control.setClass(DefHtmlClass.HEADLINE);
		} else {
			control.setClass(getCtrl().getStyleClass());
		}

		return control;
	}
}