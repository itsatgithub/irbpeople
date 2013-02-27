/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/base/OptionTag.java,v 1.8 2005/08/23 12:22:27 P001002 Exp $
 * $Revision: 1.8 $
 * $Date: 2005/08/23 12:22:27 $
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

package com.cc.framework.taglib.base;

import java.io.IOException;
import java.lang.reflect.Constructor;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cc.framework.Globals;
import com.cc.framework.taglib.TagHelp;
import com.cc.framework.taglib.controls.ColumnSelectTag;
import com.cc.framework.taglib.controls.SelectTag;
import com.cc.framework.taglib.controls.SwapSelectTag;
import com.cc.framework.ui.html.HtmlUtil;
import com.cc.framework.ui.painter.PainterHelp;
import com.cc.framework.ui.painter.html.LiteralOptionsComparator;
import com.cc.framework.ui.painter.html.OptionsComparator;
import com.cc.framework.util.StringHelp;

/**
 * Tag handler for the <code>option</code> Tag.
 * The tag generates one single option element with the help
 * of a data model for a <html:select>- or <forms:select>-tag.
 * The tag can thereby process some of the basic data types
 * of the framework.
 * <ul>
 * 	<li>Object Array - The specification of the keyProperty and labelProperty is required.</li>
 * 	<li>OptionListDataModel</li>
 * 	<li>ListDataModel - The specification of the keyProperty and labelProperty is required.</li>
 * 	<li>TreeNodeDataModel - The specification of the keyProperty and labelProperty is required.</li>
 * 	<li>SimpleEnumType2</li>
 * </ul>
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.8 $
 * @since      1.5.000
 */
public class OptionTag extends BodyTagSupport {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -8550734799609128777L;

	// --------------------------------
	//   Class Memebers
	// --------------------------------

	/** Select Tag of the struts framework */
	private static Class strutsSelectTag = null;

	static {
		try {
			strutsSelectTag = Class.forName("org.apache.struts.taglib.html.SelectTag");
		} catch (ClassNotFoundException cnfe) {
			// The Struts framework adapter is not present!
		}
	}

	// --------------------------------
	//   Memebers
	// --------------------------------

	/**
	 * Commons Logging instance.
	 */
	private transient Log log = LogFactory.getLog(this.getClass());

	/**
	 * The options value
	 */
	private String key = null;

	/**
	 * The options text
	 */
	private String label = null;

	/**
	 * This flag indicates that the text is set
	 * from the tag body
	 */
	private boolean labelRaw = false;

	/**
	 * The maximum String length
	 */
	private int maxlength = -1;

	/**
	 * Is this option disabled?
	 */
	private boolean disabled = false;

	/**
	 * The style associated with this tag.
	 */
	private String style = null;

	/**
	 * The named style class associated with this tag.
	 */
	private String styleClass = null;

	/**
	 * The identifier associated with this tag.
	 */
	private String styleId = null;

	/**
	 * Specifies if all Strings should be converted
	 * into there HTML representation
	 */
	private boolean filter = true;

	// --------------------------------
	//      methods
	// --------------------------------

	/**
	 * Constructor
	 */
	public OptionTag() {
		super();
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#setPageContext(PageContext pc)
	 */
	public void setPageContext(PageContext pc) {
		super.setPageContext(pc);
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#release()
	 */
	public void release() {
		// initialize variables
		key			= null;
		label		= null;
		maxlength	= -1;
		disabled	= false;
		style		= null;
		styleClass	= null;
		styleId		= null;
		filter		= true;
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException {
		// The Tag has no Body
		return EVAL_BODY_BUFFERED;
	}

	/**
	 * Process the body text of this tag (if any).
	 *
	 * @return			SKIP_BODY
	 * @exception		JspException if a JSP exception
	 * 					has occurred
	 */
	public int doAfterBody() throws JspException {

		if (bodyContent != null) {
			String text = bodyContent.getString();
			if (text != null) {
				text = text.trim();
				if (text.length() > 0) {
					this.label		= text;
					this.labelRaw	= true;
				}
			}
		}

		return SKIP_BODY;
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException {

		OptionsComparator optionsComparator = null;

		// identify the selected option. It's defined by the enclosing tag
 		Object parentTag = pageContext.getAttribute(Globals.STRUTS_SELECT_KEY);

		if (parentTag != null) {
			if (parentTag instanceof SelectTag) {
				// Options Tag is embedded in a SelectTag or FormElementSelectTag
				optionsComparator = ((SelectTag) parentTag).getOptionsComparator();
			} else if (parentTag instanceof SwapSelectTag) {
				// Options Tag is embedded in a SwapSelectTag or FormElementSelectTag
				optionsComparator = ((SwapSelectTag) parentTag).getOptionsComparator();
			} else if (parentTag instanceof ColumnSelectTag) {
				// Options Tag is embedded in a ColumnSelectTag
				optionsComparator = new LiteralOptionsComparator(((ColumnSelectTag) parentTag).getCurrentProperty());
			} else if (TagHelp.checkForBaseClass(parentTag.getClass(), strutsSelectTag)) {
				// Options Tag is embedded in a struts SelectTag
				try {
					Class comparatorClass =
						Class.forName("com.cc.framework.taglib.StrutsSelectTagComparator");

					Constructor constructor =
						comparatorClass.getConstructor(new Class[]{strutsSelectTag});

					optionsComparator =
						(OptionsComparator) constructor.newInstance(new Object[]{parentTag});
				} catch (Exception e) {
					log.error("Could not create comparator instance", e);
				}
			}
		}

		// write the options list to the OutputStream
		JspWriter writer = pageContext.getOut();

		try {
			writer.print(renderOptionElement(optionsComparator));
		} catch (IOException e) {
			throw new JspException(e);
		}

		// Evaluate the remainder of this page
		return EVAL_PAGE;
	}

	/**
	 * Generate an HTML %lt;option&gt; element.
	 *
	 * @param			cmp The options comparator to check if this
	 * 					option is selected
	 * @return			HTML option elment string
	 * @exception		JspException if a JSP exception
	 * 					has occurred
	 */
	protected String renderOptionElement(OptionsComparator cmp) throws JspException {
		StringBuffer results = new StringBuffer();

		results
			.append("<option value=\"")
        	.append(key)
			.append("\"");

		if (disabled) {
			results.append(" disabled=\"disabled\"");
		}

		if (cmp.match(key)) {
			results.append(" selected=\"selected\"");
		}

		if (style != null) {
			results
				.append(" style=\"")
				.append(style)
				.append("\"");
		}
		if (styleId != null) {
			results
				.append(" id=\"")
				.append(styleId)
				.append("\"");
		}
		if (styleClass != null) {
			results
				.append(" class=\"")
				.append(styleClass)
				.append("\"");
		}

		results
			.append(">")
			.append(text())
			.append("</option>");

		return results.toString();
	}

	/**
	 * Return the text to be displayed to the user for this
	 * option (if any).
	 *
	 * @return		the text to display for this option
	 * @exception	JspException if an error occurs
	 */
	protected String text() throws JspException {
		String optionText = null;

		if (labelRaw) {
			optionText = label;
		} else {
			optionText = HtmlUtil.encodeHtml(
				StringHelp.truncate(
					PainterHelp.localize(pageContext, label), maxlength),
				filter);
		}

		// no body text and no key to lookup so display the value
		if (optionText == null) {
			optionText = key;
		}

		return optionText;
	}

	/**
	 * An HTML-style can be directly specified with this attribute.
	 * 
	 * @param style
	 *            The style to set
	 */
	public void setStyle(String style) {
		this.style = style;
	}

	/**
	 * The HTML-class attribute of the element can
	 * be specified with this attribute.
	 *
	 * @param styleClass The styleClass to set
	 */
	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	/**
	 * Set the style identifier for this tag.
	 *
	 * @param styleId The new style identifier
	 */
	public void setStyleId(String styleId) {
		this.styleId = styleId;
	}

	/**
	 * Specifies the options key.
	 *
	 * @param		key The key
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * Specifies the label text for the option
	 *
	 * @param		label The options label
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * Specifies the maximum number of characters that
	 * are displayed for the option elements
	 *
	 * @param	maxlength	The maximum number of characters
	 * @throws JspException
	 *             If the Argument can't be converted to int
	 */
	public void setMaxlength(String maxlength) throws JspException {
		this.maxlength = TagHelp.toInt(maxlength);
	}

	/**
	 * Specifies the maximum number of characters that
	 * are displayed for the option elements
	 *
	 * @param	maxlength	The maximum number of characters
	 */
	public void setMaxlength(int maxlength) {
		this.maxlength = maxlength;
	}

	/**
	 * Marks this option as disabled
	 *
	 * @param		disabled Disabled state
	 * @throws		JspException If <code>disabled</code> is not a
	 * 				valid boolean value
	 */
	public void setDisabled(String disabled) throws JspException {
		this.disabled = TagHelp.toBoolean(disabled);
	}

	/**
	 * Marks this option as disabled
	 *
	 * @param		disabled Disabled state
	 */
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	/**
	 * The automatic HTML coding of the column contents can be activated
	 * or disabled with the filter-attribute. The default is <code>true</code>
	 *
	 * @param	filter	<code>true</code> if the column content should be HTML encoded.
	 * @throws		JspException 	If the Argument can't be converted	to boolean
	 */
	public void setFilter(String filter) throws JspException {
		this.filter = TagHelp.toBoolean(filter);
	}
}