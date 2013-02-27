/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/global/GlobalPainterFactory.java,v 1.7 2005/07/12 06:36:47 P001002 Exp $
 * $Revision: 1.7 $
 * $Date: 2005/07/12 06:36:47 $
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

package com.cc.framework.ui.painter.global;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;

import com.cc.framework.common.Singleton;
import com.cc.framework.ui.control.Control;
import com.cc.framework.ui.painter.ControlPainter;
import com.cc.framework.ui.painter.PainterContext;
import com.cc.framework.ui.painter.PainterFactory;
import com.cc.framework.ui.painter.ResourceMap;

/**
 * Global Painter Factory Class.
 *
 * @author      <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version     $Revision: 1.7 $
 * @since       1.3
 */
public class GlobalPainterFactory extends PainterFactory implements Singleton {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 7596452325305109787L;

	/**
	 * The single instance of this class
	 */
	private static GlobalPainterFactory instance = new GlobalPainterFactory();

	/**
	 * Constructor
	 */
	protected GlobalPainterFactory() {
		super();
	}

	/**
	 * @see com.cc.framework.ui.painter.PainterFactory#createResourceMap()
	 */
	protected ResourceMap createResourceMap() {
		return new GlobalResourceMap();
	}

	/**
	 * Returns the unique Id for this Painterfactory
	 *
	 * @return	The unique Id for this Painterfactory which is "def"
	 */
	public String getFactoryId() {
		return "global";
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
		return null;
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

		// JavaScript resources
		writer.print("\t<script type='text/javascript' language='JavaScript' src='");
		writer.print(map.getString(GlobalResources.FW_FILE_JS_COMMON, false));
		writer.println("'></script>");

		writer.print("\t<script type='text/javascript' language='JavaScript' src='");
		writer.print(map.getString(GlobalResources.FW_FILE_JS_ENVIRONMENT, false));
		writer.println("'></script>");

		writer.print("\t<script type='text/javascript' language='JavaScript' src='");
		writer.print(map.getString(GlobalResources.FW_FILE_JS_UTILITY, false));
		writer.println("'></script>");

		writer.print("\t<script type='text/javascript' language='JavaScript' src='");
		writer.print(map.getString(GlobalResources.FW_FILE_JS_FORMATTER, false));
		writer.println("'></script>");
	}
}