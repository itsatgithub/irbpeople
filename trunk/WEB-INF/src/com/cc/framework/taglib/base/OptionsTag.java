/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/taglib/base/OptionsTag.java,v 1.28 2005/08/23 12:22:27 P001002 Exp $
 * $Revision: 1.28 $
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
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cc.framework.Globals;
import com.cc.framework.taglib.TagHelp;
import com.cc.framework.taglib.controls.ColumnSelectTag;
import com.cc.framework.taglib.controls.SelectTag;
import com.cc.framework.taglib.controls.SwapSelectTag;
import com.cc.framework.ui.model.OptionListDesignModel;
import com.cc.framework.ui.model.imp.OptionListDesignModelImp;
import com.cc.framework.ui.painter.html.OptionsComparator;
import com.cc.framework.ui.painter.html.OptionsPainter;

/**
 * Tag handler for the <code>options</code> Tag. The tag generates the option
 * list with the help of a data model for a <html:select>- or
 * <forms:select>-tag. The tag can thereby process some of the basic data types
 * of the framework.
 * <ul>
 * <li>Object Array - The specification of the keyProperty and labelProperty is
 * required.</li>
 * <li>OptionListDataModel</li>
 * <li>ListDataModel - The specification of the keyProperty and labelProperty
 * is required.</li>
 * <li>TreeNodeDataModel - The specification of the keyProperty and
 * labelProperty is required.</li>
 * <li>SimpleEnumType2</li>
 * </ul>
 * 
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version $Revision: 1.28 $
 * @since 1.0
 */
public class OptionsTag extends TagSupport {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 6910463561239054241L;

	// --------------------------------
	// Class Memebers
	// --------------------------------

	/** Select Tag of the struts framework */
	private static Class strutsSelectTag = null;

	static {
		try {
			strutsSelectTag = Class
					.forName("org.apache.struts.taglib.html.SelectTag");
		} catch (ClassNotFoundException cnfe) {
			// The Struts framework adapter is not present!
		}
	}

	// --------------------------------
	// Memebers
	// --------------------------------

	/**
	 * Commons Logging instance.
	 */
	private transient Log log = LogFactory.getLog(this.getClass());

	/**
	 * Field name
	 */
	private String name = null;

	/**
	 * The design model
	 */
	private OptionListDesignModel designModel = null;

	// --------------------------------
	// methods
	// --------------------------------

	/**
	 * Constructor
	 */
	public OptionsTag() {
		super();
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#release()
	 */
	public void release() {
		designModel = null;
		name = null;
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#setPageContext(PageContext pc)
	 */
	public void setPageContext(PageContext pc) {
		super.setPageContext(pc);

		// initialize variables
		designModel = null;
		name = null;
	}

	/**
	 * Retrieves or creates the design model
	 * 
	 * @return OptionListDesignModel
	 */
	public OptionListDesignModel getDesignModel() {
		if (designModel == null) {
			designModel = new OptionListDesignModelImp();
		}

		return designModel;
	}

	/**
	 * Retrieves the bean which holds the Display Data or Control Instance
	 * 
	 * @return Bean (Control instance or DataModel)
	 * @throws JspException
	 *             Is thrown when the bean could not be found
	 */
	protected Object lookupBean() throws JspException {
		return TagHelp.lookupBean(pageContext, this, name, getDesignModel()
				.getProperty(), null);
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException {
		// The Tag has no Body
		return SKIP_BODY;
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException {

		// Paint the Option list direct to the output stream
		boolean paintDirect = true;

		OptionsComparator optionsComparator = null;

		// search the model
		Object optionsList = lookupBean();

		// identify the selected option. It's defined by the enclosing tag
		Object parentTag = pageContext.getAttribute(Globals.STRUTS_SELECT_KEY);

		if (parentTag != null) {
			if (parentTag instanceof SelectTag) {
				// Options Tag is embedded in a SelectTag or
				// FormElementSelectTag
				paintDirect = false;

				SelectTag selectTag = (SelectTag) parentTag;
				selectTag.setOptionList(getDesignModel(), optionsList);
			} else if (parentTag instanceof SwapSelectTag) {
				// Options Tag is embedded in a SwapSelectTag or
				// FormElementSelectTag
				paintDirect = false;

				SwapSelectTag selectTag = (SwapSelectTag) parentTag;
				selectTag.setOptionList(getDesignModel(), optionsList);
			} else if (parentTag instanceof ColumnSelectTag) {
				// Options Tag is embedded in a ColumnSelectTag
				paintDirect = false;

				ColumnSelectTag selectTag = (ColumnSelectTag) parentTag;
				selectTag.setOptionList(getDesignModel(), optionsList);
			} else if (TagHelp.checkForBaseClass(parentTag.getClass(),
					strutsSelectTag)) {
				// Options Tag is embedded in a struts SelectTag
				paintDirect = true;

				try {
					Class comparatorClass = Class
							.forName("com.cc.framework.taglib.StrutsSelectTagComparator");

					Constructor constructor = comparatorClass
							.getConstructor(new Class[] { strutsSelectTag });

					optionsComparator = (OptionsComparator) constructor
							.newInstance(new Object[] { parentTag });
				} catch (Exception e) {
					log.error("Could not create comparator instance", e);
				}
			}
		}

		if (paintDirect) {
			OptionsPainter painter = new OptionsPainter(getDesignModel(),
					optionsList, optionsComparator);

			// write the options list to the OutputStream
			JspWriter writer = pageContext.getOut();

			try {
				writer.print(painter.paint(pageContext));
			} catch (IOException e) {
				throw new JspException(e);
			}
		}

		// release the design model
		designModel = null;

		// Evaluate the remainder of this page
		return EVAL_PAGE;
	}

	/**
	 * Specifies the name of the Java-Bean. The Java-Bean must be stored in the
	 * given scope. When the tag is surrounded by a Struts <html:form> tag, no
	 * Bean Name need be specified. In this case, the Java-Bean is drawn via a
	 * property of the Struts Form Bean.
	 * 
	 * @param name
	 *            The name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Specifies the name of the property using which the Java-Bean is to be
	 * accessed. This is generally only necessary when the Java-Bean is
	 * associated with a Struts Form Bean.
	 * 
	 * @param property
	 *            The name of the property
	 */
	public void setProperty(String property) {
		getDesignModel().setProperty(property);
	}

	/**
	 * An HTML-style can be directly specified with this attribute.
	 * 
	 * @param style
	 *            The style to set
	 */
	public void setStyle(String style) {
		getDesignModel().setStyle(style);
	}

	/**
	 * The HTML-class attribute of the element can be specified with this
	 * attribute.
	 * 
	 * @param styleClass
	 *            The styleClass to set
	 */
	public void setStyleClass(String styleClass) {
		getDesignModel().setStyleClass(styleClass);
	}

	/**
	 * Specifies the label of an additional empty-entry. What is involved here
	 * is an entry with an empty key. This is always required when the
	 * null-value is also to be offered in the list.
	 * 
	 * @param empty
	 *            The text to display for the user to make a selection like "<Please
	 *            select>"
	 */
	public void setEmpty(String empty) {
		getDesignModel().setEmpty(empty);
	}

	/**
	 * Specifies the name of the property which returns the key of the option.
	 * Note: Valid Java designator/label for a property which has to be
	 * implemented from the elements of the collection
	 * 
	 * @param keyProperty
	 *            The keyProperty to set
	 */
	public void setKeyProperty(String keyProperty) {
		getDesignModel().setKeyProperty(keyProperty);
	}

	/**
	 * Specifies the name of the property which supplies the display text of the
	 * option.
	 * 
	 * @param labelProperty
	 *            The labelProperty to set
	 */
	public void setLabelProperty(String labelProperty) {
		getDesignModel().setLabelProperty(labelProperty);
	}

	/**
	 * Specifies the maximum number of characters that are displayed for the
	 * option elements
	 * 
	 * @param maxlength
	 *            The maximum number of characters
	 * @throws JspException
	 *             If the Argument can't be converted to int
	 */
	public void setMaxlength(String maxlength) throws JspException {
		getDesignModel().setMaxLength(TagHelp.toInt(maxlength));
	}

	/**
	 * The automatic HTML coding of the column contents can be activated or
	 * disabled with the filter-attribute. The default is <code>true</code>
	 * 
	 * @param filter
	 *            <code>true</code> if the column content should be HTML
	 *            encoded.
	 * @throws JspException
	 *             If the Argument can't be converted to boolean
	 */
	public void setFilter(String filter) throws JspException {
		getDesignModel().setFilter(TagHelp.toBoolean(filter));
	}

	/**
	 * If this flag is set to <code>true</code> the framework interprets all
	 * labels as resource identifiers and localize them. If the flag is set to
	 * <code>false</code> the lables are treated as string literals without
	 * localization.
	 * 
	 * @param localize
	 *            Localization setting for the option list
	 * @throws JspException
	 *             If the Argument can't be converted to boolean
	 */
	public void setLocalize(String localize) throws JspException {
		getDesignModel().setLocalize(TagHelp.toBoolean(localize));
	}
}