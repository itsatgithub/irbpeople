package presentation.actions.oPersonalSys;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import presentation.formbeans.oPersonalSys.Action_modify_page_personal_Form;
import presentation.formbeans.oPersonalSys.Personal_comments_container;
import presentation.formbeans.objects.Personal_comment_Form;
import utils.Pair;
import utils.actions.BOAdderFunctions;
import utils.filter.ListConfigurator;
import utils.formbeans.BOAdderFormBean;
import utils.formbeans.FormBeanManager;

import bussineslogic.controlers.UseCaseFacade;
import bussineslogic.excepciones.InternalException;
import bussineslogic.excepciones.NoPermisosException;
import bussineslogic.objects.Personal_comment;

public class Personal_comments_methods {
    public static List<Personal_comment> process_personal_comments(
            BOAdderFormBean<Personal_comment_Form> boAdderFormBean2,
            FormBeanManager fbManager,
            String personalcode, String usercode) throws InternalException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException, NoPermisosException
    {
        
        List<Personal_comment> boAdderList2 = null;
        try {
        
            boAdderList2 = fbManager.getPOJOBOAdderList("personal_comments");

        } catch (NullPointerException e) {}
        
        if ( boAdderFormBean2.getOperation().equals(BOAdderFormBean.LOAD)) {

            Pair<Integer,List<Personal_comment>> listPersonal_comments = 
                UseCaseFacade.ObtainAllIpersonal_commentsFromPersonal(usercode, 
                        personalcode, new ListConfigurator());

            boAdderList2 = listPersonal_comments.getSecond();

            boAdderFormBean2.getNewFormBean().getPersonal_comment().getPersonal().setPersonalcode(personalcode);

        } else if(boAdderFormBean2.getOperation().equals(BOAdderFormBean.ADD) && boAdderList2!=null) {

            boAdderList2 = fbManager.getPOJOBOAdderList("personal_comments");

            Personal_comment newPersonal_commentTO = (Personal_comment) fbManager.getPOJOBOAdderNew("personal_comments", Personal_comment.class);

            newPersonal_commentTO.setCode(BOAdderFunctions.generateCode());

            newPersonal_commentTO.setPersonal(UseCaseFacade.ObtainPersonal(usercode, boAdderFormBean2.getNewFormBean().getPersonal_comment().getPersonal().getPersonalcode()));

            boAdderList2.add(0, UseCaseFacade.CreatePersonal_comment(usercode, newPersonal_commentTO));
             
            boAdderFormBean2.setNewFormBean(new Personal_comment_Form());

            boAdderFormBean2.getNewFormBean().getPersonal_comment().getPersonal().setPersonalcode(personalcode);
            
        } else if(boAdderFormBean2.getOperation().equals(BOAdderFormBean.DELETE) && boAdderList2!=null){

            boAdderList2 = fbManager.getPOJOBOAdderList("personal_comments");

            for (Personal_comment to_delete : boAdderList2) {
                if(to_delete.getCode().equals(boAdderFormBean2.getRemoveCode())) {
                    boAdderList2.remove(to_delete);
                    UseCaseFacade.RemovePersonal_comment(usercode, to_delete.getPersonal_commentcode());
                    break;
                }
            }
        }
        else
        {
            Pair<Integer,List<Personal_comment>> listPersonal_comments = 
                UseCaseFacade.ObtainAllIpersonal_commentsFromPersonal(usercode, 
                        personalcode, new ListConfigurator());

            boAdderList2 = listPersonal_comments.getSecond();

            boAdderFormBean2.getNewFormBean().getPersonal_comment().getPersonal().setPersonalcode(personalcode);
        }
        
        return boAdderList2;
        
    }
    
    public static void put_personal_comments(BOAdderFormBean<Personal_comment_Form> boAdderFormBean2,FormBeanManager fbManager,
            List<Personal_comment> boAdderList2, HttpServletRequest request)
        throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException
    {
        fbManager.setBOAdderList(boAdderList2, "personal_comments",
                Personal_comment_Form.class);

        fbManager.setBOAdderNew("personal_comments", boAdderFormBean2
                .getNewFormBean());

        request.setAttribute("viewlistElements2",
                ((Personal_comments_container) fbManager.getContainer())
                .getPersonal_comments().getFormBeanList());
    }
}
