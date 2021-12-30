package smpl.lang.statements;

import smpl.sys.*;
import smpl.lang.SIRExp;
import smpl.lang.visitors.SIRVisitor;
import smpl.lang.visitors.StatementVisitor;

public abstract class SIRStatement extends SIRExp<SIRStatement> {

    public abstract <S, T> T visit(StatementVisitor<SIRStatement, S, T> v, S state) throws SMPLException;


    @Override
    public <S, T> T visit(SIRVisitor<SIRStatement, S, T> v, S state) throws SMPLException {
        return visit( (StatementVisitor<SIRStatement, S, T>) v, state);
    }
    
}