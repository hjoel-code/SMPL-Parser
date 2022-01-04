package smpl.lang;

import smpl.lang.evaluators.ObjectEvaluator;
import smpl.sys.SMPLContext;
import smpl.sys.SMPLException;
import smpl.values.Primitive;

public class SIRLazy implements SIRObj {
    

    private String type;
    private SIRObj obj;
    private SMPLContext state;

    public SIRLazy(SIRObj obj, SMPLContext state) {
        this.type = "lazy";
        this.obj = obj;
        this.state = state;
    }

    public SIRObj getObj() {
        return obj;
    }

    public SMPLContext getState() {
        return state;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public Primitive eval(SMPLContext state, ObjectEvaluator eval) throws SMPLException {
        return eval.eval(state, this);
    }
    

    @Override
    public String toString() {
        return "lazy " + obj.toString();
    }
}
