/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/util/FilterTag.java,v 1.11 2005/07/08 14:15:19 P001002 Exp $
 * $Revision: 1.11 $
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

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ecs.ConcreteElement;
import org.apache.ecs.html.TD;
import org.apache.ecs.html.TR;
import org.apache.ecs.html.Table;

import com.cc.framework.ui.html.HtmlUtil;

/**
 * Tag-Handler for the <code>Filter</code> Tag.
 *
 * @author     <a href="mailto:gschulz@scc-gmbh.com">Gernot Schulz</a>
 * @version    $Revision: 1.11 $
 * @since      1.0
 */
public class FilterTag extends BodyTagSupport {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 4145084567982536758L;

	/**
	 * Commons Logging instance.
	 */
	private transient Log log	= LogFactory.getLog(this.getClass());

	/**
	 * indicates if the generated HTML-Code should be displayed
	 */
	private boolean showResult = false;


	// ------------------------------------------------
	//                methods
	// ------------------------------------------------

	/**
	 * Constructor
	 */
	public FilterTag() {
		super();
	}

	/**
	 * @see javax.servlet.jsp.tagext.BodyTagSupport#doAfterBody()
	 */
	public int doAfterBody() {
		BodyContent body = getBodyContent();

		String filteredBody = HtmlUtil.encodeHtml(body.getString());

		try	{
			JspWriter out = body.getEnclosingWriter();

			if (showResult) {
//				out.print(doCreateTable(body).toString());
				out.print("<H3>Generated HTML-Code</H3>");
				out.print(filteredBody);
				out.print("<BR>");
				out.print(body.getString());
			} else {
				out.print(filteredBody);
			}
		} catch (IOException io) {
			log.error(io.getMessage());
		}

		return SKIP_BODY;
	}



	/**
	 * Creates the output
	 * @param	body	the body content
	 * @return	ConcreteElement
	 */
	protected ConcreteElement doCreateTable(BodyContent body) {

		Table table = new Table();
		table.setBorder(1);
		table.setCellPadding(0);
		table.setCellSpacing(0);


		// Header
		TR tr = new TR()
			.addElement(new TD().addElement("Result"))
			.addElement(new TD().addElement("Generated HTML-Code"));

		table.addElement(tr);

		tr = new TR()
			.addElement(new TD().setWidth(100).addElement(body.getString()))
			.addElement(new TD().setWidth(400).addElement(HtmlUtil.encodeHtml(body.getString())));

		table.addElement(tr);

		return table;
	}

	/**
	 * Sets the Flag if the generated HTML-Code should be displayed
	 *
	 * @param result	Flag: true=show HTML-Code
	 */
	public void setResult(String result) {
		this.showResult = Boolean.valueOf(result).booleanValue();
	}
}