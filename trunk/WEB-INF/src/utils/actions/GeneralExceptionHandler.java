package utils.actions;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ExceptionHandler;
import org.hibernate.StaleObjectStateException;

public class GeneralExceptionHandler extends ExceptionHandler{
	private static final Log log = LogFactory.getLog(GeneralExceptionHandler.class);
	
	@Override
	protected void logException(Exception e) {
		if(e.getCause() instanceof StaleObjectStateException){
    		log.info(e);
    		return;
    	}
		
		log.error(e, e);
		
	}

}
