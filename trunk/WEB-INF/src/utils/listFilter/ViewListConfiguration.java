package utils.listFilter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.LazyDynaBean;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import utils.beanUtils.ExtendedBeanUtils;
import utils.beanUtils.converters.StringConverter;
import utils.filter.ListConfigurator;
import utils.userUtils.UserUtils;
import utils.validator.LocaleFieldChecks;

/**
 * This class is a nested-form-bean which contains the information of configurator of a list (filter, pagination and orther).
 * 
 * @author Automatika - JustInMind SL
 */
public class ViewListConfiguration extends ActionForm {

	/** Sufix for string values **/
	public static final String STRING_SUFIX = "_string";
	/** Sufix for date values **/
	public static final String DATE_SUFIX = "_date";
	/** Sufix for integer values **/
	public static final String INTEGER_SUFIX = "_integer";
	/** Sufix for bigDecimal values **/
	public static final String BIG_DECIMAL = "_bigdecimal";
	/** Sufix for boolean values **/
	public static final String BOOLEAN = "_boolean";
	/** Sufix for 'end' values. Indicates that is the second value (max value) for the filter. **/
	public static final String END_VALUE_SUFIX = "_end";
	/** Sufix for 'ini' values. Indicates that is the first value (min/eq value) for the filter. **/
	public static final String INI_VALUE_SUFIX = "_ini";
	/** Sufix for the comparation type value **/
	public static final String COMPARE_TYPE_SUFIX = "_compareType";
	/** Indicates that the comparation is a between **/
	public static final String BETWEEN = "between";

	/**
	 * Contains the information of the filter: the name of the attribute and its value
	 */
	LazyDynaBean filter = new LazyDynaBean();

	/**
	 * contains the information of the pagination
	 */
	Pagination pagination = new Pagination();

	/**
	 * contains the information of how to order a list.
	 */
	OrderBy orderby = new OrderBy();

	//	 List<LabelValueBean> filter=new ArrayList<LabelValueBean>();
	// Object sortAttribute=null;

	/**
	 * Adds a property to the filter configurator.
	 * @param name name of the property
	 * @param value value fo the property
	 */
	public void addFilter(String name, Object value) {
		filter.set(name, value);
	}

	/**
	 * Obtains a property of the filter configurator
	 * @param name name of the property
	 * @return value of the property
	 */
	public Object getFilterValue(String name) {
		return filter.get(name);
	}

	/**
	 * Retruns the form-bean of the filter list configurator
	 * @return the LazyDynaBean of the filter
	 */
	public LazyDynaBean getList_config_filter() {
		return filter;
	}

	/**
	 * Sets the form-bean of the filter list configurator
	 * @param filter
	 */
	public void setList_config_filter(LazyDynaBean filter) {
		this.filter = filter;
	}

	/**
	 * @return returns the Pagination data
	 */
	public Pagination getList_config_pagination() {
		return pagination;
	}

	/**
	 * Setts the pagination data
	 * @param pagination pagination data to set
	 */
	public void setList_config_pagination(Pagination pagination) {
		this.pagination = pagination;
	}

	/**
	 * Validated the filter data. To do so, each property of the filter must finish with a sufix indicating its type. Different sufixes are defined in this class.
	 * @param mapping The mapping used to select this instance
	 * @param request The servlet request we are processing
	 * @return an <code>ActionErrors</code> object that encapsulates any validation errors that have been found.
	 */
	@Override
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = super.validate(mapping, request);

		ActionMessages messages = new ActionMessages();

		if (filter.getMap().isEmpty())
			return null;

		Locale locale = UserUtils.getCurrentLocale(request);

		//validate filter
		Set<Entry<String, String>> filterValues = filter.getMap().entrySet();
		for (Iterator<Entry<String, String>> i = filterValues.iterator(); i.hasNext();) {
			Entry<String, String> property = i.next();
			String key = property.getKey();
			String attributeClassSufix = getFilterAttributeClassName(key);
			String attributeName = getFilterAttributeName(key);
			String value = property.getValue();

			if (attributeName.endsWith("_compareType"))
				continue;

			if (attributeClassSufix.equalsIgnoreCase(STRING_SUFIX)) {

			}
			if (attributeClassSufix.equalsIgnoreCase(DATE_SUFIX)) {
				if (!LocaleFieldChecks.validateDate(value, locale)) {
					messages.add("errors.date", new ActionMessage("errors.date", attributeName));
				}
			}
			if (attributeClassSufix.equalsIgnoreCase(INTEGER_SUFIX)) {
				if (!LocaleFieldChecks.validateInteger(value, locale)) {
					messages.add("errors.integer", new ActionMessage("errors.integer", attributeName));
				}
			}
			if (attributeClassSufix.equalsIgnoreCase(BIG_DECIMAL)) {
				if (!LocaleFieldChecks.validateBigDecimal(value, locale)) {
					messages.add("errors.float", new ActionMessage("errors.float", attributeName));
				}
			}
		}

		if (!messages.isEmpty()) {
			if (errors == null) {
				errors = new ActionErrors();
			}
			errors.add(messages);
		}

		return errors;
	}

	/**
	 * Obtains a {@link utils.filter.ListConfigurator} from the current viewListConfigurator filter.
	 * @param locale current locale (used for number/date conversions)
	 * @param isPaginated true if the ListConfigurator should include pagination.
	 * @return <code>ListConfigurator</code> obtained
	 */
	public ListConfigurator obtainListConfigurator(Locale locale, boolean isPaginated) {
		ListConfigurator result = new ListConfigurator();

		Set<Entry<String, String>> filterValues = filter.getMap().entrySet();

		// conversion
		for (Iterator<Entry<String, String>> i = filterValues.iterator(); i.hasNext();) {
			Entry<String, String> property = i.next();

			String key = property.getKey();
			String remplasedKey = key.replace('/', '.');
			Class attributeClass = getFilterAttributeClass(remplasedKey);
			String attributeName = getFilterAttributeName(remplasedKey);

			Object value = ExtendedBeanUtils.convertSimpleValueTo(property.getValue(), attributeClass, locale);

			if (value == null)
				continue;

			String comparizonType = null;

			if (String.class.isInstance(value)) {
				comparizonType = ListConfigurator.LIKE;
				result.setFilter(attributeName, value, comparizonType);
			}

			else if (Date.class.isInstance(value)) {
				if (attributeName.endsWith(INI_VALUE_SUFIX)) {
					attributeName = eraseSufix(attributeName, INI_VALUE_SUFIX);
					comparizonType = ListConfigurator.GREATER_EQUAL;
				} else if (attributeName.endsWith(END_VALUE_SUFIX)) {
					attributeName = eraseSufix(attributeName, END_VALUE_SUFIX);
					comparizonType = ListConfigurator.LOWER_EQUAL;
				}
				result.setFilter(attributeName, value, comparizonType);
			} else if (Boolean.class.isInstance(value)) {
				comparizonType = ListConfigurator.EQUAL;
				result.setFilter(attributeName, value, comparizonType);
			} else { // numero
				String currentInfo = attributeName.substring(attributeName.lastIndexOf('_'));

				if (!currentInfo.equalsIgnoreCase(COMPARE_TYPE_SUFIX)) {
					attributeName = attributeName.substring(0, attributeName.lastIndexOf('_'));

					String compareTypeName = key.replace(INI_VALUE_SUFIX, COMPARE_TYPE_SUFIX);
					compareTypeName = compareTypeName.replace(END_VALUE_SUFIX, COMPARE_TYPE_SUFIX);
					String compareValue = (String) filter.getMap().get(compareTypeName);

					if (currentInfo.equalsIgnoreCase(INI_VALUE_SUFIX)) {
						if (compareValue.equalsIgnoreCase(BETWEEN)) {
							result.setFilter(attributeName, value, ListConfigurator.GREATER_EQUAL);
						} else {
							result.setFilter(attributeName, value, compareValue);
						}
					}

					if (currentInfo.equalsIgnoreCase(END_VALUE_SUFIX)) {
						if (compareValue.equalsIgnoreCase(BETWEEN)) {
							result.setFilter(attributeName, value, ListConfigurator.LOWER_EQUAL);
						}
					}
				}
			}

		}

		if (isPaginated) {
			if (pagination.getMaxResults() != null && !pagination.getMaxResults().equals("")) {
				result.setMaxResults(Integer.parseInt(pagination.getMaxResults()));
			}

			if (pagination.getFirstEntry() != null && !pagination.getFirstEntry().equals("")) {
				result.setFirstEntry(Integer.parseInt(pagination.getFirstEntry()));
			}
		}

		if (orderby.getAsc() != null && !orderby.getAsc().equals("")) {
			result.setAsc(orderby.getAsc());
		}

		if (orderby.getAttribute() != null && !orderby.getAttribute().equals("")) {
			result.setOrderBy(orderby.getAttribute());
		}

		return result;
	}

	/**
	 * Obtains a {@link utils.filter.ListConfigurator} from the current viewListConfigurator filter.
	 * @param locale current locale (used for number/date conversions)
	 * @param isPaginated true if the ListConfigurator should include pagination.
	 * @return <code>ListConfigurator</code> obtained
	 */
	public ListConfigurator obtainListConfiguratorForViews(Locale locale) {
		ListConfigurator result = new ListConfigurator();

		Set<Entry<String, String>> filterValues = filter.getMap().entrySet();

		// conversion
		for (Iterator<Entry<String, String>> i = filterValues.iterator(); i.hasNext();) {
			Entry<String, String> property = i.next();

			String key = property.getKey();
			String remplasedKey = key.replace('/', '.');
			Class attributeClass = getFilterAttributeClass(remplasedKey);
			String attributeName = getFilterAttributeName(remplasedKey);

			Object value = ExtendedBeanUtils.convertSimpleValueTo(property.getValue(), attributeClass, locale);

			if (value == null)
				continue;

			String comparizonType = null;

			if (String.class.isInstance(value)) {

				String currentInfo = "";
				if (attributeName.contains("_")) {
					currentInfo = attributeName.substring(attributeName.lastIndexOf('_'));
				}

				if (!currentInfo.equalsIgnoreCase(COMPARE_TYPE_SUFIX)) {

					//attributeName=attributeName.substring(0,attributeName.lastIndexOf('_'));

					String compareTypeName = key.replace(STRING_SUFIX, COMPARE_TYPE_SUFIX + STRING_SUFIX);

					String compareValue = (String) filter.getMap().get(compareTypeName);

					result.setFilter(attributeName, value, compareValue);

				}

			}

			else if (Date.class.isInstance(value)) {

				String currentInfo = attributeName.substring(attributeName.lastIndexOf('_'));

				if (!currentInfo.equalsIgnoreCase(COMPARE_TYPE_SUFIX)) {
					attributeName = attributeName.substring(0, attributeName.lastIndexOf('_'));

					String compareTypeName;

					if (key.startsWith(attributeName)) {

						String suffix = key.substring(attributeName.length());

						compareTypeName = suffix.replace(INI_VALUE_SUFIX, COMPARE_TYPE_SUFIX);
						compareTypeName = compareTypeName.replace(END_VALUE_SUFIX, COMPARE_TYPE_SUFIX);

						compareTypeName = attributeName + compareTypeName;
					} else {
						compareTypeName = key.replace(INI_VALUE_SUFIX, COMPARE_TYPE_SUFIX);
						compareTypeName = compareTypeName.replace(END_VALUE_SUFIX, COMPARE_TYPE_SUFIX);
					}

					String compareValue = (String) filter.getMap().get(compareTypeName);

					if (currentInfo.equalsIgnoreCase(INI_VALUE_SUFIX)) {
						if (compareValue.equalsIgnoreCase(BETWEEN)) {
							result.setFilter(attributeName, value, ListConfigurator.GREATER_EQUAL);
						} else {
							result.setFilter(attributeName, value, compareValue);
						}
					}

					if (currentInfo.equalsIgnoreCase(END_VALUE_SUFIX)) {
						if (compareValue.equalsIgnoreCase(BETWEEN)) {
							result.setFilter(attributeName, value, ListConfigurator.LOWER_EQUAL);
						}
					}
				}

			} else if (Boolean.class.isInstance(value)) {
				comparizonType = ListConfigurator.EQUAL;
				result.setFilter(attributeName, value, comparizonType);
			} else { // numero
				String currentInfo = attributeName.substring(attributeName.lastIndexOf('_'));

				if (!currentInfo.equalsIgnoreCase(COMPARE_TYPE_SUFIX)) {
					attributeName = attributeName.substring(0, attributeName.lastIndexOf('_'));

					String compareTypeName = key.replace(INI_VALUE_SUFIX, COMPARE_TYPE_SUFIX);
					compareTypeName = compareTypeName.replace(END_VALUE_SUFIX, COMPARE_TYPE_SUFIX);
					String compareValue = (String) filter.getMap().get(compareTypeName);

					if (currentInfo.equalsIgnoreCase(INI_VALUE_SUFIX)) {
						if (compareValue.equalsIgnoreCase(BETWEEN)) {
							result.setFilter(attributeName, value, ListConfigurator.GREATER_EQUAL);
						} else {
							result.setFilter(attributeName, value, compareValue);
						}
					}

					if (currentInfo.equalsIgnoreCase(END_VALUE_SUFIX)) {
						if (compareValue.equalsIgnoreCase(BETWEEN)) {
							result.setFilter(attributeName, value, ListConfigurator.LOWER_EQUAL);
						}
					}
				}
			}

		}

		return result;
	}

	public List<String[]> obtainListConfiguratorForViewsToSave(Locale locale) {

		List<String[]> result = new ArrayList();

		Set<Entry<String, String>> filterValues = filter.getMap().entrySet();

		// conversion
		for (Iterator<Entry<String, String>> i = filterValues.iterator(); i.hasNext();) {
			Entry<String, String> property = i.next();

			String key = property.getKey();
			String remplasedKey = key.replace('/', '.');
			Class attributeClass = getFilterAttributeClass(remplasedKey);
			String attributeName = getFilterAttributeName(remplasedKey);

			Locale eng = new Locale("en");
			StringConverter sc = new StringConverter(eng);

			Object value = ExtendedBeanUtils.convertSimpleValueTo(property.getValue(), attributeClass, locale);

			if (value == null)
				continue;

			String comparizonType = null;

			if (String.class.isInstance(value)) {

				String currentInfo = "";
				if (attributeName.contains("_")) {
					currentInfo = attributeName.substring(attributeName.lastIndexOf('_'));
				}

				if (!currentInfo.equalsIgnoreCase(COMPARE_TYPE_SUFIX)) {

					//attributeName=attributeName.substring(0,attributeName.lastIndexOf('_'));

					String compareTypeName = key.replace(STRING_SUFIX, COMPARE_TYPE_SUFIX + STRING_SUFIX);

					String compareValue = (String) filter.getMap().get(compareTypeName);

					result.add(new String[] { attributeName, (String) value, compareValue, "String" });

				}

			}

			else if (Date.class.isInstance(value)) {

				String currentInfo = attributeName.substring(attributeName.lastIndexOf('_'));

				if (!currentInfo.equalsIgnoreCase(COMPARE_TYPE_SUFIX)) {
					attributeName = attributeName.substring(0, attributeName.lastIndexOf('_'));

					String compareTypeName = key.replace(INI_VALUE_SUFIX, COMPARE_TYPE_SUFIX);
					compareTypeName = compareTypeName.replace(END_VALUE_SUFIX, COMPARE_TYPE_SUFIX);
					String compareValue = (String) filter.getMap().get(compareTypeName);

					if (currentInfo.equalsIgnoreCase(INI_VALUE_SUFIX)) {
						if (compareValue.equalsIgnoreCase(BETWEEN)) {
							result.add(new String[] { attributeName, (String) sc.convert(String.class, value), ListConfigurator.GREATER_EQUAL, "Date" });
						} else {
							result.add(new String[] { attributeName, (String) sc.convert(String.class, value), compareValue, "Date" });
						}
					}

					if (currentInfo.equalsIgnoreCase(END_VALUE_SUFIX)) {
						if (compareValue.equalsIgnoreCase(BETWEEN)) {
							result.add(new String[] { attributeName, (String) sc.convert(String.class, value), ListConfigurator.LOWER_EQUAL, "Date" });
						}
					}
				}

			} else if (Boolean.class.isInstance(value)) {
				comparizonType = ListConfigurator.EQUAL;
				result.add(new String[] { attributeName, (String) sc.convert(String.class, value), comparizonType, "Boolean" });
			} else { // numero
				String currentInfo = attributeName.substring(attributeName.lastIndexOf('_'));
				String classtype = null;
				if (BigDecimal.class.isInstance(value)) {
					classtype = "BigDecimal";
				} else {
					classtype = "Integer";
				}

				if (!currentInfo.equalsIgnoreCase(COMPARE_TYPE_SUFIX)) {
					attributeName = attributeName.substring(0, attributeName.lastIndexOf('_'));

					String compareTypeName = key.replace(INI_VALUE_SUFIX, COMPARE_TYPE_SUFIX);
					compareTypeName = compareTypeName.replace(END_VALUE_SUFIX, COMPARE_TYPE_SUFIX);
					String compareValue = (String) filter.getMap().get(compareTypeName);

					if (currentInfo.equalsIgnoreCase(INI_VALUE_SUFIX)) {
						if (compareValue.equalsIgnoreCase(BETWEEN)) {
							result.add(new String[] { attributeName, value.toString(), ListConfigurator.GREATER_EQUAL, classtype });
						} else {
							result.add(new String[] { attributeName, value.toString(), compareValue, classtype });
						}
					}

					if (currentInfo.equalsIgnoreCase(END_VALUE_SUFIX)) {
						if (compareValue.equalsIgnoreCase(BETWEEN)) {

							result.add(new String[] { attributeName, value.toString(), ListConfigurator.LOWER_EQUAL, classtype });
						}
					}
				}
			}

		}

		return result;
	}

	/**
	 * This class contains the information of the pagination of a list
	 * @author Automatika - JustInMind SL
	 */
	public class Pagination {

		/**The first entry (index)that must be shown. If filled, it must be an integer*/
		String firstEntry;
		/**The number of results that must be shown. If filled, it must be an integer*/
		String maxResults;
		/**Indicates how many items has the current list. If filled, it must be an integer*/
		String listSize;

		/**
		 * @return returns the first entry (index)that must be shown. If filled, it must be an integer
		 */
		public String getFirstEntry() {
			return firstEntry;
		}

		/**
		 * Sets the first entry (index)that must be shown. If filled, it must be an integer
		 * @param firstEntry value to set
		 */
		public void setFirstEntry(String firstEntry) {
			this.firstEntry = firstEntry;
		}

		/**
		 * @return returns the number of results that must be shown. If filled, it must be an integer
		 */
		public String getMaxResults() {
			return maxResults;
		}

		/**
		 * Sets the number of resultsthat must be shown. If filled, it must be an integer
		 * @param maxResults value to set
		 */
		public void setMaxResults(String maxResults) {
			this.maxResults = maxResults;
		}

		/**
		 * @return returns how many items has the current list. If filled, it must be an integer
		 */
		public String getListSize() {
			return listSize;
		}

		/**
		 * Sets how many items has the current list. If filled, it must be an integer.
		 * @param listSize value to set
		 */
		public void setListSize(String listSize) {
			this.listSize = listSize;
		}
	}

	/**
	 * This class contains the information of how to sort a list
	 * @author Automatika - JustInMind SL
	 */
	public class OrderBy {

		/** indicates which is the attribute to sort with*/
		String attribute;
		String asc;

		/**
		 * @return returns if the list sholud be ordered decreasing/increasing.
		 */
		public String getAsc() {
			return asc;
		}

		public void setAsc(String asc) {
			this.asc = asc;
		}

		/**
		 * @return returns which is the attribute to sort with
		 */
		public String getAttribute() {
			return attribute;
		}

		/**
		 * Sets which is the attribute to sort with
		 * @param attribute name of the attribute
		 */
		public void setAttribute(String attribute) {
			this.attribute = attribute;
		}
	}

	/**
	 * @return returns the orderby data
	 */
	public OrderBy getList_config_orderby() {
		return orderby;
	}

	/**
	 * sets the orderby data
	 * @param orderby data to set
	 */
	public void setList_config_orderby(OrderBy orderby) {
		this.orderby = orderby;
	}

	/**
	 * Given a name with the type sufix it returns the sufix.
	 * @param attribute name with sufix
	 * @return sufix
	 */
	private static String getFilterAttributeClassName(String attribute) {
		int index = attribute.lastIndexOf('_');
		String type = attribute.substring(index);
		return type;
	}

	/**
	 * Given a name with the type sufix it returns the class of the attribute
	 * @param attribute attribute name with sufix
	 * @return class of the attribute according to the sufix
	 */
	private Class getFilterAttributeClass(String attribute) {
		String type = getFilterAttributeClassName(attribute);

		if (type.equalsIgnoreCase(STRING_SUFIX)) {
			return String.class;
		}
		if (type.equalsIgnoreCase(DATE_SUFIX)) {
			return Date.class;
		}
		if (type.equalsIgnoreCase(INTEGER_SUFIX)) {
			return Integer.class;
		}
		if (type.equalsIgnoreCase(BIG_DECIMAL)) {
			return BigDecimal.class;
		}
		if (type.equalsIgnoreCase(BOOLEAN)) {
			return Boolean.class;
		}
		return null;
	}

	/**
	 * Given a name with the type sufix it returns the 'real' name (without the sufix) of the attribute.
	 * @param attribute name with sufix
	 * @return attribute name without sufix
	 */
	private String getFilterAttributeName(String attribute) {
		int index = attribute.lastIndexOf('_');
		return attribute.substring(0, index);
	}

	/**
	 * Erases the type sufix of a String
	 * @param string original string
	 * @param sufix sufix to delete
	 * @return the modified string
	 */
	private String eraseSufix(String string, String sufix) {
		return string.substring(0, string.length() - sufix.length());
	}

}
