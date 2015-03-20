package bussineslogic.controlers;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.Vector;

import ldap.LDAPLogin;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.hibernate.exception.ConstraintViolationException;

import utils.Pair;
import utils.Persistent;
import utils.beanUtils.converters.DateConverter;
import utils.beanUtils.converters.NumberConverter;
import utils.beanUtils.converters.StringConverter;
import utils.filter.ListConfigurator;
import utils.filter.ListConfigurator.CompareData;
import utils.hibernate.HibernateUtil;
import utils.listFilter.ViewListConfiguration;
import utils.userUtils.UserUtils;
import bussineslogic.excepciones.HolidaysException;
import bussineslogic.excepciones.InternalException;
import bussineslogic.excepciones.NoPermisosException;
import bussineslogic.excepciones.UsuarioExisteException;
import bussineslogic.excepciones.UsuarioNoActivoException;
import bussineslogic.excepciones.ValidationFailedException;
import bussineslogic.objects.Academic_info;
import bussineslogic.objects.Alumni_communications;
import bussineslogic.objects.Alumni_external_job_positions;
import bussineslogic.objects.Alumni_external_job_sectors;
import bussineslogic.objects.Alumni_external_jobs;
import bussineslogic.objects.Alumni_irb_job_positions;
import bussineslogic.objects.Alumni_irb_jobs;
import bussineslogic.objects.Alumni_job_position_types;
import bussineslogic.objects.Alumni_params;
import bussineslogic.objects.Alumni_personal;
import bussineslogic.objects.Alumni_titles;
import bussineslogic.objects.ApplicationPreferences;
import bussineslogic.objects.Area;
import bussineslogic.objects.AuditLog;
import bussineslogic.objects.Auditmessage;
import bussineslogic.objects.Auditmessagetype;
import bussineslogic.objects.Bank;
import bussineslogic.objects.Benefits;
import bussineslogic.objects.Category;
import bussineslogic.objects.Child;
import bussineslogic.objects.Column;
import bussineslogic.objects.Compensation;
import bussineslogic.objects.Country;
import bussineslogic.objects.CustomList;
import bussineslogic.objects.Education;
import bussineslogic.objects.Filter;
import bussineslogic.objects.FilterType;
import bussineslogic.objects.Funding_detail;
import bussineslogic.objects.Gender;
import bussineslogic.objects.Grant;
import bussineslogic.objects.Grant_concession;
import bussineslogic.objects.Holiday;
import bussineslogic.objects.Irb_budget;
import bussineslogic.objects.Irbholiday;
import bussineslogic.objects.Irbholidayinfo;
import bussineslogic.objects.Location;
import bussineslogic.objects.Marital_status;
import bussineslogic.objects.Nationality;
import bussineslogic.objects.Notirb_active_personal;
import bussineslogic.objects.OrderBy;
import bussineslogic.objects.Organization_unit;
import bussineslogic.objects.Params;
import bussineslogic.objects.Payment;
import bussineslogic.objects.Payroll_institution;
import bussineslogic.objects.Personal;
import bussineslogic.objects.PersonalPhoto;
import bussineslogic.objects.Personal_comment;
import bussineslogic.objects.Personalstate;
import bussineslogic.objects.Position;
import bussineslogic.objects.Professional;
import bussineslogic.objects.Report;
import bussineslogic.objects.Research_group;
import bussineslogic.objects.Type_of_benefit;
import bussineslogic.objects.Type_of_compensation;
import bussineslogic.objects.Type_of_contract;
import bussineslogic.objects.Type_of_education;
import bussineslogic.objects.Type_of_grant;
import bussineslogic.objects.Type_of_holidays;
import bussineslogic.objects.Type_of_institution;
import bussineslogic.objects.Type_of_study;
import bussineslogic.objects.Unit;
import bussineslogic.objects.User_access;
import bussineslogic.objects.Usuario;
import bussineslogic.objects.Views;
import bussineslogic.objects.Work_experience;
import bussineslogic.objects.Working_hours;

import com.justinmind.identify.IdentifyManager_Plain;
import com.justinmind.identify.identifyException;
import com.justinmind.usermanagement.api.UserManagement;
import com.justinmind.usermanagement.entity.Entity;
import com.justinmind.usermanagement.entity.Language;
import com.justinmind.usermanagement.entity.Role;
import com.justinmind.usermanagement.exception.EntityNotFoundException;
import com.justinmind.usermanagement.exception.IdentifierException;
import com.justinmind.usermanagement.exception.PermissionPriorityException;
import com.justinmind.usermanagement.exception.UserManagementException;

/**
 * This class contains use cases of the application.
 * 
 * @author Automatika - JustInMind SL
 * 
 */
@SuppressWarnings("unchecked")
public class UseCase {

    private static org.apache.log4j.Category log = org.apache.log4j.Logger
	    .getLogger(UseCase.class);

    public final static String ADMINISTRATOR_ROLE_NAME = "administrator";
    public final static String BASIC_ROLE_NAME = "basic";
    public final static String ALUMNI_ROLE_NAME = "irbpeople_alumni";
    public final static String ALUMNI_ADMIN_ROLE_NAME = "irbpeople_alumni_admin";
    public final static String HUMAN_RESOURCES_ROLE_NAME = "irbpeople_rw";
    public final static String IRBPEOPLE_GRANT_ROLE_NAME = "irbpeople_grant";
    public final static String IRBPEOPLE_INNOVATION_ROLE_NAME = "irbpeople_innovation";
    public final static String SUPERVISOR_ROLE_NAME = "supervisor";
    public final static String GUEST_ROLE_NAME = "guest";

    private final static String MAIL_MESSAGE = "0";
    private final static String CREATE_MESSAGE = "1";
    private final static String MODIFY_MESSAGE = "2";
    private final static String DELETE_MESSAGE = "3";

    private static ResourceBundle MAINCONFIG = ResourceBundle
	    .getBundle("MainConfiguration");

    public static final Usuario SYSTEM_USER = new Usuario();
    {
	SYSTEM_USER.setUsuariocode("-");
    }

    // Use case methods

    /**
     * This method creates a Research_group.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOResearch_group
     *            Research_group data transfer object (DTO) with the values of
     *            the new instance.
     * @return the new research_group created with this Use Case
     * @throws InternalException
     * @throws NoPermisosException
     * @throws ValidationFailedException
     */
    public static Research_group CreateResearch_group(Usuario user,
	    Research_group TOResearch_group) throws InternalException,
	    NoPermisosException, ValidationFailedException {

	testIsHHRR(user);

	try {
	    /** 1. We begin the DB transaction. * */
	    HibernateUtil.beginTransaction();

	    /**
	     * 2. For each association from the TOResearch_group that are filled
	     * in the DTO we put the real objects from the DB. *
	     */

	    // we store if the supervisor is defined for later use
	    boolean _supervisorIsDefined = false;

	    if (TOResearch_group.getSupervisor() != null
		    && TOResearch_group.getSupervisor().getPersonalcode() != null) {
		// if supervisor is defined we replace the supervisor in the DTO
		// with its current state in the DB.
		_supervisorIsDefined = true;

		TOResearch_group.setSupervisor(getPersonal(TOResearch_group
			.getSupervisor().getPersonalcode()));
	    }

	    // we store if the unit is defined for later use
	    boolean _unitIsDefined = false;

	    if (TOResearch_group.getUnit() != null
		    && TOResearch_group.getUnit().getUnitcode() != null) {
		// if unit is defined we replace the unit in the DTO with its
		// current state in the DB.
		_unitIsDefined = true;

		TOResearch_group.setUnit(getUnit(TOResearch_group.getUnit()
			.getUnitcode()));
	    }

	    /** 3. We create the new instance * */
	    Research_group research_group = new Research_group();

	    /**
	     * 4. We set all the simple attributes (no associations) to the new
	     * instance *
	     */

	    research_group.setDescription(TOResearch_group.getDescription());
	    research_group.setGroup(TOResearch_group.getGroup());
	    if (research_group.getGroup() != null
		    && research_group.getGroup().trim().equals(""))
		research_group.setGroup(null);

	    /** 5. We set the code to the new instance * */
	    try {
		IdentifyManager_Plain im = IdentifyManager_Plain.singleton();

		research_group
			.setResearch_groupcode(im.getId(TOResearch_group));
	    } catch (identifyException ie) {

		log.error(
			"Error en asignaciï¿½n de nuevo id en CreateResearch_group",
			ie);
		throw new Error(ie.getMessage());
	    }

	    /** 6. We save the new instance to the DB* */
	    HibernateUtil.getSession().save(research_group);

	    /**
	     * We associate the current object to the other objects (only in
	     * case that the associations where defined in the DTO) *
	     */

	    if (_supervisorIsDefined) {

		if (TOResearch_group.getSupervisor() != null) {

		    TOResearch_group.getSupervisor().addIsupervisor(
			    research_group);
		}

		research_group.setSupervisor(TOResearch_group.getSupervisor());
	    }

	    if (_unitIsDefined) {

		if (TOResearch_group.getUnit() != null) {

		    TOResearch_group.getUnit().addIunit(research_group);
		}

		research_group.setUnit(TOResearch_group.getUnit());
	    }

	    /** 7. We create an Audit message * */
	    CreateCreationAuditmessage(user, research_group);

	    /** 8. We commit the DB transaction and return the new instance * */
	    HibernateUtil.commitTransaction();

	    return research_group;
	} catch (Exception cve) {
	    Map<String, List<String>> parameters = new Hashtable<String, List<String>>();
	    parameters.put("propertyError.existingValue",
		    Arrays.asList(new String[] { "group" }));
	    throw new ValidationFailedException(parameters);
	}

    }

    /**
     * This method modifies a research_group.
     * 
     * @param user
     *            The user who executes this use case
     * @param research_group
     *            Research_group (not DTO) with the values of the modified
     *            instance. The code of this attribute indicates which
     *            research_group will be modified.
     * @return the modified research_group
     * @throws InternalException
     * @throws NoPermisosException
     */
    private static Research_group ChangeResearch_groupSupervisor(Usuario user,
	    Research_group research_group, Personal supervisor)
	    throws InternalException, NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	Personal oldSupervisor = research_group.getSupervisor();

	/** test is there is no change* */
	if (oldSupervisor == null && supervisor == null) {
	    return research_group;
	}
	if (oldSupervisor != null && supervisor != null
		&& research_group.getCode().equals(supervisor)) {
	    return research_group;
	}

	// boolean wasSupervisor = supervisor != null
	// && !supervisor.getIsupervisor().isEmpty();

	/** Continue with the changes* */
	if (oldSupervisor != null) {
	    oldSupervisor.removeIsupervisor(research_group);
	}
	if (supervisor != null) {
	    supervisor.addIsupervisor(research_group);
	}
	research_group.setSupervisor(supervisor);

	/** change the user roles* */
	// if (oldSupervisor != null &&
	// oldSupervisor.getIsupervisor().isEmpty()) {
	// Usuario oldSupervisorPersonal = UseCase.ObtenerUsuarioFromPersonal(
	// user, oldSupervisor.getCode());
	// if (oldSupervisorPersonal != null) {
	// QuitarRol(user, oldSupervisorPersonal,
	// getRole(SUPERVISOR_ROLE_NAME));
	// }
	// }

	// if (supervisor != null && !wasSupervisor) {
	// Usuario newSupervisorPersonal = UseCase.ObtenerUsuarioFromPersonal(
	// user, supervisor.getCode());
	// if (newSupervisorPersonal != null) {
	// AddRol(user, newSupervisorPersonal, SUPERVISOR_ROLE_NAME);
	// }
	// }

	// UseCaseUtils.sendSettedToSupervisor(supervisor, research_group);

	HibernateUtil.commitTransaction();

	return research_group;
    }

    /**
     * This method modifies a research_group.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOResearch_group
     *            Research_group data transfer object (DTO) with the values of
     *            the modified instance. The code of this attribute indicates
     *            which research_group will be modified.
     * @return the modified research_group
     * @throws InternalException
     * @throws NoPermisosException
     * @throws ValidationFailedException
     */
    public static Research_group UpdateResearch_group(Usuario user,
	    Research_group TOResearch_group) throws InternalException,
	    NoPermisosException, ValidationFailedException {
	testIsHHRR(user);

	try {
	    /** 1. We begin the DB transaction. * */
	    HibernateUtil.beginTransaction();

	    /**
	     * 2. For each association from the TOResearch_group that are filled
	     * in the DTO we put the real objects from the DB. *
	     */

	    // we store if the supervisor is defined for later use
	    boolean _supervisorIsDefined = false;

	    if (TOResearch_group.getSupervisor() != null
		    && TOResearch_group.getSupervisor().getPersonalcode() != null) {
		// if supervisor is defined we replace the supervisor in the DTO
		// with its current state in the DB.
		_supervisorIsDefined = true;

		TOResearch_group.setSupervisor(getPersonal(TOResearch_group
			.getSupervisor().getPersonalcode()));
	    }

	    // we store if the unit is defined for later use
	    boolean _unitIsDefined = false;

	    if (TOResearch_group.getUnit() != null
		    && TOResearch_group.getUnit().getUnitcode() != null) {
		// if unit is defined we replace the unit in the DTO with its
		// current state in the DB.
		_unitIsDefined = true;

		TOResearch_group.setUnit(getUnit(TOResearch_group.getUnit()
			.getUnitcode()));
	    }

	    /** 3. We obtain form the DB the instance to modify * */
	    Research_group research_group = getResearch_group(TOResearch_group
		    .getResearch_groupcode());
	    ;

	    /**
	     * 4. We set all the simple attributes (no associations) to the
	     * instance *
	     */

	    research_group.setDescription(TOResearch_group.getDescription());
	    research_group.setGroup(TOResearch_group.getGroup());
	    if (research_group.getGroup() != null
		    && research_group.getGroup().trim().equals(""))
		research_group.setGroup(null);

	    /**
	     * 5. We set the DTO version to the modified object and we update it
	     * with the new values in the DB. We evict and update the instance
	     * to prevent concurrent modification *
	     */
	    HibernateUtil.getSession().evict(research_group);
	    research_group.setVersion(TOResearch_group.getVersion());
	    HibernateUtil.getSession().update(research_group);

	    /**
	     * We associate/disassociate the current object to the other objects
	     * (only in case that the associations where defined in the DTO) *
	     */

	    if (_supervisorIsDefined) {
		ChangeResearch_groupSupervisor(user, research_group,
			TOResearch_group.getSupervisor());
	    }

	    if (_unitIsDefined) {

		if (research_group.getUnit() != null) {

		    research_group.getUnit().removeIunit(research_group);
		}

		if (TOResearch_group.getUnit() != null) {

		    TOResearch_group.getUnit().addIunit(research_group);
		}

		research_group.setUnit(TOResearch_group.getUnit());
	    }

	    /** 6. We create an Audit message * */
	    CreateModificationAuditmessage(user, research_group);

	    /** 7. We commit the DB transaction and return the new instance * */
	    HibernateUtil.commitTransaction();

	    return research_group;
	} catch (Exception e) {
	    Map<String, List<String>> parameters = new Hashtable<String, List<String>>();
	    parameters.put("propertyError.existingValue",
		    Arrays.asList(new String[] { "group" }));
	    throw new ValidationFailedException(parameters);
	}

    }

    /**
     * This method removes a research_group.
     * 
     * @param user
     *            The user who executes this use case
     * @param research_groupcode
     *            Code of the research_group to be removed
     * @throws NoPermisosException
     */
    public static void RemoveResearch_group(Usuario user,
	    String research_groupcode) throws NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain the object to delete form the DB. * */
	Research_group research_group = getResearch_group(research_groupcode);

	/** 3. We mark it as deleted. * */
	research_group.setDeleted(Boolean.TRUE);

	/** 4. We create an Audit message * */
	CreateRemovealAuditmessage(user, research_group);

	/** 5. We commit the DB transaction. * */
	HibernateUtil.commitTransaction();
    }

    /**
     * This method obtains one instance of research_group given its code.
     * 
     * @param user
     *            The user who executes this use case
     * @param research_groupcode
     *            Code of the research_group to be obtained
     * @return Research_group with the given code.
     */
    public static Research_group ObtainResearch_group(Usuario user,
	    String research_groupcode) {

	/**
	 * 1. We obtain the object from the DB using the private getter and we
	 * return it. *
	 */

	Research_group research_group = getResearch_group(research_groupcode);
	return research_group;
    }

    /**
     * This method obtains all instances of Research_group, given a
     * list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     */
    public static Pair<Integer, List<Research_group>> ObtainAllResearch_group(
	    Usuario user, ListConfigurator configurator) {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(
		Research_group.class);

	// we only want to obtain the non deleted objects
	crit.add(Expression.eq("deleted", Boolean.FALSE));

	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	List<Research_group> research_groups = (List<Research_group>) crit
		.list();

	Pair<Integer, List<Research_group>> pair = new Pair<Integer, List<Research_group>>(
		count, research_groups);

	return pair;
    }

    /**
     * This method obtains all instances of Personal, which belong to the set of
     * supervisor of a research_group, given a list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param research_group
     *            Research_group which contains the set of supervisor
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     */
    public static Pair<Integer, List<Personal>> ObtainAllSupervisorFromResearch_group(
	    Usuario user, Research_group research_group,
	    ListConfigurator configurator) {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(
		Personal.class);

	// we only want to obtain the non deleted objects

	crit.add(Expression.eq("deleted", Boolean.FALSE));

	// we add the requirement that we only want to display the ones which
	// are associated
	crit.createCriteria("isupervisor").add(
		Expression.idEq(research_group.getResearch_groupcode()));

	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	Pair<Integer, List<Personal>> pair = new Pair<Integer, List<Personal>>(
		count, crit.list());

	return pair;
    }

    /**
     * This method obtains all instances of Professional, which belong to the
     * set of iresearch_group of a research_group, given a list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param research_group
     *            Research_group which contains the set of iresearch_group
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     */
    public static Pair<Integer, List<Professional>> ObtainAllIresearch_groupFromResearch_group(
	    Usuario user, Research_group research_group,
	    ListConfigurator configurator) {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(
		Professional.class);

	// we only want to obtain the non deleted objects

	crit.add(Expression.eq("deleted", Boolean.FALSE));

	// we add the requirement that we only want to display the ones which
	// are associated
	crit.createCriteria("research_group").add(
		Expression.idEq(research_group.getResearch_groupcode()));

	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	Pair<Integer, List<Professional>> pair = new Pair<Integer, List<Professional>>(
		count, crit.list());

	return pair;
    }

    /**
     * This method obtains all instances of Education, which belong to the set
     * of ieducation_personal of a personal, given a list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param personal
     *            Personal which contains the set of ieducation_personal
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     * @throws NoPermisosException 
     */
    public static Pair<Integer, List<Education>> ObtainAllIeducation_personalFromPersonal(
	    Usuario user, Personal personal, ListConfigurator configurator) throws NoPermisosException {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(
		Education.class);

	// we only want to obtain the non deleted objects

	crit.add(Expression.eq("deleted", Boolean.FALSE));

	// we add the requirement that we only want to display the ones which
	// are associated
	crit.createCriteria("education_personal").add(
		Expression.idEq(personal.getPersonalcode()));

	checkByRole(user, personal.getPersonalcode());
	
	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	Pair<Integer, List<Education>> pair = new Pair<Integer, List<Education>>(
		count, crit.list());

	return pair;
    }

    /**
     * This method obtains all instances of Professional, which belong to the
     * set of iprofessional_personal of a personal, given a list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param personal
     *            Personal which contains the set of iprofessional_personal
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     * @throws NoPermisosException 
     */
    public static Pair<Integer, List<Professional>> ObtainAllIprofessional_personalFromPersonal(
	    Usuario user, Personal personal, ListConfigurator configurator) throws NoPermisosException {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(
		Professional.class);

	// we only want to obtain the non deleted objects

	crit.add(Expression.eq("deleted", Boolean.FALSE));
	checkByRole(user, personal.getPersonalcode());
	
	// we add the requirement that we only want to display the ones which
	// are associated
	crit.createCriteria("professional_personal").add(
		Expression.idEq(personal.getPersonalcode()));

	crit.setFirstResult(0);
	configurator.setMaxResults(5000);
	configurator.setOrderBy("start_date");	
	configurator.setAsc("");
	
	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	Pair<Integer, List<Professional>> pair = new Pair<Integer, List<Professional>>(
		count, crit.list());

	return pair;
    }

    /**
     * This method obtains all active instances of Professional, which belong to
     * the set of iprofessional_personal of a personal, given a
     * list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param personal
     *            Personal which contains the set of iprofessional_personal
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     * @throws NoPermisosException 
     */
    public static Pair<Integer, List<Professional>> ObtainAllActiveIprofessional_personalFromPersonal(
	    Usuario user, Personal personal, ListConfigurator configurator) throws NoPermisosException {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(
		Professional.class);

	// we only want to obtain the non deleted objects

	crit.add(Expression.eq("deleted", Boolean.FALSE));

	crit.add(Expression.eq("current", Boolean.TRUE));

	checkByRole(user, personal.getPersonalcode());
	
	// we add the requirement that we only want to display the ones which
	// are associated
	crit.createCriteria("professional_personal").add(
		Expression.idEq(personal.getPersonalcode()));

	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	Pair<Integer, List<Professional>> pair = new Pair<Integer, List<Professional>>(
		count, crit.list());

	return pair;
    }

    private static Professional ObtainActiveProfessionalFromPersonal(
	    Personal personal) {

	Criteria crit = HibernateUtil.getSession().createCriteria(
		Professional.class);

	crit.add(Expression.eq("deleted", Boolean.FALSE));
	
	// crit.add(Expression.or(Expression.isNull("end_date"), Expression.gt(
	// "end_date", new Date())));

	crit.add(Expression.eq("current", Boolean.TRUE));

	crit.createCriteria("professional_personal").add(
		Expression.idEq(personal.getPersonalcode()));

	List<Professional> list = crit.list();

	if (list != null && list.size() > 0)
	    return list.get(0);
	else
	    return null;

    }

    /**
     * This method obtains all instances of Compensation, which belong to the
     * set of icompensation_personal of a personal, given a list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param personal
     *            Personal which contains the set of icompensation_personal
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     * @throws NoPermisosException 
     */
    public static Pair<Integer, List<Compensation>> ObtainAllIcompensation_personalFromPersonal(
	    Usuario user, Personal personal, ListConfigurator configurator) throws NoPermisosException {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(
		Compensation.class);

	// we only want to obtain the non deleted objects

	crit.add(Expression.eq("deleted", Boolean.FALSE));
	checkByRole(user, personal.getPersonalcode());
	
	// we add the requirement that we only want to display the ones which
	// are associated
	crit.createCriteria("compensation_personal").add(
		Expression.idEq(personal.getPersonalcode()));

	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	crit.setFirstResult(0);
	configurator.setMaxResults(5000);
	configurator.setOrderBy("start_date");	
	configurator.setAsc("");	
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	Pair<Integer, List<Compensation>> pair = new Pair<Integer, List<Compensation>>(
		count, crit.list());

	return pair;
    }

    /**
     * This method obtains the current compensation of a personal
     * 
     * @param user
     *            The user who executes this use case
     * @param personal
     *            Personal which contains the set of icompensation_personal
     * @return the las compesation
     * @throws NoPermisosException 
     */
    public static Compensation ObtainCurrentCompensationFromPersonal(
	    Usuario user, Personal personal) throws NoPermisosException {
	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(
		Compensation.class);

	// we only want to obtain the non deleted objects

	crit.add(Expression.eq("deleted", Boolean.FALSE));
	checkByRole(user, personal.getPersonalcode());
	
	// we add the requirement that we only want to display the ones which
	// are associated
	crit.createCriteria("compensation_personal").add(
		Expression.idEq(personal.getPersonalcode()));

	crit.addOrder(Order.desc("start_date"));

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	List compensations = crit.list();
	if (compensations.isEmpty())
	    return null;

	Compensation pair = (Compensation) compensations.get(0);
	return pair;
    }

    /**
     * This method obtains all instances of Holiday, which belong to the set of
     * iholiday_personal of a personal, given a list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param personal
     *            Personal which contains the set of iholiday_personal
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     */
    public static Pair<Integer, List<Holiday>> ObtainAllIholiday_personalFromPersonal(
	    Usuario user, Personal personal, ListConfigurator configurator) {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession()
		.createCriteria(Holiday.class);

	// we only want to obtain the non deleted objects

	crit.add(Expression.eq("deleted", Boolean.FALSE));

	// we add the requirement that we only want to display the ones which
	// are associated
	crit.createCriteria("holiday_personal").add(
		Expression.idEq(personal.getPersonalcode()));

	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	Pair<Integer, List<Holiday>> pair = new Pair<Integer, List<Holiday>>(
		count, crit.list());

	return pair;
    }

    /**
     * This method obtains all instances of Grant_concession, which belong to
     * the set of igrant_concession_personal of a personal, given a
     * list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param personal
     *            Personal which contains the set of igrant_concession_personal
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     * @throws NoPermisosException 
     */
    public static Pair<Integer, List<Grant_concession>> ObtainAllIgrant_concession_personalFromPersonal(
	    Usuario user, Personal personal, ListConfigurator configurator) throws NoPermisosException {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(
		Grant_concession.class);

	// we only want to obtain the non deleted objects

	crit.add(Expression.eq("deleted", Boolean.FALSE));
	checkByRole(user, personal.getPersonalcode());
	
	// we add the requirement that we only want to display the ones which
	// are associated
	crit.createCriteria("grant_concession_personal").add(
		Expression.idEq(personal.getPersonalcode()));

	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	Pair<Integer, List<Grant_concession>> pair = new Pair<Integer, List<Grant_concession>>(
		count, crit.list());

	return pair;
    }
    
    /**
     * This method obtains all instances of Academic_info, which belong to
     * the set of iacademic_info_personal of a personal, given a
     * list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param personal
     *            Personal which contains the set of iacademic_info_personal
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     * @throws NoPermisosException 
     */
    public static Pair<Integer, List<Academic_info>> ObtainAllIacademic_info_personalFromPersonal(
	    Usuario user, Personal personal, ListConfigurator configurator) throws NoPermisosException {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(
		Academic_info.class);

	// we only want to obtain the non deleted objects

	crit.add(Expression.eq("deleted", Boolean.FALSE));
	checkByRole(user, personal.getPersonalcode());
	
	// we add the requirement that we only want to display the ones which
	// are associated
	crit.createCriteria("academic_info_personal").add(
		Expression.idEq(personal.getPersonalcode()));

	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	Pair<Integer, List<Academic_info>> pair = new Pair<Integer, List<Academic_info>>(
		count, crit.list());

	return pair;
    }
    
    
    
    
	/**
     * This method creates a Alumni_external_jobs.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOAlumni_external_jobs
     *            Alumni_external_jobs data transfer object (DTO) with the values of
     *            the new instance.
     * @return the new alumni_external_jobs created with this Use Case
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Alumni_external_jobs CreateAlumni_external_jobs(Usuario user,
	    Alumni_external_jobs TOAlumni_external_jobs) throws InternalException,
	    NoPermisosException {

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/**
	 * 2. For each association from the TOAlumni_external_jobs that are filled
	 * in the DTO we put the real objects from the DB. *
	 */

	// we store if the alumni_external_jobs_personal is defined for later use
	boolean _alumni_external_jobs_personalIsDefined = false;

	if (TOAlumni_external_jobs.getPersonal() != null
		&& TOAlumni_external_jobs.getPersonal()
			.getAlumni_personalcode() != null) {
	    
		_alumni_external_jobs_personalIsDefined = true;
	    TOAlumni_external_jobs.setPersonal(getAlumni_personal(TOAlumni_external_jobs.getPersonal().getAlumni_personalcode()));
	}

	boolean _external_job_positionsIsDefined = false;

	if (TOAlumni_external_jobs.getExternal_job_positions() != null
		&& TOAlumni_external_jobs.getExternal_job_positions().getAlumni_external_job_positionscode() != null) {

		_external_job_positionsIsDefined = true;
	    TOAlumni_external_jobs.setExternal_job_positions(getAlumni_external_job_positions(TOAlumni_external_jobs.getExternal_job_positions().getAlumni_external_job_positionscode()));
	}
	
	boolean _external_job_sectorsIsDefined = false;

	if (TOAlumni_external_jobs.getExternal_job_sectors() != null
		&& TOAlumni_external_jobs.getExternal_job_sectors().getAlumni_external_job_sectorscode() != null) {

		_external_job_sectorsIsDefined = true;
	    TOAlumni_external_jobs.setExternal_job_sectors(getAlumni_external_job_sectors(TOAlumni_external_jobs.getExternal_job_sectors().getAlumni_external_job_sectorscode()));
	}
	
	boolean _country_codeIsDefined = false;

	if (TOAlumni_external_jobs.getCountry() != null
		&& TOAlumni_external_jobs.getCountry().getCode() != null) {
	    // if grant is defined we replace the grant in the DTO with its
	    // current state in the DB.
		_country_codeIsDefined = true;

	    TOAlumni_external_jobs.setCountry(getCountry(TOAlumni_external_jobs.getCountry().getCode()));
	}

	/** 3. We create the new instance * */
	Alumni_external_jobs alumni_external_jobs = new Alumni_external_jobs();

	/**
	 * 4. We set all the simple attributes (no associations) to the new
	 * instance *
	 */

	alumni_external_jobs.setStart_date(TOAlumni_external_jobs.getStart_date());
	alumni_external_jobs.setEnd_date(TOAlumni_external_jobs.getEnd_date());
	alumni_external_jobs.setComments(TOAlumni_external_jobs.getComments());
	alumni_external_jobs.setInstitution(TOAlumni_external_jobs.getInstitution());
	alumni_external_jobs.setAddress(TOAlumni_external_jobs.getAddress());
	alumni_external_jobs.setPostcode(TOAlumni_external_jobs.getPostcode());
	alumni_external_jobs.setCity(TOAlumni_external_jobs.getCity());
	alumni_external_jobs.setTelephone(TOAlumni_external_jobs.getTelephone());
	
	
	/** 5. We set the code to the new instance * */
	try {
	    IdentifyManager_Plain im = IdentifyManager_Plain.singleton();

	    alumni_external_jobs.setAlumni_external_jobscode(im
		    .getId(TOAlumni_external_jobs));
	} catch (identifyException ie) {

	    log.error(
		    "Error en asignaciï¿½n de nuevo id en CreateAlumni_external_jobs",
		    ie);
	    throw new Error(ie.getMessage());
	}

	/** 6. We save the new instance to the DB* */
	HibernateUtil.getSession().save(alumni_external_jobs);

	/**
	 * We associate the current object to the other objects (only in case
	 * that the associations where defined in the DTO) *
	 */

	if (_alumni_external_jobs_personalIsDefined) {

	    if (TOAlumni_external_jobs.getPersonal() != null) {

		TOAlumni_external_jobs.getPersonal()
			.addIalumni_external_jobs(alumni_external_jobs);
	    }

	    alumni_external_jobs.setPersonal(TOAlumni_external_jobs
		    .getPersonal());
	}
	
	if (_external_job_positionsIsDefined) {

	    if (TOAlumni_external_jobs.getExternal_job_positions() != null) {

		TOAlumni_external_jobs.getExternal_job_positions()
			.addIalumni_external_jobs(alumni_external_jobs);
	    }

	    alumni_external_jobs.setExternal_job_positions(TOAlumni_external_jobs
		    .getExternal_job_positions());
	}

	if (_external_job_sectorsIsDefined) {

	    if (TOAlumni_external_jobs.getExternal_job_sectors() != null) {

		TOAlumni_external_jobs.getExternal_job_sectors()
			.addIalumni_external_jobs(alumni_external_jobs);
	    }

	    alumni_external_jobs.setExternal_job_sectors(TOAlumni_external_jobs
		    .getExternal_job_sectors());
	}

	if (_country_codeIsDefined) {
		
		if (TOAlumni_external_jobs.getCountry() != null) {
			
			TOAlumni_external_jobs.getCountry()
			.addIalumni_external_jobs(alumni_external_jobs);
		}
		
		alumni_external_jobs.setCountry(TOAlumni_external_jobs
				.getCountry());
	}


	/** 7. We create an Audit message * */
	//CreateCreationAuditmessage(user, alumni_external_jobs);

	/** 8. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return alumni_external_jobs;
    }

    /**
     * This method modifies a alumni_external_jobs.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOAlumni_external_jobs
     *            Alumni_external_jobs data transfer object (DTO) with the values of
     *            the modified instance. The code of this attribute indicates
     *            which alumni_external_jobs will be modified.
     * @return the modified alumni_external_jobs
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Alumni_external_jobs UpdateAlumni_external_jobs(Usuario user,
	    Alumni_external_jobs TOAlumni_external_jobs) throws InternalException,
	    NoPermisosException {

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 3. We obtain form the DB the instance to modify * */
	Alumni_external_jobs alumni_external_jobs = getAlumni_external_jobs(TOAlumni_external_jobs
		.getAlumni_external_jobscode());
	
	/**
	 * 2. For each association from the TOAlumni_external_jobs that are filled
	 * in the DTO we put the real objects from the DB. *
	 */

	// we store if the alumni_external_jobs_personal is defined for later use
		boolean _alumni_external_jobs_personalIsDefined = false;

		if (TOAlumni_external_jobs.getPersonal() != null
			&& TOAlumni_external_jobs.getPersonal()
				.getAlumni_personalcode() != null) {
		    
			_alumni_external_jobs_personalIsDefined = true;
		    TOAlumni_external_jobs.setPersonal(getAlumni_personal(TOAlumni_external_jobs.getPersonal().getAlumni_personalcode()));
		}

		boolean _external_job_positionsIsDefined = false;

		if (TOAlumni_external_jobs.getExternal_job_positions() != null
			&& TOAlumni_external_jobs.getExternal_job_positions().getAlumni_external_job_positionscode() != null) {

			_external_job_positionsIsDefined = true;
		    TOAlumni_external_jobs.setExternal_job_positions(getAlumni_external_job_positions(TOAlumni_external_jobs.getExternal_job_positions().getAlumni_external_job_positionscode()));
		}
		
		boolean _external_job_sectorsIsDefined = false;

		if (TOAlumni_external_jobs.getExternal_job_sectors() != null
			&& TOAlumni_external_jobs.getExternal_job_sectors().getAlumni_external_job_sectorscode() != null) {

			_external_job_sectorsIsDefined = true;
		    TOAlumni_external_jobs.setExternal_job_sectors(getAlumni_external_job_sectors(TOAlumni_external_jobs.getExternal_job_sectors().getAlumni_external_job_sectorscode()));
		}
		
		boolean _country_codeIsDefined = false;

		if (TOAlumni_external_jobs.getCountry() != null
			&& TOAlumni_external_jobs.getCountry().getCode() != null) {
		    // if grant is defined we replace the grant in the DTO with its
		    // current state in the DB.
			_country_codeIsDefined = true;

		    TOAlumni_external_jobs.setCountry(getCountry(TOAlumni_external_jobs.getCountry().getCode()));
		}
	
		/**
	 * 4. We set all the simple attributes (no associations) to the instance
	 * *
	 */

		alumni_external_jobs.setStart_date(TOAlumni_external_jobs.getStart_date());
		alumni_external_jobs.setEnd_date(TOAlumni_external_jobs.getEnd_date());
		alumni_external_jobs.setComments(TOAlumni_external_jobs.getComments());
		alumni_external_jobs.setInstitution(TOAlumni_external_jobs.getInstitution());
		alumni_external_jobs.setAddress(TOAlumni_external_jobs.getAddress());
		alumni_external_jobs.setPostcode(TOAlumni_external_jobs.getPostcode());
		alumni_external_jobs.setCity(TOAlumni_external_jobs.getCity());
		alumni_external_jobs.setTelephone(TOAlumni_external_jobs.getTelephone());

	/**
	 * 5. We set the DTO version to the modified object and we update it
	 * with the new values in the DB. We evict and update the instance to
	 * prevent concurrent modification *
	 */
	HibernateUtil.getSession().evict(alumni_external_jobs);
	alumni_external_jobs.setVersion(TOAlumni_external_jobs.getVersion());
	HibernateUtil.getSession().update(alumni_external_jobs);

	/**
	 * We associate/disassociate the current object to the other objects
	 * (only in case that the associations where defined in the DTO) *
	 */

	if (_alumni_external_jobs_personalIsDefined) {

		if (alumni_external_jobs.getPersonal() != null) {
			alumni_external_jobs.getPersonal().removeIalumni_external_jobs(alumni_external_jobs);
	    }
		
	    if (TOAlumni_external_jobs.getPersonal() != null) {

		TOAlumni_external_jobs.getPersonal()
			.addIalumni_external_jobs(alumni_external_jobs);
	    }

	    alumni_external_jobs.setPersonal(TOAlumni_external_jobs
		    .getPersonal());
	}
	
	if (_external_job_positionsIsDefined) {

		if (alumni_external_jobs.getExternal_job_positions() != null) {
			alumni_external_jobs.getExternal_job_positions().removeIalumni_external_jobs(alumni_external_jobs);
	    }
		
	    if (TOAlumni_external_jobs.getExternal_job_positions() != null) {

		TOAlumni_external_jobs.getExternal_job_positions()
			.addIalumni_external_jobs(alumni_external_jobs);
	    }

	    alumni_external_jobs.setExternal_job_positions(TOAlumni_external_jobs
		    .getExternal_job_positions());
	}

	if (_external_job_sectorsIsDefined) {
		if (alumni_external_jobs.getExternal_job_sectors() != null) {
			alumni_external_jobs.getExternal_job_sectors().removeIalumni_external_jobs(alumni_external_jobs);
	    }
	    if (TOAlumni_external_jobs.getExternal_job_sectors() != null) {

		TOAlumni_external_jobs.getExternal_job_sectors()
			.addIalumni_external_jobs(alumni_external_jobs);
	    }

	    alumni_external_jobs.setExternal_job_sectors(TOAlumni_external_jobs
		    .getExternal_job_sectors());
	}

	if (_country_codeIsDefined) {
		if (alumni_external_jobs.getCountry() != null) {
			alumni_external_jobs.getCountry().removeIalumni_external_jobs(alumni_external_jobs);
		}
		if (TOAlumni_external_jobs.getCountry() != null) {
			
			TOAlumni_external_jobs.getCountry()
			.addIalumni_external_jobs(alumni_external_jobs);
		}
		
		alumni_external_jobs.setCountry(TOAlumni_external_jobs
				.getCountry());
	}

	
	/** 6. We create an Audit message * */
	CreateModificationAuditmessage(user, alumni_external_jobs);

	/** 7. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return alumni_external_jobs;
    }

    /**
     * This method removes a alumni_external_jobs.
     * 
     * @param user
     *            The user who executes this use case
     * @param alumni_external_jobscode
     *            Code of the alumni_external_jobs to be removed
     * @throws NoPermisosException
     */
    public static void RemoveAlumni_external_jobs(Usuario user,
	    String alumni_external_jobscode) throws NoPermisosException {

		/** 1. We begin the DB transaction. * */
		HibernateUtil.beginTransaction();
	
		/** 2. We obtain the object to delete form the DB. * */
		Alumni_external_jobs alumni_external_jobs = getAlumni_external_jobs(alumni_external_jobscode);
		// testIsHHRROrItself(user,
		// alumni_external_jobs.getPersonal());
	
		/** 3. We mark it as deleted. * */
		alumni_external_jobs.setDeleted(Boolean.TRUE);
	
		/** 4. We create an Audit message * */
		CreateRemovealAuditmessage(user, alumni_external_jobs);
	
		/** 5. We commit the DB transaction. * */
		HibernateUtil.commitTransaction();
    }

    /**
     * This method obtains one instance of alumni_external_jobs given its code.
     * 
     * @param user
     *            The user who executes this use case
     * @param alumni_external_jobscode
     *            Code of the alumni_external_jobs to be obtained
     * @return Alumni_external_jobs with the given code.
     */
    public static Alumni_external_jobs ObtainAlumni_external_jobs(Usuario user,
	    String alumni_external_jobscode) {

	/**
	 * 1. We obtain the object from the DB using the private getter and we
	 * return it. *
	 */

	Alumni_external_jobs alumni_external_jobs = getAlumni_external_jobs(alumni_external_jobscode);
	return alumni_external_jobs;
    }

    /**
     * This method obtains all instances of Alumni_external_jobs, given a
     * list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     */
    public static Pair<Integer, List<Alumni_external_jobs>> ObtainAllAlumni_external_jobs(
	    Usuario user, ListConfigurator configurator) {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(
		Alumni_external_jobs.class);

	// we only want to obtain the non deleted objects
	crit.add(Expression.eq("deleted", Boolean.FALSE));

	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	List<Alumni_external_jobs> alumni_external_jobss = (List<Alumni_external_jobs>) crit
		.list();

	Pair<Integer, List<Alumni_external_jobs>> pair = new Pair<Integer, List<Alumni_external_jobs>>(
		count, alumni_external_jobss);

	return pair;
    }
	
	/**
     * Returns the Alumni_external_jobs with the given key. This method is used
     * internally to get objects form the database.
     * 
     * @param alumni_external_jobscode
     *            code of the Alumni_external_jobs
     * @return Alumni_external_jobs with the given code
     */
    protected static Alumni_external_jobs getAlumni_external_jobs(
	    String alumni_external_jobscode) {
	Alumni_external_jobs alumni_external_jobs = (Alumni_external_jobs) HibernateUtil
		.getSession().get(Alumni_external_jobs.class, alumni_external_jobscode);
	return alumni_external_jobs;
    }
	
	
	public static String SetCurrentAlumni_external_jobs(Usuario usuario,
    	    String alumni_external_jobscode) {

    	/** 1. We begin the DB transaction. * */
    	HibernateUtil.beginTransaction();

    	/** 2. We obtain the object to delete form the DB. * */
    	Alumni_external_jobs alumni_external_jobs = getAlumni_external_jobs(alumni_external_jobscode);

    	boolean oldValue = alumni_external_jobs.isCurrent();

    	Set<Alumni_external_jobs> lineasDeAlumni_external_jobscode = alumni_external_jobs
    		.getPersonal().getIalumni_external_jobs();

    	for (Alumni_external_jobs linea : lineasDeAlumni_external_jobscode) {
    	    linea.setCurrent(false);
    	}

    	/** 3. We mark it as deleted. * */
    	alumni_external_jobs.setCurrent(!oldValue);

    	/** 4. We create an Audit message * */
    	// CreateRemovealAuditmessage(user, professional);

    	/** 5. We commit the DB transaction. * */
    	HibernateUtil.commitTransaction();

    	return alumni_external_jobs.getPersonal()
    		.getAlumni_personalcode();
    }
    
	/**
     * This method creates a Alumni_irb_jobs.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOAlumni_irb_jobs
     *            Alumni_irb_jobs data transfer object (DTO) with the values of
     *            the new instance.
     * @return the new alumni_irb_jobs created with this Use Case
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Alumni_irb_jobs CreateAlumni_irb_jobs(Usuario user,
	    Alumni_irb_jobs TOAlumni_irb_jobs) throws InternalException,
	    NoPermisosException {

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/**
	 * 2. For each association from the TOAlumni_irb_jobs that are filled
	 * in the DTO we put the real objects from the DB. *
	 */

	// we store if the alumni_irb_jobs_personal is defined for later use
	boolean _alumni_irb_jobs_personalIsDefined = false;

	if (TOAlumni_irb_jobs.getPersonal() != null
		&& TOAlumni_irb_jobs.getPersonal()
			.getAlumni_personalcode() != null) {
	    
		_alumni_irb_jobs_personalIsDefined = true;
	    TOAlumni_irb_jobs.setPersonal(getAlumni_personal(TOAlumni_irb_jobs.getPersonal().getAlumni_personalcode()));
	}

	boolean _irb_job_positionsIsDefined = false;

	if (TOAlumni_irb_jobs.getIrb_job_positions() != null
		&& TOAlumni_irb_jobs.getIrb_job_positions().getAlumni_irb_job_positionscode() != null) {

		_irb_job_positionsIsDefined = true;
	    TOAlumni_irb_jobs.setIrb_job_positions(getAlumni_irb_job_positions(TOAlumni_irb_jobs.getIrb_job_positions().getAlumni_irb_job_positionscode()));
	}
	
	
	boolean _unitIsDefined = false;

	if (TOAlumni_irb_jobs.getUnit() != null
		&& TOAlumni_irb_jobs.getUnit().getCode() != null) {
	    // if grant is defined we replace the grant in the DTO with its
	    // current state in the DB.
		_unitIsDefined = true;

	    TOAlumni_irb_jobs.setUnit(getUnit(TOAlumni_irb_jobs.getUnit().getCode()));
	}
	
	boolean _unit_2IsDefined = false;
	
	if (TOAlumni_irb_jobs.getUnit_2() != null
			&& TOAlumni_irb_jobs.getUnit_2().getCode() != null) {
		// if grant is defined we replace the grant in the DTO with its
		// current state in the DB.
		_unit_2IsDefined = true;
		
		TOAlumni_irb_jobs.setUnit_2(getUnit(TOAlumni_irb_jobs.getUnit_2().getCode()));
	}
	
	boolean _research_groupIsDefined = false;
	
	if (TOAlumni_irb_jobs.getResearch_group() != null
			&& TOAlumni_irb_jobs.getResearch_group().getCode() != null) {
		// if grant is defined we replace the grant in the DTO with its
		// current state in the DB.
		_research_groupIsDefined = true;
		
		TOAlumni_irb_jobs.setResearch_group(getResearch_group(TOAlumni_irb_jobs.getResearch_group().getCode()));
	}
	
	boolean _research_group_2IsDefined = false;
	
	if (TOAlumni_irb_jobs.getResearch_group_2() != null
			&& TOAlumni_irb_jobs.getResearch_group_2().getCode() != null) {
		// if grant is defined we replace the grant in the DTO with its
		// current state in the DB.
		_research_group_2IsDefined = true;
				
		TOAlumni_irb_jobs.setResearch_group_2(getResearch_group(TOAlumni_irb_jobs.getResearch_group_2().getCode()));
	}
	
	

	/** 3. We create the new instance * */
	Alumni_irb_jobs alumni_irb_jobs = new Alumni_irb_jobs();

	/**
	 * 4. We set all the simple attributes (no associations) to the new
	 * instance *
	 */

	alumni_irb_jobs.setStart_date(TOAlumni_irb_jobs.getStart_date());
	alumni_irb_jobs.setEnd_date(TOAlumni_irb_jobs.getEnd_date());	
	
	
	/** 5. We set the code to the new instance * */
	try {
	    IdentifyManager_Plain im = IdentifyManager_Plain.singleton();

	    alumni_irb_jobs.setAlumni_irb_jobscode(im
		    .getId(TOAlumni_irb_jobs));
	} catch (identifyException ie) {

	    log.error(
		    "Error en asignaciï¿½n de nuevo id en CreateAlumni_irb_jobs",
		    ie);
	    throw new Error(ie.getMessage());
	}

	/** 6. We save the new instance to the DB* */
	HibernateUtil.getSession().save(alumni_irb_jobs);

	/**
	 * We associate the current object to the other objects (only in case
	 * that the associations where defined in the DTO) *
	 */

	if (_alumni_irb_jobs_personalIsDefined) {

	    if (TOAlumni_irb_jobs.getPersonal() != null) {

		TOAlumni_irb_jobs.getPersonal()
			.addIalumni_irb_jobs(alumni_irb_jobs);
	    }

	    alumni_irb_jobs.setPersonal(TOAlumni_irb_jobs
		    .getPersonal());
	}
	
	if (_irb_job_positionsIsDefined) {

	    if (TOAlumni_irb_jobs.getIrb_job_positions() != null) {

		TOAlumni_irb_jobs.getIrb_job_positions()
			.addIalumni_irb_jobs(alumni_irb_jobs);
	    }

	    alumni_irb_jobs.setIrb_job_positions(TOAlumni_irb_jobs
		    .getIrb_job_positions());
	}

	if (_unitIsDefined) {
		
		if (TOAlumni_irb_jobs.getUnit() != null) {
			
			TOAlumni_irb_jobs.getUnit()
			.addIalumni_irb_jobs(alumni_irb_jobs);
		}
		
		alumni_irb_jobs.setUnit(TOAlumni_irb_jobs
				.getUnit());
	}
	
	if (_unit_2IsDefined) {
		
		if (TOAlumni_irb_jobs.getUnit_2() != null) {
			
			TOAlumni_irb_jobs.getUnit_2()
			.addIalumni_irb_jobs_2(alumni_irb_jobs);
		}
		
		alumni_irb_jobs.setUnit_2(TOAlumni_irb_jobs
				.getUnit_2());
	}

	if (_research_groupIsDefined) {
		
		if (TOAlumni_irb_jobs.getResearch_group() != null) {
			
			TOAlumni_irb_jobs.getResearch_group()
			.addIalumni_irb_jobs_3(alumni_irb_jobs);
		}
		
		alumni_irb_jobs.setResearch_group(TOAlumni_irb_jobs
				.getResearch_group());
	}

	if (_research_group_2IsDefined) {
		
		if (TOAlumni_irb_jobs.getResearch_group_2() != null) {
			
			TOAlumni_irb_jobs.getResearch_group_2()
			.addIalumni_irb_jobs_4(alumni_irb_jobs);
		}
		
		alumni_irb_jobs.setResearch_group_2(TOAlumni_irb_jobs
				.getResearch_group_2());
	}
	

	/** 7. We create an Audit message * */
	CreateCreationAuditmessage(user, alumni_irb_jobs);

	/** 8. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return alumni_irb_jobs;
    }

    /**
     * This method modifies a alumni_irb_jobs.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOAlumni_irb_jobs
     *            Alumni_irb_jobs data transfer object (DTO) with the values of
     *            the modified instance. The code of this attribute indicates
     *            which alumni_irb_jobs will be modified.
     * @return the modified alumni_irb_jobs
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Alumni_irb_jobs UpdateAlumni_irb_jobs(Usuario user,
	    Alumni_irb_jobs TOAlumni_irb_jobs) throws InternalException,
	    NoPermisosException {

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 3. We obtain form the DB the instance to modify * */
	Alumni_irb_jobs alumni_irb_jobs = getAlumni_irb_jobs(TOAlumni_irb_jobs
		.getAlumni_irb_jobscode());
	
	/**
	 * 2. For each association from the TOAlumni_irb_jobs that are filled
	 * in the DTO we put the real objects from the DB. *
	 */
	boolean _alumni_irb_jobs_personalIsDefined = false;
	// we store if the alumni_irb_jobs_personal is defined for later use
	if (TOAlumni_irb_jobs.getPersonal() != null
			&& TOAlumni_irb_jobs.getPersonal()
				.getAlumni_personalcode() != null) {
		    
			_alumni_irb_jobs_personalIsDefined = true;
		    TOAlumni_irb_jobs.setPersonal(getAlumni_personal(TOAlumni_irb_jobs.getPersonal().getAlumni_personalcode()));
		}

		boolean _irb_job_positionsIsDefined = false;

		if (TOAlumni_irb_jobs.getIrb_job_positions() != null
			&& TOAlumni_irb_jobs.getIrb_job_positions().getAlumni_irb_job_positionscode() != null) {

			_irb_job_positionsIsDefined = true;
		    TOAlumni_irb_jobs.setIrb_job_positions(getAlumni_irb_job_positions(TOAlumni_irb_jobs.getIrb_job_positions().getAlumni_irb_job_positionscode()));
		}
		
		
		boolean _unitIsDefined = false;

		if (TOAlumni_irb_jobs.getUnit() != null
			&& TOAlumni_irb_jobs.getUnit().getCode() != null) {
		    // if grant is defined we replace the grant in the DTO with its
		    // current state in the DB.
			_unitIsDefined = true;

		    TOAlumni_irb_jobs.setUnit(getUnit(TOAlumni_irb_jobs.getUnit().getCode()));
		}
		
		boolean _unit_2IsDefined = false;
		
		if (TOAlumni_irb_jobs.getUnit_2() != null
				&& TOAlumni_irb_jobs.getUnit_2().getCode() != null) {
			// if grant is defined we replace the grant in the DTO with its
			// current state in the DB.
			_unit_2IsDefined = true;
			
			TOAlumni_irb_jobs.setUnit_2(getUnit(TOAlumni_irb_jobs.getUnit_2().getCode()));
		}
		
		boolean _research_groupIsDefined = false;
		
		if (TOAlumni_irb_jobs.getResearch_group() != null
				&& TOAlumni_irb_jobs.getResearch_group().getCode() != null) {
			// if grant is defined we replace the grant in the DTO with its
			// current state in the DB.
			_research_groupIsDefined = true;
			TOAlumni_irb_jobs.setResearch_group(getResearch_group(TOAlumni_irb_jobs.getResearch_group().getCode()));
		}
	
		boolean _research_group_2IsDefined = false;
		
		if (TOAlumni_irb_jobs.getResearch_group_2() != null
				&& TOAlumni_irb_jobs.getResearch_group_2().getCode() != null) {
			// if grant is defined we replace the grant in the DTO with its
			// current state in the DB.
			_research_group_2IsDefined = true;
			
			TOAlumni_irb_jobs.setResearch_group_2(getResearch_group(TOAlumni_irb_jobs.getResearch_group_2().getCode()));
		}
		
		/**
	 * 4. We set all the simple attributes (no associations) to the instance
	 * *
	 */

		alumni_irb_jobs.setStart_date(TOAlumni_irb_jobs.getStart_date());
		alumni_irb_jobs.setEnd_date(TOAlumni_irb_jobs.getEnd_date());	

	/**
	 * 5. We set the DTO version to the modified object and we update it
	 * with the new values in the DB. We evict and update the instance to
	 * prevent concurrent modification *
	 */
	HibernateUtil.getSession().evict(alumni_irb_jobs);
	alumni_irb_jobs.setVersion(TOAlumni_irb_jobs.getVersion());
	HibernateUtil.getSession().update(alumni_irb_jobs);

	/**
	 * We associate/disassociate the current object to the other objects
	 * (only in case that the associations where defined in the DTO) *
	 */

	if (_alumni_irb_jobs_personalIsDefined) {
		
		if (alumni_irb_jobs.getPersonal() != null) {
			alumni_irb_jobs.getPersonal().removeIalumni_irb_jobs(alumni_irb_jobs);
	    }
		
	    if (TOAlumni_irb_jobs.getPersonal() != null) {

		TOAlumni_irb_jobs.getPersonal()
			.addIalumni_irb_jobs(alumni_irb_jobs);
	    }

	    alumni_irb_jobs.setPersonal(TOAlumni_irb_jobs
		    .getPersonal());
	}
	
	if (_irb_job_positionsIsDefined) {

		if (alumni_irb_jobs.getIrb_job_positions() != null) {
			alumni_irb_jobs.getIrb_job_positions().removeIalumni_irb_jobs(alumni_irb_jobs);
	    }
		
	    if (TOAlumni_irb_jobs.getIrb_job_positions() != null) {

		TOAlumni_irb_jobs.getIrb_job_positions()
			.addIalumni_irb_jobs(alumni_irb_jobs);
	    }

	    alumni_irb_jobs.setIrb_job_positions(TOAlumni_irb_jobs
		    .getIrb_job_positions());
	}

	if (_unitIsDefined) {
		if (alumni_irb_jobs.getUnit() != null) {
			alumni_irb_jobs.getUnit().removeIalumni_irb_jobs(alumni_irb_jobs);
	    }
		if (TOAlumni_irb_jobs.getUnit() != null) {
			
			TOAlumni_irb_jobs.getUnit()
			.addIalumni_irb_jobs(alumni_irb_jobs);
		}
		
		alumni_irb_jobs.setUnit(TOAlumni_irb_jobs
				.getUnit());
	}
	
	if (_unit_2IsDefined) {
		if (alumni_irb_jobs.getUnit_2() != null) {
			alumni_irb_jobs.getUnit_2().removeIalumni_irb_jobs(alumni_irb_jobs);
	    }
		if (TOAlumni_irb_jobs.getUnit_2() != null) {
			
			TOAlumni_irb_jobs.getUnit_2()
			.addIalumni_irb_jobs_2(alumni_irb_jobs);
		}
		
		alumni_irb_jobs.setUnit_2(TOAlumni_irb_jobs
				.getUnit_2());
	}

	
	if (_research_groupIsDefined) {
		if (alumni_irb_jobs.getResearch_group() != null) {
			alumni_irb_jobs.getResearch_group().removeIalumni_irb_jobs_3(alumni_irb_jobs);
	    }
		if (TOAlumni_irb_jobs.getResearch_group() != null) {
			
			TOAlumni_irb_jobs.getResearch_group()
			.addIalumni_irb_jobs_3(alumni_irb_jobs);
		}
		
		alumni_irb_jobs.setResearch_group(TOAlumni_irb_jobs
				.getResearch_group());
	}
	
	if (_research_group_2IsDefined) {
		if (alumni_irb_jobs.getResearch_group_2() != null) {
			alumni_irb_jobs.getResearch_group_2().removeIalumni_irb_jobs_4(alumni_irb_jobs);
	    }
		if (TOAlumni_irb_jobs.getResearch_group_2() != null) {
			
			TOAlumni_irb_jobs.getResearch_group_2()
			.addIalumni_irb_jobs_4(alumni_irb_jobs);
		}
		
		alumni_irb_jobs.setResearch_group_2(TOAlumni_irb_jobs
				.getResearch_group_2());
	}

	
	
	/** 6. We create an Audit message * */
	CreateModificationAuditmessage(user, alumni_irb_jobs);

	/** 7. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return alumni_irb_jobs;
    }

    /**
     * This method removes a alumni_irb_jobs.
     * 
     * @param user
     *            The user who executes this use case
     * @param alumni_irb_jobscode
     *            Code of the alumni_irb_jobs to be removed
     * @throws NoPermisosException
     */
    public static void RemoveAlumni_irb_jobs(Usuario user,
	    String alumni_irb_jobscode) throws NoPermisosException {

		/** 1. We begin the DB transaction. * */
		HibernateUtil.beginTransaction();
	
		/** 2. We obtain the object to delete form the DB. * */
		Alumni_irb_jobs alumni_irb_jobs = getAlumni_irb_jobs(alumni_irb_jobscode);
		// testIsHHRROrItself(user,
		// alumni_irb_jobs.getPersonal());
	
		/** 3. We mark it as deleted. * */
		alumni_irb_jobs.setDeleted(Boolean.TRUE);
	
		/** 4. We create an Audit message * */
		CreateRemovealAuditmessage(user, alumni_irb_jobs);
	
		/** 5. We commit the DB transaction. * */
		HibernateUtil.commitTransaction();
    }

    /**
     * This method obtains one instance of alumni_irb_jobs given its code.
     * 
     * @param user
     *            The user who executes this use case
     * @param alumni_irb_jobscode
     *            Code of the alumni_irb_jobs to be obtained
     * @return Alumni_irb_jobs with the given code.
     */
    public static Alumni_irb_jobs ObtainAlumni_irb_jobs(Usuario user,
	    String alumni_irb_jobscode) {

	/**
	 * 1. We obtain the object from the DB using the private getter and we
	 * return it. *
	 */

	Alumni_irb_jobs alumni_irb_jobs = getAlumni_irb_jobs(alumni_irb_jobscode);
	return alumni_irb_jobs;
    }

    /**
     * This method obtains all instances of Alumni_irb_jobs, given a
     * list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     */
    public static Pair<Integer, List<Alumni_irb_jobs>> ObtainAllAlumni_irb_jobs(
	    Usuario user, ListConfigurator configurator) {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(
		Alumni_irb_jobs.class);

	// we only want to obtain the non deleted objects
	crit.add(Expression.eq("deleted", Boolean.FALSE));

	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	List<Alumni_irb_jobs> alumni_irb_jobss = (List<Alumni_irb_jobs>) crit
		.list();

	Pair<Integer, List<Alumni_irb_jobs>> pair = new Pair<Integer, List<Alumni_irb_jobs>>(
		count, alumni_irb_jobss);

	return pair;
    }
	
	/**
     * Returns the Alumni_irb_jobs with the given key. This method is used
     * internally to get objects form the database.
     * 
     * @param alumni_irb_jobscode
     *            code of the Alumni_irb_jobs
     * @return Alumni_irb_jobs with the given code
     */
    protected static Alumni_irb_jobs getAlumni_irb_jobs(
	    String alumni_irb_jobscode) {
	Alumni_irb_jobs alumni_irb_jobs = (Alumni_irb_jobs) HibernateUtil
		.getSession().get(Alumni_irb_jobs.class, alumni_irb_jobscode);
	return alumni_irb_jobs;
    }
    
    
    
    
    
    
    
    /**
     * This method obtains all instances of Alumni_external_jobs, which belong to
     * the set of ialumni_external_jobs_alumni_personal of a personal, given a
     * list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param personal
     *            Personal which contains the set of ialumni_external_jobs_alumni_personal
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     * @throws NoPermisosException 
     */
    public static Pair<Integer, List<Alumni_external_jobs>> ObtainAllIalumni_external_jobs_alumni_personalFromPersonal(
	    Usuario user, Alumni_personal personal, ListConfigurator configurator) throws NoPermisosException {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(
		Alumni_external_jobs.class);

	// we only want to obtain the non deleted objects

	crit.add(Expression.eq("deleted", Boolean.FALSE));
		
	// we add the requirement that we only want to display the ones which
	// are associated
	crit.createCriteria("personal").add(
		Expression.idEq(personal.getAlumni_personalcode()));

	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	Pair<Integer, List<Alumni_external_jobs>> pair = new Pair<Integer, List<Alumni_external_jobs>>(
		count, crit.list());

	return pair;
    }

	
	  /**
     * This method obtains all instances of Alumni_irb_jobs, which belong to
     * the set of ialumni_irb_jobs_alumni_personal of a personal, given a
     * list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param personal
     *            Personal which contains the set of ialumni_irb_jobs_alumni_personal
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     * @throws NoPermisosException 
     */
    public static Pair<Integer, List<Alumni_irb_jobs>> ObtainAllIalumni_irb_jobs_alumni_personalFromPersonal(
	    Usuario user, Alumni_personal personal, ListConfigurator configurator) throws NoPermisosException {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(
		Alumni_irb_jobs.class);

	// we only want to obtain the non deleted objects

	crit.add(Expression.eq("deleted", Boolean.FALSE));	
	
	// we add the requirement that we only want to display the ones which
	// are associated
	crit.createCriteria("personal").add(
		Expression.idEq(personal.getAlumni_personalcode()));

	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	Pair<Integer, List<Alumni_irb_jobs>> pair = new Pair<Integer, List<Alumni_irb_jobs>>(
		count, crit.list());

	return pair;
    }


    /**
     * This method obtains all instances of Work_experience, which belong to the
     * set of iwork_experience_personal of a personal, given a
     * list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param personal
     *            Personal which contains the set of iwork_experience_personal
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     * @throws NoPermisosException 
     */
    public static Pair<Integer, List<Work_experience>> ObtainAllIwork_experience_personalFromPersonal(
	    Usuario user, Personal personal, ListConfigurator configurator) throws NoPermisosException {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(
		Work_experience.class);

	// we only want to obtain the non deleted objects

	crit.add(Expression.eq("deleted", Boolean.FALSE));
	checkByRole(user, personal.getPersonalcode());
	
	// we add the requirement that we only want to display the ones which
	// are associated
	crit.createCriteria("work_experience_personal").add(
		Expression.idEq(personal.getPersonalcode()));

	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	Pair<Integer, List<Work_experience>> pair = new Pair<Integer, List<Work_experience>>(
		count, crit.list());

	return pair;
    }

    /**
     * This method obtains all instances of Funding_detail, which belong to the
     * set of ifunding_detail_personal of a personal, given a list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param personal
     *            Personal which contains the set of ifunding_detail_personal
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     * @throws NoPermisosException 
     */
    public static Pair<Integer, List<Funding_detail>> ObtainAllIfunding_detail_personalFromPersonal(
	    Usuario user, Personal personal, ListConfigurator configurator) throws NoPermisosException {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(
		Funding_detail.class);

	// we only want to obtain the non deleted objects

	crit.add(Expression.eq("deleted", Boolean.FALSE));
	checkByRole(user, personal.getPersonalcode());

	// we add the requirement that we only want to display the ones which
	// are associated
	crit.createCriteria("funding_detail_personal").add(
		Expression.idEq(personal.getPersonalcode()));

	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	Pair<Integer, List<Funding_detail>> pair = new Pair<Integer, List<Funding_detail>>(
		count, crit.list());

	return pair;
    }

    /**
     * This method obtains all instances of Child, which belong to the set of
     * ichild_personal of a personal, given a list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param personal
     *            Personal which contains the set of ichild_personal
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     */
    public static Pair<Integer, List<Child>> ObtainAllIchild_personalFromPersonal(
	    Usuario user, Personal personal, ListConfigurator configurator) {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(Child.class);

	// we only want to obtain the non deleted objects

	crit.add(Expression.eq("deleted", Boolean.FALSE));

	// we add the requirement that we only want to display the ones which
	// are associated
	crit.createCriteria("child_personal").add(
		Expression.idEq(personal.getPersonalcode()));

	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	Pair<Integer, List<Child>> pair = new Pair<Integer, List<Child>>(count,
		crit.list());

	return pair;
    }

    public static Pair<Integer, List<Personal_comment>> ObtainAllIpersonal_commentsFromPersonal(
	    Usuario user, Personal personal, ListConfigurator configurator) throws NoPermisosException {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(
		Personal_comment.class);

	// we only want to obtain the non deleted objects

	crit.add(Expression.eq("deleted", Boolean.FALSE));
	checkByRole(user, personal.getPersonalcode());
	
	// we add the requirement that we only want to display the ones which
	// are associated
	crit.createCriteria("personal").add(
		Expression.idEq(personal.getPersonalcode()));

	crit.addOrder(Order.desc("input_date"));

	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	Pair<Integer, List<Personal_comment>> pair = new Pair<Integer, List<Personal_comment>>(
		count, crit.list());

	return pair;
    }

    /**
     * This method obtains all instances of Benefits, which belong to the set of
     * ibenefits_personal of a personal, given a list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param personal
     *            Personal which contains the set of ibenefits_personal
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     * @throws NoPermisosException 
     */
    public static Pair<Integer, List<Benefits>> ObtainAllIbenefits_personalFromPersonal(
	    Usuario user, Personal personal, ListConfigurator configurator) throws NoPermisosException {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(
		Benefits.class);

	// we only want to obtain the non deleted objects

	crit.add(Expression.eq("deleted", Boolean.FALSE));
	checkByRole(user, personal.getPersonalcode());

	// we add the requirement that we only want to display the ones which
	// are associated
	crit.createCriteria("benefits_personal").add(
		Expression.idEq(personal.getPersonalcode()));

	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	Pair<Integer, List<Benefits>> pair = new Pair<Integer, List<Benefits>>(
		count, crit.list());

	return pair;
    }

    /**
     * This method creates a Professional.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOProfessional
     *            Professional data transfer object (DTO) with the values of the
     *            new instance.
     * @return the new professional created with this Use Case
     * @throws InternalException
     * @throws ValidationFailedException
     */
    public static Professional CreateProfessional(Usuario user,
	    Professional TOProfessional) throws InternalException,
	    ValidationFailedException {

	if (!validateProfessional(TOProfessional)) {
	    Map<String, List<String>> parameters = new Hashtable<String, List<String>>();
	    parameters.put("propertyError.required_attribute",
		    Arrays.asList(new String[] { "research_group" }));
	    throw new ValidationFailedException(parameters);
	}

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/**
	 * 2. For each association from the TOProfessional that are filled in
	 * the DTO we put the real objects from the DB. *
	 */

	// we store if the professional_personal is defined for later use
	boolean _professional_personalIsDefined = false;

	if (TOProfessional.getProfessional_personal() != null
		&& TOProfessional.getProfessional_personal().getPersonalcode() != null) {
	    // if professional_personal is defined we replace the
	    // professional_personal in the DTO with its current state in the
	    // DB.
	    _professional_personalIsDefined = true;

	    TOProfessional.setProfessional_personal(getPersonal(TOProfessional
		    .getProfessional_personal().getPersonalcode()));
	}

	// we store if the research_group is defined for later use
	boolean _research_groupIsDefined = false;

	if (TOProfessional.getResearch_group() != null
		&& TOProfessional.getResearch_group().getResearch_groupcode() != null) {
	    // if research_group is defined we replace the research_group in the
	    // DTO with its current state in the DB.
	    _research_groupIsDefined = true;

	    TOProfessional.setResearch_group(getResearch_group(TOProfessional
		    .getResearch_group().getResearch_groupcode()));
	}
	
	// we store if the research_group is defined for later use
	boolean _research_group_2IsDefined = false;

	if (TOProfessional.getResearch_group_2() != null
		&& TOProfessional.getResearch_group_2().getResearch_groupcode() != null) {
	    // if research_group is defined we replace the research_group in the
	    // DTO with its current state in the DB.
	    _research_group_2IsDefined = true;

	    TOProfessional.setResearch_group_2(getResearch_group(TOProfessional
		    .getResearch_group_2().getResearch_groupcode()));
	}
	
	// we store if the research_group is defined for later use
	boolean _research_group_3IsDefined = false;

	if (TOProfessional.getResearch_group_3() != null
		&& TOProfessional.getResearch_group_3().getResearch_groupcode() != null) {
	    // if research_group is defined we replace the research_group in the
	    // DTO with its current state in the DB.
	    _research_group_3IsDefined = true;

	    TOProfessional.setResearch_group_3(getResearch_group(TOProfessional
		    .getResearch_group_3().getResearch_groupcode()));
	}
	
	// we store if the research_group is defined for later use
	boolean _research_group_4IsDefined = false;

	if (TOProfessional.getResearch_group_4() != null
		&& TOProfessional.getResearch_group_4().getResearch_groupcode() != null) {
	    // if research_group is defined we replace the research_group in the
	    // DTO with its current state in the DB.
	    _research_group_4IsDefined = true;

	    TOProfessional.setResearch_group_4(getResearch_group(TOProfessional
		    .getResearch_group_4().getResearch_groupcode()));
	}

	// we store if the type_of_contract is defined for later use
	boolean _type_of_contractIsDefined = false;

	if (TOProfessional.getType_of_contract() != null
		&& TOProfessional.getType_of_contract()
			.getType_of_contractcode() != null) {
	    // if type_of_contract is defined we replace the type_of_contract in
	    // the DTO with its current state in the DB.
	    _type_of_contractIsDefined = true;

	    TOProfessional
		    .setType_of_contract(getType_of_contract(TOProfessional
			    .getType_of_contract().getType_of_contractcode()));
	}

	// we store if the location is defined for later use
	boolean _locationIsDefined = false;

	if (TOProfessional.getLocation() != null
		&& TOProfessional.getLocation().getLocationcode() != null) {
	    // if location is defined we replace the location in the DTO with
	    // its current state in the DB.
	    _locationIsDefined = true;

	    TOProfessional.setLocation(getLocation(TOProfessional.getLocation()
		    .getLocationcode()));
	}

	// we store if the position is defined for later use
	boolean _positionIsDefined = false;

	if (TOProfessional.getPosition() != null
		&& TOProfessional.getPosition().getPositioncode() != null) {
	    // if position is defined we replace the position in the DTO with
	    // its current state in the DB.
	    _positionIsDefined = true;

	    TOProfessional.setPosition(getPosition(TOProfessional.getPosition()
		    .getPositioncode()));
	}

	// we store if the professional_unit is defined for later use
	boolean _professional_unitIsDefined = false;

	if (TOProfessional.getProfessional_unit() != null
		&& TOProfessional.getProfessional_unit().getUnitcode() != null) {
	    // if professional_unit is defined we replace the professional_unit
	    // in the DTO with its current state in the DB.
	    _professional_unitIsDefined = true;

	    TOProfessional.setProfessional_unit(getUnit(TOProfessional
		    .getProfessional_unit().getUnitcode()));
	}
	
	// we store if the professional_unit is defined for later use
	boolean _professional_unit_2IsDefined = false;

	if (TOProfessional.getProfessional_unit_2() != null
		&& TOProfessional.getProfessional_unit_2().getUnitcode() != null) {
	    // if professional_unit is defined we replace the professional_unit
	    // in the DTO with its current state in the DB.
	    _professional_unit_2IsDefined = true;

	    TOProfessional.setProfessional_unit_2(getUnit(TOProfessional
		    .getProfessional_unit_2().getUnitcode()));
	}
	
	// we store if the professional_unit is defined for later use
	boolean _professional_unit_3IsDefined = false;

	if (TOProfessional.getProfessional_unit_3() != null
		&& TOProfessional.getProfessional_unit_3().getUnitcode() != null) {
	    // if professional_unit is defined we replace the professional_unit
	    // in the DTO with its current state in the DB.
	    _professional_unit_3IsDefined = true;

	    TOProfessional.setProfessional_unit_3(getUnit(TOProfessional
		    .getProfessional_unit_3().getUnitcode()));
	}
	
	// we store if the professional_unit is defined for later use
	boolean _professional_unit_4IsDefined = false;

	if (TOProfessional.getProfessional_unit_4() != null
		&& TOProfessional.getProfessional_unit_4().getUnitcode() != null) {
	    // if professional_unit is defined we replace the professional_unit
	    // in the DTO with its current state in the DB.
	    _professional_unit_4IsDefined = true;

	    TOProfessional.setProfessional_unit_4(getUnit(TOProfessional
		    .getProfessional_unit_4().getUnitcode()));
	}

	// we store if the payroll_institution is defined for later use
	boolean _payroll_institutionIsDefined = false;

	if (TOProfessional.getPayroll_institution() != null
		&& TOProfessional.getPayroll_institution()
			.getPayroll_institutioncode() != null) {
	    // if payroll_institution is defined we replace the
	    // payroll_institution in the DTO with its current state in the DB.
	    _payroll_institutionIsDefined = true;

	    TOProfessional
		    .setPayroll_institution(getPayroll_institution(TOProfessional
			    .getPayroll_institution()
			    .getPayroll_institutioncode()));
	}

	// we store if the payroll_institution_2 is defined for later use
	boolean _payroll_institution_2IsDefined = false;

	if (TOProfessional.getPayroll_institution_2() != null
		&& TOProfessional.getPayroll_institution_2()
			.getPayroll_institutioncode() != null) {
	    // if payroll_institution_2 is defined we replace the
	    // payroll_institution_2 in the DTO with its current state in the
	    // DB.
	    _payroll_institution_2IsDefined = true;

	    TOProfessional
		    .setPayroll_institution_2(getPayroll_institution(TOProfessional
			    .getPayroll_institution_2()
			    .getPayroll_institutioncode()));
	}

	/** 3. We create the new instance * */
	Professional professional = new Professional();

	/**
	 * 4. We set all the simple attributes (no associations) to the new
	 * instance *
	 */

	professional.setStart_date(TOProfessional.getStart_date());
	professional.setEnd_date(TOProfessional.getEnd_date());
	

	Professional currentPro = null;

	for (Professional pro : TOProfessional.getProfessional_personal()
		.getIprofessional_personal()) {
	    if (pro.isCurrent()) {
		currentPro = pro;
		break;
	    }
	}

	if (currentPro != null) {

	    if (TOProfessional.getEmail() == null
		    || TOProfessional.getEmail().equals("")) {
		professional.setEmail(currentPro.getEmail());
	    }

	    if (TOProfessional.getPhone() == null
		    || TOProfessional.getPhone().equals("")) {
		professional.setPhone(currentPro.getPhone());
	    }

	    if (TOProfessional.getMobile_long() == null
		    || TOProfessional.getMobile_long().equals("")) {
		professional.setMobile_long(currentPro.getMobile_long());
	    }

	    if (TOProfessional.getMobile_short() == null
		    || TOProfessional.getMobile_short().equals("")) {
		professional.setMobile_short(currentPro.getMobile_short());
	    }

	    if (TOProfessional.getLab_phone_number() == null
		    || TOProfessional.getLab_phone_number().equals("")) {
		professional.setLab_phone_number(currentPro
			.getLab_phone_number());
	    }

	    if (TOProfessional.getFax() == null
		    || TOProfessional.getFax().equals("")) {
		professional.setFax(currentPro.getFax());
	    }

	} else {
	    professional.setEmail(TOProfessional.getEmail());

	    professional.setPhone(TOProfessional.getPhone());

	    professional.setMobile_long(TOProfessional.getMobile_long());

	    professional.setMobile_short(TOProfessional.getMobile_short());

	    professional.setLab_phone_number(TOProfessional
		    .getLab_phone_number());

	    professional.setFax(TOProfessional.getFax());
	}

	/** 5. We set the code to the new instance * */
	try {
	    IdentifyManager_Plain im = IdentifyManager_Plain.singleton();

	    professional.setProfessionalcode(im.getId(TOProfessional));
	} catch (identifyException ie) {

	    log.error("Error en asignaciï¿½n de nuevo id en CreateProfessional",
		    ie);
	    throw new Error(ie.getMessage());
	}

	/** 6. We save the new instance to the DB* */
	HibernateUtil.getSession().save(professional);

	/**
	 * We associate the current object to the other objects (only in case
	 * that the associations where defined in the DTO) *
	 */

	if (_professional_personalIsDefined) {

	    if (TOProfessional.getProfessional_personal() != null) {

		TOProfessional.getProfessional_personal()
			.addIprofessional_personal(professional);
	    }

	    professional.setProfessional_personal(TOProfessional
		    .getProfessional_personal());
	}

	if (_research_groupIsDefined) {

	    if (TOProfessional.getResearch_group() != null) {

		TOProfessional.getResearch_group().addIresearch_group(
			professional);
	    }

	    professional.setResearch_group(TOProfessional.getResearch_group());
	}
	
	if (_research_group_2IsDefined) {

	    if (TOProfessional.getResearch_group_2() != null) {

		TOProfessional.getResearch_group_2().addIresearch_group_2(
			professional);
	    }

	    professional.setResearch_group_2(TOProfessional.getResearch_group_2());
	}
	
	if (_research_group_3IsDefined) {

	    if (TOProfessional.getResearch_group_3() != null) {

		TOProfessional.getResearch_group_3().addIresearch_group_3(
			professional);
	    }

	    professional.setResearch_group_3(TOProfessional.getResearch_group_3());
	}
	
	if (_research_group_4IsDefined) {

	    if (TOProfessional.getResearch_group_4() != null) {

		TOProfessional.getResearch_group_4().addIresearch_group_4(
			professional);
	    }

	    professional.setResearch_group_4(TOProfessional.getResearch_group_4());
	}
	
	

	if (_type_of_contractIsDefined) {

	    if (TOProfessional.getType_of_contract() != null) {

		TOProfessional.getType_of_contract().addItype_of_contract(
			professional);
	    }

	    professional.setType_of_contract(TOProfessional
		    .getType_of_contract());
	}

	if (_locationIsDefined) {

	    if (TOProfessional.getLocation() != null) {

		TOProfessional.getLocation().addIlocation(professional);
	    }

	    professional.setLocation(TOProfessional.getLocation());
	}

	if (_positionIsDefined) {

	    if (TOProfessional.getPosition() != null) {

		TOProfessional.getPosition().addIposition(professional);
	    }

	    professional.setPosition(TOProfessional.getPosition());
	}

	if (_professional_unitIsDefined) {

	    if (TOProfessional.getProfessional_unit() != null) {

		TOProfessional.getProfessional_unit().addIprofessional_unit(
			professional);
	    }

	    professional.setProfessional_unit(TOProfessional
		    .getProfessional_unit());
	}
	
	if (_professional_unit_2IsDefined) {

	    if (TOProfessional.getProfessional_unit_2() != null) {

		TOProfessional.getProfessional_unit_2().addIprofessional_unit_2(
			professional);
	    }

	    professional.setProfessional_unit_2(TOProfessional
		    .getProfessional_unit_2());
	}
	
	if (_professional_unit_3IsDefined) {

	    if (TOProfessional.getProfessional_unit_3() != null) {

		TOProfessional.getProfessional_unit_3().addIprofessional_unit_3(
			professional);
	    }

	    professional.setProfessional_unit_3(TOProfessional
		    .getProfessional_unit_3());
	}
	
	if (_professional_unit_4IsDefined) {

	    if (TOProfessional.getProfessional_unit_4() != null) {

		TOProfessional.getProfessional_unit_4().addIprofessional_unit_4(
			professional);
	    }

	    professional.setProfessional_unit_4(TOProfessional
		    .getProfessional_unit_4());
	}

	if (_payroll_institutionIsDefined) {

	    if (TOProfessional.getPayroll_institution() != null) {

		TOProfessional.getPayroll_institution()
			.addIpayroll_institution(professional);
	    }

	    professional.setPayroll_institution(TOProfessional
		    .getPayroll_institution());
	}

	if (_payroll_institution_2IsDefined) {

	    if (TOProfessional.getPayroll_institution_2() != null) {

		TOProfessional.getPayroll_institution_2()
			.addIpayroll_institution_2(professional);
	    }

	    professional.setPayroll_institution_2(TOProfessional
		    .getPayroll_institution_2());
	}

	if (professional.getLocation() != null) {
	    professional.setBuilding(professional.getLocation()
		    .getBuildingcode());
	}

	/** 7. We create an Audit message * */
	CreateCreationAuditmessage(user, professional);

	/** 8. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return professional;

    }

    private static boolean validateProfessional(Professional professional) {

	// Cell and developmental biology
	// * Structural and computational biology
	// * Molecular medicine
	// * Chemistry and molecular pharmacology
	// * Oncology

	String unitcodesString = MAINCONFIG
		.getString("unitcodesRequiringResearch_group");

	if (unitcodesString != null) {
	    String[] unitcodes = unitcodesString.split("\\s*(,|;|:)\\s*");

	    Set<String> set_of_codes = new HashSet();
	    Collections.addAll(set_of_codes, unitcodes);

	    boolean inTheSet = set_of_codes.contains(professional
		    .getProfessional_unit().getCode());

	    return !inTheSet
		    || (professional.getResearch_group() != null
			    && professional.getResearch_group().getCode() != null && !professional
			    .getResearch_group().getCode().equals(""));

	} else {
	    return true;
	}
    }

    /**
     * This method modifies the research group of a professional.
     * 
     * @param user
     *            The user who executes this use case
     * @param professional
     *            Professional (not DTO) to be modified.
     * @param newResearchGroup
     * @return the modified professional
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Professional UpdateProfessionalReseachGroup(Usuario user,
	    Professional professional, Research_group newResearchGroup)
	    throws InternalException, NoPermisosException {
	Research_group orlResearchGroup = professional.getResearch_group();
	if (orlResearchGroup == null && newResearchGroup == null)
	    return professional;

	if (orlResearchGroup != null
		&& newResearchGroup != null
		&& orlResearchGroup.getCode()
			.equals(newResearchGroup.getCode())) {
	    return professional;
	}

	if (orlResearchGroup != null) {
	    orlResearchGroup.removeIresearch_group(professional);
	}
	if (newResearchGroup != null) {
	    newResearchGroup.addIresearch_group(professional);
	}
	professional.setResearch_group(newResearchGroup);

	// UseCaseUtils.sendValidatingPersonalSettedToSupervisor(newResearchGroup
	// .getSupervisor(), professional.getProfessional_personal());

	return professional;

    }
    

    /**
     * This method modifies the research group of a professional.
     * 
     * @param user
     *            The user who executes this use case
     * @param professional
     *            Professional (not DTO) to be modified.
     * @param newResearchGroup
     * @return the modified professional
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Professional UpdateProfessionalReseachGroup_2(Usuario user,
	    Professional professional, Research_group newResearchGroup)
	    throws InternalException, NoPermisosException {
	Research_group orlResearchGroup = professional.getResearch_group_2();
	if (orlResearchGroup == null && newResearchGroup == null)
	    return professional;

	if (orlResearchGroup != null
		&& newResearchGroup != null
		&& orlResearchGroup.getCode()
			.equals(newResearchGroup.getCode())) {
	    return professional;
	}

	if (orlResearchGroup != null) {
	    orlResearchGroup.removeIresearch_group_2(professional);
	}
	if (newResearchGroup != null) {
	    newResearchGroup.addIresearch_group_2(professional);
	}
	professional.setResearch_group_2(newResearchGroup);

	// UseCaseUtils.sendValidatingPersonalSettedToSupervisor(newResearchGroup
	// .getSupervisor(), professional.getProfessional_personal());

	return professional;

    }
    
    /**
     * This method modifies the research group of a professional.
     * 
     * @param user
     *            The user who executes this use case
     * @param professional
     *            Professional (not DTO) to be modified.
     * @param newResearchGroup
     * @return the modified professional
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Professional UpdateProfessionalReseachGroup_3(Usuario user,
	    Professional professional, Research_group newResearchGroup)
	    throws InternalException, NoPermisosException {
	Research_group orlResearchGroup = professional.getResearch_group_3();
	if (orlResearchGroup == null && newResearchGroup == null)
	    return professional;

	if (orlResearchGroup != null
		&& newResearchGroup != null
		&& orlResearchGroup.getCode()
			.equals(newResearchGroup.getCode())) {
	    return professional;
	}

	if (orlResearchGroup != null) {
	    orlResearchGroup.removeIresearch_group_3(professional);
	}
	if (newResearchGroup != null) {
	    newResearchGroup.addIresearch_group_3(professional);
	}
	professional.setResearch_group_3(newResearchGroup);

	// UseCaseUtils.sendValidatingPersonalSettedToSupervisor(newResearchGroup
	// .getSupervisor(), professional.getProfessional_personal());

	return professional;

    }
    
    
    /**
     * This method modifies the research group of a professional.
     * 
     * @param user
     *            The user who executes this use case
     * @param professional
     *            Professional (not DTO) to be modified.
     * @param newResearchGroup
     * @return the modified professional
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Professional UpdateProfessionalReseachGroup_4(Usuario user,
	    Professional professional, Research_group newResearchGroup)
	    throws InternalException, NoPermisosException {
	Research_group orlResearchGroup = professional.getResearch_group_4();
	if (orlResearchGroup == null && newResearchGroup == null)
	    return professional;

	if (orlResearchGroup != null
		&& newResearchGroup != null
		&& orlResearchGroup.getCode()
			.equals(newResearchGroup.getCode())) {
	    return professional;
	}

	if (orlResearchGroup != null) {
	    orlResearchGroup.removeIresearch_group_4(professional);
	}
	if (newResearchGroup != null) {
	    newResearchGroup.addIresearch_group_4(professional);
	}
	professional.setResearch_group_4(newResearchGroup);

	// UseCaseUtils.sendValidatingPersonalSettedToSupervisor(newResearchGroup
	// .getSupervisor(), professional.getProfessional_personal());

	return professional;

    }

    /**
     * This method modifies a professional.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOProfessional
     *            Professional data transfer object (DTO) with the values of the
     *            modified instance. The code of this attribute indicates which
     *            professional will be modified.
     * @return the modified professional
     * @throws InternalException
     * @throws NoPermisosException
     * @throws ValidationFailedException
     */
    public static Professional UpdateProfessional(Usuario user,
	    Professional TOProfessional) throws InternalException,
	    NoPermisosException, ValidationFailedException {

	if (!validateProfessional(TOProfessional)) {
	    Map<String, List<String>> parameters = new Hashtable<String, List<String>>();
	    parameters.put("propertyError.required_attribute",
		    Arrays.asList(new String[] { "research_group" }));
	    throw new ValidationFailedException(parameters);
	}

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 3. We obtain form the DB the instance to modify * */
	Professional professional = getProfessional(TOProfessional
		.getProfessionalcode());

	/****/

	Personal personal = TOProfessional.getProfessional_personal();

	Professional pr = ObtainActiveProfessionalFromPersonal(personal);

	if (personal.getState().getCode().equals(Personalstate.ACTIVE_CODE)
		&& professional.equals(pr))
	    UseCaseUtils.updateLDAPFromModify(professional, TOProfessional,
		    null, null);

	/****/

	/**
	 * 2. For each association from the TOProfessional that are filled in
	 * the DTO we put the real objects from the DB. *
	 */

	// we store if the professional_personal is defined for later use
	boolean _professional_personalIsDefined = false;

	if (TOProfessional.getProfessional_personal() != null
		&& TOProfessional.getProfessional_personal().getPersonalcode() != null) {
	    // if professional_personal is defined we replace the
	    // professional_personal in the DTO with its current state in the
	    // DB.
	    _professional_personalIsDefined = true;

	    TOProfessional.setProfessional_personal(getPersonal(TOProfessional
		    .getProfessional_personal().getPersonalcode()));
	}

	// we store if the research_group is defined for later use
	boolean _research_groupIsDefined = false;

	if (TOProfessional.getResearch_group() != null
		&& TOProfessional.getResearch_group().getResearch_groupcode() != null) {
	    // if research_group is defined we replace the research_group in the
	    // DTO with its current state in the DB.
	    _research_groupIsDefined = true;

	    TOProfessional.setResearch_group(getResearch_group(TOProfessional
		    .getResearch_group().getResearch_groupcode()));
	}
	
	// we store if the research_group is defined for later use
	boolean _research_group_2IsDefined = false;

	if (TOProfessional.getResearch_group_2() != null
		&& TOProfessional.getResearch_group_2().getResearch_groupcode() != null) {
	    // if research_group is defined we replace the research_group in the
	    // DTO with its current state in the DB.
	    _research_group_2IsDefined = true;

	    TOProfessional.setResearch_group_2(getResearch_group(TOProfessional
		    .getResearch_group_2().getResearch_groupcode()));
	}
	
	// we store if the research_group is defined for later use
	boolean _research_group_3IsDefined = false;

	if (TOProfessional.getResearch_group_3() != null
		&& TOProfessional.getResearch_group_3().getResearch_groupcode() != null) {
	    // if research_group is defined we replace the research_group in the
	    // DTO with its current state in the DB.
	    _research_group_3IsDefined = true;

	    TOProfessional.setResearch_group_3(getResearch_group(TOProfessional
		    .getResearch_group_3().getResearch_groupcode()));
	}
	
	// we store if the research_group is defined for later use
	boolean _research_group_4IsDefined = false;

	if (TOProfessional.getResearch_group_4() != null
		&& TOProfessional.getResearch_group_4().getResearch_groupcode() != null) {
	    // if research_group is defined we replace the research_group in the
	    // DTO with its current state in the DB.
	    _research_group_4IsDefined = true;

	    TOProfessional.setResearch_group_4(getResearch_group(TOProfessional
		    .getResearch_group_4().getResearch_groupcode()));
	}

	// we store if the type_of_contract is defined for later use
	boolean _type_of_contractIsDefined = false;

	if (TOProfessional.getType_of_contract() != null
		&& TOProfessional.getType_of_contract()
			.getType_of_contractcode() != null) {
	    // if type_of_contract is defined we replace the type_of_contract in
	    // the DTO with its current state in the DB.
	    _type_of_contractIsDefined = true;

	    TOProfessional
		    .setType_of_contract(getType_of_contract(TOProfessional
			    .getType_of_contract().getType_of_contractcode()));
	}

	// we store if the location is defined for later use
	boolean _locationIsDefined = false;

	if (TOProfessional.getLocation() != null
		&& TOProfessional.getLocation().getLocationcode() != null) {
	    // if location is defined we replace the location in the DTO with
	    // its current state in the DB.
	    _locationIsDefined = true;

	    TOProfessional.setLocation(getLocation(TOProfessional.getLocation()
		    .getLocationcode()));
	}

	// we store if the position is defined for later use
	boolean _positionIsDefined = false;

	if (TOProfessional.getPosition() != null
		&& TOProfessional.getPosition().getPositioncode() != null) {
	    // if position is defined we replace the position in the DTO with
	    // its current state in the DB.
	    _positionIsDefined = true;

	    TOProfessional.setPosition(getPosition(TOProfessional.getPosition()
		    .getPositioncode()));
	}

	// we store if the professional_unit is defined for later use
	boolean _professional_unitIsDefined = false;

	if (TOProfessional.getProfessional_unit() != null
		&& TOProfessional.getProfessional_unit().getUnitcode() != null) {
	    // if professional_unit is defined we replace the professional_unit
	    // in the DTO with its current state in the DB.
	    _professional_unitIsDefined = true;

	    TOProfessional.setProfessional_unit(getUnit(TOProfessional
		    .getProfessional_unit().getUnitcode()));
	}
	
	// we store if the professional_unit is defined for later use
	boolean _professional_unit_2IsDefined = false;

	if (TOProfessional.getProfessional_unit_2() != null
		&& TOProfessional.getProfessional_unit_2().getUnitcode() != null) {
	    // if professional_unit is defined we replace the professional_unit
	    // in the DTO with its current state in the DB.
	    _professional_unit_2IsDefined = true;

	    TOProfessional.setProfessional_unit_2(getUnit(TOProfessional
		    .getProfessional_unit_2().getUnitcode()));
	}
	
	// we store if the professional_unit is defined for later use
	boolean _professional_unit_3IsDefined = false;

	if (TOProfessional.getProfessional_unit_3() != null
		&& TOProfessional.getProfessional_unit_3().getUnitcode() != null) {
	    // if professional_unit is defined we replace the professional_unit
	    // in the DTO with its current state in the DB.
	    _professional_unit_3IsDefined = true;

	    TOProfessional.setProfessional_unit_3(getUnit(TOProfessional
		    .getProfessional_unit_3().getUnitcode()));
	}
	
	// we store if the professional_unit is defined for later use
	boolean _professional_unit_4IsDefined = false;

	if (TOProfessional.getProfessional_unit_4() != null
		&& TOProfessional.getProfessional_unit_4().getUnitcode() != null) {
	    // if professional_unit is defined we replace the professional_unit
	    // in the DTO with its current state in the DB.
	    _professional_unit_4IsDefined = true;

	    TOProfessional.setProfessional_unit_4(getUnit(TOProfessional
		    .getProfessional_unit_4().getUnitcode()));
	}

	// we store if the payroll_institution is defined for later use
	boolean _payroll_institutionIsDefined = false;

	if (TOProfessional.getPayroll_institution() != null
		&& TOProfessional.getPayroll_institution()
			.getPayroll_institutioncode() != null) {
	    // if payroll_institution is defined we replace the
	    // payroll_institution in the DTO with its current state in the DB.
	    _payroll_institutionIsDefined = true;

	    TOProfessional
		    .setPayroll_institution(getPayroll_institution(TOProfessional
			    .getPayroll_institution()
			    .getPayroll_institutioncode()));
	}

	// we store if the payroll_institution_2 is defined for later use
	boolean _payroll_institution_2IsDefined = false;

	if (TOProfessional.getPayroll_institution_2() != null
		&& TOProfessional.getPayroll_institution_2()
			.getPayroll_institutioncode() != null) {
	    // if payroll_institution_2 is defined we replace the
	    // payroll_institution_2 in the DTO with its current state in the
	    // DB.
	    _payroll_institution_2IsDefined = true;

	    TOProfessional
		    .setPayroll_institution_2(getPayroll_institution(TOProfessional
			    .getPayroll_institution_2()
			    .getPayroll_institutioncode()));
	}

	/**
	 * 4. We set all the simple attributes (no associations) to the instance
	 * *
	 */

	professional.setStart_date(TOProfessional.getStart_date());
	professional.setEnd_date(TOProfessional.getEnd_date());
	

	professional.setEmail(TOProfessional.getEmail());

	professional.setPhone(TOProfessional.getPhone());

	professional.setMobile_long(TOProfessional.getMobile_long());

	professional.setMobile_short(TOProfessional.getMobile_short());

	professional.setLab_phone_number(TOProfessional.getLab_phone_number());

	professional.setFax(TOProfessional.getFax());

	/**
	 * 5. We set the DTO version to the modified object and we update it
	 * with the new values in the DB. We evict and update the instance to
	 * prevent concurrent modification *
	 */
	HibernateUtil.getSession().evict(professional);
	professional.setVersion(TOProfessional.getVersion());
	HibernateUtil.getSession().update(professional);

	/**
	 * We associate/disassociate the current object to the other objects
	 * (only in case that the associations where defined in the DTO) *
	 */

	if (_professional_personalIsDefined) {

	    if (professional.getProfessional_personal() != null) {

		professional.getProfessional_personal()
			.removeIprofessional_personal(professional);
	    }

	    if (TOProfessional.getProfessional_personal() != null) {

		TOProfessional.getProfessional_personal()
			.addIprofessional_personal(professional);
	    }

	    professional.setProfessional_personal(TOProfessional
		    .getProfessional_personal());
	}

	if (_research_groupIsDefined) {
	    UpdateProfessionalReseachGroup(user, professional,
		    TOProfessional.getResearch_group());
	}
	

	if (_research_group_2IsDefined) {
	    UpdateProfessionalReseachGroup_2(user, professional,
		    TOProfessional.getResearch_group_2());
	}
	
	if (_research_group_3IsDefined) {
	    UpdateProfessionalReseachGroup_3(user, professional,
		    TOProfessional.getResearch_group_3());
	}
	
	if (_research_group_4IsDefined) {
	    UpdateProfessionalReseachGroup_4(user, professional,
		    TOProfessional.getResearch_group_4());
	}

	if (_type_of_contractIsDefined) {

	    if (professional.getType_of_contract() != null) {

		professional.getType_of_contract().removeItype_of_contract(
			professional);
	    }

	    if (TOProfessional.getType_of_contract() != null) {

		TOProfessional.getType_of_contract().addItype_of_contract(
			professional);
	    }

	    professional.setType_of_contract(TOProfessional
		    .getType_of_contract());
	}

	if (_locationIsDefined) {

	    if (professional.getLocation() != null) {

		professional.getLocation().removeIlocation(professional);
	    }

	    if (TOProfessional.getLocation() != null) {

		TOProfessional.getLocation().addIlocation(professional);
	    }

	    professional.setLocation(TOProfessional.getLocation());
	}

	if (_positionIsDefined) {

	    if (professional.getPosition() != null) {

		professional.getPosition().removeIposition(professional);
	    }

	    if (TOProfessional.getPosition() != null) {

		TOProfessional.getPosition().addIposition(professional);
	    }

	    professional.setPosition(TOProfessional.getPosition());
	}

	if (_professional_unitIsDefined) {

	    if (professional.getProfessional_unit() != null) {

		professional.getProfessional_unit().removeIprofessional_unit(
			professional);
	    }

	    if (TOProfessional.getProfessional_unit() != null) {

		TOProfessional.getProfessional_unit().addIprofessional_unit(
			professional);
	    }

	    professional.setProfessional_unit(TOProfessional
		    .getProfessional_unit());
	}
	
	if (_professional_unit_2IsDefined) {

	    if (professional.getProfessional_unit_2() != null) {

		professional.getProfessional_unit_2().removeIprofessional_unit_2(
			professional);
	    }

	    if (TOProfessional.getProfessional_unit_2() != null) {

		TOProfessional.getProfessional_unit_2().addIprofessional_unit_2(
			professional);
	    }

	    professional.setProfessional_unit_2(TOProfessional
		    .getProfessional_unit_2());
	}
	
	if (_professional_unit_3IsDefined) {

	    if (professional.getProfessional_unit_3() != null) {

		professional.getProfessional_unit_3().removeIprofessional_unit_3(
			professional);
	    }

	    if (TOProfessional.getProfessional_unit_3() != null) {

		TOProfessional.getProfessional_unit_3().addIprofessional_unit_3(
			professional);
	    }

	    professional.setProfessional_unit_3(TOProfessional
		    .getProfessional_unit_3());
	}
	
	if (_professional_unit_4IsDefined) {

	    if (professional.getProfessional_unit_4() != null) {

		professional.getProfessional_unit_4().removeIprofessional_unit_4(
			professional);
	    }

	    if (TOProfessional.getProfessional_unit_4() != null) {

		TOProfessional.getProfessional_unit_4().addIprofessional_unit_4(
			professional);
	    }

	    professional.setProfessional_unit_4(TOProfessional
		    .getProfessional_unit_4());
	}

	if (_payroll_institutionIsDefined) {

	    if (professional.getPayroll_institution() != null) {

		professional.getPayroll_institution()
			.removeIpayroll_institution(professional);
	    }

	    if (TOProfessional.getPayroll_institution() != null) {

		TOProfessional.getPayroll_institution()
			.addIpayroll_institution(professional);
	    }

	    professional.setPayroll_institution(TOProfessional
		    .getPayroll_institution());
	}

	if (_payroll_institution_2IsDefined) {

	    if (professional.getPayroll_institution_2() != null) {

		professional.getPayroll_institution_2()
			.removeIpayroll_institution_2(professional);
	    }

	    if (TOProfessional.getPayroll_institution_2() != null) {

		TOProfessional.getPayroll_institution_2()
			.addIpayroll_institution_2(professional);
	    }

	    professional.setPayroll_institution_2(TOProfessional
		    .getPayroll_institution_2());
	}

	if (professional.getLocation() != null) {
	    professional.setBuilding(professional.getLocation()
		    .getBuildingcode());
	} else {
	    professional.setBuilding(null);
	}

	/** 6. We create an Audit message * */
	CreateModificationAuditmessage(user, professional);

	/** 7. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return professional;
    }

    /**
     * This method removes a professional.
     * 
     * @param user
     *            The user who executes this use case
     * @param professionalcode
     *            Code of the professional to be removed
     * @throws NoPermisosException
     */
    public static void RemoveProfessional(Usuario user, String professionalcode)
	    throws NoPermisosException {
	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain the object to delete form the DB. * */
	Professional professional = getProfessional(professionalcode);

	/** 3. We mark it as deleted. * */
	professional.setDeleted(Boolean.TRUE);

	/** 4. We create an Audit message * */
	CreateRemovealAuditmessage(user, professional);

	/** 5. We commit the DB transaction. * */
	HibernateUtil.commitTransaction();
    }

    public static String SetCurrentProfessional(Usuario user,
	    String professionalcode) throws NoPermisosException {

	String irbCodeString = MAINCONFIG
		.getString("irbPayrollInstitutionCode");

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain the object to delete form the DB. * */
	Professional professional = getProfessional(professionalcode);

	boolean oldValue = professional.isCurrent();

	Set<Professional> lineasDeProfessional = professional
		.getProfessional_personal().getIprofessional_personal();

	for (Professional linea : lineasDeProfessional) {
	    linea.setCurrent(false);
	}

	professional.setCurrent(!oldValue);

	if (professional.isCurrent()
		&& (professional.getPayroll_institution() != null && professional
			.getPayroll_institution().getCode()
			.equals(irbCodeString))) {
	    Irbholidayinfo holidayinfo = CreateOrModifyIrbholidayinfo(user,
		    professional);

	    if (holidayinfo != null) {
		RecalculateHolidaysFromPersonal(holidayinfo);
	    }
	}

	HibernateUtil.commitTransaction();

	return professional.getProfessional_personal().getPersonalcode();
    }

    /**
     * This method obtains one instance of professional given its code.
     * 
     * @param user
     *            The user who executes this use case
     * @param professionalcode
     *            Code of the professional to be obtained
     * @return Professional with the given code.
     */
    public static Professional ObtainProfessional(Usuario user,
	    String professionalcode) {

	/**
	 * 1. We obtain the object from the DB using the private getter and we
	 * return it. *
	 */

	Professional professional = getProfessional(professionalcode);
	return professional;
    }

    /**
     * This method obtains all instances of Professional, given a
     * list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     */
    public static Pair<Integer, List<Professional>> ObtainAllProfessional(
	    Usuario user, ListConfigurator configurator) {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(
		Professional.class);

	// we only want to obtain the non deleted objects
	crit.add(Expression.eq("deleted", Boolean.FALSE));

	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	List<Professional> professionals = (List<Professional>) crit.list();

	Pair<Integer, List<Professional>> pair = new Pair<Integer, List<Professional>>(
		count, professionals);

	return pair;
    }

    /**
     * This method creates a Location.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOLocation
     *            Location data transfer object (DTO) with the values of the new
     *            instance.
     * @return the new location created with this Use Case
     * @throws InternalException
     * @throws ValidationFailedException
     * @throws NoPermisosException
     */
    public static Location CreateLocation(Usuario user, Location TOLocation)
	    throws InternalException, ValidationFailedException,
	    NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	if (getLocation(TOLocation.getLocationcode()) != null) {
	    Map<String, List<String>> parameters = new Hashtable<String, List<String>>();
	    parameters.put("propertyError.existingValue",
		    Arrays.asList(new String[] { "locationcode" }));
	    throw new ValidationFailedException(parameters);
	}

	/** 2. We create the new instance * */
	Location location = new Location();

	/**
	 * 3. We set all the simple attributes (no associations) to the new
	 * instance *
	 */

	location.setDescription(TOLocation.getDescription());

	/** 4. We set the code to the new instance * */
	location.setCode(TOLocation.getCode());

	/** 5. We save the new instance to the DB* */
	HibernateUtil.getSession().save(location);

	/** 6. We create an Audit message * */
	CreateCreationAuditmessage(user, location);

	/** 7. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return location;
    }

    public static Location updateLocationCode(Usuario user,
	    String locationCode, String newLocationCode)
	    throws ValidationFailedException, NoPermisosException {
	testIsHHRR(user);

	if (newLocationCode.equalsIgnoreCase(locationCode))
	    return getLocation(locationCode);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain form the DB the instance to modify * */
	Location location = getLocation(locationCode);

	if (getLocation(newLocationCode) != null) {
	    Map<String, List<String>> parameters = new Hashtable<String, List<String>>();
	    parameters.put("propertyError.existingValue",
		    Arrays.asList(new String[] { "locationcode" }));
	    throw new ValidationFailedException(parameters);
	}

	Location newLocation = new Location();
	newLocation.setLocationcode(newLocationCode);
	newLocation.setDescription(location.getDescription());
	newLocation.setBuildingcode(location.getBuildingcode());
	for (Professional prof : location.getIlocation()) {
	    prof.setLocation(newLocation);
	}
	newLocation.setIlocation(location.getIlocation());

	HibernateUtil.getSession().save(newLocation);
	HibernateUtil.getSession().delete(location);

	CreateAuditmessage(user, "auditmessage.locationCodeChange",
		new String[] { location.getCode(), newLocation.getCode() },
		MODIFY_MESSAGE);

	HibernateUtil.commitTransaction();

	return newLocation;
    }

    /**
     * This method modifies a location.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOLocation
     *            Location data transfer object (DTO) with the values of the
     *            modified instance. The code of this attribute indicates which
     *            location will be modified.
     * @return the modified location
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Location UpdateLocation(Usuario user, Location TOLocation)
	    throws InternalException, NoPermisosException {
	testIsHHRR(user);
	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain form the DB the instance to modify * */
	Location location = getLocation(TOLocation.getLocationcode());
	;

	/**
	 * 3. We set all the simple attributes (no associations) to the instance
	 * *
	 */

	location.setDescription(TOLocation.getDescription());

	/**
	 * 4. We set the DTO version to the modified object and we update it
	 * with the new values in the DB. We evict and update the instance to
	 * prevent concurrent modification *
	 */
	HibernateUtil.getSession().evict(location);
	location.setVersion(TOLocation.getVersion());
	HibernateUtil.getSession().update(location);

	/** 5. We create an Audit message * */
	CreateModificationAuditmessage(user, location);

	/** 6. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return location;
    }

    /**
     * This method removes a location.
     * 
     * @param user
     *            The user who executes this use case
     * @param locationcode
     *            Code of the location to be removed
     * @throws NoPermisosException
     */
    public static void RemoveLocation(Usuario user, String locationcode)
	    throws NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain the object to delete form the DB. * */
	Location location = getLocation(locationcode);

	/** 3. We mark it as deleted. * */
	location.setDeleted(Boolean.TRUE);

	/** 4. We create an Audit message * */
	CreateRemovealAuditmessage(user, location);

	/** 5. We commit the DB transaction. * */
	HibernateUtil.commitTransaction();
    }

    /**
     * This method obtains one instance of location given its code.
     * 
     * @param user
     *            The user who executes this use case
     * @param locationcode
     *            Code of the location to be obtained
     * @return Location with the given code.
     */
    public static Location ObtainLocation(Usuario user, String locationcode) {

	/**
	 * 1. We obtain the object from the DB using the private getter and we
	 * return it. *
	 */

	Location location = getLocation(locationcode);
	return location;
    }

    /**
     * This method obtains all instances of Location, given a list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     */
    public static Pair<Integer, List<Location>> ObtainAllLocation(Usuario user,
	    ListConfigurator configurator) {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(
		Location.class);

	// we only want to obtain the non deleted objects
	crit.add(Expression.eq("deleted", Boolean.FALSE));

	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	List<Location> locations = (List<Location>) crit.list();

	Pair<Integer, List<Location>> pair = new Pair<Integer, List<Location>>(
		count, locations);

	return pair;
    }

    /**
     * This method creates a Type_of_education.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOType_of_education
     *            Type_of_education data transfer object (DTO) with the values
     *            of the new instance.
     * @return the new type_of_education created with this Use Case
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Type_of_education CreateType_of_education(Usuario user,
	    Type_of_education TOType_of_education) throws InternalException,
	    NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We create the new instance * */
	Type_of_education type_of_education = new Type_of_education();

	/**
	 * 3. We set all the simple attributes (no associations) to the new
	 * instance *
	 */

	type_of_education.setDescription(TOType_of_education.getDescription());

	/** 4. We set the code to the new instance * */
	try {
	    IdentifyManager_Plain im = IdentifyManager_Plain.singleton();

	    type_of_education.setType_of_educationcode(im
		    .getId(TOType_of_education));
	} catch (identifyException ie) {

	    log.error(
		    "Error en asignaciï¿½n de nuevo id en CreateType_of_education",
		    ie);
	    throw new Error(ie.getMessage());
	}

	/** 5. We save the new instance to the DB* */
	HibernateUtil.getSession().save(type_of_education);

	/** 6. We create an Audit message * */
	CreateCreationAuditmessage(user, type_of_education);

	/** 7. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return type_of_education;
    }

    /**
     * This method modifies a type_of_education.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOType_of_education
     *            Type_of_education data transfer object (DTO) with the values
     *            of the modified instance. The code of this attribute indicates
     *            which type_of_education will be modified.
     * @return the modified type_of_education
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Type_of_education UpdateType_of_education(Usuario user,
	    Type_of_education TOType_of_education) throws InternalException,
	    NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain form the DB the instance to modify * */
	Type_of_education type_of_education = getType_of_education(TOType_of_education
		.getType_of_educationcode());
	;

	/**
	 * 3. We set all the simple attributes (no associations) to the instance
	 * *
	 */

	type_of_education.setDescription(TOType_of_education.getDescription());

	/**
	 * 4. We set the DTO version to the modified object and we update it
	 * with the new values in the DB. We evict and update the instance to
	 * prevent concurrent modification *
	 */
	HibernateUtil.getSession().evict(type_of_education);
	type_of_education.setVersion(TOType_of_education.getVersion());
	HibernateUtil.getSession().update(type_of_education);

	/** 5. We create an Audit message * */
	CreateModificationAuditmessage(user, type_of_education);

	/** 6. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return type_of_education;
    }

    /**
     * This method removes a type_of_education.
     * 
     * @param user
     *            The user who executes this use case
     * @param type_of_educationcode
     *            Code of the type_of_education to be removed
     * @throws NoPermisosException
     */
    public static void RemoveType_of_education(Usuario user,
	    String type_of_educationcode) throws NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain the object to delete form the DB. * */
	Type_of_education type_of_education = getType_of_education(type_of_educationcode);

	/** 3. We mark it as deleted. * */
	type_of_education.setDeleted(Boolean.TRUE);

	/** 4. We create an Audit message * */
	CreateRemovealAuditmessage(user, type_of_education);

	/** 5. We commit the DB transaction. * */
	HibernateUtil.commitTransaction();
    }

    /**
     * This method obtains one instance of type_of_education given its code.
     * 
     * @param user
     *            The user who executes this use case
     * @param type_of_educationcode
     *            Code of the type_of_education to be obtained
     * @return Type_of_education with the given code.
     */
    public static Type_of_education ObtainType_of_education(Usuario user,
	    String type_of_educationcode) {

	/**
	 * 1. We obtain the object from the DB using the private getter and we
	 * return it. *
	 */

	Type_of_education type_of_education = getType_of_education(type_of_educationcode);
	return type_of_education;
    }

    /**
     * This method obtains all instances of Type_of_education, given a
     * list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     */
    public static Pair<Integer, List<Type_of_education>> ObtainAllType_of_education(
	    Usuario user, ListConfigurator configurator) {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(
		Type_of_education.class);

	// we only want to obtain the non deleted objects
	crit.add(Expression.eq("deleted", Boolean.FALSE));

	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	List<Type_of_education> type_of_educations = (List<Type_of_education>) crit
		.list();

	Pair<Integer, List<Type_of_education>> pair = new Pair<Integer, List<Type_of_education>>(
		count, type_of_educations);

	return pair;
    }

    /**
     * This method creates a Benefits.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOBenefits
     *            Benefits data transfer object (DTO) with the values of the new
     *            instance.
     * @return the new benefits created with this Use Case
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Benefits CreateBenefits(Usuario user, Benefits TOBenefits)
	    throws InternalException, NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/**
	 * 2. For each association from the TOBenefits that are filled in the
	 * DTO we put the real objects from the DB. *
	 */

	// we store if the benefits_personal is defined for later use
	boolean _benefits_personalIsDefined = false;

	if (TOBenefits.getBenefits_personal() != null
		&& TOBenefits.getBenefits_personal().getPersonalcode() != null) {
	    // if benefits_personal is defined we replace the benefits_personal
	    // in the DTO with its current state in the DB.
	    _benefits_personalIsDefined = true;

	    TOBenefits.setBenefits_personal(getPersonal(TOBenefits
		    .getBenefits_personal().getPersonalcode()));
	}

	// we store if the type_of_benefit is defined for later use
	boolean _type_of_benefitIsDefined = false;

	if (TOBenefits.getType_of_benefit() != null
		&& TOBenefits.getType_of_benefit().getType_of_benefitcode() != null) {
	    // if type_of_benefit is defined we replace the type_of_benefit in
	    // the DTO with its current state in the DB.
	    _type_of_benefitIsDefined = true;

	    TOBenefits.setType_of_benefit(getType_of_benefit(TOBenefits
		    .getType_of_benefit().getType_of_benefitcode()));
	}

	/** 3. We create the new instance * */
	Benefits benefits = new Benefits();

	/**
	 * 4. We set all the simple attributes (no associations) to the new
	 * instance *
	 */

	benefits.setStart_date(TOBenefits.getStart_date());

	benefits.setEnd_date(TOBenefits.getEnd_date());

	benefits.setDescription(TOBenefits.getDescription());

	/** 5. We set the code to the new instance * */
	try {
	    IdentifyManager_Plain im = IdentifyManager_Plain.singleton();

	    benefits.setBenefitscode(im.getId(TOBenefits));
	} catch (identifyException ie) {

	    log.error("Error en asignaciï¿½n de nuevo id en CreateBenefits", ie);
	    throw new Error(ie.getMessage());
	}

	/** 6. We save the new instance to the DB* */
	HibernateUtil.getSession().save(benefits);

	/**
	 * We associate the current object to the other objects (only in case
	 * that the associations where defined in the DTO) *
	 */

	if (_benefits_personalIsDefined) {

	    if (TOBenefits.getBenefits_personal() != null) {

		TOBenefits.getBenefits_personal().addIbenefits_personal(
			benefits);
	    }

	    benefits.setBenefits_personal(TOBenefits.getBenefits_personal());
	}

	if (_type_of_benefitIsDefined) {

	    if (TOBenefits.getType_of_benefit() != null) {

		TOBenefits.getType_of_benefit().addItype_of_benefit(benefits);
	    }

	    benefits.setType_of_benefit(TOBenefits.getType_of_benefit());
	}

	/** 7. We create an Audit message * */
	CreateCreationAuditmessage(user, benefits);

	/** 8. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return benefits;
    }

    /**
     * This method modifies a benefits.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOBenefits
     *            Benefits data transfer object (DTO) with the values of the
     *            modified instance. The code of this attribute indicates which
     *            benefits will be modified.
     * @return the modified benefits
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Benefits UpdateBenefits(Usuario user, Benefits TOBenefits)
	    throws InternalException, NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/**
	 * 2. For each association from the TOBenefits that are filled in the
	 * DTO we put the real objects from the DB. *
	 */

	// we store if the benefits_personal is defined for later use
	boolean _benefits_personalIsDefined = false;

	if (TOBenefits.getBenefits_personal() != null
		&& TOBenefits.getBenefits_personal().getPersonalcode() != null) {
	    // if benefits_personal is defined we replace the benefits_personal
	    // in the DTO with its current state in the DB.
	    _benefits_personalIsDefined = true;

	    TOBenefits.setBenefits_personal(getPersonal(TOBenefits
		    .getBenefits_personal().getPersonalcode()));
	}

	// we store if the type_of_benefit is defined for later use
	boolean _type_of_benefitIsDefined = false;

	if (TOBenefits.getType_of_benefit() != null
		&& TOBenefits.getType_of_benefit().getType_of_benefitcode() != null) {
	    // if type_of_benefit is defined we replace the type_of_benefit in
	    // the DTO with its current state in the DB.
	    _type_of_benefitIsDefined = true;

	    TOBenefits.setType_of_benefit(getType_of_benefit(TOBenefits
		    .getType_of_benefit().getType_of_benefitcode()));
	}

	/** 3. We obtain form the DB the instance to modify * */
	Benefits benefits = getBenefits(TOBenefits.getBenefitscode());
	;

	/**
	 * 4. We set all the simple attributes (no associations) to the instance
	 * *
	 */

	benefits.setStart_date(TOBenefits.getStart_date());

	benefits.setEnd_date(TOBenefits.getEnd_date());

	benefits.setDescription(TOBenefits.getDescription());

	/**
	 * 5. We set the DTO version to the modified object and we update it
	 * with the new values in the DB. We evict and update the instance to
	 * prevent concurrent modification *
	 */
	HibernateUtil.getSession().evict(benefits);
	benefits.setVersion(TOBenefits.getVersion());
	HibernateUtil.getSession().update(benefits);

	/**
	 * We associate/disassociate the current object to the other objects
	 * (only in case that the associations where defined in the DTO) *
	 */

	if (_benefits_personalIsDefined) {

	    if (benefits.getBenefits_personal() != null) {

		benefits.getBenefits_personal().removeIbenefits_personal(
			benefits);
	    }

	    if (TOBenefits.getBenefits_personal() != null) {

		TOBenefits.getBenefits_personal().addIbenefits_personal(
			benefits);
	    }

	    benefits.setBenefits_personal(TOBenefits.getBenefits_personal());
	}

	if (_type_of_benefitIsDefined) {

	    if (benefits.getType_of_benefit() != null) {

		benefits.getType_of_benefit().removeItype_of_benefit(benefits);
	    }

	    if (TOBenefits.getType_of_benefit() != null) {

		TOBenefits.getType_of_benefit().addItype_of_benefit(benefits);
	    }

	    benefits.setType_of_benefit(TOBenefits.getType_of_benefit());
	}

	/** 6. We create an Audit message * */
	CreateModificationAuditmessage(user, benefits);

	/** 7. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return benefits;
    }

    /**
     * This method removes a benefits.
     * 
     * @param user
     *            The user who executes this use case
     * @param benefitscode
     *            Code of the benefits to be removed
     * @throws NoPermisosException
     */
    public static void RemoveBenefits(Usuario user, String benefitscode)
	    throws NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain the object to delete form the DB. * */
	Benefits benefits = getBenefits(benefitscode);

	/** 3. We mark it as deleted. * */
	benefits.setDeleted(Boolean.TRUE);

	/** 4. We create an Audit message * */
	CreateRemovealAuditmessage(user, benefits);

	/** 5. We commit the DB transaction. * */
	HibernateUtil.commitTransaction();
    }

    /**
     * This method obtains one instance of benefits given its code.
     * 
     * @param user
     *            The user who executes this use case
     * @param benefitscode
     *            Code of the benefits to be obtained
     * @return Benefits with the given code.
     */
    public static Benefits ObtainBenefits(Usuario user, String benefitscode) {

	/**
	 * 1. We obtain the object from the DB using the private getter and we
	 * return it. *
	 */

	Benefits benefits = getBenefits(benefitscode);
	return benefits;
    }

    /**
     * This method obtains all instances of Benefits, given a list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     */
    public static Pair<Integer, List<Benefits>> ObtainAllBenefits(Usuario user,
	    ListConfigurator configurator) {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(
		Benefits.class);

	// we only want to obtain the non deleted objects
	crit.add(Expression.eq("deleted", Boolean.FALSE));

	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	List<Benefits> benefitss = (List<Benefits>) crit.list();

	Pair<Integer, List<Benefits>> pair = new Pair<Integer, List<Benefits>>(
		count, benefitss);

	return pair;
    }

    /**
     * This method creates a Type_of_benefit.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOType_of_benefit
     *            Type_of_benefit data transfer object (DTO) with the values of
     *            the new instance.
     * @return the new type_of_benefit created with this Use Case
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Type_of_benefit CreateType_of_benefit(Usuario user,
	    Type_of_benefit TOType_of_benefit) throws InternalException,
	    NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We create the new instance * */
	Type_of_benefit type_of_benefit = new Type_of_benefit();

	/**
	 * 3. We set all the simple attributes (no associations) to the new
	 * instance *
	 */

	type_of_benefit.setDescription(TOType_of_benefit.getDescription());

	/** 4. We set the code to the new instance * */
	try {
	    IdentifyManager_Plain im = IdentifyManager_Plain.singleton();

	    type_of_benefit.setType_of_benefitcode(im.getId(TOType_of_benefit));
	} catch (identifyException ie) {

	    log.error(
		    "Error en asignaciï¿½n de nuevo id en CreateType_of_benefit",
		    ie);
	    throw new Error(ie.getMessage());
	}

	/** 5. We save the new instance to the DB* */
	HibernateUtil.getSession().save(type_of_benefit);

	/** 6. We create an Audit message * */
	CreateCreationAuditmessage(user, type_of_benefit);

	/** 7. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return type_of_benefit;
    }

    /**
     * This method modifies a type_of_benefit.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOType_of_benefit
     *            Type_of_benefit data transfer object (DTO) with the values of
     *            the modified instance. The code of this attribute indicates
     *            which type_of_benefit will be modified.
     * @return the modified type_of_benefit
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Type_of_benefit UpdateType_of_benefit(Usuario user,
	    Type_of_benefit TOType_of_benefit) throws InternalException,
	    NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain form the DB the instance to modify * */
	Type_of_benefit type_of_benefit = getType_of_benefit(TOType_of_benefit
		.getType_of_benefitcode());
	;

	/**
	 * 3. We set all the simple attributes (no associations) to the instance
	 * *
	 */

	type_of_benefit.setDescription(TOType_of_benefit.getDescription());

	/**
	 * 4. We set the DTO version to the modified object and we update it
	 * with the new values in the DB. We evict and update the instance to
	 * prevent concurrent modification *
	 */
	HibernateUtil.getSession().evict(type_of_benefit);
	type_of_benefit.setVersion(TOType_of_benefit.getVersion());
	HibernateUtil.getSession().update(type_of_benefit);

	/** 5. We create an Audit message * */
	CreateModificationAuditmessage(user, type_of_benefit);

	/** 6. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return type_of_benefit;
    }

    /**
     * This method removes a type_of_benefit.
     * 
     * @param user
     *            The user who executes this use case
     * @param type_of_benefitcode
     *            Code of the type_of_benefit to be removed
     * @throws NoPermisosException
     */
    public static void RemoveType_of_benefit(Usuario user,
	    String type_of_benefitcode) throws NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain the object to delete form the DB. * */
	Type_of_benefit type_of_benefit = getType_of_benefit(type_of_benefitcode);

	/** 3. We mark it as deleted. * */
	type_of_benefit.setDeleted(Boolean.TRUE);

	/** 4. We create an Audit message * */
	CreateRemovealAuditmessage(user, type_of_benefit);

	/** 5. We commit the DB transaction. * */
	HibernateUtil.commitTransaction();
    }

    /**
     * This method obtains one instance of type_of_benefit given its code.
     * 
     * @param user
     *            The user who executes this use case
     * @param type_of_benefitcode
     *            Code of the type_of_benefit to be obtained
     * @return Type_of_benefit with the given code.
     */
    public static Type_of_benefit ObtainType_of_benefit(Usuario user,
	    String type_of_benefitcode) {

	/**
	 * 1. We obtain the object from the DB using the private getter and we
	 * return it. *
	 */

	Type_of_benefit type_of_benefit = getType_of_benefit(type_of_benefitcode);
	return type_of_benefit;
    }

    /**
     * This method obtains all instances of Type_of_benefit, given a
     * list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     */
    public static Pair<Integer, List<Type_of_benefit>> ObtainAllType_of_benefit(
	    Usuario user, ListConfigurator configurator) {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(
		Type_of_benefit.class);

	// we only want to obtain the non deleted objects
	crit.add(Expression.eq("deleted", Boolean.FALSE));

	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	List<Type_of_benefit> type_of_benefits = (List<Type_of_benefit>) crit
		.list();

	Pair<Integer, List<Type_of_benefit>> pair = new Pair<Integer, List<Type_of_benefit>>(
		count, type_of_benefits);

	return pair;
    }

    /**
     * This method creates a Grant.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOGrant
     *            Grant data transfer object (DTO) with the values of the new
     *            instance.
     * @return the new grant created with this Use Case
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Grant CreateGrant(Usuario user, Grant TOGrant)
	    throws InternalException, NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/**
	 * 2. For each association from the TOGrant that are filled in the DTO
	 * we put the real objects from the DB. *
	 */

	// we store if the type_of_grant is defined for later use
	boolean _type_of_grantIsDefined = false;

	if (TOGrant.getType_of_grant() != null
		&& TOGrant.getType_of_grant().getType_of_grantcode() != null) {
	    // if type_of_grant is defined we replace the type_of_grant in the
	    // DTO with its current state in the DB.
	    _type_of_grantIsDefined = true;

	    TOGrant.setType_of_grant(getType_of_grant(TOGrant
		    .getType_of_grant().getType_of_grantcode()));
	}

	/** 3. We create the new instance * */
	Grant grant = new Grant();

	/**
	 * 4. We set all the simple attributes (no associations) to the new
	 * instance *
	 */

	grant.setDescription(TOGrant.getDescription());

	grant.setIs_irbs(TOGrant.isIs_irbs());

	/** 5. We set the code to the new instance * */
	try {
	    IdentifyManager_Plain im = IdentifyManager_Plain.singleton();

	    grant.setGrantcode(im.getId(TOGrant));
	} catch (identifyException ie) {

	    log.error("Error en asignaciï¿½n de nuevo id en CreateGrant", ie);
	    throw new Error(ie.getMessage());
	}

	/** 6. We save the new instance to the DB* */
	HibernateUtil.getSession().save(grant);

	/**
	 * We associate the current object to the other objects (only in case
	 * that the associations where defined in the DTO) *
	 */

	if (_type_of_grantIsDefined) {

	    if (TOGrant.getType_of_grant() != null) {

		TOGrant.getType_of_grant().addItype_of_grant(grant);
	    }

	    grant.setType_of_grant(TOGrant.getType_of_grant());
	}

	/** 7. We create an Audit message * */
	CreateCreationAuditmessage(user, grant);

	/** 8. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return grant;
    }

    /**
     * This method modifies a grant.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOGrant
     *            Grant data transfer object (DTO) with the values of the
     *            modified instance. The code of this attribute indicates which
     *            grant will be modified.
     * @return the modified grant
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Grant UpdateGrant(Usuario user, Grant TOGrant)
	    throws InternalException, NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/**
	 * 2. For each association from the TOGrant that are filled in the DTO
	 * we put the real objects from the DB. *
	 */

	// we store if the type_of_grant is defined for later use
	boolean _type_of_grantIsDefined = false;

	if (TOGrant.getType_of_grant() != null
		&& TOGrant.getType_of_grant().getType_of_grantcode() != null) {
	    // if type_of_grant is defined we replace the type_of_grant in the
	    // DTO with its current state in the DB.
	    _type_of_grantIsDefined = true;

	    TOGrant.setType_of_grant(getType_of_grant(TOGrant
		    .getType_of_grant().getType_of_grantcode()));
	}

	/** 3. We obtain form the DB the instance to modify * */
	Grant grant = getGrant(TOGrant.getGrantcode());
	;

	/**
	 * 4. We set all the simple attributes (no associations) to the instance
	 * *
	 */

	grant.setDescription(TOGrant.getDescription());
	grant.setIs_irbs(TOGrant.isIs_irbs());

	/**
	 * 5. We set the DTO version to the modified object and we update it
	 * with the new values in the DB. We evict and update the instance to
	 * prevent concurrent modification *
	 */
	HibernateUtil.getSession().evict(grant);
	grant.setVersion(TOGrant.getVersion());
	HibernateUtil.getSession().update(grant);

	/**
	 * We associate/disassociate the current object to the other objects
	 * (only in case that the associations where defined in the DTO) *
	 */

	if (_type_of_grantIsDefined) {

	    if (grant.getType_of_grant() != null) {

		grant.getType_of_grant().removeItype_of_grant(grant);
	    }

	    if (TOGrant.getType_of_grant() != null) {

		TOGrant.getType_of_grant().addItype_of_grant(grant);
	    }

	    grant.setType_of_grant(TOGrant.getType_of_grant());
	}

	/** 6. We create an Audit message * */
	CreateModificationAuditmessage(user, grant);

	/** 7. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return grant;
    }

    /**
     * This method removes a grant.
     * 
     * @param user
     *            The user who executes this use case
     * @param grantcode
     *            Code of the grant to be removed
     * @throws NoPermisosException
     */
    public static void RemoveGrant(Usuario user, String grantcode)
	    throws NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain the object to delete form the DB. * */
	Grant grant = getGrant(grantcode);

	/** 3. We mark it as deleted. * */
	grant.setDeleted(Boolean.TRUE);

	/** 4. We create an Audit message * */
	CreateRemovealAuditmessage(user, grant);

	/** 5. We commit the DB transaction. * */
	HibernateUtil.commitTransaction();
    }

    /**
     * This method obtains one instance of grant given its code.
     * 
     * @param user
     *            The user who executes this use case
     * @param grantcode
     *            Code of the grant to be obtained
     * @return Grant with the given code.
     */
    public static Grant ObtainGrant(Usuario user, String grantcode) {

	/**
	 * 1. We obtain the object from the DB using the private getter and we
	 * return it. *
	 */

	Grant grant = getGrant(grantcode);
	return grant;
    }

    /**
     * This method obtains all instances of Grant, given a list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     */
    public static Pair<Integer, List<Grant>> ObtainAllGrant(Usuario user,
	    ListConfigurator configurator) {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(Grant.class);

	// we only want to obtain the non deleted objects
	crit.add(Expression.eq("deleted", Boolean.FALSE));

	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	List<Grant> grants = (List<Grant>) crit.list();

	Pair<Integer, List<Grant>> pair = new Pair<Integer, List<Grant>>(count,
		grants);

	return pair;
    }

    /**
     * This method creates a Education.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOEducation
     *            Education data transfer object (DTO) with the values of the
     *            new instance.
     * @return the new education created with this Use Case
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Education CreateEducation(Usuario user, Education TOEducation)
	    throws InternalException, NoPermisosException {

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/**
	 * 2. For each association from the TOEducation that are filled in the
	 * DTO we put the real objects from the DB. *
	 */

	// we store if the education_personal is defined for later use
	boolean _education_personalIsDefined = false;
	if (TOEducation.getEducation_personal() != null
		&& TOEducation.getEducation_personal().getPersonalcode() != null) {
	    // if education_personal is defined we replace the
	    // education_personal in the DTO with its current state in the DB.
	    _education_personalIsDefined = true;
	    TOEducation.setEducation_personal(getPersonal(TOEducation
		    .getEducation_personal().getPersonalcode()));
	}

	// testIsHHRROrItself(user, TOEducation.getEducation_personal());

	// we store if the type is defined for later use
	boolean _typeIsDefined = false;

	if (TOEducation.getType() != null
		&& TOEducation.getType().getType_of_educationcode() != null) {
	    // if type is defined we replace the type in the DTO with its
	    // current state in the DB.
	    _typeIsDefined = true;

	    TOEducation.setType(getType_of_education(TOEducation.getType()
		    .getType_of_educationcode()));
	}

	// we store if the education_country is defined for later use
	boolean _education_countryIsDefined = false;

	if (TOEducation.getEducation_country() != null
		&& TOEducation.getEducation_country().getCountrycode() != null) {
	    // if education_country is defined we replace the education_country
	    // in the DTO with its current state in the DB.
	    _education_countryIsDefined = true;

	    TOEducation.setEducation_country(getCountry(TOEducation
		    .getEducation_country().getCountrycode()));
	}

	/** 3. We create the new instance * */
	Education education = new Education();

	/**
	 * 4. We set all the simple attributes (no associations) to the new
	 * instance *
	 */

	education.setStart_date(TOEducation.getStart_date());

	education.setEnd_date(TOEducation.getEnd_date());

	education.setGraduation_date(TOEducation.getGraduation_date());

	education.setTitle(TOEducation.getTitle());

	education.setSpeciality(TOEducation.getSpeciality());

	education.setCenter(TOEducation.getCenter());

	/** 5. We set the code to the new instance * */
	try {
	    IdentifyManager_Plain im = IdentifyManager_Plain.singleton();

	    education.setEducationcode(im.getId(TOEducation));
	} catch (identifyException ie) {

	    log.error("Error en asignaciï¿½n de nuevo id en CreateEducation", ie);
	    throw new Error(ie.getMessage());
	}

	/** 6. We save the new instance to the DB* */
	HibernateUtil.getSession().save(education);

	/**
	 * We associate the current object to the other objects (only in case
	 * that the associations where defined in the DTO) *
	 */

	if (_education_personalIsDefined) {

	    if (TOEducation.getEducation_personal() != null) {

		TOEducation.getEducation_personal().addIeducation_personal(
			education);
	    }

	    education
		    .setEducation_personal(TOEducation.getEducation_personal());
	}

	if (_typeIsDefined) {

	    if (TOEducation.getType() != null) {

		TOEducation.getType().addItype(education);
	    }

	    education.setType(TOEducation.getType());
	}

	if (_education_countryIsDefined) {

	    if (TOEducation.getEducation_country() != null) {

		TOEducation.getEducation_country().addIeducation_country(
			education);
	    }

	    education.setEducation_country(TOEducation.getEducation_country());
	}

	/** 7. We create an Audit message * */
	CreateCreationAuditmessage(user, education);

	/** 8. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return education;
    }

    /**
     * This method modifies a education.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOEducation
     *            Education data transfer object (DTO) with the values of the
     *            modified instance. The code of this attribute indicates which
     *            education will be modified.
     * @return the modified education
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Education UpdateEducation(Usuario user, Education TOEducation)
	    throws InternalException, NoPermisosException {

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 3. We obtain form the DB the instance to modify * */
	Education education = getEducation(TOEducation.getEducationcode());
	// testIsHHRROrItself(user, TOEducation.getEducation_personal());

	/**
	 * 2. For each association from the TOEducation that are filled in the
	 * DTO we put the real objects from the DB. *
	 */

	// we store if the education_personal is defined for later use
	boolean _education_personalIsDefined = false;

	if (TOEducation.getEducation_personal() != null
		&& TOEducation.getEducation_personal().getPersonalcode() != null) {
	    // if education_personal is defined we replace the
	    // education_personal in the DTO with its current state in the DB.
	    _education_personalIsDefined = true;

	    TOEducation.setEducation_personal(getPersonal(TOEducation
		    .getEducation_personal().getPersonalcode()));
	}

	// we store if the type is defined for later use
	boolean _typeIsDefined = false;

	if (TOEducation.getType() != null
		&& TOEducation.getType().getType_of_educationcode() != null) {
	    // if type is defined we replace the type in the DTO with its
	    // current state in the DB.
	    _typeIsDefined = true;

	    TOEducation.setType(getType_of_education(TOEducation.getType()
		    .getType_of_educationcode()));
	}

	// we store if the education_country is defined for later use
	boolean _education_countryIsDefined = false;

	if (TOEducation.getEducation_country() != null
		&& TOEducation.getEducation_country().getCountrycode() != null) {
	    // if education_country is defined we replace the education_country
	    // in the DTO with its current state in the DB.
	    _education_countryIsDefined = true;

	    TOEducation.setEducation_country(getCountry(TOEducation
		    .getEducation_country().getCountrycode()));
	}

	/**
	 * 4. We set all the simple attributes (no associations) to the instance
	 * *
	 */

	education.setStart_date(TOEducation.getStart_date());

	education.setEnd_date(TOEducation.getEnd_date());

	education.setGraduation_date(TOEducation.getGraduation_date());

	education.setTitle(TOEducation.getTitle());

	education.setSpeciality(TOEducation.getSpeciality());

	education.setCenter(TOEducation.getCenter());

	/**
	 * 5. We set the DTO version to the modified object and we update it
	 * with the new values in the DB. We evict and update the instance to
	 * prevent concurrent modification *
	 */
	HibernateUtil.getSession().evict(education);
	education.setVersion(TOEducation.getVersion());
	HibernateUtil.getSession().update(education);

	/**
	 * We associate/disassociate the current object to the other objects
	 * (only in case that the associations where defined in the DTO) *
	 */

	if (_education_personalIsDefined) {

	    if (education.getEducation_personal() != null) {

		education.getEducation_personal().removeIeducation_personal(
			education);
	    }

	    if (TOEducation.getEducation_personal() != null) {

		TOEducation.getEducation_personal().addIeducation_personal(
			education);
	    }

	    education
		    .setEducation_personal(TOEducation.getEducation_personal());
	}

	if (_typeIsDefined) {

	    if (education.getType() != null) {

		education.getType().removeItype(education);
	    }

	    if (TOEducation.getType() != null) {

		TOEducation.getType().addItype(education);
	    }

	    education.setType(TOEducation.getType());
	}

	if (_education_countryIsDefined) {

	    if (education.getEducation_country() != null) {

		education.getEducation_country().removeIeducation_country(
			education);
	    }

	    if (TOEducation.getEducation_country() != null) {

		TOEducation.getEducation_country().addIeducation_country(
			education);
	    }

	    education.setEducation_country(TOEducation.getEducation_country());
	}

	/** 6. We create an Audit message * */
	CreateModificationAuditmessage(user, education);

	/** 7. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return education;
    }

    /**
     * This method removes a education.
     * 
     * @param user
     *            The user who executes this use case
     * @param educationcode
     *            Code of the education to be removed
     * @throws NoPermisosException
     */
    public static void RemoveEducation(Usuario user, String educationcode)
	    throws NoPermisosException {

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain the object to delete form the DB. * */
	Education education = getEducation(educationcode);

	// testIsHHRROrItself(user, education.getEducation_personal());

	/** 3. We mark it as deleted. * */
	education.setDeleted(Boolean.TRUE);

	/** 4. We create an Audit message * */
	CreateRemovealAuditmessage(user, education);

	/** 5. We commit the DB transaction. * */
	HibernateUtil.commitTransaction();
    }

    /**
     * This method obtains one instance of education given its code.
     * 
     * @param user
     *            The user who executes this use case
     * @param educationcode
     *            Code of the education to be obtained
     * @return Education with the given code.
     */
    public static Education ObtainEducation(Usuario user, String educationcode) {

	/**
	 * 1. We obtain the object from the DB using the private getter and we
	 * return it. *
	 */

	Education education = getEducation(educationcode);
	return education;
    }

    /**
     * This method obtains all instances of Education, given a
     * list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     */
    public static Pair<Integer, List<Education>> ObtainAllEducation(
	    Usuario user, ListConfigurator configurator) {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(
		Education.class);

	// we only want to obtain the non deleted objects
	crit.add(Expression.eq("deleted", Boolean.FALSE));
	crit.setCacheable(true);

	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	List<Education> educations = (List<Education>) crit.list();

	Pair<Integer, List<Education>> pair = new Pair<Integer, List<Education>>(
		count, educations);

	return pair;
    }

    /**
     * This method creates a Marital_status.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOMarital_status
     *            Marital_status data transfer object (DTO) with the values of
     *            the new instance.
     * @return the new marital_status created with this Use Case
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Marital_status CreateMarital_status(Usuario user,
	    Marital_status TOMarital_status) throws InternalException,
	    NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We create the new instance * */
	Marital_status marital_status = new Marital_status();

	/**
	 * 3. We set all the simple attributes (no associations) to the new
	 * instance *
	 */

	marital_status.setDescription(TOMarital_status.getDescription());

	/** 4. We set the code to the new instance * */
	try {
	    IdentifyManager_Plain im = IdentifyManager_Plain.singleton();

	    marital_status.setMarital_statuscode(im.getId(TOMarital_status));
	} catch (identifyException ie) {

	    log.error(
		    "Error en asignaciï¿½n de nuevo id en CreateMarital_status",
		    ie);
	    throw new Error(ie.getMessage());
	}

	/** 5. We save the new instance to the DB* */
	HibernateUtil.getSession().save(marital_status);

	/** 6. We create an Audit message * */
	CreateCreationAuditmessage(user, marital_status);

	/** 7. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return marital_status;
    }

    /**
     * This method modifies a marital_status.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOMarital_status
     *            Marital_status data transfer object (DTO) with the values of
     *            the modified instance. The code of this attribute indicates
     *            which marital_status will be modified.
     * @return the modified marital_status
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Marital_status UpdateMarital_status(Usuario user,
	    Marital_status TOMarital_status) throws InternalException,
	    NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain form the DB the instance to modify * */
	Marital_status marital_status = getMarital_status(TOMarital_status
		.getMarital_statuscode());
	;

	/**
	 * 3. We set all the simple attributes (no associations) to the instance
	 * *
	 */

	marital_status.setDescription(TOMarital_status.getDescription());

	/**
	 * 4. We set the DTO version to the modified object and we update it
	 * with the new values in the DB. We evict and update the instance to
	 * prevent concurrent modification *
	 */
	HibernateUtil.getSession().evict(marital_status);
	marital_status.setVersion(TOMarital_status.getVersion());
	HibernateUtil.getSession().update(marital_status);

	/** 5. We create an Audit message * */
	CreateModificationAuditmessage(user, marital_status);

	/** 6. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return marital_status;
    }

    /**
     * This method removes a marital_status.
     * 
     * @param user
     *            The user who executes this use case
     * @param marital_statuscode
     *            Code of the marital_status to be removed
     * @throws NoPermisosException
     */
    public static void RemoveMarital_status(Usuario user,
	    String marital_statuscode) throws NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain the object to delete form the DB. * */
	Marital_status marital_status = getMarital_status(marital_statuscode);

	/** 3. We mark it as deleted. * */
	marital_status.setDeleted(Boolean.TRUE);

	/** 4. We create an Audit message * */
	CreateRemovealAuditmessage(user, marital_status);

	/** 5. We commit the DB transaction. * */
	HibernateUtil.commitTransaction();
    }

    /**
     * This method obtains one instance of marital_status given its code.
     * 
     * @param user
     *            The user who executes this use case
     * @param marital_statuscode
     *            Code of the marital_status to be obtained
     * @return Marital_status with the given code.
     */
    public static Marital_status ObtainMarital_status(Usuario user,
	    String marital_statuscode) {

	/**
	 * 1. We obtain the object from the DB using the private getter and we
	 * return it. *
	 */

	Marital_status marital_status = getMarital_status(marital_statuscode);
	return marital_status;
    }

    /**
     * This method obtains all instances of Marital_status, given a
     * list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     */
    public static Pair<Integer, List<Marital_status>> ObtainAllMarital_status(
	    Usuario user, ListConfigurator configurator) {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(
		Marital_status.class);

	// we only want to obtain the non deleted objects
	crit.add(Expression.eq("deleted", Boolean.FALSE));
	crit.setCacheable(true);

	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	List<Marital_status> marital_statuss = (List<Marital_status>) crit
		.list();

	Pair<Integer, List<Marital_status>> pair = new Pair<Integer, List<Marital_status>>(
		count, marital_statuss);

	return pair;
    }

    /**
     * This method creates a Nationality.
     * 
     * @param user
     *            The user who executes this use case
     * @param TONationality
     *            Nationality data transfer object (DTO) with the values of the
     *            new instance.
     * @return the new nationality created with this Use Case
     * @throws InternalException
     * @throws ValidationFailedException
     * @throws NoPermisosException
     */
    public static Nationality CreateNationality(Usuario user,
	    Nationality TONationality) throws InternalException,
	    ValidationFailedException, NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	if (getNationality(TONationality.getCode()) != null) {
	    Map<String, List<String>> parameters = new Hashtable<String, List<String>>();
	    parameters.put("propertyError.existingValue",
		    Arrays.asList(new String[] { "nationalitycode" }));
	    throw new ValidationFailedException(parameters);
	}

	/** 2. We create the new instance * */
	Nationality nationality = new Nationality();

	/**
	 * 3. We set all the simple attributes (no associations) to the new
	 * instance *
	 */

	nationality.setDescription(TONationality.getDescription());

	/** 4. We set the code to the new instance * */
	nationality.setNationalitycode(TONationality.getCode());

	/** 5. We save the new instance to the DB* */
	HibernateUtil.getSession().save(nationality);

	/** 6. We create an Audit message * */
	CreateCreationAuditmessage(user, nationality);

	/** 7. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return nationality;
    }

    /**
     * This method modifies a nationality.
     * 
     * @param user
     *            The user who executes this use case
     * @param TONationality
     *            Nationality data transfer object (DTO) with the values of the
     *            modified instance. The code of this attribute indicates which
     *            nationality will be modified.
     * @return the modified nationality
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Nationality UpdateNationality(Usuario user,
	    Nationality TONationality) throws InternalException,
	    NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain form the DB the instance to modify * */
	Nationality nationality = getNationality(TONationality
		.getNationalitycode());
	;

	/**
	 * 3. We set all the simple attributes (no associations) to the instance
	 * *
	 */

	nationality.setDescription(TONationality.getDescription());

	/**
	 * 4. We set the DTO version to the modified object and we update it
	 * with the new values in the DB. We evict and update the instance to
	 * prevent concurrent modification *
	 */
	HibernateUtil.getSession().evict(nationality);
	nationality.setVersion(TONationality.getVersion());
	HibernateUtil.getSession().update(nationality);

	/** 5. We create an Audit message * */
	CreateModificationAuditmessage(user, nationality);

	/** 6. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return nationality;
    }

    /**
     * This method removes a nationality.
     * 
     * @param user
     *            The user who executes this use case
     * @param nationalitycode
     *            Code of the nationality to be removed
     * @throws NoPermisosException
     */
    public static void RemoveNationality(Usuario user, String nationalitycode)
	    throws NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain the object to delete form the DB. * */
	Nationality nationality = getNationality(nationalitycode);

	/** 3. We mark it as deleted. * */
	nationality.setDeleted(Boolean.TRUE);

	/** 4. We create an Audit message * */
	CreateRemovealAuditmessage(user, nationality);

	/** 5. We commit the DB transaction. * */
	HibernateUtil.commitTransaction();
    }

    /**
     * This method obtains one instance of nationality given its code.
     * 
     * @param user
     *            The user who executes this use case
     * @param nationalitycode
     *            Code of the nationality to be obtained
     * @return Nationality with the given code.
     */
    public static Nationality ObtainNationality(Usuario user,
	    String nationalitycode) {

	/**
	 * 1. We obtain the object from the DB using the private getter and we
	 * return it. *
	 */

	Nationality nationality = getNationality(nationalitycode);
	return nationality;
    }

    /**
     * This method obtains all instances of Nationality, given a
     * list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     */
    public static Pair<Integer, List<Nationality>> ObtainAllNationality(
	    Usuario user, ListConfigurator configurator) {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(
		Nationality.class);

	// we only want to obtain the non deleted objects
	crit.add(Expression.eq("deleted", Boolean.FALSE));
	crit.setCacheable(true);

	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	List<Nationality> nationalitys = (List<Nationality>) crit.list();

	Pair<Integer, List<Nationality>> pair = new Pair<Integer, List<Nationality>>(
		count, nationalitys);

	return pair;
    }

    /**
     * This method creates a Payroll_institution.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOPayroll_institution
     *            Payroll_institution data transfer object (DTO) with the values
     *            of the new instance.
     * @return the new payroll_institution created with this Use Case
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Payroll_institution CreatePayroll_institution(Usuario user,
	    Payroll_institution TOPayroll_institution)
	    throws InternalException, NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We create the new instance * */
	Payroll_institution payroll_institution = new Payroll_institution();

	/**
	 * 3. We set all the simple attributes (no associations) to the new
	 * instance *
	 */

	payroll_institution.setDescription(TOPayroll_institution
		.getDescription());

	/** 4. We set the code to the new instance * */
	try {
	    IdentifyManager_Plain im = IdentifyManager_Plain.singleton();

	    payroll_institution.setPayroll_institutioncode(im
		    .getId(TOPayroll_institution));
	} catch (identifyException ie) {

	    log.error(
		    "Error en asignaciï¿½n de nuevo id en CreatePayroll_institution",
		    ie);
	    throw new Error(ie.getMessage());
	}

	/** 5. We save the new instance to the DB* */
	HibernateUtil.getSession().save(payroll_institution);

	/** 6. We create an Audit message * */
	CreateCreationAuditmessage(user, payroll_institution);

	/** 7. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return payroll_institution;
    }

    /**
     * This method modifies a payroll_institution.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOPayroll_institution
     *            Payroll_institution data transfer object (DTO) with the values
     *            of the modified instance. The code of this attribute indicates
     *            which payroll_institution will be modified.
     * @return the modified payroll_institution
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Payroll_institution UpdatePayroll_institution(Usuario user,
	    Payroll_institution TOPayroll_institution)
	    throws InternalException, NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain form the DB the instance to modify * */
	Payroll_institution payroll_institution = getPayroll_institution(TOPayroll_institution
		.getPayroll_institutioncode());
	;

	/**
	 * 3. We set all the simple attributes (no associations) to the instance
	 * *
	 */

	payroll_institution.setDescription(TOPayroll_institution
		.getDescription());

	/**
	 * 4. We set the DTO version to the modified object and we update it
	 * with the new values in the DB. We evict and update the instance to
	 * prevent concurrent modification *
	 */
	HibernateUtil.getSession().evict(payroll_institution);
	payroll_institution.setVersion(TOPayroll_institution.getVersion());
	HibernateUtil.getSession().update(payroll_institution);

	/** 5. We create an Audit message * */
	CreateModificationAuditmessage(user, payroll_institution);

	/** 6. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return payroll_institution;
    }

    /**
     * This method removes a payroll_institution.
     * 
     * @param user
     *            The user who executes this use case
     * @param payroll_institutioncode
     *            Code of the payroll_institution to be removed
     * @throws NoPermisosException
     */
    public static void RemovePayroll_institution(Usuario user,
	    String payroll_institutioncode) throws NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain the object to delete form the DB. * */
	Payroll_institution payroll_institution = getPayroll_institution(payroll_institutioncode);

	/** 3. We mark it as deleted. * */
	payroll_institution.setDeleted(Boolean.TRUE);

	/** 4. We create an Audit message * */
	CreateRemovealAuditmessage(user, payroll_institution);

	/** 5. We commit the DB transaction. * */
	HibernateUtil.commitTransaction();
    }

    /**
     * This method obtains one instance of payroll_institution given its code.
     * 
     * @param user
     *            The user who executes this use case
     * @param payroll_institutioncode
     *            Code of the payroll_institution to be obtained
     * @return Payroll_institution with the given code.
     */
    public static Payroll_institution ObtainPayroll_institution(Usuario user,
	    String payroll_institutioncode) {

	/**
	 * 1. We obtain the object from the DB using the private getter and we
	 * return it. *
	 */

	Payroll_institution payroll_institution = getPayroll_institution(payroll_institutioncode);
	return payroll_institution;
    }

    /**
     * This method obtains all instances of Payroll_institution, given a
     * list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     */
    public static Pair<Integer, List<Payroll_institution>> ObtainAllPayroll_institution(
	    Usuario user, ListConfigurator configurator) {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(
		Payroll_institution.class);

	// we only want to obtain the non deleted objects
	crit.add(Expression.eq("deleted", Boolean.FALSE));

	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	List<Payroll_institution> payroll_institutions = (List<Payroll_institution>) crit
		.list();

	Pair<Integer, List<Payroll_institution>> pair = new Pair<Integer, List<Payroll_institution>>(
		count, payroll_institutions);

	return pair;
    }

    /**
     * This method creates a Child.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOChild
     *            Child data transfer object (DTO) with the values of the new
     *            instance.
     * @return the new child created with this Use Case
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Child CreateChild(Usuario user, Child TOChild)
	    throws InternalException, NoPermisosException {

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/**
	 * 2. For each association from the TOChild that are filled in the DTO
	 * we put the real objects from the DB. *
	 */

	// we store if the child_personal is defined for later use
	boolean _child_personalIsDefined = false;

	if (TOChild.getChild_personal() != null
		&& TOChild.getChild_personal().getPersonalcode() != null) {
	    // if child_personal is defined we replace the child_personal in the
	    // DTO with its current state in the DB.
	    _child_personalIsDefined = true;

	    TOChild.setChild_personal(getPersonal(TOChild.getChild_personal()
		    .getPersonalcode()));
	}

	// testIsHHRROrItself(user, TOChild.getChild_personal());

	/** 3. We create the new instance * */
	Child child = new Child();

	/**
	 * 4. We set all the simple attributes (no associations) to the new
	 * instance *
	 */

	child.setBirth_date(TOChild.getBirth_date());

	child.setObservations(TOChild.getObservations());

	/** 5. We set the code to the new instance * */
	try {
	    IdentifyManager_Plain im = IdentifyManager_Plain.singleton();

	    child.setChildcode(im.getId(TOChild));
	} catch (identifyException ie) {

	    log.error("Error en asignaciï¿½n de nuevo id en CreateChild", ie);
	    throw new Error(ie.getMessage());
	}

	/** 6. We save the new instance to the DB* */
	HibernateUtil.getSession().save(child);

	/**
	 * We associate the current object to the other objects (only in case
	 * that the associations where defined in the DTO) *
	 */

	if (_child_personalIsDefined) {

	    if (TOChild.getChild_personal() != null) {

		TOChild.getChild_personal().addIchild_personal(child);
	    }

	    child.setChild_personal(TOChild.getChild_personal());
	}

	/** 7. We create an Audit message * */
	CreateCreationAuditmessage(user, child);

	/** 8. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return child;
    }

    public static Personal_comment CreatePersonal_comment(Usuario user,
	    Personal_comment TOPersonal_comment) throws InternalException,
	    NoPermisosException {

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/**
	 * 2. For each association from the TOChild that are filled in the DTO
	 * we put the real objects from the DB. *
	 */

	// we store if the child_personal is defined for later use
	boolean _personalIsDefined = false;

	if (TOPersonal_comment.getPersonal() != null
		&& TOPersonal_comment.getPersonal().getPersonalcode() != null) {
	    // if child_personal is defined we replace the child_personal in the
	    // DTO with its current state in the DB.
	    _personalIsDefined = true;

	    TOPersonal_comment.setPersonal(getPersonal(TOPersonal_comment
		    .getPersonal().getPersonalcode()));
	}

	// testIsHHRROrItself(user, TOChild.getChild_personal());

	/** 3. We create the new instance * */
	Personal_comment personal_comment = new Personal_comment();

	/**
	 * 4. We set all the simple attributes (no associations) to the new
	 * instance *
	 */

	personal_comment.setInput_date(new Date());

	personal_comment.setText(TOPersonal_comment.getText());

	if (user.getPersonal() != null
		&& user.getPersonal().getUsername() != null) {
	    personal_comment.setAuthor(user.getPersonal().getUsername());
	} else {
	    personal_comment.setAuthor(user.getCode());
	}

	/** 5. We set the code to the new instance * */
	try {
	    IdentifyManager_Plain im = IdentifyManager_Plain.singleton();

	    personal_comment.setPersonal_commentcode(im
		    .getId(TOPersonal_comment));
	} catch (identifyException ie) {

	    log.error("Error en asignaciï¿½n de nuevo id en CreateChild", ie);
	    throw new Error(ie.getMessage());
	}

	/** 6. We save the new instance to the DB* */

	/**
	 * We associate the current object to the other objects (only in case
	 * that the associations where defined in the DTO) *
	 */

	if (_personalIsDefined) {

	    if (TOPersonal_comment.getPersonal() != null) {

		TOPersonal_comment.getPersonal().addIpersonal_comments(
			personal_comment);
	    }

	    personal_comment.setPersonal(TOPersonal_comment.getPersonal());
	}

	HibernateUtil.getSession().save(personal_comment);

	/** 7. We create an Audit message * */
	CreateCreationAuditmessage(user, personal_comment);

	/** 8. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return personal_comment;
    }

    /**
     * This method modifies a child.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOChild
     *            Child data transfer object (DTO) with the values of the
     *            modified instance. The code of this attribute indicates which
     *            child will be modified.
     * @return the modified child
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Child UpdateChild(Usuario user, Child TOChild)
	    throws InternalException, NoPermisosException {

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 3. We obtain form the DB the instance to modify * */
	Child child = getChild(TOChild.getChildcode());
	;
	// testIsHHRROrItself(user, child.getChild_personal());

	/**
	 * 2. For each association from the TOChild that are filled in the DTO
	 * we put the real objects from the DB. *
	 */

	// we store if the child_personal is defined for later use
	boolean _child_personalIsDefined = false;

	if (TOChild.getChild_personal() != null
		&& TOChild.getChild_personal().getPersonalcode() != null) {
	    // if child_personal is defined we replace the child_personal in the
	    // DTO with its current state in the DB.
	    _child_personalIsDefined = true;

	    TOChild.setChild_personal(getPersonal(TOChild.getChild_personal()
		    .getPersonalcode()));
	}

	/**
	 * 4. We set all the simple attributes (no associations) to the instance
	 * *
	 */

	child.setBirth_date(TOChild.getBirth_date());

	child.setObservations(TOChild.getObservations());

	/**
	 * 5. We set the DTO version to the modified object and we update it
	 * with the new values in the DB. We evict and update the instance to
	 * prevent concurrent modification *
	 */
	HibernateUtil.getSession().evict(child);
	child.setVersion(TOChild.getVersion());
	HibernateUtil.getSession().update(child);

	/**
	 * We associate/disassociate the current object to the other objects
	 * (only in case that the associations where defined in the DTO) *
	 */

	if (_child_personalIsDefined) {

	    if (child.getChild_personal() != null) {

		child.getChild_personal().removeIchild_personal(child);
	    }

	    if (TOChild.getChild_personal() != null) {

		TOChild.getChild_personal().addIchild_personal(child);
	    }

	    child.setChild_personal(TOChild.getChild_personal());
	}

	/** 6. We create an Audit message * */
	CreateModificationAuditmessage(user, child);

	/** 7. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return child;
    }

    /**
     * This method removes a child.
     * 
     * @param user
     *            The user who executes this use case
     * @param childcode
     *            Code of the child to be removed
     * @throws NoPermisosException
     */
    public static void RemoveChild(Usuario user, String childcode)
	    throws NoPermisosException {

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain the object to delete form the DB. * */
	Child child = getChild(childcode);
	// testIsHHRROrItself(user, child.getChild_personal());

	/** 3. We mark it as deleted. * */
	child.setDeleted(Boolean.TRUE);

	/** 4. We create an Audit message * */
	CreateRemovealAuditmessage(user, child);

	/** 5. We commit the DB transaction. * */
	HibernateUtil.commitTransaction();
    }

    public static void RemovePersonal_comment(Usuario user,
	    String personal_commentcode) throws NoPermisosException {

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain the object to delete form the DB. * */
	Personal_comment personal_comment = getPersonal_comment(personal_commentcode);

	/** 3. We mark it as deleted. * */
	personal_comment.setDeleted(Boolean.TRUE);

	/** 4. We create an Audit message * */
	CreateRemovealAuditmessage(user, personal_comment);

	/** 5. We commit the DB transaction. * */
	HibernateUtil.commitTransaction();
    }

    /**
     * This method obtains one instance of child given its code.
     * 
     * @param user
     *            The user who executes this use case
     * @param childcode
     *            Code of the child to be obtained
     * @return Child with the given code.
     */
    public static Child ObtainChild(Usuario user, String childcode) {

	/**
	 * 1. We obtain the object from the DB using the private getter and we
	 * return it. *
	 */

	Child child = getChild(childcode);
	return child;
    }

    /**
     * This method obtains all instances of Child, given a list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     */
    public static Pair<Integer, List<Child>> ObtainAllChild(Usuario user,
	    ListConfigurator configurator) {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(Child.class);

	// we only want to obtain the non deleted objects
	crit.add(Expression.eq("deleted", Boolean.FALSE));
	crit.setCacheable(true);

	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	List<Child> childs = (List<Child>) crit.list();

	Pair<Integer, List<Child>> pair = new Pair<Integer, List<Child>>(count,
		childs);

	return pair;
    }

    /**
     * This method creates a Work_experience.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOWork_experience
     *            Work_experience data transfer object (DTO) with the values of
     *            the new instance.
     * @return the new work_experience created with this Use Case
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Work_experience CreateWork_experience(Usuario user,
	    Work_experience TOWork_experience) throws InternalException,
	    NoPermisosException {

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/**
	 * 2. For each association from the TOWork_experience that are filled in
	 * the DTO we put the real objects from the DB. *
	 */

	// we store if the work_experience_personal is defined for later use
	boolean _work_experience_personalIsDefined = false;

	if (TOWork_experience.getWork_experience_personal() != null
		&& TOWork_experience.getWork_experience_personal()
			.getPersonalcode() != null) {
	    // if work_experience_personal is defined we replace the
	    // work_experience_personal in the DTO with its current state in the
	    // DB.
	    _work_experience_personalIsDefined = true;

	    TOWork_experience
		    .setWork_experience_personal(getPersonal(TOWork_experience
			    .getWork_experience_personal().getPersonalcode()));
	}

	// testIsHHRROrItself(user, TOWork_experience
	// .getWork_experience_personal());

	// we store if the type_of_institution is defined for later use
	boolean _type_of_institutionIsDefined = false;

	if (TOWork_experience.getType_of_institution() != null
		&& TOWork_experience.getType_of_institution()
			.getType_of_institutioncode() != null) {
	    // if type_of_institution is defined we replace the
	    // type_of_institution in the DTO with its current state in the DB.
	    _type_of_institutionIsDefined = true;

	    TOWork_experience
		    .setType_of_institution(getType_of_institution(TOWork_experience
			    .getType_of_institution()
			    .getType_of_institutioncode()));
	}

	// we store if the area is defined for later use
	boolean _areaIsDefined = false;

	if (TOWork_experience.getArea() != null
		&& TOWork_experience.getArea().getAreacode() != null) {
	    // if area is defined we replace the area in the DTO with its
	    // current state in the DB.
	    _areaIsDefined = true;

	    TOWork_experience.setArea(getArea(TOWork_experience.getArea()
		    .getAreacode()));
	}

	// we store if the work_experience_position is defined for later use
	boolean _work_experience_positionIsDefined = false;

	if (TOWork_experience.getWork_experience_position() != null
		&& TOWork_experience.getWork_experience_position()
			.getPositioncode() != null) {
	    // if work_experience_position is defined we replace the
	    // work_experience_position in the DTO with its current state in the
	    // DB.
	    _work_experience_positionIsDefined = true;

	    TOWork_experience
		    .setWork_experience_position(getPosition(TOWork_experience
			    .getWork_experience_position().getPositioncode()));
	}

	// we store if the work_experience_country is defined for later use
	boolean _work_experience_countryIsDefined = false;

	if (TOWork_experience.getWork_experience_country() != null
		&& TOWork_experience.getWork_experience_country()
			.getCountrycode() != null) {
	    // if work_experience_country is defined we replace the
	    // work_experience_country in the DTO with its current state in the
	    // DB.
	    _work_experience_countryIsDefined = true;

	    TOWork_experience
		    .setWork_experience_country(getCountry(TOWork_experience
			    .getWork_experience_country().getCountrycode()));
	}

	/** 3. We create the new instance * */
	Work_experience work_experience = new Work_experience();

	/**
	 * 4. We set all the simple attributes (no associations) to the new
	 * instance *
	 */

	work_experience.setStart_date(TOWork_experience.getStart_date());

	work_experience.setEnd_date(TOWork_experience.getEnd_date());

	work_experience.setName_of_institution_or_company(TOWork_experience
		.getName_of_institution_or_company());

	work_experience.setDepartment(TOWork_experience.getDepartment());

	work_experience.setPosition(TOWork_experience.getPosition());

	/** 5. We set the code to the new instance * */
	try {
	    IdentifyManager_Plain im = IdentifyManager_Plain.singleton();

	    work_experience.setWork_experiencecode(im.getId(TOWork_experience));
	} catch (identifyException ie) {

	    log.error(
		    "Error en asignaciï¿½n de nuevo id en CreateWork_experience",
		    ie);
	    throw new Error(ie.getMessage());
	}

	/** 6. We save the new instance to the DB* */
	HibernateUtil.getSession().save(work_experience);

	/**
	 * We associate the current object to the other objects (only in case
	 * that the associations where defined in the DTO) *
	 */

	if (_work_experience_personalIsDefined) {

	    if (TOWork_experience.getWork_experience_personal() != null) {

		TOWork_experience.getWork_experience_personal()
			.addIwork_experience_personal(work_experience);
	    }

	    work_experience.setWork_experience_personal(TOWork_experience
		    .getWork_experience_personal());
	}

	if (_type_of_institutionIsDefined) {

	    if (TOWork_experience.getType_of_institution() != null) {

		TOWork_experience.getType_of_institution()
			.addItype_of_institution(work_experience);
	    }

	    work_experience.setType_of_institution(TOWork_experience
		    .getType_of_institution());
	}

	if (_areaIsDefined) {

	    if (TOWork_experience.getArea() != null) {

		TOWork_experience.getArea().addIarea(work_experience);
	    }

	    work_experience.setArea(TOWork_experience.getArea());
	}

	if (_work_experience_positionIsDefined) {

	    if (TOWork_experience.getWork_experience_position() != null) {

		TOWork_experience.getWork_experience_position()
			.addIwork_experience_position(work_experience);
	    }

	    work_experience.setWork_experience_position(TOWork_experience
		    .getWork_experience_position());
	}

	if (_work_experience_countryIsDefined) {

	    if (TOWork_experience.getWork_experience_country() != null) {

		TOWork_experience.getWork_experience_country()
			.addIwork_experience_country(work_experience);
	    }

	    work_experience.setWork_experience_country(TOWork_experience
		    .getWork_experience_country());
	}

	/** 7. We create an Audit message * */
	CreateCreationAuditmessage(user, work_experience);

	/** 8. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return work_experience;
    }

    /**
     * This method modifies a work_experience.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOWork_experience
     *            Work_experience data transfer object (DTO) with the values of
     *            the modified instance. The code of this attribute indicates
     *            which work_experience will be modified.
     * @return the modified work_experience
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Work_experience UpdateWork_experience(Usuario user,
	    Work_experience TOWork_experience) throws InternalException,
	    NoPermisosException {

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 3. We obtain form the DB the instance to modify * */
	Work_experience work_experience = getWork_experience(TOWork_experience
		.getWork_experiencecode());
	;
	// testIsHHRROrItself(user,
	// work_experience.getWork_experience_personal());

	/**
	 * 2. For each association from the TOWork_experience that are filled in
	 * the DTO we put the real objects from the DB. *
	 */

	// we store if the work_experience_personal is defined for later use
	boolean _work_experience_personalIsDefined = false;

	if (TOWork_experience.getWork_experience_personal() != null
		&& TOWork_experience.getWork_experience_personal()
			.getPersonalcode() != null) {
	    // if work_experience_personal is defined we replace the
	    // work_experience_personal in the DTO with its current state in the
	    // DB.
	    _work_experience_personalIsDefined = true;

	    TOWork_experience
		    .setWork_experience_personal(getPersonal(TOWork_experience
			    .getWork_experience_personal().getPersonalcode()));
	}

	// we store if the type_of_institution is defined for later use
	boolean _type_of_institutionIsDefined = false;

	if (TOWork_experience.getType_of_institution() != null
		&& TOWork_experience.getType_of_institution()
			.getType_of_institutioncode() != null) {
	    // if type_of_institution is defined we replace the
	    // type_of_institution in the DTO with its current state in the DB.
	    _type_of_institutionIsDefined = true;

	    TOWork_experience
		    .setType_of_institution(getType_of_institution(TOWork_experience
			    .getType_of_institution()
			    .getType_of_institutioncode()));
	}

	// we store if the area is defined for later use
	boolean _areaIsDefined = false;

	if (TOWork_experience.getArea() != null
		&& TOWork_experience.getArea().getAreacode() != null) {
	    // if area is defined we replace the area in the DTO with its
	    // current state in the DB.
	    _areaIsDefined = true;

	    TOWork_experience.setArea(getArea(TOWork_experience.getArea()
		    .getAreacode()));
	}

	// we store if the work_experience_position is defined for later use
	boolean _work_experience_positionIsDefined = false;

	if (TOWork_experience.getWork_experience_position() != null
		&& TOWork_experience.getWork_experience_position()
			.getPositioncode() != null) {
	    // if work_experience_position is defined we replace the
	    // work_experience_position in the DTO with its current state in the
	    // DB.
	    _work_experience_positionIsDefined = true;

	    TOWork_experience
		    .setWork_experience_position(getPosition(TOWork_experience
			    .getWork_experience_position().getPositioncode()));
	}

	// we store if the work_experience_country is defined for later use
	boolean _work_experience_countryIsDefined = false;

	if (TOWork_experience.getWork_experience_country() != null
		&& TOWork_experience.getWork_experience_country()
			.getCountrycode() != null) {
	    // if work_experience_country is defined we replace the
	    // work_experience_country in the DTO with its current state in the
	    // DB.
	    _work_experience_countryIsDefined = true;

	    TOWork_experience
		    .setWork_experience_country(getCountry(TOWork_experience
			    .getWork_experience_country().getCountrycode()));
	}

	/**
	 * 4. We set all the simple attributes (no associations) to the instance
	 * *
	 */

	work_experience.setStart_date(TOWork_experience.getStart_date());

	work_experience.setEnd_date(TOWork_experience.getEnd_date());

	work_experience.setName_of_institution_or_company(TOWork_experience
		.getName_of_institution_or_company());

	work_experience.setDepartment(TOWork_experience.getDepartment());

	work_experience.setPosition(TOWork_experience.getPosition());

	/**
	 * 5. We set the DTO version to the modified object and we update it
	 * with the new values in the DB. We evict and update the instance to
	 * prevent concurrent modification *
	 */
	HibernateUtil.getSession().evict(work_experience);
	work_experience.setVersion(TOWork_experience.getVersion());
	HibernateUtil.getSession().update(work_experience);

	/**
	 * We associate/disassociate the current object to the other objects
	 * (only in case that the associations where defined in the DTO) *
	 */

	if (_work_experience_personalIsDefined) {

	    if (work_experience.getWork_experience_personal() != null) {

		work_experience.getWork_experience_personal()
			.removeIwork_experience_personal(work_experience);
	    }

	    if (TOWork_experience.getWork_experience_personal() != null) {

		TOWork_experience.getWork_experience_personal()
			.addIwork_experience_personal(work_experience);
	    }

	    work_experience.setWork_experience_personal(TOWork_experience
		    .getWork_experience_personal());
	}

	if (_type_of_institutionIsDefined) {

	    if (work_experience.getType_of_institution() != null) {

		work_experience.getType_of_institution()
			.removeItype_of_institution(work_experience);
	    }

	    if (TOWork_experience.getType_of_institution() != null) {

		TOWork_experience.getType_of_institution()
			.addItype_of_institution(work_experience);
	    }

	    work_experience.setType_of_institution(TOWork_experience
		    .getType_of_institution());
	}

	if (_areaIsDefined) {

	    if (work_experience.getArea() != null) {

		work_experience.getArea().removeIarea(work_experience);
	    }

	    if (TOWork_experience.getArea() != null) {

		TOWork_experience.getArea().addIarea(work_experience);
	    }

	    work_experience.setArea(TOWork_experience.getArea());
	}

	if (_work_experience_positionIsDefined) {

	    if (work_experience.getWork_experience_position() != null) {

		work_experience.getWork_experience_position()
			.removeIwork_experience_position(work_experience);
	    }

	    if (TOWork_experience.getWork_experience_position() != null) {

		TOWork_experience.getWork_experience_position()
			.addIwork_experience_position(work_experience);
	    }

	    work_experience.setWork_experience_position(TOWork_experience
		    .getWork_experience_position());
	}

	if (_work_experience_countryIsDefined) {

	    if (work_experience.getWork_experience_country() != null) {

		work_experience.getWork_experience_country()
			.removeIwork_experience_country(work_experience);
	    }

	    if (TOWork_experience.getWork_experience_country() != null) {

		TOWork_experience.getWork_experience_country()
			.addIwork_experience_country(work_experience);
	    }

	    work_experience.setWork_experience_country(TOWork_experience
		    .getWork_experience_country());
	}

	/** 6. We create an Audit message * */
	CreateModificationAuditmessage(user, work_experience);

	/** 7. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return work_experience;
    }

    /**
     * This method removes a work_experience.
     * 
     * @param user
     *            The user who executes this use case
     * @param work_experiencecode
     *            Code of the work_experience to be removed
     * @throws NoPermisosException
     */
    public static void RemoveWork_experience(Usuario user,
	    String work_experiencecode) throws NoPermisosException {

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain the object to delete form the DB. * */
	Work_experience work_experience = getWork_experience(work_experiencecode);
	// testIsHHRROrItself(user,
	// work_experience.getWork_experience_personal());

	/** 3. We mark it as deleted. * */
	work_experience.setDeleted(Boolean.TRUE);

	/** 4. We create an Audit message * */
	CreateRemovealAuditmessage(user, work_experience);

	/** 5. We commit the DB transaction. * */
	HibernateUtil.commitTransaction();
    }

    /**
     * This method obtains one instance of work_experience given its code.
     * 
     * @param user
     *            The user who executes this use case
     * @param work_experiencecode
     *            Code of the work_experience to be obtained
     * @return Work_experience with the given code.
     */
    public static Work_experience ObtainWork_experience(Usuario user,
	    String work_experiencecode) {

	/**
	 * 1. We obtain the object from the DB using the private getter and we
	 * return it. *
	 */

	Work_experience work_experience = getWork_experience(work_experiencecode);
	return work_experience;
    }

    /**
     * This method obtains all instances of Work_experience, given a
     * list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     */
    public static Pair<Integer, List<Work_experience>> ObtainAllWork_experience(
	    Usuario user, ListConfigurator configurator) {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(
		Work_experience.class);

	// we only want to obtain the non deleted objects
	crit.add(Expression.eq("deleted", Boolean.FALSE));

	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	List<Work_experience> work_experiences = (List<Work_experience>) crit
		.list();

	Pair<Integer, List<Work_experience>> pair = new Pair<Integer, List<Work_experience>>(
		count, work_experiences);

	return pair;
    }

    /**
     * This method creates a Type_of_grant.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOType_of_grant
     *            Type_of_grant data transfer object (DTO) with the values of
     *            the new instance.
     * @return the new type_of_grant created with this Use Case
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Type_of_grant CreateType_of_grant(Usuario user,
	    Type_of_grant TOType_of_grant) throws InternalException,
	    NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We create the new instance * */
	Type_of_grant type_of_grant = new Type_of_grant();

	/**
	 * 3. We set all the simple attributes (no associations) to the new
	 * instance *
	 */

	type_of_grant.setDescription(TOType_of_grant.getDescription());

	/** 4. We set the code to the new instance * */
	try {
	    IdentifyManager_Plain im = IdentifyManager_Plain.singleton();

	    type_of_grant.setType_of_grantcode(im.getId(TOType_of_grant));
	} catch (identifyException ie) {

	    log.error("Error en asignaciï¿½n de nuevo id en CreateType_of_grant",
		    ie);
	    throw new Error(ie.getMessage());
	}

	/** 5. We save the new instance to the DB* */
	HibernateUtil.getSession().save(type_of_grant);

	/** 6. We create an Audit message * */
	CreateCreationAuditmessage(user, type_of_grant);

	/** 7. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return type_of_grant;
    }

    /**
     * This method modifies a type_of_grant.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOType_of_grant
     *            Type_of_grant data transfer object (DTO) with the values of
     *            the modified instance. The code of this attribute indicates
     *            which type_of_grant will be modified.
     * @return the modified type_of_grant
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Type_of_grant UpdateType_of_grant(Usuario user,
	    Type_of_grant TOType_of_grant) throws InternalException,
	    NoPermisosException {
	testIsHHRR(user);
	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain form the DB the instance to modify * */
	Type_of_grant type_of_grant = getType_of_grant(TOType_of_grant
		.getType_of_grantcode());
	;

	/**
	 * 3. We set all the simple attributes (no associations) to the instance
	 * *
	 */

	type_of_grant.setDescription(TOType_of_grant.getDescription());

	/**
	 * 4. We set the DTO version to the modified object and we update it
	 * with the new values in the DB. We evict and update the instance to
	 * prevent concurrent modification *
	 */
	HibernateUtil.getSession().evict(type_of_grant);
	type_of_grant.setVersion(TOType_of_grant.getVersion());
	HibernateUtil.getSession().update(type_of_grant);

	/** 5. We create an Audit message * */
	CreateModificationAuditmessage(user, type_of_grant);

	/** 6. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return type_of_grant;
    }

    /**
     * This method removes a type_of_grant.
     * 
     * @param user
     *            The user who executes this use case
     * @param type_of_grantcode
     *            Code of the type_of_grant to be removed
     * @throws NoPermisosException
     */
    public static void RemoveType_of_grant(Usuario user,
	    String type_of_grantcode) throws NoPermisosException {
	testIsHHRR(user);
	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain the object to delete form the DB. * */
	Type_of_grant type_of_grant = getType_of_grant(type_of_grantcode);

	/** 3. We mark it as deleted. * */
	type_of_grant.setDeleted(Boolean.TRUE);

	/** 4. We create an Audit message * */
	CreateRemovealAuditmessage(user, type_of_grant);

	/** 5. We commit the DB transaction. * */
	HibernateUtil.commitTransaction();
    }

    /**
     * This method obtains one instance of type_of_grant given its code.
     * 
     * @param user
     *            The user who executes this use case
     * @param type_of_grantcode
     *            Code of the type_of_grant to be obtained
     * @return Type_of_grant with the given code.
     */
    public static Type_of_grant ObtainType_of_grant(Usuario user,
	    String type_of_grantcode) {

	/**
	 * 1. We obtain the object from the DB using the private getter and we
	 * return it. *
	 */

	Type_of_grant type_of_grant = getType_of_grant(type_of_grantcode);
	return type_of_grant;
    }

    /**
     * This method obtains all instances of Type_of_grant, given a
     * list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     */
    public static Pair<Integer, List<Type_of_grant>> ObtainAllType_of_grant(
	    Usuario user, ListConfigurator configurator) {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(
		Type_of_grant.class);

	// we only want to obtain the non deleted objects
	crit.add(Expression.eq("deleted", Boolean.FALSE));

	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	List<Type_of_grant> type_of_grants = (List<Type_of_grant>) crit.list();

	Pair<Integer, List<Type_of_grant>> pair = new Pair<Integer, List<Type_of_grant>>(
		count, type_of_grants);

	return pair;
    }

    /**
     * This method creates a Payment.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOPayment
     *            Payment data transfer object (DTO) with the values of the new
     *            instance.
     * @return the new payment created with this Use Case
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Payment CreatePayment(Usuario user, Payment TOPayment)
	    throws InternalException, NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We create the new instance * */
	Payment payment = new Payment();

	/**
	 * 3. We set all the simple attributes (no associations) to the new
	 * instance *
	 */

	payment.setDescription(TOPayment.getDescription());

	/** 4. We set the code to the new instance * */
	try {
	    IdentifyManager_Plain im = IdentifyManager_Plain.singleton();

	    payment.setPaymentcode(im.getId(TOPayment));
	} catch (identifyException ie) {

	    log.error("Error en asignaciï¿½n de nuevo id en CreatePayment", ie);
	    throw new Error(ie.getMessage());
	}

	/** 5. We save the new instance to the DB* */
	HibernateUtil.getSession().save(payment);

	/** 6. We create an Audit message * */
	CreateCreationAuditmessage(user, payment);

	/** 7. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return payment;
    }

    /**
     * This method modifies a payment.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOPayment
     *            Payment data transfer object (DTO) with the values of the
     *            modified instance. The code of this attribute indicates which
     *            payment will be modified.
     * @return the modified payment
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Payment UpdatePayment(Usuario user, Payment TOPayment)
	    throws InternalException, NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain form the DB the instance to modify * */
	Payment payment = getPayment(TOPayment.getPaymentcode());
	;

	/**
	 * 3. We set all the simple attributes (no associations) to the instance
	 * *
	 */

	payment.setDescription(TOPayment.getDescription());

	/**
	 * 4. We set the DTO version to the modified object and we update it
	 * with the new values in the DB. We evict and update the instance to
	 * prevent concurrent modification *
	 */
	HibernateUtil.getSession().evict(payment);
	payment.setVersion(TOPayment.getVersion());
	HibernateUtil.getSession().update(payment);

	/** 5. We create an Audit message * */
	CreateModificationAuditmessage(user, payment);

	/** 6. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return payment;
    }

    /**
     * This method removes a payment.
     * 
     * @param user
     *            The user who executes this use case
     * @param paymentcode
     *            Code of the payment to be removed
     * @throws NoPermisosException
     */
    public static void RemovePayment(Usuario user, String paymentcode)
	    throws NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain the object to delete form the DB. * */
	Payment payment = getPayment(paymentcode);

	/** 3. We mark it as deleted. * */
	payment.setDeleted(Boolean.TRUE);

	/** 4. We create an Audit message * */
	CreateRemovealAuditmessage(user, payment);

	/** 5. We commit the DB transaction. * */
	HibernateUtil.commitTransaction();
    }

    /**
     * This method obtains one instance of payment given its code.
     * 
     * @param user
     *            The user who executes this use case
     * @param paymentcode
     *            Code of the payment to be obtained
     * @return Payment with the given code.
     */
    public static Payment ObtainPayment(Usuario user, String paymentcode) {

	/**
	 * 1. We obtain the object from the DB using the private getter and we
	 * return it. *
	 */

	Payment payment = getPayment(paymentcode);
	return payment;
    }

    /**
     * This method obtains all instances of Payment, given a list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     */
    public static Pair<Integer, List<Payment>> ObtainAllPayment(Usuario user,
	    ListConfigurator configurator) {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession()
		.createCriteria(Payment.class);

	// we only want to obtain the non deleted objects
	crit.add(Expression.eq("deleted", Boolean.FALSE));
	crit.setCacheable(true);

	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	List<Payment> payments = (List<Payment>) crit.list();

	Pair<Integer, List<Payment>> pair = new Pair<Integer, List<Payment>>(
		count, payments);

	return pair;
    }

    /**
     * This method creates a Organization_unit.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOOrganization_unit
     *            Organization_unit data transfer object (DTO) with the values
     *            of the new instance.
     * @return the new organization_unit created with this Use Case
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Organization_unit CreateOrganization_unit(Usuario user,
	    Organization_unit TOOrganization_unit) throws InternalException,
	    NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We create the new instance * */
	Organization_unit organization_unit = new Organization_unit();

	/**
	 * 3. We set all the simple attributes (no associations) to the new
	 * instance *
	 */

	organization_unit.setDescription(TOOrganization_unit.getDescription());

	/** 4. We set the code to the new instance * */
	try {
	    IdentifyManager_Plain im = IdentifyManager_Plain.singleton();

	    organization_unit.setOrganization_unitcode(im
		    .getId(TOOrganization_unit));
	} catch (identifyException ie) {

	    log.error(
		    "Error en asignaciï¿½n de nuevo id en CreateOrganization_unit",
		    ie);
	    throw new Error(ie.getMessage());
	}

	/** 5. We save the new instance to the DB* */
	HibernateUtil.getSession().save(organization_unit);

	/** 6. We create an Audit message * */
	CreateCreationAuditmessage(user, organization_unit);

	/** 7. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return organization_unit;
    }

    /**
     * This method modifies a organization_unit.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOOrganization_unit
     *            Organization_unit data transfer object (DTO) with the values
     *            of the modified instance. The code of this attribute indicates
     *            which organization_unit will be modified.
     * @return the modified organization_unit
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Organization_unit UpdateOrganization_unit(Usuario user,
	    Organization_unit TOOrganization_unit) throws InternalException,
	    NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain form the DB the instance to modify * */
	Organization_unit organization_unit = getOrganization_unit(TOOrganization_unit
		.getOrganization_unitcode());
	;

	/**
	 * 3. We set all the simple attributes (no associations) to the instance
	 * *
	 */

	organization_unit.setDescription(TOOrganization_unit.getDescription());

	/**
	 * 4. We set the DTO version to the modified object and we update it
	 * with the new values in the DB. We evict and update the instance to
	 * prevent concurrent modification *
	 */
	HibernateUtil.getSession().evict(organization_unit);
	organization_unit.setVersion(TOOrganization_unit.getVersion());
	HibernateUtil.getSession().update(organization_unit);

	/** 5. We create an Audit message * */
	CreateModificationAuditmessage(user, organization_unit);

	/** 6. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return organization_unit;
    }

    /**
     * This method removes a organization_unit.
     * 
     * @param user
     *            The user who executes this use case
     * @param organization_unitcode
     *            Code of the organization_unit to be removed
     * @throws NoPermisosException
     */
    public static void RemoveOrganization_unit(Usuario user,
	    String organization_unitcode) throws NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain the object to delete form the DB. * */
	Organization_unit organization_unit = getOrganization_unit(organization_unitcode);

	/** 3. We mark it as deleted. * */
	organization_unit.setDeleted(Boolean.TRUE);

	/** 4. We create an Audit message * */
	CreateRemovealAuditmessage(user, organization_unit);

	/** 5. We commit the DB transaction. * */
	HibernateUtil.commitTransaction();
    }

    /**
     * This method obtains one instance of organization_unit given its code.
     * 
     * @param user
     *            The user who executes this use case
     * @param organization_unitcode
     *            Code of the organization_unit to be obtained
     * @return Organization_unit with the given code.
     */
    public static Organization_unit ObtainOrganization_unit(Usuario user,
	    String organization_unitcode) {

	/**
	 * 1. We obtain the object from the DB using the private getter and we
	 * return it. *
	 */

	Organization_unit organization_unit = getOrganization_unit(organization_unitcode);
	return organization_unit;
    }

    /**
     * This method obtains all instances of Organization_unit, given a
     * list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     */
    public static Pair<Integer, List<Organization_unit>> ObtainAllOrganization_unit(
	    Usuario user, ListConfigurator configurator) {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(
		Organization_unit.class);

	// we only want to obtain the non deleted objects
	crit.add(Expression.eq("deleted", Boolean.FALSE));

	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	List<Organization_unit> organization_units = (List<Organization_unit>) crit
		.list();

	Pair<Integer, List<Organization_unit>> pair = new Pair<Integer, List<Organization_unit>>(
		count, organization_units);

	return pair;
    }

    // ---------------------------------------------------------------------
    /**
     * This method creates a Irb_budget.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOIrb_budget
     *            Irb_budget data transfer object (DTO) with the values of the
     *            new instance.
     * @return the new irb_budget created with this Use Case
     * @throws InternalException
     * @throws NoPermisosException
     * @throws ValidationFailedException
     */
    public static Irb_budget CreateIrb_budget(Usuario user,
	    Irb_budget TOIrb_budget) throws InternalException,
	    NoPermisosException, ValidationFailedException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain form the DB the instance to modify * */
	if (getIrb_budget(TOIrb_budget.getCode()) != null) {
	    Map<String, List<String>> parameters = new Hashtable<String, List<String>>();
	    parameters.put("propertyError.existingValue",
		    Arrays.asList(new String[] { "newIrb_budgetcode" }));
	    throw new ValidationFailedException(parameters);
	}

	/** 2. We create the new instance * */
	Irb_budget irb_budget = new Irb_budget();

	/**
	 * 3. We set all the simple attributes (no associations) to the new
	 * instance *
	 */
	irb_budget.setDescription(TOIrb_budget.getDescription());

	/** 4. We set the code to the new instance * */
	irb_budget.setIrb_budgetcode(TOIrb_budget.getCode());

	/** 5. We save the new instance to the DB* */
	HibernateUtil.getSession().save(irb_budget);

	/** 6. We create an Audit message * */
	CreateCreationAuditmessage(user, irb_budget);

	/** 7. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return irb_budget;
    }

    /**
     * This method modifies a irb_budget.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOIrb_budget
     *            Irb_budget data transfer object (DTO) with the values of the
     *            modified instance. The code of this attribute indicates which
     *            irb_budget will be modified.
     * @return the modified irb_budget
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Irb_budget UpdateIrb_budget(Usuario user,
	    Irb_budget TOIrb_budget) throws InternalException,
	    NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain form the DB the instance to modify * */
	Irb_budget irb_budget = getIrb_budget(TOIrb_budget.getIrb_budgetcode());
	;

	/**
	 * 3. We set all the simple attributes (no associations) to the instance
	 * *
	 */
	irb_budget.setDescription(TOIrb_budget.getDescription());

	/**
	 * 4. We set the DTO version to the modified object and we update it
	 * with the new values in the DB. We evict and update the instance to
	 * prevent concurrent modification *
	 */
	HibernateUtil.getSession().evict(irb_budget);
	irb_budget.setVersion(TOIrb_budget.getVersion());
	HibernateUtil.getSession().update(irb_budget);

	/** 5. We create an Audit message * */
	CreateModificationAuditmessage(user, irb_budget);

	/** 6. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return irb_budget;
    }

    public static Irb_budget updateIrb_budgetCode(Usuario user,
	    String irb_budgetCode, String newIrb_budgetCode)
	    throws ValidationFailedException, NoPermisosException {
	testIsHHRR(user);

	if (newIrb_budgetCode.equalsIgnoreCase(irb_budgetCode))
	    return getIrb_budget(irb_budgetCode);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain form the DB the instance to modify * */
	Irb_budget irb_budget = getIrb_budget(irb_budgetCode);

	if (getIrb_budget(newIrb_budgetCode) != null) {
	    Map<String, List<String>> parameters = new Hashtable<String, List<String>>();
	    parameters.put("propertyError.existingValue",
		    Arrays.asList(new String[] { "newIrb_budgetcode" }));
	    throw new ValidationFailedException(parameters);
	}

	Irb_budget newIrb_budget = new Irb_budget();
	newIrb_budget.setIrb_budgetcode(newIrb_budgetCode);
	newIrb_budget.setDescription(irb_budget.getDescription());
	for (Funding_detail funding : irb_budget.getIfunding_detail()) {
	    funding.setIrb_budget_code(newIrb_budget);
	}
	newIrb_budget.setIfunding_detail(irb_budget.getIfunding_detail());

	HibernateUtil.getSession().save(newIrb_budget);
	HibernateUtil.getSession().delete(irb_budget);

	CreateAuditmessage(user, "auditmessage.Irb_budgetCodeChange",
		new String[] { irb_budget.getCode(), newIrb_budget.getCode() },
		MODIFY_MESSAGE);

	HibernateUtil.commitTransaction();

	return newIrb_budget;
    }

    /**
     * This method removes a irb_budget.
     * 
     * @param user
     *            The user who executes this use case
     * @param irb_budgetcode
     *            Code of the irb_budget to be removed
     * @throws NoPermisosException
     */
    public static void RemoveIrb_budget(Usuario user, String irb_budgetcode)
	    throws NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain the object to delete form the DB. * */
	Irb_budget irb_budget = getIrb_budget(irb_budgetcode);

	/** 3. We mark it as deleted. * */
	irb_budget.setDeleted(Boolean.TRUE);

	/** 4. We create an Audit message * */
	CreateRemovealAuditmessage(user, irb_budget);

	/** 5. We commit the DB transaction. * */
	HibernateUtil.commitTransaction();
    }

    /**
     * This method obtains one instance of irb_budget given its code.
     * 
     * @param user
     *            The user who executes this use case
     * @param irb_budgetcode
     *            Code of the irb_budget to be obtained
     * @return Irb_budget with the given code.
     */
    public static Irb_budget ObtainIrb_budget(Usuario user,
	    String irb_budgetcode) {

	/**
	 * 1. We obtain the object from the DB using the private getter and we
	 * return it. *
	 */

	Irb_budget irb_budget = getIrb_budget(irb_budgetcode);
	return irb_budget;
    }

    /**
     * This method obtains all instances of Irb_budget, given a
     * list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     */
    public static Pair<Integer, List<Irb_budget>> ObtainAllIrb_budget(
	    Usuario user, ListConfigurator configurator) {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(
		Irb_budget.class);

	// we only want to obtain the non deleted objects
	crit.add(Expression.eq("deleted", Boolean.FALSE));

	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	List<Irb_budget> irb_budgets = (List<Irb_budget>) crit.list();

	Pair<Integer, List<Irb_budget>> pair = new Pair<Integer, List<Irb_budget>>(
		count, irb_budgets);

	return pair;
    }

    // -------------------------------------------------------

    /**
     * This method creates a Working_hours.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOWorking_hours
     *            Working_hours data transfer object (DTO) with the values of
     *            the new instance.
     * @return the new working_hours created with this Use Case
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Working_hours CreateWorking_hours(Usuario user,
	    Working_hours TOWorking_hours) throws InternalException,
	    NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We create the new instance * */
	Working_hours working_hours = new Working_hours();

	/**
	 * 3. We set all the simple attributes (no associations) to the new
	 * instance *
	 */

	working_hours.setDescription(TOWorking_hours.getDescription());

	/** 4. We set the code to the new instance * */
	try {
	    IdentifyManager_Plain im = IdentifyManager_Plain.singleton();

	    working_hours.setWorking_hourscode(im.getId(TOWorking_hours));
	} catch (identifyException ie) {

	    log.error("Error en asignaciï¿½n de nuevo id en CreateWorking_hours",
		    ie);
	    throw new Error(ie.getMessage());
	}

	/** 5. We save the new instance to the DB* */
	HibernateUtil.getSession().save(working_hours);

	/** 6. We create an Audit message * */
	CreateCreationAuditmessage(user, working_hours);

	/** 7. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return working_hours;
    }

    /**
     * This method modifies a working_hours.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOWorking_hours
     *            Working_hours data transfer object (DTO) with the values of
     *            the modified instance. The code of this attribute indicates
     *            which working_hours will be modified.
     * @return the modified working_hours
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Working_hours UpdateWorking_hours(Usuario user,
	    Working_hours TOWorking_hours) throws InternalException,
	    NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain form the DB the instance to modify * */
	Working_hours working_hours = getWorking_hours(TOWorking_hours
		.getWorking_hourscode());
	;

	/**
	 * 3. We set all the simple attributes (no associations) to the instance
	 * *
	 */

	working_hours.setDescription(TOWorking_hours.getDescription());

	/**
	 * 4. We set the DTO version to the modified object and we update it
	 * with the new values in the DB. We evict and update the instance to
	 * prevent concurrent modification *
	 */
	HibernateUtil.getSession().evict(working_hours);
	working_hours.setVersion(TOWorking_hours.getVersion());
	HibernateUtil.getSession().update(working_hours);

	/** 5. We create an Audit message * */
	CreateModificationAuditmessage(user, working_hours);

	/** 6. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return working_hours;
    }

    /**
     * This method removes a working_hours.
     * 
     * @param user
     *            The user who executes this use case
     * @param working_hourscode
     *            Code of the working_hours to be removed
     * @throws NoPermisosException
     */
    public static void RemoveWorking_hours(Usuario user,
	    String working_hourscode) throws NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain the object to delete form the DB. * */
	Working_hours working_hours = getWorking_hours(working_hourscode);

	/** 3. We mark it as deleted. * */
	working_hours.setDeleted(Boolean.TRUE);

	/** 4. We create an Audit message * */
	CreateRemovealAuditmessage(user, working_hours);

	/** 5. We commit the DB transaction. * */
	HibernateUtil.commitTransaction();
    }

    /**
     * This method obtains one instance of working_hours given its code.
     * 
     * @param user
     *            The user who executes this use case
     * @param working_hourscode
     *            Code of the working_hours to be obtained
     * @return Working_hours with the given code.
     */
    public static Working_hours ObtainWorking_hours(Usuario user,
	    String working_hourscode) {

	/**
	 * 1. We obtain the object from the DB using the private getter and we
	 * return it. *
	 */

	Working_hours working_hours = getWorking_hours(working_hourscode);
	return working_hours;
    }

    /**
     * This method obtains all instances of Working_hours, given a
     * list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     */
    public static Pair<Integer, List<Working_hours>> ObtainAllWorking_hours(
	    Usuario user, ListConfigurator configurator) {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(
		Working_hours.class);

	// we only want to obtain the non deleted objects
	crit.add(Expression.eq("deleted", Boolean.FALSE));
	crit.setCacheable(true);

	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	List<Working_hours> working_hourss = (List<Working_hours>) crit.list();

	Pair<Integer, List<Working_hours>> pair = new Pair<Integer, List<Working_hours>>(
		count, working_hourss);

	return pair;
    }

    private static Auditmessage CreateAuditmessage(Usuario user,
	    String property, String[] parameters, String typecode) {
	Locale loc = new Locale("en");
	if (user != null) {
	    loc = new Locale(user.getLanguage().getLanguagecode());
	}
	ResourceBundle messages = ResourceBundle.getBundle("MessageResources",
		loc);
	MessageFormat formatter = new MessageFormat("");
	formatter.setLocale(loc);

	formatter.applyPattern(messages.getString(property));
	String message = formatter.format(parameters);

	return CreateAuditmessage(user, message, typecode);
    }

    protected static Auditmessage CreateAuditmessage(Usuario user,
	    String message, String typecode) {

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 3. We create the new instance * */
	Auditmessage auditmessage = new Auditmessage();

	/**
	 * 4. We set all the simple attributes (no associations) to the new
	 * instance *
	 */
	auditmessage.setTimestamp(new Date());
	auditmessage.setMessage(message);

	/** 5. We set the code to the new instance * */
	try {
	    IdentifyManager_Plain im = IdentifyManager_Plain.singleton();
	    auditmessage.setAuditmessagecode(im.getId(auditmessage));
	} catch (identifyException ie) {
	    log.error("Error en asignaciï¿½n de nuevo id en CreateAuditmessage",
		    ie);
	    // throw new Error(ie.getMessage());
	}

	/** 6. We save the new instance to the DB* */
	HibernateUtil.getSession().save(auditmessage);

	auditmessage.setUser(user.getUsuariocode());
	auditmessage.setType(getAuditmessagetype(typecode));

	/** 8. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return auditmessage;
    }

    protected static AuditLog CreateAuditLogmessage(Usuario user,
	    String remoteHost, String type) {

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 3. We create the new instance * */
	AuditLog audit_log = new AuditLog();

	/**
	 * 4. We set all the simple attributes (no associations) to the new
	 * instance *
	 */
	audit_log.setDate(new Date());

	if (user != null) {
	    if (user.getPersonal() != null
		    && user.getPersonal().getUsername() != null) {
		audit_log.setUser(user.getPersonal().getUsername());
	    } else {
		audit_log.setUser(user.getCode());
	    }
	    Set roles = user.getRoles();
	    String rolesString = "";
	    for (Object role : roles) {
		rolesString += ((Role) role).getDescription();
	    }

	    audit_log.setRole(rolesString);
	} else {
	    audit_log.setUser("");
	    audit_log.setRole("");
	}

	if (remoteHost == null)
	    remoteHost = "<<unknown>>";
	audit_log.setRemoteHost(remoteHost);

	audit_log.setType(type);

	/** 5. We set the code to the new instance * */
	try {
	    IdentifyManager_Plain im = IdentifyManager_Plain.singleton();
	    audit_log.setAudit_logcode(im.getId("audit_log"));
	} catch (identifyException ie) {
	    log.error(
		    "Error en asignaciï¿½n de nuevo id en CreateAuditLogmessage",
		    ie);
	}

	/** 6. We save the new instance to the DB* */
	HibernateUtil.getSession().save(audit_log);

	/** 8. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return audit_log;
    }

    private static Auditmessage CreateCommonAuditmessage(Usuario user,
	    Object createdObject, String type) {
	String property = null;
	if (type.equals(CREATE_MESSAGE)) {
	    property = "auditmessage.create";
	} else if (type.equals(MODIFY_MESSAGE)) {
	    property = "auditmessage.modify";
	} else if (type.equals(DELETE_MESSAGE)) {
	    property = "auditmessage.delete";
	}

	String objectName = ((Persistent) createdObject).getCode();
	String className = createdObject
		.getClass()
		.getName()
		.substring(
			createdObject.getClass().getName().lastIndexOf('.') + 1);
	String[] parameters = new String[] { className, objectName };

	return CreateAuditmessage(user, property, parameters, type);

    }

    private static Auditmessage CreateStateChangeAuditmessage(Usuario user,
	    Object createdObject, String stateName) {
	String property = "auditmessage.state_change";

	String objectName = ((Persistent) createdObject).getCode();
	String className = createdObject
		.getClass()
		.getName()
		.substring(
			createdObject.getClass().getName().lastIndexOf('.') + 1);
	String[] parameters = new String[] { className, objectName, stateName };
	return CreateAuditmessage(user, property, parameters, MODIFY_MESSAGE);
    }

    private static Auditmessage CreateCreationAuditmessage(Usuario user,
	    Object createdObject) {
	return CreateCommonAuditmessage(user, createdObject, CREATE_MESSAGE);
    }

    private static Auditmessage CreateModificationAuditmessage(Usuario user,
	    Object createdObject) {
	return CreateCommonAuditmessage(user, createdObject, MODIFY_MESSAGE);
    }

    private static Auditmessage CreateRemovealAuditmessage(Usuario user,
	    Object createdObject) {
	return CreateCommonAuditmessage(user, createdObject, DELETE_MESSAGE);
    }

    public static Auditmessage CreateMailSentAuditmessage(Personal person,
	    String subject, String email) {
	Locale loc = new Locale("en");
	if (person != null) {
	    loc = new Locale(person.getLanguage().getLanguagecode());
	}
	ResourceBundle messages = ResourceBundle.getBundle("MessageResources",
		loc);
	MessageFormat formatter = new MessageFormat("");
	formatter.setLocale(loc);

	formatter.applyPattern(messages.getString("auditmessage.mailSent"));
	String message = formatter.format(new String[] { subject, email });

	Usuario fakeUser = new Usuario();
	fakeUser.setCode(person.getPersonalcode());
	fakeUser.setEntitycode(person.getPersonalcode());

	return CreateAuditmessage(fakeUser, message, MAIL_MESSAGE);
    }

    // public static Pair<Integer, List<Auditmessage>>
    // ObtainNewAuditmessageFromUser(Usuario user, Date date){
    // ListConfigurator configurator=new ListConfigurator();
    // configurator.setFilter("timestamp", new Date(),
    // ListConfigurator.GREATER);
    // return ObtainAuditmessageFromUser(user, user, configurator);
    // }

    public static Pair<Integer, List<Auditmessage>> ObtainAuditmessageFromUser(
	    String user, String auditUser, ListConfigurator configurator) {
	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(
		Auditmessage.class);

	// we only want to obtain the non deleted objects
	crit.add(Expression.eq("deleted", Boolean.FALSE));

	crit.add(Expression.eq("user", auditUser));
	crit.setCacheable(true);

	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	List<Auditmessage> auditmessages = (List<Auditmessage>) crit.list();

	Pair<Integer, List<Auditmessage>> pair = new Pair<Integer, List<Auditmessage>>(
		count, auditmessages);

	return pair;
    }

    /**
     * This method obtains one instance of auditmessage given its code.
     * 
     * @param user
     *            The user who executes this use case
     * @param auditmessagecode
     *            Code of the auditmessage to be obtained
     * @return Auditmessage with the given code.
     */
    public static Auditmessage ObtainAuditmessage(Usuario user,
	    String auditmessagecode) {

	/**
	 * 1. We obtain the object from the DB using the private getter and we
	 * return it. *
	 */

	Auditmessage auditmessage = getAuditmessage(auditmessagecode);
	return auditmessage;
    }

    /**
     * This method obtains all instances of Auditmessage, given a
     * list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     */
    public static Pair<Integer, List<Auditmessage>> ObtainAllAuditmessage(
	    Usuario user, ListConfigurator configurator) {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(
		Auditmessage.class);

	// we only want to obtain the non deleted objects
	crit.add(Expression.eq("deleted", Boolean.FALSE));
	crit.setCacheable(true);

	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	List<Auditmessage> auditmessages = (List<Auditmessage>) crit.list();

	Pair<Integer, List<Auditmessage>> pair = new Pair<Integer, List<Auditmessage>>(
		count, auditmessages);

	return pair;
    }

    /**
     * This method obtains all instances of Auditmessagetype, given a
     * list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     */
    public static Pair<Integer, List<Auditmessagetype>> ObtainAllAuditmessagetype(
	    Usuario user, ListConfigurator configurator) {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(
		Auditmessagetype.class);

	// we only want to obtain the non deleted objects
	crit.add(Expression.eq("deleted", Boolean.FALSE));

	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	List<Auditmessagetype> auditmessagetypes = (List<Auditmessagetype>) crit
		.list();

	Pair<Integer, List<Auditmessagetype>> pair = new Pair<Integer, List<Auditmessagetype>>(
		count, auditmessagetypes);

	return pair;
    }

    /**
     * This method creates a Bank.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOBank
     *            Bank data transfer object (DTO) with the values of the new
     *            instance.
     * @return the new bank created with this Use Case
     * @throws InternalException
     * @throws ValidationFailedException
     * @throws NoPermisosException
     */
    public static Bank CreateBank(Usuario user, Bank TOBank)
	    throws InternalException, ValidationFailedException,
	    NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	if (getBank(TOBank.getBankcode()) != null) {
	    Map<String, List<String>> parameters = new Hashtable<String, List<String>>();
	    parameters.put("propertyError.existingValue",
		    Arrays.asList(new String[] { "bankcode" }));
	    throw new ValidationFailedException(parameters);
	}

	/** 2. We create the new instance * */
	Bank bank = new Bank();

	/**
	 * 3. We set all the simple attributes (no associations) to the new
	 * instance *
	 */

	bank.setDescription(TOBank.getDescription());

	/** 4. We set the code to the new instance * */
	bank.setBankcode(TOBank.getBankcode());

	/** 5. We save the new instance to the DB* */
	HibernateUtil.getSession().save(bank);

	/** 6. We create an Audit message * */
	CreateCreationAuditmessage(user, bank);

	/** 7. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return bank;
    }

    /**
     * This method modifies a bank.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOBank
     *            Bank data transfer object (DTO) with the values of the
     *            modified instance. The code of this attribute indicates which
     *            bank will be modified.
     * @return the modified bank
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Bank UpdateBank(Usuario user, Bank TOBank)
	    throws InternalException, NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain form the DB the instance to modify * */
	Bank bank = getBank(TOBank.getBankcode());
	;

	/**
	 * 3. We set all the simple attributes (no associations) to the instance
	 * *
	 */

	bank.setDescription(TOBank.getDescription());

	/**
	 * 4. We set the DTO version to the modified object and we update it
	 * with the new values in the DB. We evict and update the instance to
	 * prevent concurrent modification *
	 */
	HibernateUtil.getSession().evict(bank);
	bank.setVersion(TOBank.getVersion());
	HibernateUtil.getSession().update(bank);

	/** 5. We create an Audit message * */
	CreateModificationAuditmessage(user, bank);

	/** 6. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return bank;
    }

    /**
     * This method removes a bank.
     * 
     * @param user
     *            The user who executes this use case
     * @param bankcode
     *            Code of the bank to be removed
     * @throws NoPermisosException
     */
    public static void RemoveBank(Usuario user, String bankcode)
	    throws NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain the object to delete form the DB. * */
	Bank bank = getBank(bankcode);

	/** 3. We mark it as deleted. * */
	bank.setDeleted(Boolean.TRUE);

	/** 4. We create an Audit message * */
	CreateRemovealAuditmessage(user, bank);

	/** 5. We commit the DB transaction. * */
	HibernateUtil.commitTransaction();
    }

    /**
     * This method obtains one instance of bank given its code.
     * 
     * @param user
     *            The user who executes this use case
     * @param bankcode
     *            Code of the bank to be obtained
     * @return Bank with the given code.
     */
    public static Bank ObtainBank(Usuario user, String bankcode) {

	/**
	 * 1. We obtain the object from the DB using the private getter and we
	 * return it. *
	 */

	Bank bank = getBank(bankcode);
	return bank;
    }

    /**
     * This method obtains all instances of Bank, given a list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     */
    public static Pair<Integer, List<Bank>> ObtainAllBank(Usuario user,
	    ListConfigurator configurator) {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(Bank.class);

	// we only want to obtain the non deleted objects
	crit.add(Expression.eq("deleted", Boolean.FALSE));
	crit.setCacheable(true);

	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	List<Bank> banks = (List<Bank>) crit.list();

	Pair<Integer, List<Bank>> pair = new Pair<Integer, List<Bank>>(count,
		banks);

	return pair;
    }

    /**
     * This method creates a Compensation.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOCompensation
     *            Compensation data transfer object (DTO) with the values of the
     *            new instance.
     * @return the new compensation created with this Use Case
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Compensation CreateCompensation(Usuario user,
	    Compensation TOCompensation) throws InternalException,
	    NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** finish last compensation* */
	Compensation oldCompensation = ObtainCurrentCompensationFromPersonal(
		user, TOCompensation.getCompensation_personal());
	if (oldCompensation != null && oldCompensation.getEnd_date() == null) {
	    if (TOCompensation != null
		    && TOCompensation.getStart_date() != null) {
		oldCompensation.setEnd_date(new Date(TOCompensation
			.getStart_date().getTime() - 1000 * 60 * 60 * 24));
	    }
	}

	/**
	 * 2. For each association from the TOCompensation that are filled in
	 * the DTO we put the real objects from the DB. *
	 */

	// we store if the compensation_personal is defined for later use
	boolean _compensation_personalIsDefined = false;

	if (TOCompensation.getCompensation_personal() != null
		&& TOCompensation.getCompensation_personal().getPersonalcode() != null) {
	    // if compensation_personal is defined we replace the
	    // compensation_personal in the DTO with its current state in the
	    // DB.
	    _compensation_personalIsDefined = true;

	    TOCompensation.setCompensation_personal(getPersonal(TOCompensation
		    .getCompensation_personal().getPersonalcode()));
	}

	// we store if the type_of_compensation is defined for later use
	boolean _type_of_compensationIsDefined = false;

	if (TOCompensation.getType_of_compensation() != null
		&& TOCompensation.getType_of_compensation()
			.getType_of_compensationcode() != null) {
	    // if type_of_compensation is defined we replace the
	    // type_of_compensation in the DTO with its current state in the DB.
	    _type_of_compensationIsDefined = true;

	    TOCompensation
		    .setType_of_compensation(getType_of_compensation(TOCompensation
			    .getType_of_compensation()
			    .getType_of_compensationcode()));
	}

	/** 3. We create the new instance * */
	Compensation compensation = new Compensation();

	/**
	 * 4. We set all the simple attributes (no associations) to the new
	 * instance *
	 */

	compensation.setStart_date(TOCompensation.getStart_date());

	compensation.setEnd_date(TOCompensation.getEnd_date());

	compensation.setDescription(TOCompensation.getDescription());

	compensation.setAmount(TOCompensation.getAmount());

	/** 5. We set the code to the new instance * */
	try {
	    IdentifyManager_Plain im = IdentifyManager_Plain.singleton();

	    compensation.setCompensationcode(im.getId(TOCompensation));
	} catch (identifyException ie) {

	    log.error("Error en asignaciï¿½n de nuevo id en CreateCompensation",
		    ie);
	    throw new Error(ie.getMessage());
	}

	/** 6. We save the new instance to the DB* */
	HibernateUtil.getSession().save(compensation);

	/**
	 * We associate the current object to the other objects (only in case
	 * that the associations where defined in the DTO) *
	 */

	if (_compensation_personalIsDefined) {

	    if (TOCompensation.getCompensation_personal() != null) {

		TOCompensation.getCompensation_personal()
			.addIcompensation_personal(compensation);
	    }

	    compensation.setCompensation_personal(TOCompensation
		    .getCompensation_personal());
	}

	if (_type_of_compensationIsDefined) {

	    if (TOCompensation.getType_of_compensation() != null) {

		TOCompensation.getType_of_compensation()
			.addItype_of_compensation(compensation);
	    }

	    compensation.setType_of_compensation(TOCompensation
		    .getType_of_compensation());
	}

	/** 7. We create an Audit message * */
	CreateCreationAuditmessage(user, compensation);

	/** 8. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return compensation;
    }

    /**
     * This method modifies a compensation.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOCompensation
     *            Compensation data transfer object (DTO) with the values of the
     *            modified instance. The code of this attribute indicates which
     *            compensation will be modified.
     * @return the modified compensation
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Compensation UpdateCompensation(Usuario user,
	    Compensation TOCompensation) throws InternalException,
	    NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/**
	 * 2. For each association from the TOCompensation that are filled in
	 * the DTO we put the real objects from the DB. *
	 */

	// we store if the compensation_personal is defined for later use
	boolean _compensation_personalIsDefined = false;

	if (TOCompensation.getCompensation_personal() != null
		&& TOCompensation.getCompensation_personal().getPersonalcode() != null) {
	    // if compensation_personal is defined we replace the
	    // compensation_personal in the DTO with its current state in the
	    // DB.
	    _compensation_personalIsDefined = true;

	    TOCompensation.setCompensation_personal(getPersonal(TOCompensation
		    .getCompensation_personal().getPersonalcode()));
	}

	// we store if the type_of_compensation is defined for later use
	boolean _type_of_compensationIsDefined = false;

	if (TOCompensation.getType_of_compensation() != null
		&& TOCompensation.getType_of_compensation()
			.getType_of_compensationcode() != null) {
	    // if type_of_compensation is defined we replace the
	    // type_of_compensation in the DTO with its current state in the DB.
	    _type_of_compensationIsDefined = true;

	    TOCompensation
		    .setType_of_compensation(getType_of_compensation(TOCompensation
			    .getType_of_compensation()
			    .getType_of_compensationcode()));
	}

	/** 3. We obtain form the DB the instance to modify * */
	Compensation compensation = getCompensation(TOCompensation
		.getCompensationcode());
	;

	/**
	 * 4. We set all the simple attributes (no associations) to the instance
	 * *
	 */

	compensation.setStart_date(TOCompensation.getStart_date());

	compensation.setEnd_date(TOCompensation.getEnd_date());

	compensation.setDescription(TOCompensation.getDescription());

	compensation.setAmount(TOCompensation.getAmount());

	/**
	 * 5. We set the DTO version to the modified object and we update it
	 * with the new values in the DB. We evict and update the instance to
	 * prevent concurrent modification *
	 */
	HibernateUtil.getSession().evict(compensation);
	compensation.setVersion(TOCompensation.getVersion());
	HibernateUtil.getSession().update(compensation);

	/**
	 * We associate/disassociate the current object to the other objects
	 * (only in case that the associations where defined in the DTO) *
	 */

	if (_compensation_personalIsDefined) {

	    if (compensation.getCompensation_personal() != null) {

		compensation.getCompensation_personal()
			.removeIcompensation_personal(compensation);
	    }

	    if (TOCompensation.getCompensation_personal() != null) {

		TOCompensation.getCompensation_personal()
			.addIcompensation_personal(compensation);
	    }

	    compensation.setCompensation_personal(TOCompensation
		    .getCompensation_personal());
	}

	if (_type_of_compensationIsDefined) {

	    if (compensation.getType_of_compensation() != null) {

		compensation.getType_of_compensation()
			.removeItype_of_compensation(compensation);
	    }

	    if (TOCompensation.getType_of_compensation() != null) {

		TOCompensation.getType_of_compensation()
			.addItype_of_compensation(compensation);
	    }

	    compensation.setType_of_compensation(TOCompensation
		    .getType_of_compensation());
	}

	/** 6. We create an Audit message * */
	CreateModificationAuditmessage(user, compensation);

	/** 7. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return compensation;
    }

    /**
     * This method removes a compensation.
     * 
     * @param user
     *            The user who executes this use case
     * @param compensationcode
     *            Code of the compensation to be removed
     * @throws NoPermisosException
     */
    public static void RemoveCompensation(Usuario user, String compensationcode)
	    throws NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain the object to delete form the DB. * */
	Compensation compensation = getCompensation(compensationcode);

	/** 3. We mark it as deleted. * */
	compensation.setDeleted(Boolean.TRUE);

	/** 4. We create an Audit message * */
	CreateRemovealAuditmessage(user, compensation);

	/** 5. We commit the DB transaction. * */
	HibernateUtil.commitTransaction();
    }

    /**
     * This method obtains one instance of compensation given its code.
     * 
     * @param user
     *            The user who executes this use case
     * @param compensationcode
     *            Code of the compensation to be obtained
     * @return Compensation with the given code.
     */
    public static Compensation ObtainCompensation(Usuario user,
	    String compensationcode) {

	/**
	 * 1. We obtain the object from the DB using the private getter and we
	 * return it. *
	 */

	Compensation compensation = getCompensation(compensationcode);
	return compensation;
    }

    /**
     * This method obtains all instances of Compensation, given a
     * list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     */
    public static Pair<Integer, List<Compensation>> ObtainAllCompensation(
	    Usuario user, ListConfigurator configurator) {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(
		Compensation.class);

	// we only want to obtain the non deleted objects
	crit.add(Expression.eq("deleted", Boolean.FALSE));

	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	List<Compensation> compensations = (List<Compensation>) crit.list();

	Pair<Integer, List<Compensation>> pair = new Pair<Integer, List<Compensation>>(
		count, compensations);

	return pair;
    }

    /**
     * This method creates a Type_of_institution.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOType_of_institution
     *            Type_of_institution data transfer object (DTO) with the values
     *            of the new instance.
     * @return the new type_of_institution created with this Use Case
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Type_of_institution CreateType_of_institution(Usuario user,
	    Type_of_institution TOType_of_institution)
	    throws InternalException, NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We create the new instance * */
	Type_of_institution type_of_institution = new Type_of_institution();

	/**
	 * 3. We set all the simple attributes (no associations) to the new
	 * instance *
	 */

	type_of_institution.setDescription(TOType_of_institution
		.getDescription());

	/** 4. We set the code to the new instance * */
	try {
	    IdentifyManager_Plain im = IdentifyManager_Plain.singleton();

	    type_of_institution.setType_of_institutioncode(im
		    .getId(TOType_of_institution));
	} catch (identifyException ie) {

	    log.error(
		    "Error en asignaciï¿½n de nuevo id en CreateType_of_institution",
		    ie);
	    throw new Error(ie.getMessage());
	}

	/** 5. We save the new instance to the DB* */
	HibernateUtil.getSession().save(type_of_institution);

	/** 6. We create an Audit message * */
	CreateCreationAuditmessage(user, type_of_institution);

	/** 7. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return type_of_institution;
    }

    /**
     * This method modifies a type_of_institution.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOType_of_institution
     *            Type_of_institution data transfer object (DTO) with the values
     *            of the modified instance. The code of this attribute indicates
     *            which type_of_institution will be modified.
     * @return the modified type_of_institution
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Type_of_institution UpdateType_of_institution(Usuario user,
	    Type_of_institution TOType_of_institution)
	    throws InternalException, NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain form the DB the instance to modify * */
	Type_of_institution type_of_institution = getType_of_institution(TOType_of_institution
		.getType_of_institutioncode());
	;

	/**
	 * 3. We set all the simple attributes (no associations) to the instance
	 * *
	 */

	type_of_institution.setDescription(TOType_of_institution
		.getDescription());

	/**
	 * 4. We set the DTO version to the modified object and we update it
	 * with the new values in the DB. We evict and update the instance to
	 * prevent concurrent modification *
	 */
	HibernateUtil.getSession().evict(type_of_institution);
	type_of_institution.setVersion(TOType_of_institution.getVersion());
	HibernateUtil.getSession().update(type_of_institution);

	/** 5. We create an Audit message * */
	CreateModificationAuditmessage(user, type_of_institution);

	/** 6. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return type_of_institution;
    }

    /**
     * This method removes a type_of_institution.
     * 
     * @param user
     *            The user who executes this use case
     * @param type_of_institutioncode
     *            Code of the type_of_institution to be removed
     * @throws NoPermisosException
     */
    public static void RemoveType_of_institution(Usuario user,
	    String type_of_institutioncode) throws NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain the object to delete form the DB. * */
	Type_of_institution type_of_institution = getType_of_institution(type_of_institutioncode);

	/** 3. We mark it as deleted. * */
	type_of_institution.setDeleted(Boolean.TRUE);

	/** 4. We create an Audit message * */
	CreateRemovealAuditmessage(user, type_of_institution);

	/** 5. We commit the DB transaction. * */
	HibernateUtil.commitTransaction();
    }

    /**
     * This method obtains one instance of type_of_institution given its code.
     * 
     * @param user
     *            The user who executes this use case
     * @param type_of_institutioncode
     *            Code of the type_of_institution to be obtained
     * @return Type_of_institution with the given code.
     */
    public static Type_of_institution ObtainType_of_institution(Usuario user,
	    String type_of_institutioncode) {

	/**
	 * 1. We obtain the object from the DB using the private getter and we
	 * return it. *
	 */

	Type_of_institution type_of_institution = getType_of_institution(type_of_institutioncode);
	return type_of_institution;
    }

    /**
     * This method obtains all instances of Type_of_institution, given a
     * list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     */
    public static Pair<Integer, List<Type_of_institution>> ObtainAllType_of_institution(
	    Usuario user, ListConfigurator configurator) {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(
		Type_of_institution.class);

	// we only want to obtain the non deleted objects
	crit.add(Expression.eq("deleted", Boolean.FALSE));
	crit.setCacheable(true);

	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	List<Type_of_institution> type_of_institutions = (List<Type_of_institution>) crit
		.list();

	Pair<Integer, List<Type_of_institution>> pair = new Pair<Integer, List<Type_of_institution>>(
		count, type_of_institutions);

	return pair;
    }

    
    
    
    
    
    /**
     * This method creates a Type_of_study.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOType_of_study
     *            Type_of_study data transfer object (DTO) with the values
     *            of the new instance.
     * @return the new type_of_study created with this Use Case
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Type_of_study CreateType_of_study(Usuario user,
	    Type_of_study TOType_of_study)
	    throws InternalException, NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We create the new instance * */
	Type_of_study type_of_study = new Type_of_study();

	/**
	 * 3. We set all the simple attributes (no associations) to the new
	 * instance *
	 */

	type_of_study.setDescription(TOType_of_study
		.getDescription());

	/** 4. We set the code to the new instance * */
	try {
	    IdentifyManager_Plain im = IdentifyManager_Plain.singleton();

	    type_of_study.setType_of_studycode(im
		    .getId(TOType_of_study));
	} catch (identifyException ie) {

	    log.error(
		    "Error en asignaciï¿½n de nuevo id en CreateType_of_study",
		    ie);
	    throw new Error(ie.getMessage());
	}

	/** 5. We save the new instance to the DB* */
	HibernateUtil.getSession().save(type_of_study);

	/** 6. We create an Audit message * */
	CreateCreationAuditmessage(user, type_of_study);

	/** 7. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return type_of_study;
    }

    /**
     * This method modifies a type_of_study.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOType_of_study
     *            Type_of_study data transfer object (DTO) with the values
     *            of the modified instance. The code of this attribute indicates
     *            which type_of_study will be modified.
     * @return the modified type_of_study
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Type_of_study UpdateType_of_study(Usuario user,
	    Type_of_study TOType_of_study)
	    throws InternalException, NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain form the DB the instance to modify * */
	Type_of_study type_of_study = getType_of_study(TOType_of_study
		.getType_of_studycode());
	;

	/**
	 * 3. We set all the simple attributes (no associations) to the instance
	 * *
	 */

	type_of_study.setDescription(TOType_of_study
		.getDescription());

	/**
	 * 4. We set the DTO version to the modified object and we update it
	 * with the new values in the DB. We evict and update the instance to
	 * prevent concurrent modification *
	 */
	HibernateUtil.getSession().evict(type_of_study);
	type_of_study.setVersion(TOType_of_study.getVersion());
	HibernateUtil.getSession().update(type_of_study);

	/** 5. We create an Audit message * */
	CreateModificationAuditmessage(user, type_of_study);

	/** 6. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return type_of_study;
    }

    /**
     * This method removes a type_of_study.
     * 
     * @param user
     *            The user who executes this use case
     * @param type_of_studycode
     *            Code of the type_of_study to be removed
     * @throws NoPermisosException
     */
    public static void RemoveType_of_study(Usuario user,
	    String type_of_studycode) throws NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain the object to delete form the DB. * */
	Type_of_study type_of_study = getType_of_study(type_of_studycode);

	/** 3. We mark it as deleted. * */
	type_of_study.setDeleted(Boolean.TRUE);

	/** 4. We create an Audit message * */
	CreateRemovealAuditmessage(user, type_of_study);

	/** 5. We commit the DB transaction. * */
	HibernateUtil.commitTransaction();
    }

    /**
     * This method obtains one instance of type_of_study given its code.
     * 
     * @param user
     *            The user who executes this use case
     * @param type_of_studycode
     *            Code of the type_of_study to be obtained
     * @return Type_of_study with the given code.
     */
    public static Type_of_study ObtainType_of_study(Usuario user,
	    String type_of_studycode) {

	/**
	 * 1. We obtain the object from the DB using the private getter and we
	 * return it. *
	 */

	Type_of_study type_of_study = getType_of_study(type_of_studycode);
	return type_of_study;
    }

    /**
     * This method obtains all instances of Type_of_study, given a
     * list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     */
    public static Pair<Integer, List<Type_of_study>> ObtainAllType_of_study(
	    Usuario user, ListConfigurator configurator) {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(
		Type_of_study.class);

	// we only want to obtain the non deleted objects
	crit.add(Expression.eq("deleted", Boolean.FALSE));
	crit.setCacheable(true);

	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	List<Type_of_study> type_of_study = (List<Type_of_study>) crit
		.list();

	Pair<Integer, List<Type_of_study>> pair = new Pair<Integer, List<Type_of_study>>(
		count, type_of_study);

	return pair;
    }
    
    public static Params UpdateParams(Usuario user,
    	    Params TOParams)
    	    throws InternalException, NoPermisosException {
    	
		/** 1. We begin the DB transaction. * */
		HibernateUtil.beginTransaction();
	
		/** 2. We obtain form the DB the instance to modify * */
		Params params = getParams(TOParams
			.getParamscode());
	;

	/**
	 * 3. We set all the simple attributes (no associations) to the instance
	 * *
	 */

	params.setValue(TOParams
		.getValue());
	
	/**
	 * 4. We set the DTO version to the modified object and we update it
	 * with the new values in the DB. We evict and update the instance to
	 * prevent concurrent modification *
	 */
	HibernateUtil.getSession().evict(params);	
	HibernateUtil.getSession().update(params);

	/** 5. We create an Audit message * */
	CreateModificationAuditmessage(user, params);

	/** 6. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return params;
    }


    /**
     * This method obtains one instance of params given its code.
     * 
     * @param user
     *            The user who executes this use case
     * @param paramscode
     *            Code of the params to be obtained
     * @return Params with the given code.
     */
    public static Params ObtainParams(Usuario user,
	    String paramscode) {

		/**
		 * 1. We obtain the object from the DB using the private getter and we
		 * return it. *
		 */
	
		Params params = getParams(paramscode);
		return params;
    }
	
	    /**
     * Returns the Params with the given key. This method is used
     * internally to get objects form the database.
     * 
     * @param paramscode
     *            code of the Alumni titles
     * @return Params with the given code
     */
    protected static Params getParams(
    		String paramscode) {
    	Params params = (Params) HibernateUtil
    			.getSession().get(Params.class,
    					paramscode);
    	return params;
    }
        
    
    
    /**
     * This method modifies a alumni_params.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOAlumni_params
     *            Alumni_params data transfer object (DTO) with the values
     *            of the modified instance. The code of this attribute indicates
     *            which alumni_params will be modified.
     * @return the modified alumni_params
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Alumni_params UpdateAlumni_params(Usuario user,
	    Alumni_params TOAlumni_params)
	    throws InternalException, NoPermisosException {
	
	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain form the DB the instance to modify * */
	Alumni_params alumni_params = getAlumni_params(TOAlumni_params
		.getAlumni_paramscode());
	;

	/**
	 * 3. We set all the simple attributes (no associations) to the instance
	 * *
	 */

	alumni_params.setValue(TOAlumni_params
		.getValue());
	
	/**
	 * 4. We set the DTO version to the modified object and we update it
	 * with the new values in the DB. We evict and update the instance to
	 * prevent concurrent modification *
	 */
	HibernateUtil.getSession().evict(alumni_params);	
	HibernateUtil.getSession().update(alumni_params);

	/** 5. We create an Audit message * */
	CreateModificationAuditmessage(user, alumni_params);

	/** 6. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return alumni_params;
    }


    /**
     * This method obtains one instance of alumni_params given its code.
     * 
     * @param user
     *            The user who executes this use case
     * @param alumni_paramscode
     *            Code of the alumni_params to be obtained
     * @return Alumni_params with the given code.
     */
    public static Alumni_params ObtainAlumni_params(Usuario user,
	    String alumni_paramscode) {

	/**
	 * 1. We obtain the object from the DB using the private getter and we
	 * return it. *
	 */

	Alumni_params alumni_params = getAlumni_params(alumni_paramscode);
	return alumni_params;
    }
	
	    /**
     * Returns the Alumni_params with the given key. This method is used
     * internally to get objects form the database.
     * 
     * @param alumni_paramscode
     *            code of the Alumni titles
     * @return Alumni_params with the given code
     */
    protected static Alumni_params getAlumni_params(
    		String alumni_paramscode) {
    	Alumni_params alumni_params = (Alumni_params) HibernateUtil
    			.getSession().get(Alumni_params.class,
    					alumni_paramscode);
    	return alumni_params;
    }
    
    
    /**
     * This method creates a Alumni_titles.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOAlumni_titles
     *            Alumni_titles data transfer object (DTO) with the values
     *            of the new instance.
     * @return the new alumni_titles created with this Use Case
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Alumni_titles CreateAlumni_titles(Usuario user,
	    Alumni_titles TOAlumni_titles)
	    throws InternalException, NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We create the new instance * */
	Alumni_titles alumni_titles = new Alumni_titles();

	/**
	 * 3. We set all the simple attributes (no associations) to the new
	 * instance *
	 */

	alumni_titles.setDescription(TOAlumni_titles
		.getDescription());

	alumni_titles.setShort_description(TOAlumni_titles
			.getShort_description());
	
	alumni_titles.setOrder_number(TOAlumni_titles
			.getOrder_number());
	
	/** 4. We set the code to the new instance * */
	try {
	    IdentifyManager_Plain im = IdentifyManager_Plain.singleton();

	    alumni_titles.setAlumni_titlescode(im
		    .getId(TOAlumni_titles));
	} catch (identifyException ie) {

	    log.error(
		    "Error en asignaciï¿½n de nuevo id en CreateAlumni_titles",
		    ie);
	    throw new Error(ie.getMessage());
	}

	/** 5. We save the new instance to the DB* */
	HibernateUtil.getSession().save(alumni_titles);

	/** 6. We create an Audit message * */
	CreateCreationAuditmessage(user, alumni_titles);

	/** 7. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return alumni_titles;
    }

    /**
     * This method modifies a alumni_titles.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOAlumni_titles
     *            Alumni_titles data transfer object (DTO) with the values
     *            of the modified instance. The code of this attribute indicates
     *            which alumni_titles will be modified.
     * @return the modified alumni_titles
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Alumni_titles UpdateAlumni_titles(Usuario user,
	    Alumni_titles TOAlumni_titles)
	    throws InternalException, NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain form the DB the instance to modify * */
	Alumni_titles alumni_titles = getAlumni_titles(TOAlumni_titles
		.getAlumni_titlescode());
	;

	/**
	 * 3. We set all the simple attributes (no associations) to the instance
	 * *
	 */

	alumni_titles.setDescription(TOAlumni_titles
		.getDescription());
	
	alumni_titles.setShort_description(TOAlumni_titles
			.getShort_description());
	
	alumni_titles.setOrder_number(TOAlumni_titles
			.getOrder_number());

	/**
	 * 4. We set the DTO version to the modified object and we update it
	 * with the new values in the DB. We evict and update the instance to
	 * prevent concurrent modification *
	 */
	HibernateUtil.getSession().evict(alumni_titles);
	alumni_titles.setVersion(TOAlumni_titles.getVersion());
	HibernateUtil.getSession().update(alumni_titles);

	/** 5. We create an Audit message * */
	CreateModificationAuditmessage(user, alumni_titles);

	/** 6. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return alumni_titles;
    }

    /**
     * This method removes a alumni_titles.
     * 
     * @param user
     *            The user who executes this use case
     * @param alumni_titlescode
     *            Code of the alumni_titles to be removed
     * @throws NoPermisosException
     */
    public static void RemoveAlumni_titles(Usuario user,
	    String alumni_titlescode) throws NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain the object to delete form the DB. * */
	Alumni_titles alumni_titles = getAlumni_titles(alumni_titlescode);

	/** 3. We mark it as deleted. * */
	alumni_titles.setDeleted(Boolean.TRUE);

	/** 4. We create an Audit message * */
	CreateRemovealAuditmessage(user, alumni_titles);

	/** 5. We commit the DB transaction. * */
	HibernateUtil.commitTransaction();
    }

    /**
     * This method obtains one instance of alumni_titles given its code.
     * 
     * @param user
     *            The user who executes this use case
     * @param alumni_titlescode
     *            Code of the alumni_titles to be obtained
     * @return Alumni_titles with the given code.
     */
    public static Alumni_titles ObtainAlumni_titles(Usuario user,
	    String alumni_titlescode) {

	/**
	 * 1. We obtain the object from the DB using the private getter and we
	 * return it. *
	 */

	Alumni_titles alumni_titles = getAlumni_titles(alumni_titlescode);
	return alumni_titles;
    }

    /**
     * This method obtains all instances of Alumni_titles, given a
     * list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     */
    public static Pair<Integer, List<Alumni_titles>> ObtainAllAlumni_titles(
	    Usuario user, ListConfigurator configurator) {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(
		Alumni_titles.class);

	// we only want to obtain the non deleted objects
	crit.add(Expression.eq("deleted", Boolean.FALSE));
	crit.setCacheable(true);

	configurator.setOrderBy("order_number");
	configurator.setAsc("");
	
	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	List<Alumni_titles> alumni_titles = (List<Alumni_titles>) crit
		.list();

	Pair<Integer, List<Alumni_titles>> pair = new Pair<Integer, List<Alumni_titles>>(
		count, alumni_titles);

	return pair;
    }
    
    /**
     * This method creates a Alumni_external_job_positions.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOAlumni_external_job_positions
     *            Alumni_external_job_positions data transfer object (DTO) with the values
     *            of the new instance.
     * @return the new alumni_external_job_positions created with this Use Case
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Alumni_external_job_positions CreateAlumni_external_job_positions(Usuario user,
	    Alumni_external_job_positions TOAlumni_external_job_positions)
	    throws InternalException, NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We create the new instance * */
	Alumni_external_job_positions alumni_external_job_positions = new Alumni_external_job_positions();

	// we store if the grant is defined for later use
	boolean _job_position_type_is_defined = false;

	if (TOAlumni_external_job_positions.getJob_position_types() != null
		&& TOAlumni_external_job_positions.getJob_position_types().getAlumni_job_position_typescode() != null) {
	    // if grant is defined we replace the grant in the DTO with its
	    // current state in the DB.
		_job_position_type_is_defined = true;

		TOAlumni_external_job_positions.setJob_position_types(getAlumni_job_position_types(TOAlumni_external_job_positions.getJob_position_types()
		    .getAlumni_job_position_typescode()));
	}
	
	/**
	 * 3. We set all the simple attributes (no associations) to the new
	 * instance *
	 */

	alumni_external_job_positions.setDescription(TOAlumni_external_job_positions
		.getDescription());

	alumni_external_job_positions.setShort_description(TOAlumni_external_job_positions
			.getShort_description());
	
	alumni_external_job_positions.setOrder_number(TOAlumni_external_job_positions
			.getOrder_number());
	
	/** 4. We set the code to the new instance * */
	try {
	    IdentifyManager_Plain im = IdentifyManager_Plain.singleton();

	    alumni_external_job_positions.setAlumni_external_job_positionscode(im
		    .getId(TOAlumni_external_job_positions));
	} catch (identifyException ie) {

	    log.error(
		    "Error en asignaciï¿½n de nuevo id en CreateAlumni_external_job_positions",
		    ie);
	    throw new Error(ie.getMessage());
	}

	/** 5. We save the new instance to the DB* */
	HibernateUtil.getSession().save(alumni_external_job_positions);

	if (_job_position_type_is_defined) {

	    if (TOAlumni_external_job_positions.getJob_position_types() != null) {

	    	TOAlumni_external_job_positions.getJob_position_types().addIialumni_external_job_positions(alumni_external_job_positions);
	    }

	    alumni_external_job_positions.setJob_position_types(TOAlumni_external_job_positions.getJob_position_types());
	}
	
	/** 6. We create an Audit message * */
	CreateCreationAuditmessage(user, alumni_external_job_positions);

	/** 7. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return alumni_external_job_positions;
    }

    /**
     * This method modifies a alumni_external_job_positions.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOAlumni_external_job_positions
     *            Alumni_external_job_positions data transfer object (DTO) with the values
     *            of the modified instance. The code of this attribute indicates
     *            which alumni_external_job_positions will be modified.
     * @return the modified alumni_external_job_positions
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Alumni_external_job_positions UpdateAlumni_external_job_positions(Usuario user,
	    Alumni_external_job_positions TOAlumni_external_job_positions)
	    throws InternalException, NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain form the DB the instance to modify * */
	Alumni_external_job_positions alumni_external_job_positions = getAlumni_external_job_positions(TOAlumni_external_job_positions
		.getAlumni_external_job_positionscode());
	;

	// we store if the grant is defined for later use
	boolean _job_position_type_is_defined = false;

	if (TOAlumni_external_job_positions.getJob_position_types() != null
		&& TOAlumni_external_job_positions.getJob_position_types().getAlumni_job_position_typescode() != null) {
	    // if grant is defined we replace the grant in the DTO with its
	    // current state in the DB.
		_job_position_type_is_defined = true;

		TOAlumni_external_job_positions.setJob_position_types(getAlumni_job_position_types(TOAlumni_external_job_positions.getJob_position_types()
		    .getAlumni_job_position_typescode()));
	}
	
	/**
	 * 3. We set all the simple attributes (no associations) to the instance
	 * *
	 */

	alumni_external_job_positions.setDescription(TOAlumni_external_job_positions
		.getDescription());
	
	alumni_external_job_positions.setShort_description(TOAlumni_external_job_positions
			.getShort_description());
	
	alumni_external_job_positions.setOrder_number(TOAlumni_external_job_positions
			.getOrder_number());

	/**
	 * 4. We set the DTO version to the modified object and we update it
	 * with the new values in the DB. We evict and update the instance to
	 * prevent concurrent modification *
	 */
	HibernateUtil.getSession().evict(alumni_external_job_positions);
	alumni_external_job_positions.setVersion(TOAlumni_external_job_positions.getVersion());
	HibernateUtil.getSession().update(alumni_external_job_positions);

	if (_job_position_type_is_defined) {

		if (alumni_external_job_positions.getJob_position_types() != null) {
			alumni_external_job_positions.getJob_position_types().removeIalumni_external_job_positions(alumni_external_job_positions);
		}
	    if (TOAlumni_external_job_positions.getJob_position_types() != null) {

	    	TOAlumni_external_job_positions.getJob_position_types().addIialumni_external_job_positions(alumni_external_job_positions);
	    }

	    alumni_external_job_positions.setJob_position_types(TOAlumni_external_job_positions.getJob_position_types());
	}
	
	/** 5. We create an Audit message * */
	CreateModificationAuditmessage(user, alumni_external_job_positions);

	/** 6. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return alumni_external_job_positions;
    }

    /**
     * This method removes a alumni_external_job_positions.
     * 
     * @param user
     *            The user who executes this use case
     * @param alumni_external_job_positionscode
     *            Code of the alumni_external_job_positions to be removed
     * @throws NoPermisosException
     */
    public static void RemoveAlumni_external_job_positions(Usuario user,
	    String alumni_external_job_positionscode) throws NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain the object to delete form the DB. * */
	Alumni_external_job_positions alumni_external_job_positions = getAlumni_external_job_positions(alumni_external_job_positionscode);

	/** 3. We mark it as deleted. * */
	alumni_external_job_positions.setDeleted(Boolean.TRUE);

	/** 4. We create an Audit message * */
	CreateRemovealAuditmessage(user, alumni_external_job_positions);

	/** 5. We commit the DB transaction. * */
	HibernateUtil.commitTransaction();
    }

    /**
     * This method obtains one instance of alumni_external_job_positions given its code.
     * 
     * @param user
     *            The user who executes this use case
     * @param alumni_external_job_positionscode
     *            Code of the alumni_external_job_positions to be obtained
     * @return Alumni_external_job_positions with the given code.
     */
    public static Alumni_external_job_positions ObtainAlumni_external_job_positions(Usuario user,
	    String alumni_external_job_positionscode) {

	/**
	 * 1. We obtain the object from the DB using the private getter and we
	 * return it. *
	 */

	Alumni_external_job_positions alumni_external_job_positions = getAlumni_external_job_positions(alumni_external_job_positionscode);
	return alumni_external_job_positions;
    }

    /**
     * This method obtains all instances of Alumni_external_job_positions, given a
     * list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     */
    public static Pair<Integer, List<Alumni_external_job_positions>> ObtainAllAlumni_external_job_positions(
	    Usuario user, ListConfigurator configurator) {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(
		Alumni_external_job_positions.class);

	// we only want to obtain the non deleted objects
	crit.add(Expression.eq("deleted", Boolean.FALSE));
	crit.setCacheable(true);

	configurator.setOrderBy("order_number");
	configurator.setAsc("");
	
	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	List<Alumni_external_job_positions> alumni_external_job_positions = (List<Alumni_external_job_positions>) crit
		.list();

	Pair<Integer, List<Alumni_external_job_positions>> pair = new Pair<Integer, List<Alumni_external_job_positions>>(
		count, alumni_external_job_positions);

	return pair;
    }
    
    /**
     * This method creates a Alumni_irb_job_positions.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOAlumni_irb_job_positions
     *            Alumni_irb_job_positions data transfer object (DTO) with the values
     *            of the new instance.
     * @return the new alumni_irb_job_positions created with this Use Case
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Alumni_irb_job_positions CreateAlumni_irb_job_positions(Usuario user,
	    Alumni_irb_job_positions TOAlumni_irb_job_positions)
	    throws InternalException, NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We create the new instance * */
	Alumni_irb_job_positions alumni_irb_job_positions = new Alumni_irb_job_positions();

	// we store if the grant is defined for later use
	boolean _job_position_type_is_defined = false;

	if (TOAlumni_irb_job_positions.getJob_position_types() != null
		&& TOAlumni_irb_job_positions.getJob_position_types().getAlumni_job_position_typescode() != null) {
	    // if grant is defined we replace the grant in the DTO with its
	    // current state in the DB.
		_job_position_type_is_defined = true;

		TOAlumni_irb_job_positions.setJob_position_types(getAlumni_job_position_types(TOAlumni_irb_job_positions.getJob_position_types()
		    .getAlumni_job_position_typescode()));
	}
	
	/**
	 * 3. We set all the simple attributes (no associations) to the new
	 * instance *
	 */

	alumni_irb_job_positions.setDescription(TOAlumni_irb_job_positions
		.getDescription());

	alumni_irb_job_positions.setShort_description(TOAlumni_irb_job_positions
			.getShort_description());
	
	alumni_irb_job_positions.setOrder_number(TOAlumni_irb_job_positions
			.getOrder_number());
	
	/** 4. We set the code to the new instance * */
	try {
	    IdentifyManager_Plain im = IdentifyManager_Plain.singleton();

	    alumni_irb_job_positions.setAlumni_irb_job_positionscode(im
		    .getId(TOAlumni_irb_job_positions));
	} catch (identifyException ie) {

	    log.error(
		    "Error en asignaciï¿½n de nuevo id en CreateAlumni_irb_job_positions",
		    ie);
	    throw new Error(ie.getMessage());
	}

	/** 5. We save the new instance to the DB* */
	HibernateUtil.getSession().save(alumni_irb_job_positions);

	if (_job_position_type_is_defined) {

		if (alumni_irb_job_positions.getJob_position_types() != null) {
			
			alumni_irb_job_positions.getJob_position_types().removeIalumni_irb_job_positions(alumni_irb_job_positions);
		}
	    if (TOAlumni_irb_job_positions.getJob_position_types() != null) {

	    	TOAlumni_irb_job_positions.getJob_position_types().addIalumni_irb_job_positions(alumni_irb_job_positions);
	    }

	    alumni_irb_job_positions.setJob_position_types(TOAlumni_irb_job_positions.getJob_position_types());
	}
	
	/** 6. We create an Audit message * */
	CreateCreationAuditmessage(user, alumni_irb_job_positions);

	/** 7. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return alumni_irb_job_positions;
    }

    /**
     * This method modifies a alumni_irb_job_positions.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOAlumni_irb_job_positions
     *            Alumni_irb_job_positions data transfer object (DTO) with the values
     *            of the modified instance. The code of this attribute indicates
     *            which alumni_irb_job_positions will be modified.
     * @return the modified alumni_irb_job_positions
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Alumni_irb_job_positions UpdateAlumni_irb_job_positions(Usuario user,
	    Alumni_irb_job_positions TOAlumni_irb_job_positions)
	    throws InternalException, NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain form the DB the instance to modify * */
	Alumni_irb_job_positions alumni_irb_job_positions = getAlumni_irb_job_positions(TOAlumni_irb_job_positions
		.getAlumni_irb_job_positionscode());
	;

	// we store if the grant is defined for later use
	boolean _job_position_type_is_defined = false;

	if (TOAlumni_irb_job_positions.getJob_position_types() != null
			&& TOAlumni_irb_job_positions.getJob_position_types().getAlumni_job_position_typescode() != null) {
		    // if grant is defined we replace the grant in the DTO with its
		    // current state in the DB.
			_job_position_type_is_defined = true;

			TOAlumni_irb_job_positions.setJob_position_types(getAlumni_job_position_types(TOAlumni_irb_job_positions.getJob_position_types()
			    .getAlumni_job_position_typescode()));
		}		
	
	/**
	 * 3. We set all the simple attributes (no associations) to the instance
	 * *
	 */

	alumni_irb_job_positions.setDescription(TOAlumni_irb_job_positions
		.getDescription());
	
	alumni_irb_job_positions.setShort_description(TOAlumni_irb_job_positions
			.getShort_description());
	
	alumni_irb_job_positions.setOrder_number(TOAlumni_irb_job_positions
			.getOrder_number());

	/**
	 * 4. We set the DTO version to the modified object and we update it
	 * with the new values in the DB. We evict and update the instance to
	 * prevent concurrent modification *
	 */
	HibernateUtil.getSession().evict(alumni_irb_job_positions);
	alumni_irb_job_positions.setVersion(TOAlumni_irb_job_positions.getVersion());
	HibernateUtil.getSession().update(alumni_irb_job_positions);

	if (_job_position_type_is_defined) {

	    if (TOAlumni_irb_job_positions.getJob_position_types() != null) {

	    	TOAlumni_irb_job_positions.getJob_position_types().addIalumni_irb_job_positions(alumni_irb_job_positions);
	    }

	    alumni_irb_job_positions.setJob_position_types(TOAlumni_irb_job_positions.getJob_position_types());
	}
	
	/** 5. We create an Audit message * */
	CreateModificationAuditmessage(user, alumni_irb_job_positions);

	/** 6. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return alumni_irb_job_positions;
    }

    /**
     * This method removes a alumni_irb_job_positions.
     * 
     * @param user
     *            The user who executes this use case
     * @param alumni_irb_job_positionscode
     *            Code of the alumni_irb_job_positions to be removed
     * @throws NoPermisosException
     */
    public static void RemoveAlumni_irb_job_positions(Usuario user,
	    String alumni_irb_job_positionscode) throws NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain the object to delete form the DB. * */
	Alumni_irb_job_positions alumni_irb_job_positions = getAlumni_irb_job_positions(alumni_irb_job_positionscode);

	/** 3. We mark it as deleted. * */
	alumni_irb_job_positions.setDeleted(Boolean.TRUE);

	/** 4. We create an Audit message * */
	CreateRemovealAuditmessage(user, alumni_irb_job_positions);

	/** 5. We commit the DB transaction. * */
	HibernateUtil.commitTransaction();
    }

    /**
     * This method obtains one instance of alumni_irb_job_positions given its code.
     * 
     * @param user
     *            The user who executes this use case
     * @param alumni_irb_job_positionscode
     *            Code of the alumni_irb_job_positions to be obtained
     * @return Alumni_irb_job_positions with the given code.
     */
    public static Alumni_irb_job_positions ObtainAlumni_irb_job_positions(Usuario user,
	    String alumni_irb_job_positionscode) {

	/**
	 * 1. We obtain the object from the DB using the private getter and we
	 * return it. *
	 */

	Alumni_irb_job_positions alumni_irb_job_positions = getAlumni_irb_job_positions(alumni_irb_job_positionscode);
	return alumni_irb_job_positions;
    }

    /**
     * This method obtains all instances of Alumni_irb_job_positions, given a
     * list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     */
    public static Pair<Integer, List<Alumni_irb_job_positions>> ObtainAllAlumni_irb_job_positions(
	    Usuario user, ListConfigurator configurator) {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(
		Alumni_irb_job_positions.class);

	// we only want to obtain the non deleted objects
	crit.add(Expression.eq("deleted", Boolean.FALSE));
	crit.setCacheable(true);

	configurator.setOrderBy("order_number");
	configurator.setAsc("");
	
	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	List<Alumni_irb_job_positions> alumni_irb_job_positions = (List<Alumni_irb_job_positions>) crit
		.list();

	Pair<Integer, List<Alumni_irb_job_positions>> pair = new Pair<Integer, List<Alumni_irb_job_positions>>(
		count, alumni_irb_job_positions);

	return pair;
    }
    
    /**
     * This method creates a Alumni_job_position_types.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOAlumni_job_position_types
     *            Alumni_job_position_types data transfer object (DTO) with the values
     *            of the new instance.
     * @return the new alumni_job_position_types created with this Use Case
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Alumni_job_position_types CreateAlumni_job_position_types(Usuario user,
	    Alumni_job_position_types TOAlumni_job_position_types)
	    throws InternalException, NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We create the new instance * */
	Alumni_job_position_types alumni_job_position_types = new Alumni_job_position_types();

	/**
	 * 3. We set all the simple attributes (no associations) to the new
	 * instance *
	 */

	alumni_job_position_types.setDescription(TOAlumni_job_position_types
		.getDescription());

	alumni_job_position_types.setShort_description(TOAlumni_job_position_types
			.getShort_description());
	
	alumni_job_position_types.setOrder_number(TOAlumni_job_position_types
			.getOrder_number());
	
	/** 4. We set the code to the new instance * */
	try {
	    IdentifyManager_Plain im = IdentifyManager_Plain.singleton();

	    alumni_job_position_types.setAlumni_job_position_typescode(im
		    .getId(TOAlumni_job_position_types));
	} catch (identifyException ie) {

	    log.error(
		    "Error en asignaciï¿½n de nuevo id en CreateAlumni_job_position_types",
		    ie);
	    throw new Error(ie.getMessage());
	}

	/** 5. We save the new instance to the DB* */
	HibernateUtil.getSession().save(alumni_job_position_types);

	/** 6. We create an Audit message * */
	CreateCreationAuditmessage(user, alumni_job_position_types);

	/** 7. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return alumni_job_position_types;
    }

    /**
     * This method modifies a alumni_job_position_types.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOAlumni_job_position_types
     *            Alumni_job_position_types data transfer object (DTO) with the values
     *            of the modified instance. The code of this attribute indicates
     *            which alumni_job_position_types will be modified.
     * @return the modified alumni_job_position_types
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Alumni_job_position_types UpdateAlumni_job_position_types(Usuario user,
	    Alumni_job_position_types TOAlumni_job_position_types)
	    throws InternalException, NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain form the DB the instance to modify * */
	Alumni_job_position_types alumni_job_position_types = getAlumni_job_position_types(TOAlumni_job_position_types
		.getAlumni_job_position_typescode());
	;

	/**
	 * 3. We set all the simple attributes (no associations) to the instance
	 * *
	 */

	alumni_job_position_types.setDescription(TOAlumni_job_position_types
		.getDescription());
	
	alumni_job_position_types.setShort_description(TOAlumni_job_position_types
			.getShort_description());
	
	alumni_job_position_types.setOrder_number(TOAlumni_job_position_types
			.getOrder_number());

	/**
	 * 4. We set the DTO version to the modified object and we update it
	 * with the new values in the DB. We evict and update the instance to
	 * prevent concurrent modification *
	 */
	HibernateUtil.getSession().evict(alumni_job_position_types);
	alumni_job_position_types.setVersion(TOAlumni_job_position_types.getVersion());
	HibernateUtil.getSession().update(alumni_job_position_types);

	/** 5. We create an Audit message * */
	CreateModificationAuditmessage(user, alumni_job_position_types);

	/** 6. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return alumni_job_position_types;
    }

    /**
     * This method removes a alumni_job_position_types.
     * 
     * @param user
     *            The user who executes this use case
     * @param alumni_job_position_typescode
     *            Code of the alumni_job_position_types to be removed
     * @throws NoPermisosException
     */
    public static void RemoveAlumni_job_position_types(Usuario user,
	    String alumni_job_position_typescode) throws NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain the object to delete form the DB. * */
	Alumni_job_position_types alumni_job_position_types = getAlumni_job_position_types(alumni_job_position_typescode);

	/** 3. We mark it as deleted. * */
	alumni_job_position_types.setDeleted(Boolean.TRUE);

	/** 4. We create an Audit message * */
	CreateRemovealAuditmessage(user, alumni_job_position_types);

	/** 5. We commit the DB transaction. * */
	HibernateUtil.commitTransaction();
    }

    /**
     * This method obtains one instance of alumni_job_position_types given its code.
     * 
     * @param user
     *            The user who executes this use case
     * @param alumni_job_position_typescode
     *            Code of the alumni_job_position_types to be obtained
     * @return Alumni_job_position_types with the given code.
     */
    public static Alumni_job_position_types ObtainAlumni_job_position_types(Usuario user,
	    String alumni_job_position_typescode) {

	/**
	 * 1. We obtain the object from the DB using the private getter and we
	 * return it. *
	 */

	Alumni_job_position_types alumni_job_position_types = getAlumni_job_position_types(alumni_job_position_typescode);
	return alumni_job_position_types;
    }

    /**
     * This method obtains all instances of Alumni_job_position_types, given a
     * list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     */
    public static Pair<Integer, List<Alumni_job_position_types>> ObtainAllAlumni_job_position_types(
	    Usuario user, ListConfigurator configurator) {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(
		Alumni_job_position_types.class);

	// we only want to obtain the non deleted objects
	crit.add(Expression.eq("deleted", Boolean.FALSE));
	crit.setCacheable(true);

	configurator.setOrderBy("order_number");
	configurator.setAsc("");
	
	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	List<Alumni_job_position_types> alumni_job_position_types = (List<Alumni_job_position_types>) crit
		.list();

	Pair<Integer, List<Alumni_job_position_types>> pair = new Pair<Integer, List<Alumni_job_position_types>>(
		count, alumni_job_position_types);

	return pair;
    }
    
    /**
     * This method creates a Alumni_communications.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOAlumni_communications
     *            Alumni_communications data transfer object (DTO) with the values
     *            of the new instance.
     * @return the new alumni_communications created with this Use Case
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Alumni_communications CreateAlumni_communications(Usuario user,
	    Alumni_communications TOAlumni_communications)
	    throws InternalException, NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We create the new instance * */
	Alumni_communications alumni_communications = new Alumni_communications();

	/**
	 * 3. We set all the simple attributes (no associations) to the new
	 * instance *
	 */

	alumni_communications.setDescription(TOAlumni_communications
		.getDescription());

	alumni_communications.setShort_description(TOAlumni_communications
			.getShort_description());
	
	alumni_communications.setOrder_number(TOAlumni_communications
			.getOrder_number());
	
	/** 4. We set the code to the new instance * */
	try {
	    IdentifyManager_Plain im = IdentifyManager_Plain.singleton();

	    alumni_communications.setAlumni_communicationscode(im
		    .getId(TOAlumni_communications));
	} catch (identifyException ie) {

	    log.error(
		    "Error en asignaciï¿½n de nuevo id en CreateAlumni_communications",
		    ie);
	    throw new Error(ie.getMessage());
	}

	/** 5. We save the new instance to the DB* */
	HibernateUtil.getSession().save(alumni_communications);

	/** 6. We create an Audit message * */
	CreateCreationAuditmessage(user, alumni_communications);

	/** 7. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return alumni_communications;
    }

    /**
     * This method modifies a alumni_communications.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOAlumni_communications
     *            Alumni_communications data transfer object (DTO) with the values
     *            of the modified instance. The code of this attribute indicates
     *            which alumni_communications will be modified.
     * @return the modified alumni_communications
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Alumni_communications UpdateAlumni_communications(Usuario user,
	    Alumni_communications TOAlumni_communications)
	    throws InternalException, NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain form the DB the instance to modify * */
	Alumni_communications alumni_communications = getAlumni_communications(TOAlumni_communications
		.getAlumni_communicationscode());
	;

	/**
	 * 3. We set all the simple attributes (no associations) to the instance
	 * *
	 */

	alumni_communications.setDescription(TOAlumni_communications
		.getDescription());
	
	alumni_communications.setShort_description(TOAlumni_communications
			.getShort_description());
	
	alumni_communications.setOrder_number(TOAlumni_communications
			.getOrder_number());

	/**
	 * 4. We set the DTO version to the modified object and we update it
	 * with the new values in the DB. We evict and update the instance to
	 * prevent concurrent modification *
	 */
	HibernateUtil.getSession().evict(alumni_communications);
	alumni_communications.setVersion(TOAlumni_communications.getVersion());
	HibernateUtil.getSession().update(alumni_communications);

	/** 5. We create an Audit message * */
	CreateModificationAuditmessage(user, alumni_communications);

	/** 6. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return alumni_communications;
    }

    /**
     * This method removes a alumni_communications.
     * 
     * @param user
     *            The user who executes this use case
     * @param alumni_communicationscode
     *            Code of the alumni_communications to be removed
     * @throws NoPermisosException
     */
    public static void RemoveAlumni_communications(Usuario user,
	    String alumni_communicationscode) throws NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain the object to delete form the DB. * */
	Alumni_communications alumni_communications = getAlumni_communications(alumni_communicationscode);

	/** 3. We mark it as deleted. * */
	alumni_communications.setDeleted(Boolean.TRUE);

	/** 4. We create an Audit message * */
	CreateRemovealAuditmessage(user, alumni_communications);

	/** 5. We commit the DB transaction. * */
	HibernateUtil.commitTransaction();
    }

    /**
     * This method obtains one instance of alumni_communications given its code.
     * 
     * @param user
     *            The user who executes this use case
     * @param alumni_communicationscode
     *            Code of the alumni_communications to be obtained
     * @return Alumni_communications with the given code.
     */
    public static Alumni_communications ObtainAlumni_communications(Usuario user,
	    String alumni_communicationscode) {

	/**
	 * 1. We obtain the object from the DB using the private getter and we
	 * return it. *
	 */

	Alumni_communications alumni_communications = getAlumni_communications(alumni_communicationscode);
	return alumni_communications;
    }

    /**
     * This method obtains all instances of Alumni_communications, given a
     * list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     */
    public static Pair<Integer, List<Alumni_communications>> ObtainAllAlumni_communications(
	    Usuario user, ListConfigurator configurator) {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(
		Alumni_communications.class);

	// we only want to obtain the non deleted objects
	crit.add(Expression.eq("deleted", Boolean.FALSE));
	crit.setCacheable(true);

	configurator.setOrderBy("order_number");
	configurator.setAsc("");
	
	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	List<Alumni_communications> alumni_communications = (List<Alumni_communications>) crit
		.list();

	Pair<Integer, List<Alumni_communications>> pair = new Pair<Integer, List<Alumni_communications>>(
		count, alumni_communications);

	return pair;
    }
    
    /**
     * This method creates a Alumni_external_job_sectors.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOAlumni_external_job_sectors
     *            Alumni_external_job_sectors data transfer object (DTO) with the values
     *            of the new instance.
     * @return the new alumni_external_job_sectors created with this Use Case
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Alumni_external_job_sectors CreateAlumni_external_job_sectors(Usuario user,
	    Alumni_external_job_sectors TOAlumni_external_job_sectors)
	    throws InternalException, NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We create the new instance * */
	Alumni_external_job_sectors alumni_external_job_sectors = new Alumni_external_job_sectors();

	/**
	 * 3. We set all the simple attributes (no associations) to the new
	 * instance *
	 */

	alumni_external_job_sectors.setDescription(TOAlumni_external_job_sectors
		.getDescription());

	alumni_external_job_sectors.setShort_description(TOAlumni_external_job_sectors
			.getShort_description());
	
	alumni_external_job_sectors.setOrder_number(TOAlumni_external_job_sectors
			.getOrder_number());
	
	/** 4. We set the code to the new instance * */
	try {
	    IdentifyManager_Plain im = IdentifyManager_Plain.singleton();

	    alumni_external_job_sectors.setAlumni_external_job_sectorscode(im
		    .getId(TOAlumni_external_job_sectors));
	} catch (identifyException ie) {

	    log.error(
		    "Error en asignaciï¿½n de nuevo id en CreateAlumni_external_job_sectors",
		    ie);
	    throw new Error(ie.getMessage());
	}

	/** 5. We save the new instance to the DB* */
	HibernateUtil.getSession().save(alumni_external_job_sectors);

	/** 6. We create an Audit message * */
	CreateCreationAuditmessage(user, alumni_external_job_sectors);

	/** 7. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return alumni_external_job_sectors;
    }

    /**
     * This method modifies a alumni_external_job_sectors.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOAlumni_external_job_sectors
     *            Alumni_external_job_sectors data transfer object (DTO) with the values
     *            of the modified instance. The code of this attribute indicates
     *            which alumni_external_job_sectors will be modified.
     * @return the modified alumni_external_job_sectors
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Alumni_external_job_sectors UpdateAlumni_external_job_sectors(Usuario user,
	    Alumni_external_job_sectors TOAlumni_external_job_sectors)
	    throws InternalException, NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain form the DB the instance to modify * */
	Alumni_external_job_sectors alumni_external_job_sectors = getAlumni_external_job_sectors(TOAlumni_external_job_sectors
		.getAlumni_external_job_sectorscode());
	;

	/**
	 * 3. We set all the simple attributes (no associations) to the instance
	 * *
	 */

	alumni_external_job_sectors.setDescription(TOAlumni_external_job_sectors
		.getDescription());
	
	alumni_external_job_sectors.setShort_description(TOAlumni_external_job_sectors
			.getShort_description());
	
	alumni_external_job_sectors.setOrder_number(TOAlumni_external_job_sectors
			.getOrder_number());

	/**
	 * 4. We set the DTO version to the modified object and we update it
	 * with the new values in the DB. We evict and update the instance to
	 * prevent concurrent modification *
	 */
	HibernateUtil.getSession().evict(alumni_external_job_sectors);
	alumni_external_job_sectors.setVersion(TOAlumni_external_job_sectors.getVersion());
	HibernateUtil.getSession().update(alumni_external_job_sectors);

	/** 5. We create an Audit message * */
	CreateModificationAuditmessage(user, alumni_external_job_sectors);

	/** 6. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return alumni_external_job_sectors;
    }

    /**
     * This method removes a alumni_external_job_sectors.
     * 
     * @param user
     *            The user who executes this use case
     * @param alumni_external_job_sectorscode
     *            Code of the alumni_external_job_sectors to be removed
     * @throws NoPermisosException
     */
    public static void RemoveAlumni_external_job_sectors(Usuario user,
	    String alumni_external_job_sectorscode) throws NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain the object to delete form the DB. * */
	Alumni_external_job_sectors alumni_external_job_sectors = getAlumni_external_job_sectors(alumni_external_job_sectorscode);

	/** 3. We mark it as deleted. * */
	alumni_external_job_sectors.setDeleted(Boolean.TRUE);

	/** 4. We create an Audit message * */
	CreateRemovealAuditmessage(user, alumni_external_job_sectors);

	/** 5. We commit the DB transaction. * */
	HibernateUtil.commitTransaction();
    }

    /**
     * This method obtains one instance of alumni_external_job_sectors given its code.
     * 
     * @param user
     *            The user who executes this use case
     * @param alumni_external_job_sectorscode
     *            Code of the alumni_external_job_sectors to be obtained
     * @return Alumni_external_job_sectors with the given code.
     */
    public static Alumni_external_job_sectors ObtainAlumni_external_job_sectors(Usuario user,
	    String alumni_external_job_sectorscode) {

	/**
	 * 1. We obtain the object from the DB using the private getter and we
	 * return it. *
	 */

	Alumni_external_job_sectors alumni_external_job_sectors = getAlumni_external_job_sectors(alumni_external_job_sectorscode);
	return alumni_external_job_sectors;
    }

    /**
     * This method obtains all instances of Alumni_external_job_sectors, given a
     * list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     */
    public static Pair<Integer, List<Alumni_external_job_sectors>> ObtainAllAlumni_external_job_sectors(
	    Usuario user, ListConfigurator configurator) {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(
		Alumni_external_job_sectors.class);

	// we only want to obtain the non deleted objects
	crit.add(Expression.eq("deleted", Boolean.FALSE));
	crit.setCacheable(true);

	configurator.setOrderBy("order_number");
	configurator.setAsc("");
	
	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	List<Alumni_external_job_sectors> alumni_external_job_sectors = (List<Alumni_external_job_sectors>) crit
		.list();

	Pair<Integer, List<Alumni_external_job_sectors>> pair = new Pair<Integer, List<Alumni_external_job_sectors>>(
		count, alumni_external_job_sectors);

	return pair;
    }
    
    
    /**
     * This method creates a Holiday.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOHoliday
     *            Holiday data transfer object (DTO) with the values of the new
     *            instance.
     * @return the new holiday created with this Use Case
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Holiday CreateHoliday(Usuario user, Holiday TOHoliday)
	    throws InternalException, NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/**
	 * 2. For each association from the TOHoliday that are filled in the DTO
	 * we put the real objects from the DB. *
	 */

	// we store if the holiday_personal is defined for later use
	boolean _holiday_personalIsDefined = false;

	if (TOHoliday.getHoliday_personal() != null
		&& TOHoliday.getHoliday_personal().getPersonalcode() != null) {
	    // if holiday_personal is defined we replace the holiday_personal in
	    // the DTO with its current state in the DB.
	    _holiday_personalIsDefined = true;

	    TOHoliday.setHoliday_personal(getPersonal(TOHoliday
		    .getHoliday_personal().getPersonalcode()));
	}

	// we store if the type_of_holiday is defined for later use
	boolean _type_of_holidayIsDefined = false;

	if (TOHoliday.getType_of_holiday() != null
		&& TOHoliday.getType_of_holiday().getType_of_holidayscode() != null) {
	    // if type_of_holiday is defined we replace the type_of_holiday in
	    // the DTO with its current state in the DB.
	    _type_of_holidayIsDefined = true;

	    TOHoliday.setType_of_holiday(getType_of_holidays(TOHoliday
		    .getType_of_holiday().getType_of_holidayscode()));
	}

	/** 3. We create the new instance * */
	Holiday holiday = new Holiday();

	/**
	 * 4. We set all the simple attributes (no associations) to the new
	 * instance *
	 */

	holiday.setStart_date(TOHoliday.getStart_date());

	holiday.setEnd_date(TOHoliday.getEnd_date());

	holiday.setDays(TOHoliday.getDays());

	holiday.setDescription(TOHoliday.getDescription());

	/** 5. We set the code to the new instance * */
	try {
	    IdentifyManager_Plain im = IdentifyManager_Plain.singleton();

	    holiday.setHolidaycode(im.getId(TOHoliday));
	} catch (identifyException ie) {

	    log.error("Error en asignaciï¿½n de nuevo id en CreateHoliday", ie);
	    throw new Error(ie.getMessage());
	}

	/** 6. We save the new instance to the DB* */
	HibernateUtil.getSession().save(holiday);

	/**
	 * We associate the current object to the other objects (only in case
	 * that the associations where defined in the DTO) *
	 */

	if (_holiday_personalIsDefined) {

	    if (TOHoliday.getHoliday_personal() != null) {

		TOHoliday.getHoliday_personal().addIholiday_personal(holiday);
	    }

	    holiday.setHoliday_personal(TOHoliday.getHoliday_personal());
	}

	if (_type_of_holidayIsDefined) {

	    if (TOHoliday.getType_of_holiday() != null) {

		TOHoliday.getType_of_holiday().addItype_of_holiday(holiday);
	    }

	    holiday.setType_of_holiday(TOHoliday.getType_of_holiday());
	}

	/** 7. We create an Audit message * */
	CreateCreationAuditmessage(user, holiday);

	/** 8. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return holiday;
    }

    /**
     * This method modifies a holiday.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOHoliday
     *            Holiday data transfer object (DTO) with the values of the
     *            modified instance. The code of this attribute indicates which
     *            holiday will be modified.
     * @return the modified holiday
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Holiday UpdateHoliday(Usuario user, Holiday TOHoliday)
	    throws InternalException, NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/**
	 * 2. For each association from the TOHoliday that are filled in the DTO
	 * we put the real objects from the DB. *
	 */

	// we store if the holiday_personal is defined for later use
	boolean _holiday_personalIsDefined = false;

	if (TOHoliday.getHoliday_personal() != null
		&& TOHoliday.getHoliday_personal().getPersonalcode() != null) {
	    // if holiday_personal is defined we replace the holiday_personal in
	    // the DTO with its current state in the DB.
	    _holiday_personalIsDefined = true;

	    TOHoliday.setHoliday_personal(getPersonal(TOHoliday
		    .getHoliday_personal().getPersonalcode()));
	}

	// we store if the type_of_holiday is defined for later use
	boolean _type_of_holidayIsDefined = false;

	if (TOHoliday.getType_of_holiday() != null
		&& TOHoliday.getType_of_holiday().getType_of_holidayscode() != null) {
	    // if type_of_holiday is defined we replace the type_of_holiday in
	    // the DTO with its current state in the DB.
	    _type_of_holidayIsDefined = true;

	    TOHoliday.setType_of_holiday(getType_of_holidays(TOHoliday
		    .getType_of_holiday().getType_of_holidayscode()));
	}

	/** 3. We obtain form the DB the instance to modify * */
	Holiday holiday = getHoliday(TOHoliday.getHolidaycode());
	;

	/**
	 * 4. We set all the simple attributes (no associations) to the instance
	 * *
	 */

	holiday.setStart_date(TOHoliday.getStart_date());

	holiday.setEnd_date(TOHoliday.getEnd_date());

	holiday.setDays(TOHoliday.getDays());

	holiday.setDescription(TOHoliday.getDescription());

	/**
	 * 5. We set the DTO version to the modified object and we update it
	 * with the new values in the DB. We evict and update the instance to
	 * prevent concurrent modification *
	 */
	HibernateUtil.getSession().evict(holiday);
	holiday.setVersion(TOHoliday.getVersion());
	HibernateUtil.getSession().update(holiday);

	/**
	 * We associate/disassociate the current object to the other objects
	 * (only in case that the associations where defined in the DTO) *
	 */

	if (_holiday_personalIsDefined) {

	    if (holiday.getHoliday_personal() != null) {

		holiday.getHoliday_personal().removeIholiday_personal(holiday);
	    }

	    if (TOHoliday.getHoliday_personal() != null) {

		TOHoliday.getHoliday_personal().addIholiday_personal(holiday);
	    }

	    holiday.setHoliday_personal(TOHoliday.getHoliday_personal());
	}

	if (_type_of_holidayIsDefined) {

	    if (holiday.getType_of_holiday() != null) {

		holiday.getType_of_holiday().removeItype_of_holiday(holiday);
	    }

	    if (TOHoliday.getType_of_holiday() != null) {

		TOHoliday.getType_of_holiday().addItype_of_holiday(holiday);
	    }

	    holiday.setType_of_holiday(TOHoliday.getType_of_holiday());
	}

	/** 6. We create an Audit message * */
	CreateModificationAuditmessage(user, holiday);

	/** 7. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return holiday;
    }

    /**
     * This method removes a holiday.
     * 
     * @param user
     *            The user who executes this use case
     * @param holidaycode
     *            Code of the holiday to be removed
     * @throws NoPermisosException
     */
    public static void RemoveHoliday(Usuario user, String holidaycode)
	    throws NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain the object to delete form the DB. * */
	Holiday holiday = getHoliday(holidaycode);

	/** 3. We mark it as deleted. * */
	holiday.setDeleted(Boolean.TRUE);

	/** 4. We create an Audit message * */
	CreateRemovealAuditmessage(user, holiday);

	/** 5. We commit the DB transaction. * */
	HibernateUtil.commitTransaction();
    }

    /**
     * This method obtains one instance of holiday given its code.
     * 
     * @param user
     *            The user who executes this use case
     * @param holidaycode
     *            Code of the holiday to be obtained
     * @return Holiday with the given code.
     */
    public static Holiday ObtainHoliday(Usuario user, String holidaycode) {

	/**
	 * 1. We obtain the object from the DB using the private getter and we
	 * return it. *
	 */

	Holiday holiday = getHoliday(holidaycode);
	return holiday;
    }

    /**
     * This method obtains all instances of Holiday, given a list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     */
    public static Pair<Integer, List<Holiday>> ObtainAllHoliday(Usuario user,
	    ListConfigurator configurator) {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession()
		.createCriteria(Holiday.class);

	// we only want to obtain the non deleted objects
	crit.add(Expression.eq("deleted", Boolean.FALSE));

	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	List<Holiday> holidays = (List<Holiday>) crit.list();

	Pair<Integer, List<Holiday>> pair = new Pair<Integer, List<Holiday>>(
		count, holidays);

	return pair;
    }

    /**
     * This method creates a Grant_concession.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOGrant_concession
     *            Grant_concession data transfer object (DTO) with the values of
     *            the new instance.
     * @return the new grant_concession created with this Use Case
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Grant_concession CreateGrant_concession(Usuario user,
	    Grant_concession TOGrant_concession) throws InternalException,
	    NoPermisosException {

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/**
	 * 2. For each association from the TOGrant_concession that are filled
	 * in the DTO we put the real objects from the DB. *
	 */

	// we store if the grant_concession_personal is defined for later use
	boolean _grant_concession_personalIsDefined = false;

	if (TOGrant_concession.getGrant_concession_personal() != null
		&& TOGrant_concession.getGrant_concession_personal()
			.getPersonalcode() != null) {
	    // if grant_concession_personal is defined we replace the
	    // grant_concession_personal in the DTO with its current state in
	    // the DB.
	    _grant_concession_personalIsDefined = true;

	    TOGrant_concession
		    .setGrant_concession_personal(getPersonal(TOGrant_concession
			    .getGrant_concession_personal().getPersonalcode()));
	}

	// testIsHHRROrItself(user,
	// TOGrant_concession.getGrant_concession_personal());

	// we store if the grant is defined for later use
	boolean _grantIsDefined = false;

	if (TOGrant_concession.getGrant() != null
		&& TOGrant_concession.getGrant().getGrantcode() != null) {
	    // if grant is defined we replace the grant in the DTO with its
	    // current state in the DB.
	    _grantIsDefined = true;

	    TOGrant_concession.setGrant(getGrant(TOGrant_concession.getGrant()
		    .getGrantcode()));
	}

	/** 3. We create the new instance * */
	Grant_concession grant_concession = new Grant_concession();

	/**
	 * 4. We set all the simple attributes (no associations) to the new
	 * instance *
	 */

	grant_concession.setStart_date(TOGrant_concession.getStart_date());

	grant_concession.setEnd_date(TOGrant_concession.getEnd_date());

	grant_concession.setCall_code(TOGrant_concession.getCall_code());

	/** 5. We set the code to the new instance * */
	try {
	    IdentifyManager_Plain im = IdentifyManager_Plain.singleton();

	    grant_concession.setGrant_concessioncode(im
		    .getId(TOGrant_concession));
	} catch (identifyException ie) {

	    log.error(
		    "Error en asignaciï¿½n de nuevo id en CreateGrant_concession",
		    ie);
	    throw new Error(ie.getMessage());
	}

	/** 6. We save the new instance to the DB* */
	HibernateUtil.getSession().save(grant_concession);

	/**
	 * We associate the current object to the other objects (only in case
	 * that the associations where defined in the DTO) *
	 */

	if (_grant_concession_personalIsDefined) {

	    if (TOGrant_concession.getGrant_concession_personal() != null) {

		TOGrant_concession.getGrant_concession_personal()
			.addIgrant_concession_personal(grant_concession);
	    }

	    grant_concession.setGrant_concession_personal(TOGrant_concession
		    .getGrant_concession_personal());
	}

	if (_grantIsDefined) {

	    if (TOGrant_concession.getGrant() != null) {

		TOGrant_concession.getGrant().addIgrant(grant_concession);
	    }

	    grant_concession.setGrant(TOGrant_concession.getGrant());
	}

	/** 7. We create an Audit message * */
	CreateCreationAuditmessage(user, grant_concession);

	/** 8. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return grant_concession;
    }

    /**
     * This method modifies a grant_concession.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOGrant_concession
     *            Grant_concession data transfer object (DTO) with the values of
     *            the modified instance. The code of this attribute indicates
     *            which grant_concession will be modified.
     * @return the modified grant_concession
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Grant_concession UpdateGrant_concession(Usuario user,
	    Grant_concession TOGrant_concession) throws InternalException,
	    NoPermisosException {

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 3. We obtain form the DB the instance to modify * */
	Grant_concession grant_concession = getGrant_concession(TOGrant_concession
		.getGrant_concessioncode());
	// testIsHHRROrItself(user,
	// grant_concession.getGrant_concession_personal());

	/**
	 * 2. For each association from the TOGrant_concession that are filled
	 * in the DTO we put the real objects from the DB. *
	 */

	// we store if the grant_concession_personal is defined for later use
	boolean _grant_concession_personalIsDefined = false;

	if (TOGrant_concession.getGrant_concession_personal() != null
		&& TOGrant_concession.getGrant_concession_personal()
			.getPersonalcode() != null) {
	    // if grant_concession_personal is defined we replace the
	    // grant_concession_personal in the DTO with its current state in
	    // the DB.
	    _grant_concession_personalIsDefined = true;

	    TOGrant_concession
		    .setGrant_concession_personal(getPersonal(TOGrant_concession
			    .getGrant_concession_personal().getPersonalcode()));
	}

	// we store if the grant is defined for later use
	boolean _grantIsDefined = false;

	if (TOGrant_concession.getGrant() != null
		&& TOGrant_concession.getGrant().getGrantcode() != null) {
	    // if grant is defined we replace the grant in the DTO with its
	    // current state in the DB.
	    _grantIsDefined = true;

	    TOGrant_concession.setGrant(getGrant(TOGrant_concession.getGrant()
		    .getGrantcode()));
	}

	/**
	 * 4. We set all the simple attributes (no associations) to the instance
	 * *
	 */

	grant_concession.setStart_date(TOGrant_concession.getStart_date());

	grant_concession.setEnd_date(TOGrant_concession.getEnd_date());

	grant_concession.setCall_code(TOGrant_concession.getCall_code());

	
	/**
	 * 5. We set the DTO version to the modified object and we update it
	 * with the new values in the DB. We evict and update the instance to
	 * prevent concurrent modification *
	 */
	HibernateUtil.getSession().evict(grant_concession);
	grant_concession.setVersion(TOGrant_concession.getVersion());
	HibernateUtil.getSession().update(grant_concession);

	/**
	 * We associate/disassociate the current object to the other objects
	 * (only in case that the associations where defined in the DTO) *
	 */

	if (_grant_concession_personalIsDefined) {

	    if (grant_concession.getGrant_concession_personal() != null) {

		grant_concession.getGrant_concession_personal()
			.removeIgrant_concession_personal(grant_concession);
	    }

	    if (TOGrant_concession.getGrant_concession_personal() != null) {

		TOGrant_concession.getGrant_concession_personal()
			.addIgrant_concession_personal(grant_concession);
	    }

	    grant_concession.setGrant_concession_personal(TOGrant_concession
		    .getGrant_concession_personal());
	}

	if (_grantIsDefined) {

	    if (grant_concession.getGrant() != null) {

		grant_concession.getGrant().removeIgrant(grant_concession);
	    }

	    if (TOGrant_concession.getGrant() != null) {

		TOGrant_concession.getGrant().addIgrant(grant_concession);
	    }

	    grant_concession.setGrant(TOGrant_concession.getGrant());
	}

	/** 6. We create an Audit message * */
	CreateModificationAuditmessage(user, grant_concession);

	/** 7. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return grant_concession;
    }

    /**
     * This method removes a grant_concession.
     * 
     * @param user
     *            The user who executes this use case
     * @param grant_concessioncode
     *            Code of the grant_concession to be removed
     * @throws NoPermisosException
     */
    public static void RemoveGrant_concession(Usuario user,
	    String grant_concessioncode) throws NoPermisosException {

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain the object to delete form the DB. * */
	Grant_concession grant_concession = getGrant_concession(grant_concessioncode);
	// testIsHHRROrItself(user,
	// grant_concession.getGrant_concession_personal());

	/** 3. We mark it as deleted. * */
	grant_concession.setDeleted(Boolean.TRUE);

	/** 4. We create an Audit message * */
	CreateRemovealAuditmessage(user, grant_concession);

	/** 5. We commit the DB transaction. * */
	HibernateUtil.commitTransaction();
    }

    /**
     * This method obtains one instance of grant_concession given its code.
     * 
     * @param user
     *            The user who executes this use case
     * @param grant_concessioncode
     *            Code of the grant_concession to be obtained
     * @return Grant_concession with the given code.
     */
    public static Grant_concession ObtainGrant_concession(Usuario user,
	    String grant_concessioncode) {

	/**
	 * 1. We obtain the object from the DB using the private getter and we
	 * return it. *
	 */

	Grant_concession grant_concession = getGrant_concession(grant_concessioncode);
	return grant_concession;
    }

    /**
     * This method obtains all instances of Grant_concession, given a
     * list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     */
    public static Pair<Integer, List<Grant_concession>> ObtainAllGrant_concession(
	    Usuario user, ListConfigurator configurator) {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(
		Grant_concession.class);

	// we only want to obtain the non deleted objects
	crit.add(Expression.eq("deleted", Boolean.FALSE));

	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	List<Grant_concession> grant_concessions = (List<Grant_concession>) crit
		.list();

	Pair<Integer, List<Grant_concession>> pair = new Pair<Integer, List<Grant_concession>>(
		count, grant_concessions);

	return pair;
    }

    /**
     * This method creates a Academic_info.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOAcademic_info
     *            Academic_info data transfer object (DTO) with the values of
     *            the new instance.
     * @return the new academic_info created with this Use Case
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Academic_info CreateAcademic_info(Usuario user,
	    Academic_info TOAcademic_info) throws InternalException,
	    NoPermisosException {

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/**
	 * 2. For each association from the TOAcademic_info that are filled
	 * in the DTO we put the real objects from the DB. *
	 */

	// we store if the academic_info_personal is defined for later use
	boolean _academic_info_personalIsDefined = false;

	if (TOAcademic_info.getAcademic_info_personal() != null
		&& TOAcademic_info.getAcademic_info_personal()
			.getPersonalcode() != null) {
	    // if academic_info_personal is defined we replace the
	    // academic_info_personal in the DTO with its current state in
	    // the DB.
	    _academic_info_personalIsDefined = true;

	    TOAcademic_info
		    .setAcademic_info_personal(getPersonal(TOAcademic_info
			    .getAcademic_info_personal().getPersonalcode()));
	}

	// testIsHHRROrItself(user,
	// TOAcademic_info.getAcademic_info_personal());

	// we store if the grant is defined for later use
	boolean _type_of_studyIsDefined = false;

	if (TOAcademic_info.getType_of_study() != null
		&& TOAcademic_info.getType_of_study().getType_of_studycode() != null) {
	    // if grant is defined we replace the grant in the DTO with its
	    // current state in the DB.
	    _type_of_studyIsDefined = true;

	    TOAcademic_info.setType_of_study(getType_of_study(TOAcademic_info.getType_of_study()
		    .getType_of_studycode()));
	}
	
	boolean _lab_rotation_labIsDefined = false;

	if (TOAcademic_info.getLab_rotation_lab() != null
		&& TOAcademic_info.getLab_rotation_lab().getCode() != null) {
	    // if grant is defined we replace the grant in the DTO with its
	    // current state in the DB.
		_lab_rotation_labIsDefined = true;

	    TOAcademic_info.setLab_rotation_lab(getResearch_group(TOAcademic_info.getLab_rotation_lab()
		    .getCode()));
	}
	
	boolean _lab_rotation_lab2IsDefined = false;

	if (TOAcademic_info.getLab_rotation_lab2() != null
		&& TOAcademic_info.getLab_rotation_lab2().getCode() != null) {
	    // if grant is defined we replace the grant in the DTO with its
	    // current state in the DB.
		_lab_rotation_lab2IsDefined = true;

	    TOAcademic_info.setLab_rotation_lab2(getResearch_group(TOAcademic_info.getLab_rotation_lab2()
		    .getCode()));
	}

	/** 3. We create the new instance * */
	Academic_info academic_info = new Academic_info();

	/**
	 * 4. We set all the simple attributes (no associations) to the new
	 * instance *
	 */

	academic_info.setStart_date(TOAcademic_info.getStart_date());

	academic_info.setEnd_date(TOAcademic_info.getEnd_date());
	
	academic_info.setTac0(TOAcademic_info.isTac0());
	academic_info.setUniversity_enrolled(TOAcademic_info
		.getUniversity_enrolled());
	
	academic_info.setLab_rotation_date(TOAcademic_info.getLab_rotation_date());
	
	academic_info.setThesis_defense_date(TOAcademic_info.getThesis_defense_date());	
	academic_info.setStudies_name(TOAcademic_info.getStudies_name());
	
	academic_info.setThesis_director(TOAcademic_info.getThesis_director());
	academic_info.setThesis_codirector(TOAcademic_info.getThesis_codirector());
	
	academic_info.setTac0(TOAcademic_info.isTac0());
	

	/** 5. We set the code to the new instance * */
	try {
	    IdentifyManager_Plain im = IdentifyManager_Plain.singleton();

	    academic_info.setAcademic_infocode(im
		    .getId(TOAcademic_info));
	} catch (identifyException ie) {

	    log.error(
		    "Error en asignaciï¿½n de nuevo id en CreateAcademic_info",
		    ie);
	    throw new Error(ie.getMessage());
	}

	/** 6. We save the new instance to the DB* */
	HibernateUtil.getSession().save(academic_info);

	/**
	 * We associate the current object to the other objects (only in case
	 * that the associations where defined in the DTO) *
	 */

	if (_academic_info_personalIsDefined) {

	    if (TOAcademic_info.getAcademic_info_personal() != null) {

		TOAcademic_info.getAcademic_info_personal()
			.addIacademic_info_personal(academic_info);
	    }

	    academic_info.setAcademic_info_personal(TOAcademic_info
		    .getAcademic_info_personal());
	}

	if (_type_of_studyIsDefined) {

	    if (TOAcademic_info.getType_of_study() != null) {

		TOAcademic_info.getType_of_study().addIacademic_info(academic_info);
	    }

	    academic_info.setType_of_study(TOAcademic_info.getType_of_study());
	}
	if (_lab_rotation_labIsDefined) {
		
		if (TOAcademic_info.getLab_rotation_lab() != null) {
			
			TOAcademic_info.getLab_rotation_lab().addIacademic_info(academic_info);
		}
		
		academic_info.setLab_rotation_lab(TOAcademic_info.getLab_rotation_lab());
	}
	if (_lab_rotation_lab2IsDefined) {
		
		if (TOAcademic_info.getLab_rotation_lab2() != null) {
			
			TOAcademic_info.getLab_rotation_lab2().addIacademic_info_2(academic_info);
		}
		
		academic_info.setLab_rotation_lab2(TOAcademic_info.getLab_rotation_lab2());
	}

	/** 7. We create an Audit message * */
	CreateCreationAuditmessage(user, academic_info);

	/** 8. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return academic_info;
    }

    /**
     * This method modifies a academic_info.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOAcademic_info
     *            Academic_info data transfer object (DTO) with the values of
     *            the modified instance. The code of this attribute indicates
     *            which academic_info will be modified.
     * @return the modified academic_info
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Academic_info UpdateAcademic_info(Usuario user,
	    Academic_info TOAcademic_info) throws InternalException,
	    NoPermisosException {

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 3. We obtain form the DB the instance to modify * */
	Academic_info academic_info = getAcademic_info(TOAcademic_info
		.getAcademic_infocode());
	// testIsHHRROrItself(user,
	// academic_info.getAcademic_info_personal());

	/**
	 * 2. For each association from the TOAcademic_info that are filled
	 * in the DTO we put the real objects from the DB. *
	 */

	// we store if the academic_info_personal is defined for later use
	boolean _academic_info_personalIsDefined = false;

	if (TOAcademic_info.getAcademic_info_personal() != null
		&& TOAcademic_info.getAcademic_info_personal()
			.getPersonalcode() != null) {
	    // if academic_info_personal is defined we replace the
	    // academic_info_personal in the DTO with its current state in
	    // the DB.
	    _academic_info_personalIsDefined = true;

	    TOAcademic_info
		    .setAcademic_info_personal(getPersonal(TOAcademic_info
			    .getAcademic_info_personal().getPersonalcode()));
	}

	// we store if the grant is defined for later use
	boolean _type_of_studyIsDefined = false;

	if (TOAcademic_info.getType_of_study() != null
		&& TOAcademic_info.getType_of_study().getType_of_studycode() != null) {
	    // if grant is defined we replace the grant in the DTO with its
	    // current state in the DB.
	    _type_of_studyIsDefined = true;

	    TOAcademic_info.setType_of_study(getType_of_study(TOAcademic_info.getType_of_study()
		    .getType_of_studycode()));
	}
	
	boolean _lab_rotation_labIsDefined = false;

	if (TOAcademic_info.getLab_rotation_lab() != null
		&& TOAcademic_info.getLab_rotation_lab().getCode() != null) {
	    // if grant is defined we replace the grant in the DTO with its
	    // current state in the DB.
		_lab_rotation_labIsDefined = true;

	    TOAcademic_info.setLab_rotation_lab(getResearch_group(TOAcademic_info.getLab_rotation_lab()
		    .getCode()));
	}
	
	boolean _lab_rotation_lab2IsDefined = false;

	if (TOAcademic_info.getLab_rotation_lab2() != null
		&& TOAcademic_info.getLab_rotation_lab2().getCode() != null) {
	    // if grant is defined we replace the grant in the DTO with its
	    // current state in the DB.
		_lab_rotation_lab2IsDefined = true;

	    TOAcademic_info.setLab_rotation_lab2(getResearch_group(TOAcademic_info.getLab_rotation_lab2()
		    .getCode()));
	}
	
		/**
	 * 4. We set all the simple attributes (no associations) to the instance
	 * *
	 */

	academic_info.setStart_date(TOAcademic_info.getStart_date());

	academic_info.setEnd_date(TOAcademic_info.getEnd_date());
	
	academic_info.setTac0(TOAcademic_info.isTac0());
	academic_info.setUniversity_enrolled(TOAcademic_info
		.getUniversity_enrolled());
	
	academic_info.setLab_rotation_date(TOAcademic_info.getLab_rotation_date());
	
	academic_info.setThesis_defense_date(TOAcademic_info.getThesis_defense_date());
	academic_info.setStudies_name(TOAcademic_info.getStudies_name());
	
	academic_info.setThesis_director(TOAcademic_info.getThesis_director());
	academic_info.setThesis_codirector(TOAcademic_info.getThesis_codirector());
	
	academic_info.setTac0(TOAcademic_info.isTac0());

	/**
	 * 5. We set the DTO version to the modified object and we update it
	 * with the new values in the DB. We evict and update the instance to
	 * prevent concurrent modification *
	 */
	HibernateUtil.getSession().evict(academic_info);
	academic_info.setVersion(TOAcademic_info.getVersion());
	HibernateUtil.getSession().update(academic_info);

	/**
	 * We associate/disassociate the current object to the other objects
	 * (only in case that the associations where defined in the DTO) *
	 */

	if (_academic_info_personalIsDefined) {

	    if (academic_info.getAcademic_info_personal() != null) {

		academic_info.getAcademic_info_personal()
			.removeIacademic_info_personal(academic_info);
	    }

	    if (TOAcademic_info.getAcademic_info_personal() != null) {

		TOAcademic_info.getAcademic_info_personal()
			.addIacademic_info_personal(academic_info);
	    }

	    academic_info.setAcademic_info_personal(TOAcademic_info
		    .getAcademic_info_personal());
	}

	if (_type_of_studyIsDefined) {

	    if (academic_info.getType_of_study() != null) {

		academic_info.getType_of_study().removeIacademic_info(academic_info);
	    }

	    if (TOAcademic_info.getType_of_study() != null) {

		TOAcademic_info.getType_of_study().addIacademic_info(academic_info);
	    }

	    academic_info.setType_of_study(TOAcademic_info.getType_of_study());
	}

	if (_lab_rotation_labIsDefined) {
		
		if (TOAcademic_info.getLab_rotation_lab() != null) {
			
			TOAcademic_info.getLab_rotation_lab().addIacademic_info(academic_info);
		}
		
		academic_info.setLab_rotation_lab(TOAcademic_info.getLab_rotation_lab());
	}
	if (_lab_rotation_lab2IsDefined) {
		
		if (TOAcademic_info.getLab_rotation_lab2() != null) {
			
			TOAcademic_info.getLab_rotation_lab2().addIacademic_info_2(academic_info);
		}
		
		academic_info.setLab_rotation_lab2(TOAcademic_info.getLab_rotation_lab2());
	}
	
	/** 6. We create an Audit message * */
	CreateModificationAuditmessage(user, academic_info);

	/** 7. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return academic_info;
    }

    /**
     * This method removes a academic_info.
     * 
     * @param user
     *            The user who executes this use case
     * @param academic_infocode
     *            Code of the academic_info to be removed
     * @throws NoPermisosException
     */
    public static void RemoveAcademic_info(Usuario user,
	    String academic_infocode) throws NoPermisosException {

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain the object to delete form the DB. * */
	Academic_info academic_info = getAcademic_info(academic_infocode);
	// testIsHHRROrItself(user,
	// academic_info.getAcademic_info_personal());

	/** 3. We mark it as deleted. * */
	academic_info.setDeleted(Boolean.TRUE);

	/** 4. We create an Audit message * */
	CreateRemovealAuditmessage(user, academic_info);

	/** 5. We commit the DB transaction. * */
	HibernateUtil.commitTransaction();
    }

    /**
     * This method obtains one instance of academic_info given its code.
     * 
     * @param user
     *            The user who executes this use case
     * @param academic_infocode
     *            Code of the academic_info to be obtained
     * @return Academic_info with the given code.
     */
    public static Academic_info ObtainAcademic_info(Usuario user,
	    String academic_infocode) {

	/**
	 * 1. We obtain the object from the DB using the private getter and we
	 * return it. *
	 */

	Academic_info academic_info = getAcademic_info(academic_infocode);
	return academic_info;
    }

    /**
     * This method obtains all instances of Academic_info, given a
     * list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     */
    public static Pair<Integer, List<Academic_info>> ObtainAllAcademic_info(
	    Usuario user, ListConfigurator configurator) {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(
		Academic_info.class);

	// we only want to obtain the non deleted objects
	crit.add(Expression.eq("deleted", Boolean.FALSE));

	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	List<Academic_info> academic_infos = (List<Academic_info>) crit
		.list();

	Pair<Integer, List<Academic_info>> pair = new Pair<Integer, List<Academic_info>>(
		count, academic_infos);

	return pair;
    }
    
    
    /**
     * This method creates a Type_of_contract.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOType_of_contract
     *            Type_of_contract data transfer object (DTO) with the values of
     *            the new instance.
     * @return the new type_of_contract created with this Use Case
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Type_of_contract CreateType_of_contract(Usuario user,
	    Type_of_contract TOType_of_contract) throws InternalException,
	    NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We create the new instance * */
	Type_of_contract type_of_contract = new Type_of_contract();

	/**
	 * 3. We set all the simple attributes (no associations) to the new
	 * instance *
	 */

	type_of_contract.setDescription(TOType_of_contract.getDescription());
	type_of_contract.setIs_irbs(TOType_of_contract.isIs_irbs());

	/** 4. We set the code to the new instance * */
	try {
	    IdentifyManager_Plain im = IdentifyManager_Plain.singleton();

	    type_of_contract.setType_of_contractcode(im
		    .getId(TOType_of_contract));
	} catch (identifyException ie) {

	    log.error(
		    "Error en asignaciï¿½n de nuevo id en CreateType_of_contract",
		    ie);
	    throw new Error(ie.getMessage());
	}

	/** 5. We save the new instance to the DB* */
	HibernateUtil.getSession().save(type_of_contract);

	/** 6. We create an Audit message * */
	CreateCreationAuditmessage(user, type_of_contract);

	/** 7. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return type_of_contract;
    }

    /**
     * This method modifies a type_of_contract.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOType_of_contract
     *            Type_of_contract data transfer object (DTO) with the values of
     *            the modified instance. The code of this attribute indicates
     *            which type_of_contract will be modified.
     * @return the modified type_of_contract
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Type_of_contract UpdateType_of_contract(Usuario user,
	    Type_of_contract TOType_of_contract) throws InternalException,
	    NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain form the DB the instance to modify * */
	Type_of_contract type_of_contract = getType_of_contract(TOType_of_contract
		.getType_of_contractcode());
	;

	/**
	 * 3. We set all the simple attributes (no associations) to the instance
	 * *
	 */

	type_of_contract.setDescription(TOType_of_contract.getDescription());
	type_of_contract.setIs_irbs(TOType_of_contract.isIs_irbs());

	/**
	 * 4. We set the DTO version to the modified object and we update it
	 * with the new values in the DB. We evict and update the instance to
	 * prevent concurrent modification *
	 */
	HibernateUtil.getSession().evict(type_of_contract);
	type_of_contract.setVersion(TOType_of_contract.getVersion());
	HibernateUtil.getSession().update(type_of_contract);

	/** 5. We create an Audit message * */
	CreateModificationAuditmessage(user, type_of_contract);

	/** 6. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return type_of_contract;
    }

    /**
     * This method removes a type_of_contract.
     * 
     * @param user
     *            The user who executes this use case
     * @param type_of_contractcode
     *            Code of the type_of_contract to be removed
     * @throws NoPermisosException
     */
    public static void RemoveType_of_contract(Usuario user,
	    String type_of_contractcode) throws NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain the object to delete form the DB. * */
	Type_of_contract type_of_contract = getType_of_contract(type_of_contractcode);

	/** 3. We mark it as deleted. * */
	type_of_contract.setDeleted(Boolean.TRUE);

	/** 4. We create an Audit message * */
	CreateRemovealAuditmessage(user, type_of_contract);

	/** 5. We commit the DB transaction. * */
	HibernateUtil.commitTransaction();
    }

    /**
     * This method obtains one instance of type_of_contract given its code.
     * 
     * @param user
     *            The user who executes this use case
     * @param type_of_contractcode
     *            Code of the type_of_contract to be obtained
     * @return Type_of_contract with the given code.
     */
    public static Type_of_contract ObtainType_of_contract(Usuario user,
	    String type_of_contractcode) {

	/**
	 * 1. We obtain the object from the DB using the private getter and we
	 * return it. *
	 */

	Type_of_contract type_of_contract = getType_of_contract(type_of_contractcode);
	return type_of_contract;
    }

    /**
     * This method obtains all instances of Type_of_contract, given a
     * list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     */
    public static Pair<Integer, List<Type_of_contract>> ObtainAllType_of_contract(
	    Usuario user, ListConfigurator configurator) {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(
		Type_of_contract.class);

	// we only want to obtain the non deleted objects
	crit.add(Expression.eq("deleted", Boolean.FALSE));

	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	List<Type_of_contract> type_of_contracts = (List<Type_of_contract>) crit
		.list();

	Pair<Integer, List<Type_of_contract>> pair = new Pair<Integer, List<Type_of_contract>>(
		count, type_of_contracts);

	return pair;
    }

    /**
     * This method creates a Type_of_compensation.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOType_of_compensation
     *            Type_of_compensation data transfer object (DTO) with the
     *            values of the new instance.
     * @return the new type_of_compensation created with this Use Case
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Type_of_compensation CreateType_of_compensation(Usuario user,
	    Type_of_compensation TOType_of_compensation)
	    throws InternalException, NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We create the new instance * */
	Type_of_compensation type_of_compensation = new Type_of_compensation();

	/**
	 * 3. We set all the simple attributes (no associations) to the new
	 * instance *
	 */

	type_of_compensation.setDescription(TOType_of_compensation
		.getDescription());

	/** 4. We set the code to the new instance * */
	try {
	    IdentifyManager_Plain im = IdentifyManager_Plain.singleton();

	    type_of_compensation.setType_of_compensationcode(im
		    .getId(TOType_of_compensation));
	} catch (identifyException ie) {

	    log.error(
		    "Error en asignaciï¿½n de nuevo id en CreateType_of_compensation",
		    ie);
	    throw new Error(ie.getMessage());
	}

	/** 5. We save the new instance to the DB* */
	HibernateUtil.getSession().save(type_of_compensation);

	/** 6. We create an Audit message * */
	CreateCreationAuditmessage(user, type_of_compensation);

	/** 7. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return type_of_compensation;
    }

    /**
     * This method modifies a type_of_compensation.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOType_of_compensation
     *            Type_of_compensation data transfer object (DTO) with the
     *            values of the modified instance. The code of this attribute
     *            indicates which type_of_compensation will be modified.
     * @return the modified type_of_compensation
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Type_of_compensation UpdateType_of_compensation(Usuario user,
	    Type_of_compensation TOType_of_compensation)
	    throws InternalException, NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain form the DB the instance to modify * */
	Type_of_compensation type_of_compensation = getType_of_compensation(TOType_of_compensation
		.getType_of_compensationcode());
	;

	/**
	 * 3. We set all the simple attributes (no associations) to the instance
	 * *
	 */

	type_of_compensation.setDescription(TOType_of_compensation
		.getDescription());

	/**
	 * 4. We set the DTO version to the modified object and we update it
	 * with the new values in the DB. We evict and update the instance to
	 * prevent concurrent modification *
	 */
	HibernateUtil.getSession().evict(type_of_compensation);
	type_of_compensation.setVersion(TOType_of_compensation.getVersion());
	HibernateUtil.getSession().update(type_of_compensation);

	/** 5. We create an Audit message * */
	CreateModificationAuditmessage(user, type_of_compensation);

	/** 6. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return type_of_compensation;
    }

    /**
     * This method removes a type_of_compensation.
     * 
     * @param user
     *            The user who executes this use case
     * @param type_of_compensationcode
     *            Code of the type_of_compensation to be removed
     * @throws NoPermisosException
     */
    public static void RemoveType_of_compensation(Usuario user,
	    String type_of_compensationcode) throws NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain the object to delete form the DB. * */
	Type_of_compensation type_of_compensation = getType_of_compensation(type_of_compensationcode);

	/** 3. We mark it as deleted. * */
	type_of_compensation.setDeleted(Boolean.TRUE);

	/** 4. We create an Audit message * */
	CreateRemovealAuditmessage(user, type_of_compensation);

	/** 5. We commit the DB transaction. * */
	HibernateUtil.commitTransaction();
    }

    /**
     * This method obtains one instance of type_of_compensation given its code.
     * 
     * @param user
     *            The user who executes this use case
     * @param type_of_compensationcode
     *            Code of the type_of_compensation to be obtained
     * @return Type_of_compensation with the given code.
     */
    public static Type_of_compensation ObtainType_of_compensation(Usuario user,
	    String type_of_compensationcode) {

	/**
	 * 1. We obtain the object from the DB using the private getter and we
	 * return it. *
	 */

	Type_of_compensation type_of_compensation = getType_of_compensation(type_of_compensationcode);
	return type_of_compensation;
    }

    /**
     * This method obtains all instances of Type_of_compensation, given a
     * list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     */
    public static Pair<Integer, List<Type_of_compensation>> ObtainAllType_of_compensation(
	    Usuario user, ListConfigurator configurator) {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(
		Type_of_compensation.class);

	// we only want to obtain the non deleted objects
	crit.add(Expression.eq("deleted", Boolean.FALSE));

	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	List<Type_of_compensation> type_of_compensations = (List<Type_of_compensation>) crit
		.list();

	Pair<Integer, List<Type_of_compensation>> pair = new Pair<Integer, List<Type_of_compensation>>(
		count, type_of_compensations);

	return pair;
    }

    /**
     * This method creates a Unit.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOUnit
     *            Unit data transfer object (DTO) with the values of the new
     *            instance.
     * @return the new unit created with this Use Case
     * @throws InternalException
     * @throws NoPermisosException
     * @throws ValidationFailedException
     */
    public static Unit CreateUnit(Usuario user, Unit TOUnit)
	    throws InternalException, NoPermisosException,
	    ValidationFailedException {
	testIsHHRR(user);

	try {
	    /** 1. We begin the DB transaction. * */
	    HibernateUtil.beginTransaction();

	    /**
	     * 2. For each association from the TOUnit that are filled in the
	     * DTO we put the real objects from the DB. *
	     */

	    // we store if the organization_unit is defined for later use
	    boolean _organization_unitIsDefined = false;

	    if (TOUnit.getOrganization_unit() != null
		    && TOUnit.getOrganization_unit().getOrganization_unitcode() != null) {
		// if organization_unit is defined we replace the
		// organization_unit
		// in the DTO with its current state in the DB.
		_organization_unitIsDefined = true;

		TOUnit.setOrganization_unit(getOrganization_unit(TOUnit
			.getOrganization_unit().getOrganization_unitcode()));
	    }

	    // we store if the organization_unit is defined for later use
	    boolean _supervisorIsDefined = false;

	    if (TOUnit.getSupervisor() != null
		    && TOUnit.getSupervisor().getPersonalcode() != null) {
		// if organization_unit is defined we replace the
		// organization_unit
		// in the DTO with its current state in the DB.
		_supervisorIsDefined = true;

		TOUnit.setSupervisor(getPersonal(TOUnit.getSupervisor()
			.getPersonalcode()));
	    }

	    /** 3. We create the new instance * */
	    Unit unit = new Unit();

	    /**
	     * 4. We set all the simple attributes (no associations) to the new
	     * instance *
	     */

	    unit.setDescription(TOUnit.getDescription());
	    unit.setGroup(TOUnit.getGroup());
	    if (unit.getGroup() != null && unit.getGroup().trim().equals(""))
		unit.setGroup(null);

	    /** 5. We set the code to the new instance * */
	    try {
		IdentifyManager_Plain im = IdentifyManager_Plain.singleton();

		unit.setUnitcode(im.getId(TOUnit));
	    } catch (identifyException ie) {

		log.error("Error en asignaciï¿½n de nuevo id en CreateUnit", ie);
		throw new Error(ie.getMessage());
	    }

	    /** 6. We save the new instance to the DB* */
	    HibernateUtil.getSession().save(unit);

	    /**
	     * We associate the current object to the other objects (only in
	     * case that the associations where defined in the DTO) *
	     */

	    if (_organization_unitIsDefined) {

		if (TOUnit.getOrganization_unit() != null) {

		    TOUnit.getOrganization_unit().addIorganization_unit(unit);
		}

		unit.setOrganization_unit(TOUnit.getOrganization_unit());
	    }

	    if (_supervisorIsDefined) {

		if (TOUnit.getSupervisor() != null) {

		    TOUnit.getSupervisor().addIsupervisor_unit(unit);
		}

		unit.setSupervisor(TOUnit.getSupervisor());
	    }

	    /** 7. We create an Audit message * */
	    CreateCreationAuditmessage(user, unit);

	    /** 8. We commit the DB transaction and return the new instance * */
	    HibernateUtil.commitTransaction();

	    return unit;
	} catch (Exception cve) {
	    Map<String, List<String>> parameters = new Hashtable<String, List<String>>();
	    parameters.put("propertyError.existingValue",
		    Arrays.asList(new String[] { "group" }));
	    throw new ValidationFailedException(parameters);
	}
    }

    /**
     * This method modifies a unit.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOUnit
     *            Unit data transfer object (DTO) with the values of the
     *            modified instance. The code of this attribute indicates which
     *            unit will be modified.
     * @return the modified unit
     * @throws InternalException
     * @throws NoPermisosException
     * @throws ValidationFailedException
     */
    public static Unit UpdateUnit(Usuario user, Unit TOUnit)
	    throws InternalException, NoPermisosException,
	    ValidationFailedException {
	testIsHHRR(user);

	try {
	    /** 1. We begin the DB transaction. * */
	    HibernateUtil.beginTransaction();

	    /**
	     * 2. For each association from the TOUnit that are filled in the
	     * DTO we put the real objects from the DB. *
	     */

	    // we store if the organization_unit is defined for later use
	    boolean _organization_unitIsDefined = false;

	    if (TOUnit.getOrganization_unit() != null
		    && TOUnit.getOrganization_unit().getOrganization_unitcode() != null) {
		// if organization_unit is defined we replace the
		// organization_unit
		// in the DTO with its current state in the DB.
		_organization_unitIsDefined = true;

		TOUnit.setOrganization_unit(getOrganization_unit(TOUnit
			.getOrganization_unit().getOrganization_unitcode()));
	    }

	    // we store if the organization_unit is defined for later use
	    boolean _supervisorIsDefined = false;

	    if (TOUnit.getSupervisor() != null
		    && TOUnit.getSupervisor().getPersonalcode() != null) {
		// if organization_unit is defined we replace the
		// organization_unit
		// in the DTO with its current state in the DB.
		_supervisorIsDefined = true;

		TOUnit.setSupervisor(getPersonal(TOUnit.getSupervisor()
			.getPersonalcode()));
	    }

	    /** 3. We obtain form the DB the instance to modify * */
	    Unit unit = getUnit(TOUnit.getUnitcode());
	    ;

	    /**
	     * 4. We set all the simple attributes (no associations) to the
	     * instance *
	     */

	    unit.setDescription(TOUnit.getDescription());
	    unit.setGroup(TOUnit.getGroup());
	    if (unit.getGroup() != null && unit.getGroup().trim().equals(""))
		unit.setGroup(null);

	    /**
	     * 5. We set the DTO version to the modified object and we update it
	     * with the new values in the DB. We evict and update the instance
	     * to prevent concurrent modification *
	     */
	    HibernateUtil.getSession().evict(unit);
	    unit.setVersion(TOUnit.getVersion());
	    HibernateUtil.getSession().update(unit);

	    /**
	     * We associate/disassociate the current object to the other objects
	     * (only in case that the associations where defined in the DTO) *
	     */

	    if (_organization_unitIsDefined) {

		if (unit.getOrganization_unit() != null) {

		    unit.getOrganization_unit().removeIorganization_unit(unit);
		}

		if (TOUnit.getOrganization_unit() != null) {

		    TOUnit.getOrganization_unit().addIorganization_unit(unit);
		}

		unit.setOrganization_unit(TOUnit.getOrganization_unit());
	    }

	    if (_supervisorIsDefined) {

		if (unit.getSupervisor() != null) {

		    unit.getSupervisor().removeIsupervisor_unit(unit);
		}

		if (TOUnit.getSupervisor() != null) {

		    TOUnit.getSupervisor().addIsupervisor_unit(unit);
		}

		unit.setSupervisor(TOUnit.getSupervisor());
	    }

	    /** 6. We create an Audit message * */
	    CreateModificationAuditmessage(user, unit);

	    /** 7. We commit the DB transaction and return the new instance * */
	    HibernateUtil.commitTransaction();

	    return unit;
	} catch (Exception cve) {
	    Map<String, List<String>> parameters = new Hashtable<String, List<String>>();
	    parameters.put("propertyError.existingValue",
		    Arrays.asList(new String[] { "group" }));
	    throw new ValidationFailedException(parameters);
	}
    }

    /**
     * This method removes a unit.
     * 
     * @param user
     *            The user who executes this use case
     * @param unitcode
     *            Code of the unit to be removed
     * @throws NoPermisosException
     */
    public static void RemoveUnit(Usuario user, String unitcode)
	    throws NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain the object to delete form the DB. * */
	Unit unit = getUnit(unitcode);

	/** 3. We mark it as deleted. * */
	unit.setDeleted(Boolean.TRUE);

	/** 4. We create an Audit message * */
	CreateRemovealAuditmessage(user, unit);

	/** 5. We commit the DB transaction. * */
	HibernateUtil.commitTransaction();
    }

    /**
     * This method obtains one instance of unit given its code.
     * 
     * @param user
     *            The user who executes this use case
     * @param unitcode
     *            Code of the unit to be obtained
     * @return Unit with the given code.
     */
    public static Unit ObtainUnit(Usuario user, String unitcode) {

	/**
	 * 1. We obtain the object from the DB using the private getter and we
	 * return it. *
	 */

	Unit unit = getUnit(unitcode);
	return unit;
    }

    /**
     * This method obtains all instances of Unit, given a list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     */
    public static Pair<Integer, List<Unit>> ObtainAllUnit(Usuario user,
	    ListConfigurator configurator) {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(Unit.class);

	// we only want to obtain the non deleted objects
	crit.add(Expression.eq("deleted", Boolean.FALSE));

	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	List<Unit> units = (List<Unit>) crit.list();

	Pair<Integer, List<Unit>> pair = new Pair<Integer, List<Unit>>(count,
		units);

	return pair;
    }

    /**
     * This method creates a Gender.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOGender
     *            Gender data transfer object (DTO) with the values of the new
     *            instance.
     * @return the new gender created with this Use Case
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Gender CreateGender(Usuario user, Gender TOGender)
	    throws InternalException, NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We create the new instance * */
	Gender gender = new Gender();

	/**
	 * 3. We set all the simple attributes (no associations) to the new
	 * instance *
	 */

	gender.setDescription(TOGender.getDescription());

	/** 4. We set the code to the new instance * */
	gender.setCode(TOGender.getCode());

	/** 5. We save the new instance to the DB* */
	HibernateUtil.getSession().save(gender);

	/** 6. We create an Audit message * */
	CreateCreationAuditmessage(user, gender);

	/** 7. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return gender;
    }

    /**
     * This method modifies a gender.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOGender
     *            Gender data transfer object (DTO) with the values of the
     *            modified instance. The code of this attribute indicates which
     *            gender will be modified.
     * @return the modified gender
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Gender UpdateGender(Usuario user, Gender TOGender)
	    throws InternalException, NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain form the DB the instance to modify * */
	Gender gender = getGender(TOGender.getGendercode());
	;

	/**
	 * 3. We set all the simple attributes (no associations) to the instance
	 * *
	 */

	gender.setDescription(TOGender.getDescription());

	/**
	 * 4. We set the DTO version to the modified object and we update it
	 * with the new values in the DB. We evict and update the instance to
	 * prevent concurrent modification *
	 */
	HibernateUtil.getSession().evict(gender);
	gender.setVersion(TOGender.getVersion());
	HibernateUtil.getSession().update(gender);

	/** 5. We create an Audit message * */
	CreateModificationAuditmessage(user, gender);

	/** 6. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return gender;
    }

    /**
     * This method removes a gender.
     * 
     * @param user
     *            The user who executes this use case
     * @param gendercode
     *            Code of the gender to be removed
     * @throws NoPermisosException
     */
    public static void RemoveGender(Usuario user, String gendercode)
	    throws NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain the object to delete form the DB. * */
	Gender gender = getGender(gendercode);

	/** 3. We mark it as deleted. * */
	gender.setDeleted(Boolean.TRUE);

	/** 4. We create an Audit message * */
	CreateRemovealAuditmessage(user, gender);

	/** 5. We commit the DB transaction. * */
	HibernateUtil.commitTransaction();
    }

    /**
     * This method obtains one instance of gender given its code.
     * 
     * @param user
     *            The user who executes this use case
     * @param gendercode
     *            Code of the gender to be obtained
     * @return Gender with the given code.
     */
    public static Gender ObtainGender(Usuario user, String gendercode) {

	/**
	 * 1. We obtain the object from the DB using the private getter and we
	 * return it. *
	 */

	Gender gender = getGender(gendercode);
	return gender;
    }

    /**
     * This method obtains all instances of Gender, given a list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     */
    public static Pair<Integer, List<Gender>> ObtainAllGender(Usuario user,
	    ListConfigurator configurator) {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(Gender.class);

	// we only want to obtain the non deleted objects
	crit.add(Expression.eq("deleted", Boolean.FALSE));
	crit.setCacheable(true);

	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	List<Gender> genders = (List<Gender>) crit.list();

	Pair<Integer, List<Gender>> pair = new Pair<Integer, List<Gender>>(
		count, genders);

	return pair;
    }

    /**
     * This method creates a Country.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOCountry
     *            Country data transfer object (DTO) with the values of the new
     *            instance.
     * @return the new country created with this Use Case
     * @throws InternalException
     * @throws ValidationFailedException
     * @throws NoPermisosException
     */
    public static Country CreateCountry(Usuario user, Country TOCountry)
	    throws InternalException, ValidationFailedException,
	    NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	if (getCountry(TOCountry.getCountrycode()) != null) {
	    Map<String, List<String>> parameters = new Hashtable<String, List<String>>();
	    parameters.put("propertyError.existingValue",
		    Arrays.asList(new String[] { "countrycode" }));
	    throw new ValidationFailedException(parameters);
	}

	/** 2. We create the new instance * */
	Country country = new Country();

	/**
	 * 3. We set all the simple attributes (no associations) to the new
	 * instance *
	 */

	country.setDescription(TOCountry.getDescription());

	/** 4. We set the code to the new instance * */
	country.setCountrycode(TOCountry.getCountrycode());

	/** 5. We save the new instance to the DB* */
	HibernateUtil.getSession().save(country);

	/** 6. We create an Audit message * */
	CreateCreationAuditmessage(user, country);

	/** 7. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return country;
    }

    /**
     * This method modifies a country.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOCountry
     *            Country data transfer object (DTO) with the values of the
     *            modified instance. The code of this attribute indicates which
     *            country will be modified.
     * @return the modified country
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Country UpdateCountry(Usuario user, Country TOCountry)
	    throws InternalException, NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain form the DB the instance to modify * */
	Country country = getCountry(TOCountry.getCountrycode());
	;

	/**
	 * 3. We set all the simple attributes (no associations) to the instance
	 * *
	 */

	country.setDescription(TOCountry.getDescription());

	/**
	 * 4. We set the DTO version to the modified object and we update it
	 * with the new values in the DB. We evict and update the instance to
	 * prevent concurrent modification *
	 */
	HibernateUtil.getSession().evict(country);
	country.setVersion(TOCountry.getVersion());
	HibernateUtil.getSession().update(country);

	/** 5. We create an Audit message * */
	CreateModificationAuditmessage(user, country);

	/** 6. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return country;
    }

    /**
     * This method removes a country.
     * 
     * @param user
     *            The user who executes this use case
     * @param countrycode
     *            Code of the country to be removed
     * @throws NoPermisosException
     */
    public static void RemoveCountry(Usuario user, String countrycode)
	    throws NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain the object to delete form the DB. * */
	Country country = getCountry(countrycode);

	/** 3. We mark it as deleted. * */
	country.setDeleted(Boolean.TRUE);

	/** 4. We create an Audit message * */
	CreateRemovealAuditmessage(user, country);

	/** 5. We commit the DB transaction. * */
	HibernateUtil.commitTransaction();
    }

    /**
     * This method obtains one instance of country given its code.
     * 
     * @param user
     *            The user who executes this use case
     * @param countrycode
     *            Code of the country to be obtained
     * @return Country with the given code.
     */
    public static Country ObtainCountry(Usuario user, String countrycode) {

	/**
	 * 1. We obtain the object from the DB using the private getter and we
	 * return it. *
	 */

	Country country = getCountry(countrycode);
	return country;
    }

    /**
     * This method obtains all instances of Country, given a list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     */
    public static Pair<Integer, List<Country>> ObtainAllCountry(Usuario user,
	    ListConfigurator configurator) {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession()
		.createCriteria(Country.class);

	// we only want to obtain the non deleted objects
	crit.add(Expression.eq("deleted", Boolean.FALSE));
	crit.setCacheable(true);

	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	List<Country> countrys = (List<Country>) crit.list();

	Pair<Integer, List<Country>> pair = new Pair<Integer, List<Country>>(
		count, countrys);

	return pair;
    }

    /**
     * This method creates a Category.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOCategory
     *            Category data transfer object (DTO) with the values of the new
     *            instance.
     * @return the new category created with this Use Case
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Category CreateCategory(Usuario user, Category TOCategory)
	    throws InternalException, NoPermisosException {
	testIsHHRR(user);
	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We create the new instance * */
	Category category = new Category();

	/**
	 * 3. We set all the simple attributes (no associations) to the new
	 * instance *
	 */

	category.setDescription(TOCategory.getDescription());

	/** 4. We set the code to the new instance * */
	try {
	    IdentifyManager_Plain im = IdentifyManager_Plain.singleton();

	    category.setCategorycode(im.getId(TOCategory));
	} catch (identifyException ie) {

	    log.error("Error en asignaciï¿½n de nuevo id en CreateCategory", ie);
	    throw new Error(ie.getMessage());
	}

	/** 5. We save the new instance to the DB* */
	HibernateUtil.getSession().save(category);

	/** 6. We create an Audit message * */
	CreateCreationAuditmessage(user, category);

	/** 7. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return category;
    }

    /**
     * This method modifies a category.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOCategory
     *            Category data transfer object (DTO) with the values of the
     *            modified instance. The code of this attribute indicates which
     *            category will be modified.
     * @return the modified category
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Category UpdateCategory(Usuario user, Category TOCategory)
	    throws InternalException, NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain form the DB the instance to modify * */
	Category category = getCategory(TOCategory.getCategorycode());
	;

	/**
	 * 3. We set all the simple attributes (no associations) to the instance
	 * *
	 */

	category.setDescription(TOCategory.getDescription());

	/**
	 * 4. We set the DTO version to the modified object and we update it
	 * with the new values in the DB. We evict and update the instance to
	 * prevent concurrent modification *
	 */
	HibernateUtil.getSession().evict(category);
	category.setVersion(TOCategory.getVersion());
	HibernateUtil.getSession().update(category);

	/** 5. We create an Audit message * */
	CreateModificationAuditmessage(user, category);

	/** 6. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return category;
    }

    /**
     * This method removes a category.
     * 
     * @param user
     *            The user who executes this use case
     * @param categorycode
     *            Code of the category to be removed
     * @throws NoPermisosException
     */
    public static void RemoveCategory(Usuario user, String categorycode)
	    throws NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain the object to delete form the DB. * */
	Category category = getCategory(categorycode);

	/** 3. We mark it as deleted. * */
	category.setDeleted(Boolean.TRUE);

	/** 4. We create an Audit message * */
	CreateRemovealAuditmessage(user, category);

	/** 5. We commit the DB transaction. * */
	HibernateUtil.commitTransaction();
    }

    /**
     * This method obtains one instance of category given its code.
     * 
     * @param user
     *            The user who executes this use case
     * @param categorycode
     *            Code of the category to be obtained
     * @return Category with the given code.
     */
    public static Category ObtainCategory(Usuario user, String categorycode) {

	/**
	 * 1. We obtain the object from the DB using the private getter and we
	 * return it. *
	 */

	Category category = getCategory(categorycode);
	return category;
    }

    /**
     * This method obtains all instances of Category, given a list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     */
    public static Pair<Integer, List<Category>> ObtainAllCategory(Usuario user,
	    ListConfigurator configurator) {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(
		Category.class);

	// we only want to obtain the non deleted objects
	crit.add(Expression.eq("deleted", Boolean.FALSE));
	crit.setCacheable(true);

	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	List<Category> categorys = (List<Category>) crit.list();

	Pair<Integer, List<Category>> pair = new Pair<Integer, List<Category>>(
		count, categorys);

	return pair;
    }

    /**
     * This method creates a Funding_detail.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOFunding_detail
     *            Funding_detail data transfer object (DTO) with the values of
     *            the new instance.
     * @return the new funding_detail created with this Use Case
     * @throws InternalException
     */
    public static Funding_detail CreateFunding_detail(Usuario user,
	    Funding_detail TOFunding_detail) throws InternalException {

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/**
	 * 2. For each association from the TOFunding_detail that are filled in
	 * the DTO we put the real objects from the DB. *
	 */

	// we store if the funding_detail_personal is defined for later use
	boolean _funding_detail_personalIsDefined = false;

	if (TOFunding_detail.getFunding_detail_personal() != null
		&& TOFunding_detail.getFunding_detail_personal()
			.getPersonalcode() != null) {
	    // if funding_detail_personal is defined we replace the
	    // funding_detail_personal in the DTO with its current state in the
	    // DB.
	    _funding_detail_personalIsDefined = true;

	    TOFunding_detail
		    .setFunding_detail_personal(getPersonal(TOFunding_detail
			    .getFunding_detail_personal().getPersonalcode()));
	}

	// we store if the funding_detail_personal is defined for later use
	boolean _irb_budget_codeIsDefined = false;

	if (TOFunding_detail.getIrb_budget_code() != null
		&& TOFunding_detail.getIrb_budget_code().getCode() != null) {
	    // if funding_detail_personal is defined we replace the
	    // funding_detail_personal in the DTO with its current state in the
	    // DB.
	    _irb_budget_codeIsDefined = true;

	    TOFunding_detail.setIrb_budget_code(getIrb_budget(TOFunding_detail
		    .getIrb_budget_code().getCode()));
	}

	/** 3. We create the new instance * */
	Funding_detail funding_detail = new Funding_detail();

	/**
	 * 4. We set all the simple attributes (no associations) to the new
	 * instance *
	 */

	funding_detail.setInstitution(TOFunding_detail.getInstitution());

	funding_detail.setPercent(TOFunding_detail.getPercent());

	/** 5. We set the code to the new instance * */
	try {
	    IdentifyManager_Plain im = IdentifyManager_Plain.singleton();

	    funding_detail.setFunding_detailcode(im.getId(TOFunding_detail));
	} catch (identifyException ie) {

	    log.error(
		    "Error en asignaciï¿½n de nuevo id en CreateFunding_detail",
		    ie);
	    throw new Error(ie.getMessage());
	}

	/** 6. We save the new instance to the DB* */
	HibernateUtil.getSession().save(funding_detail);

	/**
	 * We associate the current object to the other objects (only in case
	 * that the associations where defined in the DTO) *
	 */

	if (_funding_detail_personalIsDefined) {

	    if (TOFunding_detail.getFunding_detail_personal() != null) {

		TOFunding_detail.getFunding_detail_personal()
			.addIfunding_detail_personal(funding_detail);
	    }

	    funding_detail.setFunding_detail_personal(TOFunding_detail
		    .getFunding_detail_personal());
	}

	if (_irb_budget_codeIsDefined) {
	    if (TOFunding_detail.getIrb_budget_code() != null) {

		TOFunding_detail.getIrb_budget_code().addIfunding_detail(
			funding_detail);
	    }

	    funding_detail.setIrb_budget_code(TOFunding_detail
		    .getIrb_budget_code());
	}

	/** 7. We create an Audit message * */
	CreateCreationAuditmessage(user, funding_detail);

	/** 8. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return funding_detail;
    }

    /**
     * This method modifies a funding_detail.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOFunding_detail
     *            Funding_detail data transfer object (DTO) with the values of
     *            the modified instance. The code of this attribute indicates
     *            which funding_detail will be modified.
     * @return the modified funding_detail
     * @throws InternalException
     */
    public static Funding_detail UpdateFunding_detail(Usuario user,
	    Funding_detail TOFunding_detail) throws InternalException {

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/**
	 * 2. For each association from the TOFunding_detail that are filled in
	 * the DTO we put the real objects from the DB. *
	 */

	// we store if the funding_detail_personal is defined for later use
	boolean _funding_detail_personalIsDefined = false;

	if (TOFunding_detail.getFunding_detail_personal() != null
		&& TOFunding_detail.getFunding_detail_personal()
			.getPersonalcode() != null) {
	    // if funding_detail_personal is defined we replace the
	    // funding_detail_personal in the DTO with its current state in the
	    // DB.
	    _funding_detail_personalIsDefined = true;

	    TOFunding_detail
		    .setFunding_detail_personal(getPersonal(TOFunding_detail
			    .getFunding_detail_personal().getPersonalcode()));
	}

	boolean _irb_budget_codeIsDefined = false;

	if (TOFunding_detail.getIrb_budget_code() != null
		&& TOFunding_detail.getIrb_budget_code().getCode() != null) {
	    // if funding_detail_personal is defined we replace the
	    // funding_detail_personal in the DTO with its current state in the
	    // DB.
	    _irb_budget_codeIsDefined = true;

	    TOFunding_detail.setIrb_budget_code(getIrb_budget(TOFunding_detail
		    .getIrb_budget_code().getCode()));
	}

	/** 3. We obtain form the DB the instance to modify * */
	Funding_detail funding_detail = getFunding_detail(TOFunding_detail
		.getFunding_detailcode());
	;

	/**
	 * 4. We set all the simple attributes (no associations) to the instance
	 * *
	 */

	funding_detail.setInstitution(TOFunding_detail.getInstitution());

	funding_detail
		.setIrb_budget_code(TOFunding_detail.getIrb_budget_code());

	funding_detail.setPercent(TOFunding_detail.getPercent());

	/**
	 * 5. We set the DTO version to the modified object and we update it
	 * with the new values in the DB. We evict and update the instance to
	 * prevent concurrent modification *
	 */
	HibernateUtil.getSession().evict(funding_detail);
	funding_detail.setVersion(TOFunding_detail.getVersion());
	HibernateUtil.getSession().update(funding_detail);

	/**
	 * We associate/disassociate the current object to the other objects
	 * (only in case that the associations where defined in the DTO) *
	 */

	if (_funding_detail_personalIsDefined) {

	    if (funding_detail.getFunding_detail_personal() != null) {

		funding_detail.getFunding_detail_personal()
			.removeIfunding_detail_personal(funding_detail);
	    }

	    if (TOFunding_detail.getFunding_detail_personal() != null) {

		TOFunding_detail.getFunding_detail_personal()
			.addIfunding_detail_personal(funding_detail);
	    }

	    funding_detail.setFunding_detail_personal(TOFunding_detail
		    .getFunding_detail_personal());
	}

	if (_irb_budget_codeIsDefined) {
	    if (funding_detail.getIrb_budget_code() != null) {

		funding_detail.getIrb_budget_code().removeIfunding_detail(
			funding_detail);
	    }

	    if (TOFunding_detail.getIrb_budget_code() != null) {

		TOFunding_detail.getIrb_budget_code().addIfunding_detail(
			funding_detail);
	    }

	    funding_detail.setIrb_budget_code(TOFunding_detail
		    .getIrb_budget_code());
	}

	/** 6. We create an Audit message * */
	CreateModificationAuditmessage(user, funding_detail);

	/** 7. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return funding_detail;
    }

    /**
     * This method removes a funding_detail.
     * 
     * @param user
     *            The user who executes this use case
     * @param funding_detailcode
     *            Code of the funding_detail to be removed
     */
    public static void RemoveFunding_detail(Usuario user,
	    String funding_detailcode) {

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain the object to delete form the DB. * */
	Funding_detail funding_detail = getFunding_detail(funding_detailcode);

	/** 3. We mark it as deleted. * */
	funding_detail.setDeleted(Boolean.TRUE);

	/** 4. We create an Audit message * */
	CreateRemovealAuditmessage(user, funding_detail);

	/** 5. We commit the DB transaction. * */
	HibernateUtil.commitTransaction();
    }

    /**
     * This method obtains one instance of funding_detail given its code.
     * 
     * @param user
     *            The user who executes this use case
     * @param funding_detailcode
     *            Code of the funding_detail to be obtained
     * @return Funding_detail with the given code.
     */
    public static Funding_detail ObtainFunding_detail(Usuario user,
	    String funding_detailcode) {

	/**
	 * 1. We obtain the object from the DB using the private getter and we
	 * return it. *
	 */

	Funding_detail funding_detail = getFunding_detail(funding_detailcode);
	return funding_detail;
    }

    /**
     * This method obtains all instances of Funding_detail, given a
     * list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     */
    public static Pair<Integer, List<Funding_detail>> ObtainAllFunding_detail(
	    Usuario user, ListConfigurator configurator) {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(
		Funding_detail.class);

	// we only want to obtain the non deleted objects
	crit.add(Expression.eq("deleted", Boolean.FALSE));

	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	List<Funding_detail> funding_details = (List<Funding_detail>) crit
		.list();

	Pair<Integer, List<Funding_detail>> pair = new Pair<Integer, List<Funding_detail>>(
		count, funding_details);

	return pair;
    }

    /**
     * This method creates a Area.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOArea
     *            Area data transfer object (DTO) with the values of the new
     *            instance.
     * @return the new area created with this Use Case
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Area CreateArea(Usuario user, Area TOArea)
	    throws InternalException, NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We create the new instance * */
	Area area = new Area();

	/**
	 * 3. We set all the simple attributes (no associations) to the new
	 * instance *
	 */

	area.setDescription(TOArea.getDescription());

	/** 4. We set the code to the new instance * */
	try {
	    IdentifyManager_Plain im = IdentifyManager_Plain.singleton();

	    area.setAreacode(im.getId(TOArea));
	} catch (identifyException ie) {

	    log.error("Error en asignaciï¿½n de nuevo id en CreateArea", ie);
	    throw new Error(ie.getMessage());
	}

	/** 5. We save the new instance to the DB* */
	HibernateUtil.getSession().save(area);

	/** 6. We create an Audit message * */
	CreateCreationAuditmessage(user, area);

	/** 7. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return area;
    }

    /**
     * This method modifies a area.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOArea
     *            Area data transfer object (DTO) with the values of the
     *            modified instance. The code of this attribute indicates which
     *            area will be modified.
     * @return the modified area
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Area UpdateArea(Usuario user, Area TOArea)
	    throws InternalException, NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain form the DB the instance to modify * */
	Area area = getArea(TOArea.getAreacode());
	;

	/**
	 * 3. We set all the simple attributes (no associations) to the instance
	 * *
	 */

	area.setDescription(TOArea.getDescription());

	/**
	 * 4. We set the DTO version to the modified object and we update it
	 * with the new values in the DB. We evict and update the instance to
	 * prevent concurrent modification *
	 */
	HibernateUtil.getSession().evict(area);
	area.setVersion(TOArea.getVersion());
	HibernateUtil.getSession().update(area);

	/** 5. We create an Audit message * */
	CreateModificationAuditmessage(user, area);

	/** 6. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return area;
    }

    /**
     * This method removes a area.
     * 
     * @param user
     *            The user who executes this use case
     * @param areacode
     *            Code of the area to be removed
     * @throws NoPermisosException
     */
    public static void RemoveArea(Usuario user, String areacode)
	    throws NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain the object to delete form the DB. * */
	Area area = getArea(areacode);

	/** 3. We mark it as deleted. * */
	area.setDeleted(Boolean.TRUE);

	/** 4. We create an Audit message * */
	CreateRemovealAuditmessage(user, area);

	/** 5. We commit the DB transaction. * */
	HibernateUtil.commitTransaction();
    }

    /**
     * This method obtains one instance of area given its code.
     * 
     * @param user
     *            The user who executes this use case
     * @param areacode
     *            Code of the area to be obtained
     * @return Area with the given code.
     */
    public static Area ObtainArea(Usuario user, String areacode) {

	/**
	 * 1. We obtain the object from the DB using the private getter and we
	 * return it. *
	 */

	Area area = getArea(areacode);
	return area;
    }

    /**
     * This method obtains all instances of Area, given a list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     */
    public static Pair<Integer, List<Area>> ObtainAllArea(Usuario user,
	    ListConfigurator configurator) {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(Area.class);

	// we only want to obtain the non deleted objects
	crit.add(Expression.eq("deleted", Boolean.FALSE));

	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	List<Area> areas = (List<Area>) crit.list();

	Pair<Integer, List<Area>> pair = new Pair<Integer, List<Area>>(count,
		areas);

	return pair;
    }

    /**
     * This method creates a Type_of_holidays.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOType_of_holidays
     *            Type_of_holidays data transfer object (DTO) with the values of
     *            the new instance.
     * @return the new type_of_holidays created with this Use Case
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Type_of_holidays CreateType_of_holidays(Usuario user,
	    Type_of_holidays TOType_of_holidays) throws InternalException,
	    NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We create the new instance * */
	Type_of_holidays type_of_holidays = new Type_of_holidays();

	/**
	 * 3. We set all the simple attributes (no associations) to the new
	 * instance *
	 */

	type_of_holidays.setDescription(TOType_of_holidays.getDescription());

	/** 4. We set the code to the new instance * */
	try {
	    IdentifyManager_Plain im = IdentifyManager_Plain.singleton();

	    type_of_holidays.setType_of_holidayscode(im
		    .getId(TOType_of_holidays));
	} catch (identifyException ie) {

	    log.error(
		    "Error en asignaciï¿½n de nuevo id en CreateType_of_holidays",
		    ie);
	    throw new Error(ie.getMessage());
	}

	/** 5. We save the new instance to the DB* */
	HibernateUtil.getSession().save(type_of_holidays);

	/** 6. We create an Audit message * */
	CreateCreationAuditmessage(user, type_of_holidays);

	/** 7. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return type_of_holidays;
    }

    /**
     * This method modifies a type_of_holidays.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOType_of_holidays
     *            Type_of_holidays data transfer object (DTO) with the values of
     *            the modified instance. The code of this attribute indicates
     *            which type_of_holidays will be modified.
     * @return the modified type_of_holidays
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Type_of_holidays UpdateType_of_holidays(Usuario user,
	    Type_of_holidays TOType_of_holidays) throws InternalException,
	    NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain form the DB the instance to modify * */
	Type_of_holidays type_of_holidays = getType_of_holidays(TOType_of_holidays
		.getType_of_holidayscode());
	;

	/**
	 * 3. We set all the simple attributes (no associations) to the instance
	 * *
	 */

	type_of_holidays.setDescription(TOType_of_holidays.getDescription());

	/**
	 * 4. We set the DTO version to the modified object and we update it
	 * with the new values in the DB. We evict and update the instance to
	 * prevent concurrent modification *
	 */
	HibernateUtil.getSession().evict(type_of_holidays);
	type_of_holidays.setVersion(TOType_of_holidays.getVersion());
	HibernateUtil.getSession().update(type_of_holidays);

	/** 5. We create an Audit message * */
	CreateModificationAuditmessage(user, type_of_holidays);

	/** 6. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return type_of_holidays;
    }

    /**
     * This method removes a type_of_holidays.
     * 
     * @param user
     *            The user who executes this use case
     * @param type_of_holidayscode
     *            Code of the type_of_holidays to be removed
     * @throws NoPermisosException
     */
    public static void RemoveType_of_holidays(Usuario user,
	    String type_of_holidayscode) throws NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain the object to delete form the DB. * */
	Type_of_holidays type_of_holidays = getType_of_holidays(type_of_holidayscode);

	/** 3. We mark it as deleted. * */
	type_of_holidays.setDeleted(Boolean.TRUE);

	/** 4. We create an Audit message * */
	CreateRemovealAuditmessage(user, type_of_holidays);

	/** 5. We commit the DB transaction. * */
	HibernateUtil.commitTransaction();
    }

    /**
     * This method obtains one instance of type_of_holidays given its code.
     * 
     * @param user
     *            The user who executes this use case
     * @param type_of_holidayscode
     *            Code of the type_of_holidays to be obtained
     * @return Type_of_holidays with the given code.
     */
    public static Type_of_holidays ObtainType_of_holidays(Usuario user,
	    String type_of_holidayscode) {

	/**
	 * 1. We obtain the object from the DB using the private getter and we
	 * return it. *
	 */

	Type_of_holidays type_of_holidays = getType_of_holidays(type_of_holidayscode);
	return type_of_holidays;
    }

    /**
     * This method obtains all instances of Type_of_holidays, given a
     * list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     */
    public static Pair<Integer, List<Type_of_holidays>> ObtainAllType_of_holidays(
	    Usuario user, ListConfigurator configurator) {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(
		Type_of_holidays.class);

	// we only want to obtain the non deleted objects
	crit.add(Expression.eq("deleted", Boolean.FALSE));

	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	List<Type_of_holidays> type_of_holidayss = (List<Type_of_holidays>) crit
		.list();

	Pair<Integer, List<Type_of_holidays>> pair = new Pair<Integer, List<Type_of_holidays>>(
		count, type_of_holidayss);

	return pair;
    }

    public static PersonalPhoto createPersonalPhoto(Usuario user,
	    PersonalPhoto TOPersonalPhoto) {
	HibernateUtil.beginTransaction();
	PersonalPhoto personalPhoto = new PersonalPhoto();
	personalPhoto.setPhoto(TOPersonalPhoto.getPhoto());
	try {
	    IdentifyManager_Plain im = IdentifyManager_Plain.singleton();
	    personalPhoto.setPersonalPhotocode(im.getId(new PersonalPhoto()));
	} catch (identifyException ie) {
	    log.error("Error en asignaciï¿½n de nuevo id en CreatePersonalPhoto",
		    ie);
	    throw new Error(ie.getMessage());
	}
	HibernateUtil.getSession().save(personalPhoto);
	HibernateUtil.commitTransaction();

	return personalPhoto;
    }

    public static void RemovePersonalPersonalPhoto(Usuario user,
	    String personalcode) throws InternalException,
	    ValidationFailedException {
	HibernateUtil.beginTransaction();
	Personal personal = getPersonal(personalcode);
	PersonalPhoto photo = personal.getPhoto();
	if (photo != null) {
	    personal.setPhoto(null);
	    HibernateUtil.getSession().delete(photo);
	}
	UseCaseUtils.updateLDAPFromModify(null,
		ObtainActiveProfessionalFromPersonal(personal), null, null);
	HibernateUtil.commitTransaction();
    }

    public static void RemovePersonalPhoto(Usuario user,
	    String personalPhotocode) {
	HibernateUtil.beginTransaction();
	HibernateUtil.getSession().delete(getPersonalPhoto(personalPhotocode));
	HibernateUtil.commitTransaction();
    }

    public static PersonalPhoto setPersonalPhotoToPersonal(Usuario user,
	    PersonalPhoto TOPersonalPhoto, String personalCode)
	    throws NoPermisosException, InternalException,
	    ValidationFailedException {
	Personal personal = getPersonal(personalCode);
	// testIsHHRROrItself(user, personal);

	HibernateUtil.beginTransaction();

	if (personal.getState().getCode().equals(Personalstate.ACTIVE_CODE))
	    UseCaseUtils.updateLDAPFromModify(null,
		    ObtainActiveProfessionalFromPersonal(personal), null,
		    TOPersonalPhoto);

	PersonalPhoto personalPhoto = new PersonalPhoto();
	personalPhoto.setPhoto(TOPersonalPhoto.getPhoto());
	try {
	    IdentifyManager_Plain im = IdentifyManager_Plain.singleton();

	    personalPhoto.setPersonalPhotocode(im.getId(new PersonalPhoto()));
	} catch (identifyException ie) {

	    log.error("Error en asignaciï¿½n de nuevo id en CreatePersonalPhoto",
		    ie);
	    throw new Error(ie.getMessage());
	}

	HibernateUtil.getSession().save(personalPhoto);

	if (personal.getPhoto() != null) {
	    HibernateUtil.getSession().delete(personal.getPhoto());
	}
	personal.setPhoto(personalPhoto);

	HibernateUtil.commitTransaction();

	return personalPhoto;
    }

    public static PersonalPhoto obtainPersonalPhoto(Usuario user,
	    String personalPhotocode) {
	return getPersonalPhoto(personalPhotocode);
    }

    /**
     * This method creates a Personal.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOPersonal
     *            Personal data transfer object (DTO) with the values of the new
     *            instance.
     * @return the new personal created with this Use Case
     * @throws InternalException
     * @throws ValidationFailedException
     */
    public static Personal CreatePersonal(Usuario user, Personal TOPersonal)
	    throws InternalException, ValidationFailedException {

	try {
	    /** 1. We begin the DB transaction. * */

	    HibernateUtil.beginTransaction();

	    /**
	     * 2. For each association from the TOPersonal that are filled in
	     * the DTO we put the real objects from the DB. *
	     */

	    // we store if the birth_country is defined for later use
	    boolean _birth_countryIsDefined = false;

	    if (TOPersonal.getBirth_country() != null
		    && TOPersonal.getBirth_country().getCountrycode() != null) {
		// if birth_country is defined we replace the birth_country in
		// the
		// DTO with its current state in the DB.
		_birth_countryIsDefined = true;

		TOPersonal.setBirth_country(getCountry(TOPersonal
			.getBirth_country().getCountrycode()));
	    }

	    // we store if the nationality is defined for later use
	    boolean _nationalityIsDefined = false;

	    if (TOPersonal.getNationality() != null
		    && TOPersonal.getNationality().getNationalitycode() != null) {
		// if nationality is defined we replace the nationality in the
		// DTO
		// with its current state in the DB.
		_nationalityIsDefined = true;

		TOPersonal.setNationality(getNationality(TOPersonal
			.getNationality().getNationalitycode()));
	    }

	    // we store if the nationality_2 is defined for later use
	    boolean _nationality_2IsDefined = false;

	    if (TOPersonal.getNationality_2() != null
		    && TOPersonal.getNationality_2().getNationalitycode() != null) {
		// if nationality_2 is defined we replace the nationality_2 in
		// the
		// DTO with its current state in the DB.
		_nationality_2IsDefined = true;

		TOPersonal.setNationality_2(getNationality(TOPersonal
			.getNationality_2().getNationalitycode()));
	    }

	    // we store if the country is defined for later use
	    boolean _countryIsDefined = false;

	    if (TOPersonal.getCountry() != null
		    && TOPersonal.getCountry().getCountrycode() != null) {
		// if country is defined we replace the country in the DTO with
		// its
		// current state in the DB.
		_countryIsDefined = true;

		TOPersonal.setCountry(getCountry(TOPersonal.getCountry()
			.getCountrycode()));
	    }

	    // we store if the payments is defined for later use
	    boolean _paymentsIsDefined = false;

	    if (TOPersonal.getPayments() != null
		    && TOPersonal.getPayments().getPaymentcode() != null) {
		// if payments is defined we replace the payments in the DTO
		// with
		// its current state in the DB.
		_paymentsIsDefined = true;

		TOPersonal.setPayments(getPayment(TOPersonal.getPayments()
			.getPaymentcode()));
	    }

	    // we store if the end_of_contract_country is defined for later use
	    boolean _end_of_contract_countryIsDefined = false;

	    if (TOPersonal.getEnd_of_contract_country() != null
		    && TOPersonal.getEnd_of_contract_country().getCountrycode() != null) {
		// if end_of_contract_country is defined we replace the
		// end_of_contract_country in the DTO with its current state in
		// the
		// DB.
		_end_of_contract_countryIsDefined = true;

		TOPersonal.setEnd_of_contract_country(getCountry(TOPersonal
			.getEnd_of_contract_country().getCountrycode()));
	    }

	    // we store if the gender is defined for later use
	    boolean _genderIsDefined = false;

	    if (TOPersonal.getGender() != null
		    && TOPersonal.getGender().getGendercode() != null) {
		// if gender is defined we replace the gender in the DTO with
		// its
		// current state in the DB.
		_genderIsDefined = true;

		TOPersonal.setGender(getGender(TOPersonal.getGender()
			.getGendercode()));
	    }

	    // we store if the marital_status is defined for later use
	    boolean _marital_statusIsDefined = false;

	    if (TOPersonal.getMarital_status() != null
		    && TOPersonal.getMarital_status().getMarital_statuscode() != null) {
		// if marital_status is defined we replace the marital_status in
		// the
		// DTO with its current state in the DB.
		_marital_statusIsDefined = true;

		TOPersonal.setMarital_status(getMarital_status(TOPersonal
			.getMarital_status().getMarital_statuscode()));
	    }

	    // we store if the bank is defined for later use
	    boolean _bankIsDefined = false;

	    if (TOPersonal.getBank() != null
		    && TOPersonal.getBank().getBankcode() != null) {
		// if bank is defined we replace the bank in the DTO with its
		// current state in the DB.
		_bankIsDefined = true;

		TOPersonal.setBank(getBank(TOPersonal.getBank().getBankcode()));
	    }

	    // we store if the working_hours is defined for later use
	    boolean _working_hoursIsDefined = false;

	    if (TOPersonal.getWorking_hours() != null
		    && TOPersonal.getWorking_hours().getWorking_hourscode() != null) {
		// if working_hours is defined we replace the working_hours in
		// the
		// DTO with its current state in the DB.
		_working_hoursIsDefined = true;

		TOPersonal.setWorking_hours(getWorking_hours(TOPersonal
			.getWorking_hours().getWorking_hourscode()));
	    }

	    // we store if the category is defined for later use
	    boolean _categoryIsDefined = false;

	    if (TOPersonal.getCategory() != null
		    && TOPersonal.getCategory().getCategorycode() != null) {
		// if category is defined we replace the category in the DTO
		// with
		// its current state in the DB.
		_categoryIsDefined = true;

		TOPersonal.setCategory(getCategory(TOPersonal.getCategory()
			.getCategorycode()));
	    }

	    boolean _languageIsDefined = false;
	    if (TOPersonal.getLanguage() != null
		    && TOPersonal.getLanguage().getLanguagecode() != null
		    && !TOPersonal.getLanguage().getLanguagecode().equals("")) {

		_languageIsDefined = true;

		TOPersonal.setLanguage(getLanguage(TOPersonal.getLanguage()
			.getLanguagecode()));
	    } else {
		_languageIsDefined = true;
		TOPersonal.setLanguage(getLanguage("es"));
	    }

	    /** 3. We create the new instance * */
	    Personal personal = new Personal();

	    /**
	     * 4. We set all the simple attributes (no associations) to the new
	     * instance *
	     */

	    personal.setName(TOPersonal.getName());

	    personal.setSurname1(TOPersonal.getSurname1());

	    personal.setSurname2(TOPersonal.getSurname2());

	    personal.setDni(TOPersonal.getDni());

	    personal.setBirth_date(TOPersonal.getBirth_date());

	    personal.setBirth_city(TOPersonal.getBirth_city());

	    personal.setStreet(TOPersonal.getStreet());

	    personal.setCity(TOPersonal.getCity());

	    personal.setZip_code(TOPersonal.getZip_code());

	    personal.setPhone(TOPersonal.getPhone());

	    personal.setPhone2(TOPersonal.getPhone2());

	    personal.setIce_phone(TOPersonal.getIce_phone());

	    personal.setSecond_affiliation(TOPersonal.getSecond_affiliation());

	    personal.setSs_number(TOPersonal.getSs_number());

	    personal.setPersonal_email(TOPersonal.getPersonal_email());

	    personal.setUsername(TOPersonal.getUsername() != null
		    && !TOPersonal.getUsername().trim().equals("") ? TOPersonal
		    .getUsername().trim() : null);

	    personal.setBank_account(TOPersonal.getBank_account());
	    
	    personal.setAccess_scientific_publications(TOPersonal.isAccess_scientific_publications());
	    
	    personal.setBic(TOPersonal.getBic());

	    personal.setResearch_project(TOPersonal.getResearch_project());

	    personal.setSponsoring_agency(TOPersonal.getSponsoring_agency());

	    personal.setHolding_institution(TOPersonal.getHolding_institution());

	    personal.setPrincipal_investigator(TOPersonal
		    .getPrincipal_investigator());

	    personal.setEnd_of_contract_reason(TOPersonal
		    .getEnd_of_contract_reason());

	    personal.setEnd_of_contract_address(TOPersonal
		    .getEnd_of_contract_address());

	    personal.setEnd_of_contract_city(TOPersonal
		    .getEnd_of_contract_city());

	    personal.setEnd_of_contract_zip_code(TOPersonal
		    .getEnd_of_contract_zip_code());

	    personal.setEnd_of_contract_phone(TOPersonal
		    .getEnd_of_contract_phone());

	    personal.setEnd_of_contract_email(TOPersonal
		    .getEnd_of_contract_email());

	    /** 5. We set the code to the new instance * */
	    if (TOPersonal.getCode() != null
		    && !TOPersonal.getCode().equals("")) {
		/**
		 * 5.1 If the TO contains a code, we set it: we are creating a
		 * personal for a user *
		 */
		personal.setCode(TOPersonal.getCode());
	    } else {
		/**
		 * 5.2 If the TO not contains a code, we create it (using the
		 * code generator of Usuario): we are creating a personal
		 * without a user *
		 */
		try {
		    IdentifyManager_Plain im = IdentifyManager_Plain
			    .singleton();

		    personal.setPersonalcode(Entity.USER_CODE
			    + im.getId(new Usuario()));
		} catch (identifyException ie) {

		    log.error(
			    "Error en asignaciï¿½n de nuevo id en CreatePersonal",
			    ie);
		    throw new Error(ie.getMessage());
		}
	    }
	    /** 6. We save the new instance to the DB* */
	    HibernateUtil.getSession().save(personal);

	    /**
	     * We associate the current object to the other objects (only in
	     * case that the associations where defined in the DTO) *
	     */

	    if (_birth_countryIsDefined) {

		if (TOPersonal.getBirth_country() != null) {

		    TOPersonal.getBirth_country().addIbirth_country(personal);
		}

		personal.setBirth_country(TOPersonal.getBirth_country());
	    }

	    if (_nationalityIsDefined) {

		if (TOPersonal.getNationality() != null) {

		    TOPersonal.getNationality().addInationality(personal);
		}

		personal.setNationality(TOPersonal.getNationality());
	    }

	    if (_nationality_2IsDefined) {

		if (TOPersonal.getNationality_2() != null) {

		    TOPersonal.getNationality_2().addInationality_2(personal);
		}

		personal.setNationality_2(TOPersonal.getNationality_2());
	    }

	    if (_countryIsDefined) {

		if (TOPersonal.getCountry() != null) {

		    TOPersonal.getCountry().addIcountry(personal);
		}

		personal.setCountry(TOPersonal.getCountry());
	    }

	    if (_paymentsIsDefined) {

		if (TOPersonal.getPayments() != null) {

		    TOPersonal.getPayments().addIpayments(personal);
		}

		personal.setPayments(TOPersonal.getPayments());
	    }

	    if (_end_of_contract_countryIsDefined) {

		if (TOPersonal.getEnd_of_contract_country() != null) {

		    TOPersonal.getEnd_of_contract_country()
			    .addIend_of_contract_country(personal);
		}

		personal.setEnd_of_contract_country(TOPersonal
			.getEnd_of_contract_country());
	    }

	    if (_genderIsDefined) {

		if (TOPersonal.getGender() != null) {

		    TOPersonal.getGender().addIgender(personal);
		}

		personal.setGender(TOPersonal.getGender());
	    }

	    if (_marital_statusIsDefined) {

		if (TOPersonal.getMarital_status() != null) {

		    TOPersonal.getMarital_status().addImarital_status(personal);
		}

		personal.setMarital_status(TOPersonal.getMarital_status());
	    }

	    if (_bankIsDefined) {

		if (TOPersonal.getBank() != null) {

		    TOPersonal.getBank().addIbank(personal);
		}

		personal.setBank(TOPersonal.getBank());
	    }

	    if (_working_hoursIsDefined) {

		if (TOPersonal.getWorking_hours() != null) {

		    TOPersonal.getWorking_hours().addIworking_hours(personal);
		}

		personal.setWorking_hours(TOPersonal.getWorking_hours());
	    }

	    if (_categoryIsDefined) {

		if (TOPersonal.getCategory() != null) {

		    TOPersonal.getCategory().addIcategory(personal);
		}

		personal.setCategory(TOPersonal.getCategory());
	    }

	    if (_languageIsDefined) {
		personal.setLanguage(TOPersonal.getLanguage());
	    }

	    personal.setState(getPersonalstate(Personalstate.EDITING_CODE));

	    /** 7. We create an Audit message * */
	    CreateCreationAuditmessage(user, personal);

	    /** 8. We commit the DB transaction and return the new instance * */
	    HibernateUtil.commitTransaction();

	    return personal;
	} catch (RuntimeException e) {
	    if (e.getCause() instanceof ConstraintViolationException
		    && e.getCause().getCause().getMessage()
			    .contains("unique_username")) {
		Map<String, List<String>> parameters = new Hashtable<String, List<String>>();
		parameters.put("propertyError.existingValue",
			Arrays.asList(new String[] { "username" }));
		throw new ValidationFailedException(parameters);
	    } else {
		throw e;
	    }
	}

    }

    /**
     * This method modifies a personal.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOPersonal
     *            Personal data transfer object (DTO) with the values of the
     *            modified instance. The code of this attribute indicates which
     *            personal will be modified.
     * @return the modified personal
     * @throws InternalException
     * @throws ValidationFailedException
     */
    public static Personal UpdatePersonal(Usuario user, Personal TOPersonal)
	    throws InternalException, ValidationFailedException {

	try {

	    /** 1. We begin the DB transaction. * */
	    HibernateUtil.beginTransaction();

	    /**
	     * 2. For each association from the TOPersonal that are filled in
	     * the DTO we put the real objects from the DB. *
	     */

	    // we store if the birth_country is defined for later use
	    boolean _birth_countryIsDefined = false;

	    if (TOPersonal.getBirth_country() != null
		    && TOPersonal.getBirth_country().getCountrycode() != null) {
		// if birth_country is defined we replace the birth_country in
		// the
		// DTO with its current state in the DB.
		_birth_countryIsDefined = true;

		TOPersonal.setBirth_country(getCountry(TOPersonal
			.getBirth_country().getCountrycode()));
	    }

	    // we store if the nationality is defined for later use
	    boolean _nationalityIsDefined = false;

	    if (TOPersonal.getNationality() != null
		    && TOPersonal.getNationality().getNationalitycode() != null) {
		// if nationality is defined we replace the nationality in the
		// DTO
		// with its current state in the DB.
		_nationalityIsDefined = true;

		TOPersonal.setNationality(getNationality(TOPersonal
			.getNationality().getNationalitycode()));
	    }

	    // we store if the nationality_2 is defined for later use
	    boolean _nationality_2IsDefined = false;

	    if (TOPersonal.getNationality_2() != null
		    && TOPersonal.getNationality_2().getNationalitycode() != null) {
		// if nationality_2 is defined we replace the nationality_2 in
		// the
		// DTO with its current state in the DB.
		_nationality_2IsDefined = true;

		TOPersonal.setNationality_2(getNationality(TOPersonal
			.getNationality_2().getNationalitycode()));
	    }

	    // we store if the country is defined for later use
	    boolean _countryIsDefined = false;

	    if (TOPersonal.getCountry() != null
		    && TOPersonal.getCountry().getCountrycode() != null) {
		// if country is defined we replace the country in the DTO with
		// its
		// current state in the DB.
		_countryIsDefined = true;

		TOPersonal.setCountry(getCountry(TOPersonal.getCountry()
			.getCountrycode()));
	    }

	    // we store if the payments is defined for later use
	    boolean _paymentsIsDefined = false;

	    if (TOPersonal.getPayments() != null
		    && TOPersonal.getPayments().getPaymentcode() != null) {
		// if payments is defined we replace the payments in the DTO
		// with
		// its current state in the DB.
		_paymentsIsDefined = true;

		TOPersonal.setPayments(getPayment(TOPersonal.getPayments()
			.getPaymentcode()));
	    }

	    // we store if the end_of_contract_country is defined for later use
	    boolean _end_of_contract_countryIsDefined = false;

	    if (TOPersonal.getEnd_of_contract_country() != null
		    && TOPersonal.getEnd_of_contract_country().getCountrycode() != null) {
		// if end_of_contract_country is defined we replace the
		// end_of_contract_country in the DTO with its current state in
		// the
		// DB.
		_end_of_contract_countryIsDefined = true;

		TOPersonal.setEnd_of_contract_country(getCountry(TOPersonal
			.getEnd_of_contract_country().getCountrycode()));
	    }

	    // we store if the gender is defined for later use
	    boolean _genderIsDefined = false;

	    if (TOPersonal.getGender() != null
		    && TOPersonal.getGender().getGendercode() != null) {
		// if gender is defined we replace the gender in the DTO with
		// its
		// current state in the DB.
		_genderIsDefined = true;

		TOPersonal.setGender(getGender(TOPersonal.getGender()
			.getGendercode()));
	    }

	    // we store if the marital_status is defined for later use
	    boolean _marital_statusIsDefined = false;

	    if (TOPersonal.getMarital_status() != null
		    && TOPersonal.getMarital_status().getMarital_statuscode() != null) {
		// if marital_status is defined we replace the marital_status in
		// the
		// DTO with its current state in the DB.
		_marital_statusIsDefined = true;

		TOPersonal.setMarital_status(getMarital_status(TOPersonal
			.getMarital_status().getMarital_statuscode()));
	    }

	    // we store if the bank is defined for later use
	    boolean _bankIsDefined = false;

	    if (TOPersonal.getBank() != null
		    && TOPersonal.getBank().getBankcode() != null) {
		// if bank is defined we replace the bank in the DTO with its
		// current state in the DB.
		_bankIsDefined = true;

		TOPersonal.setBank(getBank(TOPersonal.getBank().getBankcode()));
	    }

	    // we store if the working_hours is defined for later use
	    boolean _working_hoursIsDefined = false;

	    if (TOPersonal.getWorking_hours() != null
		    && TOPersonal.getWorking_hours().getWorking_hourscode() != null) {
		// if working_hours is defined we replace the working_hours in
		// the
		// DTO with its current state in the DB.
		_working_hoursIsDefined = true;

		TOPersonal.setWorking_hours(getWorking_hours(TOPersonal
			.getWorking_hours().getWorking_hourscode()));
	    }

	    // we store if the category is defined for later use
	    boolean _categoryIsDefined = false;

	    if (TOPersonal.getCategory() != null
		    && TOPersonal.getCategory().getCategorycode() != null) {
		// if category is defined we replace the category in the DTO
		// with
		// its current state in the DB.
		_categoryIsDefined = true;

		TOPersonal.setCategory(getCategory(TOPersonal.getCategory()
			.getCategorycode()));
	    }

	    boolean _languageIsDefined = false;
	    if (TOPersonal.getLanguage() != null
		    && TOPersonal.getLanguage().getLanguagecode() != null) {
		_languageIsDefined = true;

		TOPersonal.setLanguage(getLanguage(TOPersonal.getLanguage()
			.getLanguagecode()));
	    }

	    // we store if the state is defined for later use
	    boolean _stateIsDefined = false;

	    if (TOPersonal.getState() != null
		    && TOPersonal.getState().getPersonalstatecode() != null) {
		// if state is defined we replace the state in the DTO with its
		// current state in the DB.
		_stateIsDefined = true;

		TOPersonal.setState(getPersonalstate(TOPersonal.getState()
			.getPersonalstatecode()));
	    }

	    /** 3. We obtain form the DB the instance to modify * */
	    Personal personal = getPersonal(TOPersonal.getPersonalcode());

	    /*****/

	    // actulitzem la informacio del LDAP nomes si es un personal actiu
	    if (personal.getState().getCode().equals(Personalstate.ACTIVE_CODE)) {

		Professional professional = ObtainActiveProfessionalFromPersonal(TOPersonal);

		UseCaseUtils.updateLDAPFromModify(null, professional,
			TOPersonal, null);
	    }

	    /*****/

	    /**
	     * 4. We set all the simple attributes (no associations) to the
	     * instance *
	     */

	    personal.setName(TOPersonal.getName());

	    personal.setSurname1(TOPersonal.getSurname1());

	    personal.setSurname2(TOPersonal.getSurname2());

	    personal.setDni(TOPersonal.getDni());

	    personal.setBirth_date(TOPersonal.getBirth_date());

	    personal.setBirth_city(TOPersonal.getBirth_city());

	    personal.setStreet(TOPersonal.getStreet());

	    personal.setCity(TOPersonal.getCity());

	    personal.setZip_code(TOPersonal.getZip_code());

	    personal.setPhone(TOPersonal.getPhone());

	    personal.setPhone2(TOPersonal.getPhone2());

	    personal.setIce_phone(TOPersonal.getIce_phone());

	    personal.setSecond_affiliation(TOPersonal.getSecond_affiliation());

	    personal.setSs_number(TOPersonal.getSs_number());

	    personal.setPersonal_email(TOPersonal.getPersonal_email());

	    if (TOPersonal.getUsername() != null
		    && !TOPersonal.getUsername().equals("")) {
		personal.setUsername(TOPersonal.getUsername());
	    } else {
		personal.setUsername(null);
	    }

	    personal.setBank_account(TOPersonal.getBank_account());
	    
	    personal.setAccess_scientific_publications(TOPersonal.isAccess_scientific_publications());
	    
	    personal.setBic(TOPersonal.getBic());

	    personal.setResearch_project(TOPersonal.getResearch_project());

	    personal.setSponsoring_agency(TOPersonal.getSponsoring_agency());

	    personal.setHolding_institution(TOPersonal.getHolding_institution());

	    personal.setPrincipal_investigator(TOPersonal
		    .getPrincipal_investigator());

	    personal.setEnd_of_contract_reason(TOPersonal
		    .getEnd_of_contract_reason());

	    personal.setEnd_of_contract_address(TOPersonal
		    .getEnd_of_contract_address());

	    personal.setEnd_of_contract_city(TOPersonal
		    .getEnd_of_contract_city());

	    personal.setEnd_of_contract_zip_code(TOPersonal
		    .getEnd_of_contract_zip_code());

	    personal.setEnd_of_contract_phone(TOPersonal
		    .getEnd_of_contract_phone());

	    personal.setEnd_of_contract_email(TOPersonal
		    .getEnd_of_contract_email());

	    /**
	     * 5. We set the DTO version to the modified object and we update it
	     * with the new values in the DB. We evict and update the instance
	     * to prevent concurrent modification *
	     */
	    HibernateUtil.getSession().evict(personal);
	    personal.setVersion(TOPersonal.getVersion());
	    HibernateUtil.getSession().update(personal);

	    /**
	     * We associate/disassociate the current object to the other objects
	     * (only in case that the associations where defined in the DTO) *
	     */

	    if (_birth_countryIsDefined) {

		if (personal.getBirth_country() != null) {

		    personal.getBirth_country().removeIbirth_country(personal);
		}

		if (TOPersonal.getBirth_country() != null) {

		    TOPersonal.getBirth_country().addIbirth_country(personal);
		}

		personal.setBirth_country(TOPersonal.getBirth_country());
	    }

	    if (_nationalityIsDefined) {

		if (personal.getNationality() != null) {

		    personal.getNationality().removeInationality(personal);
		}

		if (TOPersonal.getNationality() != null) {

		    TOPersonal.getNationality().addInationality(personal);
		}

		personal.setNationality(TOPersonal.getNationality());
	    }

	    if (_nationality_2IsDefined) {

		if (personal.getNationality_2() != null) {

		    personal.getNationality_2().removeInationality_2(personal);
		}

		if (TOPersonal.getNationality_2() != null) {

		    TOPersonal.getNationality_2().addInationality_2(personal);
		}

		personal.setNationality_2(TOPersonal.getNationality_2());
	    }

	    if (_countryIsDefined) {

		if (personal.getCountry() != null) {

		    personal.getCountry().removeIcountry(personal);
		}

		if (TOPersonal.getCountry() != null) {

		    TOPersonal.getCountry().addIcountry(personal);
		}

		personal.setCountry(TOPersonal.getCountry());
	    }

	    if (_paymentsIsDefined) {

		if (personal.getPayments() != null) {

		    personal.getPayments().removeIpayments(personal);
		}

		if (TOPersonal.getPayments() != null) {

		    TOPersonal.getPayments().addIpayments(personal);
		}

		personal.setPayments(TOPersonal.getPayments());
	    }

	    if (_end_of_contract_countryIsDefined) {

		if (personal.getEnd_of_contract_country() != null) {

		    personal.getEnd_of_contract_country()
			    .removeIend_of_contract_country(personal);
		}

		if (TOPersonal.getEnd_of_contract_country() != null) {

		    TOPersonal.getEnd_of_contract_country()
			    .addIend_of_contract_country(personal);
		}

		personal.setEnd_of_contract_country(TOPersonal
			.getEnd_of_contract_country());
	    }

	    if (_genderIsDefined) {

		if (personal.getGender() != null) {

		    personal.getGender().removeIgender(personal);
		}

		if (TOPersonal.getGender() != null) {

		    TOPersonal.getGender().addIgender(personal);
		}

		personal.setGender(TOPersonal.getGender());
	    }

	    if (_marital_statusIsDefined) {

		if (personal.getMarital_status() != null) {

		    personal.getMarital_status()
			    .removeImarital_status(personal);
		}

		if (TOPersonal.getMarital_status() != null) {

		    TOPersonal.getMarital_status().addImarital_status(personal);
		}

		personal.setMarital_status(TOPersonal.getMarital_status());
	    }

	    if (_bankIsDefined) {

		if (personal.getBank() != null) {

		    personal.getBank().removeIbank(personal);
		}

		if (TOPersonal.getBank() != null) {

		    TOPersonal.getBank().addIbank(personal);
		}

		personal.setBank(TOPersonal.getBank());
	    }

	    if (_working_hoursIsDefined) {

		if (personal.getWorking_hours() != null) {

		    personal.getWorking_hours().removeIworking_hours(personal);
		}

		if (TOPersonal.getWorking_hours() != null) {

		    TOPersonal.getWorking_hours().addIworking_hours(personal);
		}

		personal.setWorking_hours(TOPersonal.getWorking_hours());
	    }

	    if (_categoryIsDefined) {

		if (personal.getCategory() != null) {

		    personal.getCategory().removeIcategory(personal);
		}

		if (TOPersonal.getCategory() != null) {

		    TOPersonal.getCategory().addIcategory(personal);
		}

		personal.setCategory(TOPersonal.getCategory());
	    }

	    if (_languageIsDefined) {
		personal.setLanguage(TOPersonal.getLanguage());
	    }

	    if (_stateIsDefined) {

		personal.setState(TOPersonal.getState());
	    }

	    /** 6. We create an Audit message * */
	    CreateModificationAuditmessage(user, personal);

	    /** 7. We commit the DB transaction and return the new instance * */
	    HibernateUtil.commitTransaction();

	    return personal;

	} catch (RuntimeException e) {
	    if (e.getCause() instanceof ConstraintViolationException
		    && e.getCause().getCause().getMessage()
			    .contains("unique_username")) {
		Map<String, List<String>> parameters = new Hashtable<String, List<String>>();
		parameters.put("propertyError.existingValue",
			Arrays.asList(new String[] { "username" }));
		throw new ValidationFailedException(parameters);
	    } else {
		throw e;
	    }
	}

    }

    /**
     * This method removes a personal.
     * 
     * @param user
     *            The user who executes this use case
     * @param personalcode
     *            Code of the personal to be removed
     * @throws InternalException
     * @throws NoPermisosException
     * @throws UsuarioNoActivoException
     * @throws ValidationFailedException
     */
    public static void RemovePersonal(Usuario user, String personalcode)
	    throws NoPermisosException, InternalException,
	    UsuarioNoActivoException, ValidationFailedException {

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain the object to delete form the DB. * */
	Personal personal = getPersonal(personalcode);

	if (personal.getState().getCode().equals(Personalstate.ACTIVE_CODE)) {

	    UseCaseUtils
		    .updateLDAPFromDelete(ObtainActiveProfessionalFromPersonal(personal));

	}

	/** 3. We mark it as deleted. * */
	personal.setDeleted(Boolean.TRUE);
	// HibernateUtil.getSession().delete(personal);

	// Usuario userOfPersonal = ObtenerUsuarioFromPersonal(user,
	// personalcode);
	// if (userOfPersonal != null) {
	// try {
	// BorrarUsuario(user, userOfPersonal);
	// } catch (InternalException ie) {
	// }
	// }

	/** 4. We create an Audit message * */
	CreateRemovealAuditmessage(user, personal);

	/** 5. We commit the DB transaction. * */
	HibernateUtil.commitTransaction();
    }

    /**
     * This method obtains one instance of personal given its code.
     * 
     * @param user
     *            The user who executes this use case
     * @param personalcode
     *            Code of the personal to be obtained
     * @return Personal with the given code.
     */
    public static Personal ObtainPersonal(Usuario user, String personalcode) {

	/**
	 * 1. We obtain the object from the DB using the private getter and we
	 * return it. *
	 */

	Personal personal = getPersonal(personalcode);
	return personal;
    }

    /**
     * This method obtains all instances of Personal, given a list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     */
    public static Pair<Integer, List<Personal>> ObtainAllPersonal(Usuario user,
	    ListConfigurator configurator) {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(
		Personal.class);

	// we only want to obtain the non deleted objects
	crit.add(Expression.eq("deleted", Boolean.FALSE));

	
    
	
	
	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */

	List<Personal> personals = (List<Personal>) crit.list();

	Pair<Integer, List<Personal>> pair = new Pair<Integer, List<Personal>>(
		count, personals);

	return pair;
    }

    public static Pair<Integer, Pair<List<Personal>, Map<String, String[]>>> ObtainAllPersonalAndOrderMap(
	    Usuario user, ListConfigurator configurator) {

    log.debug("ObtainAllPersonalAndOrderMap");
    log.debug(user.getRoles().size());
    if(user.getRoles().size()>0){
	    for(Role r : (Set<Role>)user.getRoles()){
	    	log.debug("Role name: "+r.getRolename());
	    }
    }else{
    	log.debug("No roles found!");
    }
    	
	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(
		Personal.class);

	// we only want to obtain the non deleted objects
	crit.add(Expression.eq("deleted", Boolean.FALSE));

	
	filterByRole(user, crit);
	
	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit, true);

	
	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */

	// ScrollableResults scroll = crit.scroll(ScrollMode.SCROLL_SENSITIVE);

	List<Personal> personals = (List<Personal>) crit.list();

	crit.setFirstResult(0);
	crit.setMaxResults(5000);

	List<Personal> allPersonals = (List<Personal>) crit.list();

	Map<String, String[]> map = new HashMap<String, String[]>();

	for (int i = 0; i < allPersonals.size(); i++) {
	    String next = i == allPersonals.size() - 1 ? null : allPersonals
		    .get(i + 1).getCode();
	    String previous = i == 0 ? null : allPersonals.get(i - 1).getCode();
	    map.put(allPersonals.get(i).getCode(), new String[] { previous,
		    next });
	}

	Pair<Integer, Pair<List<Personal>, Map<String, String[]>>> pair = new Pair<Integer, Pair<List<Personal>, Map<String, String[]>>>(
		count, new Pair<List<Personal>, Map<String, String[]>>(
			personals, map));

	return pair;
    }
    
    

    private static void checkByRole(Usuario user,String personalcode) throws NoPermisosException{
    	if (user==null) {
    		return;
    	}
    	Criteria crit = HibernateUtil.getSession().createCriteria(Personal.class);
    	crit.add(Expression.eq("deleted", Boolean.FALSE));
    	crit.add(Expression.eq("personalcode", personalcode));
    	filterByRole(user, crit);
    	if(crit.list().size()==0){
    		throw new NoPermisosException();
    	}
    }
    
    private static String addFilterByRoleWhere(Usuario user, String where){
    	String sqlToAdd="";    	
    	if (UserUtils.checkRole(user, UseCase.IRBPEOPLE_GRANT_ROLE_NAME)){
    		log.debug("Filtering for IRBPepole Grant");
    		sqlToAdd += " personalcode in ( " +
    			"SELECT " +
    			"	fper.personalcode " +
    			"FROM " +
    			"	personal fper " +
    			"LEFT JOIN professional fpro ON fpro.professional_personal = fper.personalcode " +
    			"WHERE " +
    			"	fper.deleted = 0 " +
    			"AND fpro.deleted = 0 " +
    			"AND fpro.current = 1 " +
    			"AND fpro.position IN ('00005', '00006', '00007','00014') " +
    			")";
    	} else if(UserUtils.checkRole(user, UseCase.IRBPEOPLE_INNOVATION_ROLE_NAME)) {
    		log.debug("Filtering for IRBPepole Innovation");
    		sqlToAdd += " personalcode in( " +
    			"SELECT " +
    			"	fper.personalcode " +
    			"FROM " +
    			"	personal fper " +
    			"LEFT JOIN professional fpro ON fpro.professional_personal = fper.personalcode " +
    			"LEFT JOIN unit fun1 on fpro.professional_unit = fun1.unitcode " +
    			"LEFT JOIN unit fun2 on fpro.professional_unit_2 = fun2.unitcode " +
    			"LEFT JOIN unit fun3 on fpro.professional_unit_3 = fun3.unitcode " +
    			"LEFT JOIN unit fun4 on fpro.professional_unit_4 = fun4.unitcode " +
    			"WHERE "+
    			"	fper.deleted = 0 " +
    			"AND fpro.deleted = 0 " +
    			"AND fpro.current = 1 " +
    			"AND fpro.position NOT IN ('00019') " +
    			"AND (fun1.organization_unit is null or fun1.organization_unit not in ('00002', '00003', '00004')) " +
    			"AND (fun2.organization_unit is null or fun2.organization_unit not in ('00002', '00003', '00004')) " +
    			"AND (fun3.organization_unit is null or fun3.organization_unit not in ('00002', '00003', '00004')) " +
    			"AND (fun4.organization_unit is null or fun4.organization_unit not in ('00002', '00003', '00004')) " +
    			")";
    	}
    	if(where.length()>0 && sqlToAdd.length()>0){
    		sqlToAdd = " and " + sqlToAdd;
    	}
    	return sqlToAdd;
    }
    
    private static void filterByRole(Usuario user, Criteria crit){
    	if (UserUtils.checkRole(user, UseCase.IRBPEOPLE_GRANT_ROLE_NAME)){
    		log.debug("Filtering for IRBPeople Grant");
    		crit.createAlias("iprofessional_personal", "p")
    		.createAlias("p.position", "po")
    	    .add(Restrictions.eq("p.current", true))
    	    .add(Restrictions.eq("p.deleted", false))
    	    .add(Restrictions.in("po.positioncode",new String[]{"00005", "00006", "00007", "00014"}));
    	}else if(UserUtils.checkRole(user, UseCase.IRBPEOPLE_INNOVATION_ROLE_NAME)){
    		log.debug("Filtering for IRBPeople Innovation");
    		crit.createAlias("iprofessional_personal", "p")    		
    		.createAlias("p.position", "po")
    	    .createAlias("p.professional_unit", "u1", Criteria.LEFT_JOIN)
    	    .createAlias("p.professional_unit_2", "u2", Criteria.LEFT_JOIN)
    	    .createAlias("p.professional_unit_3", "u3", Criteria.LEFT_JOIN)
    	    .createAlias("p.professional_unit_4", "u4", Criteria.LEFT_JOIN)
    	    .createAlias("u1.organization_unit", "ou1", Criteria.LEFT_JOIN)
    	    .createAlias("u2.organization_unit", "ou2", Criteria.LEFT_JOIN)
    	    .createAlias("u3.organization_unit", "ou3", Criteria.LEFT_JOIN)
    	    .createAlias("u4.organization_unit", "ou4", Criteria.LEFT_JOIN)
    	    .add(Restrictions.eq("p.current", true))
    	    .add(Restrictions.eq("p.deleted", false))
    	    .add(Restrictions.or(Restrictions.isNull("ou1.organization_unitcode"), Restrictions.not(Restrictions.in("ou1.organization_unitcode", new String[]{"00002", "00003", "00004"}))))
    	    .add(Restrictions.or(Restrictions.isNull("ou2.organization_unitcode"), Restrictions.not(Restrictions.in("ou2.organization_unitcode", new String[]{"00002", "00003", "00004"}))))
    	    .add(Restrictions.or(Restrictions.isNull("ou3.organization_unitcode"), Restrictions.not(Restrictions.in("ou3.organization_unitcode", new String[]{"00002", "00003", "00004"}))))
    	    .add(Restrictions.or(Restrictions.isNull("ou4.organization_unitcode"), Restrictions.not(Restrictions.in("ou4.organization_unitcode", new String[]{"00002", "00003", "00004"}))))
    	    .add(Restrictions.not(Restrictions.in("po.positioncode",new String[]{"00019"})));
    	}
    }
    
    /**
     * This method obtains all instances of Personal that do not have user,
     * given a list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     * @throws InternalException
     */
    public static Pair<Integer, List<Personal>> ObtainAllPersonalWithoutUser(
	    Usuario user, ListConfigurator configurator)
	    throws InternalException {

    	log.debug("ObtainAllPersonalWithoutUser");
    	
	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(
		Personal.class);

	// we only want to obtain the non deleted objects
	crit.add(Expression.eq("deleted", Boolean.FALSE));
	List<Usuario> users;
	try {
	    Criteria userCrit = HibernateUtil.getSession().createCriteria(
		    Usuario.class);
	    userCrit.add(Expression.isNull("enddate"));
	    users = userCrit.list();
	} catch (Exception e) {
	    throw new InternalException();
	}

	if (!users.isEmpty()) {
	    List<String> usersCodes = new ArrayList<String>();
	    for (Usuario it_usuario : users) {
		usersCodes.add(it_usuario.getCode());
	    }

	    crit.add(Expression.not(Expression.in("personalcode", usersCodes)));
	}
	
	filterByRole(user, crit);
	

	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	
	List<Personal> personals = (List<Personal>) crit.list();

	Pair<Integer, List<Personal>> pair = new Pair<Integer, List<Personal>>(
		count, personals);

	return pair;
    }

    /**
     * This method obtains all instances of Personalstate, given a
     * list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     */
    public static Pair<Integer, List<Personalstate>> ObtainAllPersonalstate(
	    Usuario user, ListConfigurator configurator) {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(
		Personalstate.class);

	// we only want to obtain the non deleted objects
	crit.add(Expression.eq("deleted", Boolean.FALSE));

	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	List<Personalstate> personalstates = (List<Personalstate>) crit.list();

	Pair<Integer, List<Personalstate>> pair = new Pair<Integer, List<Personalstate>>(
		count, personalstates);

	return pair;
    }

    /**
     * This method creates a Position.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOPosition
     *            Position data transfer object (DTO) with the values of the new
     *            instance.
     * @return the new position created with this Use Case
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Position CreatePosition(Usuario user, Position TOPosition)
	    throws InternalException, NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We create the new instance * */
	Position position = new Position();

	/**
	 * 3. We set all the simple attributes (no associations) to the new
	 * instance *
	 */

	position.setDescription(TOPosition.getDescription());

	/** 4. We set the code to the new instance * */
	try {
	    IdentifyManager_Plain im = IdentifyManager_Plain.singleton();

	    position.setPositioncode(im.getId(TOPosition));
	} catch (identifyException ie) {

	    log.error("Error en asignaciï¿½n de nuevo id en CreatePosition", ie);
	    throw new Error(ie.getMessage());
	}

	/** 5. We save the new instance to the DB* */
	HibernateUtil.getSession().save(position);

	/** 6. We create an Audit message * */
	CreateCreationAuditmessage(user, position);

	/** 7. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return position;
    }

    /**
     * This method modifies a position.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOPosition
     *            Position data transfer object (DTO) with the values of the
     *            modified instance. The code of this attribute indicates which
     *            position will be modified.
     * @return the modified position
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Position UpdatePosition(Usuario user, Position TOPosition)
	    throws InternalException, NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain form the DB the instance to modify * */
	Position position = getPosition(TOPosition.getPositioncode());
	;

	/**
	 * 3. We set all the simple attributes (no associations) to the instance
	 * *
	 */

	position.setDescription(TOPosition.getDescription());

	/**
	 * 4. We set the DTO version to the modified object and we update it
	 * with the new values in the DB. We evict and update the instance to
	 * prevent concurrent modification *
	 */
	HibernateUtil.getSession().evict(position);
	position.setVersion(TOPosition.getVersion());
	HibernateUtil.getSession().update(position);

	/** 5. We create an Audit message * */
	CreateModificationAuditmessage(user, position);

	/** 6. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return position;
    }

    /**
     * This method removes a position.
     * 
     * @param user
     *            The user who executes this use case
     * @param positioncode
     *            Code of the position to be removed
     * @throws NoPermisosException
     */
    public static void RemovePosition(Usuario user, String positioncode)
	    throws NoPermisosException {
	testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain the object to delete form the DB. * */
	Position position = getPosition(positioncode);

	/** 3. We mark it as deleted. * */
	position.setDeleted(Boolean.TRUE);

	/** 4. We create an Audit message * */
	CreateRemovealAuditmessage(user, position);

	/** 5. We commit the DB transaction. * */
	HibernateUtil.commitTransaction();
    }

    /**
     * This method obtains one instance of position given its code.
     * 
     * @param user
     *            The user who executes this use case
     * @param positioncode
     *            Code of the position to be obtained
     * @return Position with the given code.
     */
    public static Position ObtainPosition(Usuario user, String positioncode) {

	/**
	 * 1. We obtain the object from the DB using the private getter and we
	 * return it. *
	 */

	Position position = getPosition(positioncode);
	return position;
    }

    /**
     * This method obtains all instances of Position, given a list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     */
    public static Pair<Integer, List<Position>> ObtainAllPosition(Usuario user,
	    ListConfigurator configurator) {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(
		Position.class);

	// we only want to obtain the non deleted objects
	crit.add(Expression.eq("deleted", Boolean.FALSE));

	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	List<Position> positions = (List<Position>) crit.list();

	Pair<Integer, List<Position>> pair = new Pair<Integer, List<Position>>(
		count, positions);

	return pair;
    }

    /**
     * This method creates a new user.
     * 
     * @param userTO
     *            Data transfer object (DTO) with the values of the new
     *            instance.
     * @return new user
     * @throws UsuarioExisteException
     */
    public static Usuario CrearUsuario(Usuario userTO)
	    throws UsuarioExisteException {

	testExistsUser(userTO);

	Usuario user = InternalCrearUsuario(userTO, null);

	if (user.getActiveboolean() != 1) {
	    UseCaseUtils.sendConfirmationMail(user);
	}

	return user;
    }

    /**
     * This method creates a new user.
     * 
     * @param userTO
     *            Data transfer object (DTO) with the values of the new
     *            instance.
     * @return new user
     * @throws UsuarioExisteException
     * @throws InternalException
     */
    public static Usuario CreateUsuarioForPersonal(Usuario userTO,
	    String personalcode) throws UsuarioExisteException,
	    InternalException {
	Usuario oldUser = getUsuario(personalcode);

	if (oldUser != null) {
	    HibernateUtil.beginTransaction();
	    try {
		UserManagement.singleton().removeAllRoles(oldUser);
	    } catch (Exception e) {
		throw new InternalException(e);
	    }
	    HibernateUtil.getSession().delete(oldUser);
	    HibernateUtil.commitTransaction();
	    /*
	     * oldUser.setEmail(userTO.getEmail()); oldUser.setEnddate(null);
	     * oldUser.changePassword(new String(userTO.getPassword()));
	     * 
	     * try { UserManagement.singleton().removeAllRoles(oldUser); } catch
	     * (Exception e) { throw new InternalException(e); }
	     * 
	     * HibernateUtil.getSession().update(oldUser);
	     * 
	     * HibernateUtil.commitTransaction();
	     * 
	     * return oldUser;
	     */
	}

	testExistsUser(userTO);

	Usuario user = InternalCrearUsuario(userTO, personalcode);

	return user;
    }

    private static Usuario InternalCrearUsuario(Usuario userTO, String userCode)
	    throws UsuarioExisteException {
	HibernateUtil.beginTransaction();

	Usuario user = new Usuario();

	if (userCode == null) {
	    /** Create the code of the user * */
	    try {
		IdentifyManager_Plain im = IdentifyManager_Plain.singleton();
		user.setUsername(im.getId(user));
	    } catch (identifyException ie) {

		log.error(
			"Error en asignaciï¿½n de nuevo id en CreateCompensation",
			ie);
		throw new Error(ie.getMessage());
	    }
	} else {
	    user.setUsuariocode(userCode);
	}

	user.changePassword(new String(userTO.getPassword()));
	user.setEmail(userTO.getEmail());
	// descomentar para la feature de multilenguaje
	// user.setLanguage(getLanguage(userTO.getLanguage().getLanguagecode()));
	user.setLanguage(getLanguage("en"));

	if (userTO.getActiveboolean() != null && userTO.getActiveboolean() == 1) {
	    user.setActiveboolean(1);
	} else {
	    user.setActiveboolean(0);
	}
	user.setActivationCode(UseCaseUtils.createActivationCode());

	HibernateUtil.getSession().save(user);

	CreateAuditmessage(user, "auditmessage.createUser",
		new String[] { user.getUsername() }, CREATE_MESSAGE);

	HibernateUtil.commitTransaction();

	return user;
    }

    /**
     * This method modifies a user.
     * 
     * @param admin
     *            The user who executes this use case
     * @param userTO
     *            Data transfer object (DTO) with the values of the modified
     *            user. The code of this attribute indicates which user will be
     *            modified.
     * @return modified user
     * @throws NoPermisosException
     * @throws InternalException
     * @throws UsuarioNoActivoException
     */
    public static Usuario ModificarUsuario(Usuario admin, Usuario userTO)
	    throws NoPermisosException, UsuarioNoActivoException,
	    InternalException {

	HibernateUtil.beginTransaction();

	testIsAdminOrItself(admin, userTO);

	Usuario user = (Usuario) getUsuario(userTO.getUsuariocode());

	user.setUsername(userTO.getUsername());

	user.setLanguage(getLanguage(userTO.getLanguage().getLanguagecode()));

	HibernateUtil.getSession().evict(user);

	user.setVersion(userTO.getVersion());

	HibernateUtil.getSession().update(user);

	CreateAuditmessage(user, "auditmessage.modifyUser",
		new String[] { userTO.getUsername() }, MODIFY_MESSAGE);

	HibernateUtil.commitTransaction();

	return user;
    }

    /**
     * This method registers a user.
     * 
     * @param user
     *            The user who executes this use case
     * @param pendiente
     *            The user who to register
     * @return registered user
     * @throws NoPermisosException
     * @throws InternalException
     * @throws UsuarioNoActivoException
     * @throws ValidationFailedException
     */
    public static Usuario AltaUsuario(Usuario user, Usuario pendiente)
	    throws UsuarioNoActivoException, NoPermisosException,
	    InternalException, ValidationFailedException {
	return AltaUsuario(user, pendiente, null);
    }

    /**
     * This method registers a user.
     * 
     * @param user
     *            The user who executes this use case
     * @param pendiente
     *            The user who to register
     * @param activateionCode
     *            Activation code of the user
     * @return registered user
     * @throws NoPermisosException
     * @throws InternalException
     * @throws UsuarioNoActivoException
     * @throws ValidationFailedException
     */
    public static Usuario AltaUsuario(Usuario user, Usuario pendiente,
	    String activationCode) throws UsuarioNoActivoException,
	    NoPermisosException, InternalException, ValidationFailedException {
	HibernateUtil.beginTransaction();

	// testIsActiveUser(user);
	// testIsAdmin(user);
	testAltaUsuarioPermissions(user, pendiente, activationCode);

	/** we set the user to active * */
	pendiente.setActiveboolean(1);

	/** we create a Personal if needed * */
	if (pendiente.getPersonal() == null) {
	    Personal personalTO = new Personal();
	    personalTO.setCode(pendiente.getCode());

	    Personal personal = CreatePersonal(pendiente, personalTO);
	    pendiente.setPersonal(personal);
	}

	/** we erase its activation code * */
	pendiente.setActivationCode(null);

	HibernateUtil.getSession().update(pendiente);

	CreateAuditmessage((user != null) ? user : pendiente,
		"auditmessage.activateUser",
		new String[] { pendiente.getCode() }, MODIFY_MESSAGE);

	if (activationCode != null)
	    UseCaseUtils.sendUserActivatedMail(pendiente);

	HibernateUtil.commitTransaction();

	return pendiente;
    }

    /**
     * This method modifies password's user.
     * 
     * @param user
     *            The user who executes this use case
     * @param pendiente
     *            The user who will be modified its password
     * @param password
     *            new user's password
     * @return modified user
     * @throws NoPermisosException
     * @throws InternalException
     * @throws UsuarioNoActivoException
     */
    public static Usuario ModificarPassword(Usuario user, Usuario pendiente,
	    String password) throws UsuarioNoActivoException,
	    NoPermisosException, InternalException {
	HibernateUtil.beginTransaction();

	testIsActiveUser(user);
	testIsAdminOrItself(user, pendiente);

	pendiente.changePassword(password);
	pendiente.setChangePasswordCode(null);

	HibernateUtil.getSession().update(pendiente);

	CreateAuditmessage(user, "auditmessage.changedPassword",
		new String[] { pendiente.getUsername() }, MODIFY_MESSAGE);

	HibernateUtil.commitTransaction();

	return pendiente;
    }

    /**
     * This method marks an existing user as deleted.
     * 
     * @param user
     *            The user who executes this use case
     * @param pendiente
     *            The user who will be marked as deleted
     * @return User which has been marked as deleted.
     * @throws NoPermisosException
     * @throws InternalException
     * @throws UsuarioNoActivoException
     */
    public static Usuario BorrarUsuario(Usuario user, Usuario pendiente)
	    throws UsuarioNoActivoException, NoPermisosException,
	    InternalException {
	HibernateUtil.beginTransaction();

	testIsActiveUser(user);
	// testIsAdminOrItself(user, pendiente);

	try {
	    pendiente.delete();
	} catch (UserManagementException e) {
	    if (!e.getMessage().contains("already deleted"))
		manageInternalException(e);
	}
	HibernateUtil.getSession().update(pendiente);

	CreateAuditmessage(user, "auditmessage.deleteUser",
		new String[] { pendiente.getCode() }, DELETE_MESSAGE);

	HibernateUtil.commitTransaction();

	return pendiente;
    }

    /**
     * This method deactivates a user.
     * 
     * @param user
     *            The user who executes this use case
     * @param pendiente
     *            The user to deactivate
     * @return deactivated user
     * @throws NoPermisosException
     * @throws InternalException
     * @throws UsuarioNoActivoException
     */
    public static Usuario DesactivarUsuario(Usuario user, Usuario pendiente)
	    throws UsuarioNoActivoException, NoPermisosException,
	    InternalException {
	HibernateUtil.beginTransaction();

	testIsActiveUser(user);
	testIsAdminOrItself(user, pendiente);

	pendiente.setActiveboolean(0);
	HibernateUtil.getSession().update(pendiente);

	CreateAuditmessage(user, "auditmessage.deactivateUser",
		new String[] { pendiente.getUsername() }, CREATE_MESSAGE);

	HibernateUtil.commitTransaction();

	return pendiente;
    }

    /**
     * This method recovers a user.
     * 
     * @param user
     *            The user who executes this use case
     * @param pendiente
     *            User to recover
     * @return recovered user
     * @throws NoPermisosException
     * @throws InternalException
     * @throws UsuarioNoActivoException
     */
    public static Usuario RestaurarUsuario(Usuario user, Usuario pendiente)
	    throws UsuarioNoActivoException, NoPermisosException,
	    InternalException {
	HibernateUtil.beginTransaction();

	testIsActiveUser(user);
	testIsAdmin(user);

	try {
	    pendiente.recover();
	} catch (UserManagementException e) {
	    manageInternalException(e);
	}

	HibernateUtil.getSession().update(pendiente);

	CreateAuditmessage(user, "auditmessage.userRecover",
		new String[] { pendiente.getUsername() }, MODIFY_MESSAGE);

	HibernateUtil.commitTransaction();

	return pendiente;
    }

    /**
     * This method removes a user definitively.
     * 
     * @param user
     *            The user who executes this use case
     * @param pendiente
     *            The user who will be removed definitively
     * @return removed user
     * @throws NoPermisosException
     * @throws InternalException
     * @throws UsuarioNoActivoException
     */
    public static Usuario BorrarUsuarioDefinitivamente(Usuario user,
	    Usuario pendiente) throws UsuarioNoActivoException,
	    NoPermisosException, InternalException {
	HibernateUtil.beginTransaction();

	testIsActiveUser(user);
	testIsAdmin(user);

	pendiente.setDeletedboolean(new Integer(1));
	HibernateUtil.getSession().update(pendiente);

	CreateAuditmessage(user, "auditmessage.deleteUserDefinitely",
		new String[] { pendiente.getCode() }, DELETE_MESSAGE);

	HibernateUtil.commitTransaction();

	return pendiente;
    }

    /**
     * This method removes all users definitively.
     * 
     * @param user
     *            The user who executes this use case
     * @throws NoPermisosException
     * @throws InternalException
     * @throws UsuarioNoActivoException
     */
    public static void BorrarTODOSUsuariosDefinitivamente(Usuario user)
	    throws UsuarioNoActivoException, NoPermisosException,
	    InternalException {
	HibernateUtil.beginTransaction();

	testIsActiveUser(user);
	testIsAdmin(user);

	try {
	    UserManagement.singleton().deleteAllUsersDefinately();
	} catch (Exception e) {
	    manageInternalException(e);
	}

	CreateAuditmessage(user, "auditmessage.deleteAll", new String[] {},
		DELETE_MESSAGE);

	HibernateUtil.commitTransaction();

    }

    /**
     * This function gets a user from its username, only if it's active and not
     * deleted
     * 
     * @param username
     *            String with the username of the user to obtain
     * @return logged user
     * @throws UsuarioNoActivoException 
     */
    public static Usuario HacerLogin(String username, String password,
	    String remoteHost) throws UsuarioNoActivoException {
		Usuario user = null;
	
		HibernateUtil.beginTransaction();	    
		User_access userAccess = getUser_access(username);
		String userFullname = null;
		if(userAccess == null || !userAccess.isLocked()){
		
			if (!MAINCONFIG.getString("ldapActive").equals("yes")) {
			    try {
				UserManagement um = UserManagement.singleton();
				user = (Usuario) um.getUserForLogin(username);
				// esto es para obtener los roles del usuario
				// en este momento y no dejar la relaciï¿½n como lazy
				// ya que los vamos a necesitar cuando la sesion ya se haya
				// cerrado.
				Set<Role> roles2 = user.getRoles();
				for (Role r : roles2) {
				    r.getEntitycode();
				}
		
			    } catch (Exception e) {
				log.debug(e);
			    }
			    
			    if(user!=null){
			    	userFullname = user.getFullname();		    			
			    }
			    
			    if (user == null || !user.isMyPassword(password)) {
				user = null;
			    }
			} else // si estï¿½ configurado el acceso ldap
			{
		
			    LDAPLogin ldapLogin = new LDAPLogin();
		
			    boolean correctLogin = ldapLogin.doLogin(username, password);
			    
			    try{
			    	String getLDAPFullName = MAINCONFIG.getString("getLDAPFullName");
			    	if (getLDAPFullName!=null && (getLDAPFullName.trim().equals("yes") || getLDAPFullName.trim().equals("1"))){
			    		userFullname = ldapLogin.getFullname(username);
			    	}else{
			    		userFullname = "UNDEFINED";
			    	}
			    }catch(Exception e){
			    	log.error("Error obteniendo nombre completo en LDAP para " + username, e);
			    }
			    
			    if (correctLogin) {
				user = new Usuario();
				try {
				    Set<Role> roles = new HashSet<Role>();
				    roles.add(new Role(ldapLogin.getUserrole(), ldapLogin
					    .getUserrole(), ""));
				    
				    log.debug("Setting user roles to user:" + roles);
				    
				    user.setRoles(roles);
				    
				    Criteria crit = HibernateUtil.getSession().createCriteria(
					    Personal.class);
				    crit.add(Expression.eq("username", username));
		
				    Personal per = (Personal) crit.uniqueResult();
		
				    if (per == null) {
					// Este username no estï¿½ en la BD de personal de
					// IRBPeople personal.username
					user = null;
						log.debug("User not found in personal");
				    } else {
					user.setPersonal(per);
						log.debug("User found in personal");
		
					user.setLanguage(per.getLanguage());
					user.setCode(per.getPersonalcode());
					user.setUsername(per.getUsercode());			
				    }
		
				    // Update userrole table
				    Query deleteQuery = HibernateUtil.getSession().createSQLQuery("delete from userrole where usercode=?");
		    		deleteQuery.setString(0, per.getPersonalcode());
		    		deleteQuery.executeUpdate();

		    		deleteQuery = HibernateUtil.getSession().createSQLQuery("delete from umuser where entitycode=?");
		    		deleteQuery.setString(0, per.getUsercode());
		    		deleteQuery.executeUpdate();
		    		
		    		Query insertQuery = HibernateUtil.getSession().createSQLQuery("insert into umuser (entitycode,password,languagecode) values (?,?,?)");
		    		insertQuery.setString(0, per.getUsercode());
		    		insertQuery.setString(1, "");
		    		insertQuery.setString(2, "en");
		    		insertQuery.executeUpdate();		    		
		    		
		    		insertQuery = HibernateUtil.getSession().createSQLQuery("insert into userrole (rolecode,usercode) values (?,?)");
		    		insertQuery.setString(0, ldapLogin.getUserrole());
		    		insertQuery.setString(1, per.getPersonalcode());
		    		insertQuery.executeUpdate();
		    		
				    
				} catch (IdentifierException e) {
				    log.debug(e);
				} catch (NonUniqueResultException nure) {
				    log.debug(nure);
				    user = null;
				}
			    } else {
				user = null;
			    }
			}
		}
		
		if(userAccess == null && userFullname!=null){
			userAccess = new User_access();
			userAccess.setUsercode(username);
			userAccess.setName(userFullname);
			userAccess.setLocked(false);
			userAccess.setTries(0);
			userAccess.setLast_access(new Date());
			HibernateUtil.getSession().save(userAccess);
		}
		if ( userAccess != null && !userAccess.isLocked()){
			userAccess.setLast_access(new Date());
		}else{
			user = null;
		}
		
		if (user == null) {

			Params params = getParams("MAX_LOGIN_TRIES");
	    	int maxAccessTry = Integer.parseInt(params.getValue());
			
			if(userAccess != null && !userAccess.isLocked()){
				//Check if user has to be locked
				userAccess.setTries(userAccess.getTries()+1);
				if(maxAccessTry <= userAccess.getTries()){
					userAccess.setLocked(true);				
					CreateAuditLogmessage(null, remoteHost, "Login incorrecto, se bloquea usuario tras " + userAccess.getTries() + " intentos: " + username);
				}else{
					CreateAuditLogmessage(null, remoteHost, "Login incorrecto, " + userAccess.getTries() + "intentos: " + username);	
				}
			}else{
				//User is already locked
				CreateAuditLogmessage(null, remoteHost, "Usuario previamente bloqueado: " + username);
			}
		    
		} else {
			// Correct login, reset tries		
			userAccess.setTries(0);
		    CreateAuditLogmessage(user, remoteHost, "Login correcto");
		}
	
		HibernateUtil.commitTransaction();
	
		if (userAccess!=null && userAccess.isLocked()){
			throw new UsuarioNoActivoException();
		}
		
		return user;
    }
    
    public static User_access ObtainUser_access(Usuario user, String user_accesscode) {
    	return getUser_access(user_accesscode);    	
    }
    
    protected static User_access getUser_access(String personal) {
    	return (User_access) HibernateUtil.getSession().get(User_access.class, personal);    	
    }

    public static void HacerLogout(Usuario user, String remoteHost, String type) {
    	CreateAuditLogmessage(user, remoteHost, type);
    }
    
    public static User_access UnlockUser_access(Usuario user, String useraccess_code) throws InternalException {
    	HibernateUtil.beginTransaction();

    	User_access user_access = getUser_access(useraccess_code);
    	user_access.setLocked(false);
    	CreateStateChangeAuditmessage(user, user_access, "unlocked");
    	HibernateUtil.commitTransaction();

    	return user_access;
    }
    
    public static Pair<Integer, Pair<List<User_access>, Map<String, String[]>>> ObtainAllUser_accessAndOrderMap(
    	    Usuario user, ListConfigurator configurator) {

        /** 1. We create an Hibernate Criteria to obtain the desired values * */
    	Criteria crit = HibernateUtil.getSession().createCriteria(User_access.class);

    	// we only want to obtain the non deleted objects
    	crit.add(Expression.eq("deleted", Boolean.FALSE));
    	
    	filterByRole(user, crit);
    	
    	// we add the ListConfigurator to the criteria, obtaining the number of
    	// results without the pagination
    	int count = configurator.addCriterions(crit, true);

    	
    	/**
    	 * 2. We obtain the list form the DB and we return it with the number of
    	 * elements in the DB *
    	 */

    	List<User_access> users = (List<User_access>) crit.list();

    	crit.setFirstResult(0);
    	crit.setMaxResults(5000);

    	List<User_access> allUsers = (List<User_access>) crit.list();

    	Map<String, String[]> map = new HashMap<String, String[]>();

    	for (int i = 0; i < allUsers.size(); i++) {
    	    String next = i == allUsers.size() - 1 ? null : allUsers
    		    .get(i + 1).getCode();
    	    String previous = i == 0 ? null : allUsers.get(i - 1).getCode();
    	    map.put(allUsers.get(i).getCode(), new String[] { previous,
    		    next });
    	}

    	Pair<Integer, Pair<List<User_access>, Map<String, String[]>>> pair = new Pair<Integer, Pair<List<User_access>, Map<String, String[]>>>(
    		count, new Pair<List<User_access>, Map<String, String[]>>(
    				users, map));

    	return pair;
        }
    

    /**
     * This method terminates a active user.
     * 
     * @param user
     *            The user who executes this use case
     * @param debaja
     *            The user who will be terminated
     * @throws NoPermisosException
     * @throws InternalException
     * @throws UsuarioNoActivoException
     */
    public static void BajaUsuarioActivo(Usuario user, Usuario debaja)
	    throws UsuarioNoActivoException, NoPermisosException,
	    InternalException {

	HibernateUtil.beginTransaction();

	testIsActiveUser(user);
	testIsAdmin(user);

	try {
	    UserManagement.singleton().inactiveUser(user);
	} catch (Exception e) {
	    manageInternalException(e);
	}

	CreateAuditmessage(user, "auditmessage.deactivateUser",
		new String[] { debaja.getCode() }, MODIFY_MESSAGE);

	HibernateUtil.commitTransaction();
    }

    /**
     * This method clears all roles from a user.
     * 
     * @param admin
     *            The user who executes this use case
     * @param userTO
     *            Usuario to which the roles are deleted
     * @throws NoPermisosException
     * @throws InternalException
     */
    public static void QuitarRoles(Usuario admin, Usuario userTO)
	    throws NoPermisosException, InternalException {

	testIsAdmin(admin);

	HibernateUtil.beginTransaction();

	Usuario user = (Usuario) getUsuario(userTO.getUsuariocode());

	try {
	    UserManagement.singleton().removeAllRoles(user);
	} catch (Exception e) {
	    manageInternalException(e);
	}

	CreateAuditmessage(admin, "auditmessage.removeRolesFromUser",
		new String[] { userTO.getUsername() }, MODIFY_MESSAGE);

	HibernateUtil.commitTransaction();
    }

    /**
     * This method clears all roles from a user.
     * 
     * @param admin
     *            The user who executes this use case
     * @param userTO
     *            Usuario to which the roles are deleted
     * @throws NoPermisosException
     * @throws InternalException
     */
    public static void QuitarRol(Usuario admin, Usuario userTO, Role role)
	    throws NoPermisosException, InternalException {

	// testIsAdmin(admin);

	HibernateUtil.beginTransaction();

	Usuario user = (Usuario) getUsuario(userTO.getUsuariocode());

	Set<Role> roles = UseCase.ObtenerRolesUsuario(admin,
		userTO.getUsuariocode());

	try {
	    UserManagement.singleton().removeAllRoles(user);
	} catch (Exception e) {
	    manageInternalException(e);
	}

	for (Role it_role : roles) {
	    if (it_role.getId() != role.getId()) {
		try {
		    UserManagement.singleton().AddRoleToUser(role, user);
		} catch (Exception e) {
		    manageInternalException(e);
		}
	    }
	}

	CreateAuditmessage(admin, "auditmessage.roleRemovedFromUser",
		new String[] { role.getId(), userTO.getUsername() },
		MODIFY_MESSAGE);

	HibernateUtil.commitTransaction();
    }

    /**
     * This method add a role to a user.
     * 
     * @param admin
     *            The user who executes this use case
     * @param userTO
     *            The user to which the role is added
     * @param rolId
     *            The roll who will be added
     * @throws NoPermisosException
     * @throws InternalException
     */
    public static void AddRol(Usuario admin, Usuario userTO, String rolId)
	    throws NoPermisosException, InternalException {

	// testIsAdmin(admin);

	HibernateUtil.beginTransaction();

	Role rol = getRole(rolId);
	Usuario user = (Usuario) getUsuario(userTO.getUsuariocode());

	try {
	    UserManagement.singleton().AddRoleToUser(rol, user);
	} catch (Exception e) {
	    manageInternalException(e);
	}

	CreateAuditmessage(admin, "auditmessage.roleAdded", new String[] {
		rolId, userTO.getCode() }, MODIFY_MESSAGE);

	HibernateUtil.commitTransaction();
    }

    /**
     * This method obtains all languages of the application.
     * 
     * @return List of languages
     */
    public static List<Language> ObtenerIdiomas() {

	Criteria crit = HibernateUtil.getSession().createCriteria(
		Language.class);
	crit.setCacheable(true);

	List<Language> idiomas = (List<Language>) crit.list();

	return idiomas;
    }

    /**
     * This method obtains all the users of a role.
     * 
     * @param admin
     *            The user who executes this use case
     * @param userId
     *            The user who will be obained its roles
     * @return Roles of the desired user
     */
    public static Set<Role> ObtenerRolesUsuario(Usuario admin, String userId) {

	Usuario user = (Usuario) HibernateUtil.getSession().get(Usuario.class,
		userId);

	if (user == null) {
	    HashSet<Role> roles = new HashSet<Role>();
	    Role rol = new Role();
	    rol.setEntitycode("irbpeople_rw");
	    roles.add(rol);
	    return roles;
	}

	return user.getRoles();

    }

    /**
     * This method returns true if the user is a Basic user (no supervisor,
     * rrhh, admin)
     * 
     * @param admin
     *            The user who executes this use case
     * @param userId
     *            The user who will be obained its roles
     * @return true if it is a basic user
     */
    public static boolean isOnlyRoleBasic(Usuario admin, String userId)
	    throws InternalException {
	Set<Role> roles = ObtenerRolesUsuario(admin, userId);

	if (roles.contains(getRole(SUPERVISOR_ROLE_NAME))) {
	    return false;
	}

	if (roles.contains(getRole(HUMAN_RESOURCES_ROLE_NAME))) {
	    return false;
	}
	if (roles.contains(getRole(ADMINISTRATOR_ROLE_NAME))) {
	    return false;
	}
	return roles.contains(getRole(BASIC_ROLE_NAME));

    }

    /**
     * This method obtains all the rolls of the application.
     * 
     * @return Set of rolls.
     * @throws InternalException
     */
    public static List<Role> ObtenerRoles() throws InternalException {

	List<Role> roles = null;
	try {
	    roles = (ArrayList<Role>) UserManagement.singleton().getRoles();
	} catch (Exception e) {
	    manageInternalException(e);
	}

	for (int j = 0; j < roles.size(); j++) {
	    if (roles.get(j).getEntitycode().equalsIgnoreCase(GUEST_ROLE_NAME))
		roles.remove(j);
	}

	return roles;
    }

    /**
     * This method obtains a user given its code (no username)
     * 
     * @param admin
     *            The user who executes this use case
     * @param userId
     *            Code (no username) of the user to obtain
     * @return obtained user
     * @throws NoPermisosException
     * @throws InternalException
     */
    public static Usuario ObtenerUsuario(Usuario admin, String userId)
	    throws NoPermisosException, InternalException {

	Usuario user = getUsuario(userId);

	// testIsAdminOrItself(admin, user);
	return user;
    }

    /**
     * This method obtains a user given its personal
     * 
     * @param admin
     *            The user who executes this use case
     * @param personalcode
     *            personal code of the personal related to the disared user
     * @return obtained user
     * @throws NoPermisosException
     * @throws InternalException
     */
    protected static Usuario ObtenerUsuarioFromPersonal(Usuario admin,
	    String personalcode) throws NoPermisosException, InternalException {

	Criteria crit = HibernateUtil.getSession()
		.createCriteria(Usuario.class);
	crit.createCriteria("personal").add(Expression.idEq(personalcode));

	Usuario user = (Usuario) crit.uniqueResult();

	user.setEmail(UseCase.ObtenerEmailActualFromPersonal(personalcode));

	return (Usuario) crit.uniqueResult();

	// testIsAdminOrItself(admin, user);

    }

    public static String ObtenerEmailActualFromPersonal(String personalcode) {
	Criteria crit = HibernateUtil.getSession().createCriteria(
		Professional.class);
	crit.add(Expression.eq("current", true));
	crit.add(Expression.eq("deleted", false));
	crit.createCriteria("professional_personal").add(
		Expression.eq("personalcode", personalcode));

	try {
	    Professional currentProfessionalInfo = (Professional) crit
		    .uniqueResult();

	    if (currentProfessionalInfo == null) {
		// No hay ningï¿½n resultado. En este punto no es un error pero,
		// probablemente,
		// el mï¿½todo llamante deberï¿½a informar al usuario de que estï¿½
		// intentando enviar un email
		// a alguien que no tiene asignado ninguna direcciï¿½n de email.
		return null;
	    } else {
		return currentProfessionalInfo.getEmail();
	    }

	} catch (HibernateException he) {
	    // mas de un resultado. Esto es, segï¿½n la lï¿½gica de negocio, una
	    // inconsistencia en la BD.
	    log.error("El registro de personal con cï¿½digo: "
		    + personalcode
		    + " tiene mï¿½s de un registro marcado como actual en la tabla professional.");
	    return null;
	}

    }

    /**
     * This method obtains all system users, given a configurator
     * 
     * @param admin
     *            The user who executes this use case
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the system users which match
     *         the configurator (incluing pagination)
     * @throws NoPermisosException
     * @throws InternalException
     * @throws UsuarioNoActivoException
     */
    public static Pair<Integer, List<Usuario>> ObtenerUsuariosSistema(
	    Usuario admin, ListConfigurator configurator)
	    throws UsuarioNoActivoException, NoPermisosException,
	    InternalException {

	testIsActiveUser(admin);
	testIsAdmin(admin);

	ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

	Criteria crit = HibernateUtil.getSession()
		.createCriteria(Usuario.class);

	int count = configurator.addCriterions(crit);

	usuarios = (ArrayList<Usuario>) crit.list();

	Pair<Integer, List<Usuario>> pair = new Pair<Integer, List<Usuario>>(
		count, usuarios);

	return pair;
    }

    /**
     * This method obtains all active users, given a configurator
     * 
     * @param admin
     *            The user who executes this use case
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the active users which match
     *         the configurator (incluing pagination)
     * @throws NoPermisosException
     * @throws InternalException
     * @throws UsuarioNoActivoException
     */
    public static Pair<Integer, List<Usuario>> ObtenerUsuariosActivos(
	    Usuario admin, ListConfigurator configurator)
	    throws UsuarioNoActivoException, NoPermisosException,
	    InternalException {

	// testIsActiveUser(admin);
	// testIsAdmin(admin);

	Pair pair = null;
	try {
	    pair = UserManagement.singleton().getActiveUsersList(configurator,
		    Usuario.class);
	} catch (Exception e) {
	    manageInternalException(e);
	}
	return pair;
    }

    /**
     * This method obtains all pending users, given a configurator
     * 
     * @param admin
     *            The user who executes this use case
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the pending users which match
     *         the configurator (incluing pagination)
     * @throws NoPermisosException
     * @throws InternalException
     * @throws UsuarioNoActivoException
     */
    public static Pair<Integer, List<Usuario>> ObtenerUsuariosPendientes(
	    Usuario admin, ListConfigurator configurator)
	    throws UsuarioNoActivoException, NoPermisosException,
	    InternalException {

	testIsActiveUser(admin);
	testIsAdmin(admin);

	Pair pair = null;
	try {
	    pair = UserManagement.singleton().getInactiveUsersList(
		    configurator, Usuario.class);
	} catch (Exception e) {
	    manageInternalException(e);
	}
	return pair;
    }

    /**
     * This method obtains all the marked as deleted users, given a configurator
     * 
     * @param admin
     *            The user who executes this use case
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the marked as deleted users
     *         which match the configurator (incluing pagination)
     * @throws NoPermisosException
     * @throws InternalException
     * @throws UsuarioNoActivoException
     */
    public static Pair<Integer, List<Usuario>> ObtenerUsuariosBorrados(
	    Usuario admin, ListConfigurator configurator)
	    throws UsuarioNoActivoException, NoPermisosException,
	    InternalException {

	testIsActiveUser(admin);
	testIsAdmin(admin);

	Pair pair = null;
	try {
	    pair = UserManagement.singleton().getDeletedUsers(configurator,
		    Usuario.class);
	} catch (Exception e) {
	    manageInternalException(e);
	}
	return pair;
    }

    // private getter methods

    /**
     * Returns the Research_group with the given key. This method is used
     * internally to get objects form the database.
     * 
     * @param research_groupcode
     *            code of the Research_group
     * @return Research_group with the given code
     */
    protected static Research_group getResearch_group(String research_groupcode) {
	Research_group research_group = (Research_group) HibernateUtil
		.getSession().get(Research_group.class, research_groupcode);
	return research_group;
    }

    /**
     * Returns the Professional with the given key. This method is used
     * internally to get objects form the database.
     * 
     * @param professionalcode
     *            code of the Professional
     * @return Professional with the given code
     */
    protected static Professional getProfessional(String professionalcode) {
	Professional professional = (Professional) HibernateUtil.getSession()
		.get(Professional.class, professionalcode);
	return professional;
    }

    /**
     * Returns the Location with the given key. This method is used internally
     * to get objects form the database.
     * 
     * @param locationcode
     *            code of the Location
     * @return Location with the given code
     */
    protected static Location getLocation(String locationcode) {
	Location location = (Location) HibernateUtil.getSession().get(
		Location.class, locationcode);
	return location;
    }

    /**
     * Returns the Type_of_education with the given key. This method is used
     * internally to get objects form the database.
     * 
     * @param type_of_educationcode
     *            code of the Type_of_education
     * @return Type_of_education with the given code
     */
    protected static Type_of_education getType_of_education(
	    String type_of_educationcode) {
	Type_of_education type_of_education = (Type_of_education) HibernateUtil
		.getSession().get(Type_of_education.class,
			type_of_educationcode);
	return type_of_education;
    }

    /**
     * Returns the Benefits with the given key. This method is used internally
     * to get objects form the database.
     * 
     * @param benefitscode
     *            code of the Benefits
     * @return Benefits with the given code
     */
    protected static Benefits getBenefits(String benefitscode) {
	Benefits benefits = (Benefits) HibernateUtil.getSession().get(
		Benefits.class, benefitscode);
	return benefits;
    }

    /**
     * Returns the Type_of_benefit with the given key. This method is used
     * internally to get objects form the database.
     * 
     * @param type_of_benefitcode
     *            code of the Type_of_benefit
     * @return Type_of_benefit with the given code
     */
    protected static Type_of_benefit getType_of_benefit(
	    String type_of_benefitcode) {
	Type_of_benefit type_of_benefit = (Type_of_benefit) HibernateUtil
		.getSession().get(Type_of_benefit.class, type_of_benefitcode);
	return type_of_benefit;
    }

    /**
     * Returns the Grant with the given key. This method is used internally to
     * get objects form the database.
     * 
     * @param grantcode
     *            code of the Grant
     * @return Grant with the given code
     */
    protected static Grant getGrant(String grantcode) {
	Grant grant = (Grant) HibernateUtil.getSession().get(Grant.class,
		grantcode);
	return grant;
    }

    /**
     * Returns the Education with the given key. This method is used internally
     * to get objects form the database.
     * 
     * @param educationcode
     *            code of the Education
     * @return Education with the given code
     */
    protected static Education getEducation(String educationcode) {
	Education education = (Education) HibernateUtil.getSession().get(
		Education.class, educationcode);
	return education;
    }

    /**
     * Returns the Marital_status with the given key. This method is used
     * internally to get objects form the database.
     * 
     * @param marital_statuscode
     *            code of the Marital_status
     * @return Marital_status with the given code
     */
    protected static Marital_status getMarital_status(String marital_statuscode) {
	Marital_status marital_status = (Marital_status) HibernateUtil
		.getSession().get(Marital_status.class, marital_statuscode);
	return marital_status;
    }

    /**
     * Returns the Nationality with the given key. This method is used
     * internally to get objects form the database.
     * 
     * @param nationalitycode
     *            code of the Nationality
     * @return Nationality with the given code
     */
    protected static Nationality getNationality(String nationalitycode) {
	Nationality nationality = (Nationality) HibernateUtil.getSession().get(
		Nationality.class, nationalitycode);
	return nationality;
    }

    /**
     * Returns the Payroll_institution with the given key. This method is used
     * internally to get objects form the database.
     * 
     * @param payroll_institutioncode
     *            code of the Payroll_institution
     * @return Payroll_institution with the given code
     */
    protected static Payroll_institution getPayroll_institution(
	    String payroll_institutioncode) {
	Payroll_institution payroll_institution = (Payroll_institution) HibernateUtil
		.getSession().get(Payroll_institution.class,
			payroll_institutioncode);
	return payroll_institution;
    }

    /**
     * Returns the Child with the given key. This method is used internally to
     * get objects form the database.
     * 
     * @param childcode
     *            code of the Child
     * @return Child with the given code
     */
    protected static Child getChild(String childcode) {
	Child child = (Child) HibernateUtil.getSession().get(Child.class,
		childcode);
	return child;
    }

    protected static Personal_comment getPersonal_comment(
	    String personal_commentcode) {
	Personal_comment personal_comment = (Personal_comment) HibernateUtil
		.getSession().get(Personal_comment.class, personal_commentcode);
	return personal_comment;
    }

    /**
     * Returns the Work_experience with the given key. This method is used
     * internally to get objects form the database.
     * 
     * @param work_experiencecode
     *            code of the Work_experience
     * @return Work_experience with the given code
     */
    protected static Work_experience getWork_experience(
	    String work_experiencecode) {
	Work_experience work_experience = (Work_experience) HibernateUtil
		.getSession().get(Work_experience.class, work_experiencecode);
	return work_experience;
    }

    /**
     * Returns the Type_of_grant with the given key. This method is used
     * internally to get objects form the database.
     * 
     * @param type_of_grantcode
     *            code of the Type_of_grant
     * @return Type_of_grant with the given code
     */
    protected static Type_of_grant getType_of_grant(String type_of_grantcode) {
	Type_of_grant type_of_grant = (Type_of_grant) HibernateUtil
		.getSession().get(Type_of_grant.class, type_of_grantcode);
	return type_of_grant;
    }

    /**
     * Returns the Payment with the given key. This method is used internally to
     * get objects form the database.
     * 
     * @param paymentcode
     *            code of the Payment
     * @return Payment with the given code
     */
    protected static Payment getPayment(String paymentcode) {
	Payment payment = (Payment) HibernateUtil.getSession().get(
		Payment.class, paymentcode);
	return payment;
    }

    /**
     * Returns the Organization_unit with the given key. This method is used
     * internally to get objects form the database.
     * 
     * @param organization_unitcode
     *            code of the Organization_unit
     * @return Organization_unit with the given code
     */
    protected static Organization_unit getOrganization_unit(
	    String organization_unitcode) {
	Organization_unit organization_unit = (Organization_unit) HibernateUtil
		.getSession().get(Organization_unit.class,
			organization_unitcode);
	return organization_unit;
    }

    /**
     * Returns the Working_hours with the given key. This method is used
     * internally to get objects form the database.
     * 
     * @param working_hourscode
     *            code of the Working_hours
     * @return Working_hours with the given code
     */
    protected static Working_hours getWorking_hours(String working_hourscode) {
	Working_hours working_hours = (Working_hours) HibernateUtil
		.getSession().get(Working_hours.class, working_hourscode);
	return working_hours;
    }

    /**
     * Returns the Auditmessage with the given key. This method is used
     * internally to get objects form the database.
     * 
     * @param auditmessagecode
     *            code of the Auditmessage
     * @return Auditmessage with the given code
     */
    protected static Auditmessage getAuditmessage(String auditmessagecode) {
	Auditmessage auditmessage = (Auditmessage) HibernateUtil.getSession()
		.get(Auditmessage.class, auditmessagecode);
	return auditmessage;
    }

    /**
     * Returns the Auditmessagetype with the given key. This method is used
     * internally to get objects form the database.
     * 
     * @param auditmessagetypecode
     *            code of the Auditmessagetype
     * @return Auditmessagetype with the given code
     */
    protected static Auditmessagetype getAuditmessagetype(
	    String auditmessagetypecode) {
	Auditmessagetype auditmessagetype = (Auditmessagetype) HibernateUtil
		.getSession().get(Auditmessagetype.class, auditmessagetypecode);
	return auditmessagetype;
    }

    /**
     * Returns the Bank with the given key. This method is used internally to
     * get objects form the database.
     * 
     * @param bankcode
     *            code of the Bank
     * @return Bank with the given code
     */
    protected static Bank getBank(String bankcode) {
	Bank bank = (Bank) HibernateUtil.getSession().get(Bank.class, bankcode);
	return bank;
    }

    /**
     * Returns the Compensation with the given key. This method is used
     * internally to get objects form the database.
     * 
     * @param compensationcode
     *            code of the Compensation
     * @return Compensation with the given code
     */
    protected static Compensation getCompensation(String compensationcode) {
	Compensation compensation = (Compensation) HibernateUtil.getSession()
		.get(Compensation.class, compensationcode);
	return compensation;
    }

    /**
     * Returns the Type_of_institution with the given key. This method is used
     * internally to get objects form the database.
     * 
     * @param type_of_institutioncode
     *            code of the Type_of_institution
     * @return Type_of_institution with the given code
     */
    protected static Type_of_institution getType_of_institution(
	    String type_of_institutioncode) {
	Type_of_institution type_of_institution = (Type_of_institution) HibernateUtil
		.getSession().get(Type_of_institution.class,
			type_of_institutioncode);
	return type_of_institution;
    }
    
    /**
     * Returns the Type_of_study with the given key. This method is used
     * internally to get objects form the database.
     * 
     * @param type_of_studycode
     *            code of the Type_of_study
     * @return Type_of_study with the given code
     */
    protected static Type_of_study getType_of_study(
	    String type_of_studycode) {
	Type_of_study type_of_study = (Type_of_study) HibernateUtil
		.getSession().get(Type_of_study.class,
			type_of_studycode);
	return type_of_study;
    }

    /**
     * Returns the Alumni Personal with the given key. This method is used internally
     * to get objects form the database.
     * 
     * @param alumni_personalcode
     *            code of the Alumni_personal
     * @return Alumni_personal with the given code
     */
    protected static Alumni_personal getAlumni_personal(String alumni_personalcode) {
    	Alumni_personal alumni_personal = (Alumni_personal) HibernateUtil.getSession().get(
    			Alumni_personal.class, alumni_personalcode);
    	return alumni_personal;
    }
    		
	 /**
     * This method creates a Alumni_personal.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOAlumni_personal
     *            Alumni_personal data transfer object (DTO) with the values of the new
     *            instance.
     * @return the new alumni_personal created with this Use Case
     * @throws InternalException
     * @throws ValidationFailedException
     */
    public static Alumni_personal CreateAlumni_personal(Usuario user, Alumni_personal TOAlumni_personal)
	    throws InternalException, ValidationFailedException {

	try {
	    /** 1. We begin the DB transaction. * */

	    HibernateUtil.beginTransaction();

	    /**
	     * 2. For each association from the TOAlumni_personal that are filled in
	     * the DTO we put the real objects from the DB. *
	     */
	    boolean _genderIsDefined = false;
	    if (TOAlumni_personal.getGender() != null
		    && TOAlumni_personal.getGender().getGendercode() != null
		    && !TOAlumni_personal.getGender().getGendercode().equals("")) {

	    	_genderIsDefined = true;

	    	TOAlumni_personal.setGender(getGender(TOAlumni_personal.getGender()
			.getGendercode()));
	    }
	    
	    boolean _nationalityIsDefined = false;
	    if (TOAlumni_personal.getNationality() != null
		    && TOAlumni_personal.getNationality().getNationalitycode() != null
		    && !TOAlumni_personal.getNationality().getNationalitycode().equals("")) {

	    	_nationalityIsDefined = true;

	    	TOAlumni_personal.setNationality(getNationality(TOAlumni_personal.getNationality()
			.getNationalitycode()));
	    }

	    boolean _nationality_2IsDefined = false;
	    if (TOAlumni_personal.getNationality_2() != null
	    		&& TOAlumni_personal.getNationality_2().getNationalitycode() != null
	    		&& !TOAlumni_personal.getNationality_2().getNationalitycode().equals("")) {
	    	
	    	_nationality_2IsDefined = true;
	    	
	    	TOAlumni_personal.setNationality_2(getNationality(TOAlumni_personal.getNationality_2()
	    			.getNationalitycode()));
	    }
	    
	    boolean _alumni_titlesIsDefined = false;
	    if (TOAlumni_personal.getTitles() != null
	    		&& TOAlumni_personal.getTitles().getAlumni_titlescode() != null
	    		&& !TOAlumni_personal.getTitles().getAlumni_titlescode().equals("")) {
	    	
	    	_alumni_titlesIsDefined = true;
	    	
	    	TOAlumni_personal.setTitles(getAlumni_titles((TOAlumni_personal.getTitles().getAlumni_titlescode())));
	    }
	    

	    /** 3. We create the new instance * */
	    Alumni_personal alumni_personal = new Alumni_personal();

	    /**
	     * 4. We set all the simple attributes (no associations) to the new
	     * instance *
	     */

	    alumni_personal.setExternal(TOAlumni_personal.isExternal());
	    alumni_personal.setFirstname(TOAlumni_personal.getFirstname());
	    alumni_personal.setSurname(TOAlumni_personal.getSurname());
	    alumni_personal.setIrb_surname(TOAlumni_personal.getIrb_surname());
	    alumni_personal.setBirth(TOAlumni_personal.getBirth());
	    alumni_personal.setEmail(TOAlumni_personal.getEmail());
	    alumni_personal.setUrl(TOAlumni_personal.getUrl());
	    alumni_personal.setFacebook(TOAlumni_personal.getFacebook());
	    alumni_personal.setLinkedin(TOAlumni_personal.getLinkedin());
	    alumni_personal.setTwitter(TOAlumni_personal.getTwitter());
	    alumni_personal.setKeywords(TOAlumni_personal.getKeywords());
	    alumni_personal.setBiography(TOAlumni_personal.getBiography());
	    alumni_personal.setAwards(TOAlumni_personal.getAwards());
	    alumni_personal.setORCIDID(TOAlumni_personal.getORCIDID());
	    alumni_personal.setResearcherid(TOAlumni_personal.getResearcherid());
	    alumni_personal.setPubmedid(TOAlumni_personal.getPubmedid());
	    alumni_personal.setVerified(TOAlumni_personal.isVerified());
	    alumni_personal.setShow_data(TOAlumni_personal.isShow_data());
	    
	    alumni_personal.setRemarks(TOAlumni_personal.getRemarks());
	    alumni_personal.setSkype(TOAlumni_personal.getSkype());
	    alumni_personal.setCellphone(TOAlumni_personal.getCellphone());
	    alumni_personal.setDeceased(TOAlumni_personal.isDeceased());
	    alumni_personal.setCommunications_wanted(TOAlumni_personal.isCommunications_wanted());
	    

	   /** 5. We set the code to the new instance * */
	    if (TOAlumni_personal.getCode() != null
		    && !TOAlumni_personal.getCode().equals("")) {
			/**
			 * 5.1 If the TO contains a code, we set it: we are creating a
			 * alumni_personal for a user *
			 */
			alumni_personal.setCode(TOAlumni_personal.getCode());
	    } else {
			try {
			    IdentifyManager_Plain im = IdentifyManager_Plain
				    .singleton();
			    
			    alumni_personal.setAlumni_personalcode(im.getId(TOAlumni_personal));
			} catch (identifyException ie) {
	
			    log.error(
				    "Error en asignaciï¿½n de nuevo id en CreateAlumni_personal",
				    ie);
			    throw new Error(ie.getMessage());
			}
	    }
	    
	    /** 6. We save the new instance to the DB* */
	    HibernateUtil.getSession().save(alumni_personal);

	    
	    
	    
	    /**
	     * We associate the current object to the other objects (only in
	     * case that the associations where defined in the DTO) *
	     */

	    if (_genderIsDefined) {
	    	if (TOAlumni_personal.getGender() != null) {
	    		TOAlumni_personal.getGender().addIalumni_personal(alumni_personal);
	    	}
	    	alumni_personal.setGender(TOAlumni_personal.getGender());
	    }
	    if (_nationalityIsDefined) {
	    	if (TOAlumni_personal.getNationality() != null) {
	    		TOAlumni_personal.getNationality().addIalumni_personal(alumni_personal);
	    	}
	    	alumni_personal.setNationality(TOAlumni_personal.getNationality());
	    }
	    if (_nationality_2IsDefined) {
	    	if (TOAlumni_personal.getNationality_2() != null) {
	    		TOAlumni_personal.getNationality_2().addIalumni_personal_2(alumni_personal);
	    	}
	    	alumni_personal.setNationality_2(TOAlumni_personal.getNationality_2());
	    }
	    if (_alumni_titlesIsDefined) {
	    	if (TOAlumni_personal.getTitles() != null) {
	    		TOAlumni_personal.getTitles().addIialumni_personal(alumni_personal);
	    	}
	    	alumni_personal.setTitles(TOAlumni_personal.getTitles());
	    }
	    
	    

	    /** 7. We create an Audit message * */
	    CreateCreationAuditmessage(user, alumni_personal);

	    /** 8. We commit the DB transaction and return the new instance * */
	    HibernateUtil.commitTransaction();

	    return alumni_personal;
	} catch (RuntimeException e) {
		throw e;
	}
    }

    /**
     * This method modifies a alumni_personal.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOAlumni_personal
     *            Alumni_personal data transfer object (DTO) with the values of the
     *            modified instance. The code of this attribute indicates which
     *            alumni_personal will be modified.
     * @return the modified alumni_personal
     * @throws InternalException
     * @throws ValidationFailedException
     */
    public static Alumni_personal UpdateAlumni_personal(Usuario user, Alumni_personal TOAlumni_personal)
	    throws InternalException, ValidationFailedException {

	try {

	    /** 1. We begin the DB transaction. * */
	    HibernateUtil.beginTransaction();

	    /**
	     * 2. For each association from the TOAlumni_personal that are filled in
	     * the DTO we put the real objects from the DB. *
	     */

	    /**
	     * 2. For each association from the TOAlumni_personal that are filled in
	     * the DTO we put the real objects from the DB. *
	     */
	    boolean _genderIsDefined = false;
	    if (TOAlumni_personal.getGender() != null
		    && TOAlumni_personal.getGender().getGendercode() != null
		    && !TOAlumni_personal.getGender().getGendercode().equals("")) {

	    	_genderIsDefined = true;

	    	TOAlumni_personal.setGender(getGender(TOAlumni_personal.getGender()
			.getGendercode()));
	    }
	    
	    boolean _nationalityIsDefined = false;
	    if (TOAlumni_personal.getNationality() != null
		    && TOAlumni_personal.getNationality().getNationalitycode() != null
		    && !TOAlumni_personal.getNationality().getNationalitycode().equals("")) {

	    	_nationalityIsDefined = true;

	    	TOAlumni_personal.setNationality(getNationality(TOAlumni_personal.getNationality()
			.getNationalitycode()));
	    }

	    boolean _nationality_2IsDefined = false;
	    if (TOAlumni_personal.getNationality_2() != null
	    		&& TOAlumni_personal.getNationality_2().getNationalitycode() != null
	    		&& !TOAlumni_personal.getNationality_2().getNationalitycode().equals("")) {
	    	
	    	_nationality_2IsDefined = true;
	    	
	    	TOAlumni_personal.setNationality_2(getNationality(TOAlumni_personal.getNationality_2()
	    			.getNationalitycode()));
	    }
	    
	    boolean _alumni_titlesIsDefined = false;
	    if (TOAlumni_personal.getTitles() != null
	    		&& TOAlumni_personal.getTitles().getAlumni_titlescode() != null
	    		&& !TOAlumni_personal.getTitles().getAlumni_titlescode().equals("")) {
	    	
	    	_alumni_titlesIsDefined = true;
	    	
	    	TOAlumni_personal.setTitles(getAlumni_titles((TOAlumni_personal.getTitles().getAlumni_titlescode())));
	    }

	    /** 3. We obtain form the DB the instance to modify * */
	    Alumni_personal alumni_personal = getAlumni_personal(TOAlumni_personal.getAlumni_personalcode());

	    /*****/

	    /*****/

	    /**
	     * 4. We set all the simple attributes (no associations) to the
	     * instance *
	     */

	    alumni_personal.setExternal(TOAlumni_personal.isExternal());
	    alumni_personal.setFirstname(TOAlumni_personal.getFirstname());
	    alumni_personal.setSurname(TOAlumni_personal.getSurname());
	    alumni_personal.setIrb_surname(TOAlumni_personal.getIrb_surname());
	    alumni_personal.setBirth(TOAlumni_personal.getBirth());
	    alumni_personal.setEmail(TOAlumni_personal.getEmail());
	    alumni_personal.setUrl(TOAlumni_personal.getUrl());
	    alumni_personal.setFacebook(TOAlumni_personal.getFacebook());
	    alumni_personal.setLinkedin(TOAlumni_personal.getLinkedin());
	    alumni_personal.setTwitter(TOAlumni_personal.getTwitter());
	    alumni_personal.setKeywords(TOAlumni_personal.getKeywords());
	    alumni_personal.setBiography(TOAlumni_personal.getBiography());
	    alumni_personal.setAwards(TOAlumni_personal.getAwards());
	    alumni_personal.setORCIDID(TOAlumni_personal.getORCIDID());
	    alumni_personal.setResearcherid(TOAlumni_personal.getResearcherid());
	    alumni_personal.setPubmedid(TOAlumni_personal.getPubmedid());
	    alumni_personal.setVerified(TOAlumni_personal.isVerified());
	    alumni_personal.setShow_data(TOAlumni_personal.isShow_data());
	    
	    alumni_personal.setRemarks(TOAlumni_personal.getRemarks());
	    alumni_personal.setSkype(TOAlumni_personal.getSkype());
	    alumni_personal.setCellphone(TOAlumni_personal.getCellphone());
	    alumni_personal.setDeceased(TOAlumni_personal.isDeceased());
	    alumni_personal.setCommunications_wanted(TOAlumni_personal.isCommunications_wanted());

	    
	    /**
	     * 5. We set the DTO version to the modified object and we update it
	     * with the new values in the DB. We evict and update the instance
	     * to prevent concurrent modification *
	     */
	    HibernateUtil.getSession().evict(alumni_personal);
	    alumni_personal.setVersion(TOAlumni_personal.getVersion());
	    HibernateUtil.getSession().update(alumni_personal);

	    /**
	     * We associate/disassociate the current object to the other objects
	     * (only in case that the associations where defined in the DTO) *
	     */
	    
	    if (_genderIsDefined) {
	    	if (alumni_personal.getGender() != null) {
	    		alumni_personal.getGender().removeIalumni_personal(alumni_personal);
	    	}
	    	if (TOAlumni_personal.getGender() != null) {
	    		TOAlumni_personal.getGender().addIalumni_personal(alumni_personal);
	    	}
	    	alumni_personal.setGender(TOAlumni_personal.getGender());
	    }
	    if (_nationalityIsDefined) {
	    	if (alumni_personal.getNationality() != null) {
	    		alumni_personal.getNationality().removeIalumni_personal(alumni_personal);
	    	}
	    	if (TOAlumni_personal.getNationality() != null) {
	    		TOAlumni_personal.getNationality().addIalumni_personal(alumni_personal);
	    	}
	    	alumni_personal.setNationality(TOAlumni_personal.getNationality());
	    }
	    if (_nationality_2IsDefined) {
	    	if (alumni_personal.getNationality_2() != null) {
	    		alumni_personal.getNationality_2().removeIalumni_personal_2(alumni_personal);
	    	}
	    	if (TOAlumni_personal.getNationality_2() != null) {
	    		TOAlumni_personal.getNationality_2().addIalumni_personal_2(alumni_personal);
	    	}
	    	alumni_personal.setNationality_2(TOAlumni_personal.getNationality_2());
	    }
	    if (_alumni_titlesIsDefined) {
	    	if (alumni_personal.getTitles() != null) {
	    		alumni_personal.getTitles().removeIalumni_personal(alumni_personal);
	    	}
	    	if (TOAlumni_personal.getTitles() != null) {
	    		TOAlumni_personal.getTitles().addIialumni_personal(alumni_personal);
	    	}
	    	alumni_personal.setTitles(TOAlumni_personal.getTitles());
	    }

	    alumni_personal.clearIalumni_communications();
	    alumni_personal.setIalumni_communications(TOAlumni_personal.getIalumni_communications());
	    
	    /** 6. We create an Audit message * */	    
	    CreateModificationAuditmessage(user, alumni_personal);

	    /** 7. We commit the DB transaction and return the new instance * */
	    HibernateUtil.commitTransaction();

	    return alumni_personal;

	} catch (RuntimeException e) {
	    throw e;	    
	}

    }

    /**
     * This method removes a alumni_personal.
     * 
     * @param user
     *            The user who executes this use case
     * @param alumni_personalcode
     *            Code of the alumni_personal to be removed
     * @throws InternalException
     * @throws NoPermisosException
     * @throws UsuarioNoActivoException
     * @throws ValidationFailedException
     */
    public static void RemoveAlumni_personal(Usuario user, String alumni_personalcode)
	    throws NoPermisosException, InternalException,
	    UsuarioNoActivoException, ValidationFailedException {

		/** 1. We begin the DB transaction. * */
		HibernateUtil.beginTransaction();
	
		/** 2. We obtain the object to delete form the DB. * */
		Alumni_personal alumni_personal = getAlumni_personal(alumni_personalcode);
	
		/** 3. We mark it as deleted. * */
		alumni_personal.setDeleted(Boolean.TRUE);
		
		/** 4. We create an Audit message * */
		CreateRemovealAuditmessage(user, alumni_personal);
	
		/** 5. We commit the DB transaction. * */
		HibernateUtil.commitTransaction();
    }

    /**
     * This method obtains one instance of alumni_personal given its code.
     * 
     * @param user
     *            The user who executes this use case
     * @param alumni_personalcode
     *            Code of the alumni_personal to be obtained
     * @return Alumni_personal with the given code.
     */
    public static Alumni_personal ObtainAlumni_personal(Usuario user, String alumni_personalcode) {

		/**
		 * 1. We obtain the object from the DB using the private getter and we
		 * return it. *
		 */
	
		Alumni_personal alumni_personal = getAlumni_personal(alumni_personalcode);
		return alumni_personal;
    }

    /**
     * This method obtains all instances of Alumni_personal, given a list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     */
    public static Pair<Integer, List<Alumni_personal>> ObtainAllAlumni_personal(Usuario user,
	    ListConfigurator configurator) {

		/** 1. We create an Hibernate Criteria to obtain the desired values * */
		Criteria crit = HibernateUtil.getSession().createCriteria(
			Alumni_personal.class);
	
		// we only want to obtain the non deleted objects
		crit.add(Expression.eq("deleted", Boolean.FALSE));	
		
		// we add the ListConfigurator to the criteria, obtaining the number of
		// results without the pagination
		int count = configurator.addCriterions(crit);
	
		/**
		 * 2. We obtain the list form the DB and we return it with the number of
		 * elements in the DB *
		 */
	
		List<Alumni_personal> alumni_personals = (List<Alumni_personal>) crit.list();
	
		Pair<Integer, List<Alumni_personal>> pair = new Pair<Integer, List<Alumni_personal>>(
			count, alumni_personals);
	
		return pair;
    }
    
    /**
     * This method obtains all instances of Alumni_personal, not external, given a list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     */
    public static Pair<Integer, List<Alumni_personal>> ObtainNotExternalAlumni_personal(Usuario user,
	    ListConfigurator configurator) {

		/** 1. We create an Hibernate Criteria to obtain the desired values * */
		Criteria crit = HibernateUtil.getSession().createCriteria(
			Alumni_personal.class);
	
		// we only want to obtain the non deleted objects
		crit.add(Expression.eq("deleted", Boolean.FALSE));	
		crit.add(Expression.eq("external", Boolean.FALSE));
		
		// we add the ListConfigurator to the criteria, obtaining the number of
		// results without the pagination
		int count = configurator.addCriterions(crit);
	
		/**
		 * 2. We obtain the list form the DB and we return it with the number of
		 * elements in the DB *
		 */
	
		List<Alumni_personal> alumni_personals = (List<Alumni_personal>) crit.list();
	
		Pair<Integer, List<Alumni_personal>> pair = new Pair<Integer, List<Alumni_personal>>(
			count, alumni_personals);
	
		return pair;
    }

    public static Pair<Integer, Pair<List<Alumni_personal>, Map<String, String[]>>> ObtainValidatedAlumni_personalAndOrderMap(
    	    Usuario user, ListConfigurator configurator) {
    	Criteria crit = HibernateUtil.getSession().createCriteria(Alumni_personal.class);
    	crit.add(Expression.eq("verified", Boolean.TRUE));
    	return ObtainAllAlumni_personalAndOrderMap(user, configurator, crit);
    }

    public static Pair<Integer, Pair<List<Alumni_personal>, Map<String, String[]>>> ObtainNotValidatedAlumni_personalAndOrderMap(
    		Usuario user, ListConfigurator configurator) {
    	Criteria crit = HibernateUtil.getSession().createCriteria(Alumni_personal.class);
    	crit.add(Expression.eq("verified", Boolean.FALSE));
    	crit.add(Expression.eq("external", Boolean.TRUE));
    	return ObtainAllAlumni_personalAndOrderMap(user, configurator, crit);
    }
    
    public static Pair<Integer, Pair<List<Alumni_personal>, Map<String, String[]>>> ObtainAllAlumni_personalAndOrderMap(
    	    Usuario user, ListConfigurator configurator) {
    	Criteria crit = HibernateUtil.getSession().createCriteria(Alumni_personal.class);
    	return ObtainAllAlumni_personalAndOrderMap(user, configurator, crit);
    }
    
    public static Pair<Integer, Pair<List<Alumni_personal>, Map<String, String[]>>> ObtainAllAlumni_personalAndOrderMap(
	    Usuario user, ListConfigurator configurator, Criteria crit) {

    	log.debug("ObtainAllAlumni_personalAndOrderMap");
    	
		// we only want to obtain the non deleted objects
		crit.add(Expression.eq("deleted", Boolean.FALSE));	
		filterByRole(user, crit);
		
		// we add the ListConfigurator to the criteria, obtaining the number of
		// results without the pagination
		int count = configurator.addCriterions(crit, true);
	
		
		/**
		 * 2. We obtain the list form the DB and we return it with the number of
		 * elements in the DB *
		 */
	
		// ScrollableResults scroll = crit.scroll(ScrollMode.SCROLL_SENSITIVE);
	
		List<Alumni_personal> alumni_personals = (List<Alumni_personal>) crit.list();
	
		crit.setFirstResult(0);
		crit.setMaxResults(5000);
	
		List<Alumni_personal> allAlumni_personals = (List<Alumni_personal>) crit.list();
	
		Map<String, String[]> map = new HashMap<String, String[]>();
	
		for (int i = 0; i < allAlumni_personals.size(); i++) {
		    String next = i == allAlumni_personals.size() - 1 ? null : allAlumni_personals
			    .get(i + 1).getCode();
		    String previous = i == 0 ? null : allAlumni_personals.get(i - 1).getCode();
		    map.put(allAlumni_personals.get(i).getCode(), new String[] { previous,
			    next });
		}
	
		Pair<Integer, Pair<List<Alumni_personal>, Map<String, String[]>>> pair = new Pair<Integer, Pair<List<Alumni_personal>, Map<String, String[]>>>(
			count, new Pair<List<Alumni_personal>, Map<String, String[]>>(
				alumni_personals, map));
	
		return pair;
    }    		
    
    
    /**
     * Returns the Alumni_titles with the given key. This method is used
     * internally to get objects form the database.
     * 
     * @param titlescode
     *            code of the Alumni titles
     * @return Alumni_titles with the given code
     */
    protected static Alumni_titles getAlumni_titles(
    		String titlescode) {
    	Alumni_titles alumni_titles = (Alumni_titles) HibernateUtil
    			.getSession().get(Alumni_titles.class,
    					titlescode);
    	return alumni_titles;
    }
    
    /**
     * Returns the Alumni_external_job_positions with the given key. This method is used
     * internally to get objects form the database.
     * 
     * @param external_job_positionscode
     *            code of the Alumni external_job_positions
     * @return Alumni_external_job_positions with the given code
     */
    protected static Alumni_external_job_positions getAlumni_external_job_positions(
    		String external_job_positionscode) {
    	Alumni_external_job_positions alumni_external_job_positions = (Alumni_external_job_positions) HibernateUtil
    			.getSession().get(Alumni_external_job_positions.class,
    					external_job_positionscode);
    	return alumni_external_job_positions;
    }
    
    /**
     * Returns the Alumni_irb_job_positions with the given key. This method is used
     * internally to get objects form the database.
     * 
     * @param irb_job_positionscode
     *            code of the Alumni irb_job_positions
     * @return Alumni_irb_job_positions with the given code
     */
    protected static Alumni_irb_job_positions getAlumni_irb_job_positions(
    		String irb_job_positionscode) {
    	Alumni_irb_job_positions alumni_irb_job_positions = (Alumni_irb_job_positions) HibernateUtil
    			.getSession().get(Alumni_irb_job_positions.class,
    					irb_job_positionscode);
    	return alumni_irb_job_positions;
    }
    
    /**
     * Returns the Alumni_job_position_types with the given key. This method is used
     * internally to get objects form the database.
     * 
     * @param code
     *            code of the Alumni titles
     * @return Alumni_job_position_types with the given code
     */
    protected static Alumni_job_position_types getAlumni_job_position_types(
    		String code) {
    	Alumni_job_position_types alumni_job_position_types = (Alumni_job_position_types) HibernateUtil
    			.getSession().get(Alumni_job_position_types.class,
    					code);
    	return alumni_job_position_types;
    }
    
    /**
     * Returns the Alumni_communications with the given key. This method is used
     * internally to get objects form the database.
     * 
     * @param alumnicommuniations_code
     *            code of the Alumni communications
     * @return Alumni_titles with the given code
     */
    protected static Alumni_communications getAlumni_communications(
    		String alumnicommuniations_code) {
    	Alumni_communications alumnicommuniations = (Alumni_communications) HibernateUtil
    			.getSession().get(Alumni_communications.class,
    					alumnicommuniations_code);
    	return alumnicommuniations;
    }
    
    /**
     * Returns the Alumni_external_job_sectors with the given key. This method is used
     * internally to get objects form the database.
     * 
     * @param titlescode
     *            code of the Alumni titles
     * @return Alumni_external_job_sectors with the given code
     */
    protected static Alumni_external_job_sectors getAlumni_external_job_sectors(
    		String titlescode) {
    	Alumni_external_job_sectors alumni_external_job_sectors = (Alumni_external_job_sectors) HibernateUtil
    			.getSession().get(Alumni_external_job_sectors.class,
    					titlescode);
    	return alumni_external_job_sectors;
    }

    /**
     * Returns the Holiday with the given key. This method is used internally to
     * get objects form the database.
     * 
     * @param holidaycode
     *            code of the Holiday
     * @return Holiday with the given code
     */
    protected static Holiday getHoliday(String holidaycode) {
	Holiday holiday = (Holiday) HibernateUtil.getSession().get(
		Holiday.class, holidaycode);
	return holiday;
    }

    /**
     * Returns the Grant_concession with the given key. This method is used
     * internally to get objects form the database.
     * 
     * @param grant_concessioncode
     *            code of the Grant_concession
     * @return Grant_concession with the given code
     */
    protected static Grant_concession getGrant_concession(
	    String grant_concessioncode) {
	Grant_concession grant_concession = (Grant_concession) HibernateUtil
		.getSession().get(Grant_concession.class, grant_concessioncode);
	return grant_concession;
    }
    
    /**
     * Returns the Academic_info with the given key. This method is used
     * internally to get objects form the database.
     * 
     * @param academic_infocode
     *            code of the Academic_info
     * @return Academic_info with the given code
     */
    protected static Academic_info getAcademic_info(
	    String academic_infocode) {
	Academic_info academic_info = (Academic_info) HibernateUtil
		.getSession().get(Academic_info.class, academic_infocode);
	return academic_info;
    }

    /**
     * Returns the Type_of_contract with the given key. This method is used
     * internally to get objects form the database.
     * 
     * @param type_of_contractcode
     *            code of the Type_of_contract
     * @return Type_of_contract with the given code
     */
    protected static Type_of_contract getType_of_contract(
	    String type_of_contractcode) {
	Type_of_contract type_of_contract = (Type_of_contract) HibernateUtil
		.getSession().get(Type_of_contract.class, type_of_contractcode);
	return type_of_contract;
    }

    /**
     * Returns the Type_of_compensation with the given key. This method is used
     * internally to get objects form the database.
     * 
     * @param type_of_compensationcode
     *            code of the Type_of_compensation
     * @return Type_of_compensation with the given code
     */
    protected static Type_of_compensation getType_of_compensation(
	    String type_of_compensationcode) {
	Type_of_compensation type_of_compensation = (Type_of_compensation) HibernateUtil
		.getSession().get(Type_of_compensation.class,
			type_of_compensationcode);
	return type_of_compensation;
    }

    /**
     * Returns the Unit with the given key. This method is used internally to
     * get objects form the database.
     * 
     * @param unitcode
     *            code of the Unit
     * @return Unit with the given code
     */
    protected static Unit getUnit(String unitcode) {
	Unit unit = (Unit) HibernateUtil.getSession().get(Unit.class, unitcode);
	return unit;
    }

    /**
     * Returns the Gender with the given key. This method is used internally to
     * get objects form the database.
     * 
     * @param gendercode
     *            code of the Gender
     * @return Gender with the given code
     */
    protected static Gender getGender(String gendercode) {
	Gender gender = (Gender) HibernateUtil.getSession().get(Gender.class,
		gendercode);
	return gender;
    }

    /**
     * Returns the Country with the given key. This method is used internally to
     * get objects form the database.
     * 
     * @param countrycode
     *            code of the Country
     * @return Country with the given code
     */
    protected static Country getCountry(String countrycode) {
	Country country = (Country) HibernateUtil.getSession().get(
		Country.class, countrycode);
	return country;
    }

    /**
     * Returns the Category with the given key. This method is used internally
     * to get objects form the database.
     * 
     * @param categorycode
     *            code of the Category
     * @return Category with the given code
     */
    protected static Category getCategory(String categorycode) {
	Category category = (Category) HibernateUtil.getSession().get(
		Category.class, categorycode);
	return category;
    }

    /**
     * Returns the Funding_detail with the given key. This method is used
     * internally to get objects form the database.
     * 
     * @param funding_detailcode
     *            code of the Funding_detail
     * @return Funding_detail with the given code
     */
    protected static Funding_detail getFunding_detail(String funding_detailcode) {
	Funding_detail funding_detail = (Funding_detail) HibernateUtil
		.getSession().get(Funding_detail.class, funding_detailcode);
	return funding_detail;
    }

    /**
     * Returns the Area with the given key. This method is used internally to
     * get objects form the database.
     * 
     * @param areacode
     *            code of the Area
     * @return Area with the given code
     */
    protected static Area getArea(String areacode) {
	Area area = (Area) HibernateUtil.getSession().get(Area.class, areacode);
	return area;
    }

    /**
     * Returns the Type_of_holidays with the given key. This method is used
     * internally to get objects form the database.
     * 
     * @param type_of_holidayscode
     *            code of the Type_of_holidays
     * @return Type_of_holidays with the given code
     */
    protected static Type_of_holidays getType_of_holidays(
	    String type_of_holidayscode) {
	Type_of_holidays type_of_holidays = (Type_of_holidays) HibernateUtil
		.getSession().get(Type_of_holidays.class, type_of_holidayscode);
	return type_of_holidays;
    }

    /**
     * Returns the Personal with the given key. This method is used internally
     * to get objects form the database.
     * 
     * @param personalcode
     *            code of the Personal
     * @return Personal with the given code
     */
    protected static Personal getPersonal(String personalcode) {
	Personal personal = (Personal) HibernateUtil.getSession().get(
		Personal.class, personalcode);
	return personal;
    }

    /**
     * Returns the Report with the given key. This method is used internally to
     * get objects form the database.
     * 
     * @param reportcode
     *            code of the Report
     * @return Report with the given code
     */
    protected static Report getReport(String reportcode) {
	Report report = (Report) HibernateUtil.getSession().get(Report.class,
		reportcode);
	return report;
    }

    protected static PersonalPhoto getPersonalPhoto(String personalPhotocode) {
	PersonalPhoto personalPhoto = (PersonalPhoto) HibernateUtil
		.getSession().get(PersonalPhoto.class, personalPhotocode);
	return personalPhoto;
    }

    /**
     * Returns the Personalstate with the given key. This method is used
     * internally to get objects form the database.
     * 
     * @param personalstatecode
     *            code of the Personalstate
     * @return Personalstate with the given code
     */
    protected static Personalstate getPersonalstate(String personalstatecode) {
	Personalstate personalstate = (Personalstate) HibernateUtil
		.getSession().get(Personalstate.class, personalstatecode);
	return personalstate;
    }

    /**
     * Returns the Position with the given key. This method is used internally
     * to get objects form the database.
     * 
     * @param positioncode
     *            code of the Position
     * @return Position with the given code
     */
    protected static Position getPosition(String positioncode) {
	Position position = (Position) HibernateUtil.getSession().get(
		Position.class, positioncode);
	return position;
    }

    /**
     * Returns the User with the given code (no username). This method is used
     * internally to get users form the database.
     * 
     * @param userId
     *            Code (no username) of the user
     * @return User with the given code
     */
    public static Usuario getUsuario(String userId) throws InternalException {

	Usuario user = null;
	try {
		log.debug("Getting User from UserManagement by userId: " + userId);
		user = (Usuario) UserManagement.singleton().getUser(userId);
	    log.debug("getUsuario: "+user + " - Roles: " + user.getRoles());
	} catch (EntityNotFoundException e) {
		log.debug("User " + userId +" not found in Umuser table");
		user = new Usuario();

	    Criteria crit = HibernateUtil.getSession().createCriteria(
		    Personal.class);
	    crit.add(Expression.eq("personalcode", userId));

	    Personal per = (Personal) crit.uniqueResult();

	    if (per == null) {
		Language l = new Language();
		l.setLanguage("en");
		user.setLanguage(l);
		return user;
	    }

	    user.setPersonal(per);

	    user.setLanguage(per.getLanguage());
	    user.setCode(per.getPersonalcode());
	    user.setUsername(per.getUsercode());

	    String userrole = getUserRole(per.getUsercode());
	    
	    if (userrole!=null) {
		    Set<Role> roles = new HashSet<Role>();
		    try {
				roles.add(new Role(userrole, userrole, ""));
			} catch (IdentifierException e1) {
				log.error("Error assigning role");
			}
		    user.setRoles(roles);
	    }
	    
	} catch (Exception e) {
	    manageInternalException(e);
	}
	return user;
    }

    private static String getUserRole(String usercode){
    	Object rolename = HibernateUtil.getSession().createSQLQuery("SELECT rolecode FROM userrole WHERE usercode = :usercode")
    	.addScalar("rolecode", Hibernate.TEXT)
    	.setParameter("usercode",  usercode)
    	.uniqueResult();
    	log.debug("User " + usercode + ", role retrieved from database: " + rolename);
    	if (rolename != null)
    		return (String) rolename;
    	return null;
    }
    
    /**
     * Returns the Language with the given code. This method is used internally
     * to get languages form the database.
     * 
     * @param id
     *            Code of the Language
     * @return Language with the given code
     */
    protected static Language getLanguage(String id) {
	Language lang = (Language) HibernateUtil.getSession().get(
		Language.class, id);
	return lang;
    }

    /**
     * Returns the Role with the given code. This method is used internally to
     * get roles form the database.
     * 
     * @param id
     *            Code of the Role
     * @return Role with the given code
     */
    protected static Role getRole(String id) {
	Role rol = (Role) HibernateUtil.getSession().get(Role.class, id);
	return rol;
    }

    /**
     * Returns the Irb_budget with the given code. This method is used
     * internally to get Irb_budget form the database.
     * 
     * @param id
     *            Code of the Irb_budget
     * @return Irb_budget with the given code
     */
    protected static Irb_budget getIrb_budget(String id) {
	Irb_budget irb_budget = (Irb_budget) HibernateUtil.getSession().get(
		Irb_budget.class, id);
	return irb_budget;
    }

    // tests

    /**
     * Tests if a user is an active user. If the test fails, a
     * UsuarioNoActivoException is thorwn.
     * 
     * @param user
     *            user to be tested
     * @throws UsuarioNoActivoException
     *             This exception is thrown if the test fails.
     * @throws InternalException
     */
    private static void testIsActiveUser(Usuario user)
	    throws UsuarioNoActivoException, InternalException {
	try {
	    if (!UserManagement.singleton().isActive(user)) {
		throw new UsuarioNoActivoException();
	    }
	} catch (PermissionPriorityException e) {
	    manageInternalException(e);
	} catch (IOException e) {
	    manageInternalException(e);
	}
    }

    /**
     * Tests if a user is an administrator, or if it is the same as anotherone.
     * If the test fails, a UsuarioNoActivoException is thorwn.
     * 
     * @param execUser
     *            the user to be tested
     * @param dataUser
     *            the user to be compared to the firstone
     * @throws NoPermisosException
     *             This exception is thrown if the test fails.
     */
    private static void testIsAdminOrItself(Usuario execUser, Usuario dataUser)
	    throws NoPermisosException {
	if (execUser == SYSTEM_USER)
	    return;
	if (execUser.getId().equals(dataUser.getId()))
	    return;
	testIsAdmin(execUser);
    }

    /**
     * Tests if a user is an administrator. If the test fails, a
     * UsuarioNoActivoException is thorwn.
     * 
     * @param user
     *            the user to be tested
     * @throws NoPermisosException
     *             This exception is thrown if the test fails.
     */
    private static void testIsAdmin(Usuario user) throws NoPermisosException {
	if (user == SYSTEM_USER)
	    return;
	if (isAdmin(user))
	    return;
	throw new NoPermisosException();
    }

    /**
     * Tests if a user is an Human resources. If the test fails, a
     * UsuarioNoActivoException is thorwn.
     * 
     * @param user
     *            the user to be tested
     * @throws NoPermisosException
     *             This exception is thrown if the test fails.
     */
    private static void testIsHHRR(Usuario user) throws NoPermisosException {
	return;
	// if (user == SYSTEM_USER)
	// return;
	// if (isHHRR(user))
	// return;
	// throw new NoPermisosException();
    }

    // private static void testIsHHRRorSupervisorof(Usuario user, Personal
    // personal)
    // throws NoPermisosException {
    // if (isHHRR(user))
    // return;
    //
    // }

    // private static void testIsSupervisor(Usuario user)
    // throws NoPermisosException {
    // if (!isSupervisor(user)) {
    // throw new NoPermisosException();
    // }
    // }

    // private static void testIsSupervisorOf(Usuario user, Personal personal)
    // throws NoPermisosException {
    // testIsSupervisor(user);
    // if (!isSupervisorOf(user, personal)) {
    // throw new NoPermisosException();
    // }
    // }

    // private static void testIsHHRROrItself(Usuario execUser, Usuario
    // dataUser)
    // throws NoPermisosException {
    // if (execUser == SYSTEM_USER)
    // return;
    // if (execUser.getId().equals(dataUser.getId()))
    // return;
    // testIsHHRR(execUser);
    // }

    // private static void testIsHHRROrItself(Usuario execUser, Personal
    // personal)
    // throws NoPermisosException {
    // return;
    // if (execUser == SYSTEM_USER)
    // return;
    // if (execUser.getPersonal() != null
    // && execUser.getPersonal().getCode().equals(personal.getCode()))
    // return;
    // testIsHHRR(execUser);
    // }

    /**
     * Tests if a user is an administrator.
     * 
     * @param user
     *            the user to be tested
     * @return returns true if the user is an administrator
     */
    private static boolean isAdmin(Usuario user) {
	return (user.getRoles().contains(getRole(ADMINISTRATOR_ROLE_NAME)));
    }

    
    /**
     * Tests if a user is an alumni administrator.
     * 
     * @param user
     *            the user to be tested
     * @return returns true if the user is an administrator
     */
    public static boolean isAlumni(Usuario user) {
    	return (checkRole(user, ALUMNI_ROLE_NAME) || checkRole(user, ALUMNI_ADMIN_ROLE_NAME));
    }
    
    /**
     * Tests if a user is an human resorces.
     * 
     * @param user
     *            the user to be tested
     * @return returns true if the user is an administrator
     */
    public static boolean isHHRR(Usuario user) {
	return (checkRole(user, HUMAN_RESOURCES_ROLE_NAME) || isIRBPeopleGrant(user) || isIRBInnovation(user));
    }
    
    /**
     * Tests if a user is IRB People Grant.
     * 
     * @param user
     *            the user to be tested
     * @return returns true if the user is an administrator
     */
    public static boolean isIRBPeopleGrant(Usuario user) {
    	return checkRole(user, IRBPEOPLE_GRANT_ROLE_NAME);    	
    }
    
    /**
     * Tests if a user is IRB People Innovation.
     * 
     * @param user
     *            the user to be tested
     * @return returns true if the user is an administrator
     */
    public static boolean isIRBInnovation(Usuario user) {
    	return checkRole(user, IRBPEOPLE_INNOVATION_ROLE_NAME);    	
    }

    public static boolean checkRole(Usuario user, String role){    	
    	boolean checkRole = user.getRoles().contains(getRole(role));
    	log.debug("User roles: "+user.getRoles() +" - expected: " + getRole(role) + ": " + checkRole);
    	return checkRole;
    }
    
    // private static boolean isSupervisor(Usuario user) {
    // return (user.getRoles().contains(getRole(SUPERVISOR_ROLE_NAME)));
    // }

    // private static boolean isSupervisorOf(Usuario user, Personal personal) {
    // if (user.getPersonal() == null)
    // return false;
    //
    // Set<Research_group> reserachGroupsSupervied = user.getPersonal()
    // .getIsupervisor();
    // Set<Professional> professionals = personal.getIprofessional_personal();
    //
    // for (Professional prof : professionals) {
    // if (prof.getResearch_group() != null
    // && reserachGroupsSupervied.contains(prof
    // .getResearch_group())) {
    // return true;
    // }
    // }
    //
    // return false;
    // }

    /**
     * Tests if a user does not exists (the test fails if the user allready
     * exists). If the test fails, a UsuarioExisteException is thorwn.
     * 
     * @param user
     *            the user to be tested
     * @throws UsuarioExisteException
     *             This exception is thrown if the test fails.
     */
    private static void testExistsUser(Usuario user)
	    throws UsuarioExisteException {

	if (!HibernateUtil.getSession().createCriteria(Usuario.class)
		.add(Expression.eq("email", user.getEmail())).list().isEmpty()) {
	    throw new UsuarioExisteException();
	}
    }

    private static void manageInternalException(Exception e)
	    throws InternalException {
	log.error(e);
	throw new InternalException();
    }

    public static Personal ObtainPersonalFromUser(Usuario usuario,
	    Usuario currentUsuario) throws InternalException {
	Usuario user = getUsuario(currentUsuario.getCode());
	return user.getPersonal();
    }

    private static void testAltaUsuarioPermissions(Usuario user,
	    Usuario pendiente, String activationCode)
	    throws NoPermisosException, InternalException {
	if (user != null && isAdmin(user))
	    return;

	if (activationCode != null && pendiente.getActivationCode() != null
		&& pendiente.getActivationCode().equals(activationCode))
	    return;

	throw new NoPermisosException();

    }

    /**
     * This method obtains all instances of Personal, given a list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param personal
     *            supervisor
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     */
    public static Pair<Integer, List<Personal>> ObtainAllPersonalSupervisedBy(
	    Usuario user, Personal personal, ListConfigurator configurator) {

	// 1.2 we look for the ones which are in the research groups
	Set<Research_group> reserachGroups = personal.getIsupervisor();
	if (reserachGroups.isEmpty()) {
	    return new Pair<Integer, List<Personal>>(0,
		    new ArrayList<Personal>());
	}

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(
		Personal.class);

	// we only want to obtain the non deleted objects
	crit.add(Expression.eq("deleted", Boolean.FALSE));

	// 1.We only want to obtain the personal which its research group is one
	// of the personal research groups
	// 1.1We look for the active profesionals

	Criteria activeProfessionals = crit.createCriteria(
		"iprofessional_personal").add(
		Expression.eq("current", Boolean.TRUE));

	// reserachGroups.add(getResearch_group("00002"));
	activeProfessionals
		.add(Expression.in("research_group", reserachGroups));

	// DUPLICATE ENTRIES EXIST?? if so delete them

	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	List<Personal> personals = (List<Personal>) crit.list();

	Pair<Integer, List<Personal>> pair = new Pair<Integer, List<Personal>>(
		count, personals);

	return pair;
    }

    /**
     * This method changes the state of a personal from edition to validation.
     * In case the user related to the personal is a basic user, it is deleted
     * 
     * @param user
     *            The user who executes this use case
     * @param personal
     *            Personal (not DTO) to be modified.
     * @return the modified personal
     * @throws InternalException
     * @throws NoPermisosException
     * @throws UsuarioNoActivoException
     * @throws ValidationFailedException
     */
    public static Personal changeStateOfPersonalFromEditionToValidating(
	    Usuario user, Personal personal) throws InternalException,
	    NoPermisosException, UsuarioNoActivoException,
	    ValidationFailedException {

	checkToValidateRequiredData(user, personal.getPersonalcode());

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2.We set the new state to the personal * */
	personal.setState(getPersonalstate(Personalstate.VALIDATING_CODE));

	/**
	 * 2.2 In case that the user of the personal is a basic user, we delete
	 * it *
	 */

	// if (isOnlyRoleBasic(user, personal.getCode())) {
	// Usuario usuarioFromPersonal = ObtenerUsuarioFromPersonal(user,
	// personal.getCode());
	// BorrarUsuario(user, usuarioFromPersonal);
	// }

	// creem codi per a la validacio del supervisor
	personal.setValidationCode(UseCaseUtils.createValidationCode());

	CreateStateChangeAuditmessage(user, personal, "validating");

	UseCaseUtils.sendAdviseSupervisorNewValidationPersonalMail(personal);

	/** 3. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return personal;
    }

    /**
     * This method changes the state of a personal from validation to in
     * progress
     * 
     * @param user
     *            The user who executes this use case
     * @param personal
     *            Personal (not DTO) to be modified.
     * @return the modified personal
     * @throws InternalException
     * @throws ValidationFailedException
     */
    public static Personal changeStateOfPersonalFromValidatingToValidated(
	    Usuario user, Personal personal) throws InternalException {

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	RemoveValidationCode(personal);

	/** 2.We set the new state to the personal * */
	personal.setState(getPersonalstate(Personalstate.VALIDATED_CODE));

	CreateStateChangeAuditmessage(user, personal, "validated");

	/** 3. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return personal;
    }

    /**
     * This method changes the state of a personal from validation to editing
     * 
     * @param user
     *            The user who executes this use case
     * @param personal
     *            Personal (not DTO) to be modified.
     * @return the modified personal
     * @throws InternalException
     * @throws ValidationFailedException
     */
    public static Personal changeStateOfPersonalFromValidatingToEditing(
	    Usuario user, Personal personal) {

	HibernateUtil.beginTransaction();

	personal.setState(getPersonalstate(Personalstate.EDITING_CODE));

	CreateStateChangeAuditmessage(user, personal, "editing");

	HibernateUtil.commitTransaction();

	return personal;
    }

    private static void RemoveValidationCode(Personal personal) {

	HibernateUtil.beginTransaction();

	if (personal.getValidationCode() != null)
	    personal.setValidationCode(null);

	HibernateUtil.commitTransaction();
    }

    /**
     * This method changes the state of a personal from validated to active
     * 
     * @param user
     *            The user who executes this use case
     * @param personal
     *            Personal (not DTO) to be modified.
     * @return the modified personal
     * @throws InternalException
     * @throws ValidationFailedException
     * @throws NoPermisosException 
     */
    public static Personal changeStateOfPersonalFromValidatedToActive(
	    Usuario user, Personal personal) throws InternalException,
	    ValidationFailedException, NoPermisosException {

	checkToValidatedRequiredData(user, personal.getCode());

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2.We set the new state to the personal * */
	personal.setState(getPersonalstate(Personalstate.ACTIVE_CODE));

	UseCaseUtils
		.updateLDAPFromCreate(ObtainActiveProfessionalFromPersonal(personal));

	CreateStateChangeAuditmessage(user, personal, "active");

	/** 3. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return personal;
    }

    /**
     * This method changes the state of a personal from in active to inactive
     * 
     * @param user
     *            The user who executes this use case
     * @param newPersonal
     *            Personal (not DTO) to be modified.
     * @return the modified personal
     * @throws InternalException
     * @throws NoPermisosException
     */
    public static Personal changeStateOfPersonalFromActiveToInactive(
	    Usuario user, Personal newPersonal) throws InternalException,
	    NoPermisosException {

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	Personal currentPersonal = UseCase.getPersonal(newPersonal
		.getPersonalcode());

	/** 2.We set the new state to the personal * */

	currentPersonal.setEnd_of_contract_address(newPersonal
		.getEnd_of_contract_address());
	currentPersonal.setEnd_of_contract_city(newPersonal
		.getEnd_of_contract_city());
	// currentPersonal.setEnd_of_contract_country(
	// newPersonal.getEnd_of_contract_country());
	currentPersonal.setEnd_of_contract_email(newPersonal
		.getEnd_of_contract_email());
	currentPersonal.setEnd_of_contract_phone(newPersonal
		.getEnd_of_contract_phone());
	currentPersonal.setEnd_of_contract_reason(newPersonal
		.getEnd_of_contract_reason());
	currentPersonal.setEnd_of_contract_zip_code(newPersonal
		.getEnd_of_contract_zip_code());

	currentPersonal.setState(getPersonalstate(Personalstate.INACTIVE_CODE));

	boolean _end_of_contract_countryIsDefined = false;

	if (newPersonal.getEnd_of_contract_country() != null
		&& newPersonal.getEnd_of_contract_country().getCountrycode() != null) {
	    // if end_of_contract_country is defined we replace the
	    // end_of_contract_country in the DTO with its current state in the
	    // DB.
	    _end_of_contract_countryIsDefined = true;

	    newPersonal.setEnd_of_contract_country(getCountry(newPersonal
		    .getEnd_of_contract_country().getCountrycode()));
	}

	HibernateUtil.getSession().evict(currentPersonal);
	currentPersonal.setVersion(newPersonal.getVersion());
	HibernateUtil.getSession().update(currentPersonal);

	if (_end_of_contract_countryIsDefined) {

	    if (currentPersonal.getEnd_of_contract_country() != null) {

		currentPersonal.getEnd_of_contract_country()
			.removeIend_of_contract_country(currentPersonal);
	    }

	    if (newPersonal.getEnd_of_contract_country() != null) {

		newPersonal.getEnd_of_contract_country()
			.addIend_of_contract_country(currentPersonal);
	    }

	    currentPersonal.setEnd_of_contract_country(newPersonal
		    .getEnd_of_contract_country());
	}

	CreateStateChangeAuditmessage(user, currentPersonal, "inactive");

	// UseCaseUtils.sendAdviseAdviseSupervisorInactivatedPersonalMail(currentPersonal);
	UseCaseUtils.sendEndOfContractInstructions(currentPersonal);

	/** 3. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();
	
	// Export if it's necessary
	ExportAlumni(user, newPersonal.getPersonalcode());
	return currentPersonal;
    }

    /**
     * This method changes the state of a personal from in inactive to active
     * 
     * @param user
     *            The user who executes this use case
     * @param personal
     *            Personal (not DTO) to be modified.
     * @return the modified personal
     * @throws InternalException
     */
    public static Personal changeStateOfPersonalFromInactiveToActive(
	    Usuario user, Personal personal) throws InternalException {

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2.We set the new state to the personal * */
	personal.setState(getPersonalstate(Personalstate.ACTIVE_CODE));

	CreateStateChangeAuditmessage(user, personal, "active");

	/** 3. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return personal;
    }

    /**
     * This method changes the password of a user to a generated password
     * 
     * @param usuario
     *            The user who executes this use case
     * @param usuario2
     *            Usuario (not DTO) to be modified.
     * @return the modified Usuario
     * @throws InternalException
     * @throws NoPermisosException
     * @throws UsuarioNoActivoException
     * @throws InternalException
     */
    public static Usuario generatePassword(Usuario usuario, Usuario usuario2,
	    String passwordGenerationActivationCode)
	    throws UsuarioNoActivoException, NoPermisosException,
	    InternalException {

	if (usuario2.getChangePasswordCode() == null
		|| passwordGenerationActivationCode == null
		|| usuario2.getChangePasswordCode().equals("")
		|| !usuario2.getChangePasswordCode().equals(
			passwordGenerationActivationCode)) {
	    throw new NoPermisosException();
	}
	HibernateUtil.beginTransaction();

	String password = UseCaseUtils.generatePassword();

	usuario2 = ModificarPassword(usuario2, usuario2, password);

	CreateAuditmessage(usuario, "auditmessage.generatedPassword",
		new String[] { usuario2.getUsername() }, MODIFY_MESSAGE);

	HibernateUtil.commitTransaction();

	UseCaseUtils.sendPasswordChangedMail(usuario2, password);

	return usuario2;
    }

    /**
     * This method changes generates a request to generate a password of a user
     * 
     * @param usuario
     *            The user who executes this use case
     * @param usuario2
     *            Usuario (not DTO) to be modified.
     * @return the modified Usuario
     * @throws NoPermisosException
     */
    public static Usuario requestToGeneratePassword(Usuario usuario,
	    Usuario usuario2) throws NoPermisosException {
	String passwordGenerationActivationCode = UseCaseUtils
		.createActivationCode();

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	usuario2.setChangePasswordCode(passwordGenerationActivationCode);
	HibernateUtil.getSession().update(usuario2);

	CreateAuditmessage(usuario, "auditmessage.changePasswordRequest",
		new String[] { usuario2.getUsername() }, MODIFY_MESSAGE);

	/** 2. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	UseCaseUtils.sendPasswordChangeRequestedMail(usuario2,
		passwordGenerationActivationCode);

	return usuario2;
    }

    /**
     * This method checks if a personal can be changed to valitation from
     * edition
     * 
     * @param usuario
     * @param personalCode
     * @throws ValidationFailedException
     * @throws NoPermisosException 
     */
    public static void checkToValidateRequiredData(Usuario usuario,
	    String personalCode) throws ValidationFailedException, NoPermisosException {
	Map<String, List<String>> result = new Hashtable<String, List<String>>();

	Personal personal = getPersonal(personalCode);

	List<String> requiredAtt = new ArrayList<String>();
	if (personal.getName() == null || personal.getName().equals(""))
	    requiredAtt.add("personal.name");
	if (personal.getSurname1() == null || personal.getSurname1().equals(""))
	    requiredAtt.add("personal.surname1");
	if (personal.getDni() == null || personal.getDni().equals(""))
	    requiredAtt.add("personal.dni");
	if (personal.getGender() == null)
	    requiredAtt.add("personal.gender");
	if (personal.getBirth_date() == null)
	    requiredAtt.add("personal.birth_date");
	if (personal.getBirth_city() == null)
	    requiredAtt.add("personal.birth_city");
	if (personal.getBirth_country() == null)
	    requiredAtt.add("personal.birth_country");
	if (personal.getNationality() == null)
	    requiredAtt.add("personal.nationality");
	if (personal.getStreet() == null || personal.getStreet().equals(""))
	    requiredAtt.add("personal.street");
	if (personal.getCity() == null || personal.getCity().equals(""))
	    requiredAtt.add("personal.city");
	if (personal.getZip_code() == null || personal.getZip_code().equals(""))
	    requiredAtt.add("personal.zip_code");
	if (personal.getCountry() == null)
	    requiredAtt.add("personal.country");
	if (personal.getPhone() == null || personal.getPhone().equals(""))
	    requiredAtt.add("personal.phone");

	ListConfigurator professionalsListConf = new ListConfigurator();
	professionalsListConf.setOrderBy("professionalcode");
	professionalsListConf.setAsc("");
	List<Professional> professionals = UseCase
		.ObtainAllIprofessional_personalFromPersonal(usuario, personal,
			professionalsListConf).second;
	Professional professional = null;
	if (professionals != null & !professionals.isEmpty()) {
	    professional = professionals.get(0);
	}
	// if(professionals==null || professionals.isEmpty() ||
	// professionals.get(0).getResearch_group()==null)
	// requiredAtt.add("professional.research_group");
	if (professional != null && professional.getResearch_group() != null
		&& professional.getPosition() == null)
	    requiredAtt.add("professional.position");
	if (professional == null || professional.getLocation() == null)
	    requiredAtt.add("professional.location");
	// if (professional == null || professional.getEmail() == null
	// || professional.getEmail().equals(""))
	// requiredAtt.add("professional.email");
	// if (professional == null || professional.getPhone() == null
	// || professional.getPhone().equals(""))
	// requiredAtt.add("professional.phone");
	// if(professional==null || professional.getMobile()==null ||
	// professional.getMobile().equals(""))
	// requiredAtt.add("professional.mobile");
	if (professional == null || professional.getStart_date() == null)
	    requiredAtt.add("professional.start_date");

	if (professional == null || professional.getProfessional_unit() == null)
	    requiredAtt.add("unit");

	if (!requiredAtt.isEmpty()) {
	    result.put("propertyError.required_attribute", requiredAtt);
	}

	List<String> requiredList = new ArrayList<String>();
	if (personal.getIeducation_personal().isEmpty())
	    requiredList.add("personal.education");

	if (!requiredList.isEmpty()) {
	    result.put("propertyError.empty_attribute", requiredList);
	}

	if (!result.isEmpty()) {
	    throw new ValidationFailedException(result);
	}

    }

    /**
     * This method checks if a personal can be changed to progress from
     * validation
     * 
     * @param usuario
     * @param personalCode
     * @throws ValidationFailedException
     * @throws NoPermisosException 
     */
    public static void checkToValidatedRequiredData(Usuario usuario,
	    String personalCode) throws ValidationFailedException, NoPermisosException {
	Map<String, List<String>> result = new Hashtable<String, List<String>>();

	Personal personal = getPersonal(personalCode);

	List<String> requiredAtt = new ArrayList<String>();

	ListConfigurator professionalsListConf = new ListConfigurator();
	professionalsListConf.setOrderBy("professionalcode");
	professionalsListConf.setAsc("");
	List<Professional> professionals = UseCase
		.ObtainAllIprofessional_personalFromPersonal(usuario, personal,
			professionalsListConf).second;
	Professional professional = null;
	if (professionals != null & !professionals.isEmpty()) {
	    professional = professionals.get(0);
	}

	if (professional == null || professional.getStart_date() == null)
	    requiredAtt.add("professional.start_date");
	// if (professional == null || professional.getResearch_group() == null)
	// requiredAtt.add("professional.research_group");
	if (professional == null || professional.getProfessional_unit() == null)
	    requiredAtt.add("professional.unit");
	if (professional == null || professional.getPosition() == null)
	    requiredAtt.add("profesioanl.position");
	// if(professional!=null && professional.getResearch_group()!=null &&
	// professional.getPosition()==null)
	// requiredAtt.add("professional.position");
	if (professional == null
		|| professional.getPayroll_institution() == null)
	    requiredAtt.add("payroll_institution");
	// if (professional == null
	// || professional.getPayroll_institution_2() == null)
	// requiredAtt.add("payroll_institution_2");

	// if(personal.getResearch_project()==null)
	// requiredAtt.add("personal.research_project");
	// if(personal.getSponsoring_agency()==null)
	// requiredAtt.add("personal.sponsoring_agency");
	// if(personal.getHolding_institution()==null)
	// requiredAtt.add("personal.holding_institution");
	// if(personal.getPrincipal_investigator()==null)
	// requiredAtt.add("personal.principal_investigator");

	if (!requiredAtt.isEmpty()) {
	    result.put("propertyError.required_attribute", requiredAtt);
	}

	// List<String> requiredList=new ArrayList<String>();
	// if(personal.getIfunding_detail_personal().isEmpty())
	// requiredList.add("funding_detail");
	//
	// if(!requiredList.isEmpty()){
	// result.put("propertyError.empty_attribute", requiredList);
	// }

	if (!result.isEmpty()) {
	    throw new ValidationFailedException(result);
	}

    }

    public static boolean isSupervisorOf(Usuario usuario, Personal supervisor,
	    Personal personal) throws NoPermisosException {
	List<Professional> professionals = ObtainAllActiveIprofessional_personalFromPersonal(
		null, personal, new ListConfigurator()).second;
	for (Professional profesional : professionals) {
	    if (profesional.getResearch_group() != null
		    && profesional.getResearch_group().getSupervisor() != null
		    && profesional.getResearch_group().getSupervisor()
			    .getCode().equals(supervisor.getCode()))
		return true;
	}
	return false;
    }

    public static boolean isObtentionPhaseActive() {
	return getCurrentApplicationPreferences().isObtentionPhaseActive();
    }

    private static ApplicationPreferences getCurrentApplicationPreferences() {
	ApplicationPreferences result = (ApplicationPreferences) HibernateUtil
		.getSession().get(ApplicationPreferences.class, "1");
	return result;
    }

    // public static List<Usuario> obtainAllUsersOfRole(Usuario usuario, String
    // roleId){
    //
    //
    // /** 1. We create an Hibernate Criteria to obtain the desired values **/
    // Criteria crit = HibernateUtil.getSession().createCriteria(Usuario.class);
    //
    // crit.add(Expression.eq("activeboolean", 0));
    // crit.add(Expression.isNull("enddate"));
    // crit.add(Expression.eq("deletedboolean", 0));
    //
    // //we only want to obtain the non deleted objects
    // crit.add(Expression.eq("deleted",Boolean.FALSE));
    //
    // /** 2. We obtain the list form the DB and we return it with the number of
    // elements in the DB **/
    // List<Organization_unit> organization_units = (List<Organization_unit>)
    // crit.list();
    //
    // Pair<Integer, List<Organization_unit>> pair = new Pair<Integer,
    // List<Organization_unit>>(
    // count, organization_units);
    //
    // return pair;
    // }

    /**
     * This method creates a Report.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOReport
     *            Report data transfer object (DTO) with the values of the new
     *            instance.
     * @return the new report created with this Use Case
     * @throws InternalException
     */
    public static Report CreateReport(Usuario user, Report TOReport)
	    throws InternalException {

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/**
	 * 2. For each association from the TOReport that are filled in the DTO
	 * we put the real objects from the DB. *
	 */

	// we store if the Author is defined for later use
	boolean _AuthorIsDefined = false;

	if (TOReport.getAuthor() != null
		&& TOReport.getAuthor().getUsuariocode() != null) {
	    // if Author is defined we replace the Author in the DTO with its
	    // current state in the DB.
	    _AuthorIsDefined = true;

	    TOReport.setAuthor(getUsuario(TOReport.getAuthor().getUsuariocode()));
	}

	/** 3. We create the new instance * */
	Report report = new Report();

	/**
	 * 4. We set all the simple attributes (no associations) to the new
	 * instance *
	 */

	report.setName(TOReport.getName());

	report.setDate(TOReport.getDate());

	report.setFilename(TOReport.getFilename());

	report.setType(TOReport.getType());

	report.setIs_public(TOReport.getIs_public());

	report.setObservations(TOReport.getObservations());

	/** 5. We set the code to the new instance * */
	try {
	    IdentifyManager_Plain im = IdentifyManager_Plain.singleton();

	    report.setReportcode(im.getId(TOReport));
	} catch (identifyException ie) {

	    log.error("Error en asignaciï¿½n de nuevo id en CreateReport", ie);
	    throw new Error(ie.getMessage());
	}

	/** 6. We save the new instance to the DB* */
	HibernateUtil.getSession().save(report);

	/**
	 * We associate the current object to the other objects (only in case
	 * that the associations where defined in the DTO) *
	 */

	if (_AuthorIsDefined) {

	    if (TOReport.getAuthor() != null) {

		TOReport.getAuthor().addIauthor(report);
	    }

	    report.setAuthor(TOReport.getAuthor());
	}

	/** 7. We create an Audit message * */
	CreateCreationAuditmessage(user, report);

	/** 8. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return report;
    }

    /**
     * This method modifies a report.
     * 
     * @param user
     *            The user who executes this use case
     * @param TOReport
     *            Report data transfer object (DTO) with the values of the
     *            modified instance. The code of this attribute indicates which
     *            report will be modified.
     * @return the modified report
     * @throws InternalException
     */
    public static Report UpdateReport(Usuario user, Report TOReport)
	    throws InternalException {

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/**
	 * 2. For each association from the TOReport that are filled in the DTO
	 * we put the real objects from the DB. *
	 */

	// we store if the Author is defined for later use
	boolean _AuthorIsDefined = false;

	if (TOReport.getAuthor() != null
		&& TOReport.getAuthor().getUsuariocode() != null) {
	    // if Author is defined we replace the Author in the DTO with its
	    // current state in the DB.
	    _AuthorIsDefined = true;

	    TOReport.setAuthor(getUsuario(TOReport.getAuthor().getUsuariocode()));
	}

	/** 3. We obtain form the DB the instance to modify * */
	Report report = getReport(TOReport.getReportcode());
	;

	/**
	 * 4. We set all the simple attributes (no associations) to the instance
	 * *
	 */

	report.setName(TOReport.getName());

	report.setDate(TOReport.getDate());

	report.setFilename(TOReport.getFilename());

	report.setType(TOReport.getType());

	report.setIs_public(TOReport.getIs_public());

	report.setObservations(TOReport.getObservations());

	/**
	 * 5. We set the DTO version to the modified object and we update it
	 * with the new values in the DB. We evict and update the instance to
	 * prevent concurrent modification *
	 */
	HibernateUtil.getSession().evict(report);
	report.setVersion(TOReport.getVersion());
	HibernateUtil.getSession().update(report);

	/**
	 * We associate/disassociate the current object to the other objects
	 * (only in case that the associations where defined in the DTO) *
	 */

	if (_AuthorIsDefined) {

	    if (report.getAuthor() != null) {

		report.getAuthor().removeIauthor(report);
	    }

	    if (TOReport.getAuthor() != null) {

		TOReport.getAuthor().addIauthor(report);
	    }

	    report.setAuthor(TOReport.getAuthor());
	}

	/** 6. We create an Audit message * */
	CreateModificationAuditmessage(user, report);

	/** 7. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return report;
    }

    /**
     * This method removes a report.
     * 
     * @param user
     *            The user who executes this use case
     * @param reportcode
     *            Code of the report to be removed
     */
    public static void RemoveReport(Usuario user, String reportcode) {

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain the object to delete form the DB. * */
	Report report = getReport(reportcode);

	/** 3. We mark it as deleted. * */
	report.setDeleted(Boolean.TRUE);

	/** 4. We create an Audit message * */
	CreateRemovealAuditmessage(user, report);

	/** 5. We commit the DB transaction. * */
	HibernateUtil.commitTransaction();
    }

    /**
     * This method obtains one instance of report given its code.
     * 
     * @param user
     *            The user who executes this use case
     * @param reportcode
     *            Code of the report to be obtained
     * @return Report with the given code.
     */
    public static Report ObtainReport(Usuario user, String reportcode) {

	/**
	 * 1. We obtain the object from the DB using the private getter and we
	 * return it. *
	 */

	Report report = getReport(reportcode);
	return report;
    }

    /**
     * This method obtains all instances of Report, given a list-configurator.
     * 
     * @param user
     *            The user who executes this use case
     * @param configurator
     *            ListConfigurator to be used
     * @return A pair with an Integer with the total number of instances which
     *         match the search without appling the 'pagination' of the
     *         ListConfigurator, and the list of the instances which match the
     *         configurator (incluing pagination)
     */
    public static Pair<Integer, List<Report>> ObtainAllReport(Usuario user,
	    ListConfigurator configurator) {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(Report.class);

	// we only want to obtain the non deleted objects
	crit.add(Expression.eq("deleted", Boolean.FALSE));

	// we add the ListConfigurator to the criteria, obtaining the number of
	// results without the pagination
	int count = configurator.addCriterions(crit);

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	List<Report> reports = (List<Report>) crit.list();

	Pair<Integer, List<Report>> pair = new Pair<Integer, List<Report>>(
		count, reports);

	return pair;
    }

    public static Personal testValidatePersonal(Usuario usuario,
	    String personalcode, String validationCode) throws Exception {

	Personal personal = ObtainPersonal(usuario, personalcode);
	if (personal == null || personal.isDeleted()
		|| personal.getValidationCode() == null)
	    throw new Exception();

	if (validationCode == null
		|| !personal.getValidationCode().equals(validationCode))
	    throw new NoPermisosException();

	return personal;
    }

    public static void cancelValidationPersonal(Usuario usuario,
	    String personalcode, String observacions) throws Exception {

	Personal personal = ObtainPersonal(usuario, personalcode);

	RemoveValidationCode(personal);

	changeStateOfPersonalFromValidatingToEditing(usuario, personal);

	UseCaseUtils.sendMailToAdviseValidationFail(personal, observacions);
    }

    // public static List<Usuario> obtainAllRRHH(Usuario user) throws Exception
    // {
    //
    // List<Usuario> usuarios = ObtenerUsuariosActivos(user,
    // new ListConfigurator()).getSecond();
    // List<Usuario> rrhh = new ArrayList<Usuario>();
    // for (Usuario usuario : usuarios) {
    // if (isHHRR(usuario)) {
    // rrhh.add(usuario);
    // }
    // }
    // return rrhh;
    // }

    public static void validatePersonal(Usuario usuario, String personalcode,
	    String observacions) throws Exception {

	Personal personal = ObtainPersonal(usuario, personalcode);

	changeStateOfPersonalFromValidatingToValidated(usuario, personal);

	UseCaseUtils.sendMailToAdviseValidationSuccess(personal, observacions);
    }

    public static Type_of_education obtainMostAdvancedEducationForPersonal(
	    String personalcode) {

	Query q = HibernateUtil
		.getSession()
		.createQuery(
			"select max(edu.type.order) from bussineslogic.objects.Education edu where edu.deleted=0 and edu.education_personal.personalcode='"
				+ personalcode + "'");

	Object o = q.uniqueResult();

	if (o != null && o instanceof Integer) {

	    Criteria crit = HibernateUtil.getSession().createCriteria(
		    Type_of_education.class);

	    crit.add(Expression.eq("order", o));

	    List types = crit.list();

	    if (types != null && !types.isEmpty()) {
		return (Type_of_education) types.get(0);
	    }
	}

	return null;

    }

    public static Pair<Integer, List<Personal>> ObtainPersonalWithContract(
	    Usuario usuario, ListConfigurator configurator) {

	String irbCodeString = MAINCONFIG
		.getString("irbPayrollInstitutionCode");

	Criteria crit = HibernateUtil.getSession().createCriteria(
		Personal.class);

	// crit
	// .add(Expression.eq("deleted", Boolean.FALSE))
	// .add(Expression.eq("state",
	// getPersonalstate(Personalstate.ACTIVE_CODE)))
	// .createCriteria("iprofessional_personal")
	// .add(Expression.eq("current", Boolean.TRUE))
	// .createCriteria("type_of_contract")
	// .add(Expression.eq("is_irbs", Boolean.TRUE))
	// .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

	crit.add(Expression.eq("deleted", Boolean.FALSE))
		.add(Expression.eq("state",
			getPersonalstate(Personalstate.ACTIVE_CODE)))
		.createCriteria("iprofessional_personal")
		.add(Expression.eq("current", Boolean.TRUE))
		.add(Expression.eq("payroll_institution",
			getPayroll_institution(irbCodeString)))
		.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

	crit.addOrder(Order.asc("surname1"));
	crit.addOrder(Order.asc("surname2"));

	int count = configurator != null ? configurator.addCriterions(crit) : 0;

	List<Personal> personals = (List<Personal>) crit.list();

	Set<String> personalcodes = new HashSet<String>();
	List<Personal> toRemove = new ArrayList();
	for (Personal p : personals) {
	    if (personalcodes.contains(p.getPersonalcode())) {
		toRemove.add(p);
	    } else {
		personalcodes.add(p.getPersonalcode());
	    }
	}

	for (Personal p : toRemove) {
	    personals.remove(p);
	    // count--;
	}

	Pair<Integer, List<Personal>> pair = new Pair<Integer, List<Personal>>(
		personals.size(), personals);

	return pair;
    }

    public static Pair<Integer, Pair<List<Personal>, Map<String, String[]>>> ObtainPersonalWithContractAndOrderMap(
	    Usuario usuario, ListConfigurator configurator) {

	Criteria crit = HibernateUtil.getSession().createCriteria(
		Personal.class);

	crit.add(Expression.eq("deleted", Boolean.FALSE))
		.add(Expression.eq("state",
			getPersonalstate(Personalstate.ACTIVE_CODE)))
		.createCriteria("iprofessional_personal")
		.add(Expression.eq("current", Boolean.TRUE))
		.createCriteria("type_of_contract")
		.add(Expression.eq("is_irbs", Boolean.TRUE))
		.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

	int count = configurator.addCriterions(crit, true);

	List<Personal> personals = (List<Personal>) crit.list();

	// REMOVE DUPLICATES IF THERE ARE
	Set<String> personalcodes = new HashSet<String>();
	List<Personal> toRemove = new ArrayList();
	for (Personal p : personals) {
	    if (personalcodes.contains(p.getPersonalcode())) {
		toRemove.add(p);
	    } else {
		personalcodes.add(p.getPersonalcode());
	    }
	}

	for (Personal p : toRemove) {
	    personals.remove(p);
	    count--;
	}
	// END OF REMOVE DUPLICATES

	// Pair<Integer, List<Personal>> pair = new Pair<Integer,
	// List<Personal>>(
	// count, personals);
	//
	// return pair;

	crit.setFirstResult(0);
	crit.setMaxResults(5000);

	List<Personal> allPersonals = (List<Personal>) crit.list();

	Map<String, String[]> map = new HashMap<String, String[]>();

	for (int i = 0; i < allPersonals.size(); i++) {
	    String current = allPersonals.get(i).getCode();
	    String next = i == allPersonals.size() - 1 ? null : allPersonals
		    .get(i + 1).getCode();
	    String previous = i == 0 ? null : allPersonals.get(i - 1).getCode();

	    // TREAT DUPLICATES
	    if (!current.equals(previous)) {
		map.put(current, new String[] { previous, next });
	    } else {
		String[] pair = map.get(current);
		map.put(current, new String[] { pair[0], next });
	    }
	    // END OF TREAT DUPLICATES
	}

	Pair<Integer, Pair<List<Personal>, Map<String, String[]>>> pair = new Pair<Integer, Pair<List<Personal>, Map<String, String[]>>>(
		count, new Pair<List<Personal>, Map<String, String[]>>(
			personals, map));

	return pair;
    }

    public static Pair<Integer, List<Personal>> ObtainPersonalWithContractAboutToExpire(
	    Usuario usuario, ListConfigurator configurator) {

	Criteria crit = HibernateUtil.getSession().createCriteria(
		Personal.class);

	Calendar now = Calendar.getInstance();
	Calendar endOfPeriod = (Calendar) now.clone();

	Integer interval = 30;

	try {
	    interval = Integer
		    .parseInt(MAINCONFIG.getString("interval").trim());
	} catch (Exception e) {
	}

	endOfPeriod.add(Calendar.DAY_OF_MONTH, interval);

	crit.add(Expression.eq("deleted", Boolean.FALSE))
		.add(Expression.eq("state",
			getPersonalstate(Personalstate.ACTIVE_CODE)))
		.createCriteria("iprofessional_personal")
		.add(Expression.eq("current", Boolean.TRUE))
		.add(Expression.isNotNull("end_date"))
		.add(Expression.between("end_date", now.getTime(),
			endOfPeriod.getTime()))
		.createCriteria("type_of_contract")
		.add(Expression.eq("is_irbs", Boolean.TRUE))
		.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

	int count = configurator.addCriterions(crit);

	List<Personal> personals = (List<Personal>) crit.list();

	Pair<Integer, List<Personal>> pair = new Pair<Integer, List<Personal>>(
		count, personals);

	return pair;
    }

    public static Pair<Integer, List<Personal>> ObtainPersonalWithGrant(
	    Usuario usuario, ListConfigurator configurator) {

	Criteria crit = HibernateUtil.getSession().createCriteria(
		Personal.class);

	crit.add(Expression.eq("deleted", Boolean.FALSE))
		.add(Expression.eq("state",
			getPersonalstate(Personalstate.ACTIVE_CODE)))
		.createCriteria("igrant_concession_personal")
		.add(Expression.eq("current", Boolean.TRUE))
		.createCriteria("grant")
		.add(Expression.eq("is_irbs", Boolean.TRUE))
		.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

	int count = configurator.addCriterions(crit);

	List<Personal> personals = (List<Personal>) crit.list();

	Set<String> personalcodes = new HashSet<String>();
	List<Personal> toRemove = new ArrayList();
	for (Personal p : personals) {
	    if (personalcodes.contains(p.getPersonalcode())) {
		toRemove.add(p);
	    } else {
		personalcodes.add(p.getPersonalcode());
	    }
	}

	for (Personal p : toRemove) {
	    personals.remove(p);
	    count--;
	}

	Pair<Integer, List<Personal>> pair = new Pair<Integer, List<Personal>>(
		count, personals);

	return pair;
    }

    public static Pair<Integer, Pair<List<Personal>, Map<String, String[]>>> ObtainPersonalWithGrantAndOrderMap(
	    Usuario usuario, ListConfigurator configurator) {

	Criteria crit = HibernateUtil.getSession().createCriteria(
		Personal.class);

	crit.add(Expression.eq("deleted", Boolean.FALSE))
		.add(Expression.eq("state",
			getPersonalstate(Personalstate.ACTIVE_CODE)))
		.createCriteria("igrant_concession_personal")
		.add(Expression.eq("current", Boolean.TRUE))
		.createCriteria("grant")
		.add(Expression.eq("is_irbs", Boolean.TRUE))
		.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

	int count = configurator.addCriterions(crit, true);

	List<Personal> personals = (List<Personal>) crit.list();

	Set<String> personalcodes = new HashSet<String>();
	List<Personal> toRemove = new ArrayList();
	for (Personal p : personals) {
	    if (personalcodes.contains(p.getPersonalcode())) {
		toRemove.add(p);
	    } else {
		personalcodes.add(p.getPersonalcode());
	    }
	}

	for (Personal p : toRemove) {
	    personals.remove(p);
	    count--;
	}

	crit.setFirstResult(0);
	crit.setMaxResults(5000);

	List<Personal> allPersonals = (List<Personal>) crit.list();

	Map<String, String[]> map = new HashMap<String, String[]>();

	for (int i = 0; i < allPersonals.size(); i++) {
	    String current = allPersonals.get(i).getCode();
	    String next = i == allPersonals.size() - 1 ? null : allPersonals
		    .get(i + 1).getCode();
	    String previous = i == 0 ? null : allPersonals.get(i - 1).getCode();

	    // TREAT DUPLICATES
	    if (!current.equals(previous)) {
		map.put(current, new String[] { previous, next });
	    } else {
		String[] pair = map.get(current);
		map.put(current, new String[] { pair[0], next });
	    }
	    // END OF TREAT DUPLICATES
	}

	Pair<Integer, Pair<List<Personal>, Map<String, String[]>>> pair = new Pair<Integer, Pair<List<Personal>, Map<String, String[]>>>(
		count, new Pair<List<Personal>, Map<String, String[]>>(
			personals, map));

	return pair;
    }

    public static Pair<Integer, List<Personal>> ObtainPersonalWithGrantAboutToExpire(
	    Usuario usuario, ListConfigurator configurator) {

	Criteria crit = HibernateUtil.getSession().createCriteria(
		Personal.class);

	Calendar now = Calendar.getInstance();
	Calendar endOfPeriod = (Calendar) now.clone();

	Integer interval = 30;

	try {
	    interval = Integer
		    .parseInt(MAINCONFIG.getString("interval").trim());
	} catch (Exception e) {
	}

	endOfPeriod.add(Calendar.DAY_OF_MONTH, interval);

	crit.add(Expression.eq("deleted", Boolean.FALSE))
		.add(Expression.eq("state",
			getPersonalstate(Personalstate.ACTIVE_CODE)))
		.createCriteria("igrant_concession_personal")
		.add(Expression.eq("current", Boolean.TRUE))
		.add(Expression.isNotNull("end_date"))
		.add(Expression.between("end_date", now.getTime(),
			endOfPeriod.getTime())).createCriteria("grant")
		.add(Expression.eq("is_irbs", Boolean.TRUE))
		.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

	int count = configurator.addCriterions(crit);

	List<Personal> personals = (List<Personal>) crit.list();

	Pair<Integer, List<Personal>> pair = new Pair<Integer, List<Personal>>(
		count, personals);

	return pair;
    }

    public static Pair<Integer, List<Personal>> ObtainOtherPersonal(
	    Usuario usuario, ListConfigurator configurator) {

	Criteria crit = HibernateUtil.getSession().createCriteria(
		Notirb_active_personal.class);

	int count = configurator.addCriterions(crit);

	List<Personal> personals = (List<Personal>) crit.list();

	Pair<Integer, List<Personal>> pair = new Pair<Integer, List<Personal>>(
		count, personals);

	return pair;
    }

    public static Pair<Integer, Pair<List<Personal>, Map<String, String[]>>> ObtainOtherPersonalAndOrderMap(
	    Usuario usuario, ListConfigurator configurator) {

	Criteria crit = HibernateUtil.getSession().createCriteria(
		Notirb_active_personal.class);

	int count = configurator.addCriterions(crit, true);

	List<Personal> personals = (List<Personal>) crit.list();

	crit.setFirstResult(0);
	crit.setMaxResults(5000);

	List<Notirb_active_personal> allPersonals = (List<Notirb_active_personal>) crit
		.list();

	Map<String, String[]> map = new HashMap<String, String[]>();

	for (int i = 0; i < allPersonals.size(); i++) {
	    String current = allPersonals.get(i).getCode();
	    String next = i == allPersonals.size() - 1 ? null : allPersonals
		    .get(i + 1).getCode();
	    String previous = i == 0 ? null : allPersonals.get(i - 1).getCode();

	    map.put(current, new String[] { previous, next });
	}

	Pair<Integer, Pair<List<Personal>, Map<String, String[]>>> pair = new Pair<Integer, Pair<List<Personal>, Map<String, String[]>>>(
		count, new Pair<List<Personal>, Map<String, String[]>>(
			personals, map));

	return pair;
    }

    public static Pair<Integer, List<Irbholidayinfo>> ObtainAllIrbholidayinfoForYear(
	    Usuario usuario, int year, ListConfigurator configurator) {

	Criteria crit = HibernateUtil.getSession().createCriteria(
		Irbholidayinfo.class);

	crit.add(Expression.eq("year", year));

	int count = configurator.addCriterions(crit);

	List<Irbholidayinfo> irbholidayinfo = crit.list();

	Pair<Integer, List<Irbholidayinfo>> pair = new Pair<Integer, List<Irbholidayinfo>>(
		count, irbholidayinfo);

	return pair;

    }

    public static Irbholiday Create_Irbholiday(Usuario usuario,
	    Irbholiday TOIrbholiday) {

	HibernateUtil.beginTransaction();

	Irbholiday irbholiday = new Irbholiday();

	boolean personalIsDefined = false;

	if (TOIrbholiday.getPersonal() != null
		&& TOIrbholiday.getPersonal().getPersonalcode() != null) {
	    // if professional_personal is defined we replace the
	    // professional_personal in the DTO with its current state in the
	    // DB.
	    personalIsDefined = true;

	    TOIrbholiday.setPersonal(getPersonal(TOIrbholiday.getPersonal()
		    .getPersonalcode()));
	}

	try {
	    IdentifyManager_Plain im = IdentifyManager_Plain.singleton();

	    irbholiday.setIrbholidaycode(im.getId(TOIrbholiday));
	} catch (identifyException ie) {

	    log.error("Error en asignaciï¿½n de nuevo id en irbholiday", ie);
	    throw new Error(ie.getMessage());
	}

	irbholiday.setInitialdate(TOIrbholiday.getInitialdate());
	irbholiday.setEnddate(TOIrbholiday.getEnddate());
	irbholiday.setType(TOIrbholiday.getType());
	irbholiday.setStatus(TOIrbholiday.getStatus());
	irbholiday.setHolidays(TOIrbholiday.getHolidays());
	irbholiday.setPreviousyearholidays(TOIrbholiday
		.getPreviousyearholidays());
	irbholiday.setAps(TOIrbholiday.getAps());
	irbholiday.setDescription(TOIrbholiday.getDescription());
	irbholiday.setValidationcode(TOIrbholiday.getValidationcode());

	HibernateUtil.getSession().save(irbholiday);

	if (personalIsDefined) {

	    if (irbholiday.getPersonal() != null) {

		irbholiday.getPersonal().removeIirbholiday(irbholiday);
	    }

	    if (TOIrbholiday.getPersonal() != null) {

		TOIrbholiday.getPersonal().addIirbholiday(irbholiday);
	    }

	    irbholiday.setPersonal(TOIrbholiday.getPersonal());

	}

	if (usuario != null)
	    CreateCreationAuditmessage(usuario, irbholiday);

	HibernateUtil.commitTransaction();

	return irbholiday;
    }

    // funcio que ens retorna cert si hi ha algun irbholiday entre les dates per
    // a la persona amb personalcode
    private static boolean checkIrbholidayBetweenDates(String personalcode,
	    Date initialdate, Date enddate) {

	Criteria crit = HibernateUtil.getSession().createCriteria(
		Irbholiday.class);

	crit.add(Expression.between("initialdate", initialdate, enddate))
		.createCriteria("personal").add(Expression.idEq(personalcode));

	List list = crit.list();

	return list != null && list.size() > 0;

    }

    /*
     * private static void UpdateIrbholidayinfoFromCreate(Usuario usuario,
     * Irbholiday irbholiday, Irbholidayinfo irbholidayinfo) {
     * 
     * HibernateUtil.beginTransaction();
     * 
     * if(irbholiday.getStatus() == Irbholiday.STATUS_APROVAT) {
     * if(irbholiday.getType() == Irbholiday.TYPE_VACANCES) {
     * irbholidayinfo.setPreviousyearholidays
     * (irbholidayinfo.getPreviousyearholidays() -
     * irbholiday.getPreviousyearholidays());
     * irbholidayinfo.setHolidays(irbholidayinfo.getHolidays() -
     * irbholiday.getHolidays());
     * 
     * Irbholidayinfo next = ObtainNextIrbholidayinfoFromIrbholidayinfo(usuario,
     * irbholidayinfo);
     * next.setPreviousyearholidays(Math.max(next.getPreviousyearholidays() -
     * irbholiday.getHolidays(), 0)); } else {
     * irbholidayinfo.setAps(irbholidayinfo.getAps() - irbholiday.getAps()); } }
     * else { if(irbholiday.getType() == Irbholiday.TYPE_VACANCES) {
     * irbholidayinfo.setPendingholidays(irbholidayinfo.getPendingholidays() +
     * irbholiday.getHolidays() + irbholiday.getPreviousyearholidays()); } else
     * { irbholidayinfo.setPendingaps(irbholidayinfo.getPendingaps() +
     * irbholiday.getAps()); } }
     * 
     * if(irbholiday.getPreviousyearholidays() > 0)
     * irbholidayinfo.setHolidaysinlimit(irbholidayinfo.getHolidaysinlimit() +
     * irbholiday.getHolidays());
     * 
     * HibernateUtil.getSession().save(irbholidayinfo);
     * 
     * HibernateUtil.commitTransaction(); }
     */
    /*
     * private static void UpdateIrbholidayinfoFromCreate(Usuario usuario,
     * Irbholiday irbholiday, Irbholidayinfo irbholidayinfo, Irbholidayinfo
     * irbholidayinfo2) {
     * 
     * HibernateUtil.beginTransaction();
     * 
     * Date dend = getEndYear(irbholidayinfo.getYear());
     * 
     * int diff1 = UseCaseUtils.getDiffDays(irbholiday.getInitialdate(), dend);
     * 
     * if(irbholiday.getStatus() == Irbholiday.STATUS_APROVAT) {
     * if(irbholiday.getType() == Irbholiday.TYPE_VACANCES) {
     * irbholidayinfo.setHolidays(irbholidayinfo.getHolidays() - diff1);
     * irbholidayinfo2
     * .setPreviousyearholidays(Math.max(irbholidayinfo2.getPreviousyearholidays
     * () - diff1 - irbholiday.getPreviousyearholidays(), 0));
     * irbholidayinfo2.setHolidays(irbholidayinfo2.getHolidays() -
     * (irbholiday.getHolidays() - diff1));
     * 
     * Irbholidayinfo next = ObtainNextIrbholidayinfoFromIrbholidayinfo(null,
     * irbholidayinfo2);
     * next.setPreviousyearholidays(Math.max(next.getPreviousyearholidays() -
     * (irbholiday.getHolidays() - diff1), 0)); } else {
     * irbholidayinfo.setAps(irbholidayinfo.getAps() - diff1);
     * irbholidayinfo2.setAps(irbholidayinfo2.getAps() - (irbholiday.getAps() -
     * diff1)); } } else { if(irbholiday.getType() == Irbholiday.TYPE_VACANCES)
     * { irbholidayinfo.setPendingholidays(irbholidayinfo.getPendingholidays() +
     * diff1);
     * irbholidayinfo2.setPendingholidays(irbholidayinfo2.getPendingholidays() +
     * ((irbholiday.getHolidays()-diff1)+irbholiday.getPreviousyearholidays()));
     * } else { irbholidayinfo.setPendingaps(irbholidayinfo.getPendingaps() +
     * diff1); irbholidayinfo2.setPendingaps(irbholidayinfo2.getPendingaps() +
     * (irbholiday.getAps() - diff1)); } }
     * 
     * if(irbholiday.getPreviousyearholidays() > 0)
     * irbholidayinfo2.setHolidaysinlimit(irbholidayinfo2.getHolidaysinlimit() +
     * (irbholiday.getHolidays() - diff1));
     * 
     * HibernateUtil.getSession().save(irbholidayinfo);
     * HibernateUtil.getSession().save(irbholidayinfo2);
     * 
     * HibernateUtil.commitTransaction(); }
     */

    public static Irbholiday DeleteIrbholiday(Usuario usuario,
	    String irbholidaycode) throws HolidaysException {

	HibernateUtil.beginTransaction();

	Irbholiday irbholiday = getIrbholiday(irbholidaycode);

	// CheckHolidays(irbholiday);

	int yearIntCurrent = getCurrentYearForHolidays();

	int yearInt = irbholiday.getInitialdate() != null ? (irbholiday
		.getInitialdate().getYear() + 1900) : 0;

	boolean recalculate = true;

	if (irbholiday.getType() == Irbholiday.TYPE_FESTIU
		|| irbholiday.getType() == Irbholiday.TYPE_LIMIT_VANCANCES_ANY_ANTERIOR) {
	    if (yearInt < yearIntCurrent) {
		throw new HolidaysException("error.date");
	    } else if (yearInt > yearIntCurrent) {
		// caso particular, permitimos entrar los festivos de aï¿½os
		// venideros.
		recalculate = false;
	    }
	} else if (yearInt != yearIntCurrent && yearInt != (yearIntCurrent - 1)) {
	    throw new HolidaysException("error.date");
	}

	HibernateUtil.getSession().delete(irbholiday);

	if (irbholiday.getPersonal() != null) {
	    Irbholidayinfo irbholidayinfo = ObtainIrbholidayinfoFromIrbholiday(
		    usuario, irbholiday).get(0);
	    RecalculateHolidaysFromPersonal(irbholidayinfo);
	} else {
	    if (recalculate) {
		RecalculateAllHolidays(irbholiday);
	    }

	}

	CreateRemovealAuditmessage(usuario, irbholiday);

	HibernateUtil.commitTransaction();

	return irbholiday;

    }

    /*
     * private static void CheckAfectedHolidays(Irbholiday festiu) throws
     * HolidaysException {
     * 
     * List<Irbholiday> list = ObtainAllIrbholidaysOnFestiu(festiu);
     * 
     * if(list!=null && list.size()>0) { for(Irbholiday irbholiday : list) {
     * 
     * Irbholidayinfo irbholidayinfo = ObtainIrbholidayinfoFromIrbholiday(null,
     * irbholiday).get(0);
     * 
     * if(irbholiday.getStatus() == Irbholiday.STATUS_APROVAT) {
     * 
     * irbholidayinfo.setHolidays(irbholidayinfo.getHolidays() +
     * irbholiday.getHolidays());
     * irbholidayinfo.setPreviousyearholidays(irbholidayinfo
     * .getPreviousyearholidays() + irbholiday.getPreviousyearholidays());
     * irbholidayinfo.setAps(irbholidayinfo.getAps() + irbholiday.getAps());
     * 
     * } else {
     * 
     * irbholidayinfo.setPendingholidays(irbholidayinfo.getPendingholidays() -
     * (irbholiday.getHolidays()+irbholiday.getPreviousyearholidays()));
     * irbholidayinfo.setPendingaps(irbholidayinfo.getPendingaps() -
     * irbholiday.getAps()); } CheckHolidays(irbholiday); } } }
     */

    /*
     * private static List<Irbholiday> ObtainAllIrbholidaysOnFestiu(Irbholiday
     * festiu) {
     * 
     * Criteria crit
     * =HibernateUtil.getSession().createCriteria(Irbholiday.class);
     * 
     * Date din = festiu.getInitialdate(); Date dfi = festiu.getEnddate();
     * 
     * crit.add(Expression.le("initialdate", din)).add(Expression.ge("enddate",
     * dfi));
     * 
     * List<Irbholiday> list = crit.list();
     * 
     * return list; }
     */

    /*
     * private static void UpdateIrbholidayinfoFromDelete(Usuario usuario,
     * Irbholiday irbholiday) {
     * 
     * HibernateUtil.beginTransaction();
     * 
     * List<Irbholidayinfo> list = ObtainIrbholidayinfoFromIrbholiday(usuario,
     * irbholiday);
     * 
     * Irbholidayinfo irbholidayinfo = list.get(0);
     * 
     * // dins del mateix any if(list.size() == 1) { if(irbholiday.getStatus()
     * == Irbholiday.STATUS_APROVAT) { if(irbholiday.getType() ==
     * Irbholiday.TYPE_VACANCES) {
     * 
     * Irbholidayinfo prev =
     * ObtainPreviousIrbholidayinfoFromIrbholidayinfo(usuario, irbholidayinfo);
     * 
     * // sumem el numero de vacances que em esborrat
     * irbholidayinfo.setHolidays(irbholidayinfo.getHolidays() +
     * irbholiday.getHolidays()); // sumem el numero de vacances del any
     * anterior que hem esborrat (????)
     * irbholidayinfo.setPreviousyearholidays(Math
     * .min(irbholidayinfo.getPreviousyearholidays() +
     * irbholiday.getPreviousyearholidays(), prev.getHolidays()));
     * 
     * Irbholidayinfo next = ObtainNextIrbholidayinfoFromIrbholidayinfo(null,
     * irbholidayinfo);
     * next.setPreviousyearholidays(next.getPreviousyearholidays() +
     * (irbholiday.getHolidays() - next.getHolidaysinlimit()));
     * 
     * if(irbholiday.getPreviousyearholidays() > 0)
     * irbholidayinfo.setHolidaysinlimit(irbholidayinfo.getHolidaysinlimit() -
     * irbholiday.getHolidays());
     * 
     * } else { irbholidayinfo.setAps(irbholidayinfo.getAps() +
     * irbholiday.getAps()); } } else { if(irbholiday.getType() ==
     * Irbholiday.TYPE_VACANCES) {
     * irbholidayinfo.setPendingholidays(irbholidayinfo.getPendingholidays() -
     * (irbholiday.getHolidays()+irbholiday.getPreviousyearholidays())); } else
     * { irbholidayinfo.setPendingaps(irbholidayinfo.getPendingaps() -
     * irbholiday.getAps()); } } // entre dos anys } else {
     * 
     * Irbholidayinfo irbholidayinfo2 = list.get(1);
     * 
     * Date dend = getEndYear(irbholidayinfo.getYear());
     * 
     * int diff1 = UseCaseUtils.getDiffDays(irbholiday.getInitialdate(), dend);
     * 
     * if(irbholiday.getStatus() == Irbholiday.STATUS_APROVAT) {
     * if(irbholiday.getType() == Irbholiday.TYPE_VACANCES) {
     * 
     * irbholidayinfo.setHolidays(irbholidayinfo.getHolidays() + diff1);
     * irbholidayinfo2
     * .setPreviousyearholidays(Math.min(irbholidayinfo2.getPreviousyearholidays
     * () + diff1 + (irbholiday.getPreviousyearholidays() -
     * irbholidayinfo2.getHolidaysinlimit()), irbholidayinfo.getHolidays()));
     * irbholidayinfo2.setHolidays(irbholidayinfo2.getHolidays() +
     * (irbholiday.getHolidays() - diff1));
     * 
     * Irbholidayinfo next = ObtainNextIrbholidayinfoFromIrbholidayinfo(null,
     * irbholidayinfo2);
     * next.setPreviousyearholidays(next.getPreviousyearholidays() +
     * (irbholiday.getHolidays() - diff1 - next.getHolidaysinlimit()));
     * 
     * } else { irbholidayinfo.setAps(irbholidayinfo.getAps() + diff1);
     * irbholidayinfo2.setAps(irbholidayinfo2.getAps() + (irbholiday.getAps() -
     * diff1)); } } else { if(irbholiday.getType() == Irbholiday.TYPE_VACANCES)
     * { irbholidayinfo.setPendingholidays(irbholidayinfo.getPendingholidays() -
     * diff1);
     * irbholidayinfo2.setPendingholidays(irbholidayinfo2.getPendingholidays() -
     * ((irbholiday.getHolidays()-diff1)+irbholiday.getPreviousyearholidays()));
     * } else { irbholidayinfo.setPendingaps(irbholidayinfo.getPendingaps() -
     * diff1); irbholidayinfo2.setPendingaps(irbholidayinfo2.getPendingaps() -
     * (irbholiday.getAps() - diff1)); } }
     * 
     * HibernateUtil.getSession().save(irbholidayinfo2); }
     * 
     * HibernateUtil.getSession().save(irbholidayinfo);
     * 
     * HibernateUtil.commitTransaction();
     * 
     * }
     */

    protected static Irbholiday getIrbholiday(String irbholidaycode) {

	Irbholiday irbholiday = (Irbholiday) HibernateUtil.getSession().get(
		Irbholiday.class, irbholidaycode);
	return irbholiday;
    }

    public static Irbholiday ObtainIrbholiday(Usuario usuario,
	    String irbholidaycode) {
	return getIrbholiday(irbholidaycode);
    }

    public static Irbholiday UpdateIrbholiday(Usuario usuario,
	    Irbholiday TOIrbholiday) throws HolidaysException {

	boolean recalculate = true;

	if (TOIrbholiday.getType() == Irbholiday.TYPE_FESTIU
		|| TOIrbholiday.getType() == Irbholiday.TYPE_LIMIT_VANCANCES_ANY_ANTERIOR) {
	    int currentYearInt = getCurrentYearForHolidays();
	    if (TOIrbholiday.getInitialdate() == null) {
		throw new HolidaysException("error.date");
	    }
	    int yearInt = TOIrbholiday.getInitialdate().getYear() + 1900;
	    if (yearInt < currentYearInt) {
		throw new HolidaysException("error.date");
	    } else if (yearInt > currentYearInt) {
		recalculate = false;
	    }
	} else {
	    CheckHolidays(TOIrbholiday);
	}

	HibernateUtil.beginTransaction();

	Irbholiday irbholiday = getIrbholiday(TOIrbholiday.getIrbholidaycode());

	boolean personalIsDefined = false;

	if (TOIrbholiday.getPersonal() != null
		&& TOIrbholiday.getPersonal().getPersonalcode() != null) {
	    // if professional_personal is defined we replace the
	    // professional_personal in the DTO with its current state in the
	    // DB.
	    personalIsDefined = true;

	    TOIrbholiday.setPersonal(getPersonal(TOIrbholiday.getPersonal()
		    .getPersonalcode()));
	}

	irbholiday.setInitialdate(TOIrbholiday.getInitialdate());
	irbholiday.setEnddate(TOIrbholiday.getEnddate());
	irbholiday.setType(TOIrbholiday.getType());
	irbholiday.setStatus(TOIrbholiday.getStatus());
	irbholiday.setDescription(TOIrbholiday.getDescription());

	HibernateUtil.getSession().evict(irbholiday);
	HibernateUtil.getSession().update(irbholiday);

	if (personalIsDefined) {

	    if (irbholiday.getPersonal() != null) {

		irbholiday.getPersonal().removeIirbholiday(irbholiday);
	    }

	    if (TOIrbholiday.getPersonal() != null) {

		TOIrbholiday.getPersonal().addIirbholiday(irbholiday);
	    }

	    irbholiday.setPersonal(TOIrbholiday.getPersonal());
	}

	if (irbholiday.getPersonal() != null) {
	    Irbholidayinfo irbholidayinfo = ObtainIrbholidayinfoFromIrbholiday(
		    usuario, irbholiday).get(0);
	    RecalculateHolidaysFromPersonal(irbholidayinfo);
	} else {
	    if (recalculate) {
		RecalculateAllHolidays(irbholiday);
	    }

	}

	CreateModificationAuditmessage(usuario, irbholiday);

	HibernateUtil.commitTransaction();

	return irbholiday;
    }

    /** Funcio que obte el numero de dies festius entre 2 dates */
    @SuppressWarnings("deprecation")
    static int ObtainFestiusBetweenDates(Date initialdate, Date enddate) {

	Criteria crit = HibernateUtil.getSession().createCriteria(
		Irbholiday.class);

	crit.add(Expression.between("initialdate", initialdate, enddate)).add(
		Expression.eq("type", Irbholiday.TYPE_FESTIU));

	List<Irbholiday> festius = (List<Irbholiday>) crit.list();

	// Fines de semana:

	// int totalDias = UseCaseUtils.getDiffDays(initialdate, enddate);

	int totalSabadosDomingos = UseCaseUtils.getNumDaysWeekends(initialdate,
		enddate);

	for (Irbholiday h : festius) {
	    int diaSemana = h.getInitialdate().getDay();
	    if (diaSemana == 0 || diaSemana == 6) {
		totalSabadosDomingos--;
	    }
	}

	return totalSabadosDomingos + festius.size();
    }

    /** funcio que obte tots els dies festius d'un any **/
    public static Pair<Integer, List<Irbholiday>> ObtainIrbholidayFestiu(
	    Usuario usuario, int year, ListConfigurator configurator) {

	Criteria crit = HibernateUtil.getSession().createCriteria(
		Irbholiday.class);

	Date dIn = (new GregorianCalendar(year, 0, 1)).getTime();
	Date dFi = (new GregorianCalendar(year, 11, 31)).getTime();

	crit.add(Expression.between("initialdate", dIn, dFi)).add(
		Expression.eq("type", Irbholiday.TYPE_FESTIU));

	int count = configurator.addCriterions(crit);

	List<Irbholiday> irbholiday = (List<Irbholiday>) crit.list();

	Pair<Integer, List<Irbholiday>> pair = new Pair<Integer, List<Irbholiday>>(
		count, irbholiday);

	return pair;

    }

    /**
     * funcio que retorna el dia limit de vacances de l'any anterior, si estï¿½
     * definit
     */
    public static Irbholiday ObtainIrbholidayLimitVacances(Usuario usuario,
	    Calendar year) {

	Criteria crit = HibernateUtil.getSession().createCriteria(
		Irbholiday.class);

	Date dIn = year.getTime();
	Date dFi = UseCaseUtils.getEndYearDate(year);

	crit.add(Expression.between("initialdate", dIn, dFi)).add(
		Expression.eq("type",
			Irbholiday.TYPE_LIMIT_VANCANCES_ANY_ANTERIOR));

	List<Irbholiday> list = crit.list();

	if (list != null && list.size() > 0)
	    return list.get(0);
	else
	    return null;
    }

    /** funcio que retorna totes les vacances o aps d'un any d'una persona */
    public static Pair<Integer, List<Irbholiday>> ObtainAllIIrbholidayFromPersonal(
	    Usuario usuario, String personalcode, Calendar year,
	    ListConfigurator configurator) {

	Criteria crit = HibernateUtil.getSession().createCriteria(
		Irbholiday.class);

	Date dIn = year.getTime();
	Date dFi = UseCaseUtils.getEndYearDate(year);

	crit.add(
		Expression.or(Expression.between("initialdate", dIn, dFi),
			Expression.between("enddate", dIn, dFi)))
		.createCriteria("personal").add(Expression.idEq(personalcode));

	int count = configurator.addCriterions(crit);

	List<Irbholiday> list = crit.list();

	return new Pair<Integer, List<Irbholiday>>(count, list);
    }

    public static Irbholidayinfo Create_Irbholidayinfo(Usuario usuario,
	    Irbholidayinfo TOIrbholidayinfo) {

	HibernateUtil.beginTransaction();

	Irbholidayinfo irbholidayinfo = new Irbholidayinfo();

	boolean personalIsDefined = false;

	if (TOIrbholidayinfo.getPersonal() != null
		&& TOIrbholidayinfo.getPersonal().getPersonalcode() != null) {
	    // if professional_personal is defined we replace the
	    // professional_personal in the DTO with its current state in the
	    // DB.
	    personalIsDefined = true;

	    TOIrbholidayinfo.setPersonal(getPersonal(TOIrbholidayinfo
		    .getPersonal().getPersonalcode()));
	}

	try {
	    IdentifyManager_Plain im = IdentifyManager_Plain.singleton();

	    irbholidayinfo.setIrbholidayinfocode(im.getId(TOIrbholidayinfo));
	} catch (identifyException ie) {

	    log.error("Error en asignaciï¿½n de nuevo id en irbholidayinfo", ie);
	    throw new Error(ie.getMessage());
	}

	irbholidayinfo.setYear(TOIrbholidayinfo.getYear());
	irbholidayinfo.setHolidays(TOIrbholidayinfo.getHolidays());
	irbholidayinfo
		.setPendingholidays(TOIrbholidayinfo.getPendingholidays());
	irbholidayinfo.setAps(TOIrbholidayinfo.getAps());
	irbholidayinfo.setPendingaps(TOIrbholidayinfo.getPendingaps());
	irbholidayinfo.setPreviousyearholidays(TOIrbholidayinfo
		.getPreviousyearholidays());
	irbholidayinfo
		.setHolidaysforyear(TOIrbholidayinfo.getHolidaysforyear());
	irbholidayinfo.setApsforyear(TOIrbholidayinfo.getApsforyear());
	irbholidayinfo.setPendingpreviousyearholidays(TOIrbholidayinfo
		.getPendingpreviousyearholidays());
	irbholidayinfo.setPreviousyearholidaysforyear(TOIrbholidayinfo
		.getPreviousyearholidaysforyear());

	HibernateUtil.getSession().save(irbholidayinfo);

	if (personalIsDefined) {

	    if (irbholidayinfo.getPersonal() != null) {

		irbholidayinfo.getPersonal().removeIirbholidayinfo(
			irbholidayinfo);
	    }

	    if (TOIrbholidayinfo.getPersonal() != null) {

		TOIrbholidayinfo.getPersonal().addIirbholidayinfo(
			irbholidayinfo);
	    }

	    irbholidayinfo.setPersonal(TOIrbholidayinfo.getPersonal());
	}

	HibernateUtil.commitTransaction();

	return irbholidayinfo;
    }

    /** funcio que crea un irbholidayinfo amb els atributs per defecte */
    public static Irbholidayinfo CreateOrModifyIrbholidayinfo(Usuario usuario,
	    Professional professional) {
	return CreateOrModifyIrbholidayinfo(usuario, professional,
		getCurrentYearForHolidays(), -1);
    }

    public static Irbholidayinfo CreateOrModifyIrbholidayinfo(Usuario usuario,
	    Professional professional, int year, int previousYearHolidays) {

	Date[] dateRange = UseCaseUtils.limitDateRangeToYear(new Date[] {
		professional.getStart_date(), professional.getEnd_date() },
		year);
	if (dateRange[0] == null && dateRange[1] == null)
	    return null;

	Personal personal = professional.getProfessional_personal();

	Set<Irbholidayinfo> infoVacaciones = personal.getIirbholidayinfo();

	Irbholidayinfo currentInfo = null;
	for (Irbholidayinfo info : infoVacaciones) {
	    if (info.getYear() == year) {
		currentInfo = info;
		break;
	    }
	}

	boolean nuevo = false;
	if (currentInfo == null) {
	    currentInfo = new Irbholidayinfo();
	    currentInfo.setPersonal(personal);
	    currentInfo.setYear(year);

	    Set<Professional> pros = personal.getIprofessional_personal();
	    Vector<Date[]> rangos = new Vector<Date[]>();

	    String irbCodeString = MAINCONFIG
		    .getString("irbPayrollInstitutionCode");

	    for (Professional pro : pros) {
		Date[] rango = UseCaseUtils.limitDateRangeToYear(new Date[] {
			pro.getStart_date(), pro.getEnd_date() }, year);
		if (pro != null
			&& pro.isDeleted() == false
			&& (rango[0] != null || rango[1] != null)
			&& (pro.getPayroll_institution() != null && pro
				.getPayroll_institution().getCode()
				.equals(irbCodeString))) {
		    rangos.add(rango);
		}
	    }

	    // boolean solapamiento = false;
	    // for (int i = 0; i < rangos.size() - 1; i++) {
	    // for (int j = i + 1; j < rangos.size(); j++) {
	    // if (UseCaseUtils.dateRangesOverlap(rangos.get(i), rangos.get(j)))
	    // {
	    // log.error("Dos registros de professional de la ficha de personal: "
	    // + personal.getCode() + " se solapan.");
	    // solapamiento = true;
	    // }
	    // }
	    // }

	    // ya no comprobamos el solapamiento. Segï¿½n Cristina Mï¿½ndez lo que
	    // hay que hacer es:
	    // coger la primera fecha en el aï¿½o y contar las vacaciones hasta el
	    // final del aï¿½o
	    // o sea: rango={primera fecha, 31/12}

	    Date fechaMasTemprana = null;
	    if (rangos.size() > 0) {
		fechaMasTemprana = rangos.get(0)[0];
	    }
	    for (int i = 1; i < rangos.size(); i++) {
		if (fechaMasTemprana != null
			&& fechaMasTemprana.compareTo(rangos.get(i)[0]) > 0)
		    fechaMasTemprana = rangos.get(i)[0];
	    }

	    if (fechaMasTemprana == null)
		fechaMasTemprana = (new GregorianCalendar(year, 0, 1))
			.getTime();

	    dateRange = new Date[] { fechaMasTemprana,
		    (new GregorianCalendar(year, 11, 31)).getTime() };

	    currentInfo.setHolidaysforyear(UseCaseUtils
		    .calculateHolidaysForYear(dateRange));
	    currentInfo.setApsforyear(UseCaseUtils
		    .calculateApsForYear(dateRange));

	    // if (solapamiento) {
	    // currentInfo.setHolidaysforyear(UseCaseUtils.calculateHolidaysForYear(dateRange));
	    // currentInfo.setApsforyear(UseCaseUtils.calculateApsForYear(dateRange));
	    // } else {
	    // int holidaysForYear = 0;
	    // int apsForYear = 0;
	    //
	    // for (Date[] rango : rangos) {
	    // holidaysForYear += UseCaseUtils.calculateHolidaysForYear(rango);
	    // apsForYear += UseCaseUtils.calculateApsForYear(rango);
	    // }
	    //
	    // currentInfo.setHolidaysforyear(holidaysForYear);
	    // currentInfo.setApsforyear(apsForYear);
	    // }

	    nuevo = true;
	}

	if (previousYearHolidays != -1) {
	    currentInfo.setPreviousyearholidaysforyear(previousYearHolidays);
	}

	if (nuevo) {
	    return Create_Irbholidayinfo(usuario, currentInfo);
	} else {
	    HibernateUtil.getSession().update(currentInfo);
	}

	return currentInfo;
    }

    public static Irbholidayinfo UpdateIrbholidayinfo(Usuario usuario,
	    Irbholidayinfo TOirbholidayinfo) throws HolidaysException {

	int yearInt = getCurrentYearForHolidays();

	if (TOirbholidayinfo.getYear() != yearInt) {
	    throw new HolidaysException("error.date");
	}

	HibernateUtil.beginTransaction();

	Irbholidayinfo irbholidayinfo = getIrbholidayinfo(TOirbholidayinfo
		.getIrbholidayinfocode());

	// if(TOirbholidayinfo.getHolidaysforyear() <
	// (irbholidayinfo.getHolidays()+irbholidayinfo.getPendingholidays())
	// || TOirbholidayinfo.getApsforyear() <
	// (irbholidayinfo.getAps()+irbholidayinfo.getPendingaps())
	// || TOirbholidayinfo.getPreviousyearholidaysforyear() <
	// (irbholidayinfo.getPreviousyearholidays() +
	// irbholidayinfo.getPendingpreviousyearholidays()))
	// throw new HolidaysException("error.holidayinfo");

	// nomes canviarem els parametres que es poden canviar
	irbholidayinfo
		.setHolidaysforyear(TOirbholidayinfo.getHolidaysforyear());
	irbholidayinfo.setApsforyear(TOirbholidayinfo.getApsforyear());
	// irbholidayinfo.setHolidays(TOirbholidayinfo.getHolidaysforyear());
	// irbholidayinfo.setAps(TOirbholidayinfo.getApsforyear());
	irbholidayinfo.setPreviousyearholidaysforyear(TOirbholidayinfo
		.getPreviousyearholidaysforyear());

	RecalculateHolidaysFromPersonal(irbholidayinfo);

	if (irbholidayinfo.getRemainingaps() < 0
		|| irbholidayinfo.getRemainingholidays() < 0) {
	    throw new HolidaysException("error.holidayinfo");
	}

	HibernateUtil.commitTransaction();

	return irbholidayinfo;
    }

    /**
     * funcio que obte el irbholidayinfo associat a una persona i any, si no
     * existeix la crea
     */
    public static Irbholidayinfo ObtainIrbholidayinfoFromPersonal(
	    Usuario usuario, String personalcode, Calendar year) {

	Criteria crit = HibernateUtil.getSession().createCriteria(
		Irbholidayinfo.class);

	crit.add(Expression.eq("year", year.get(Calendar.YEAR)))
		.createCriteria("personal").add(Expression.idEq(personalcode));

	List<Irbholidayinfo> list = crit.list();

	if (list == null || list.size() == 0) {
	    return null;
	    // return CreateIrbholidayinfo(usuario, personalcode, year);
	} else {

	    Irbholidayinfo info = list.get(0);
	    // try {
	    // Irbholiday limitVacances =
	    // UseCaseFacade.ObtainIrbholidayLimitVacances(null, year);
	    //
	    // year.set(Calendar.MONTH, Calendar.DECEMBER);
	    // year.set(Calendar.DAY_OF_MONTH, 31);
	    //
	    // Date limite = year.getTime();
	    //
	    // if( limitVacances != null && limitVacances.getInitialdate() !=
	    // null)
	    // {
	    // limite = limitVacances.getInitialdate();
	    // }
	    //
	    // Calendar today = Calendar.getInstance();
	    //
	    // today.add(Calendar.DAY_OF_MONTH, -1); //el dï¿½a de limite para
	    // gastas las vacaciones del aï¿½o anterior
	    // // es vï¿½lido asï¿½ que tenemos que comparar la fecha del dï¿½a
	    // anterior.
	    //
	    // if(limite.before( today.getTime() ))
	    // {
	    // info.setPreviousyearholidaysforyear(0);
	    // }
	    //
	    // } catch (InternalException e) {
	    //
	    // e.printStackTrace();
	    // }

	    return info;
	}
    }

    /**
     * funcio que retorna, si existeix, el irbholiday que esta enmig de l'any
     * especificat i el segï¿½ent
     */
    // private static Irbholiday ObtainIrbholidayBetweenYears(String
    // personalcode, Calendar year) {
    //
    // Criteria crit =
    // HibernateUtil.getSession().createCriteria(Irbholiday.class);
    //
    // Date dFi = UseCaseUtils.getEndYearDate(year);
    //
    // crit.add(Expression.le("initialdate", dFi)).add(Expression.gt("enddate",
    // dFi))
    // .createCriteria("personal").add(Expression.idEq(personalcode));
    //
    // List<Irbholiday> list = crit.list();
    //
    // if(list != null && list.size() > 0) return list.get(0);
    // else return null;
    // }

    /** funcio que retorna totes les vacances o aps d'un any d'una persona */
    private static List<Irbholiday> ObtainAllIIrbholidayFromPersonal(
	    String personalcode, Calendar year) {

	Criteria crit = HibernateUtil.getSession().createCriteria(
		Irbholiday.class);

	Date dIn = year.getTime();
	Date dFi = UseCaseUtils.getEndYearDate(year);

	crit.add(Expression.ge("initialdate", dIn))
		.add(Expression.le("enddate", dFi)).createCriteria("personal")
		.add(Expression.idEq(personalcode));

	crit.addOrder(Order.asc("initialdate"));

	List<Irbholiday> list = crit.list();

	if (list != null && list.size() > 0)
	    return list;
	else
	    return null;
    }

    protected static Irbholidayinfo getIrbholidayinfo(String irbholidayinfocode) {

	Irbholidayinfo irbholidayinfo = (Irbholidayinfo) HibernateUtil
		.getSession().get(Irbholidayinfo.class, irbholidayinfocode);
	return irbholidayinfo;
    }

    public static Irbholidayinfo ObtainIrbholidayinfo(Usuario usuario,
	    String irbholidayinfocode) {
	return getIrbholidayinfo(irbholidayinfocode);
    }

    public static Personal ObtainPersonalFromIrbholidayinfo(Usuario usuario,
	    String irbholidayinfocode) {

	Irbholidayinfo irbholidayinfo = getIrbholidayinfo(irbholidayinfocode);
	return irbholidayinfo.getPersonal();
    }

    private static int getTotalDays(List<Irbholiday> list) {
	int days = 0;
	for (Irbholiday i : list) {
	    days += UseCaseUtils
		    .getDiffDays(i.getInitialdate(), i.getEnddate());
	}
	return days;
    }

    public static int ObtainPreviousHolidaysFormPersonal(Usuario usuario,
	    String personalcode, Calendar year) {

	Criteria crit = HibernateUtil.getSession().createCriteria(
		Irbholiday.class);

	year.add(Calendar.YEAR, -1);

	Date dIn = year.getTime();
	Date dFi = UseCaseUtils.getEndYearDate(year);

	crit.add(Expression.between("initialdate", dIn, dFi))
		.add(Expression.eq("type", Irbholiday.TYPE_VACANCES))
		.add(Expression.eq("status", Irbholiday.STATUS_APROVAT))
		.createCriteria("personal").add(Expression.idEq(personalcode));

	return getHolidaysForYear() - getTotalDays(crit.list());
    }

    /** Dies per defecte **/

    private static int getHolidaysForYear() {
	return 23;
    }

    // private static int getApsForYear() {
    // return 9;
    // }

    public static int getCurrentYearForHolidays() {
	Criteria crit = HibernateUtil.getSession().createCriteria(
		Irbholiday.class);

	crit.add(Expression.eq("type",
		Irbholiday.TYPE_CURRENT_YEAR_FOR_HOLIDAYS));

	List<Irbholiday> list = crit.list();

	if (list != null && list.size() > 0) {
	    return list.get(0).getInitialdate().getYear() + 1900;
	} else {

	    GregorianCalendar now = new GregorianCalendar();
	    now.set(GregorianCalendar.MONTH, 0);
	    now.set(GregorianCalendar.DAY_OF_MONTH, 1);

	    Irbholiday TOirbholiday = new Irbholiday();
	    TOirbholiday.setDescription("Current year holidays");
	    TOirbholiday.setInitialdate(now.getTime());
	    TOirbholiday.setEnddate(now.getTime());
	    TOirbholiday.setStatus(Irbholiday.STATUS_APROVAT);
	    TOirbholiday.setType(Irbholiday.TYPE_CURRENT_YEAR_FOR_HOLIDAYS);
	    Create_Irbholiday(null, TOirbholiday);
	    return now.get(GregorianCalendar.YEAR);
	}
    }

    /**
     * funcio que crea un irbholiday pendent d'aprovacio
     * 
     * @throws HolidaysException
     **/

    public static Irbholiday CreatePendingIrbholiday(Usuario usuario,
	    Irbholiday TOirbholiday) throws NoPermisosException,
	    InternalException, HolidaysException {

	HibernateUtil.beginTransaction();

	TOirbholiday.setStatus(Irbholiday.STATUS_DEMANAT);
	TOirbholiday.setValidationcode(UseCaseUtils.createValidationCode());

	// comprovem que no hi ha algun periode de vancances pel mig
	if (checkIrbholidayBetweenDates(TOirbholiday.getPersonal()
		.getPersonalcode(), TOirbholiday.getInitialdate(),
		TOirbholiday.getEnddate())) {
	    throw new HolidaysException("error.irbholidayexist");
	}

	// comprovem que el nou irbholiday es valid
	CheckHolidays(TOirbholiday);

	// creem el bussines object
	Irbholiday irbholiday = Create_Irbholiday(usuario, TOirbholiday);

	Irbholidayinfo irbholidayinfo = ObtainIrbholidayinfoFromIrbholiday(
		usuario, irbholiday).get(0);

	// fem el recalcul de la informacio
	RecalculateHolidaysFromPersonal(irbholidayinfo);

	UseCaseUtils
		.sendAdviseSupervisorNewHolidaysFromPersonalMail(irbholiday);

	UseCaseUtils.sendMailHolidaysPending(irbholiday);

	HibernateUtil.commitTransaction();

	return irbholiday;
    }

    /** funcio que retorna el irbholidayinfo anterior al especificat */
    // private static Irbholidayinfo
    // ObtainPreviousIrbholidayinfoFromIrbholidayinfo(
    // Usuario usuario, Irbholidayinfo irbholidayinfo) {
    //
    // return ObtainIrbholidayinfoFromPersonal(usuario,
    // irbholidayinfo.getPersonal().getPersonalcode(),
    // new GregorianCalendar(irbholidayinfo.getYear()-1, 0, 1));
    // }

    /** funcio que retorna el irbholidayinfo posterior al especificat */
    // private static Irbholidayinfo ObtainNextIrbholidayinfoFromIrbholidayinfo(
    // Usuario usuario, Irbholidayinfo irbholidayinfo) {
    //
    // return ObtainIrbholidayinfoFromPersonal(usuario,
    // irbholidayinfo.getPersonal().getPersonalcode(),
    // new GregorianCalendar(irbholidayinfo.getYear()+1, 0, 1));
    // }

    private static List<Irbholidayinfo> ObtainIrbholidayinfoFromIrbholiday(
	    Usuario usuario, Irbholiday irbholiday) {

	List<Irbholidayinfo> irbholidayinfo = new ArrayList<Irbholidayinfo>();

	// List<Calendar> years = getYears(irbholiday.getInitialdate(),
	// irbholiday.getEnddate());

	irbholidayinfo.add(ObtainIrbholidayinfoFromPersonal(usuario, irbholiday
		.getPersonal().getPersonalcode(), new GregorianCalendar(
		irbholiday.getEnddate().getYear() + 1900, 0, 1)));

	// if (years.size() > 1) {
	// irbholidayinfo.add(ObtainIrbholidayinfoFromPersonal(usuario,
	// irbholiday.getPersonal().getPersonalcode(), years.get(1)));
	// }

	return irbholidayinfo;
    }

    // retorna l'any o anys en que es troben les dates
    private static List<Calendar> getYears(Date initialdate, Date enddate) {

	Calendar cini = new GregorianCalendar(), cend = new GregorianCalendar();
	cini.setTime(initialdate);
	cend.setTime(enddate);
	cini.set(Calendar.MONTH, 0);
	cend.set(Calendar.MONTH, 0);
	cini.set(Calendar.DAY_OF_MONTH, 1);
	cend.set(Calendar.DAY_OF_MONTH, 1);

	List<Calendar> list = new ArrayList<Calendar>();

	list.add(cini);

	if (cini.get(Calendar.YEAR) != cend.get(Calendar.YEAR)) {
	    list.add(cend);
	}

	return list;

    }

    public static Irbholiday CreateIrbholiday(Usuario usuario,
	    Irbholiday TOirbholiday) throws NoPermisosException,
	    InternalException, HolidaysException {

	int yearIntCurrent = getCurrentYearForHolidays();

	int yearInt = TOirbholiday.getInitialdate() != null ? (TOirbholiday
		.getInitialdate().getYear() + 1900) : 0;

	boolean recalculate = true;

	if (TOirbholiday.getType() == Irbholiday.TYPE_FESTIU
		|| TOirbholiday.getType() == Irbholiday.TYPE_LIMIT_VANCANCES_ANY_ANTERIOR) {
	    if (yearInt < yearIntCurrent) {
		throw new HolidaysException("error.date");
	    } else if (yearInt > yearIntCurrent) {
		// caso particular, permitimos entrar los festivos de aï¿½os
		// venideros.
		recalculate = false;
	    }
	}

	HibernateUtil.beginTransaction();

	TOirbholiday.setStatus(Irbholiday.STATUS_APROVAT);

	Irbholiday irbholiday = Create_Irbholiday(usuario, TOirbholiday);

	if (recalculate) {
	    RecalculateAllHolidays(irbholiday);
	}

	HibernateUtil.commitTransaction();

	return irbholiday;
    }

    /** funcio que crea un irbholiday de una persona aprovat */
    public static Irbholiday CreateIrbholidayFromPersonal(Usuario usuario,
	    Irbholiday TOirbholiday) throws NoPermisosException,
	    InternalException, HolidaysException {

	HibernateUtil.beginTransaction();

	TOirbholiday.setStatus(Irbholiday.STATUS_APROVAT);

	// comprovem que no hi ha algun periode de vancances pel mig
	if (checkIrbholidayBetweenDates(TOirbholiday.getPersonal()
		.getPersonalcode(), TOirbholiday.getInitialdate(),
		TOirbholiday.getEnddate())) {
	    throw new HolidaysException("error.irbholidayexist");
	}

	// comprovem que el nou irbholiday es valid
	CheckHolidays(TOirbholiday);

	// creem el bussines object
	Irbholiday irbholiday = Create_Irbholiday(usuario, TOirbholiday);

	Irbholidayinfo irbholidayinfo = ObtainIrbholidayinfoFromIrbholiday(
		usuario, irbholiday).get(0);

	// fem el recalcul de la informacio
	RecalculateHolidaysFromPersonal(irbholidayinfo);

	UseCaseUtils.sendMailHolidaysAproved(irbholiday, irbholidayinfo);

	HibernateUtil.commitTransaction();

	return irbholiday;
    }

    /** funcio que recalcula totes les vacances de totes les persones */
    private static void RecalculateAllHolidays(Irbholiday irbholiday) {

	int currentYear = getCurrentYearForHolidays();

	List<Personal> personals = ObtainPersonalWithContract(null,
		new ListConfigurator()).getSecond();

	ViewListConfiguration configurator = new ViewListConfiguration();
	configurator.getList_config_orderby().setAttribute("initialdate");
	configurator.getList_config_orderby().setAsc("asc");

	List<Irbholiday> festivosCurrent = ObtainIrbholidayFestiu(null,
		currentYear, configurator.obtainListConfigurator(null, false))
		.getSecond();

	Date limitHolidaysCurrent = ObtainIrbholidayLimitVacances(null,
		new GregorianCalendar(currentYear, 0, 1)).getInitialdate();

	List<Irbholiday> festivosMinus1 = ObtainIrbholidayFestiu(null,
		currentYear - 1,
		configurator.obtainListConfigurator(null, false)).getSecond();

	Date limitHolidaysMinus1 = ObtainIrbholidayLimitVacances(null,
		new GregorianCalendar(currentYear - 1, 0, 1)) != null ? ObtainIrbholidayLimitVacances(
		null, new GregorianCalendar(currentYear - 1, 0, 1))
		.getInitialdate() : null;

	for (Personal personal : personals) {

	    Irbholidayinfo irbholidayinfo = ObtainIrbholidayinfoFromPersonal(
		    null, personal.getPersonalcode(), new GregorianCalendar(
			    currentYear, 0, 1));
	    if (irbholidayinfo != null) {
		Irbholidayinfo ihiMinus1 = ObtainIrbholidayinfoFromPersonal(
			null, irbholidayinfo.getPersonal().getPersonalcode(),
			new GregorianCalendar(currentYear - 1, 0, 1));

		if (ihiMinus1 != null) {
		    RecalculateHolidaysFromPersonal(ihiMinus1, festivosMinus1,
			    limitHolidaysMinus1, currentYear - 1);

		}

		if (ihiMinus1 != null && ihiMinus1.getRemainingholidays() >= 0) {
		    irbholidayinfo.setPreviousyearholidaysforyear(ihiMinus1
			    .getRemainingholidays());
		}
		RecalculateHolidaysFromPersonal(irbholidayinfo,
			festivosCurrent, limitHolidaysCurrent, currentYear);
	    }

	}
    }

    public static void RecalculateHolidaysFromPersonal(
	    Irbholidayinfo irbholidayinfo) {

	int currentYear = getCurrentYearForHolidays();

	String personalcode = irbholidayinfo.getPersonal().getPersonalcode();

	irbholidayinfo = ObtainIrbholidayinfoFromPersonal(null, personalcode,
		new GregorianCalendar(currentYear, 0, 1));
	Irbholidayinfo ihiMinus1 = ObtainIrbholidayinfoFromPersonal(null,
		personalcode, new GregorianCalendar(currentYear - 1, 0, 1));

	ViewListConfiguration configurator = new ViewListConfiguration();
	configurator.getList_config_orderby().setAttribute("initialdate");
	configurator.getList_config_orderby().setAsc("asc");

	// currentYear - 1

	if (ihiMinus1 != null) {

	    List<Irbholiday> festivos = ObtainIrbholidayFestiu(null,
		    currentYear - 1,
		    configurator.obtainListConfigurator(null, false))
		    .getSecond();

	    Date limitHolidays = ObtainIrbholidayLimitVacances(null,
		    new GregorianCalendar(currentYear - 1, 0, 1))
		    .getInitialdate();

	    RecalculateHolidaysFromPersonal(ihiMinus1, festivos, limitHolidays,
		    currentYear - 1);

	}
	//

	if (irbholidayinfo != null) {

	    if (ihiMinus1 != null && ihiMinus1.getRemainingholidays() >= 0) {
		irbholidayinfo.setPreviousyearholidaysforyear(ihiMinus1
			.getRemainingholidays());
	    }
	    List<Irbholiday> festivos = ObtainIrbholidayFestiu(null,
		    currentYear,
		    configurator.obtainListConfigurator(null, false))
		    .getSecond();

	    Date limitHolidays = ObtainIrbholidayLimitVacances(null,
		    new GregorianCalendar(currentYear, 0, 1)).getInitialdate();

	    RecalculateHolidaysFromPersonal(irbholidayinfo, festivos,
		    limitHolidays, currentYear);
	}

    }

    /**
     * funcio que recalcula les vacances de una persona del any especificat
     * (irbholidayinfo) i del seguent
     */
    private static void RecalculateHolidaysFromPersonal(
	    Irbholidayinfo irbholidayinfo, List<Irbholiday> festivos,
	    Date limitHolidays, int currentYear) {

	if (irbholidayinfo == null) {
	    log.error("Holidayinfo es null en RecalculateHolidaysFromPersonal.");
	    StackTraceElement[] stackTrace = Thread.currentThread()
		    .getStackTrace();
	    for (StackTraceElement element : stackTrace) {
		log.error("\t" + element);
	    }
	    return;
	}

	int yearInt = currentYear;

	// obtenim la llista de irbholiday per aquest any
	List<Irbholiday> list = ObtainAllIIrbholidayFromPersonal(irbholidayinfo
		.getPersonal().getPersonalcode(), new GregorianCalendar(
		yearInt, 0, 1));

	// vacaciones gastadas
	int holidays = 0;
	int aps = 0;
	int previousyearholidays = 0;

	// vacaciones gastadas pendientes de aprovaciï¿½n
	int pendingHolidays = 0;
	int pendingAps = 0;
	int pendingPreviousyearholidays = 0;

	int previousyearholidaysforyear = irbholidayinfo
		.getPreviousyearholidaysforyear();
	if (list != null) {
	    for (Irbholiday holiday : list) {
		Date[] dates = UseCaseUtils.limitDateRangeToYear(new Date[] {
			holiday.getInitialdate(), holiday.getEnddate() },
			yearInt);
		Date start = dates[0];
		Date end = dates[1];

		if (holiday.getType() == Irbholiday.TYPE_ASUMPTES_PROPIS) {
		    int numDays = UseCaseUtils.diasLaborablesAnno(start, end,
			    festivos, yearInt);
		    if (holiday.getStatus() == Irbholiday.STATUS_APROVAT) {
			aps += numDays;
		    } else {
			pendingAps += numDays;
		    }

		    holiday.setAps(numDays);
		} else if (holiday.getType() == Irbholiday.TYPE_VACANCES) {
		    int numDaysPre = 0;
		    int numDays = 0;
		    if (start.compareTo(limitHolidays) <= 0
			    && end.compareTo(limitHolidays) <= 0) {
			// el periodo estï¿½ integramente antes de la fecha lï¿½mite
			numDays = UseCaseUtils.diasLaborablesAnno(start, end,
				festivos, yearInt);
			if (previousyearholidaysforyear >= numDays) {
			    previousyearholidaysforyear -= numDays;
			    numDaysPre = numDays;
			    numDays = 0;
			} else {
			    numDaysPre = previousyearholidaysforyear;
			    previousyearholidaysforyear = 0;
			    numDays -= numDaysPre;
			}
		    } else if (start.compareTo(limitHolidays) <= 0) {
			// el periodo estï¿½ a caballo sobre la fecha lï¿½mite
			numDaysPre = UseCaseUtils.diasLaborablesAnno(start,
				limitHolidays, festivos, yearInt);
			GregorianCalendar nextToLimitHolidays = new GregorianCalendar();
			nextToLimitHolidays.setTime(limitHolidays);
			nextToLimitHolidays.add(GregorianCalendar.DAY_OF_YEAR,
				1);

			numDays = UseCaseUtils.diasLaborablesAnno(
				nextToLimitHolidays.getTime(), end, festivos,
				yearInt);

			if (numDaysPre <= previousyearholidaysforyear) {
			    previousyearholidaysforyear -= numDaysPre;
			} else {
			    numDays += numDaysPre - previousyearholidaysforyear;
			    numDaysPre = previousyearholidaysforyear;
			    previousyearholidaysforyear = 0;
			}
		    } else {
			// el periodo estï¿½ integramente despuï¿½s de la fecha
			// lï¿½mite
			numDays = UseCaseUtils.diasLaborablesAnno(start, end,
				festivos, yearInt);
		    }

		    if (holiday.getStatus() == Irbholiday.STATUS_APROVAT) {

			holidays += numDays;
			previousyearholidays += numDaysPre;
		    } else {
			pendingHolidays += numDays;
			pendingPreviousyearholidays += numDaysPre;
		    }

		    holiday.setHolidays(numDays);
		    holiday.setPreviousyearholidays(numDaysPre);
		}
	    }
	}

	irbholidayinfo.setAps(aps);
	irbholidayinfo.setPendingaps(pendingAps);

	irbholidayinfo.setHolidays(holidays);
	irbholidayinfo.setPendingholidays(pendingHolidays);

	irbholidayinfo.setPreviousyearholidays(previousyearholidays);
	irbholidayinfo
		.setPendingpreviousyearholidays(pendingPreviousyearholidays);

	HibernateUtil.getSession().update(irbholidayinfo);

	// if(list == null) list = new ArrayList<Irbholiday>();
	//
	// // orderem les llistes segons les dates
	// Collections.sort(list, new IrbholidayComparator());
	//
	// Irbholiday betweenyears =
	// ObtainIrbholidayBetweenYears(irbholidayinfo.getPersonal().getPersonalcode(),
	// new GregorianCalendar(irbholidayinfo.getYear(), 0, 1));
	//
	// // fem el recalcul
	// RecalculateIrbholidayinfo(list, irbholidayinfo, betweenyears);
	//
	// // obtenim la llista de irbholiday del any seguent
	// Irbholidayinfo info2 =
	// ObtainNextIrbholidayinfoFromIrbholidayinfo(null, irbholidayinfo);
	//
	// List<Irbholiday> list2 =
	// ObtainAllIIrbholidayFromPersonal(irbholidayinfo.getPersonal().getPersonalcode(),
	// new GregorianCalendar(info2.getYear(), 0, 1));
	//
	// if(list2 == null) list2 = new ArrayList<Irbholiday>();
	//
	// Collections.sort(list2, new IrbholidayComparator());
	//
	// RecalculateIrbholidayinfo(list2, info2, betweenyears);
    }

    /** metode de recalcul de les vacancaes */
    // private static void RecalculateIrbholidayinfo(List<Irbholiday>
    // irbholidays, Irbholidayinfo irbholidayinfo, Irbholiday betweenyears) {
    //
    // HibernateUtil.beginTransaction();
    //
    // Irbholiday limit = ObtainIrbholidayLimitVacances(null, new
    // GregorianCalendar(irbholidayinfo.getYear(), 0, 1));
    //
    // int holidays = irbholidayinfo.getHolidaysforyear();
    // int aps = irbholidayinfo.getApsforyear();
    // int previous = ObtainPreviousIrbholidayinfoFromIrbholidayinfo(null,
    // irbholidayinfo).getHolidays();
    // int pendingholidays = 0;
    // int pendingaps = 0;
    //
    // Date dini = UseCaseUtils.getIniYear(irbholidayinfo.getYear());
    // Date dfi = UseCaseUtils.getEndYear(irbholidayinfo.getYear());
    //
    // if(betweenyears!=null && betweenyears.getInitialdate().before(dini)) {
    //
    // betweenyears.setHolidays(0); betweenyears.setAps(0);
    // betweenyears.setPreviousyearholidays(0);
    //
    // int diff = UseCaseUtils.getDiffDays(dini, betweenyears.getEnddate());
    //
    // if(betweenyears.getType() == Irbholiday.TYPE_VACANCES) {
    // calculateHolidays(betweenyears, limit, diff, holidays, previous);
    // } else {
    // betweenyears.setAps(diff);
    // }
    //
    // if(betweenyears.getStatus() == Irbholiday.STATUS_APROVAT) {
    // holidays -= betweenyears.getHolidays();
    // previous -= betweenyears.getPreviousyearholidays();
    // } else {
    // pendingholidays += betweenyears.getHolidays();
    // pendingaps += betweenyears.getAps();
    // }
    // }
    //
    // for(Irbholiday irbholiday : irbholidays) {
    //
    // irbholiday.setAps(0); irbholiday.setHolidays(0);
    // irbholiday.setPreviousyearholidays(0);
    //
    // int festius = ObtainFestiusBetweenDates(irbholiday.getInitialdate(),
    // irbholiday.getEnddate());
    // int diff = UseCaseUtils.getDiffDays(irbholiday.getInitialdate(),
    // irbholiday.getEnddate()) - festius;
    //
    // if(irbholiday.getType() == Irbholiday.TYPE_VACANCES) {
    // calculateHolidays(irbholiday, limit, diff, holidays, previous);
    // } else {
    // irbholiday.setAps(diff);
    // }
    //
    // if(irbholiday.getStatus() == Irbholiday.STATUS_APROVAT) {
    // holidays -= irbholiday.getHolidays();
    // previous -= irbholiday.getPreviousyearholidays();
    // aps -= irbholiday.getAps();
    // } else {
    // pendingholidays += irbholiday.getHolidays();
    // pendingaps += irbholiday.getAps();
    // }
    // }
    //
    // if(betweenyears!=null && betweenyears.getEnddate().after(dfi)) {
    //
    // betweenyears.setHolidays(0); betweenyears.setAps(0);
    // betweenyears.setPreviousyearholidays(0);
    //
    // int diff = UseCaseUtils.getDiffDays(betweenyears.getInitialdate(), dfi);
    //
    // if(betweenyears.getType() == Irbholiday.TYPE_VACANCES) {
    // calculateHolidays(betweenyears, limit, diff, holidays, previous);
    // } else {
    // betweenyears.setAps(diff);
    // }
    //
    // if(betweenyears.getStatus() == Irbholiday.STATUS_APROVAT) {
    // holidays -= betweenyears.getHolidays();
    // previous -= betweenyears.getPreviousyearholidays();
    // } else {
    // pendingholidays += betweenyears.getHolidays();
    // pendingaps += betweenyears.getAps();
    // }
    // }
    //
    // irbholidayinfo.setHolidays(Math.max(holidays, 0));
    // irbholidayinfo.setPreviousyearholidays(Math.max(previous, 0));
    // irbholidayinfo.setAps(Math.max(aps, 0));
    // irbholidayinfo.setPendingaps(Math.max(pendingaps, 0));
    // irbholidayinfo.setPendingholidays(Math.max(pendingholidays, 0));
    //
    // HibernateUtil.getSession().update(irbholidayinfo);
    //
    // HibernateUtil.commitTransaction();
    // }

    /*
     * private static void CalculateHolidays(Irbholiday irbholiday) throws
     * HolidaysException {
     * 
     * List<Irbholidayinfo> list = ObtainIrbholidayinfoFromIrbholiday(null,
     * irbholiday);
     * 
     * Irbholidayinfo irbholidayinfo = list.get(0);
     * 
     * Irbholiday limit = ObtainIrbholidayLimitVacances(null, new
     * GregorianCalendar(irbholidayinfo.getYear(), 0, 1));
     * 
     * if(limit == null) throw new HolidaysException("error.limit");
     * 
     * int previous = irbholidayinfo.getPreviousyearholidays(); int holidays =
     * irbholidayinfo.getHolidays(); int aps = irbholidayinfo.getAps(); int
     * festius = ObtainFestiusBetweenDates(irbholiday.getInitialdate(),
     * irbholiday.getEnddate()); int diff =
     * UseCaseUtils.getDiffDays(irbholiday.getInitialdate(),
     * irbholiday.getEnddate()) - festius;
     * 
     * if(list.size() == 1) {
     * 
     * if(irbholiday.getType() == Irbholiday.TYPE_VACANCES) {
     * 
     * calculateHolidays(irbholiday, limit, diff, holidays, previous);
     * 
     * } else {
     * 
     * calculateAps(irbholiday, diff, aps);
     * 
     * }
     * 
     * UpdateIrbholidayinfoFromCreate(null, irbholiday, irbholidayinfo);
     * 
     * } else {
     * 
     * Irbholidayinfo irbholidayinfo2 = list.get(1);
     * 
     * Irbholiday limit2 = ObtainIrbholidayLimitVacances(null, new
     * GregorianCalendar(irbholidayinfo2.getYear(), 0, 1));
     * 
     * if(limit2 == null) throw new HolidaysException("error.limit");
     * 
     * Date dend = getEndYear(irbholidayinfo.getYear()); Date dini =
     * getIniYear(irbholidayinfo2.getYear());
     * 
     * int festius1 = ObtainFestiusBetweenDates(irbholiday.getInitialdate(),
     * dend);
     * 
     * int diff1 = UseCaseUtils.getDiffDays(irbholiday.getInitialdate(), dend) -
     * festius1;
     * 
     * int festius2 = ObtainFestiusBetweenDates(dini, irbholiday.getEnddate());
     * 
     * int diff2 = UseCaseUtils.getDiffDays(dini, irbholiday.getEnddate()) -
     * festius2;
     * 
     * int previous2 = irbholidayinfo2.getPreviousyearholidays(); int holidays2
     * = irbholidayinfo2.getHolidays(); int aps2 = irbholidayinfo2.getAps();
     * 
     * if(irbholiday.getType() == Irbholiday.TYPE_VACANCES) {
     * 
     * calculateHolidays(irbholiday, limit, diff1, holidays, previous); int prev
     * = irbholiday.getPreviousyearholidays(); int hol =
     * irbholiday.getHolidays(); calculateHolidays(irbholiday, limit2, diff2,
     * holidays2, previous2);
     * irbholiday.setPreviousyearholidays(irbholiday.getPreviousyearholidays() +
     * prev); irbholiday.setHolidays(irbholiday.getHolidays() + hol);
     * 
     * } else {
     * 
     * calculateAps(irbholiday, diff1, aps); int ap = irbholiday.getAps();
     * calculateAps(irbholiday, diff2, aps2);
     * irbholiday.setAps(irbholiday.getAps() + ap);
     * 
     * }
     * 
     * UpdateIrbholidayinfoFromCreate(null, irbholiday, irbholidayinfo,
     * irbholidayinfo2);
     * 
     * }
     * 
     * }
     */

    /**
     * funcio que calcula els atributs holidays, previousyearholidays i aps d'un
     * irbholiday
     */
    // private static void calculateHolidays(Irbholiday irbholiday, Irbholiday
    // limit, int diff, int holidays, int previous) {
    //
    // boolean limend = irbholiday.getEnddate().before(limit.getInitialdate());
    // boolean limini =
    // irbholiday.getInitialdate().before(limit.getInitialdate());
    //
    // // estem dins del limit
    // if(limend) {
    // if(previous >= diff)
    // irbholiday.setPreviousyearholidays(diff);
    //
    // else {
    // irbholiday.setPreviousyearholidays(previous);
    // irbholiday.setHolidays(diff - previous);
    // }
    //
    // // estem entre mig
    // } else if(!limend && limini) {
    //
    // int festius1 = ObtainFestiusBetweenDates(irbholiday.getInitialdate(),
    // limit.getInitialdate());
    // int festius2 = ObtainFestiusBetweenDates(limit.getInitialdate(),
    // irbholiday.getEnddate());
    //
    // int diff1 = UseCaseUtils.getDiffDays(irbholiday.getInitialdate(),
    // limit.getInitialdate()) - festius1 - 1;
    // int diff2 = UseCaseUtils.getDiffDays(limit.getInitialdate(),
    // irbholiday.getEnddate()) - festius2;
    //
    // if(previous >= diff1 && holidays >= diff2) {
    //
    // irbholiday.setPreviousyearholidays(diff1);
    // irbholiday.setHolidays(diff2);
    //
    // } else {
    //
    // irbholiday.setHolidays(diff1 - previous + diff2);
    // irbholiday.setPreviousyearholidays(previous);
    // }
    //
    // // esta a fora del limit
    // } else {
    // irbholiday.setHolidays(diff);
    // }
    // }

    /** funcio que comprova que un irbholiday pot ser creat */
    @SuppressWarnings("deprecation")
    public static void CheckHolidays(Irbholiday irbholiday)
	    throws HolidaysException {

	int currentYearInt = getCurrentYearForHolidays();
	int yearInt = irbholiday.getInitialdate().getYear() + 1900;

	// No se permite el caso de vacaciones a caballo de los dos aï¿½os.

	if (irbholiday.getInitialdate().getYear() != irbholiday.getEnddate()
		.getYear()) {
	    throw new HolidaysException("error.dates_between_two_years");
	}

	if ((irbholiday.getInitialdate().getYear() + 1900) != currentYearInt
		&& (irbholiday.getInitialdate().getYear() + 1900) != (currentYearInt - 1)) {
	    throw new HolidaysException("error.date");
	}

	Irbholiday limit = ObtainIrbholidayLimitVacances(null,
		new GregorianCalendar(yearInt, 0, 1));

	if (limit == null)
	    throw new HolidaysException("error.limit");

	Date limitHolidays = limit.getInitialdate();

	ViewListConfiguration configurator = new ViewListConfiguration();
	configurator.getList_config_orderby().setAttribute("initialdate");
	configurator.getList_config_orderby().setAsc("asc");
	List<Irbholiday> festivos = ObtainIrbholidayFestiu(null,
		getCurrentYearForHolidays(),
		configurator.obtainListConfigurator(null, false)).getSecond();

	Irbholidayinfo info = ObtainIrbholidayinfoFromPersonal(null, irbholiday
		.getPersonal().getPersonalcode(), new GregorianCalendar(
		yearInt, 0, 1));
	int previousyearholidaysforyear = info.getPreviousyearholidaysforyear();
	int holidays = 0;

	int previousyearholidays = 0;

	Date[] dates = UseCaseUtils
		.limitDateRangeToYear(new Date[] { irbholiday.getInitialdate(),
			irbholiday.getEnddate() }, yearInt);
	Date start = dates[0];
	Date end = dates[1];

	if (irbholiday.getType() == Irbholiday.TYPE_ASUMPTES_PROPIS) {
	    int numDays = UseCaseUtils.diasLaborablesAnno(start, end, festivos,
		    yearInt);

	    if (info.getRemainingaps() < numDays) {
		throw new HolidaysException("error.days");
	    } else {
		return;
	    }
	} else if (irbholiday.getType() == Irbholiday.TYPE_VACANCES) {
	    int numDaysPre = 0;
	    int numDays = 0;
	    if (start.compareTo(limitHolidays) <= 0
		    && end.compareTo(limitHolidays) <= 0) {
		// el periodo estï¿½ integramente antes de la fecha lï¿½mite
		numDays = UseCaseUtils.diasLaborablesAnno(start, end, festivos,
			yearInt);
		if (previousyearholidaysforyear >= numDays) {
		    previousyearholidaysforyear -= numDays;
		    numDaysPre = numDays;
		    numDays = 0;
		} else {
		    numDaysPre = previousyearholidaysforyear;
		    previousyearholidaysforyear = 0;
		    numDays -= numDaysPre;
		}
	    } else if (start.compareTo(limitHolidays) <= 0) {
		// el periodo estï¿½ a caballo sobre la fecha lï¿½mite
		numDaysPre = UseCaseUtils.diasLaborablesAnno(start,
			limitHolidays, festivos, yearInt);
		GregorianCalendar nextToLimitHolidays = new GregorianCalendar();
		nextToLimitHolidays.setTime(limitHolidays);
		nextToLimitHolidays.add(GregorianCalendar.DAY_OF_YEAR, 1);

		numDays = UseCaseUtils.diasLaborablesAnno(
			nextToLimitHolidays.getTime(), end, festivos, yearInt);

		if (numDaysPre <= previousyearholidaysforyear) {
		    previousyearholidaysforyear -= numDaysPre;
		} else {
		    numDays += numDaysPre - previousyearholidaysforyear;
		    numDaysPre = previousyearholidaysforyear;
		    previousyearholidaysforyear = 0;
		}
	    } else {
		// el periodo estï¿½ integramente despuï¿½s de la fecha lï¿½mite
		numDays = UseCaseUtils.diasLaborablesAnno(start, end, festivos,
			yearInt);
	    }

	    holidays += numDays;
	    previousyearholidays += numDaysPre;

	    if (info.getRemainingholidays() < holidays) {
		throw new HolidaysException("error.days");
	    } else {
		return;
	    }
	}

	// int previous = irbholidayinfo.getPreviousyearholidays();
	// int holidays = irbholidayinfo.getHolidays();
	// int aps = irbholidayinfo.getAps();
	// int festius = ObtainFestiusBetweenDates(irbholiday.getInitialdate(),
	// irbholiday.getEnddate());
	// int diff = UseCaseUtils.getDiffDays(irbholiday.getInitialdate(),
	// irbholiday.getEnddate()) - festius;
	//
	// if(list.size() == 1) {
	//
	// if(irbholiday.getType() == Irbholiday.TYPE_VACANCES) {
	// checkHolidays(irbholiday, limit, diff, holidays, previous);
	// } else {
	// checkeAps(irbholiday, diff, aps);
	// }
	//
	// } else {
	// Irbholidayinfo irbholidayinfo2 = list.get(1);
	//
	// Irbholiday limit2 = ObtainIrbholidayLimitVacances(null, new
	// GregorianCalendar(irbholidayinfo2.getYear(), 0, 1));
	//
	// if(limit2 == null) throw new HolidaysException("error.limit");
	//
	// Date dend = UseCaseUtils.getEndYear(irbholidayinfo.getYear());
	// Date dini = UseCaseUtils.getIniYear(irbholidayinfo2.getYear());
	//
	// int festius1 = ObtainFestiusBetweenDates(irbholiday.getInitialdate(),
	// dend);
	//
	// int diff1 = UseCaseUtils.getDiffDays(irbholiday.getInitialdate(),
	// dend) - festius1;
	//
	// int festius2 = ObtainFestiusBetweenDates(dini,
	// irbholiday.getEnddate());
	//
	// int diff2 = UseCaseUtils.getDiffDays(dini, irbholiday.getEnddate()) -
	// festius2;
	//
	// int previous2 = irbholidayinfo2.getPreviousyearholidays();
	// int holidays2 = irbholidayinfo2.getHolidays();
	// int aps2 = irbholidayinfo2.getAps();
	//
	// if(irbholiday.getType() == Irbholiday.TYPE_VACANCES) {
	// checkHolidays(irbholiday, limit, diff1, holidays, previous);
	// checkHolidays(irbholiday, limit2, diff2, holidays2, previous2);
	// } else {
	// checkeAps(irbholiday, diff1, aps);
	// checkeAps(irbholiday, diff2, aps2);
	// }
	// }

    }

    private static void checkHolidays(Irbholiday irbholiday, Irbholiday limit,
	    int diff, int holidays, int previous) throws HolidaysException {

	boolean limend = irbholiday.getEnddate().before(limit.getInitialdate());
	boolean limini = irbholiday.getInitialdate().before(
		limit.getInitialdate());

	// estem dins del limit
	if (limend) {
	    if (previous < diff && previous + holidays < diff)
		throw new HolidaysException("error.days");

	    // estem entre mig
	} else if (!limend && limini) {

	    int festius1 = ObtainFestiusBetweenDates(
		    irbholiday.getInitialdate(), limit.getInitialdate());
	    int festius2 = ObtainFestiusBetweenDates(limit.getInitialdate(),
		    irbholiday.getEnddate());

	    int diff1 = UseCaseUtils.getDiffDays(irbholiday.getInitialdate(),
		    limit.getInitialdate()) - festius1 - 1;
	    int diff2 = UseCaseUtils.getDiffDays(limit.getInitialdate(),
		    irbholiday.getEnddate()) - festius2;

	    if (holidays < diff2
		    || previous < diff1
		    && (previous + holidays < diff1 || previous + holidays
			    - diff1 < diff2))
		throw new HolidaysException("error.days");

	    // esta a fora del limit
	} else {
	    if (holidays < diff)
		throw new HolidaysException("error.days");
	}
    }

    private static void checkeAps(Irbholiday irbholiday, int diff, int aps)
	    throws HolidaysException {

	if (aps < diff)
	    throw new HolidaysException("error.days");
    }

    /** funcio que fa el canvi d'estat de les vacances **/
    public static Irbholiday ChangeStateOfIrbholidayFromDemanatToAprovat(
	    Usuario usuario, Irbholiday irbholiday, String observacions)
	    throws NoPermisosException, InternalException {

	HibernateUtil.beginTransaction();

	irbholiday.setStatus(Irbholiday.STATUS_APROVAT);
	irbholiday.setValidationcode(null);

	HibernateUtil.getSession().evict(irbholiday);
	HibernateUtil.getSession().update(irbholiday);

	Irbholidayinfo irbholidayinfo = ObtainIrbholidayinfoFromIrbholiday(
		usuario, irbholiday).get(0);

	RecalculateHolidaysFromPersonal(irbholidayinfo);

	UseCaseUtils.sendMailHolidaysValidationSuccess(irbholiday,
		irbholidayinfo, observacions);

	CreateModificationAuditmessage(usuario, irbholiday);

	HibernateUtil.commitTransaction();

	return irbholiday;
    }

    /** funcio de cancelacio de validacio de les vacances */
    public static Irbholiday cancelValidationHolidays(Usuario usuario,
	    Irbholiday irbholiday, String observacions)
	    throws NoPermisosException, InternalException {

	HibernateUtil.beginTransaction();

	irbholiday.setValidationcode(null);

	Irbholidayinfo irbholidayinfo = ObtainIrbholidayinfoFromIrbholiday(
		usuario, irbholiday).get(0);

	UseCaseUtils.sendMailHolidaysValidationFail(irbholiday, irbholidayinfo,
		observacions);

	CreateModificationAuditmessage(usuario, irbholiday);

	HibernateUtil.commitTransaction();

	return irbholiday;
    }

    /**
     * funcio que envia un mail a l'usuari informant de l'estat de les seves
     * vacances
     */
    public static Irbholidayinfo sendHolidaysInfoFromPersonal(Usuario usuario,
	    Irbholidayinfo irbholidayinfo) throws NoPermisosException,
	    InternalException {

	UseCaseUtils.sendMailHolidaysInfo(irbholidayinfo);

	return irbholidayinfo;

    }

    /**
     * Obtains the data stored in the DataBase of the selected view, with the
     * filter options in the ListConfigurator and the OrderBy list
     * 
     * @param string
     * **/
    public static Pair<Integer, List<Object[]>> ObtainCustomListData(
	    Usuario user, String view_name, ListConfigurator configurator,
	    String[] filters, String[] OrderBy, String dateString)
	    throws ClassNotFoundException {

	String columnNames = "";

	for (String col : filters) {
	    if (col.startsWith("`") && col.endsWith("`")) {
		columnNames += col + " as "
			+ col.replace("`", "").replace(".", "") + ", ";
	    } else {
		columnNames += "`" + col + "`, ";
	    }
	}

	// to remove the last ", "
	columnNames = columnNames.substring(0, columnNames.length() - 2);

	// add orderbys
	String orderByString = "";
	if (OrderBy != null && OrderBy.length > 0) {
	    orderByString = " order by ";
	    for (int i = 0; i < OrderBy.length; i++) {
		if (OrderBy[i].startsWith("`") && OrderBy[i].endsWith("`")) {
		    orderByString += OrderBy[i] + ", ";
		} else {
		    orderByString += "`" + OrderBy[i] + "`, ";
		}
	    }
	    // to remove the last ", "
	    orderByString = orderByString.substring(0,
		    orderByString.length() - 2);
	}

	SQLQuery query = null;
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	Date now = new Date();
	String nowString = df.format(now);

	if (view_name.equals("view_personal_professional_date")) {

	    List<CompareData> filtros = configurator.getFilters();

	    for (CompareData filtro : filtros) {

		String filterColumnName = filtro.getAttributeName();

		if (!filterColumnName.equals("listdate")) {
		    if (!columnNames.contains(filterColumnName)) {
			columnNames += "," + filterColumnName;
		    }
		} else {

		    dateString = df.format(filtro.getValue());
		}

	    }

	    if (dateString == null || dateString.trim().equals("")) {
		dateString = nowString;
	    }
	    // else {
	    // MultipleDateFormat mdf = new MultipleDateFormat();
	    //
	    // Date d = mdf.parse(dateString, new Locale(user.getLanguage()
	    // .getLanguagecode()));
	    //
	    // dateString = df.format(d);
	    // }

	    view_name += " left join `grant_concession` `gc` on `gc`.`grant_concession_personal` = `personalcode` and `gc`.`deleted`=0 and ( (gc.start_date is null and gc.end_date is null)  or ('"
		    + dateString
		    + "' between gc.start_date and gc.end_date) or (gc.start_date is null and '"
		    + dateString
		    + "' <= gc.end_date) or ('"
		    + dateString
		    + "' >= gc.start_date and gc.end_date is null))"
		    + " left join `table_grant` `tg` on `tg`.`grantcode` = `gc`.`table_grant`"
		    + " left join `type_of_grant` `typ` on `typ`.`type_of_grantcode` = `tg`.`type_of_grant`"
		    + " left join `compensation` `com` on `personalcode` = `com`.`compensation_personal` and `com`.`deleted`=0 and ( (com.start_date is null and com.end_date is null)  or ('"
		    + dateString
		    + "' between com.start_date and com.end_date) or (com.start_date is null and '"
		    + dateString
		    + "' <= com.end_date) or ('"
		    + dateString
		    + "' >= com.start_date and com.end_date is null))"
		    + " left join `type_of_compensation` `tco` on `com`.`type_of_compensation` = `tco`.`type_of_compensationcode`"
		    + " left join `benefits` `ben` on `personalcode` = `ben`.`benefits_personal` and `ben`.`deleted`=0 and ( (ben.start_date is null and ben.end_date is null)  or ('"
		    + dateString
		    + "' between ben.start_date and ben.end_date) or (ben.start_date is null and '"
		    + dateString
		    + "' <= ben.end_date) or ('"
		    + dateString
		    + "' >= ben.start_date and ben.end_date is null))"
		    + " left join type_of_benefit `tbe` on ben.type_of_benefit = tbe.type_of_benefitcode ";
	    
	    
	    //Add academic info
	    view_name += " left join `academic_info` `ai` on `ai`.`academic_info_personal` = `personalcode` and `ai`.`deleted`=0 and ( (ai.start_date is null and ai.end_date is null)  or ('"
			    + dateString
			    + "' between ai.start_date and ai.end_date) or (ai.start_date is null and '"
			    + dateString
			    + "' <= ai.end_date) or ('"
			    + dateString
			    + "' >= ai.start_date and ai.end_date is null))"
			    + " LEFT JOIN `type_of_study` `ts` ON `ts`.`type_of_studycode` = `ai`.`type_of_study`"
			    + " LEFT JOIN `research_group` `airg` ON `ai`.`lab_rotation_lab` = `airg`.`research_groupcode`"
			    + " LEFT JOIN `research_group` `airg2` ON `ai`.`lab_rotation_lab` = `airg2`.`research_groupcode`";
	    

	    String max_education_field = "(select typedu.description from EDUCATION edu"
		    + " inner join TYPE_OF_EDUCATION typedu on edu.type=typedu.type_of_educationcode and edu.deleted=0"
		    + " where edu.education_personal=personalcode and "
		    + " (graduation_date is null or graduation_date <= '"
		    + dateString
		    + "') and"
		    + " typedu.order= (select max(typedu2.`order`) from EDUCATION edu2"
		    + "   inner join TYPE_OF_EDUCATION typedu2 on edu2.type=typedu2.type_of_educationcode and edu2.deleted=0 "
		    + "where edu2.education_personal=personalcode and"
		    + "(edu2.graduation_date is null or edu2.graduation_date <= '"
		    + dateString + "')) limit 1)";

	    if (columnNames.contains("`max_education`")) {
		columnNames = columnNames.replace("`max_education`",
			max_education_field + " as max_education");
	    }

	    if (orderByString.contains("`max_education`")) {
		orderByString = orderByString.replace("`max_education`",
			max_education_field);
	    }

	    String whereClause = makeWhereFromListConfigurator(user, configurator);

	    if (whereClause.contains("`max_education`")) {
		whereClause = whereClause.replace("`max_education`",
			max_education_field);
	    }

	    //TODO Atención distinct
	    String queryString = "select " + columnNames + " from "
		    + view_name + whereClause;

	    if (!whereClause.contains(" where ")) {
		queryString += " where ";
	    } else {
		queryString += " and ";
	    }

	    queryString += " (('"
		    + dateString
		    + "' between professional_start_date and professional_end_date or ('"
		    + dateString
		    + "' >= professional_start_date and professional_end_date is null) or (professional_start_date is null and '"
		    + dateString
		    + "' <= professional_end_date) or (professional_start_date is null and professional_end_date is null))  )"; // and
																// prodeleted
																// is
																// not
																// null
																// and
																// prodeleted=0

	    query = HibernateUtil.getSession().createSQLQuery(
		    queryString + " " + orderByString);
	} else {

	    List<CompareData> filtros = configurator.getFilters();

	    boolean filteredByBenefitsDates = false;

	    for (CompareData filtro : filtros) {

		String filterColumnName = filtro.getAttributeName();

		if (!columnNames.contains(filterColumnName)) {
		    columnNames += "," + filterColumnName;
		}

		if (filterColumnName.contains("`ben`.`start_date`")
			|| filterColumnName.contains("`ben`.`end_date`")) {
		    filteredByBenefitsDates = true;
		}
	    }

	    if ((columnNames.contains("`ben`") || columnNames.contains("`tbe`"))
		    && !filteredByBenefitsDates) {
		view_name += " left join `benefits` `ben` on `personalcode` = `ben`.`benefits_personal` and `ben`.`deleted`=0 and ( (ben.start_date is null and ben.end_date is null)  or ('"
			+ nowString
			+ "' between ben.start_date and ben.end_date) or (ben.start_date is null and '"
			+ nowString
			+ "' <= ben.end_date) or ('"
			+ nowString
			+ "' >= ben.start_date and ben.end_date is null))"
			+ " left join type_of_benefit `tbe` on ben.type_of_benefit = tbe.type_of_benefitcode";

	    } else if (filteredByBenefitsDates) {
		view_name += " left join `benefits` `ben` on `personalcode` = `ben`.`benefits_personal` and `ben`.`deleted`=0 left join type_of_benefit `tbe` on ben.type_of_benefit = tbe.type_of_benefitcode";
	    }

	    //TODO Atención distinct
	    String queryString = "select " + columnNames + " from "
		    + view_name + makeWhereFromListConfigurator(user, configurator)
		    + orderByString;

	    query = HibernateUtil.getSession().createSQLQuery(queryString);
	}

	List<Object[]> objects = (List<Object[]>) query.list();

	
	
	Pair<Integer, List<Object[]>> pair = new Pair<Integer, List<Object[]>>(
		objects.size(), objects);

	return pair;

    }

    private static String makeWhereFromListConfigurator(
	    Usuario user, ListConfigurator configurator) {

	String whereString = "";

	for (ListConfigurator.CompareData comp : configurator.getFilters()) {
	    String compType = comp.getComparison();
	    String filtro = null;
	    String attribute;
	    if (comp.getAttributeName().startsWith("`")
		    && comp.getAttributeName().endsWith("`")) {
		attribute = comp.getAttributeName();
	    } else if (comp.getAttributeName().equals("listdate")) {
		continue;
	    } else {
		attribute = "`" + comp.getAttributeName() + "`";
	    }

	    String value = comp.getValue().toString();

	    if (compType.equalsIgnoreCase(ListConfigurator.EQUAL)) {

		if (comp.getValue() instanceof Boolean) {
		    filtro = attribute + " = "
			    + ((Boolean) comp.getValue() ? "1" : "0");
		} else if (comp.getValue() instanceof String) {
		    filtro = attribute + " ='" + value + "'";
		} else if (comp.getValue() instanceof Date) {
		    filtro = attribute + " ='"
			    + sqldateFormater((Date) comp.getValue()) + "' ";
		} else {
		    filtro = attribute + " = " + value;
		}
	    } else if (compType.equalsIgnoreCase(ListConfigurator.NOT_EQUAL)) {
		if (comp.getValue() instanceof Boolean) {
		    filtro = attribute + " <> "
			    + ((Boolean) comp.getValue() ? "1" : "0");
		} else if (comp.getValue() instanceof String) {
		    filtro = attribute + " <>'" + value + "'";
		} else {
		    filtro = attribute + " <> " + value;
		}
	    } else if (compType.equalsIgnoreCase(ListConfigurator.GREATER)) {
		if (comp.getValue() instanceof Date) {
		    filtro = attribute + " >'"
			    + sqldateFormater((Date) comp.getValue()) + "' ";
		} else {
		    filtro = attribute + " > " + value;
		}
	    } else if (compType
		    .equalsIgnoreCase(ListConfigurator.GREATER_EQUAL)) {
		if (comp.getValue() instanceof Date) {
		    filtro = attribute + " >='"
			    + sqldateFormater((Date) comp.getValue()) + "' ";
		} else {
		    filtro = attribute + " >= " + value;
		}
	    } else if (compType.equalsIgnoreCase(ListConfigurator.LIKE)) {
		filtro = attribute + " like '%" + value + "%'";
	    } else if (compType.equalsIgnoreCase(ListConfigurator.LOWER)) {
		if (comp.getValue() instanceof Date) {
		    filtro = attribute + " <'"
			    + sqldateFormater((Date) comp.getValue()) + "' ";
		} else {
		    filtro = attribute + " < " + value;
		}
	    } else if (compType.equalsIgnoreCase(ListConfigurator.LOWER_EQUAL)) {
		if (comp.getValue() instanceof Date) {
		    filtro = attribute + " <='"
			    + sqldateFormater((Date) comp.getValue()) + "' ";
		} else {
		    filtro = attribute + " <= " + value;
		}
	    }

	    if (filtro != null) {
		filtro += " ";
		whereString = whereString.length()==0 ? filtro : whereString
			+ " and " + filtro;
	    }

	}

	whereString += addFilterByRoleWhere(user, whereString);
	
	if (whereString.equals("")) {
	    return whereString;
	} else {
	    return " where (" + whereString + ")";
	}
    }

    @SuppressWarnings("deprecation")
    private static String sqldateFormater(Date date) {
	return (date.getYear() + 1900) + "-" + (date.getMonth() + 1) + "-"
		+ date.getDate();
    }

    /**
     * Prepares the information returned by the previous funtion to be viewed,
     * with the information of the list of columns (filters)
     * **/
    public static List<String[]> ProcessListData(List<Object[]> itemsList,
	    String[] filters, Locale loc) throws IllegalAccessException,
	    InvocationTargetException, NoSuchMethodException {

	List<String[]> result = new ArrayList();

	try {
	    StringConverter sc = new StringConverter(loc);

	    Iterator<Object[]> it = itemsList.iterator();

	    while (it.hasNext()) {

		Object value = it.next();
		Object[] current;

		if (value instanceof Object[]) {
		    current = (Object[]) value;
		} else {
		    current = new Object[] { value };
		}

		String[] row = new String[filters.length];

		for (int i = 0; i < filters.length; i++) {

		    row[i] = (String) sc.convert(String.class, current[i]);
		}

		result.add(row);

	    }
	} catch (Exception e) {
	    log.error(e);
	}

	return result;
    }

    protected static CustomList getCustomList(String customListcode) {
	CustomList customList = (CustomList) HibernateUtil.getSession().get(
		CustomList.class, customListcode);
	return customList;
    }

    protected static Column getColumn(String columncode) {
	Column column = (Column) HibernateUtil.getSession().get(Column.class,
		columncode);
	return column;
    }

    protected static Filter getFilter(String filtercode) {
	Filter filter = (Filter) HibernateUtil.getSession().get(Filter.class,
		filtercode);
	return filter;
    }

    protected static FilterType getFilterType(String FilterTypecode) {
	FilterType FilterType = (FilterType) HibernateUtil.getSession().get(
		FilterType.class, FilterTypecode);
	return FilterType;
    }

    protected static OrderBy getOrderBy(String OrderBycode) {
	OrderBy OrderBy = (OrderBy) HibernateUtil.getSession().get(
		OrderBy.class, OrderBycode);
	return OrderBy;
    }

    protected static Views getView(String Viewscode) {
	Views Views = (Views) HibernateUtil.getSession().get(Views.class,
		Viewscode);
	return Views;
    }

    public static CustomList CreateCustomList(Usuario user,
	    CustomList TOCustomList) throws InternalException,
	    NoPermisosException {

	// testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We create the new instance * */
	CustomList customList = new CustomList();

	/**
	 * 3. We set all the simple attributes (no associations) to the new
	 * instance *
	 */

	customList.setName(TOCustomList.getName());
	customList.setView_name(TOCustomList.getView_name());
	customList.setUsercode(TOCustomList.getUsercode());
	customList.setUpdate_date(TOCustomList.getUpdate_date());
	customList.setComments(TOCustomList.getComments());

	/** 4. We set the code to the new instance * */
	try {
	    IdentifyManager_Plain im = IdentifyManager_Plain.singleton();

	    customList.setCustomListcode(im.getId(TOCustomList));
	} catch (identifyException ie) {

	    log.error("Error en asignaciï¿½n de nuevo id en CreateCustomList", ie);
	    throw new Error(ie.getMessage());
	}

	/** 5. We save the new instance to the DB* */
	HibernateUtil.getSession().save(customList);

	/** 6. We create an Audit message * */
	CreateCreationAuditmessage(user, customList);

	/** 7. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return customList;
    }

    public static CustomList UpdateCustomList(Usuario user,
	    CustomList TOCustomList) throws InternalException,
	    NoPermisosException {

	// testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain form the DB the instance to modify * */
	CustomList customList = getCustomList(TOCustomList.getCustomListcode());
	;

	/**
	 * 3. We set all the simple attributes (no associations) to the instance
	 * *
	 */

	customList.setName(TOCustomList.getName());
	customList.setView_name(TOCustomList.getView_name());
	customList.setUsercode(TOCustomList.getUsercode());
	customList.setUpdate_date(TOCustomList.getUpdate_date());
	customList.setComments(TOCustomList.getComments());

	/**
	 * 4. We set the DTO version to the modified object and we update it
	 * with the new values in the DB. We evict and update the instance to
	 * prevent concurrent modification *
	 */
	HibernateUtil.getSession().evict(customList);
	customList.setVersion(TOCustomList.getVersion());
	HibernateUtil.getSession().update(customList);

	/** 5. We create an Audit message * */
	CreateModificationAuditmessage(user, customList);

	/** 6. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return customList;
    }

    public static void RemoveCustomList(Usuario user, String customListcode)
	    throws NoPermisosException {

	// testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain the object to delete form the DB. * */
	CustomList customList = getCustomList(customListcode);

	/** 3. We mark it as deleted. * */
	customList.setDeleted(Boolean.TRUE);

	/** 4. We create an Audit message * */
	CreateRemovealAuditmessage(user, customList);

	/** 5. We commit the DB transaction. * */
	HibernateUtil.commitTransaction();
    }

    public static CustomList ObtainCustomList(Usuario user,
	    String customListcode) {

	/**
	 * 1. We obtain the object from the DB using the private getter and we
	 * return it. *
	 */

	CustomList customList = getCustomList(customListcode);
	return customList;
    }

    public static List<CustomList> ObtainAllCustomList(Usuario user) {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(
		CustomList.class);

	// we only want to obtain the non deleted objects
	crit.add(Expression.eq("deleted", Boolean.FALSE));

	Role o = (Role) user.getRoles().toArray()[0];

	Query query = HibernateUtil
		.getSession()
		.createSQLQuery(
			"SELECT name FROM views vw inner join views_permissions per on vw.viewscode=per.view_name or per.view_name='all' where per.entitycode = :paramRole");
	query.setString("paramRole", o.getEntitycode());
	List results = query.list();

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	List<CustomList> customLists = (List<CustomList>) crit.list();
	List<CustomList> accesibleCustomLists = new ArrayList();
	for (CustomList cl : customLists) {
	    if (results.contains(cl.getView_name()))
		accesibleCustomLists.add(cl);
	}

	return accesibleCustomLists;

    }

    public static Column CreateColumn(Usuario user, Column TOColumn)
	    throws InternalException, NoPermisosException {

	// testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We create the new instance * */
	Column column = new Column();

	/**
	 * 3. We set all the simple attributes (no associations) to the new
	 * instance *
	 */

	column.setName(TOColumn.getName());

	if (TOColumn.getAssocCustomList() != null
		&& (TOColumn.getAssocCustomList().getCustomListcode() != null && !TOColumn
			.getAssocCustomList().getCustomListcode().equals(""))) {

	    column.setAssocCustomList(getCustomList(TOColumn
		    .getAssocCustomList().getCustomListcode()));

	}

	/** 4. We set the code to the new instance * */
	try {
	    IdentifyManager_Plain im = IdentifyManager_Plain.singleton();

	    column.setColumncode(im.getId(TOColumn));
	} catch (identifyException ie) {

	    log.error("Error en asignaciï¿½n de nuevo id en CreateColumn", ie);
	    throw new Error(ie.getMessage());
	}

	/** 5. We save the new instance to the DB* */
	HibernateUtil.getSession().save(column);

	/** 6. We create an Audit message * */
	// CreateCreationAuditmessage(user, column);

	/** 7. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return column;
    }

    public static Column UpdateColumn(Usuario user, Column TOColumn)
	    throws InternalException, NoPermisosException {

	// testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain form the DB the instance to modify * */
	Column column = getColumn(TOColumn.getColumncode());
	;

	/**
	 * 3. We set all the simple attributes (no associations) to the instance
	 * *
	 */

	column.setName(TOColumn.getName());

	if (TOColumn.getAssocCustomList() != null
		&& (TOColumn.getAssocCustomList().getCustomListcode() != null && !TOColumn
			.getAssocCustomList().getCustomListcode().equals(""))) {

	    column.setAssocCustomList(getCustomList(TOColumn
		    .getAssocCustomList().getCustomListcode()));

	}

	/**
	 * 4. We set the DTO version to the modified object and we update it
	 * with the new values in the DB. We evict and update the instance to
	 * prevent concurrent modification *
	 */
	HibernateUtil.getSession().evict(column);
	column.setVersion(TOColumn.getVersion());
	HibernateUtil.getSession().update(column);

	/** 5. We create an Audit message * */
	// CreateModificationAuditmessage(user, column);

	/** 6. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return column;
    }

    public static void RemoveColumn(Usuario user, String columncode)
	    throws NoPermisosException {

	// testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain the object to delete form the DB. * */
	Column column = getColumn(columncode);

	/** 3. We mark it as deleted. * */
	column.setDeleted(Boolean.TRUE);

	/** 4. We create an Audit message * */
	// CreateRemovealAuditmessage(user, column);

	/** 5. We commit the DB transaction. * */
	HibernateUtil.commitTransaction();
    }

    public static Column ObtainColumn(Usuario user, String columncode) {

	/**
	 * 1. We obtain the object from the DB using the private getter and we
	 * return it. *
	 */

	Column column = getColumn(columncode);
	return column;
    }

    /**
     * Obtains all the columns associated to the CustomList whith this
     * customlistcode
     * 
     * **/
    public static List<Column> ObtainAllColumnsFrom(Usuario user,
	    String customlistcode) {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(Column.class);

	// we only want to obtain the non deleted objects
	crit.add(Expression.eq("deleted", Boolean.FALSE));

	crit.createCriteria("assocCustomList").add(
		Expression.idEq(customlistcode));

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	List<Column> column = (List<Column>) crit.list();

	return column;

    }

    /**
     * Remove all the columns associated to the CustomList whith this
     * customlistcode
     * 
     * **/
    public static void RemoveAllColumnsFrom(Usuario user, String customlistcode)
	    throws NoPermisosException {

	List<Column> columnlist = ObtainAllColumnsFrom(user, customlistcode);
	Iterator<Column> it = columnlist.iterator();
	while (it.hasNext()) {
	    Column temp = it.next();
	    RemoveColumn(user, temp.getColumncode());
	}

    }

    /**
     * Updates the columns associated to the CustomList whith this
     * customlistcode
     * 
     * **/
    public static void UpdateAllColumns(Usuario user, String customlistcode,
	    String[] columns) throws NoPermisosException, InternalException {

	RemoveAllColumnsFrom(user, customlistcode);
	CustomList customlist = ObtainCustomList(user, customlistcode);
	if (columns != null) {
	    for (int i = 0; i < columns.length; i++) {
		Column temp = new Column();
		temp.setAssocCustomList(customlist);
		temp.setName(columns[i]);

		CreateColumn(user, temp);
	    }
	}
    }

    public static Filter CreateFilter(Usuario user, Filter TOFilter)
	    throws InternalException, NoPermisosException {

	// testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We create the new instance * */
	Filter filter = new Filter();

	/**
	 * 3. We set all the simple attributes (no associations) to the new
	 * instance *
	 */

	filter.setName(TOFilter.getName());
	filter.setValue(TOFilter.getValue());
	filter.setType(TOFilter.getType());

	if (TOFilter.getAssocCustomList() != null
		&& (TOFilter.getAssocCustomList().getCustomListcode() != null && !TOFilter
			.getAssocCustomList().getCustomListcode().equals(""))) {

	    filter.setAssocCustomList(getCustomList(TOFilter
		    .getAssocCustomList().getCustomListcode()));

	}

	if (TOFilter.getAssocFilterType() != null
		&& (TOFilter.getAssocFilterType().getFilterTypecode() != null && !TOFilter
			.getAssocFilterType().getFilterTypecode().equals(""))) {

	    filter.setAssocFilterType(getFilterType(TOFilter
		    .getAssocFilterType().getFilterTypecode()));

	}

	/** 4. We set the code to the new instance * */
	try {
	    IdentifyManager_Plain im = IdentifyManager_Plain.singleton();

	    filter.setFiltercode(im.getId(TOFilter));
	} catch (identifyException ie) {

	    log.error("Error en asignaciï¿½n de nuevo id en CreateFilter", ie);
	    throw new Error(ie.getMessage());
	}

	/** 5. We save the new instance to the DB* */
	HibernateUtil.getSession().save(filter);

	/** 6. We create an Audit message * */
	// CreateCreationAuditmessage(user, filter);

	/** 7. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return filter;
    }

    public static Filter UpdateFilter(Usuario user, Filter TOFilter)
	    throws InternalException, NoPermisosException {

	// testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain form the DB the instance to modify * */
	Filter filter = getFilter(TOFilter.getFiltercode());
	;

	/**
	 * 3. We set all the simple attributes (no associations) to the instance
	 * *
	 */

	filter.setName(TOFilter.getName());

	if (TOFilter.getAssocCustomList() != null
		&& (TOFilter.getAssocCustomList().getCustomListcode() != null && !TOFilter
			.getAssocCustomList().getCustomListcode().equals(""))) {

	    filter.setAssocCustomList(getCustomList(TOFilter
		    .getAssocCustomList().getCustomListcode()));

	}

	if (TOFilter.getAssocFilterType() != null
		&& (TOFilter.getAssocFilterType().getFilterTypecode() != null && !TOFilter
			.getAssocFilterType().getFilterTypecode().equals(""))) {

	    filter.setAssocFilterType(getFilterType(TOFilter
		    .getAssocFilterType().getFilterTypecode()));

	}

	/**
	 * 4. We set the DTO version to the modified object and we update it
	 * with the new values in the DB. We evict and update the instance to
	 * prevent concurrent modification *
	 */
	HibernateUtil.getSession().evict(filter);
	filter.setVersion(TOFilter.getVersion());
	HibernateUtil.getSession().update(filter);

	/** 5. We create an Audit message * */
	// CreateModificationAuditmessage(user, filter);

	/** 6. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return filter;
    }

    public static void RemoveFilter(Usuario user, String filtercode)
	    throws NoPermisosException {

	// testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain the object to delete form the DB. * */
	Filter filter = getFilter(filtercode);

	/** 3. We mark it as deleted. * */
	filter.setDeleted(Boolean.TRUE);

	/** 4. We create an Audit message * */
	// CreateRemovealAuditmessage(user, filter);

	/** 5. We commit the DB transaction. * */
	HibernateUtil.commitTransaction();
    }

    public static Filter ObtainFilter(Usuario user, String filtercode) {

	/**
	 * 1. We obtain the object from the DB using the private getter and we
	 * return it. *
	 */

	Filter filter = getFilter(filtercode);
	return filter;
    }

    public static List<Filter> ObtainAllFiltersFrom(Usuario user,
	    String customlistcode) {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(Filter.class);

	// we only want to obtain the non deleted objects
	crit.add(Expression.eq("deleted", Boolean.FALSE));

	crit.createCriteria("assocCustomList").add(
		Expression.idEq(customlistcode));

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	List<Filter> filter = (List<Filter>) crit.list();

	return filter;

    }

    public static void RemoveAllFiltersFrom(Usuario user, String customlistcode)
	    throws NoPermisosException {

	List<Filter> filterlist = ObtainAllFiltersFrom(user, customlistcode);
	Iterator<Filter> it = filterlist.iterator();
	while (it.hasNext()) {
	    Filter temp = it.next();
	    RemoveFilter(user, temp.getFiltercode());
	}

    }

    public static void UpdateAllFilter(Usuario user, String customlistcode,
	    List<String[]> filter) throws NoPermisosException,
	    InternalException {
	// List<String> filter: Each element: {attributeName, value,
	// compareValue, type (String, Date, Integer..)}

	RemoveAllFiltersFrom(user, customlistcode);
	CustomList customlist = ObtainCustomList(user, customlistcode);

	if (filter != null) {
	    Iterator<String[]> it = filter.iterator();
	    while (it.hasNext()) {
		String[] elem = it.next();
		Filter temp = new Filter();
		temp.setAssocCustomList(customlist);
		temp.setName(elem[0]);
		temp.setValue(elem[1]);
		temp.setAssocFilterType(ObtainFilterType(user, elem[2]));
		temp.setType(elem[3]);
		CreateFilter(user, temp);
	    }

	}
    }

    /**
     * Creates a List of Arrays with the information of the filters.
     * 
     * the "between" filter is stored in 2 rows (a greater an Equal one and
     * Lower and equal). This function unifies this two conditions
     * 
     * 
     * Returned List of Arrays : [Name, Value, Type of filter, (2nd value in
     * case of between type)]
     * 
     * **/
    public static List<String[]> CreateArrayFromfilterList(List<Filter> filter,
	    Locale locale) {

	List<String[]> result = new ArrayList();
	Iterator<Filter> it = filter.iterator();

	while (it.hasNext()) {
	    Filter temp = it.next();

	    /** For reconstructing between filtering options correctly **/
	    StringConverter sc = new StringConverter(locale);
	    Iterator<String[]> it2 = result.iterator();
	    Boolean isBetween = false;
	    String curr_value = temp.getValue();
	    if (temp.getType().equals("BigDecimal")) {
		curr_value = (String) sc.convert(String.class, new BigDecimal(
			temp.getValue()));
	    }
	    while (it2.hasNext()) {
		String[] temp2 = it2.next();
		if (temp2[0].equals(temp.getName())) { // Is a between filter (2
						       // filters with the same
						       // name)
		    result.remove(temp2);

		    if (temp.getAssocFilterType().getFilterTypecode()
			    .equals("greaterEqual")) {

			result.add(new String[] { temp2[0], curr_value,
				"between", temp2[1] });
		    } else {
			result.add(new String[] { temp2[0], temp2[1],
				"between", curr_value });
		    }
		    isBetween = true;
		    break;
		}
	    }

	    if (!isBetween) {
		result.add(new String[] { temp.getName(), curr_value,
			temp.getAssocFilterType().getName() });
	    }
	}

	return result;

    }

    /**
     * Converts a List of Filters into a ListConfigurator
     * **/
    public static ListConfigurator CreateListConfiguratorFromFilterList(
	    List<Filter> filterlist, Locale locale) {

	ListConfigurator result = new ListConfigurator();
	Iterator<Filter> it = filterlist.iterator();

	NumberConverter nc = new NumberConverter(locale);
	// BooleanConverter bc = new BooleanConverter(locale);
	DateConverter dc = new DateConverter(locale);

	while (it.hasNext()) {

	    Filter temp = it.next();
	    if (temp.getType().equals("String")) {

		result.setFilter(temp.getName(), temp.getValue(), temp
			.getAssocFilterType().getFilterTypecode());

	    } else if (temp.getType().equals("Boolean")) {

		Boolean bool_val = false;
		if (temp.getValue().equals("true")) {
		    bool_val = true;
		}

		result.setFilter(temp.getName(), bool_val, temp
			.getAssocFilterType().getFilterTypecode());

	    } else if (temp.getType().equals("Date")) {

		result.setFilter(temp.getName(), (Date) dc.convert(Date.class,
			temp.getValue()), temp.getAssocFilterType()
			.getFilterTypecode());

	    } else if (temp.getType().equals("BigDecimal")) {

		result.setFilter(temp.getName(), (BigDecimal) new BigDecimal(
			temp.getValue()), temp.getAssocFilterType()
			.getFilterTypecode());
	    } else {// integer
		result.setFilter(temp.getName(),
			(Integer) nc.convert(Integer.class, temp.getValue()),
			temp.getAssocFilterType().getFilterTypecode());
	    }

	}

	return result;
    }

    public static String[] CreateListFromColumnList(List<Column> col_list) {

	String[] result = new String[col_list.size()];

	Iterator<Column> it = col_list.iterator();
	int i = 0;
	while (it.hasNext()) {
	    Column temp = it.next();
	    result[i] = temp.getName();
	    i++;
	}

	return result;

    }

    public static Vector CreateVectorFromColumnList(List<Column> col_list) {

	Vector result = new Vector();

	Iterator<Column> it = col_list.iterator();

	while (it.hasNext()) {
	    Column temp = it.next();
	    result.add(temp.getName());

	}

	return result;

    }

    public static String[] CreateListFromOrderByList(List<OrderBy> ord_list) {

	String[] result = new String[ord_list.size()];

	Iterator<OrderBy> it = ord_list.iterator();
	int i = 0;
	while (it.hasNext()) {
	    OrderBy temp = it.next();
	    result[i] = temp.getName();
	    i++;
	}

	return result;

    }

    public static Vector CreateVectorFromOrderByList(List<OrderBy> ord_list) {

	Vector result = new Vector();

	Iterator<OrderBy> it = ord_list.iterator();

	while (it.hasNext()) {
	    OrderBy temp = it.next();
	    result.add(temp.getName());

	}

	return result;

    }

    public static OrderBy CreateOrderBy(Usuario user, OrderBy TOOrderBy)
	    throws InternalException, NoPermisosException {

	// testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We create the new instance * */
	OrderBy orderBy = new OrderBy();

	/**
	 * 3. We set all the simple attributes (no associations) to the new
	 * instance *
	 */

	orderBy.setName(TOOrderBy.getName());

	if (TOOrderBy.getAssocCustomList() != null
		&& (TOOrderBy.getAssocCustomList().getCustomListcode() != null && !TOOrderBy
			.getAssocCustomList().getCustomListcode().equals(""))) {

	    orderBy.setAssocCustomList(getCustomList(TOOrderBy
		    .getAssocCustomList().getCustomListcode()));

	}

	/** 4. We set the code to the new instance * */
	try {
	    IdentifyManager_Plain im = IdentifyManager_Plain.singleton();

	    orderBy.setOrderBycode(im.getId(TOOrderBy));
	} catch (identifyException ie) {

	    log.error("Error en asignaciï¿½n de nuevo id en CreateOrderBy", ie);
	    throw new Error(ie.getMessage());
	}

	/** 5. We save the new instance to the DB* */
	HibernateUtil.getSession().save(orderBy);

	/** 6. We create an Audit message * */
	// CreateCreationAuditmessage(user, orderBy);

	/** 7. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return orderBy;
    }

    public static OrderBy UpdateOrderBy(Usuario user, OrderBy TOOrderBy)
	    throws InternalException, NoPermisosException {

	// testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain form the DB the instance to modify * */
	OrderBy orderBy = getOrderBy(TOOrderBy.getOrderBycode());
	;

	/**
	 * 3. We set all the simple attributes (no associations) to the instance
	 * *
	 */

	orderBy.setName(TOOrderBy.getName());

	if (TOOrderBy.getAssocCustomList() != null
		&& (TOOrderBy.getAssocCustomList().getCustomListcode() != null && !TOOrderBy
			.getAssocCustomList().getCustomListcode().equals(""))) {

	    orderBy.setAssocCustomList(getCustomList(TOOrderBy
		    .getAssocCustomList().getCustomListcode()));

	}

	/**
	 * 4. We set the DTO version to the modified object and we update it
	 * with the new values in the DB. We evict and update the instance to
	 * prevent concurrent modification *
	 */
	HibernateUtil.getSession().evict(orderBy);
	orderBy.setVersion(TOOrderBy.getVersion());
	HibernateUtil.getSession().update(orderBy);

	/** 5. We create an Audit message * */
	// CreateModificationAuditmessage(user, orderBy);

	/** 6. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return orderBy;
    }

    public static void RemoveOrderBy(Usuario user, String orderBycode)
	    throws NoPermisosException {

	// testIsHHRR(user);

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain the object to delete form the DB. * */
	OrderBy orderBy = getOrderBy(orderBycode);

	/** 3. We mark it as deleted. * */
	orderBy.setDeleted(Boolean.TRUE);

	/** 4. We create an Audit message * */
	// CreateRemovealAuditmessage(user, orderBy);

	/** 5. We commit the DB transaction. * */
	HibernateUtil.commitTransaction();
    }

    public static OrderBy ObtainOrderBy(Usuario user, String orderBycode) {

	/**
	 * 1. We obtain the object from the DB using the private getter and we
	 * return it. *
	 */

	OrderBy orderBy = getOrderBy(orderBycode);
	return orderBy;
    }

    public static List<OrderBy> ObtainAllOrderBysFrom(Usuario user,
	    String customlistcode) {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession()
		.createCriteria(OrderBy.class);

	// we only want to obtain the non deleted objects
	crit.add(Expression.eq("deleted", Boolean.FALSE));

	crit.createCriteria("assocCustomList").add(
		Expression.idEq(customlistcode));

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	List<OrderBy> orderBy = (List<OrderBy>) crit.list();

	return orderBy;

    }

    public static void RemoveAllOrderByFrom(Usuario user, String customlistcode)
	    throws NoPermisosException {

	List<OrderBy> orderbylist = ObtainAllOrderBysFrom(user, customlistcode);
	Iterator<OrderBy> it = orderbylist.iterator();
	while (it.hasNext()) {
	    OrderBy temp = it.next();
	    RemoveOrderBy(user, temp.getOrderBycode());
	}

    }

    public static void UpdateAllOrderBy(Usuario user, String customlistcode,
	    String[] orderby) throws NoPermisosException, InternalException {

	RemoveAllOrderByFrom(user, customlistcode);
	CustomList customlist = ObtainCustomList(user, customlistcode);
	if (orderby != null) {
	    for (int i = 0; i < orderby.length; i++) {
		OrderBy temp = new OrderBy();
		temp.setAssocCustomList(customlist);
		temp.setName(orderby[i]);

		CreateOrderBy(user, temp);
	    }
	}
    }

    public static FilterType ObtainFilterType(Usuario user,
	    String filterTypecode) {

	/**
	 * 1. We obtain the object from the DB using the private getter and we
	 * return it. *
	 */

	FilterType filterType = getFilterType(filterTypecode);
	return filterType;
    }

    public static List<Views> ObtainAllViews(Usuario user) {

	/** 1. We create an Hibernate Criteria to obtain the desired values * */
	Criteria crit = HibernateUtil.getSession().createCriteria(Views.class);

	// we only want to obtain the non deleted objects
	crit.add(Expression.eq("deleted", Boolean.FALSE));

	Role o = (Role) user.getRoles().toArray()[0];

	Query query = HibernateUtil
		.getSession()
		.createSQLQuery(
			"SELECT name FROM views vw inner join views_permissions per on vw.viewscode=per.view_name or per.view_name='all' where per.entitycode = :paramRole");
	query.setString("paramRole", o.getEntitycode());
	List results = query.list();

	/**
	 * 2. We obtain the list form the DB and we return it with the number of
	 * elements in the DB *
	 */
	List<Views> views = (List<Views>) crit.list();
	List<Views> accesibleViews = new ArrayList();
	for (Views view : views) {
	    if (results.contains(view.getName()))
		accesibleViews.add(view);
	}

	return accesibleViews;

    }

    public static void UpdateCustomListElements(Usuario user,
	    String customlistcode, String[] columns, String[] orderby,
	    List<String[]> filter)

    throws NoPermisosException, InternalException {

	CustomList cl = ObtainCustomList(user, customlistcode);
	cl.setUpdate_date(new Date());

	UpdateCustomList(user, cl);
	UpdateAllColumns(user, customlistcode, columns);
	UpdateAllOrderBy(user, customlistcode, orderby);
	UpdateAllFilter(user, customlistcode, filter);

    }

    public static void DeleteCustomListElements(Usuario user,
	    String customlistcode) throws NoPermisosException {

	RemoveAllColumnsFrom(user, customlistcode);
	RemoveAllFiltersFrom(user, customlistcode);
	RemoveAllOrderByFrom(user, customlistcode);
	RemoveCustomList(user, customlistcode);
    }

    public static String SetCurrentCompensation(Usuario usuario,
	    String compensationcode) {
	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain the object to delete form the DB. * */
	Compensation compensation = getCompensation(compensationcode);

	boolean oldValue = compensation.isCurrent();

	Set<Compensation> lineasDeCompensation = compensation
		.getCompensation_personal().getIcompensation_personal();

	for (Compensation linea : lineasDeCompensation) {
	    linea.setCurrent(false);
	}

	/** 3. We mark it as deleted. * */
	compensation.setCurrent(!oldValue);

	/** 4. We create an Audit message * */
	// CreateRemovealAuditmessage(user, professional);

	/** 5. We commit the DB transaction. * */
	HibernateUtil.commitTransaction();

	return compensation.getCompensation_personal().getPersonalcode();
    }

    public static String SetCurrentGrant_concession(Usuario usuario,
	    String grant_concessioncode) {

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain the object to delete form the DB. * */
	Grant_concession grant_concession = getGrant_concession(grant_concessioncode);

	boolean oldValue = grant_concession.isCurrent();

	Set<Grant_concession> lineasDeGrant_concessioncode = grant_concession
		.getGrant_concession_personal().getIgrant_concession_personal();

	for (Grant_concession linea : lineasDeGrant_concessioncode) {
	    linea.setCurrent(false);
	}

	/** 3. We mark it as deleted. * */
	grant_concession.setCurrent(!oldValue);

	/** 4. We create an Audit message * */
	// CreateRemovealAuditmessage(user, professional);

	/** 5. We commit the DB transaction. * */
	HibernateUtil.commitTransaction();

	return grant_concession.getGrant_concession_personal()
		.getPersonalcode();
    }

    public static String SetCurrentAcademic_info(Usuario usuario,
    	    String academic_infocode) {

    	/** 1. We begin the DB transaction. * */
    	HibernateUtil.beginTransaction();

    	/** 2. We obtain the object to delete form the DB. * */
    	Academic_info academic_info = getAcademic_info(academic_infocode);

    	boolean oldValue = academic_info.isCurrent();

    	Set<Academic_info> lineasDeAcademic_infocode = academic_info
    		.getAcademic_info_personal().getIacademic_info_personal();

    	for (Academic_info linea : lineasDeAcademic_infocode) {
    	    linea.setCurrent(false);
    	}

    	/** 3. We mark it as deleted. * */
    	academic_info.setCurrent(!oldValue);

    	/** 4. We create an Audit message * */
    	// CreateRemovealAuditmessage(user, professional);

    	/** 5. We commit the DB transaction. * */
    	HibernateUtil.commitTransaction();

    	return academic_info.getAcademic_info_personal()
    		.getPersonalcode();
    }
    
    public static CustomList SetPeriodicCustomList(Usuario usuario,
	    String customListcode) {

	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 2. We obtain the object to delete form the DB. * */
	CustomList customList = getCustomList(customListcode);

	boolean oldValue = customList.isPeriodic();

	customList.setPeriodic(!oldValue);

	/** 5. We commit the DB transaction. * */
	HibernateUtil.commitTransaction();

	return customList;
    }

    public static Personal_comment ObtainPersonal_comment(Usuario usuario,
	    String personal_commentcode) {
	Personal_comment personal_comment = getPersonal_comment(personal_commentcode);
	return personal_comment;
    }

    public static Personal_comment UpdatePersonal_comment(Usuario usuario,
	    Personal_comment TOPersonal_comment) {
	/** 1. We begin the DB transaction. * */
	HibernateUtil.beginTransaction();

	/** 3. We obtain form the DB the instance to modify * */
	Personal_comment personal_comment = getPersonal_comment(TOPersonal_comment
		.getPersonal_commentcode());

	/**
	 * 4. We set all the simple attributes (no associations) to the instance
	 * *
	 */

	// personal_comment.setInput_date(TOPersonal_comment.getInput_date());

	personal_comment.setText(TOPersonal_comment.getText());

	/**
	 * 5. We set the DTO version to the modified object and we update it
	 * with the new values in the DB. We evict and update the instance to
	 * prevent concurrent modification *
	 */
	HibernateUtil.getSession().evict(personal_comment);

	HibernateUtil.getSession().update(personal_comment);

	/** 6. We create an Audit message * */
	CreateModificationAuditmessage(usuario, personal_comment);

	/** 7. We commit the DB transaction and return the new instance * */
	HibernateUtil.commitTransaction();

	return personal_comment;
    }

    public static void HolidaysChangeOfYear() {
	int year = getCurrentYearForHolidays();
	int nextYear = year + 1;

	// AQUI HAY QUE CAMBIAR EL Aï¿½O

	List<Personal> personals = ObtainPersonalWithContract(null,
		new ListConfigurator()).getSecond();

	for (Personal personal : personals) {

	    Irbholidayinfo oldirbholidayinfo = ObtainIrbholidayinfoFromPersonal(
		    null, personal.getPersonalcode(), new GregorianCalendar(
			    year, 0, 1));

	    Irbholidayinfo holidayinfo = CreateOrModifyIrbholidayinfo(null,
		    ObtainActiveProfessionalFromPersonal(personal));
	    holidayinfo.setPreviousyearholidaysforyear(oldirbholidayinfo
		    .getRemainingholidays());
	    RecalculateHolidaysFromPersonal(holidayinfo);
	}
    }

    public static int createNextYearHolidays(Usuario usuario) {

	HibernateUtil.beginTransaction();

	int currentYear = getCurrentYearForHolidays();
	int nextYear = currentYear + 1;

	Pair<Integer, List<Personal>> _list_personal = ObtainPersonalWithContract(
		null,
		(new ViewListConfiguration())
			.obtainListConfiguratorForViews(null));

	for (Personal perso : _list_personal.getSecond()) {
	    for (Professional pro : perso.getIprofessional_personal()) {
		if (pro.isCurrent() && !pro.isDeleted()) {

		    Irbholidayinfo currentHolidayInfo = ObtainIrbholidayinfoFromPersonal(
			    null, perso.getPersonalcode(),
			    new GregorianCalendar(currentYear, 0, 1));

		    int remainingHolidays = currentHolidayInfo != null ? currentHolidayInfo
			    .getRemainingholidays() : 0;

		    if (remainingHolidays < 0)
			remainingHolidays = 0;
		    CreateOrModifyIrbholidayinfo(usuario, pro, nextYear,
			    remainingHolidays);
		}
	    }
	}

	Criteria crit = HibernateUtil.getSession().createCriteria(
		Irbholiday.class);

	crit.add(Expression.eq("type",
		Irbholiday.TYPE_CURRENT_YEAR_FOR_HOLIDAYS));

	List<Irbholiday> list = crit.list();

	if (list != null && list.size() > 0) {
	    list.get(0).setInitialdate(
		    new GregorianCalendar(nextYear, 0, 1).getTime());
	    list.get(0).setEnddate(
		    new GregorianCalendar(nextYear, 0, 1).getTime());
	    HibernateUtil.getSession().update(list.get(0));
	}

	HibernateUtil.commitTransaction();

	return nextYear;
    }

    public static List<Irbholiday> ObtainHolidaysBetweenDates(Date startDate,
	    Date endDate) {
	Criteria crit = HibernateUtil.getSession().createCriteria(
		Irbholiday.class);

	crit.add(Expression.eq("status", Irbholiday.STATUS_APROVAT))
		.add(Expression.le("initialdate", endDate))
		.add(Expression.ge("enddate", startDate))
		.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

	return (List<Irbholiday>) crit.list();

    }

    public static Pair<Integer, List<Personal>> ObtainPersonalWithContractByUnit(
	    Usuario usuario, String unitcode) {
	String irbCodeString = MAINCONFIG
		.getString("irbPayrollInstitutionCode");

	Criteria crit = HibernateUtil.getSession().createCriteria(
		Personal.class);

	crit.add(Expression.eq("deleted", Boolean.FALSE)).add(
		Expression.eq("state",
			getPersonalstate(Personalstate.ACTIVE_CODE)));

	Criteria subCrit = crit
		.createCriteria("iprofessional_personal")
		.add(Expression.eq("current", Boolean.TRUE))
		.add(Expression.eq("payroll_institution",
			getPayroll_institution(irbCodeString)));

	if (unitcode != null && !unitcode.equals("")) {
	    subCrit.add(Expression.eq("professional_unit.unitcode", unitcode));
	}

	crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

	crit.addOrder(Order.asc("surname1"));
	crit.addOrder(Order.asc("surname2"));

	// int count = configurator != null ? configurator.addCriterions(crit) :
	// 0;

	List<Personal> personals = (List<Personal>) crit.list();

	Set<String> personalcodes = new HashSet<String>();
	List<Personal> toRemove = new ArrayList();
	for (Personal p : personals) {
	    if (personalcodes.contains(p.getPersonalcode())) {
		toRemove.add(p);
	    } else {
		personalcodes.add(p.getPersonalcode());
	    }
	}

	for (Personal p : toRemove) {
	    personals.remove(p);
	    // count--;
	}

	Pair<Integer, List<Personal>> pair = new Pair<Integer, List<Personal>>(
		personals.size(), personals);

	return pair;
    }
    
    
    private static void exportPersonalToAlumni(Usuario user, String personalcode){
    	//Begin transaction
    	HibernateUtil.beginTransaction();
    	
    	Personal personal = getPersonal(personalcode);
    	//Copy into alumni 
    	Alumni_personal alumni_personal = new Alumni_personal();

    	//Default values
    	alumni_personal.setVersion(0);
    	alumni_personal.setDeleted(false);
    	alumni_personal.setExternal(false);
    	alumni_personal.setVerified(false);
    	alumni_personal.setShow_data(false);
    	
    	alumni_personal.setAlumni_personalcode(personal.getPersonalcode());
    	alumni_personal.setFirstname(personal.getName());
    	String surname = personal.getSurname1();
    	if(personal.getSurname2()!=null && personal.getSurname2().length()>0){
    		surname += " " + personal.getSurname2();
    	}
    	alumni_personal.setSurname(surname);
    	alumni_personal.setIrb_surname(surname);
    	
    	if(personal.getGender()!=null)
    		alumni_personal.setGender(personal.getGender());
    	
    	if(personal.getNationality()!=null)
    		alumni_personal.setNationality(personal.getNationality());

    	if(personal.getNationality_2()!=null)
    		alumni_personal.setNationality_2(personal.getNationality_2());
    	
    	if(personal.getBirth_date()!=null)
    		alumni_personal.setBirth(personal.getBirth_date());
    	
    	if(personal.getPersonal_email()!=null && personal.getPersonal_email().length()>0)
    		alumni_personal.setEmail(personal.getPersonal_email());
    	
    	
    	
    	HibernateUtil.getSession().save(alumni_personal);
    	
    	//Copy into alumni_irb_jobs
    	Set<Professional> professionals = personal.getIprofessional_personal();
    	if(professionals!=null){
	    	for (Professional p : professionals){
	    		if(p.isDeleted()){
	    			continue;
	    		}
	    		Alumni_irb_jobs alumni_irb_job = new Alumni_irb_jobs();
	    		alumni_irb_job.setDeleted(false);
	    		alumni_irb_job.setVersion(0);
	    		
	    		//Create code
	    		try {
	    		    IdentifyManager_Plain im = IdentifyManager_Plain.singleton();
	    		    alumni_irb_job.setAlumni_irb_jobscode(im.getId(alumni_irb_job));
	    		} catch (identifyException ie) {
	
	    		    log.error(
	    			    "Error en asignación de nuevo id en ExportPersonalToAlumni",
	    			    ie);
	    		    throw new Error(ie.getMessage());
	    		}    		
	    		alumni_irb_job.setStart_date(p.getStart_date());
	    		alumni_irb_job.setEnd_date(p.getEnd_date());
	    		alumni_irb_job.setUnit(p.getProfessional_unit());
	    		alumni_irb_job.setUnit_2(p.getProfessional_unit_2());
	    		
	    		alumni_irb_job.setResearch_group(p.getResearch_group());
	    		alumni_irb_job.setResearch_group_2(p.getResearch_group_2());
	    		
	    		if (p.getPosition()!=null){
	    			Alumni_irb_job_positions position = getAlumni_irb_job_positions(p.getPosition().getCode());
	    			if (position!=null) {
	    				alumni_irb_job.setIrb_job_positions(position);
	    			}
	    		}
	    		alumni_irb_job.setPersonal(alumni_personal);
	    		alumni_personal.addIalumni_irb_jobs(alumni_irb_job);
	    		HibernateUtil.getSession().save(alumni_irb_job);
	    	}
    	}
    	
    	/** 7. We create an Audit message * */
    	CreateCreationAuditmessage(user, alumni_personal);    	
    	
    	
    	
    	//End transaction
    	/** 8. We commit the DB transaction and return the new instance * */
    	HibernateUtil.commitTransaction();
    }
    
    	
    public static void ForceExportAlumni(Usuario user){
    	ExportAlumni(user, null);
    }
    
    private static void updateBeancodes(String table){
    	HibernateUtil.beginTransaction();
    	String query = "update beancodes set codenumber = lpad(((select max(" + table + "code) from " + table + " ) + 1),13,0) where bean = '" + table + "'";
		SQLQuery sqlQuery = HibernateUtil.getSession().createSQLQuery(query);  
		sqlQuery.executeUpdate();  
		HibernateUtil.commitTransaction();
    }
    
    public static void ExportAlumni(Usuario user, String personalcode){
    	Alumni_params alumni_params = getAlumni_params("EXPORT_MIN_DAYS");
    	int days = Integer.parseInt(alumni_params.getValue());
    	
    	//Search for personal with professional longer than @days and NOT in current alumni
    	Criteria crit = HibernateUtil.getSession().createCriteria(Personal.class);
    	crit.add(Expression.eq("deleted", Boolean.FALSE));
    	Date fromDate = new Date();  
    	Calendar c = Calendar.getInstance();
    	c.setTime(fromDate);
    	c.add(Calendar.DATE, -days);
    	fromDate.setTime(c.getTime().getTime());    	
    
    	crit.createAlias("iprofessional_personal", "p", Criteria.INNER_JOIN);    	
	    crit.add(Restrictions.eq("p.deleted", Boolean.FALSE));
    	crit.add(Restrictions.le("p.start_date", fromDate));
    	
    	//Only obtain one person it the personal code it's set
    	if(personalcode!=null)
    		crit.add(Restrictions.eq("personalcode", personalcode));    	

    	//Inactive
    	crit.add(Restrictions.eq("state.personalstatecode", Personalstate.INACTIVE_CODE));
    	DetachedCriteria subquery = DetachedCriteria.forClass(Alumni_personal.class)
    		    //.add(Expression.eq("deleted", Boolean.FALSE))
    		    .setProjection(Projections.property("alumni_personalcode"));
    	
    	crit.add(Subqueries.propertyNotIn("personalcode", subquery));
    	crit.setProjection(Projections.projectionList().add(Projections.property("personalcode")).add(Projections.groupProperty("personalcode")));
    	
    	List<Object> personal = (List<Object>) crit.list();
    	
    	if(personal.size()>0){
    		log.debug("Initializing export of " + personal.size() + " personal");
	    	for(Object p : personal){
	    		exportPersonalToAlumni(user, (String)((Object[])p)[0]);
	    		log.debug("Exported: " + p);    		
	    	}
	    	
	    	updateBeancodes("alumni_personal");
	    	updateBeancodes("alumni_irb_jobs");
    	}
    }
    
    public static void ValidateAlumniPersonal(Usuario user, Alumni_personal external_alumni_personal, Alumni_personal alumni_personal) throws ValidationFailedException, NoPermisosException, InternalException, UsuarioNoActivoException {
    	
    	HibernateUtil.beginTransaction();
    	
    	if(alumni_personal.isExternal()){
    		String e = "Error, the target alumni personal can not be external";
    		log.error(e);
    		throw new Error(e);
    	}
    	
    	// Alumni information
    	alumni_personal.setSurname(external_alumni_personal.getSurname());
    	alumni_personal.setUrl(external_alumni_personal.getUrl());
    	alumni_personal.setFacebook(external_alumni_personal.getFacebook());
    	alumni_personal.setLinkedin(external_alumni_personal.getLinkedin());
    	alumni_personal.setTwitter(external_alumni_personal.getTwitter());
    	alumni_personal.setKeywords(external_alumni_personal.getKeywords());
    	alumni_personal.setBiography(external_alumni_personal.getBiography());
    	alumni_personal.setAwards(external_alumni_personal.getAwards());
    	alumni_personal.setORCIDID(external_alumni_personal.getORCIDID());
    	alumni_personal.setPubmedid(external_alumni_personal.getPubmedid());
    	alumni_personal.setResearcherid(external_alumni_personal.getResearcherid());
    	alumni_personal.setVerified(external_alumni_personal.isVerified());
    	alumni_personal.setShow_data(external_alumni_personal.isShow_data());
    	
    	alumni_personal.setRemarks(external_alumni_personal.getRemarks());
	    alumni_personal.setSkype(external_alumni_personal.getSkype());
	    alumni_personal.setCellphone(external_alumni_personal.getCellphone());
	    alumni_personal.setDeceased(external_alumni_personal.isDeceased());
	    alumni_personal.setCommunications_wanted(external_alumni_personal.isCommunications_wanted());
    	    	
    	// External jobs and communications
    	Set<Alumni_communications> communications = external_alumni_personal.getIalumni_communications();
    	Set<Alumni_external_jobs> external_jobs = external_alumni_personal.getIalumni_external_jobs();
    	
    	if(communications.size()>0){
    		Iterator<Alumni_communications> communicationsIt = communications.iterator();
    		Set<Alumni_communications> alumni_communications = new HashSet<Alumni_communications>();
    		while(communicationsIt.hasNext()) {
	        	Alumni_communications c = communicationsIt.next();
	        	Alumni_communications c2 = getAlumni_communications(c.getAlumni_communicationscode());
	        	alumni_communications.add(c2);
	        }	        
	        alumni_personal.clearIalumni_communications();
	        alumni_personal.setIalumni_communications(alumni_communications);    
    	}
    	
    	
    	if(external_jobs.size()>0){
	    	Iterator<Alumni_external_jobs> externalJobsIt = external_jobs.iterator();
	        while(externalJobsIt.hasNext()) {
	        	externalJobsIt.next().setPersonal(alumni_personal);
	        }	        
    	}
    	
    	if(external_alumni_personal.getTitles()!=null){
    		alumni_personal.setTitles(external_alumni_personal.getTitles());
    	}
    	
    	alumni_personal.setVersion(alumni_personal.getVersion()+1);
    	HibernateUtil.getSession().update(alumni_personal);
    	
    	CreateCreationAuditmessage(user, alumni_personal);
    	
    	HibernateUtil.commitTransaction();
    	
    	RemoveAlumni_personal(user, external_alumni_personal.getAlumni_personalcode());
    	
    }
}