package smpl.lang.string;

import smpl.lang.SIRObj;
import smpl.lang.SIRVar;
import smpl.lang.evaluators.ObjectEvaluator;
import smpl.lang.visitors.StringVisitor;
import smpl.sys.SMPLContext;
import smpl.sys.SMPLException;
import smpl.values.type.simple.SMPLString;

public class StringLit extends StringExp {
    
    private SIRObj exp;
    private boolean isExp;
    private String str;


    public StringLit(String str) {
        this.str = str;
        this.isExp = false;
    }

    public StringLit(SIRObj exp) {
        this.isExp = true;
        this.exp = exp;
    }

    public SIRObj getExp() {
        return exp;
    }


    public boolean isExp() {
        return isExp;
    }


    public String getStr() {
        return str;
    }

    @Override
    public SMPLString eval(SMPLContext state, ObjectEvaluator eval) throws SMPLException {
        return (SMPLString) eval.eval(state, this);
    }

    @Override
    public <S, T> T visit(StringVisitor<StringExp, S, T> v, S state) throws SMPLException {
        return v.visitStringLit(this, state);
    }

    @Override
    public String toString() {
        return str;
    }
}
