/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/def/DefMessagePainter.java,v 1.26 2005/09/27 14:06:22 P001002 Exp $
 * $Revision: 1.26 $
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

import java.util.Arrays;
import java.util.Collection;
import java.util.Vector;

import org.apache.ecs.AlignType;
import org.apache.ecs.ConcreteElement;
import org.apache.ecs.ElementContainer;
import org.apache.ecs.StringElement;
import org.apache.ecs.html.IMG;
import org.apache.ecs.html.TD;
import org.apache.ecs.html.TR;
import org.apache.ecs.html.Table;

import com.cc.framework.message.Message;
import com.cc.framework.ui.control.MessageControl;
import com.cc.framework.ui.model.FrameTitle;
import com.cc.framework.ui.model.InnerFrame;
import com.cc.framework.ui.painter.Frame;
import com.cc.framework.ui.painter.PainterContext;

/**
  * Base class for the message controls
 *
 * @author      <a href="mailto:hschulz@scc-gmbh.com">Gernot Schulz</a>
 * @version     $Revision: 1.26 $
 * @since       1.0
 */
public abstract class DefMessagePainter extends DefPainterBase {

	/**
	 * Images für die unterschiedlichen Fehlerklassen
	 */
	public static final String[] IMAGES =
		new String[] {
			DefResources.IMAGE_SEV_NONE,
			DefResources.IMAGE_SEV_QUESTION,
			DefResources.IMAGE_SEV_INFO,
			DefResources.IMAGE_SEV_WARN,
			DefResources.IMAGE_SEV_ERROR,
			DefResources.IMAGE_SEV_FATAL};

	/**
	 * Constructor
	 *
	 * @param	painterContext	The PainterContext
	 * @param	ctrl			The Control to render
	 */
	public DefMessagePainter(PainterContext painterContext, MessageControl ctrl) {
		super(painterContext);
	}

	/**
	 * Retrieves the control for this painter
	 *
	 * @return		Control
	 */
	protected MessageControl getCtrl() {
		return (MessageControl) getPainterContext().getControl();
	}

	/**
	 * Returns a list of messages that is filtered to the
	 * controls severity
	 *
	 * @return		Filtered Message List
	 */
	protected Message[] getMessages() {
		return getCtrl().getFilteredMessages();
	}

	/**
	 * Returns a severity image for the given message
	 *
	 * @param		message Message
	 * @return		Severity Image
	 */
	protected IMG createSeverityImg(Message message) {
		return createImage(IMAGES[message.getSeverity().toInt()]);
	}

	/**
	 * Creates an additional header.
	 *
	 * @return	ConcreteElement
	 */
	protected ConcreteElement doCreateHeader() {
		return null;
	}

	/**
	 * Template mthod that gets called when the body of the control should be
	 * rendered.
	 * 
	 * @return Body HTML Elements
	 */
	protected ConcreteElement doCreateBody() {

		Table body = (Table) new Table()
			.setCellPadding(0)
			.setCellSpacing(7)
			.setWidth("100%")
			.setClass(getElementClass(DefClassType.CLASS_INNER_BODY));

		Message[] messages = getMessages();

		// sort messages
		Arrays.sort(messages);

		// add messages
		for (int i = 0; i < messages.length; i++) {
			// now we add the single messages
			body.addElement(addMessage(messages[i]));
		}

		return body;
	}

	/**
	 * Creates an additional footer.
	 * The current layout does not render a footer
	 * @return	ConcreteElement
	 */
	protected ConcreteElement doCreateFooter() {
		InnerFrame[] frames = getCtrl().getInnerFrames(null);

		if ((frames == null) || (frames.length == 0)) {
			return null;
		} else {
			ElementContainer ec = new ElementContainer();
			for (int i = 0; i < frames.length; i++) {
				ec.addElement(getFramePainter().createInnerFrame(frames[i]));
			}

			return ec;
		}
	}

	/**
	 * Adds a single Message
	 *
	 * @param message	The message to add
	 * @return	ConcreteElement
	 */
	protected ConcreteElement addMessage(Message message) {
		TR tr = new TR()
			.setVAlign(AlignType.top)
			.addElement(new TD()
				.setWidth(10))
			.addElement(new TD()
				.addElement(createSeverityImg(message))
				.setVAlign(AlignType.top)
				.setStyle("padding-top: 6px;"))
			.addElement(new TD(html(message.getMessage(null), getCtrl().filter()))
				.setClass(DefHtmlClass.MESSAGE_BODY_DATA))
			.addElement(new TD()
				.setWidth(5));

		return tr;
	}

	/**
	 * Creates the buttons in the header of the list
	 *
	 * @return	collection of ConcreteElement Items
	 */
	protected Collection doCreateTitleButtons() {
		Vector buttons = new Vector();

		return buttons;
	}

	/**
	 * Creates the Title for the Frame
	 *
	 * @return		Frame Title or <code>null</code>
	 */
	protected FrameTitle getFrameTitle() {
		return new FrameTitle(
			getCtrl().getImage(),
			getSmartCaption(getCtrl().getCaption(), getCtrl().getDetail()),
			getSmartDetail(getCtrl().getCaption(), getCtrl().getDetail()),
			getCtrl().getHelp(),
			doCreateTitleButtons());
	}

	/**
	 * @see com.cc.framework.ui.painter.ControlPainter#doCreateElement()
	 */
	protected ConcreteElement doCreateElement() {
		// if no messages exists we terminate.
		// In this case return an empty HTML-Element!
		if (getCtrl().getDataModel() == null || getCtrl().size() == 0) {
			return new StringElement("");
		}

		// Render the control
		Frame control =
			getFramePainter().createFrame(getFrameTitle(), true);

		if (getStyleId() != null) {
			control.setID(getStyleId());
		}

		if (getCtrl().getStyle() != null) {
			control.setStyle(getCtrl().getStyle());
		}

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

		// body
		control
			.addBodyElement(doCreateHeader())
			.addBodyElement(doCreateBody())
			.addBodyElement(doCreateFooter());

		return control;
	}
}