package presentation.actions.oCalendarSys;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.formbeans.oCalendarSys.Action_manage_personal_calendar_Form;
import presentation.formbeans.objects.Irbholiday_Form;
import presentation.formbeans.objects.Irbholidayinfo_Form;
import uicomponents.scheduler.SchedulerFacadeForCommonControlsScheduler;
import utils.Pair;
import utils.actions.NavigationFunctions;
import utils.beanUtils.ExtendedBeanUtils;
import utils.formbeans.FormBeanManager;
import utils.listFilter.ViewListConfiguration;
import utils.userUtils.UserUtils;
import bussineslogic.controlers.UseCaseFacade;
import bussineslogic.controlers.UseCaseUtils;
import bussineslogic.objects.Irbholiday;
import bussineslogic.objects.Irbholidayinfo;
import bussineslogic.objects.Personal;

/**
 * 
 * @author Factory - JustInMind SL
 * 
 */
public class Action_manage_personal_calendar extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        /** 1. We obtain the user Information */
        String usercode = UserUtils.getCurrentUsuarioId(request);
        Locale locale = UserUtils.getCurrentLocale(request);

        /**
         * 2. We set this page as a input point (see
         * NavigationFunctions.setInputPoint function for more info).
         */
        NavigationFunctions.setReturnPoint(request, mapping.getPath());

        Action_manage_personal_calendar_Form action_manage_personal_calendar_Form = (Action_manage_personal_calendar_Form) form;
        
        FormBeanManager fbManager = new FormBeanManager(request, action_manage_personal_calendar_Form);

        if (action_manage_personal_calendar_Form.getPersonalcode() == null) {
            Personal personal = UseCaseFacade.ObtainPersonalFromIrbholidayinfo(usercode,
                    action_manage_personal_calendar_Form.getIrbholidayinfocode());
            action_manage_personal_calendar_Form.setPersonalcode(personal.getPersonalcode());
        }
        
        String s = (String) request.getSession().getAttribute("year");
        int yearCal = SchedulerFacadeForCommonControlsScheduler.getViewYear(request);
        
        Irbholidayinfo irbholidayinfo=null;
        
        String schedAction = request.getParameter("action");
        if(schedAction!=null && schedAction.equals("ChangeDate"))
        {
            if(request.getParameter("param")!=null)
            {
                GregorianCalendar newYear = new GregorianCalendar();
                newYear.setTimeInMillis(Long.valueOf(request.getParameter("param")).longValue());
                
                
                
                irbholidayinfo = UseCaseFacade.ObtainIrbholidayinfoFromPersonal(usercode,
                        action_manage_personal_calendar_Form.getPersonalcode(), newYear);
                
                if(irbholidayinfo==null)
                {
                    yearCal = Integer.parseInt(s);
                    irbholidayinfo = UseCaseFacade.ObtainIrbholidayinfoFromPersonal(usercode,
                            action_manage_personal_calendar_Form.getPersonalcode(), new GregorianCalendar(yearCal, 0, 1));
                    
                    request.setAttribute("theScheduler", "sched_" + mapping.getPath());
                }
                else
                {
                    yearCal=newYear.get(Calendar.YEAR);
                    SchedulerFacadeForCommonControlsScheduler.facadePrepareScheduler(request, "sched_" + mapping.getPath(), null,
                            yearCal);
                    
                    irbholidayinfo = UseCaseFacade.ObtainIrbholidayinfoFromPersonal(usercode,
                            action_manage_personal_calendar_Form.getPersonalcode(), newYear);
                    request.setAttribute("theScheduler", "sched_" + mapping.getPath());
                }
                
            }
        }
        else
        {
            SchedulerFacadeForCommonControlsScheduler.facadePrepareScheduler(request, "sched_" + mapping.getPath(), null,
                    s != null && !s.equals("") ? Integer.parseInt(s) : Calendar.getInstance().get(Calendar.YEAR));
            
            irbholidayinfo = UseCaseFacade.ObtainIrbholidayinfoFromPersonal(usercode,
                    action_manage_personal_calendar_Form.getPersonalcode(), new GregorianCalendar(yearCal, 0, 1));
        }

        /**
         * 3.2. We add the values of the the view-lists (if any) to the request.
         */

        /** 3.2.1. We obtain the current ViewListConfiguration from the request */
        ViewListConfiguration filter = (ViewListConfiguration) request.getAttribute("viewListConfiguration");

        filter.getList_config_orderby().setAttribute("initialdate");
        filter.getList_config_orderby().setAsc("asc");

        
        
        /** 3.2.2. We obtain the list of objects form the Business logic */

        /** obtenir els dies marcats **/

        // Ara hem d'agafar els dies per a l'usuari seleccionat
        Pair<Integer, List<Irbholiday>> _list_irbholiday = UseCaseFacade.ObtainAllIIrbholidayFromPersonal(usercode,
                action_manage_personal_calendar_Form.getPersonalcode(), new GregorianCalendar(yearCal, 0, 1), filter
                        .obtainListConfigurator(locale, false));

        Pair<Integer, List<Irbholiday>> _list_festiu = UseCaseFacade.obtainIrbholidayFestiu(usercode, yearCal, filter
                .obtainListConfigurator(locale, false));

        

        
        
        Irbholiday limit = UseCaseFacade.ObtainIrbholidayLimitVacances(usercode, new GregorianCalendar(yearCal, 0, 1));

        GregorianCalendar temp = new GregorianCalendar();
        GregorianCalendar today = new GregorianCalendar(temp.get(Calendar.YEAR), temp.get(Calendar.MONTH), temp.get(Calendar.DAY_OF_MONTH));
        
        if(today.getTime().compareTo(limit.getInitialdate()) > 0)
        {
            request.setAttribute("pastLimit", "true");
        }
        
        fbManager.cleanContainer();

        fbManager = new FormBeanManager(request, Action_manage_personal_calendar_Form.class);

        /**
         * 6.2. We add the current POJO merged with the nested-pojo-form-bean to
         * the manager.
         */

        fbManager.setAttribute(irbholidayinfo, "irbholidayinfo_Form", Irbholidayinfo_Form.class);

        /** 6.3. We add the container of the manager to the request. */

        request.setAttribute("oCalendarSys__action_manage_personal_calendar_Form", fbManager.getContainer());

        /** 3.2.3. We obtain copy the list of pojos to a list of FormBeans */

        List<Irbholiday_Form> viewlistElements = ExtendedBeanUtils.copyPropertiesToFormBean(_list_irbholiday
                .getSecond(), locale, Irbholiday_Form.class);

        List<Irbholiday_Form> festius = ExtendedBeanUtils.copyPropertiesToFormBean(_list_festiu.getSecond(), locale,
                Irbholiday_Form.class);

        if (limit != null) {

            Irbholiday_Form limit_form = new Irbholiday_Form();

            ExtendedBeanUtils.copyPropertiesToFormBean(limit_form, limit, locale);

            // afegim el dia limit de les vacances del anya anteriror
            festius.add(limit_form);
        }

        // afegim dies festius per mostrar al calendari
        festius.addAll(viewlistElements);

        /** 3.2.4. Load data in the calendar. */
        SchedulerFacadeForCommonControlsScheduler.facadeLoadAppointments(request, festius,
                "sched_" + mapping.getPath(), null, request.getLocale());

        // changes codes to descriptions
        for (Irbholiday_Form i : viewlistElements) {
            i.setType(Irbholiday_Form.getType(i.getType(), locale));
            i.setStatus(Irbholiday_Form.getStatus(i.getStatus(), locale));
            i.setTotal("" + UseCaseUtils.totalDays(i.getInitialdate(), i.getEnddate(), locale));
        }

        request.setAttribute("viewlistElements", viewlistElements);

        /**
         * 3.3. We add the values of popup-selectables (if any) to the request:
         * we need to put the value of the to-string method, because the
         * FormBean may only store be the code
         */

        /** 4. We navigate to the correct page. */

        return NavigationFunctions.findForward(request, mapping, "success");

    }

}