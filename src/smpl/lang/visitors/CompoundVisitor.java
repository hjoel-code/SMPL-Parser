package smpl.lang.visitors;

import smpl.sys.SMPLException;
import smpl.lang.SIRExp;
import smpl.lang.compound.PairLit;
import smpl.lang.compound.VectorLit;
import smpl.lang.compound.ProcExp;
import smpl.lang.compound.TupleExp;
import smpl.lang.compound.SubvectorLit;
import smpl.lang.compound.CaseCondExp;

public interface CompoundVisitor<E extends SIRExp<E>, S, T> extends SIRVisitor<E,S,T>  {
    
    public T visitPairExp(PairLit pair, S state) throws SMPLException;
    public T visitTupleExp(TupleExp tuple, S state) throws SMPLException;
    public T visitProcExp(ProcExp proc, S state) throws SMPLException;
    public T visitVectorExp(VectorLit vector, S state) throws SMPLException;
    public T visitSubvectorExp(SubvectorLit subvector, S state) throws SMPLException;
    
    public T visitCaseCondExp(CaseCondExp caseC, S state) throws SMPLException;
}
