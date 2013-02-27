/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/javascript/JavaScriptUtil.java,v 1.4 2005/05/01 08:56:23 P001002 Exp $
 * $Revision: 1.4 $
 * $Date: 2005/05/01 08:56:23 $
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

package com.cc.framework.ui.javascript;

import com.cc.framework.ui.model.ImageModel;


/**
 * Helper Class for JavaScript Resources
 *
 * @author      <a href="mailto:gschulz@scc-gmbh.com">Gernot Schulz</a>
 * @version     $Revision: 1.4 $
 * @since       1.5
 */
public abstract class JavaScriptUtil {

	/**
	 * Constructor
	 */
	private JavaScriptUtil() {
		super();
	}
	
	/**
	 * Creates a Javascript Icon Object which is used to generate  images
	 *
	 * @param resourceId	The id for this resource
	 * @param imageModel	The image model, containing the information from the registered image
	 * @return A String like  Icon(id, resPath, src, width, height, tooltip, alt)
	 */
	public static String doCreateJSImage(String resourceId, ImageModel imageModel) {

		// String to create: Icon(id, resPath, src, width, height, tooltip, alt)

		if (null == imageModel) {
			// Happens if the image was not registerd with the ResourceMap
			// Ensure that a valid javascript is generated
			return "Icon(null, null, null, null, null, null, null);";
		}

		StringBuffer img = new StringBuffer();

		img
			.append("Icon('")
			.append(resourceId)
			.append("', ")
			.append("null")
			.append(", '")
			.append(imageModel.getSource())
			.append("',")
			.append(imageModel.getWidth())
			.append(",")
			.append(imageModel.getHeight())
			.append(",")
			.append(imageModel.getTooltip())
			.append(",")
			.append(imageModel.getAlternate())
			.append(");");

		return img.toString();
	}

	/**
	 * Creates a String representation that is compatible
	 * with JavaScript syntax
	 *
	 * @param	str The string which should be transformed
	 * 			into a Java Script String
	 * @return	The string in valid JavaScript Syntax
	 */
	public static String encodeJavaScript(String str) {
	
		if (null == str) {
			return "";
		}
	
		StringBuffer out = new StringBuffer();
	
		char[] ch = str.toCharArray();
		for (int i = 0; i < ch.length; i++) {
	
			if (ch[i] == '\"') {
				out.append("\\\"");
			} else if (ch[i] == '\'') {
				out.append("\\'");
			} else if (ch[i] == '\\') {
				out.append("\\\\");
			} else {
				out.append(ch[i]);
			}
		}
	
		return out.toString();
	}
}