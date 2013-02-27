package presentation.actions.oReportSys;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Collections;
import java.util.Map;

import net.sf.jasperreports.engine.JasperCompileManager;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

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

import bussineslogic.controlers.UseCaseFacade;
import bussineslogic.excepciones.ValidationFailedException;

import bussineslogic.objects.*;
import presentation.formbeans.objects.*;
import presentation.formbeans.other.Action_reportfile_Form;

import presentation.formbeans.oReportSys.*;


/**
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class Action_new_new_page_report extends Action {
    
//    @Override
//    public ActionForward execute(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {    	    	    
//
//    	/** 1.  We obtain the user Information */
//    	String usercode = UserUtils.getCurrentUsuarioId(request); 
//    	Locale locale = UserUtils.getCurrentLocale(request); 
//    	
//
//
//	
//	/** 2.  We obtain the initial form bean and we put it to a new FormBeanManager. */
//	
//Action_new_new_page_report_Form action_new_new_page_report_Form=(Action_new_new_page_report_Form) form;
//
//FormBeanManager fbManager=new FormBeanManager(request, action_new_new_page_report_Form);	
//	
//	/** 3.  We use the business logic to create the new item */
//	
//	Report report = UseCaseFacade.CreateReport(usercode,(Report) fbManager.getPOJO("report_Form",Report.class));	
//	
//	/** 4.  In case that a BOAdder exists in the previous jsp, we update the list of values */
//	
//	
//
//	/** 5.  We clean the current container */
//	fbManager.cleanContainer();
//
//	/** 6.  We return to the correct page (introducing some values to the request if necessary) */
//	
//	
///** 6.1.  We look if the current page is beeing opened in a popup window. */
//boolean isPopUp = NavigationFunctions.isPopUpWindow(request);
///** 6.2.  We look for the forward to execute (we put the current object in case it is used in the next page) */
//
//return NavigationFunctions.findForward(request, mapping, "success", "reportcode="+report.getReportcode(), isPopUp, report.getCode(), report.toString());
//	
//	
//		}
    
    @Override
    public ActionForward execute(ActionMapping arg0, ActionForm arg1, HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {
        
        Action_new_new_page_report_Form reportfile_Form = (Action_new_new_page_report_Form) arg1;
        
        Map<String, List<String>> result=new Hashtable<String, List<String>>();
        
        if(reportfile_Form!=null)
        {
            FormFile reportFile = reportfile_Form.getReport_Form().getFilename();
            if(reportFile!=null && reportFile.getFileName()!=null && !reportFile.getFileName().trim().equals(""))
            {
                if(reportFile.getFileName().endsWith(".jrxml"))
                {
                    byte[] bytes=reportFile.getFileData();
                    String savePath= getServlet().getServletContext().getRealPath("/WEB-INF/reports");
                    String fileName= reportFile.getFileName();
                    
                    try
                    {
                        JasperCompileManager.compileReport(reportFile.getInputStream());

                        File uploadedFile=new File(savePath, fileName);
                        RandomAccessFile raf=
                            new RandomAccessFile(uploadedFile, "rw");
                        raf.write(bytes, 0, bytes.length - 2);
                        raf.close();                    
                    
                    }
                    catch(net.sf.jasperreports.engine.JRException e)
                    {
                        List<String> errors=new ArrayList<String>();
                        errors.add("Filename");

                        result.put("errors.invalid_reportfile", errors);                        
                    }
                    catch(Exception e)
                    {
                        List<String> errors=new ArrayList<String>();
                        errors.add("Filename");

                        result.put("errors.saving_report", errors); 
                    }

                }
                else
                {
                    List<String> errors=new ArrayList<String>();
                    errors.add("Filename");

                    result.put("errors.not_report_filetype", errors);
                }
            }
            else
            {
                List<String> errors=new ArrayList<String>();
                errors.add("Filename");

                result.put("errors.required", errors);
            }
        }
        
        if(!result.isEmpty()){
            throw new ValidationFailedException(result);
        }
        
        boolean isPopUp = NavigationFunctions.isPopUpWindow(arg2);
        /** 6.2.  We look for the forward to execute (we put the current object in case it is used in the next page) */

        return NavigationFunctions.findForward(arg2, arg0, "success", isPopUp);
            
    }
}