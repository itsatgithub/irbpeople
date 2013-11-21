package presentation.actions.oAlumni_irb_jobsSys;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.formbeans.oAlumni_irb_jobsSys.Action_add_alumni_irb_jobs_Form;
import presentation.formbeans.objects.Alumni_irb_job_positions_Form;
import presentation.formbeans.objects.Alumni_irb_jobs_Form;
import presentation.formbeans.objects.Unit_Form;
import utils.Pair;
import utils.actions.NavigationFunctions;
import utils.beanUtils.ExtendedBeanUtils;
import utils.filter.ListConfigurator;
import utils.formbeans.FormBeanManager;
import utils.formbeans.ToStringComparator;
import utils.userUtils.UserUtils;
import bussineslogic.controlers.UseCaseFacade;
import bussineslogic.objects.Alumni_irb_job_positions;
import bussineslogic.objects.Alumni_irb_jobs;
import bussineslogic.objects.Country;
import bussineslogic.objects.Unit;

/**
 * 
 * @author Automatika - JustInMind SL
 * 
 */
public class Action_add_alumni_irb_jobs extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		/** 1. We obtain the user Information */
		String usercode = UserUtils.getCurrentUsuarioId(request);
		Locale locale = UserUtils.getCurrentLocale(request);

		/**
		 * 2. We set this page as a input point (see
		 * NavigationFunctions.setInputPoint function for more info).
		 */
		NavigationFunctions.setInputPoint(request, mapping.getPath());

		/**
		 * 3. We obtain the initial form bean and we put it to a new
		 * FormBeanManager.
		 */

		Action_add_alumni_irb_jobs_Form action_add_alumni_irb_jobs_Form = (Action_add_alumni_irb_jobs_Form) form;

		FormBeanManager fbManager = new FormBeanManager(request, action_add_alumni_irb_jobs_Form);

		/** 4. We obtain the initial POJO. */

		Alumni_irb_jobs alumni_irb_jobs = null;

		/**
		 * 5. In case a BOAdder exists in the following page, we reed the info
		 * from the BOAdder.
		 */

		/** 6. We Prepare the values that will be used in the next jsp */
		/** 6.1. We create a new FormBeanManager (and delete the old one) */

		fbManager.cleanContainer();

		fbManager = new FormBeanManager(request, Action_add_alumni_irb_jobs_Form.class);

		/**
		 * 6.2. We add the current POJO merged with the nested-pojo-form-bean to
		 * the manager.
		 */

		fbManager.setAttribute(alumni_irb_jobs, "alumni_irb_jobs_Form", Alumni_irb_jobs_Form.class, action_add_alumni_irb_jobs_Form.getAlumni_irb_jobs_Form());

		/** 6.3. We add the container of the manager to the request. */

		request.setAttribute("oAlumni_irb_jobsSys__action_add_alumni_irb_jobs_Form", fbManager.getContainer());

		/**
		 * 6.4. We add the values of all (if any) of the support tables which
		 * will be used in the jsp (for selectables) to the request.
		 */

		/** 6.4.5. We obtain the values form the business logic. */

		Pair<Integer, List<Alumni_irb_job_positions>> _selec_alumni_irb_job_positions = UseCaseFacade.ObtainAllAlumni_irb_job_positions(usercode, new ListConfigurator());
		List<Country> _selec_alumni_irb_job_positions_Form = ExtendedBeanUtils.copyPropertiesToFormBean(_selec_alumni_irb_job_positions.getSecond(), locale, Alumni_irb_job_positions_Form.class);		
		request.setAttribute("_selec_alumni_irb_job_positions", _selec_alumni_irb_job_positions_Form);

		Pair<Integer, List<Unit>> _selec_unit = UseCaseFacade.ObtainAllUnit(usercode, new ListConfigurator());
		List<Unit> _selec_unit_Form = ExtendedBeanUtils.copyPropertiesToFormBean(_selec_unit.getSecond(), locale, Unit_Form.class);
		Collections.sort(_selec_unit_Form, new ToStringComparator());
		request.setAttribute("_selec_unit", _selec_unit_Form);
		

		/**
		 * 6.5. We add the values of the the view-lists (if any) to the request.
		 */

		/** 6.6. We add the values of the BOAdder (if any) to the request. */

		/**
		 * 6.7. We add the values of popup-selectables (if any) to the request:
		 * we need to put the value of the to-string method, because the
		 * FormBean may only store be the code
		 */

		/** 7. We navigate to the correct page. */

		return NavigationFunctions.findForward(request, mapping, "success");

	}
}