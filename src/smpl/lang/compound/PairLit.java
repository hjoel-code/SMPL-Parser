package smpl.lang.compound;



import smpl.lang.SIRVar;
import smpl.lang.SIRObj;
import smpl.lang.evaluators.ObjectEvaluator;
import smpl.lang.visitors.*;
import smpl.sys.SMPLContext;
import smpl.sys.SMPLException;
import smpl.values.type.compound.SMPLPair;


public class PairLit extends CompoundExp {

    private SIRObj obj1;
    private SIRObj obj2;
    private String context;
    private final String type;
    private SIRVar<CompoundExp> varExp;

    public PairLit(SIRVar<CompoundExp> var) {
        this.type = "pair";
        this.context = "var";
        this.varExp = var;
    }

    public String getContext() {
        return context;
    }

    public SIRVar<CompoundExp> getVarExp() {
        return varExp;
    }

    public SIRObj getObj1() {
        return obj1;
    }

    public SIRObj getObj2() {
        return obj2;
    }

    public String getType() {
        return type;
    }

    @Override
    public SMPLPair eval(SMPLContext state, ObjectEvaluator eval) throws SMPLException {
        return  (SMPLPair) eval.eval(state, this);
    }

    @Override
    public <S, T> T visit(CompoundVisitor<CompoundExp, S, T> v, S state) throws SMPLException {
        return v.visitPairExp(this, state);
    }

    
}
