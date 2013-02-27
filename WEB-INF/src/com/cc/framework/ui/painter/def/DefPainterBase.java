/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/def/DefPainterBase.java,v 1.19 2005/05/01 08:57:07 P001002 Exp $
 * $Revision: 1.19 $
 * $Date: 2005/05/01 08:57:07 $
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

import java.text.MessageFormat;

import org.apache.ecs.ConcreteElement;

import com.cc.framework.ui.AlignmentType;
import com.cc.framework.ui.control.ControlActionDef;
import com.cc.framework.ui.model.ClientEvent;
import com.cc.framework.ui.model.imp.ClientHandlerImp;
import com.cc.framework.ui.painter.ActionPainter;
import com.cc.framework.ui.painter.ControlPainter;
import com.cc.framework.ui.painter.PainterContext;

/**
 * Base Class for all painters in the painter.def package
 *
 * @author      <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version     $Revision: 1.19 $
 * @since       1.0
 */
public abstract class DefPainterBase extends ControlPainter {

	/**
	 * Constructor
	 *
	 * @param	painterContext The PainterContext
	 */
	public DefPainterBase(PainterContext painterContext) {
		super(painterContext);
	}

	/**
	 * Creates a Help-Button with the given Help Id
	 *
	 * @param		helpId the help id
	 * @return		ConcreteElement or <code>null</code> when
	 * 				no help button should be painted
	 */
	public ConcreteElement doCreateHelpButton(String helpId) {
		return createHelpElement(helpId, DefResources.BUTTON_HELP_1, AlignmentType.CENTER);
	}

	/**
	 * Creates a Help-Button with the given Help Id
	 *
	 * @param		helpId the help id
	 * @return		ConcreteElement or <code>null</code> when
	 * 				no help button should be painted
	 */
	public ConcreteElement doCreateHelpIcon(String helpId) {
		return createHelpElement(helpId, DefResources.IMAGE_HELP, AlignmentType.TOP);
	}

	/**
	 * Creates a Help-Button with the given Help Id
	 *
	 * @param		helpId the help id
	 * @param		imageResourceId image resource
	 * @param		align Image Alignment
	 * @return		ConcreteElement or <code>null</code> when
	 * 				no help button should be painted
	 */
	public ConcreteElement createHelpElement(String helpId, String imageResourceId, AlignmentType align) {
		return createHelpElement(getPainterContext(), helpId, imageResourceId, align);
	}

	/**
	 * Creates a Help-Button with the given Help Id
	 *
	 * @param		ctx The painter context
	 * @param		helpId the help id
	 * @param		imageResourceId image resource
	 * @param		align Image Alignment
	 * @return		ConcreteElement or <code>null</code> when
	 * 				no help button should be painted
	 */
	public static ConcreteElement createHelpElement(PainterContext ctx, String helpId, String imageResourceId, AlignmentType align) {
		if (helpId == null) {
			// This control has no help context assigned
			return null;
		}

		// create a help button
		ActionPainter ap = ctx.createActionPainter(ControlActionDef.ACTION_HELP);
		ap.addParameter(helpId);
		ap.setImage(ctx.getImage(imageResourceId));
		ap.setImageStyle("margin-left:5px;");
		ap.setImageVAlign(align);
		ap.setTooltip(ctx.getFrameworkString(DefResources.FW_HELP_TOOLTIP));
		ap.setStyleClass(DefHtmlClass.HELP);
		ap.setTarget(ctx.getFrameworkString(DefResources.FW_HELP_TARGET));

		String onclick = ctx.getFrameworkString(DefResources.FW_HELP_ONCLICK);
		if ((onclick != null) && (onclick.trim().length() > 0)) {
			onclick = MessageFormat.format(onclick, new Object[]{helpId});

			ClientHandlerImp handler = new ClientHandlerImp();
			handler.setHandler(ClientEvent.ONCLICK, onclick);

			ap.setClientHandler(handler);
		}

		return ap.createElement();
	}

	/**
	 * @see com.cc.framework.ui.painter.ControlPainter#getElementClass(int)
	 */
	public String getElementClass(int type) {

		switch (type) {

			case DefClassType.CLASS_CAPTION:
				return DefHtmlClass.CAPTION;

			case DefClassType.CLASS_DETAIL:
				return DefHtmlClass.DETAIL;

			case DefClassType.CLASS_INNER_FRAME:
				return DefHtmlClass.INNER_FRAME;

			case DefClassType.CLASS_INNER_FRAME_NO_BORDER:
				return DefHtmlClass.INNER_FRAME_NO_BORDER;

			case DefClassType.CLASS_BODY:
				return DefHtmlClass.BODY;

			case DefClassType.CLASS_INNER_BODY:
				return DefHtmlClass.INNER_BODY;

			case DefClassType.CLASS_HEADER:
				return DefHtmlClass.HEADER;

			default :
				return super.getElementClass(type);
		}
	}
}