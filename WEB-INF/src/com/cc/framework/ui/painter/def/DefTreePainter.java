/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/def/DefTreePainter.java,v 1.48 2005/09/27 14:06:22 P001002 Exp $
 * $Revision: 1.48 $
 * $Date: 2005/09/27 14:06:22 $
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

package com.cc.framework.ui.painter.def;

import org.apache.ecs.ConcreteElement;
import org.apache.ecs.ElementContainer;
import org.apache.ecs.html.Div;
import org.apache.ecs.html.TD;
import org.apache.ecs.html.TR;
import org.apache.ecs.html.Table;

import com.cc.framework.ui.RunAt;
import com.cc.framework.ui.control.TreeControl;
import com.cc.framework.ui.control.TreeLineIterator;
import com.cc.framework.ui.javascript.JavaScript;
import com.cc.framework.ui.javascript.JavaScriptUtil;
import com.cc.framework.ui.model.ImageModel;
import com.cc.framework.ui.model.TreeDesignModel;
import com.cc.framework.ui.model.TreeGroupDataModel;
import com.cc.framework.ui.model.TreeNodeDataModel;
import com.cc.framework.ui.model.imp.ClientHandlerImp;
import com.cc.framework.ui.painter.PainterContext;
import com.cc.framework.ui.painter.PainterHelp;
import com.cc.framework.util.TreeHelp;

/**
 * Painter for the Tree Control
 *
 * @author      <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version     $Revision: 1.48 $
 * @since       1.0
 */
public class DefTreePainter extends DefPainterBase {

	/**
	 * The size of the images
	 */
	private static final int IMAGE_SIZE			= 15;

	// -------------------------
	//        methods
	// -------------------------

	/**
	 * Constructor for DefTreePainter
	 *
	 * @param	painterContext	The PainterContext
	 * @param	ctrl			The Control to render
	 */
	public DefTreePainter(PainterContext painterContext, TreeControl ctrl) {
		super(painterContext);
	}

	/**
	 * Retrieves the control for this painter
	 *
	 * @return		Control
	 */
	protected TreeControl getCtrl() {
		return (TreeControl) getPainterContext().getControl();
	}

	/**
	 * Returns the DesignModel
	 * @return	TreeDesignModel
	 */
	protected TreeDesignModel getDesignModel() {
		return (TreeDesignModel) getCtrl().getDesignModel();
	}

	/**
	 * Returns the Root-Element of the Tree
	 * @return	TreeGroupDataModel
	 */
	protected TreeGroupDataModel getRoot() {
		return (TreeGroupDataModel) getCtrl().getDataModel();
	}

	/**
	 * Renders a Sub Tree of the Control. Included Sub Trees will be
	 * renderd recursive.
	 * @param	iter	TreeLineIterator
	 * @return	ConcreteElement
	 */
	protected ConcreteElement createTreeLines(TreeLineIterator iter) {

		TreeDesignModel designModel = getDesignModel();

		ElementContainer tree = new ElementContainer();

		int lineIndex = 1;

		while (!iter.done()) {
			boolean oddLine = (lineIndex++ % 2) == 1;

			String label =
				PainterHelp.callPropertyGetStr(
					iter.current(),
					designModel.getLabelProperty());

			String tooltip =
				PainterHelp.callPropertyGetStr(
					iter.current(),
					designModel.getTooltipProperty());

			if (tooltip != null) {
				tooltip = html(tooltip);
			}

			ImageModel image =
				DefPainterUtility.getItemImage(
					getPainterContext(),
					iter,
					designModel.getImageProperty(),
					designModel.getImageMap(),
					IMAGE_SIZE);

			ConcreteElement outline =
				DefPainterUtility.createOutline(
					getPainterContext(),
					getDesignModel(),
					ClientHandlerImp.evaluate(getPageContext(), getCtrl(), iter.current()),
					iter,
					html(label, designModel.filter(), designModel.getMaxLength()),
					tooltip,
					image,
					IMAGE_SIZE,
					getCtrl().getTransaction());

			// Apply any design tules
			PainterHelp.applyDesignRules(
				getPageContext(),
				outline,
				getCtrl().getDesignRules(),
				iter.current());

			tree.addElement(
				new TR().addElement(new TD(outline)).setClass(
					getRowClass(oddLine, iter.isMarked())));

			iter.next();
		}

		return tree;
	}

	/**
	 * Returns the class id for a even/odd row which is selected/deselected
	 * @param oddLine		Indicates if its a odd row
	 * @param highlighted	Flag which indicates if the row is highlighted
	 * @return HTML class for this row
	 */
	protected String getRowClass(boolean oddLine, boolean highlighted) {
		if (highlighted) {
			return (oddLine ? DefHtmlClass.TREECONTROL_ODDLINE_SEL : DefHtmlClass.TREECONTROL_EVENLINE_SEL);
		} else {
			return (oddLine ? DefHtmlClass.TREECONTROL_ODDLINE : DefHtmlClass.TREECONTROL_EVENLINE);
		}
	}

	/**
	 * Gennerates the needed html for the tree
	 * @return	ConcreteElement
	 */
	protected ConcreteElement doCreateElement() {

		ElementContainer container = new ElementContainer();

		TreeDesignModel designModel	= getDesignModel();
		TreeGroupDataModel root		= getRoot();

		// if no informations are available we terminate
		if ((designModel == null) || (root == null)) {
			return null;
		}

		Table control = new Table()
			.setCellPadding(0)
			.setCellSpacing(0)
			.setBorder(0);

		if (getStyleId() != null) {
			control.setID(getStyleId());
		}

		if (getCtrl().getStyle() != null) {
			control.setStyle(getCtrl().getStyle());
		}

		if (getCtrl().getStyleClass() == null) {
			control.setClass(getElementClass(DefClassType.CLASS_CONTROL));
		} else {
			control.setClass(getCtrl().getStyleClass());
		}

		if (designModel.getWidth() != null) {
			control.setWidth(designModel.getWidth());
		}

		if (getCtrl().getSummary() != null) {
			control.setSummary(getCtrl().getSummary());
		}

		TreeLineIterator iter	= null;

		if (designModel.showRoot()) {
			// if the root should be displayed it is now generated as
			// the first element
			iter = new TreeLineIterator(getCtrl(), root, getPrincipal());
		} else {
			// Start the Iteration on the first child of the root element
			TreeNodeDataModel firstChild = (root.size() == 0) ? null : root.getChild(0);

			iter = new TreeLineIterator(getCtrl(), firstChild, getPrincipal());
		}

		// Using the iterator, we can now render the rows of our control
		ConcreteElement lines = createTreeLines(iter);

		if (lines != null) {
			control.addElement(lines);
		}

		// embed the control into an html span element
		// the span is needed if we want to replace
		// and generate the tree on the client/browser using javascript
		Div div = new Div();
		if (getCtrl().getControlName() != null) {
			div.setID("tree_" + getCtrl().getControlName());
		}

		div.addElement(control);
		container.addElement(div);

		// if our tree should work without server
		// roundtrip we must generate and add the needed
		// javascript
		if (getCtrl().getRunAt().equals(RunAt.CLIENT)) {
			JavaScript script = createClientScript();
			if (script != null) {
				container.addElement(script);
			}
		}

		return container;
	}


	/**
	 * Creates the Javascript needed to render the tree on the client
	 * @return The Javascript fragment
	 */
	protected JavaScript createClientScript() {
		StringBuffer buf	= new StringBuffer();
		String controlId	= getCtrl().getControlName();

		// First create the tree js object and set the runAt attribute.
		// Example:
		// var ctrl_mytree=new Tree('mytree', false);
		// ctrl_mytree.setRunAt(RunAt.CLIENT);
		buf
			.append("var ctrl_")
			.append(controlId)
			.append("=new Tree('")
			.append(controlId)
			.append("',")
			.append(getCtrl().isFormElement())
			.append(");");

		// styleClass
		buf
			.append("ctrl_")
			.append(controlId)
			.append(".setStyleClass('");

		if (getCtrl().getStyleClass() == null) {
			buf.append(getElementClass(DefClassType.CLASS_CONTROL));
		} else {
			buf.append(getCtrl().getStyleClass());
		}

		buf
			.append("');");

		// runat
		buf
			.append("ctrl_")
			.append(controlId)
			.append(".setRunAt(RunAt.")
			.append(RunAt.CLIENT.equals(getCtrl().getRunAt()) ? "CLIENT" : "SERVER")
			.append(");");

		// expandmode
		buf
			.append("ctrl_")
			.append(controlId)
			.append(".setExpandMode(ExpandMode.")
			.append(null != getDesignModel().getExpansionMode() ? getDesignModel().getExpansionMode().toString().toUpperCase() : "")
			.append(");");

		// LinesAtRoot
		buf
			.append("ctrl_")
			.append(controlId)
			.append(".setLinesAtRoot(")
			.append(getDesignModel().showLinesAtRoot())
			.append(");");

		// GroupSelect
		buf
			.append("ctrl_")
			.append(controlId)
			.append(".setGroupSelect(")
			.append(getDesignModel().getGroupSelect())
			.append(");");

		// show Root
		buf
			.append("ctrl_")
			.append(controlId)
			.append(".setShowRoot(")
			.append(getDesignModel().showRoot())
			.append(");");

		// checkboxes
		buf
			.append("ctrl_")
			.append(controlId)
			.append(".setCheckboxes(")
			.append(getDesignModel().showCheckBoxes())
			.append(");");

		// maxlength
		buf
			.append("ctrl_")
			.append(controlId)
			.append(".setMaxlength(")
			.append(getDesignModel().getMaxLength())
			.append(");");

		// Action
		buf
			.append("ctrl_")
			.append(controlId)
			.append(".setAction('")
			.append(getCtrl().getAction())
			.append("');");

		// ImageMap
		if (null != getDesignModel().getImageMap()) {
			String imgName = getDesignModel().getImageMap().getName();

			buf
				.append("ctrl_")
				.append(controlId)
				.append(".setImageMap(")
				.append(imgName)
				.append(");");
		}

		// Now add the group and nodes Example:
		// ctrl_products.setRoot(new TreeGroup('0', 'Products').appendChild(new TreeGroup('1201', 'Desktops')
		buf
			.append("ctrl_")
			.append(controlId)
			.append(".setRoot(")
			.append(appendNodes(0, (TreeNodeDataModel) getCtrl().getDataModel()))
			.append(");");


		// Now setup the TreePainterData object, which holds information about the resources
		// var tpdata = new TreePainterData(tree, 'cc2/', arr_images_mycontrol);
		// Create the images
		String[] imageRes = doCreateJSImageResourceArray();

		// create the JavaScript array which holds all the image resources
		// We will then pass this array to our JavaScript painter
		buf
			.append("var arr_imageRes_")
			.append(controlId)
			.append(" = new Array();");

		for (int i = 0; i < imageRes.length; i++) {
			String image = JavaScriptUtil.doCreateJSImage(imageRes[i], getPainterContext().getImage(15, imageRes[i]));

			buf
				.append("arr_imageRes_")
				.append(controlId)
				.append("['")
				.append(imageRes[i])
				.append("'] = new ")
				.append(image);
		}

		buf
			.append("var tpd_")
			.append(controlId)
			.append("=new TreePainterData(ctrl_")
			.append(controlId)
			.append(", '")
			.append(getPainterContext().request().getContextPath())
			.append("', ")
			.append("arr_imageRes_").append(controlId)
			.append(");");

		// Add the javascript object which starts redering the Tree
		// TreePainter.render(tpdata);
		buf
			.append("TreePainter.render(tpd_")
			.append(controlId)
			.append(");");

		JavaScript script = new JavaScript();
		script.addElement(buf.toString());
		return script;
	}

	/**
	 * Creates the array which holds the images resources
	 * used by our javascript tree
	 *
	 * @return Array containing the images resources
	 */
	protected String[] doCreateJSImageResourceArray() {
		return new String[] {
			DefResources.TREE_FOLDEROPEN,
			DefResources.TREE_FOLDERCLOSED,
			DefResources.TREE_ITEM,
			DefResources.TREE_STRUCTURE,
			DefResources.TREE_STRUCTURE_2,
			DefResources.TREE_STRUCTURE_10,
			DefResources.TREE_STRUCTURE_12,
			DefResources.TREE_STRUCTURE_14,
			DefResources.TREE_STRUCTURE_16,
			DefResources.TREE_STRUCTURE_18,
			DefResources.TREE_STRUCTURE_26,
			DefResources.TREE_STRUCTURE_30,
			DefResources.TREE_STRUCTURE_32,
			DefResources.TREE_STRUCTURE_34,
			DefResources.TREE_STRUCTURE_42,
			DefResources.TREE_STRUCTURE_46,
			DefResources.CHECKBOX_NONE,
			DefResources.CHECKBOX_INVALID,
			DefResources.CHECKBOX_UNCHECKED,
			DefResources.CHECKBOX_CHECKED,
			DefResources.CHECKBOX_INDETERMINATE};
	}


	/**
	 * Appends the tree nodes as java scripting variables
	 *
	 * @param		indent Indent level of the node for pretty printing
	 * @param		node the node
	 * @return		returns the generated javascript code
	 */
	protected StringBuffer appendNodes(int indent, TreeNodeDataModel node) {

		StringBuffer script = new StringBuffer();

		String nl = System.getProperty("line.separator");

		String label   = PainterHelp.callPropertyGetStr(node, getDesignModel().getLabelProperty());
		String tooltip = PainterHelp.callPropertyGetStr(node, getDesignModel().getTooltipProperty());
		String image   = PainterHelp.callPropertyGetStr(node, getDesignModel().getImageProperty());
		String enabled = PainterHelp.callPropertyGetStr(node, getDesignModel().getEnableProperty());

		tooltip = (null == tooltip) ? "" : tooltip;

		// check if the target attribute was set
		String target = getDesignModel().getTarget();

		// otherwise check if the targetProperty attribute was set
		if (null == target) {
			target  = PainterHelp.callPropertyGetStr(node, getDesignModel().getTargetProperty());
		}

		if (node instanceof TreeGroupDataModel) {
			// it is a group node
			TreeGroupDataModel group = (TreeGroupDataModel) node;

			// check if the node is expanded / collapsed or of type expandex
			String nodeState = "NodeState.NONE";

			if (getCtrl().isExpanded(node)) {
				nodeState = "NodeState.EXPAND";
			} else if (!getCtrl().isExpanded(node) & group.size() == -1) {
				nodeState = "NodeState.EXPANDEX";
			} else {
				nodeState = "NodeState.COLLAPSE";
			}

			script
				.append("new TreeGroup('")
				.append(JavaScriptUtil.encodeJavaScript(group.getUniqueKey()))
				.append("', '")
				.append(JavaScriptUtil.encodeJavaScript(label))
				.append("', '")
				.append(JavaScriptUtil.encodeJavaScript(tooltip))
				.append("', ")
				.append(nodeState)
				.append(", ")
				.append(TreeHelp.getCheckState(node))
				.append(", ")
				.append(getCtrl().isSelected(node))
				.append(", '")
				.append(image)
				.append("', '")
				.append(target)
				.append("', ")
				.append(enabled)
				.append(")");


			for (int i = 0; i < group.size(); i++) {
				script.append(nl);

				for (int j = 0; j <= indent; j++) {
					script.append(" ");
				}

				script
					.append(".appendChild(")
					.append(appendNodes(indent + 1, group.getChild(i)))
					.append(")");
			}

		} else {
			// it is a simple terminal node

			script
				.append("new TreeNode('")
				.append(JavaScriptUtil.encodeJavaScript(node.getUniqueKey()))
				.append("', '")
				.append(JavaScriptUtil.encodeJavaScript(label))
				.append("', '")
				.append(JavaScriptUtil.encodeJavaScript(tooltip))
				.append("', ")
				.append(TreeHelp.getCheckState(node))
				.append(", ")
				.append(getCtrl().isSelected(node))
				.append(", '")
				.append(image)
				.append("', '")
				.append(target)
				.append("', ")
				.append(enabled)
				.append(")");
		}

		return script;
	}

	/**
	 * @see com.cc.framework.ui.painter.ControlPainter#getElementClass(int)
	 */
	public String getElementClass(int type) {
		switch (type) {
			case DefClassType.CLASS_CONTROL:
				return DefHtmlClass.TREECONTROL_TREE;
			default:
				return super.getElementClass(type);
		}
	}
}