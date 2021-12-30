package smpl.lang.builtins;

import smpl.lang.SIRFunctionExp;
import smpl.sys.SMPLException;

public interface SIRFunctions<T, A, S> {
    
    public abstract String getSymbol();
    public abstract T apply(A eval, S state, SIRFunctionExp exp) throws SMPLException;

}
