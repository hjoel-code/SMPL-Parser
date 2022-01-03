package smpl.lang.bool;

import smpl.lang.SIRBinaryExp;
import smpl.lang.SIRUnaryExp;
import smpl.lang.evaluators.ObjectEvaluator;
import smpl.lang.visitors.BoolVisitor;
import smpl.sys.SMPLContext;
import smpl.sys.SMPLException;
import smpl.values.type.simple.SMPLBool;

public class BoolCalc extends BoolExp {

    private String calcType;
    private SIRBinaryExp<BoolExp> binary;
    private SIRUnaryExp<BoolExp> unary;


    public BoolCalc(SIRBinaryExp<BoolExp> calc) {
        this.calcType = "binary";
        this.binary = calc;
    }

    public BoolCalc(SIRUnaryExp<BoolExp> calc) {
        this.calcType = "unary";
        this.unary = calc;
    }


    public SIRUnaryExp<BoolExp> getUnary() {
        return unary;
    }


    public SIRBinaryExp<BoolExp> getBinary() {
        return binary;
    }


    public String getCalcType() {
        return calcType;
    }



    @Override
    public SMPLBool eval(SMPLContext state, ObjectEvaluator eval) throws SMPLException {
        return (SMPLBool) eval.eval(state, this);
    }

    @Override
    public <S, T> T visit(BoolVisitor<BoolExp, S, T> v, S state) throws SMPLException {
        return v.visitBoolCalc(this, state);
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
