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
    private final String type;
    private SIRObj exp;
    private boolean isExp;

    public PairLit(SIRObj exp) {
        this.type = "pair";
        this.isExp = true;
        this.exp = exp;
    }

    public boolean isExp() {
        return isExp;
    }

    public SIRObj getExp() {
        return exp;
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

    @Override
    public String toString() {
        return getExp().toString();
    }

    
}
