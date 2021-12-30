package smpl.lang.compound;

import java.util.ArrayList;

import smpl.lang.SIRExp;
import smpl.lang.SIRObj;
import smpl.lang.evaluators.ObjectEvaluator;
import smpl.lang.visitors.SIRVisitor;
import smpl.sys.SMPLContext;
import smpl.sys.SMPLException;
import smpl.values.Primitive;

public class TupleExp extends SIRExp<TupleExp> implements SIRObj {

    private String type;
    private ArrayList<SIRObj> tuple;

    public TupleExp(ArrayList<SIRObj> tuple) {
        this.type = "tuple";
        this.tuple = tuple;
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
    public Primitive eval(SMPLContext state, ObjectEvaluator eval) throws SMPLException {
        return eval.eval(state, this);
    }

    @Override
    public <S, T> T visit(SIRVisitor<TupleExp, S, T> v, S state) throws SMPLException {
        // TODO Auto-generated method stub
        return null;
    }
    

}
