package smpl.lang.compound;


import smpl.lang.visitors.CompoundVisitor;
import smpl.lang.SIRExp;
import smpl.lang.SIRObj;
import smpl.lang.visitors.SIRVisitor;
import smpl.sys.SMPLException;

public abstract class CompoundExp extends SIRExp<CompoundExp> implements SIRObj {
    
    public abstract <S, T> T visit(CompoundVisitor<CompoundExp, S, T> v, S state) throws SMPLException;

    @Override
    public <S, T> T visit(SIRVisitor<CompoundExp, S, T> v, S state) throws SMPLException {
        return visit( (CompoundVisitor<CompoundExp, S, T>) v, state);
    }
    
}
