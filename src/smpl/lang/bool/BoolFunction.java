package smpl.lang.bool;

import smpl.lang.SIRFunctionExp;
import smpl.lang.evaluators.ObjectEvaluator;
import smpl.lang.visitors.BoolVisitor;
import smpl.sys.SMPLContext;
import smpl.sys.SMPLException;
import smpl.values.Primitive;

public class BoolFunction extends BoolExp{



    private SIRFunctionExp<BoolExp> func;

    public BoolFunction(SIRFunctionExp<BoolExp> func) {
        this.func = func;
    }

    public SIRFunctionExp<BoolExp> getFunction() {
        return func;
    }

    @Override
    public <S, T> T visit(BoolVisitor<BoolExp, S, T> v, S state) throws SMPLException {
        return v.visitSIRFunction(func, state);
    }

    @Override
    public Primitive eval(SMPLContext state, ObjectEvaluator eval) throws SMPLException {
        return eval.evalSIRFunction(state, func);
    }

}
