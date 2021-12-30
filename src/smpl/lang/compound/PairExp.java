package smpl.lang.compound;

import smpl.lang.SIRFunctionExp;
import smpl.lang.evaluators.ObjectEvaluator;
import smpl.lang.visitors.CompoundVisitor;
import smpl.sys.SMPLContext;
import smpl.sys.SMPLException;
import smpl.values.Primitive;

public class PairExp extends CompoundExp {

    private SIRFunctionExp<CompoundExp> func;
    private String type;

    public PairExp(SIRFunctionExp<CompoundExp> func) {
        this.type = "pair";
        this.func = func;
    }

    public SIRFunctionExp<CompoundExp> getFunc() {
        return func;
    }



    @Override
    public String getType() {
        return type;
    }


    @Override
    public <S, T> T visit(CompoundVisitor<CompoundExp, S, T> v, S state) throws SMPLException {
        return v.visitSIRFunction(func, state);
    }

    @Override
    public Primitive eval(SMPLContext state, ObjectEvaluator eval) throws SMPLException {
        return eval.evalSIRFunction(state, func);
    }
    
}
