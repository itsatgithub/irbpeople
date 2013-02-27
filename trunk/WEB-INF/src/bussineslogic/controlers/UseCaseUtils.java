package bussineslogic.controlers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;

import ldap.ldif.LDIFGenerator;
import ldap.ssh.SSHConnection;
import utils.dateformat.MultipleDateFormat;
import utils.filter.ListConfigurator;
import utils.hibernate.HibernateUtil;
import bussineslogic.excepciones.InternalException;
import bussineslogic.excepciones.NoPermisosException;
import bussineslogic.excepciones.ValidationFailedException;
import bussineslogic.objects.Irbholiday;
import bussineslogic.objects.Irbholidayinfo;
import bussineslogic.objects.Personal;
import bussineslogic.objects.PersonalPhoto;
import bussineslogic.objects.Professional;
import bussineslogic.objects.Research_group;
import bussineslogic.objects.Usuario;

import com.justinmind.MailSystem.AbstractMailHolidaysToUsers;
import com.justinmind.MailSystem.AbstractMailToSupervisors;
import com.justinmind.MailSystem.MailAdviseRRHH;
import com.justinmind.MailSystem.MailAdviseSupervisorNewHolidaysFromPersonal;
import com.justinmind.MailSystem.MailAdviseSupervisorNewValidationPersonal;
import com.justinmind.MailSystem.MailController;
import com.justinmind.MailSystem.MailEndOfContractInstructions;
import com.justinmind.MailSystem.MailPasswordChanged;
import com.justinmind.MailSystem.MailSettedToSupervisor;

public class UseCaseUtils {

	private static org.apache.log4j.Category log = org.apache.log4j.Logger.getLogger(UseCaseUtils.class);

	private static ResourceBundle MAINCONFIG = ResourceBundle.getBundle("MainConfiguration");

	public static void sendConfirmationMail(Usuario user) {
		MailController.singleton().PrepareMail("UserActivation", user, HibernateUtil.getSession());
	}

	public static void sendPasswordChangedMail(Usuario user, String password) {
		MailController.singleton().PrepareMail("PasswordChanged", new MailPasswordChanged.MailData(user, password), HibernateUtil.getSession());
	}

	public static void sendPasswordChangeRequestedMail(Usuario user, String activationCode) {
		MailController.singleton().PrepareMail("PasswordGenerationRequired", user, HibernateUtil.getSession());
	}

	public static void sendUserActivatedMail(Usuario user) {
		MailController.singleton().PrepareMail("UserActivated", user, HibernateUtil.getSession());
	}

	public static String createActivationCode() {
		return Long.toString(Math.abs(new Random().nextLong()));
	}

	// retorna un codi per a la validacio
	public static String createValidationCode() {
		return createActivationCode();
	}

	public static String generatePassword() {
		return Integer.toString(Math.abs(new Random().nextInt()));
	}

	public static void sendAdviseSupervisorNewHolidaysFromPersonalMail(Irbholiday irbholiday) throws NoPermisosException, InternalException {

		List<Professional> professionals =
				UseCase.ObtainAllActiveIprofessional_personalFromPersonal(null, irbholiday.getPersonal(), new ListConfigurator()).second;

		Usuario supervisorUser = null;

		for (Professional professional : professionals) {
			if (professional.getResearch_group() == null || professional.getResearch_group().getSupervisor() == null)
				continue;

			MailController.singleton().PrepareMail("AdviseSupervisorNewHolidaysFromPersonal", new MailAdviseSupervisorNewHolidaysFromPersonal.MailData(professional.getResearch_group().getSupervisor(), irbholiday), HibernateUtil.getSession());
		}

		if (supervisorUser == null) {
			for (Professional professional : professionals) {
				if (professional.getProfessional_unit() == null || professional.getProfessional_unit().getSupervisor() == null)
					continue;

				MailController.singleton().PrepareMail("AdviseSupervisorNewHolidaysFromPersonal", new MailAdviseSupervisorNewHolidaysFromPersonal.MailData(professional.getProfessional_unit().getSupervisor(), irbholiday), HibernateUtil.getSession());
			}
		}
	}

	public static void sendAdviseSupervisorNewValidationPersonalMail(Personal personal) throws NoPermisosException, InternalException {
		sendMailTOSupervisor(personal, "AdviseSupervisorNewValidationPersonal");
	}

	public static void sendAdviseSupervisorReturnedToValidationPersonalMail(Personal personal) throws NoPermisosException, InternalException {
		sendMailTOSupervisor(personal, "AdviseSupervisorReturnedToValidationPersonal");
	}

	public static void sendAdviseAdviseSupervisorInactivatedPersonalMail(Personal personal) throws NoPermisosException, InternalException {
		sendMailTOSupervisor(personal, "AdviseSupervisorInactivatedPersonal");
	}

	private static void sendMailTOSupervisor(Personal personal, String mailType) throws NoPermisosException, InternalException {

		List<Professional> professionals = UseCase.ObtainAllActiveIprofessional_personalFromPersonal(null, personal, new ListConfigurator()).second;

		for (Professional professional : professionals) {
			if (professional.getResearch_group() == null || professional.getResearch_group().getSupervisor() == null)
				continue;

			MailController.singleton().PrepareMail(mailType, new MailAdviseSupervisorNewValidationPersonal.MailData(professional.getResearch_group().getSupervisor(), personal), HibernateUtil.getSession());
		}
	}

	public static void sendValidatingPersonalSettedToSupervisor(Personal supervisor, Personal valitadionPersonal) throws NoPermisosException, InternalException {
		if (supervisor == null)
			return;;

		MailController.singleton().PrepareMail("ValidatingPersonalSettedToSupervisor", new AbstractMailToSupervisors.MailData(supervisor, valitadionPersonal), HibernateUtil.getSession());
	}

	public static void sendSettedToSupervisor(Personal supervisor, Research_group group) throws NoPermisosException, InternalException {
		if (supervisor == null)
			return;

		MailController.singleton().PrepareMail("SettedToSupervisor", new MailSettedToSupervisor.MailData(supervisor, group), HibernateUtil.getSession());
	}

	public static void sendMailToRRHH(Personal personal, String mailType, String observacions) throws Exception {
		//		List<Usuario> rrhh = UseCase.obtainAllRRHH(null);
		//		for(Usuario usuario:rrhh) {
		MailController.singleton().PrepareMail(mailType, new MailAdviseRRHH.MailData(null, personal, observacions), HibernateUtil.getSession());
		//		}
	}

	public static void sendMailToAdviseValidationSuccess(Personal personal, String observacions) throws Exception {
		sendMailToRRHH(personal, "ValidationSuccess", observacions);
	}

	public static void sendMailToAdviseValidationFail(Personal personal, String observacions) throws Exception {
		sendMailToRRHH(personal, "ValidationFail", observacions);
	}

	public static void sendMailHolidaysToUsers(String mailType, Irbholiday irbholiday, Irbholidayinfo irbholidayinfo, String observacions) throws NoPermisosException, InternalException {

		String personalcode;
		if (irbholiday != null)
			personalcode = irbholiday.getPersonal().getPersonalcode();
		else
			personalcode = irbholidayinfo.getPersonal().getPersonalcode();

		Personal person = UseCase.getPersonal(personalcode);
		if (person != null) {
			MailController.singleton().PrepareMail(mailType, new AbstractMailHolidaysToUsers.MailData(person, irbholiday, irbholidayinfo, observacions), HibernateUtil.getSession());
		}
	}

	public static void sendMailHolidaysAproved(Irbholiday irbholiday, Irbholidayinfo irbholidayinfo) throws NoPermisosException, InternalException {
		sendMailHolidaysToUsers("HolidaysAproved", irbholiday, irbholidayinfo, null);
	}

	public static void sendMailHolidaysPending(Irbholiday irbholiday) throws NoPermisosException, InternalException {
		sendMailHolidaysToUsers("HolidaysPending", irbholiday, null, null);
	}

	public static void sendMailHolidaysValidationFail(Irbholiday irbholiday, Irbholidayinfo irbholidayinfo, String obs) throws NoPermisosException, InternalException {
		sendMailHolidaysToUsers("HolidaysValidationFail", irbholiday, irbholidayinfo, obs);
	}

	public static void sendMailHolidaysValidationSuccess(Irbholiday irbholiday, Irbholidayinfo irbholidayinfo, String obs) throws NoPermisosException, InternalException {
		sendMailHolidaysToUsers("HolidaysValidationSuccess", irbholiday, irbholidayinfo, /*cal??*/obs);
	}

	public static void sendMailHolidaysInfo(Irbholidayinfo irbholidayinfo) throws NoPermisosException, InternalException {
		sendMailHolidaysToUsers("HolidaysInfo", null, irbholidayinfo, null);
	}

	// funcions d'actualitzacio del LDAP

	// Obtenen el misstage LDIF segons el tipus d'operacio
	// i preparen la connexió SSH utilitzant el missatge generat

	/**
	 * Metode que actulitza el LDAP quan un personal pasa a ser actiu. Crea l'objecte al LDAP.
	 * 
	 * @param professional: del personal
	 * @throws ValidationFailedException 
	 * @throws InternalException 
	 */
	public static void updateLDAPFromCreate(Professional professional) throws ValidationFailedException, InternalException {

		String ldif = LDIFGenerator.generateCreateLDIF(professional);

		log.debug("----CREATE LDIF START----");
		log.debug(ldif);
		log.debug("----CREATE LDIF END ----");

		if (ldif != null) {
			SSHConnection ssh = new SSHConnection();
			ssh.connectAndAuthenticate();
			ssh.transferLdiffAndExecuteCommand(ldif);
			ssh.connectionClose();
		}
	}

	/**
	 * Metode que actulitza el LDAP quan fem una modificacío del personal. Només actulitza, si algun camp
	 * dels que s'ha de controlar s'ha modificat. 
	 * 
	 * @param old: professional antic (Quan estem modificant el professional)
	 * @param professional: d'on s'obtenen totes les dades 
	 * @param personal: personal amb modificacions (Quan estem modicant les dades basiques del personal)
	 * @param personalPhoto: foto del personal (Quan estem modificant la foto)
	 * @throws ValidationFailedException 
	 * @throws InternalException 
	 */
	public static void updateLDAPFromModify(Professional old, Professional professional, Personal personal, PersonalPhoto personalPhoto) throws ValidationFailedException, InternalException {

		String ldif = LDIFGenerator.generateModifyLDIF(old, professional, personal, personalPhoto);

		log.debug("---- MODIFY LDIF start----");
		log.debug(ldif);
		log.debug("---- MODIFY LDIF END  ----");

		if (ldif != null) {
			SSHConnection ssh = new SSHConnection();
			ssh.connectAndAuthenticate();
			ssh.transferLdiffAndExecuteCommand(ldif);
			ssh.connectionClose();
		}
	}

	/**
	 * Metode que actulitza el LDAP quan un personal es esborrat. Esborra l'objecte al LDAP.
	 * 
	 * @param professional: del personal
	 * @throws InternalException 
	 * @throws ValidationFailedException 
	 */
	public static void updateLDAPFromDelete(Professional professional) throws ValidationFailedException {

		String ldif = LDIFGenerator.generateDeleteLDIF(professional);

		log.debug("---- DELETE LDIF start----");
		log.debug(ldif);
		log.debug("---- DELETE LDIF END  ----");

		if (ldif != null) {
			SSHConnection ssh = new SSHConnection();
			ssh.connectAndAuthenticate();
			ssh.transferLdiffAndExecuteCommand(ldif);
			ssh.connectionClose();
		}
	}

	///////

	/** funcio que retorna el numero de dies entre les dates especificades */
	public static int getDiffDays(Date fechainicio, Date fechafin) {

		BigDecimal elapsed;

		long finicio;
		long ffin;

		finicio = fechainicio.getTime();

		ffin = fechafin.getTime();

		elapsed = new BigDecimal(ffin - finicio).divide(new BigDecimal(1000 * 60 * 60 * 24), RoundingMode.HALF_UP);

		return elapsed.intValue() + 1; //ambos inclusive

	}

	public static int totalDays(String initialdate, String enddate, Locale locale) {
		MultipleDateFormat mdf = new MultipleDateFormat();
		Date in = null, en = null;
		try {
			in = mdf.parse(initialdate, locale);
			en = mdf.parse(enddate, locale);
		}
		catch (Exception e) {
			return -1;
		}
		return getDiffDays(in, en) - UseCase.ObtainFestiusBetweenDates(in, en);

	}

	/** funcio que retorna la data del ultim dia de l'any */
	public static Date getEndYearDate(Calendar year) {
		year.add(java.util.Calendar.YEAR, 1);
		year.add(java.util.Calendar.DAY_OF_YEAR, -1);
		return year.getTime();
	}

	public static Date getIniYear(int year) {
		return new GregorianCalendar(year, 0, 1).getTime();
	}

	public static Date getEndYear(int year) {
		Calendar c = new GregorianCalendar(year + 1, 0, 1);
		c.add(Calendar.DAY_OF_YEAR, -1);
		return c.getTime();
	}

	public static int getNumDaysWeekends(Date initialdate, Date enddate) {

		int total = getDiffDays(initialdate, enddate);
		int totalSD = 0;
		int diaSemInicio = getWeekDayMondayZero(initialdate);

		// Primera versión del algoritmo            
		//            int diaSemFin = getWeekDayMondayZero(enddate);
		//            
		//            if(total < 7)
		//            {
		//                if(diaSemInicio == 6)
		//                {
		//                    totalSD = 1;
		//                }
		//                else
		//                {
		//                    int diaSemFinTotal = diaSemInicio + total - 1;
		//                    
		//                    totalSD = diaSemFinTotal >= 6 ? 2 :
		//                        diaSemFinTotal == 5 ? 1 : 0;
		//                }
		//            }
		//            else
		//            {
		//                totalSD = ((total - (7-diaSemInicio))/7)*2;
		//                if(diaSemInicio<=5) totalSD += 2;
		//                if(diaSemInicio==6) totalSD += 1;
		//                if(diaSemFin==5) totalSD++;
		//            }
		// Fin primera versión del algoritmo

		totalSD = ((total + diaSemInicio) / 7) * 2;
		if (diaSemInicio == 6)
			totalSD--;
		if ((total + diaSemInicio) % 7 == 6)
			totalSD++;

		return totalSD;

	}

	@SuppressWarnings("deprecation")
	public static int getWeekDayMondayZero(Date fecha) {
		return (fecha.getDay() + 6) % 7;
	}

	public static void sendEndOfContractInstructions(Personal currentPersonal) {

		String fileName = MAINCONFIG.getString("endOfContractInstructionsFileName");

		MailController.singleton().PrepareMail("EndOfContractInstructions", new MailEndOfContractInstructions.MailData(currentPersonal, fileName), HibernateUtil.getSession());

	}

	@SuppressWarnings("deprecation")
	public static int getFestivosEntreFechas(java.util.Date start, java.util.Date end, List<Irbholiday> festivos) {
		int totalFestivos = 0;
		for (Irbholiday h : festivos) {
			if (start.compareTo(h.getInitialdate()) > 0 || end.compareTo(h.getEnddate()) < 0)
				continue;
			int diaSemana = h.getInitialdate().getDay();
			if (diaSemana != 0 && diaSemana != 6) {
				totalFestivos++;
			}
		}
		return totalFestivos;
	}

	public static int diasLaborables(Date start, Date end, List<Irbholiday> festivos) {
		int totalDias = UseCaseUtils.getDiffDays(start, end);
		if (totalDias <= 0)
			return 0;
		int totalFinesDeSemana = UseCaseUtils.getNumDaysWeekends(start, end);
		int totalFestivos = UseCaseUtils.getFestivosEntreFechas(start, end, festivos);

		return totalDias - totalFinesDeSemana - totalFestivos;
	}

	public static int diasLaborablesAnno(Date start, Date end, List<Irbholiday> festivos, int anno) {
		Date[] newDates = limitDateRangeToYear(new Date[] { start, end }, anno);

		Date startDate = newDates[0];
		Date endDate = newDates[1];

		return diasLaborables(startDate, endDate, festivos);
	}

	@SuppressWarnings("deprecation")
	public static Date[] limitDateRangeToYear(Date[] range, int anno) {
		Date start = range[0];
		Date end = range[1];

		if (start == null)
			start = (new GregorianCalendar(1900, 0, 1)).getTime();
		if (end == null)
			end = (new GregorianCalendar(2100, 0, 1)).getTime();

		int startAnno = start.getYear() + 1900;
		int endAnno = end.getYear() + 1900;

		if ((startAnno < anno && endAnno < anno) || (startAnno > anno && endAnno > anno)) {
			return (new Date[] { null, null });
		};

		if (startAnno < anno && endAnno >= anno)
			start = (new GregorianCalendar(anno, 0, 1)).getTime();
		if (endAnno > anno && startAnno <= anno)
			end = (new GregorianCalendar(anno, 11, 31)).getTime();

		return new Date[] { start, end };

	}

	public static int calculateHolidaysForYear(Date[] dateRange) {

		Float days = new Float(((float) getDiffDays(dateRange[0], dateRange[1])) * 0.0630136);

		//		int daysInt = days.intValue();
		//
		//		if (days.compareTo(new Float(daysInt)) > 0)
		//			return daysInt + 1;

		return Math.round(days);
	}

	public static int calculateApsForYear(Date[] dateRange) {

		Float days = new Float(((float) getDiffDays(dateRange[0], dateRange[1])) * 0.0246575);

		//		int daysInt = days.intValue();
		//
		//		if (days.compareTo(new Float(daysInt)) > 0)
		//			return daysInt + 1;

		return Math.round(days);
	}

	public static boolean dateRangesOverlap(Date[] dateRangeA, Date[] dateRangeB) {
		return (dateRangeB[0].compareTo(dateRangeA[1]) <= 0 && dateRangeB[1].compareTo(dateRangeA[1]) >= 0) || (dateRangeA[0].compareTo(dateRangeB[1]) <= 0 && dateRangeA[1].compareTo(dateRangeB[1]) >= 0);
	}

	public static Date[] mergeDateRanges(Date[] dateRangeA, Date[] dateRangeB) {
		return new Date[] { dateRangeA[0].compareTo(dateRangeB[0]) <= 0 ? dateRangeA[0] : dateRangeB[0], dateRangeA[1].compareTo(dateRangeB[1]) >= 0 ? dateRangeA[1] : dateRangeB[1] };
	}

}
