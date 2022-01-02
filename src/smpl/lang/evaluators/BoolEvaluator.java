package smpl.lang.evaluators;

import java.util.HashMap;

import smpl.lang.bool.BoolCalc;
import smpl.lang.bool.BoolExp;
import smpl.lang.bool.BoolLit;
import smpl.lang.builtins.SMPLFunctions;
import smpl.lang.ops.BinOpBool;
import smpl.lang.ops.BinOpLogic;
import smpl.lang.visitors.BoolVisitor;
import smpl.sys.Environment;
import smpl.sys.SMPLException;
import smpl.lang.SIRBinaryExp;
import smpl.lang.SIRFunctionExp;
import smpl.lang.SIRUnaryExp;
import smpl.lang.SIRVar;
import smpl.lang.SIRProgram;
import smpl.lang.SIRSequence;
import smpl.lang.arith.AIRExp;
import smpl.values.Primitive;
import smpl.values.type.simple.*;

public class BoolEvaluator implements BoolVisitor<BoolExp, Environment<Primitive>, SMPLBool> {

    private final SMPLEvaluator eval;

    private HashMap<String, BinOpBool> boolOps;
    private HashMap<String, BinOpLogic> logicOps;
    private HashMap<String, SMPLFunctions> functions;

    public BoolEvaluator(SMPLEvaluator eval) {
        this.eval = eval;

        boolOps = new HashMap<>();
        for (BinOpBool op : BinOpBool.values()) {
            boolOps.put(op.getSymbol(), op);
        }

        logicOps = new HashMap<>();
        for (BinOpLogic op : BinOpLogic.values()) {
            logicOps.put(op.getSymbol(), op);
        }

        functions = new HashMap<>();
        for (SMPLFunctions func : SMPLFunctions.values()) {
            functions.put(func.getSymbol(), func);
        }

    }

    @Override
    public SMPLBool visitASTVar(SIRVar<BoolExp> var, Environment<Primitive> state)
            throws SMPLException {
        return (SMPLBool) state.get(var.getVar());
    }

    @Override
    public SMPLBool visitASTBinaryExp(SIRBinaryExp<BoolExp> biExp, Environment<Primitive> state)
            throws SMPLException {

        String type = biExp.getExp1().getType();
        System.out.println(type);

        if (type.equals("arith")) {

            String opName = biExp.getOperator();
            BinOpBool op = boolOps.get(opName);

            double leftArg = (double) biExp.getExp1().eval(state.getContext(), eval.getObjectEvaluator()).getPrimitive();
            double rightArg = (double) biExp.getExp2().eval(state.getContext(), eval.getObjectEvaluator()).getPrimitive();

            return new SMPLBool(op.apply(leftArg, rightArg));

            

        } else {

            String opName = biExp.getOperator();
            BinOpLogic op = logicOps.get(opName);

            boolean leftArg = (boolean) biExp.getExp1().eval(state.getContext(), eval.getObjectEvaluator()).getPrimitive();
            boolean rightArg = (boolean) biExp.getExp2().eval(state.getContext(), eval.getObjectEvaluator()).getPrimitive();
            
            return new SMPLBool(op.apply(leftArg, rightArg));
        }
    }

    @Override
    public SMPLBool visitASTUnaryExp(SIRUnaryExp<BoolExp> urExp, Environment<Primitive> state)
            throws SMPLException {

        String type = urExp.getExp().getType();
        System.out.println(type);

        if (type.equals("arith")) {

            String opName = urExp.getOperator();
            BinOpBool op = boolOps.get(opName);

            AIRExp leftExp = (AIRExp) urExp.getExp();

            double leftArg = leftExp.visit(eval.getArithEval(), state).getPrimitive();

            return new SMPLBool(op.apply(leftArg, null));
        } else {
            String opName = urExp.getOperator();
            BinOpLogic op = logicOps.get(opName);

            BoolExp leftExp = (BoolExp) urExp.getExp();

            boolean leftArg = leftExp.visit(this, state).getPrimitive();

            return new SMPLBool(op.apply(leftArg, null));
        }
    }

    @Override
    public SMPLBool visitBoolLit(BoolLit exp, Environment<Primitive> state)
            throws SMPLException {
        return exp.getContext().equals("var") ? exp.getVarExp().visit(this, state)
                : new SMPLBool(exp.getBool());
    }

    @Override
    public SMPLBool visitBoolCalc(BoolCalc exp, Environment<Primitive> state)
            throws SMPLException {
        if (exp.getCalcType().equals("binary")) {
            return exp.getBinary().visit(this, state);
        } else {
            return exp.getUnary().visit(this, state);
        }
    }

    @Override
    public SMPLBool visitSIRFunction(SIRFunctionExp<BoolExp> func, Environment<Primitive> state) throws SMPLException {
        String symbol = func.getSymbol();
        SMPLFunctions cont = functions.get(symbol);
        return (SMPLBool) cont.apply(eval, state, func);
    }

    @Override
    public SMPLBool visitSMPLProgram(SIRProgram sp, Environment<Primitive> arg)
            throws SMPLException {
        return null;
    }

    @Override
    public SMPLBool visitStmtSequence(SIRSequence seq, Environment<Primitive> state)
            throws SMPLException {
        return null;
    }

}
