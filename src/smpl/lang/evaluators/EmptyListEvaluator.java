package smpl.lang.evaluators;

import java.util.Collections;
import java.util.HashMap;


import smpl.lang.visitors.EmptyListVisitor;
import smpl.sys.Environment;
import smpl.sys.SMPLException;
import smpl.lang.SIRBinaryExp;
import smpl.lang.SIRFunctionExp;
import smpl.lang.SIRUnaryExp;
import smpl.lang.SIRVar;
import smpl.lang.SIRProgram;
import smpl.lang.SIRSequence;
import smpl.lang.emptyList.*;
import smpl.values.Primitive;
import smpl.values.type.simple.*;
import java.util.List;

public class EmptyListEvaluator implements EmptyListVisitor<EmptyListExp, Environment<Primitive>, SMPLEmptyList> {

    private final SMPLEvaluator eval;

    private HashMap<String, List> emptyLsts;

    public EmptyListEvaluator(SMPLEvaluator eval) {
        this.eval = eval;

        


    }


    @Override
    public SMPLEmptyList visitEmptyListLit(EmptyListLit lst, Environment<Primitive> state)
            throws SMPLException {
                if (lst.isExp()) {
                    try {
                        return (SMPLEmptyList) lst.getExp().eval(state.getContext(), eval.getObjectEvaluator());
                    } catch (Exception e) {
                        throw new SMPLException("Expected empty list expression.");
                    }
                } else {
                    List<Primitive> newLst = Collections.emptyList();
                    return new SMPLEmptyList(newLst);
                }
    }

    @Override
    public SMPLEmptyList visitASTVar(SIRVar<EmptyListExp> var, Environment<Primitive> state)
            throws SMPLException {
        return (SMPLEmptyList) state.get(var.getVar());
    }


    @Override
    public SMPLEmptyList visitSMPLProgram(SIRProgram sp, Environment<Primitive> arg)
            throws SMPLException {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public SMPLEmptyList visitStmtSequence(SIRSequence seq, Environment<Primitive> state)
            throws SMPLException {
        // TODO Auto-generated method stub
        return null;
            }


    @Override
    public SMPLEmptyList visitASTBinaryExp(SIRBinaryExp<EmptyListExp> biExp, Environment<Primitive> state)
            throws SMPLException {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public SMPLEmptyList visitASTUnaryExp(SIRUnaryExp<EmptyListExp> urExp, Environment<Primitive> state)
            throws SMPLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SMPLEmptyList visitSIRFunction(SIRFunctionExp<EmptyListExp> func, Environment<Primitive> state) throws SMPLException {
        // TODO Auto-generated method stub
        return null;
    }


}
