package bussineslogic.excepciones;

/**
 *  Excepction caused beacause the user does not exists
 *  @author Automatika - JustInMind SL
 */
public class UsuarioExisteException extends Exception {

    private static final long serialVersionUID = 1L;

    public UsuarioExisteException() {
        super("Ya existe un usuario con ese identificador.");
    }

    public UsuarioExisteException(String arg0) {
        super(arg0);
    }
    
    public UsuarioExisteException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }
    
    public UsuarioExisteException(Throwable arg0) {
        super(arg0);
    }

}