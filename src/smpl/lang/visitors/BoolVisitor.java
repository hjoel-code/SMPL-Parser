package smpl.lang.visitors;

import smpl.lang.SIRExp;
import smpl.lang.bool.BoolCalc;
import smpl.lang.bool.BoolLit;
import smpl.sys.SMPLException;


public interface BoolVisitor<E extends SIRExp<E>, S, T> extends SIRVisitor<E,S,T>   {
    
    public T visitBoolLit(BoolLit exp, S state) throws SMPLException;
    public T visitBoolCalc(BoolCalc exp, S state) throws SMPLException;

}
