package bussineslogic.controlers;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import utils.Pair;
import utils.filter.ListConfigurator;
import bussineslogic.excepciones.HolidaysException;
import bussineslogic.excepciones.InternalException;
import bussineslogic.excepciones.NoPermisosException;
import bussineslogic.excepciones.UsuarioExisteException;
import bussineslogic.excepciones.UsuarioNoActivoException;
import bussineslogic.excepciones.ValidationFailedException;
import bussineslogic.objects.Academic_info;
import bussineslogic.objects.Area;
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
import bussineslogic.objects.OrderBy;
import bussineslogic.objects.Organization_unit;
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
import bussineslogic.objects.Usuario;
import bussineslogic.objects.Views;
import bussineslogic.objects.Work_experience;
import bussineslogic.objects.Working_hours;

import com.justinmind.usermanagement.entity.Language;
import com.justinmind.usermanagement.entity.Role;

/**
 * This class contains the use case facades of the application.
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class UseCaseFacade {

	//	Use case methods 

	/**
	 * This method creates a research_group.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param research_group Research_group data transfer object (DTO) with the values of the new instance.
	 * @return the new research_group created with the Use Case
	 * @throws InternalException
	 * @throws NoPermisosException 
	 * @throws ValidationFailedException 
	 */
	public static Research_group CreateResearch_group(String administradorId, Research_group research_group) throws InternalException, NoPermisosException, ValidationFailedException {
		return UseCase.CreateResearch_group(UseCase.getUsuario(administradorId), research_group);
	}

	/**
	 * This method modifies a research_group.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param research_group Research_group data transfer object (DTO) with the values of the modified instance. The code of this attribute indicates which research_group will be modified.
	 * @return the modified research_group
	 * @throws InternalException
	 * @throws NoPermisosException 
	 * @throws ValidationFailedException 
	 */
	public static Research_group UpdateResearch_group(String administradorId, Research_group research_group) throws InternalException, NoPermisosException, ValidationFailedException {
		return UseCase.UpdateResearch_group(UseCase.getUsuario(administradorId), research_group);
	}

	/**
	 * This method removes a research_group.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param research_groupcode  Code of the research_group to be removed
	 */
	public static void RemoveResearch_group(String administradorId, String research_groupcode) throws InternalException, NoPermisosException {
		UseCase.RemoveResearch_group(UseCase.getUsuario(administradorId), research_groupcode);
	}

	/**
	 * This method obtains one instance of research_group given its code.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param research_groupcode Code of the research_group to be obtained
	 * @return Research_group with the given code.
	 * @throws InternalException
	 */
	public static Research_group ObtainResearch_group(String administradorId, String research_groupcode) throws InternalException {
		return UseCase.ObtainResearch_group(UseCase.getUsuario(administradorId), research_groupcode);
	}

	/**
	 * This method obtains all instances of Research_group, given a list-configurator.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param configurator ListConfigurator to be used
	 * @return A pair with an Integer with the total number of instances which match the search without appling the 'pagination' of the ListConfigurator, and the list of the instances which match the configurator (incluing pagination)
	 * @throws InternalException
	 */
	public static Pair<Integer, List<Research_group>> ObtainAllResearch_group(String administradorId, ListConfigurator configurator) throws InternalException {
		return UseCase.ObtainAllResearch_group(UseCase.getUsuario(administradorId), configurator);
	}

	/**
	 * This method obtains all instances of Personal, which belong to the set of supervisor of a research_group, given a list-configurator.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param research_groupcode Code of the research_group which contains the set of supervisor
	 * @param configurator ListConfigurator to be used
	 * @return A pair with an Integer with the total number of instances which match the search without appling the 'pagination' of the ListConfigurator, and the list of the instances which match the configurator (incluing pagination)
	 */
	public static Pair<Integer, List<Personal>> ObtainAllSupervisorFromResearch_group(String administradorId, String research_groupcode, ListConfigurator configurator) throws InternalException {
		return UseCase.ObtainAllSupervisorFromResearch_group(UseCase.getUsuario(administradorId), UseCase.getResearch_group(research_groupcode), configurator);
	}

	/**
	 * This method obtains all instances of Professional, which belong to the set of iresearch_group of a research_group, given a list-configurator.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param research_groupcode Code of the research_group which contains the set of iresearch_group
	 * @param configurator ListConfigurator to be used
	 * @return A pair with an Integer with the total number of instances which match the search without appling the 'pagination' of the ListConfigurator, and the list of the instances which match the configurator (incluing pagination)
	 */
	public static Pair<Integer, List<Professional>> ObtainAllIresearch_groupFromResearch_group(String administradorId, String research_groupcode, ListConfigurator configurator) throws InternalException {
		return UseCase.ObtainAllIresearch_groupFromResearch_group(
				UseCase.getUsuario(administradorId),
				UseCase.getResearch_group(research_groupcode),
				configurator);
	}

	/**
	 * This method obtains all instances of Education, which belong to the set of ieducation_personal of a personal, given a list-configurator.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param personalcode Code of the personal which contains the set of ieducation_personal
	 * @param configurator ListConfigurator to be used
	 * @return A pair with an Integer with the total number of instances which match the search without appling the 'pagination' of the ListConfigurator, and the list of the instances which match the configurator (incluing pagination)
	 * @throws NoPermisosException 
	 */
	public static Pair<Integer, List<Education>> ObtainAllIeducation_personalFromPersonal(String administradorId, String personalcode, ListConfigurator configurator) throws InternalException, NoPermisosException {
		return UseCase.ObtainAllIeducation_personalFromPersonal(UseCase.getUsuario(administradorId), UseCase.getPersonal(personalcode), configurator);
	}

	/**
	 * This method obtains all instances of Professional, which belong to the set of iprofessional_personal of a personal, given a list-configurator.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param personalcode Code of the personal which contains the set of iprofessional_personal
	 * @param configurator ListConfigurator to be used
	 * @return A pair with an Integer with the total number of instances which match the search without appling the 'pagination' of the ListConfigurator, and the list of the instances which match the configurator (incluing pagination)
	 * @throws NoPermisosException 
	 */
	public static Pair<Integer, List<Professional>> ObtainAllIprofessional_personalFromPersonal(String administradorId, String personalcode, ListConfigurator configurator) throws InternalException, NoPermisosException {
		return UseCase.ObtainAllIprofessional_personalFromPersonal(UseCase.getUsuario(administradorId), UseCase.getPersonal(personalcode), configurator);
	}

	/**
	 * This method obtains all instances of Compensation, which belong to the set of icompensation_personal of a personal, given a list-configurator.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param personalcode Code of the personal which contains the set of icompensation_personal
	 * @param configurator ListConfigurator to be used
	 * @return A pair with an Integer with the total number of instances which match the search without appling the 'pagination' of the ListConfigurator, and the list of the instances which match the configurator (incluing pagination)
	 * @throws NoPermisosException 
	 */
	public static Pair<Integer, List<Compensation>> ObtainAllIcompensation_personalFromPersonal(String administradorId, String personalcode, ListConfigurator configurator) throws InternalException, NoPermisosException {
		return UseCase.ObtainAllIcompensation_personalFromPersonal(UseCase.getUsuario(administradorId), UseCase.getPersonal(personalcode), configurator);
	}

	/**
	 * This method obtains the current compensation of a personal
	 * 
	 * @param person The user who executes this use case
	 * @param personal Personal which contains the set of icompensation_personal
	 * @return the las compesation
	 * @throws InternalException 
	 * @throws NoPermisosException 
	 */
	public static Compensation ObtainCurrentCompensationFromPersonal(String administradorId, String personalcode) throws InternalException, NoPermisosException {
		return UseCase.ObtainCurrentCompensationFromPersonal(UseCase.getUsuario(administradorId), UseCase.getPersonal(personalcode));
	}

	/**
	 * This method obtains all instances of Holiday, which belong to the set of iholiday_personal of a personal, given a list-configurator.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param personalcode Code of the personal which contains the set of iholiday_personal
	 * @param configurator ListConfigurator to be used
	 * @return A pair with an Integer with the total number of instances which match the search without appling the 'pagination' of the ListConfigurator, and the list of the instances which match the configurator (incluing pagination)
	 */
	public static Pair<Integer, List<Holiday>> ObtainAllIholiday_personalFromPersonal(String administradorId, String personalcode, ListConfigurator configurator) throws InternalException {
		return UseCase.ObtainAllIholiday_personalFromPersonal(UseCase.getUsuario(administradorId), UseCase.getPersonal(personalcode), configurator);
	}

	/**
	 * This method obtains all instances of Grant_concession, which belong to the set of igrant_concession_personal of a personal, given a list-configurator.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param personalcode Code of the personal which contains the set of igrant_concession_personal
	 * @param configurator ListConfigurator to be used
	 * @return A pair with an Integer with the total number of instances which match the search without appling the 'pagination' of the ListConfigurator, and the list of the instances which match the configurator (incluing pagination)
	 * @throws NoPermisosException 
	 */
	public static Pair<Integer, List<Grant_concession>> ObtainAllIgrant_concession_personalFromPersonal(String administradorId, String personalcode, ListConfigurator configurator) throws InternalException, NoPermisosException {
		return UseCase.ObtainAllIgrant_concession_personalFromPersonal(UseCase.getUsuario(administradorId), UseCase.getPersonal(personalcode), configurator);
	}

	/**
	 * This method obtains all instances of Academic_info, which belong to the set of iacademic_info_personal of a personal, given a list-configurator.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param personalcode Code of the personal which contains the set of iacademic_info_personal
	 * @param configurator ListConfigurator to be used
	 * @return A pair with an Integer with the total number of instances which match the search without appling the 'pagination' of the ListConfigurator, and the list of the instances which match the configurator (incluing pagination)
	 * @throws NoPermisosException 
	 */
	public static Pair<Integer, List<Academic_info>> ObtainAllIacademic_info_personalFromPersonal(String administradorId, String personalcode, ListConfigurator configurator) throws InternalException, NoPermisosException {
		return UseCase.ObtainAllIacademic_info_personalFromPersonal(UseCase.getUsuario(administradorId), UseCase.getPersonal(personalcode), configurator);
	}
	
	/**
	 * This method obtains all instances of Work_experience, which belong to the set of iwork_experience_personal of a personal, given a list-configurator.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param personalcode Code of the personal which contains the set of iwork_experience_personal
	 * @param configurator ListConfigurator to be used
	 * @return A pair with an Integer with the total number of instances which match the search without appling the 'pagination' of the ListConfigurator, and the list of the instances which match the configurator (incluing pagination)
	 * @throws NoPermisosException 
	 */
	public static Pair<Integer, List<Work_experience>> ObtainAllIwork_experience_personalFromPersonal(String administradorId, String personalcode, ListConfigurator configurator) throws InternalException, NoPermisosException {
		return UseCase.ObtainAllIwork_experience_personalFromPersonal(UseCase.getUsuario(administradorId), UseCase.getPersonal(personalcode), configurator);
	}

	/**
	 * This method obtains all instances of Funding_detail, which belong to the set of ifunding_detail_personal of a personal, given a list-configurator.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param personalcode Code of the personal which contains the set of ifunding_detail_personal
	 * @param configurator ListConfigurator to be used
	 * @return A pair with an Integer with the total number of instances which match the search without appling the 'pagination' of the ListConfigurator, and the list of the instances which match the configurator (incluing pagination)
	 * @throws NoPermisosException 
	 */
	public static Pair<Integer, List<Funding_detail>> ObtainAllIfunding_detail_personalFromPersonal(String administradorId, String personalcode, ListConfigurator configurator) throws InternalException, NoPermisosException {
		return UseCase.ObtainAllIfunding_detail_personalFromPersonal(UseCase.getUsuario(administradorId), UseCase.getPersonal(personalcode), configurator);
	}

	/**
	 * This method obtains all instances of Child, which belong to the set of ichild_personal of a personal, given a list-configurator.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param personalcode Code of the personal which contains the set of ichild_personal
	 * @param configurator ListConfigurator to be used
	 * @return A pair with an Integer with the total number of instances which match the search without appling the 'pagination' of the ListConfigurator, and the list of the instances which match the configurator (incluing pagination)
	 */
	public static Pair<Integer, List<Child>> ObtainAllIchild_personalFromPersonal(String administradorId, String personalcode, ListConfigurator configurator) throws InternalException {
		return UseCase.ObtainAllIchild_personalFromPersonal(UseCase.getUsuario(administradorId), UseCase.getPersonal(personalcode), configurator);
	}

	public static Pair<Integer, List<Personal_comment>> ObtainAllIpersonal_commentsFromPersonal(String administradorId, String personalcode, ListConfigurator configurator) throws InternalException, NoPermisosException {
		return UseCase.ObtainAllIpersonal_commentsFromPersonal(UseCase.getUsuario(administradorId), UseCase.getPersonal(personalcode), configurator);
	}

	/**
	 * This method obtains all instances of Benefits, which belong to the set of ibenefits_personal of a personal, given a list-configurator.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param personalcode Code of the personal which contains the set of ibenefits_personal
	 * @param configurator ListConfigurator to be used
	 * @return A pair with an Integer with the total number of instances which match the search without appling the 'pagination' of the ListConfigurator, and the list of the instances which match the configurator (incluing pagination)
	 * @throws NoPermisosException 
	 */
	public static Pair<Integer, List<Benefits>> ObtainAllIbenefits_personalFromPersonal(String administradorId, String personalcode, ListConfigurator configurator) throws InternalException, NoPermisosException {
		return UseCase.ObtainAllIbenefits_personalFromPersonal(UseCase.getUsuario(administradorId), UseCase.getPersonal(personalcode), configurator);
	}

	/**
	 * This method creates a professional.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param professional Professional data transfer object (DTO) with the values of the new instance.
	 * @return the new professional created with the Use Case
	 * @throws InternalException
	 * @throws ValidationFailedException 
	 */
	public static Professional CreateProfessional(String administradorId, Professional professional) throws InternalException, ValidationFailedException {
		return UseCase.CreateProfessional(UseCase.getUsuario(administradorId), professional);
	}

	/**
	 * This method modifies a professional.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param professional Professional data transfer object (DTO) with the values of the modified instance. The code of this attribute indicates which professional will be modified.
	 * @return the modified professional
	 * @throws InternalException
	 * @throws NoPermisosException 
	 * @throws ValidationFailedException 
	 */
	public static Professional UpdateProfessional(String administradorId, Professional professional) throws InternalException, NoPermisosException, ValidationFailedException {
		return UseCase.UpdateProfessional(UseCase.getUsuario(administradorId), professional);
	}

	/**
	 * This method removes a professional.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param professionalcode  Code of the professional to be removed
	 */
	public static void RemoveProfessional(String administradorId, String professionalcode) throws InternalException, NoPermisosException {
		UseCase.RemoveProfessional(UseCase.getUsuario(administradorId), professionalcode);
	}

	public static String SetCurrentProfessional(String administratorId, String professionalcode) throws InternalException, NoPermisosException {
		return UseCase.SetCurrentProfessional(UseCase.getUsuario(administratorId), professionalcode);
	}

	/**
	 * This method obtains one instance of professional given its code.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param professionalcode Code of the professional to be obtained
	 * @return Professional with the given code.
	 * @throws InternalException
	 */
	public static Professional ObtainProfessional(String administradorId, String professionalcode) throws InternalException {
		return UseCase.ObtainProfessional(UseCase.getUsuario(administradorId), professionalcode);
	}

	/**
	 * This method obtains all instances of Professional, given a list-configurator.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param configurator ListConfigurator to be used
	 * @return A pair with an Integer with the total number of instances which match the search without appling the 'pagination' of the ListConfigurator, and the list of the instances which match the configurator (incluing pagination)
	 * @throws InternalException
	 */
	public static Pair<Integer, List<Professional>> ObtainAllProfessional(String administradorId, ListConfigurator configurator) throws InternalException {
		return UseCase.ObtainAllProfessional(UseCase.getUsuario(administradorId), configurator);
	}

	/**
	 * This method creates a location.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param location Location data transfer object (DTO) with the values of the new instance.
	 * @return the new location created with the Use Case
	 * @throws InternalException
	 * @throws ValidationFailedException 
	 */
	public static Location CreateLocation(String administradorId, Location location) throws InternalException, ValidationFailedException, NoPermisosException {
		return UseCase.CreateLocation(UseCase.getUsuario(administradorId), location);
	}

	/**
	 * This method modifies a location.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param location Location data transfer object (DTO) with the values of the modified instance. The code of this attribute indicates which location will be modified.
	 * @return the modified location
	 * @throws InternalException
	 */
	public static Location UpdateLocation(String administradorId, Location location) throws InternalException, NoPermisosException {
		return UseCase.UpdateLocation(UseCase.getUsuario(administradorId), location);
	}

	public static Location updateLocationCode(String administradorId, String locationCode, String newLocationCode) throws ValidationFailedException, NoPermisosException, InternalException {
		return UseCase.updateLocationCode(UseCase.getUsuario(administradorId), locationCode, newLocationCode);
	}

	/**
	 * This method removes a location.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param locationcode  Code of the location to be removed
	 */
	public static void RemoveLocation(String administradorId, String locationcode) throws InternalException, NoPermisosException {
		UseCase.RemoveLocation(UseCase.getUsuario(administradorId), locationcode);
	}

	/**
	 * This method obtains one instance of location given its code.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param locationcode Code of the location to be obtained
	 * @return Location with the given code.
	 * @throws InternalException
	 */
	public static Location ObtainLocation(String administradorId, String locationcode) throws InternalException {
		return UseCase.ObtainLocation(UseCase.getUsuario(administradorId), locationcode);
	}

	/**
	 * This method obtains all instances of Location, given a list-configurator.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param configurator ListConfigurator to be used
	 * @return A pair with an Integer with the total number of instances which match the search without appling the 'pagination' of the ListConfigurator, and the list of the instances which match the configurator (incluing pagination)
	 * @throws InternalException
	 */
	public static Pair<Integer, List<Location>> ObtainAllLocation(String administradorId, ListConfigurator configurator) throws InternalException {
		return UseCase.ObtainAllLocation(UseCase.getUsuario(administradorId), configurator);
	}

	/**
	 * This method creates a type_of_education.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param type_of_education Type_of_education data transfer object (DTO) with the values of the new instance.
	 * @return the new type_of_education created with the Use Case
	 * @throws InternalException
	 */
	public static Type_of_education CreateType_of_education(String administradorId, Type_of_education type_of_education) throws InternalException, NoPermisosException {
		return UseCase.CreateType_of_education(UseCase.getUsuario(administradorId), type_of_education);
	}

	/**
	 * This method modifies a type_of_education.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param type_of_education Type_of_education data transfer object (DTO) with the values of the modified instance. The code of this attribute indicates which type_of_education will be modified.
	 * @return the modified type_of_education
	 * @throws InternalException
	 */
	public static Type_of_education UpdateType_of_education(String administradorId, Type_of_education type_of_education) throws InternalException, NoPermisosException {
		return UseCase.UpdateType_of_education(UseCase.getUsuario(administradorId), type_of_education);
	}

	/**
	 * This method removes a type_of_education.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param type_of_educationcode  Code of the type_of_education to be removed
	 */
	public static void RemoveType_of_education(String administradorId, String type_of_educationcode) throws InternalException, NoPermisosException {
		UseCase.RemoveType_of_education(UseCase.getUsuario(administradorId), type_of_educationcode);
	}

	/**
	 * This method obtains one instance of type_of_education given its code.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param type_of_educationcode Code of the type_of_education to be obtained
	 * @return Type_of_education with the given code.
	 * @throws InternalException
	 */
	public static Type_of_education ObtainType_of_education(String administradorId, String type_of_educationcode) throws InternalException {
		return UseCase.ObtainType_of_education(UseCase.getUsuario(administradorId), type_of_educationcode);
	}

	/**
	 * This method obtains all instances of Type_of_education, given a list-configurator.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param configurator ListConfigurator to be used
	 * @return A pair with an Integer with the total number of instances which match the search without appling the 'pagination' of the ListConfigurator, and the list of the instances which match the configurator (incluing pagination)
	 * @throws InternalException
	 */
	public static Pair<Integer, List<Type_of_education>> ObtainAllType_of_education(String administradorId, ListConfigurator configurator) throws InternalException {
		return UseCase.ObtainAllType_of_education(UseCase.getUsuario(administradorId), configurator);
	}

	/**
	 * This method creates a benefits.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param benefits Benefits data transfer object (DTO) with the values of the new instance.
	 * @return the new benefits created with the Use Case
	 * @throws InternalException
	 */
	public static Benefits CreateBenefits(String administradorId, Benefits benefits) throws InternalException, NoPermisosException {
		return UseCase.CreateBenefits(UseCase.getUsuario(administradorId), benefits);
	}

	/**
	 * This method modifies a benefits.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param benefits Benefits data transfer object (DTO) with the values of the modified instance. The code of this attribute indicates which benefits will be modified.
	 * @return the modified benefits
	 * @throws InternalException
	 */
	public static Benefits UpdateBenefits(String administradorId, Benefits benefits) throws InternalException, NoPermisosException {
		return UseCase.UpdateBenefits(UseCase.getUsuario(administradorId), benefits);
	}

	/**
	 * This method removes a benefits.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param benefitscode  Code of the benefits to be removed
	 */
	public static void RemoveBenefits(String administradorId, String benefitscode) throws InternalException, NoPermisosException {
		UseCase.RemoveBenefits(UseCase.getUsuario(administradorId), benefitscode);
	}

	/**
	 * This method obtains one instance of benefits given its code.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param benefitscode Code of the benefits to be obtained
	 * @return Benefits with the given code.
	 * @throws InternalException
	 */
	public static Benefits ObtainBenefits(String administradorId, String benefitscode) throws InternalException {
		return UseCase.ObtainBenefits(UseCase.getUsuario(administradorId), benefitscode);
	}

	/**
	 * This method obtains all instances of Benefits, given a list-configurator.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param configurator ListConfigurator to be used
	 * @return A pair with an Integer with the total number of instances which match the search without appling the 'pagination' of the ListConfigurator, and the list of the instances which match the configurator (incluing pagination)
	 * @throws InternalException
	 */
	public static Pair<Integer, List<Benefits>> ObtainAllBenefits(String administradorId, ListConfigurator configurator) throws InternalException {
		return UseCase.ObtainAllBenefits(UseCase.getUsuario(administradorId), configurator);
	}

	/**
	 * This method creates a type_of_benefit.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param type_of_benefit Type_of_benefit data transfer object (DTO) with the values of the new instance.
	 * @return the new type_of_benefit created with the Use Case
	 * @throws InternalException
	 */
	public static Type_of_benefit CreateType_of_benefit(String administradorId, Type_of_benefit type_of_benefit) throws InternalException, NoPermisosException {
		return UseCase.CreateType_of_benefit(UseCase.getUsuario(administradorId), type_of_benefit);
	}

	/**
	 * This method modifies a type_of_benefit.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param type_of_benefit Type_of_benefit data transfer object (DTO) with the values of the modified instance. The code of this attribute indicates which type_of_benefit will be modified.
	 * @return the modified type_of_benefit
	 * @throws InternalException
	 */
	public static Type_of_benefit UpdateType_of_benefit(String administradorId, Type_of_benefit type_of_benefit) throws InternalException, NoPermisosException {
		return UseCase.UpdateType_of_benefit(UseCase.getUsuario(administradorId), type_of_benefit);
	}

	/**
	 * This method removes a type_of_benefit.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param type_of_benefitcode  Code of the type_of_benefit to be removed
	 */
	public static void RemoveType_of_benefit(String administradorId, String type_of_benefitcode) throws InternalException, NoPermisosException {
		UseCase.RemoveType_of_benefit(UseCase.getUsuario(administradorId), type_of_benefitcode);
	}

	/**
	 * This method obtains one instance of type_of_benefit given its code.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param type_of_benefitcode Code of the type_of_benefit to be obtained
	 * @return Type_of_benefit with the given code.
	 * @throws InternalException
	 */
	public static Type_of_benefit ObtainType_of_benefit(String administradorId, String type_of_benefitcode) throws InternalException {
		return UseCase.ObtainType_of_benefit(UseCase.getUsuario(administradorId), type_of_benefitcode);
	}

	/**
	 * This method obtains all instances of Type_of_benefit, given a list-configurator.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param configurator ListConfigurator to be used
	 * @return A pair with an Integer with the total number of instances which match the search without appling the 'pagination' of the ListConfigurator, and the list of the instances which match the configurator (incluing pagination)
	 * @throws InternalException
	 */
	public static Pair<Integer, List<Type_of_benefit>> ObtainAllType_of_benefit(String administradorId, ListConfigurator configurator) throws InternalException {
		return UseCase.ObtainAllType_of_benefit(UseCase.getUsuario(administradorId), configurator);
	}

	/**
	 * This method creates a grant.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param grant Grant data transfer object (DTO) with the values of the new instance.
	 * @return the new grant created with the Use Case
	 * @throws InternalException
	 */
	public static Grant CreateGrant(String administradorId, Grant grant) throws InternalException, NoPermisosException {
		return UseCase.CreateGrant(UseCase.getUsuario(administradorId), grant);
	}

	/**
	 * This method modifies a grant.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param grant Grant data transfer object (DTO) with the values of the modified instance. The code of this attribute indicates which grant will be modified.
	 * @return the modified grant
	 * @throws InternalException
	 */
	public static Grant UpdateGrant(String administradorId, Grant grant) throws InternalException, NoPermisosException {
		return UseCase.UpdateGrant(UseCase.getUsuario(administradorId), grant);
	}

	/**
	 * This method removes a grant.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param grantcode  Code of the grant to be removed
	 */
	public static void RemoveGrant(String administradorId, String grantcode) throws InternalException, NoPermisosException {
		UseCase.RemoveGrant(UseCase.getUsuario(administradorId), grantcode);
	}

	/**
	 * This method obtains one instance of grant given its code.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param grantcode Code of the grant to be obtained
	 * @return Grant with the given code.
	 * @throws InternalException
	 */
	public static Grant ObtainGrant(String administradorId, String grantcode) throws InternalException {
		return UseCase.ObtainGrant(UseCase.getUsuario(administradorId), grantcode);
	}

	/**
	 * This method obtains all instances of Grant, given a list-configurator.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param configurator ListConfigurator to be used
	 * @return A pair with an Integer with the total number of instances which match the search without appling the 'pagination' of the ListConfigurator, and the list of the instances which match the configurator (incluing pagination)
	 * @throws InternalException
	 */
	public static Pair<Integer, List<Grant>> ObtainAllGrant(String administradorId, ListConfigurator configurator) throws InternalException {
		return UseCase.ObtainAllGrant(UseCase.getUsuario(administradorId), configurator);
	}

	/**
	 * This method creates a education.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param education Education data transfer object (DTO) with the values of the new instance.
	 * @return the new education created with the Use Case
	 * @throws InternalException
	 */
	public static Education CreateEducation(String administradorId, Education education) throws InternalException, NoPermisosException {
		return UseCase.CreateEducation(UseCase.getUsuario(administradorId), education);
	}

	/**
	 * This method modifies a education.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param education Education data transfer object (DTO) with the values of the modified instance. The code of this attribute indicates which education will be modified.
	 * @return the modified education
	 * @throws InternalException
	 */
	public static Education UpdateEducation(String administradorId, Education education) throws InternalException, NoPermisosException {
		return UseCase.UpdateEducation(UseCase.getUsuario(administradorId), education);
	}

	/**
	 * This method removes a education.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param educationcode  Code of the education to be removed
	 */
	public static void RemoveEducation(String administradorId, String educationcode) throws InternalException, NoPermisosException {
		UseCase.RemoveEducation(UseCase.getUsuario(administradorId), educationcode);
	}

	/**
	 * This method obtains one instance of education given its code.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param educationcode Code of the education to be obtained
	 * @return Education with the given code.
	 * @throws InternalException
	 */
	public static Education ObtainEducation(String administradorId, String educationcode) throws InternalException {
		return UseCase.ObtainEducation(UseCase.getUsuario(administradorId), educationcode);
	}

	/**
	 * This method obtains all instances of Education, given a list-configurator.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param configurator ListConfigurator to be used
	 * @return A pair with an Integer with the total number of instances which match the search without appling the 'pagination' of the ListConfigurator, and the list of the instances which match the configurator (incluing pagination)
	 * @throws InternalException
	 */
	public static Pair<Integer, List<Education>> ObtainAllEducation(String administradorId, ListConfigurator configurator) throws InternalException {
		return UseCase.ObtainAllEducation(UseCase.getUsuario(administradorId), configurator);
	}

	/**
	 * This method creates a marital_status.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param marital_status Marital_status data transfer object (DTO) with the values of the new instance.
	 * @return the new marital_status created with the Use Case
	 * @throws InternalException
	 */
	public static Marital_status CreateMarital_status(String administradorId, Marital_status marital_status) throws InternalException, NoPermisosException {
		return UseCase.CreateMarital_status(UseCase.getUsuario(administradorId), marital_status);
	}

	/**
	 * This method modifies a marital_status.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param marital_status Marital_status data transfer object (DTO) with the values of the modified instance. The code of this attribute indicates which marital_status will be modified.
	 * @return the modified marital_status
	 * @throws InternalException
	 */
	public static Marital_status UpdateMarital_status(String administradorId, Marital_status marital_status) throws InternalException, NoPermisosException {
		return UseCase.UpdateMarital_status(UseCase.getUsuario(administradorId), marital_status);
	}

	/**
	 * This method removes a marital_status.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param marital_statuscode  Code of the marital_status to be removed
	 */
	public static void RemoveMarital_status(String administradorId, String marital_statuscode) throws InternalException, NoPermisosException {
		UseCase.RemoveMarital_status(UseCase.getUsuario(administradorId), marital_statuscode);
	}

	/**
	 * This method obtains one instance of marital_status given its code.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param marital_statuscode Code of the marital_status to be obtained
	 * @return Marital_status with the given code.
	 * @throws InternalException
	 */
	public static Marital_status ObtainMarital_status(String administradorId, String marital_statuscode) throws InternalException {
		return UseCase.ObtainMarital_status(UseCase.getUsuario(administradorId), marital_statuscode);
	}

	/**
	 * This method obtains all instances of Marital_status, given a list-configurator.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param configurator ListConfigurator to be used
	 * @return A pair with an Integer with the total number of instances which match the search without appling the 'pagination' of the ListConfigurator, and the list of the instances which match the configurator (incluing pagination)
	 * @throws InternalException
	 */
	public static Pair<Integer, List<Marital_status>> ObtainAllMarital_status(String administradorId, ListConfigurator configurator) throws InternalException {
		return UseCase.ObtainAllMarital_status(UseCase.getUsuario(administradorId), configurator);
	}

	/**
	 * This method creates a nationality.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param nationality Nationality data transfer object (DTO) with the values of the new instance.
	 * @return the new nationality created with the Use Case
	 * @throws InternalException
	 * @throws ValidationFailedException 
	 */
	public static Nationality CreateNationality(String administradorId, Nationality nationality) throws InternalException, ValidationFailedException, NoPermisosException {
		return UseCase.CreateNationality(UseCase.getUsuario(administradorId), nationality);
	}

	/**
	 * This method modifies a nationality.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param nationality Nationality data transfer object (DTO) with the values of the modified instance. The code of this attribute indicates which nationality will be modified.
	 * @return the modified nationality
	 * @throws InternalException
	 */
	public static Nationality UpdateNationality(String administradorId, Nationality nationality) throws InternalException, NoPermisosException {
		return UseCase.UpdateNationality(UseCase.getUsuario(administradorId), nationality);
	}

	/**
	 * This method removes a nationality.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param nationalitycode  Code of the nationality to be removed
	 */
	public static void RemoveNationality(String administradorId, String nationalitycode) throws InternalException, NoPermisosException {
		UseCase.RemoveNationality(UseCase.getUsuario(administradorId), nationalitycode);
	}

	/**
	 * This method obtains one instance of nationality given its code.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param nationalitycode Code of the nationality to be obtained
	 * @return Nationality with the given code.
	 * @throws InternalException
	 */
	public static Nationality ObtainNationality(String administradorId, String nationalitycode) throws InternalException {
		return UseCase.ObtainNationality(UseCase.getUsuario(administradorId), nationalitycode);
	}

	public static Nationality ObtainDefaultNationallity(String administradorId) throws InternalException {
		return ObtainNationality(administradorId, "ESP");
	}

	/**
	 * This method obtains all instances of Nationality, given a list-configurator.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param configurator ListConfigurator to be used
	 * @return A pair with an Integer with the total number of instances which match the search without appling the 'pagination' of the ListConfigurator, and the list of the instances which match the configurator (incluing pagination)
	 * @throws InternalException
	 */
	public static Pair<Integer, List<Nationality>> ObtainAllNationality(String administradorId, ListConfigurator configurator) throws InternalException {
		return UseCase.ObtainAllNationality(UseCase.getUsuario(administradorId), configurator);
	}

	/**
	 * This method creates a payroll_institution.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param payroll_institution Payroll_institution data transfer object (DTO) with the values of the new instance.
	 * @return the new payroll_institution created with the Use Case
	 * @throws InternalException
	 */
	public static Payroll_institution CreatePayroll_institution(String administradorId, Payroll_institution payroll_institution) throws InternalException, NoPermisosException {
		return UseCase.CreatePayroll_institution(UseCase.getUsuario(administradorId), payroll_institution);
	}

	/**
	 * This method modifies a payroll_institution.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param payroll_institution Payroll_institution data transfer object (DTO) with the values of the modified instance. The code of this attribute indicates which payroll_institution will be modified.
	 * @return the modified payroll_institution
	 * @throws InternalException
	 */
	public static Payroll_institution UpdatePayroll_institution(String administradorId, Payroll_institution payroll_institution) throws InternalException, NoPermisosException {
		return UseCase.UpdatePayroll_institution(UseCase.getUsuario(administradorId), payroll_institution);
	}

	/**
	 * This method removes a payroll_institution.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param payroll_institutioncode  Code of the payroll_institution to be removed
	 */
	public static void RemovePayroll_institution(String administradorId, String payroll_institutioncode) throws InternalException, NoPermisosException {
		UseCase.RemovePayroll_institution(UseCase.getUsuario(administradorId), payroll_institutioncode);
	}

	/**
	 * This method obtains one instance of payroll_institution given its code.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param payroll_institutioncode Code of the payroll_institution to be obtained
	 * @return Payroll_institution with the given code.
	 * @throws InternalException
	 */
	public static Payroll_institution ObtainPayroll_institution(String administradorId, String payroll_institutioncode) throws InternalException {
		return UseCase.ObtainPayroll_institution(UseCase.getUsuario(administradorId), payroll_institutioncode);
	}

	/**
	 * This method obtains all instances of Payroll_institution, given a list-configurator.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param configurator ListConfigurator to be used
	 * @return A pair with an Integer with the total number of instances which match the search without appling the 'pagination' of the ListConfigurator, and the list of the instances which match the configurator (incluing pagination)
	 * @throws InternalException
	 */
	public static Pair<Integer, List<Payroll_institution>> ObtainAllPayroll_institution(String administradorId, ListConfigurator configurator) throws InternalException {
		return UseCase.ObtainAllPayroll_institution(UseCase.getUsuario(administradorId), configurator);
	}

	/**
	 * This method creates a child.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param child Child data transfer object (DTO) with the values of the new instance.
	 * @return the new child created with the Use Case
	 * @throws InternalException
	 */
	public static Child CreateChild(String administradorId, Child child) throws InternalException, NoPermisosException {
		return UseCase.CreateChild(UseCase.getUsuario(administradorId), child);
	}

	public static Personal_comment CreatePersonal_comment(String administradorId, Personal_comment personal_comment) throws InternalException, NoPermisosException {
		return UseCase.CreatePersonal_comment(UseCase.getUsuario(administradorId), personal_comment);
	}

	/**
	 * This method modifies a child.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param child Child data transfer object (DTO) with the values of the modified instance. The code of this attribute indicates which child will be modified.
	 * @return the modified child
	 * @throws InternalException
	 */
	public static Child UpdateChild(String administradorId, Child child) throws InternalException, NoPermisosException {
		return UseCase.UpdateChild(UseCase.getUsuario(administradorId), child);
	}

	/**
	 * This method removes a child.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param childcode  Code of the child to be removed
	 */
	public static void RemoveChild(String administradorId, String childcode) throws InternalException, NoPermisosException {
		UseCase.RemoveChild(UseCase.getUsuario(administradorId), childcode);
	}

	public static void RemovePersonal_comment(String administradorId, String personal_commentcode) throws InternalException, NoPermisosException {
		UseCase.RemovePersonal_comment(UseCase.getUsuario(administradorId), personal_commentcode);
	}

	/**
	 * This method obtains one instance of child given its code.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param childcode Code of the child to be obtained
	 * @return Child with the given code.
	 * @throws InternalException
	 */
	public static Child ObtainChild(String administradorId, String childcode) throws InternalException {
		return UseCase.ObtainChild(UseCase.getUsuario(administradorId), childcode);
	}

	/**
	 * This method obtains all instances of Child, given a list-configurator.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param configurator ListConfigurator to be used
	 * @return A pair with an Integer with the total number of instances which match the search without appling the 'pagination' of the ListConfigurator, and the list of the instances which match the configurator (incluing pagination)
	 * @throws InternalException
	 */
	public static Pair<Integer, List<Child>> ObtainAllChild(String administradorId, ListConfigurator configurator) throws InternalException {
		return UseCase.ObtainAllChild(UseCase.getUsuario(administradorId), configurator);
	}

	/**
	 * This method creates a work_experience.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param work_experience Work_experience data transfer object (DTO) with the values of the new instance.
	 * @return the new work_experience created with the Use Case
	 * @throws InternalException
	 */
	public static Work_experience CreateWork_experience(String administradorId, Work_experience work_experience) throws InternalException, NoPermisosException {
		return UseCase.CreateWork_experience(UseCase.getUsuario(administradorId), work_experience);
	}

	/**
	 * This method modifies a work_experience.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param work_experience Work_experience data transfer object (DTO) with the values of the modified instance. The code of this attribute indicates which work_experience will be modified.
	 * @return the modified work_experience
	 * @throws InternalException
	 */
	public static Work_experience UpdateWork_experience(String administradorId, Work_experience work_experience) throws InternalException, NoPermisosException {
		return UseCase.UpdateWork_experience(UseCase.getUsuario(administradorId), work_experience);
	}

	/**
	 * This method removes a work_experience.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param work_experiencecode  Code of the work_experience to be removed
	 */
	public static void RemoveWork_experience(String administradorId, String work_experiencecode) throws InternalException, NoPermisosException {
		UseCase.RemoveWork_experience(UseCase.getUsuario(administradorId), work_experiencecode);
	}

	/**
	 * This method obtains one instance of work_experience given its code.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param work_experiencecode Code of the work_experience to be obtained
	 * @return Work_experience with the given code.
	 * @throws InternalException
	 */
	public static Work_experience ObtainWork_experience(String administradorId, String work_experiencecode) throws InternalException {
		return UseCase.ObtainWork_experience(UseCase.getUsuario(administradorId), work_experiencecode);
	}

	/**
	 * This method obtains all instances of Work_experience, given a list-configurator.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param configurator ListConfigurator to be used
	 * @return A pair with an Integer with the total number of instances which match the search without appling the 'pagination' of the ListConfigurator, and the list of the instances which match the configurator (incluing pagination)
	 * @throws InternalException
	 */
	public static Pair<Integer, List<Work_experience>> ObtainAllWork_experience(String administradorId, ListConfigurator configurator) throws InternalException {
		return UseCase.ObtainAllWork_experience(UseCase.getUsuario(administradorId), configurator);
	}

	/**
	 * This method creates a type_of_grant.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param type_of_grant Type_of_grant data transfer object (DTO) with the values of the new instance.
	 * @return the new type_of_grant created with the Use Case
	 * @throws InternalException
	 */
	public static Type_of_grant CreateType_of_grant(String administradorId, Type_of_grant type_of_grant) throws InternalException, NoPermisosException {
		return UseCase.CreateType_of_grant(UseCase.getUsuario(administradorId), type_of_grant);
	}

	/**
	 * This method modifies a type_of_grant.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param type_of_grant Type_of_grant data transfer object (DTO) with the values of the modified instance. The code of this attribute indicates which type_of_grant will be modified.
	 * @return the modified type_of_grant
	 * @throws InternalException
	 */
	public static Type_of_grant UpdateType_of_grant(String administradorId, Type_of_grant type_of_grant) throws InternalException, NoPermisosException {
		return UseCase.UpdateType_of_grant(UseCase.getUsuario(administradorId), type_of_grant);
	}

	/**
	 * This method removes a type_of_grant.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param type_of_grantcode  Code of the type_of_grant to be removed
	 */
	public static void RemoveType_of_grant(String administradorId, String type_of_grantcode) throws InternalException, NoPermisosException {
		UseCase.RemoveType_of_grant(UseCase.getUsuario(administradorId), type_of_grantcode);
	}

	/**
	 * This method obtains one instance of type_of_grant given its code.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param type_of_grantcode Code of the type_of_grant to be obtained
	 * @return Type_of_grant with the given code.
	 * @throws InternalException
	 */
	public static Type_of_grant ObtainType_of_grant(String administradorId, String type_of_grantcode) throws InternalException {
		return UseCase.ObtainType_of_grant(UseCase.getUsuario(administradorId), type_of_grantcode);
	}

	/**
	 * This method obtains all instances of Type_of_grant, given a list-configurator.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param configurator ListConfigurator to be used
	 * @return A pair with an Integer with the total number of instances which match the search without appling the 'pagination' of the ListConfigurator, and the list of the instances which match the configurator (incluing pagination)
	 * @throws InternalException
	 */
	public static Pair<Integer, List<Type_of_grant>> ObtainAllType_of_grant(String administradorId, ListConfigurator configurator) throws InternalException {
		return UseCase.ObtainAllType_of_grant(UseCase.getUsuario(administradorId), configurator);
	}

	/**
	 * This method creates a payment.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param payment Payment data transfer object (DTO) with the values of the new instance.
	 * @return the new payment created with the Use Case
	 * @throws InternalException
	 */
	public static Payment CreatePayment(String administradorId, Payment payment) throws InternalException, NoPermisosException {
		return UseCase.CreatePayment(UseCase.getUsuario(administradorId), payment);
	}

	/**
	 * This method modifies a payment.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param payment Payment data transfer object (DTO) with the values of the modified instance. The code of this attribute indicates which payment will be modified.
	 * @return the modified payment
	 * @throws InternalException
	 */
	public static Payment UpdatePayment(String administradorId, Payment payment) throws InternalException, NoPermisosException {
		return UseCase.UpdatePayment(UseCase.getUsuario(administradorId), payment);
	}

	/**
	 * This method removes a payment.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param paymentcode  Code of the payment to be removed
	 */
	public static void RemovePayment(String administradorId, String paymentcode) throws InternalException, NoPermisosException {
		UseCase.RemovePayment(UseCase.getUsuario(administradorId), paymentcode);
	}

	/**
	 * This method obtains one instance of payment given its code.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param paymentcode Code of the payment to be obtained
	 * @return Payment with the given code.
	 * @throws InternalException
	 */
	public static Payment ObtainPayment(String administradorId, String paymentcode) throws InternalException {
		return UseCase.ObtainPayment(UseCase.getUsuario(administradorId), paymentcode);
	}

	/**
	 * This method obtains all instances of Payment, given a list-configurator.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param configurator ListConfigurator to be used
	 * @return A pair with an Integer with the total number of instances which match the search without appling the 'pagination' of the ListConfigurator, and the list of the instances which match the configurator (incluing pagination)
	 * @throws InternalException
	 */
	public static Pair<Integer, List<Payment>> ObtainAllPayment(String administradorId, ListConfigurator configurator) throws InternalException {
		return UseCase.ObtainAllPayment(UseCase.getUsuario(administradorId), configurator);
	}

	/**
	 * This method creates a irb_budget.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param irb_budget Irb_budget data transfer object (DTO) with the values of the new instance.
	 * @return the new irb_budget created with the Use Case
	 * @throws InternalException
	 * @throws ValidationFailedException 
	 */
	public static Irb_budget CreateIrb_budget(String administradorId, Irb_budget irb_budget) throws InternalException, NoPermisosException, ValidationFailedException {
		return UseCase.CreateIrb_budget(UseCase.getUsuario(administradorId), irb_budget);
	}

	/**
	 * This method modifies a irb_budget.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param irb_budget Irb_budget data transfer object (DTO) with the values of the modified instance. The code of this attribute indicates which irb_budget will be modified.
	 * @return the modified irb_budget
	 * @throws InternalException
	 */
	public static Irb_budget UpdateIrb_budget(String administradorId, Irb_budget irb_budget) throws InternalException, NoPermisosException {
		return UseCase.UpdateIrb_budget(UseCase.getUsuario(administradorId), irb_budget);
	}

	public static Irb_budget updateIrb_budgetCode(String administradorId, String irb_budget, String newIrb_budgetCode) throws ValidationFailedException, NoPermisosException, InternalException {
		return UseCase.updateIrb_budgetCode(UseCase.getUsuario(administradorId), irb_budget, newIrb_budgetCode);
	}

	/**
	 * This method removes a irb_budget.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param irb_budgetcode  Code of the irb_budget to be removed
	 */
	public static void RemoveIrb_budget(String administradorId, String irb_budgetcode) throws InternalException, NoPermisosException {
		UseCase.RemoveIrb_budget(UseCase.getUsuario(administradorId), irb_budgetcode);
	}

	/**
	 * This method obtains one instance of irb_budget given its code.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param irb_budgetcode Code of the irb_budget to be obtained
	 * @return Irb_budget with the given code.
	 * @throws InternalException
	 */
	public static Irb_budget ObtainIrb_budget(String administradorId, String irb_budgetcode) throws InternalException {
		return UseCase.ObtainIrb_budget(UseCase.getUsuario(administradorId), irb_budgetcode);
	}

	/**
	 * This method obtains all instances of Irb_budget, given a list-configurator.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param configurator ListConfigurator to be used
	 * @return A pair with an Integer with the total number of instances which match the search without appling the 'pagination' of the ListConfigurator, and the list of the instances which match the configurator (incluing pagination)
	 * @throws InternalException
	 */
	public static Pair<Integer, List<Irb_budget>> ObtainAllIrb_budget(String administradorId, ListConfigurator configurator) throws InternalException {
		return UseCase.ObtainAllIrb_budget(UseCase.getUsuario(administradorId), configurator);
	}

	/**
	 * This method creates a organization_unit.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param organization_unit Organization_unit data transfer object (DTO) with the values of the new instance.
	 * @return the new organization_unit created with the Use Case
	 * @throws InternalException
	 */
	public static Organization_unit CreateOrganization_unit(String administradorId, Organization_unit organization_unit) throws InternalException, NoPermisosException {
		return UseCase.CreateOrganization_unit(UseCase.getUsuario(administradorId), organization_unit);
	}

	/**
	 * This method modifies a organization_unit.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param organization_unit Organization_unit data transfer object (DTO) with the values of the modified instance. The code of this attribute indicates which organization_unit will be modified.
	 * @return the modified organization_unit
	 * @throws InternalException
	 */
	public static Organization_unit UpdateOrganization_unit(String administradorId, Organization_unit organization_unit) throws InternalException, NoPermisosException {
		return UseCase.UpdateOrganization_unit(UseCase.getUsuario(administradorId), organization_unit);
	}

	/**
	 * This method removes a organization_unit.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param organization_unitcode  Code of the organization_unit to be removed
	 */
	public static void RemoveOrganization_unit(String administradorId, String organization_unitcode) throws InternalException, NoPermisosException {
		UseCase.RemoveOrganization_unit(UseCase.getUsuario(administradorId), organization_unitcode);
	}

	/**
	 * This method obtains one instance of organization_unit given its code.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param organization_unitcode Code of the organization_unit to be obtained
	 * @return Organization_unit with the given code.
	 * @throws InternalException
	 */
	public static Organization_unit ObtainOrganization_unit(String administradorId, String organization_unitcode) throws InternalException {
		return UseCase.ObtainOrganization_unit(UseCase.getUsuario(administradorId), organization_unitcode);
	}

	/**
	 * This method obtains all instances of Organization_unit, given a list-configurator.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param configurator ListConfigurator to be used
	 * @return A pair with an Integer with the total number of instances which match the search without appling the 'pagination' of the ListConfigurator, and the list of the instances which match the configurator (incluing pagination)
	 * @throws InternalException
	 */
	public static Pair<Integer, List<Organization_unit>> ObtainAllOrganization_unit(String administradorId, ListConfigurator configurator) throws InternalException {
		return UseCase.ObtainAllOrganization_unit(UseCase.getUsuario(administradorId), configurator);
	}

	/**
	 * This method creates a working_hours.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param working_hours Working_hours data transfer object (DTO) with the values of the new instance.
	 * @return the new working_hours created with the Use Case
	 * @throws InternalException
	 */
	public static Working_hours CreateWorking_hours(String administradorId, Working_hours working_hours) throws InternalException, NoPermisosException {
		return UseCase.CreateWorking_hours(UseCase.getUsuario(administradorId), working_hours);
	}

	/**
	 * This method modifies a working_hours.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param working_hours Working_hours data transfer object (DTO) with the values of the modified instance. The code of this attribute indicates which working_hours will be modified.
	 * @return the modified working_hours
	 * @throws InternalException
	 */
	public static Working_hours UpdateWorking_hours(String administradorId, Working_hours working_hours) throws InternalException, NoPermisosException {
		return UseCase.UpdateWorking_hours(UseCase.getUsuario(administradorId), working_hours);
	}

	/**
	 * This method removes a working_hours.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param working_hourscode  Code of the working_hours to be removed
	 */
	public static void RemoveWorking_hours(String administradorId, String working_hourscode) throws InternalException, NoPermisosException {
		UseCase.RemoveWorking_hours(UseCase.getUsuario(administradorId), working_hourscode);
	}

	/**
	 * This method obtains one instance of working_hours given its code.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param working_hourscode Code of the working_hours to be obtained
	 * @return Working_hours with the given code.
	 * @throws InternalException
	 */
	public static Working_hours ObtainWorking_hours(String administradorId, String working_hourscode) throws InternalException {
		return UseCase.ObtainWorking_hours(UseCase.getUsuario(administradorId), working_hourscode);
	}

	/**
	 * This method obtains all instances of Working_hours, given a list-configurator.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param configurator ListConfigurator to be used
	 * @return A pair with an Integer with the total number of instances which match the search without appling the 'pagination' of the ListConfigurator, and the list of the instances which match the configurator (incluing pagination)
	 * @throws InternalException
	 */
	public static Pair<Integer, List<Working_hours>> ObtainAllWorking_hours(String administradorId, ListConfigurator configurator) throws InternalException {
		return UseCase.ObtainAllWorking_hours(UseCase.getUsuario(administradorId), configurator);
	}

	/**
	 * This method obtains one instance of auditmessage given its code.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param auditmessagecode Code of the auditmessage to be obtained
	 * @return Auditmessage with the given code.
	 * @throws InternalException
	 */
	public static Auditmessage ObtainAuditmessage(String administradorId, String auditmessagecode) throws InternalException {
		return UseCase.ObtainAuditmessage(UseCase.getUsuario(administradorId), auditmessagecode);
	}

	/**
	 * This method obtains all instances of Auditmessage, given a list-configurator.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param configurator ListConfigurator to be used
	 * @return A pair with an Integer with the total number of instances which match the search without appling the 'pagination' of the ListConfigurator, and the list of the instances which match the configurator (incluing pagination)
	 * @throws InternalException
	 */
	public static Pair<Integer, List<Auditmessage>> ObtainAllAuditmessage(String administradorId, ListConfigurator configurator) throws InternalException {
		return UseCase.ObtainAllAuditmessage(UseCase.getUsuario(administradorId), configurator);
	}

	public static Pair<Integer, List<Auditmessage>> ObtainAuditmessageFromUser(String administradorId, String auditUserCode, ListConfigurator configurator) throws InternalException {
		return UseCase.ObtainAuditmessageFromUser(administradorId, auditUserCode, configurator);
	}

	/**
	 * This method obtains all instances of Auditmessagetype, given a list-configurator.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param configurator ListConfigurator to be used
	 * @return A pair with an Integer with the total number of instances which match the search without appling the 'pagination' of the ListConfigurator, and the list of the instances which match the configurator (incluing pagination)
	 * @throws InternalException
	 */
	public static Pair<Integer, List<Auditmessagetype>> ObtainAllAuditmessagetype(String administradorId, ListConfigurator configurator) throws InternalException {
		return UseCase.ObtainAllAuditmessagetype(UseCase.getUsuario(administradorId), configurator);
	}

	/**
	 * This method creates a bank.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param bank Bank data transfer object (DTO) with the values of the new instance.
	 * @return the new bank created with the Use Case
	 * @throws InternalException
	 * @throws ValidationFailedException 
	 */
	public static Bank CreateBank(String administradorId, Bank bank) throws InternalException, ValidationFailedException, NoPermisosException {
		return UseCase.CreateBank(UseCase.getUsuario(administradorId), bank);
	}

	/**
	 * This method modifies a bank.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param bank Bank data transfer object (DTO) with the values of the modified instance. The code of this attribute indicates which bank will be modified.
	 * @return the modified bank
	 * @throws InternalException
	 */
	public static Bank UpdateBank(String administradorId, Bank bank) throws InternalException, NoPermisosException {
		return UseCase.UpdateBank(UseCase.getUsuario(administradorId), bank);
	}

	/**
	 * This method removes a bank.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param bankcode  Code of the bank to be removed
	 */
	public static void RemoveBank(String administradorId, String bankcode) throws InternalException, NoPermisosException {
		UseCase.RemoveBank(UseCase.getUsuario(administradorId), bankcode);
	}

	/**
	 * This method obtains one instance of bank given its code.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param bankcode Code of the bank to be obtained
	 * @return Bank with the given code.
	 * @throws InternalException
	 */
	public static Bank ObtainBank(String administradorId, String bankcode) throws InternalException {
		return UseCase.ObtainBank(UseCase.getUsuario(administradorId), bankcode);
	}

	/**
	 * This method obtains all instances of Bank, given a list-configurator.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param configurator ListConfigurator to be used
	 * @return A pair with an Integer with the total number of instances which match the search without appling the 'pagination' of the ListConfigurator, and the list of the instances which match the configurator (incluing pagination)
	 * @throws InternalException
	 */
	public static Pair<Integer, List<Bank>> ObtainAllBank(String administradorId, ListConfigurator configurator) throws InternalException {
		return UseCase.ObtainAllBank(UseCase.getUsuario(administradorId), configurator);
	}

	/**
	 * This method creates a compensation.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param compensation Compensation data transfer object (DTO) with the values of the new instance.
	 * @return the new compensation created with the Use Case
	 * @throws InternalException
	 */
	public static Compensation CreateCompensation(String administradorId, Compensation compensation) throws InternalException, NoPermisosException {
		return UseCase.CreateCompensation(UseCase.getUsuario(administradorId), compensation);
	}

	/**
	 * This method modifies a compensation.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param compensation Compensation data transfer object (DTO) with the values of the modified instance. The code of this attribute indicates which compensation will be modified.
	 * @return the modified compensation
	 * @throws InternalException
	 */
	public static Compensation UpdateCompensation(String administradorId, Compensation compensation) throws InternalException, NoPermisosException {
		return UseCase.UpdateCompensation(UseCase.getUsuario(administradorId), compensation);
	}

	/**
	 * This method removes a compensation.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param compensationcode  Code of the compensation to be removed
	 */
	public static void RemoveCompensation(String administradorId, String compensationcode) throws InternalException, NoPermisosException {
		UseCase.RemoveCompensation(UseCase.getUsuario(administradorId), compensationcode);
	}

	/**
	 * This method obtains one instance of compensation given its code.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param compensationcode Code of the compensation to be obtained
	 * @return Compensation with the given code.
	 * @throws InternalException
	 */
	public static Compensation ObtainCompensation(String administradorId, String compensationcode) throws InternalException {
		return UseCase.ObtainCompensation(UseCase.getUsuario(administradorId), compensationcode);
	}

	/**
	 * This method obtains all instances of Compensation, given a list-configurator.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param configurator ListConfigurator to be used
	 * @return A pair with an Integer with the total number of instances which match the search without appling the 'pagination' of the ListConfigurator, and the list of the instances which match the configurator (incluing pagination)
	 * @throws InternalException
	 */
	public static Pair<Integer, List<Compensation>> ObtainAllCompensation(String administradorId, ListConfigurator configurator) throws InternalException {
		return UseCase.ObtainAllCompensation(UseCase.getUsuario(administradorId), configurator);
	}

	/**
	 * This method creates a type_of_institution.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param type_of_institution Type_of_institution data transfer object (DTO) with the values of the new instance.
	 * @return the new type_of_institution created with the Use Case
	 * @throws InternalException
	 */
	public static Type_of_institution CreateType_of_institution(String administradorId, Type_of_institution type_of_institution) throws InternalException, NoPermisosException {
		return UseCase.CreateType_of_institution(UseCase.getUsuario(administradorId), type_of_institution);
	}

	/**
	 * This method modifies a type_of_institution.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param type_of_institution Type_of_institution data transfer object (DTO) with the values of the modified instance. The code of this attribute indicates which type_of_institution will be modified.
	 * @return the modified type_of_institution
	 * @throws InternalException
	 */
	public static Type_of_institution UpdateType_of_institution(String administradorId, Type_of_institution type_of_institution) throws InternalException, NoPermisosException {
		return UseCase.UpdateType_of_institution(UseCase.getUsuario(administradorId), type_of_institution);
	}

	/**
	 * This method removes a type_of_institution.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param type_of_institutioncode  Code of the type_of_institution to be removed
	 */
	public static void RemoveType_of_institution(String administradorId, String type_of_institutioncode) throws InternalException, NoPermisosException {
		UseCase.RemoveType_of_institution(UseCase.getUsuario(administradorId), type_of_institutioncode);
	}

	/**
	 * This method obtains one instance of type_of_institution given its code.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param type_of_institutioncode Code of the type_of_institution to be obtained
	 * @return Type_of_institution with the given code.
	 * @throws InternalException
	 */
	public static Type_of_institution ObtainType_of_institution(String administradorId, String type_of_institutioncode) throws InternalException {
		return UseCase.ObtainType_of_institution(UseCase.getUsuario(administradorId), type_of_institutioncode);
	}

	/**
	 * This method obtains all instances of Type_of_institution, given a list-configurator.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param configurator ListConfigurator to be used
	 * @return A pair with an Integer with the total number of instances which match the search without appling the 'pagination' of the ListConfigurator, and the list of the instances which match the configurator (incluing pagination)
	 * @throws InternalException
	 */
	public static Pair<Integer, List<Type_of_institution>> ObtainAllType_of_institution(String administradorId, ListConfigurator configurator) throws InternalException {
		return UseCase.ObtainAllType_of_institution(UseCase.getUsuario(administradorId), configurator);
	}

	/**
	 * This method creates a type_of_study.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param type_of_study Type_of_study data transfer object (DTO) with the values of the new instance.
	 * @return the new type_of_study created with the Use Case
	 * @throws InternalException
	 */
	public static Type_of_study CreateType_of_study(String administradorId, Type_of_study type_of_study) throws InternalException, NoPermisosException {
		return UseCase.CreateType_of_study(UseCase.getUsuario(administradorId), type_of_study);
	}

	/**
	 * This method modifies a type_of_study.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param type_of_study Type_of_study data transfer object (DTO) with the values of the modified instance. The code of this attribute indicates which type_of_study will be modified.
	 * @return the modified type_of_study
	 * @throws InternalException
	 */
	public static Type_of_study UpdateType_of_study(String administradorId, Type_of_study type_of_study) throws InternalException, NoPermisosException {
		return UseCase.UpdateType_of_study(UseCase.getUsuario(administradorId), type_of_study);
	}

	/**
	 * This method removes a type_of_study.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param type_of_studycode  Code of the type_of_study to be removed
	 */
	public static void RemoveType_of_study(String administradorId, String type_of_studycode) throws InternalException, NoPermisosException {
		UseCase.RemoveType_of_study(UseCase.getUsuario(administradorId), type_of_studycode);
	}

	/**
	 * This method obtains one instance of type_of_study given its code.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param type_of_studycode Code of the type_of_study to be obtained
	 * @return Type_of_study with the given code.
	 * @throws InternalException
	 */
	public static Type_of_study ObtainType_of_study(String administradorId, String type_of_studycode) throws InternalException {
		return UseCase.ObtainType_of_study(UseCase.getUsuario(administradorId), type_of_studycode);
	}

	/**
	 * This method obtains all instances of Type_of_study, given a list-configurator.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param configurator ListConfigurator to be used
	 * @return A pair with an Integer with the total number of instances which match the search without appling the 'pagination' of the ListConfigurator, and the list of the instances which match the configurator (incluing pagination)
	 * @throws InternalException
	 */
	public static Pair<Integer, List<Type_of_study>> ObtainAllType_of_study(String administradorId, ListConfigurator configurator) throws InternalException {
		return UseCase.ObtainAllType_of_study(UseCase.getUsuario(administradorId), configurator);
	}

	
	
	/**
	 * This method creates a holiday.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param holiday Holiday data transfer object (DTO) with the values of the new instance.
	 * @return the new holiday created with the Use Case
	 * @throws InternalException
	 */
	public static Holiday CreateHoliday(String administradorId, Holiday holiday) throws InternalException, NoPermisosException {
		return UseCase.CreateHoliday(UseCase.getUsuario(administradorId), holiday);
	}

	/**
	 * This method modifies a holiday.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param holiday Holiday data transfer object (DTO) with the values of the modified instance. The code of this attribute indicates which holiday will be modified.
	 * @return the modified holiday
	 * @throws InternalException
	 */
	public static Holiday UpdateHoliday(String administradorId, Holiday holiday) throws InternalException, NoPermisosException {
		return UseCase.UpdateHoliday(UseCase.getUsuario(administradorId), holiday);
	}

	/**
	 * This method removes a holiday.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param holidaycode  Code of the holiday to be removed
	 */
	public static void RemoveHoliday(String administradorId, String holidaycode) throws InternalException, NoPermisosException {
		UseCase.RemoveHoliday(UseCase.getUsuario(administradorId), holidaycode);
	}

	/**
	 * This method obtains one instance of holiday given its code.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param holidaycode Code of the holiday to be obtained
	 * @return Holiday with the given code.
	 * @throws InternalException
	 */
	public static Holiday ObtainHoliday(String administradorId, String holidaycode) throws InternalException {
		return UseCase.ObtainHoliday(UseCase.getUsuario(administradorId), holidaycode);
	}

	/**
	 * This method obtains all instances of Holiday, given a list-configurator.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param configurator ListConfigurator to be used
	 * @return A pair with an Integer with the total number of instances which match the search without appling the 'pagination' of the ListConfigurator, and the list of the instances which match the configurator (incluing pagination)
	 * @throws InternalException
	 */
	public static Pair<Integer, List<Holiday>> ObtainAllHoliday(String administradorId, ListConfigurator configurator) throws InternalException {
		return UseCase.ObtainAllHoliday(UseCase.getUsuario(administradorId), configurator);
	}

	/**
	 * This method creates a grant_concession.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param grant_concession Grant_concession data transfer object (DTO) with the values of the new instance.
	 * @return the new grant_concession created with the Use Case
	 * @throws InternalException
	 */
	public static Grant_concession CreateGrant_concession(String administradorId, Grant_concession grant_concession) throws InternalException, NoPermisosException {
		return UseCase.CreateGrant_concession(UseCase.getUsuario(administradorId), grant_concession);
	}

	/**
	 * This method modifies a grant_concession.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param grant_concession Grant_concession data transfer object (DTO) with the values of the modified instance. The code of this attribute indicates which grant_concession will be modified.
	 * @return the modified grant_concession
	 * @throws InternalException
	 */
	public static Grant_concession UpdateGrant_concession(String administradorId, Grant_concession grant_concession) throws InternalException, NoPermisosException {
		return UseCase.UpdateGrant_concession(UseCase.getUsuario(administradorId), grant_concession);
	}

	/**
	 * This method removes a grant_concession.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param grant_concessioncode  Code of the grant_concession to be removed
	 */
	public static void RemoveGrant_concession(String administradorId, String grant_concessioncode) throws InternalException, NoPermisosException {
		UseCase.RemoveGrant_concession(UseCase.getUsuario(administradorId), grant_concessioncode);
	}

	/**
	 * This method obtains one instance of grant_concession given its code.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param grant_concessioncode Code of the grant_concession to be obtained
	 * @return Grant_concession with the given code.
	 * @throws InternalException
	 */
	public static Grant_concession ObtainGrant_concession(String administradorId, String grant_concessioncode) throws InternalException {
		return UseCase.ObtainGrant_concession(UseCase.getUsuario(administradorId), grant_concessioncode);
	}

	/**
	 * This method obtains all instances of Grant_concession, given a list-configurator.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param configurator ListConfigurator to be used
	 * @return A pair with an Integer with the total number of instances which match the search without appling the 'pagination' of the ListConfigurator, and the list of the instances which match the configurator (incluing pagination)
	 * @throws InternalException
	 */
	public static Pair<Integer, List<Grant_concession>> ObtainAllGrant_concession(String administradorId, ListConfigurator configurator) throws InternalException {
		return UseCase.ObtainAllGrant_concession(UseCase.getUsuario(administradorId), configurator);
	}

	/**
	 * This method creates a academic_info.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param academic_info Academic_info data transfer object (DTO) with the values of the new instance.
	 * @return the new academic_info created with the Use Case
	 * @throws InternalException
	 */
	public static Academic_info CreateAcademic_info(String administradorId, Academic_info academic_info) throws InternalException, NoPermisosException {
		return UseCase.CreateAcademic_info(UseCase.getUsuario(administradorId), academic_info);
	}

	/**
	 * This method modifies a academic_info.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param academic_info Academic_info data transfer object (DTO) with the values of the modified instance. The code of this attribute indicates which academic_info will be modified.
	 * @return the modified academic_info
	 * @throws InternalException
	 */
	public static Academic_info UpdateAcademic_info(String administradorId, Academic_info academic_info) throws InternalException, NoPermisosException {
		return UseCase.UpdateAcademic_info(UseCase.getUsuario(administradorId), academic_info);
	}

	/**
	 * This method removes a academic_info.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param academic_infocode  Code of the academic_info to be removed
	 */
	public static void RemoveAcademic_info(String administradorId, String academic_infocode) throws InternalException, NoPermisosException {
		UseCase.RemoveAcademic_info(UseCase.getUsuario(administradorId), academic_infocode);
	}

	/**
	 * This method obtains one instance of academic_info given its code.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param academic_infocode Code of the academic_info to be obtained
	 * @return Academic_info with the given code.
	 * @throws InternalException
	 */
	public static Academic_info ObtainAcademic_info(String administradorId, String academic_infocode) throws InternalException {
		return UseCase.ObtainAcademic_info(UseCase.getUsuario(administradorId), academic_infocode);
	}

	/**
	 * This method obtains all instances of Academic_info, given a list-configurator.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param configurator ListConfigurator to be used
	 * @return A pair with an Integer with the total number of instances which match the search without appling the 'pagination' of the ListConfigurator, and the list of the instances which match the configurator (incluing pagination)
	 * @throws InternalException
	 */
	public static Pair<Integer, List<Academic_info>> ObtainAllAcademic_info(String administradorId, ListConfigurator configurator) throws InternalException {
		return UseCase.ObtainAllAcademic_info(UseCase.getUsuario(administradorId), configurator);
	}

	
	/**
	 * This method creates a type_of_contract.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param type_of_contract Type_of_contract data transfer object (DTO) with the values of the new instance.
	 * @return the new type_of_contract created with the Use Case
	 * @throws InternalException
	 */
	public static Type_of_contract CreateType_of_contract(String administradorId, Type_of_contract type_of_contract) throws InternalException, NoPermisosException {
		return UseCase.CreateType_of_contract(UseCase.getUsuario(administradorId), type_of_contract);
	}

	/**
	 * This method modifies a type_of_contract.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param type_of_contract Type_of_contract data transfer object (DTO) with the values of the modified instance. The code of this attribute indicates which type_of_contract will be modified.
	 * @return the modified type_of_contract
	 * @throws InternalException
	 */
	public static Type_of_contract UpdateType_of_contract(String administradorId, Type_of_contract type_of_contract) throws InternalException, NoPermisosException {
		return UseCase.UpdateType_of_contract(UseCase.getUsuario(administradorId), type_of_contract);
	}

	/**
	 * This method removes a type_of_contract.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param type_of_contractcode  Code of the type_of_contract to be removed
	 */
	public static void RemoveType_of_contract(String administradorId, String type_of_contractcode) throws InternalException, NoPermisosException {
		UseCase.RemoveType_of_contract(UseCase.getUsuario(administradorId), type_of_contractcode);
	}

	/**
	 * This method obtains one instance of type_of_contract given its code.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param type_of_contractcode Code of the type_of_contract to be obtained
	 * @return Type_of_contract with the given code.
	 * @throws InternalException
	 */
	public static Type_of_contract ObtainType_of_contract(String administradorId, String type_of_contractcode) throws InternalException {
		return UseCase.ObtainType_of_contract(UseCase.getUsuario(administradorId), type_of_contractcode);
	}

	/**
	 * This method obtains all instances of Type_of_contract, given a list-configurator.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param configurator ListConfigurator to be used
	 * @return A pair with an Integer with the total number of instances which match the search without appling the 'pagination' of the ListConfigurator, and the list of the instances which match the configurator (incluing pagination)
	 * @throws InternalException
	 */
	public static Pair<Integer, List<Type_of_contract>> ObtainAllType_of_contract(String administradorId, ListConfigurator configurator) throws InternalException {
		return UseCase.ObtainAllType_of_contract(UseCase.getUsuario(administradorId), configurator);
	}

	/**
	 * This method creates a type_of_compensation.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param type_of_compensation Type_of_compensation data transfer object (DTO) with the values of the new instance.
	 * @return the new type_of_compensation created with the Use Case
	 * @throws InternalException
	 */
	public static Type_of_compensation CreateType_of_compensation(String administradorId, Type_of_compensation type_of_compensation) throws InternalException, NoPermisosException {
		return UseCase.CreateType_of_compensation(UseCase.getUsuario(administradorId), type_of_compensation);
	}

	/**
	 * This method modifies a type_of_compensation.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param type_of_compensation Type_of_compensation data transfer object (DTO) with the values of the modified instance. The code of this attribute indicates which type_of_compensation will be modified.
	 * @return the modified type_of_compensation
	 * @throws InternalException
	 */
	public static Type_of_compensation UpdateType_of_compensation(String administradorId, Type_of_compensation type_of_compensation) throws InternalException, NoPermisosException {
		return UseCase.UpdateType_of_compensation(UseCase.getUsuario(administradorId), type_of_compensation);
	}

	/**
	 * This method removes a type_of_compensation.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param type_of_compensationcode  Code of the type_of_compensation to be removed
	 */
	public static void RemoveType_of_compensation(String administradorId, String type_of_compensationcode) throws InternalException, NoPermisosException {
		UseCase.RemoveType_of_compensation(UseCase.getUsuario(administradorId), type_of_compensationcode);
	}

	/**
	 * This method obtains one instance of type_of_compensation given its code.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param type_of_compensationcode Code of the type_of_compensation to be obtained
	 * @return Type_of_compensation with the given code.
	 * @throws InternalException
	 */
	public static Type_of_compensation ObtainType_of_compensation(String administradorId, String type_of_compensationcode) throws InternalException {
		return UseCase.ObtainType_of_compensation(UseCase.getUsuario(administradorId), type_of_compensationcode);
	}

	/**
	 * This method obtains all instances of Type_of_compensation, given a list-configurator.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param configurator ListConfigurator to be used
	 * @return A pair with an Integer with the total number of instances which match the search without appling the 'pagination' of the ListConfigurator, and the list of the instances which match the configurator (incluing pagination)
	 * @throws InternalException
	 */
	public static Pair<Integer, List<Type_of_compensation>> ObtainAllType_of_compensation(String administradorId, ListConfigurator configurator) throws InternalException {
		return UseCase.ObtainAllType_of_compensation(UseCase.getUsuario(administradorId), configurator);
	}

	/**
	 * This method creates a unit.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param unit Unit data transfer object (DTO) with the values of the new instance.
	 * @return the new unit created with the Use Case
	 * @throws InternalException
	 * @throws ValidationFailedException 
	 */
	public static Unit CreateUnit(String administradorId, Unit unit) throws InternalException, NoPermisosException, ValidationFailedException {
		return UseCase.CreateUnit(UseCase.getUsuario(administradorId), unit);
	}

	/**
	 * This method modifies a unit.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param unit Unit data transfer object (DTO) with the values of the modified instance. The code of this attribute indicates which unit will be modified.
	 * @return the modified unit
	 * @throws InternalException
	 * @throws ValidationFailedException 
	 */
	public static Unit UpdateUnit(String administradorId, Unit unit) throws InternalException, NoPermisosException, ValidationFailedException {
		return UseCase.UpdateUnit(UseCase.getUsuario(administradorId), unit);
	}

	/**
	 * This method removes a unit.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param unitcode  Code of the unit to be removed
	 */
	public static void RemoveUnit(String administradorId, String unitcode) throws InternalException, NoPermisosException {
		UseCase.RemoveUnit(UseCase.getUsuario(administradorId), unitcode);
	}

	/**
	 * This method obtains one instance of unit given its code.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param unitcode Code of the unit to be obtained
	 * @return Unit with the given code.
	 * @throws InternalException
	 */
	public static Unit ObtainUnit(String administradorId, String unitcode) throws InternalException {
		return UseCase.ObtainUnit(UseCase.getUsuario(administradorId), unitcode);
	}

	/**
	 * This method obtains all instances of Unit, given a list-configurator.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param configurator ListConfigurator to be used
	 * @return A pair with an Integer with the total number of instances which match the search without appling the 'pagination' of the ListConfigurator, and the list of the instances which match the configurator (incluing pagination)
	 * @throws InternalException
	 */
	public static Pair<Integer, List<Unit>> ObtainAllUnit(String administradorId, ListConfigurator configurator) throws InternalException {
		return UseCase.ObtainAllUnit(UseCase.getUsuario(administradorId), configurator);
	}

	/**
	 * This method creates a gender.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param gender Gender data transfer object (DTO) with the values of the new instance.
	 * @return the new gender created with the Use Case
	 * @throws InternalException
	 */
	public static Gender CreateGender(String administradorId, Gender gender) throws InternalException, NoPermisosException {
		return UseCase.CreateGender(UseCase.getUsuario(administradorId), gender);
	}

	/**
	 * This method modifies a gender.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param gender Gender data transfer object (DTO) with the values of the modified instance. The code of this attribute indicates which gender will be modified.
	 * @return the modified gender
	 * @throws InternalException
	 */
	public static Gender UpdateGender(String administradorId, Gender gender) throws InternalException, NoPermisosException {
		return UseCase.UpdateGender(UseCase.getUsuario(administradorId), gender);
	}

	/**
	 * This method removes a gender.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param gendercode  Code of the gender to be removed
	 */
	public static void RemoveGender(String administradorId, String gendercode) throws InternalException, NoPermisosException {
		UseCase.RemoveGender(UseCase.getUsuario(administradorId), gendercode);
	}

	/**
	 * This method obtains one instance of gender given its code.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param gendercode Code of the gender to be obtained
	 * @return Gender with the given code.
	 * @throws InternalException
	 */
	public static Gender ObtainGender(String administradorId, String gendercode) throws InternalException {
		return UseCase.ObtainGender(UseCase.getUsuario(administradorId), gendercode);
	}

	/**
	 * This method obtains all instances of Gender, given a list-configurator.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param configurator ListConfigurator to be used
	 * @return A pair with an Integer with the total number of instances which match the search without appling the 'pagination' of the ListConfigurator, and the list of the instances which match the configurator (incluing pagination)
	 * @throws InternalException
	 */
	public static Pair<Integer, List<Gender>> ObtainAllGender(String administradorId, ListConfigurator configurator) throws InternalException {
		return UseCase.ObtainAllGender(UseCase.getUsuario(administradorId), configurator);
	}

	/**
	 * This method creates a country.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param country Country data transfer object (DTO) with the values of the new instance.
	 * @return the new country created with the Use Case
	 * @throws InternalException
	 * @throws ValidationFailedException 
	 */
	public static Country CreateCountry(String administradorId, Country country) throws InternalException, ValidationFailedException, NoPermisosException {
		return UseCase.CreateCountry(UseCase.getUsuario(administradorId), country);
	}

	/**
	 * This method modifies a country.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param country Country data transfer object (DTO) with the values of the modified instance. The code of this attribute indicates which country will be modified.
	 * @return the modified country
	 * @throws InternalException
	 */
	public static Country UpdateCountry(String administradorId, Country country) throws InternalException, NoPermisosException {
		return UseCase.UpdateCountry(UseCase.getUsuario(administradorId), country);
	}

	/**
	 * This method removes a country.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param countrycode  Code of the country to be removed
	 */
	public static void RemoveCountry(String administradorId, String countrycode) throws InternalException, NoPermisosException {
		UseCase.RemoveCountry(UseCase.getUsuario(administradorId), countrycode);
	}

	/**
	 * This method obtains one instance of country given its code.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param countrycode Code of the country to be obtained
	 * @return Country with the given code.
	 * @throws InternalException
	 */
	public static Country ObtainCountry(String administradorId, String countrycode) throws InternalException {
		return UseCase.ObtainCountry(UseCase.getUsuario(administradorId), countrycode);
	}

	public static Country ObtainDefaultCountry(String administradorId) throws InternalException {
		return ObtainCountry(administradorId, "ES");
	}

	/**
	 * This method obtains all instances of Country, given a list-configurator.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param configurator ListConfigurator to be used
	 * @return A pair with an Integer with the total number of instances which match the search without appling the 'pagination' of the ListConfigurator, and the list of the instances which match the configurator (incluing pagination)
	 * @throws InternalException
	 */
	public static Pair<Integer, List<Country>> ObtainAllCountry(String administradorId, ListConfigurator configurator) throws InternalException {
		return UseCase.ObtainAllCountry(UseCase.getUsuario(administradorId), configurator);
	}

	/**
	 * This method creates a category.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param category Category data transfer object (DTO) with the values of the new instance.
	 * @return the new category created with the Use Case
	 * @throws InternalException
	 */
	public static Category CreateCategory(String administradorId, Category category) throws InternalException, NoPermisosException {
		return UseCase.CreateCategory(UseCase.getUsuario(administradorId), category);
	}

	/**
	 * This method modifies a category.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param category Category data transfer object (DTO) with the values of the modified instance. The code of this attribute indicates which category will be modified.
	 * @return the modified category
	 * @throws InternalException
	 */
	public static Category UpdateCategory(String administradorId, Category category) throws InternalException, NoPermisosException {
		return UseCase.UpdateCategory(UseCase.getUsuario(administradorId), category);
	}

	/**
	 * This method removes a category.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param categorycode  Code of the category to be removed
	 */
	public static void RemoveCategory(String administradorId, String categorycode) throws InternalException, NoPermisosException {
		UseCase.RemoveCategory(UseCase.getUsuario(administradorId), categorycode);
	}

	/**
	 * This method obtains one instance of category given its code.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param categorycode Code of the category to be obtained
	 * @return Category with the given code.
	 * @throws InternalException
	 */
	public static Category ObtainCategory(String administradorId, String categorycode) throws InternalException {
		return UseCase.ObtainCategory(UseCase.getUsuario(administradorId), categorycode);
	}

	/**
	 * This method obtains all instances of Category, given a list-configurator.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param configurator ListConfigurator to be used
	 * @return A pair with an Integer with the total number of instances which match the search without appling the 'pagination' of the ListConfigurator, and the list of the instances which match the configurator (incluing pagination)
	 * @throws InternalException
	 */
	public static Pair<Integer, List<Category>> ObtainAllCategory(String administradorId, ListConfigurator configurator) throws InternalException {
		return UseCase.ObtainAllCategory(UseCase.getUsuario(administradorId), configurator);
	}

	/**
	 * This method creates a funding_detail.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param funding_detail Funding_detail data transfer object (DTO) with the values of the new instance.
	 * @return the new funding_detail created with the Use Case
	 * @throws InternalException
	 */
	public static Funding_detail CreateFunding_detail(String administradorId, Funding_detail funding_detail) throws InternalException {
		return UseCase.CreateFunding_detail(UseCase.getUsuario(administradorId), funding_detail);
	}

	/**
	 * This method modifies a funding_detail.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param funding_detail Funding_detail data transfer object (DTO) with the values of the modified instance. The code of this attribute indicates which funding_detail will be modified.
	 * @return the modified funding_detail
	 * @throws InternalException
	 */
	public static Funding_detail UpdateFunding_detail(String administradorId, Funding_detail funding_detail) throws InternalException {
		return UseCase.UpdateFunding_detail(UseCase.getUsuario(administradorId), funding_detail);
	}

	/**
	 * This method removes a funding_detail.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param funding_detailcode  Code of the funding_detail to be removed
	 */
	public static void RemoveFunding_detail(String administradorId, String funding_detailcode) throws InternalException {
		UseCase.RemoveFunding_detail(UseCase.getUsuario(administradorId), funding_detailcode);
	}

	/**
	 * This method obtains one instance of funding_detail given its code.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param funding_detailcode Code of the funding_detail to be obtained
	 * @return Funding_detail with the given code.
	 * @throws InternalException
	 */
	public static Funding_detail ObtainFunding_detail(String administradorId, String funding_detailcode) throws InternalException {
		return UseCase.ObtainFunding_detail(UseCase.getUsuario(administradorId), funding_detailcode);
	}

	/**
	 * This method obtains all instances of Funding_detail, given a list-configurator.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param configurator ListConfigurator to be used
	 * @return A pair with an Integer with the total number of instances which match the search without appling the 'pagination' of the ListConfigurator, and the list of the instances which match the configurator (incluing pagination)
	 * @throws InternalException
	 */
	public static Pair<Integer, List<Funding_detail>> ObtainAllFunding_detail(String administradorId, ListConfigurator configurator) throws InternalException {
		return UseCase.ObtainAllFunding_detail(UseCase.getUsuario(administradorId), configurator);
	}

	/**
	 * This method creates a area.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param area Area data transfer object (DTO) with the values of the new instance.
	 * @return the new area created with the Use Case
	 * @throws InternalException
	 */
	public static Area CreateArea(String administradorId, Area area) throws InternalException, NoPermisosException {
		return UseCase.CreateArea(UseCase.getUsuario(administradorId), area);
	}

	/**
	 * This method modifies a area.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param area Area data transfer object (DTO) with the values of the modified instance. The code of this attribute indicates which area will be modified.
	 * @return the modified area
	 * @throws InternalException
	 */
	public static Area UpdateArea(String administradorId, Area area) throws InternalException, NoPermisosException {
		return UseCase.UpdateArea(UseCase.getUsuario(administradorId), area);
	}

	/**
	 * This method removes a area.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param areacode  Code of the area to be removed
	 */
	public static void RemoveArea(String administradorId, String areacode) throws InternalException, NoPermisosException {
		UseCase.RemoveArea(UseCase.getUsuario(administradorId), areacode);
	}

	/**
	 * This method obtains one instance of area given its code.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param areacode Code of the area to be obtained
	 * @return Area with the given code.
	 * @throws InternalException
	 */
	public static Area ObtainArea(String administradorId, String areacode) throws InternalException {
		return UseCase.ObtainArea(UseCase.getUsuario(administradorId), areacode);
	}

	/**
	 * This method obtains all instances of Area, given a list-configurator.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param configurator ListConfigurator to be used
	 * @return A pair with an Integer with the total number of instances which match the search without appling the 'pagination' of the ListConfigurator, and the list of the instances which match the configurator (incluing pagination)
	 * @throws InternalException
	 */
	public static Pair<Integer, List<Area>> ObtainAllArea(String administradorId, ListConfigurator configurator) throws InternalException {
		return UseCase.ObtainAllArea(UseCase.getUsuario(administradorId), configurator);
	}

	/**
	 * This method creates a type_of_holidays.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param type_of_holidays Type_of_holidays data transfer object (DTO) with the values of the new instance.
	 * @return the new type_of_holidays created with the Use Case
	 * @throws InternalException
	 */
	public static Type_of_holidays CreateType_of_holidays(String administradorId, Type_of_holidays type_of_holidays) throws InternalException, NoPermisosException {
		return UseCase.CreateType_of_holidays(UseCase.getUsuario(administradorId), type_of_holidays);
	}

	/**
	 * This method modifies a type_of_holidays.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param type_of_holidays Type_of_holidays data transfer object (DTO) with the values of the modified instance. The code of this attribute indicates which type_of_holidays will be modified.
	 * @return the modified type_of_holidays
	 * @throws InternalException
	 */
	public static Type_of_holidays UpdateType_of_holidays(String administradorId, Type_of_holidays type_of_holidays) throws InternalException, NoPermisosException {
		return UseCase.UpdateType_of_holidays(UseCase.getUsuario(administradorId), type_of_holidays);
	}

	/**
	 * This method removes a type_of_holidays.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param type_of_holidayscode  Code of the type_of_holidays to be removed
	 */
	public static void RemoveType_of_holidays(String administradorId, String type_of_holidayscode) throws InternalException, NoPermisosException {
		UseCase.RemoveType_of_holidays(UseCase.getUsuario(administradorId), type_of_holidayscode);
	}

	/**
	 * This method obtains one instance of type_of_holidays given its code.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param type_of_holidayscode Code of the type_of_holidays to be obtained
	 * @return Type_of_holidays with the given code.
	 * @throws InternalException
	 */
	public static Type_of_holidays ObtainType_of_holidays(String administradorId, String type_of_holidayscode) throws InternalException {
		return UseCase.ObtainType_of_holidays(UseCase.getUsuario(administradorId), type_of_holidayscode);
	}

	/**
	 * This method obtains all instances of Type_of_holidays, given a list-configurator.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param configurator ListConfigurator to be used
	 * @return A pair with an Integer with the total number of instances which match the search without appling the 'pagination' of the ListConfigurator, and the list of the instances which match the configurator (incluing pagination)
	 * @throws InternalException
	 */
	public static Pair<Integer, List<Type_of_holidays>> ObtainAllType_of_holidays(String administradorId, ListConfigurator configurator) throws InternalException {
		return UseCase.ObtainAllType_of_holidays(UseCase.getUsuario(administradorId), configurator);
	}

	public static PersonalPhoto setPersonalPhotoToPersonal(String administradorId, PersonalPhoto TOPersonalPhoto, String personalCode) throws InternalException, NoPermisosException, ValidationFailedException {
		return UseCase.setPersonalPhotoToPersonal(UseCase.getUsuario(administradorId), TOPersonalPhoto, personalCode);
	}

	public static PersonalPhoto createPersonalPhoto(String administradorId, PersonalPhoto TOPersonalPhoto) throws InternalException {
		return UseCase.createPersonalPhoto(UseCase.getUsuario(administradorId), TOPersonalPhoto);
	}

	public static void RemovePersonalPersonalPhoto(String administradorId, String personalcode) throws InternalException, ValidationFailedException {
		UseCase.RemovePersonalPersonalPhoto(UseCase.getUsuario(administradorId), personalcode);
	}

	public static void RemovePersonalPhoto(String administradorId, String personalPhotocode) throws InternalException {
		UseCase.RemovePersonalPhoto(UseCase.getUsuario(administradorId), personalPhotocode);
	}

	public static PersonalPhoto obtainPersonalPhoto(String administradorId, String personalPhotocode) throws InternalException {
		return UseCase.obtainPersonalPhoto(UseCase.getUsuario(administradorId), personalPhotocode);
	}

	/**
	 * This method creates a personal.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param personal Personal data transfer object (DTO) with the values of the new instance.
	 * @return the new personal created with the Use Case
	 * @throws InternalException
	 * @throws ValidationFailedException 
	 */
	public static Personal CreatePersonal(String administradorId, Personal personal) throws InternalException, ValidationFailedException {
		return UseCase.CreatePersonal(UseCase.getUsuario(administradorId), personal);
	}

	/**
	 * This method modifies a personal.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param personal Personal data transfer object (DTO) with the values of the modified instance. The code of this attribute indicates which personal will be modified.
	 * @return the modified personal
	 * @throws InternalException
	 * @throws ValidationFailedException 
	 */
	public static Personal UpdatePersonal(String administradorId, Personal personal) throws InternalException, ValidationFailedException {
		return UseCase.UpdatePersonal(UseCase.getUsuario(administradorId), personal);
	}

	/**
	 * This method removes a personal.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param personalcode  Code of the personal to be removed
	 * @throws UsuarioNoActivoException 
	 * @throws NoPermisosException 
	 * @throws ValidationFailedException 
	 */
	public static void RemovePersonal(String administradorId, String personalcode) throws InternalException, NoPermisosException, UsuarioNoActivoException, ValidationFailedException {
		UseCase.RemovePersonal(UseCase.getUsuario(administradorId), personalcode);
	}

	/**
	 * This method obtains one instance of personal given its code.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param personalcode Code of the personal to be obtained
	 * @return Personal with the given code.
	 * @throws InternalException
	 */
	public static Personal ObtainPersonal(String administradorId, String personalcode) throws InternalException {
		return UseCase.ObtainPersonal(UseCase.getUsuario(administradorId), personalcode);
	}

	/**
	 * This method obtains all instances of Personal, given a list-configurator.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param configurator ListConfigurator to be used
	 * @return A pair with an Integer with the total number of instances which match the search without appling the 'pagination' of the ListConfigurator, and the list of the instances which match the configurator (incluing pagination)
	 * @throws InternalException
	 */
	public static Pair<Integer, List<Personal>> ObtainAllPersonal(String administradorId, ListConfigurator configurator) throws InternalException {
		return UseCase.ObtainAllPersonal(UseCase.getUsuario(administradorId), configurator);
	}

	public static Pair<Integer, Pair<List<Personal>, Map<String, String[]>>> ObtainAllPersonalAndOrderMap(String administradorId, ListConfigurator configurator) throws InternalException {
		return UseCase.ObtainAllPersonalAndOrderMap(UseCase.getUsuario(administradorId), configurator);
	}

	public static Pair<Integer, Pair<List<Personal>, Map<String, String[]>>> ObtainPersonalWithContractAndOrderMap(String administradorId, ListConfigurator configurator) throws InternalException {
		return UseCase.ObtainPersonalWithContractAndOrderMap(UseCase.getUsuario(administradorId), configurator);
	}

	public static Pair<Integer, Pair<List<Personal>, Map<String, String[]>>> ObtainPersonalWithGrantAndOrderMap(String administradorId, ListConfigurator configurator) throws InternalException {
		return UseCase.ObtainPersonalWithGrantAndOrderMap(UseCase.getUsuario(administradorId), configurator);
	}

	/**
	 * This method obtains all instances of Personal, given a list-configurator.
	 * 
	 * @param person The user who executes this use case
	 * @param personal supervisor
	 * @param configurator ListConfigurator to be used
	 * @return A pair with an Integer with the total number of instances which match the search without appling the 'pagination' of the ListConfigurator, and the list of the instances which match the configurator (incluing pagination)
	 */
	public static Pair<Integer, List<Personal>> ObtainAllPersonalSupervisedBy(String administradorId, String personalcode, ListConfigurator configurator) throws InternalException {
		return UseCase.ObtainAllPersonalSupervisedBy(UseCase.getUsuario(administradorId), UseCase.getPersonal(personalcode), configurator);
	}

	/**
	 * This method obtains all instances of Personalstate, given a list-configurator.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param configurator ListConfigurator to be used
	 * @return A pair with an Integer with the total number of instances which match the search without appling the 'pagination' of the ListConfigurator, and the list of the instances which match the configurator (incluing pagination)
	 * @throws InternalException
	 */
	public static Pair<Integer, List<Personalstate>> ObtainAllPersonalstate(String administradorId, ListConfigurator configurator) throws InternalException {
		return UseCase.ObtainAllPersonalstate(UseCase.getUsuario(administradorId), configurator);
	}

	/**
	 * This method creates a position.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param position Position data transfer object (DTO) with the values of the new instance.
	 * @return the new position created with the Use Case
	 * @throws InternalException
	 */
	public static Position CreatePosition(String administradorId, Position position) throws InternalException, NoPermisosException {
		return UseCase.CreatePosition(UseCase.getUsuario(administradorId), position);
	}

	/**
	 * This method modifies a position.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param position Position data transfer object (DTO) with the values of the modified instance. The code of this attribute indicates which position will be modified.
	 * @return the modified position
	 * @throws InternalException
	 */
	public static Position UpdatePosition(String administradorId, Position position) throws InternalException, NoPermisosException {
		return UseCase.UpdatePosition(UseCase.getUsuario(administradorId), position);
	}

	/**
	 * This method removes a position.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param positioncode  Code of the position to be removed
	 */
	public static void RemovePosition(String administradorId, String positioncode) throws InternalException, NoPermisosException {
		UseCase.RemovePosition(UseCase.getUsuario(administradorId), positioncode);
	}

	/**
	 * This method obtains one instance of position given its code.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param positioncode Code of the position to be obtained
	 * @return Position with the given code.
	 * @throws InternalException
	 */
	public static Position ObtainPosition(String administradorId, String positioncode) throws InternalException {
		return UseCase.ObtainPosition(UseCase.getUsuario(administradorId), positioncode);
	}

	/**
	 * This method obtains all instances of Position, given a list-configurator.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param configurator ListConfigurator to be used
	 * @return A pair with an Integer with the total number of instances which match the search without appling the 'pagination' of the ListConfigurator, and the list of the instances which match the configurator (incluing pagination)
	 * @throws InternalException
	 */
	public static Pair<Integer, List<Position>> ObtainAllPosition(String administradorId, ListConfigurator configurator) throws InternalException {
		return UseCase.ObtainAllPosition(UseCase.getUsuario(administradorId), configurator);
	}

	/**
	 * This method obtains all the languages of the application. 
	 *
	 * @return List of languages
	 */
	public static List<Language> ObtenerIdiomas() {
		return UseCase.ObtenerIdiomas();
	}

	/**
	 * This method obtains all the rolls of the application. 
	 *
	 * @return List of rolls
	 */
	public static List<Role> ObtenerRoles() throws InternalException {
		return UseCase.ObtenerRoles();
	}

	/**
	 * This function gets a user from its username, only if it's active and not deleted
	 *
	 * @param username String with the username of the user to obtain
	 * @return Logged user
	 */
	public static Usuario HacerLogin(String username, String password, String remoteHost) {
		return UseCase.HacerLogin(username, password, remoteHost);
	}

	public static void HacerLogout(Usuario user, String remoteHost, String type) {
		UseCase.HacerLogout(user, remoteHost, type);
	}

	/**
	 * This method activates an existing user
	 *
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param usuarioId The user who will be actived
	 * @return User which has been activated
	 * @throws NoPermisosException
	 * @throws InternalException
	 * @throws UsuarioNoActivoException
	 * @throws ValidationFailedException 
	 */
	public static Usuario AltaUsuario(String administradorId, String usuarioId) throws UsuarioNoActivoException, NoPermisosException, InternalException, ValidationFailedException {
		return UseCase.AltaUsuario(UseCase.getUsuario(administradorId), UseCase.getUsuario(usuarioId));
	}

	/**
	 * This method activates an existing user
	 *
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param usuarioId The user who will be actived
	 * @return User which has been activated
	 * @throws NoPermisosException
	 * @throws InternalException
	 * @throws UsuarioNoActivoException
	 * @throws ValidationFailedException 
	 */
	public static Usuario AltaUsuario(String administradorId, String usuarioId, String activationCode) throws UsuarioNoActivoException, NoPermisosException, InternalException, ValidationFailedException {
		return UseCase.AltaUsuario(UseCase.getUsuario(administradorId), UseCase.getUsuario(usuarioId), activationCode);
	}

	/**
	 * This method marks an existing user as deleted.
	 *
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param usuarioId The user who will be marked as deleted
	 * @return User which has been marked as deleted.
	 * @throws NoPermisosException
	 * @throws InternalException
	 * @throws UsuarioNoActivoException
	 */
	public static Usuario BorrarUsuario(String administradorId, String usuarioId) throws UsuarioNoActivoException, NoPermisosException, InternalException {
		return UseCase.BorrarUsuario(UseCase.getUsuario(administradorId), UseCase.getUsuario(usuarioId));
	}

	/**
	 * This method modifies the user's password.
	 *
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param cambiado old user password
	 * @return password new user password
	 * @throws NoPermisosException
	 * @throws InternalException
	 * @throws UsuarioNoActivoException
	 */
	public static Usuario ModificarPassword(String administradorId, String cambiado, String password) throws UsuarioNoActivoException, NoPermisosException, InternalException {
		return UseCase.ModificarPassword(UseCase.getUsuario(administradorId), UseCase.getUsuario(cambiado), password);
	}

	/**
	 * This method deactivates a user.
	 *
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param usuarioId Code (no username) of the user to deactivate
	 * @return deactivated user
	 * @throws NoPermisosException
	 * @throws InternalException
	 * @throws UsuarioNoActivoException
	 */
	public static Usuario DesactivarUsuario(String administradorId, String usuarioId) throws UsuarioNoActivoException, NoPermisosException, InternalException {
		return UseCase.DesactivarUsuario(UseCase.getUsuario(administradorId), UseCase.getUsuario(usuarioId));
	}

	/**
	 * This method recovers a user.
	 *
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param usuarioId Code (no username) of the user to recover
	 * @return recovered user
	 * @throws NoPermisosException
	 * @throws InternalException
	 * @throws UsuarioNoActivoException
	 */
	public static Usuario RestaurarUsuario(String administradorId, String usuarioId) throws UsuarioNoActivoException, NoPermisosException, InternalException {
		return UseCase.RestaurarUsuario(UseCase.getUsuario(administradorId), UseCase.getUsuario(usuarioId));
	}

	/**
	 * This method removes a user definitively. 
	 *
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param usuarioId Code (no username) of the user to removed definetely
	 * @return Removed user
	 * @throws NoPermisosException
	 * @throws InternalException
	 * @throws UsuarioNoActivoException
	 */
	public static Usuario BorrarUsuarioDefinitivamente(String administradorId, String usuarioId) throws UsuarioNoActivoException, NoPermisosException, InternalException {
		return UseCase.BorrarUsuarioDefinitivamente(UseCase.getUsuario(administradorId), UseCase.getUsuario(usuarioId));
	}

	/**
	 * This method removes all users definitively.
	 *
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @throws NoPermisosException
	 * @throws InternalException
	 * @throws UsuarioNoActivoException
	 */
	public static void BorrarTODOSUsuariosDefinitivamente(String administradorId) throws UsuarioNoActivoException, NoPermisosException, InternalException {
		UseCase.BorrarTODOSUsuariosDefinitivamente(UseCase.getUsuario(administradorId));
	}

	/**
	 * This method creates a new user.
	 *
	 * @param usuario Data transfer object (DTO) with the values of the new instance.
	 * @return new user 
	 * @throws UsuarioExisteException
	 */
	public static Usuario CrearUsuario(Usuario usuario) throws UsuarioExisteException {
		return UseCase.CrearUsuario(usuario);
	}

	public static Usuario CreateUsuarioForPersonal(Usuario userTO, String personalcode) throws UsuarioExisteException, InternalException {
		return UseCase.CreateUsuarioForPersonal(userTO, personalcode);
	}

	/**
	 * This method obtains a user given its code (no username)
	 *
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param usuarioId Code (no username) of the user to obtain
	 * @return Obtained user
	 * @throws NoPermisosException
	 * @throws InternalException
	 */
	public static Usuario ObtenerUsuario(String administradorId, String usuarioId) throws NoPermisosException, InternalException {

		Usuario user = UseCase.ObtenerUsuario(UseCase.getUsuario(administradorId), usuarioId);

		return user;
	}

	/**
	 * This method obtains the roles of a user. 
	 *
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param usuarioId Code (no username) of the user to which the roles are obtained
	 * @return Roles of the desired user
	 * @throws NoPermisosException
	 * @throws InternalException
	 */
	public static Set<Role> ObtenerRolesUsuario(String administradorId, String usuarioId) throws NoPermisosException, InternalException {

		Set<Role> roles = UseCase.ObtenerRolesUsuario(UseCase.getUsuario(administradorId), usuarioId);

		return roles;
	}

	/**
	 * This method returns true if the user is a Basic user (no supervisor, rrhh, admin)
	 *
	 * @param admin The user who executes this use case
	 * @param userId The user who will be obained its roles
	 * @return true if it is a basic user
	 */
	public static boolean isOnlyRoleBasic(String admin, String userId) throws InternalException {
		return UseCase.isOnlyRoleBasic(UseCase.getUsuario(admin), userId);
	}

	/**
	 * This method obtains all active users, given a configurator
	 *
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param configurator ListConfigurator to be used
	 * @return A pair with an Integer with the total number of instances which match the search without appling the 'pagination' of the ListConfigurator, and the list of the active users which match the configurator (incluing pagination)
	 * @throws NoPermisosException
	 * @throws InternalException
	 * @throws UsuarioNoActivoException
	 */
	public static Pair<Integer, List<Usuario>> ObtenerUsuariosActivos(String administradorId, ListConfigurator configurator) throws UsuarioNoActivoException, NoPermisosException, InternalException {

		Pair<Integer, List<Usuario>> usuarios = UseCase.ObtenerUsuariosActivos(UseCase.getUsuario(administradorId), configurator);

		return usuarios;
	}

	/**
	 * This method obtains all system users, given a configurator
	 *
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param configurator ListConfigurator to be used
	 * @return A pair with an Integer with the total number of instances which match the search without appling the 'pagination' of the ListConfigurator, and the list of the system users which match the configurator (incluing pagination)
	 * @throws NoPermisosException
	 * @throws InternalException
	 * @throws UsuarioNoActivoException
	 */
	public static Pair<Integer, List<Usuario>> ObtenerUsuariosSistema(String administradorId, ListConfigurator configurator) throws UsuarioNoActivoException, NoPermisosException, InternalException {

		Pair<Integer, List<Usuario>> usuarios = UseCase.ObtenerUsuariosSistema(UseCase.getUsuario(administradorId), configurator);

		return usuarios;
	}

	/**
	 * This method obtains all pending users, given a configurator  
	 *
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param configurator ListConfigurator to be used
	 * @return A pair with an Integer with the total number of instances which match the search without appling the 'pagination' of the ListConfigurator, and the list of the pending users which match the configurator (incluing pagination)
	 * @throws NoPermisosException
	 * @throws InternalException
	 * @throws UsuarioNoActivoException	 
	 */
	public static Pair<Integer, List<Usuario>> ObtenerUsuariosPendientes(String administradorId, ListConfigurator configurator) throws UsuarioNoActivoException, NoPermisosException, InternalException {

		Pair<Integer, List<Usuario>> usuarios = UseCase.ObtenerUsuariosPendientes(UseCase.getUsuario(administradorId), configurator);

		return usuarios;
	}

	/**
	 * This method obtains all the marked as deleted users, given a configurator
	 *
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param configurator ListConfigurator to be used
	 * @return A pair with an Integer with the total number of instances which match the search without appling the 'pagination' of the ListConfigurator, and the list of the marked as deleted users which match the configurator (incluing pagination)
	 * @throws NoPermisosException
	 * @throws InternalException
	 * @throws UsuarioNoActivoException
	 */
	public static Pair<Integer, List<Usuario>> ObtenerUsuariosBorrados(String administradorId, ListConfigurator configurator) throws UsuarioNoActivoException, NoPermisosException, InternalException {

		Pair<Integer, List<Usuario>> usuarios = UseCase.ObtenerUsuariosBorrados(UseCase.getUsuario(administradorId), configurator);

		return usuarios;
	}

	/**
	 * This method modifies a user.  
	 *
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param usuario Data transfer object (DTO) with the values of the modified user. The code of this attribute indicates which user will be modified.
	 * @return Modified user
	 * @throws NoPermisosException
	 * @throws InternalException
	 * @throws UsuarioNoActivoException
	 */
	public static Usuario ModificarUsuario(String administradorId, Usuario usuario) throws NoPermisosException, UsuarioNoActivoException, InternalException {

		Usuario user = UseCase.ModificarUsuario(UseCase.getUsuario(administradorId), usuario);
		return user;
	}

	/**
	 * This method clears all roles from a user.
	 *
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param usuario The user to which the roles are deleted
	 * @throws NoPermisosException
	 * @throws InternalException
	 * @throws UsuarioNoActivoException
	 */
	public static void QuitarRoles(String administradorId, Usuario usuario) throws NoPermisosException, UsuarioNoActivoException, InternalException {
		UseCase.QuitarRoles(UseCase.getUsuario(administradorId), usuario);
	}

	/**
	 * This method add a role to a user.
	 *
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param usuario The user to which the role is added
	 * @param rolId Code of the role to add
	 * @throws NoPermisosException
	 * @throws InternalException
	 * @throws UsuarioNoActivoException
	 */
	public static void AddRol(String administradorId, Usuario usuario, String rolId) throws NoPermisosException, UsuarioNoActivoException, InternalException {
		UseCase.AddRol(UseCase.getUsuario(administradorId), usuario, rolId);
	}

	/**
	 * This method returns the personal related to a user
	 * @param usercode Code (no username) of the user who executes this use case
	 * @param currentUsuario User related to the personal
	 * @return Personal related to currentUsuario
	 * @throws InternalException 
	 */
	public static Personal ObtainPersonalFromUser(String usercode, Usuario currentUsuario) throws InternalException {
		return UseCase.ObtainPersonalFromUser(UseCase.getUsuario(usercode), currentUsuario);
	}

	/**
	 * This method changes the state of a personal from edition to validation
	 * 
	 * @param person The user who executes this use case
	 * @param personalcode Code of the personal to be modified.
	 * @return the modified personal
	 * @throws InternalException
	 * @throws UsuarioNoActivoException 
	 * @throws NoPermisosException 
	 * @throws ValidationFailedException 
	 */
	public static Personal changeStateOfPersonalFromEditionToValidating(String usercode, String personalcode) throws InternalException, NoPermisosException, UsuarioNoActivoException, ValidationFailedException {
		return UseCase.changeStateOfPersonalFromEditionToValidating(UseCase.getUsuario(usercode), UseCase.getPersonal(personalcode));
	}

	/**
	 * This method changes the state of a personal from validation to in progress
	 * 
	 * @param person The user who executes this use case
	 * @param personalcode Code of the personal to be modified.
	 * @return the modified personal
	 * @throws InternalException
	 * @throws ValidationFailedException 
	 */
	public static Personal changeStateOfPersonalFromValidatingToValidated(String usercode, String personalcode) throws InternalException {
		return UseCase.changeStateOfPersonalFromValidatingToValidated(UseCase.getUsuario(usercode), UseCase.getPersonal(personalcode));
	}

	/**
	 * This method changes the state of a personal from in progress to active
	 * 
	 * @param person The user who executes this use case
	 * @param personalcode Code of the personal to be modified.
	 * @return the modified personal
	 * @throws InternalException
	 * @throws ValidationFailedException 
	 * @throws NoPermisosException 
	 */
	public static Personal changeStateOfPersonalFromValidatedToActive(String usercode, String personalcode) throws InternalException, ValidationFailedException, NoPermisosException {
		return UseCase.changeStateOfPersonalFromValidatedToActive(UseCase.getUsuario(usercode), UseCase.getPersonal(personalcode));
	}

	/**
	 * This method changes the state of a personal from in active to inactive
	 * 
	 * @param person The user who executes this use case
	 * @param personalcode Code of the personal to be modified.
	 * @return the modified personal
	 * @throws InternalException
	 * @throws NoPermisosException 
	 */
	public static Personal changeStateOfPersonalFromActiveToInactive(String usercode, Personal personal) throws InternalException, NoPermisosException {
		return UseCase.changeStateOfPersonalFromActiveToInactive(UseCase.getUsuario(usercode), personal);
	}

	/**
	 * This method changes the state of a personal from in inactive to active
	 * 
	 * @param person The user who executes this use case
	 * @param personalcode Code of the personal to be modified.
	 * @return the modified personal
	 * @throws InternalException
	 */
	public static Personal changeStateOfPersonalFromInactiveToActive(String usercode, String personalcode) throws InternalException {
		return UseCase.changeStateOfPersonalFromInactiveToActive(UseCase.getUsuario(usercode), UseCase.getPersonal(personalcode));
	}

	/**
	 * Generates a new password for a user if the passwordGenerationActivationCode is correct (it is generated by requestToGeneratePassword(String,String)
	 * @param usercode
	 * @param usuariocode
	 * @param passwordGenerationActivationCode
	 * @return
	 * @throws UsuarioNoActivoException
	 * @throws NoPermisosException
	 * @throws InternalException
	 */
	public static Usuario generatePassword(String usercode, String usuariocode, String passwordGenerationActivationCode) throws UsuarioNoActivoException, NoPermisosException, InternalException {
		return UseCase.generatePassword(UseCase.getUsuario(usercode), UseCase.getUsuario(usuariocode), passwordGenerationActivationCode);

	}

	/**
	 * It generates a password-generation-activation-code. See generatePassword(String, String, String)
	 * @param usercode
	 * @param usuariocode
	 * @return
	 * @throws InternalException
	 * @throws NoPermisosException 
	 */
	public static Usuario requestToGeneratePassword(String usercode, String usuariocode) throws InternalException, NoPermisosException {
		return UseCase.requestToGeneratePassword(UseCase.getUsuario(usercode), UseCase.getUsuario(usuariocode));

	}

	/**
	 * This method checks if a personal can be changed to valitation from edition
	 * @param person
	 * @param personalCode
	 * @throws InternalException 
	 * @throws ValidationFailedException 
	 * @throws NoPermisosException 
	 */
	public static void checkToValidateRequiredData(String usercode, String personalCode) throws InternalException, ValidationFailedException, NoPermisosException {
		UseCase.checkToValidateRequiredData(UseCase.getUsuario(usercode), personalCode);
	}

	/**
	 * This method checks if a personal can be changed to progress from validation
	 * @param person
	 * @param personalCode
	 * @throws InternalException 
	 * @throws ValidationFailedException 
	 * @throws NoPermisosException 
	 */
	public static void checkToProgressRequiredData(String usercode, String personalCode) throws InternalException, ValidationFailedException, NoPermisosException {
		UseCase.checkToValidatedRequiredData(UseCase.getUsuario(usercode), personalCode);
	}

	public static boolean isSupervisorOf(String usercode, String supervisorPersonalCode, String personalCode) throws InternalException, NoPermisosException {
		return UseCase.isSupervisorOf(UseCase.getUsuario(usercode), UseCase.getPersonal(supervisorPersonalCode), UseCase.getPersonal(personalCode));
	}

	public static boolean isObtentionPhaseActive() {
		return UseCase.isObtentionPhaseActive();
	}

	public static Pair<Integer, List<Personal>> ObtainAllPersonalWithoutUser(String usercode, ListConfigurator configurator) throws InternalException {
		return UseCase.ObtainAllPersonalWithoutUser(UseCase.getUsuario(usercode), configurator);
	}

	/**
	 * This method creates a report.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param report Report data transfer object (DTO) with the values of the new instance.
	 * @return the new report created with the Use Case
	 * @throws InternalException
	 */
	public static Report CreateReport(String administradorId, Report report) throws InternalException {
		return UseCase.CreateReport(UseCase.getUsuario(administradorId), report);
	}

	/**
	 * This method modifies a report.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param report Report data transfer object (DTO) with the values of the modified instance. The code of this attribute indicates which report will be modified.
	 * @return the modified report
	 * @throws InternalException
	 */
	public static Report UpdateReport(String administradorId, Report report) throws InternalException {
		return UseCase.UpdateReport(UseCase.getUsuario(administradorId), report);
	}

	/**
	 * This method removes a report.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param reportcode  Code of the report to be removed
	 */
	public static void RemoveReport(String administradorId, String reportcode) throws InternalException {
		UseCase.RemoveReport(UseCase.getUsuario(administradorId), reportcode);
	}

	/**
	 * This method obtains one instance of report given its code.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param reportcode Code of the report to be obtained
	 * @return Report with the given code.
	 * @throws InternalException
	 */
	public static Report ObtainReport(String administradorId, String reportcode) throws InternalException {
		return UseCase.ObtainReport(UseCase.getUsuario(administradorId), reportcode);
	}

	/**
	 * This method obtains all instances of Report, given a list-configurator.
	 * 
	 * @param administradorId Code (no username) of the user who executes this use case
	 * @param configurator ListConfigurator to be used
	 * @return A pair with an Integer with the total number of instances which match the search without appling the 'pagination' of the ListConfigurator, and the list of the instances which match the configurator (incluing pagination)
	 * @throws InternalException
	 */
	public static Pair<Integer, List<Report>> ObtainAllReport(String administradorId, ListConfigurator configurator) throws InternalException {
		return UseCase.ObtainAllReport(UseCase.getUsuario(administradorId), configurator);
	}

	public static Personal testValidatePersonal(String usercode, String personalcode, String validationCode) throws Exception {

		return UseCase.testValidatePersonal(UseCase.getUsuario(usercode), personalcode, validationCode);
	}

	public static void cancelValidatePersonal(String usercode, String personalcode, String observacions) throws Exception {

		UseCase.cancelValidationPersonal(UseCase.getUsuario(usercode), personalcode, observacions);
	}

	public static void validatePersonal(String usercode, String personalcode, String observacions) throws Exception {

		UseCase.validatePersonal(UseCase.getUsuario(usercode), personalcode, observacions);
	}

	public static Pair<Integer, List<Personal>> ObtainPersonalWithContract(String usercode, ListConfigurator configurator) throws InternalException {

		return UseCase.ObtainPersonalWithContract(UseCase.getUsuario(usercode), configurator);
	}

	public static Pair<Integer, List<Personal>> ObtainPersonalWithContractByUnit(String usercode, String unitcode) throws InternalException {

		return UseCase.ObtainPersonalWithContractByUnit(UseCase.getUsuario(usercode), unitcode);
	}

	public static Pair<Integer, List<Personal>> ObtainPersonalWithContractAboutToExpire(String usercode, ListConfigurator configurator) throws InternalException {

		return UseCase.ObtainPersonalWithContractAboutToExpire(UseCase.getUsuario(usercode), configurator);
	}

	public static Pair<Integer, List<Personal>> ObtainPersonalWithGrant(String usercode, ListConfigurator configurator) throws InternalException {

		return UseCase.ObtainPersonalWithGrant(UseCase.getUsuario(usercode), configurator);
	}

	public static Pair<Integer, List<Personal>> ObtainPersonalWithGrantAboutToExpire(String usercode, ListConfigurator configurator) throws InternalException {

		return UseCase.ObtainPersonalWithGrantAboutToExpire(UseCase.getUsuario(usercode), configurator);
	}

	public static Pair<Integer, List<Personal>> ObtainOtherPersonal(String usercode, ListConfigurator configurator) throws InternalException {
		return UseCase.ObtainOtherPersonal(UseCase.getUsuario(usercode), configurator);
	}

	public static Pair<Integer, Pair<List<Personal>, Map<String, String[]>>> ObtainOtherPersonalAndOrderMap(String usercode, ListConfigurator configurator) throws InternalException {
		return UseCase.ObtainOtherPersonalAndOrderMap(UseCase.getUsuario(usercode), configurator);
	}

	public static Irbholiday CreateIrbholiday(String usercode, Irbholiday irbholiday) throws InternalException, NoPermisosException, HolidaysException {
		return UseCase.CreateIrbholiday(UseCase.getUsuario(usercode), irbholiday);
	}

	public static Irbholiday CreateIrbholidayFromPersonal(String usercode, Irbholiday TOirbholiday) throws NoPermisosException, InternalException, HolidaysException {
		return UseCase.CreateIrbholidayFromPersonal(UseCase.getUsuario(usercode), TOirbholiday);
	}

	public static Irbholiday DeleteIrbholiday(String usercode, String irbholidaycode) throws InternalException, HolidaysException {
		return UseCase.DeleteIrbholiday(UseCase.getUsuario(usercode), irbholidaycode);
	}

	public static Irbholiday ObtainIrbholiday(String usercode, String irbholidaycode) throws InternalException {
		return UseCase.ObtainIrbholiday(UseCase.getUsuario(usercode), irbholidaycode);
	}

	public static Irbholiday UpdateIrbholiday(String usercode, Irbholiday irbholiday) throws InternalException, HolidaysException {
		return UseCase.UpdateIrbholiday(UseCase.getUsuario(usercode), irbholiday);
	}

	public static Pair<Integer, List<Irbholiday>> obtainIrbholidayFestiu(String usercode, int year, ListConfigurator configurator) throws InternalException {

		return UseCase.ObtainIrbholidayFestiu(UseCase.getUsuario(usercode), year, configurator);
	}

	public static Pair<Integer, List<Irbholiday>> ObtainAllIIrbholidayFromPersonal(String usercode, String personalcode, java.util.Calendar date, ListConfigurator configurator) throws InternalException {
		return UseCase.ObtainAllIIrbholidayFromPersonal(UseCase.getUsuario(usercode), personalcode, date, configurator);
	}

	public static Irbholiday ObtainIrbholidayLimitVacances(String usercode, Calendar year) throws InternalException {
		return UseCase.ObtainIrbholidayLimitVacances(null, year);
	}

	public static Irbholidayinfo ObtainIrbholidayinfoFromPersonal(String usercode, String personalcode, Calendar year) throws InternalException {
		return UseCase.ObtainIrbholidayinfoFromPersonal(UseCase.getUsuario(usercode), personalcode, year);
	}

	public static Irbholiday CreatePendingIrbholiday(String usercode, Irbholiday irbholiday) throws InternalException, NoPermisosException, HolidaysException {
		return UseCase.CreatePendingIrbholiday(UseCase.getUsuario(usercode), irbholiday);
	}

	public static Irbholiday ChangeStateOfIrbholidayFromDemanatToAprovat(String usercode, Irbholiday irbholiday, String observacions) throws InternalException, NoPermisosException {
		return UseCase.ChangeStateOfIrbholidayFromDemanatToAprovat(UseCase.getUsuario(usercode), irbholiday, observacions);
	}

	public static Irbholiday cancelValidateHolidays(String usercode, Irbholiday irbholiday, String observacions) throws InternalException, NoPermisosException {
		return UseCase.cancelValidationHolidays(UseCase.getUsuario(usercode), irbholiday, observacions);
	}

	public static List<Views> ObtainAllViews(Usuario user) throws InternalException {
		return UseCase.ObtainAllViews(user);
	}

	public static List<CustomList> ObtainAllCustomList(Usuario user) throws InternalException {
		return UseCase.ObtainAllCustomList(user);
	}

	public static CustomList CreateCustomList(String usercode, CustomList TOCustomList) throws InternalException, NoPermisosException {
		return UseCase.CreateCustomList(UseCase.getUsuario(usercode), TOCustomList);
	}

	public static Column CreateColumn(String usercode, Column TOColumn) throws InternalException, NoPermisosException {
		return UseCase.CreateColumn(UseCase.getUsuario(usercode), TOColumn);
	}

	public static OrderBy CreateOrderBy(String usercode, OrderBy TOOrderBy) throws InternalException, NoPermisosException {
		return UseCase.CreateOrderBy(UseCase.getUsuario(usercode), TOOrderBy);
	}

	public static Filter CreateFilter(String usercode, Filter TOFilter) throws InternalException, NoPermisosException {
		return UseCase.CreateFilter(UseCase.getUsuario(usercode), TOFilter);
	}

	public static FilterType ObtainFilterType(String usercode, String filtertypecode) throws InternalException {
		return UseCase.ObtainFilterType(UseCase.getUsuario(usercode), filtertypecode);
	}

	public static CustomList ObtainCustomList(String usercode, String customListcode) throws InternalException {
		return UseCase.ObtainCustomList(UseCase.getUsuario(usercode), customListcode);
	}

	public static List<Column> ObtainAllColumnsFrom(String usercode, String customlistcode) throws InternalException {
		return UseCase.ObtainAllColumnsFrom(UseCase.getUsuario(usercode), customlistcode);
	}

	public static List<Filter> ObtainAllFiltersFrom(String usercode, String customlistcode) throws InternalException {
		return UseCase.ObtainAllFiltersFrom(UseCase.getUsuario(usercode), customlistcode);
	}

	public static List<OrderBy> ObtainAllOrderBysFrom(String usercode, String customlistcode) throws InternalException {
		return UseCase.ObtainAllOrderBysFrom(UseCase.getUsuario(usercode), customlistcode);
	}

	public static void UpdateCustomListElements(String usercode, String customlistcode, String[] columns, String[] orderby, List<String[]> filter) throws InternalException, NoPermisosException {

		UseCase.UpdateCustomListElements(UseCase.getUsuario(usercode), customlistcode, columns, orderby, filter);
	}

	public static void DeleteCustomListElements(String usercode, String customlistcode) throws InternalException, NoPermisosException {

		UseCase.DeleteCustomListElements(UseCase.getUsuario(usercode), customlistcode);
	}

	public static Personal ObtainPersonalFromIrbholidayinfo(String usercode, String irbholidayinfocode) throws InternalException {
		return UseCase.ObtainPersonalFromIrbholidayinfo(UseCase.getUsuario(usercode), irbholidayinfocode);
	}

	public static Irbholidayinfo ObtainIrbholidayinfo(String usercode, String irbholidayinfocode) throws InternalException {
		return UseCase.ObtainIrbholidayinfo(UseCase.getUsuario(usercode), irbholidayinfocode);
	}

	public static Irbholidayinfo sendHolidaysInfoFromPersonal(String usercode, Irbholidayinfo irbholidayinfo) throws NoPermisosException, InternalException {
		return UseCase.sendHolidaysInfoFromPersonal(UseCase.getUsuario(usercode), irbholidayinfo);
	}

	public static Pair<Integer, List<Irbholidayinfo>> ObtainAllIrbholidayinfoFromYear(String usercode, int year, ListConfigurator configurator) throws InternalException {
		return UseCase.ObtainAllIrbholidayinfoForYear(UseCase.getUsuario(usercode), year, configurator);
	}

	public static Irbholidayinfo UpdateIrbholidayinfo(String usercode, Irbholidayinfo irbholidayinfo) throws InternalException, HolidaysException {
		return UseCase.UpdateIrbholidayinfo(UseCase.getUsuario(usercode), irbholidayinfo);
	}

	public static String SetCurrentCompensation(String usercode, String compensationcode) throws InternalException {
		return UseCase.SetCurrentCompensation(UseCase.getUsuario(usercode), compensationcode);
	}

	public static String SetCurrentGrant_concession(String usercode, String grant_concessioncode) throws InternalException {
		return UseCase.SetCurrentGrant_concession(UseCase.getUsuario(usercode), grant_concessioncode);
	}

	public static String SetCurrentAcademic_info(String usercode, String academic_infocode) throws InternalException {
		return UseCase.SetCurrentAcademic_info(UseCase.getUsuario(usercode), academic_infocode);
	}

	public static CustomList SetPeriodicCustomList(String usercode, String customListcode) throws InternalException {
		return UseCase.SetPeriodicCustomList(UseCase.getUsuario(usercode), customListcode);
	}

	public static Personal_comment ObtainPersonal_comment(String usercode, String personal_commentcode) throws InternalException {
		return UseCase.ObtainPersonal_comment(UseCase.getUsuario(usercode), personal_commentcode);
	}

	public static Personal_comment UpdatePersonal_comment(String usercode, Personal_comment personal_comment) throws InternalException {
		return UseCase.UpdatePersonal_comment(UseCase.getUsuario(usercode), personal_comment);
	}

	public static Object createNextYearHolidays(String usercode) throws InternalException {
		return UseCase.createNextYearHolidays(UseCase.getUsuario(usercode));

	}

	public static List<Irbholiday> ObtainHolidaysBetweenDates(Date startDate, Date endDate) {
		return UseCase.ObtainHolidaysBetweenDates(startDate, endDate);
	}
}