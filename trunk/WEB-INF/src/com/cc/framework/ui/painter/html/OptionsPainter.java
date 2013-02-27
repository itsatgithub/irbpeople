/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/html/OptionsPainter.java,v 1.29 2005/08/02 19:15:01 P001002 Exp $
 * $Revision: 1.29 $
 * $Date: 2005/08/02 19:15:01 $
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

import javax.servlet.jsp.PageContext;

import org.apache.ecs.Entities;
import org.apache.ecs.html.Option;

import com.cc.framework.ui.html.HtmlUtil;
import com.cc.framework.ui.model.OptionListDesignModel;
import com.cc.framework.ui.painter.PainterContext;
import com.cc.framework.ui.painter.PainterHelp;
import com.cc.framework.util.StringHelp;

/**
 * Painter for an option list
 * The framework supports different data types which
 * can be used to create an option list.
 * <ul>
 * 	<li>java.util.Collection</li>
 * 	<li>Array</li>
 * 	<li>java.util.Iterator</li>
 * 	<li>java.util.Map</li>
 * 	<li>java.util.Enumeration</li>
 * 	<li>com.cc.framework.ui.model.OptionListDataModel</li>
 * 	<li>com.cc.framework.ui.model.ListDataModel</li>
 * 	<li>com.cc.framework.ui.model.TreeNodeDataModel</li>
 * 	<li>com.cc.framework.common.SimpleEnumType2</li>
 * <ul>
 *
 * @author	    <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version	    $Revision: 1.29 $
 * @since       1.0
 */
public class OptionsPainter {

	/**
	 * The designmodel
	 */
	private OptionListDesignModel designModel	= null;

	/**
	 * The Datamodel
	 */
	private Object dataModel					= null;

	/**
	 * The selected value (the datamodel)
	 */
	private OptionsComparator comparator		= null;

	/**
	 * Constructor for OptionsPainter
	 * 
	 * @param designModel
	 *            OptionListDesignModel
	 * @param dataModel
	 *            The Options (Datamodel)
	 * @param comparator
	 *            OptionsComparator
	 */
	public OptionsPainter(OptionListDesignModel designModel, Object dataModel, OptionsComparator comparator) {
		super();

		this.designModel	= designModel;
		this.dataModel		= dataModel;
		this.comparator		= comparator;
	}

	/**
	 * Paints the options. The framework supports different data types.
	 * 
	 * @param ctx
	 *            The Painter Context
	 * @return (HTML) String with the option list
	 */
	public String paint(PainterContext ctx) {
		return paint(ctx.getPageContext());
	}

	/**
	 * Paints the options. The framework supports different data types.
	 * 
	 * @param ctx
	 *            The Jsp Page Context
	 * @return (HTML) String with the option list
	 */
	public String paint(PageContext ctx) {
		StringBuffer buf = null;

		// create an option iterator
		OptionsIterator iter = OptionsHelp.createIterator(designModel, dataModel);

		if (iter == null) {
			// There are no options available
			buf = new StringBuffer();

			if (designModel.getEmpty() != null) {
				addOption(buf, "", PainterHelp.localize(ctx, designModel.getEmpty()), 0);
			}
		} else {
			buf = createOptions(ctx, iter);
		}

		return buf.toString();
	}

	/**
	 * Detectes if the option is selected
	 * 
	 * @param value
	 *            The value which should be check if it's selected
	 * @return boolean <code>true</code> if the option i selected;
	 *         <code>false</code> otherwise.
	 */
	protected boolean isSelected(Object value) {
		if (comparator == null) {
			return false;
		} else {
			return comparator.match(value);
		}
	}

	/**
	 * Method createOptions
	 * 
	 * @param ctx
	 *            the Jsp page context
	 * @param iter
	 *            Options iterator
	 * @return StringBuffer
	 */
	protected StringBuffer createOptions(PageContext ctx, OptionsIterator iter) {

		StringBuffer buf = new StringBuffer();

		if (designModel.getEmpty() != null) {
			addOption(buf, "", PainterHelp.localize(ctx, designModel.getEmpty()), 0);
		}

		for (; !iter.isDone(); iter.next()) {
			Object itemKey		= iter.getKey();
			Object itemLabel	= iter.getLabel();
			int itemIndent		= iter.getIndent();

			if ((itemLabel != null) && designModel.localize()) {
				itemLabel = PainterHelp.localize(ctx, itemLabel.toString());
			}

			addOption(buf, itemKey, itemLabel, itemIndent);
		}

		return buf;
	}

	/**
	 * Add an option element to the specified StringBuffer based on the
	 * specified parameters.
	 * 
	 * @param buf
	 *            StringBuffer accumulating our results
	 * @param value
	 *            Value to be returned to the server for this option
	 * @param label
	 *            Value to be shown to the user for this option
	 * @param indent
	 *            Indent
	 */
	protected void addOption(StringBuffer buf, Object value, Object label, int indent) {

		buf.append("<option value='");

		if ((value != null) && !"".equals(value)) {
			buf.append(HtmlUtil.encodeHtml(value));
		}

		buf.append("'");

		if (isSelected(value)) {
			buf.append(" selected");
		}

		if (designModel.getStyle() != null) {
			buf.append(" style='").append(designModel.getStyle()).append("'");
		}

		if (designModel.getStyleClass() != null) {
			buf
				.append(" class='").append(designModel.getStyleClass()).append("'");
		}

		buf.append(">");

		for (int i = 0; i < indent; i++) {
			buf.append(Entities.NBSP).append(Entities.NBSP);
		}

		if (label == null) {
			buf.append(Entities.NBSP);
		} else {
			buf.append(
				HtmlUtil.encodeHtml(
					StringHelp.truncate(
						label.toString(),
						designModel.getMaxLength()),
					designModel.filter()));
		}

		buf.append("</option>\r\n");
	}

	/**
	 * Creates an HTML option element from the current iterator position
	 * 
	 * @param ctx
	 *            the painter context
	 * @param iterator
	 *            the option iterator positioned on the option to paint
	 * @param comparator
	 *            the comperator (or <code>null</code>) to determin the
	 *            selection state of the current option element
	 * @return HTML option element
	 */
	public static Option createOption(
		PainterContext ctx,
		OptionsIterator iterator,
		OptionsComparator comparator) {

		Object value	= iterator.getKey();
		Object label	= iterator.getLabel();

		Option option = new Option();

		if ((value != null) && !"".equals(value)) {
			option.setValue(HtmlUtil.encodeHtml(value));
		}

		if ((comparator != null) && comparator.match(value)) {
			option.setSelected(true);
		}

		if (iterator.getStyle() != null) {
			option.setStyle(iterator.getStyle());
		}

		if (iterator.getStyleClass() != null) {
			option.setClass(iterator.getStyleClass());
		}

		if (label == null) {
			option.addElement(Entities.NBSP);
		} else {
			StringBuffer buf = new StringBuffer();

			for (int i = 0; i < iterator.getIndent(); i++) {
				buf.append(Entities.NBSP).append(Entities.NBSP);
			}

			if (iterator.localize()) {
				label = ctx.localize(label.toString());
			}
			
			buf.append(
				HtmlUtil.encodeHtml(
					StringHelp.truncate(
						label.toString(),
						iterator.getMaxLength()),
					iterator.filter()));

			option.addElement(buf.toString());
		}

		return option;
	}
}