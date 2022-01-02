
package smpl.lang.evaluators;

import smpl.lang.SIRVar;

import java.util.HashMap;

import smpl.lang.SIRFunctionExp;
import smpl.lang.SIRLazy;
import smpl.lang.SIRObj;
import smpl.lang.SIRRef;
import smpl.lang.arith.AIRExp;
import smpl.lang.bool.BoolExp;
import smpl.lang.builtins.SMPLFunctions;
import smpl.lang.chars.CharExp;
import smpl.lang.chars.CharLit;
import smpl.lang.emptyList.EmptyListExp;
import smpl.lang.emptyList.EmptyListLit;
import smpl.lang.compound.CompoundExp;
import smpl.lang.compound.PairLit;
import smpl.lang.compound.VectorLit;
import smpl.lang.compound.ProcExp;
import smpl.lang.compound.TupleExp;
import smpl.lang.string.StringExp;
import smpl.sys.SMPLContext;
import smpl.sys.SMPLException;
import smpl.values.Primitive;
import smpl.values.type.simple.SMPLLazy;
import smpl.values.type.simple.SMPLRef;

public class ObjectEvaluator {

    private SMPLEvaluator eval;
    private HashMap<String, SMPLFunctions> functions;

    public ObjectEvaluator(SMPLEvaluator eval) {
        this.eval = eval;

        functions = new HashMap<>();
        for (SMPLFunctions func : SMPLFunctions.values()) {
            functions.put(func.getSymbol(), func);
        }
    }

    public SMPLEvaluator getEval() {
        return eval;
    }

    public Primitive eval(SMPLContext state, SIRObj obj) throws SMPLException {

        String type = obj.getType();
        System.out.println("EVAL: " + type);

        if (type.equals("arith")) {
            AIRExp exp = (AIRExp) obj;
            return exp.visit(eval.getArithEval(), state.getGlobalEnvironment());
        } else if (type.equals("bool")) {
            BoolExp exp = (BoolExp) obj;
            return exp.visit(eval.getBoolEval(), state.getGlobalEnvironment());
        } else if (type.equals("string")) {
            StringExp exp = (StringExp) obj;
            return exp.visit(eval.getStrEval(), state.getGlobalEnvironment());
        } else if (type.equals("char")) {
            CharLit exp = (CharLit) obj;
            return exp.visit(eval.getCharEval(), state.getGlobalEnvironment());
        } else if (type.equals("elist")) {
            EmptyListLit exp = (EmptyListLit) obj;
            return exp.visit(eval.getEmptyListEval(), state.getGlobalEnvironment());
        } else if (type.equals("pair")) {
            PairLit exp = (PairLit) obj;
            return exp.visit(eval.getCompoundEval(), state.getGlobalEnvironment());
        } else if (type.equals("vector")) {
            VectorLit exp = (VectorLit) obj;
            return exp.visit(eval.getCompoundEval(), state.getGlobalEnvironment());
        } else if (type.equals("tuple")) {
            TupleExp exp = (TupleExp) obj;
            return exp.visit(eval.getCompoundEval(), state.getGlobalEnvironment());
        } else if (type.equals("proc")) {
            ProcExp exp = (ProcExp) obj;
            return exp.visit(eval.getCompoundEval(), state.getGlobalEnvironment());
        } else if (type.equals("lazy")) {
            SIRLazy lazy = (SIRLazy) obj;
            return new SMPLLazy(lazy.getObj(), lazy.getState());
        } else {
            throw new SMPLException("Failed to Evaluate input");
        }
    }

    public Primitive evalVar(SMPLContext state, SIRVar obj) throws SMPLException {

        String type = state.getVariableEnvironment().get(obj.getVar());
        System.out.println("VAR: " + type);

        if (type.equals("arith")) {

            SIRVar<AIRExp> exp = (SIRVar<AIRExp>) obj;
            return exp.visit(eval.getArithEval(), state.getGlobalEnvironment());

        } else if (type.equals("bool")) {

            SIRVar<BoolExp> exp = (SIRVar<BoolExp>) obj;
            return exp.visit(eval.getBoolEval(), state.getGlobalEnvironment());

        } else if (type.equals("string")) {

            SIRVar<StringExp> exp = (SIRVar<StringExp>) obj;
            return exp.visit(eval.getStrEval(), state.getGlobalEnvironment());

        } else if (type.equals("char")) {

            SIRVar<CharExp> exp = (SIRVar<CharExp>) obj;
            return exp.visit(eval.getCharEval(), state.getGlobalEnvironment());

        } else if (type.equals("elist")) {

            SIRVar<EmptyListExp> exp = (SIRVar<EmptyListExp>) obj;
            return exp.visit(eval.getEmptyListEval(), state.getGlobalEnvironment());

        } else if (type.equals("pair")) {

            SIRVar<CompoundExp> exp = (SIRVar<CompoundExp>) obj;
            return exp.visit(eval.getCompoundEval(), state.getGlobalEnvironment());
        
        } else if (type.equals("vector")) {

            SIRVar<CompoundExp> exp = (SIRVar<CompoundExp>) obj;
            return exp.visit(eval.getCompoundEval(), state.getGlobalEnvironment());
        
        } else if (type.equals("tuple")) {

            SIRVar<CompoundExp> exp = (SIRVar<CompoundExp>) obj;
            return exp.visit(eval.getCompoundEval(), state.getGlobalEnvironment());

        } else if (type.equals("proc")) {

            SIRVar<CompoundExp> exp = (SIRVar<CompoundExp>) obj;
            return exp.visit(eval.getCompoundEval(), state.getGlobalEnvironment());

        } else if (type.equals("ref")) {

            SMPLRef ref = (SMPLRef) state.getGlobalEnvironment().get(obj.getVar());
            return ref.getReference();

        } else if (type.equals("lazy")) {

            SMPLLazy lazy = (SMPLLazy) state.getGlobalEnvironment().get(obj.getVar());
            return lazy.getVal(this);

        } else {
            throw new SMPLException("Unbound Variable: " + obj.getVar());
        }
    }

    public Primitive evalSIRFunction(SMPLContext state, SIRFunctionExp obj) throws SMPLException {
        String symbol = obj.getSymbol();
        SMPLFunctions cont = functions.get(symbol);
        return cont.apply(eval, state.getGlobalEnvironment(), obj);
    }

    public Primitive evalParamRef(SMPLContext state, SIRRef ref) throws SMPLException {
        SIRVar var = (SIRVar) ref.getObj();
        return new SMPLRef(var.getVar(), ref.getState());
    }
}
