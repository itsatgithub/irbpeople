package bussineslogic.excepciones;

 /**
 *  Excepction caused beacause the user is not active
 *  @author Automatika - JustInMind SL
 */
public class HolidaysException extends Exception {

    private static final long serialVersionUID = 1L;

    public HolidaysException() {
        super("No hi ha prous dies!");
    }

    public HolidaysException(String arg0) {
        super(arg0);
    }

    public HolidaysException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public HolidaysException(Throwable arg0) {
        super(arg0);
    }
}