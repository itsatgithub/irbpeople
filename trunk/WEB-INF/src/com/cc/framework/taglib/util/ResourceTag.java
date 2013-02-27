/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/util/ResourceTag.java,v 1.7 2005/07/08 14:15:19 P001002 Exp $
 * $Revision: 1.7 $
 * $Date: 2005/07/08 14:15:19 $
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

package com.cc.framework.taglib.util;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.cc.framework.taglib.TagHelp;
import com.cc.framework.ui.html.HtmlUtil;
import com.cc.framework.ui.painter.PainterFactory;
import com.cc.framework.ui.painter.PainterHelp;

/**
 * Translates the given resource key
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Gernot Schulz</a>
 * @version    $Revision: 1.7 $
 * @since      1.4
 */
public class ResourceTag extends TagSupport {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -7058706766632576195L;

	/**
	 * The optional locale
	 */
	private String localeName;

	/**
	 * The resource key
	 */
	private String key;

	/**
	 * HTML encoding active
	 */
	private boolean filter = true;

	/**
	 * Constructor
	 */
	public ResourceTag() {
		super();
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#release()
	 */
	public void release() {
		super.release();

		localeName = null;
		key = null;
		filter = true;
	}

	/**
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	public int doStartTag() throws JspException {

		try {
			JspWriter out = pageContext.getOut();
			paint(out);
		} catch (IOException io) {
			throw new JspException(io.getMessage());
		}

		return SKIP_BODY;
	}

	/**
	 * Generate the JavaScript and initializ the DTPRes
	 * JavaScript object.
	 *
	 * @param writer	JspWriter
	 * @throws IOException	If an IO Exception occur
	 */
	protected void paint(JspWriter writer) throws IOException {
		Locale locale = PainterHelp.localeFromName(pageContext, localeName);

		// localize the key
		String value = PainterHelp.localizeKey(pageContext, key, null, locale, true);

		// if not found check the resource map
		if (value == null) {
			value = PainterFactory.getStringResource(pageContext, key);
		}

		writer.write(HtmlUtil.encodeHtml(value, filter));
	}

	/**
	 * The automatic HTML coding of the column contents can be activated
	 * or disabled with the filter-attribute. The default is <code>true</code>
	 *
	 * @param	filter	<code>true</code> if the column content should be HTML encoded.
	 * @throws	JspException If the Argument can't be converted to boolean
	 */
	public void setFilter(String filter) throws JspException {
		this.filter = TagHelp.toBoolean(filter);
	}

	/**
	 * Sets the resource key which should be translated
	 *
	 * @param	key resource key
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * Sets a local.
	 *
	 * @param localeName	The local
	 */
	public void setLocale(String localeName) {
		this.localeName = localeName;
	}
}
