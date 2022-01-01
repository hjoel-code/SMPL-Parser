package smpl.lang.compound;



import smpl.lang.SIRVar;
import smpl.lang.SIRObj;
import smpl.lang.evaluators.ObjectEvaluator;
import smpl.lang.visitors.*;
import smpl.sys.SMPLContext;
import smpl.sys.SMPLException;
import smpl.values.type.compound.SMPLVector;


public class VectorLit extends CompoundExp {

    private SIRObj[] arr;
    private String context;
    private final String type;
    private SIRVar<CompoundExp> varExp;

    public VectorLit(SIRVar<CompoundExp> var) {
        this.type = "vector";
        this.context = "var";
        this.varExp = var;
    }

    public String getContext() {
        return context;
    }

    public SIRVar<CompoundExp> getVarExp() {
        return varExp;
    }

    public SIRObj[] getVector() {
        return arr;
    }

    public String getType() {
        return type;
    }

    @Override
    public SMPLVector eval(SMPLContext state, ObjectEvaluator eval) throws SMPLException {
        return  (SMPLVector) eval.eval(state, this);
    }

    @Override
    public <S, T> T visit(CompoundVisitor<CompoundExp, S, T> v, S state) throws SMPLException {
        return v.visitVectorExp(this, state);
    }

    
}
