package smpl.lang.arith;

import smpl.lang.SIRBinaryExp;
import smpl.lang.SIRUnaryExp;
import smpl.lang.evaluators.ObjectEvaluator;
import smpl.lang.visitors.AIRVisitor;
import smpl.sys.SMPLContext;
import smpl.sys.SMPLException;
import smpl.values.type.simple.SMPLArith;

public class ArithCalc extends AIRExp {

    private String calcType;
    private SIRBinaryExp<AIRExp> binary;
    private SIRUnaryExp<AIRExp> unary;


    public ArithCalc(SIRBinaryExp<AIRExp> calc) {
        this.calcType = "binary";
        this.binary = calc;
    }

    public ArithCalc(SIRUnaryExp<AIRExp> calc) {
        this.calcType = "unary";
        this.unary = calc;
    }


    public SIRUnaryExp<AIRExp> getUnary() {
        return unary;
    }


    public SIRBinaryExp<AIRExp> getBinary() {
        return binary;
    }


    public String getCalcType() {
        return calcType;
    }



    @Override
    public SMPLArith eval(SMPLContext state, ObjectEvaluator eval) throws SMPLException {
        return (SMPLArith) eval.eval(state, this);
    }

    @Override
    public <S, T> T visit(AIRVisitor<AIRExp, S, T> v, S state) throws SMPLException {
        return v.visitArithCalc(this, state);
    }

    @Override
    public String toString() {
        if (getCalcType().equals("unary")) {
            return unary.toString();
        } else {
            return binary.toString();
        }
    }
    

}
