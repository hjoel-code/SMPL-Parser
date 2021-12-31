package smpl.lang.statements;

import smpl.lang.SIRObj;
import smpl.lang.visitors.StatementVisitor;
import smpl.sys.SMPLException;

public class SIRSingleStmt extends SIRStatement {
    private SIRObj exp;

    public SIRSingleStmt(SIRObj exp) {
        this.exp = exp;
    }

    public SIRObj getExp() {
        return exp;
    }

    @Override
    public <S, T> T visit(StatementVisitor<SIRStatement, S, T> v, S state) throws SMPLException {
        return v.visitSIRSingleStmt(this, state);
    }    
}
