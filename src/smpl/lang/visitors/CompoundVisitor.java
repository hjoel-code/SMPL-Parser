package smpl.lang.visitors;

import smpl.sys.SMPLException;
import smpl.lang.SIRExp;
import smpl.lang.compound.PairLit;
import smpl.lang.compound.TupleExp;

public interface CompoundVisitor<E extends SIRExp<E>, S, T> extends SIRVisitor<E,S,T>  {
    
    public T visitPairExp(PairLit pair, S state) throws SMPLException;
    public T visitTupleExp(TupleExp tuple, S state) throws SMPLException;
    
}
