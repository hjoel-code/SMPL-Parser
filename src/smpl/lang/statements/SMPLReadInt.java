package smpl.lang.statements;

import smpl.lang.visitors.StatementVisitor;
import smpl.sys.SMPLException;

public class SMPLReadInt extends SIRStatement {
    public SMPLReadInt() {}

    @Override
    public <S, T> T visit(StatementVisitor<SIRStatement, S, T> v, S state) throws SMPLException {
        return v.visitSMPLReadInt(this, state);
    }
}
