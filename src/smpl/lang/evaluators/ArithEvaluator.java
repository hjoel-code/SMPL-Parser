package smpl.lang.evaluators;

import java.util.HashMap;

import smpl.lang.visitors.AIRVisitor;
import smpl.sys.*;
import smpl.values.Primitive;
import smpl.values.type.simple.*;
import smpl.lang.*;
import smpl.lang.arith.AIRExp;
import smpl.lang.arith.AIRLit;
import smpl.lang.arith.ArithCalc;
import smpl.lang.ops.BinOpArith;
import smpl.lang.ops.UnOpArith;

/**
 * An evaluator for arithmetic subexpressions of SMPL programs.
 */

public class ArithEvaluator implements AIRVisitor<AIRExp, Environment<Primitive>, SMPLArith> {

    HashMap<String, UnOpArith> unOpsMap;
    HashMap<String, BinOpArith> binOpsMap;

    private SMPLEvaluator eval;

    public ArithEvaluator(SMPLEvaluator eval) {
        this.eval = eval;
        init();
    }

    private void init() {
        unOpsMap = new HashMap<>();
        for (UnOpArith op : UnOpArith.values()) {
            unOpsMap.put(op.getSymbol(), op);
        }

        binOpsMap = new HashMap<>();
        for (BinOpArith op : BinOpArith.values()) {
            binOpsMap.put(op.getSymbol(), op);
        }
    }

    @Override
    public SMPLArith visitAIRExpInt(AIRLit exp, Environment<Primitive> state)
            throws SMPLException {
        if (exp.isExp()) {
            try {
                return (SMPLArith) exp.getExp().eval(state.getContext(), eval.getObjectEvaluator());
            } catch (Exception e) {
                throw new SMPLException("Expected arithmetic expression.");
            }
            
        } else {
            return new SMPLArith(exp.getVal(), exp.getRep());
        }
    }

    @Override
    public SMPLArith visitASTBinaryExp(SIRBinaryExp<AIRExp> exp,
            Environment<Primitive> state) throws SMPLException {
        String opName = exp.getOperator();
        BinOpArith op = binOpsMap.get(opName);

        double leftArg = (double) exp.getExp1().eval(state.getContext(), eval.getObjectEvaluator()).getPrimitive();
        double rightArg = (double) exp.getExp2().eval(state.getContext(), eval.getObjectEvaluator()).getPrimitive();
        return new SMPLArith(op.apply(leftArg, rightArg));

    }

    @Override
    public SMPLArith visitASTUnaryExp(SIRUnaryExp<AIRExp> exp, Environment<Primitive> state)
            throws SMPLException {
        String opName = exp.getOperator();
        UnOpArith op = unOpsMap.get(opName);
        AIRExp argExp = (AIRExp) exp.getExp();
        double arg = argExp.visit(this, state).getPrimitive();
        return new SMPLArith(op.apply(arg));
    }

    @Override
    public SMPLArith visitASTVar(SIRVar<AIRExp> var, Environment<Primitive> state)
            throws SMPLException {
                
        return (SMPLArith) state.get(var.getVar());
    }

    @Override
    public SMPLArith visitArithCalc(ArithCalc exp, Environment<Primitive> state)
            throws SMPLException {
        if (exp.getCalcType().equals("binary")) {
            return exp.getBinary().visit(this, state);
        } else {
            return exp.getUnary().visit(this, state);
        }
    }

    @Override
    public SMPLArith visitSMPLProgram(SIRProgram sp, Environment<Primitive> arg)
            throws SMPLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SMPLArith visitStmtSequence(SIRSequence seq, Environment<Primitive> state)
            throws SMPLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SMPLArith visitSIRFunction(SIRFunctionExp<AIRExp> func, Environment<Primitive> state) throws SMPLException {
        // TODO Auto-generated method stub
        return null;
    }

}
