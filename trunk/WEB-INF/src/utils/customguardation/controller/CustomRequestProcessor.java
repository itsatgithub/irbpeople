package utils.customguardation.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bussineslogic.objects.Usuario;

import org.apache.struts.Globals;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.InvalidCancelException;
import org.apache.struts.config.ForwardConfig;
import org.apache.struts.tiles.TilesRequestProcessor;

import com.justinmind.usermanagement.entity.Role;
import com.justinmind.util.siteorg.SiteOrg;
import com.justinmind.util.siteorg.SiteOrgManager;

import bussineslogic.controlers.UseCaseFacade;
import bussineslogic.excepciones.InternalException;
import bussineslogic.excepciones.NoPermisosException;
import bussineslogic.excepciones.ValidationFailedException;

import utils.actions.NavigationFunctions;
import utils.filter.ListConfigurator;
import utils.jsp.JspUtils;
import utils.linkprocessors.CommandActionLinkProcessor;
import utils.listFilter.ViewListConfiguration;
import utils.userUtils.UserUtils;

/**
 * Custom request processor.
 * 
 * @author Automatika - JustInMind SL
 */
public class CustomRequestProcessor extends TilesRequestProcessor {

	public static final String FIRST_ENTRY = "firstEntry";

	public static final String MAX_RESULTS = "maxResults";

	public static final String FILTER_NAME = "_filter.";

	public static final String PAGINATION_NAME = "_pagination.";

	public static final String ATT_OBTENTION_PHASE_ACTIVE = "att_obtention_phase_active";

	@Override
	protected void processContent(HttpServletRequest request, HttpServletResponse response) {
		super.processContent(request, response);
		request.setAttribute(ATT_OBTENTION_PHASE_ACTIVE, new Boolean(UseCaseFacade.isObtentionPhaseActive()));
	}

	public static final String ORDER_NAME = "_orderby.";

	public static final String LIST_CONFIG_NAME = "list_config";

	public static final String SUBMIT_CONFIGURATOR_NAME = ".submitConfigurator";

	/**
	 * Returns true only if there's a user in the session (the session is still
	 * active)
	 * 
	 * @param session
	 * @return
	 */
	protected boolean sessionActive(HttpServletRequest request) {
		Usuario usr = UserUtils.getCurrentUsuario(request);
		if (usr == null) {
			log.info("Invalid session, go to login!");
			return false;
		} else {
			log.debug("Session ok");
			return true;
		}
	}

	@Override
	protected boolean processRoles(HttpServletRequest request, HttpServletResponse response, ActionMapping mapping) throws IOException, ServletException {

		Usuario user = UserUtils.getCurrentUsuario(request);
		boolean isEnabled;

		try {
			isEnabled = isUserEnabled(user, mapping, request);
			if (isEnabled)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		super.doForward(UserUtils.LOGIN_ACTION_PATH, request, response);
		return false;

	}

	@Override
	protected void processPopulate(HttpServletRequest request, HttpServletResponse response, ActionForm form, ActionMapping mapping) throws ServletException {
		super.processPopulate(request, response, form, mapping);

		// populate the filter element
		ViewListConfiguration config = new ViewListConfiguration();

		Enumeration names = request.getParameterNames();
		String submitOk = request.getParameter(LIST_CONFIG_NAME + SUBMIT_CONFIGURATOR_NAME);

		if (config.getList_config_pagination().getMaxResults() == null || config.getList_config_pagination().getMaxResults().equals("")) {
			Usuario user = UserUtils.getCurrentUsuario(request);
			// TODO: coger el número de elementos que quiere visualizar el
			// usuario
			config.getList_config_pagination().setMaxResults("10");
		}
		if (config.getList_config_pagination().getFirstEntry() == null || config.getList_config_pagination().getFirstEntry().equals("")) {
			config.getList_config_pagination().setFirstEntry("0");
		}

		if (submitOk == null || !Boolean.parseBoolean(submitOk)) {
			request.setAttribute("viewListConfiguration", config);
			return;
		}

		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			String value = ((String) request.getParameter(name)).trim();

			// is a value for the list config
			if (name.startsWith(LIST_CONFIG_NAME) && !value.equals("")) {
				name = erasePrefix(name, LIST_CONFIG_NAME);

				// filter type
				if (name.startsWith(FILTER_NAME)) {
					String attribute = erasePrefix(name, FILTER_NAME);
					config.addFilter(attribute, value);
				}

				// pagination type
				if (name.startsWith(PAGINATION_NAME)) {
					String attribute = erasePrefix(name, PAGINATION_NAME);
					if (attribute.startsWith(FIRST_ENTRY)) {
						config.getList_config_pagination().setFirstEntry(value);
					} else if (attribute.startsWith(MAX_RESULTS)) {
						config.getList_config_pagination().setMaxResults(value);
					}
				}

				// orderBy
				if (name.startsWith(ORDER_NAME)) {
					String attribute = erasePrefix(name, ORDER_NAME);
					if (attribute.startsWith("attribute")) {
						config.getList_config_orderby().setAttribute(value);
					} else if (attribute.startsWith("asc")) {
						config.getList_config_orderby().setAsc(value);
					}
				}
			}

		}

		request.setAttribute("viewListConfiguration", config);
	}

	/**
	 * Modified copy of super.processValidate in orther to enable null input
	 */
	protected boolean super_processValidate(HttpServletRequest request, HttpServletResponse response, ActionForm form, ActionMapping mapping)
			throws IOException, ServletException, InvalidCancelException {

		if (form == null) {
			return (true);
		}

		// Has validation been turned off for this mapping?
		if (!mapping.getValidate()) {
			return (true);
		}

		// Was this request cancelled? If it has been, the mapping also
		// needs to state whether the cancellation is permissable; otherwise
		// the cancellation is considered to be a symptom of a programmer
		// error or a spoof.
		if (request.getAttribute(Globals.CANCEL_KEY) != null) {
			if (mapping.getCancellable()) {
				if (log.isDebugEnabled()) {
					log.debug(" Cancelled transaction, skipping validation");
				}
				return (true);
			} else {
				request.removeAttribute(Globals.CANCEL_KEY);
				throw new InvalidCancelException();
			}
		}

		// Call the form bean's validation method
		if (log.isDebugEnabled()) {
			log.debug(" Validating input form properties");
		}
		ActionMessages errors = form.validate(mapping, request);
		if ((errors == null) || errors.isEmpty()) {
			if (log.isTraceEnabled()) {
				log.trace("  No errors detected, accepting input");
			}
			return (true);
		}

		// Special handling for multipart request
		if (form.getMultipartRequestHandler() != null) {
			if (log.isTraceEnabled()) {
				log.trace("  Rolling back multipart request");
			}
			form.getMultipartRequestHandler().rollback();
		}

		// Was an input path (or forward) specified for this mapping?
		String input = mapping.getInput();

		// beggining of the added code
		if (input == null)
			input = NavigationFunctions.getInputPoint(request);
		// end of the added code

		if (input == null) {
			if (log.isTraceEnabled()) {
				log.trace("  Validation failed but no input form available");
			}
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, getInternal().getMessage("noInput", mapping.getPath()));
			return (false);
		}

		// Save our error messages and return to the input form if possible
		if (log.isDebugEnabled()) {
			log.debug(" Validation failed, returning to '" + input + "'");
		}
		request.setAttribute(Globals.ERROR_KEY, errors);

		if (NavigationFunctions.isPopUpWindow(request)) {
			input = NavigationFunctions.getPopUpActionName(input);
		}

		if (moduleConfig.getControllerConfig().getInputForward()) {
			ForwardConfig forward = mapping.findForward(input);
			processForwardConfig(request, response, forward);
		} else {
			internalModuleRelativeForward(input, request, response);
		}

		return (false);

	}

	@Override
	protected boolean processValidate(HttpServletRequest request, HttpServletResponse response, ActionForm form, ActionMapping mapping) throws IOException,
			ServletException, InvalidCancelException {
		// calling to a modified super in orther to enable null input
		boolean result = super_processValidate(request, response, form, mapping);

		ViewListConfiguration config = (ViewListConfiguration) request.getAttribute("viewListConfiguration");
		if (config != null) {
			ActionMessages errors = config.validate(mapping, request);

			if (!((errors == null) || errors.isEmpty())) {
				// error in the validation method

				ActionMessages oldErrors = (ActionMessages) request.getAttribute(Globals.ERROR_KEY);
				if (oldErrors != null)
					errors.add(oldErrors);

				request.setAttribute(Globals.ERROR_KEY, errors);
				// result=false;
			}
		}
		return result;

	}

	/**
	 * Operation that tests if a user is enabled (due to roles) to acces the
	 * current action
	 * 
	 * @param user
	 *            user that is using the application
	 * @param mapping
	 *            current action mapping
	 * @param request
	 *            current request
	 * @return true if the use has permisions to acces the action
	 * @throws Exception
	 */
	private boolean isUserEnabled(Usuario user, ActionMapping mapping, HttpServletRequest request) throws Exception {

		SiteOrg siteOrg = (SiteOrg) request.getSession().getAttribute("siteorg");

//		if (user == null && siteOrg == null) {
//			// Si no hay siteOrg tenemos que cargar uno con las acciones "por
//			// defecto"
//			siteOrg = SiteOrgManager.singleton().siteOrgFactoryUsingCache(SiteOrgManager.singleton().getRolesManager().getDefaultRoleName(),
//					new CommandActionLinkProcessor());
//			siteOrg.configAndCheck();
//			siteOrg.configureForThisUser(null);
//			request.getSession().setAttribute("siteorg", siteOrg);
//		}

		String commandName = NavigationFunctions.getCommandName(mapping);
		String actionName = NavigationFunctions.getActionName(mapping);
		String path = mapping.getPath();

		// TODO: la lista de acciones podría/debería estar definida
		// en el fichero de roles, en lugar de hacer esto.
		boolean defaultActions = (UserUtils.LOGIN_PATH.equals(path)) || (UserUtils.DO_LOGIN_PATH.equals(path)) || (UserUtils.DO_LOGOUT_PATH.equals(path))
				|| (UserUtils.ADD_USER_PATH.equals(path)) || (UserUtils.DO_ADD_USER_PATH.equals(path)) || commandName.equalsIgnoreCase("_popUp");

		boolean userAllowed = defaultActions || siteOrg.isAccessibleAction(commandName, actionName);

		if (!userAllowed) {
			ActionErrors errors = new ActionErrors();
			ActionMessages messages = new ActionMessages();
			messages.add("info.se-necesita-login", new ActionMessage("info.se-necesita-login"));
			errors.add(messages);
			request.setAttribute(Globals.ERROR_KEY, errors);
		}

		return userAllowed;
	}

	private String erasePrefix(String string, String prefix) {
		return string.substring(prefix.length());
	}

	private String eraseSufix(String string, String sufix) {
		return string.substring(0, string.length() - sufix.length());
	}

	/**
	 * Do a forward using request dispatcher. Uri is a valid uri. If response
	 * has already been commited, do an include instead.
	 * 
	 * @param uri
	 *            Uri or Definition name to forward.
	 * @param request
	 *            Current page request.
	 * @param response
	 *            Current page response.
	 */
	protected void doForward(String uri, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		super.doForward(uri, request, response);

	}

	/**
	 * <P>
	 * Ask the specified <code>Action</code> instance to handle this request.
	 * Return the <code>ActionForward</code> instance (if any) returned by the
	 * called <code>Action</code> for further processing.
	 * </P>
	 * 
	 * @param request
	 *            The servlet request we are processing
	 * @param response
	 *            The servlet response we are creating
	 * @param action
	 *            The Action instance to be used
	 * @param form
	 *            The ActionForm instance to pass to this Action
	 * @param mapping
	 *            The ActionMapping instance to pass to this Action
	 * 
	 * @exception IOException
	 *                if an input/output error occurs
	 * @exception ServletException
	 *                if a servlet exception occurs
	 */
	protected ActionForward processActionPerform(HttpServletRequest request, HttpServletResponse response, Action action, ActionForm form, ActionMapping mapping)
			throws IOException, ServletException {

		try {
			return (action.execute(mapping, form, request, response));
		} catch (ValidationFailedException e) {
			NavigationFunctions.putActionError(request, mapping, e.getParameters());
			return NavigationFunctions.findInput(request, mapping);
		} catch (Exception e) {
			return (processException(request, response, e, form, mapping));
		}

	}

	/**
	 * <p>
	 * Automatically select a <code>Locale</code> for the current user, if
	 * requested. <strong>NOTE</strong> - configuring Locale selection will
	 * trigger the creation of a new <code>HttpSession</code> if necessary.
	 * </p>
	 * 
	 * @param request
	 *            The servlet request we are processing
	 * @param response
	 *            The servlet response we are creating
	 */
	protected void processLocale(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.setAttribute(Globals.LOCALE_KEY, UserUtils.getCurrentLocale(request));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.RequestProcessor#process(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		refreshLoginData(request);

		super.process(request, response);
	}

	private void refreshLoginData(HttpServletRequest request) {
		refreshUser(request);
		refreshSiteOrg(request);
	}

	private void refreshUser(HttpServletRequest request) {
		String userName = UserUtils.getCurrentUsername(request);
		if (userName == null || userName.equals(""))
			return;
		Usuario user = null;
		try {
//			user = UseCaseFacade.HacerLogin(userName,null);
		} catch (Exception e) {
			log.error(e, e);
		}
//		UserUtils.setCurrentUsuario(request, user);
	}

	private void refreshSiteOrg(HttpServletRequest request) {
		SiteOrg siteOrg = (SiteOrg) request.getSession().getAttribute("siteorg");
		Usuario user = UserUtils.getCurrentUsuario(request);

		String[] rolesToSet;
		Set<String> siteRoleNames = new HashSet<String>();
		Set<String> userRoleNames = new HashSet<String>();
		String currentSiteLanguage=null;
		
		//construct site set
		if (siteOrg == null) {
			siteRoleNames.add(SiteOrgManager.singleton().getRolesManager().getDefaultRoleName());
		}
		else{
			String[] siteRoleNamesArray=siteOrg.getRoleNames();
			for (String roleName : siteRoleNamesArray) {
				siteRoleNames.add(roleName);
			}
			currentSiteLanguage=siteOrg.getSiteName();
		}
			
		

		// construct current set
		if (user == null) {
			userRoleNames.add(SiteOrgManager.singleton().getRolesManager().getDefaultRoleName());
		} else {
			Set<Role> roles=null;
			try {
			    roles = UserUtils.getCurrentUsuario(request).getRoles();
//				roles = UseCaseFacade.ObtenerRolesUsuario(user.getUsuariocode(), user.getUsuariocode());
			} catch (Exception e) {
				log.error(e,e);
			}
			if (roles == null || roles.isEmpty()) {
				userRoleNames.add(SiteOrgManager.singleton().getRolesManager().getDefaultRoleName());
			} else {
				for (Role role : roles) {
					userRoleNames.add(role.getId());
				}
			}
		}

		// compare them
		if (siteOrg==null || !currentSiteLanguage.equals(UserUtils.getCurrentLocale(request).getLanguage()) || !userRoleNames.equals(siteRoleNames)) {
			//site has changed-> we configure the new site
			try {
				siteOrg = SiteOrgManager.singleton().siteOrgFactoryUsingCache(userRoleNames.toArray(new String[0]), UserUtils.getCurrentLocale(request).getLanguage(), new CommandActionLinkProcessor());
				siteOrg.configAndCheck();
	            siteOrg.configureForThisUser(null);
			} catch (Exception e) {
				log.error(e,e);
				siteOrg=null;
			}            
			request.getSession().setAttribute("siteorg", siteOrg);
		}
	}

}