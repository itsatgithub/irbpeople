/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/html/OptionsHelp.java,v 1.21 2005/09/27 14:06:24 P001002 Exp $
 * $Revision: 1.21 $
 * $Date: 2005/09/27 14:06:24 $
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

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.ecs.ConcreteElement;
import org.apache.ecs.ElementContainer;
import org.apache.ecs.Entities;
import org.apache.ecs.html.BR;
import org.apache.ecs.html.Div;
import org.apache.ecs.html.NOBR;
import org.apache.ecs.html.Span;

import com.cc.framework.common.SimpleEnumType2;
import com.cc.framework.ui.control.LineIterator;
import com.cc.framework.ui.control.ListLineIterator;
import com.cc.framework.ui.control.TreeLineIterator;
import com.cc.framework.ui.html.HtmlUtil;
import com.cc.framework.ui.model.ListDataModel;
import com.cc.framework.ui.model.OptionListDataModel;
import com.cc.framework.ui.model.OptionListDesignModel;
import com.cc.framework.ui.model.TreeNodeDataModel;
import com.cc.framework.ui.painter.PainterContext;
import com.cc.framework.util.Util;

/**
 * HelperClass for Option Lists
 * 
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version $Revision: 1.21 $
 */
public abstract class OptionsHelp {

	/**
	 * Baseclass for Option iterators
	 */
	private abstract static class IterBase implements OptionsIterator {

		/**
		 * The designmodel
		 */
		private OptionListDesignModel designModel = null;

		/**
		 * The current key
		 */
		private Object curKey = null;

		/**
		 * The current label
		 */
		private Object curLabel = null;

		/**
		 * Constructor
		 * 
		 * @param designModel
		 *            the design model
		 */
		public IterBase(OptionListDesignModel designModel) {
			super();

			this.designModel = designModel;
		}

		/**
		 * Returns the name of the property which returns the key of the option.
		 * 
		 * @return Name of the property
		 */
		public String getKeyProperty() {
			return designModel.getKeyProperty();
		}

		/**
		 * Returns the name of the property which supplies the display text of
		 * the option.
		 * 
		 * @return Name of the property
		 */
		public String getLabelProperty() {
			return designModel.getLabelProperty();
		}

		/**
		 * Selects the object
		 * 
		 * @param current
		 *            Current iteration element
		 */
		protected void select(Object current) {

			// Set the current key
			if (getKeyProperty() == null) {
				curKey = null;
			} else {
				curKey = callPropertyGet(current, getKeyProperty());
			}

			// Set the current label
			if (getLabelProperty() == null) {
				curLabel = curKey;
			} else {
				curLabel = callPropertyGet(current, getLabelProperty());
			}
		}

		/**
		 * Selects the object
		 * 
		 * @param key
		 *            Current key
		 * @param label
		 *            Current label
		 */
		protected void select(Object key, Object label) {
			this.curKey		= key;
			this.curLabel	= label;
		}

		/**
		 * Processes the Getter-Methode on the spezified Bean
		 * 
		 * @param bean
		 *            Bean to be processed
		 * @param property
		 *            Name of the Property
		 * @return Object
		 */
		protected static Object callPropertyGet(Object bean, String property) {

			if ((bean == null) || (property == null)) {
				return null;
			}

			return Util.getSafeProperty(bean, property);
		}

		/**
		 * @see com.cc.framework.ui.painter.html.OptionsIterator#getIndent()
		 */
		public int getIndent() {
			return 0;
		}

		/**
		 * @see com.cc.framework.ui.painter.html.OptionsIterator#localize()
		 */
		public boolean localize() {
			return designModel.localize();
		}

		/**
		 * @see com.cc.framework.ui.painter.html.OptionsIterator#getKey()
		 */
		public final Object getKey() {
			return curKey;
		}

		/**
		 * @see com.cc.framework.ui.painter.html.OptionsIterator#getLabel()
		 */
		public final Object getLabel() {
			return curLabel;
		}

		/**
		 * @see com.cc.framework.ui.painter.html.OptionsIterator#getMaxLength()
		 */
		public final int getMaxLength() {
			return designModel.getMaxLength();
		}

		/**
		 * @see com.cc.framework.ui.painter.html.OptionsIterator#getStyle()
		 */
		public String getStyle() {
			return designModel.getStyle();
		}

		/**
		 * @see com.cc.framework.ui.painter.html.OptionsIterator#getStyleClass()
		 */
		public String getStyleClass() {
			return designModel.getStyleClass();
		}

		/**
		 * @see com.cc.framework.ui.painter.html.OptionsIterator#filter()
		 */
		public boolean filter() {
			return designModel.filter();
		}
	}

	/**
	 * OptionsIterator for List
	 */
	private static class IterList extends IterBase {

		/**
		 * The option elements
		 */
		private List list = null;

		/**
		 * The current index
		 */
		private int index = -1;

		/**
		 * Constructor
		 * 
		 * @param designModel
		 *            the design model
		 * @param list
		 *            the option elements
		 */
		public IterList(OptionListDesignModel designModel, List list) {
			super(designModel);

			this.list = list;

			// Position the iterator on the first element
			next();
		}

		/**
		 * @see com.cc.framework.ui.painter.html.OptionsIterator#isDone()
		 */
		public boolean isDone() {
			return (index < 0) || (index >= list.size());
		}

		/**
		 * @see com.cc.framework.ui.painter.html.OptionsIterator#next()
		 */
		public void next() {
			++index;

			if (index < list.size()) {
				select(list.get(index));
			}
		}
	}

	/**
	 * OptionsIterator for Iterator
	 */
	private static class IterIterator extends IterBase {

		/**
		 * The option elements
		 */
		private Iterator iter = null;

		/**
		 * Out of elements indicator
		 */
		private boolean done = true;

		/**
		 * Constructor
		 * 
		 * @param designModel
		 *            the design model
		 * @param iter
		 *            the options iterator
		 */
		public IterIterator(OptionListDesignModel designModel, Iterator iter) {
			super(designModel);

			this.iter = iter;

			// Position the iterator on the first element
			next();
		}

		/**
		 * @see com.cc.framework.ui.painter.html.OptionsIterator#isDone()
		 */
		public boolean isDone() {
			return done;
		}

		/**
		 * @see com.cc.framework.ui.painter.html.OptionsIterator#next()
		 */
		public void next() {
			done = !iter.hasNext();

			if (!done) {
				select(iter.next());
			}
		}
	}

	/**
	 * OptionsIterator for Enumeration
	 */
	private static class IterEnumeration extends IterBase {

		/**
		 * The option elements
		 */
		private Enumeration e = null;

		/**
		 * Out of elements indicator
		 */
		private boolean done = true;

		/**
		 * Constructor
		 * 
		 * @param designModel
		 *            the design model
		 * @param e
		 *            the options enumerator
		 */
		public IterEnumeration(OptionListDesignModel designModel, Enumeration e) {
			super(designModel);

			this.e = e;

			// Position the iterator on the first element
			next();
		}

		/**
		 * @see com.cc.framework.ui.painter.html.OptionsIterator#isDone()
		 */
		public boolean isDone() {
			return done;
		}

		/**
		 * @see com.cc.framework.ui.painter.html.OptionsIterator#next()
		 */
		public void next() {
			done = !e.hasMoreElements();

			if (!done) {
				select(e.nextElement());
			}
		}
	}

	/**
	 * OptionsIterator for OptionListDataModel
	 */
	private static class IterOptionListDataModel extends IterBase {

		/**
		 * The option elements
		 */
		private OptionListDataModel list = null;

		/**
		 * The current index
		 */
		private int index = -1;

		/**
		 * Constructor
		 * 
		 * @param designModel
		 *            the design model
		 * @param list
		 *            the option elements
		 */
		public IterOptionListDataModel(OptionListDesignModel designModel, OptionListDataModel list) {
			super(designModel);

			this.list = list;

			// Position the iterator on the first element
			next();
		}

		/**
		 * @see com.cc.framework.ui.painter.html.OptionsIterator#isDone()
		 */
		public boolean isDone() {
			return (index < 0) || (index >= list.size());
		}

		/**
		 * @see com.cc.framework.ui.painter.html.OptionsIterator#next()
		 */
		public void next() {
			++index;

			if (index < list.size()) {
				select(list.getKey(index), list.getValue(index));
			}
		}
	}

	/**
	 * OptionsIterator for LineIterator
	 */
	private static class IterLineIterator extends IterBase {

		/**
		 * The option elements
		 */
		private LineIterator iter = null;

		/**
		 * Constructor
		 * 
		 * @param designModel
		 *            the design model
		 * @param list
		 *            the option elements
		 */
		public IterLineIterator(OptionListDesignModel designModel, ListDataModel list) {
			super(designModel);

			iter = new ListLineIterator(null, list, 0, null);

			// Position the iterator on the first element
			iter.reset();
			select();
		}

		/**
		 * Constructor
		 * 
		 * @param designModel
		 *            the design model
		 * @param tree
		 *            the option elements
		 */
		public IterLineIterator(OptionListDesignModel designModel, TreeNodeDataModel tree) {
			super(designModel);

			iter = new TreeLineIterator(tree, null);

			// Position the iterator on the first element
			iter.reset();
			select();
		}

		/**
		 * Selects the current iteration element
		 */
		private void select() {
			if (!iter.done()) {
				Object itemKey;

				// Set the current key
				if (getKeyProperty() == null) {
					itemKey = iter.currentKey();
				} else {
					itemKey = iter.current(getKeyProperty());
				}

				Object itemLabel = itemKey;

				if (getLabelProperty() != null) {
					itemLabel = iter.current(getLabelProperty());
				}

				select(itemKey, itemLabel);
			}
		}

		/**
		 * @see com.cc.framework.ui.painter.html.OptionsIterator#isDone()
		 */
		public boolean isDone() {
			return iter.done();
		}

		/**
		 * @see com.cc.framework.ui.painter.html.OptionsIterator#next()
		 */
		public void next() {
			iter.next();
			select();
		}

		/**
		 * @see com.cc.framework.ui.painter.html.OptionsIterator#getIndent()
		 */
		public int getIndent() {
			if (iter.current() instanceof TreeNodeDataModel) {
				int level = 0;

				TreeNodeDataModel node = ((TreeNodeDataModel) iter.current()).getParent();
				while (node != null) {
					++level;
					node = node.getParent();
				}

				return level;
			} else {
				return 0;
			}
		}
	}

	/**
	 * OptionsIterator for SimpleEnumType2
	 */
	private static class IterSimpleEnumType2 extends IterBase {

		/**
		 * The option elements
		 */
		private SimpleEnumType2[] list = null;

		/**
		 * The current index
		 */
		private int index = -1;

		/**
		 * Constructor
		 *
		 * @param		designModel the design model
		 * @param		e the option elements
		 */
		public IterSimpleEnumType2(OptionListDesignModel designModel, SimpleEnumType2 e) {
			super(designModel);

			this.list = e.elements();

			// Position the iterator on the first element
			next();
		}

		/**
		 * @see com.cc.framework.ui.painter.html.OptionsIterator#isDone()
		 */
		public boolean isDone() {
			return (index < 0) || (index >= list.length);
		}

		/**
		 * @see com.cc.framework.ui.painter.html.OptionsIterator#next()
		 */
		public void next() {
			++index;

			if (index < list.length) {
				select(list[index].getKey(), list[index].getValue());
			}
		}
	}

	/**
	 * Constructor
	 */
	private OptionsHelp() {
		super();
	}

	/**
	 * Creates an iterator for the given option collection
	 * 
	 * @param designModel
	 *            Optionlist design model
	 * @param dataModel
	 *            options collection
	 * @return Iterator or <code>null</code>
	 */
	public static OptionsIterator createIterator(OptionListDesignModel designModel, Object dataModel) {

		if (dataModel ==  null) {
			return null;
		} else if (dataModel.getClass().isArray()) {
			return new IterList(designModel, Arrays.asList((Object[]) dataModel));
		} else if (dataModel instanceof OptionListDataModel) {
			return new IterOptionListDataModel(designModel, (OptionListDataModel) dataModel);
		} else if (dataModel instanceof ListDataModel) {
			return new IterLineIterator(designModel, (ListDataModel) dataModel);
		} else if (dataModel instanceof TreeNodeDataModel) {
			return new IterLineIterator(designModel, (TreeNodeDataModel) dataModel);
		} else if (dataModel instanceof SimpleEnumType2) {
			return new IterSimpleEnumType2(designModel, (SimpleEnumType2) dataModel);
		} else if (dataModel instanceof Collection) {
			return new IterIterator(designModel, ((Collection) dataModel).iterator());
		} else if (dataModel instanceof Iterator) {
			return new IterIterator(designModel, (Iterator) dataModel);
		} else if (dataModel instanceof Map) {
			return new IterIterator(designModel, ((Map) dataModel).entrySet().iterator());
		} else if (dataModel instanceof Enumeration) {
			return new IterEnumeration(designModel, (Enumeration) dataModel);
		} else {
			return null;
		}
	}

	/**
	 * Creates a String array with all selected option <b>labels</b>
	 * 
	 * @param ctx
	 *            Painter Context
	 * @param designModel
	 *            Optionlist design model
	 * @param dataModel
	 *            options collection
	 * @param cmp
	 *            Comparator
	 * @return Selected Labels
	 */
	public static String[] translateSelectedItems(
		PainterContext ctx,
		OptionListDesignModel designModel,
		Object dataModel,
		OptionsComparator cmp) {

		return translateSelectedItems(
			ctx,
			createIterator(designModel, dataModel),
			cmp);
	}

	/**
	 * Creates a String array with all selected option <b>labels</b>
	 * 
	 * @param ctx
	 *            Painter Context
	 * @param iter
	 *            options iterator
	 * @param cmp
	 *            Comparator
	 * @return Selected Labels
	 */
	public static String[] translateSelectedItems(
		PainterContext ctx,
		OptionsIterator iter,
		OptionsComparator cmp) {

		Vector selected = new Vector();

		if (iter != null) {
			for (; !iter.isDone(); iter.next()) {
				if (cmp.match(iter.getKey())) {

					Object label = iter.getLabel();

					if (label != null) {
						if (iter.localize()) {
							selected.add(ctx.localize(label.toString()));
						} else {
							selected.add(label.toString());
						}
					}
				}
			}
		}

		String[] result = new String[selected.size()];
		return (String[]) selected.toArray(result);
	}

	/**
	 * Creates a element with all selected option <b>labels</b>
	 * 
	 * @param selected
	 *            the selected key
	 * @return Selected Labels
	 */
	public static ConcreteElement createDisplayElement(Object selected) {
		return createDisplayElement(toArray(selected));
	}

	/**
	 * Creates a element with all selected option <b>labels</b>
	 * 
	 * @param selected
	 *            array with the selected keys
	 * @return Selected Labels
	 */
	public static ConcreteElement createDisplayElement(Object[] selected) {

		ElementContainer element = new ElementContainer();

		if ((selected == null) || (selected.length == 0)) {
			element.addElement(Entities.NBSP);
		} else {
			for (int i = 0; i < selected.length; i++) {
				if (i > 0) {
					element.addElement(new BR());
				}

				element
					.addElement(new Span()
						.addElement(new NOBR())
						.addElement(HtmlUtil.encodeHtml(selected[i])));
			}
		}

		return new Div().addElement(element);
	}

	/**
	 * Creates a element with all selected option <b>labels</b>
	 * 
	 * @param ctx
	 *            Painter Context
	 * @param iter
	 *            options iterator
	 * @param cmp
	 *            Comparator
	 * @return Selected Labels
	 */
	public static ConcreteElement createDisplayElement(
		PainterContext ctx,
		OptionsIterator iter,
		OptionsComparator cmp) {

		ElementContainer element = new ElementContainer();

		if (iter != null) {

			if (iter.isDone()) {
				element.addElement(Entities.NBSP);
			} else {
				boolean first = true;

				for (; !iter.isDone(); iter.next()) {
					if (cmp.match(iter.getKey())) {
						Object label = iter.getLabel();

						if (first) {
							first = false;
						} else {
							element.addElement(new BR());
						}

						if ((label != null) && iter.localize()) {
							label = ctx.localize(label.toString());
						}

						element
							.addElement(new Span()
								.addElement(new NOBR())
								.addElement(HtmlUtil.encodeHtml(label, iter.filter())));
					}
				}
			}
		}

		return new Div().addElement(element);
	}

	/**
	 * Transforms an object into an Array
	 * 
	 * @param value
	 *            Object
	 * @return Array of objects
	 */
	public static Object[] toArray(Object value) {

		if (value == null) {
			return null;
		} else if (value instanceof Collection) {
			Collection c = (Collection) value;

			return c.toArray(new Object[c.size()]);
		} else if (value.getClass().isArray()) {
			int n = Array.getLength(value);
			Object[] results = new Object[n];
			for (int i = 0; i < n; i++) {
				results[i] = Array.get(value, i);
			}
			return results;
		} else {
			return new Object[] {value};
		}
	}
}