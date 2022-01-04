package smpl.lang.bool;

import smpl.lang.SIRObj;
import smpl.lang.evaluators.ObjectEvaluator;
import smpl.lang.visitors.BoolVisitor;
import smpl.sys.SMPLContext;
import smpl.sys.SMPLException;
import smpl.values.type.simple.SMPLBool;

public class BoolLit extends BoolExp{
    

    private Boolean bool;
    private SIRObj exp;
    private boolean isExp;


    public BoolLit(Boolean bool) {
        super();
        this.bool = bool;
        this.isExp = false;
    }

    public BoolLit(SIRObj exp) {
        super();
        this.exp = exp;
        this.isExp = true;
    }

    public boolean isExp() {
        return isExp;
    }

    public SIRObj getExp() {
        return exp;
    }

    public Boolean getBool() {
        return bool;
    }

    @Override
    public SMPLBool eval(SMPLContext state, ObjectEvaluator eval) throws SMPLException {
        try {
            return (SMPLBool) eval.eval(state, this);
        } catch (SMPLException e) {
            throw e;
        } catch (Exception e) {
            throw new SMPLException("Expected boolean expression");
        }
        
    }

    @Override
    public <S, T> T visit(BoolVisitor<BoolExp, S, T> v, S state) throws SMPLException {
        return v.visitBoolLit(this, state);
    }

    @Override
    public String toString() {
        if (isExp()) {
            return getExp().toString();
        } else {
            return String.valueOf(getBool());
        }
    }
}
