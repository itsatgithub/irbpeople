package bussineslogic.excepciones;

/**
 *  Excepction which is due to an error of the application
 *  @author Automatika - JustInMind SL
 */
public class InternalException extends Exception {

    private static final long serialVersionUID = 1L;

    public InternalException() {
        super("Error interno en la aplicación. Si el problema se repite contacte con el administrador.\n Disculpen las molestias.");
    }
    
    public InternalException(String arg0) {
        super(arg0);
    }

    public InternalException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }
    
    public InternalException(Throwable arg0) {
        super(arg0);
    }

}