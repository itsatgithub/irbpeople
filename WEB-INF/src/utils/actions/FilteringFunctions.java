package utils.actions;

import utils.listFilter.ViewListConfiguration;

public class FilteringFunctions {
	
	
	
	
	public static ViewListConfiguration addPersonalStateFilter(ViewListConfiguration filter, String statecode){
		filter.addFilter("state.personalstatecode"+ViewListConfiguration.STRING_SUFIX, statecode);
		return filter;
	}

}
