package smpl.lang.visitors;

import smpl.sys.SMPLException;
import smpl.lang.SIRExp;
import smpl.lang.compound.PairLit;

public interface CompoundVisitor<E extends SIRExp<E>, S, T> extends SIRVisitor<E,S,T>  {
    
    public T visitPairExp(PairLit pair, S state) throws SMPLException;
    
}
