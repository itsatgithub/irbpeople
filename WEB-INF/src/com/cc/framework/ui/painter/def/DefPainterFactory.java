/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/def/DefPainterFactory.java,v 1.40 2005/07/31 16:06:02 P001002 Exp $
 * $Revision: 1.40 $
 * $Date: 2005/07/31 16:06:02 $
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

import java.io.IOException;

import javax.servlet.jsp.JspWriter;

import com.cc.framework.common.Severity;
import com.cc.framework.common.Singleton;
import com.cc.framework.ui.FormType;
import com.cc.framework.ui.MenuType;
import com.cc.framework.ui.control.ButtonControl;
import com.cc.framework.ui.control.Control;
import com.cc.framework.ui.control.CrumbsControl;
import com.cc.framework.ui.control.FormControl;
import com.cc.framework.ui.control.FrameControl;
import com.cc.framework.ui.control.GaugeControl;
import com.cc.framework.ui.control.HeadlineControl;
import com.cc.framework.ui.control.InfoControl;
import com.cc.framework.ui.control.ListControl;
import com.cc.framework.ui.control.MenuControl;
import com.cc.framework.ui.control.MessageControl;
import com.cc.framework.ui.control.PanelControl;
import com.cc.framework.ui.control.RecurrencePatternControl;
import com.cc.framework.ui.control.SchedulerControl;
import com.cc.framework.ui.control.TabbarControl;
import com.cc.framework.ui.control.TabsetControl;
import com.cc.framework.ui.control.TreeControl;
import com.cc.framework.ui.painter.ControlPainter;
import com.cc.framework.ui.painter.FramePainter;
import com.cc.framework.ui.painter.PainterContext;
import com.cc.framework.ui.painter.PainterFactory;
import com.cc.framework.ui.painter.ResourceMap;
import com.cc.framework.ui.painter.def.frame.DefErrorFramePainter;
import com.cc.framework.ui.painter.def.frame.DefFormFramePainter;
import com.cc.framework.ui.painter.def.frame.DefInfoFramePainter;
import com.cc.framework.ui.painter.def.frame.DefPanelFramePainter;
import com.cc.framework.ui.painter.def.frame.DefSearchFramePainter;
import com.cc.framework.ui.painter.def.frame.DefSeverityFramePainter;
import com.cc.framework.ui.painter.def.frame.DefSimpleFramePainter;
import com.cc.framework.ui.painter.def.frame.DefTabbedFramePainter;
import com.cc.framework.ui.painter.def.frame.DefWarningFramePainter;

/**
 * Factory Class for creating the Default-Painters.
 * <p>
 * Important:<br>
 * The Defaultpainter must be registerd in your application at
 * startup. The best place to do this is in your
 * FrontControllerServlet init() method.
 *
 * <pre>
 *	public void init() throws ServletException {
 *
 *		super.init();
 *
 *		// Register all PainterFactories
 *		PainterFactory.registerApplicationPainter(getServletContext(), DefPainterFactory.instance());
 *		PainterFactory.registerApplicationPainter(getServletContext(), HtmlPainterFactory.instance());
 *		// other painters go here ...
 *
 *		// Log the information about the system-environment.
 *		if (log.isInfoEnabled()) {
 *	 		log.info(createApplicationInfo());
 *			log.info(HttpUtil.createEnvironmentInfo());
 *			log.info(HttpUtil.createContextInfo(getServletContext()));
 *		}
 *	}
 * </pre>
 * </p>
 *
 * @author      <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version     $Revision: 1.40 $
 * @since       1.0
 */
public class DefPainterFactory extends PainterFactory implements Singleton {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -8237928430487378901L;

	/**
	 * The single instance of this class
	 */
	private static DefPainterFactory instance = new DefPainterFactory();

	/**
	 * Constructor
	 */
	protected DefPainterFactory() {
		super();
	}

	/**
	 * @see com.cc.framework.ui.painter.PainterFactory#createResourceMap()
	 */
	protected ResourceMap createResourceMap() {
		return new DefResourceMap();
	}

	/**
	 * Returns the unique Id for this Painterfactory
	 *
	 * @return	The unique Id for this Painterfactory which is "def"
	 */
	public String getFactoryId() {
		return "def";
	}

	/**
	 * Returns the single instance of the class (singleton)
	 *
	 * @return	The single instance  of the DefPainterFactory
	 */
	public static PainterFactory instance() {
		return instance;
	}

	/**
	 * @see com.cc.framework.ui.painter.PainterFactory#doCreatePainter(com.cc.framework.ui.painter.PainterContext, com.cc.framework.ui.control.Control)
	 */
	protected ControlPainter doCreatePainter(PainterContext painterContext, Control ctrl) {

		// Attention: first the subclass and then the baseclass!
		// Otherwise the instanceof-comparison will fail!!

		if (ctrl instanceof ButtonControl) {
			return new DefButtonPainter(painterContext, (ButtonControl) ctrl);
		} else if (ctrl instanceof TreeControl) {
			return new DefTreePainter(painterContext, (TreeControl) ctrl);
		} else if (ctrl instanceof ListControl) {
			// the SimpleListControl and the TreeListControl are using the same painter.
			return new DefListPainter(painterContext, (ListControl) ctrl);
		} else if (ctrl instanceof HeadlineControl) {
			return new DefHeadlinePainter(painterContext, (HeadlineControl) ctrl);
		} else if (ctrl instanceof TabsetControl) {
			return new DefTabsetPainter(painterContext, (TabsetControl) ctrl);
		} else if (ctrl instanceof TabbarControl) {
			return new DefTabbarPainter(painterContext, (TabbarControl) ctrl);
		} else if (ctrl instanceof GaugeControl) {
			return new DefGaugePainter(painterContext, (GaugeControl) ctrl);
		} else if (ctrl instanceof FormControl) {
			FormType ft = ((FormControl) ctrl).getFormType();

			if ((ft == FormType.CREATE) || (ft == FormType.EDIT)) {
				return new DefFormEditPainter(painterContext, (FormControl) ctrl);
			} else if (ft == FormType.DISPLAY) {
				return new DefFormDisplayPainter(painterContext, (FormControl) ctrl);
			} else if (ft == FormType.SEARCH) {
				return new DefFormSearchPainter(painterContext, (FormControl) ctrl);
			} else if (ft == FormType.HEADER) {
				return new DefFormHeaderPainter(painterContext, (FormControl) ctrl);
			} else if (ft == FormType.INFO) {
				return new DefFormInfoPainter(painterContext, (FormControl) ctrl);
			}
		} else if (ctrl instanceof MenuControl) {
			MenuType mt = ((MenuControl) ctrl).getType();

			if (MenuType.SIDEBAR.equals(mt)) {
				return new DefMenuSidebarPainter(painterContext, (MenuControl) ctrl);
			} else if (MenuType.MAIN.equals(mt)) {
				return new DefMenuMainPainter(painterContext, (MenuControl) ctrl);
			} else if (MenuType.TOOLS.equals(mt)) {
				return new DefMenuToolsPainter(painterContext, (MenuControl) ctrl);
			}
		} else if (ctrl instanceof FrameControl) {
			return new DefFramePainter(painterContext, (FrameControl) ctrl);
		} else if (ctrl instanceof CrumbsControl) {
			return new DefCrumbsPainter(painterContext, (CrumbsControl) ctrl);
		} else if (ctrl instanceof InfoControl) {
			return new DefInfoPainter(painterContext, (InfoControl) ctrl);
		} else if (ctrl instanceof MessageControl) {
			Severity severity = ((MessageControl) ctrl).getSeverity();

			if (severity.isError()) {
				return new DefMessageErrorPainter(painterContext, (MessageControl) ctrl);
			} else if (severity.isWarning()) {
				return new DefMessageWarningPainter(painterContext, (MessageControl) ctrl);
			} else if (severity.isInformation()) {
				return new DefMessageInfoPainter(painterContext, (MessageControl) ctrl);
			} else {
				return new DefMessageSeverityPainter(painterContext, (MessageControl) ctrl);
			}
		} else if (ctrl instanceof PanelControl) {
			return new DefPanelPainter(painterContext, (PanelControl) ctrl);
		} else if (ctrl instanceof SchedulerControl) {
			return new DefSchedulerPainter(painterContext, (SchedulerControl) ctrl);
		} else if (ctrl instanceof RecurrencePatternControl) {
			return new DefRecurrencePatternPainter(painterContext, (RecurrencePatternControl) ctrl);
		}

		return null;
	}

	/**
	 * @see com.cc.framework.ui.painter.PainterFactory#doCreateFramePainter(com.cc.framework.ui.painter.PainterContext, com.cc.framework.ui.control.Control)
	 */
	protected FramePainter doCreateFramePainter(PainterContext painterContext, Control ctrl) {

		if (ctrl instanceof InfoControl) {
			return new DefSimpleFramePainter();
		} else if (ctrl instanceof PanelControl) {
			return new DefPanelFramePainter();
		} else if (ctrl instanceof TabbarControl) {
			return new DefTabbedFramePainter(false);
		} else if (ctrl instanceof FormControl) {
			FormType ft = ((FormControl) ctrl).getFormType();

			if (ft == FormType.SEARCH) {
				return new DefSearchFramePainter();
			}
		} else if (ctrl instanceof MessageControl) {
			Severity severity = ((MessageControl) ctrl).getSeverity();

			if (severity.isError()) {
				return new DefErrorFramePainter();
			} else if (severity.isWarning()) {
				return new DefWarningFramePainter();
			} else if (severity.isInformation()) {
				return new DefInfoFramePainter();
			} else {
				return new DefSeverityFramePainter();
			}
		}

		return new DefFormFramePainter();
	}

	/**
	 * @see com.cc.framework.ui.painter.PainterFactory#doCreateHeaderIncludes(javax.servlet.jsp.JspWriter)
	 */
	protected void doCreateHeaderIncludes(JspWriter writer) throws IOException {

		ResourceMap map = getResources();

		// If a painter uses other directorys for Cascading Stylesheets
		// this methode can be overriden in the subclass
		// Note: If only the Cascading Stylesheets are modefied. The behavoir
		// and layout of the controls will be the same.

		// CSS resources
		writer.print("\t<link rel='stylesheet' href='");
		writer.print(map.getString(DefResources.FW_FILE_CSS_DEFAULT, false));
		writer.println("' charset='ISO-8859-1' type='text/css'>");

		// JavaScript resources
		writer.print("\t<script type='text/javascript' language='JavaScript' src='");
		writer.print(map.getString(DefResources.FW_FILE_JS_FUNCTIONS, false));
		writer.println("'></script>");

		writer.print("\t<script type='text/javascript' language='JavaScript' src='");
		writer.print(map.getString(DefResources.FW_FILE_JS_RESOURCEMAP, false));
		writer.println("'></script>");

		writer.print("\t<script type='text/javascript' language='JavaScript' src='");
		writer.print(map.getString(DefResources.FW_FILE_JS_CONTROLS, false));
		writer.println("'></script>");

		writer.print("\t<script type='text/javascript' language='JavaScript' src='");
		writer.print(map.getString(DefResources.FW_FILE_JS_TABSET, false));
		writer.println("'></script>");

		writer.print("\t<script type='text/javascript' language='JavaScript' src='");
		writer.print(map.getString(DefResources.FW_FILE_JS_TREE, false));
		writer.println("'></script>");

		writer.print("\t<script type='text/javascript' language='JavaScript' src='");
		writer.print(map.getString(DefResources.FW_FILE_JS_LIST, false));
		writer.println("'></script>");
	}
}