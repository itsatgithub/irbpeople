package com.cc.framework.adapter.struts.utils;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServletWrapper;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.upload.MultipartRequestHandler;
import org.apache.struts.upload.MultipartRequestWrapper;
import org.apache.struts.util.ModuleUtils;
import org.apache.struts.util.RequestUtils;

import com.cc.framework.adapter.struts.ActionContext;
import com.cc.framework.convert.Converter;
import com.cc.framework.convert.ConverterException;
import com.cc.framework.convert.ConverterHelp;
import com.cc.framework.util.PropertyMap;

public class FWRequestUtils extends RequestUtils {

	/**
	 * <p>
	 * Populate the properties of the specified JavaBean from the specified HTTP
	 * request, based on matching each parameter name (plus an optional prefix
	 * and/or suffix) against the corresponding JavaBeans "property setter"
	 * methods in the bean's class. Suitable conversion is done for argument
	 * types as described under <code>setProperties</code>.
	 * </p>
	 * 
	 * <p>
	 * If you specify a non-null <code>prefix</code> and a non-null
	 * <code>suffix</code>, the parameter name must match <strong>both</strong>
	 * conditions for its value(s) to be used in populating bean properties. If
	 * the request's content type is "multipart/form-data" and the method is
	 * "POST", the <code>HttpServletRequest</code> object will be wrapped in a
	 * <code>MultipartRequestWrapper</code object.</p>
	 *
	 * @param bean The JavaBean whose properties are to be set
	 * @param prefix The prefix (if any) to be prepend to bean property
	 *               names when looking for matching parameters
	 * @param suffix The suffix (if any) to be appended to bean property
	 *               names when looking for matching parameters
	 * @param request The HTTP request whose parameters are to be used
	 *                to populate bean properties
	 *
	 * @exception ServletException if an exception is thrown while setting
	 *            property values
	 */
	public static void populate(ActionContext ctx, String prefix, String suffix) throws ServletException {

		// Build a list of relevant request parameters from this request
		HashMap properties = new HashMap();

		// Iterator of parameter names
		Enumeration names = null;

		// Map for multipart parameters
		Map multipartParameters = null;

		Object bean = ctx.form();
		String contentType = ctx.request().getContentType();
		String method = ctx.request().getMethod();
		boolean isMultipart = false;

		if ((contentType != null)
				&& (contentType.startsWith("multipart/form-data"))
				&& (method.equalsIgnoreCase("POST"))) {

			// Get the ActionServletWrapper from the form bean
			ActionServletWrapper servlet;
			if (bean instanceof ActionForm) {
				servlet = ((ActionForm) bean).getServletWrapper();
			} else {
				throw new ServletException("bean that's supposed to be "
						+ "populated from a multipart request is not of type "
						+ "\"org.apache.struts.action.ActionForm\", but type "
						+ "\"" + bean.getClass().getName() + "\"");
			}

			// Obtain a MultipartRequestHandler
			MultipartRequestHandler multipartHandler = getMultipartHandler(ctx.request());

			// Set the multipart request handler for our ActionForm.
			// If the bean isn't an ActionForm, an exception would have been
			// thrown earlier, so it's safe to assume that our bean is
			// in fact an ActionForm.
			((ActionForm) bean).setMultipartRequestHandler(multipartHandler);

			if (multipartHandler != null) {
				isMultipart = true;
				// Set servlet and mapping info
				servlet.setServletFor(multipartHandler);
				multipartHandler.setMapping((ActionMapping) ctx.request()
						.getAttribute(Globals.MAPPING_KEY));
				// Initialize multipart request class handler
				multipartHandler.handleRequest(ctx.request());
				// stop here if the maximum length has been exceeded
				Boolean maxLengthExceeded = (Boolean) ctx.request()
						.getAttribute(MultipartRequestHandler.ATTRIBUTE_MAX_LENGTH_EXCEEDED);
				if ((maxLengthExceeded != null)
						&& (maxLengthExceeded.booleanValue())) {
					return;
				}
				// retrieve form values and put into properties
				multipartParameters = getAllParametersForMultipartRequest(
						ctx.request(), multipartHandler);
				names = Collections.enumeration(multipartParameters.keySet());
			}
		}

		if (!isMultipart) {
			names = ctx.request().getParameterNames();
		}

		Map converters = new Hashtable();
		
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			String stripped = name;
			if (prefix != null) {
				if (!stripped.startsWith(prefix)) {
					continue;
				}
				stripped = stripped.substring(prefix.length());
			}
			if (suffix != null) {
				if (!stripped.endsWith(suffix)) {
					continue;
				}
				stripped = stripped.substring(0, stripped.length()
						- suffix.length());
			}
			Object parameterValue = null;
			if (isMultipart) {
				parameterValue = multipartParameters.get(name);
			} else {
				parameterValue = ctx.request().getParameterValues(name);
			}

			// Populate parameters, except "standard" struts and
			// common controls attributes
			// such as 'org.apache.struts.action.CANCEL'
			// or 'com.cc.framewokr.cvt'
			if (stripped.equals(com.cc.framework.Globals.CONVERTER_PARAM)) {
				String[] parameterValues;
				
				if (parameterValue.getClass().isArray()) {
					parameterValues = (String[]) parameterValue;
				} else {
					parameterValues = new String[] {(String) parameterValue};
				}
				
				for (int i = 0; i < parameterValues.length; i++) {
					PropertyMap map = PropertyMap.parse(parameterValues[i].toString());
					
					try {
						converters.put(map.getProperty("name"), createConverter(map));
					} catch (ConverterException ce) {
						throw new ServletException(ce);
					}
				}
			} else if (!stripped.startsWith("org.apache.struts.")
					&& !stripped.startsWith("com.cc.framework.")) {
				properties.put(stripped, parameterValue);
			}
		}

		// Set the corresponding properties of our bean
		try {
// TODO: alternative: converter irgendwo registrieren (Thread Context?)
			FWBeanUtils.populate(ctx, bean, properties, converters);
		} catch (Exception e) {
			throw new ServletException("FWRequestUtils.populate", e);
		}

	}

	private static Converter createConverter(PropertyMap map) throws ConverterException {
		String c = map.getProperty("cvt");
		
		return ConverterHelp.getInstance(c);
	}
	
	
	/**
	 * <b>Unmodified COPY FROM RequestUtils!</b>
	 * <p>
	 * Try to locate a multipart request handler for this request. First, look
	 * for a mapping-specific handler stored for us under an attribute. If one
	 * is not present, use the global multipart handler, if there is one.
	 * </p>
	 * 
	 * @param request
	 *            The HTTP request for which the multipart handler should be
	 *            found.
	 * @return the multipart handler to use, or null if none is found.
	 * 
	 * @exception ServletException
	 *                if any exception is thrown while attempting to locate the
	 *                multipart handler.
	 */
	private static MultipartRequestHandler getMultipartHandler(
			HttpServletRequest request) throws ServletException {

		MultipartRequestHandler multipartHandler = null;
		String multipartClass = (String) request
				.getAttribute(Globals.MULTIPART_KEY);
		request.removeAttribute(Globals.MULTIPART_KEY);

		// Try to initialize the mapping specific request handler
		if (multipartClass != null) {
			try {
				multipartHandler = (MultipartRequestHandler) applicationInstance(multipartClass);
			} catch (ClassNotFoundException cnfe) {
				log.error("MultipartRequestHandler class \"" + multipartClass
						+ "\" in mapping class not found, "
						+ "defaulting to global multipart class");
			} catch (InstantiationException ie) {
				log.error("InstantiationException when instantiating "
						+ "MultipartRequestHandler \"" + multipartClass
						+ "\", "
						+ "defaulting to global multipart class, exception: "
						+ ie.getMessage());
			} catch (IllegalAccessException iae) {
				log.error("IllegalAccessException when instantiating "
						+ "MultipartRequestHandler \"" + multipartClass
						+ "\", "
						+ "defaulting to global multipart class, exception: "
						+ iae.getMessage());
			}

			if (multipartHandler != null) {
				return multipartHandler;
			}
		}

		ModuleConfig moduleConfig = ModuleUtils.getInstance().getModuleConfig(
				request);

		multipartClass = moduleConfig.getControllerConfig().getMultipartClass();

		// Try to initialize the global request handler
		if (multipartClass != null) {
			try {
				multipartHandler = (MultipartRequestHandler) applicationInstance(multipartClass);

			} catch (ClassNotFoundException cnfe) {
				throw new ServletException("Cannot find multipart class \""
						+ multipartClass + "\"" + ", exception: "
						+ cnfe.getMessage());

			} catch (InstantiationException ie) {
				throw new ServletException(
						"InstantiationException when instantiating "
								+ "multipart class \"" + multipartClass
								+ "\", exception: " + ie.getMessage());

			} catch (IllegalAccessException iae) {
				throw new ServletException(
						"IllegalAccessException when instantiating "
								+ "multipart class \"" + multipartClass
								+ "\", exception: " + iae.getMessage());
			}

			if (multipartHandler != null) {
				return multipartHandler;
			}
		}

		return multipartHandler;
	}

	/**
	 * <b>Unmodified COPY FROM RequestUtils!</b>
	 * <p>
	 * Create a <code>Map</code> containing all of the parameters supplied for
	 * a multipart request, keyed by parameter name. In addition to text and
	 * file elements from the multipart body, query string parameters are
	 * included as well.
	 * </p>
	 * 
	 * @param request
	 *            The (wrapped) HTTP request whose parameters are to be added to
	 *            the map.
	 * @param multipartHandler
	 *            The multipart handler used to parse the request.
	 * 
	 * @return the map containing all parameters for this multipart request.
	 */
	private static Map getAllParametersForMultipartRequest(
			HttpServletRequest request, MultipartRequestHandler multipartHandler) {

		Map parameters = new HashMap();
		Hashtable elements = multipartHandler.getAllElements();
		Enumeration e = elements.keys();
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			parameters.put(key, elements.get(key));
		}

		if (request instanceof MultipartRequestWrapper) {
			request = ((MultipartRequestWrapper) request).getRequest();
			e = request.getParameterNames();
			while (e.hasMoreElements()) {
				String key = (String) e.nextElement();
				parameters.put(key, request.getParameterValues(key));
			}
		} else {
			log.debug("Gathering multipart parameters for unwrapped request");
		}

		return parameters;
	}
}