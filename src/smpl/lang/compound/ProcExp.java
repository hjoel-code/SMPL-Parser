package smpl.lang.compound;

import java.util.ArrayList;

import smpl.lang.SIRProgram;
import smpl.lang.evaluators.ObjectEvaluator;
import smpl.lang.visitors.CompoundVisitor;
import smpl.sys.SMPLContext;
import smpl.sys.SMPLException;
import smpl.values.Primitive;

public class ProcExp extends CompoundExp {

    private String type;
    private SIRProgram body;
    private ArrayList<String> params;

    public ProcExp(ArrayList<String> params, SIRProgram body) {
        this.type = "proc";
        this.params = params;
        this.body = body;
    }


    public ProcExp(String param, SIRProgram body) {
        this.type = "proc";
        this.params = new ArrayList<>();
        this.params.add(param);
        this.body = body;
    }

    public SIRProgram getBody() {
        return body;
    }

    public ArrayList<String> getParams() {
        return params;
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
        return v.visitProcExp(this, state);
    }
    
}
