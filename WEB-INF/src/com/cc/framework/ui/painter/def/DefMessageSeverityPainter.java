/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/def/DefMessageSeverityPainter.java,v 1.13 2005/05/01 08:57:08 P001002 Exp $
 * $Revision: 1.13 $
 * $Date: 2005/05/01 08:57:08 $
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

import org.apache.ecs.html.IMG;

import com.cc.framework.common.Severity;
import com.cc.framework.message.Message;
import com.cc.framework.ui.control.MessageControl;
import com.cc.framework.ui.painter.PainterContext;

/**
 * Painter for the framework messages
 *
 * @author      <a href="mailto:hschulz@scc-gmbh.com">Gernot Schulz</a>
 * @version     $Revision: 1.13 $
 * @since       1.2
 */
public class DefMessageSeverityPainter extends DefMessagePainter {

	/**
	 * Constructor
	 *
	 * @param painterContext	PainterContext
	 * @param ctrl				MessageControl
	 */
	public DefMessageSeverityPainter(PainterContext painterContext,	MessageControl ctrl) {
		super(painterContext, ctrl);
	}

	/**
	 * @see com.cc.framework.ui.painter.ControlPainter#getElementClass(int)
	 */
	public String getElementClass(int type) {
		if (type == DefClassType.CLASS_CONTROL) {
			return DefHtmlClass.SEVERITY_FORM;
		} else {
			return super.getElementClass(type);
		}
	}

	/**
	 * @see com.cc.framework.ui.painter.def.DefMessagePainter#createSeverityImg(com.cc.framework.message.Message)
	 */
	protected IMG createSeverityImg(Message message) {
		if (Severity.NONE.equals(message.getSeverity())) {
			return createImage(DefResources.IMAGE_DOT_COLOR, getColor(DefColorPalette.ERROR_COLOR_TEXT));
		} else {
			return super.createSeverityImg(message);
		}
	}
}
