/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/control/ControlActionDef.java,v 1.39 2005/11/13 14:03:58 P001002 Exp $
 * $Revision: 1.39 $
 * $Date: 2005/11/13 14:03:58 $
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

package com.cc.framework.ui.control;

import java.io.Serializable;
import java.util.Hashtable;

import com.cc.framework.common.InvalidEnumType;
import com.cc.framework.common.SimpleEnumType;
import com.cc.framework.common.SortOrder;
import com.cc.framework.ui.SelectMode;
import com.cc.framework.ui.model.SchedulerScope;
import com.cc.framework.ui.painter.PainterContext;

/**
 * Encapsulates an action with the needed parameters.
 * <p>
 * <b>NOTE:</b> New actions must also be assign into the <code>MAP</code>!
 * 
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version $Revision: 1.39 $
 * @since 1.0
 */
public class ControlActionDef implements SimpleEnumType, Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -7514395284976051542L;

	/**
	 * Action: Page this event is triggered within an control, when the user
	 * moves from one page to another page
	 * 
	 * Handler: void onPage(ControlRequestContext ctx, int page)
	 * 
	 * Arguments: page The page to which the system should go. -1 = jump to the
	 * last page 0..n = jump to the specified page Caution: The Handler itself
	 * must ensure adherence to the valid range.
	 */
	public static final ControlActionDef ACTION_PAGE = new ControlActionDef(
			"Page", new Class[] { int.class });

	/**
	 * Handler: void onCellClick(ControlRequestContext ctx, String column,
	 * String key)
	 */
	public static final ControlActionDef ACTION_CELLCLICK = new ControlActionDef(
			"CellClick", new Class[] { String.class, String.class });

	/**
	 * Handler: void onDrilldown(ControlRequestContext ctx, String key)
	 */
	public static final ControlActionDef ACTION_DRILLDOWN = new ControlActionDef(
			"Drilldown", new Class[] { String.class });

	/**
	 * Handler: void onSelect(ControlRequestContext ctx, String key)
	 */
	public static final ControlActionDef ACTION_SELECT = new ControlActionDef(
			"Select", new Class[] { String.class });

	/**
	 * Handler: void onEdit(ControlRequestContext ctx, String key)
	 */
	public static final ControlActionDef ACTION_EDIT = new ControlActionDef(
			"Edit", new Class[] { String.class });

	/**
	 * Handler: void onEdit(ControlRequestContext ctx, String key)
	 */
	public static final ControlActionDef ACTION_DELETE = new ControlActionDef(
			"Delete", new Class[] { String.class });

	/**
	 * Handler: void onAdd(ControlRequestContext ctx, String parentKey)
	 */
	public static final ControlActionDef ACTION_ADD = new ControlActionDef(
			"Add", new Class[] { String.class });

	/**
	 * Action: Expand This action is triggered by a tree if a groupnode is
	 * opend.
	 * 
	 * Handler: void onExpand(ControlRequestContext ctx, String key)
	 */
	public static final ControlActionDef ACTION_EXPAND = new ControlActionDef(
			"Expand", new Class[] { String.class });

	/**
	 * Action: Collapse This action is triggered by a tree if a groupnode is
	 * closed.
	 * 
	 * Handler: void onCollapse(ControlRequestContext ctx, String key)
	 */
	public static final ControlActionDef ACTION_COLLAPSE = new ControlActionDef(
			"Collapse", new Class[] { String.class });

	/**
	 * Action: ExpandEx This action is triggered by a tree if a groupnode with
	 * unknown number of children is opend. The handler can now load the
	 * required nodes/data
	 * 
	 * Handler: void onExpandEx(ControlRequestContext ctx, String key)
	 */
	public static final ControlActionDef ACTION_EXPANDEX = new ControlActionDef(
			"ExpandEx", new Class[] { String.class });

	/**
	 * Action: Check This action is triggered if a checkbox is clicked.
	 * 
	 * Handler: void onCheck(ControlRequestContext ctx, String key, SelectMode
	 * mode, boolean check)
	 */
	public static final ControlActionDef ACTION_CHECK = new ControlActionDef(
			"Check", new Class[] { String.class, SelectMode.class,
					boolean.class });

	/**
	 * Action: CheckAll This action is triggered if all rows are checked or
	 * unchecked.
	 * 
	 * Handler: void onCheckAll(ControlRequestContext ctx, SelectMode mode,
	 * boolean check)
	 */
	public static final ControlActionDef ACTION_CHECKALL = new ControlActionDef(
			"CheckAll", new Class[] { SelectMode.class, boolean.class });

	/**
	 * Action: CheckColumn This action is triggered if all rows of a secific
	 * column are checked or unchecked.
	 * 
	 * Handler: void onCheckColumn(ControlRequestContext ctx, String column,
	 * boolean check)
	 */
	public static final ControlActionDef ACTION_CHECKCOLUMN = new ControlActionDef(
			"CheckColumn", new Class[] { String.class, boolean.class });

	/**
	 * Action: Create
	 * 
	 * Handler: void onCreate(ControlRequestContext ctx)
	 */
	public static final ControlActionDef ACTION_CREATE = new ControlActionDef(
			"Create", new Class[] {});

	/**
	 * Action: Refresh
	 * 
	 * Handler: void onRefresh(ControlRequestContext ctx)
	 */
	public static final ControlActionDef ACTION_REFRESH = new ControlActionDef(
			"Refresh", new Class[] {});

	/**
	 * Action: TabClick
	 * 
	 * Handler: void onTabClick(ControlRequestContext ctx, String tabId)
	 */
	public static final ControlActionDef ACTION_TABCLICK = new ControlActionDef(
			"TabClick", new Class[] { String.class });

	/**
	 * Action: TabScroll
	 * 
	 * Handler: void onTabScroll(ControlRequestContext ctx, String tabId)
	 */
	public static final ControlActionDef ACTION_TABSCROLL = new ControlActionDef(
			"TabScroll", new Class[] { String.class });

	/**
	 * Action: CrumbClick
	 * 
	 * Handler: void onCrumbClick(ControlRequestContext ctx, String crumbId)
	 */
	public static final ControlActionDef ACTION_CRUMBCLICK = new ControlActionDef(
			"CrumbClick", new Class[] { String.class });

	/**
	 * Handler: void onSort(ControlRequestContext ctx, String column, SortOrder
	 * direction)
	 */
	public static final ControlActionDef ACTION_SORT = new ControlActionDef(
			"Sort", new Class[] { String.class, SortOrder.class });

	/**
	 * Action: help
	 * 
	 * Handler: void onHelp(ControlRequestContext ctx, String helpId)
	 */
	public static final ControlActionDef ACTION_HELP = new ControlActionDef(
			"Help", new Class[] { String.class });

	/**
	 * Action: minimize
	 * 
	 * Handler: void onMinimize(ControlRequestContext ctx)
	 */
	public static final ControlActionDef ACTION_MINIMIZE = new ControlActionDef(
			"Minimize", new Class[] {});

	/**
	 * Action: maximize
	 * 
	 * Handler: void onMaximize(ControlRequestContext ctx)
	 */
	public static final ControlActionDef ACTION_MAXIMIZE = new ControlActionDef(
			"Maximize", new Class[] {});

	/**
	 * Action: restore
	 * 
	 * Handler: void onRestore(ControlRequestContext ctx)
	 */
	public static final ControlActionDef ACTION_RESTORE = new ControlActionDef(
			"Restore", new Class[] {});

	/**
	 * Action: close
	 * 
	 * Handler: void onClose(ControlRequestContext ctx)
	 */
	public static final ControlActionDef ACTION_CLOSE = new ControlActionDef(
			"Close", new Class[] {});

	/**
	 * Action: View this event is triggered within an control, when the user
	 * switches to another view
	 * 
	 * Handler: void onView(ControlRequestContext ctx, String view)
	 */
	public static final ControlActionDef ACTION_VIEW = new ControlActionDef(
			"View", new Class[] { String.class });

	/**
	 * Action: ChangeDate this event is triggered within an scheduler control,
	 * when the user switches the controls view to another date. The parameter
	 * timeInMillis designates the new time in UTC milliseconds from the epoch.
	 * 
	 * Handler: void onChangeDate(ControlRequestContext ctx, long timeInMillis,
	 * String view)
	 */
	public static final ControlActionDef ACTION_CHANGEDATE = new ControlActionDef(
			"ChangeDate", new Class[] { long.class, String.class });

	/**
	 * Action: ChangeDate this event is triggered within an scheduler control,
	 * when the user klicks on a date element. The parameter timeInMillis
	 * designates the new time in UTC milliseconds from the epoch.
	 * 
	 * Handler: void onSelectDate(ControlRequestContext ctx, long timeInMillis,
	 * String view)
	 */
	public static final ControlActionDef ACTION_SELECTDATE = new ControlActionDef(
			"SelectDate", new Class[] { long.class, String.class });

	/**
	 * Action: Appointment this event is triggered within an scheduler control,
	 * when the user klicks on and appointment The parameter timeInMillis
	 * designates the time in UTC milliseconds from the epoch.
	 * 
	 * Handler: void onAppointmentclick(ControlRequestContext ctx, String key,
	 * long timeInMillis)
	 */
	public static final ControlActionDef ACTION_APPOINTMENTCLICK = new ControlActionDef(
			"AppointmentClick", new Class[] { String.class, long.class });

	/**
	 * Action: CheckAppointment This action is triggered if a checkbox on an
	 * appointment is clicked.
	 * 
	 * Handler: void onCheckAppointment(ControlRequestContext ctx, String key,
	 * long timeInMillis, boolean check)
	 */
	public static final ControlActionDef ACTION_CHECKAPPOINTMENT = new ControlActionDef(
			"CheckAppointment", new Class[] { String.class, long.class, boolean.class });

	/**
	 * Action: CheckDate This action is triggered if a checkbox on an date is
	 * clicked.
	 * 
	 * Handler: void onCheckDate(ControlRequestContext ctx, long timeInMillis,
	 * SchedulerScope scope, boolean check)
	 */
	public static final ControlActionDef ACTION_CHECKDATE = new ControlActionDef(
			"CheckDate", new Class[] { long.class, SchedulerScope.class, boolean.class });

	/**
	 * Action: AddAppointment this event is triggered within an scheduler
	 * control, when the user klicks on a add appointment button element. The
	 * parameter timeInMillis designates the new time in UTC milliseconds from
	 * the epoch.
	 * 
	 * Handler: void onAddDate(ControlRequestContext ctx, long timeInMillis)
	 */
	public static final ControlActionDef ACTION_ADDAPPOINTMENT = new ControlActionDef(
			"AddAppointment", new Class[] { long.class });

	/**
	 * Action: Print (without parameters)
	 * 
	 * Handler: void onPrint(ControlRequestContext ctx)
	 */
	public static final ControlActionDef ACTION_PRINTLIST = new ControlActionDef(
			"PrintList", new Class[] {});

	/**
	 * Action: Export (without parameters)
	 * 
	 * Handler: void onExport(ControlRequestContext ctx)
	 */
	public static final ControlActionDef ACTION_EXPORTLIST = new ControlActionDef(
			"ExportList", new Class[] {});
	/**
	 * List with all actions defined above
	 */
	private static Hashtable registry;

	/**
	 * The static constructor registers all global action definitions in the
	 * registry
	 */
	static {
		registry = new Hashtable();

		registerActionDefinition(ACTION_ADD);
		registerActionDefinition(ACTION_CELLCLICK);
		registerActionDefinition(ACTION_CHECK);
		registerActionDefinition(ACTION_CHECKALL);
		registerActionDefinition(ACTION_CHECKCOLUMN);
		registerActionDefinition(ACTION_CLOSE);
		registerActionDefinition(ACTION_COLLAPSE);
		registerActionDefinition(ACTION_CREATE);
		registerActionDefinition(ACTION_CRUMBCLICK);
		registerActionDefinition(ACTION_DELETE);
		registerActionDefinition(ACTION_DRILLDOWN);
		registerActionDefinition(ACTION_EDIT);
		registerActionDefinition(ACTION_EXPAND);
		registerActionDefinition(ACTION_EXPANDEX);
		registerActionDefinition(ACTION_HELP);
		registerActionDefinition(ACTION_MAXIMIZE);
		registerActionDefinition(ACTION_MINIMIZE);
		registerActionDefinition(ACTION_PAGE);
		registerActionDefinition(ACTION_REFRESH);
		registerActionDefinition(ACTION_RESTORE);
		registerActionDefinition(ACTION_SELECT);
		registerActionDefinition(ACTION_SORT);
		registerActionDefinition(ACTION_TABCLICK);
		registerActionDefinition(ACTION_EXPORTLIST);
		registerActionDefinition(ACTION_PRINTLIST);

		// Scheduler Events
		registerActionDefinition(ACTION_CHANGEDATE);
		registerActionDefinition(ACTION_APPOINTMENTCLICK);
		registerActionDefinition(ACTION_SELECTDATE);
		registerActionDefinition(ACTION_VIEW);
		registerActionDefinition(ACTION_CHECKAPPOINTMENT);
		registerActionDefinition(ACTION_ADDAPPOINTMENT);
		registerActionDefinition(ACTION_CHECKDATE);
	}

	/**
	 * The name of the action. This name is used to call an event handler in the
	 * action class. The eventhandler must follow the naming convention:
	 * <code>do[action]()</code>.
	 */
	private String action;

	/**
	 * Array with the parameters that belongs to the action.
	 */
	private Class[] types = null;

	/**
	 * Constructor for ControlActionDef The class is instantiate with the
	 * parameters. So the typs of the parameters can be checked.
	 * 
	 * @param actionCode
	 *            Name of the Action
	 * @param types
	 *            Paramter of the action
	 */
	public ControlActionDef(String actionCode, Class[] types) {
		this.action = actionCode;
		this.types = types;
	}

	/**
	 * Registers an action definition in the global registry
	 * 
	 * @param def
	 *            The action definition
	 */
	public static synchronized void registerActionDefinition(ControlActionDef def) {
		if (registry.contains(def.getName())) {
			// unregister a previous action definition
			registry.remove(def.getName());
		}

		registry.put(def.getName(), def);
	}

	/**
	 * Checks if a certain action is registerd in the action registry
	 * 
	 * @param action
	 *            The action name to check
	 * @return returns <code>true</code> if the action is allready registered
	 *         in the registry
	 */
	public static boolean isRegistered(String action) {
		return registry.containsKey(action);
	}

	/**
	 * Returns a ControlActionDef for the argument
	 * 
	 * @param code
	 *            String to parse
	 * @return ControlActionDef or <code>null</code>
	 */
	public static ControlActionDef query(String code) {

		return (ControlActionDef) registry.get(code);
	}

	/**
	 * Returns a ControlActionDef for the argument
	 * 
	 * @param code
	 *            String to parse
	 * @return ControlActionDef
	 */
	public static ControlActionDef parse(String code) {

		ControlActionDef def = (ControlActionDef) registry.get(code);

		if (def == null) {
			throw new InvalidEnumType("Unknown ControlActionDef: " + code);
		}

		return def;
	}

	/**
	 * Compares the argument to this type
	 * 
	 * @param obj
	 *            String to compare
	 * @return boolean
	 */
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		} else if (obj instanceof String) {
			return action.equals(obj);
		} else if (obj instanceof ControlActionDef) {
			return action.equals(((ControlActionDef) obj).action);
		}

		return false;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return action.hashCode();
	}

	/**
	 * Die Methode erzeugt eine konkrete Aktionsinstanz zu dieser
	 * Aktionsdefinition. Mit Hilfe der ControlAction.setParam() Methoden können
	 * die zu den vordefinierten Parametertypen passenden aktuellen Parameter
	 * gesetzt werden.
	 * 
	 * @param ctx
	 *            the painter context
	 * @return ControlAction
	 */
	public ControlAction createInstance(PainterContext ctx) {
		return createInstance(ctx.getControl());
	}

	/**
	 * Die Methode erzeugt eine konkrete Aktionsinstanz zu dieser
	 * Aktionsdefinition. Mit Hilfe der ControlAction.setParam() Methoden können
	 * die zu den vordefinierten Parametertypen passenden aktuellen Parameter
	 * gesetzt werden.
	 * 
	 * @param ctrl
	 *            Control
	 * @return ControlAction
	 */
	public ControlAction createInstance(Control ctrl) {
		if (!isRegistered(action)) {
			registerActionDefinition(this);
		}

		return new ControlAction(this, ctrl);
	}

	/**
	 * Die Methode erzeugt eine konkrete Aktionsinstanz zu dieser
	 * Aktionsdefinition. Mit Hilfe der ControlAction.setParam() Methoden können
	 * die zu den vordefinierten Parametertypen passenden aktuellen Parameter
	 * gesetzt werden.
	 * 
	 * @param ctrl
	 *            Control
	 * @param actionName
	 *            Action name
	 * @return ControlAction
	 */
	public ControlAction createInstance(Control ctrl, String actionName) {
		if (!isRegistered(action)) {
			registerActionDefinition(this);
		}

		return new ControlAction(this, ctrl, actionName);
	}

	/**
	 * Liefert die Anzahl der formalen Parameter zurück.
	 * 
	 * @return int
	 */
	public int getFormalParameterCount() {
		return types.length;
	}

	/**
	 * Liefert ein Array mit den Typen der formalen Parameter zurück. Der erste
	 * Parameter in der Liste ist dabei immer vom Typ ControlRequestContext!
	 * 
	 * @return Class
	 */
	public Class[] getFormalParameterTypes() {

		Class[] list = new Class[1 + types.length];

		// Als erstes wird immer der Kontext übergeben!
		list[0] = ControlRequestContext.class;

		for (int i = 0; i < types.length; i++) {
			list[i + 1] = types[i];
		}

		return list;
	}

	/**
	 * Method mapActualParameter
	 * 
	 * @param ctx
	 *            ControlRequestContext
	 * @param paramStr
	 *            Parameter
	 * @return Object
	 * @throws Exception
	 *             If an Exception occurred
	 */
	public Object[] mapActualParameter(ControlRequestContext ctx,
			String[] paramStr) throws Exception {

		// Nun werden alle formalen Parameter abgewandert. Dabei werden
		// die übergebenen aktuellen Parameter in die passenden Datentypen
		// konvertiert.
		Object[] param = new Object[1 + types.length];

		// Als erstes wird immer der Kontext übergeben!
		param[0] = ctx;

		for (int i = 0; i < types.length; i++) {
			Class type = types[i];

			if (i >= paramStr.length) {
				throw new Exception("Not enough parameters for action: "
						+ action);
			}

			if ((type == int.class) || (type == Integer.class)) {
				param[i + 1] = new Integer(paramStr[i]);
			} else if ((type == long.class) || (type == Long.class)) {
				param[i + 1] = new Long(paramStr[i]);
			} else if ((type == boolean.class) || (type == Boolean.class)) {
				param[i + 1] = new Boolean(paramStr[i]);
			} else if (type == String.class) {
				param[i + 1] = new String(paramStr[i]);
			} else if (type == SortOrder.class) {
				param[i + 1] = SortOrder.parse(paramStr[i]);
			} else if (type == SelectMode.class) {
				param[i + 1] = SelectMode.parse(paramStr[i]);
			} else if (type == SchedulerScope.class) {
				param[i + 1] = SchedulerScope.parse(paramStr[i]);
			} else {
				// Für diesen Parametertyp is noch keine Zeichenkonvertierungs-
				// funktion vorhanden!
				throw new Exception("Unknown Parameter Type: " + type.getName());
			}
		}

		return param;
	}

	/**
	 * Retrieves the class for the given Paramter
	 * 
	 * @param index
	 *            Parameter Index
	 * @return Parameter Type
	 */
	public Class getParameterType(int index) {
		return types[index];
	}

	/**
	 * Method checkType
	 * 
	 * @param index
	 *            Index
	 * @param type
	 *            Type
	 */
	public void checkType(int index, Class type) {

		if (types[index] != type) {
			throw new IllegalArgumentException("Parameter[" + index
					+ "] type mismatch. Expected type="
					+ types[index].getName());
		}
	}

	/**
	 * Method getName
	 * 
	 * @return String
	 */
	public String getName() {
		return action;
	}

	/**
	 * Retrieves the action identifier
	 * 
	 * @return action identifier
	 */
	public String getAction() {
		return action;
	}

	/**
	 * Retrieves the parameter types for this action
	 * 
	 * @return parameter types
	 */
	public Class[] getTypes() {
		return types;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuffer buf = new StringBuffer();

		buf.append(action).append("(");

		if (types != null) {
			for (int i = 0; i < types.length; i++) {
				if (i > 0) {
					buf.append(", ");
				}

				buf.append(types[i].getName());
			}
		}

		buf.append(")");

		return buf.toString();
	}
}