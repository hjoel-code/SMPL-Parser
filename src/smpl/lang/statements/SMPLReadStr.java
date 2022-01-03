package smpl.lang.statements;

import smpl.lang.visitors.StatementVisitor;
import smpl.sys.SMPLException;

public class SMPLReadStr extends SIRStatement {
    public SMPLReadStr() {}

    @Override
    public <S, T> T visit(StatementVisitor<SIRStatement, S, T> v, S state) throws SMPLException {
        return v.visitSMPLReadStr(this, state);
    }
}
