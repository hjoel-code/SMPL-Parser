package smpl.lang.statements;

import java.util.ArrayList;

import smpl.lang.compound.TupleExp;
import smpl.lang.visitors.StatementVisitor;
import smpl.sys.SMPLException;

public class TupleAssignment extends SIRStatement {


    private TupleExp tuple;
    private ArrayList<String> ids;

    public TupleAssignment(ArrayList<String> ids, TupleExp tuple) {
        this.ids = ids;
        this.tuple = tuple;
    }

    public ArrayList<String> getIds() {
        return ids;
    }

    public TupleExp getTuple() {
        return tuple;
    }

    @Override
    public <S, T> T visit(StatementVisitor<SIRStatement, S, T> v, S state) throws SMPLException {
        return v.visitTupleAssignment(this, state);
    }

    @Override
    public String toString() {
        String out = "";


        for (String id : ids) {
            out += id + " ";
        }

        out += " = " + getTuple().toString();

        return out;
    }
    
}
