package utils.excel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.text.MessageFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.Vector;

import org.apache.commons.digester.Digester;
import org.apache.struts.config.ConfigRuleSet;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.config.ModuleConfigFactory;
import org.apache.struts.config.PlugInConfig;
import org.xml.sax.InputSource;

import utils.Pair;
import utils.filter.ListConfigurator;
import utils.hibernate.HibernateUtil;
import bussineslogic.controlers.UseCase;
import bussineslogic.objects.Column;
import bussineslogic.objects.CustomList;
import bussineslogic.objects.Filter;
import bussineslogic.objects.OrderBy;
import bussineslogic.objects.Usuario;

import com.justinmind.helper.composition.common.types.TypesManager;
import com.justinmind.helper.composition.report.ReportComObject;
import com.justinmind.helper.composition.report.ReportComposition;
import com.justinmind.helper.composition.report.ReportCompositionFactory;
import com.justinmind.usermanagement.entity.Role;

public class CustomListExporter {

	private static ResourceBundle bundle = ResourceBundle.getBundle("MainConfiguration");

	private String contextPath;
	private String reportsPath = "WEB-INF/reportfields";
	private String typesPath = "WEB-INF/types";
	private String operatorsPath = "WEB-INF/operators";
	private String strutsConfigFile = "WEB-INF/struts-config.xml";

	private ReportComposition reportComposition;

	org.apache.log4j.Category log = org.apache.log4j.Logger.getLogger(CustomListExporter.class);

	public static void main(String[] args) throws Exception {
		CustomListExporter cle = new CustomListExporter();

		cle.initConfiguration();
		cle.start();

	}

	public void start() throws Exception {
		Usuario user = new Usuario();

		Role rol = new Role();

		rol.setEntitycode("irbpeople_ro");

		Set roles = new HashSet();

		roles.add(rol);

		user.setRoles(roles);

		List<CustomList> customLists = UseCase.ObtainAllCustomList(user);

		for (CustomList list : customLists) {
			if (list.getPeriodic()) {
				exportPeriodicList(list);
			}
		}

	}

	private void exportPeriodicList(CustomList list) throws Exception {
		String pathAndFile = bundle.getString("periodicListFilenameWithPath");

		Date today = new Date();

		String processedPathAndFile = MessageFormat.format(pathAndFile, list.getName(), today);

		FileOutputStream fout = new FileOutputStream(processedPathAndFile);

		exportToStream(list, fout);

	}

	public CustomListExporter() throws Exception {

		initConfiguration();

		HibernateUtil.getSession();

		initReportComposition();

	}

	private void initConfiguration() {

		this.contextPath = bundle.getString("contextPath");

		URL codeBasePath = CustomListExporter.class.getProtectionDomain().getCodeSource().getLocation();

		Thread.currentThread().setContextClassLoader(new CustomListExporterClassLoader(codeBasePath));

		ModuleConfigFactory factoryObject = ModuleConfigFactory.createFactory();
		ModuleConfig config = factoryObject.createModuleConfig("");

		Digester configDigester = new Digester();
		configDigester.setNamespaceAware(true);
		configDigester.setValidating(true);
		configDigester.setUseContextClassLoader(true);
		configDigester.addRuleSet(new ConfigRuleSet());

		configDigester.push(config);

		InputStream input = null;
		try {
			String slash = "";
			if (contextPath.charAt(1) == ':')
				slash = "/";

			URL url = new URL("file", slash, contextPath + strutsConfigFile);

			InputSource is = new InputSource(url.toExternalForm());
			input = url.openStream();
			is.setByteStream(input);
			configDigester.parse(is);

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (input != null) {
				try {
					input.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		PlugInConfig plugInConfigs[] = config.findPlugInConfigs();

		for (PlugInConfig plugIn : plugInConfigs) {
			if (plugIn.getClassName().contains("ListCompositionPlugIn")) {
				Map<?, ?> props = plugIn.getProperties();

				for (Object key : props.keySet()) {
					if (((String) key).equals("typesPath")) {
						this.typesPath = (String) props.get(key);
					} else if (((String) key).equals("operatorsPath")) {
						this.operatorsPath = (String) props.get(key);
					} else if (((String) key).equals("reportsPath")) {
						this.reportsPath = (String) props.get(key);
					}
				}
			}
		}

	}

	@SuppressWarnings("unchecked")
	public void exportToStream(CustomList customList, OutputStream out) throws ClassNotFoundException {
		String idioma_configurado = "en";
		try {
			idioma_configurado = bundle.getString("localeForPeriodicListings");
		}
		catch (MissingResourceException mre) {}

		Locale locale = new Locale(idioma_configurado);

		ListConfigurator list_config = null;
		String[] column_list = null;
		String[] orderby_list = null;

		String customListCode = customList.getCode();

		// obtenir els filtres
		// use case obtainallfrom per a filter, column i orderby i les seves facades
		//obtenir les columnes i orderbys inserir-les a List<String>

		List<Filter> filter_object_list = UseCase.ObtainAllFiltersFrom(null, customListCode);
		List<Column> column_object_list = UseCase.ObtainAllColumnsFrom(null, customListCode);
		List<OrderBy> orderby_object_list = UseCase.ObtainAllOrderBysFrom(null, customListCode);

		String viewName = customList.getView_name();
		list_config = UseCase.CreateListConfiguratorFromFilterList(filter_object_list, locale);
		column_list = UseCase.CreateListFromColumnList(column_object_list);
		orderby_list = UseCase.CreateListFromOrderByList(orderby_object_list);

		Pair<Integer, List<Object[]>> listView = UseCase.ObtainCustomListData(null, viewName, list_config, column_list, orderby_list, null);

		/**convert the objects list in a List<string[]> **/

		XLSManager xlsm = new XLSManager();

		//look up which view must be shown depending on which action 
		//calls the actionNewListing action

		//if its called by itself because we want to change the view:

		ReportComObject rco = reportComposition.getReport(viewName);

		// ******************** SELECT SECTION ***********************
		// ***********************************************************

		//we fill a hashtable with the (fieldname, label) pair
		//so we can easyly find which label corresponds to the name used
		//in the beans (the fieldname)
		Vector defaultSelectFieldNames = rco.getSelect().getVisibleFieldNames();
		Vector defaultSelectLabels = rco.getSelect().getVisibleLabels(idioma_configurado);
		Hashtable selectFields = new Hashtable();
		Iterator itNames = defaultSelectFieldNames.iterator();
		Iterator itLabels = defaultSelectLabels.iterator();
		while (itNames.hasNext()) {
			selectFields.put((String) itNames.next(), (String) itLabels.next());
		}

		//Vector column_names = new Vector();
		String[] column_labels = new String[column_list.length];

		//we lookup which fields must be shown in the right <select> (the
		//ones which are chosen to use in the saved report)
		String[] rightFields = column_list;

		//and we fill the Vectors containing the fieldnames and labels
		// of the right side <select> using the hashtable to find the labels
		itNames = defaultSelectFieldNames.iterator();
		itLabels = defaultSelectLabels.iterator();
		for (int i = 0; i < rightFields.length; i++) {

			column_labels[i] = (String) selectFields.get(rightFields[i]);
		}

		if (listView.getSecond() != null) {

			xlsm.createXLSNoBeans(listView.getSecond().toArray(), viewName, column_list, column_labels, out);
		}
	}

	public void initReportComposition() throws Exception {
		//Inicializaci�n del sistema de reports para el filtro.
		TypesManager typesManager = new TypesManager(contextPath + typesPath, contextPath + operatorsPath);

		ReportCompositionFactory rcf = new ReportCompositionFactory();

		ReportComposition rc = rcf.createReportComposition("irb_rrhh", contextPath + reportsPath, typesManager);

		this.reportComposition = rc;
	}
}
