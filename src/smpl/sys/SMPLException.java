package smpl.sys;

/** Parent class for all exceptions thrown by the function calculator. */
public class SMPLException extends Exception {
    private static final long serialVersionUID = 1L;

    SMPLException cause;

    public SMPLException() {
      super();
    }

    public SMPLException(String s) {
      super(s);
    }

    public SMPLException(String s, SMPLException smple) {
      super(s);
      cause = smple;
    }

    public String report() {
      if (cause == null)
        return getMessage();
      else
        return getMessage() + " caused by " + cause.report();
    }
}
