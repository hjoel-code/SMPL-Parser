package smpl.lang;


import smpl.lang.evaluators.ObjectEvaluator;
import smpl.lang.visitors.SIRVisitor;
import smpl.sys.SMPLContext;
import smpl.sys.SMPLException;
import smpl.values.Primitive;

public class SIRVar<E extends SIRExp<E>> extends SIRExp<E> implements SIRObj {


    private String var; 
    private String type;

    public SIRVar() {
        this.type = "var";
    }


    public SIRVar(String var) {
        this();
        this.var = var;
    }

    @Override
    public String getType() {
        return type;
    }

    public String getVar() {
        return var;
    }

    
    @Override
    public <S, T> T visit(SIRVisitor<E, S, T> v, S state) throws SMPLException {
        return v.visitASTVar(this, state);
    }

    @Override
    public Primitive eval(SMPLContext state, ObjectEvaluator eval) throws SMPLException {
       return eval.evalVar(state, this);
    }

    @Override
    public String toString() {
        return getVar();
    }

}
