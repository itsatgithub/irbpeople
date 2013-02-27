package utils.formbeans;

import java.util.Comparator;

public class ToStringComparator implements Comparator<Object>{

	public int compare(Object o1, Object o2) {
		return o1.toString().compareToIgnoreCase(o2.toString());
	}

}
