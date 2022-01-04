package smpl.lang.compound;

import java.util.ArrayList;

import smpl.lang.evaluators.ObjectEvaluator;
import smpl.lang.visitors.CompoundVisitor;
import smpl.sys.SMPLContext;
import smpl.sys.SMPLException;
import smpl.values.Primitive;
import smpl.values.type.compound.SMPLSingleCase;

public class CaseCondExp extends CompoundExp {
    private ArrayList<SMPLSingleCase> cases;
    private String type;

    public CaseCondExp(ArrayList<SMPLSingleCase> cases) {
        this.type = "case";
        this.cases = cases;
    }

    public ArrayList<SMPLSingleCase> getCases() {
        return cases;
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
    public <S, T> T visit(CompoundVisitor<CompoundExp, S, T> v, S state) throws SMPLException {
        return v.visitCaseCondExp(this, state);
    }
}
