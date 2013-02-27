package presentation.actions.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import utils.userUtils.UserUtils;
import bussineslogic.controlers.UseCase;
import bussineslogic.controlers.UseCaseFacade;
import bussineslogic.objects.Usuario;

import com.cc.framework.security.SecurityUtil;
import com.cc.framework.ui.painter.PainterFactory;

public class DoLogout extends Action {

    private static org.apache.log4j.Category log = org.apache.log4j.Logger.getLogger(DoLogout.class);
    
    @Override
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
        try
        {
            Usuario userThatIsLoggingOut = UserUtils.getCurrentUsuario(request);
        
            UserUtils.removeCurrentUsuario(request);


            UserUtils.addSessionSavedAuditMessage(request, "info.sesion-finalizada");


            PainterFactory.resetSessionPainter(request.getSession());
            SecurityUtil.unregisterPrincipal(request.getSession());
            
            if(userThatIsLoggingOut != null)
            {
                UseCaseFacade.HacerLogout(userThatIsLoggingOut, request.getRemoteHost(), "Logout");
            }
        }
        catch(Exception e)
        {
            log.error("Error logging out", e);
        }
        
        
       	return mapping.findForward("success");
    }
    
}