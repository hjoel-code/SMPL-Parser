package smpl.values.type.simple;

import smpl.lang.SIRObj;
import smpl.lang.evaluators.ObjectEvaluator;
import smpl.sys.SMPLContext;
import smpl.sys.SMPLException;
import smpl.values.Primitive;

public class SMPLLazy extends Primitive<Primitive>{



    private SMPLContext context;
    private SIRObj val;

    public SMPLLazy(SIRObj obj, SMPLContext context) {
        super("lazy");
        this.val = obj;
        this.context = context;
    }

    public Primitive getVal(ObjectEvaluator eval) throws SMPLException {
        return val.eval(context, eval);
    }


    public SMPLContext getContext() {
        return context;
    }

    @Override
    public Primitive getPrimitive() {
        return this;
    }

    @Override
    public String getOutput() {
        return null;
    }
    
}
