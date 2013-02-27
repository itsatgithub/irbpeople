package bussineslogic.excepciones;

/**
 *  Excepction caused beacause an user is trying to do something that is has no permisions over.
 *  @author Automatika - JustInMind SL
 */
public class NoPermisosException extends Exception {

    private static final long serialVersionUID = 1L;

    public NoPermisosException() {
        super("El usuario no tiene permisos para realizar esta operación.");
    }
    
    public NoPermisosException(String arg0) {
        super(arg0);
    }

    public NoPermisosException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }
    
    public NoPermisosException(Throwable arg0) {
        super(arg0);
    }

}