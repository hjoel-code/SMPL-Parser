package smpl.lang.statements;

import smpl.lang.SIRObj;
import smpl.lang.visitors.*;
import smpl.sys.*;

public class SMPLAssignment extends SIRStatement {
    
    protected String var;
    protected SIRObj exp;
    
    public SMPLAssignment(String id, SIRObj exp) {
        this.var = id;
        this.exp = exp;
    }

    public String getVar() {
        return this.var;
    }

    public void setExp(SIRObj exp) {
        this.exp = exp;
    }

    public SIRObj getExp() {
        return this.exp;
    }

    @Override
    public <S, T> T visit(StatementVisitor<SIRStatement, S, T> v, S state) throws SMPLException {
        return v.visitSMPLAssignment(this, state);
    }

    
}
