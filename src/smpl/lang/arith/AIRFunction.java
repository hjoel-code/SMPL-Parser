package smpl.lang.arith;

import smpl.sys.*;
import smpl.lang.evaluators.ObjectEvaluator;
import smpl.lang.visitors.*;
import smpl.lang.SIRFunctionExp;
import smpl.sys.SMPLContext;
import smpl.sys.SMPLException;
import smpl.values.Primitive;

public class AIRFunction extends AIRExp{



    private SIRFunctionExp<AIRExp> func;

    public AIRFunction(SIRFunctionExp<AIRExp> func) {
        this.func = func;
    }

    public SIRFunctionExp<AIRExp> getFunction() {
        return func;
    }

    @Override
    public <S, T> T visit(AIRVisitor<AIRExp, S, T> v, S state) throws SMPLException {
        return v.visitSIRFunction(func, state);
    }

    @Override
    public Primitive eval(SMPLContext state, ObjectEvaluator eval) throws SMPLException {
        return eval.evalSIRFunction(state, func);
    }

}
