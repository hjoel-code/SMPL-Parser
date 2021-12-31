package smpl.lang.evaluators;

import java.util.ArrayList;
import java.util.HashMap;

import smpl.lang.*;
import smpl.lang.builtins.SMPLFunctions;
import smpl.lang.compound.*;
import smpl.lang.visitors.CompoundVisitor;
import smpl.sys.Environment;
import smpl.sys.SMPLException;
import smpl.values.type.compound.*;
import smpl.values.CompoundPrimitive;
import smpl.values.Primitive;

public class CompoundEvaluator
        implements CompoundVisitor<CompoundExp, Environment<Primitive>, CompoundPrimitive> {

    private SMPLEvaluator eval;
    private HashMap<String, SMPLFunctions> functions;

    public CompoundEvaluator(SMPLEvaluator eval) {
        this.eval = eval;

        functions = new HashMap<>();
        for (SMPLFunctions func : SMPLFunctions.values()) {
            functions.put(func.getSymbol(), func);
        }
    }

    @Override
    public CompoundPrimitive visitSMPLProgram(SIRProgram sp, Environment<Primitive> arg)
            throws SMPLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CompoundPrimitive visitStmtSequence(SIRSequence seq, Environment<Primitive> state)
            throws SMPLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CompoundPrimitive visitASTVar(SIRVar<CompoundExp> var, Environment<Primitive> state)
            throws SMPLException {
        return (CompoundPrimitive) state.get(var.getVar());
    }

    @Override
    public CompoundPrimitive visitASTBinaryExp(SIRBinaryExp<CompoundExp> biExp, Environment<Primitive> state)
            throws SMPLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CompoundPrimitive visitASTUnaryExp(SIRUnaryExp<CompoundExp> urExp, Environment<Primitive> state)
            throws SMPLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CompoundPrimitive visitPairExp(PairLit pair, Environment<Primitive> state)
            throws SMPLException {

        if (pair.getContext().equals("var")) {
            return pair.getVarExp().visit(this, state);
        } else {

            CompoundPrimitive<SMPLPair> exp1 = (SMPLPair) pair.getObj1().eval(state.getContext(),
                    eval.getObjectEvaluator());
            CompoundPrimitive<SMPLPair> exp2 = (SMPLPair) pair.getObj2().eval(state.getContext(),
                    eval.getObjectEvaluator());

            return new SMPLPair(exp1, exp2);
        }
    }

    @Override
    public CompoundPrimitive visitSIRFunction(SIRFunctionExp<CompoundExp> func, Environment<Primitive> state)
            throws SMPLException {
        String symbol = func.getSymbol();
        SMPLFunctions cont = functions.get(symbol);
        return (CompoundPrimitive) cont.apply(eval, state, func);
    }

    @Override
    public CompoundPrimitive visitTupleExp(TupleExp tuple, Environment<Primitive> state) throws SMPLException {
        ArrayList<SIRObj> tupleExp = tuple.getTuple();
        ArrayList<Primitive> tupleVal = new ArrayList<>();

        for (SIRObj exp : tupleExp) {
            tupleVal.add(exp.eval(state.getContext(), eval.getObjectEvaluator()));
        }
        
        return new SMPLTuple(tupleVal);
    }

    @Override
    public CompoundPrimitive visitProcExp(ProcExp proc, Environment<Primitive> state) throws SMPLException {
        return new SMPLProc(proc.getParams(), proc.getBody(), state.getContext().extendEnvironment());
    }

}
