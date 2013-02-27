package bussineslogic.excepciones;

 /**
 *  Excepction caused beacause the user is not active
 *  @author Automatika - JustInMind SL
 */
public class UsuarioNoActivoException extends Exception {

    private static final long serialVersionUID = 1L;

    public UsuarioNoActivoException() {
        super("El usuario no está activo.");
    }

    public UsuarioNoActivoException(String arg0) {
        super(arg0);
    }

    public UsuarioNoActivoException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public UsuarioNoActivoException(Throwable arg0) {
        super(arg0);
    }
}