package smpl.lang.compound;


import java.util.ArrayList;
import smpl.lang.SIRVar;
import smpl.lang.SIRObj;
import smpl.lang.evaluators.ObjectEvaluator;
import smpl.lang.visitors.*;
import smpl.sys.SMPLContext;
import smpl.sys.SMPLException;
import smpl.values.type.compound.SMPLSubvector;

public class SubvectorLit extends CompoundExp{

    private ArrayList<SIRObj> els;
    private String context;
    private final String type;
    private SIRVar<CompoundExp> varExp;

    public SubvectorLit(SIRVar<CompoundExp> var) {
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

    public ArrayList<SIRObj>  getSubvector() {
        return els;
    }

    public String getType() {
        return type;
    }

    @Override
    public SMPLSubvector eval(SMPLContext state, ObjectEvaluator eval) throws SMPLException {
        return  (SMPLSubvector) eval.eval(state, this);
    }

    @Override
    public <S, T> T visit(CompoundVisitor<CompoundExp, S, T> v, S state) throws SMPLException {
        return v.visitSubvectorExp(this, state);
    }
}
