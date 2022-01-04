package smpl.lang;

import smpl.lang.evaluators.ObjectEvaluator;
import smpl.sys.SMPLContext;
import smpl.sys.SMPLException;
import smpl.values.Primitive;

public class SIRRef implements SIRObj {
    

    private SMPLContext state;
    private SIRObj obj;
    private String type;

    public SIRRef(SIRObj obj, SMPLContext state) {
        this.type = "ref";
        this.obj = obj;
        this.state = state;
    }

    public SMPLContext getState() {
        return state;
    }

    public SIRObj getObj() {
        return obj;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public Primitive eval(SMPLContext state, ObjectEvaluator eval) throws SMPLException {
        return eval.evalParamRef(state, this);
    }

    @Override
    public String toString() {
        return "ref " + obj.toString();
    }
}
