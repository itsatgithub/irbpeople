package presentation.actions.other;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Collections;
import java.util.Map;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import utils.formbeans.ToStringComparator;
import utils.actions.NavigationFunctions;
import utils.actions.BOAdderFunctions;
import utils.formbeans.FormBeanManager;
import utils.formbeans.BOAdderFormBean;
import utils.userUtils.UserUtils;
import utils.Pair;
import utils.listFilter.ViewListConfiguration;
import utils.beanUtils.ExtendedBeanUtils;
import utils.filter.ListConfigurator;
import utils.hibernate.HibernateUtil;

import bussineslogic.controlers.UseCaseFacade;
import bussineslogic.excepciones.ValidationFailedException;

import bussineslogic.objects.*;
import presentation.formbeans.objects.*;

import presentation.formbeans.other.*;


/**
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class Action_Show_Viewer extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
    throws Exception {    	    	    

        /** 1.  We obtain the user Information */
        String usercode = UserUtils.getCurrentUsuarioId(request); 
        Locale locale = UserUtils.getCurrentLocale(request); 




        /** 2.  We obtain the initial form bean and we put it to a new FormBeanManager. */

        Action_Show_Viewer_Form action_Show_Viewer_Form=(Action_Show_Viewer_Form) form;

        FormBeanManager fbManager=new FormBeanManager(request, action_Show_Viewer_Form);			

        /** 3.  We clean the current container */		
        fbManager.cleanContainer();

        /** 4.  We return to the correct page (introducing some values to the request if necessary) */


        /** 4.1.  We look if the current page is beeing opened in a popup window. */
        boolean isPopUp = NavigationFunctions.isPopUpWindow(request);
        /** 4.2.  We look for the forward to execute. */
        Map<String, List<String>> result=new Hashtable<String, List<String>>();

        try {
            String report = request.getParameter("selReport");

            JasperReport jr = (JasperReport)JRLoader.loadObject(servlet.getServletContext().getRealPath("/WEB-INF/reports/")+ "/" + report +".jasper");

            JRParameter[] params=jr.getParameters();

            HashMap hm = new HashMap();

            Action_reports.treatParams(request, result, hm, params);

            if(!result.isEmpty()){
                throw new ValidationFailedException(result);
            }

            JasperPrint print;
            print = JasperFillManager.fillReport(
                    servlet.getServletContext().getRealPath("/WEB-INF/reports/")+ "/" + report + ".jasper",
                    hm, HibernateUtil.getSession().connection()
            );

            JRExporter exporter = new net.sf.jasperreports.engine.export.JRHtmlExporter();

            exporter.setParameter(
                    JRExporterParameter.OUTPUT_STREAM,
                    response.getOutputStream());
            exporter.setParameter(
                    JRExporterParameter.JASPER_PRINT,print)
                    ;
            exporter.exportReport();
            
        } catch (net.sf.jasperreports.engine.JRException e) {

            e.printStackTrace();
        }

        return null;


    }
}