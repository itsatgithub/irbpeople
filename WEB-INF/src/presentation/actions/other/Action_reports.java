package presentation.actions.other;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Collections;
import java.util.Map;
import java.util.Vector;

import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.justinmind.helper.composition.common.types.TypesManager;
import com.justinmind.helper.composition.report.ReportComObject;
import com.justinmind.helper.composition.report.ReportComposition;
import com.justinmind.helper.composition.report.ReportCompositionFactory;

import utils.formbeans.ToStringComparator;
import utils.actions.NavigationFunctions;
import utils.actions.BOAdderFunctions;
import utils.formbeans.FormBeanManager;
import utils.formbeans.BOAdderFormBean;
import utils.userUtils.UserUtils;
import utils.Glob;
import utils.Pair;
import utils.listFilter.ViewListConfiguration;
import utils.beanUtils.ExtendedBeanUtils;
import utils.beanUtils.converters.DateConverter;
import utils.beanUtils.converters.NumberConverter;
import utils.filter.ListConfigurator;

//import bsh.Interpreter;
import bussineslogic.controlers.UseCaseFacade;

import bussineslogic.objects.*;
import presentation.formbeans.objects.*;

import presentation.formbeans.other.*;



/**
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class Action_reports extends Action {
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {    	    	    

    	/** 1.  We obtain the user Information */
    	String usercode = UserUtils.getCurrentUsuarioId(request); 
    	Locale locale = UserUtils.getCurrentLocale(request); 
    	


			
		/** 2.  We set this page as a input point (see NavigationFunctions.setInputPoint function for more info). */
		NavigationFunctions.setReturnPoint(request, mapping.getPath());
		
		/** 3.  We Prepare the values that will be used in the next jsp */
		/** 3.1.  We add the values of all (if any) of the support tables which will be used in the jsp (for selectables) to the request. */
		
		
		
		/** 3.2.  We add the values of the the view-lists (if any) to the request. */			
		
		
		
		
		
		
		/** 3.3.  We add the values of popup-selectables (if any) to the request: we need to put the value of the to-string method, because the FormBean may only store be the code */
		
		

		/** 4.  We navigate to the correct page. */		
		
        String[] dir = new java.io.File(servlet.getServletContext().getRealPath("/WEB-INF/reports/")).list(new Glob("*.jrxml") ); 

        java.util.Arrays.sort(dir);        

       


        
//        TypesManager typesManager = new TypesManager(servlet.getServletContext().getRealPath("/WEB-INF/types"),
//                servlet.getServletContext().getRealPath("/WEB-INF/operators"));
//        
//        ReportCompositionFactory rcf = new ReportCompositionFactory();
//        
//        ReportComposition rc = 
//            rcf.createReportComposition("reports",
//                servlet.getServletContext().getRealPath("/WEB-INF/reports/parameters"),
//                typesManager);
//        
//        Vector reports=rc.getReportsByCategory("maincat","reports");
        
        Action_reports_Form af = new Action_reports_Form();
        
        ArrayList<String> reportList = new ArrayList();
        
        for(String r: dir)
        {
            reportList.add(r.substring(0, r.length()-6) ); // quitamos los caracteres finales (.jrxml)
        }
        
        String report=((Action_reports_Form)form).getSelReport();
        if(report==null || report.equals("")) report=reportList.get(0);

        ArrayList paramList = new ArrayList();
        
        try
        {
            JasperCompileManager.compileReportToFile(servlet.getServletContext().getRealPath("/WEB-INF/reports/")+ "/" + report +".jrxml");

            JasperReport jr = (JasperReport)JRLoader.loadObject(servlet.getServletContext().getRealPath("/WEB-INF/reports/")+ "/" + report +".jasper");

            JRParameter[] params=jr.getParameters();

            paramList = new ArrayList();

            for(JRParameter par: params)
            {
                if(!par.isSystemDefined())
                {

                    paramList.add(new Object[]{ par.getName(), par.getDescription()==null ? par.getName() : par.getDescription(),par.getValueClassName()});

//                  Interpreter i = new Interpreter();  

//                  Object defaultValue = i.eval(par.getDefaultValueExpression().getText());
                }
            }
        }
        catch(Exception e){}
        
        af.setReports(reportList);
        af.setReportValues(reportList);
        af.setSelReport(report);
        
        request.setAttribute("other__action_reports_Form", af);
        request.setAttribute("parameters", paramList);
        
		return NavigationFunctions.findForward(request, mapping, "success");
		
		
			}
    
    public static void treatParams(HttpServletRequest request, Map<String, List<String>> result, HashMap hm, JRParameter[] params) throws Exception
    {
        for(JRParameter par: params)
        {
            if(!par.isSystemDefined())
            {
                String requestParamValue = request.getParameter("param_" + par.getName());

                if(requestParamValue!=null && !requestParamValue.equals(""))
                {
                    Class parClass = Class.forName(par.getValueClassName());

                    if(parClass.equals(java.util.Date.class))
                    {
                        Object value = (new DateConverter(UserUtils.getCurrentLocale(request))).convert(null, requestParamValue);
                        if(value!=null)
                        {
                            hm.put(par.getName(), value);
                        }
                        else
                        {
                            List<String> errors=new ArrayList<String>();
                            errors.add(par.getDescription()==null ? par.getName() : par.getDescription());

                            result.put("errors.date", errors);
                        }
                    }
                    else if(parClass.equals(String.class))
                    {
                        hm.put(par.getName(),requestParamValue);
                    }
                    else if(parClass.equals(Double.class) || parClass.equals(Float.class) || parClass.equals(BigDecimal.class))
                    {
                        Object value = (new NumberConverter(UserUtils.getCurrentLocale(request))).convert(BigDecimal.class, requestParamValue);

                        if(value!=null)
                        {
                            Double doubleValue = ((BigDecimal)value).doubleValue();

                            hm.put(par.getName(), parClass.getConstructor(double.class).newInstance(doubleValue));
                        }
                        else
                        {
                            List<String> errors=new ArrayList<String>();
                            errors.add(par.getDescription()==null ? par.getName() : par.getDescription());

                            result.put("errors.localebigdecimal", errors);
                        }
                    }
                    else if(parClass.equals(Long.class) || parClass.equals(Short.class) || parClass.equals(Integer.class))
                    {
                        Object value = (new NumberConverter(UserUtils.getCurrentLocale(request))).convert(Integer.class, requestParamValue);

                        if(value!=null)
                        {
                            hm.put(par.getName(), parClass.getConstructor(long.class).newInstance(value));
                        }
                        else
                        {
                            List<String> errors=new ArrayList<String>();
                            errors.add(par.getDescription()==null ? par.getName() : par.getDescription());

                            result.put("errors.localebigdecimal", errors);
                        }
                    }
                }
            }
        }
    }
}