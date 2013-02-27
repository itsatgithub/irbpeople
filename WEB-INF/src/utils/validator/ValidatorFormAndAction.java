package utils.validator;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.validator.Validator;
import org.apache.commons.validator.ValidatorException;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.Resources;
import org.apache.struts.validator.ValidatorForm;

/**
 * Extension of ValidatorForm to enable validation by action in the validation.xml
 * @author Automatika - JustInMind SL
 */
public class ValidatorFormAndAction extends ValidatorForm{
	
    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getLog(DynaValidatorFormAndAction.class);

    public String getValidationKey2(ActionMapping mapping,
                                   HttpServletRequest request) {
        return mapping.getPath();
    }
    
    /*
     * (non-Javadoc)
     * @see org.apache.struts.validator.ValidatorForm#validate(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
    	ActionErrors errors=super.validate(mapping, request);

        

        String validationKey = getValidationKey2(mapping, request);
        ServletContext application = getServlet().getServletContext();
        Validator validator = Resources.initValidator(validationKey,
                             this,
                             application, request,
                             errors, getPage());

        try {
        	if(getValidatorResults()==null){
        		setValidatorResults(validator.validate());
        	} else 
        		getValidatorResults().merge(validator.validate());
        } catch (ValidatorException e) {
            log.error(e.getMessage(), e);
        }

        return errors;
    }
}
