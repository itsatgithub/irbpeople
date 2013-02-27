/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/html/HtmlPainterFactory.java,v 1.21 2005/08/08 10:04:37 P001002 Exp $
 * $Revision: 1.21 $
 * $Date: 2005/08/08 10:04:37 $
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

import java.io.IOException;

import javax.servlet.jsp.JspWriter;

import com.cc.framework.common.Singleton;
import com.cc.framework.ui.control.CalendarControl;
import com.cc.framework.ui.control.CheckboxControl;
import com.cc.framework.ui.control.ColorPickerControl;
import com.cc.framework.ui.control.Control;
import com.cc.framework.ui.control.HiddenControl;
import com.cc.framework.ui.control.PlainTextControl;
import com.cc.framework.ui.control.RadioControl;
import com.cc.framework.ui.control.SelectControl;
import com.cc.framework.ui.control.SpinControl;
import com.cc.framework.ui.control.SwapSelectControl;
import com.cc.framework.ui.control.TextControl;
import com.cc.framework.ui.control.TextPopupControl;
import com.cc.framework.ui.control.TextareaControl;
import com.cc.framework.ui.painter.ControlPainter;
import com.cc.framework.ui.painter.PainterContext;
import com.cc.framework.ui.painter.PainterFactory;
import com.cc.framework.ui.painter.ResourceMap;

/**
 * Factory for creating HTML-Painter.
 * <p>
 * If new HTML painters are developed which belongs to
 * the painter.html package they should be registered in this
 * Painter. Therefor add the new class in the doCreatePainter() method
 * or create a new subclass and override the doCreatePainter() methode
 * an register the new class at startup of the application.
 * If you create other painters which do not belong to this package
 * create a new package an generate an new PainterFactory class which
 * extends the PainterFactory base class. In your application register
 * the painterfactory at startup. A new painter normaly serves a new GUI layout.
 *
 * <pre>
 * Note:
 * If you want to use the HTMLPainter it must be registered at startup.
 * The best place is in your FrontControllerServlet init() method.
 *
 *	public void init() throws ServletException {
 *
 *		super.init();
 *
 *		// Register all PainterFactories
 *		PainterFactory.registerApplicationPainter(getServletContext(), DefPainterFactory.instance());
 *		PainterFactory.registerApplicationPainter(getServletContext(), HtmlPainterFactory.instance());
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
 * @version     $Revision: 1.21 $
 * @since       1.0
 */
public class HtmlPainterFactory extends PainterFactory implements Singleton {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 4585229785716307310L;

	/**
	 * The single instance of this class
	 */
	private static HtmlPainterFactory instance = new HtmlPainterFactory();

	/**
	 * Constructor for HtmlPainterFactory
	 */
	protected HtmlPainterFactory() {
		super();
	}

	/**
	 * @see com.cc.framework.ui.painter.PainterFactory#createResourceMap()
	 */
	protected ResourceMap createResourceMap() {
		return new HtmlResourceMap();
	}

	/**
	 * Returns a unique Id for the Factory
	 *
	 * @return	The id of the factory which is "html" for HTML-Painter
	 */
	public String getFactoryId() {
		return "html";
	}

	/**
	 * Returns the singelton instance of this class
	 *
	 * @return	The HtmlPainterFactory
	 */
	public static PainterFactory instance() {
		return instance;
	}

	/**
	 * @see com.cc.framework.ui.painter.PainterFactory#doCreatePainter(com.cc.framework.ui.painter.PainterContext, com.cc.framework.ui.control.Control)
	 */
	protected ControlPainter doCreatePainter(PainterContext painterContext, Control ctrl) {

		// Achtung: Bei voneinander abgeleiteten Control-Klassen muss
		// auf die Reihenfolge der instanceof-Vergleiche geachtet werden!
		// Erst die Unter- und dann die Oberklassen!!

		//
		// Check for HTML-Controls which can be created
		// by this Factory
		//
		if (ctrl instanceof ColorPickerControl) {
			return new HtmlColorPickerPainter(painterContext, (ColorPickerControl) ctrl);
		} else if (ctrl instanceof CalendarControl) {
			return new HtmlCalendarPainter(painterContext, (CalendarControl) ctrl);
		} else if (ctrl instanceof SpinControl) {
			return new HtmlSpinPainter(painterContext, (SpinControl) ctrl);
		} else if (ctrl instanceof HiddenControl) {
			return new HtmlHiddenPainter(painterContext, (HiddenControl) ctrl);
		} else if (ctrl instanceof PlainTextControl) {
			return new HtmlPlainTextPainter(painterContext, (PlainTextControl) ctrl);
		} else if (ctrl instanceof TextControl) {
			return new HtmlTextPainter(painterContext, (TextControl) ctrl);
		} else if (ctrl instanceof TextPopupControl) {
			return new HtmlTextPopupPainter(painterContext, (TextPopupControl) ctrl);
		} else if (ctrl instanceof TextareaControl) {
			return new HtmlTextareaPainter(painterContext, (TextareaControl) ctrl);
		} else if (ctrl instanceof SelectControl) {
			return new HtmlSelectPainter(painterContext, (SelectControl) ctrl);
		} else if (ctrl instanceof SwapSelectControl) {
			return new HtmlSwapSelectPainter(painterContext, (SwapSelectControl) ctrl);
		} else if (ctrl instanceof CheckboxControl) {
			return new HtmlCheckboxPainter(painterContext, (CheckboxControl) ctrl);
		} else if (ctrl instanceof RadioControl) {
			return new HtmlRadioPainter(painterContext, (RadioControl) ctrl);
		}

		return null;
	}

	/**
	 * @see com.cc.framework.ui.painter.PainterFactory#doCreateHeaderIncludes(javax.servlet.jsp.JspWriter)
	 */
	protected void doCreateHeaderIncludes(JspWriter writer) throws IOException {
		// no action
	}
}