package smpl.lang.statements;

import smpl.lang.SIRObj;
import smpl.lang.visitors.StatementVisitor;
import smpl.sys.SMPLException;

public class Statement extends SIRStatement {


    private SIRObj exp;

    public Statement(SIRObj obj) {
        this.exp = obj;
    }

    public SIRObj getExp() {
        return exp;
    }

    @Override
    public <S, T> T visit(StatementVisitor<SIRStatement, S, T> v, S state) throws SMPLException {
        return v.visitStatement(this, state);
    }

    @Override
    public String toString() {
        return getExp().toString();
    }
    
}
