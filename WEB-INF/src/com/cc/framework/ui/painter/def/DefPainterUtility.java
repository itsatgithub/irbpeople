/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/painter/def/DefPainterUtility.java,v 1.37 2005/07/12 16:07:40 P001002 Exp $
 * $Revision: 1.37 $
 * $Date: 2005/07/12 16:07:40 $
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

import java.util.Stack;

import org.apache.ecs.AlignType;
import org.apache.ecs.ConcreteElement;
import org.apache.ecs.Entities;
import org.apache.ecs.html.Span;
import org.apache.ecs.html.TD;
import org.apache.ecs.html.TR;
import org.apache.ecs.html.Table;

import com.cc.framework.ui.ExpansionMode;
import com.cc.framework.ui.ImageMap;
import com.cc.framework.ui.SelectMode;
import com.cc.framework.ui.control.ControlActionDef;
import com.cc.framework.ui.control.LineIterator;
import com.cc.framework.ui.control.TreeLineIterator;
import com.cc.framework.ui.model.ClientEvent;
import com.cc.framework.ui.model.ClientHandler;
import com.cc.framework.ui.model.ImageModel;
import com.cc.framework.ui.model.TreeGroupDataModel;
import com.cc.framework.ui.model.TreeNodeDataModel;
import com.cc.framework.ui.model.TreeStyle;
import com.cc.framework.ui.painter.ActionPainter;
import com.cc.framework.ui.painter.PainterContext;
import com.cc.framework.ui.painter.PainterHelp;

/**
 * Class DefPainterUtility
 *
 * @author		<a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version		$Revision: 1.37 $
 * @since       1.0
 */
public abstract class DefPainterUtility {

	/**
	 * Constructor
	 */
	private DefPainterUtility() {
		super();
	}
	
	/**
	 * Returns the Target Attribute which should be included in a HyperLink
	 * @param	treeStyle	TreeStyle
	 * @param	iter		TreeLineIterator
	 * @return	String
	 */
	public static String getTarget(TreeStyle treeStyle, TreeLineIterator iter) {
		String target = null;

		if (treeStyle.getTargetProperty() != null) {
			target = String.valueOf(iter.current(treeStyle.getTargetProperty()));
		} else {
			target = treeStyle.getTarget();
		}

		return target;
	}

	/**
	 * Creates the Resource Id for an Tree structure element
	 *
	 * @param		image Element code
	 * @return		Resource Identifier
	 */
	private static String getStructureResourceId(int image) {
		if (image == 0) {
			return DefResources.TREE_STRUCTURE;
		} else {
			return DefResources.TREE_STRUCTURE + image;
		}
	}

	/**
	 * Method getItemImage
	 *
	 * @param		painterContext the painter context
	 * @param		iter the Item iterator withe the
	 * 				item to paint
	 * @param		imageProperty Image Property
	 * @param		map Imagemap
	 * @return		Image Model
	 */
	public static ImageModel getItemImage(
		PainterContext painterContext,
		LineIterator iter,
		String imageProperty,
		ImageMap map) {

		ImageModel image = null;

		// Resources können nur dann angezeigt werden, wenn im JSP-Tag
		// ein Image Property angegeben wurde.
		if (imageProperty != null) {
			Object value = iter.current(imageProperty);

			// Nun wird der Wert auf ein passendes Image abgebildet
			if (map != null) {
				image = map.mapValueToImage(value);
			}
		}

		return image;
	}

	/**
	 * Method getItemImage
	 *
	 * @param		painterContext the painter context
	 * @param		iter the Item iterator withe the
	 * 				item to paint
	 * @param		imageProperty Image Property
	 * @param		map Imagemap
	 * @param		size Imagesize
	 * @return		Image Model
	 */
	public static ImageModel getItemImage(
		PainterContext painterContext,
		TreeLineIterator iter,
		String imageProperty,
		ImageMap map,
		int size) {

		ImageModel image = null;

		// Resources können nur dann angezeigt werden, wenn im JSP-Tag
		// ein Image Property angegeben wurde.
		if (imageProperty != null) {
			TreeNodeDataModel node = (TreeNodeDataModel) iter.current();
			boolean expanded = iter.isCurrentExpanded();

			Object value = PainterHelp.callPropertyGet(node, imageProperty);

			if ((value != null) && (node instanceof TreeGroupDataModel)) {
				value = value.toString() + (expanded ? ".open" : ".closed");
			}

			// Nun wird der Wert auf ein passendes Image abgebildet
			if (map != null) {
				image = map.mapValueToImage(value);
			}

			// Wenn bei einem Gruppenknoten kein passendes Mapping gefunden
			// wurde, dann wird eines Framework Bilder verwendet.
			if (image == null) {
				if (node instanceof TreeGroupDataModel) {
					if (expanded) {
						image = painterContext.getImage(size, DefResources.TREE_FOLDEROPEN);
					} else {
						image = painterContext.getImage(size, DefResources.TREE_FOLDERCLOSED);
					}
				} else {
					image = painterContext.getImage(size, DefResources.TREE_ITEM);
				}
			}
		}

		return image;
	}

	/**
	 * Erzeugt eine Baumstruktur
	 *
	 * @param ctx				Painter Context
	 * @param style				TreeStyle
	 * @param handler			Java Script Handlers
	 * @param iter				TreeLineIterator
	 * @param label				Label (HTML encoded)
	 * @param tooltip			Tooltip (HTML encoded)
	 * @param icon				ImageModel
	 * @param size				The size
	 * @param transaction		Generates the Transaction Token in Hyperlinks
	 * @return	ConcreteElement
	 */
	public static ConcreteElement createOutline(
		PainterContext ctx,
		TreeStyle style,
		ClientHandler handler,
		TreeLineIterator iter,
		String label,
		String tooltip,
		ImageModel icon,
		int size,
		boolean transaction) {

		TreeNodeDataModel node = (TreeNodeDataModel) iter.current();

		if (node == null) {
			return null;
		}

		// Den Check-Status des Items ermitteln, wenn notwendig
		int checkState = -1;

		if (style.showCheckBoxes()) {
			checkState = iter.currentCheckState();
		}

		boolean expanded	= iter.isCurrentExpanded();
		boolean selected	= iter.isCurrentSelected();
		String key			= node.getUniqueKey();
		boolean isGroup		= node instanceof TreeGroupDataModel;

		int children = 0;

		if (node instanceof TreeGroupDataModel) {
			children = ((TreeGroupDataModel) node).size();
		}

		// Die einzelnen Segmente der Baumstruktur werden in umgekehrter
		// Reihenfolge auf einen Stack gelegt
		Stack stack			= new Stack();

		// ================================
		// Label
		// ================================

		// Das Item Label zeichnen
		ConcreteElement labelElement = null;

		boolean enabled		= !isGroup || style.getGroupSelect();

		// check if the  function for this row is disabled
		if (enabled && (style.getEnableProperty() != null)) {
			Object enableObj = iter.current(style.getEnableProperty());

			enabled = PainterHelp.toBoolean(enableObj, true);
		}

		if (enabled) {
			ActionPainter ap = ctx.createActionPainter(ControlActionDef.ACTION_DRILLDOWN);

			ap.setTransaction(transaction);
			ap.addParameter(key);
			ap.setLabel(label);
			ap.setTarget(getTarget(style, iter));
			ap.setStyleId(style.getStyleId());
			ap.setClientHandler(handler);

			labelElement = ap.createElement();
		} else {
			labelElement = new Span(Entities.NBSP + label);
		}

		// Den optionalen Tooltip setzen
		if (tooltip != null) {
			labelElement.setTitle(tooltip);
		}

		stack.push(
			new TD(labelElement).setClass(
				selected ? DefHtmlClass.TREEITEM_SEL : DefHtmlClass.TREEITEM));

		// ================================
		// Icon
		// ================================

		// Das Icon zeichen, wenn vorhanden
		if (icon != null) {
			stack.push(new TD(ctx.createImage(icon)));
		}

		// ================================
		// Checkbox
		// ================================
		if (style.showCheckBoxes()) {
			ConcreteElement checkBox =
				createCheckBox(
					ctx,
					key,
					checkState,
					style.getSelectMode(),
					handler,
					size,
					ctx.isDisplayOnly(),
					transaction);

			if (checkBox != null) {
				stack.push(new TD(checkBox));
			}
		}

		// ================================
		// Baumstruktur
		// ================================

		// Es wird die folgende Bit-Kodierung zur Bestimmung des
		// Resources verwendet:
		//
		// 0x20 = Plus-Zeichen vor dem Item
		// 0x10 = Minus-Zeichen vor dem Item
		// 0x08 = Verbindungslinie nach Norden
		// 0x04 = Verbindungslinie nach Süden
		// 0x02 = Verbindungslinie nach Osten
		// 0x01 = Verbindungslinie nach Westen
		//
		// Von allen möglichen Kombinationen werden jedoch
		// lediglich 13 Bitmaps tatsächlich verwendet! Die
		// Bitmaps sind unter ihrem dezimalen(!) Namen
		// abgelegt.

		// Das Item hat auf jeden Fall eine Verbindungslinie nach Osten
		int img				= style.showLines() ? 0x02 : 0x00;
		boolean childLevel	= true;
		boolean rootLevel	= false;

		// Die Icons zum auf und Zuklappen der Äste werden nur dann angezeigt,
		// wenn der Baum nicht ohnehin vollständig aufgeklappt ist.
		boolean showButtons	=
			style.showButtons() && !ExpansionMode.FULL.equals(style.getExpansionMode());

		while (node != null) {
			if ((node.getParent() == null) && !style.showRoot()) {
				break;
			}

			TreeGroupDataModel parent = node.getParent();

			// Wir befinden uns auf der obersten Anzeigeebene das Baumes,
			// wenn der aktuelle Knoten die Baumwurzel ist, oder wenn
			// bei nicht angezeigter Wurzel der übergeordnete Knoten die
			// Wurzel ist.
			rootLevel = (parent == null) || (!style.showRoot() && (parent.getParent() == null));

			// Wenn auf der Wurzelebene Keine Verbindungslinien gezeichent
			// werden soll, dann kann an dieser Stelle abgebrochen werden
			if (rootLevel && !style.showLinesAtRoot()) {
				break;
			}

			if (!rootLevel && style.showLines()) {

				// Eine Verbindungslinie zum Parent nach Norden zeichnen
				if (childLevel) {
					img |= 0x08;
				}

				if (!iter.isLastVisibleChild(node)) {
					// Es gibt eine Verbindungslinie zu weiteren Söhnen
					// in Richtung Süden
					img |= 0x08 | 0x04;
				}
			}

			// Der folgende Block wird NUR für den and die Prozedur
			// übergebenen Knoten ausgewerter => childLevel == true
			if (childLevel && showButtons) {
				if (-1 == children) {

					// Die Anzahl der Kinder steht noch nicht fest.
					// Es wird deshalb ein (+) Symbol zum aufklappen angeboten
					img |= 0x20;
				} else if (children > 0) {

					// Der Knoten hat Kinder. Es entscheidet nun der
					// Expansionsstatus über das angezeigte Image.
					if (expanded) {

						// Symbol zum Schließen vorsehen
						img |= 0x10;
					} else {

						// Symbol zum öffnen vorsehen
						img |= 0x20;
					}
				}
			}

			if (!childLevel) {
				img &= 0x0C;
			}

			ImageModel structureImage = ctx.getImage(size, getStructureResourceId(img));
			ConcreteElement segment = null;

			if ((img & 0x20) > 0) {

				// Es wird ein (+) Zeichen angezeigt.
				// Es muß also ein Hyperlink zum öffnen des Knoten eingefügt werden.
				// Wenn die Kinder des Knotens zu diesem Zeitpunkt bereits geladen sind,
				// dann kann dieses von der JSP-Seite selbst gehandelt werden. Im
				// anderen Fall muß das Controller-Servlet aufgefordert werden.
				if (-1 == children) {
					ActionPainter ap = ctx.createActionPainter(ControlActionDef.ACTION_EXPANDEX);

					ap.setTransaction(transaction);
					ap.addParameter(key);
					ap.setImage(structureImage);
					ap.addEventHandler(ClientEvent.ONCLICK, handler.getHandler(ClientEvent.EXT_ONEXPANDEX));

					segment = ap.createElement();
				} else {
					ActionPainter ap = ctx.createActionPainter(ControlActionDef.ACTION_EXPAND);

					ap.setTransaction(transaction);
					ap.addParameter(key);
					ap.setImage(structureImage);
					ap.addEventHandler(ClientEvent.ONCLICK, handler.getHandler(ClientEvent.EXT_ONEXPAND));

					segment = ap.createElement();
				}
			} else if ((img & 0x10) > 0) {

				// Es wird ein (-) Zeichen angezeigt.
				// Es muß also ein Hyperlink zum schließen des Knoten eingefügt werden.
				ActionPainter ap = ctx.createActionPainter(ControlActionDef.ACTION_COLLAPSE);

				ap.setTransaction(transaction);
				ap.addParameter(key);
				ap.setImage(structureImage);
				ap.addEventHandler(ClientEvent.ONCLICK, handler.getHandler(ClientEvent.EXT_ONCOLLAPSE));

				segment = ap.createElement();
			} else {
				// Es handelt sich um eine einfache Verbindungslinie welche über
				// keinen Link verfügt.
				segment = ctx.createImage(structureImage);
			}

			if ((img & 0x0c) == 0x0c) {
				ImageModel bgImage = ctx.getImage(size, getStructureResourceId(0x0c));

				stack.push(
					new TD(segment)
						.setStyle("background-image:url(" + bgImage.getSource() + ");background-repeat:repeat-y"));
			} else {
				stack.push(new TD(segment));
			}

			node		= parent;
			img			= 0;
			childLevel	= false;
		}

		// Die Tabelle zusammenbauen
		TR row = new TR();
		row.setVAlign(AlignType.top);

		while (!stack.isEmpty()) {
			row.addElement((ConcreteElement) stack.pop());
		}

		return (Table) new Table()
			.setBorder(0)
			.setCellPadding(0)
			.setCellSpacing(0)
			.setHeight("100%")
			.setWidth("100%")
			.addElement(row)
			.setClass(DefHtmlClass.TREE_OUTLINE);
	}

	/**
	 * renders a CheckBox
	 * @param ctx				Painter context
	 * @param key				String
	 * @param checkState		CheckState true if checked
	 * @param selectMode		SelectMode
	 * @param handler			Java Script Handlers
	 * @param size				Image Size
	 * @param displayOnly		Indicates that this checkbox
	 * 							should be only for display
	 * @param transaction		Generates the Transaction Token in Hyperlinks
	 * @return	ConcreteElement
	 */
	public static ConcreteElement createCheckBox(
		PainterContext ctx,
		String key,
		int checkState,
		SelectMode selectMode,
		ClientHandler handler,
		int size,
		boolean displayOnly,
		boolean transaction) {

		ConcreteElement cb	= null;

		switch (checkState) {
			case -2 :
				// Der Knoten bietet keine Checkbox an
				cb = ctx.createImage(size, DefResources.CHECKBOX_NONE);
				break;

			case -1 :
				// Der Knoten verfügt nicht über die notwendigen Informationen
				cb = ctx.createImage(size, DefResources.CHECKBOX_INVALID);
				break;

			case 0 :
				// Der Knoten ist nicht markiert
				if (displayOnly) {
					cb = ctx.createImage(size, DefResources.CHECKBOX_UNCHECKED);
				} else {
					ActionPainter ap = ctx.createActionPainter(ControlActionDef.ACTION_CHECK);

					ap.setTransaction(transaction);
					ap.addParameter(key);
					ap.addParameter(selectMode);
					ap.addParameter(true);
					ap.setImage(ctx.getImage(size, DefResources.CHECKBOX_UNCHECKED));
					ap.addEventHandler(ClientEvent.ONCLICK, handler.getHandler(ClientEvent.EXT_ONCHECK));

					cb = ap.createElement();
				}
				break;

			case 1 :
				// Der Knoten ist vollständig markiert
				if (displayOnly) {
					cb = ctx.createImage(size, DefResources.CHECKBOX_CHECKED);
				} else {
					ActionPainter ap = ctx.createActionPainter(ControlActionDef.ACTION_CHECK);

					ap.setTransaction(transaction);
					ap.addParameter(key);
					ap.addParameter(selectMode);
					ap.addParameter(false);
					ap.setImage(ctx.getImage(size, DefResources.CHECKBOX_CHECKED));
					ap.addEventHandler(ClientEvent.ONCLICK, handler.getHandler(ClientEvent.EXT_ONUNCHECK));

					cb = ap.createElement();
				}
				break;

			case 2 :
				// Der Knoten ist nur teilweise markiert
				if (displayOnly) {
					cb = ctx.createImage(size, DefResources.CHECKBOX_INDETERMINATE);
				} else {
					ActionPainter ap = ctx.createActionPainter(ControlActionDef.ACTION_CHECK);

					ap.setTransaction(transaction);
					ap.addParameter(key);
					ap.addParameter(selectMode);
					ap.addParameter(false);
					ap.setImage(ctx.getImage(size, DefResources.CHECKBOX_INDETERMINATE));
					ap.addEventHandler(ClientEvent.ONCLICK, handler.getHandler(ClientEvent.EXT_ONUNCHECK));

					cb = ap.createElement();
				}
				break;

			default :
				;
		}

		return cb;
	}
}