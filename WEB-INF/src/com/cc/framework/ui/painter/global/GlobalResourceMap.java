/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/global/GlobalResourceMap.java,v 1.5 2005/07/12 06:36:47 P001002 Exp $
 * $Revision: 1.5 $
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

import com.cc.framework.ui.painter.ResourceMapImp;

/**
 * The GlobalResourceMap registers all images that are needed by
 * the GlobalPainter.
 *
 * @author	  <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version	  $Revision: 1.5 $
 * @since     1.2
 */
public class GlobalResourceMap extends ResourceMapImp implements GlobalResources {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -817926386365004246L;

	/**
	 * The base directory used for resources by this Painterfactory
	 */
	private static final String RESOURCE_DIR = "../fw/global/";

	/**
	 * Constructor
	 */
	public GlobalResourceMap() {
		super();

		setResourceDir(RESOURCE_DIR);
	}

	/**
	 * @see com.cc.framework.ui.painter.ResourceMapImp#doRegisterColors()
	 */
	protected void doRegisterColors() {
		// no resources
	}

	/**
	 * @see com.cc.framework.ui.painter.ResourceMapImp#doRegisterImages()
	 */
	protected void doRegisterImages() {
		// no resources
	}

	/**
	 * @see com.cc.framework.ui.painter.ResourceMapImp#doRegisterStrings()
	 */
	protected void doRegisterStrings() {

		// Register Java Script Files
		registerString(FW_FILE_JS_COMMON,		expandFileName("jscript/common.js"));
		registerString(FW_FILE_JS_ENVIRONMENT,	expandFileName("jscript/environment.js"));
		registerString(FW_FILE_JS_UTILITY,		expandFileName("jscript/utility.js"));
		registerString(FW_FILE_JS_FORMATTER,	expandFileName("jscript/formatter.js"));
	}

	/**
	 * Expands a file name
	 *
	 * @param		src Fielename
	 * @return		Expanded filename
	 */
	protected String expandFileName(String src) {
		StringBuffer fullPath = new StringBuffer()
			.append(getResourceDir())
			.append(src);

		return fullPath.toString();
	}
}