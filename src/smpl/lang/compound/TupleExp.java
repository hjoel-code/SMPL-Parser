package smpl.lang.compound;

import java.util.ArrayList;

import smpl.lang.SIRObj;
import smpl.lang.SIRVar;
import smpl.lang.evaluators.ObjectEvaluator;
import smpl.lang.visitors.CompoundVisitor;
import smpl.sys.SMPLContext;
import smpl.sys.SMPLException;
import smpl.values.Primitive;



public class TupleExp extends CompoundExp  {

    private String type;
    private Boolean isVar;
    private ArrayList<SIRObj> tuple;
    private SIRVar<CompoundExp> varExp;

    public TupleExp(ArrayList<SIRObj> tuple) {
        this.type = "tuple";
        this.tuple = tuple;
        this.isVar = false;
    }

    public TupleExp(SIRVar<CompoundExp> var) {
        this.varExp = var;
        this.isVar = true;

    }

    public boolean isVariable() {
        return isVar;
    }

    public SIRVar<CompoundExp> getVarExp() {
        return varExp;
    }

    public int getTupleLength() {
        return tuple.size();
    }

    public ArrayList<SIRObj> getTuple() {
        return tuple;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public <S, T> T visit(CompoundVisitor<CompoundExp, S, T> v, S state) throws SMPLException {
        return v.visitTupleExp(this, state);
    }

    @Override
    public Primitive eval(SMPLContext state, ObjectEvaluator eval) throws SMPLException {
        if (isVar) {
            return eval.evalVar(state, varExp);
        }
        return eval.eval(state, this);
    }
    

}
