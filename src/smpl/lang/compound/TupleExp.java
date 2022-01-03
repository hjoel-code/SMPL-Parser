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
    private ArrayList<SIRObj> tuple;
    private SIRObj exp;
    private boolean isExp;

    public TupleExp(ArrayList<SIRObj> tuple) {
        this.type = "tuple";
        this.tuple = tuple;
        this.isExp = false;
    }

    public TupleExp(SIRObj exp) {
        this.type = "tuple";
        this.exp = exp;
        this.isExp = true;

    }

    public boolean isExp() {
        return isExp;
    }

    public SIRObj getExp() {
        return exp;
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
        if (isExp()) {
            return exp.eval(state, eval);
        }
        return eval.eval(state, this);
    }

    @Override
    public String toString() {
        if (isExp()) {
            return getExp().toString();
        } 

        String out = "(";

        for (SIRObj obj: getTuple()) {
            out += obj.toString() + "  ";
        }

        out += ")";

        return out;
    }
    

}
