package smpl.lang.compound;



import smpl.lang.SIRObj;
import smpl.lang.evaluators.ObjectEvaluator;
import smpl.lang.visitors.*;
import smpl.sys.SMPLContext;
import smpl.sys.SMPLException;
import smpl.values.type.compound.SMPLVector;


public class VectorLit extends CompoundExp {

    private SIRObj[] arr;
    private Boolean isExp;
    private final String type;
    private SIRObj exp;

    public VectorLit(SIRObj exp) {
        this.type = "vector";
        this.isExp = true;
        this.exp = exp;
    }

    public SIRObj getExp() {
        return exp;
    }
    

    public Boolean isExp() {
        return isExp;
    }

    public SIRObj[] getVector() {
        return arr;
    }

    public String getType() {
        return type;
    }

    @Override
    public SMPLVector eval(SMPLContext state, ObjectEvaluator eval) throws SMPLException {
        try {
            return  (SMPLVector) eval.eval(state, this);
        } catch (Exception e) {
            throw new SMPLException("Expected an vector expression.");
        }
        
    }

    @Override
    public <S, T> T visit(CompoundVisitor<CompoundExp, S, T> v, S state) throws SMPLException {
        return v.visitVectorExp(this, state);
    }

    
}
