package utils.formbeans;

import java.util.Comparator;
import java.util.Date;

import bussineslogic.objects.Irbholiday;

public class IrbholidayComparator implements Comparator<Irbholiday> {

	public int compare(Irbholiday o1, Irbholiday o2) {
		
		Date dateo1 = o1.getInitialdate();
		Date dateo2 = o2.getInitialdate();
		
		if(dateo1.equals(dateo2)) return 0;
		else if(dateo1.after(dateo2)) return 1;
		else return -1;
	}

}
