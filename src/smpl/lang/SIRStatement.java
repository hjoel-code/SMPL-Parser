package smpl.lang;

import smpl.sys.*;

public class SIRStatement extends SIRExp {
    SIRExp sExp;

    public SIRStatement() {
        super();
    }

    public SIRStatement(SIRExp sExp) {
        this.sExp = sExp;
    } 

    public SIRExp getExp() {
        return sExp;
    }

    @Override
    public <S, T> T visit(SMPLVisitor<S, T> v, S state) throws SMPLException {
        return v.visitSMPLStatement(this, state);
    }
}
