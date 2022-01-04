package smpl.lang.visitors;

import smpl.lang.SIRExp;
import smpl.lang.emptyList.EmptyListLit;
import smpl.sys.SMPLException;


public interface EmptyListVisitor<E extends SIRExp<E>, S, T> extends SIRVisitor<E,S,T>   {
    
    public T visitEmptyListLit(EmptyListLit exp, S state) throws SMPLException;

}
