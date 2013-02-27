package utils.validator;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.validator.Validator;
import org.apache.commons.validator.ValidatorException;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;
import org.apache.struts.validator.Resources;

/**
 * Extension of DynaValidatorForm to enable validation by action in the validation.xml
 * 
 * @author Automatika - JustInMind SL
 */
public class DynaValidatorFormAndAction extends DynaValidatorForm{
    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getLog(DynaValidatorFormAndAction.class);
	
	   /**
     * Returns the Validation key.
     *
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     * @return validation key - the action element's 'path' attribute in this case
     */
    public String getValidationKey2(ActionMapping mapping,
                                   HttpServletRequest request) {

        return mapping.getPath();
    }
    
    /**
     * Validate the properties that have been set from this HTTP request,
     * and return an <code>ActionErrors</code> object that encapsulates any
     * validation errors that have been found.  If no errors are found, return
     * <code>null</code> or an <code>ActionErrors</code> object with no
     * recorded error messages.
     *
     * @param mapping The mapping used to select this instance.
     * @param request The servlet request we are processing.
     * @return <code>ActionErrors</code> object that encapsulates any validation errors.
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
        	getValidatorResults().merge(validator.validate());
        } catch (ValidatorException e) {
            log.error(e.getMessage(), e);
        }

        return errors;
    }
}
