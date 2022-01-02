package smpl.lang.evaluators;

import smpl.lang.*;
import smpl.lang.string.*;
import smpl.lang.visitors.StringVisitor;
import smpl.sys.SMPLException;
import smpl.values.Primitive;
import smpl.values.type.simple.SMPLString;
import smpl.sys.Environment;
import smpl.lang.arith.AIRExp;
import smpl.values.type.simple.SMPLArith;

public class StringEvaluator implements StringVisitor<StringExp, Environment<Primitive>, SMPLString> {

    private SMPLEvaluator eval;

    public StringEvaluator(SMPLEvaluator eval) {
        this.eval = eval;
    }

    @Override
    public SMPLString visitStringLit(StringLit str, Environment<Primitive> state)
            throws SMPLException {
        return str.getContext().equals("var") ? str.getVarExp().visit(this, state) : new SMPLString(str.getStr());
    }

    @Override 
    public SMPLString visitSubString(SubString subStr, Environment<Primitive> state) 
        throws SMPLException {
        StringLit str = (StringLit) subStr.getString();
        String strVal = str.visit(this, state).getPrimitive();
        AIRExp arg1 = (AIRExp) subStr.getArg1();
        AIRExp arg2 = (AIRExp) subStr.getArg2();

        Primitive arg1Prim = arg1.visit(eval.getArithEval(), state);
        Primitive arg2Prim = arg2.visit(eval.getArithEval(), state);
        SMPLArith start = (SMPLArith) arg1Prim;
        SMPLArith end = (SMPLArith) arg2Prim;
        Integer startIndex = start.getPrimitive().intValue();
        Integer endIndex = end.getPrimitive().intValue();

        if (start.getRep() == "" & end.getRep() == "") { // ensure args is integers
            if (startIndex >= 0 & endIndex <= strVal.length()) {
                return new SMPLString(strVal.substring(startIndex, endIndex));
            }
        } else {
            throw new SMPLException("Ensure last two args are integers");
        }
 

        throw new SMPLException("start index must be less than string length and end must be less or equal to the length");
    }

    @Override
    public SMPLString visitStringConcat(StringConcat strconcat, Environment<Primitive> state) 
        throws SMPLException {
        return new SMPLString(strconcat.getArg1().visit(this, state).getPrimitive() + strconcat.getArg2().visit(this, state).getPrimitive());       
    }

    @Override
    public SMPLString visitSMPLProgram(SIRProgram sp, Environment<Primitive> arg)
            throws SMPLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SMPLString visitStmtSequence(SIRSequence seq, Environment<Primitive> state)
            throws SMPLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SMPLString visitASTVar(SIRVar<StringExp> var, Environment<Primitive> state)
            throws SMPLException {
        return (SMPLString) state.get(var.getVar());
    }

    @Override
    public SMPLString visitASTBinaryExp(SIRBinaryExp<StringExp> biExp, Environment<Primitive> state)
            throws SMPLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SMPLString visitASTUnaryExp(SIRUnaryExp<StringExp> urExp, Environment<Primitive> state)
            throws SMPLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SMPLString visitSIRFunction(SIRFunctionExp<StringExp> func, Environment<Primitive> state)
            throws SMPLException {
        // TODO Auto-generated method stub
        return null;
    }

    

}
