/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/util/CalendarResourceTag.java,v 1.4 2005/07/08 14:15:19 P001002 Exp $
 * $Revision: 1.4 $
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

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.cc.framework.ui.painter.html.HtmlPainterHelp;

/**
 * This tag adds the javascript object "DTPRes" to a html page
 * and initalize the javascript object with the localized weekday
 * names, month names, button labels andd the window title.
 * The calendar.js file need this information to retrieve and
 * display the localized message strings. Predefined keys can
 * be used to specified the message resources within the resource
 * bundel for the application.
 * <ul>
 * 	<li>fw.calendar.button.ok.label      = Label for the OK button</li>
 * 	<li>fw.calendar.button.cancel.label  = Label for the Cancel button</li>
 * 	<li>fw.calendar.button.today.label   = Label for the Today button</li>
 * 	<li>fw.calendar.title                = Titel for the browser window</li>
 * </ul>
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Gernot Schulz</a>
 * @version    $Revision: 1.4 $
 * @since      1.4
 */
public class CalendarResourceTag extends TagSupport {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 5236906727340988350L;

	/**
	 * The locale for the calendar resources
	 */
	private String localeName;

	/**
	 * Constructor
	 */
	public CalendarResourceTag() {
		super();
	}

	/**
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	public int doStartTag() throws JspException {
		HtmlPainterHelp.createCalendarScript(pageContext, localeName);

		return SKIP_BODY;
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
