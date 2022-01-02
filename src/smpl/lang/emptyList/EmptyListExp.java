package smpl.lang.emptyList;

import smpl.lang.SIRExp;
import smpl.lang.SIRObj;
import smpl.lang.visitors.*;
import smpl.sys.SMPLException;

public abstract class EmptyListExp extends SIRExp<EmptyListExp> implements SIRObj {



    private final String type;

    public EmptyListExp() {
        this.type = "elist";
    }

    @Override
    public String getType() {
        return type;
    }

    public abstract <S, T> T visit(EmptyListVisitor<EmptyListExp, S, T> v, S state) throws SMPLException;


    @Override
    public <S, T> T visit(SIRVisitor<EmptyListExp, S, T> v, S state) throws SMPLException {
        return visit((EmptyListVisitor<EmptyListExp, S, T>)v, state);
    }
}


