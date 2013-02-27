package bussineslogic.excepciones;

import java.util.List;
import java.util.Map;

/**
 *  This exception is thrown when a validation fails
 *  @author Automatika - JustInMind SL
 */
public class ValidationFailedException extends Exception {

    private static final long serialVersionUID = 1L;
    
    /**
     * parameters that do not fullfil the validation grouped by the property to acomplish
     */
    Map<String, List<String>> parameters;

    public ValidationFailedException(Map<String, List<String>> parameters) {
        super("Validation failed.");
        this.parameters=parameters;
    }
    
    public ValidationFailedException(String arg0, Map<String, List<String>> parameters) {
        super(arg0);
        this.parameters=parameters;
    }

    public ValidationFailedException(String arg0, Throwable arg1, Map<String, List<String>> parameters) {
        super(arg0, arg1);
        this.parameters=parameters;
    }
    
    public ValidationFailedException(Throwable arg0, Map<String, List<String>> parameters) {
        super(arg0);
        this.parameters=parameters;
    }

	public Map<String, List<String>> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, List<String>> parameters) {
		this.parameters = parameters;
	}

}