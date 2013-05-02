package presentation.actions.personalphoto;

import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import utils.userUtils.UserUtils;

import bussineslogic.controlers.UseCaseFacade;
import bussineslogic.objects.PersonalPhoto;

public class GetImageAction extends Action{

	public static final String NO_PHOTO="no-photo";
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String usercode = UserUtils.getCurrentUsuarioId(request); 
		
		String contentType="image/gif";
		
		String id=request.getParameter("personalPhotocode");
		if(id==null || id.equals(NO_PHOTO)){
			id="0000000000001";
		}
		
		PersonalPhoto photo=UseCaseFacade.obtainPersonalPhoto(usercode, id);

		
//		response.setContentType(contentType);
		if(photo!=null){
			OutputStream os=response.getOutputStream();
			os.write(photo.getPhoto());
		}
		
		return null;
	}
	

}
