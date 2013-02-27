/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/def/DefPanelPainter.java,v 1.22 2005/06/02 14:09:01 P001002 Exp $
 * $Revision: 1.22 $
 * $Date: 2005/06/02 14:09:01 $
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

import java.util.Locale;

import org.apache.ecs.ConcreteElement;
import org.apache.ecs.Entities;
import org.apache.ecs.StringElement;

import com.cc.framework.ui.control.PanelControl;
import com.cc.framework.ui.model.ClientEvent;
import com.cc.framework.ui.model.FrameTitle;
import com.cc.framework.ui.model.ImageModel;
import com.cc.framework.ui.model.PanelContentDesignModel;
import com.cc.framework.ui.model.PanelItemDesignModel;
import com.cc.framework.ui.painter.Frame;
import com.cc.framework.ui.painter.PainterContext;
import com.cc.framework.ui.painter.PainterHelp;

/**
 * Painter for the Panel Control
 *
 * @author   <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version  $Revision: 1.22 $
 * @since    1.0
 */
public class DefPanelPainter extends DefPainterBase {

	/**
	 * Constructor
	 *
	 * @param	painterContext	The PainterContext
	 * @param	ctrl			The Control to render
	 */
	public DefPanelPainter(PainterContext painterContext, PanelControl ctrl) {
		super(painterContext);
	}

	/**
	 * Retrieves the control for this painter
	 *
	 * @return		Control
	 */
	protected PanelControl getCtrl() {
		return (PanelControl) getPainterContext().getControl();
	}

	/**
	 * Retrieves the filter setting for a given content element
	 *
	 * @param		content Content element
	 * @return		Boolean
	 */
	protected boolean filter(PanelContentDesignModel content) {
		return content.filter();
	}

	/**
	 * Retrieves the filter setting for a given item element
	 *
	 * @param		content Content element
	 * @param		item Panel item
	 * @return		Boolean
	 */
	protected boolean filter(PanelContentDesignModel content, PanelItemDesignModel item) {
		if (item.filter() == null) {
			return content.filter();
		} else {
			return item.filter().booleanValue();
		}
	}

	/**
	 * Retrieves the locale to use for a given content element
	 *
	 * @param		content Content element
	 * @return		Locale
	 */
	protected Locale getLocale(PanelContentDesignModel content) {
		if (content.getLocaleName() == null) {
			return getPainterContext().getLocale();
		} else {
			String localeName = content.getLocaleName();

			return PainterHelp.localeFromName(getPageContext(), localeName);
		}
	}

	/**
	 * Retrieves the locale to use for a given content element
	 *
	 * @param		content Content element
	 * @param		item Panel item
	 * @return		Locale
	 */
	protected Locale getLocale(PanelContentDesignModel content, PanelItemDesignModel item) {
		if (item.getLocaleName() == null) {
			return getLocale(content);
		} else {
			String localeName = item.getLocaleName();

			return PainterHelp.localeFromName(getPageContext(), localeName);
		}
	}

	/**
	 * Paints the Panel Body
	 *
	 * @param	content Pannel Content
	 * @return	ConcreteElement
	 */
	protected ConcreteElement doCreateBody(PanelContentDesignModel content) {

		PanelItemDesignModel[] items = content.getItems();
		ImageModel image = content.getImage();

		StringBuffer out = new StringBuffer();

		out.append("<table width='100%' cellspacing='0' cellpadding='0' class='body'>");

		if (null != image) {
			out.append(doCreateImage(image));
		}

		boolean firstVisible = true;

		for (int i = 0; i < content.getItems().length; i++) {

			// is this panel visible for the user?
			if (!items[i].getPermission().isGranted(getPrincipal())) {
				continue;
			}

			// draw a spacer
			if (!firstVisible) {
				out.append(doCreateSpacer());
			} else {
				firstVisible = false;
			}

			out.append(doCreateLink(content, items[i]));
			out.append(doCreateDetail(content, items[i]));
		}

		out.append("</table>");

		return new StringElement(out.toString());
	}

	/**
	 * Creates the image
	 *
	 * @param image	The ImageModel for the current panel item
	 * @return	StringBuffer
	 */
	protected StringBuffer doCreateImage(ImageModel image) {

		StringBuffer out = new StringBuffer();

		out
			.append("<tr>")
			.append("<td class='img'>")
			.append(PainterHelp.createImageStr(getPageContext(), image))
			.append("</td>")
			.append("</tr>");

		return out;
	}

	/**
	 * Creates the hyperlink
	 *
	 * @param		content Content element
	 * @param		item The PanelItemDesignModel for the current panel item
	 * @return		StringBuffer
	 */
	protected StringBuffer doCreateLink(PanelContentDesignModel content, PanelItemDesignModel item) {

		boolean filter	= filter(content, item);
		Locale locale	= getLocale(content, item);

		String target	= "";
		String handler	= "";
		String tooltip	= "";

		if (null != item.getTarget() && item.getTarget().length() > 0) {
			target = " target='" + item.getTarget() + "'";
		}

		if (null != item.getHandler(ClientEvent.ONCLICK)) {
			handler = " onclick=\"" + item.getHandler(ClientEvent.ONCLICK) + "\"";
		}

		if (null != item.getTooltip()) {
			tooltip = " title=\"" + html(localize(item.getTooltip(), locale), filter) + "\"";
		}

		StringBuffer out = new StringBuffer();

		out
			.append("<tr>")
			.append("<td class='text'>")
			.append("<a href='")
			.append(decorateURL(item.getAction()))
			.append("' ")
			.append(tooltip)
			.append(target)
			.append(handler)
			.append(">")
			.append(createImage(DefResources.IMAGE_LINK_EXTERNAL))
			.append(Entities.NBSP)
			.append(html(localize(item.getTitle(), locale), filter))
			.append("</a>")
			.append("</td>")
			.append("</tr>");

		return out;
	}

	/**
	 * Creates the detail
	 *
	 * @param		content Content element
	 * @param		item The PanelItemDesignModel for the current panel item
	 * @return		StringBuffer
	 */
	protected StringBuffer doCreateDetail(PanelContentDesignModel content, PanelItemDesignModel item) {

		boolean filter	= filter(content, item);
		Locale locale	= getLocale(content, item);

		StringBuffer out = new StringBuffer();

		if (null != item.getDetail()) {
			out
				.append("<tr>")
				.append("<td class='text'>")
				.append(html(localize(item.getDetail(), locale), filter))
				.append("</td>")
				.append("</tr>");
		}

		return out;
	}

	/**
	 * Creates a Spacer between the items
	 * @return	StringBuffer
	 */
	protected StringBuffer doCreateSpacer() {

		StringBuffer out = new StringBuffer()
			.append("<tr>")
			.append("<td class='line' colspan='2'>")
			.append("<img src='").append(getImageSrc(DefResources.IMAGE_SPACER)).append("' width='1' height='1' border='0' alt=''>")
			.append("</td>")
			.append("</tr>");

		return out;
	}

	/**
	 * Creates the Title for the Frame
	 * 
	 * @return		Frame Title or <code>null</code>
	 */
	protected FrameTitle getFrameTitle() {
		PanelContentDesignModel[] content = getCtrl().getContent();

		if ((content == null) || (content.length == 0)) {
			return null;
		}

		return getFrameTitle(content[0]);
	}

	/**
	 * Creates the Title for the Frame
	 * 
	 * @param		content The Panel content section
	 * @return		Frame Title or <code>null</code>
	 */
	protected FrameTitle getFrameTitle(PanelContentDesignModel content) {
		boolean filter	= filter(content);
		Locale locale	= getLocale(content);
	
		FrameTitle title = new FrameTitle();
		title.setFilter(filter);
		title.setCaption(localize(content.getTitle(), locale));
		title.setTooltip(localize(content.getTooltip(), locale));
			
		return title;
	}

	/**
	 * Creates the HTML Code for th following Elements:
	 * <ul>
	 *   <li>Title</li>
	 *   <li>Body</li>
	 *   <li>Footer (optional)</li>
	 * </ul>
	 * @return	ConcreteElement
	 */
	protected ConcreteElement doCreateElement() {

		// if no designmodel is specified then terminate
		if (getCtrl().getDesignModel() == null) {
			return null;
		}

		// check permission to show this panel
		if (!getCtrl().getPermission().isGranted(getPrincipal())) {
			return null;
		}

		boolean border = true;

		// render the control
		Frame control =
			getFramePainter().createFrame(getFrameTitle(), border);

		if (getStyleId() != null) {
			control.setID(getStyleId());
		}

		if (getCtrl().getStyle() != null) {
			control.setStyle(getCtrl().getStyle());
		}

		// set general style class
		if (getCtrl().getStyleClass() == null) {
			control.setClass(DefHtmlClass.PANEL);
		} else {
			control.setClass(getCtrl().getStyleClass());
		}

		if (getCtrl().getWidth() != null) {
			control.setWidth(getCtrl().getWidth());
		}

		if (getCtrl().getSummary() != null) {
			control.setSummary(getCtrl().getSummary());
		}

		// Render the Groups within the Panel
		PanelContentDesignModel[] content = getCtrl().getContent();
		for (int i = 0; i < content.length; i++) {

			// check permission - show panel content ?
			if (content[i].show(getPrincipal())) {
				// create the section Header
				if (i > 0) {
					getFramePainter().addSection(
						control,
						getFrameTitle(content[i]),
						border);
				}

				// create the Content Body
				control.addBodyElement(doCreateBody(content[i]));
			}
		}

		return control;
	}
}