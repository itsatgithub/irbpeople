/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/controls/ColumnBaseTei.java,v 1.4 2005/02/16 18:03:06 P001001 Exp $
 * $Revision: 1.4 $
 * $Date: 2005/02/16 18:03:06 $
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

package com.cc.framework.taglib.controls;

import javax.servlet.jsp.tagext.TagData;
import javax.servlet.jsp.tagext.TagExtraInfo;
import javax.servlet.jsp.tagext.VariableInfo;

/**
 * Implementation of <code>TagExtraInfo</code> for the <b>columnhtml</b>
 * tag, identifying the scripting object(s) to be made visible.
 * 
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.4 $
 */
public abstract class ColumnBaseTei extends TagExtraInfo {

	/**
	 * The Suffix for the rowben key
	 */
	public static final String ROWKEY_SUFFIX = "_key";

	/**
	 * @see javax.servlet.jsp.tagext.TagExtraInfo#getVariableInfo(javax.servlet.jsp.tagext.TagData)
	 */
	public VariableInfo[] getVariableInfo(TagData tagdata) {

		// This methodis called only once(!) by the JSP-Processor
		// to generate the JSP-Sourcecode.
		// So it has no access to any dynamic data
		// from the application or the Tag Handler!

		String id	= tagdata.getAttributeString("id");

		if (id == null) {
			return null;
		} else {
			String type	= tagdata.getAttributeString("type");

			if (type == null) {
				// Use type "java.lang.Object" as a default
				type = Object.class.getName();
			}

			return new VariableInfo[] {
				new VariableInfo(
					id + ROWKEY_SUFFIX,
					String.class.getName(),
					true,
					VariableInfo.NESTED),
				new VariableInfo(
					id,
					type,
					true,
					VariableInfo.NESTED)};
		}
	}
}