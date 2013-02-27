/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/ui/control/RecurrencePatternControl.java,v 1.6 2005/09/27 14:06:22 P001002 Exp $
 * $Revision: 1.6 $
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

package com.cc.framework.ui.control;

import com.cc.framework.ui.model.ControlDesignModel;
import com.cc.framework.ui.model.DataModel;
import com.cc.framework.ui.model.RecurrencePattern;
import com.cc.framework.ui.model.RecurrencePatternDesignModel;
import com.cc.framework.ui.model.RecurrencePatternStateModel;
import com.cc.framework.ui.model.RecurrenceType;
import com.cc.framework.ui.model.StateModel;
import com.cc.framework.ui.model.imp.RecurrencePatternImp;
import com.cc.framework.util.CalendarHelp;

/**
 * TextControl
 *
 * @author   <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version  $Revision: 1.6 $
 * @since    1.0
 */
public class RecurrencePatternControl
	extends Control
	implements RecurrencePatternStateModel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -8084598260204794916L;

	/**
	 * Designmodel
	 */
	private RecurrencePatternDesignModel designModel = null;

	/**
	 * DataModel
	 */
//	private RecurrencePattern dataModel = null;

	private RecurrenceType type = RecurrenceType.NONE;

	private int dayDayMask = 0;
	private int dayInterval = 0;

	private int weekDayMask = 0;
	private int weekInterval = 0;

	private int monthSubtype = 0;
	private int monthDayOfMonth = 0;
	private int monthInterval = 0;

	private int monthNthInstance = 0;
	private int monthNthDayMask = 0;
	private int monthNthInterval = 0;

	private int yearSubtype = 0;
	private int yearMonthOfYear	= 0;
	private int yearDayOfMonth = 0;

	private int yearNthInstance = 0;
	private int yearNthDayMask = 0;
	private int yearNthMonthOfYear = 0;

	/*
		private int occurrences = 10;
		private Calendar patternEndDate = null;
		private Calendar patternStartDate = null;
	*/

	/**
	 * Constructor
	 */
	public RecurrencePatternControl() {
		super();
	}

	/**
	 * Sets the DesignModel
	 * @param	designModel	RecurrencePatternDesignModel
	 */
	public void setDesignModel(RecurrencePatternDesignModel designModel) {
		this.designModel = designModel;
	}

	/**
	 * @see com.cc.framework.ui.control.Control#getDataModel()
	 */
	public DataModel getDataModel() {
// TODO:
		return null;
	}

	/**
	 * @see com.cc.framework.ui.control.Control#getDesignModel()
	 */
	public ControlDesignModel getDesignModel() {
		return designModel;
	}

	/**
	 * @see com.cc.framework.ui.control.Control#getStateModel()
	 */
	public StateModel getStateModel() {
		return this;
	}

	/**
	 * @see com.cc.framework.ui.model.StateModel#reset()
	 */
	public void reset() {
		type				= RecurrenceType.NONE;

		dayInterval			= 1;
		dayDayMask			= 0;

		weekInterval		= 1;
		weekDayMask			= 0;

		monthSubtype		= 0;
		monthDayOfMonth		= 0;
		monthInterval		= 1;

		monthNthInstance	= 1;
		monthNthDayMask		= 0;
		monthNthInterval	= 1;
		
		yearSubtype			= 0;
		yearMonthOfYear		= 0;
		yearDayOfMonth		= 0;

		yearNthInstance		= 1;
		yearNthDayMask		= 0;
		yearNthMonthOfYear	= 0;
	}

	/**
	 * Returns if the filter is activated (default=true). This means
	 * that all Strings which should be displayed in the HTML page
	 * are html encoded
	 *
	 * @return	<code>true</code> if string will be html encoded;
	 * 			<code>false</code> otherwise
	 */
	public boolean filter() {
		return designModel.filter();
	}

	/**
	 * Initializes the controls variables from the
	 * given recurrence pattern
	 * 
	 * @param		pattern the pattern
	 */
	public void setFromPattern(RecurrencePattern pattern) {
		reset();

		if ((pattern == null) || RecurrenceType.NONE.equals(pattern.getRecurrenceType())) {
			this.type				= RecurrenceType.NONE;
		} else if (RecurrenceType.DAILY.equals(pattern.getRecurrenceType())) {
			this.type				= RecurrenceType.DAILY;
			this.dayInterval		= pattern.getInterval();
			this.dayDayMask			= pattern.getDayOfWeekMask();
		} else if (RecurrenceType.WEEKLY.equals(pattern.getRecurrenceType())) {
			this.type				= RecurrenceType.WEEKLY;
			this.weekInterval		= pattern.getInterval();
			this.weekDayMask		= pattern.getDayOfWeekMask();
		} else if (RecurrenceType.MONTHLY.equals(pattern.getRecurrenceType())) {
			this.type				= RecurrenceType.MONTHLY;
			this.monthSubtype		= 0;
			this.monthDayOfMonth	= pattern.getDayOfMonth();
			this.monthInterval		= pattern.getInterval();
		} else if (RecurrenceType.MONTHLY_NTH.equals(pattern.getRecurrenceType())) {
			this.type				= RecurrenceType.MONTHLY;
			this.monthSubtype		= 1;
			this.monthNthInstance	= pattern.getInstance();
			this.monthNthDayMask	= pattern.getDayOfWeekMask();
			this.monthNthInterval	= pattern.getInterval();
		} else if (RecurrenceType.YEARLY.equals(pattern.getRecurrenceType())) {
			this.type				= RecurrenceType.YEARLY;
			this.yearSubtype		= 0;
			this.yearMonthOfYear	= pattern.getMonthOfYear();
			this.yearDayOfMonth		= pattern.getDayOfMonth();
		} else if (RecurrenceType.YEARLY_NTH.equals(pattern.getRecurrenceType())) {
			this.type				= RecurrenceType.YEARLY;
			this.yearSubtype		= 1;
			this.yearNthInstance	= pattern.getInstance();
			this.yearNthDayMask		= pattern.getDayOfWeekMask();
			this.yearNthMonthOfYear	= pattern.getMonthOfYear();
		}
	}

	/**
	 * Initializes the given recurrence pattern with the values
	 * from the control. A new pattern will be created if the
	 * parameter is <cos>null</code>
	 * 
	 * @param		pattern the pattern to fill
	 * @return		RecurrencePattern
	 */
	public RecurrencePattern setToPattern(RecurrencePattern pattern) {
		if (pattern == null) {
			pattern = new RecurrencePatternImp();
		}

		RecurrenceType recurType	= RecurrenceType.NONE;
		int dayOfMonth				= 0;
		int dayOfWeekMask			= 0;
		int instance				= 0;
		int interval				= 0;
		int monthOfYear				= 0;

		if (RecurrenceType.DAILY.equals(type)) {
			recurType		= RecurrenceType.DAILY;
			dayOfWeekMask	= dayDayMask;
			if ((dayDayMask == CalendarHelp.DOWM_WEEKDAYS) || (dayDayMask == CalendarHelp.DOWM_WEEKENDDAYS)) {
				interval	= 1;
			} else {
				interval	= Math.max(1, dayInterval);
			}
		} else if (RecurrenceType.WEEKLY.equals(type)) {
			recurType		= RecurrenceType.WEEKLY;
			dayOfWeekMask	= weekDayMask;
			interval		= Math.max(1, weekInterval);
		} else if (RecurrenceType.MONTHLY.equals(type)) {
			if (monthSubtype == 0) {
				recurType		= RecurrenceType.MONTHLY;
				dayOfMonth		= Math.max(1, monthDayOfMonth);
				interval		= Math.max(1, monthInterval);
			} else {
				recurType		= RecurrenceType.MONTHLY_NTH;
				instance		= monthNthInstance;
				dayOfWeekMask	= monthNthDayMask;
				interval		= Math.max(1, monthNthInterval);
			}
		} else if (RecurrenceType.YEARLY.equals(type)) {
			if (yearSubtype == 0) {
				recurType		= RecurrenceType.YEARLY;
				monthOfYear		= yearMonthOfYear;
				dayOfMonth		= Math.max(1, yearDayOfMonth);
			} else {
				recurType		= RecurrenceType.YEARLY_NTH;
				instance		= yearNthInstance;
				dayOfWeekMask	= yearNthDayMask;
				monthOfYear		= yearNthMonthOfYear;
			}
		}

		// set the patterns attributes
		pattern.setPattern(
			recurType,
			dayOfMonth,
			dayOfWeekMask,
			instance,
			interval,
			monthOfYear);

		return pattern;
	}

	/**
	 * @return Returns the Recurrence Type
	 */
	public RecurrenceType getRecurrenceType() {
		return type;
	}

	/**
	 * @return Returns the Recurrence Type as a String
	 */
	public String getType() {
		return type.toString();
	}

	/**
	 * @param type
	 *            the recurrence type
	 */
	public void setType(String type) {
		this.type = RecurrenceType.parse(type);
	}

	/**
	 * Sets the Mask for one specific week day
	 * 
	 * @param day
	 *            the day index
	 * @param value
	 *            <code>true</code> if the bit for the given day is to be set
	 */
	public void setWeekDayMask(int day, boolean value) {
		weekDayMask |= (1 << day);
	}

	/**
	 * Retrieves the Mask for one specific week day
	 * 
	 * @param day
	 *            the day index
	 * @return <code>true</code> if the bit for the given day is set in the
	 *         week day mask
	 */
	public boolean getWeekDayMask(int day) {
		return ((weekDayMask & (1 << day)) > 0);
	}

	/**
	 * @return Returns the day mask
	 */
	public int getDayDayMask() {
		return dayDayMask;
	}

	/**
	 * @return Returns the interval for dayly recurrences
	 */
	public int getDayInterval() {
		return dayInterval;
	}

	/**
	 * @return Returns the day of month in Monthly reccurrences
	 */
	public int getMonthDayOfMonth() {
		return monthDayOfMonth;
	}

	/**
	 * @return Returns the interval for monthly recurrences
	 */
	public int getMonthInterval() {
		return monthInterval;
	}

	/**
	 * @return Returns the Nthday mask for monthly recurrences
	 */
	public int getMonthNthDayMask() {
		return monthNthDayMask;
	}

	/**
	 * @return monthNthInstance
	 */
	public int getMonthNthInstance() {
		return monthNthInstance;
	}

	/**
	 * @return monthNthInterval
	 */
	public int getMonthNthInterval() {
		return monthNthInterval;
	}

	/**
	 * @return monthSubtype
	 */
	public int getMonthSubtype() {
		return monthSubtype;
	}

	/**
	 * @return weekInterval
	 */
	public int getWeekInterval() {
		return weekInterval;
	}

	/**
	 * @return yearDayOfMonth
	 */
	public int getYearDayOfMonth() {
		return yearDayOfMonth;
	}

	/**
	 * @return yearMonthOfYear
	 */
	public int getYearMonthOfYear() {
		return yearMonthOfYear;
	}

	/**
	 * @return yearNthDayMask
	 */
	public int getYearNthDayMask() {
		return yearNthDayMask;
	}

	/**
	 * @return yearNthInstance
	 */
	public int getYearNthInstance() {
		return yearNthInstance;
	}

	/**
	 * @return yearNthMonthOfYear
	 */
	public int getYearNthMonthOfYear() {
		return yearNthMonthOfYear;
	}

	/**
	 * @return yearSubtype
	 */
	public int getYearSubtype() {
		return yearSubtype;
	}

	/**
	 * @param i
	 */
	public void setDayDayMask(int i) {
		this.dayDayMask = i;
	}

	/**
	 * @param i
	 */
	public void setDayInterval(int i) {
		this.dayInterval = i;
	}

	/**
	 * @param i
	 */
	public void setMonthDayOfMonth(int i) {
		this.monthDayOfMonth = i;
	}

	/**
	 * @param i
	 */
	public void setMonthInterval(int i) {
		this.monthInterval = i;
	}

	/**
	 * @param i
	 */
	public void setMonthNthDayMask(int i) {
		this.monthNthDayMask = i;
	}

	/**
	 * @param i
	 */
	public void setMonthNthInstance(int i) {
		this.monthNthInstance = i;
	}

	/**
	 * @param i
	 */
	public void setMonthNthInterval(int i) {
		this.monthNthInterval = i;
	}

	/**
	 * @param i
	 */
	public void setMonthSubtype(int i) {
		this.monthSubtype = i;
	}

	/**
	 * @param i
	 */
	public void setWeekInterval(int i) {
		this.weekInterval = i;
	}

	/**
	 * @param i
	 */
	public void setYearDayOfMonth(int i) {
		this.yearDayOfMonth = i;
	}

	/**
	 * @param i
	 */
	public void setYearMonthOfYear(int i) {
		this.yearMonthOfYear = i;
	}

	/**
	 * @param i
	 */
	public void setYearNthDayMask(int i) {
		this.yearNthDayMask = i;
	}

	/**
	 * @param i
	 */
	public void setYearNthInstance(int i) {
		this.yearNthInstance = i;
	}

	/**
	 * @param i
	 */
	public void setYearNthMonthOfYear(int i) {
		this.yearNthMonthOfYear = i;
	}

	/**
	 * @param i
	 */
	public void setYearSubtype(int i) {
		this.yearSubtype = i;
	}
}