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
import smpl.lang.bool.BoolExp;
import smpl.values.SMPLResults;


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

        if (pair.isExp()) {
            try {
                return (SMPLPair) pair.getExp().eval(state.getContext(), eval.getObjectEvaluator());
            } catch (Exception e) {
                throw new SMPLException("Expected pair expression");
            }
        } else {

            CompoundPrimitive<SMPLPair> exp1 = (SMPLPair) pair.getObj1().eval(state.getContext(),
                    eval.getObjectEvaluator());
            CompoundPrimitive<SMPLPair> exp2 = (SMPLPair) pair.getObj2().eval(state.getContext(),
                    eval.getObjectEvaluator());

            return new SMPLPair(exp1, exp2);
        }
    }

    @Override
    public CompoundPrimitive visitVectorExp(VectorLit vector, Environment<Primitive> state)
            throws SMPLException {

        if (vector.isExp()) {
            try {
                return (SMPLVector) vector.getExp().eval(state.getContext(), eval.getObjectEvaluator());
            } catch (Exception e) {
                throw new SMPLException("Expected vector expression.");
            }
            
        } else {
            SIRObj[] arr = vector.getVector();
            ArrayList<Primitive> vecLst = new ArrayList<>();
            for(int i = 0; i < arr.length; i++){
                Primitive eleEval = arr[i].eval(state.getContext(), eval.getObjectEvaluator());
                if(eleEval.getType() == "subvector"){
                    SMPLSubvector subv = (SMPLSubvector) eleEval;
                    ArrayList<Primitive> l = subv.getPrimitive();
                    for(Primitive e: l){
                        vecLst.add(e);
                    }
                } else {
                    vecLst.add(eleEval);
                }
            }
            Primitive[] vecArr = new Primitive[vecLst.size()];
            vecArr = vecLst.toArray(vecArr);
            return new SMPLVector(vecArr);
        }
    }

    @Override
    public CompoundPrimitive visitSubvectorExp(SubvectorLit subvector, Environment<Primitive> state)
            throws SMPLException {
        ArrayList<SIRObj> arr = subvector.getSubvector();
        ArrayList<Primitive> vecArr = new ArrayList<>();
        for(int i = 0; i < arr.size(); i++){
            Primitive eleEval = arr.get(i).eval(state.getContext(), eval.getObjectEvaluator());
            vecArr.add(eleEval);
        }
        return new SMPLSubvector(vecArr);
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

    @Override
    public CompoundPrimitive visitCaseCondExp(CaseCondExp cCond, Environment<Primitive> state) throws SMPLException {
        ArrayList<SMPLSingleCase> cases = cCond.getCases();

        for (int i = 0; i < cases.size(); i++) {
            SMPLSingleCase sCase = cases.get(i);
            Boolean predicate = sCase.getPredicate().visit(eval.getBoolEval(), state.getContext().getGlobalEnvironment()).getPrimitive();

            if (predicate) {
                return new SMPLSingleCase(eval.visitStmtSequence(sCase.getConsequence(), state.getContext()));
            } 
        }

        return new SMPLSingleCase(new SMPLResults());
    }

}
