package smpl.lang.bool;

import smpl.lang.SIRExp;
import smpl.lang.SIRObj;
import smpl.lang.visitors.*;
import smpl.sys.SMPLException;

public abstract class BoolExp extends SIRExp<BoolExp> implements SIRObj {



    private final String type;

    public BoolExp() {
        this.type = "bool";
    }

    @Override
    public String getType() {
        return type;
    }

    public abstract <S, T> T visit(BoolVisitor<BoolExp, S, T> v, S state) throws SMPLException;


    @Override
    public <S, T> T visit(SIRVisitor<BoolExp, S, T> v, S state) throws SMPLException {
        return visit((BoolVisitor<BoolExp, S, T>)v, state);
    }
}


